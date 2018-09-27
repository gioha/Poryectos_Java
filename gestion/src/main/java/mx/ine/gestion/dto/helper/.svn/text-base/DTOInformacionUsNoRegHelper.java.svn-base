/**
 * @(#)DTOInformacionUsNoRegHelper.java 16/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Clase de ayuda en la vista de usuarios no registrados,
 * contiene la información que se muestra
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
public class DTOInformacionUsNoRegHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -7475020265965182692L;

	/* ----------------------------------- Atributos ------------------------------------ */

	/**
	 * Indica si el área de LDAP que tiene el usuario ya fue registrada o no en el sistema de
	 * gestión (más especificamente en la BD)
	 */
	private boolean tienePosibleArea;

	/**
	 * Indica si el área en la que no esta dado de alta tiene usuarios con rol de ADMINISTRADOR DE
	 * ÁREA, los cuales se le proporsionaran para que se comunique con ellos.
	 */
	private boolean tieneAdministradoresArea;

	/**
	 * Atributo que contendra la lista de administradores del área a para la que el usuario aun
	 * no ha sido dada de alta
	 */
	private List<DTOEstructuraAreasEntity> listaAdministradoresArea;

	/**
	 * Atributo que guardara la información del titular para cuando el caso en el que el área
	 * del usuario no registrado no tenga administrador
	 */
	private DTOEstructuraAreasEntity titular;

	/**
	 * Atributo que guarda la información de los corresponsables del titular para el caso en el
	 * que el área del usuario no registrado no tenga administrador
	 */
	private List<DTOEstructuraAreasEntity> listaCorresponsablesTitular;
	
	/* -------------------------------- getters & setters ------------------------------- */
	
	/**
	 * @return valor de tipo Boolean que esta contenido en la variable tienePosibleArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 16/01/2018
	 */
	public boolean getTienePosibleArea() {
		return tienePosibleArea;
	}

	/**
	 * @param tienePosibleArea : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 16/01/2018
	 */
	public void setTienePosibleArea(boolean tienePosibleArea) {
		this.tienePosibleArea = tienePosibleArea;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable tieneAdministradoresArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public boolean isTieneAdministradoresArea() {
		return tieneAdministradoresArea;
	}

	/**
	 * @param tieneAdministradoresArea : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public void setTieneAdministradoresArea(boolean tieneAdministradoresArea) {
		this.tieneAdministradoresArea = tieneAdministradoresArea;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaAdministradoresArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaAdministradoresArea() {
		return listaAdministradoresArea;
	}

	/**
	 * @param listaAdministradoresArea : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public void setListaAdministradoresArea(
			List<DTOEstructuraAreasEntity> listaAdministradoresArea) {
		this.listaAdministradoresArea = listaAdministradoresArea;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable titular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public DTOEstructuraAreasEntity getTitular() {
		return titular;
	}

	/**
	 * @param titular : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public void setTitular(DTOEstructuraAreasEntity titular) {
		this.titular = titular;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaCorresponsablesTitular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaCorresponsablesTitular() {
		return listaCorresponsablesTitular;
	}

	/**
	 * @param listaCorresponsablesTitular : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public void setListaCorresponsablesTitular(
			List<DTOEstructuraAreasEntity> listaCorresponsablesTitular) {
		this.listaCorresponsablesTitular = listaCorresponsablesTitular;
	}

}
