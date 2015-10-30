package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class PedidoFabricaDTO {
	@XmlElement
	private int idPedido;
	@XmlElement
	List<ItemsPedidoFabricaDTO> items;


	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public List<ItemsPedidoFabricaDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemsPedidoFabricaDTO> items) {
		this.items = items;
	}

}
