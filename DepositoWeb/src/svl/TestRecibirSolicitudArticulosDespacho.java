package svl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;
import mdb.Producer;

/**
 * Servlet implementation class TestQueue
 */
@WebServlet("/TestRecibirSolicitudArticulosDespacho")
public class TestRecibirSolicitudArticulosDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRecibirSolicitudArticulosDespacho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Producer prod = new Producer(null);
		Gson gson = new Gson();

		SolicitudArticuloDTO solicitudArticuloJSON = new SolicitudArticuloDTO();
		solicitudArticuloJSON.setIdSolicitudArticulo(1);
		solicitudArticuloJSON.setIdDespacho("DES-GXX");
		solicitudArticuloJSON.setItems(new ArrayList<ItemSolicitudArticuloDTO>());
		
		for(int i = 0; i<3; i++){
			ItemSolicitudArticuloDTO itemSolicitudArticuloJSON = new ItemSolicitudArticuloDTO();
			itemSolicitudArticuloJSON.setIdArticulo(i+1);
			itemSolicitudArticuloJSON.setCantidad((i+1)*i);
			solicitudArticuloJSON.getItems().add(itemSolicitudArticuloJSON);
		}
		
		String json = gson.toJson(solicitudArticuloJSON);

		prod.notificar(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
