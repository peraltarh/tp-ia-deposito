
package vo;

import java.util.Date;
import java.util.List;


public class PedidoVO {

	private int idPedidoLocal;
	private int idPedido;
	private Date fechaSolicitud;
	List<ItemPedidoVO> itemsPedidosAFabrica;
	private Date fechaRecepcion;
	private FabricaVO fabrica;
	private EnumEstadoPedidoVO estado;

	public int getIdPedido() {
		return idPedido;
	}
	
	
	public int getIdPedidoLocal() {
		return idPedidoLocal;
	}



	public void setIdPedidoLocal(int idPedidoLocal) {
		this.idPedidoLocal = idPedidoLocal;
	}



	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}


	public List<ItemPedidoVO> getItemsPedidosAFabrica() {
		return itemsPedidosAFabrica;
	}

	public void setItemsPedidosAFabrica(List<ItemPedidoVO> itemsPedidosAFabrica) {
		this.itemsPedidosAFabrica = itemsPedidosAFabrica;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public FabricaVO getFabrica() {
		return fabrica;
	}

	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}

	public EnumEstadoPedidoVO getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPedidoVO estado) {
		this.estado = estado;
	}


}
