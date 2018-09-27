/**
 * @(#)DTODatosFirmaDocID.java 04/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase en la cual se mapea la llave primaria compuesta de la
 * tabla de DATOS_FIRMA_DOC perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 04/10/2017
 */
public class DTODatosFirmaDocID implements Serializable{

	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = 1525237891416286387L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */	

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;
		
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersonaFirma;

	/* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersonaFirma == null) ? 0 : idPersonaFirma.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
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
		DTODatosFirmaDocID other = (DTODatosFirmaDocID) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersonaFirma == null) {
			if (other.idPersonaFirma != null)
				return false;
		} else if (!idPersonaFirma.equals(other.idPersonaFirma))
			return false;
		return true;
	}

	/* ------------------------------------- Getters/Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public Integer getIdPersonaFirma() {
		return idPersonaFirma;
	}

	/**
	 * @param idPersonaFirma : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setIdPersonaFirma(Integer idPersonaFirma) {
		this.idPersonaFirma = idPersonaFirma;
	}

}
