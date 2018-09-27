/**
 * @(#)VHOficialiaPartesInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import java.util.List;

import org.primefaces.event.FileUploadEvent;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOAreasOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOCapturaOficialiaHelper;

/**
 * Interfaz que contiene la firma de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author David Rodríguez Corral
 * @since 07/12/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public interface VHOficialiaPartesInterface {
	/**
	 * Método que obtiene la ruta del documento a clasificar para su descarga
	 *
	 * @return String que guarda la ruta del documento a descargar
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/10/2017
	 */
	public String obtenerRutaGlusterPdf();
	
	/**
	 * Método que obtiene la ruta del anexo que tiene un documento
	 *
	 * @return String que guarda la ruta del anexo a descargar
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/10/2017
	 */
	public String obtenerRutaGlusterAnexo();

	/**
	 * Método que verifica que se haya insertado por lo menos un campo para la búsqueda de persona
	 *
	 * @return String que guarda un mensaje de alerta en caso de no haberse insertado 
	 * 
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public String validarConsultaRemitentes(DTOAreasOficialiaHelper dtoCombosRemitentes);
	
	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como remitente.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarAgregarRemitente(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp);
	
	/**
	 * Método que tiene la funcionalidad de agregar los remitentes que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarRemitentes(DTOAreasOficialiaHelper dtoCombosRemitentes);
	
	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como destinatario.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarAgregarDestinatario(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp);
	
	/**
	 * Método que tiene la funcionalidad de agregar los destinatarios que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarDestinatarios(DTOAreasOficialiaHelper dtoCombosDestinatarios);
	
	/**
	 * Método que se utiliza para validar cuando se va a agregar a una persona como CCP.
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 * @return String: manda una cadena con el error en caso de haber un problema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public String validarAgregarCCP(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp);
	
	/**
	 * Método que tiene la funcionalidad de agregar los CCP que fueron seleccionados
	 * y lo que conlleva agregarlos
	 * 
	 * @param filtros: Objeto que contiene la información seleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/10/2017
	 */
	public void agregarCCP(DTOAreasOficialiaHelper dtoCombosCcp);

	/**
	 * Método encargado de validar los metadatos de un archivo adjunto con el fin de
	 * verificar que la extensión de dicho archivo sea la requerida.
	 * @param FileUploadEvent archivo, String tipoDoc
	 * @throws Exception
	 * @return boolean
	 * @author José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public boolean validarMetadatosArchivo(FileUploadEvent archivo, String tipoAdjunto) throws Exception;

	/**
	 * Método encargado de generar un temporal del archivo cargado para su posterior carga en el gluster.
	 * @param DTOCapturaOficialiaHelper dtoCapturaOf, FileUploadEvent archivo, boolean esAnexo, boolean eliminarTemp
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void generarTemporalArchivoAdjunto(DTOCapturaOficialiaHelper dtoCapturaOf, FileUploadEvent archivo, boolean esAnexo, boolean eliminarTemp) throws Exception;

	/**
	 * Método encargado de validar cada uno de los campos introducidos por el usuario en la vista de Folio y Carga de Documento.
	 * @param dtoCapturaOf, dtoDocumentoOf, areaDoc, conNumeroDoc, listaRemitentesDoc, listaDestinatariosDoc, listaCcpDoc)
	 * @return String
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public String validarFiltrosCaptura(DTOCapturaOficialiaHelper dtoCapturaOf, DTODocumentoEntity dtoDocumentoOf, String areaDoc,
										Boolean conNumeroDoc, List<DTOEstructuraAreasEntity> listaRemitentesDoc,
										List<DTOEstructuraAreasEntity> listaDestinatariosDoc, List<DTOEstructuraAreasEntity> listaCcpDoc);

	/**
	 * Método que valida la correcta inserción de los filtros de búsqueda de remitentes externos.
	 * @param DTOCapturaOficialiaHelper dtoCapturaOf
	 * @return String
	 * 
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public String validarConsultaRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf);

	/**
	 * Método que valida la correcta adición de un nuevo remitente externo a la lista de agregados.
	 * @param DTOCapturaOficialiaHelper dtoCaptura
	 * @return String
	 *
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public String validarAgregarRemitenteExt(DTOCapturaOficialiaHelper dtoCaptura);

	/**
	 * Método que inserta uno o varios remitentes externos a la lista de remitentes externos agregados.
	 * @param DTOCapturaOficialiaHelper dtoCaptura
	 * @return void
	 *
	 * @author José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public void agregarRemitentesExt(DTOCapturaOficialiaHelper dtoCaptura);

}
