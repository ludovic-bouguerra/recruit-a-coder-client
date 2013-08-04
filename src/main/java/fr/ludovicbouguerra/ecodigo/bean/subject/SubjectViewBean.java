package fr.ludovicbouguerra.ecodigo.bean.subject;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.ludovicbouguerra.ecodigo.dao.ISubjectDAO;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;


@ManagedBean
@ViewScoped
public class SubjectViewBean {

	@EJB
	private ISubjectDAO subjectDAO;
	
	
	private Subject subject;
	private TestCases testCase;
	
	public SubjectViewBean(){

	}
	
	@PostConstruct
	public void init(){
		Integer id = null;
		try{
			id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( "id" ));
		} catch (NumberFormatException e){
			
		}
		if (id == null){
			subject = new Subject();
		}else {
			subject = subjectDAO.findById(id);
		}
		testCase = new TestCases();
	}
	
	public void addTestCase() {
		subject.addTestCase(testCase);
		testCase = new TestCases();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public TestCases getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCases testCase) {
		this.testCase = testCase;
	}
	

	

}
