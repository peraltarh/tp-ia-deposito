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
import controlador.ControladorDeposito;
import dto.ItemsPedidoFabricaDTO;
import dto.PedidoFabricaDTO;

/**
 * Servlet implementation class TestRegistrarRecepcionArticulosFabrica
 */
@WebServlet("/TestRegistrarRecepcionArticulosFabrica")
public class TestRegistrarRecepcionArticulosFabrica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ControladorDeposito dep;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRegistrarRecepcionArticulosFabrica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PedidoFabricaDTO pedido = new PedidoFabricaDTO();
		pedido.setIdPedido(1000);
		
		List<ItemsPedidoFabricaDTO> items = new LinkedList<ItemsPedidoFabricaDTO>();
		ItemsPedidoFabricaDTO ite = new ItemsPedidoFabricaDTO();
		ite.setIdArticulo(1);
		ite.setCantidad(2);
		items.add(ite);
		pedido.setItems(items);
		
		/*
		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(PedidoFabricaDTO.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(pedido, writer);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(writer.toString()); 
		*/
		int idPedido = 99999;
		dep.registrarRecepcionArticulosFabrica(idPedido);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
