package com.deposito.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itempedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemPedidoRESTBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int idItemPedido;
	@XmlElement
	private ArticuloRESTBean articulo;
	@XmlElement
	private int cantidad;

	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public ArticuloRESTBean getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloRESTBean articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


}
