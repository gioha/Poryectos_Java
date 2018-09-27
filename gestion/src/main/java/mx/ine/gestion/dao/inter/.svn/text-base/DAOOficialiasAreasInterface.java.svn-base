/**
 * @(#)DAOOficialiasAreasInterface.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasID;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

/**
 * Interfaz que contiene la firma de los métodos que consultaran
 * a la tabla OFICIALIAS_AREAS del esquema GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public interface DAOOficialiasAreasInterface extends DAOGenericGestionInterface<DTOOficialiasAreasEntity, DTOOficialiasAreasID> {

	/**
	 * Método que consulta en la bd las oficialias por área.
	 * 
	 * @return List<DTOOficialiasAreasEntity>: Lista que contiene las relaciones para crear una estructura.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public List<DTOOficialiasAreasEntity> consultarOficialiasPorArea(Integer tipoArea, Integer idArea);

	/**
	 * Método que consulta todas las areas de los destinatarios de un documento
	 * 
	 * @return List<DTOCAreaEntity>: Lista que contiene las areas
	 * 
	 * @author David Rodríguez Corral
	 * @since 14/11/2017
	 */
	public List<DTOCAreaEntity> consultarAreasOficialia(Integer idOficialia);

	/**
	 * Método que consulta todas las areas registradas en cada una de las oficialías
	 * @return List<DTOCAreaEntity>: Lista que contiene las areas recuperadas
	 * 
	 * @update José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTOCAreaEntity> consultarTodasLasAreasOficialia();

}
