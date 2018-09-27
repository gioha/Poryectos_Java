/**
 * @(#)DTODocumentosRemitentesID.java 29/08/2017
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
 * tabla de DOCUMENTOS_REMITENTES perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 21/11/2017
 */
public class DTODocumentosRemitentesID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -3482166504942240542L;
	
	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersonaRemitente;

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
		DTODocumentosRemitentesID other = (DTODocumentosRemitentesID) obj;
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

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaRemitente
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public Integer getIdPersonaRemitente() {
		return idPersonaRemitente;
	}

	/**
	 * @param idPersonaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setIdPersonaRemitente(Integer idPersonaRemitente) {
		this.idPersonaRemitente = idPersonaRemitente;
	}

}
