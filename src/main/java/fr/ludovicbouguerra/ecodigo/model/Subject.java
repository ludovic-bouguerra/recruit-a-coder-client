package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.TIME)
	private Date timeLimit;

	
	@ManyToMany(mappedBy="subjects")
	private List<Epreuve> epreuves;
	


	public Subject(){
		testCases = new ArrayList<TestCases>();
		epreuves = new ArrayList<Epreuve>();
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
	
	public void addTestCase(TestCases testCase){
		testCase.setSubject(this);
		testCases.add(testCase);
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public List<Epreuve> getEpreuves() {
		return epreuves;
	}

	public void setEpreuves(List<Epreuve> epreuves) {
		this.epreuves = epreuves;
	}
	
	public void addEpreuve(Epreuve epreuve){
		epreuves.add(epreuve);
	}
	

	
}
