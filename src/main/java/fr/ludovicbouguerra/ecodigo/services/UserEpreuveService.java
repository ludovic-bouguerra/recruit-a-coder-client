package fr.ludovicbouguerra.ecodigo.services;

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

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.User;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;

@Stateless
@Local(value = IUserEpreuveService.class)
public class UserEpreuveService implements IUserEpreuveService {

	@EJB
	private IUserEpreuveDAO userEpreuveDAO;

	@Resource(name = "mail/codigo")
	private Session mailSession;

	@Override
	public void createEpreuveForUser(Epreuve epreuve, String email) throws AddressException, MessagingException {

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

}
