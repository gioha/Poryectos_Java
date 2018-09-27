package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import mx.org.ine.servicios.dto.DTOBase;

@Entity
@Table(name = "ACUERDOS.C_CLASIFICACIONES")
@NamedQuery(name = "DTOCClasificaciones.findAll", query = "SELECT d FROM DTOCClasificaciones d")
public class DTOCClasificaciones extends DTOBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3286844613101215132L;

	/**
	 * 
	 */

	@Id
	@NotNull
	@Column(name = "ID_CLASIFICACION", nullable = false)
	private Integer idClasificacion;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ORDEN", nullable = false)
	private Integer orden;
	
	@Column(name = "TIPO_CLASIFICACION", nullable = false)
	private Integer tipoClasificacion;

	@Transient
	boolean tienePuntoED;

	@Transient
	boolean eliminable;
	
	/**
	 * Constructor default de la clase DTOTipoSesiones
	 * 
	 * @author Jair López
	 * @since 12/10/2017
	 * @param
	 * @return Void
	 * **/
	public DTOCClasificaciones() {
		this.idClasificacion 	= new Integer(0);
		this.descripcion 		= "";
		this.orden 				= new Integer(0);
		this.tipoClasificacion  = new Integer(0);
		this.tienePuntoED       = false;
		this.eliminable         = false;
	}

	public Integer getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(Integer idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Override
	public Date getFechaHora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFechaHora(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUsuario(String arg0) {
		// TODO Auto-generated method stub

	}

	public Integer getTipoClasificacion() {
		return tipoClasificacion;
	}

	public void setTipoClasificacion(Integer tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	public boolean isTienePuntoED() {
		return tienePuntoED;
	}

	public void setTienePuntoED(boolean tienePuntoED) {
		this.tienePuntoED = tienePuntoED;
	}

	public boolean isEliminable() {
		return eliminable;
	}

	public void setEliminable(boolean eliminable) {
		this.eliminable = eliminable;
	}

}
