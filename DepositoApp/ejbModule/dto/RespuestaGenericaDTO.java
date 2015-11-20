package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resultado")
public class RespuestaGenericaDTO implements Serializable {
	private static final long serialVersionUID = 5934831354674400715L;

	private String estate;

	private String message;

	@XmlAttribute(name = "estado")
	public String getEstado() {
		return estate;
	}

	public void setEstado(String estado) {
		this.estate = estado;
	}

	@XmlAttribute(name = "mensaje")
	public String getMensaje() {
		return message;
	}

	public void setMensaje(String mensaje) {
		this.message = mensaje;
	}
}
