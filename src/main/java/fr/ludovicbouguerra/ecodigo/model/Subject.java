package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Subject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4364556202911093113L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * 
	 */
	private String title;
	
	/**
	 * 
	 */
	@Lob
	private String enonce;
	
	/**
	 * 
	 */
	private int difficulty;
	
	
	public Subject(){
		testCases = new ArrayList<TestCases>();
	}
	
	/**
	 * Liste des cas de tests
	 */
	@OneToMany(mappedBy="subject", cascade=CascadeType.ALL)
	private List<TestCases> testCases;
	
	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public List<TestCases> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCases> testCases) {
		this.testCases = testCases;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	
}
