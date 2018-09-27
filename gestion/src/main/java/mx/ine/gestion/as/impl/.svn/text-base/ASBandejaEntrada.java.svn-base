/**
 * @(#)ASBandejaEntrada.java 28/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASBandejaEntradaInterface;
import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaEntradaInterface;
import mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOBandejaECCPInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEInfoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOInstruccionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAORespuestasTurnadosInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTORespuestaTurnado;
import mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;
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
@Component("asBandejaEntrada")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASBandejaEntrada implements ASBandejaEntradaInterface {

	@Autowired
	@Qualifier("daoBandejaEAtecion")
	private DAOBandejaEAtencionInterface daoBandejaEAtencionInterface;
	
	@Autowired
	@Qualifier("daoHBandejaEAtecion")
	private DAOHBandejaEAtencionInterface daoHBandejaEAtencionInterface;

	@Autowired
	@Qualifier("daoBandejaECCP")
	private DAOBandejaECCPInterface daoBandejaECCPInterface;

	@Autowired
	@Qualifier("daoBandejaEInfo")
	private DAOBandejaEInfoInterface daoBandejaEInfoInterface;

	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;
	
	@Autowired
	@Qualifier("daoHBandejaERecibidos")
	private DAOHBandejaERecibidosInterface daoHBandejaERecibidosInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "NOTIFICACIONES"
	 */
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;
	
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
	@Qualifier("daoHistDocTurno")
	private DAOHistDocTurnoInterface daoHistDocTurnoInterface;
	
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
	@Qualifier("daoInstrucciones")
	private DAOInstruccionesInterface daoInstruccionesInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones de negocio de la bandeja de entrada
	 */
	@Autowired
	@Qualifier("boBandejaEntrada")
	private BOBandejaEntradaInterface boBandejaEntradaInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	@Autowired
	@Qualifier("boBandejaBorradores")
	private BOBandejaBorradoresInterface boBandejaBorradoresInterface;
	
	/**
	 * Interface que sirve para realizar diferentes operaciones de negocio de la bandeja de entrada
	 */
	@Autowired
	@Qualifier("boBandejaHistorico")
	private BOBandejaHistoricoInterface boBandejaHistoricoInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerListaRecibidosLazy(mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaERecibidosEntity> obtenerListaRecibidosLazy(DTOBandejaERecibidosHelper filtroRecibidoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) throws Exception{
		return daoBandejaERecibidosInterface.obtenerListaRecibidosLazy(filtroRecibidoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerTotalDeRegistros(mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper)
	 */
	@Override
	public int obtenerTotalDeRegistrosRecibidos(DTOBandejaERecibidosHelper filtroRecibidoHelper) throws Exception{
		return daoBandejaERecibidosInterface.consultarNumeroRecibidos(filtroRecibidoHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerListaDeDatos(mx.ine.gestion.dto.helper.DTOBandejaECCPHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaECCPEntity> obtenerListaCCPLazy(DTOBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) throws Exception{
		return daoBandejaECCPInterface.obtenerListaCCPLazy(filtroCCPHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerTotalDeRegistros(mx.ine.gestion.dto.helper.DTOBandejaECCPHelper)
	 */
	@Override
	public Integer obtenerTotalDeRegistrosCCP(DTOBandejaECCPHelper filtroCCPHelper) throws Exception {
		return daoBandejaECCPInterface.consultarNumeroCCP(filtroCCPHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerListaAtencionLazy(mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaEAtencionEntity> obtenerListaAtencionLazy(DTOBandejaEAtencionHelper filtroAtencionHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) throws Exception {
		return daoBandejaEAtencionInterface.obtenerListaAtencionLazy(filtroAtencionHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerTotalDeRegistrosAtencion(mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper)
	 */
	@Override
	public int obtenerTotalDeRegistrosAtencion(DTOBandejaEAtencionHelper filtroAtencionHelper) throws Exception{
		return daoBandejaEAtencionInterface.consultarNumeroAtencion(filtroAtencionHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerListaInfoLazy(mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaEInfoEntity> obtenerListaInfoLazy( DTOBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) throws Exception{
		return daoBandejaEInfoInterface.obtenerListaInfoLazy(filtroInfoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerTotalDeRegistrosInfo(mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper)
	 */
	@Override
	public Integer obtenerTotalDeRegistrosInfo(DTOBandejaEInfoHelper filtroInfoHelper) throws Exception{
		return daoBandejaEInfoInterface.consultarNumeroInfo(filtroInfoHelper);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido, boolean activar) throws Exception{
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		
		DTOHistDocRecepEntity histDocRecep = daoHistDocRecepInterface.obtenerHistoricoRecepcion(recibido.getDocumento(), persona, Integer.parseInt( Utilidades.mensajeProperties("estatus_asignado_a") ));
		DTOHistDocTurnoEntity histDocTurnoEntity = daoHistDocTurnoInterface.obtenerHistoricoTurnado(histDocRecep.getIdHistoricoRecep());
		histDocTurnoEntity.setIdEstatusTurno(Integer.parseInt( Utilidades.mensajeProperties("id_estatus_enterado")) );
		daoHistDocRecepInterface.modificar(histDocTurnoEntity);
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_entrada").equals(""+miNotificacion.getIdApartado())){
			numNotificacion = miNotificacion.getDocumentosPendientes();
			if (activar) {
				miNotificacion.setDocumentosPendientes(++numNotificacion);
				recibido.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes(--numNotificacion);
				recibido.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}else{
				miNotificacion.setDocumentosPendientes(0);
				recibido.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoBandejaERecibidosInterface.modificar(recibido);
			
		}else{
			//log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#notificacionBandejaRecibidos(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaECCPEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOBandejaECCPEntity ccp, boolean activar) throws Exception{
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_entrada").equals(""+miNotificacion.getIdApartado())){
			numNotificacion = miNotificacion.getDocumentosPendientes();
			if (activar) {
				miNotificacion.setDocumentosPendientes(++numNotificacion);
				ccp.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes(--numNotificacion);
				ccp.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}else{
				miNotificacion.setDocumentosPendientes(0);
				ccp.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoBandejaECCPInterface.modificar(ccp);
			
		}else{
			//log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#notificacionBandejaAtencion(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaAtencion(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, boolean activar) throws Exception{
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		DTOHistDocTurnoEntity histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), persona);
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_entrada").equals(""+miNotificacion.getIdApartado())){
			numNotificacion = miNotificacion.getDocumentosPendientes();
			if (activar) {
				miNotificacion.setDocumentosPendientes(++numNotificacion);
				atencion.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes(--numNotificacion);
				atencion.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
				
				//Se cambia el estatus a Enterado
				histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enterado")));
			}else{
				miNotificacion.setDocumentosPendientes(0);
				atencion.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
				
				//Se cambia el estatus a Enterado
				histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enterado")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoBandejaEAtencionInterface.modificar(atencion);
			daoHistDocTurnoInterface.modificar(histDocTurno);
			
		}else{
			//log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#notificacionBandejaInfo(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaEInfoEntity, boolean)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void notificacionBandejaInfo(DTOEstructuraAreasEntity persona, DTOBandejaEInfoEntity info, boolean activar) throws Exception{
		int numNotificacion;
		DTONotificacionesEntity miNotificacion = 
		daoNotificacionesInterface.consultarNotificacion(persona.getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja")));
		DTOHistDocTurnoEntity histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(info.getDocumento(), persona);
		
		if(Utilidades.mensajeProperties("id_apartado_bandeja_entrada").equals(""+miNotificacion.getIdApartado())){
			numNotificacion = miNotificacion.getDocumentosPendientes();
			if (activar) {
				miNotificacion.setDocumentosPendientes(++numNotificacion);
				info.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")));
			} else if (!activar && numNotificacion > 0) {
				miNotificacion.setDocumentosPendientes(--numNotificacion);
				info.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
				
				//Se cambia el estatus a Enterado
				histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enterado")));
			}else{
				miNotificacion.setDocumentosPendientes(0);
				info.setNoLeido(Integer.parseInt( Utilidades.mensajeProperties("constante_documento_leido")));
				
				//Se cambia el estatus a Enterado
				histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enterado")));
			}
			daoNotificacionesInterface.modificar(miNotificacion);
			daoBandejaEInfoInterface.modificar(info);
			daoHistDocTurnoInterface.modificar(histDocTurno);
			
		}else{
			//log.error("La persona con ID: " + persona.getIdPersona() + "no tiene correcto su registro en la tabla notificaciones del ID_APARTADO3, ya que debería ser "+Utilidades.mensajeProperties("id_apartado_bandeja_borradores"));
		}
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#registrarAtencion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void registrarAtencion(DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception{
		DTOHBandejaERecibidosEntity historico = null;
		DTOHistDocTurnoEntity histTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(recibido.getDocumento(), persona);
		histTurno.setIdEstatusTurno(estatus);
		daoHistDocTurnoInterface.modificar(histTurno);
		
		recibido.setEnAtencion(estatus);
		historico = boBandejaHistoricoInterface.crearHRecibido(recibido);
		daoHBandejaERecibidosInterface.guardar(historico);
		daoBandejaERecibidosInterface.eliminar(recibido);
			
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void registrarAtencion(DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception{
		DTOHBandejaEAtencionEntity historico = null;
		DTOHistDocTurnoEntity histTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), persona);
		histTurno.setIdEstatusTurno(estatus);
		daoHistDocTurnoInterface.modificar(histTurno);

		atencion.setEnAtencion(estatus);
			
		historico = boBandejaHistoricoInterface.crearHAtencion(atencion);
		daoHBandejaERecibidosInterface.guardar(historico);
		daoBandejaERecibidosInterface.eliminar(atencion);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenrHistoricoTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento) throws Exception {
		List<DTOHistDocRecepEntity> listaPersonasTurnaron = daoHistDocRecepInterface.obtenerListaHistoricoRecepcion(documento, true);
		List<DTOHistDocRecepEntity> listaPersonasTurnaronUnica = daoHistDocRecepInterface.obtenerListaPersonasUnicasTurnaron(documento);
		listaPersonasTurnaron = boBandejaEntradaInterface.revisarRepetidosLista(listaPersonasTurnaron, listaPersonasTurnaronUnica);
		List<DTOHistDocRecepEntity> listaPersonasTurnados = daoHistDocRecepInterface.obtenerListaHistoricoRecepcion(documento, false);
		
		return boBandejaEntradaInterface.crearArbolHistoricoTurnado(listaPersonasTurnaron, listaPersonasTurnados);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#actualizarDocumentoAEnterado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) throws Exception{
		DTOHistDocTurnoEntity auxHistTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(documento, persona);
		auxHistTurno.setIdEstatusTurno(Integer.parseInt( Utilidades.mensajeProperties("id_estatus_enterado")));
		daoHistDocTurnoInterface.guardar(auxHistTurno);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) throws Exception{
		
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTORespuestaTurnado respuestaTurnado = new DTORespuestaTurnado();
		DTOHBandejaERecibidosEntity historico = null;
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
			respuestaTurnado.setNombreAdjunto(filtroResponder.getNombreAdjuntoTemporal());
			respuestaTurnado.setNombreAdjuntoOriginal(filtroResponder.getNombreAdjuntoOriginal());
			boBandejaEntradaInterface.guardarAdjuntoGlusterPrincipal(filtroResponder, recibido);
		}
		
		daoRespuestasTurnadosInterface.guardar(respuestaTurnado);
		
		
		//Se borra de la bandeja de entrada y se pasa al historico
		recibido.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
		recibido.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
		
		historico = boBandejaHistoricoInterface.crearHRecibido(recibido);
		daoHBandejaERecibidosInterface.guardar(historico);
		daoBandejaERecibidosInterface.eliminar(recibido);
		
		//Se actualiza el estatus en el historico
		histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(recibido.getDocumento(), persona);
		histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
		daoHistDocTurnoInterface.modificar(histDocTurno);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception {
		
		DTOComentariosDocumentoEntity comentarioTurnado = null;
		DTORespuestaTurnado respuestaTurnado = new DTORespuestaTurnado();
		DTOHBandejaEAtencionEntity historico = null;
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
			
			idComentario = daoComentariosDocumentoInterface.guardarComentarioTurnado(comentarioTurnado, persona);
			
			respuestaTurnado.setIdComentario(idComentario);
		}
		
		if(filtroResponder.getNombreAdjuntoTemporal() != null){
			respuestaTurnado.setNombreAdjunto(filtroResponder.getNombreAdjuntoTemporal());
			respuestaTurnado.setNombreAdjuntoOriginal(filtroResponder.getNombreAdjuntoOriginal());
			boBandejaEntradaInterface.guardarAdjuntoGlusterPrincipal(filtroResponder, atencion);
		}

		
		daoRespuestasTurnadosInterface.guardar(respuestaTurnado);
		
		//Se borra de la bandeja de entrada y se pasa al historico
		atencion.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
		atencion.setTieneRespuesta(Integer.valueOf(Utilidades.mensajeProperties("estatus_con_respuesta")));
		
		historico = boBandejaHistoricoInterface.crearHAtencion(atencion);
		daoHBandejaEAtencionInterface.guardar(historico);
		daoBandejaEAtencionInterface.eliminar(atencion);
		
		//Se actualiza el estatus en el historico
		histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(atencion.getDocumento(), persona);
		histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
		daoHistDocTurnoInterface.modificar(histDocTurno);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception {
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
				daoBandejaERecibidosInterface.guardar(nuevoRecibido);
				notificarEntrada(titular, Utilidades.mensajeProperties("constante_notificacion_incrementar"));
				
				//Se inserta en el historico de turno
				dtoHistTurno = boBandejaSeguimientoInterface.crearHistoricoTurno(titular, recibido.getDocumento(), idHistoricoRecep, Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")) , null);
				daoHistDocTurnoInterface.guardar(dtoHistTurno);
			}
		}
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario)
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
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaERecibidosEntity recibidoSeleccionado) throws Exception{
		return daoInstruccionesInterface.obtenerInstrucciones(recibidoSeleccionado.getIdHistoricoRecep());
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEAtencionEntity atencionSeleccionado) throws Exception{
		return daoInstruccionesInterface.obtenerInstrucciones(atencionSeleccionado.getIdHistoricoRecep());
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEntradaInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOBandejaEInfoEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEInfoEntity infoSeleccionado) throws Exception{
		return daoInstruccionesInterface.obtenerInstrucciones(infoSeleccionado.getIdHistoricoRecep());
	}
	
}
