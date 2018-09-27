/**
 * @(#)BSDAcronimos.java 01/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASAcronimosInterface;
import mx.ine.gestion.bsd.inter.BSDAcronimosInterface;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;
import mx.org.ine.servicios.bsd.BSDPaginarLazyInterface;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del módulo de Acrónimos.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 01/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */

@Component("bsdAcronimos")
@Scope("prototype")
public class BSDAcronimos implements BSDAcronimosInterface, BSDPaginarLazyInterface<DTOAcronimoEntity, DTOFiltrosAcronimosHelper> {
	
	@Autowired
	@Qualifier("asAcronimos")
	private ASAcronimosInterface asAcronimosInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerListaDeDatos(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOAcronimoEntity> obtenerListaDeDatos(DTOFiltrosAcronimosHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {
		
		return asAcronimosInterface.obtenerListaDeDatos(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.bsd.BSDPaginarLazyInterface#obtenerTotalDeRegistros(java.lang.Object)
	 */
	@Override
	public Integer obtenerTotalDeRegistros(DTOFiltrosAcronimosHelper filtros) {

		return asAcronimosInterface.obtenerTotalDeAcronimosLazy();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarAreas(int, int)
	 */
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) {
		return asAcronimosInterface.consultarAreas(tipoArea, idEntidad);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarTiposDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos() {
		return asAcronimosInterface.consultarTiposDocumentos();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#guardarAcronimo(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public void guardarAcronimo(DTOAcronimoEntity acronimo) throws Exception{
		asAcronimosInterface.guardarAcronimo(acronimo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#eliminarAcronimo(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public void eliminarAcronimo(DTOAcronimoEntity acronimo) throws Exception {
		asAcronimosInterface.eliminarAcronimo(acronimo);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarTiposAreas()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposAreas() {
		return asAcronimosInterface.consultarTiposAreas();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarTipoAreaConDocumento()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTipoAreaConDocumento() {
			return asAcronimosInterface.consultarTipoAreaConDocumento();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarTipoDocumentoConDocumento()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTipoDocumentoConDocumento() {
			return asAcronimosInterface.consultarTipoDocumentoConDocumento();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarAcronimoEnUso(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public boolean consultarAcronimoEnUso(DTOAcronimoEntity acronimo) {
		return asAcronimosInterface.consultarAcronimoEnUso(acronimo);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() {
		return asAcronimosInterface.consultarEstados();
	}

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#recuperarAreasAcronimos()
	 */
	@Override
	public List<DTOCAreaEntity> recuperarAreasAcronimos() {
		return asAcronimosInterface.recuperarAreasAcronimos();
	}

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAcronimosInterface#recuperarAcronimosPorArea(Integer idArea)
	 */
	@Override
	public List<DTOAcronimoEntity> recuperarAcronimosPorArea(Integer idArea) {
		return asAcronimosInterface.recuperarAcronimosPorArea(idArea);
	}

}
