package notificacion;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import bean.AdminConfiguracionBean;
import configuracion.Configuracion;
import dto.ArticuloDTO;
import dto.DetalleLogLmDTO;
import modelo.Articulo;

@Stateless
public class AdminNoticacionBean {

	private List<Configuracion> gruposAnotificar;

	@EJB
	private AdminConfiguracionBean adm;

	public void informarArticulo(Articulo articulo, String tipoModulo) {

		this.gruposAnotificar = adm.buscarConfiguracionAsincronica(tipoModulo);

		Iterator<Configuracion> it = gruposAnotificar.iterator();
		String notificacion = obtenerArticuloXML(articulo);

		while (it.hasNext()) {
			Configuracion unaConfiguracion = it.next();
			unaConfiguracion.notificar(notificacion);
		}

	}

	private String obtenerArticuloXML(Articulo articulo) {
		ArticuloDTO articuloDTO = new ArticuloDTO();
		articuloDTO.setCategoria(articulo.getTipo().getNombre());
		articuloDTO.setDescripcion(articulo.getDescripcion());
		articuloDTO.setFechaAlta(articulo.getFechaAlta().toString());
		articuloDTO.setFichaTecnica(articulo.getFichaTecnica());
		articuloDTO.setIdArticulo(articulo.getIdArticulo());
		articuloDTO.setIdDeposito("DEP-G12");
		articuloDTO.setMarca(articulo.getMarca());
		articuloDTO.setNombre(articulo.getNombre());
		articuloDTO.setOrigen(articulo.getOrigen());
		articuloDTO.setPrecio(articulo.getPrecio());
		articuloDTO.setUrlFoto(articulo.getUrlFoto());

		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(ArticuloDTO.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(articuloDTO, writer);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

	public void informarArticuloLM(Articulo articulo) {
		this.gruposAnotificar = adm.buscarConfiguracion("MON");
				
		Iterator<Configuracion> it = gruposAnotificar.iterator();
		String notificacion = obtenerLogXML(articulo);

		while (it.hasNext()) {
			Configuracion unaConfiguracion = it.next();
			unaConfiguracion.notificar(notificacion);
		}

	}

	private String obtenerLogXML(Articulo articulo) {
		DetalleLogLmDTO logLM = new DetalleLogLmDTO();
		logLM.setFecha("ej fecha");
		logLM.setModulo("DEP-G12");
		logLM.descripcion("Se creo el articulo: Id " + articulo.getIdArticulo() + "Nombre:" + articulo.getNombre());

		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(DetalleLogLmDTO.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(logLM, writer);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

}
