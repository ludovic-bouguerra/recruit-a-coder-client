package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.ClientAccount;
import fr.ludovicbouguerra.ecodigo.model.Offer;
import fr.ludovicbouguerra.ecodigo.model.Subscription;

@Stateless
@Local(value=ISubscriptionDAO.class)
public class SubscriptionDao implements ISubscriptionDAO{

	@PersistenceContext(unitName="codigo")
	private EntityManager em;
	
	@EJB
	private IOfferDAO offerDAO;
	
	@Override
	public void subscribe(ClientAccount account, int offerId) throws OfferNotFoundException {
		
		Subscription subscription = new Subscription();
		subscription.setAccount(account);
		Offer o = offerDAO.findById(offerId);		
		subscription.setOffer(o);
		subscription.setPrice(o.getPrice());
		em.persist(subscription);
		
	}

}
