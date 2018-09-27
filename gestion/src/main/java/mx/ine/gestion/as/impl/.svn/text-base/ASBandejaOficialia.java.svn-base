/**
 * @(#)ASInstrucciones.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.as.inter.ASBandejaOficialiaInterface;
import mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface;
import mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;

import org.jboss.logging.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de administrar el o los DAO del módulo de Instrucciones.
 *
 * @author David Rodríguez Corral
 * @since 16/11/2017
 */
@Component("asBandejaOficialia")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASBandejaOficialia implements ASBandejaOficialiaInterface{

	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(ASBandejaOficialia.class);
	
	
	@Autowired
	@Qualifier("daoBandejaEntradasOficialia")
	private DAOBandejaEntradasOficialiaInterface daoBandejaEntradasOficialiaInterface;
	
	@Autowired
	@Qualifier("daoOficialiasAreas")
	private DAOOficialiasAreasInterface daoOficialiasAreasInterface;
	
	@Autowired
	@Qualifier("daoAnexo")
	private DAODocumentoAnexoInterface daoAnexoInterface;

	@Autowired
	@Qualifier("daoDocumentosRemitentes")
	private DAODocumentosRemitentesInterface daoDocumentosRemitentesInterface;
	
	@Autowired
	@Qualifier("daoApartadosNumDocOf")
	private DAOApartadosNumDocOfInterface daoApartadosNumDocOfInterface;
	
	@Autowired
	@Qualifier("daoNotificacionesOF")
	private DAONotificacionesOFInterface daoNotificacionesOFInterface;
	
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarAreasBandejaEntrada(java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasBandejaEntrada(Integer idOficialia) {
		return daoBandejaEntradasOficialiaInterface.consultarAreasBandejaEntrada(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarNumeroEntrada(java.lang.Integer)
	 */
	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia) {
		return daoBandejaEntradasOficialiaInterface.consultarNumeroEntrada(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#obtenerListaDeDocumentos(mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> obtenerListaDeDocumentos(DTOBandejaOficialiaHelper bandeja, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		
		List<DTOBandejaEntradasOficialiaEntity> dtoListaBandeja = daoBandejaEntradasOficialiaInterface.buscarDocumentosDisponiblesLazy(
		bandeja, indicePrimerElemento, tamanioMaxPagina, campoOrden, tipoOrdenamiento, filtrosPorColumna);
		
		logger.info("Entrando a consultar lazy");
		logger.info("con tamaño "+dtoListaBandeja.size());
		return dtoListaBandeja;
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#obtenerTotalDeDocumentos(mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper)
	 */
	@Override
	public Integer obtenerTotalDeDocumentos(DTOBandejaOficialiaHelper bandeja) {
		logger.info("con tamaño total"+daoBandejaEntradasOficialiaInterface.getTotalDocumentosLazy(bandeja));
		return daoBandejaEntradasOficialiaInterface.getTotalDocumentosLazy(bandeja);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarAreasOficialias(java.lang.Integer)
	 */
	@Override
	public List<DTOCAreaEntity> consultarAreasOficialias(Integer idOficialia) {
		return daoOficialiasAreasInterface.consultarAreasOficialia(idOficialia);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarBandeja(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(Integer idOficialia, Integer idArea) {
		return daoBandejaEntradasOficialiaInterface.consultarBandeja(idOficialia, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarNumeroEntrada(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia, Integer idArea) {
		return daoBandejaEntradasOficialiaInterface.consultarNumeroEntrada(idOficialia, idArea);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarAnexos(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> consultarAnexos(DTOBandejaEntradasOficialiaEntity dtBandejaSeleccionados) {
		return daoAnexoInterface.consultarAnexos(dtBandejaSeleccionados.getIdDocumento());
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarBandejaOficialia(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialia(Integer idOficialia, Integer idArea) {
		logger.info("Entrando a consultar lazy");
		return daoBandejaEntradasOficialiaInterface.consultarBandejaOficialia(idOficialia, idArea);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarBandejaOficialiaNombre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialiaNombre(Integer idOficialia, Integer idArea) {
		return daoBandejaEntradasOficialiaInterface.consultarBandejaOficialiaNombre(idOficialia, idArea);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#clasificarDocumento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String clasificarDocumento(DTOBandejaEntradasOficialiaEntity dtoBandeja, Integer idPersona, Integer idArea) {
		String mensaje = "";
		
		//logger.info("el valor es "+daoBandejaEntradasOficialiaInterface.consultarDocumentoClasificando(dtoBandeja.getIdDocumento(), idArea));
		DTOBandejaEntradasOficialiaEntity dtoConsulta = daoBandejaEntradasOficialiaInterface.consultarDocumentoPendiente(dtoBandeja, idPersona);
		
		if (dtoConsulta != null){
			
			mensaje = "Antes de asignar folio a este documento, termina de asignar el documento "+dtoConsulta.getDtoDocumento().getNoDocumento()+" haciendo clic en 'Captura de documento'";
			return mensaje;
			
		}
		if (daoBandejaEntradasOficialiaInterface.consultarDocumentoClasificando(dtoBandeja.getIdDocumento(), idArea) != null){
			
			mensaje = "El documento esta siendo clasificado";
			return mensaje;
			
		}
		
		else{
			
			daoBandejaEntradasOficialiaInterface.clasificarDocumento(dtoBandeja.getIdDocumento(), idPersona, idArea);
			
			return mensaje;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarRemitentes(java.lang.Integer)
	 */
	@Override
	public String consultarRemitentes(Integer idDocumento, Integer idArea, Integer tipoArea, Integer noLeido) {
		
		if(daoBandejaEntradasOficialiaInterface.consultarLeido(idDocumento, idArea, tipoArea).intValue() == 1){
		
			daoBandejaEntradasOficialiaInterface.marcarComoLeido(idDocumento, idArea, tipoArea);
			daoNotificacionesOFInterface.decrementar(idArea, tipoArea);
			
		}
		
		return daoDocumentosRemitentesInterface.consultarRemitentesCadena(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.as.inter.ASBandejaOficialiaInterface#consultarNotificacionesPorArea(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarNotificacionesPorArea(Integer idArea, Integer tipoArea, Integer idApartado) {
		return daoNotificacionesOFInterface.consultarNotificacionesPorArea(idArea, tipoArea, idApartado);
	}


}

