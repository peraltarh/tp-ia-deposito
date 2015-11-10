package notificacion;

import javax.xml.ws.BindingProvider;

import com.monitor.webservice.LogDTO;
import com.monitor.webservice.WSInformeAuditoriaBean;
import com.monitor.webservice.WSInformeAuditoriaBeanService;

import configuracion.Configuracion;


public class NotificacionSincronica {
	private String url;

	public NotificacionSincronica(Configuracion configuracion) {
		try {
			url = "http://"+configuracion.getUrl() + "/" + configuracion.getRecurso();

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
		WSInformeAuditoriaBeanService service = new WSInformeAuditoriaBeanService();
		WSInformeAuditoriaBean port = service.getWSInformeAuditoriaBeanPort();
					
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(
		      BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		    
		port.agregarInforme(detalle);
	} 

}
