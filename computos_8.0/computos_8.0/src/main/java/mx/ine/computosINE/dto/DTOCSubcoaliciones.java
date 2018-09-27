package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "COMPUTOSINE.C_SUBCOALICIONES")
@NamedQuery(name = "DTOCSubcoaliciones.findAll", query = "SELECT d FROM DTOCSubcoaliciones d")
public class DTOCSubcoaliciones extends DTOBase implements Serializable {
	
	private static final long serialVersionUID = 8244659388903976222L;

	@Id
	@Column(name = "ID_COALICION", nullable = false)
	private Integer idCoalicion;
	
	@Id
	@Column(name = "ID_SUBCOALICION", nullable = false)
	private Integer idSubcoalicion;

	@Column(name = "EMBLEMA", nullable = false)
	private String emblema;
	
	@Column(name = "ORDEN", nullable = false)
	private Integer orden;
	
	@Column(name = "SIGLAS", nullable = false)
	private String siglas;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;

	//@OneToMany
	//private List<DTOReglasEstatus> reglasEstatus;

	public DTOCSubcoaliciones(){
	}
	
	public DTOCSubcoaliciones(Integer idCoalicion, Integer idSubcoalicion, String emblema, 
			Integer orden, String siglas, String usuario, Date fechaHora) {
		super();
		this.idCoalicion = idCoalicion;
		this.idSubcoalicion = idSubcoalicion;
		this.emblema = emblema;
		this.orden = orden;
		this.siglas = siglas;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
	}

	public Integer getIdCoalicion() {
		return idCoalicion;
	}

	public void setIdCoalicion(Integer idCoalicion) {
		this.idCoalicion = idCoalicion;
	}

	public Integer getIdSubcoalicion() {
		return idSubcoalicion;
	}

	public void setIdSubcoalicion(Integer idSubcoalicion) {
		this.idSubcoalicion = idSubcoalicion;
	}

	public String getEmblema() {
		return emblema;
	}

	public void setEmblema(String emblema) {
		this.emblema = emblema;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
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
