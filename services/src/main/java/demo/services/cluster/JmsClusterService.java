package demo.services.cluster;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

public class JmsClusterService implements IClusterService {

	private ConnectionFactory connectionFactory;
	private Connection connection;

	public JmsClusterService(RegistryShutdownHub shutdownHub) throws JMSException {
		// bootstrap ActiveMQ as JMS provider
		System.out.println(String.format("[JMS] starting JMS service..."));
		this.connectionFactory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");

		this.connection = connectionFactory.createConnection();
		this.connection.start();
		System.out.println(String.format("[JMS] starting JMS service...done"));

		shutdownHub.addRegistryShutdownListener(new Runnable() {

			@Override
			public void run() {
				shutdown();
			}
		});
	}

	private void shutdown() {
		try {
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private Topic toTopic(String topic) {
		return new ActiveMQTopic(topic);
	}

	@Override
	public void send(String topic, Serializable event) {
		try {
			doSend(topic, event);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void doSend(String topic, Serializable event) throws JMSException {

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		try {
			ObjectMessage message = session.createObjectMessage(event);
			// message.setLongProperty(SENDER_PROPERTY, serviceID);

			MessageProducer producer = session.createProducer(toTopic(topic));

			try {
				producer.setTimeToLive(10000);
				producer.send(message);
			} finally {
				producer.close();
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void addListener(String topic, ClusterEventListener listener) {
		try {
			doAddListener(topic, listener);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void doAddListener(final String topic, final ClusterEventListener listener) throws JMSException {

		// session will need to stay open until consumer is no longer
		// needed
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		session.createConsumer(toTopic(topic)).setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try {
					listener.receive(((ObjectMessage) message).getObject());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
