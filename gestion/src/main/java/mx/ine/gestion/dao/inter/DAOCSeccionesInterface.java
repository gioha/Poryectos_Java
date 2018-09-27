/**
 * @(#)C_SECCIONES.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;

/**
 * Interface que contiene la firma de los métodos que consultaran a la tabla
 * C_SECCIONES del esquema GESTION4.
 * 
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
public interface DAOCSeccionesInterface extends DAOGenericGestionInterface<DTOCSeccionesEntity, Integer> {

	/**
	 * Método que regresa la lista de secciones ordenadas por id_seccion
	 * 
	 * @return lista de secciones
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCSeccionesEntity> consultarSecciones();

	
}
