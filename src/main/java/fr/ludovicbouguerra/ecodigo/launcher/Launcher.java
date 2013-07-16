package fr.ludovicbouguerra.ecodigo.launcher;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.jms.Message;
import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.compilationclient.CompilationClient;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
@ManagedBean
@RequestScoped
public class Launcher {

	private String language;
	private String code;
	private String inputData;
	private String expectedResult;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String launch() throws LanguageNotFoundException {
		try {
			if (language.equals("java")) {

				CompilationClient cc = new CompilationClient();
				String result = cc.sendCompilation(code, language,
						"Salut donnee", expectedResult);

				/*
				 * String result = java.execute(code, expectedResult);
				 */
				
				System.out.println("result");
				FacesMessage fm = new FacesMessage("Votre programme réponds aux spécifications" + result);
				fm.setSeverity(FacesMessage.SEVERITY_INFO);
				FacesContext.getCurrentInstance().addMessage(null,
						fm);
			} else {
				throw new LanguageNotFoundException();
			}
		} catch (TimeLimitExceededException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		} catch (UnexpectedResult e) {
			FacesMessage message = new FacesMessage("Résultat innatendu : "+e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null,
						message);
		}

		return "welcome.xhtml";

	}

}
