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
@Table(name = "envios")
public class Envio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEnvio;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSolicitudDePedido")
	private SolicitudDePedido solicitudDePedido;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEnvio")
	private List<ItemPedido> itemsEnviados;
	private Date fecha;
	// tal vez no haga falta este atributo
	private EnumEstadoEnvio estado;

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public SolicitudDePedido getSolicitudDePedido() {
		return solicitudDePedido;
	}

	public void setSolicitudDePedido(SolicitudDePedido solicitudDePedido) {
		this.solicitudDePedido = solicitudDePedido;
	}

	public List<ItemPedido> getItemsEnviados() {
		return itemsEnviados;
	}

	public void setItemsEnviados(List<ItemPedido> itemsEnviados) {
		this.itemsEnviados = itemsEnviados;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EnumEstadoEnvio getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoEnvio estado) {
		this.estado = estado;
	}

}
