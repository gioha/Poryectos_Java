package mx.ine.gestion.dto.db;

import java.io.Serializable;

/**
 * @(#)DTODocumentoCCPID.java 13/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase ID para la Clase DTODocumentoCCPEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 13/09/2017
 */
public class DTODocumentoCCPID implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5624982908589874303L;
	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	private Integer				idDocumento;
	/**
	 * Atributo que guarda el ID del destinatario, parte de la PK y FK para la
	 * tabla ESTRUCTURA_AREAS.
	 */
	private Integer				idPersona;
	/**
	 * Atributo que guarda el ID del ccp.
	 */
	private Integer				idCcp;

	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoCCPID
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return idPersona
	 */
	public Integer getIdPersona() {

		return idPersona;
	}

	/**
	 * @param idpersona
	 *            idpersona del DTODocumentoCCPID
	 */
	public void setIdpersona(Integer idpersona) {

		this.idPersona = idpersona;
	}

	/**
	 * @return idCcp
	 */
	public Integer getIdCcp() {

		return idCcp;
	}

	/**
	 * @param idCcp
	 *            idCcp del DTODocumentoCCPID
	 */
	public void setIdCcp(Integer idCcp) {

		this.idCcp = idCcp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCcp == null) ? 0 : idCcp.hashCode());
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		DTODocumentoCCPID other = (DTODocumentoCCPID) obj;
		if(idCcp == null){
			if(other.idCcp != null)
				return false;
		} else if(!idCcp.equals(other.idCcp))
			return false;
		if(idDocumento == null){
			if(other.idDocumento != null)
				return false;
		} else if(!idDocumento.equals(other.idDocumento))
			return false;
		if(idPersona == null){
			if(other.idPersona != null)
				return false;
		} else if(!idPersona.equals(other.idPersona))
			return false;
		return true;
	}
}
