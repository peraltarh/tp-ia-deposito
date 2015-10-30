package ws;

import java.io.StringReader;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import controlador.ControladorDeposito;
import dto.PedidoFabricaDTO;
import interfaces.PedidoFabricaService;

@Stateless
@WebService
public class PedidoFabricaBeanService implements PedidoFabricaService {
	@EJB
	ControladorDeposito dep;
	
	public PedidoFabricaBeanService() {
		// TODO Auto-generated constructor stub
	}

	@WebMethod
	public void recibirPedido(String detallePedido) {
		PedidoFabricaDTO pedido = getPedido(detallePedido);
		dep.registrarRecepcionArticulosFabrica(pedido);
	}

	private PedidoFabricaDTO getPedido(String message) {
		PedidoFabricaDTO pedido = null;
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(PedidoFabricaDTO.class);
			Unmarshaller u = jc.createUnmarshaller();
			StringReader reader = new StringReader(message);

			pedido = (PedidoFabricaDTO) u.unmarshal(reader);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return pedido;
	}

}
