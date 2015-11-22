package ws;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import controlador.ControladorDeposito;
import interfaces.PedidoFabricaService;
import notificacion.AdminNoticacionBean;

@Stateless
@WebService
public class PedidoFabricaBean implements PedidoFabricaService {
	@EJB
	ControladorDeposito dep;
	@EJB
	AdminNoticacionBean not;
	
	private static Logger logger = Logger.getLogger(PedidoFabricaBean.class.getName());

	public PedidoFabricaBean() {
		// TODO Auto-generated constructor stub
	}

	@WebMethod
	public void recibirPedido(int idPedido) {
		logger.info("Recibo pedido de F�brica: idPedido: " + idPedido);
		not.informarLogLM("Recibo pedido de F�brica: idPedido: " + idPedido);
		dep.registrarRecepcionArticulosFabrica(idPedido);
		logger.info("Fin proceso recepci�n pedido");
		not.informarLogLM("Fin proceso recepci�n pedido");
	}

}
