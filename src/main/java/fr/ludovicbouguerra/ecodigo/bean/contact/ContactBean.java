package fr.ludovicbouguerra.ecodigo.bean.contact;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean
@RequestScoped
public class ContactBean {

	private String firstName;
	private String lastName;
	private String mail;
	private String company;
	private String message;

	@Resource(name = "mail/codigo")
	private Session mailSession;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String send() {

		Message message = new MimeMessage(mailSession);
		try {
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("bouguerra.ludovic@gmail.com"));

			message.setSubject("Nouvelle inscription " + firstName + " "
					+ lastName);
			message.setText("Nouvelle inscription " + firstName + " "
					+ lastName + " " + company + " " + mail + "\n"+this.message);
			Transport.send(message);
		} catch (MessagingException e) {
		}
		return "pretty:home";

	}

}
