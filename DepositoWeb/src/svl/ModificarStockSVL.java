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
 * Servlet implementation class ModificarStockSVL
 */
@WebServlet("/ModificarStockSVL")
public class ModificarStockSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarStockSVL() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		int idStock = Integer.parseInt((String) request.getSession().getAttribute("idStock"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));

		controladorDep.actualizarStock(idStock, cantidad);
		
		response.getWriter().println("Stock modificado con exito");;

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
