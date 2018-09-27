/**
 * @(#)DAOInstruccionesInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesID;

/**
 * Interface que contiene la firma de los métodos que consultaran
 * a la tabla INSTRUCCIONES del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 28/08/2017
 */
public interface DAOInstruccionesInterface extends DAOGenericGestionInterface<DTOInstruccionesEntity, DTOInstruccionesID>{

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
	 * Método que elimina logicamente las instrucciones del usuario en sesión
	 * cambiando el estatus = 'A'-- Activa a estatus = 'E' --Eliminada.
	 * 
	 * @param idPersona: Entero que contiene el id de la persona en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void eliminarInstruccionesLogicas(Integer idPersona);
	
	/**
	 * Método que actualiza la lista de instrucciones en la bd del usuario en sesión.
	 * 
	 * @param lista: Lista que contiene las instrucciones a actualizar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void actualizarInstrucciones(DTOInstruccionesEntity dtoInstrucciones);
	
	public List<DTOInstruccionesEntity> obtenerInstrucciones(Integer idHistoricoRecep);
}
