package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;

/**
 * @(#)DAODocumentoInterface.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * DTODocumentoEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
public interface DAODocumentoInterface extends
		DAOGenericGestionInterface<DTODocumentoEntity, Integer> {

//	/**
//	 * Método que elimia el documento recibido de forma lógica(Cambiando el
//	 * estatus del documento a la constante "documento_eliminado")
//	 * 
//	 * @param dtoDocumentoEntity
//	 *            : El documento que será borrado lógicamente.
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/10/2017
//	 */
//	public void eliminarDocumento(DTODocumentoEntity dtoDocumentoEntity);
//
//	/**
//	 * Método que elimia el documento asociado al borrador recibido de forma
//	 * lógica(Cambiando el estatus del documento a la constante
//	 * "documento_eliminado")
//	 * 
//	 * @param dtoBorradorDocumentosEntity
//	 *            : Borrador
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/10/2017
//	 */
//	public void eliminarDocumento(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity);
	
	/**
	 * Método que consulta un registro de la tabla DOCUMENTOS
	 * 
	 * @param idDocumento
	 *            : id documento del documento
	 *
	 * @return Objeto de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTODocumentoEntity consultarDocumento(Integer idDocumento);
	
	/**
	 * Método que prende la bandera firmado en la tabla DOCUMENTOS para indicar
	 * que el documento tiene firma
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public void activarFirmaDocumento(Integer idDocumento);
	
	/**
	 * Método que apaga la bandera firmado en la tabla DOCUMENTOS para indicar
	 * se canceló la firma del documento
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/11/2017
	 */
	public void desactivarFirmaDocumento(Integer idDocumento);
	
	
	/**
	 * Método que prende la bandera de validado en la tabla DOCUMENTOS para
	 * indicar que el documento tiene validación
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 30/10/2017
	 */
	public void activarValidacionDocumento(Integer idDocumento);
	
	/**
	 * Método que apaga la bandera validado en la tabla de DOCUMENTOS
	 * 
	 * @param idDocumento
	 *            : Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void eliminarValidacionEnDocumento(Integer idDocumento);
	
	/**
	 * Método que prende la bandera editado en la tabla DOCUMENTOS para indicar
	 * que el documento ha sido editado y necesita ser transformado a pdf
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 13/11/2017
	 */
	public void activarEdicionDocumento(Integer idDocumento);
	
	/**
	 * Método que apaga la bandera editado en la tabla DOCUMENTOS
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 13/11/2017
	 */
	public void desactivarEdicionDocumento(Integer idDocumento);
	
	/**
	 * Método que obtiene el tipo de documento 
	 * 
	 * @param id del Documento: Integer que guarda el id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public String obtenerTipoDocumento(Integer idDocumento);

}
