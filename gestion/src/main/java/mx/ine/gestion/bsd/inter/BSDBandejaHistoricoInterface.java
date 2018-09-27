/**
 * @(#)BSDBandejaHistoricoInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDBandejaHistoricoInterface {

	/**
	 * Método que registra en la BD la atención hacía un documento, actualizando
	 * el "ID_ESTATUS_TURNO" de la tabla "HIST_DOC_TURNO"
	 * 
	 * @param recibido: DTO que permite obteber el id del documento a actualizar
	 * @param persona: DTO que permite obtener el id de la persona que tiene asociado ese documento
	 * @param estatus: El estatus que se guardará en la BD
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/02/2018
	 */
	public void registrarAtencion(DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario);
}
