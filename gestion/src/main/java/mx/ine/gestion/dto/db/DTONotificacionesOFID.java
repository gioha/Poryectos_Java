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
 * Clase que guarda la llave compuesta de la tabla de NOTIFICACIONES_OF del esquema de GESTION4,
 * la cual tiene como función generar las nótificaciones para el menú de la bandeja de
 * oficialia
 * 
 * @author Roberto Shirásago Domínguez
 * @since 18/12/2017
 */
public class DTONotificacionesOFID implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 2374614027457902456L;

	/**
	 * Atributo que contiene el identificador del área a la que pertenece el
	 * acronimo
	 */
	@NotNull
	private Integer idArea;

	/**
	 * Atributo que contiene el identificador del tipo área a la que pertenece
	 * el acronimo (puede ser organo centra y organo desconcentrado)
	 */
	@NotNull
	private Integer tipoArea;

	/**
	 * Parte de la llave primaria el cual identifica al módulo
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
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idModulo == null) ? 0 : idModulo.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
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
		DTONotificacionesOFID other = (DTONotificacionesOFID) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idModulo == null) {
			if (other.idModulo != null)
				return false;
		} else if (!idModulo.equals(other.idModulo))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}
	
	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idModulo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}
}
