package fr.ludovicbouguerra.ecodigo.bean.accounts;



import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import fr.ludovicbouguerra.ecodigo.dao.IAccountDAO;
import fr.ludovicbouguerra.ecodigo.dao.ISubscriptionDAO;
import fr.ludovicbouguerra.ecodigo.dao.OfferNotFoundException;
import fr.ludovicbouguerra.ecodigo.model.ClientAccount;
import fr.ludovicbouguerra.ecodigo.services.IPayement;

@ManagedBean
@SessionScoped
public class AccountBean {

	private ClientAccount account;
	private int formule;

	@EJB
	private transient IAccountDAO accountDAO;
	
	@EJB
	private transient ISubscriptionDAO subscriptionDAO;
	
	@EJB
	private transient IPayement paymentService;
	
	public AccountBean() {
		account = new ClientAccount();
	}

	/**
	 * 
	 * @return
	 */
	public String register() {
		if (isAccountValide()) {
			return "pretty:validation";
		} else {
			return "pretty:register";
		}

	}

	/**
	 * 
	 * @return
	 */
	public String validate() {

		//paymentService.paymentInstruction(amount, urlSuccess, urlCancel);
		accountDAO.register(account);
		try {
			subscriptionDAO.subscribe(account, formule);
		} catch (OfferNotFoundException e) {
			
		}
		account = new ClientAccount();
		return "pretty:valide";
	}

	/**
	 * 
	 * 
	 */
	public String subscribeOffer() {
		return "";
	}

	public ClientAccount getAccount() {
		return account;
	}

	private boolean isAccountValide() {
		return account.getLastName() != null;
	}

	public void setAccount(ClientAccount account) {
		this.account = account;
	}

	public int getFormule() {
		return formule;
	}

	public void setFormule(int formule) {
		this.formule = formule;
	}

}
