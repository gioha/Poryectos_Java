package mx.ine.gestion.dto.db;

import java.io.Serializable;

/**
 * @(#)DTODocumentoDestinatarioID.java 13/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase ID para la Clase DTODocumentoDestinatarioEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 13/09/2017
 */
public class DTODocumentoDestinatarioID implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 681362482801747526L;
	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	private Integer				idDocumento;

	/**
	 * Atributo que guarda el ID del ccp, parte de la PK y FK para la tabla
	 * ESTRUCTURA_AREAS.
	 */
	private Integer	idPersonaDestinataria;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result + ((idPersonaDestinataria == null) ? 0 : idPersonaDestinataria.hashCode());
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
		DTODocumentoDestinatarioID other = (DTODocumentoDestinatarioID) obj;
		if(idDocumento == null){
			if(other.idDocumento != null)
				return false;
		} else if(!idDocumento.equals(other.idDocumento))
			return false;
		if(idPersonaDestinataria == null){
			if(other.idPersonaDestinataria != null)
				return false;
		} else if(!idPersonaDestinataria.equals(other.idPersonaDestinataria))
			return false;
		return true;
	}
	
	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoDestinatarioID
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaDestinataria
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public Integer getIdPersonaDestinataria() {
		return idPersonaDestinataria;
	}

	/**
	 * @param idPersonaDestinataria : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setIdPersonaDestinataria(Integer idPersonaDestinataria) {
		this.idPersonaDestinataria = idPersonaDestinataria;
	}

	
}
