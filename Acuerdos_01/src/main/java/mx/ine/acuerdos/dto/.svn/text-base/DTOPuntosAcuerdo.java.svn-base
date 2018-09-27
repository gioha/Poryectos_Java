package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.Puntos_Acuerdo")
@NamedQuery(name = "DTOPuntosAcuerdo.findAll", query = "SELECT d FROM DTOPuntosAcuerdo d")
public class DTOPuntosAcuerdo extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7070104153104470761L;

	@EmbeddedId
	private DTOPuntosAcuerdoPK id;
	
	@Column(name = "ID_CLASIFICACION", nullable = false)
	private Integer idClasificacion;
	
	@Column(name = "TEXTO_PUNTO", nullable = true)
	private String textoPunto;
	
	@Column(name = "ACCION", nullable = true)
	private String accion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_NOTIFICACION", nullable = true)
	private Date fechaNotificacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_OFICIO", nullable = true)
	private Date fechaOficio;	

	@Column(name = "NUM_OFICIO_NOTIF", nullable = true)
	private String numOficioNotif;	

	@Column(name = "URL_ARCHIVO_ADJ", nullable = true)
	private String urlArchivoAdj;
	
	@Column(name = "USUARIO")
	private String usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	@Column(name = "ACTIVO", nullable = false)
	private Integer activo;

	@Column(name = "NUMERALIA_TEXTO", nullable = false)
	private String numeraliaTexto;
	
	@Column(name = "NOTIFICACION", nullable = false)
	private Integer notificacion;
	
	@Transient
	private String estatusGlobal;
	
	@Transient
	private String semaforo;
	

	public DTOPuntosAcuerdo() {
		this.accion = "";
		this.fechaNotificacion = null;
		this.fechaOficio = null;
		this.idClasificacion = new Integer(0);
		// idNumAcuerdo = new Integer(0);
		this.numOficioNotif = "";
		this.textoPunto = "";
		this.urlArchivoAdj = "";
		this.activo= new Integer(0);
		this.notificacion = new Integer(0);
		this.estatusGlobal = "";
		this.semaforo = "";
		setUsuario("");
		setFechaHora(new Date());
	}

	public DTOPuntosAcuerdoPK getId() {
		return id;
	}

	public void setId(DTOPuntosAcuerdoPK id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public Date getFechaOficio() {
		return fechaOficio;
	}

	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}

	public Integer getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(Integer idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getNumOficioNotif() {
		return numOficioNotif;
	}

	public void setNumOficioNotif(String numOficioNotif) {
		this.numOficioNotif = numOficioNotif;
	}

	public String getTextoPunto() {
		return textoPunto;
	}

	public void setTextoPunto(String textoPunto) {
		this.textoPunto = textoPunto;
	}

	public String getUrlArchivoAdj() {
		return urlArchivoAdj;
	}

	public void setUrlArchivoAdj(String urlArchivoAdj) {
		this.urlArchivoAdj = urlArchivoAdj;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getNumeraliaTexto() {
		return numeraliaTexto;
	}

	public void setNumeraliaTexto(String numeraliaTexto) {
		this.numeraliaTexto = numeraliaTexto;
	}

	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	@Override
	public String getUsuario() {
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

	public Integer getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Integer notificacion) {
		this.notificacion = notificacion;
	}

	public String getEstatusGlobal() {
		return estatusGlobal;
	}

	public void setEstatusGlobal(String estatusGlobal) {
		this.estatusGlobal = estatusGlobal;
	}

	public String getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(String semarofo) {
		this.semaforo = semarofo;
	}

}
