package notificacion;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import bean.AdminConfiguracionBean;
import configuracion.Configuracion;
import configuracion.ConfiguracionAsincronica;
import configuracion.ConfiguracionSincronica;
import dto.ArticuloDTO;
import modelo.Articulo;
import wsLM.LogDTO;

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
	
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String fecha = formatFecha.format(articulo.getFechaAlta());
		
		articuloDTO.setFechaAlta(fecha);
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
		this.gruposAnotificar = adm.buscarConfiguracionAsincronica("MON");
				
		Iterator<Configuracion> itAsync = gruposAnotificar.iterator();
		
		String idModulo = "DEP-G12";
		String mensaje = "Articulo creado: Id " + articulo.getIdArticulo() + "Nombre:" + articulo.getNombre();
		String notificacion = idModulo + "_" + mensaje;
				
		while (itAsync.hasNext()) {
			Configuracion unaConfiguracion = itAsync.next();
			unaConfiguracion.notificar(notificacion);
		}
		
		this.gruposAnotificar = adm.buscarConfiguracionSincronica("MON");
		
		LogDTO detalle = new LogDTO();
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String fecha = formatFecha.format(new Date());
		
		detalle.setFecha(fecha);
		detalle.setIdModulo(idModulo);
		detalle.setMensaje(mensaje);
		Iterator<Configuracion> itSync = gruposAnotificar.iterator();
		
		while (itSync.hasNext()) {
			Configuracion unaConfiguracion = itSync.next();
			unaConfiguracion.notificarLog(detalle);
		}
		

	}


	private String obtenerLogXML(Articulo articulo) {
		LogDTO logLM = new LogDTO();
		//logLM.setFecha("ej fecha");
		//logLM.setIdModulo("DEP-G12");
		//logLM.descripcion("Se creo el articulo: Id " + articulo.getIdArticulo() + "Nombre:" + articulo.getNombre());

		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(LogDTO.class);
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
