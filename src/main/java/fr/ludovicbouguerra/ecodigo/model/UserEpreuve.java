package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserEpreuve implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457378824768849349L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Epreuve epreuve;

	
    @Enumerated(EnumType.STRING)
	private UserEpreuveState state;
	
	@Temporal(TemporalType.DATE)
	private Date dateBegin;

	@Temporal(TemporalType.DATE)
	private Date dateEnd;

	@OneToMany(mappedBy="userEpreuve", targetEntity=UserResponse.class)
	private List<UserResponse> userReponses;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}


	public List<UserResponse> getUserReponses() {
		return userReponses;
	}

	public void setUserReponses(List<UserResponse> userReponses) {
		this.userReponses = userReponses;
	}

	public UserEpreuveState getState() {
		return state;
	}

	public void setState(UserEpreuveState state) {
		this.state = state;
	}

}
