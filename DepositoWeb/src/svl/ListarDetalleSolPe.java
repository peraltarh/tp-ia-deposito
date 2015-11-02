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
import vo.PedidoVO;
import vo.SolicitudDePedidoVO;

/**
 * Servlet implementation class ListarSolicitudesPedidosPendientes
 */
@WebServlet("/ListarSolicitudesPedidosPendientes")
public class ListarDetalleSolPe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarDetalleSolPe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idSolPe = Integer.parseInt(request.getParameter("filtro"));
		
		controladorDep.buscarSolicitud(idSolPe);
//VIEJO		
//		
//		List<SolicitudDePedidoVO> solicitudes = controladorDep.listarPedidosPendientes();
//		
//		request.setAttribute("listadoSolicitudesPedidosPendientes", solicitudes);
//
//		RequestDispatcher rd = request.getRequestDispatcher("listadoSolicitudesPedidosPendientes.jsp");
//		rd.forward(request, response);
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
