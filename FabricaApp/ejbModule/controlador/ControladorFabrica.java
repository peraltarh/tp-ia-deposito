package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import bean.AdminFabricaBean;
import modelo.Despacho;
import modelo.EnumEstadoPedido;
import modelo.Pedido;
import vo.EnumEstadoPedidoVO;
import vo.PedidoVO;

@Stateless
public class ControladorFabrica {
	private List<Pedido> pedidos;
	private List<Despacho> despachos;

	@EJB
	private AdminFabricaBean fab;

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
