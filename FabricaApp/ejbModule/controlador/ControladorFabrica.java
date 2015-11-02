package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import bean.AdminFabricaBean;
import modelo.EnumEstadoPedido;
import modelo.Pedido;
import vo.EnumEstadoPedidoVO;
import vo.PedidoVO;

@Stateful
public class ControladorFabrica {
	private List<Pedido> pedidos;

	@EJB
	private AdminFabricaBean fab;
	
	public void agregarPedido (Pedido pedido){
		if(pedidos == null) pedidos = new ArrayList<Pedido>();
		pedidos.add(pedido);
		
		fab.persistirPedido(pedido);
		
	}
	

	public List<PedidoVO> buscarPedidos() {

		 List<Pedido> pedidos =  fab.obtenerPedidosConEstado(EnumEstadoPedido.PENDIENTE);
		 List<PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
		 for (Pedido pedido : pedidos) {
			 PedidoVO pedidoVO = new PedidoVO();
			 pedidoVO.setIdPedido(pedido.getIdPedido());
			 switch (pedido.getEstado()) {
			case ENTREGADO:
				pedidoVO.setEstado(EnumEstadoPedidoVO.ENTREGADO);
				break;
			case FALLIDO:
				pedidoVO.setEstado(EnumEstadoPedidoVO.FALLIDO);
				break;
			case PENDIENTE:
				pedidoVO.setEstado(EnumEstadoPedidoVO.PENDIENTE);
				break;
			}
		}
		 return pedidosVO;
		
	}

}
