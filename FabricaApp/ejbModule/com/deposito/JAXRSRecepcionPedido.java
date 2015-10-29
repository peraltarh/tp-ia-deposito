package com.deposito;

import java.io.StringReader;
import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import com.deposito.bean.PedidoRESTBean;


@Path("/service")
public class JAXRSRecepcionPedido {
	@GET
    @Path("/hola")
	@Produces({ "text/plain" })
    public String hola() {
        return "Hola!";
    }
	
    @PUT
    @Consumes("application/json")
    public synchronized void solitudPedido(String xml) {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(PedidoRESTBean.class);
			javax.xml.bind.Unmarshaller um = jc.createUnmarshaller();
			PedidoRESTBean p = (PedidoRESTBean)um.unmarshal(new StringReader(xml));
			System.out.println(p.getIdPedido());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	

}
