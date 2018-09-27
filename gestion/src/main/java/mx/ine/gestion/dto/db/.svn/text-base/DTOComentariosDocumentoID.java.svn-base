/**
 * @(#)DTOComentariosDocumentoID.java 29/08/2017
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
 * tabla de COMENTARIOS_DOCUMENTO perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 12/09/2017
 */
public class DTOComentariosDocumentoID implements Serializable{

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -5943343017662490518L;
	
	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;
	
	/**
	 * Parte de la llave primaria
	 */
	@NotNull
	private Integer idComentario;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idComentario == null) ? 0 : idComentario.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
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
		DTOComentariosDocumentoID other = (DTOComentariosDocumentoID) obj;
		if (idComentario == null) {
			if (other.idComentario != null)
				return false;
		} else if (!idComentario.equals(other.idComentario))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		return true;
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

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idComentario
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

}
