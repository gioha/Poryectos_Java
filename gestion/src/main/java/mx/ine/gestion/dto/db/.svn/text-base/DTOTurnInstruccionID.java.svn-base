/**
 * @(#)DTOValidacionDocumentosID.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase en la cual se mapea la llave primaria compuesta de la
 * tabla de TURN_INSTRUCCION perteneciente al esquema de gestion4
 *
 * @author Pável Alexei Martínez Regalado
 * @since  18/01/2018
 */
public class DTOTurnInstruccionID implements Serializable {

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -7376480280134884367L;
	
	/**
	 * Parte de la llave primaria, es llave foránea a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;

	/**
	 * Parte de la llave primaria, es llave foránea a HIST_DOC_RECEP
	 */
	@NotNull
	private Integer idHistoricoRecep;

	/**
	 * Parte de la llave primaria, es llave foránea a ESTRUCTURA_AREAS
	 */
	@NotNull
	private Integer idPersona;

	/**
	 * Parte de la llave primaria, es llave foranea a INSTRUCCIONES
	 */
	@NotNull
	private Integer idInstruccion;

	/**
	 * @return variable de tipo Integer contenida en idDocumento
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento: variable de tipo Integer contenida en idDocumento
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoRecep
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep: variable de tipo Integer contenida en idHistoricoRecep
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
	}

	/**
	 * @return variable de tipo Integer contenida en idPersona
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona: variable de tipo Integer contenida en idPersona
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return variable de tipo Integer contenida en idInstruccion
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdInstruccion() {
		return idInstruccion;
	}

	/**
	 * @param idInstruccion: variable de tipo Integer contenida en idInstruccion
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdInstruccion(Integer idInstruccion) {
		this.idInstruccion = idInstruccion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime
				* result
				+ ((idHistoricoRecep == null) ? 0 : idHistoricoRecep.hashCode());
		result = prime * result
				+ ((idInstruccion == null) ? 0 : idInstruccion.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOTurnInstruccionID other = (DTOTurnInstruccionID) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idHistoricoRecep == null) {
			if (other.idHistoricoRecep != null)
				return false;
		} else if (!idHistoricoRecep.equals(other.idHistoricoRecep))
			return false;
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

}
