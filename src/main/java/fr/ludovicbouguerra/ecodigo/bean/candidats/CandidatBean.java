package fr.ludovicbouguerra.ecodigo.bean.candidats;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.mail.MessagingException;


import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.services.IUserEpreuveService;

@ManagedBean
@RequestScoped
public class CandidatBean {
	private String email;

	private Epreuve epreuve;



	@EJB
	private IUserEpreuveService userEpreuveService;
	
	public CandidatBean() {
		epreuve = new Epreuve();
	}
	
	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void save() {
		try {
			userEpreuveService.createEpreuveForUser(epreuve, email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
