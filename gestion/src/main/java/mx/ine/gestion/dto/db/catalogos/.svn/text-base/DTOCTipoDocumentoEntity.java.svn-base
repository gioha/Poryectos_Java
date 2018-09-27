package mx.ine.gestion.dto.db.catalogos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @(#)DTOCTiposDocumentos.java 05/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase DTO para el catálogo de tipo de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 05/09/2017
 */
@Entity
@Table(name = "C_TIPO_DOCUMENTOS", schema = "gestion4")
public class DTOCTipoDocumentoEntity implements Serializable {

	private static final long	serialVersionUID	= 8161943935757461721L;
	/**
	 * Atributo que guarda el ID del tipo de documento
	 */
	@Id
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Integer	idTipoDocumento;

	/**
	 * Atributo que guarda la descripción del tipo de documento
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion;

	/**
	 * @return idTipoDocumento
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public Integer getIdTipoDocumento() {

		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento
	 *            id del tipo de documento
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {

		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return descripcion
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public String getDescripcion() {

		return descripcion;
	}

	/**
	 * @param descripcion
	 *            descripción del tipo de documento
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
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
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
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
		DTOCTipoDocumentoEntity other = (DTOCTipoDocumentoEntity) obj;
		if(idTipoDocumento == null){
			if(other.idTipoDocumento != null)
				return false;
		} else if(!idTipoDocumento.equals(other.idTipoDocumento))
			return false;
		return true;
	}
}
