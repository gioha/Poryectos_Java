/**
 * @(#)BSDValidacion.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASValidacionInterface;
import mx.ine.gestion.bsd.inter.BSDValidacionInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del módulo de Firma.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 12/10/2017
 */

@Component("bsdValidacion")
@Scope("prototype")
public class BSDValidacion implements BSDValidacionInterface{
	
	@Autowired
	@Qualifier("asValidacion")
	private ASValidacionInterface asValidacionInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#consultarDocsParaValidacion(java.lang.Integer)
	 */
	public List<DTOValidacionDocumentosEntity> consultarDocsParaValidacion(Integer idPersona)throws Exception{
		return asValidacionInterface.consultarDocsParaValidacion(idPersona);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#guardarComentario(java.util.List, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona) throws Exception {
		asValidacionInterface.guardarComentario(dtoComentario, persona);	
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#eliminarValidacion(java.util.List)
	 */
	@Override
	public void eliminarValidacion(List<DTOValidacionDocumentosEntity> dtoDocumentosSeleccionados) throws Exception{
		asValidacionInterface.eliminarValidacion(dtoDocumentosSeleccionados);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#activarComentario(java.util.List)
	 */
	@Override
	public void activarComentario(List<DTOComentariosDocumentoEntity> dtoComentarios) throws Exception{
		asValidacionInterface.activarComentario(dtoComentarios);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#obtenerBorrador(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona) throws Exception {
		return asValidacionInterface.obtenerBorrador(idDocumento, idPersona);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#registrarEnHistorial(java.util.List)
	 */
	@Override
	public void registrarEnHistorial(List<DTOHistDocCreacionEntity> dtoHistorial) throws Exception {
		asValidacionInterface.registrarEnHistorial(dtoHistorial);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#regresarARemitente(java.util.List)
	 */
	@Override
	public void regresarARemitente(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception {
		asValidacionInterface.regresarARemitente(dtoLSeleccionados);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#validar(java.util.List)
	 */
	@Override
	public void validar(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception{
		asValidacionInterface.validar(dtoLSeleccionados);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#insertarVisualizacion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarVisualizacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception {
		asValidacionInterface.insertarVisualizacion(idPersona, idPersonaRemitente, idDocumento);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#insertarEdicion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarEdicion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception {
		asValidacionInterface.insertarEdicion(idPersona, idPersonaRemitente, idDocumento);
		
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#guardarComentario(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void guardarComentario(DTOValidacionDocumentosEntity dtoValidacion, DTOEstructuraAreasEntity persona) throws Exception {
		asValidacionInterface.guardarComentario(dtoValidacion, persona);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#eliminarComentario(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity)
	 */
	@Override
	public void eliminarComentario(DTOValidacionDocumentosEntity dtoValidacion) throws Exception {
		asValidacionInterface.eliminarComentario(dtoValidacion);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#cambiarALeido(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity)
	 */
	@Override
	public void cambiarALeido(DTOValidacionDocumentosEntity dtoValidacion) throws Exception{
		asValidacionInterface.cambiarALeido(dtoValidacion);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		return asValidacionInterface.obtenerDetalleDocumento(idDocumento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#obtenerAnexoPorDocumento(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception{
		return asValidacionInterface.obtenerAnexoPorDocumento(documento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#consultarHistorialPorIdDocumento(int)
	 */
	@Override
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception {
		return asValidacionInterface.consultarHistorialPorIdDocumento(idDocumento);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDValidacionInterface#consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception {
		return asValidacionInterface.consultarComentarios(dtoDocumentoEntity);
	}
}
