package com.deposito;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;


import com.deposito.bean.ItemPedidoRESTBean;
import com.deposito.bean.PedidoRESTBean;

import controlador.ControladorFabrica;
import modelo.Articulo;
import modelo.Categoria;
import modelo.EnumEstadoPedido;
import modelo.ItemPedido;
import modelo.Pedido;


@Path("/service")
@RequestScoped
//@Stateless
public class JAXRSRecepcionPedido{
	
	@Inject
//	@EJB
	private ControladorFabrica contFabrica;

	
    @POST
    @Path("/crearPedido")
    @Consumes({"application/json","application/json"}) @Produces({ "text/plain" })
    public String solitudPedido(PedidoRESTBean pedidoREST) {
    	
    	Pedido pedido = new Pedido();
    	pedido.setIdPedidoLocal(0);
    	pedido.setEstado(EnumEstadoPedido.PENDIENTE);
    	pedido.setFechaRecepcion(pedidoREST.getFechaRecepcion());
    	pedido.setFechaSolicitud(pedidoREST.getFechaSolicitud());
    	pedido.setIdPedido(pedidoREST.getIdPedido());
    	List<ItemPedido> items = new ArrayList<ItemPedido>();
    	pedido.setItemsPedidosAFabrica(items);
    	
    	for (ItemPedidoRESTBean itemRest : pedidoREST.getItemsPedidosAFabrica()) {
			ItemPedido item = new ItemPedido();
			item.setIdItemPedidoLocal(0);
			item.setCantidad(itemRest.getCantidad());
			Articulo articulo = new Articulo();
			articulo.setIdArticuloLocal(0);
			articulo.setDescripcion(itemRest.getArticulo().getDescripcion());
			articulo.setFechaAlta(itemRest.getArticulo().getFechaAlta());
			articulo.setFichaTecnica(itemRest.getArticulo().getFichaTecnica());
			articulo.setIdArticulo(itemRest.getArticulo().getIdArticulo());
			articulo.setMarca(itemRest.getArticulo().getMarca());
			articulo.setNombre(itemRest.getArticulo().getNombre());
			articulo.setOrigen(itemRest.getArticulo().getOrigen());
			articulo.setPrecio(itemRest.getArticulo().getPrecio());
			Categoria cate = new Categoria();
			cate.setIdCategoriaLocal(0);
			cate.setIdCategoria(itemRest.getArticulo().getTipo().getIdCategoria());
			cate.setNombre(itemRest.getArticulo().getTipo().getNombre());
			articulo.setTipo(cate);
			articulo.setUrlFoto(itemRest.getArticulo().getUrlFoto());
			
			item.setArticulo(articulo);
			items.add(item);
		}
    	
    	contFabrica.agregarPedido(pedido);
    	
    	
		return "200";
    	
    	
    }


    
}
