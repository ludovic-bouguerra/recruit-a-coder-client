package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserEpreuve implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457378824768849349L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private User user;
	private Epreuve epreuve;
	
	@Temporal(TemporalType.DATE)
	private Date dateBegin;
	
	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}	
	
	
}
