/**
 * DTOEdicionesDocumento.java 02/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * @author Homero Villanueva Dominguez
 * @since 02/10/2017
 */

@Entity
@Table(name = "EDICIONES_DOCUMENTO", schema = "gestion4")
@IdClass(value = DTOEdicionesDocumentoID.class)
public class DTOEdicionesDocumentoEntity extends DTOBase {

	/**
	 *Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -1393865456608638212L;
	
	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;
	
	/**
	 * Objeto para cargar la información de la tabla de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity dtoDocumento;

	/**
	 * Indica el año de la edición
	 */
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Parte de la llave primaria
	 */
	@Id
	@Column(name = "ID_EDICION")
	private Integer idEdicion;

	/**
	 * Indica el estatus del comentario, 0 NO leido, 1 Leido
	 */
	@Column(name = "ESTATUS")
	private Integer estatus;

	/**
	 * Indica el usuario que realizó la edición
	 */
	@Column(name = "USUARIO_EDICION")
	private String usuarioEdicion;
	
	/**
	 * Atributo que permite guardar la fecha y hora de edición del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA_EDICION")
	private Date fechaHoraEdicion;

	/**
	 * Atributo que permite guardar el nombre del usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	/**
	 * Nombre completo del usuario que realizó la edición
	 */
	@Transient
	private String nombreCompleto;
	
	// ------------------------ HASHCODE & EQUALS------------------------ //

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idEdicion == null) ? 0 : idEdicion.hashCode());
		return result;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOEdicionesDocumentoEntity other = (DTOEdicionesDocumentoEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idEdicion == null) {
			if (other.idEdicion != null)
				return false;
		} else if (!idEdicion.equals(other.idEdicion))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOEdicionesDocumentoEntity [idDocumento=" + idDocumento
				+ ", dtoDocumento=" + dtoDocumento + ", anio=" + anio
				+ ", idEdicion=" + idEdicion + ", estatus=" + estatus
				+ ", usuarioEdicion=" + usuarioEdicion + ", fechaHoraEdicion="
				+ fechaHoraEdicion + ", usuario=" + usuario + ", fechaHora="
				+ fechaHora + ", nombreCompleto=" + nombreCompleto + "]";
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEdicion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getIdEdicion() {
		return idEdicion;
	}

	/**
	 * @param idEdicion
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setIdEdicion(Integer idEdicion) {
		this.idEdicion = idEdicion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento
	 *            : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHoraEdicion
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public Date getFechaHoraEdicion() {
		return fechaHoraEdicion;
	}

	/**
	 * @param fechaHoraEdicion
	 *            : valor que se ingresa a la variable de tipo Date
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void setFechaHoraEdicion(Date fechaHoraEdicion) {
		this.fechaHoraEdicion = fechaHoraEdicion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;

	}

	/**
	 * @return valor de tipo String que esta contenido en la variable usuarioEdicion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/11/2017
	 */
	public String getUsuarioEdicion() {
		return usuarioEdicion;
	}

	/**
	 * @param usuarioEdicion : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/11/2017
	 */
	public void setUsuarioEdicion(String usuarioEdicion) {
		this.usuarioEdicion = usuarioEdicion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreCompleto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}
