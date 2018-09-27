/**
 * @(#)DTOValidacionDocumentosID.java 10/10/2017
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
 * tabla de VALIDACION_DOCUMENTOS perteneciente al esquema de gestion4
 *
 * @author Pável Alexei Martínez Regalado
 * @since  10/10/2017
 */
public class DTOValidacionDocumentosID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -1558456246817618414L;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOValidacionDocumentosID other = (DTOValidacionDocumentosID) obj;
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
	 * @author Pável Alexei Martínez Regalado
	 * @since  10/10/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since  10/10/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo idDocumento que esta contenido en la variable Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since  10/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since  10/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
}
