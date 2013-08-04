package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;

@Stateless
@Local(value=IUserEpreuveDAO.class)
public class UserEpreuveDAO implements IUserEpreuveDAO {

	@PersistenceContext(unitName="codigo")
	private EntityManager em;
	
	@Override
	public void saveOrUpdate(UserEpreuve u) {
		em.merge(u);	
	}
	
}
