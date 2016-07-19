package com.jms.main;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

/**
 * 消息生产者
 * @author latitude
 *
 */
public class JmsProducer {
	private static final String USER = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKE = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ActiveMQConnectionFactory activeMQConnectionFactory = null;
		ActiveMQConnection activeMQConnection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer messageProducer = null;
		
		try {
			activeMQConnectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, BROKE);
			activeMQConnection = (ActiveMQConnection) activeMQConnectionFactory.createConnection();
			activeMQConnection.start();
			session = activeMQConnection.createSession(Boolean.TRUE,ActiveMQSession.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myTest");
			messageProducer = session.createProducer(destination);
			sendMsg(10, session, messageProducer);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private static void sendMsg(int num,Session session,MessageProducer producer) throws JMSException {
		for (int i = 0; i < num; i++) {
			TextMessage tmsg = session.createTextMessage("");
			producer.send(tmsg);
		}
	}

}
