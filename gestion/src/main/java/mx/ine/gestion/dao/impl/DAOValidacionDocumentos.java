/**
 * @(#)DAOValidacionDocumentos.java 12/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosID;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.Utilidades;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase encargada de hacer las consultas al schema de validacion_documentos de gestion4.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 12/10/2017
 */
@Repository("daoValidacionDocumentos")
@Scope("prototype")
public class DAOValidacionDocumentos extends DAOGenericGestion<DTOValidacionDocumentosEntity, DTOValidacionDocumentosID>
		implements DAOValidacionDocumentosInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#consultarDocsParaValidacion(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOValidacionDocumentosEntity> consultarDocsParaValidacion(Integer idPersona) {
		
		Criteria criteria;
		List<DTOValidacionDocumentosEntity> lista = null;
		if(idPersona != null){
			criteria= getSession().createCriteria(DTOValidacionDocumentosEntity.class);
			criteria.add(Restrictions.eq("idPersona", idPersona));
			criteria.createAlias("dtoDocumento", "dtoDocumento", JoinType.INNER_JOIN);
			criteria.createAlias("dtoPersonaRemitente", "dtoPersonaRemitente", JoinType.INNER_JOIN);
			criteria.createAlias("dtoComentario", "dtoComentario", JoinType.LEFT_OUTER_JOIN);
			criteria.addOrder(Order.desc("fechaHora"));
			lista = criteria.list();
		}
		return lista;
	}
	
	/**
	 * Método que crea un objeto DTOFirma con ciertos valores de default.
	 * 
	 * @param destinatario: Objeto del cual se obtiene el idPersona del destinatario.
	 * @param dtoDocumentoEntity: Objeto del cual se obtiene el Id del Docuemnto.
	 * @param remitente: Objeto del cual se obtiene el idPersona de la persona remitente
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/01/2018
	 */
	private DTOValidacionDocumentosEntity crearObjetoValidacion(DTOEstructuraAreasEntity destinatario, DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity remitente) {
		
		DTOValidacionDocumentosEntity dtoFirma = new DTOValidacionDocumentosEntity();
		
		dtoFirma.setIdPersona(destinatario.getIdPersona());
		dtoFirma.setIdDocumento(dtoDocumentoEntity.getIdDocumento());
		dtoFirma.setAnio(dtoDocumentoEntity.getAnio());
		dtoFirma.setIdPersonaRemitente(remitente.getIdPersona());
		
		dtoFirma.setEstatusRegresado(Integer.parseInt(Utilidades.mensajeProperties("validacion_estatus_no_regresado")));
		
		dtoFirma.setConModificaciones(0);
		//dtoFirma.setIdComentario(0);
		dtoFirma.setConVisualizacion(0);
		dtoFirma.setNoLeido(1);
		
		return dtoFirma;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#insertarValidacion(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.String, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarValidacion(DTOEstructuraAreasEntity destinatario, DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity remitente) {
		guardar(crearObjetoValidacion(destinatario, dtoDocumentoEntity, remitente));
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#insertarValidacion(java.util.List, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.String, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarValidacion(List<DTOEstructuraAreasEntity> listaDestinatarios, DTODocumentoEntity dtoDocumentoEntity,
			DTOEstructuraAreasEntity remitente) {
		for (DTOEstructuraAreasEntity destinatario : listaDestinatarios) {
			guardar(crearObjetoValidacion(destinatario, dtoDocumentoEntity, remitente));
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#actualizarValidaciones(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void actualizarValidaciones(Integer personaRegresoId, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_validacion_actualizarValidaciones"));
		query.setInteger("personaRegresoId", personaRegresoId);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#eliminarValidacion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void eliminarValidacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_validacion_eliminarValidacion"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#insertarVisualizacion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarVisualizacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_validacion_visualizacion"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#insertarEdicion(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarEdicion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_validacion_edicion"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#insertarComentario(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento, Integer idComentario) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery( "query_validacion_comentario"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idComentario", idComentario);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#quitarComentario(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void quitarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery( "query_validacion_comentario_quitar"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#obtenerIdComentario(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer obtenerIdComentario(Integer idDocumento, String usuarioComento) {
		Criteria criteria = this.getSession().createCriteria(DTOComentariosDocumentoEntity.class);
		criteria.setProjection(Projections.max("idComentario"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("usuarioComento", usuarioComento));
		Integer ultimoId = (Integer)criteria.uniqueResult();
		return ultimoId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#obtenerIdComentario(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer obtenerEstatusRegresado(Integer idDocumento, Integer idPersona) {
			Criteria criteria = this.getSession().createCriteria(DTOValidacionDocumentosEntity.class);
			criteria.setProjection(Projections.max("estatusRegresado"));
			criteria.add(Restrictions.eq("idDocumento", idDocumento));
			criteria.add(Restrictions.eq("idPersona", idPersona));
			Integer ultimoId = (Integer)criteria.uniqueResult();
			return ultimoId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOValidacionDocumentosInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_documento_obtener_detalle"))
				.addScalar("remitentes", StringType.INSTANCE)
				.addScalar("destinatarios", StringType.INSTANCE)
				.addScalar("ccps", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTODocumentoOficialiaHelper.class));
		query.setInteger("idDocumento", idDocumento);
		
		return (DTODocumentoOficialiaHelper) query.uniqueResult();			
	}
}