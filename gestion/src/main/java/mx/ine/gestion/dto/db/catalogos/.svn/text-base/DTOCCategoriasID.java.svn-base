/**
 * @(#)DTOCCategoriasID.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db.catalogos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase que representa la llave compuesta de la entidad C_CATEGORIAS que se encuentra en el esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
public class DTOCCategoriasID implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = -99212823834954512L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */
	
	/**
	 * Atributo que guarda el ID de la Sección es llave foranea a C_SECCIONES.
	 */
	@NotNull
	private Integer	idSeccion;
	
	/**
	 * Atributo que guarda el ID de la Categoría.
	 */
	@NotNull
	private Integer	idCategoria;

	
	
	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */

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
		DTOCCategoriasID other = (DTOCCategoriasID) obj;
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
	
	/* ------------------------------------- Getters y Setters ------------------------------------ */

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
	
}
