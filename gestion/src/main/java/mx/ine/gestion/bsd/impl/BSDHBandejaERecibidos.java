/**
 * @(#)BSDHBandejaERecibidos.java 10/01/2018
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
import mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdHBandejaERecibidos")
@Scope("prototype")
public class BSDHBandejaERecibidos implements BSDHBandejaERecibidosInterface, BSDPaginarLazyInterface<DTOHBandejaERecibidosEntity,DTOHBandejaERecibidosHelper>{

	@Autowired
	@Qualifier("asBandejaHistorico")
	private ASBandejaHistoricoInterface asBandejaHistoricoInterface;
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaERecibidosEntity> obtenerListaDeDatos(DTOHBandejaERecibidosHelper filtroHRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		return asBandejaHistoricoInterface.obtenerListaRecibidosLazy(filtroHRecibidoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOHBandejaERecibidosHelper filtroHRecibidoHelper) {
		return asBandejaHistoricoInterface.obtenerTotalDeRegistrosRecibidos(filtroHRecibidoHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface#notificacionBandejaRecibidos(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, boolean)
	 */
	@Override
	public void notificacionBandejaRecibidos(DTOEstructuraAreasEntity persona, DTOHBandejaERecibidosEntity histRecibido, boolean activar) {
		// TODO Auto-generated method stub
		
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void registrarAtencion(DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity persona, Integer estatus, String comentario) {
		asBandejaHistoricoInterface.registrarAtencion(recibido, persona, estatus, comentario);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface#responderTurnado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	public void responderTurnado(DTOEstructuraAreasEntity persona, DTOHBandejaERecibidosEntity recibido, DTOResponderTurnadoHelper filtroResponder) throws Exception {
		asBandejaHistoricoInterface.responderTurnado(persona, recibido, filtroResponder);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface#reasignarDocumento(java.util.List, mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void reasignarDocumento(List<DTOEstructuraAreasEntity> listaTitulares, DTOHBandejaERecibidosEntity recibido, DTOEstructuraAreasEntity usuario) throws Exception {
		asBandejaHistoricoInterface.reasignarDocumento(listaTitulares, recibido, usuario);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaERecibidosInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaERecibidosEntity recibidoSeleccionado) {
		return asBandejaHistoricoInterface.obtenerInstrucciones(recibidoSeleccionado);
	}
}