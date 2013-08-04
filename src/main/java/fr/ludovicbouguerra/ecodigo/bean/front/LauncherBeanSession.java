package fr.ludovicbouguerra.ecodigo.bean.front;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.compilationclient.CompilationClient;
import fr.ludovicbouguerra.ecodigo.compilationclient.ICompilationClient;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.launcher.LanguageNotFoundException;
import fr.ludovicbouguerra.ecodigo.model.CheckedTestCase;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;

@ManagedBean
@SessionScoped
public class LauncherBeanSession {

	private List<UserResponse> responses;
	private List<List<CheckedTestCase>> checkedTestCases;
	
	
	private int index;
		
	
	public LauncherBeanSession(){
		index = 0;
		responses = new ArrayList<UserResponse>();
		checkedTestCases = new ArrayList<List<CheckedTestCase>>();
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

				ArrayList<String> inputData = new ArrayList<String>();
				ArrayList<String> expectedResult = new ArrayList<String>();
				
				for (CheckedTestCase testCase : checkedTestCases.get(index)){
					if (testCase.isChecked()){
						inputData.add(testCase.getTestCase().getInput());
						expectedResult.add(testCase.getTestCase().getExpected());
					}
				}
				System.out.println(responses.get(index).getLanguage());
				System.out.println(inputData.size());
				System.out.println(expectedResult.size());
				System.out.println("result");

				ICompilationClient cc = new CompilationClient();
				String result = cc.sendCompilation(getCode(), responses.get(index).getLanguage(),
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



	public List<List<CheckedTestCase>> getCheckedTestCases() {
		return checkedTestCases;
	}



	public void setCheckedTestCases(List<List<CheckedTestCase>> checkedTestCases) {
		this.checkedTestCases = checkedTestCases;
	}

}
