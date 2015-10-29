package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Articulo;
import modelo.SolicitudDePedido;

@Stateless
public class AdminSolicitudPedidoBean {
	@PersistenceContext(unitName = "DEP")
	private EntityManager em;

	public void nuevaSolicitudPedido(SolicitudDePedido solPe) {
		em.persist(solPe);
		
	}

}
