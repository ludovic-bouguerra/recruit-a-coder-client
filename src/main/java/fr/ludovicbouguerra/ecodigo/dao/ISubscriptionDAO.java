package fr.ludovicbouguerra.ecodigo.dao;

import fr.ludovicbouguerra.ecodigo.model.ClientAccount;

public interface ISubscriptionDAO {
	
	public void subscribe(ClientAccount account, int offerId) throws OfferNotFoundException;
	
}
