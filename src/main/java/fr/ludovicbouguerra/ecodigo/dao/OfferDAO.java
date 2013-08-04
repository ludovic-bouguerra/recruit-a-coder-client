package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.Offer;

@Stateless
@Local(value=IOfferDAO.class)
public class OfferDAO implements IOfferDAO{

	@PersistenceContext(unitName="codigo")
	private EntityManager em;
	
	@Override
	public Offer findById(int id) throws OfferNotFoundException {
		
		Offer o = em.find(Offer.class, id);
		if (o==null || !o.isActive()){
			throw new OfferNotFoundException();
		}else {
			return o;	
		}
		
	}
	
	
	
}
