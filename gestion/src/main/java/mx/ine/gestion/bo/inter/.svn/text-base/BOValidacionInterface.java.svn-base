/**
 * @(#)BOValidacionInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */

package mx.ine.gestion.bo.inter;

import org.jodconverter.office.OfficeException;

import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;

/**
 * Interfaz donde se declararan los métodos de la clase BO del módulo de
 * Firma.
 *
 * @author David Rodríguez Corral
 * @since 15/10/2017
 */
public interface BOValidacionInterface {
	/**
	 * Método que crea un comentario de tipo DTOComentarioDocumentosEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOComentarioDocumentosEntity que contiene un comentario
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOComentariosDocumentoEntity crearComentario(DTOValidacionDocumentosEntity dtoFirma);
	
	/**
	 * Método que crea un objeto edicion de tipo DTOEdicionesDocumentoEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOComentarioDocumentosEntity que contiene una edición
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOEdicionesDocumentoEntity crearEdicion(DTOValidacionDocumentosEntity dtoFirma);
	
	/**
	 * Método que crea un objeto historial de creación de tipo DTOHistDocCreacionEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOComentarioDocumentosEntity que contiene un historial al guardar
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOHistDocCreacionEntity crearHistorial(DTOValidacionDocumentosEntity dtoFirma);
	
	public void validarDocumento(DTOValidacionDocumentosEntity dtoValidacionDocumento, String iniciales);
	
	/**
	 * Método para llenar los datos que faltan para poder insertar el comentario
	 * 
	 * @param dtoComentario: Objeto a crear
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public void configurarComentario(DTOComentariosDocumentoEntity dtoComentario);
	
	public void crearPdf(DTODocumentoEntity documento) throws Exception;
	
	public void transformarOpenOffice(String docPath, String pdfPath, DTODocumentoEntity documento) throws OfficeException;
	
	
}