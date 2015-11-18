package dto;

import java.util.List;

public class EnvioDTO {

	private int idSolicitudArticulo;

	private List<ItemSolicitudArticuloDTO> items;

	private String idDeposito;

	public int idSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitudArticulo = idSolicitud;
	}

	public List<ItemSolicitudArticuloDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemSolicitudArticuloDTO> items) {
		this.items = items;
	}

	public int getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}

	public String getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}

}
