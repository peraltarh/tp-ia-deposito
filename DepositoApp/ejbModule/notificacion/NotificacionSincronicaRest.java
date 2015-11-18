package notificacion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import configuracion.Configuracion;
import dto.EnvioDTO;
import dto.PedidoFabricaDTO;

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

	public void notificarEntregaArticulosDespacho(EnvioDTO envio) {

		String json = convertirAstring(envio);
		logger.info("Articulos a Enviar:" + json);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpPost httpPost = new
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
