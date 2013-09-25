package fr.ludovicbouguerra.ecodigo.bean.candidats;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.services.IUserEpreuveService;

@ManagedBean
@RequestScoped
public class CandidatResultBean {

	private List<UserEpreuve> userEpreuves;

	@EJB
	private IUserEpreuveDAO userEpreuveDAO;

	@EJB
	private IUserEpreuveService userEpreuveService;
	
	private UserEpreuve epreuve;

	private int epreuveScore;

	@PostConstruct
	public void init() {
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

	public String showResult() {
		if (epreuve != null) {
			setEpreuveScore(userEpreuveService.getScoreForUserEpreuve(epreuve));
			return "result.xhtml";
		}
		return "results.xhtml";

	}

	public void setEpreuve(UserEpreuve epreuve) {
		this.epreuve = epreuve;
	}

	public int getEpreuveScore() {
		return epreuveScore;
	}

	public void setEpreuveScore(int epreuveScore) {
		this.epreuveScore = epreuveScore;
	}

}
