package fr.ludovicbouguerra.ecodigo.bean.epreuves;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.IEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.IUserDAO;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.User;

@ManagedBean
public class EpreuveBean {
	
	@EJB
	private IEpreuveDAO epreuveDAO;
	
	@EJB
	private IUserDAO userDAO;
	
	public EpreuveBean(){

	}
	
	
	private String inviter(String email, int epreuveID){
		User u = new User();
		u.setEmail(email);
		
		return "";
	}
	
	public String edit(int id){
		return "pretty:manager_epreuve_new?id="+id;
	}

	public IEpreuveDAO getEpreuveDAO() {
		return epreuveDAO;
	}

	public void setEpreuveDAO(IEpreuveDAO epreuveDAO) {
		this.epreuveDAO = epreuveDAO;
	}

	
	public List<Epreuve> listEpreuves(){
		return epreuveDAO.findAllEpreuves();
	}
	
}
