/**
 * @(#)DTOCEstatusHistDocTurno.java 08/02/2018
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
 * @author Homero Fidel Villanueva
 *
 */
@Entity
@Table(name = "C_ESTATUS_HIST_DOC_TURNO", schema = "GESTION4")
public class DTOCEstatusHistDocTurno implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = -3873611927611222157L;

	/**
	 * Atributo que guarda el ID del Estatus.
	 */
	@Id
	@Column(name = "ID_ESTATUS_TURNO", nullable = false)
	private Integer idEstatusTurno;

	/**
	 * Atributo que guarda el la descripción del estatus
	 */
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEstatusTurno == null) ? 0 : idEstatusTurno.hashCode());
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
		DTOCEstatusHistDocTurno other = (DTOCEstatusHistDocTurno) obj;
		if (idEstatusTurno == null) {
			if (other.idEstatusTurno != null)
				return false;
		} else if (!idEstatusTurno.equals(other.idEstatusTurno))
			return false;
		return true;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEstatusTurno
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public Integer getIdEstatusTurno() {
		return idEstatusTurno;
	}

	/**
	 * @param idEstatusTurno : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public void setIdEstatusTurno(Integer idEstatusTurno) {
		this.idEstatusTurno = idEstatusTurno;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable descripcion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
