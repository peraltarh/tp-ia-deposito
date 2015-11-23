package notificacion;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.monitor.webservice.LogDTO;

import bean.AdminConfiguracionBean;
import configuracion.Configuracion;
import dto.ArticuloDTO;
import dto.EnvioDTO;
import dto.ItemSolicitudArticuloDTO;
import modelo.Articulo;
import modelo.Envio;
import modelo.ItemPedido;
import modelo.SolicitudDePedido;



@Stateless
public class AdminNoticacionBean {

	private List<Configuracion> gruposAnotificar;

	@EJB
	private AdminConfiguracionBean adm;
	
	private static Logger logger = Logger.getLogger(AdminNoticacionBean.class.getName());
	
	public void informarArticulo(Articulo articulo, String tipoModulo) {

		this.gruposAnotificar = adm.buscarConfiguracionAsincronica(tipoModulo);

		Iterator<Configuracion> it = gruposAnotificar.iterator();
		String notificacion = obtenerArticuloXML(articulo);
		
		logger.info("Notificación Asíncrona Portal/Despacho: " + notificacion);
		informarLogLM("Notificación Asíncrona Portal/Despacho: " + notificacion);
		

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
		articuloDTO.setIdArticulo(articulo.getCodArticulo());
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
		String mensaje = "Articulo creado: Codigo " + articulo.getCodArticulo() + " Nombre: " + articulo.getNombre();
		String notificacion = idModulo + "_" + mensaje;
		
		logger.info("Notificación Asíncrona LM: " + "Mensaje " + notificacion);
		informarLogLM("Notificación Asíncrona LM: " + "Mensaje " + notificacion);
		
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
		
		logger.info("Notificación Síncrona LM: " + "Fecha/Modulo/Mensaje " + detalle.getFecha() + "/"+ detalle.getIdModulo() + "/" + detalle.getMensaje());
		informarLogLM("Notificación Síncrona LM: " + "Fecha/Modulo/Mensaje " + detalle.getFecha() + "/"+ detalle.getIdModulo() + "/" + detalle.getMensaje());
		
		while (itSync.hasNext()) {
			Configuracion unaConfiguracion = itSync.next();
			unaConfiguracion.notificarLog(detalle);
		}
		
	}
	
	public void entregarArticulosDespacho(SolicitudDePedido solPe, Envio envio) {
		String idDespacho = solPe.getIdDespacho();
		
		this.gruposAnotificar = adm.buscarConfiguracionSincronica(idDespacho);
		
		EnvioDTO envioDTO = new EnvioDTO();
		List<ItemSolicitudArticuloDTO> itemsEnvioDTO = new LinkedList<ItemSolicitudArticuloDTO>();
		
		List<ItemPedido> itemsEnvio = envio.getItemsEnviados(); 
		Iterator <ItemPedido> itEnv = itemsEnvio.iterator();
		
		logger.info("Entrega Articulos a Despacho");
		informarLogLM("Entrega Articulos a Despacho");
		
		
		while (itEnv.hasNext()){
			ItemPedido itemEnvio = itEnv.next();
			ItemSolicitudArticuloDTO itemDTO = new ItemSolicitudArticuloDTO();
			itemDTO.setIdArticulo(itemEnvio.getArticulo().getCodArticulo());
			itemDTO.setCantidad(itemEnvio.getCantidad());
			itemsEnvioDTO.add(itemDTO);			
		}
		
		envioDTO.setItems(itemsEnvioDTO);
		envioDTO.setIdDeposito("DEP-G12");
		envioDTO.setIdSolicitud(solPe.getIdSolicitudArticulo());
				
		Iterator<Configuracion> itSync = gruposAnotificar.iterator();
		
		while (itSync.hasNext()) {
			Configuracion unaConfiguracion = itSync.next();
			unaConfiguracion.notificarEntregaArticulosDespacho(envioDTO);
		}
		
	}
	
	public void informarLogLM(String mensajeLog) {
		this.gruposAnotificar = adm.buscarConfiguracionAsincronica("MON");
				
		Iterator<Configuracion> itAsync = gruposAnotificar.iterator();
		
		String idModulo = "DEP-G12";
		String mensaje = mensajeLog;
		String notificacion = idModulo + "_" + mensaje;
		
		logger.info("Notificación Asíncrona LM: " + "Mensaje " + notificacion);
//		informarLogLM("Notificación Asíncrona LM: " + "Mensaje " + notificacion);
		
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
		
		logger.info("Notificación Síncrona LM: " + "Fecha/Modulo/Mensaje " + detalle.getFecha() + "/"+ detalle.getIdModulo() + "/" + detalle.getMensaje());
//		informarLogLM("Notificación Síncrona LM: " + "Fecha/Modulo/Mensaje " + detalle.getFecha() + "/"+ detalle.getIdModulo() + "/" + detalle.getMensaje());
		
		while (itSync.hasNext()) {
			Configuracion unaConfiguracion = itSync.next();
			unaConfiguracion.notificarLog(detalle);
		}
		
	}	

}
