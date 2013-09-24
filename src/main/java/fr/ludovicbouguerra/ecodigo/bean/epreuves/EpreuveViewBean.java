package fr.ludovicbouguerra.ecodigo.bean.epreuves;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import fr.ludovicbouguerra.ecodigo.dao.IEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.ISubjectDAO;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.Subject;

@ManagedBean
@ViewScoped
public class EpreuveViewBean {
    
    private Epreuve epreuve;
	@EJB
	private transient ISubjectDAO subjectDAO;
    
	@EJB
	private transient IEpreuveDAO epreuveDAO;


	private List<Subject> subjectsAvailable;
	private Subject[] subjectsSelected;

    public Subject[] getSubjectsSelected() {
		return subjectsSelected;
	}

	public void setSubjectsSelected(Subject[] subjectsSelected) {
		this.subjectsSelected = subjectsSelected;
	}

	public EpreuveViewBean(){
    	
    }
    
    @PostConstruct
    public void init(){
		Integer id = null;
		try{
			id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( "id" ));
		} catch (NumberFormatException e){
			
		}
		if (id == null){
			epreuve = new Epreuve();
		}else {
			epreuve = epreuveDAO.findById(id);
		}
		setSubjectsAvailable(subjectDAO.findAllSubjects());
		subjectsSelected = epreuve.getSubjects().toArray(new Subject[epreuve.getSubjects().size()]);
    }

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	
	public List<Subject> getSubjectList(){
		return subjectDAO.findAllSubjects();
	}

	
	public String save(){
		
		epreuve.getSubjects().clear();
		for (Subject subject: subjectsSelected) {
			epreuve.addSubject(subjectDAO.findById(subject.getId()));
		}
		epreuveDAO.update(epreuve);
		return "pretty:manager_epreuve_list";
	}

	public List<Subject> getSubjectsAvailable() {
		return subjectsAvailable;
	}

	public void setSubjectsAvailable(List<Subject> subjectsAvailable) {
		this.subjectsAvailable = subjectsAvailable;
	}




	
}
