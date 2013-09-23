package fr.ludovicbouguerra.ecodigo.services;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompilationServiceTest {

	private static EJBContainer container;
    private static Context ctx;
 
    @BeforeClass
    public static void setup() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
            CompilationService.class.getResource("/glassfish").getFile());
        container = EJBContainer.createEJBContainer(properties);
        ctx = container.getContext();
        
    }
    
    @Test
    public void testCompilationClient() throws NamingException{
    	ICompilationService compilationService  = (ICompilationService) ctx
    	        .lookup("java:global/codigo/classes/CompilationService");

    }
	
    @AfterClass
    public static void teardown() {
        container.close();
    }
    
}
