package interfaces;

import javax.ejb.Remote;

@Remote
public interface PedidoFabricaService {
	public void recibirPedido(int idPedido);
}
