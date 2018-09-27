/**
 * @(#)BSDBandejaOficialia.java 16/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASBandejaOficialiaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del modulo de instrucciones..
 *
 * @author David Rodríguez Corral
 * @since 16/11/2017
 */
@Component("bsdBandejaOficialia")
@Scope("prototype")
public class BSDBandejaOficialia implements BSDBandejaOficialiaInterface, BSDPaginarLazyInterface<DTOBandejaEntradasOficialiaEntity, DTOBandejaOficialiaHelper>{

	@Autowired
	@Qualifier("asBandejaOficialia")
	private ASBandejaOficialiaInterface asBandejaOficialiaInterface;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface#consultarAreasBandejaEntrada(java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasBandejaEntrada(Integer idOficialia) {
		return asBandejaOficialiaInterface.consultarAreasBandejaEntrada(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface#consultarNumeroEntrada(java.lang.Integer)
	 */
	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia) {
		return asBandejaOficialiaInterface.consultarNumeroEntrada(idOficialia);
	}

	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(Integer idOficialia) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<DTOCAreaEntity> consultarAreasOficialias(Integer idOficialia) {
		return asBandejaOficialiaInterface.consultarAreasOficialias(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> obtenerListaDeDatos(
			DTOBandejaOficialiaHelper bandeja, int indicePrimerElemento, 
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {

		return asBandejaOficialiaInterface.obtenerListaDeDocumentos(
				bandeja, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override 
	public Integer obtenerTotalDeRegistros(DTOBandejaOficialiaHelper bandeja) {
		return asBandejaOficialiaInterface.obtenerTotalDeDocumentos(bandeja);
	}

	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(
			Integer idOficialia, Integer idArea) {
		return asBandejaOficialiaInterface.consultarBandeja(idOficialia, idArea);
	}

	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia, Integer idArea) {
		return asBandejaOficialiaInterface.consultarNumeroEntrada(idOficialia, idArea);
	}

	@Override
	public List<DTODocumentoAnexoEntity> consultarAnexos(
			DTOBandejaEntradasOficialiaEntity dtBandejaSeleccionados) {
		return asBandejaOficialiaInterface.consultarAnexos(dtBandejaSeleccionados);
	}

	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialia(
			Integer idOficialia, Integer idArea) {
		return asBandejaOficialiaInterface.consultarBandejaOficialia(idOficialia, idArea);
	}
	
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialiaNombre(
			Integer idOficialia, Integer idArea) {
		return asBandejaOficialiaInterface.consultarBandejaOficialiaNombre(idOficialia, idArea);
	}

	@Override
	public String clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandeja, Integer idPersona, Integer idArea) {
		return asBandejaOficialiaInterface.clasificarDocumento(dtoBandeja, idPersona, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface#consultarRemitentes(java.lang.Integer)
	 */
	@Override
	public String consultarRemitentes(Integer idDocumento, Integer idArea, Integer tipoArea, Integer noLeido) {
		return asBandejaOficialiaInterface.consultarRemitentes(idDocumento, idArea, tipoArea, noLeido);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface#consultarNotificacionesPorArea(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarNotificacionesPorArea(Integer idArea, Integer tipoArea, Integer idApartado) {
		return asBandejaOficialiaInterface.consultarNotificacionesPorArea(idArea, tipoArea, idApartado);
	}
	
}
