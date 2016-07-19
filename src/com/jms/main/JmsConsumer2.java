package com.jms.main;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

public class JmsConsumer2 {
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
			session.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					TextMessage tms = (TextMessage) msg;
					try {
						System.out.println(tms.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
