package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Articulo;
import modelo.EnumEstadoPedido;
import modelo.EnumSolicitudDePedido;
import modelo.ItemPedido;
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

	public SolicitudDePedido buscarSolPe(long idSolPe) {
		return em.find(SolicitudDePedido.class, idSolPe);
	}

	public List<ItemPedido> getItemsPedidoDeSolpe(long idSolPe){
		Query q = em.createQuery("FROM ItemPedido iP WHERE idSolicitudDePedido =:idSolPe AND idPedido IS NULL").setParameter("idSolPe",idSolPe);
		List<ItemPedido> resultado = (List<ItemPedido>) q.getResultList();

		return resultado;
	}

}
