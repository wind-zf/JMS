package com.jms.main;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

/**
 * JMS Ïû·ÑÕß
 * @author latitude
 *
 */
public class JmsConsumer {
	private static final String USER = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKE = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ActiveMQConnectionFactory activeMQConnectionFactory = null;
		ActiveMQConnection activeMQConnection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer messageConsumer = null;
		
		try {
			activeMQConnectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, BROKE);
			activeMQConnection = (ActiveMQConnection) activeMQConnectionFactory.createConnection();
			activeMQConnection.start();
			session = activeMQConnection.createSession(Boolean.FALSE,ActiveMQSession.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myTest");
			messageConsumer = session.createConsumer(destination);
			while(true) {
				TextMessage tms = (TextMessage) messageConsumer.receive();
				if(tms != null) {
					System.out.println("-->"+tms.getText());
				} else {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
