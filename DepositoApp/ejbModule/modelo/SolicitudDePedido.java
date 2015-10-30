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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes_pedido")
public class SolicitudDePedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idSolicitudDePedido;
	private EnumSolicitudDePedido estado;
	private Date fecha;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSolicitudDePedido")
	private List<ItemPedido> itemsPedido;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedido")
	private Pedido pedido;
	private String idDespacho;
	// id enviado por Despacho
	private int idSolicitudArticuloDespacho;

	public long getIdSolicitudDePedido() {
		return idSolicitudDePedido;
	}

	public void setIdSolicitudDePedido(long idSolicitudDePedido) {
		this.idSolicitudDePedido = idSolicitudDePedido;
	}

	public EnumSolicitudDePedido getEstado() {
		return estado;
	}

	public void setEstado(EnumSolicitudDePedido estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(List<ItemPedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getIdDespacho() {
		return idDespacho;
	}

	public void setIdDespacho(String idDespacho) {
		this.idDespacho = idDespacho;
	}

	public int getIdSolicitudArticulo() {
		return idSolicitudArticuloDespacho;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticuloDespacho = idSolicitudArticulo;
	}

}
