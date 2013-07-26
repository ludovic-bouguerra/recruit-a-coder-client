package fr.ludovicbouguerra.ecodigo.bean;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.ISubjectDAO;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;

@ManagedBean
@RequestScoped
public class SubjectBean {
	
	
	private Subject subject;
	private TestCases test;

	
	@EJB
	private transient ISubjectDAO subjectDAO;
	
	public SubjectBean() {
		subject = new Subject();
		test = new TestCases();
	}
	
	public String save(){
		
		FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
		if (test.getSubject() != null && !test.getSubject().equals("") && test.getDescription() != null && !test.getDescription().equals("")){
			subject.getTestCases().add(test);	
		}
		
		subjectDAO.createSubject(subject);
		return "list.xhtml";
	}
	
	public String remove(int id){
		FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
		subjectDAO.remove(id);
		return "list.xhtml";

	}
	
	public String edit(int id){
		subject = subjectDAO.findById(id);
		test = new TestCases();
		return "new.xhtml";
	}

	public List<Subject> listSubjects(){
		return subjectDAO.findAllSubjects();
	}


	
	public void addTestCase(){
		subject.getTestCases().add(test);
		test = new TestCases();
	}
	


	public Subject getSubject() {
		return subject;
	}



	public void setSubject(Subject subject) {
		this.subject = subject;
	}



	public TestCases getTest() {
		return test;
	}



	public void setTest(TestCases test) {
		this.test = test;
	}
		
	


	
	
}
