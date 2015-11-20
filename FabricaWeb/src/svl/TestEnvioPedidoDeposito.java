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

import controlador.ControladorFabrica;
import modelo.Articulo;
import modelo.ItemPedido;
import vo.PedidoVO;

/**
 * Servlet implementation class TestEnvioPediddoDeposito
 */
@WebServlet("/TestEnvioPedidoDeposito")
public class TestEnvioPedidoDeposito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ControladorFabrica controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestEnvioPedidoDeposito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PedidoVO pedido = new PedidoVO();
		pedido.setIdPedido(99999);

		controlador.cerrarPedido(pedido);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
