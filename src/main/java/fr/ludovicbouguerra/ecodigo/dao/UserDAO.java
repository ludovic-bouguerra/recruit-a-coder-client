package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.User;

@Stateless
@Local(value=IUserDAO.class)
public class UserDAO implements IUserDAO{

	@PersistenceContext(unitName="codigo")
	EntityManager em;
	
	
	@Override
	public void saveOrUpdate(User user) {
		em.merge(user);
		em.flush();
	}
	
	
	
	
}
