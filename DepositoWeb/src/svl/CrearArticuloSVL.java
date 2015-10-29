package svl;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorDeposito;

/**
 * Servlet implementation class CrearArticuloSVL
 */
@WebServlet("/CrearArticuloSVL")
public class CrearArticuloSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearArticuloSVL() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		String marca = (String) request.getParameter("marca");
		int precio = Integer.parseInt(request.getParameter("precio"));
		String url = (String) request.getParameter("url");
		String origen = (String) request.getParameter("origen");
		String ficha = (String) request.getParameter("ficha");
		String categoria = (String) request.getParameter("categoria");
		int cantidad= Integer.parseInt(request.getParameter("cantidad"));

		controladorDep.nuevoArticulo(nombre,descripcion,marca,precio,url,origen,ficha,categoria,cantidad);
		response.getWriter().println("Articulo creado con exito");;
		

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
