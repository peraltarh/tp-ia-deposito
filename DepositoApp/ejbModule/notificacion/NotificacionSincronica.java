package notificacion;

import java.net.URL;
import configuracion.Configuracion;
import wsLM.LogDTO;
import wsLM.WSInformeAuditoriaBean;
import wsLM.WSInformeAuditoriaBeanService;
import ws.LogisticaMonitoreoBeanService;
import ws.LogisticaMonitoreoWS;

public class NotificacionSincronica {
	private URL url;

	public NotificacionSincronica(Configuracion configuracion) {
		try {
			url = new URL("http://"+configuracion.getUrl() + "/" + configuracion.getRecurso() + "?wsdl");

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
		WSInformeAuditoriaBean port = new WSInformeAuditoriaBeanService(url).getWSInformeAuditoriaBeanPort();
		port.agregarInforme(detalle);
	} 

}
