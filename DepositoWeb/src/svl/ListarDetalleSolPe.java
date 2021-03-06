package svl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorDeposito;
import modelo.ItemPedido;
import vo.PedidoVO;
import vo.SolicitudDePedidoVO;
import vo.ItemPedidoVO;

/**
 * Servlet implementation class ListarSolicitudesPedidosPendientes
 */
@WebServlet("/ListarDetalleSolPe")
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

		int idSolPe2= Integer.parseInt((String)request.getSession().getAttribute("idSolPe"));

		SolicitudDePedidoVO solPeVO= controladorDep.buscarSolicitud(idSolPe2);
		List<ItemPedidoVO> itemsVO = controladorDep.getItemsPedidoSolPe(idSolPe2);
		
		//CAMBIO
		solPeVO.setItemsPedido(itemsVO);
		ArrayList<Integer> cantidadesStock = new ArrayList<Integer>();
		for(ItemPedidoVO itemVO : solPeVO.getItemsPedido())
		{
			int cant = 0;
			cant=controladorDep.getStockArticulo(itemVO.getArticulo().getIdArticulo());
			cantidadesStock.add(cant);
		}
		request.setAttribute("solicitudVO", solPeVO);
		request.setAttribute("cantidadesStock", cantidadesStock);

		RequestDispatcher rd = request.getRequestDispatcher("listadoDetalleSolPe.jsp");
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
