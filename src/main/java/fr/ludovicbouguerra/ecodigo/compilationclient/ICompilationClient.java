package fr.ludovicbouguerra.ecodigo.compilationclient;

import java.util.ArrayList;

import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public interface ICompilationClient {
	

	
	public String sendCompilation(String code, String language, ArrayList<String> inputData, ArrayList<String> expectedResult) throws TimeLimitExceededException, UnexpectedResult;
	
}
