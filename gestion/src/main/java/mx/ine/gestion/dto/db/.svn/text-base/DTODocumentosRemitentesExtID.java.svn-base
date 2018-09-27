/**
 * @(#)DTODocumentDTODocumentosRemitentesExtIDosRemitentesID.java 02/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase encargada de proporcionar el mapeo de la llave primaria compuesta
 * de la tabla de DOCUMENTOS_REMITENTES_EXT perteneciente al esquema de GESTION4
 *
 * @author José Miguel Ortiz
 * @since 02/04/2018
 */
public class DTODocumentosRemitentesExtID implements Serializable {
	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -8764064806966281460L;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@NotNull
	private Integer idRemitente;
	
	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@NotNull
	private Integer idArea;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@NotNull
	private Integer tipoArea;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;

	/* ------------------------------ Getters & Setters ------------------------------ */
	public Integer getIdRemitente() {
		return idRemitente;
	}

	public void setIdRemitente(Integer idRemitente) {
		this.idRemitente = idRemitente;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/* ------------------------------ Sobreescritura de métodos ------------------------------ */
	/** (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRemitente == null) ? 0 : idRemitente.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result + ((tipoArea == null) ? 0 : tipoArea.hashCode());
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		return result;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;

		DTODocumentosRemitentesExtID other = (DTODocumentosRemitentesExtID) obj;

		if(idRemitente == null) {
			if(other.idRemitente != null)
				return false;
		} else if(!idRemitente.equals(other.idRemitente))
			return false;

		if(idArea == null) {
			if(other.idArea != null)
				return false;
		} else if(!idArea.equals(other.idArea))
			return false;

		if(tipoArea == null) {
			if(other.tipoArea != null)
				return false;
		} else if(!tipoArea.equals(other.tipoArea))
			return false;

		if(idDocumento == null) {
			if(other.idDocumento != null)
				return false;
		} else if(!idDocumento.equals(other.idDocumento))
			return false;

		return true;
	}

}
