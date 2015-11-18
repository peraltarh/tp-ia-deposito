package configuracion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.monitor.webservice.LogDTO;

import dto.EnvioDTO;
import dto.PedidoFabricaDTO;
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
		NotificacionAsincronica notificacion = new NotificacionAsincronica(this);
		notificacion.notificar(mensaje);
	}

	@Override
	public void notificarLog(LogDTO detalle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notificarPedidoFabrica(PedidoFabricaDTO pedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notificarEntregaArticulosDespacho(EnvioDTO articulos) {
		// TODO Auto-generated method stub
		
	}



}
