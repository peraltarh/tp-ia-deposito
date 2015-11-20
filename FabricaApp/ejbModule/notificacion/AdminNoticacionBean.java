package notificacion;

import java.util.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class AdminNoticacionBean {

	private static Logger logger = Logger.getLogger(AdminNoticacionBean.class.getName());

	public void entregarPedidoDeposito(int idPedido) {
		NotificacionSincronica noti = new NotificacionSincronica();
		noti.notificarPedidoDeposito(idPedido);
	}

}
