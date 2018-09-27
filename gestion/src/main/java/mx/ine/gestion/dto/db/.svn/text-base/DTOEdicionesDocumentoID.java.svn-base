/**
 * DTOEdicionesDocumentoID.java 02/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

/**
 * @author Homero Villanueva Dominguez
 * @since 02/10/2017
 */
public class DTOEdicionesDocumentoID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8267909065886839452L;

	/**
	 * 
	 */
	private Integer idDocumento;
	/**
	 * 
	 */
	private Integer idEdicion;

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
				+ ((idEdicion == null) ? 0 : idEdicion.hashCode());
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
		DTOEdicionesDocumentoID other = (DTOEdicionesDocumentoID) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idEdicion == null) {
			if (other.idEdicion != null)
				return false;
		} else if (!idEdicion.equals(other.idEdicion))
			return false;
		return true;
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEdicion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public Integer getIdEdicion() {
		return idEdicion;
	}

	/**
	 * @param idEdicion
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/10/2017
	 */
	public void setIdEdicion(Integer idEdicion) {
		this.idEdicion = idEdicion;
	}

}
