/**
 * @(#)DTOBandejaEntradasOficialiaID.java 12/11/2017
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
 * tabla de BANDEJA_ENTRADAS_OFICIALIA perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 12/11/2017
 */
public class DTOBandejaEntradasOficialiaID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -5393402549246636676L;

	/**
	 * Parte de la llave primaria, es llave foranea a OFICIALIAS
	 */
	@NotNull
	private Integer idOficialia;
	
	/**
	 * Parte de la llave primaria, es llave forane a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;
	
	/**
	 * Parte de la llave primaria, es llave forane a CAREAS
	 */
	@NotNull
	private Integer idAreaDestinatario;
	
	/**
	 * Parte de la llave primaria, es llave forane a CAREAS
	 */
	@NotNull
	private Integer tipoAreaDestinatario;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idAreaDestinatario == null) ? 0 : idAreaDestinatario
						.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idOficialia == null) ? 0 : idOficialia.hashCode());
		result = prime
				* result
				+ ((tipoAreaDestinatario == null) ? 0 : tipoAreaDestinatario
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
		DTOBandejaEntradasOficialiaID other = (DTOBandejaEntradasOficialiaID) obj;
		if (idAreaDestinatario == null) {
			if (other.idAreaDestinatario != null)
				return false;
		} else if (!idAreaDestinatario.equals(other.idAreaDestinatario))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idOficialia == null) {
			if (other.idOficialia != null)
				return false;
		} else if (!idOficialia.equals(other.idOficialia))
			return false;
		if (tipoAreaDestinatario == null) {
			if (other.tipoAreaDestinatario != null)
				return false;
		} else if (!tipoAreaDestinatario.equals(other.tipoAreaDestinatario))
			return false;
		return true;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaDestinatario
	 * 
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public Integer getIdAreaDestinatario() {
		return idAreaDestinatario;
	}

	/**
	 * @param idAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public void setIdAreaDestinatario(Integer idAreaDestinatario) {
		this.idAreaDestinatario = idAreaDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaDestinatario
	 * 
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public Integer getTipoAreaDestinatario() {
		return tipoAreaDestinatario;
	}

	/**
	 * @param tipoAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public void setTipoAreaDestinatario(Integer tipoAreaDestinatario) {
		this.tipoAreaDestinatario = tipoAreaDestinatario;
	}
	
}
