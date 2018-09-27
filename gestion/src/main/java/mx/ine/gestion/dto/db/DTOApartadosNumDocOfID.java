/**
 * @(#)DTOApartadosNumDocOfID.java 09/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase que contiene la LLAVE COMPUESTA de la tabla de APARTADOS_NUM_DOC_OF del esquema
 * de gestion4 la cual tiene la función de apartar folios de los documentos al ser clasificados
 * en el módulo de oficialia.
 * 
 * @author David Rodríguez Corral
 * @since 09/12/2017
 */
public class DTOApartadosNumDocOfID implements Serializable {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = 1970593735125415636L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Atributo que indica el id_area del número de documento que se aparta
	 */
	@NotNull
	private Integer idArea;

	/**
	 * Atributo que indica el tipo_area del número de documento que se aparta
	 */
	@NotNull
	private Integer tipoArea;
	
	/**
	 * Identificador del id de oficialia
	 */
	@NotNull
	private Integer idFolioOf;

	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idFolioOf == null) ? 0 : idFolioOf.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
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
		DTOApartadosNumDocOfID other = (DTOApartadosNumDocOfID) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idFolioOf == null) {
			if (other.idFolioOf != null)
				return false;
		} else if (!idFolioOf.equals(other.idFolioOf))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idFolioOf
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getIdFolioOf() {
		return idFolioOf;
	}

	/**
	 * @param idFolioOf : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setIdFolioOf(Integer idFolioOf) {
		this.idFolioOf = idFolioOf;
	}

}
