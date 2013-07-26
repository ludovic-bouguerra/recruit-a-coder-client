package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import fr.ludovicbouguerra.ecodigo.model.Epreuve;

public interface IEpreuveDAO {
	
	public void create(Epreuve epreuve);
	
	public void update(Epreuve epreuve);
	
	public void remove(Epreuve epreuve);
	
	public List<Epreuve> findAllEpreuves();
	public Epreuve findById(int id);
}
