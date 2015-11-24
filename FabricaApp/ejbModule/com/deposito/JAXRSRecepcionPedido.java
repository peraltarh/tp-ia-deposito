package com.deposito;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

import com.deposito.bean.*;
import controlador.ControladorFabrica;
import modelo.Articulo;
import modelo.Categoria;
import modelo.EnumEstadoPedido;
import modelo.ItemPedido;
import modelo.Pedido;

@Stateless
@Path("/service")
public class JAXRSRecepcionPedido{
	
	@Inject
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
