package notificacion;

import java.net.URL;



import configuracion.Configuracion;
import ws.LogDTO;
import ws.WSInformeAuditoriaBean;
import ws.WSInformeAuditoriaBeanService;

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
		WSInformeAuditoriaBean port  = new WSInformeAuditoriaBeanService(url).getWSInformeAuditoriaBeanPort();
		boolean salida = port.agregarInforme(detalle);
		if (salida) {
			System.out.println("Verdadero");
		} else {
			System.out.println("Falso");
		}
	} 

}
