/**
 * @(#)BOCapturaDocumentoInterface.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import org.jodconverter.office.OfficeException;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;

/**
 * Interface de la capa de BO para funciones de negocio relacionadas al
 * documento.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
public interface BOCapturaDocumentoInterface {

	/**
	 * Método que obtiene la información del objeto de filtros y genera un objeto de entidad que se guarda en BD
	 * 
	 * @param filtros: Objeto que contiene la información capturada por el usuario.
	 * @return DTODocumentoEntity: Objeto de entidad generado
	 *
	 * @author Sergio Ley Garcia
     * @since 01/09/2017
	 */
	public DTODocumentoEntity generarEntidadDocumento(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que obtiene la información de sesión y genera un objeto de entidad que se guarda en BD
	 * 
	 * @param idDocumento: Identificador del documento generado
	 * @return DTOBorradorDocumentosEntity: Objeto de entidad generado
	 *
	 * @author Sergio Ley Garcia
     * @since 01/09/2017
	 */
	public DTOBorradorDocumentosEntity generaEntidadBorrador(Integer idDocumento);

	/**
	 * Método para guardar el archivo temporal en la carpeta principal y renombrarlo
	 * 
	 * @param filtros: Objeto que contiene la información capturada por el usuario.
	 * @param idDocumento: Identificador del documento generado
	 * @throws Exception: manda una excepcion en caso de haber un error
	 */
	public void guardarDocumentoGlusterPrincipal(DTOFiltrosCapturaDocumentoHelper filtros, Integer idDocumento) throws Exception;

	/**
	 * Método para echar para atras el guardado del documento temporal en la carpeta principal, esto por si
	 * se da que mande excepción
	 * 
	 * @param filtros: Objeto que contiene la información capturada por el usuario.
	 * @param idDocumento: Identificador del documento generado
	 * @throws Exception: manda una excepcion en caso de haber un error
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 23/01/2018
	 */
	public void reversaAGuardarDocumentoGlusterPrincipal(DTOFiltrosCapturaDocumentoHelper filtros, Integer idDocumento) throws Exception;

	/**
	 * Método que transforma el archivo guardado docx en pdf
	 * 
	 * @param idDocumento: Identificador del documento generado
	 * @throws OfficeException: manda una excepcion en caso de haber un error
	 */
	public void transformarAPdf(Integer idDocumento) throws OfficeException;
}
