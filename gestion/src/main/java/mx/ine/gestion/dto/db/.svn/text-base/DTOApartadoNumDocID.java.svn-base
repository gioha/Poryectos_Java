/**
 * @(#)DTOApartadoNumDocID.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase que contiene la LLAVE COMPUESTA de la tabla de APARTADO_NUM_DOC del esquema
 * de gestion4 la cual tiene la función de apartar folios de los documentos.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
public class DTOApartadoNumDocID implements Serializable {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = 7598288419510024725L;

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
	 * Identificador del tipo de documento del acronimo 
	 * que se utiliza en el número de documento que se aparta
	 */
	@NotNull
	private Integer idTipoDocumento;

	/**
	 * Atributo que contiene la llave privada del acronimo que esta relacionado al documento
	 */
	@NotNull
	private Integer idAcronimo;

	/**
	 * Secuencia logica que contiene el número del documento que se esta apartando
	 */
	@NotNull
	private Integer idNumeroDocumento;

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
		result = prime
				* result
				+ ((idNumeroDocumento == null) ? 0 : idNumeroDocumento
						.hashCode());
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
		DTOApartadoNumDocID other = (DTOApartadoNumDocID) obj;
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
		if (idNumeroDocumento == null) {
			if (other.idNumeroDocumento != null)
				return false;
		} else if (!idNumeroDocumento.equals(other.idNumeroDocumento))
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
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idNumeroDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdNumeroDocumento() {
		return idNumeroDocumento;
	}

	/**
	 * @param idNumeroDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdNumeroDocumento(Integer idNumeroDocumento) {
		this.idNumeroDocumento = idNumeroDocumento;
	}
}
