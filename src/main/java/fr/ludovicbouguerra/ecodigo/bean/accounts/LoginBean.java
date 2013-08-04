package fr.ludovicbouguerra.ecodigo.bean.accounts;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.ludovicbouguerra.ecodigo.dao.AccountNotFoundException;
import fr.ludovicbouguerra.ecodigo.dao.IAccountDAO;

@ManagedBean
@RequestScoped
public class LoginBean {
	

	private String email;
	private String password;
	
	@EJB
	private IAccountDAO accountDAO;
	
	public LoginBean(){
		
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String retrievePassword(){
		return "pretty:retrieve-password";
		
	}
	
	public String login(){
		try {
			accountDAO.getAccountFor(email, password);
			return "home.xhtml";
		} catch (AccountNotFoundException e) {
			return "login.xhtml";
		}
	}
	
}
