/**
 * @(#)DTODocumentosRemitentesEntity.java 21/11/2017
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
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de DOCUMENTOS_REMITENTES perteneciente al
 * esquema de GESTION4
 *
 * @author David Rodríguez Corral
 * @since 21/11/2017
 */
@Entity
@Table(name = "DOCUMENTOS_REMITENTES", schema = "gestion4")
@IdClass(value = DTODocumentosRemitentesID.class)
public class DTODocumentosRemitentesEntity extends DTOBase {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 8981291926879364808L;

	/*------------------Atributos/Campos------------------ */

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
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name = "ID_PERSONA_REMITENTE")
	private Integer idPersonaRemitente;

	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_REMITENTE", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructuraAreas;

	/**
	 * Indica el año de la firma.
	 *
	 */
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Cuenta del usuario que registra.
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Fecha y hora en que se genera el registro.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;	

	/*----------------- Métodos/Sobrescritura----------------*/

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime
				* result
				+ ((idPersonaRemitente == null) ? 0 : idPersonaRemitente
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		DTODocumentosRemitentesEntity other = (DTODocumentosRemitentesEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersonaRemitente == null) {
			if (other.idPersonaRemitente != null)
				return false;
		} else if (!idPersonaRemitente.equals(other.idPersonaRemitente))
			return false;
		return true;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentosRemitentesEntity [idDocumento=" + idDocumento
				+ ", dtoDocumento=" + dtoDocumento + ", idPersonaRemitente="
				+ idPersonaRemitente + ", dtoEstructuraAreas="
				+ dtoEstructuraAreas + ", anio=" + anio + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	/*--------------------- Getters/Setters---------------------*/

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la
	 *         variable dtoDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento
	 *            : valor que se ingresa a la variable de tipo
	 *            DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idPersonaRemitente
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public Integer getIdPersonaRemitente() {
		return idPersonaRemitente;
	}

	/**
	 * @param idPersonaRemitente
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setIdPersonaRemitente(Integer idPersonaRemitente) {
		this.idPersonaRemitente = idPersonaRemitente;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable dtoEstructuraAreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	/**
	 * @param dtoEstructuraAreas
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}
