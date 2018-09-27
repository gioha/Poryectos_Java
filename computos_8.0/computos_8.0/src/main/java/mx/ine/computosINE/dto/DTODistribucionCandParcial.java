package mx.ine.computosINE.dto;

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

/**
 * <code>DTODistribucionCandParcial.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 27/04/2017 16:08:00
 */
@Entity
@Table(name = "COMPUTOSINE.DISTRIBUCION_CAND_PARCIAL")
@NamedQuery(name = "DTODistribucionCandParcial.findAll", query = "SELECT d FROM DTODistribucionCandParcial d")
public class DTODistribucionCandParcial extends DTOBase implements Serializable {


	private static final long serialVersionUID = -8545740630970983345L;

	@EmbeddedId
	private DTODistribucionCandParcialPK id;
	
	@Column(name = "TIPO_ASOCIACION", nullable = false)
	private Integer tipoAsociacion;
	
	@Column(name = "ORDEN", nullable = false)
	private Integer orden;
	
	@Column(name = "VOTOS", nullable = false)
	private Integer votos;
	
	@Column(name = "EMBLEMA")
	private String emblema;
	
//	@Column(name = "NOMBRE_ASOCIACION")
	@Transient
	private String nombreAsociacion;
	
	@Column(name="USUARIO", nullable = false)
	private String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA", nullable = false)
	private Date fecha;
	
	
	
	public DTODistribucionCandParcial() {
	}

	public DTODistribucionCandParcial(DTODistribucionCandParcialPK id, Integer tipoAsociacion, Integer orden, Integer votos, String emblema,String usuario,
			Date fecha) {
		super();
		this.id = id;
		this.tipoAsociacion = tipoAsociacion;
		this.orden = orden;
		this.votos = votos;
		this.emblema = emblema;
		this.usuario = usuario;
		this.fecha = fecha;
	}

	public DTODistribucionCandParcialPK getId() {
		return id;
	}

	public void setId(DTODistribucionCandParcialPK id) {
		this.id = id;
	}

	
	public Integer getTipoAsociacion() {
		return tipoAsociacion;
	}

	public void setTipoAsociacion(Integer tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}


	public String getEmblema() {
		return emblema;
	}

	public void setEmblema(String emblema) {
		this.emblema = emblema;
	}

	public String getNombreAsociacion() {
		return nombreAsociacion;
	}

	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
	}

	@Override
	public Date getFechaHora() {
		return fecha;
	}

	@Override
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setFechaHora(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
