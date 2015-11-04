package mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import controlador.ControladorDeposito;
import dto.SolicitudArticuloDTO;

/**
 * Message-Driven Bean implementation class for: SolicitudPedidoMDB2
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/SolicitudArticulos"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/SolicitudArticulos")
public class SolicitudPedidoMDB implements MessageListener {
	@EJB
	private ControladorDeposito dep;
	private static Logger logger = Logger.getLogger(SolicitudPedidoMDB.class.getName());

    /**
     * Default constructor. 
     */
    public SolicitudPedidoMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	String solicitudArticuloJSON = null;
		try {
			solicitudArticuloJSON = ((TextMessage)message).getText();
			logger.info("Recepcion Solicitud Articulos JSON recibido : " + solicitudArticuloJSON);
		} catch (JMSException e) {
			logger.info("Error al obtener JSON Artículo desde Despacho ");
			e.printStackTrace();
		}
    	
    	Gson gson = new Gson();

    	SolicitudArticuloDTO solicitud = gson.fromJson(solicitudArticuloJSON, SolicitudArticuloDTO.class);
    	    	
    	dep.nuevaSolicitudPedido(solicitud);
        
    }

}
