/**
 * @(#)VHBandejaEntradaInterface.java 19/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

import org.primefaces.event.FileUploadEvent;

/**
 * @author Homero Fidel Villanuevav
 * @since 19/03/2018
 *
 */
public interface VHBandejaEntradaInterface {

	/**
	 * Método que genera un archivo temportal cuando se adjunta un documento, para evitar que se pierda y se le pueda dar un mejor manejo.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @param archivo: Objeto de primefaces que contiene la información del archivo adjunto
	 * @throws Exception: en caso de un error manda excepción.
	 */
	public void generarTemporalArchivoAdjunto(DTOResponderTurnadoHelper filtroResponder, FileUploadEvent archivo) throws Exception;
	
	public void guardarAdjunto(DTOResponderTurnadoHelper filtroResponder);
}
