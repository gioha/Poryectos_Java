/**
 * @(#)BOFirmaInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jodconverter.office.OfficeException;

import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity; 
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;

/**
 * Interfaz donde se declararan los métodos de la clase BO del módulo de
 * Firma.
 *
 * @author David Rodríguez Corral
 * @since 15/10/2017
 */
public interface BOFirmaInterface {

	/**
	 * Método que crea un comentario de tipo DTOComentarioDocumentosEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOComentarioDocumentosEntity Objeto que contiene un comentario
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOComentariosDocumentoEntity crearComentario(DTOFirmaDocumentosEntity dtoFirma);
	
	/**
	 * Método que crea un objeto edicion de tipo DTOEdicionesDocumentoEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOEdicionesDocumentoEntity: Objeto que contiene la edición
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOEdicionesDocumentoEntity crearEdicion(DTOFirmaDocumentosEntity dtoFirma);
	
	/**
	 * Método que crea un objeto historial de creación de tipo DTOHistDocCreacionEntity
	 * 
	 * @param dtoFirma: Objeto a crear
	 * 
	 * @return DTOHistDocCreacionEntity que contiene un historial al guardar
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOHistDocCreacionEntity crearHistorial(DTOFirmaDocumentosEntity dtoFirma);
	
	/**
	 * Método que verifica si ya se firmó y/o validó el documento que se quiere editar
	 * 
	 * @param dtoDocumento: Objeto para verificar la firma y validación
	 * 
	 * @return Integer que contiene representa un tipo de mensaje
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public Integer verificarFirmaYValidacion(DTODocumentoEntity dtoDocumento);
	
	/**
	 * Método que crea un objeto tipo DTOHistDocCreacionEntity
	 * 
	 * @param dtoFirmaDocumentos: Objeto a crear
	 * 
	 * @return DTODatosFirmaDocEntity que contiene los datos de los documentos a firmar
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTODatosFirmaDocEntity crearDatosDatosFirmas(DTOFirmaDocumentosEntity dtoFirmaDocumentos);
	
	/**
	 * Método que crea un objeto tipo DTOEnviadosDocumentosEntity
	 * 
	 * @param dtoEnviadosDocumentos: Objeto a crear
	 * @param idArea: id del área
	 * @param tipoArea: tipo del área
	 * @param idPersona: id de la persona
	 * 
	 * @return DTOEnviadosDocumentosEntity: Objeto para insertar los enviados 

	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	 public DTOEnviadosDocumentosEntity crearEnviados(DTOFirmaDocumentosEntity dtoEnviadosDocumentos, Integer idArea, Integer tipoArea, Integer idPersona);
	
	/**
	 * Método para llenar los datos que faltan para poder insertar el comentario
	 * 
	 * @param dtoComentario: comentario
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/11/2017
	 */
	public void configurarComentario(DTOComentariosDocumentoEntity dtoComentario);

	/**
	 * Método que crea un objeto tipo DTOBandejaEntradasOficialiaEntity
	 * 
	 * @param dtoFirmados: Objeto a crear
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity que contiene los datos de los documentos a la Bandeja de Oficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public DTOBandejaEntradasOficialiaEntity crearBandejaEntrada(DTOBandejaEntradasOficialiaEntity dtoFirmados);
	
	/**
	 * Método que convierte un archivo docx a pdf
	 * 
	 * @param documento: Objeto que contiene los datos del documento a convertir
	 * @param carpeta: String que contiene la carpeta donde está guardado el documento - Puede ser "documentos"7"documentos_firmados"
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity que contiene los datos de los documentos a la Bandeja de Oficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void crearPdf(DTODocumentoEntity documento, String carpeta) throws Exception;
	
	/**
	 * Método que convierte un archivo docx a pdf
	 * 
	 * @param documento: Objeto que contiene los datos del documento a convertir
	 * @param carpeta: String que contiene la carpeta donde está guardado el documento - Puede ser "documentos"7"documentos_firmados"
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity que contiene los datos de los documentos a la Bandeja de Oficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void transformarOpenOffice(String docPath, String pdfPath, DTODocumentoEntity documento) throws OfficeException;

	public DTODocumentosRemitentesEntity crearDocsRems(DTOFirmaDocumentosEntity dtoFirmados);
	
	/**
	 * Método obtiene el hash de un documento
	 * 
	 * @param nombreDocumento: Nombre del documento
	 * @param path: String que contiene la dirección del documento

	 * 
	 * @return String: Contiene el hash

	 * 
	 * @author David Rodríguez Corral
	 * @throws java.io.IOException 
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @since 02/01/2017
	 */
	public String crearHashDocumento(String nombreDocumento, String path) throws NoSuchAlgorithmException, java.io.IOException ;
	
	/**
	 * Método que inserta la firma en el documento
	 * 
	 * @param nombreDocumento: dtoBorradorDocumento
	 * @param nombreDocumento: dtoFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void insertarFirmaEnDcumento(DTODocumentoEntity dtoBorradorDocumento,
			DTOFirmaDocumentosEntity dtoFirma)throws  IOException, Exception, FileNotFoundException;
	
	/**
	 * Método obtiene el hash de una cadena con algoritmo SHA-256
	 * 
	 * @param cadena: cadeana a obtener su hash
	 * 
	 * @return String que contiene el hash de una cadena
	 * 
	 * @author David Rodríguez Corral
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @since 04/01/2017
	 */
	public String crearHashCadena(String cadena) throws NoSuchAlgorithmException, UnsupportedEncodingException ;
}
