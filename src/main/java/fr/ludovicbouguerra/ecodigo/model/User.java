package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218825486198460162L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	

	/**
	 * Candidate E-mail
	 */
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
