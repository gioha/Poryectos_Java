/**
 * @(#)DTORemitentesExternosOfID.java 19/01/2018
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
 * tabla de REMITENTES_EXTERNOS_OF perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 19/01/2018
 */
public class DTORemitentesExternosOfID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -1100423700313096155L;
	
	/**
	 * Parte de la llave primaria
	 */
	@NotNull
	private Integer idRemitente;
	
	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer idArea;
	
	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer tipoArea;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idRemitente == null) ? 0 : idRemitente.hashCode());
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
		DTORemitentesExternosOfID other = (DTORemitentesExternosOfID) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idRemitente == null) {
			if (other.idRemitente != null)
				return false;
		} else if (!idRemitente.equals(other.idRemitente))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idRemitente
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getIdRemitente() {
		return idRemitente;
	}

	/**
	 * @param idRemitente: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setIdRemitente(Integer idRemitente) {
		this.idRemitente = idRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}
	
}
