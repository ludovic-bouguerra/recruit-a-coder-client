package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7559269709997307553L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity=UserEpreuve.class)
	private UserEpreuve userEpreuve;
	
	@OneToMany(mappedBy="userResponse", targetEntity=UserResponseTestCase.class)
	private Collection<UserResponseTestCase> testCases;
	

	@ManyToOne(targetEntity=Subject.class)
	private Subject subject;
	private String code;
	private boolean valid;
	private String language;
	private String response;

	@Temporal(TemporalType.DATE)
	private Date dateBegin;
	
	@Temporal(TemporalType.DATE)
	private Date dateEnd;


	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UserEpreuve getUserEpreuve() {
		return userEpreuve;
	}

	public void setUserEpreuve(UserEpreuve userEpreuve) {
		this.userEpreuve = userEpreuve;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public Collection<UserResponseTestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Collection<UserResponseTestCase> testCases) {
		this.testCases = testCases;
	}

	
	
}
