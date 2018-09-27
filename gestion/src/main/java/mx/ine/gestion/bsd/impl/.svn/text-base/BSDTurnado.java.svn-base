/**
 * @(#)BSDTurnado.java 08/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.as.inter.ASTurnadoInterface;
import mx.ine.gestion.bsd.inter.BSDTurnadoInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.util.CorreosGestion;
import mx.ine.gestion.util.ServicioPostal;
import mx.org.ine.servicios.dto.DTOBase;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene las llamadas a los BSD´s que se utilizan en el módulo de Turnado
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 08/01/2018
 * @update José Miguel Ortiz
 * @since 20/04/2018
 */
@Scope("prototype")
@Component("bsdTurnado")
public class BSDTurnado implements BSDTurnadoInterface {
	private static final Logger log = Logger.getLogger(ServicioPostal.class);

	@Autowired
	@Qualifier("asTurnado")
	private ASTurnadoInterface asTurnadoInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#consultarPersonasTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasTurnado(DTOEstructuraAreasEntity personaActual) throws Exception {
		return asTurnadoInterface.consultarPersonasTurnado(personaActual);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#consultarInstruccionesAtencion(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	public List<DTOInstruccionesEntity> consultarInstruccionesAtencion(DTOEstructuraAreasEntity personaActual) throws Exception {
		return asTurnadoInterface.consultarInstruccionesAtencion(personaActual);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#consultarInstruccionesInformativas(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	public List<DTOInstruccionesEntity> consultarInstruccionesInformativas(DTOEstructuraAreasEntity personaActual) throws Exception {
		return asTurnadoInterface.consultarInstruccionesInformativas(personaActual);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#turnarDesdeRecibidos(java.util.List, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
	 */
//	public void turnarDesdeRecibidos(List<DTOTurnadoHelper> listaTurnado, DTOBandejaERecibidosEntity documentoBandejaRecibidos,
//			DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception {
//		asTurnadoInterface.turnarDesdeRecibidos(listaTurnado, documentoBandejaRecibidos, personaLogueada, comentario);
//	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#turnar(java.util.List, mx.org.ine.servicios.dto.DTOBase, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
	 */
	public void turnar(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja,
			DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception {
		asTurnadoInterface.turnar(listaTurnado, documentoBandeja, personaLogueada, comentario);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#turnarHistorico(java.util.List, mx.org.ine.servicios.dto.DTOBase, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String, java.util.List)
	 */
	@Override
	public void turnarHistorico(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja, DTOEstructuraAreasEntity personaLogueada, String comentario, List<DTOTurnadoInstruHelper> instruccionesRealizadas)
			throws Exception {
		asTurnadoInterface.turnarHistorico(listaTurnado, documentoBandeja, personaLogueada, comentario, instruccionesRealizadas);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#obtenerInstruccionesRealizadas(mx.ine.gestion.dto.DTOUsuarioLogin, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOTurnadoInstruHelper> obtenerInstruccionesRealizadas(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento) throws Exception {
		return asTurnadoInterface.obtenerInstruccionesRealizadas(dtoEstructuraAreasEntity, documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#obtenerComentarioTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public String obtenerComentarioTurnado(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno) {
		return asTurnadoInterface.obtenerComentarioTurnado(documento, personaTurno);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDTurnadoInterface#enviarCorreoNotificacion(DTOEstructuraAreasEntity usuarioLogueado, List<DTOTurnadoHelper> listaTurnado, DTODocumentoEntity documentoTurnado, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas)
	 */
	@Override
	public void enviarCorreoNotificacion(DTOUsuarioLogin usuarioLogueado, List<DTOTurnadoHelper> listaTurnado, DTODocumentoEntity documentoTurnado, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas) {
		CorreosGestion correo;
		List<String> listaCorreos = new ArrayList<String>();
		ServicioPostal servicioPostal = new ServicioPostal();

		for(DTOTurnadoHelper destinatario : listaTurnado) {
			if(destinatario.isEnviarATurnar() && destinatario.getTipoInstrucciones().equals("aten")) {
				for(DTOInstruccionesEntity instruccion : listaAtencion) {
					if(destinatario.getIdsInstruccionesInsertar().contains(instruccion.getIdInstruccion()) &&
					   instruccion.getConNotificacion().equals(1)) {
						correo =  new CorreosGestion(usuarioLogueado, destinatario, documentoTurnado, listaAtencion, listaInformativas);
//						listaCorreos.add(destinatario.getPersona().getCuentaLDAP() + "@ine.mx");
//						listaCorreos.add("miguelortizroque@gmail.com");
						listaCorreos.add("norma.guzmang@ine.mx");
						listaCorreos.add("daniel.ramirezy@ine.mx");
						listaCorreos.add("anakaren.floresg@ine.mx");
			
						try {
							servicioPostal.enviarCorreoNotificacion(correo.getAsuntoCorreo().toString(), correo.getCuerpoCorreo().toString(), listaCorreos, null);
							break;
						} catch (Exception e) {
							log.error("BSDTurnado.enviarCorreoNotificacion: " + e.getMessage());
						}
					}
				}
			}
		}
	}

}
