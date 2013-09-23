package fr.ludovicbouguerra.ecodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserResponseTestCase {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity=UserResponse.class)
	private UserResponse userResponse;
	
	@ManyToOne(targetEntity=TestCases.class)
	private TestCases testCase;

	private boolean valid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserResponse getUserResponse() {
		return userResponse;
	}
	public void setUserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}
	public TestCases getTestCase() {
		return testCase;
	}
	public void setTestCase(TestCases testCase) {
		this.testCase = testCase;
	}
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
