package fr.ludovicbouguerra.ecodigo.bean.front;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.TimeLimitExceededException;


import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.launcher.LanguageNotFoundException;
import fr.ludovicbouguerra.ecodigo.model.TestCases;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;
import fr.ludovicbouguerra.ecodigo.services.ICompilationService;

@ManagedBean
@SessionScoped
public class LauncherBeanSession {

	
	private UserEpreuve epreuve;
	



	private List<UserResponse> responses;


	private List<TestCases> testCases;
	
	public List<TestCases> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCases> testCases) {
		this.testCases = testCases;
	}

	@EJB
	private ICompilationService compilationService;
	
	
	private int index;
		
	
	public LauncherBeanSession(){
		index = 0;
		responses = new ArrayList<UserResponse>();
		testCases = new ArrayList<TestCases>();
	}
	
	public String save(){
		
		
		
		return "";
		
	}
	
	public int getPage(){
		return index+1;
	}
	
	public void nextStep(){
		index ++;
		if (index >= responses.size()){
			index = 0;
		}
	}
	
	public void previousStep(){
		index --;
		if (index < 0){
			index = responses.size()-1;
		}
	}
	
	public List<UserResponse> getResponses() {
		return responses;
	}

	
	public int getExerciceCount(){
		return responses.size();
	}
	
	public void setResponses(List<UserResponse> responses) {
		this.responses = responses;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int page) {
		this.index = page;
	}


	public String getCode() {
		return responses.get(index).getCode();
	}


	public void setCode(String code) {
		responses.get(index).setCode(code);
	}
	
	public void launch() throws LanguageNotFoundException{
		try {
			if (responses.get(index).getLanguage().equals("java")) {

				String inputData = testCases.get(index).getInput();
				String expectedResult = testCases.get(index).getExpected();
				
				System.out.println("result");

				String result = compilationService.sendCompilation(getCode(), responses.get(index).getLanguage(),
						inputData, expectedResult);

				
				System.out.println("result");
				
				responses.get(index).setResponse(result);
				responses.get(index).setValid(true);
			} else {
				throw new LanguageNotFoundException();
			}
		} catch (TimeLimitExceededException e) {
			responses.get(index).setValid(false);
			responses.get(index).setResponse("Timeout");

		} catch (UnexpectedResult e) {
			responses.get(index).setValid(false);
			responses.get(index).setResponse(e.getMessage());
						
		}

	}





	
	public UserEpreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(UserEpreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	
}
