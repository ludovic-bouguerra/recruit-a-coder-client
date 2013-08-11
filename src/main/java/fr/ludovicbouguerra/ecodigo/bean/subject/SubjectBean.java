package fr.ludovicbouguerra.ecodigo.bean.subject;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.ludovicbouguerra.ecodigo.dao.ISubjectDAO;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;

@ManagedBean
@RequestScoped
public class SubjectBean {
	
	@ManagedProperty("#{subjectViewBean}")
	private transient SubjectViewBean subjectViewBean;
	
	@EJB
	private ISubjectDAO subjectDAO;
	
	public SubjectBean() {
		
		
	}
	
	public String save(){
		
		FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
		if (subjectViewBean.getSubject() != null && !subjectViewBean.getSubject().equals("") && subjectViewBean.getTestCase().getDescription() != null && !subjectViewBean.getTestCase().getDescription().equals("")){
			subjectViewBean.addTestCase();	
		}
		
		subjectDAO.save(subjectViewBean.getSubject());
		return "list.xhtml";
	}
	
	public String remove(int id){
		FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
		subjectDAO.remove(id);
		return "list.xhtml";

	}
	
	public String edit(int id){
		subjectViewBean.setSubject(subjectDAO.findById(id));
		subjectViewBean.setTestCase(new TestCases());
		
		return "new.xhtml?faces-redirect=true&id="+id;
	}

	public List<Subject> listSubjects(){
		return subjectDAO.findAllSubjects();
	}
	

	public SubjectViewBean getSubjectViewBean() {
		return subjectViewBean;
	}

	public void setSubjectViewBean(SubjectViewBean subjectViewBean) {
		this.subjectViewBean = subjectViewBean;
	}


	
}
