/**
 * @(#)DAOFirmaDocumentosInterface.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosID;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;

/**
 * Interface que contiene la firma de los métodos que consultarán a la tabla
 * FIRMA_DOCUMENTOS del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
public interface DAOFirmaDocumentosInterface
		extends
		DAOGenericGestionInterface<DTOFirmaDocumentosEntity, DTOFirmaDocumentosID> {

	/**
	 * Método que consulta en la bd los documentos que tiene el usuario para ser
	 * firmados
	 * 
	 * @param idPersona
	 *            : id de la persona que esta en sesión.
	 * @param pendienteEnvio
	 *            : Entero que indica si no está firmado o está pendiente de
	 *            envío (ya se firmó).
	 * 
	 * @return List<DTOInstrucciones>: Lista que contiene los documentos para
	 *         ser firmados.
	 * 
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public List<DTOFirmaDocumentosEntity> consultarDocsFirmas(
			Integer idPersona, Integer pendienteEnvio);

	/**
	 * Método que revisa en la tabla "FIRMA_DOCUMENTOS y regresa todos los
	 * registros que tengan el Id Documento del borrador recibido.
	 * 
	 * @param dtoBorradorDocumentosEntity
	 *            : Borrador del cual se tomará su Id Documento y se buscarán
	 *            todos los registros que coincidan.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/10/2017
	 */
	public List<DTOFirmaDocumentosEntity> consultarFirmaBorrador(
			DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity);

	/**
	 * Método que inserta un registro en la tabla "FIRMA_DOCUMENTOS".
	 * 
	 * @param destinatario
	 *            : Objeto del cual se insertar el Id de la persona que va a
	 *            firmar el docuemnto.
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
	 * @since 05/10/2017
	 */
	public void insertarFirma(DTOEstructuraAreasEntity destinatario,
			DTODocumentoEntity dtoDocumentoEntity,
			 DTOEstructuraAreasEntity remitente);

	/**
	 * Método que inserta un registro en la tabla "FIRMA_DOCUMENTOS".
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
	 * @since 05/10/2017
	 */
	public void insertarFirma(
			List<DTOEstructuraAreasEntity> listaDestinatarios,
			DTODocumentoEntity dtoDocumentoEntity,
			 DTOEstructuraAreasEntity remitente);

	/**
	 * Método que modifica el campo PENDIENTE_ENVIO de la tabla FIRMA_DOCUMENTOS
	 * para indicar que el documento ya está firmado pero aún falta de enviar a
	 * destinatario
	 * 
	 * @param dtoDocFirmado
	 *            : Objeto de documentos a modificar el campo
	 * @param pendiente
	 *            : Entero que indica si el campo ya se firmó o no
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/10/2017
	 */
	public void actualizarPendienteEnvio(
			DTOFirmaDocumentosEntity dtoDocsFirmados, Integer pendiente);
	
	/**
	 * Método que prende la bandera con_modicaciones en la tabla de FIRMA_DOCUMENTOS
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void activarModificacionFirma(Integer idPersona, Integer idDocumento);
	
	/**
	 * Método que activa la bandera conVisualizacion indicando que el documento ha sido visto
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/11/2017
	 */
	public void activarVisualizacion(Integer idPersona, Integer idDocumento);

	/**
	 * Método registra quien regreso el documento a remitente
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/11/2017
	 */
	public void activarEstatusPersonaRegresado(Integer idPersona,
			Integer idDocumento);

	/*
	 * 
	 */
	public void insertarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento, Integer idComentario);
	
	public void quitarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento);

	/*
	 * 
	 */
	public Integer obtenerIdComentario(Integer idDocumento, String usuarioComento);
	
	public Integer consultarEdicionDocumento(Integer idDocumento);
	
	/**
	 * Método que obtiene el estatus regresado de un documento 
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 27/11/2017
	 */
	public Integer consultarEstadoRegresado(Integer idPersona, Integer idDocumento);
	
	public void marcarComoLeido(Integer idPersona, Integer idDocumento);
	
	/**
	 * Método que bloquea un documento
	 * 
	 * @param idPersona: id de la persona que esta en sesión.
	 * @param idDocumento: id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/01/2017
	 */
	public void bloquearDocumento(Integer idDocumento, Integer idPersona);
	
	/**
	 * Método que libera un documento
	 * 
	 * @param idPersona: id de la persona que esta en sesión.
	 * @param idDocumento: id del documento
	 * 
	 * @return DTOFirmaDocumentosEntity: Objeto firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/01/2017
	 */
	public void desbloquearDocumento(Integer idDocumento, Integer idPersona);
	
	/**
	 * Método otiene un registro de FIRMA_DOCUMENTOS
	 * 
	 * @param idPersona: id de la persona que esta en sesión.
	 * @param idDocumento: id del documento
	 * 
	 * @return DTOFirmaDocumentosEntity: Objeto firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/01/2017
	 */
	public DTOFirmaDocumentosEntity obtenerFirma(Integer idDocumento, Integer idPersona);
	
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
	
	public Integer consultarRegresado(Integer idPersona, Integer idDocumento);

}
