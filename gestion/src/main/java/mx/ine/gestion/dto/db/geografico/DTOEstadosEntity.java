/**
 * @(#)DTOEstadosEntity.java 13/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db.geografico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la entidad ESTADOS que se encuentra en el esquema de GESTION4
 *
 * @author Pável Alexei Martínez Regalado
 * @since 13/12/2017
 */
@Entity
@Table(name = "ESTADOS", schema = "GEOGRAFICOINE")
public class DTOEstadosEntity implements Serializable {

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 5234114323922685770L;

	/**
	 * Atributo que guarda el ID del Estado.
	 */
	@Id
	@Column(name = "ID_ESTADO")
	private Integer	idEstado;

	/**
	 * Atributo que contiene el corte del estado.
	 */
	@Column(name = "ID_CORTE")
	private Integer idCorte;

	/**
	 * Atributo que guarda el nombre del estado.
	 */
	@Column(name = "NOMBRE_ESTADO")
	private String nombreEstado;

	/**
	 * Atributo que contiene la abreviatura del estado.
	 */
	@Column(name = "ABREVIATURA")
	private String abreviatura;

	/**
	 * Atributo que contiene el identificador de la circunscripción a la que pertenece el estado.
	 */
	@Column(name = "ID_CIRCUNSCRIPCION")
	private Integer idCircunscripcion;
	
	/**
	 * Atributo que contiene el nombre de la circunscripción a la que pertenece el estado.
	 */
	@Column(name = "CIRCUNSCRIPCION")
	private String circunscripcion;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEstado == null) ? 0 : idEstado.hashCode());
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
		DTOEstadosEntity other = (DTOEstadosEntity) obj;
		if (idEstado == null) {
			if (other.idEstado != null)
				return false;
		} else if (!idEstado.equals(other.idEstado))
			return false;
		return true;
	}

	/**
	 * @return variable de tipo Integer contenida en idEstado
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado: variable de tipo Integer contenida en idEstado
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return variable de tipo Integer contenida en idCorte
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdCorte() {
		return idCorte;
	}

	/**
	 * @param idCorte: variable de tipo Integer contenida en idCorte
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdCorte(Integer idCorte) {
		this.idCorte = idCorte;
	}

	/**
	 * @return variable de tipo String contenida en nombreEstado
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}

	/**
	 * @param nombreEstado: variable de tipo String contenida en nombreEstado
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	/**
	 * @return variable de tipo String contenida en abreviatura
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura: variable de tipo String contenida en abreviatura
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return variable de tipo Integer contenida en idCircunscripcion
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdCircunscripcion() {
		return idCircunscripcion;
	}

	/**
	 * @param idCircunscripcion: variable de tipo Integer contenida en idCircunscripcion
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdCircunscripcion(Integer idCircunscripcion) {
		this.idCircunscripcion = idCircunscripcion;
	}

	/**
	 * @return variable de tipo String contenida en circunscripcion
	 * 
	 * @since 14/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getCircunscripcion() {
		return circunscripcion;
	}

	/**
	 * @param circunscripcion: variable de tipo String contenida en circunscripcion
	 *
	 * @since 14/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setCircunscripcion(String circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

}
