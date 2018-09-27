/**
 * @(#)DAOBandejaEntradasOficialiaInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaID;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;

import org.primefaces.model.SortOrder;


/**
 * Interface que contiene la firma de los métodos que consultaran
 * a la tabla BANDEJA_ENTRADAS_OFICIALIA del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 12/11/2017
 */
public interface DAOBandejaEntradasOficialiaInterface extends DAOGenericGestionInterface<DTOBandejaEntradasOficialiaEntity, DTOBandejaEntradasOficialiaID>{
	
	/**
	 * Método que inserta
	 * 
	 * @param lista: Lista que contiene los documentos a registrar en la bandeja de entrada de oficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void insertar(DTOBandejaEntradasOficialiaEntity dtoBandejaOficialia);
	
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
	
	public List<DTOBandejaEntradasOficialiaEntity> buscarDocumentosDisponiblesLazy(
			DTOBandejaOficialiaHelper bandeja, int indicePrimerElemento, int tamanioMaxPagina, 
			String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna);

	public Integer getTotalDocumentosLazy(DTOBandejaOficialiaHelper bandeja);

	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(
			Integer idOficialia, Integer idArea);
	
	/**
	 * Método que consulta en la bd los documentos en la bandeja por idOficialia y idArea
	 * 
	 * @return List<DTOBandejaEntradasOficialiaEntity>: Lista de documentos en la bandeja de entrada de oficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 26/11/2017
	 */
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialia(
			Integer idOficialia, Integer idArea);
	
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialiaNombre(Integer idOficialia, Integer idArea);
	
	/**
	 * Método que consulta a la tabla de BANDEJA_ENTRADAS_OFICIALIAS para activar la bandera en_clasificacion e indicar en el campo
	 * id_persona _clasificando el id de la persona quien está clasificando el documento
	 * 
	 * @param idDocumento: id del documento que se clasificará
	 * @param idPersona: id de la persona quién desea clasificar el documento
	 * @param idArea: id del área que clasificará el documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/12/2017
	 */
	public void clasificarDocumento(Integer idDocumento, Integer idPersona, Integer idArea);
	
	/**
	 * Método que consulta a la tabla de BANDEJA_ENTRADAS_OFICIALIAS para desactivar la bandera en_clasificacion y poner null
	 * al campo id_oficialia_clasificando y al campo fecha_clasificacion indicando que la clasificación del documento  ha sido
	 * cancelada y estará disponible para que alguien más lo pueda clasificar
	 * 
	 * @param idDocumento: id del documento a cancelar
	 * @param idArea: id del área
	 * @param tipoArea: tipo del área
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/12/2017
	 */
	public void cancelarClasificarDocumento(Integer idDocumento, Integer idArea, Integer tipoArea);
	
	/**
	 * Método que consulta en la tabla si el documento está siendo clasificado 
	 * 
	 * @param idDocumento: id del documento por consultar si está siendo clasificando
	 * @param idArea: id del área por consultar
	 * 
	 * @return Integer indicando si el documento se está clasificando, 1 - Si, null - No
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/12/2017
	 */
	public Integer consultarDocumentoClasificando(Integer idDocumento, Integer idArea);
	
	/**
	 * Método que elimina el documento de la bandeja de entrada del area para todas las oficialias que estan registradas en ella
	 * 
	 * @param dtoBandeja: Objeto que contiene el documento que será eliminado
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void eliminarDocumentoPorArea(DTOBandejaEntradasOficialiaEntity dtoBandeja);

	/**
	 * Método que cambia a 0 el campo NO_LEIDO para indicar que el documento ha sido leído
	 * 
	 * @param idDocumento: id del documento del cual indicará que ya fue leído
	 * @param idArea: id del área del cual indicará que ya fue leído
	 * @param tipoArea: tipo del área del cual indicará que ya fue leído
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/12/2017
	 */
	public void marcarComoLeido(Integer idDocumento, Integer idArea, Integer tipoArea);	
	
	/**
	 * Método que consulta en la tabla si hay documento que se hayan dejado clasificando pendientes
	 * 
	 * @param dtoBandeja: Objeto que contiene los datos a consultar
	 * @param idOficialia: id de la oficialia
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity Objeto
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity consultarDocumentoPendiente(DTOBandejaEntradasOficialiaEntity dtoBandeja, Integer idOficialia);
	
	/**
	 * Método que consulta si el documento ha sido leido
	 * 
	 * @param idDocumento: id del documento del cual indicará que ya fue leído
	 * @param idArea: id del área del cual indicará que ya fue leído
	 * @param tipoArea: tipo del área del cual indicará que ya fue leído
	 * 
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public Integer consultarLeido(Integer idDocumento, Integer idArea, Integer tipoArea);	
	
	/**
	 * Método que consulta si hay un folio pendiente
	 * 
	 * @param idOficialia: Contiene el id de oficialia
	 * 
	 * @return DTOBandejaEntradasOficialiaEntity Objeto que contiene el folio pendiente
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity consultarFoliosPendientes(Integer idOficialia);

}
