package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import bean.AdminDepositoBean;
import dto.ItemSolicitudArticuloDTO;
import dto.ItemsPedidoFabricaDTO;
import dto.PedidoFabricaDTO;
import dto.SolicitudArticuloDTO;
import modelo.Articulo;
import modelo.Categoria;
import modelo.Despacho;
import modelo.EnumEstadoItemPedido;
import modelo.EnumSolicitudDePedido;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Portal;
import modelo.SolicitudDePedido;
import modelo.Stock;
import notificacion.AdminNoticacionBean;
import vo.ArticuloVO;
import vo.CategoriaVO;
import vo.EnumSolicitudDePedidoVO;
import vo.ItemPedidoVO;
import vo.PedidoVO;
import vo.SolicitudDePedidoVO;

@Stateless
@SuppressWarnings("unused")
public class ControladorDeposito {
	private List<SolicitudDePedido> solicitudes;
	private List<Pedido> pedidos;
	private List<Stock> stocks;
	private List<Despacho> despachos;
	private List<Portal> portales;
	@EJB
	private AdminDepositoBean dep;

	@EJB
	AdminNoticacionBean notificacion;

	public void nuevoArticulo(String nombre, String descripcion, String marca, int precio, String url, String origen,
			String ficha, String categoria, int cantidad) {
		Articulo articulo = new Articulo();

		articulo.setNombre(nombre);
		articulo.setDescripcion(descripcion);
		articulo.setMarca(marca);
		articulo.setPrecio(precio);
		articulo.setUrlFoto(url);
		articulo.setOrigen(origen);
		articulo.setFichaTecnica(ficha);
		articulo.setFechaAlta(new Date());

		Categoria cat = new Categoria();
		cat.setNombre(categoria);

		articulo.setTipo(cat);

		Stock stock = new Stock();

		stock.setCantidad(cantidad);
		stock.setArticulo(articulo);

		dep.nuevoArticulo(articulo, stock);

		notificacion.informarArticulo(articulo, "DES");
		// notificacion.informarArticulo(articulo, "DEP");
		// notificacion.informarArticuloLM(articulo);

	}

	public void actualizarStock(int idStock, int cantidad) {
		dep.actualizarStockArticulo(idStock, cantidad);
	}

	public List<ArticuloVO> buscarArticulos(String criterioBusqueda) {
		List<Articulo> articulos = dep.buscarArticulos(criterioBusqueda);
		List<ArticuloVO> articulosVO = new LinkedList<ArticuloVO>();
		Iterator<Articulo> it = articulos.iterator();

		while (it.hasNext()) {
			Articulo unArticulo = it.next();
			ArticuloVO unArticuloVO = new ArticuloVO();
			unArticuloVO.setIdArticulo(unArticulo.getIdArticulo());
			unArticuloVO.setDescripcion(unArticulo.getDescripcion());
			unArticuloVO.setNombre(unArticulo.getNombre());
			unArticuloVO.setPrecio(unArticulo.getPrecio());
			Stock stock = obtenerStock(unArticulo.getIdArticulo());
			unArticuloVO.setIdStock(stock.getIdStock());
			unArticuloVO.setStock(stock.getCantidad());
			unArticuloVO.setMarca(unArticulo.getMarca());
			unArticuloVO.setOrigen(unArticulo.getOrigen());
			unArticuloVO.setFichaTecnica(unArticulo.getFichaTecnica());

			articulosVO.add(unArticuloVO);
		}
		return articulosVO;
	}

	private Stock obtenerStock(int idArticulo) {
		return dep.buscarStock(idArticulo);
	}
	
	public List<SolicitudDePedidoVO> obtenerSolicitudesDePedidoEnEstado(EnumSolicitudDePedidoVO estado){
		
		EnumSolicitudDePedido enumP;
		switch (estado) {
		case ENTREGADO:
			enumP = EnumSolicitudDePedido.ENTREGADO;
			break;
		case FALLIDO:
			enumP = EnumSolicitudDePedido.FALLIDO;
			break;
		case PENDIENTE:
			enumP = EnumSolicitudDePedido.PENDIENTE;
			break;
		default:
			enumP = EnumSolicitudDePedido.PENDIENTE;
			break;
			
		}
		
		List<SolicitudDePedido> miLista = dep.obtenerSolicitudesDePedidoEnEstado(enumP);
		List<SolicitudDePedidoVO> miListaVOs = new ArrayList<SolicitudDePedidoVO>();
		for (SolicitudDePedido solpe : miLista) {
			SolicitudDePedidoVO solpeVO = new SolicitudDePedidoVO();
			switch (solpe.getEstado()) {
			case ENTREGADO:
				solpeVO.setEstado(EnumSolicitudDePedidoVO.ENTREGADO);
				break;
			case FALLIDO:
				solpeVO.setEstado(EnumSolicitudDePedidoVO.FALLIDO);
				break;
			case PENDIENTE:
				solpeVO.setEstado(EnumSolicitudDePedidoVO.PENDIENTE);
				break;
			}
			solpeVO.setFecha(solpe.getFecha());
			solpeVO.setIdSolicitudDePedido(solpe.getIdSolicitudDePedido());
			
			List<ItemPedido> itemsPedido = solpe.getItemsPedido();
			List<ItemPedidoVO> itemsPedidoVO = new ArrayList<ItemPedidoVO>();
			for (ItemPedido itemPedido : itemsPedido) {
				ItemPedidoVO itemPedidoVO = new ItemPedidoVO();
				ArticuloVO articuloVO = new ArticuloVO();
				CategoriaVO categoriaVO = new CategoriaVO();
				categoriaVO.setIdCategoria(itemPedido.getArticulo().getTipo().getIdCategoria());
				categoriaVO.setNombre(itemPedido.getArticulo().getTipo().getNombre());
				articuloVO.setCategoria(categoriaVO);
				articuloVO.setDescripcion(itemPedido.getArticulo().getDescripcion());
				articuloVO.setFechaAlta(itemPedido.getArticulo().getFechaAlta());
				articuloVO.setFichaTecnica(itemPedido.getArticulo().getFichaTecnica());
				articuloVO.setIdArticulo(itemPedido.getArticulo().getIdArticulo());
				// TODO No se que onda aca!!!
				articuloVO.setIdStock(itemPedido.getArticulo().getIdArticulo());
				articuloVO.setMarca(itemPedido.getArticulo().getMarca());
				articuloVO.setNombre(itemPedido.getArticulo().getNombre());
				articuloVO.setOrigen(itemPedido.getArticulo().getOrigen());
				articuloVO.setPrecio(itemPedido.getArticulo().getPrecio());
				// TODO No se que onda aca!!!
				articuloVO.setStock(9);
				articuloVO.setUrlFoto(itemPedido.getArticulo().getUrlFoto());
				itemPedidoVO.setArticulo(articuloVO);
				itemPedidoVO.setCantidad(itemPedido.getCantidad());
				itemPedidoVO.setIdItemPedido(itemPedido.getIdItemPedido());
				itemsPedidoVO.add(itemPedidoVO);
			}
				
			solpeVO.setItemsPedido(itemsPedidoVO);
			PedidoVO pedidoVO = new PedidoVO();
			//pedidoVO.s
			solpeVO.setPedido(pedidoVO);
		}
		
		return miListaVOs;
	}
	public void nuevaSolicitudPedido(SolicitudArticuloDTO solicitud) {		
		SolicitudDePedido solPe = new SolicitudDePedido();
		solPe.setFecha(new Date());
		solPe.setEstado(EnumSolicitudDePedido.PENDIENTE);
		
		List<ItemSolicitudArticuloDTO> itemsSolicitudDTO = solicitud.getItems();
		List<ItemPedido> itemsSolicitud = new LinkedList<ItemPedido>();
		
		Iterator<ItemSolicitudArticuloDTO> it = itemsSolicitudDTO.iterator();
		
		while (it.hasNext()){
			ItemSolicitudArticuloDTO unItemDTO = it.next();
			ItemPedido unItem = new ItemPedido();
			unItem.setArticulo(dep.obtenerArticulo(unItemDTO.getIdArticulo()));
			unItem.setCantidad(unItemDTO.getCantidad());
			unItem.setEstado(EnumEstadoItemPedido.PENDIENTE);
			itemsSolicitud.add(unItem);
		}
		
		solPe.setItemsPedido(itemsSolicitud);
		dep.nuevaSolicitudPedido(solPe);
	}
	public List<CategoriaVO> obtenerCategorias () {
		List<CategoriaVO> categoriasVO = new LinkedList<>();
		List<Categoria> categorias = dep.obtenerCategorias();
		Iterator<Categoria> it = categorias.iterator();
		
		while (it.hasNext()) {
			Categoria categoria = it.next();
			CategoriaVO categoriaVO = new CategoriaVO();
			categoriaVO.setIdCategoria(categoria.getIdCategoria());
			categoriaVO.setNombre(categoria.getNombre());
			categoriasVO.add(categoriaVO);
		}
		return categoriasVO;
	}
	
	public void registrarRecepcionArticulosFabrica (PedidoFabricaDTO pedido) {
		List<ItemsPedidoFabricaDTO> items = pedido.getItems();
		Iterator<ItemsPedidoFabricaDTO> it = items.iterator();
		
		while (it.hasNext()) {
			ItemsPedidoFabricaDTO item = it.next();
			int nuevoStock = dep.buscarStock(item.getIdArticulo()).getCantidad() + item.getCantidad();
			dep.actualizarStockArticulo(item.getIdArticulo(), nuevoStock);
		}

		dep.actualizarFechaRecepcionPedido(pedido.getIdSolicitudCompra(), new Date());		
	}
}
