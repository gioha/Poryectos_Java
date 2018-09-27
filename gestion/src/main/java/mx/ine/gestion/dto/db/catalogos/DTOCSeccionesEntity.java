/**
 * @(#)DTOCSeccionesEntity.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

package mx.ine.gestion.dto.db.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la entidad C_SECCIONES que se encuentra en el esquema de
 * gestion4
 *
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
@Entity
@Table(name = "C_SECCIONES", schema = "gestion4")
public class DTOCSeccionesEntity implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = -3660050952589016525L;

	/**
	 * Atributo que guarda el ID de la Sección.
	 */
	@Id
	@Column(name = "ID_SECCION", nullable = false)
	private Integer	idSeccion;

	/**
	 * Atributo que guarda el número de la sección
	 */
	@Column(name = "NUMERO_SECCION", nullable = false)
	private String	numeroSeccion;
	
	/**
	 * Atributo que guarda la descripción de la sección
	 */
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idSeccion
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public Integer getIdSeccion() {
		return idSeccion;
	}

	/**
	 * @param idSeccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroSeccion
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public String getNumeroSeccion() {
		return numeroSeccion;
	}

	/**
	 * @param numeroSeccion : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setNumeroSeccion(String numeroSeccion) {
		this.numeroSeccion = numeroSeccion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable descripcion
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSeccion == null) ? 0 : idSeccion.hashCode());
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
		DTOCSeccionesEntity other = (DTOCSeccionesEntity) obj;
		if (idSeccion == null) {
			if (other.idSeccion != null)
				return false;
		} else if (!idSeccion.equals(other.idSeccion))
			return false;
		return true;
	}	
	
	

}