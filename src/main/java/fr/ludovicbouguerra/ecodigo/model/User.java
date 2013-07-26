package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218825486198460162L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
}
