package bean;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Articulo;
import modelo.EnumEstadoPedido;
import modelo.EnumSolicitudDePedido;
import modelo.Pedido;
import modelo.SolicitudDePedido;

/**
 * Session Bean implementation class AdminPedido
 */
@Stateless
public class AdminPedidoBean {

	@PersistenceContext(unitName= "DEP")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<SolicitudDePedido> obtenerSolicitudesDePedidoEnEstado(EnumSolicitudDePedido estado){
		Query q = em.createQuery("FROM SolicitudDePedido solpe WHERE estado =:status").setParameter("status",estado);
		List<SolicitudDePedido> resultado = (List<SolicitudDePedido>) q.getResultList();

		return resultado;
	}

	public void actualizarFechaRecepcionPedido(int idSolicitudCompra, Date fechaRecepcion) {
		Pedido pedido = em.find(Pedido.class, idSolicitudCompra);
		if (pedido != null)
			pedido.setEstado(EnumEstadoPedido.ENTREGADO);
			pedido.setFechaRecepcion(fechaRecepcion);
			em.merge(pedido);
	}
	
	public int grabarPedido(Pedido pedido){
		em.persist(pedido);
		return pedido.getIdPedido();
	}

	public Pedido buscarPedido(int idPedido) {
	    Pedido pedido = em.find(Pedido.class, idPedido);
		return pedido;	
	}
	
}

