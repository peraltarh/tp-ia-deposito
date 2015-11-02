package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.EnumEstadoPedido;
import modelo.Pedido;

/**
 * Session Bean implementation class AdminPedido
 */
@Stateless
public class AdminPedidoBean {

	@PersistenceContext(unitName= "FAC")
	private EntityManager em;
	
	
	
	public AdminPedidoBean() {
		super();
	}



	@SuppressWarnings("unchecked")
	public List<Pedido> obtenerPedidosEnEstado(EnumEstadoPedido estado){
		Query q = em.createQuery("FROM Pedido p WHERE p.estado = :est ").setParameter("est", estado);
		return (List<Pedido>) q.getResultList();
	}
	
	public void persistirPedido (Pedido pedido){
		em.persist(pedido);
	}
	

}
