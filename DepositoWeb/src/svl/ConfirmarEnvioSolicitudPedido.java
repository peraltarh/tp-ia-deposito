package svl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import controlador.ControladorDeposito;

/**
 * Servlet implementation class ModificarStockSVL
 */
@WebServlet("/ConfirmarEnvioSolicitudPedido")
public class ConfirmarEnvioSolicitudPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ControladorDeposito controladorDep;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmarEnvioSolicitudPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		int i = Integer.parseInt((String)request.getSession().getAttribute("cantI"));
		ArrayList<Integer> cantidadesEnviar = new ArrayList<Integer>();
		for(int j=0; j<i; j++)
		{
			String valor = request.getParameter("cantidadEnviar"+j);
			System.out.println(valor);
			cantidadesEnviar.add(Integer.parseInt(valor));
		}
		int idSolPe = Integer.parseInt((String) request.getSession().getAttribute("idSolPe"));
		controladorDep.confirmarEnvioSolicitudPedido(idSolPe, cantidadesEnviar);
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
