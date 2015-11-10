package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import bean.AdminDepositoBean;
import bean.AdminPedidoBean;
import dto.ItemSolicitudArticuloDTO;
import dto.ItemsPedidoFabricaDTO;
import dto.PedidoFabricaDTO;
import dto.SolicitudArticuloDTO;
import modelo.Articulo;
import modelo.Categoria;
import modelo.Despacho;
import modelo.EnumEstadoItemPedido;
import modelo.EnumEstadoPedido;
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
	private AdminPedidoBean ped;

	@EJB
	AdminNoticacionBean notificacion;

	public void nuevoArticulo(String nombre, int codigo, String descripcion, String marca, int precio, String url, String origen,
			String ficha, long idCategoria, int cantidad) {
		Articulo articulo = new Articulo();

		articulo.setNombre(nombre);
		articulo.setCodArticulo(codigo);
		articulo.setDescripcion(descripcion);
		articulo.setMarca(marca);
		articulo.setPrecio(precio);
		articulo.setUrlFoto(url);
		articulo.setOrigen(origen);
		articulo.setFichaTecnica(ficha);
		articulo.setFechaAlta(new Date());

		Stock stock = new Stock();

		stock.setCantidad(cantidad);
		stock.setArticulo(articulo);

		dep.nuevoArticulo(articulo, stock,idCategoria);

		notificacion.informarArticulo(articulo, "DES");
		notificacion.informarArticulo(articulo, "DEP");
		notificacion.informarArticuloLM(articulo);

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
			unArticuloVO.setCodigoArticulo(unArticulo.getCodArticulo());
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
				articuloVO.setIdStock(this.obtenerStock(itemPedido.getArticulo().getIdArticulo()).getIdStock());
				articuloVO.setMarca(itemPedido.getArticulo().getMarca());
				articuloVO.setNombre(itemPedido.getArticulo().getNombre());
				articuloVO.setOrigen(itemPedido.getArticulo().getOrigen());
				articuloVO.setPrecio(itemPedido.getArticulo().getPrecio());
				articuloVO.setStock(this.obtenerStock(itemPedido.getArticulo().getIdArticulo()).getCantidad());
				articuloVO.setUrlFoto(itemPedido.getArticulo().getUrlFoto());
				itemPedidoVO.setArticulo(articuloVO);
				itemPedidoVO.setCantidad(itemPedido.getCantidad());
				itemPedidoVO.setIdItemPedido(itemPedido.getIdItemPedido());
				itemsPedidoVO.add(itemPedidoVO);
			}
			//TODO FER
			solpeVO.setItemsPedido(itemsPedidoVO);
			PedidoVO pedidoVO = new PedidoVO();
			pedidoVO.setItemsPedidosAFabrica(itemsPedidoVO);
			//pedidoVO.s
			solpeVO.setPedido(pedidoVO);
			miListaVOs.add(solpeVO);
		}

		return miListaVOs;
	}
	public void nuevaSolicitudPedido(SolicitudArticuloDTO solicitud) {		
		SolicitudDePedido solPe = new SolicitudDePedido();
		solPe.setFecha(new Date());
		solPe.setEstado(EnumSolicitudDePedido.PENDIENTE);
		solPe.setIdDespacho(solicitud.getIdDespacho());
		solPe.setIdSolicitudArticulo(solicitud.getIdSolicitudArticulo());

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
			Stock stockArticulo = dep.buscarStock(item.getIdArticulo());

			int nuevoStock = stockArticulo.getCantidad() + item.getCantidad();
			dep.actualizarStockArticulo(stockArticulo.getIdStock(), nuevoStock);
		}

		dep.actualizarFechaRecepcionPedido(pedido.getIdPedido(), new Date());		
	}

	public int generarPedido(PedidoVO pedidoVO) {
		Pedido pedido = new Pedido();
		pedido.setEstado(EnumEstadoPedido.PENDIENTE);
		// TODO setear una fabrica, preguntar a Rodri o a Franco.
		pedido.setFabrica(null);
		pedido.setFechaSolicitud(pedidoVO.getFechaSolicitud());
		List<ItemPedido> itemsPedidosAFabrica = new ArrayList<ItemPedido>();
		for(int i = 0; i < pedidoVO.getItemsPedidosAFabrica().size(); i++){
			ItemPedido item = new ItemPedido();
			Articulo articulo = new Articulo();
			Categoria categoria = new Categoria();
			articulo.setDescripcion(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getDescripcion());
			articulo.setFechaAlta(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getFechaAlta());
			articulo.setFichaTecnica(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getFichaTecnica());
			articulo.setIdArticulo(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getIdArticulo());
			articulo.setMarca(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getMarca());
			articulo.setNombre(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getNombre());
			articulo.setOrigen(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getOrigen());
			articulo.setPrecio(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getPrecio());
			categoria.setIdCategoria(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getCategoria().getIdCategoria());
			categoria.setNombre(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getCategoria().getNombre());
			articulo.setTipo(categoria);
			articulo.setUrlFoto(pedidoVO.getItemsPedidosAFabrica().get(i).getArticulo().getUrlFoto());
			item.setArticulo(articulo);
			item.setCantidad(pedidoVO.getItemsPedidosAFabrica().get(i).getCantidad());
			item.setEstado(EnumEstadoItemPedido.PENDIENTE);
			item.setIdItemPedido(pedidoVO.getItemsPedidosAFabrica().get(i).getIdItemPedido());
			itemsPedidosAFabrica.add(item);
		}
		pedido.setItemsPedidosAFabrica(itemsPedidosAFabrica);

		ped.grabarPedido(pedido);
		return pedido.getIdPedido();
	}
	public List<SolicitudDePedidoVO> listarPedidosPendientes() {
		EnumSolicitudDePedido estado =EnumSolicitudDePedido.PENDIENTE;
		List<SolicitudDePedido> solicitudes = dep.obtenerSolicitudesPedidoEnEstado(estado);
		List<SolicitudDePedidoVO> solicitudesVO = new LinkedList<SolicitudDePedidoVO>();
		Iterator<SolicitudDePedido> it = solicitudes.iterator();


		while (it.hasNext()) {
			SolicitudDePedido solicitud = it.next();
			SolicitudDePedidoVO solicitudVO = new SolicitudDePedidoVO();
			solicitudVO.setIdSolicitudDePedido(solicitud.getIdSolicitudDePedido());
			solicitudVO.setFecha(solicitud.getFecha());
			solicitudVO.setItemsPedido(null);
			solicitudesVO.add(solicitudVO);

		}
		return solicitudesVO;
	}

	public SolicitudDePedidoVO buscarSolicitud(int idSolPe) {
		SolicitudDePedidoVO solPeVO = new SolicitudDePedidoVO();
		SolicitudDePedido solPe=dep.buscarSolPe(idSolPe);

		solPeVO.setFecha(solPe.getFecha());
		solPeVO.setIdSolicitudDePedido(idSolPe);
		solPeVO.setItemsPedido(new ArrayList<ItemPedidoVO>());
		for( ItemPedido item : solPe.getItemsPedido() )
		{
			ItemPedidoVO itemVO = new ItemPedidoVO();
			itemVO.setIdItemPedido(item.getIdItemPedido());
			itemVO.setCantidad(item.getCantidad());

			ArticuloVO artVO = new ArticuloVO();

			artVO.setDescripcion(item.getArticulo().getDescripcion());
			artVO.setIdArticulo(item.getArticulo().getIdArticulo());

			itemVO.setArticulo(artVO);

			solPeVO.addItemPedidoVO(itemVO);
		}


		return solPeVO;

	}

	public int getStockArticulo(int idArticulo) {
		Stock stock= dep.buscarStock(idArticulo);
		return stock.getCantidad();
	}

}
