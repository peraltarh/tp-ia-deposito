package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import modelo.Articulo;
import modelo.Categoria;
import modelo.EnumSolicitudDePedido;
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

	public void nuevoArticulo(Articulo articulo, Stock stock) {
		adminArticulo.nuevoArticulo(articulo);
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

}
