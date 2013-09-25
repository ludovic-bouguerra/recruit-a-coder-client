package fr.ludovicbouguerra.ecodigo.services;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.IUserResponseTestCaseDAO;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;
import fr.ludovicbouguerra.ecodigo.model.User;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuveState;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;
import fr.ludovicbouguerra.ecodigo.model.UserResponseTestCase;

@Stateless
@Local(value = IUserEpreuveService.class)
public class UserEpreuveService implements IUserEpreuveService {

	@EJB
	private IUserEpreuveDAO userEpreuveDAO;

	@EJB
	private IUserResponseTestCaseDAO userResponseTestCaseDAO;

	@EJB
	private ICompilationService compilationService;

	@Resource(name = "mail/codigo")
	private Session mailSession;

	@Override
	public void createEpreuveForUser(Epreuve epreuve, String email)
			throws AddressException, MessagingException {

		User user = new User();
		user.setEmail(email);
		UserEpreuve ue = userEpreuveDAO.createUserEpreuve(epreuve, user);
		Message message = new MimeMessage(mailSession);
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));

		message.setSubject("Invitation E-CODIGO");
		message.setText("Vous êtes invités à répondre à ce questionnaire"
				+ " Merci de prévoir environs 2 heures ");

		userEpreuveDAO.saveOrUpdate(ue);
		Transport.send(message);
	}

	@Override
	public UserEpreuve initEpreuve(int id)
			throws EpreuveAlreadyFinishedException, EpreuveNotFound {

		UserEpreuve ue = userEpreuveDAO.findById(id);
		if (ue != null) {
			if (ue.getState().equals(UserEpreuveState.FINISHED)) {
				throw new EpreuveAlreadyFinishedException();
			} else if (ue.getState().equals(UserEpreuveState.STARTED)) {
				return ue;
			} else if (ue.getState().equals(UserEpreuveState.CREATED)) {
				ue.setDateBegin(new Date());
				ue.setState(UserEpreuveState.STARTED);
				ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
				for (Subject sub : ue.getEpreuve().getSubjects()) {
					UserResponse u = new UserResponse();
					u.setUserEpreuve(ue);
					u.setSubject(sub);
					u.setCode("");
					u.setLanguage("java");
					u.setValid(false);
					u.setDateBegin(new Date());
					userResponses.add(u);
					ue.setUserReponses(userResponses);
				}

				ue = userEpreuveDAO.saveOrUpdate(ue);
				return ue;
			}
		}
		throw new EpreuveNotFound();

	}

	/**
	 * Calling when the user validate his response
	 */
	@Override
	public void send(UserEpreuve ue) {
		ue.setState(UserEpreuveState.FINISHED);
		ue.setDateEnd(new Date());

		for (UserResponse userResponse : ue.getUserReponses()) {
			for (TestCases testsCases : userResponse.getSubject()
					.getTestCases()) {

				boolean result = true;

				try {
					compilationService.sendCompilation(userResponse.getCode(),
							userResponse.getLanguage(), testsCases.getInput(),
							testsCases.getExpected());
				} catch (TimeLimitExceededException e) {
					result = false;
				} catch (UnexpectedResult e) {
					result = false;
				}

				userResponseTestCaseDAO.createUserEpreuve(userResponse,
						testsCases, result);
			}
		}

		userEpreuveDAO.saveOrUpdate(ue);
	}

	public int getScoreForUserEpreuve(UserEpreuve ue) {

		int totalTests = 0;
		int score = 0;

		for (UserResponse responses : ue.getUserReponses()) {
			for (UserResponseTestCase userResponseTests : responses
					.getTestCases()) {
				if (userResponseTests.isValid()) {
					score++;
				}
				totalTests++;
			}
		}

		/**
		 * Result in percentages
		 */
		return score / totalTests * 100;
	}

}
