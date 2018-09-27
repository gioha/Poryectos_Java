package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.ACUERDOS")
@NamedQuery(name = "DTOAcuerdos.findAll", query = "SELECT d FROM DTOAcuerdos d")
public class DTOAcuerdos extends DTOBase implements Serializable {
	private static final long serialVersionUID = -8633379508250542239L;

	@Id
	@NotNull
	@Column(name = "ID_NUM_ACUERDO", nullable = false)
	private String idNumAcuerdo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_EMISION", nullable = false)
	private Date fechaSesion;

	@Column(name = "ID_TIPO_SESION", nullable = false)
	private Integer idTipoSesion;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "LIGA_ACUERDO", nullable = true)
	private String ligaAcuerdo;

	@Column(name = "ACUERDO_ADJUNTO", nullable = true)
	private String acuerdoAdjunto;

	@Column(name = "ESTATUS", nullable = false)
	private Integer estatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_NOTIFICACION_ENGROSE", nullable = true)
	private Date fechaEngrose;

	@Column(name = "NUM_OFICIO_NOTIFICACION", nullable = true)
	private String numOficioEngrose;

	@Column(name = "NOTIFICACION_ADJUNTA", nullable = true)
	private String engroseAdjunto;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;

	@Column(name = "ACTIVO", nullable = false)
	private Integer activo;
	
	@Column(name = "ENGROSE", nullable = false)
	private Integer engrose;
	
	@Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
	private Integer idTipoDocumento;
	
	@Column(name = "ID_NEGOCIO", nullable = false)
	private Integer idNegocio;

	/**
	 * Constructor default para la clase DTOAcuerdos
	 * @author José Hurtado
	 * @update Miguel Ortiz
	 * @since 22/02/2018
	 * @param
	 * @return void
	 **/
	public DTOAcuerdos() {
		this.idNumAcuerdo		= "";
		this.fechaSesion		= null;
		this.idTipoSesion		= new Integer(0);
		this.nombre				= "";
		this.ligaAcuerdo		= "";
		this.acuerdoAdjunto		= "";
		this.estatus			= new Integer(0);
		this.fechaEngrose		= null;
		this.numOficioEngrose	= "";
		this.engroseAdjunto		= "";
		this.activo				= new Integer(0);
		this.engrose			= new Integer(0);
		this.idTipoDocumento	= new Integer(0);
		setUsuario("");
		setFechaHora(new Date());
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getIdNumAcuerdo() {
		return idNumAcuerdo;
	}

	public void setIdNumAcuerdo(String idNumAcuerdo) {
		this.idNumAcuerdo = idNumAcuerdo;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public Integer getIdTipoSesion() {
		return idTipoSesion;
	}

	public void setIdTipoSesion(Integer idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLigaAcuerdo() {
		return ligaAcuerdo;
	}

	public void setLigaAcuerdo(String ligaAcuerdo) {
		this.ligaAcuerdo = ligaAcuerdo;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaEngrose() {
		return fechaEngrose;
	}

	public void setFechaEngrose(Date fechaEngrose) {
		this.fechaEngrose = fechaEngrose;
	}

	public String getNumOficioEngrose() {
		return numOficioEngrose;
	}

	public void setNumOficioEngrose(String numOficioEngrose) {
		this.numOficioEngrose = numOficioEngrose;
	}

	public String getEngroseAdjunto() {
		return engroseAdjunto;
	}

	public void setEngroseAdjunto(String engroseAdjunto) {
		this.engroseAdjunto = engroseAdjunto;
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return fechaHora;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAcuerdoAdjunto() {
		return acuerdoAdjunto;
	}

	public void setAcuerdoAdjunto(String acuerdoAdjunto) {
		this.acuerdoAdjunto = acuerdoAdjunto;
	}

	public Integer getEngrose() {
		return engrose;
	}

	public void setEngrose(Integer engrose) {
		this.engrose = engrose;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer tipo) {
		this.idTipoDocumento = tipo;
	}

	public Integer getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Integer idNegocio) {
		this.idNegocio = idNegocio;
	}

}
