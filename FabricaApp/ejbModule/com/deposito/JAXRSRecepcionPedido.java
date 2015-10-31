package com.deposito;

import java.io.StringReader;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElementDecl;


import com.deposito.bean.PedidoRESTBean;


@Path("/service")
public class JAXRSRecepcionPedido {
	@GET
    @Path("/hola")
	@Produces({ "text/plain" })
    public String hola() {
        return "Hola!";
    }
	
    @POST
    @Path("/crearPedido")
    @Consumes({"application/xml","application/json"})
    public Response solitudPedido(PedidoRESTBean pedido) {
		
    	System.out.println(pedido.getIdPedido());
		return Response.accepted().build();
    	
    	
    }


    
}
