/**
 * 
 */
package dto;

import java.util.List;

public class SolicitudArticuloDTO {

	private int idSolicitudArticulo;
	private List<ItemSolicitudArticuloDTO> items;
	private String idDespacho;

	public int getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}

	public String getIdDespacho() {
		return idDespacho;
	}

	public void setIdDespacho(String idDespacho) {
		this.idDespacho = idDespacho;
	}

	public List<ItemSolicitudArticuloDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemSolicitudArticuloDTO> items) {
		this.items = items;
	}

}
