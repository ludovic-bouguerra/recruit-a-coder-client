package fr.ludovicbouguerra.ecodigo.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;

public interface IUserEpreuveService {

	/**
	 * 
	 * @return Url for the epreuve
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public void createEpreuveForUser(Epreuve epreuve, String email) throws AddressException, MessagingException;
	
	public void send(UserEpreuve ue);

	UserEpreuve initEpreuve(int id) throws EpreuveAlreadyFinishedException, EpreuveNotFound;

	public int getScoreForUserEpreuve(UserEpreuve ue);

	
}
