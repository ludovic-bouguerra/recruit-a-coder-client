package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class TestCases implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3922193323787485613L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID", nullable=false)
	private Subject subject;
	
	@Lob
	private String description;
	
	@Lob
	private String input;
	
	@Lob
	private String expected;
	
	/**
	 * Visible or not for the user
	 */
	private boolean visible;
	
	public Subject getSuject() {
		return subject;
		
	}

	public void setSujet(Subject subject) {
		this.subject = subject;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
