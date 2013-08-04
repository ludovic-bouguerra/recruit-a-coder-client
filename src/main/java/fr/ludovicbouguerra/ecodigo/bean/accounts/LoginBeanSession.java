package fr.ludovicbouguerra.ecodigo.bean.accounts;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ludovicbouguerra.ecodigo.model.Account;

@ManagedBean
@SessionScoped
public class LoginBeanSession {

	private Account account;
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isLogged(){
		return account != null;
	}
}
