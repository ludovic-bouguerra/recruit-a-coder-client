package fr.ludovicbouguerra.ecodigo.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;


@Entity
public class ClientAccount extends Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5889677431621144387L;
	
	/**
	 * Nombre de candidats restant à pouvoir tester
	 */
	private int candidatCount;
	
	
	private Collection<Subscription> subscriptions;

	
	public ClientAccount(){
		super();
		subscriptions = new ArrayList<Subscription>();
	}
	
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public void addSubscription(Subscription subscription){
		subscriptions.add(subscription);
	}

	public int getCandidatCount() {
		return candidatCount;
	}

	public void setCandidatCount(int candidatCount) {
		this.candidatCount = candidatCount;
	}
	
}
