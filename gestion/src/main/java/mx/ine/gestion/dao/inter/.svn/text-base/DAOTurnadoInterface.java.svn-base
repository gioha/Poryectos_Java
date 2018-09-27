/**
 * @(#)DAOTurnado.java 19/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;


/**
 * Interfaz que contiene la firma de los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de apartado de folios.
 * 
 * @author  Pável Alexei Martínez Regalado
 * @since 09/01/2018
 */
public interface DAOTurnadoInterface extends DAOGenericGestionInterface<Integer, Integer> {
	
	/**
	 * Método que consulta en la bd las personas a las que puede turnar el usuario que está logueado al momento
	 * 
	 * @param personaActual: de tipo DTOEstructuraAreaEntity. Persona logueada al momento.
	 * 
	 * @return List<DTOEstructuraAreaEntity>: lista con las personas a las que se puede turnar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/01/2018
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasTurnado(DTOEstructuraAreasEntity personaActual) throws Exception;

}
