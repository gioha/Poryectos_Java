/**
 * DTOHistDocCreacionID.java 18/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author Homero Villanueva Dominguez 18/09/2017
 */
public class DTOHistDocCreacionID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4483557175943730002L;

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idHistorico;

	// ------------------------ Métodos ------------------------ //

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idHistorico == null) ? 0 : idHistorico.hashCode());
		return result;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
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
		DTOHistDocCreacionID other = (DTOHistDocCreacionID) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idHistorico == null) {
			if (other.idHistorico != null)
				return false;
		} else if (!idHistorico.equals(other.idHistorico))
			return false;
		return true;
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idHistorico
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public Integer getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

}
