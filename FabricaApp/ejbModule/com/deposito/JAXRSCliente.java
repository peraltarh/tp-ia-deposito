package com.deposito;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import javax.xml.bind.Marshaller;
import com.deposito.bean.ArticuloRESTBean;
import com.deposito.bean.CategoriaRESTBean;
import com.deposito.bean.ItemPedidoRESTBean;
import com.deposito.bean.PedidoRESTBean;
import modelo.ItemPedido;
import modelo.Pedido;


public class JAXRSCliente {


	public static void main(String[] args) throws Exception {
    	
		//Ver https://docs.oracle.com/javaee/6/tutorial/doc/gkoib.html
		
    	PedidoRESTBean pe = new PedidoRESTBean();
    	
    	pe.setIdPedido(0);
    	pe.setFechaRecepcion(GregorianCalendar.getInstance().getTime());
    	pe.setFechaSolicitud(GregorianCalendar.getInstance().getTime());
    	List<ItemPedidoRESTBean> items = new ArrayList<ItemPedidoRESTBean>();
    	pe.setItemsPedidosAFabrica(items);
        
        
    	// JSON POST
        URL url = new URL("http://localhost:8080/FabricaWeb/rest/service/crearPedido");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");

    	java.io.StringWriter sw = new StringWriter();
		
		JAXBContext jc;

		try {
			jc = JAXBContext.newInstance(PedidoRESTBean.class);
			Marshaller m = jc.createMarshaller();
			m.marshal(pe, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(urlConnection.getOutputStream(), pe);

		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
    
	
	public String enviarPedidoAFabrica (Pedido pedido){
		
		
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
