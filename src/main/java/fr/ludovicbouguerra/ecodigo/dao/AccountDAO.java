package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.ludovicbouguerra.ecodigo.model.Account;

@Stateless
@Local(value=IAccountDAO.class)
public class AccountDAO implements IAccountDAO{

	@PersistenceContext(unitName="codigo")
	private EntityManager em; 
	
	public Account getAccountFor(String email, String password) throws AccountNotFoundException{
		Query q = em.createQuery("SELECT a FROM Account a WHERE a.email =:email AND a.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		
		if (q.getResultList().isEmpty()){
			throw new AccountNotFoundException();
		}else{
			return (Account) q.getSingleResult();
		}
			
		
		
	}
	
	@Override
	public void register(Account account) {
		em.persist(account);
		em.flush();
		
	}
	
}
