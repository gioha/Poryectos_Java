/**
 * @(#)ASInstruccionesInterface.java 20/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

/**
 * Interfaz donde se declararan los métodos de la clase AS del módulo de
 * Instrucciones.
 *
 * @author David Rodríguez Corral
 * @since 20/08/2017
 */
public interface ASInstruccionesInterface {

	/**
	 * Método que consulta en la bd las instrucciones del usuario en sesión.
	 * 
	 * @return List<DTOInstrucciones>: Lista que contiene las instrucciones del usuario en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/07/2017
	 */
	public List<DTOInstruccionesEntity> consultarInstruccciones(Integer idPersona, Integer tipoInstruccion);
	
	/**
	 * Método que actualiza la lista de instrucciones en la bd del usuario en sesión.
	 * 
	 * @param lista: Lista que contiene las instrucciones a actualizar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void actualizarInstrucciones(List<DTOInstruccionesEntity> lista);
	
	/**
	 * Método que elimina logicamente las instrucciones del usuario en sesión
	 * cambiando el estatus = 'A'-- Activa a estatus = 'E' --Eliminada.
	 * 
	 * @param idPersona: Entero que contiene el id de la persona en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void eliminarInstruccionesLogicas(Integer idPersona);
}
