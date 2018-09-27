/**
 * @(#)ASHistorialInterface.java 17/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;


/**
 * Interface encargada de administrar el o los DAO del módulo de Historial.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 17/11/2017
 */
public interface ASHistorialInterface {

	/**
	 * Método para consultar las acciones realizadas en cada documento
	 * 
	 * @param idDocumento: id del documento 
	 * @return lista List<DTOHistDocCreacionEntity> con las acciones registradas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 17/11/2017
	 */
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception;

}
