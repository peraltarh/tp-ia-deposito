package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Articulo;
import modelo.Categoria;
import modelo.Stock;

@Stateless
public class AdminArticuloBean {
	@PersistenceContext(unitName = "DEP")
	private EntityManager em;

	public void nuevoArticulo(Articulo art, long idCategoria) {
		Categoria categoria = em.find(Categoria.class, idCategoria);
		art.setTipo(categoria);
		em.persist(art);
	}

	public void actualizarStockArticulo(int idStock, int nuevoStock) {
		Stock stock = em.find(Stock.class, idStock);
		if (stock != null)
			stock.setCantidad(nuevoStock);
			em.merge(stock);
	}

	@SuppressWarnings("unchecked")
	public List<Articulo> buscarArticulos (String criterioBusqueda) {
		Query query = em.createQuery("SELECT a "
				+ "FROM Articulo a join a.tipo c "
				+ "WHERE a.nombre like :criterio or "
				+ " a.descripcion like :criterio or "
				+ " c.nombre like :criterio");
		
		query.setParameter("criterio","%"+criterioBusqueda+"%");
		List<Articulo> result = (List<Articulo>)query.getResultList();
		
		return result;	
	}

	public void crearStock(Stock stock) {
		em.persist(stock);
		
	}

	public Stock buscarStock(int idArticulo) {
		Query query = em.createQuery("SELECT s "
				+ "FROM Stock s "
				+ "WHERE s.articulo.id = :id");
		
		query.setParameter("id",idArticulo);
		
		return (Stock) query.getSingleResult();	
	}

	public Articulo obtenerArticulo(int idArticulo) {
		Articulo articulo = em.find(Articulo.class, idArticulo);
		return articulo;	
		
	}
	
	public List<Categoria> obtenerCategorias() {
		Query query = em.createQuery("SELECT c "
				+ "FROM Categoria c ");
		
		List<Categoria> result = (List<Categoria>)query.getResultList();
		return result;
	}
}
