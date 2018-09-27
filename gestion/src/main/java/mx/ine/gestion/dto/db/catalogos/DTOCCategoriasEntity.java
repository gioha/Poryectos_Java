/**
 * @(#)DTOCCategoriasEntity.java 29/08/2017
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
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Clase que representa la entidad C_CATEGORIAS que se encuentra en el esquema de
 * gestion4
 *
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
@Entity
@Table(name = "C_CATEGORIAS", schema = "gestion4")
@IdClass(value = DTOCCategoriasID.class)
public class DTOCCategoriasEntity implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 8353735608609271516L;

	/**
	 * Atributo que guarda el ID de la Sección es llave foranea a C_SECCIONES.
	 */
	@Id
	@Column(name = "ID_SECCION", nullable = false)
	private Integer	idSeccion;
	
	/**
	 * Atributo que guarda el ID de la Categoría.
	 */
	@Id
	@Column(name = "ID_CATEGORIA", nullable = false)
	private Integer	idCategoria;

	/**
	 * Atributo que guarda el número de la categoría
	 */
	@Column(name = "NUMERO_CATEGORIA", nullable = false)
	private String	numeroCategoria;
	
	/**
	 * Atributo que guarda la descripción de la categoría
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
	 * @return valor de tipo Integer que esta contenido en la variable idCategoria
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroCategoria
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public String getNumeroCategoria() {
		return numeroCategoria;
	}

	/**
	 * @param numeroCategoria : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setNumeroCategoria(String numeroCategoria) {
		this.numeroCategoria = numeroCategoria;
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
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		DTOCCategoriasEntity other = (DTOCCategoriasEntity) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (idSeccion == null) {
			if (other.idSeccion != null)
				return false;
		} else if (!idSeccion.equals(other.idSeccion))
			return false;
		return true;
	}

	
}