/**
 * @(#)DTOInstruccionesEntity.java 29/08/2017
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
 * tabla de INSTRUCCIONES perteneciente al esquema de gestion4
 *
 * @author David Rodríguez Corral
 * @since 28/08/2017
 */
public class DTOInstruccionesID implements Serializable{

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -8445830617593697875L;

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersona;
	
	/**
	 * Parte de la llave primaria
	 */
	@NotNull
	private Integer idInstruccion;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idInstruccion == null) ? 0 : idInstruccion.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
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
		DTOInstruccionesID other = (DTOInstruccionesID) obj;
		if (idInstruccion == null) {
			if (other.idInstruccion != null)
				return false;
		} else if (!idInstruccion.equals(other.idInstruccion))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idInstruccion
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getIdInstruccion() {
		return idInstruccion;
	}

	/**
	 * @param idInstruccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setIdInstruccion(Integer idInstruccion) {
		this.idInstruccion = idInstruccion;
	}
	
	
		
}
