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
import vo.EnumSolicitudDePedidoVO;
import vo.SolicitudDePedidoVO;

/**
 * Servlet implementation class GenerarPedido
 */
@WebServlet("/GenerarPedido")
public class GenerarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    @EJB
    private ControladorDeposito conDep;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<SolicitudDePedidoVO> pedidos = conDep.obtenerSolicitudesDePedidoEnEstado(EnumSolicitudDePedidoVO.PENDIENTE);
		request.setAttribute("listaSolPes", pedidos);
		RequestDispatcher rd = request.getRequestDispatcher("listadoSolicitudesPedido.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
