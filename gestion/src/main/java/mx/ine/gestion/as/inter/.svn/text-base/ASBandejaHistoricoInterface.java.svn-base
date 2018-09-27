/**
 * @(#)ASBandejaHistoricoInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface ASBandejaHistoricoInterface {

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
	public List<DTOHBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOHBandejaERecibidosHelper filtroRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);

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
	public int obtenerTotalDeRegistrosRecibidos(DTOHBandejaERecibidosHelper filtroHRecibidoHelper);
	
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
	public List<DTOHBandejaECCPEntity> obtenerListaCCPLazy(
			DTOHBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
	
	/**
	 * 
	 * @param filtroCCPHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public Integer obtenerTotalDeRegistrosCCP(DTOHBandejaECCPHelper filtroCCPHelper);
	
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
	public List<DTOHBandejaEAtencionEntity> obtenerListaAtencionLazy(
			DTOHBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);

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
	public int obtenerTotalDeRegistrosAtencion( DTOHBandejaEAtencionHelper filtroAtencionHelper); 

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
	public List<DTOHBandejaEInfoEntity> obtenerListaInfoLazy(
			DTOHBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
	
	/**
	 * 
	 * @param filtroInfoHelper
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/01/2018
	 */
	public Integer obtenerTotalDeRegistrosInfo(DTOHBandejaEInfoHelper filtroInfoHelper);
	
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
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona, DTOHBandejaERecibidosEntity recibido , boolean activar);
	
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
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOHBandejaECCPEntity ccp, boolean activar);
	
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
	public void registrarAtencion(DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario);
	
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
	public void registrarAtencion(DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario);
	
	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento);
	
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento);
	
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
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) throws Exception;
	
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
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception;
	
	/**
	 * Método que reasigna un documento de la bandeja de recibidos hacía otra área
	 * 
	 * @param listaTitulares
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception;
	
	/**
	 * Método que reasigna un documento de la bandeja de atención hacía otra área
	 * 
	 * @param listaTitulares
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) throws Exception;

	/**
	 * @param infoSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEInfoEntity infoSeleccionado);

	/**
	 * @param atencionSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEAtencionEntity atencionSeleccionado);

	/**
	 * @param recibidoSeleccionado
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaERecibidosEntity recibidoSeleccionado);
}
