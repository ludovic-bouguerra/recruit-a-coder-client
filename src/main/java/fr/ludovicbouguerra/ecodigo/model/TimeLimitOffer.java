package fr.ludovicbouguerra.ecodigo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TimeLimitOffer extends Offer implements ITimeLimitOffer{

	@Temporal(TemporalType.DATE)
	private Date dateLimit;
	
	@Override
	public Date getDateLimit() {
		return dateLimit;
	}
	
	public void setDateLimit(Date dateLimit){
		this.dateLimit = dateLimit;
	}

}
