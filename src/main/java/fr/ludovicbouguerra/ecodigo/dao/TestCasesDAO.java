package fr.ludovicbouguerra.ecodigo.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ludovicbouguerra.ecodigo.model.TestCases;

@Stateless
@Local(value=ITestCaseDAO.class)
public class TestCasesDAO implements ITestCaseDAO{
    
	@PersistenceContext
	EntityManager em;
	


	public void createTestCase(int id, TestCases test) {
		test.setId(id);
		em.persist(test);
		
	}
}
