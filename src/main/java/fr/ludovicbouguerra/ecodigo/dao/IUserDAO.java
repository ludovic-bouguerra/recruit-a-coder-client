package fr.ludovicbouguerra.ecodigo.dao;

import fr.ludovicbouguerra.ecodigo.model.User;


public interface IUserDAO {
	public void saveOrUpdate(User user);
}
