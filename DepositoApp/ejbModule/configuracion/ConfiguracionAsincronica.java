package configuracion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.monitor.webservice.LogDTO;

import notificacion.NotificacionAsincronica;



@Entity
@DiscriminatorValue("async")
public class ConfiguracionAsincronica extends Configuracion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notificar(String mensaje) {
		NotificacionAsincronica  notificacion = new NotificacionAsincronica(this);
		notificacion.notificar(mensaje);
	}

	@Override
	public void notificarLog(LogDTO detalle) {
		// TODO Auto-generated method stub
		
	}
	

}
