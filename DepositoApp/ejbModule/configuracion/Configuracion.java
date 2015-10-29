package configuracion;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoRecurso", discriminatorType = DiscriminatorType.STRING)
public class Configuracion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdConfiguracion;
	// Ej: XXX-GYY: Donde XXX -> Tipo de Grupo YY Nro. de Grupo
	// DES-G01 - DEP-G02.. etc.
	private String IdGrupo;
	private String descripcion;
	private String recurso;
	private String url;

	public int getIdConfiguracion() {
		return IdConfiguracion;
	}

	public void setIdConfiguracion(int idConfiguracion) {
		IdConfiguracion = idConfiguracion;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getIdGrupo() {
		return IdGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		IdGrupo = idGrupo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void notificar(String notificacion) {
		// TODO Auto-generated method stub

	}

}
