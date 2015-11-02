package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.EnumEstadoPedido;
import modelo.Pedido;


@Stateless
public class AdminFabricaBean {


	@EJB
	private AdminPedidoBean adminPedido;

	
	
	
	public List<Pedido> obtenerPedidosConEstado(EnumEstadoPedido estado) {
		return adminPedido.obtenerPedidosEnEstado(estado);
		
	}

	public AdminFabricaBean() {
		super();
	}
	
	public void persistirPedido (Pedido pedido){
		adminPedido.persistirPedido(pedido);
	}

}
