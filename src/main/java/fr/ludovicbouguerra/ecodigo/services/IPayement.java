package fr.ludovicbouguerra.ecodigo.services;

public interface IPayement {

	public void paymentInstruction(double amount, String urlSuccess, String urlCancel);
	
}
