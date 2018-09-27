/**
 * @(#)BSDHBandejaEAtencion.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASBandejaHistoricoInterface;
import mx.ine.gestion.bsd.inter.BSDHBandejaEAtencionInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdHBandejaEAtencion")
@Scope("prototype")
public class BSDHBandejaEAtencion implements BSDHBandejaEAtencionInterface,BSDPaginarLazyInterface<DTOHBandejaEAtencionEntity, DTOHBandejaEAtencionHelper> {
	
	@Autowired
	@Qualifier("asBandejaHistorico")
	private ASBandejaHistoricoInterface asBandejaHistoricoInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaEAtencionEntity> obtenerListaDeDatos(
			DTOHBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna){
		return asBandejaHistoricoInterface.obtenerListaAtencionLazy(filtroAtencionHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOHBandejaEAtencionHelper filtroAtencionHelper) {
		return asBandejaHistoricoInterface.obtenerTotalDeRegistrosAtencion(filtroAtencionHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaEAtencionInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void registrarAtencion(DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) {
		asBandejaHistoricoInterface.registrarAtencion(atencion, persona, estatus, comentario);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaEAtencionInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity, mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaEAtencionEntity atencion, DTOResponderTurnadoHelper filtroResponder) throws Exception {
		asBandejaHistoricoInterface.responderTurnado(persona, atencion, filtroResponder);
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaEAtencionInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaEAtencionEntity atencion, DTOEstructuraAreasEntity usuario) throws Exception {
		asBandejaHistoricoInterface.reasignarDocumento(listaTitulares, atencion, usuario);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaEAtencionInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEAtencionEntity atencionSeleccionado) {
		return asBandejaHistoricoInterface.obtenerInstrucciones(atencionSeleccionado);
	}

}
