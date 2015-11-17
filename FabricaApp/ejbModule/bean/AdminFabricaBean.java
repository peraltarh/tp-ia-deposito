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
	
	public void actualizarPedido (Pedido pedido){
		adminPedido.actualizarPedido(pedido);
	}

	public Pedido obtenerPedido(int idPedidoLocal) {
		return adminPedido.obtenerPedido(idPedidoLocal);
		
	}

}
