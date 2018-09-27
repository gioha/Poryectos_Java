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
@Table(name = "COMPUTOSINE.C_DETALLE_SUBCOALICIONES")
@NamedQuery(name = "DTOCDetalleSubcoaliciones.findAll", query = "SELECT d FROM DTOCDetalleSubcoaliciones d")
public class DTOCDetalleSubcoaliciones extends DTOBase implements Serializable {
	

	private static final long serialVersionUID = -7791026623206131919L;

	@Id
	@Column(name = "ID_COALICION", nullable = false)
	private Integer idCoalicion;
	
	@Id
	@Column(name = "ID_SUBCOALICION", nullable = false)
	private Integer idSubcoalicion;
	
	@Id
	@Column(name = "ID_ASOCIACION", nullable = false)
	private Integer idAsociacion;

	@Column(name = "EMBLEMA", nullable = false)
	private String emblema;
	
	@Column(name = "ORDEN", nullable = false)
	private Integer orden;

	@Column(name = "USUARIO", nullable = false)
	private String usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", nullable = false)
	private Date fechaHora;

	//@OneToMany
	//private List<DTOReglasEstatus> reglasEstatus;

	public DTOCDetalleSubcoaliciones(){
	}
	
	public DTOCDetalleSubcoaliciones(Integer idCoalicion, Integer idSubcoalicion, Integer idAsociacion, String emblema, 
			Integer orden, String usuario, Date fechaHora) {
		super();
		this.idCoalicion = idCoalicion;
		this.idSubcoalicion = idSubcoalicion;
		this.idAsociacion = idAsociacion;
		this.emblema = emblema;
		this.orden = orden;
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

	public Integer getIdAsociacion() {
		return idAsociacion;
	}

	public void setIdAsociacion(Integer idAsociacion) {
		this.idAsociacion = idAsociacion;
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
