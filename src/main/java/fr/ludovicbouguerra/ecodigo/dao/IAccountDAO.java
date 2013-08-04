package fr.ludovicbouguerra.ecodigo.dao;

import fr.ludovicbouguerra.ecodigo.model.Account;

public interface IAccountDAO {

	public void register(Account account);
	
	public Account getAccountFor(String email, String password) throws AccountNotFoundException;

}
