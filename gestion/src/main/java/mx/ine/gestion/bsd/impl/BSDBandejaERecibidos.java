/**
 * @(#)BSDBandejaERecibidos.java 06/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASBandejaEntradaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaERecibidos")
@Scope("prototype")
public class BSDBandejaERecibidos implements BSDBandejaERecibidosInterface, BSDPaginarLazyInterface<DTOBandejaERecibidosEntity,DTOBandejaERecibidosHelper>{

	@Autowired
	@Qualifier("asBandejaEntrada")
	private ASBandejaEntradaInterface asBandejaEntradaInterface;
	
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(BSDBandejaERecibidos.class);
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaERecibidosEntity> obtenerListaDeDatos(
			DTOBandejaERecibidosHelper filtroRecibidoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna){
		
		List<DTOBandejaERecibidosEntity> lista = null;
		
		try {
			lista = asBandejaEntradaInterface.obtenerListaRecibidosLazy(filtroRecibidoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
		} catch (Exception e) {
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerListaDeDatos()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de personas remitentes de la BD para el documento seleccionado. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		return lista;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOBandejaERecibidosHelper filtroRecibidoHelper) {
		Integer num = null;
		
		try {
			num = asBandejaEntradaInterface.obtenerTotalDeRegistrosRecibidos(filtroRecibidoHelper);
		} catch (Exception e) {
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerListaDeDatos()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de personas remitentes de la BD para el documento seleccionado. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		return num;
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface#notificacionBandejaBorradores(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, boolean)
	 */
	@Override
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido, boolean activar) throws Exception {
		asBandejaEntradaInterface.notificacionBandejaRecibidos(persona, recibido, activar);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void registrarAtencion(DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception{
		asBandejaEntradaInterface.registrarAtencion(recibido, persona, estatus, comentario);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, java.lang.String, java.lang.String)
	 */
	@Override
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) throws Exception{
		asBandejaEntradaInterface.responderTurnado(persona, recibido, filtroResponder);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception {
		asBandejaEntradaInterface.reasignarDocumento(listaTitulares, recibido, usuario);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaERecibidosEntity recibidoSeleccionado) throws Exception{
		return asBandejaEntradaInterface.obtenerInstrucciones(recibidoSeleccionado);
	}
}