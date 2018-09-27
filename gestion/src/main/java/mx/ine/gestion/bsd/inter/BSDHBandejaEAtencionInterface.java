/**
 * @(#)BSDHBandejaEAtencionInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDHBandejaEAtencionInterface {

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
	public void registrarAtencion(DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario);
	
	/**
	 * Método que guarda una respuesta de un documento turnado
	 * 
	 * @param persona
	 * @param documento
	 * @param comentario
	 * @param tipoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception;
	
	/**
	 * Método que reasigna un documento de la bandeja de atención hacía otra área
	 * 
	 * @param listaTitulares
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) throws Exception;

	/**
	 * @param atencionSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEAtencionEntity atencionSeleccionado);
}
