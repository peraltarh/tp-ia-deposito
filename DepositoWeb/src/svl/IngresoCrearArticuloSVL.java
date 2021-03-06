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
import vo.CategoriaVO;

/**
 * Servlet implementation class CrearArticuloSVL
 */
@WebServlet("/IngresoCrearArticuloSVL")
public class IngresoCrearArticuloSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IngresoCrearArticuloSVL() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CategoriaVO> categoriasVO = controladorDep.obtenerCategorias();
		request.setAttribute("categorias", categoriasVO);
		RequestDispatcher rd = request.getRequestDispatcher("nuevoArticulo.jsp");
		rd.forward(request, response);
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
