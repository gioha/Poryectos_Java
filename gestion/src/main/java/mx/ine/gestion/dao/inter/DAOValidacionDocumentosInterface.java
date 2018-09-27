/**
 * @(#)DAOValidacionDocumentosInterface.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosID;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;

/**
 * Interface que contiene la firma de los métodos que consultarán a la tabla
 * VALIDACION_DOCUMENTOS del esquema gestion4.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 11/10/2017
 */
public interface DAOValidacionDocumentosInterface
		extends
		DAOGenericGestionInterface<DTOValidacionDocumentosEntity, DTOValidacionDocumentosID> {

	/**
	 * Método que consulta en la bd los documentos que tiene el usuario para ser
	 * validados
	 * 
	 * @param idPersona : id de la persona que esta en sesión.
	 * 
	 * @return List<DTOValidacionDocumentosEntity>: lista con los documentos de ese
	 * usuario para su validación.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/10/2017
	 */
	public List<DTOValidacionDocumentosEntity> consultarDocsParaValidacion(
			Integer idPersona);

	/**
	 * Método que inserta un registro en la tabla "VALIDACION_DOCUMENTOS".
	 * 
	 * @param destinatario
	 *            : Objeto del cual se insertar el Id de la persona que va a
	 *            firmar el documento.
	 * @param dtoDocumentoEntity
	 *            : Objeto del cual se va a insertar el Id del documento que va
	 *            a ser firmado.
	 * @param hashDocumentoRecibido
	 *            Hash del documento que va ser firmado.
	 * @param remitente
	 *            : Objeto del cual se va a insertar el Id de la persona que
	 *            envió el documento a firmar.
	 *
	 * @author Homero Fidel Villanueva
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/10/2017
	 */
	public void insertarValidacion(DTOEstructuraAreasEntity destinatario,
			DTODocumentoEntity dtoDocumentoEntity,
			DTOEstructuraAreasEntity remitente);

	/**
	 * Método que inserta un registro en la tabla "VALIDACION_DOCUMENTOS".
	 * 
	 * @param listaDestinatarios
	 *            : Lista de personas a las cuales se les envió a firmar un
	 *            documento.
	 * @param dtoDocumentoEntity
	 *            : Objeto del cual se va a insertar el Id del documento que va
	 *            a ser firmado.
	 * @param hashDocumentoRecibido
	 *            : Hash del documento que va ser firmado.
	 * @param remitente
	 *            : Objeto del cual se va a insertar el Id de la persona que
	 *            envió el documento a firmar.
	 *
	 * @author Homero Fidel Villanueva
	 * @author Pável Alexei Martínez Regalado
	 * @since 05/10/2017
	 */
	public void insertarValidacion(
			List<DTOEstructuraAreasEntity> listaDestinatarios,
			DTODocumentoEntity dtoDocumentoEntity,
			DTOEstructuraAreasEntity remitente);

	/**
	 * Método que elimina registros de la tabla
	 * VALIDACION_DOCUMENTOS para indicar que el documento fue regresado 
	 * por todas las personas
	 * 
	 * @param idPersona: Integer con el id de la persona que recibió.
	 * @param idPersonaRemitente: Integer con el id de la persona que mandó a validar.
	 * @param idDocumento: Integer con el id del documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 23/10/2017
	 */
	public void eliminarValidacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento);
	
	
	/**
	 * Método que modifica el campo ESTATUS_REGRESADO y PERSONA_REGRESO_ID de la tabla
	 * VALIDACION_DOCUMENTOS para indicar que una persona regreso el documento y no lo validó
	 * 
	 * @param personaRegresoId: Integer con el id de la persona que regresó el documento sin validar.
	 * @param idPersonaRemitente: Integer con el id del remitente.
	 * @param idDocumento: Integer con el id del documento que se regresó a validar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/10/2017
	 */
	public void actualizarValidaciones(Integer personaRegresoId, Integer idPersonaRemitente, Integer idDocumento);
	
	/**
	 * Método para indicar que se visualizó un documento en la bandeja de validación
	 * 
	 * @param idPersona: Integer con el id de la persona en el sistema en el momento.
	 * @param idPersonaRemitente: Integer con el id del remitente.
	 * @param idDocumento: Integer con el id del documento que se regresó a validar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/11/2017
	 */
	public void insertarVisualizacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento);
	
	/**
	 * Método para indicar que se editó un documento en la bandeja de validación
	 * 
	 * @param idPersona: Integer con el id de la persona en el sistema en el momento.
	 * @param idPersonaRemitente: Integer con el id del remitente.
	 * @param idDocumento: Integer con el id del documento que se regresó a validar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/11/2017
	 */
	public void insertarEdicion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento);
	
	/*
	 * 
	 */
	public void insertarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento, Integer idComentario);
	
	public void quitarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento);

	/*
	 * 
	 */
	public Integer obtenerIdComentario(Integer idDocumento, String usuarioComento);
	
	/**
	 * Método para obtener las listas de Remitentes, Destinatarios y Ccp de cada documento
	 * 
	 * @param idDocumento: Integer con el id del documento
	 * 
	 * @return DTODocumentoOficialiaHelper: objeto con los remitentes, destinatarios y ccps separados por comas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/01/2017
	 */
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception;

	/**
	 * 
	 * @param idDocumento
	 * @param idPersona
	 * @return
	 */
	public Integer obtenerEstatusRegresado(Integer idDocumento, Integer idPersona);
}
