/**
 * @(#)DTOOficialiasAreasID.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase que guarda la llave primaria compuesta de la tabla de OFICIALIAS_AREAS en el esquema de GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
public class DTOOficialiasAreasID implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -2866450590501333179L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Parte de la llave primaria de la tabla, es el identificador de la persona registrada como oficialia de partes.
	 */
	@NotNull
	private Integer idOficialia;

	/**
	 * Parte de la llave primaria que guarda el área al que pertenece la persona de la oficialia
	 */
	@NotNull
	private Integer idArea;
	
	/**
	 * Parte de la llave primaria que guarda el tipo de área al que pertenece la persona de la oficialia
	 */
	@NotNull
	private Integer tipoArea;

	/* --------------------------------- Implementación de Métodos ------------------------------- */

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idOficialia == null) ? 0 : idOficialia.hashCode());
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
		DTOOficialiasAreasID other = (DTOOficialiasAreasID) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idOficialia == null) {
			if (other.idOficialia != null)
				return false;
		} else if (!idOficialia.equals(other.idOficialia))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	/* ------------------------------------- Getters/Setters ------------------------------------ */

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}
}
