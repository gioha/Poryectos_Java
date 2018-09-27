package mx.ine.gestion.dto.db;

import java.io.Serializable;

/**
 * @(#)DTODocumentoAnexoID.java 13/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase ID para la Clase DTODocumentoAnexoEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 13/09/2017
 */
public class DTODocumentoAnexoID implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3066416624859264280L;

	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	private Integer				idDocumento;

	/**
	 * Atributo que guarda el ID del documento anexo.
	 */
	private Integer				idAnexo;

	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoAnexoID
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return idAnexo
	 */
	public Integer getIdAnexo() {

		return idAnexo;
	}

	/**
	 * @param idAnexo
	 *            idAnexo del DTODocumentoAnexoID
	 */
	public void setIdAnexo(Integer idAnexo) {

		this.idAnexo = idAnexo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAnexo == null) ? 0 : idAnexo.hashCode());
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		DTODocumentoAnexoID other = (DTODocumentoAnexoID) obj;
		if(idAnexo == null){
			if(other.idAnexo != null)
				return false;
		} else if(!idAnexo.equals(other.idAnexo))
			return false;
		if(idDocumento == null){
			if(other.idDocumento != null)
				return false;
		} else if(!idDocumento.equals(other.idDocumento))
			return false;
		return true;
	}
}
