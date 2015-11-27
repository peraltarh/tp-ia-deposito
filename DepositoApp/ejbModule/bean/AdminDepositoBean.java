package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import modelo.Articulo;
import modelo.Categoria;
import modelo.EnumEstadoPedido;
import modelo.EnumSolicitudDePedido;
import modelo.Pedido;
import modelo.SolicitudDePedido;
import modelo.Stock;

@Stateless
public class AdminDepositoBean {
	@EJB
	private AdminArticuloBean adminArticulo;
	@EJB
	private AdminPedidoBean adminPedido;
	@EJB
	private AdminSolicitudPedidoBean adminSolPe;

	public void nuevoArticulo(Articulo articulo, Stock stock, long idCategoria) {
		adminArticulo.nuevoArticulo(articulo,idCategoria);
		adminArticulo.crearStock(stock);
	}

	public List<Articulo> buscarArticulos(String criterioBusqueda) {
		return adminArticulo.buscarArticulos(criterioBusqueda);
	}

	public void actualizarStockArticulo(int idStock, int nuevoStock) {
		adminArticulo.actualizarStockArticulo(idStock, nuevoStock);
	}

	public Stock buscarStock(int idArticulo) {
		return adminArticulo.buscarStock(idArticulo);
	}
	
	public List<SolicitudDePedido> obtenerSolicitudesDePedidoEnEstado (EnumSolicitudDePedido estado){
		return adminPedido.obtenerSolicitudesDePedidoEnEstado(estado);
	}

	public Articulo obtenerArticulo(int idArticulo) {
		return adminArticulo.obtenerArticulo(idArticulo);
	}

	public void nuevaSolicitudPedido(SolicitudDePedido solPe) {
		adminSolPe.nuevaSolicitudPedido(solPe);		
	}
	
	public List<Categoria> obtenerCategorias() {
		return adminArticulo.obtenerCategorias();
	}

	public void actualizarFechaRecepcionPedido(int idSolicitudCompra, Date fechaRecepcion) {
		adminPedido.actualizarFechaRecepcionPedido(idSolicitudCompra,fechaRecepcion);	
	}
	
	public List<SolicitudDePedido> obtenerSolicitudesPedidoEnEstado (EnumSolicitudDePedido estado){
		return adminSolPe.obtenerSolicitudesPedidoEnEstado(estado);
	}

	public SolicitudDePedido buscarSolPe(long l) {
		return adminSolPe.buscarSolPe(l);

	}

	public Pedido buscarPedido(int idPedido) {
		return adminPedido.buscarPedido(idPedido);
		
	}

	public Categoria obtenerCategoria(long l) {
		return adminArticulo.obtenerCategoria(l);
	}

}
