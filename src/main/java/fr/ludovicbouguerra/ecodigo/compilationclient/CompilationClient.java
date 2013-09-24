package fr.ludovicbouguerra.ecodigo.compilationclient;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.sun.mail.iap.Response;
import com.sun.xml.ws.policy.privateutil.PolicyUtils.Collections;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

import javax.jms.*;
import javax.naming.TimeLimitExceededException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class CompilationClient implements ICompilationClient{

	    private int ackMode;
	    private String clientQueueName;
	 
	    private boolean transacted = false;
	    private MessageProducer producer;

	    private String server;
	    
	    public CompilationClient(String server, String clientQueueName) {
	    	this.server = server;
	    	this.clientQueueName = clientQueueName;
	        ackMode = Session.AUTO_ACKNOWLEDGE;
	    }
	 
	    private String createRandomString() {
	        Random random = new Random(System.currentTimeMillis());
	        long randomLong = random.nextLong();
	        return Long.toHexString(randomLong);
	    }
	 

		@Override
		public String sendCompilation(String code, String language,
				String inputData, String expectedResult) throws TimeLimitExceededException, UnexpectedResult {
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server);
	        Connection connection;
	        try {
	            connection = connectionFactory.createConnection();
	            connection.start();
	            Session session = connection.createSession(transacted, ackMode);
	            Destination adminQueue = session.createQueue(clientQueueName);
	 
	            System.out.println("ok");
	            //Setup a message producer to send message to the queue the server is consuming from
	            this.producer = session.createProducer(adminQueue);
	            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	 
	            //Create a temporary queue that this client will listen for responses on then create a consumer
	            //that consumes message from this temporary queue...for a real application a client should reuse
	            //the same temp queue for each message to the server...one temp queue per client
	            Destination tempDest = session.createTemporaryQueue();
	            MessageConsumer responseConsumer = session.createConsumer(tempDest);
	 
	            //This class will handle the messages to the temp queue as well
	            
	 
	            //Now create the actual message you want to send
	            Message message = session.createMessage();
	            message.setStringProperty("code", code);
	            message.setStringProperty("input-data", inputData);
	            message.setStringProperty("expected-result", expectedResult);
	            message.setStringProperty("language", language);
	            
	 
	            //Set the reply to field to the temp queue you created above, this is the queue the server
	            //will respond to
	            message.setJMSReplyTo(tempDest);
	 
	            //Set a correlation ID so when you get a response you know which sent message the response is for
	            //If there is never more than one outstanding message to the server then the
	            //same correlation ID can be used for all the messages...if there is more than one outstanding
	            //message to the server you would presumably want to associate the correlation ID with this
	            //message somehow...a Map works good
	            String correlationId = this.createRandomString();
	            message.setJMSCorrelationID(correlationId);
	            this.producer.send(message);
	            System.out.println("Awaiting response");
	            Message response = responseConsumer.receive();
	            System.out.println("Message reçu");
	            if (response != null){
	            	if (response.propertyExists("response-type") && response.getStringProperty("response-type").equals("UnexpectedResult")){
	            		throw new UnexpectedResult(response.getStringProperty("response"));
	            	}else{
            			return response.getStringProperty("response");
	            	}	            	
	            }
	            
	            throw new TimeLimitExceededException();
	            
	        } catch (JMSException e) {
	        	throw new TimeLimitExceededException();
	        }
			
		}
	 

	
}
