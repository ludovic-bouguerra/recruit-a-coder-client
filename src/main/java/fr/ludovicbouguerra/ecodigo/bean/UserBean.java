package fr.ludovicbouguerra.ecodigo.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ludovicbouguerra.ecodigo.model.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private User user;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
