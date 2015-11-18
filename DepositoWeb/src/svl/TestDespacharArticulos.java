package svl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Articulo;
import modelo.Envio;
import modelo.ItemPedido;
import modelo.SolicitudDePedido;
import notificacion.AdminNoticacionBean;

/**
 * Servlet implementation class TestDespacharArticulos
 */
@WebServlet("/TestDespacharArticulos")
public class TestDespacharArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	AdminNoticacionBean notificacion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDespacharArticulos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		SolicitudDePedido solicitud = new SolicitudDePedido();
		solicitud.setIdSolicitudArticulo(222222);
	
		List<ItemPedido> items = new LinkedList<ItemPedido>();
		
		ItemPedido ite = new ItemPedido();
		
		Articulo art = new Articulo();
		art.setCodArticulo(333333);
		art.setDescripcion("Articulo 1");
		ite.setCantidad(5);
		ite.setArticulo(art);
		
		items.add(ite);
		
		ItemPedido ite2 = new ItemPedido(); 
		
		Articulo art2 = new Articulo();
		art2.setCodArticulo(444444);
		art2.setDescripcion("Articulo 2");
		ite2.setCantidad(34);
		ite2.setArticulo(art2);
		
		items.add(ite2);
		
		solicitud.setItemsPedido(items);
		solicitud.setIdDespacho("DES-G08");
		
		Envio env = new Envio();
		env.setItemsEnviados(items);
		
		/*SolicitudDePedido solicitud2 = new SolicitudDePedido();
		solicitud2 = solicitud;
		solicitud2.setIdDespacho("DES-G02");*/
		
		notificacion.entregarArticulosDespacho(solicitud, env);
		//notificacion.entregarArticulosDespacho(solicitud2, env);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
