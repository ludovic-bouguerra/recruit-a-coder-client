package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1746482424436032832L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private boolean active;
	
	private double price;


	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
