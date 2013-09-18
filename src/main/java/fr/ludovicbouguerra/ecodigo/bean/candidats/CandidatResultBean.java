package fr.ludovicbouguerra.ecodigo.bean.candidats;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;

@ManagedBean
@RequestScoped
public class CandidatResultBean {

	private List<UserEpreuve> userEpreuves;

	@EJB
	private IUserEpreuveDAO userEpreuveDAO;
	
	
	private UserEpreuve epreuve;

	@PostConstruct
	public void init(){
		userEpreuves = userEpreuveDAO.findAll(); 
	}
	
	public List<UserEpreuve> getUserEpreuves() {
		return userEpreuves;
	}

	public void setUserEpreuves(List<UserEpreuve> userEpreuves) {
		this.userEpreuves = userEpreuves;
	}

	public UserEpreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(UserEpreuve epreuve) {
		this.epreuve = epreuve;
	}

}
