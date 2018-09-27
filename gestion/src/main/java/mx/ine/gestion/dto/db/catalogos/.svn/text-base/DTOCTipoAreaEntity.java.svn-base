/**
 * @(#)DTOCTipoArea.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la entidad C_TIPO_AREAS que se encuentra en el esquema de GESTION4
 *
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */
@Entity
@Table(name = "C_TIPO_AREAS", schema = "GESTION4")
public class DTOCTipoAreaEntity implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 71748118830904958L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */
	
	/**
	 * Atributo que contiene la llave principal de la tabla/catálogo,
	 * es una secuencia no generada (no existe la secuenta, se hace por carga)
	 */
	@Id
	@Column(name = "ID_TIPO_AREA")
	private Integer idTipoArea;

	/**
	 * Atributo que contiene la descripción del tipo de área que esta registrada
	 * por ejemplo: Organos centrales
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion;

	/* ------------------------------------------- Sobreescritura ---------------------------------------- */

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoArea == null) ? 0 : idTipoArea.hashCode());
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
		DTOCTipoAreaEntity other = (DTOCTipoAreaEntity) obj;
		if (idTipoArea == null) {
			if (other.idTipoArea != null)
				return false;
		} else if (!idTipoArea.equals(other.idTipoArea))
			return false;
		return true;
	}
	
	/* ------------------------------------------ Getters & Setters --------------------------------------- */

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdTipoArea() {
		return idTipoArea;
	}

	/**
	 * @param idTipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdTipoArea(Integer idTipoArea) {
		this.idTipoArea = idTipoArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable descripcion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
