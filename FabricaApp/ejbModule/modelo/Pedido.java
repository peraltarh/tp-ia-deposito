package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPedido;
	private Date fechaSolicitud;
	private EnumEstadoPedido estado;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedido")
	List<ItemPedido> itemsPedidosAFabrica;
	private Date fechaRecepcion;
	@Transient
	private Fabrica fabrica;

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

	public EnumEstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPedido estado) {
		this.estado = estado;
	}

	public List<ItemPedido> getItemsPedidosAFabrica() {
		return itemsPedidosAFabrica;
	}

	public void setItemsPedidosAFabrica(List<ItemPedido> itemsPedidosAFabrica) {
		this.itemsPedidosAFabrica = itemsPedidosAFabrica;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public String toString (){
		return String.valueOf(this.idPedido)+ " " + this.estado.toString();
	}
}
