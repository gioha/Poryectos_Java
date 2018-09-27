/**
 * DTOHistDocTurnoID.java 17/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author Pável Alexei Martínez Regalado 17/01/2018
 */
public class DTOHistDocTurnoID implements Serializable {

	/**
	 * Serial generado
	 */
	private static final long serialVersionUID = -5557063546259834380L;

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@NotNull
	private Integer idDocumento;

	/**
	 * Parte de la llave primaria, es llave foranea
	 */
	@NotNull
	private Integer idHistoricoRecep;

	// ------------------------ Métodos ------------------------ //

	/**
	 * @return variable de tipo Integer contenida en idDocumento
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento: variable de tipo Integer contenida en idDocumento
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoRecep
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep: variable de tipo Integer contenida en idHistoricoRecep
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
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
		DTOHistDocTurnoID other = (DTOHistDocTurnoID) obj;
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
		return true;
	}
}
