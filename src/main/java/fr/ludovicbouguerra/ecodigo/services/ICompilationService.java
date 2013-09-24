package fr.ludovicbouguerra.ecodigo.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

public interface ICompilationService {

	String sendCompilation(String code, String language,
			String inputData, String expectedResult) throws TimeLimitExceededException, UnexpectedResult;
	
}
