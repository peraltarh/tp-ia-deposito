package vo;

import java.util.Date;
import java.util.List;

public class SolicitudDePedidoVO {

	private long idSolicitudDePedido;
	private EnumSolicitudDePedidoVO estado;
	private Date fecha;
	private List<ItemPedidoVO> itemsPedido;
	private PedidoVO pedido;

	public long getIdSolicitudDePedido() {
		return idSolicitudDePedido;
	}

	public void setIdSolicitudDePedido(long idSolicitudDePedido) {
		this.idSolicitudDePedido = idSolicitudDePedido;
	}

	public EnumSolicitudDePedidoVO getEstado() {
		return estado;
	}

	public void setEstado(EnumSolicitudDePedidoVO estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ItemPedidoVO> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(List<ItemPedidoVO> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public PedidoVO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

}

