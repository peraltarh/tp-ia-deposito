package bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.EnumSolicitudDePedido;
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

}
