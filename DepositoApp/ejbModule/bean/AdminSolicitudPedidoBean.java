package bean;

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

@Stateless
public class AdminSolicitudPedidoBean {
	@PersistenceContext(unitName = "DEP")
	private EntityManager em;

	public void nuevaSolicitudPedido(SolicitudDePedido solPe) {
		em.persist(solPe);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SolicitudDePedido> obtenerSolicitudesPedidoEnEstado(EnumSolicitudDePedido estado){
		Query q = em.createQuery("FROM SolicitudDePedido solpe WHERE estado =:status").setParameter("status",estado);
		List<SolicitudDePedido> resultado = (List<SolicitudDePedido>) q.getResultList();

		return resultado;
	}


}
