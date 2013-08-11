package fr.ludovicbouguerra.ecodigo.bean.epreuves;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import fr.ludovicbouguerra.ecodigo.dao.IEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.ISubjectDAO;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.Subject;

@ManagedBean
@ViewScoped
public class EpreuveViewBean {
    private DualListModel<Subject> subjects;
    
    private Epreuve epreuve;
    
	@EJB
	private transient ISubjectDAO subjectDAO;
    
	@EJB
	private transient IEpreuveDAO epreuveDAO;
	
	
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
    	setSubjects(new DualListModel<Subject>(subjectDAO.findAllSubjects(), epreuve.getSubjects()));
    	
    }

	public DualListModel<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(DualListModel<Subject> subjects) {
		this.subjects = subjects;
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
		epreuve.setSubjects(subjects.getTarget());
		epreuveDAO.create(epreuve);
		return "pretty:manager_epreuve_list";
	}

	
}
