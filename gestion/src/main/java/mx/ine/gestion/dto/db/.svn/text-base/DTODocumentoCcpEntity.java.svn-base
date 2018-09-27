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
 * @(#)DTODocumentoCcpEntity.java 12/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de MB para el módulo captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 12/09/2017
 */
@Entity
@Table(name = "DOCUMENTOS_CCP", schema = "gestion4")
@IdClass(DTODocumentoCCPID.class)
public class DTODocumentoCcpEntity extends DTOBase {

	private static final long			serialVersionUID	= -9174727511347525146L;
	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer						idDocumento;

	/**
	 * Atributo que guarda el ID del ccp.
	 */
	@Id
	@Column(name = "ID_CCP")
	private Integer						idCcp;

	/**
	 * Atributo que guarda el ID de la persona ccp, parte de la PK y FK para la
	 * tabla ESTRUCTURA_AREAS.
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer						idPersona;

	/**
	 * Atributo que guarda el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity			documento;

	/**
	 * Atributo que guarda la persona ccp.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity	persona;
	
	/**
	 * Atributo que guarda el año del documento.
	 */
	@Column(name = "ANIO")
	private Integer						anio;
	
	/**
	 * Atributo que guarda el usuario que creo el documento.
	 */
	@Column(name = "USUARIO")
	private String						usuario;
	
	/**
	 * Atributo que guarda la fecha de creacion del documento.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date						fechaHora;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCcp == null) ? 0 : idCcp.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTODocumentoCcpEntity other = (DTODocumentoCcpEntity) obj;
		if (idCcp == null) {
			if (other.idCcp != null)
				return false;
		} else if (!idCcp.equals(other.idCcp))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentoCcpEntity [idDocumento=" + idDocumento + ", idCcp="
				+ idCcp + ", idPersona=" + idPersona + ", documento="
				+ documento + ", persona=" + persona + ", anio=" + anio
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	/**
	 * @return idPersona
	 */
	public Integer getIdPersona() {

		return idPersona;
	}

	/**
	 * @param idPersona
	 *            idPersona del DTODocumentoCcpEntity
	 */
	public void setIdPersona(Integer idPersona) {

		this.idPersona = idPersona;
	}

	/**
	 * @return documento
	 */
	public DTODocumentoEntity getDocumento() {

		return documento;
	}

	/**
	 * @param documento
	 *            documento del DTODocumentoCcpEntity
	 */
	public void setDocumento(DTODocumentoEntity documento) {

		this.documento = documento;
	}

	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoCcpEntity
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return idCcp
	 */
	public Integer getIdCcp() {

		return idCcp;
	}

	/**
	 * @param idCcp
	 *            idCcp del DTODocumentoCcpEntity
	 */
	public void setIdCcp(Integer idCcp) {

		this.idCcp = idCcp;
	}

	/**
	 * @return persona
	 */
	public DTOEstructuraAreasEntity getPersona() {

		return persona;
	}

	/**
	 * @param persona
	 *            persona del DTODocumentoCcpEntity
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {

		this.persona = persona;
	}

	/**
	 * @return anio
	 */
	public Integer getAnio() {

		return anio;
	}

	/**
	 * @param anio
	 *            anio del DTODocumentoCcpEntity
	 */
	public void setAnio(Integer anio) {

		this.anio = anio;
	}

	/**
	 * @return usuario
	 */
	@Override
	public String getUsuario() {

		return usuario;
	}

	/**
	 * @param usuario
	 *            usuario del DTODocumentoCcpEntity
	 */
	@Override
	public void setUsuario(String usuario) {

		this.usuario = usuario;
	}

	/**
	 * @return fechaHora
	 */
	@Override
	public Date getFechaHora() {

		return fechaHora;
	}

	/**
	 * @param fechaHora
	 *            fechaHora del DTODocumentoCcpEntity
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}

	
}
