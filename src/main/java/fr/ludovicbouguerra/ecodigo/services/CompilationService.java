package fr.ludovicbouguerra.ecodigo.services;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.TimeLimitExceededException;

import fr.ludovicbouguerra.ecodigo.compilationclient.CompilationClient;
import fr.ludovicbouguerra.ecodigo.compilationclient.ICompilationClient;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

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
	public String sendCompilation(String code, String language,
			String inputData, String expectedResult) throws TimeLimitExceededException, UnexpectedResult {		
		return compilation.sendCompilation(code, language, inputData, expectedResult);
	}

}
