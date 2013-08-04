package fr.ludovicbouguerra.ecodigo.dao;

import fr.ludovicbouguerra.ecodigo.model.Offer;

public interface IOfferDAO {
	
	
	public Offer findById(int id) throws OfferNotFoundException;
}
