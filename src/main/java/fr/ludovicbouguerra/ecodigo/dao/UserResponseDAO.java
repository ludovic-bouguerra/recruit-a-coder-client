package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.UserResponse;



@Stateless
@Local(value=IUserResponseDAO.class)
public class UserResponseDAO implements IUserResponseDAO {

	@PersistenceContext(unitName="codigo")
	private EntityManager em;

	@Override
	public UserResponse findById(long id) {
		return em.find(UserResponse.class, id);
	}
	
	public void saveOrUpdate(UserResponse u){
		em.merge(u);
		em.flush();
	}
	
	
}
