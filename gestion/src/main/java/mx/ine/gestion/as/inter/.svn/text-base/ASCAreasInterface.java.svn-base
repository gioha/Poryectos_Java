/**
 * @(#)ASCAreasInterface.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

/**
 * Interfaz donde se declararan los métodos de la clase AS.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 30/08/2017
 */
public interface ASCAreasInterface {
	
	/**
	 * Método que consulta las áreas.
	 * 
	 * @param tipoArea: Lista que contiene las instrucciones del usuario en sesión.
	 * @return Lista de tipo DTOCAreas con las áreas.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 30/08/2017
	 */
	public List<DTOCAreaEntity> buscarTodos() throws Exception;
	
	public List<DTOCAreaEntity> consultarAreasPorTipoArea(int tipoArea);

}
