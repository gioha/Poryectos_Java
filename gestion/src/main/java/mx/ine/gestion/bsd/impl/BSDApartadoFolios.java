/**
 * @(#)BSDApartadoFolios.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
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

import mx.ine.gestion.as.inter.ASApartadoFoliosInterface;
import mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

/**
 * Clase que contiene las llamadas a los AS´s que se utilizan en el módulo de Apartado de Folios
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
@Scope("prototype")
@Component("bsdApartadoFolios")
public class BSDApartadoFolios implements BSDApartadoFoliosInterface, BSDPaginarLazyInterface<DTOApartadoNumDocEntity, DTOFiltrosApartadoFolioHelper> {

	@Autowired
	@Qualifier("asApartadoFolios")
	private ASApartadoFoliosInterface asApartadoFoliosInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(
	 * java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOApartadoNumDocEntity> obtenerListaDeDatos(DTOFiltrosApartadoFolioHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		return this.asApartadoFoliosInterface.obtenerListaDeFoliosApartadosLazy(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOFiltrosApartadoFolioHelper filtros) {
		
		return this.asApartadoFoliosInterface.obtenerTotalFoliosApartadosLazy(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#obtenerInfoTipoAreas(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoTipoAreas(DTOFiltrosApartadoFolioHelper filtros) {

		this.asApartadoFoliosInterface.obtenerInfoTipoAreas(filtros);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#obtenerInfoAreas(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoAreas(DTOFiltrosApartadoFolioHelper filtros) {

		this.asApartadoFoliosInterface.obtenerInfoAreas(filtros);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#obtenerInfoTipoDocumentos(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoTipoDocumentos(DTOFiltrosApartadoFolioHelper filtros) {

		this.asApartadoFoliosInterface.obtenerInfoTipoDocumentos(filtros);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#obtenerInfoEntidades(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoEntidades(DTOFiltrosApartadoFolioHelper filtros) {

		this.asApartadoFoliosInterface.obtenerInfoEntidades(filtros);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#obtenerInfoActonimos(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void obtenerInfoActonimos(DTOFiltrosApartadoFolioHelper filtros) {

		this.asApartadoFoliosInterface.obtenerInfoActonimos(filtros);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#capturarApartadoFolios(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void capturarApartadoFolios(DTOFiltrosApartadoFolioHelper filtrosSeleccionados) throws Exception {

		this.asApartadoFoliosInterface.capturarApartadoFolios(filtrosSeleccionados);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDApartadoFoliosInterface#deshabilitarFoliosApartados(mx.ine.gestion.dto.db.DTOApartadoNumDocEntity)
	 */
	@Override
	public void deshabilitarFoliosApartados(DTOApartadoNumDocEntity folioApartado) throws Exception {

		this.asApartadoFoliosInterface.deshabilitarFoliosApartados(folioApartado);
	}
}
