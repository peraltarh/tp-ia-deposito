package com.fabrica;

import javax.ejb.Remote;

import vo.PedidoVO;

@Remote
public interface RecepcionRemote {
	public void recepcionCompra(PedidoVO pedido);

}
