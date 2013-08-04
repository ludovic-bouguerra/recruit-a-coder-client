package fr.ludovicbouguerra.ecodigo.model;

import javax.persistence.Entity;

@Entity
public class CandidateOffer extends Offer implements ICandidateOffer{

	private int numberCandidateAllowed;

	@Override
	public int getNumberCandidateAllowed() {
		return numberCandidateAllowed;
	}
	
	public void setNumberCandidateAllowed(int numberCandidateAllowed){
		this.numberCandidateAllowed = numberCandidateAllowed;
	}
}
