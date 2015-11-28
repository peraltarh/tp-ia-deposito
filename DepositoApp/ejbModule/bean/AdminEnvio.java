package bean;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Envio;


/**
 * Session Bean implementation class AdminPedido
 */
@Stateless
public class AdminEnvio {

	@PersistenceContext(unitName= "DEP")
	private EntityManager em;
	

	public Envio grabarEnvio(Envio envio) {
		em.persist(envio);
		return envio;
	}
	
}

