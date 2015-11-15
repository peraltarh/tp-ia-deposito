package notificacion;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.BindingProvider;

import com.monitor.webservice.LogDTO;
import com.monitor.webservice.WSInformeAuditoriaBean;
import com.monitor.webservice.WSInformeAuditoriaBeanService;

import configuracion.Configuracion;

public class NotificacionSincronica {
	private String url;
	URL urlWSDL = null;

	public NotificacionSincronica(Configuracion configuracion) {
		try {
			url = "http://" + configuracion.getUrl() + "/" + configuracion.getRecurso();

			urlWSDL = new URL("http://" + configuracion.getUrl() + "/" + configuracion.getRecurso() + "?wsdl");

		} catch (

		Exception e)

		{
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	public void notificar(String notificacion) {

	}

	public void notificarLog(LogDTO detalle) {

		WSInformeAuditoriaBeanService service = new WSInformeAuditoriaBeanService(urlWSDL);
		WSInformeAuditoriaBean port = service.getWSInformeAuditoriaBeanPort();

		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

		port.agregarInforme(detalle);
	}

}
