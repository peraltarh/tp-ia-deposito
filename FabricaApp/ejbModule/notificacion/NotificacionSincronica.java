package notificacion;

import java.net.URL;

import javax.xml.ws.BindingProvider;

import ws.PedidoFabricaBean;
import ws.PedidoFabricaBeanService;

public class NotificacionSincronica {
	private String url;
	URL urlWSDL = null;

	public NotificacionSincronica() {
		try {
			url = "http://" + "localhost:8080" + "/" + "DepositoApp/PedidoFabricaBean";

			urlWSDL = new URL("http://" + "localhost:8080" + "/" + "DepositoApp/PedidoFabricaBean" + "?wsdl");

		} catch (

		Exception e)

		{
			e.printStackTrace();
		}

	}

	public void notificarPedidoDeposito(int idPedido) {
		PedidoFabricaBeanService service = new PedidoFabricaBeanService(urlWSDL);
		PedidoFabricaBean port = service.getPedidoFabricaBeanPort();

		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

		port.recibirPedido(idPedido);

	}
}
