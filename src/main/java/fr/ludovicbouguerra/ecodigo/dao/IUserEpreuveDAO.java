package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.User;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;

public interface IUserEpreuveDAO {
	
	public UserEpreuve createUserEpreuve(Epreuve epreuve, User user);
	
	public void saveOrUpdate(UserEpreuve u);
	
	public List<UserEpreuve> findAll();

	public UserEpreuve findById(long id); 

}
