/**
 * @(#)ASBandejaHistorico.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASBandejaHistoricoInterface;
import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaEntradaInterface;
import mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaECCPInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEInfoInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOInstruccionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAORespuestasTurnadosInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTORespuestaTurnado;
import mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;

import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("asBandejaHistorico")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASBandejaHistorico implements ASBandejaHistoricoInterface{

	@Autowired
	@Qualifier("daoHBandejaEAtecion")
	private DAOHBandejaEAtencionInterface daoHBandejaEAtencionInterface;

	@Autowired
	@Qualifier("daoHBandejaECCP")
	private DAOHBandejaECCPInterface daoHBandejaECCPInterface;

	@Autowired
	@Qualifier("daoHBandejaEInfo")
	private DAOHBandejaEInfoInterface daoHBandejaEInfoInterface;

	@Autowired
	@Qualifier("daoHBandejaERecibidos")
	private DAOHBandejaERecibidosInterface daoHBandejaERecibidosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_TURNO"
	 */
	@Autowired
	@Qualifier("daoHistDocTurno")
	private DAOHistDocTurnoInterface daoHistDocTurnoInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_RECEP"
	 */
	@Autowired
	@Qualifier("daoHistDocRecep")
	private DAOHistDocRecepInterface daoHistDocRecepInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "HIST_DOC_TURNO"
	 */
	@Autowired
	@Qualifier("daoComentariosDocumento")
	private DAOComentariosDocumentoInterface daoComentariosDocumentoInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "RESPUESTAS_TURNADOS"
	 */
	@Autowired
	@Qualifier("daoRespuestasTurnados")
	private DAORespuestasTurnadosInterface daoRespuestasTurnadosInterface;
	
	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "NOTIFICACIONES"
	 */
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
	@Autowired
	@Qualifier("daoInstrucciones")
	private DAOInstruccionesInterface daoInstruccionesInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones de negocio de la bandeja de entrada
	 */
	@Autowired
	@Qualifier("boBandejaEntrada")
	private BOBandejaEntradaInterface boBandejaEntradaInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones de negocio de la bandeja de entrada
	 */
	@Autowired
	@Qualifier("boBandejaHistorico")
	private BOBandejaHistoricoInterface boBandejaHistoricoInterface;
	
	@Autowired
	@Qualifier("boBandejaBorradores")
	private BOBandejaBorradoresInterface boBandejaBorradoresInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerListaRecibidosLazy(mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOHBandejaERecibidosHelper filtroHRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return daoHBandejaERecibidosInterface.obtenerListaRecibidosLazy(filtroHRecibidoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerTotalDeRegistros(mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper)
	 */
	@Override
	public int obtenerTotalDeRegistrosRecibidos(
			DTOHBandejaERecibidosHelper filtroHRecibidoHelper) {
		return daoHBandejaERecibidosInterface.consultarNumeroHRecibidos(filtroHRecibidoHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerListaDeDatos(mx.ine.gestion.dto.helper.DTOHBandejaECCPHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaECCPEntity> obtenerListaCCPLazy(
			DTOHBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return daoHBandejaECCPInterface.obtenerListaCCPLazy(filtroCCPHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerTotalDeRegistros(mx.ine.gestion.dto.helper.DTOBandejaECCPHelper)
	 */
	@Override
	public Integer obtenerTotalDeRegistrosCCP(DTOHBandejaECCPHelper filtroCCPHelper) {
		return daoHBandejaECCPInterface.consultarNumeroCCP(filtroCCPHelper);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerListaAtencionLazy(mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaEAtencionEntity> obtenerListaAtencionLazy(
			DTOHBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return daoHBandejaEAtencionInterface.obtenerListaAtencionLazy(filtroAtencionHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerTotalDeRegistrosAtencion(mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper)
	 */
	@Override
	public int obtenerTotalDeRegistrosAtencion(
			DTOHBandejaEAtencionHelper filtroAtencionHelper) {
		return daoHBandejaEAtencionInterface.consultarNumeroAtencion(filtroAtencionHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerListaInfoLazy(mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaEInfoEntity> obtenerListaInfoLazy(
			DTOHBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return daoHBandejaEInfoInterface.obtenerListaInfoLazy(filtroInfoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerTotalDeRegistrosInfo(mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper)
	 */
	@Override
	public Integer obtenerTotalDeRegistrosInfo(
			DTOHBandejaEInfoHelper filtroInfoHelper) {
		return daoHBandejaEInfoInterface.consultarNumeroInfo(filtroInfoHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#notificacionBandejaRecibidos(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, boolean)
	 */
	@Override
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona,
			DTOHBandejaERecibidosEntity recibido, boolean activar) {
		// TODO Auto-generated method stub
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#notificacionBandejaCCP(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaECCPEntity, boolean)
	 */
	@Override
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOHBandejaECCPEntity ccp, boolean activar) {
		// TODO Auto-generated method stub
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void registrarAtencion(DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) {
		
		DTOHistDocTurnoEntity histTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(recibido.getDocumento(), persona);
		histTurno.setIdEstatusTurno(estatus);
		daoHistDocTurnoInterface.modificar(histTurno);
		
		recibido.setEnAtencion(estatus);
		daoHBandejaERecibidosInterface.modificar(recibido);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void registrarAtencion(DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) {
	
		DTOHistDocTurnoEntity histTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), persona);
		histTurno.setIdEstatusTurno(estatus);
		daoHistDocTurnoInterface.modificar(histTurno);

		atencion.setEnAtencion(estatus);
		daoHBandejaERecibidosInterface.modificar(atencion);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenrHistoricoTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento) {
		
		List<DTOHistDocRecepEntity> listaPersonasTurnaron = daoHistDocRecepInterface.obtenerListaHistoricoRecepcion(documento, true);
		List<DTOHistDocRecepEntity> listaPersonasTurnaronUnica = daoHistDocRecepInterface.obtenerListaPersonasUnicasTurnaron(documento);
		listaPersonasTurnaron = boBandejaEntradaInterface.revisarRepetidosLista(listaPersonasTurnaron, listaPersonasTurnaronUnica);
		List<DTOHistDocRecepEntity> listaPersonasTurnados = daoHistDocRecepInterface.obtenerListaHistoricoRecepcion(documento, false);
		
		return boBandejaEntradaInterface.crearArbolHistoricoTurnado(listaPersonasTurnaron, listaPersonasTurnados);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#actualizarDocumentoAEnterado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) {
		
		DTOHistDocTurnoEntity auxHistTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(documento, persona);
		auxHistTurno.setIdEstatusTurno(Integer.parseInt( Utilidades.mensajeProperties("id_estatus_enterado")));
		daoHistDocTurnoInterface.guardar(auxHistTurno);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) 
			throws Exception {
		
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTORespuestaTurnado respuestaTurnado = new DTORespuestaTurnado();
		DTOHistDocTurnoEntity histDocTurno = null;
		Integer idComentario = null;
		
		respuestaTurnado.setIdDocumento(recibido.getIdDocumento());
		respuestaTurnado.setIdHistoricoRecepcion(recibido.getIdHistoricoRecep());
		respuestaTurnado.setAnio(recibido.getAnio());
		
		if(filtroResponder.getComentario() != null && !filtroResponder.getComentario().trim().equals("")){	

			comentarioTurnado = new DTOComentariosDocumentoEntity(); 
			comentarioTurnado.setIdDocumento(recibido.getIdDocumento());
			comentarioTurnado.setAnio(recibido.getAnio());
			comentarioTurnado.setComentarios(filtroResponder.getComentario());
			comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
			comentarioTurnado.setUsuarioComento(persona.getUsuario());
			comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
			
			idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado, persona);
			
			respuestaTurnado.setIdComentario(idComentario);
		}
		
		if(filtroResponder.getNombreAdjuntoTemporal() != null){
			boBandejaHistoricoInterface.guardarAdjuntoGlusterPrincipal(filtroResponder, recibido);
			respuestaTurnado.setNombreAdjunto(filtroResponder.getNombreAdjuntoGluster());
			respuestaTurnado.setNombreAdjuntoOriginal(filtroResponder.getNombreAdjuntoOriginal());
		}
		
		daoRespuestasTurnadosInterface.guardar(respuestaTurnado);
		
		
		//Se actualiza el historico
		recibido.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
		recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
		daoHBandejaERecibidosInterface.modificar(recibido);
		
		//Se actualiza el estatus en el historico
		histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(recibido.getDocumento(), persona);
		histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
		daoHistDocTurnoInterface.modificar(histDocTurno);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity, mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception {
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTORespuestaTurnado respuestaTurnado = new DTORespuestaTurnado();
		DTOHistDocTurnoEntity histDocTurno = null;
		Integer idComentario = null;
		
		respuestaTurnado.setIdDocumento(atencion.getIdDocumento());
		respuestaTurnado.setIdHistoricoRecepcion(atencion.getIdHistoricoRecep());
		respuestaTurnado.setAnio(atencion.getAnio());
		
		if(filtroResponder.getComentario() != null && !filtroResponder.getComentario().trim().equals("")){	

			comentarioTurnado = new DTOComentariosDocumentoEntity(); 
			comentarioTurnado.setIdDocumento(atencion.getIdDocumento());
			comentarioTurnado.setAnio(atencion.getAnio());
			comentarioTurnado.setComentarios(filtroResponder.getComentario());
			comentarioTurnado.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
			comentarioTurnado.setUsuarioComento(persona.getUsuario());
			comentarioTurnado.setTipoComentario(Utilidades.mensajeProperties("tipo_comentario_turnado").charAt(0));
			
			idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado,persona);
			
			respuestaTurnado.setIdComentario(idComentario);
		}
		
		if(filtroResponder.getNombreAdjuntoTemporal() != null){
			boBandejaHistoricoInterface.guardarAdjuntoGlusterPrincipal(filtroResponder, atencion);
			respuestaTurnado.setNombreAdjunto(filtroResponder.getNombreAdjuntoGluster());
			respuestaTurnado.setNombreAdjuntoOriginal(filtroResponder.getNombreAdjuntoOriginal());
		}

		
		daoRespuestasTurnadosInterface.guardar(respuestaTurnado);
		
		//Se actualiza la atención del documento
		atencion.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
		atencion.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
		daoHBandejaEAtencionInterface.modificar(atencion);
		
		//Se actualiza el estatus en el historico
		histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), persona);
		histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
		daoHistDocTurnoInterface.modificar(histDocTurno);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception {
		//Objeto necesario para insertar en el historico de recepción
				DTOHistDocRecepEntity dtoHistRecep;
				DTOBandejaERecibidosEntity nuevoRecibido;
				Integer idHistoricoRecep;
				//Objeto necesario para insertar en el historico de Turno
				DTOHistDocTurnoEntity dtoHistTurno;
				//Insertar en la tabla de recibidos
				if(listaTitulares != null && recibido != null && usuario != null){
					for (DTOEstructuraAreasEntity titular : listaTitulares) {
						//Se insera en el historico de recepción
						dtoHistRecep = boBandejaSeguimientoInterface.crearHistoricoRecep(titular, recibido.getDocumento(), null, Integer.parseInt(Utilidades.mensajeProperties("estatus_asignado_a")));
						idHistoricoRecep = daoHistDocRecepInterface.guardar(dtoHistRecep);
						
						//Se inserta el documento en la Bandeja de Recibidos
						nuevoRecibido = boBandejaBorradoresInterface.crearDocumentoRecibido(titular, recibido.getDocumento(), idHistoricoRecep);
						//DAOGenericGestionInterface<DTOBandejaERecibidosEntity, DTOBandejaERecibidosID> daoBandejaERecibidoInterface;
						daoBandejaERecibidosInterface.guardar(nuevoRecibido);
						notificarEntrada(titular, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
						
						//Se inserta en el historico de turno
						dtoHistTurno = boBandejaSeguimientoInterface.crearHistoricoTurno(titular, recibido.getDocumento(), idHistoricoRecep, Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")) , null);
						daoHistDocTurnoInterface.guardar(dtoHistTurno);
					}
				}
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) 
			throws Exception {
		//Objeto necesario para insertar en el historico de recepción
		DTOHistDocRecepEntity dtoHistRecep;
		DTOBandejaERecibidosEntity nuevoRecibido;
		Integer idHistoricoRecep;
		//Objeto necesario para insertar en el historico de Turno
		DTOHistDocTurnoEntity dtoHistTurno;
		//Insertar en la tabla de recibidos
		if(listaTitulares != null && atencion != null && usuario != null){
			for (DTOEstructuraAreasEntity titular : listaTitulares) {
				//Se insera en el historico de recepción
				dtoHistRecep = boBandejaSeguimientoInterface.crearHistoricoRecep(titular, atencion.getDocumento(), null, Integer.parseInt(Utilidades.mensajeProperties("estatus_asignado_a")));
				idHistoricoRecep = daoHistDocRecepInterface.guardar(dtoHistRecep);
				
				//Se inserta el documento en la Bandeja de Recibidos
				nuevoRecibido = boBandejaBorradoresInterface.crearDocumentoRecibido(titular, atencion.getDocumento(), idHistoricoRecep);
				daoBandejaERecibidosInterface.guardar(nuevoRecibido);
				notificarEntrada(titular, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
				
				//Se inserta en el historico de turno
				dtoHistTurno = boBandejaSeguimientoInterface.crearHistoricoTurno(titular, atencion.getDocumento(), idHistoricoRecep, Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")) , null);
				daoHistDocTurnoInterface.guardar(dtoHistTurno);
			}
		}
	}

	/**
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @param accion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/02/2018
	 */
	private void notificarEntrada(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, String accion) {
		
		DTONotificacionesEntity notificacion;
		
			notificacion = new DTONotificacionesEntity();
			
			notificacion.setIdPersona(dtoEstructuraAreasEntity.getIdPersona());
			notificacion.setIdModulo(Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
			notificacion.setIdApartado(Integer.parseInt(Utilidades.mensajeProperties("id_apartado_bandeja_entrada")));
			daoNotificacionesInterface.insertarNotificacion(notificacion, accion);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEInfoEntity infoSeleccionado) {
		return daoInstruccionesInterface.obtenerInstrucciones(infoSeleccionado.getIdHistoricoRecep());
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEAtencionEntity atencionSeleccionado) {
		return daoInstruccionesInterface.obtenerInstrucciones(atencionSeleccionado.getIdHistoricoRecep());
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaHistoricoInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaERecibidosEntity recibidoSeleccionado) {
		return daoInstruccionesInterface.obtenerInstrucciones(recibidoSeleccionado.getIdHistoricoRecep());
	}
	
}