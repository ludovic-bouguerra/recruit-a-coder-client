package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.ludovicbouguerra.ecodigo.model.Subject;

@Stateless
@Local(value=ISubjectDAO.class)
public class SubjectDAO implements ISubjectDAO{

	@PersistenceContext(unitName="codigo")
	EntityManager em;
	
	@Override
	public void createSubject(Subject subject) {
		em.persist(subject);
		em.flush();
	}
	
	public Subject findById(int id){
		return em.find(Subject.class, id);
	}
	
	
	
	public void remove(int id){
		em.remove(findById(id));
	}
	
	public List<Subject> findAllSubjects(){
	    Query query = em.createQuery("SELECT s FROM Subject s");
	    return (List<Subject>) query.getResultList();
	}

}
