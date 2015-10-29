package svl;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorDeposito;
import vo.ArticuloVO;

/**
 * Servlet implementation class BusquedaProductos
 */
@WebServlet("/BusquedaArticulos")
public class BusquedaArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusquedaArticulos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filtro = (String) request.getParameter("filtro");

		List<ArticuloVO> articulos = controladorDep.buscarArticulos(filtro);
		request.setAttribute("listaArticulos", articulos);

		RequestDispatcher rd = request.getRequestDispatcher("listadoArticulos.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
