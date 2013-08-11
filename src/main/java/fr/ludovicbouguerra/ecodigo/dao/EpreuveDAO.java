package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;

@Stateless
@Local(value=IEpreuveDAO.class)
public class EpreuveDAO implements IEpreuveDAO{

	@PersistenceContext(unitName="codigo")
	private EntityManager em;
	
	public void create(Epreuve epreuve){
		em.persist(epreuve);
		em.flush();
	}
	
	public void update(Epreuve epreuve){
		em.merge(epreuve);
	}
	
	public void remove(int id){
		em.remove(findById(id));
	}
	
	public Epreuve findById(int id){
		return (Epreuve) em.find(Epreuve.class, id);
	}

	public List<Epreuve> findAllEpreuves(){
	    Query query = em.createQuery("SELECT e FROM Epreuve e");
	    return (List<Epreuve>) query.getResultList();
	}
}
