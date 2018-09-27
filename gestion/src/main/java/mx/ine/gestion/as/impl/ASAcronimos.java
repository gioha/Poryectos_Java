/**
 * @(#)ASAcronimos.java 01/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASAcronimosInterface;
import mx.ine.gestion.dao.inter.DAOAcronimoInterface;
import mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dao.inter.DAOCTipoDocumentoInterface;
import mx.ine.gestion.dao.inter.DAOEstadosInterface;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo de Acrónimos.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 01/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
@Component("asAcronimos")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASAcronimos implements ASAcronimosInterface {

	@Autowired
	@Qualifier("daoAcronimo")
	private DAOAcronimoInterface daoAcronimosInterface;
	
	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daoCAreasInterface;
	
	@Autowired
	@Qualifier("daoCTipoDocumento")
	private DAOCTipoDocumentoInterface daoCTiposDocumentosInterface;

	@Autowired
	@Qualifier("daoCTipoArea")
	private DAOCTipoAreaInterface daoCTiposAreasInterface;
	
	@Autowired
	@Qualifier("daoEstados")
	private DAOEstadosInterface daoEstadosInterface;

	@Autowired
	@Qualifier("daoApartadoNumDoc")
	private DAOApartadoNumDocInterface daoApartadoNumDocInterface; 

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarAreas(int, int)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) {
		return daoCAreasInterface.consultarAreas(tipoArea, idEntidad);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#obtenerListaDeDatos(mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOAcronimoEntity> obtenerListaDeDatos(DTOFiltrosAcronimosHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		return daoAcronimosInterface.obtenerListaDeAcronimosLazy(filtros, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosColumna);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#obtenerTotalDeAcronimosLazy()
	 */
	@Override
	public Integer obtenerTotalDeAcronimosLazy() {
		
		return daoAcronimosInterface.obtenerTotalDeAcronimosLazy();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarTiposDocumentos()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos() {
		return daoCTiposDocumentosInterface.consultarTodosOrdenadosAscPor("descripcion");
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#guardarAcronimo(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor={Exception.class})
	public void guardarAcronimo(DTOAcronimoEntity acronimo) throws Exception{

		if(acronimo.getIdAcronimo() == null ) {
			Integer idAcronimo = daoAcronimosInterface.obtenerIdAcronimo(acronimo);
			if(idAcronimo == null ) { 
				idAcronimo = Integer.valueOf(1);
			} else {
				idAcronimo = Integer.valueOf(idAcronimo.intValue() + 1);
			}
			acronimo.setIdAcronimo(idAcronimo);
		}
		
		daoAcronimosInterface.emergencia(acronimo);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#eliminarAcronimo(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminarAcronimo(DTOAcronimoEntity acronimo) throws Exception {
		daoAcronimosInterface.eliminar(acronimo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.vh.inter.VHEstructuraInterface#obtnenerSiglasDeListaAreas
	 * (java.util.List, java.lang.String)
	 */
	public String obtenerSiglasDeListaAreas(List<DTOCAreaEntity> areas,
			Integer idArea) {
		for (DTOCAreaEntity area : areas) {
			if (area.getIdArea().equals(idArea)) {
				return area.getSiglas();
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarTiposAreas()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTiposAreas() {
		return daoCTiposAreasInterface.consultarTodosOrdenadosAscPor("descripcion");
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarTipoAreaConDocumento()
	 */
	@Override
	public List<DTOCTipoAreaEntity> consultarTipoAreaConDocumento() {
			return daoCTiposAreasInterface.consultarTipoAreaConDocumento();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarTipoAreaConDocumento()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> consultarTipoDocumentoConDocumento() {
			return daoCTiposDocumentosInterface.consultarTipoDocumentoConDocumento();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#consultarAcronimoEnUso(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public boolean consultarAcronimoEnUso(DTOAcronimoEntity acronimo) {
		
		//1.- Validamos con documentos
		DTOApartadoNumDocEntity acronimoEnUso = daoApartadoNumDocInterface.consultarAcronimoEnUso(acronimo);
		
		if (acronimoEnUso == null) {
			return false;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASEstructuraInterface#consultarEstados()
	 */
	@Override
	public List<DTOEstadosEntity> consultarEstados() {
		return daoEstadosInterface.consultarEstadosSinOC();
	}

	/**
	 * (non-Javadoc)
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#recuperarAreasAcronimos()
	 */
	public List<DTOCAreaEntity> recuperarAreasAcronimos() {
		return daoCAreasInterface.consultarAreasAcronimos();
	}

	/**
	 * (non-Javadoc)
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAcronimosInterface#recuperarAcronimosPorArea(Integer idArea)
	 */
	public List<DTOAcronimoEntity> recuperarAcronimosPorArea(Integer idArea) {
		return daoAcronimosInterface.consultarAcronimosPorArea(idArea);
	}

}
