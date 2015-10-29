package mdb;

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
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Gson gson = new Gson();

    	SolicitudArticuloDTO solicitud = gson.fromJson(solicitudArticuloJSON, SolicitudArticuloDTO.class);
    	    	
    	
    	//System.out.println("Despacho "+ jsonToObject.getIdDespacho());
    	dep.nuevaSolicitudPedido(solicitud);
        
    }

}
