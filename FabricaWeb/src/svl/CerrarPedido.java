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

import controlador.ControladorFabrica;
import vo.PedidoVO;


/**
 * Servlet implementation class BusquedaProductos
 */
@WebServlet("/CerrarPedido")
public class CerrarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@EJB
	private ControladorFabrica controladorFab;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt((String)request.getSession().getAttribute("idPedido"));
		
		
		List<PedidoVO> pedidos = controladorFab.buscarPedidos();
		for (PedidoVO pedidoVO : pedidos) {
			if (pedidoVO.getIdPedido() == id){
				controladorFab.cerrarPedido(pedidoVO);
			}
		}
		response.getWriter().println("Pedido cerrado con exito");

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
