/**
 * @(#)ASBandejaOficialiaInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;

/**
 * Interface encargada de administrar el o los AS del mÓdulo de 
 * Bandeja de Oficialia.
 * 
 * @author David Rodríguez Corral
 * @since 16/11/2017
 */
public interface ASBandejaOficialiaInterface {

	/**
	 * Método que consulta en la bd las instrucciones del usuario en sesión.
	 * 
	 * @return List<DTOBandejaEntradasOficialiaEntity>: Lista que contiene las instrucciones del usuario en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/07/2017
	 */
	public List<DTOCAreaEntity> consultarAreasBandejaEntrada(Integer idOficialia);
	
	public Integer consultarNumeroEntrada(Integer idOficialia);
	
	public Integer consultarNumeroEntrada(Integer idOficialia, Integer idArea);

	public List<DTOBandejaEntradasOficialiaEntity> obtenerListaDeDocumentos(
			DTOBandejaOficialiaHelper bandeja, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);

	public Integer obtenerTotalDeDocumentos(DTOBandejaOficialiaHelper bandeja);

	public List<DTOCAreaEntity> consultarAreasOficialias(Integer idOficialia);

	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(Integer idOficialia, Integer idArea);

	public List<DTODocumentoAnexoEntity> consultarAnexos(DTOBandejaEntradasOficialiaEntity dtBandejaSeleccionados);
	
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialia(Integer idOficialia, Integer idArea);
	
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialiaNombre(Integer idOficialia, Integer idArea);
	
	/**
	 * Método que consulta a la tabla de BANDEJA_ENTRADAS_OFICIALIAS para activar la bandera en_clasificacion e indicar en el campo
	 * id_persona _clasificando el id de la persona quien está clasificando el documento
	 * 
	 * @param idDocumento: id del documento que se clasificará
	 * @param idPersona: id de la persona quién desea clasificar el documento
	 * @param idArea: id del área que clasificará el documento 
	 * 
	 * @return String: Mensaje indicando si el documento se puede clasificar o está siendo clasificado por alguien más
	 * 
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public String clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandeja, Integer idPersona, Integer idArea);
	
	/**
	 * Método que consulta la lista de remitentes de un documento
	 * 
	 * @param idDocumento: id del documento del cual se obtendrán los remitentes
	 * @param idArea: id Area de la cual se decremtará la notificación
	 * @param tipoArea: tipo Area de la cual se decremtará la notificación
	 * @param noLeido: Integer que dice si el documento ya fue leido
	 * 
	 * @return List<DTODocumentosRemitentesEntity> lista que contiene la lista de remitentes de un documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public String consultarRemitentes(Integer idDocumento, Integer idArea, Integer tipoArea, Integer noLeido);
	
	/**
	 * Método que consulta el número de documentos pendientes por área en NOTIFICACIONES_OF
	 * 
	 * @param idArea: id del área de la cual se hace la consulta
	 * @param tipoArea: tipo del área del cual se hace la consulta
	 * @param idApartado: id del apartado del cual se hace la consulta
	 * 
	 * @return Integer Entero que regresa el número de documentos pendientes
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/12/2017
	 */
	public Integer consultarNotificacionesPorArea(Integer idArea, Integer tipoArea, Integer idApartado);
	
}
