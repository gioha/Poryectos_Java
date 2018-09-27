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
@Table(name = "ACUERDOS.COMISIONES_UNIDAS")
@NamedQuery(name = "DTOComisionesUnidas.findAll", query = "SELECT d FROM DTOComisionesUnidas d")
public class DTOComisionesUnidas extends DTOBase implements Serializable {
	private static final long serialVersionUID = -9181068735367500357L;

	@EmbeddedId
	private DTOComisionesUnidasPK id;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;

	public DTOComisionesUnidas() {
		
		this.id 		= new DTOComisionesUnidasPK();
		this.usuario 	= "";
		this.fechaHora	= new Date();
	}

	public DTOComisionesUnidasPK getId() {
		return id;
	}

	public void setId(DTOComisionesUnidasPK id) {
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
