package configuracion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.monitor.webservice.LogDTO;

import dto.EnvioDTO;
import dto.PedidoFabricaDTO;
import notificacion.NotificacionSincronica;
import notificacion.NotificacionSincronicaRest;



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
	
	public void notificarLog(LogDTO detalle) {
		NotificacionSincronica notificacion = new NotificacionSincronica(this);
		notificacion.notificarLog(detalle);
	}

	@Override
	public void notificarEntregaArticulosDespacho(EnvioDTO articulos) {
		NotificacionSincronicaRest notificacion = new NotificacionSincronicaRest(this);
		notificacion.notificarEntregaArticulosDespacho(articulos);
		
	}

	@Override
	public void notificarPedidoFabrica(PedidoFabricaDTO pedido) {
		NotificacionSincronicaRest notificacion = new NotificacionSincronicaRest(this);
		notificacion.notificarPedidoFabrica(pedido);		
	}

}
