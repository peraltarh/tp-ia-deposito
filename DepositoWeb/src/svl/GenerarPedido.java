package svl;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
import vo.EnumEstadoPedidoVO;
import vo.EnumSolicitudDePedidoVO;
import vo.FabricaVO;
import vo.ItemPedidoVO;
import vo.PedidoVO;
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
		RequestDispatcher rd = request.getRequestDispatcher("GenerarPedido.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PedidoVO pedido = new PedidoVO();
		pedido.setEstado(EnumEstadoPedidoVO.PENDIENTE);
		pedido.setFabrica(new FabricaVO());
		Date date = new Date();
		pedido.setFechaRecepcion(null);
		pedido.setFechaSolicitud(date);
		// Genero la lista de items que voy a pedir a Fabrica.
		List<ItemPedidoVO> itemsPedidosAFabrica = new ArrayList<ItemPedidoVO>();
		String[] lista = request.getParameterValues("solpesSeleccionadas");
		for (String string : lista) {
			int SolpeInicio = 0;
			int SolpeFin = string.indexOf("-");
			String solpeString = string.substring(SolpeInicio, SolpeFin);
			int nroSolpe = Integer.parseInt(solpeString);
			SolicitudDePedidoVO solpe = conDep.buscarSolicitud(nroSolpe);
			int itemInicio = string.indexOf("-") + 1;
			int itemFin = string.length();
			String itemString = string.substring(itemInicio, itemFin);
			int nroItem = Integer.parseInt(itemString);
			ItemPedidoVO item = solpe.getItemPedido(nroItem);
			itemsPedidosAFabrica.add(item);
		}
		pedido.setItemsPedidosAFabrica(itemsPedidosAFabrica);
		pedido.setIdPedido(conDep.generarPedido(pedido));
		request.setAttribute("nroPedido", pedido.getIdPedido());
		RequestDispatcher rd = request.getRequestDispatcher("pedidoExitoso.jsp");
		rd.forward(request, response);
	}

}
