package mx.ine.gestion.dto.db;

import java.io.Serializable;

/**
 * DTO para la tabla de JERARQUIA_PERSONAS para el schema gestion4  
 *
 * @author Pável Alexei Martínez Regalado
 * @since 06/09/2017
 */
public class DTOJerarquiaPersonasID implements Serializable {
	
	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = -6368860861082551715L;

	/**
	 * Atributo que guarda el ID de la Persona.
	 */
	private Integer idPersona; 

	/**
	 * Atributo que guarda el ID de la Persona Padre.
	 */
	private Integer idPersonaPadre;


	/*
	 * GETTERS Y SETTERS
	 */
	
	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idPersona
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param : valor que se ingresa al atributo idPersona de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idPersonaPadre
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdPersonaPadre() {
		return idPersonaPadre;
	}

	/**
	 * @param : valor que se ingresa al atributo idPersonaPadre de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdPersonaPadre(Integer idPersonaPadre) {
		this.idPersonaPadre = idPersonaPadre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result
				+ ((idPersonaPadre == null) ? 0 : idPersonaPadre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOJerarquiaPersonasID other = (DTOJerarquiaPersonasID) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		if (idPersonaPadre == null) {
			if (other.idPersonaPadre != null)
				return false;
		} else if (!idPersonaPadre.equals(other.idPersonaPadre))
			return false;
		return true;
	}
	
}