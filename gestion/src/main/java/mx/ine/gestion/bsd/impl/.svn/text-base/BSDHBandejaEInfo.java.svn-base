/**
 * @(#)BSDHBandejaEInfo.java 10/01/2018
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
import mx.ine.gestion.bsd.inter.BSDHBandejaEInfoInterface;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdHBandejaEInfo")
@Scope("prototype")
public class BSDHBandejaEInfo implements BSDHBandejaEInfoInterface, BSDPaginarLazyInterface<DTOHBandejaEInfoEntity,DTOHBandejaEInfoHelper>{
	
	@Autowired
	@Qualifier("asBandejaHistorico")
	private ASBandejaHistoricoInterface asBandejaHistoricoInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOHBandejaEInfoEntity> obtenerListaDeDatos(
			DTOHBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {

		return asBandejaHistoricoInterface.obtenerListaInfoLazy(filtroInfoHelper, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOHBandejaEInfoHelper filtroInfoHelper){
		return asBandejaHistoricoInterface.obtenerTotalDeRegistrosInfo(filtroInfoHelper);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDHBandejaEInfoInterface#obtenerInstrucciones(mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity)
	 */
	@Override
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOHBandejaEInfoEntity infoSeleccionado) {
		return asBandejaHistoricoInterface.obtenerInstrucciones(infoSeleccionado);
	}

}
