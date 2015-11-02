package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import configuracion.Configuracion;
import configuracion.ConfiguracionSincronica;

@Stateless
public class AdminConfiguracionBean {
	@PersistenceContext(unitName = "DEP")
	private EntityManager em;


	@SuppressWarnings("unchecked")
	public List<Configuracion> buscarConfiguracion (String tipoModulo) {
		Query query = em.createQuery("SELECT c "
				+ "FROM Configuracion c "
				+ "WHERE c.IdGrupo like :tipoModulo"
				+ "AND c.activo = 's'");
		
		query.setParameter("tipoModulo","%"+tipoModulo+"%");
		
		return (List<Configuracion>)query.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Configuracion> buscarConfiguracionAsincronica (String tipoModulo) {
		Query query = em.createQuery("SELECT c "
				+ "FROM ConfiguracionAsincronica c "
				+ "WHERE c.IdGrupo like :tipoModulo "
				+ "AND c.activo = 's'");
		
		query.setParameter("tipoModulo","%"+tipoModulo+"%");
		
		return (List<Configuracion>)query.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Configuracion> buscarConfiguracionSincronica(String tipoModulo) {
		Query query = em.createQuery("SELECT c "
				+ "FROM ConfiguracionSincronica c "
				+ "WHERE c.IdGrupo like :tipoModulo "
				+ "AND c.activo = 's'");
		
		query.setParameter("tipoModulo","%"+tipoModulo+"%");
		
		return (List<Configuracion>) query.getResultList();
	}

}
