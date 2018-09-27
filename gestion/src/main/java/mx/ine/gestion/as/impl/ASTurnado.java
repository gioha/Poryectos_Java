/**
 * @(#)ASTurnado.java 09/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASApartadoFoliosInterface;
import mx.ine.gestion.as.inter.ASTurnadoInterface;
import mx.ine.gestion.bo.impl.BOBandejaEntrada;
import mx.ine.gestion.bo.inter.BOBandejaEntradaInterface;
import mx.ine.gestion.bo.inter.BOBandejaHistoricoInterface;
import mx.ine.gestion.bo.inter.BOTurnadoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEInfoInterface;
import mx.ine.gestion.dao.inter.DAOBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaECCPInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaEInfoInterface;
import mx.ine.gestion.dao.inter.DAOHBandejaERecibidosInterface;
import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dao.inter.DAOInstruccionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAOTurnInstruccionInterface;
import mx.ine.gestion.dao.inter.DAOTurnadoInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
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
import mx.ine.gestion.dto.db.DTOTurnInstruccionEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sun.faces.util.Util;

/**
 * Clase que contiene las llamadas a los DAO´s que se utilizan en el módulo de Turnado
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 09/01/2018
 */
@Scope("prototype")
@Component("asTurnado")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASTurnado implements ASTurnadoInterface {

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(ASApartadoFoliosInterface.class);

	@Autowired
	@Qualifier("daoTurnado")
	private DAOTurnadoInterface daoTurnadoInterface;
	
	@Autowired
	@Qualifier("boTurnado")
	private BOTurnadoInterface boTurnadoInterface;
	
	@Autowired
	@Qualifier("boBandejaHistorico")
	private BOBandejaHistoricoInterface boBandejaHistoricoInterface;
	
	@Autowired
	@Qualifier("daoInstrucciones")
	private DAOInstruccionesInterface daoInstruccionesInterface;
	
	@Autowired
	@Qualifier("daoHistDocRecep")
	private DAOHistDocRecepInterface daoHistDocRecepInterface;

	@Autowired
	@Qualifier("daoHistDocTurno")
	private DAOHistDocTurnoInterface daoHistDocTurnoInterface;

	@Autowired
	@Qualifier("DAOTurnInstruccion")
	private DAOTurnInstruccionInterface daoTurnInstruccionInterface;

	@Autowired
	@Qualifier("daoBandejaEAtecion")
	private DAOBandejaEAtencionInterface daoBandejaEAtencionInterface;
	
	@Autowired
	@Qualifier("daoBandejaERecibidos")
	private DAOBandejaERecibidosInterface daoBandejaERecibidosInterface;

	@Autowired
	@Qualifier("daoBandejaEInfo")
	private DAOBandejaEInfoInterface daoBandejaEInfoInterface;

	@Autowired
	@Qualifier("daoHBandejaERecibidos")
	private DAOHBandejaERecibidosInterface daoHBandejaERecibidosInterface;

	@Autowired
	@Qualifier("daoHBandejaEAtecion")
	private DAOHBandejaEAtencionInterface daoHBandejaAtencionInterface;

	@Autowired
	@Qualifier("daoHBandejaEInfo")
	private DAOHBandejaEInfoInterface daoHBandejaInfoInterface;

	@Autowired
	@Qualifier("daoHBandejaECCP")
	private DAOHBandejaECCPInterface daoHBandejaCCPInterface;
	
	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#consultarPersonasTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonasTurnado(DTOEstructuraAreasEntity personaActual) throws Exception {
		return daoTurnadoInterface.consultarPersonasTurnado(personaActual);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#consultarInstruccionesAtencion(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> consultarInstruccionesAtencion(DTOEstructuraAreasEntity personaActual) throws Exception {
		return daoInstruccionesInterface.consultarInstruccciones(personaActual.getIdPersona(), Integer.valueOf(1));
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#consultarInstruccionesInformativas(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> consultarInstruccionesInformativas(DTOEstructuraAreasEntity personaActual) throws Exception{
		return daoInstruccionesInterface.consultarInstruccciones(personaActual.getIdPersona(), Integer.valueOf(2));
	}
	
//	@Override
//	@Transactional(readOnly=false, rollbackFor={Exception.class})
//	public void turnarDesdeRecibidos(List<DTOTurnadoHelper> listaTurnado, DTOBandejaERecibidosEntity documentoBandejaRecibidos,
//			DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception {
//		Integer idPersonaLogueada = personaLogueada.getIdPersona();
//		Integer idArea = personaLogueada.getIdArea();
//		Integer tipoArea = personaLogueada.getTipoArea();
//		Integer idDocumento = documentoBandejaRecibidos.getIdDocumento();
//
//		for (DTOTurnadoHelper personaTurnado : listaTurnado) {
//			if (personaTurnado.isEnviarATurnar()) {
//				DTOHistDocRecepEntity histDocRecepTurnadoPor = 
//						boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, null, idPersonaLogueada, Integer.valueOf(2));
//				daoHistDocRecepInterface.guardar(histDocRecepTurnadoPor);
//
//				DTOHistDocRecepEntity histDocRecepTurnadoA = 
//						boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, idPersonaLogueada, personaTurnado.getPersona().getIdPersona(), Integer.valueOf(3));
//
//				Integer idRecep = daoHistDocRecepInterface.guardar(histDocRecepTurnadoA);
//
//				DTOHistDocTurnoEntity histDocTurnoEntity = 
//						boTurnadoInterface.construirHistDocTurno(idArea, tipoArea, idDocumento, idRecep , personaTurnado.getPersona().getIdPersona(), Integer.valueOf(1), comentario);
//				daoHistDocTurnoInterface.guardar(histDocTurnoEntity);
//
//				for (Integer idInstruccion : personaTurnado.getIdsInstruccionesInsertar()) {
//					DTOTurnInstruccionEntity turnInstruccion = boTurnadoInterface.construirInstruccion(idDocumento, idRecep, idPersonaLogueada, idInstruccion);
//					daoTurnInstruccionInterface.guardar(turnInstruccion);
//				}
//				
//				if(personaTurnado.getTipoInstrucciones().equals("aten")) {
//					DTOBandejaEAtencionEntity bandejaEAtencion = 
//						boTurnadoInterface.construirBandejaEAtencion(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.valueOf(0), idRecep, idPersonaLogueada);
//					daoBandejaEAtencionInterface.guardar(bandejaEAtencion);
//				} else if(personaTurnado.getTipoInstrucciones().equals("info")) {
//					DTOBandejaEInfoEntity bandejaEInformativa = 
//						boTurnadoInterface.construirBandejaEInfo(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.valueOf(0), idRecep, idPersonaLogueada);
//					daoBandejaEAtencionInterface.guardar(bandejaEInformativa);					
//				}
//			}
//		}
//		DTOHBandejaERecibidosEntity hBandejaERecibidos = boTurnadoInterface.construirHBandejaERecibidos(idDocumento, idPersonaLogueada, idArea, tipoArea);
//		daoHBandejaERecibidosInterface.guardar(hBandejaERecibidos);
//
//		daoBandejaEAtencionInterface.eliminar(documentoBandejaRecibidos);
//	}

	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void turnar(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja, DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception {
		//Se inicializan las variables necesarias para realizar el proceso del turnado
		Integer idPersonaLogueada = personaLogueada.getIdPersona();
		Integer idArea = personaLogueada.getIdArea();
		Integer tipoArea = personaLogueada.getTipoArea();
		Integer idDocumento = null;
		DTODocumentoEntity documento;
		
		DTOBandejaERecibidosEntity bandejaRecibidos = null; 
		DTOBandejaEAtencionEntity bandejaAtencion = null;
		DTOBandejaECCPEntity bandejaCCP= null;
		DTOBandejaEInfoEntity bandejaInfo = null;
		
		//Se obtiene el idDocumento, depende del que apartado donde se turnó 
		if(documentoBandeja instanceof DTOBandejaERecibidosEntity) {
			
			bandejaRecibidos = (DTOBandejaERecibidosEntity)documentoBandeja;
			documento = bandejaRecibidos.getDocumento();
			idDocumento = bandejaRecibidos.getIdDocumento();
			
		} else if(documentoBandeja instanceof DTOBandejaEAtencionEntity) {
			
			bandejaAtencion = (DTOBandejaEAtencionEntity)documentoBandeja;
			documento = bandejaAtencion.getDocumento();
			idDocumento = bandejaAtencion.getIdDocumento();
			
		} else if(documentoBandeja instanceof DTOBandejaECCPEntity) {

			bandejaCCP = (DTOBandejaECCPEntity)documentoBandeja;
			documento = bandejaCCP.getDocumento();
			idDocumento = bandejaCCP.getIdDocumento();

		}else if(documentoBandeja instanceof DTOBandejaEInfoEntity) {

			bandejaInfo = (DTOBandejaEInfoEntity)documentoBandeja;
			documento = bandejaInfo.getDocumento();
			idDocumento = bandejaInfo.getIdDocumento();

		}

		for (DTOTurnadoHelper personaTurnado : listaTurnado) {
			if (personaTurnado.isEnviarATurnar()) {
				//Se guardan los registros en la tabla HistDocRecep
				DTOHistDocRecepEntity histDocRecepTurnadoA;
				DTOHistDocRecepEntity histDocRecepTurnadoPor;
				DTOHistDocTurnoEntity histDocTurnoEntity;
				Integer idRecep;
				
				if(documentoBandeja instanceof DTOBandejaERecibidosEntity) {
					histDocRecepTurnadoPor = daoHistDocRecepInterface.obtenerHistoricoRecepcion(bandejaRecibidos.getDocumento(), personaLogueada);
					histDocRecepTurnadoPor.setIdEstatusRecep(Integer.parseInt(Utilidades.mensajeProperties("estatus_turnado_por")));
					daoHistDocRecepInterface.modificar(histDocRecepTurnadoPor);
				}else{
					histDocRecepTurnadoPor = boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, null, idPersonaLogueada, Integer.parseInt(Utilidades.mensajeProperties("estatus_turnado_por")));
					daoHistDocRecepInterface.guardar(histDocRecepTurnadoPor);
				}
				
				histDocRecepTurnadoA = 
						boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, idPersonaLogueada, personaTurnado.getPersona().getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("estatus_turnado_a")));

				idRecep = daoHistDocRecepInterface.guardar(histDocRecepTurnadoA);

				//Se guarda el registro en la tabla HistDocTurno
				histDocTurnoEntity = 
						boTurnadoInterface.construirHistDocTurno(idArea, tipoArea, idDocumento, idRecep , personaTurnado.getPersona().getIdPersona(), Integer.parseInt(Utilidades.mensajeProperties("id_estatus_noenterado")), comentario);
				daoHistDocTurnoInterface.guardar(histDocTurnoEntity);

				for (Integer idInstruccion : personaTurnado.getIdsInstruccionesInsertar()) {
					DTOTurnInstruccionEntity turnInstruccion = boTurnadoInterface.construirInstruccion(idDocumento, idRecep, idPersonaLogueada, personaTurnado.getPersona().getIdPersona(), idInstruccion);
					daoTurnInstruccionInterface.guardar(turnInstruccion);
				}

				if(personaTurnado.getTipoInstrucciones().equals("aten")) {
					DTOBandejaEAtencionEntity bandejaEAtencion = 
						boTurnadoInterface.construirBandejaEAtencion(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")), idRecep, idPersonaLogueada);
					daoBandejaEAtencionInterface.guardar(bandejaEAtencion);
					
					//Inserción de la Notificacion
					DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(),
							Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
					int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
					notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
					daoNotificacionesInterface.modificar(notificacion);
					
				} else if(personaTurnado.getTipoInstrucciones().equals("info")) {
					DTOBandejaEInfoEntity bandejaEInformativa = 
						boTurnadoInterface.construirBandejaEInfo(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")), idRecep, idPersonaLogueada);
					daoBandejaEInfoInterface.guardar(bandejaEInformativa);
					
					//Inserción de la Notificacion
					DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(),
							Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
					int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
					notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
					daoNotificacionesInterface.modificar(notificacion);
				}
			}
		}

		//Se elimina el docoumento de la bandeja de entrada y se inserta en el historico
		if(documentoBandeja instanceof DTOBandejaERecibidosEntity) {
			bandejaRecibidos.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			DTOHBandejaERecibidosEntity hBandejaERecibidos = boBandejaHistoricoInterface.crearHRecibido(bandejaRecibidos);
			daoHBandejaERecibidosInterface.guardar(hBandejaERecibidos);
			daoBandejaERecibidosInterface.eliminar(bandejaRecibidos);
			
		} else if(documentoBandeja instanceof DTOBandejaEAtencionEntity) {

			bandejaAtencion.setEnAtencion(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_enatencion")));
			DTOHBandejaEAtencionEntity hBandejaEAtencion = boBandejaHistoricoInterface.crearHAtencion(bandejaAtencion);
			daoHBandejaAtencionInterface.guardar(hBandejaEAtencion);
			daoBandejaEAtencionInterface.eliminar(bandejaAtencion);
			
		} else if(documentoBandeja instanceof DTOBandejaECCPEntity) {

			DTOHBandejaECCPEntity hBandejaECCP = boTurnadoInterface.construirHBandejaECCP(idDocumento, idPersonaLogueada, idArea, tipoArea);
			
			daoHBandejaAtencionInterface.guardar(hBandejaECCP);
			daoBandejaEAtencionInterface.eliminar(bandejaCCP);

		} else if(documentoBandeja instanceof DTOBandejaEInfoEntity) {

			DTOHBandejaEInfoEntity hBandejaEInfo = 
					boTurnadoInterface.construirHBandejaEInfo(idDocumento, idPersonaLogueada, idArea, tipoArea, bandejaInfo.getIdHistoricoPadre(), bandejaInfo.getIdHistoricoRecep());
			daoHBandejaAtencionInterface.guardar(hBandejaEInfo);
			daoBandejaEAtencionInterface.eliminar(bandejaInfo);

		}
		

		DTOHistDocTurnoEntity histDocTurno; 
		if(documentoBandeja instanceof DTOBandejaERecibidosEntity) {
			
			bandejaRecibidos = (DTOBandejaERecibidosEntity)documentoBandeja;
			
			//Se actualiza el histDocuTurno a "atendiendo"
			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(bandejaRecibidos.getDocumento(), personaLogueada);
			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
			daoHistDocTurnoInterface.modificar(histDocTurno);
			
		} else if(documentoBandeja instanceof DTOBandejaEAtencionEntity) {
			
			bandejaAtencion = (DTOBandejaEAtencionEntity)documentoBandeja;
			
			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(bandejaAtencion.getDocumento(), personaLogueada);
			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
			daoHistDocTurnoInterface.modificar(histDocTurno);
			
		} 
//		else if(documentoBandeja instanceof DTOBandejaECCPEntity) {
//
//			bandejaCCP = (DTOBandejaECCPEntity)documentoBandeja;
//			idDocumento = bandejaCCP.getIdDocumento();
//			
//			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(bandejaCCP.getDocumento(), personaLogueada);
//			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
//
//		}else if(documentoBandeja instanceof DTOBandejaEInfoEntity) {
//
//			bandejaInfo = (DTOBandejaEInfoEntity)documentoBandeja;
//			idDocumento = bandejaInfo.getIdDocumento();
//			
//			histDocTurno = daoHistDocTurnoInterface.obtenerHistoricoTurnado(bandejaInfo.getDocumento(), personaLogueada);
//			histDocTurno.setIdEstatusTurno(Integer.parseInt(Utilidades.mensajeProperties("id_estatus_enatencion")));
//
//		}
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#turnarHistorico(java.util.List, mx.org.ine.servicios.dto.DTOBase, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String, java.util.List)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void turnarHistorico(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja, DTOEstructuraAreasEntity personaLogueada, String comentario, List<DTOTurnadoInstruHelper> instruccionesRealizadas)
			throws Exception {
		Integer idPersonaLogueada = personaLogueada.getIdPersona();
		Integer idArea = personaLogueada.getIdArea();
		Integer tipoArea = personaLogueada.getTipoArea();
		Integer idDocumento = null;
		
		DTODocumentoEntity documento = null;
		
		DTOHBandejaERecibidosEntity bandejaRecibidos = null; 
		DTOHBandejaEAtencionEntity bandejaAtencion = null;
		DTOHBandejaECCPEntity bandejaCCP= null;
		DTOBandejaECCPEntity bandejaCCPEntrada= null;
		DTOHBandejaEInfoEntity bandejaInfo = null;
		
		//Se obtiene el idDocumento, depende del que apartado donde se turnó 
		if(documentoBandeja instanceof DTOHBandejaERecibidosEntity) {
			
			bandejaRecibidos = (DTOHBandejaERecibidosEntity)documentoBandeja;
			documento = bandejaRecibidos.getDocumento();
			idDocumento = bandejaRecibidos.getIdDocumento();
			
		} else if(documentoBandeja instanceof DTOHBandejaEAtencionEntity) {
			
			bandejaAtencion = (DTOHBandejaEAtencionEntity)documentoBandeja;
			documento = bandejaAtencion.getDocumento();
			idDocumento = bandejaAtencion.getIdDocumento();
			
		} else if(documentoBandeja instanceof DTOHBandejaECCPEntity) {

			bandejaCCP = (DTOHBandejaECCPEntity)documentoBandeja;
			documento = ((DTOHBandejaECCPEntity)documentoBandeja).getDocumento();
			idDocumento = bandejaCCP.getIdDocumento();

		}else if(documentoBandeja instanceof DTOBandejaECCPEntity) {

			bandejaCCPEntrada = (DTOBandejaECCPEntity)documentoBandeja;
			documento = ((DTOBandejaECCPEntity)documentoBandeja).getDocumento();
			idDocumento = bandejaCCPEntrada.getIdDocumento();

		}else if(documentoBandeja instanceof DTOHBandejaEInfoEntity) {

			bandejaInfo = (DTOHBandejaEInfoEntity)documentoBandeja;
			documento = ((DTOHBandejaEInfoEntity)documentoBandeja).getDocumento();
			idDocumento = bandejaInfo.getIdDocumento();

		}
		
		for (DTOTurnadoHelper personaTurnado : listaTurnado) {
			if (personaTurnado.isEnviarATurnar()) {
				Integer idRecep = null;
				DTOHistDocRecepEntity histDocRecepTurnadoPor = null;
				DTOHistDocRecepEntity histDocRecepTurnadoA  = null;
				DTOHistDocTurnoEntity histDocTurnoEntity = null;
				DTOTurnInstruccionEntity turnInstruccion = null;
				
				if(personaTurnado.isTieneIntruccionesAtencionRealizadas() || personaTurnado.isTieneIntruccionesInfoRealizadas()){
					
					idRecep = daoHistDocRecepInterface.obtenerIdHistRecepcion(documento, personaLogueada, personaTurnado.getPersona());
					
					DTOBandejaEAtencionEntity atencion;
					DTOBandejaEInfoEntity info;
					
					if(personaTurnado.isTieneIntruccionesAtencionRealizadas()){
						atencion = daoBandejaEAtencionInterface.obtenerDocumentoAtencion(documento, personaTurnado.getPersona());
						if(!atencion.getNoLeido().equals(1)){
							DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(), Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
							int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
							notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
							atencion.setNoLeido(1);
							daoNotificacionesInterface.modificar(notificacion);
							daoBandejaEAtencionInterface.modificar(atencion);
						}
					}else if(personaTurnado.isTieneIntruccionesInfoRealizadas()){
						info = daoBandejaEInfoInterface.obtenerDocumnetoInfo(documento, personaTurnado.getPersona());
						if(!info.getNoLeido().equals(1)){
							DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(), Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
							int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
							notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
							info.setNoLeido(1);
							daoNotificacionesInterface.modificar(notificacion);
						}
					}
					
				}else{	
					//Se guardan los registros en la tabla HistDocRecep
					histDocRecepTurnadoPor = boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, null, idPersonaLogueada, Integer.valueOf(2));
					daoHistDocRecepInterface.guardar(histDocRecepTurnadoPor);
	
					histDocRecepTurnadoA = boTurnadoInterface.construirHistDocRecep(idArea, tipoArea, idDocumento, idPersonaLogueada, personaTurnado.getPersona().getIdPersona(), Integer.valueOf(3));
	
					idRecep = daoHistDocRecepInterface.guardar(histDocRecepTurnadoA);
	
					//Se guarda el registro en la tabla HistDocTurno
					histDocTurnoEntity = boTurnadoInterface.construirHistDocTurno(idArea, tipoArea, idDocumento, idRecep , personaTurnado.getPersona().getIdPersona(), Integer.valueOf(1), comentario);
					daoHistDocTurnoInterface.guardar(histDocTurnoEntity);
					
					if(personaTurnado.getTipoInstrucciones().equals("aten")) {
						DTOBandejaEAtencionEntity bandejaEAtencion = 
							boTurnadoInterface.construirBandejaEAtencion(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")), idRecep, idPersonaLogueada);
						daoBandejaEAtencionInterface.guardar(bandejaEAtencion);
						
						//Inserción de la Notificacion
						DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(),
								Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
						int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
						notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
						daoNotificacionesInterface.modificar(notificacion);
						
					} else if(personaTurnado.getTipoInstrucciones().equals("info")) {
						DTOBandejaEInfoEntity bandejaEInformativa = 
							boTurnadoInterface.construirBandejaEInfo(idDocumento, personaTurnado.getPersona().getIdPersona(), idArea, tipoArea, Integer.parseInt( Utilidades.mensajeProperties("constante_documento_noLeido")), idRecep, idPersonaLogueada);
						daoBandejaEInfoInterface.guardar(bandejaEInformativa);
						
						//Inserción de la Notificacion
						DTONotificacionesEntity notificacion = daoNotificacionesInterface.consultarNotificacion(personaTurnado.getPersona().getIdPersona(),
								Integer.valueOf(Utilidades.mensajeProperties("id_modulo_bandeja")));
						int documentosPendientes = notificacion.getDocumentosPendientes().intValue() + 1;
						notificacion.setDocumentosPendientes(Integer.valueOf(documentosPendientes));
						daoNotificacionesInterface.modificar(notificacion);
					}
				}
				//Se insertan las instrucciones
				for (Integer idInstruccion : personaTurnado.getIdsInstruccionesInsertar()) {
					turnInstruccion = boTurnadoInterface.construirInstruccion(idDocumento, idRecep, idPersonaLogueada, personaTurnado.getPersona().getIdPersona(), idInstruccion);
					daoTurnInstruccionInterface.guardar(turnInstruccion);
				}
			}
		}
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#obtenerInstruccionesRealizadas(mx.ine.gestion.dto.DTOUsuarioLogin)
	 */
	@Override
	public List<DTOTurnadoInstruHelper> obtenerInstruccionesRealizadas(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento) throws Exception{
		
		return boTurnadoInterface.construirInstruccionesRealizadas(daoTurnInstruccionInterface.obtenerInstruccionesRealizadas(dtoEstructuraAreasEntity, documento));
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASTurnadoInterface#obtenerComentarioTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public String obtenerComentarioTurnado(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno) {
		String comentario = "";
		List<DTOHistDocTurnoEntity> listaTurnadas =  daoHistDocTurnoInterface.obtenerPersonasTurnadas(documento, personaTurno);
		if(listaTurnadas != null && listaTurnadas.size() > 0){
			comentario = listaTurnadas.get(0).getComentarioGrl();
		}
		return comentario;
	}	
}
