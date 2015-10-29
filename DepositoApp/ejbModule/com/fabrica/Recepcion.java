package com.fabrica;


import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import vo.PedidoVO;

/**
 * Session Bean implementation class Recepcion
 */
@Stateless
@WebService
public class Recepcion implements RecepcionRemote {

    public Recepcion() {
    }

    @WebMethod
	public void recepcionCompra(PedidoVO pedido) {
		// TODO D4 Auto-generated method stub
		
	}

}
