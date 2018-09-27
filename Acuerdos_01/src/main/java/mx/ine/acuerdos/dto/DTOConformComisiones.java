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
@Table(name = "ACUERDOS.CONFORMACION_COMISIONES")
@NamedQuery(name = "DTOConformComisiones.findAll", query = "SELECT d FROM DTOConformComisiones d")
public class DTOConformComisiones extends DTOBase implements Serializable {
	private static final long serialVersionUID = -3594387932699238206L;

	@EmbeddedId
	private DTOConformComisionesPK id;

	@Column(name="TERMINO_PERIODO", nullable = true)
	private Date terminoPeriodo;

	@Column(name="ACUERDO_ADJUNTO", nullable = true)
	private String acuerdoAdjunto;

	@Column(name="PLAN_ADJUNTO", nullable = true)
	private String planAdjunto;

	@Column(name="ESTATUS", nullable = false)
	private Integer estatus;

	@Column(name="USUARIO", nullable = false)
	private String usuario;

	@Column(name="FECHA_HORA", nullable = false)
	private Date fechaHora;

	public DTOConformComisiones() {
		this.id				= new DTOConformComisionesPK();
		this.terminoPeriodo	= new Date();
		this.acuerdoAdjunto = "";
		this.planAdjunto 	= "";
		this.estatus	 	= new Integer(0);
		this.usuario 		= "";
		this.fechaHora		= new Date();
	}

	public DTOConformComisionesPK getId() {
		return id;
	}

	public void setId(DTOConformComisionesPK id) {
		this.id = id;
	}

	public Date getTerminoPeriodo() {
		return terminoPeriodo;
	}

	public void setTerminoPeriodo(Date terminoPeriodo) {
		this.terminoPeriodo = terminoPeriodo;
	}

	public String getAcuerdoAdjunto() {
		return acuerdoAdjunto;
	}

	public void setAcuerdoAdjunto(String acuerdoAdjunto) {
		this.acuerdoAdjunto = acuerdoAdjunto;
	}

	public String getPlanAdjunto() {
		return planAdjunto;
	}

	public void setPlanAdjunto(String planAdjunto) {
		this.planAdjunto = planAdjunto;
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