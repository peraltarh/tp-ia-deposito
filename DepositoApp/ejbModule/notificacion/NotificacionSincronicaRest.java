package notificacion;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import configuracion.Configuracion;
import dto.EnvioDTO;
import dto.PedidoFabricaDTO;
import dto.RespuestaGenericaDTO;

public class NotificacionSincronicaRest {
	private String url;
	private static Logger logger = Logger.getLogger(AdminNoticacionBean.class.getName());

	public NotificacionSincronicaRest(Configuracion configuracion) {
		try {
			url = "http://" + configuracion.getUrl() + "/" + configuracion.getRecurso();

		} catch (

		Exception e)

		{
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	public void notificar(String notificacion) {

	}

	public RespuestaGenericaDTO notificarEntregaArticulosDespacho(EnvioDTO envio) {
		String respuestaXML = null;
		String json = convertirAstring(envio);
		logger.info("Articulos a Enviar a Despacho:" + json);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpPost("http://192.168.43.5:8080/TPO-DespachoG7-WEB/rest/solicitudes/recibirArticulos");
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(json));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusLine());

		HttpEntity entity = response.getEntity();
		try {
			respuestaXML = EntityUtils.toString(entity);
			System.out.println(respuestaXML);
			EntityUtils.consume(entity);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RespuestaGenericaDTO respuesta = obtenerRespuesta(respuestaXML);

		logger.info("Envío de Articulos finalizado");
		return respuesta;

	}

	private RespuestaGenericaDTO obtenerRespuesta(String respuestaXML) {
		Unmarshaller unmarshaller = null;
		RespuestaGenericaDTO respuesta = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaGenericaDTO.class);
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringReader reader;
		try {
			reader = new StringReader(respuestaXML);
			respuesta = (RespuestaGenericaDTO) unmarshaller.unmarshal(reader);
		} catch (ParseException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	public void notificarPedidoFabrica(PedidoFabricaDTO pedido) {
/*
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlConnection.setDoOutput(true);
		try {
			urlConnection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlConnection.setRequestProperty("Content-Type", "application/json");

		String json = convertirAstring(pedido);

		try {
			IOUtils.write(json, urlConnection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		// close session
	}

	private String convertirAstring(Object clase) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(clase);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
