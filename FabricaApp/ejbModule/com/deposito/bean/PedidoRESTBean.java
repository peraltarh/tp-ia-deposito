package com.deposito.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class PedidoRESTBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int idPedido;
	@XmlElement
	private Date fechaSolicitud;
	@XmlElement
	List<ItemPedidoRESTBean> itemsPedidosAFabrica;
	@XmlElement
	private Date fechaRecepcion;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}


	public List<ItemPedidoRESTBean> getItemsPedidosAFabrica() {
		return itemsPedidosAFabrica;
	}

	public void setItemsPedidosAFabrica(List<ItemPedidoRESTBean> itemsPedidosAFabrica) {
		this.itemsPedidosAFabrica = itemsPedidosAFabrica;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

}
