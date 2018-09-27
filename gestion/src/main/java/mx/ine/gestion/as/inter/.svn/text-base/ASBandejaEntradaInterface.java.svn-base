/**
 * @(#)ASBandejaEntrada.java 28/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface ASBandejaEntradaInterface {

	/**
	 * Método que obtiene los registros de la tabla "BANDEJA_E_RECIBIDOS" que
	 * coinciden con los fitros.
	 * 
	 * @param filtroRecibidoHelper
	 * @param indicePrimerElemento
	 * @param tamanioMaxPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosPorColumna
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public List<DTOBandejaERecibidosEntity> obtenerListaRecibidosLazy(DTOBandejaERecibidosHelper filtroRecibidoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna)
		throws Exception;

	/**
	 * Método que obtiene el número total de registros que coinciden con el
	 * filtro recibido
	 * 
	 * @param filtroRecibidoHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public int obtenerTotalDeRegistrosRecibidos(DTOBandejaERecibidosHelper filtroRecibidoHelper) throws Exception;
	
	/**
	 * 
	 * @param filtroCCPHelper
	 * @param indicePrimerElemento
	 * @param tamanioMaxPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosPorColumna
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public List<DTOBandejaECCPEntity> obtenerListaCCPLazy(
			DTOBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna)throws Exception;
	/**
	 * 
	 * @param filtroCCPHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public Integer obtenerTotalDeRegistrosCCP(DTOBandejaECCPHelper filtroCCPHelper) throws Exception;
	

	/**
	 * Método que obtiene los registros de la tabla "BANDEJA_E_RECIBIDOS" que
	 * coinciden con los fitros.
	 * 
	 * @param filtroRecibidoHelper
	 * @param indicePrimerElemento
	 * @param tamanioMaxPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosPorColumna
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public List<DTOBandejaEAtencionEntity> obtenerListaAtencionLazy(DTOBandejaEAtencionHelper filtroAtencionHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) throws Exception;

	/**
	 * Método que obtiene el número total de registros que coinciden con el
	 * filtro recibido
	 * 
	 * @param filtroRecibidoHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public int obtenerTotalDeRegistrosAtencion( DTOBandejaEAtencionHelper filtroAtencionHelper)throws Exception; 

	/**
	 * 
	 * @param filtroInfoHelper
	 * @param indicePrimerElemento
	 * @param tamanioMaxPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosPorColumna
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public List<DTOBandejaEInfoEntity> obtenerListaInfoLazy( DTOBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna)throws Exception;
	
	/**
	 * 
	 * @param filtroInfoHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public Integer obtenerTotalDeRegistrosInfo(DTOBandejaEInfoHelper filtroInfoHelper) throws Exception;
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * recibido y una persona.
	 * 
	 * @param persona
	 * @param recibido
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido , boolean activar) throws Exception;
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * CCP y una persona.
	 * 
	 * @param persona
	 * @param ccp
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOBandejaECCPEntity ccp, boolean activar)throws Exception;
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * recibido y una persona.
	 * 
	 * @param persona
	 * @param recibido
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaAtencion(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, boolean activar)throws Exception;
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * CCP y una persona.
	 * 
	 * @param persona
	 * @param ccp
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaInfo(DTOEstructuraAreasEntity persona, DTOBandejaEInfoEntity info, boolean activar) throws Exception;
	
	/**
	 * Método que registra en la BD la atención hacía un documento, actualizando
	 * el "ID_ESTATUS_TURNO" de la tabla "HIST_DOC_TURNO"
	 * 
	 * @param recibido: DTO que permite obteber el id del documento a actualizar
	 * @param persona: DTO que permite obtener el id de la persona que tiene asociado ese documento
	 * @param estatus: El estatus que se guardará en la BD
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/02/2018
	 */
	public void registrarAtencion(DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception;
	
	/**
	 * Método que registra en la BD la atención hacía un documento, actualizando
	 * el "ID_ESTATUS_TURNO" de la tabla "HIST_DOC_TURNO"
	 * 
	 * @param recibido: DTO que permite obteber el id del documento a actualizar
	 * @param persona: DTO que permite obtener el id de la persona que tiene asociado ese documento
	 * @param estatus: El estatus que se guardará en la BD
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/02/2018
	 */
	public void registrarAtencion(DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception;
	
	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento)throws Exception;
	
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) throws Exception;
	
	/**
	 * Método que guarda una respuesta de un documento turnado
	 * 
	 * @param persona
	 * @param documento
	 * @param comentario
	 * @param tipoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) throws Exception;
	
	/**
	 * Método que guarda una respuesta de un documento turnado
	 * 
	 * @param persona
	 * @param documento
	 * @param comentario
	 * @param tipoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception;
	
	/**
	 * Método que reasigna un documento de la bandeja de recibidos hacía otra área
	 * 
	 * @param listaTitulares
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception;
	
	/**
	 * Método que reasigna un documento de la bandeja de atención hacía otra área
	 * 
	 * @param listaTitulares
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) throws Exception;

	/**
	 * @param recibidoSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaERecibidosEntity recibidoSeleccionado) throws Exception;

	/**
	 * @param atencionSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEAtencionEntity atencionSeleccionado)throws Exception;

	/**
	 * @param infoSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEInfoEntity infoSeleccionado)throws Exception;
	
}