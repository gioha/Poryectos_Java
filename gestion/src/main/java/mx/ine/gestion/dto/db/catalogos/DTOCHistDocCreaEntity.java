/**
 * @(#)DTOCAreas.java 29/08/2017
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
 * Clase que representa la entidad C_ESTATUS_HIS_DOC_CREA que se encuentra en el esquema de
 * gestion4
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */
@Entity
@Table(name = "C_ESTATUS_HIST_DOC_CREA", schema = "gestion4")
public class DTOCHistDocCreaEntity implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 3173690688636882587L;

	/**
	 * Atributo que guarda el ID del Estatus.
	 */
	@Id
	@Column(name = "ID_ESTATUS", nullable = false)
	private Integer	idEstatus;

	/**
	 * Atributo que guarda el la descripción del estatus
	 */
	@Column(name = "DESCRIPCION", nullable = false)
	private String	descripcion;

	/**
	 * @return : variable de tipo Integer contenida en idEstatus
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return : variable de tipo String contenida en descripcion
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
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
				+ ((idEstatus == null) ? 0 : idEstatus.hashCode());
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
		DTOCHistDocCreaEntity other = (DTOCHistDocCreaEntity) obj;
		if (idEstatus == null) {
			if (other.idEstatus != null)
				return false;
		} else if (!idEstatus.equals(other.idEstatus))
			return false;
		return true;
	}
	
	
	
}