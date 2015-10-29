package configuracion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import notificacion.NotificacionSincronica;

@Entity
@DiscriminatorValue("sync")
public class ConfiguracionSincronica extends Configuracion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notificar(String mensaje) {
		NotificacionSincronica notificacion = new NotificacionSincronica(this);
		notificacion.notificar(mensaje);
	}
	

}
