/**
 * @(#)DTOPersonaFirmaHelper.java 01/05/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 * @since 01/05/2018
 *
 */
public class DTOPersonaBorradoresHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3051628201365221994L;

	private DTOEstructuraAreasEntity persona;
	
	private boolean esRemitente;
		
	public DTOPersonaBorradoresHelper(DTOEstructuraAreasEntity persona){
		this.persona = new DTOEstructuraAreasEntity(persona);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		DTOPersonaBorradoresHelper other = (DTOPersonaBorradoresHelper) obj;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}
	
	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public DTOEstructuraAreasEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable esRemitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public boolean isEsRemitente() {
		return esRemitente;
	}

	/**
	 * @param esRemitente : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setEsRemitente(boolean esRemitente) {
		this.esRemitente = esRemitente;
	}
}
