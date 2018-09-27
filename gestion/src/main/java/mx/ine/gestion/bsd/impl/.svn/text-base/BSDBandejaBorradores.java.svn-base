/**
 * BSDBorradorDocumentos.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASBandejaBorradoresInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.util.CorreosGestion;
import mx.ine.gestion.util.ServicioPostal;

/**
 * @author Homero Villanueva Dominguez
 * @update José Miguel Ortiz
 * @since 26/04/2018
 */
@Component("bsdBorradorDocumentos")
@Scope("prototype")
public class BSDBandejaBorradores implements BSDBandejaBorradoresInterface {
	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(BSDBandejaBorradores.class);

	@Autowired
	@Qualifier("asBandejaBorradores")
	private ASBandejaBorradoresInterface asBorradorDocumentosInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#consultarBorradores(java.lang.String, boolean)
	 */
	@Override
	public List<DTOBorradorDocumentosEntity> consultarBorradores(DTOBorradorDocumentosHelper helperBorradores) throws Exception {
		return asBorradorDocumentosInterface.consultarBorradores(helperBorradores);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface#consultarPersonasXArea(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonasXArea(DTOEstructuraAreasEntity persona, String coincidencia) throws Exception {
		return asBorradorDocumentosInterface.consultarPersonasXArea(persona, coincidencia);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface#consultaTitulares()
	 */
	@Override
	public List<DTOJerarquiaPersonasEntity> consultaTitulares() throws Exception {
		return asBorradorDocumentosInterface.consultaTitulares();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el  método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#insertarFirma(java.util.List, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity,* mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void enviarAFirmar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity DTOBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception  {
		asBorradorDocumentosInterface.enviarFirmar(listaDestinatarios, DTOBorradorDocumentosEntity, remitente);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface#consultarEstructurasTitulares(java.lang.String)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> consultarEstructurasTitulares(String coincidencia) throws Exception {
		return asBorradorDocumentosInterface.consultarEstructurasTitulares(coincidencia);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#eliminarDocumento(mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	public void eliminarDocumento(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity dtoEstructuraAreasEntity) throws Exception {
		asBorradorDocumentosInterface.eliminarDocumentoBorrador(dtoBorradorDocumentosEntity, dtoEstructuraAreasEntity);

	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#insertarValidacion(java.util.List, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity,  mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void enviarValidar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception {
		asBorradorDocumentosInterface.enviarValidar(listaDestinatarios, dtoBorradorDocumentosEntity, remitente);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#leerComentarios(java.util.List)
	 */
	@Override
	public void leerComentarios(DTOBorradorDocumentosEntity borrador, List<DTOComentariosNoLeidos> listaComentarios) throws Exception {
		asBorradorDocumentosInterface.leerComentarios(borrador, listaComentarios);

	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface#obtenerListaEdicionesLeidas(int)
	 */
	@Override
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesLeidas(int idDocumento) throws Exception {
		return asBorradorDocumentosInterface.obtenerListaEdicionesLeidas(idDocumento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface#obtenerListaEdicionesNoLeidas(int)
	 */
	@Override
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesNoLeidas(int idDocumento) throws Exception {
		return asBorradorDocumentosInterface.obtenerListaEdicionesNoLeidas(idDocumento);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#enviarDestinatario(mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void enviarDestinatario(DTOBorradorDocumentosEntity borrador,DTOEstructuraAreasEntity persona) throws Exception {
		asBorradorDocumentosInterface.enviarDestinatario(borrador, persona);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el  método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#actualizarBorrador(mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	public void actualizarBorrador(DTOBorradorDocumentosEntity DTOBorradorDocumentosEntity) throws Exception {
		asBorradorDocumentosInterface.actualizarBorrador(DTOBorradorDocumentosEntity);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity, boolean)
	 */
	@Override
	public void notificacionBandejaBorradores(DTOEstructuraAreasEntity persona, DTOBorradorDocumentosEntity borrador, boolean activar) throws Exception{
		asBorradorDocumentosInterface.notificacionBandejaBorradores(persona, borrador, activar);	
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#enviarCorreoNotificacion(DTOEstructuraAreasEntity remitente, List<DTOEstructuraAreasEntity> destinatarios, DTOBorradorDocumentosEntity documento, String tipoEnvio)
	 */
	@Override
	public void enviarCorreoNotificacion(DTOEstructuraAreasEntity remitente, List<DTOEstructuraAreasEntity> destinatarios, DTOBorradorDocumentosEntity documento, String tipoEnvio) {
		CorreosGestion correo;
		List<String> listaCorreos = new ArrayList<String>();
		ServicioPostal servicioPostal = new ServicioPostal();

		for(DTOEstructuraAreasEntity destinatario : destinatarios) {
//			listaCorreos.add(destinatario.getCuentaLDAP() + "@ine.mx");
		}
//		listaCorreos.add("miguelortizroque@gmail.com");
		listaCorreos.add("norma.guzmang@ine.mx");
		listaCorreos.add("daniel.ramirezy@ine.mx");
		listaCorreos.add("anakaren.floresg@ine.mx");

		correo =  new CorreosGestion(remitente, documento, tipoEnvio);

		try {
			servicioPostal.enviarCorreoNotificacion(correo.getAsuntoCorreo().toString(), correo.getCuerpoCorreo().toString(), listaCorreos, null);
		} catch (Exception e) {
			log.error("BSDBandejaBorradores.enviarCorreoNotificacion: " + e.getMessage());
		}
	}
	
}