package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.User;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuveState;

@Stateless
@Local(value=IUserEpreuveDAO.class)
public class UserEpreuveDAO implements IUserEpreuveDAO {

	@PersistenceContext(unitName="codigo")
	private EntityManager em;
	
	@Override
	public UserEpreuve saveOrUpdate(UserEpreuve u) {
		UserEpreuve refreshedUe = em.merge(u);	
		em.flush();
		return refreshedUe;
	}
	
	public List<UserEpreuve> findAll(){
	    Query query = em.createQuery("SELECT u FROM UserEpreuve u");
	    return (List<UserEpreuve>) query.getResultList();
	}

	@Override
	public UserEpreuve createUserEpreuve(Epreuve epreuve, User user) {
		UserEpreuve ue = new UserEpreuve();
		ue.setState(UserEpreuveState.CREATED);
		ue.setEpreuve(epreuve);
		ue.setUser(user);
		return ue;
	}

	@Override
	public UserEpreuve findById(long id) {
		return em.find(UserEpreuve.class, id);
	}
	
}
