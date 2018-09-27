/**
 * @(#)DTOPlantillaID.java 15/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase que contiene la llave primaria de tabla PLANTILLAS que esta en el esquema de GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 15/01/2018
 */
public class DTOPlantillaID implements Serializable {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = -1869445319315812103L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Atributo que contiene la llave primaria de la tabla
	 */
	@NotNull
	private Integer	idPlantilla;

	/**
	 * Area del usuario al que pertenece la plantilla
	 */
	@NotNull
	private Integer	idArea;

	/**
	 * Tipo de área del usuario al que pertenece la planilla
	 */
	@NotNull
	private Integer	tipoArea;

	/**
	 * Identificador del tipo de documento del usuario al que pertenece la plantilla
	 */
	@NotNull
	private Integer idTipoDocumento;

	/**
	 * Identificador del acronimo que pertenece al dueño de la plantillas
	 */
	@NotNull
	private Integer	idAcronimo;

	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAcronimo == null) ? 0 : idAcronimo.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idPlantilla == null) ? 0 : idPlantilla.hashCode());
		result = prime * result
				+ ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
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
		DTOPlantillaID other = (DTOPlantillaID) obj;
		if (idAcronimo == null) {
			if (other.idAcronimo != null)
				return false;
		} else if (!idAcronimo.equals(other.idAcronimo))
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idPlantilla == null) {
			if (other.idPlantilla != null)
				return false;
		} else if (!idPlantilla.equals(other.idPlantilla))
			return false;
		if (idTipoDocumento == null) {
			if (other.idTipoDocumento != null)
				return false;
		} else if (!idTipoDocumento.equals(other.idTipoDocumento))
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
	 * @return valor de tipo Integer que esta contenido en la variable idPlantilla
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer getIdPlantilla() {
		return idPlantilla;
	}

	/**
	 * @param idPlantilla : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void setIdPlantilla(Integer idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}
	
}
