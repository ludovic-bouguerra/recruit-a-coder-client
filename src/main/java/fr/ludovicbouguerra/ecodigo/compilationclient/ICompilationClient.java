package fr.ludovicbouguerra.ecodigo.compilationclient;

import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public interface ICompilationClient {
	

	
	public String sendCompilation(String code, String language, String inputData, String expectedResult) throws TimeLimitExceededException, UnexpectedResult;
	
}
