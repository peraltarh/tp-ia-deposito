package com.deposito;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;

import org.apache.commons.io.IOUtils;

import javax.xml.bind.Marshaller;

import com.deposito.bean.ItemPedidoRESTBean;
import com.deposito.bean.PedidoRESTBean;


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
//		
		//Unmarshal
//		String message = "";
//		JAXBContext jc;
//		try {
//			jc = JAXBContext.newInstance(PedidoRESTBean.class);
//			Unmarshaller u = jc.createUnmarshaller();
//			StringReader reader = new StringReader(message);
//
//			PedidoRESTBean pedido = (PedidoRESTBean) u.unmarshal(reader);
//
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
    	java.io.StringWriter sw = new StringWriter();
		
		JAXBContext jc;
		Result peXML;
		try {
			jc = JAXBContext.newInstance(PedidoRESTBean.class);
			Marshaller m = jc.createMarshaller();
			m.marshal(pe, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		IOUtils.write(sw.toString(), urlConnection.getOutputStream());
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
        

	
}
