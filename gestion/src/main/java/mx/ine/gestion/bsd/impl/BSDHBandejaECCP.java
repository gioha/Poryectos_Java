/**
 * @(#)BSDHBandejaECCP.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASBandejaHistoricoInterface;
import mx.ine.gestion.bsd.inter.BSDHBandejaECCPInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.helper.DTOHBandejaECCPHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdHBandejaECCP")
@Scope("prototype")
public class BSDHBandejaECCP implements BSDHBandejaECCPInterface, BSDPaginarLazyInterface<DTOHBandejaECCPEntity,DTOHBandejaECCPHelper>{
	
	@Autowired
	@Qualifier("asBandejaHistorico")
	private ASBandejaHistoricoInterface asBandejaHistoricoInterface;
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaECCPEntity> obtenerListaDeDatos(
			DTOHBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return asBandejaHistoricoInterface.obtenerListaCCPLazy(filtroCCPHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOHBandejaECCPHelper filtroCCPHelper) {
		return asBandejaHistoricoInterface.obtenerTotalDeRegistrosCCP(filtroCCPHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaECCPInterface#notificacionBandejaCCP(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaECCPEntity, boolean)
	 */
	@Override
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona,
			DTOHBandejaECCPEntity histCCP, boolean activar) {
		// TODO Auto-generated method stub
		
	}



}
