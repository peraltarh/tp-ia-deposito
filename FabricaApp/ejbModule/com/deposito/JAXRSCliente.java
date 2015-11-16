package com.deposito;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.deposito.bean.ArticuloRESTBean;
import com.deposito.bean.CategoriaRESTBean;
import com.deposito.bean.ItemPedidoRESTBean;
import com.deposito.bean.PedidoRESTBean;
import modelo.ItemPedido;
import modelo.Pedido;


public class JAXRSCliente {


	public static void main(String[] args) throws Exception {
    	

//    	PedidoRESTBean pe = new PedidoRESTBean();
//    	
//    	pe.setIdPedido(0);
//    	pe.setFechaRecepcion(GregorianCalendar.getInstance().getTime());
//    	pe.setFechaSolicitud(GregorianCalendar.getInstance().getTime());
//    	List<ItemPedidoRESTBean> items = new ArrayList<ItemPedidoRESTBean>();
//    	pe.setItemsPedidosAFabrica(items);
    	
    	Pedido pe = new Pedido();
    	pe.setIdPedido(0);
    	pe.setFechaRecepcion(GregorianCalendar.getInstance().getTime());
    	pe.setFechaSolicitud(GregorianCalendar.getInstance().getTime());
    	List<ItemPedido> items = new ArrayList<ItemPedido>();
    	pe.setItemsPedidosAFabrica(items);
//    	pe.setEstado(EnumEstadoPedido.PENDIENTE);
//        
//        
//    	// JSON POST
//        URL url = new URL("http://localhost:8080/FabricaWeb/rest/service/crearPedido");
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//		urlConnection.setDoOutput(true);
//		urlConnection.setRequestMethod("POST");
//		urlConnection.setRequestProperty("Content-Type", "application/json");
//
//
//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(urlConnection.getOutputStream(), pe);
//
//		String response = IOUtils.toString(urlConnection.getInputStream());
		
    	String response = enviarPedidoAFabrica(pe);
		
		System.out.println("Respuesta: " + response);
	}
    
	
	public static String enviarPedidoAFabrica (Pedido pedido){
		
		
		PedidoRESTBean pe = new PedidoRESTBean();
    	pe.setIdPedido(pedido.getIdPedido());
    	pe.setFechaRecepcion(null);
    	pe.setFechaSolicitud(pedido.getFechaSolicitud());
    	List<ItemPedidoRESTBean> items = new ArrayList<ItemPedidoRESTBean>();
    	pe.setItemsPedidosAFabrica(items);
    	for (ItemPedido itemPedido : pedido.getItemsPedidosAFabrica()) {
			ItemPedidoRESTBean itemRest = new ItemPedidoRESTBean();
			itemRest.setCantidad(itemPedido.getCantidad());
			
			ArticuloRESTBean articuloRest = new ArticuloRESTBean();
			articuloRest.setDescripcion(itemPedido.getArticulo().getDescripcion());
			articuloRest.setFechaAlta(itemPedido.getArticulo().getFechaAlta());
			articuloRest.setFichaTecnica(itemPedido.getArticulo().getFichaTecnica());
			articuloRest.setIdArticulo(itemPedido.getArticulo().getIdArticulo());
			articuloRest.setMarca(itemPedido.getArticulo().getMarca());
			articuloRest.setNombre(itemPedido.getArticulo().getNombre());
			articuloRest.setOrigen(itemPedido.getArticulo().getOrigen());
			articuloRest.setPrecio(itemPedido.getArticulo().getPrecio());
			articuloRest.setUrlFoto(itemPedido.getArticulo().getUrlFoto());
			CategoriaRESTBean cate = new CategoriaRESTBean();
			cate.setIdCategoria(itemPedido.getArticulo().getTipo().getIdCategoria());
			cate.setNombre(itemPedido.getArticulo().getTipo().getNombre());
			articuloRest.setTipo(cate);
			
			itemRest.setArticulo(articuloRest);
		}
        
        
    	// JSON POST
        URL url;
		try {
			url = new URL("http://localhost:8080/FabricaWeb/rest/service/crearPedido");
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(urlConnection.getOutputStream(), pe);
			
			return IOUtils.toString(urlConnection.getInputStream());
	
		} catch (IOException e) {
			return e.getMessage();
		}

	}

	
}
