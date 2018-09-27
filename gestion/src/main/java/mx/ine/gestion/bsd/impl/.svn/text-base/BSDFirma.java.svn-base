/**
 * @(#)BSDFirma.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.io.FileNotFoundException;
import java.util.List;

import mx.ine.gestion.as.inter.ASFirmaInterface;
import mx.ine.gestion.bsd.inter.BSDFirmaInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOFirmaElectronicaHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del módulo de Firma.
 *
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */

@Component("bsdFirma")
@Scope("prototype")
public class BSDFirma implements BSDFirmaInterface{
	
	@Autowired
	@Qualifier("asFirma")
	private ASFirmaInterface asFirmaInterface;
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#consultarDocsFirmas(java.lang.Integer)
	 */
	public List<DTOFirmaDocumentosEntity> consultarDocsFirmas(Integer idPersona, Integer pendienteEnvio){
		return asFirmaInterface.consultarDocsFirmas(idPersona, pendienteEnvio);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#guardarComentario(java.util.List)
	 */
	@Override
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona) {
		asFirmaInterface.guardarComentario(dtoComentario, persona);	
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#obtenerBorrador(java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona) {
		return asFirmaInterface.obtenerBorrador(idDocumento, idPersona);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#regresarARemitente(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public String regresarARemitente(List<DTOFirmaDocumentosEntity> dtoLSeleccionados) throws Exception {
		return asFirmaInterface.regresarARemitente(dtoLSeleccionados); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#verificarFirmaYValidacion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer verificarFirmaYValidacion(Integer idDocumento) {
		return asFirmaInterface.verificarFirmaYValidacion(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#enviarASSign(java.io.File, java.lang.Integer)
	 */
	@Override
	public List<DTOFirmaDocumentosEntity> obtenerDatosFirmas(List<DTOFirmaDocumentosEntity> dtoListaFirma, byte[] certificado, String tipoAlgoritmo) throws Exception {
		return asFirmaInterface.obtenerDatosFirmas(dtoListaFirma, certificado, tipoAlgoritmo);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#enviarPKCS7(java.util.List)
	 */
	@Override
	public void enviarPKCS7(List<DTOFirmaDocumentosEntity> dtoListaDatosFirma) throws Exception, FileNotFoundException {
		asFirmaInterface.enviarPKCS7(dtoListaDatosFirma);
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#regresarARemitenteDocFirmado(java.util.List)
	 */
	@Override
	public void regresarARemitenteDocFirmado(
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados) throws Exception {
		asFirmaInterface.regresarARemitenteDocFirmado(dtoDocumentosSeleccionadosFirmados);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#enviarADestinatario(java.util.List)
	 */
	@Override
	public String enviarADestinatario(
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados, Integer idArea, Integer tipoArea,Integer opcionEnvio) throws Exception {
		return asFirmaInterface.enviarADestinatario(dtoDocumentosSeleccionadosFirmados, idArea, tipoArea, opcionEnvio);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#modificarEdicionEnFirma(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarModificacionFirma(Integer idPersona, Integer idDocumento) {
		asFirmaInterface.activarModificacionFirma(idPersona, idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#eliminarValidacionEnDocumento(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public void eliminarValidacionEnDocumento(DTOFirmaDocumentosEntity dtoFirma) {
		asFirmaInterface.eliminarValidacionEnDocumento(dtoFirma);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#activarVisualizacion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarVisualizacion(Integer idPersona, Integer idDocumento) {
		asFirmaInterface.activarVisualizacion(idPersona, idDocumento);
	}
	
	@Override
	public void guardarComentario(DTOFirmaDocumentosEntity dtoFirma, DTOEstructuraAreasEntity persona) {
		asFirmaInterface.guardarComentario(dtoFirma, persona);
	}
	
	@Override
	public void eliminarComentario(DTOFirmaDocumentosEntity dtoFirma){
		asFirmaInterface.eliminarComentario(dtoFirma);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#marcarComoLeido(java.lang.Integer)
	 */
	@Override
	public void marcarComoLeido(Integer idPersona, Integer idDocumento, Integer totalDocumentos) {
		asFirmaInterface.marcarComoLeido(idPersona, idDocumento, totalDocumentos);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#marcarComoLeido(java.lang.Integer, java.util.List)
	 */
	@Override
	public void marcarComoLeido(Integer idPersona, List<DTOFirmaDocumentosEntity> noLeidos) {
		asFirmaInterface.marcarComoLeido(idPersona, noLeidos);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#verificarDocumentosFirmados(java.util.List)
	 */
	@Override
	public String verificarDocumentosFirmados(List<DTOFirmaDocumentosEntity> dtoDocsFirma) {
		return asFirmaInterface.verificarDocumentosFirmados(dtoDocsFirma);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#verificarBloqueo(java.util.List)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarBloqueo(List<DTOFirmaDocumentosEntity> dtoFirmaBloqueo) {
		return asFirmaInterface.verificarBloqueo(dtoFirmaBloqueo);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#verificarBloqueo(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarBloqueo(DTOFirmaDocumentosEntity dtoFirmaBloqueo) {
		return asFirmaInterface.verificarBloqueo(dtoFirmaBloqueo);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#verificarRegresado(java.util.List)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarRegresado(List<DTOFirmaDocumentosEntity> dtoFirmaRegreso) {
		return asFirmaInterface.verificarRegresado(dtoFirmaRegreso);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		return asFirmaInterface.obtenerDetalleDocumento(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#obtenerAnexoPorDocumento(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception {
		return asFirmaInterface.obtenerAnexoPorDocumento(documento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#consultarHistorialPorIdDocumento(int)
	 */
	@Override
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return asFirmaInterface.consultarHistorialPorIdDocumento(idDocumento);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception {
		return asFirmaInterface.consultarComentarios(dtoDocumentoEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bsd.inter.BSDFirmaInterface#rechazarDocumentos(java.util.List)
	 */
	@Override
	public String rechazarDocumentos(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados) throws Exception {
		return asFirmaInterface.rechazarDocumentos(dtoDocumentosSeleccionados);
	}
	
	

}
