package fr.ludovicbouguerra.ecodigo.dao;

import fr.ludovicbouguerra.ecodigo.model.UserResponse;

public interface IUserResponseDAO {

	public UserResponse findById(long id);
	public void saveOrUpdate(UserResponse u);
}
