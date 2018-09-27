package mx.ine.gestion.vh.inter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;


/**
 * @(#)VHCapturaDocumentoInterface.java 06/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interfaz de la capa de VH para el módulo captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 06/10/2017
 */
public interface VHCapturaDocumentoInterface {
	
	/**
	 * Método que se utiliza para reiniciar los filtros seleccionados por el usuario
	 * si este selecciona uno anterior o dependiente.
	 * 
	 * @param tipoReinicio: es un identificador que se usa dentro del método para saber que se debe de reiniciar
	 * @param filtros: Objeto que contiene la información de los filtros seleccionados por el usuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void reiniciaFiltros(Integer tipoReinicio, DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que se utiliza para validar la consulta de las personas que se seleccionaran como remitentes
	 * 
	 * @param filtros: Objeto que contiene la información de los filtros seleccionados por el usuario
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarConsultaRemitentes(DTOFiltrosCapturaDocumentoHelper filtros);
	
	/**
	 * Método que se utiliza para validar la consulta de las personas que se seleccionaran como destinatarios
	 * 
	 * @param filtros: Objeto que contiene la información de los filtros seleccionados por el usuario
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarConsultaDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros);
	
	/**
	 * Método que se utiliza para validar la consulta de las áreas que se seleccionaran como destinatarios
	 * 
	 * @param filtros: Objeto que contiene la información de los filtros seleccionados por el usuario
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public String validarConsultaDestinatariosPorArea(DTOFiltrosCapturaDocumentoHelper filtros);
	
	/**
	 * Método que se utiliza para validar la consulta de las personas que se seleccionaran como CCP
	 * 
	 * @param filtros: Objeto que contiene la información de los filtros seleccionados por el usuario
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarConsultaCCP(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como remitente.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 01/03/2018
	 */
	public String validarAgregarRemitente(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como destinatario.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 01/03/2018
	 */
	public String validarAgregarDestinatario(DTOFiltrosCapturaDocumentoHelper filtros);
	
	/**
	 * Método que se utiliza para validar cuando se va a agregar a una área como destinatario.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public String validarAgregarAreaDestinatario(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como CCP.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 01/03/2018
	 */
	public String validarAgregarCCP(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que tiene la funcionalidad de agregar los remitentes que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarRemitentes(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que tiene la funcionalidad de agregar los destinatarios que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros);
	
	/**
	 * Método que tiene la funcionalidad de agregar los áreas destinatarios que fueron seleccionadas
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 13/03/2018
	 */
	public void agregarAreasDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que tiene la funcionalidad de agregar los CCP que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarCCP(DTOFiltrosCapturaDocumentoHelper filtros);

	/**
	 * Método que genera el hyperlink para abrir el word de un documento en blanco
	 * 
	 * @param: Objeto donde se guardara el hyperlink y el nombre del archivo temporal
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void generarHyperLinkDocumentoBlanco(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception;
	
	/**
	 * Método que genera el hyperlink para abrir el word de un documento que se creara a partir de una plantilla
	 * 
	 * @param: Objeto donde se guardara el hyperlink y el nombre del archivo temporal
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void generarHyperLinkPlantilla(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception;

	/**
	 * Método que genera un archivo temportal cuando se adjunta un documento, para evitar que se pierda y se le pueda dar un mejor manejo.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @param archivo: Objeto de primefaces que contiene la información del archivo adjunto
	 * @param esAnexo: indica si se esta agregando un doc pdf o los anexos del documento
	 * @param borrarTemporales: indica si se desea agregar un archivo y borrar los temporales de la carpeta tempora que lo contendra
	 * @throws Exception: en caso de un error manda excepción.
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 14/03/2018
	 */
	public void generarTemporalArchivoAdjunto(DTOFiltrosCapturaDocumentoHelper filtros, FileUploadEvent archivo, boolean esAnexo,  boolean borrarTemporales) throws Exception;

	/**
	 * Método que valida los metadatos de un archivo adjunto en el sistema para corroborar que realmente sea la extención de archivo requerida
	 * 
	 * @param archivo: Objeto de primefaces que contiene la información del archivo adjunto
	 * @throws Exception: en caso de un error manda excepción.
	 * 
	 * @updatedBy Giovanni Hernández Alonso
	 * @since 23/03/2018
	 */
	public boolean validaMetadatosArchivoAnexo(FileUploadEvent archivo, String tipoDoc) throws Exception;

	
	/**
	 * Método que valida la información capturada previa a ser guardada.
	 * 
	 * @param filtros: Objeto que contiene la informacon capturada
	 * @return String: Regresa una cadena con el mensaje de error en caso de haberlo.
	 */
	public String validaCapturaDocumento(DTOFiltrosCapturaDocumentoHelper filtros);
	
	
	/**
	 * Método que valida los anexos almacenados en la pantalla de captura, si se ingresa un anexo agregado previamente regresa true, si si es nuevo retorna false.
	 * 
	 * @param anexos: Lista de los adjuntos agregados hasta el momento
	 * @param nombreAdjunto: Nombre del nuevo adjunto que se pretende agregar
	 * @return boolean: true si el nuevo anexo ya esta repetido, false si no esta repetido
	 */
	public boolean evaluarAdjuntoRepetidos( List<DTODocumentoAnexoHelper> anexos, String nombreAdjunto );
	
}
