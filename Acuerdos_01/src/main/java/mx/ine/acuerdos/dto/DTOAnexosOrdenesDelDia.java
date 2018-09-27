package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.ANEXOS_ORDENES_DEL_DIA")
@NamedQuery(name = "DTOAnexosOrdenesDelDia.findAll", query = "SELECT d FROM DTOAnexosOrdenesDelDia d")
public class DTOAnexosOrdenesDelDia extends DTOBase implements Serializable {
	private static final long serialVersionUID = -4926599095299720522L;

	@EmbeddedId
	private DTOAnexosOrdenesDelDiaPK id;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;

	public DTOAnexosOrdenesDelDia() {
		
		this.id 		= new DTOAnexosOrdenesDelDiaPK();
		this.usuario 	= "";
		this.fechaHora	= new Date();
	}

	public DTOAnexosOrdenesDelDiaPK getId() {
		return id;
	}

	public void setId(DTOAnexosOrdenesDelDiaPK id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

}
