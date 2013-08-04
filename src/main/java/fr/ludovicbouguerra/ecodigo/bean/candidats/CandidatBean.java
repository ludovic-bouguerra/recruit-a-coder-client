package fr.ludovicbouguerra.ecodigo.bean.candidats;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.ludovicbouguerra.ecodigo.model.User;

@ManagedBean
@RequestScoped
public class CandidatBean {
	private User user;
	
	public CandidatBean(){
		user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public void save(){
		
	}
}
