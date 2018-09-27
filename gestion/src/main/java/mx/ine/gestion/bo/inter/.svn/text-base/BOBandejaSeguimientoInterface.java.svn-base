/**
 * @(#)BOBandejaSeguimientoInterface.java 02/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.helper.DTOPersonaBorradoresHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BOBandejaSeguimientoInterface {
	
	/**
	 * Método que une dos listas y evita que las listas contengan estructuras duplicadas
	 * 
	 * @param areas
	 * @param personas
	 * @return: La unión de las 2 listas recibidas sin elementos duplicados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/01/2018
	 */
	public List<DTOEstructuraAreasEntity> unirListas(List<DTOEstructuraAreasEntity> areas, List<DTOEstructuraAreasEntity> personas);
	
	
//	/**
//	 * 
//	 * @param areas
//	 * @param personas
//	 * @return
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 01/05/2018
//	 */
//	public List<DTOEstructuraAreasEntity> unirListasBOrradorHelper(List<DTOPersonaBorradoresHelper> areas, List<DTOPersonaBorradoresHelper> personas);

	
	/**
	 * 
	 * @param documento
	 * @param dtoEstructuraAreasEntity
	 * @param estatus
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/02/2018
	 */
	public DTOHistDocCreacionEntity crearHistoricoCreacion(DTODocumentoEntity documento, DTOEstructuraAreasEntity dtoEstructuraAreasEntity, Integer estatus);
	
	public DTOHistDocCreacionEntity crearHistoricoCreacion(DTODocumentoEntity documento, DTODocumentoDestinatarioEntity destinatario, Integer estatus);
	
	/**
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @param documento
	 * @param idHistoricoPadre
	 * @param estatusRecep
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/02/2018
	 */
	public DTOHistDocRecepEntity crearHistoricoRecep(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, Integer idHistoricoPadre, Integer estatusRecep);
	
	/**
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @param documento
	 * @param idHistoricoPadre
	 * @param estatusRecep
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/02/2018
	 */
	public DTOHistDocTurnoEntity crearHistoricoTurno(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, Integer idHistoricoRecep, Integer estatusTurno, String comentario);
	
	
	/**
	 * 
	 * @param comentarios
	 * @param comentariosNoLeidos
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public int obtenerDiferenciaRegistrosComentarios(List<DTOComentariosDocumentoEntity> comentarios, List<DTOComentariosNoLeidos> comentariosNoLeidos);
	
	public DTOComentariosNoLeidos crearComentarioNoLeido(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona, DTOComentariosDocumentoEntity comentario);
	
	public boolean tieneRespuesta(DTOResponderTurnadoHelper helperRespuesta);
	
	
	
}