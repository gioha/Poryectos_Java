/**
 * @(#)DTONotificacionesID.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author Homero Fidel Villanueva
 */
public class DTONotificacionesID implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -440262112773929053L;

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersona;

	/**
	 * Parte de la llave primaria. Es el ID del módulo que tendrá notificaciones
	 */
	@NotNull
	private Integer idModulo;

	// ------------------------ Métodos ------------------------ //

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idModulo == null) ? 0 : idModulo.hashCode());
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
		DTONotificacionesID other = (DTONotificacionesID) obj;
		if (idModulo == null) {
			if (other.idModulo != null)
				return false;
		} else if (!idModulo.equals(other.idModulo))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idModulo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

}
