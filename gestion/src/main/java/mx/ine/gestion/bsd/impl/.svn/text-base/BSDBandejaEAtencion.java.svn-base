/**
 * @(#)BSDBandejaEAtencion.java 06/12/2017
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
import mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.mb.MBBandejaBorradores;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaEAtencion")
@Scope("prototype")
public class BSDBandejaEAtencion implements BSDBandejaEAtencionInterface,BSDPaginarLazyInterface<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper> {
	
	@Autowired
	@Qualifier("asBandejaEntrada")
	private ASBandejaEntradaInterface asBandejaEntradaInterface;
	
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaEAtencionEntity> obtenerListaDeDatos(
			DTOBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		
		List<DTOBandejaEAtencionEntity> lista = null;
		
		try {
			lista = asBandejaEntradaInterface.obtenerListaAtencionLazy(filtroAtencionHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerListaDeDatos()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista . ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
				
		return lista;
		
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOBandejaEAtencionHelper filtroAtencionHelper) {
		Integer num = null;
		try {
			num = asBandejaEntradaInterface.obtenerTotalDeRegistrosAtencion(filtroAtencionHelper);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerListaDeDatos()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista . ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		return num;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface#notificacionBandejaAtencion(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, boolean)
	 */
	@Override
	public void notificacionBandejaAtencion(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, boolean activar) throws Exception{
		asBandejaEntradaInterface.notificacionBandejaAtencion(persona, atencion, activar);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void registrarAtencion(DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) throws Exception {
		
		asBandejaEntradaInterface.registrarAtencion(atencion, persona, estatus, comentario);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, java.lang.String, java.lang.String)
	 */
	@Override
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception{
		asBandejaEntradaInterface.responderTurnado(persona, atencion, filtroResponder);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) 
			throws Exception {
		asBandejaEntradaInterface.reasignarDocumento(listaTitulares, atencion, usuario);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEAtencionEntity atencionSeleccionado) throws Exception{
		return asBandejaEntradaInterface.obtenerInstrucciones(atencionSeleccionado);
	}
}
