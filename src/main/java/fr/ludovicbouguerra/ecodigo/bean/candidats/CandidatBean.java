package fr.ludovicbouguerra.ecodigo.bean.candidats;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.User;

@ManagedBean
@RequestScoped
public class CandidatBean {
	private User user;
	private Epreuve epreuve;

	@Resource(name = "mail/codigo")
	private Session mailSession;

	public CandidatBean() {
		user = new User();
		epreuve = new Epreuve();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public void save() {
		try {
			Message message = new MimeMessage(mailSession);

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("Invitation E-CODIGO");
			message.setText("Vous êtes invités à répondre à ce questionnaire"
					+ " Merci de prévoir environs 2 heures ");

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
