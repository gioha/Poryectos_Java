/**
 * @(#)DAOEstadosInterface.java 13/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de ESTADOS del esquema GEOGRAFICOINE
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 13/12/2017
 */
public interface DAOEstadosInterface extends DAOGenericGestionInterface<DTOEstadosEntity, Integer> {

	/**
	 * Método para consultar los estados sin traer la opción de OC, ordenados por nombre.
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstadosSinOC();

	/**
	 * Método para consultar los estados sin organigrama
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados que no tienen organigrama
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/12/2017+
	 */
	public List<DTOEstadosEntity> consultarEstadosSinOrganigrama();
	
	/**
	 * Método para consultar los estados con organigrama
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados que tienen organigrama
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstadosConOrganigrama();

}
