/**
 * @(#)MBUsuarioNoRegistrado.java 16/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.ine.gestion.bsd.inter.BSDUsuariosNoRegistradosInterface;
import mx.ine.gestion.dto.helper.DTOInformacionUsNoRegHelper;

/**
 * Clase que recibe todas las peticiones que se ejecutan en la vista de usuarios no registrados
 * 
 * @author Roberto Shirásago Domínguez
 * @since 16/01/2018
 */
public class MBUsuarioNoRegistrado implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -6181467849440101158L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	@Autowired
	@Qualifier("bsdUsuariosNorRegistrados")
	private transient BSDUsuariosNoRegistradosInterface bsdUsuariosNoRegistradosInterface;

	/**
	 * Objeto que contiene la información que se visualiza en la pantalla
	 */
	private DTOInformacionUsNoRegHelper info;

	/* --------------------------------------- Métodos -------------------------------------------- */

	/**
	 * Método el cual busca la información que se presenta en pantalla,
	 * valida si el usuario pertenece a un área ya registrada en gestión
	 * o es una desconocida.
	 * 
	 * @param info: Objeto que contendra la información
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 16/01/2018
	 */
	public void buscarInformacionUsuarioNoRegistrado() {

		this.info = new DTOInformacionUsNoRegHelper();
		this.bsdUsuariosNoRegistradosInterface.buscarInformacionUsuarioNoRegistrado(this.info);
	}
	
	/* ----------------------------------- Getters & Setters -------------------------------------- */

	/**
	 * @return valor de tipo DTOInformacionUsNoRegHelper que esta contenido en la variable info
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 16/01/2018
	 */
	public DTOInformacionUsNoRegHelper getInfo() {
		return info;
	}

	/**
	 * @param info : valor que se ingresa a la variable de tipo DTOInformacionUsNoRegHelper
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 16/01/2018
	 */
	public void setInfo(DTOInformacionUsNoRegHelper info) {
		this.info = info;
	}
}
