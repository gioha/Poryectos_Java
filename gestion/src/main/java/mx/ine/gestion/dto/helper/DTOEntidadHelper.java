/**
 * @(#)DTOEntidadHelper.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * Clase de ayuda que contendrá las entidades
 *
 * @author Luis Urrutia V.
 * @since 07/12/2017
 */
public class DTOEntidadHelper implements Serializable{

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -6299355196318617451L;

	/**
	 * Variable que contiene el id de la entidad
	 */
	private Integer idEntidad;
	
	/**
	 * Variable que contiene el nombre de la entidad
	 */
	private String nombreEntidad;
	
	
/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidad
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public Integer getIdEntidad(){
		return idEntidad;
	}
		
	/**
	 * @param idEntidad : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public void setIdEntidad(Integer idEntidad){
		this.idEntidad = idEntidad;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable nombreEntidad
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public String getNombreEntidad(){
		return nombreEntidad;
	}
		
	/**
	 * @param nombreEntidad : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public void setNombreEntidad(String nombreEntidad){
		this.nombreEntidad = nombreEntidad;
	}
	
	
	
}
