/**
 * @(#)DTOBandejaOficialiaHelper.java 12/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * Clase DTO Helper para el módulo de bandeja de oficialía como filtro para obtener la 
 * lista de documentos a mostrar de forma lazy.
 * 
 * @author David Rodríguez Corral
 * @since 12/12/2017
 * 
 */
public class DTOBandejaOficialiaHelper implements Serializable {

	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = 6838417517431193174L;
	
	/**
	 * Atributo que guarda el filtro id de Oficialía
	 */
	private Integer idOficialia;
	
	/**
	 * Atributo que guarda el filtro id del Área
	 */
	private Integer idArea;
	
	/**
	 * Atributo que guarda el campo con el que se ordenará la lista de documentos. 
	 */
	private String campoOrden;

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable campoOrden
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public String getCampoOrden() {
		return campoOrden;
	}

	/**
	 * @param campoOrden : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public void setCampoOrden(String campoOrden) {
		this.campoOrden = campoOrden;
	}
	
}
