/**
 * @(#)DAONotificacionesOFInterface.java 18/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFID;

/**
 * Interfaz que contiene la firma de los métodos que interactuan u obtienen datos de la
 * tabla de NOTIFICAIONES_OF del esquema de GESTION4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 18712/2017
 */
public interface DAONotificacionesOFInterface extends DAOGenericGestionInterface<DTONotificacionesOFEntity, DTONotificacionesOFID> {

	/**
	 * Método que consulta los documentosPendientes para un área o áreas (depende de en cuantas este dado de alta) para un módulo (o menú)
	 * de una persona de oficialia de partes. NOTA IMPORTANTE se hace por área debido a que las notificaciones se agrupan POR ÁREA ya que
	 * el mismo documento lo puede clasificar cualquier persona de oficialia que este registrado en esa área.
	 * 
	 * @param tipoAreaIdAreas: Una lista de cadenas las cuales tienen concatenado el tipo de area y el id area (tipo_area || id_area)
	 * @param idModulo: Identificador del módulo al que se le búscan las notificaciones.
	 * @return Integer: contiene el total de documentos pendientes (suma todos los campos de documentos_pendientes.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer consultarDocumentosPendientesXAreaXIdModulo(String[] tipoAreaIdAreas, Integer idModulo);
	
	/**
	 * Método que incrementa las notificaciones al enviar a oficialía de partes, el incremento se hace por área
	 *
	 * @param notificacionesOF: Objeto que contiene el idArea, tipoArea y idModulo en el cual se hace el incremento
	 *
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public void incrementar(DTONotificacionesOFEntity notificacionesOF);
	
	/**
	 * Método que decrementa las notificaciones al enviar a oficialía de partes, el decremento se hace por área
	 *
	 * @param idArea: idArea en el cual se hace el decremento
	 * @param idArea: tipoArea en el cual se hace el decremento
	 *
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public void decrementar(Integer idArea, Integer tipoArea);
	
	/**
	 * Método que consulta el número de documentos pendientes por área en NOTIFICACIONES_OF
	 * 
	 * @param idArea: id del área de la cual se hace la consulta
	 * @param tipoArea: tipo del área del cual se hace la consulta
	 * @param idApartado: id del apartado del cual se hace la consulta
	 * 
	 * @return Integer Entero que regresa el número de documentos pendientes
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public Integer consultarNotificacionesPorArea(Integer idArea, Integer tipoArea, Integer idApartado);
}
