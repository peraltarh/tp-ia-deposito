package controlador;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import bean.AdminFabricaBean;
import modelo.EnumEstadoPedido;
import modelo.Pedido;
import notificacion.AdminNoticacionBean;
import vo.EnumEstadoPedidoVO;
import vo.PedidoVO;

@Stateless
public class ControladorFabrica {
	private List<Pedido> pedidos;

	@EJB
	private AdminFabricaBean fab;
	
	@EJB
	private AdminNoticacionBean not;
	
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
			 pedidoVO.setIdPedidoLocal(pedido.getIdPedidoLocal());
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
			 pedidosVO.add(pedidoVO);
		}
		 return pedidosVO;
		
	}


	public void cerrarPedido(PedidoVO pedidoVO) {
		
		Pedido ped = fab.obtenerPedido(pedidoVO.getIdPedidoLocal());
		ped.setEstado(EnumEstadoPedido.ENTREGADO);
		ped.setFechaRecepcion(GregorianCalendar.getInstance().getTime());
		fab.actualizarPedido(ped);

		not.entregarPedidoDeposito(pedidoVO.getIdPedido());
				
	}

}
