package svl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.AdminArticuloBean;
import bean.AdminDepositoBean;
import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;
import mdb.Producer;
import modelo.Articulo;
import modelo.Stock;

/**
 * Servlet implementation class TestQueue
 */

@WebServlet("/TestRecibirSolicitudArticulosDespacho")
public class TestRecibirSolicitudArticulosDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB
	AdminDepositoBean adminDep;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRecibirSolicitudArticulosDespacho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Producer prod = new Producer(null);
		Gson gson = new Gson();

		Articulo art = new Articulo();
		art.setIdArticulo(0);
		art.setNombre("art1");
		art.setFechaAlta(new Date());
		art.setCodArticulo(1);
		art.setPrecio(10);
		Stock stock = new Stock();
		stock.setArticulo(art);
		stock.setCantidad(100);
		
		adminDep.nuevoArticulo(art, stock,1);
		
		art = new Articulo();
		art.setIdArticulo(0);
		art.setNombre("art2");
		art.setFechaAlta(new Date());
		art.setCodArticulo(1);
		art.setPrecio(10);
		stock = new Stock();
		stock.setArticulo(art);
		stock.setCantidad(100);
		
		adminDep.nuevoArticulo(art, stock,1);
		
		art = new Articulo();
		art.setIdArticulo(0);
		art.setNombre("art3");
		art.setFechaAlta(new Date());
		art.setCodArticulo(1);
		art.setPrecio(10);
		stock = new Stock();
		stock.setArticulo(art);
		stock.setCantidad(100);
		
		adminDep.nuevoArticulo(art, stock,1);
		
		SolicitudArticuloDTO solicitudArticuloJSON = new SolicitudArticuloDTO();
		solicitudArticuloJSON.setIdSolicitudArticulo(1);
		solicitudArticuloJSON.setIdDespacho("DES-GXX");
		solicitudArticuloJSON.setItems(new ArrayList<ItemSolicitudArticuloDTO>());
		
		for(int i = 0; i<3; i++){
			ItemSolicitudArticuloDTO itemSolicitudArticuloJSON = new ItemSolicitudArticuloDTO();
			switch (i) {
			case 0:
				itemSolicitudArticuloJSON.setIdArticulo(1);
				break;
			case 1:
				itemSolicitudArticuloJSON.setIdArticulo(3);
				break;
			case 2:
				itemSolicitudArticuloJSON.setIdArticulo(5);
				break;

			default:
				break;
			}

			itemSolicitudArticuloJSON.setCantidad((i+1)*i);
			solicitudArticuloJSON.getItems().add(itemSolicitudArticuloJSON);
		}
		
		String json = gson.toJson(solicitudArticuloJSON);

		prod.notificar(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
