package ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import controlador.ControladorDeposito;
import interfaces.PedidoFabricaService;

@Stateless
@WebService
public class PedidoFabricaBean implements PedidoFabricaService {
	@EJB
	ControladorDeposito dep;

	public PedidoFabricaBean() {
		// TODO Auto-generated constructor stub
	}

	@WebMethod
	public void recibirPedido(int idPedido) {
		dep.registrarRecepcionArticulosFabrica(idPedido);
	}

}
