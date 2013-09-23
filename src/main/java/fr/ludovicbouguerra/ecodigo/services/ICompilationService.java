package fr.ludovicbouguerra.ecodigo.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.result.IResult;

public interface ICompilationService {

	Collection<IResult> sendCompilation(String code, String language,
			ArrayList<String> inputData, ArrayList<String> expectedResult) throws TimeLimitExceededException, UnexpectedResult;
	
}
