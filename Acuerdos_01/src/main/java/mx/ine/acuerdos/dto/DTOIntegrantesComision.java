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
@Table(name = "ACUERDOS.INTEGRANTES_COMISION")
@NamedQuery(name = "DTOIntegrantesComision.findAll", query = "SELECT d FROM DTOIntegrantesComision d")
public class DTOIntegrantesComision extends DTOBase implements Serializable {
	private static final long serialVersionUID = -8687783579414738742L;

	@EmbeddedId
	private DTOIntegrantesComisionPK id;

	@Column(name = "ID_TIPO_INTEGRANTE", nullable = false)
	private Integer idTipoIntegrante;
	
	@Column(name = "ESTATUS", nullable = false)
	private Integer estatus;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;

	public DTOIntegrantesComision() {
		this.id					= new DTOIntegrantesComisionPK();
		this.idTipoIntegrante	= new Integer(0);
		this.estatus			= new Integer(0);
		this.usuario			= ("");
		this.fechaHora			= new Date();
	}

	public DTOIntegrantesComisionPK getId() {
		return id;
	}

	public void setId(DTOIntegrantesComisionPK id) {
		this.id = id;
	}

	public Integer getIdTipoIntegrante() {
		return idTipoIntegrante;
	}

	public void setIdTipoIntegrante(Integer idTipoIntegrante) {
		this.idTipoIntegrante = idTipoIntegrante;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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