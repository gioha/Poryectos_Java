/**
 * @(#)BSDBandejaECCP.java 06/12/2017
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
import mx.ine.gestion.bsd.inter.BSDBandejaECCPInterface;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;
import mx.ine.gestion.mb.MBBandejaBorradores;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaECCP")
@Scope("prototype")
public class BSDBandejaECCP implements BSDBandejaECCPInterface, BSDPaginarLazyInterface<DTOBandejaECCPEntity,DTOBandejaECCPHelper>{
	
	@Autowired
	@Qualifier("asBandejaEntrada")
	private ASBandejaEntradaInterface asBandejaEntradaInterface;
	
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaECCPEntity> obtenerListaDeDatos(
			DTOBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna){
		List<DTOBandejaECCPEntity> lista = null;
		
		try {
			lista = asBandejaEntradaInterface.obtenerListaCCPLazy(filtroCCPHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
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
	public Integer obtenerTotalDeRegistros(DTOBandejaECCPHelper filtroCCPHelper) {
		Integer num = null;
		
		try {
			num  = asBandejaEntradaInterface.obtenerTotalDeRegistrosCCP(filtroCCPHelper);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerListaDeDatos()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista . ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		return num;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaECCPInterface#notificacionBandejaRecibidos(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOBandejaECCPEntity, boolean)
	 */
	@Override
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOBandejaECCPEntity ccp, boolean activar) throws Exception {
		asBandejaEntradaInterface.notificacionBandejaCCP(persona, ccp, activar);
		
	}
}