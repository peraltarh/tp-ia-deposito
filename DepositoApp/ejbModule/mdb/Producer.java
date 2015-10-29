package mdb;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import configuracion.Configuracion;

public class Producer {

	private ConnectionFactory remoteQueueCF;
	private Queue remoteQueue;
	private Connection remoteQueueConnection;
	private Session remoteQueueSession;

	public Producer(Configuracion configuracion) {
		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");

		// props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		props.put(Context.PROVIDER_URL, "http-remoting://" + "localhost:8080");
		//props.put(Context.SECURITY_PRINCIPAL, "guest");
		//props.put(Context.SECURITY_CREDENTIALS, "guest123");
		
		try {
			InitialContext ic = new InitialContext(props);
			remoteQueueCF = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
			// remoteQueue = (Queue) ic.lookup("jms/queues/LocalServer1Q");
			remoteQueue = (Queue) ic.lookup("jms/queue/SolicitudArticulos");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			remoteQueueConnection = remoteQueueCF.createConnection();
			remoteQueueConnection.start();
			remoteQueueSession = remoteQueueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void notificar(String notificacion) {
		try {
			System.out.println("XML " + notificacion);
			TextMessage txtMessage = remoteQueueSession.createTextMessage(notificacion);
			MessageProducer msgProducer = remoteQueueSession.createProducer(this.remoteQueue);
			msgProducer.send(txtMessage);
			msgProducer.close();
			finalize();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/* SOLO PARA TESTEO
	public String recibirArticulo() throws JMSException {
		MessageConsumer msgConsumer = remoteQueueSession.createConsumer(this.remoteQueue);
		TextMessage txtMsg = (TextMessage) msgConsumer.receive();
		msgConsumer.close();
		return txtMsg.getText();
	}
*/
	@Override
	protected void finalize() throws Throwable {
		remoteQueueSession.close();
		remoteQueueConnection.close();
	}
}
