package fr.ludovicbouguerra.ecodigo.bean.epreuves;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.IEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;

@ManagedBean
public class EpreuveBean {
	
	@EJB
	private IEpreuveDAO epreuveDAO;
	

	private Epreuve epreuve;
	
	public EpreuveBean(){
		epreuve = new Epreuve();
	}
	
	public String save(){
		epreuveDAO.create(epreuve);
		return "list.xhtml";
	}
	
	public String edit(){
		epreuveDAO.update(epreuve);
		return "list.xhtml";
	}

	public IEpreuveDAO getEpreuveDAO() {
		return epreuveDAO;
	}

	public void setEpreuveDAO(IEpreuveDAO epreuveDAO) {
		this.epreuveDAO = epreuveDAO;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	public List<Epreuve> listEpreuves(){
		return epreuveDAO.findAllEpreuves();
	}
	
}
