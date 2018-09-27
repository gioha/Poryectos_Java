/**
 * @(#)DTOEnviadosDocumentosID.java 29/08/2017
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
 * tabla de ENVIADOS_DOCUMENTOS perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 12/09/2017
 */
public class DTOEnviadosDocumentosID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = 4884734046845074190L;

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersona;
	
	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;

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
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOEnviadosDocumentosID other = (DTOEnviadosDocumentosID) obj;
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

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
}
