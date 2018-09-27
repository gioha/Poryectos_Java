/**
 * @(#)VHFirmaInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.List;

import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;

/**
 * Interfaz que contiene la firma de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author David Rodríguez Corral
 * @since 15/10/2017
 */
public interface VHFirmaInterface {

	/**
	 * Método que verifica que existan documentos sin cambios
	 * 
	 * @param dtoLFirma: lista de oficios a revisar
	 * 
	 * @return Boolean: Si al menos existe uno
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOFirmaDocumentosEntity verificarOficioSincambios(List<DTOFirmaDocumentosEntity> dtoLFirma);
	
	/**
	 * Método que verifica no se hayan seleccionado documentos que ya fueron enviados al remitente
	 * 
	 * @param dtoLFirma: lista de oficios a revisar
	 * 
	 * @return Boolean: Si al menos existe uno
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public String verificarOficioConNotificacion(List<DTOFirmaDocumentosEntity> dtoLFirma);
	
	/**
	 * Método que elimina las validaciones en el documento word
	 * 
	 * @param ruta: Ruta del archivo fuente
	 * @param ruta: Ruta del archivo destino
	 * 
	 * @return List<DTOFirmaDocumentosEntity>: Lista sin los oficios que no sufrieron cambios.
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void eliminarValidacionEnDocumento(String ruta, String ruta2, String iniciales) throws Exception;
	
	/**
	 * Método que abre un documento word 
	 * 
	 * @param nombreArchivo: Nombre del archivo a abrir
	 * @param ruta: ruta del archivo a abrir
	 * @param soloLectura: visualización en modo "solo lectura"
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void visualizarDocumento(String nombreArchivo, String ruta, Boolean soloLectura) throws Exception;
	
	
	/**
	 * Método que obtiene el algoritmo de  cifrado de un certificado
	 * 
	 * @param inp
	 *            Certificado a obtener el algoritmo;
	 
	 * @return String que contiene el algoritmo de cifrado
	 *
	 * @author David Rodríguez Corral
	 * @throws CertificateException 
	 * @since 07/11/2017
	 */
	public String obtenerAlgoritmoCertificado(InputStream inp) throws CertificateException;

	/**
	 * Método que elimina de la lista los documentos que ya han sido leídos
	 * 
	 * @param  dtoDocumentosSeleccionados: Lista que contiene los documentos seleccionados
	 * 
	 * @return List<DTOFirmaDocumentosEntity> lista que contienes los documentos no leídos
	 *
	 * @author David Rodríguez Corral
	 * @since 21/12/2017
	 */
	public List<DTOFirmaDocumentosEntity> eliminarDocumentosLeidos(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados);

	
	
	
}
