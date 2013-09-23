package fr.ludovicbouguerra.ecodigo.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.compilationclient.CompilationClient;
import fr.ludovicbouguerra.ecodigo.compilationclient.ICompilationClient;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.result.IResult;

@Stateless
@Local(value = ICompilationService.class)
public class CompilationService implements ICompilationService{

	@Resource(lookup = "jndi/codigo/messenger-server")
	private String messengerServer;
	
	@Resource(lookup = "jndi/codigo/messenger-queue")
	private String messengerQueue;
	
	private ICompilationClient compilation;
	
	
	@PostConstruct
	public void init(){
		compilation = new CompilationClient(messengerServer, messengerQueue); 
	}
	
	@Override
	public Collection<IResult> sendCompilation(String code, String language,
			ArrayList<String> inputData, ArrayList<String> expectedResult) throws TimeLimitExceededException, UnexpectedResult {		
		return compilation.sendCompilation(code, language, inputData, expectedResult);
	}

}
