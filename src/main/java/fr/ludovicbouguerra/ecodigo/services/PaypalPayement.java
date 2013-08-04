package fr.ludovicbouguerra.ecodigo.services;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(value=IPayement.class)
public class PaypalPayement implements IPayement{

	@Override
	public void paymentInstruction(double amount, String urlSuccess,
			String urlCancel) {
		
		
	}

}
