
package vo;


public class ItemPedidoVO {

	private int idItemPedido;
	private ArticuloVO articulo;
	private int cantidad;
	private EnumEstadoItemPedidoVO estado;
	private SolicitudDePedidoVO solpe;

	public EnumEstadoItemPedidoVO getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoItemPedidoVO estado) {
		this.estado = estado;
	}

	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public ArticuloVO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloVO articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setSolpe(SolicitudDePedidoVO solpe) {
		this.solpe = solpe;		
	}

	public SolicitudDePedidoVO getSolpe() {
		return solpe;		
	}
}
