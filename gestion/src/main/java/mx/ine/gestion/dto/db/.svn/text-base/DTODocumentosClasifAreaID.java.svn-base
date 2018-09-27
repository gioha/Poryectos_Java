/**
 * @(#)DTODocumentosClasifAreaID.java 10/12/2017
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
 * tabla de DOCUMENTOS_CLASIF_AREA perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
public class DTODocumentosClasifAreaID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = 7951151806343448661L;

	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;
	
	/**
	 * Parte de la llave primaria, es llave foranea a C_AREAS
	 */
	@NotNull
	private Integer idAreaClasifica;
	
	/**
	 * Parte de la llave primaria, es llave foranea a C_AREAS
	 */
	@NotNull
	private Integer tipoAreaClasifica;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAreaClasifica == null) ? 0 : idAreaClasifica.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime
				* result
				+ ((tipoAreaClasifica == null) ? 0 : tipoAreaClasifica
						.hashCode());
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
		DTODocumentosClasifAreaID other = (DTODocumentosClasifAreaID) obj;
		if (idAreaClasifica == null) {
			if (other.idAreaClasifica != null)
				return false;
		} else if (!idAreaClasifica.equals(other.idAreaClasifica))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (tipoAreaClasifica == null) {
			if (other.tipoAreaClasifica != null)
				return false;
		} else if (!tipoAreaClasifica.equals(other.tipoAreaClasifica))
			return false;
		return true;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaClasifica
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdAreaClasifica() {
		return idAreaClasifica;
	}

	/**
	 * @param idAreaClasifica : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdAreaClasifica(Integer idAreaClasifica) {
		this.idAreaClasifica = idAreaClasifica;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaClasifica
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getTipoAreaClasifica() {
		return tipoAreaClasifica;
	}

	/**
	 * @param tipoAreaClasifica : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setTipoAreaClasifica(Integer tipoAreaClasifica) {
		this.tipoAreaClasifica = tipoAreaClasifica;
	}

}
