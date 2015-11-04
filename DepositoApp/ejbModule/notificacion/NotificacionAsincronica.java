package notificacion;

import java.util.Properties;
import java.util.logging.Logger;

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

public class NotificacionAsincronica {

	private ConnectionFactory remoteQueueCF;
	private Queue remoteQueue;
	private Connection remoteQueueConnection;
	private Session remoteQueueSession;
	private static Logger logger = Logger.getLogger(NotificacionAsincronica.class.getName());

	public NotificacionAsincronica(Configuracion configuracion) {
		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");

		// props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		props.put(Context.PROVIDER_URL, "http-remoting://" + configuracion.getUrl());
		props.put(Context.SECURITY_PRINCIPAL, "guest");
		props.put(Context.SECURITY_CREDENTIALS, "guest123");
		
		try {
			InitialContext ic = new InitialContext(props);
			remoteQueueCF = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
			// remoteQueue = (Queue) ic.lookup("jms/queues/LocalServer1Q");
			remoteQueue = (Queue) ic.lookup(configuracion.getRecurso());
		} catch (NamingException e) {
			logger.info("Error al hacer lkp a la queue. " + "Url: " + configuracion.getUrl() + " Recurso: " + configuracion.getRecurso());
			e.printStackTrace();
		}

		try {
			remoteQueueConnection = remoteQueueCF.createConnection();
			remoteQueueConnection.start();
			remoteQueueSession = remoteQueueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			logger.info("Error al crear session");
			e.printStackTrace();
		}

	}

	public void notificar(String notificacion) {
		try {
			TextMessage txtMessage = remoteQueueSession.createTextMessage(notificacion);
			MessageProducer msgProducer = remoteQueueSession.createProducer(this.remoteQueue);
			msgProducer.send(txtMessage);
			msgProducer.close();
			finalize();
		} catch (JMSException e) {
			logger.severe("Error al enviar mensaje");
			e.printStackTrace();
		} catch (Throwable e) {
			logger.severe("Error al enviar mensaje");
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
