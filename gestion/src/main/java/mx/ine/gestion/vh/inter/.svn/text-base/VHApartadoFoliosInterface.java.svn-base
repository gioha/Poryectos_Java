/**
 * @(#)VHApartadoFoliosInterface.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;

/**
 * Interfaz que contiene la firma de los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de Apartado de Folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
public interface VHApartadoFoliosInterface {

	/**
	 * Método que se utiliza para cargar la información del usuario en sesión cuando se trata de usuario con rol
	 * diferente al administrador del sistema (el administrador de sistema puede seleccionar cualquier tipo de área
	 * y área por lo que no se precarga su información.
	 * 
	 * @param filtros: Objeto que contendra la información de sesión que se le ingresa en este método.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void cargarInformacionUsuarioEnSesion(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método para reiniciar los filtros que se encuentran en la pantalla de apartado de folios
	 * segun en el escenario que se le envie.
	 * 
	 * @param escenarioReinicio: identificador del escenario, por ejemplo el escenario 1 es cuando se selecciona el tipo de área
	 * 							 entonces todo lo que depende de ese filtro se reinicia, como el area, las listas de areas, etc.
	 * @param filtros: Objeto que contiene los valores de los filtros seleccionados así como las listas de opciones.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public void reiniciarFiltros(Integer escenarioReinicio, DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método que valida la infomación necesaria para generar la captura de folios (validaciones que no se hicieron en el front)
	 * 
	 * @param filtros: Objeto que contiene la información que el usuario selecciono en la pantalla.
	 * @return String: En caso de haber un error contiene la descripción de dicho error.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public String validarCapturaApartadoFolios(DTOFiltrosApartadoFolioHelper filtros);
}
