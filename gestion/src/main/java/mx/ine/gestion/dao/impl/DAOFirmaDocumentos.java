/**
 * @(#)DAOFirmaDocumentos.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosID;
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
 * Clase encargada de hacer las consultas al schema de gestion4.
 *
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
@Repository("daoFirmaDocumentos")
@Scope("prototype")
public class DAOFirmaDocumentos extends
		DAOGenericGestion<DTOFirmaDocumentosEntity, DTOFirmaDocumentosID>
		implements DAOFirmaDocumentosInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOFirmaInterface#consultarDocsFirmas(java.lang
	 * .Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<DTOFirmaDocumentosEntity> consultarDocsFirmas(Integer idPersona, Integer pendienteEnvio) {

		Criteria criteria = getSession().createCriteria(DTOFirmaDocumentosEntity.class);
		
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.add(Restrictions.eq("pendienteEnvio", pendienteEnvio));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		criteria.createAlias("dtoDocumentoRemitente", "dtoDocumentoRemitente",JoinType.INNER_JOIN);
		criteria.addOrder(Order.desc("fechaHora"));

		return (List<DTOFirmaDocumentosEntity>) criteria.list();

	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#consultarFirmaBorrador
	 * (mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOFirmaDocumentosEntity> consultarFirmaBorrador(
			DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
		Criteria criteria = getSession().createCriteria(
				DTOFirmaDocumentosEntity.class);
		criteria.add(Restrictions.eq("idDocumento",
				dtoBorradorDocumentosEntity.getIdDocumento()));

		return (List<DTOFirmaDocumentosEntity>) criteria.list();
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
	private DTOFirmaDocumentosEntity crearObjetoFirma(DTOEstructuraAreasEntity destinatario,
			DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity remitente) {
		
		DTOFirmaDocumentosEntity dtoFirma = new DTOFirmaDocumentosEntity();
		
		dtoFirma.setIdPersona(destinatario.getIdPersona());
		dtoFirma.setIdDocumento(dtoDocumentoEntity.getIdDocumento());
		dtoFirma.setAnio(dtoDocumentoEntity.getAnio());
		dtoFirma.setIdPersonaRemitente(remitente.getIdPersona());
		dtoFirma.setPendienteEnvio(0);
		dtoFirma.setEstatusRegresado(Integer.parseInt(Utilidades.mensajeProperties("firma_estatus_no_regresado")));
		dtoFirma.setConModificaciones(0);
		dtoFirma.setIdComentario(0);
		dtoFirma.setConVisualizacion(0);
		dtoFirma.setNoLeido(1);
		
		return dtoFirma;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#insertarFirma(mx
	 * .ine.gestion.dto.db.DTOEstructuraAreasEntity,
	 * mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.String,
	 * mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarFirma(DTOEstructuraAreasEntity destinatario, DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity remitente) {
		guardar(crearObjetoFirma(destinatario, dtoDocumentoEntity, remitente));
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#insertarFirma(java
	 * .util.List, mx.ine.gestion.dto.db.DTODocumentoEntity, java.lang.String,
	 * mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void insertarFirma(
			List<DTOEstructuraAreasEntity> listaDestinatarios, DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity remitente) {
		for (DTOEstructuraAreasEntity destinatario : listaDestinatarios) {
			guardar(crearObjetoFirma(destinatario, dtoDocumentoEntity,remitente));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#actualizarPendienteEnvio
	 * (mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public void actualizarPendienteEnvio(
			DTOFirmaDocumentosEntity dtoDocFirmado, Integer pendiente) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_actualizarPendienteEnvio"));
		query.setInteger("idPersona", dtoDocFirmado.getIdPersona());
		query.setInteger("idDocumento", dtoDocFirmado.getIdDocumento());
		query.setInteger("pendienteEnvio", pendiente);
		query.executeUpdate();

	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#modificarEdicionEnFirma(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarModificacionFirma(Integer idPersona, Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_activarModificacionFirma"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#activarVisualizacion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarVisualizacion(Integer idPersona, Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_activarVisualizacion"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#activarEstatusPersonaRegresado(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void activarEstatusPersonaRegresado(Integer idPersona,
			Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_regresarARemitente"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#insertarComentario(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void insertarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento, Integer idComentario) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_comentario"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idComentario", idComentario);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#quitarComentario(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void quitarComentario(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_comentario_quitar"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idPersonaRemitente", idPersonaRemitente);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#obtenerIdComentario(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer obtenerIdComentario(Integer idDocumento, String usuarioComento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOComentariosDocumentoEntity.class);
		criteria.setProjection(Projections.max("idComentario"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("usuarioComento", usuarioComento));
		Integer ultimoId = (Integer)criteria.uniqueResult();
		return ultimoId;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#consultarEdicionDocumento(java.lang.String)
	 */
	@Override
	public Integer consultarEdicionDocumento(Integer idDocumento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOFirmaDocumentosEntity.class);
		criteria.setProjection(Projections.max("conModificaciones"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("pendienteEnvio", 0));
		Integer hayModificacion = (Integer)criteria.uniqueResult();
		return hayModificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#consultarEstadoRegresado(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarEstadoRegresado(Integer idPersona, Integer idDocumento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOFirmaDocumentosEntity.class);
		criteria.setProjection(Projections.max("estatusRegresado"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("idPersona", idPersona));
		Integer clasificando = (Integer)criteria.uniqueResult();
		return clasificando;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#marcarComoLeido(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void marcarComoLeido(Integer idPersona, Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_marcarComoLeido"));
		query.setInteger("idPersona", idPersona);
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("noLeido", Integer.parseInt(Utilidades.mensajeProperties("constante_documento_leido")));
		
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#bloquearDocumento(java.lang.Integer)
	 */
	@Override
	public void bloquearDocumento(Integer idDocumento, Integer idPersona) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_firma_activarBloqueo"));
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idPersonaBloqueado", idPersona);
		query.executeUpdate();
	} 
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#bloquearDocumento(java.lang.Integer)
	 */
	@Override
	public void desbloquearDocumento(Integer idDocumento, Integer idPersona) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_firma_desactivarBloqueo"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#obtenerFirma(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOFirmaDocumentosEntity obtenerFirma(Integer idDocumento, Integer idPersona) {
		
		Criteria criteria = getSession().createCriteria(DTOFirmaDocumentosEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		//criteria.createAlias("dtoPersonaRegreso", "dtoPersonaRegreso",JoinType.INNER_JOIN);
	

		return (DTOFirmaDocumentosEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOFirmaDocumentosInterface#obtenerDetalleDocumento(java.lang.Integer)
	 */
	@Override
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_documento_obtener_detalle"))
				.addScalar("remitentes", StringType.INSTANCE)
				.addScalar("destinatarios", StringType.INSTANCE)
				.addScalar("ccps", StringType.INSTANCE);
		query.setResultTransformer(Transformers
				.aliasToBean(DTODocumentoOficialiaHelper.class));
		query.setInteger("idDocumento", idDocumento);
		return (DTODocumentoOficialiaHelper) query.uniqueResult();	
	} 
	
	@Override
	public Integer consultarRegresado(Integer idPersona, Integer idDocumento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOFirmaDocumentosEntity.class);
		criteria.setProjection(Projections.property("estatusRegresado"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("idPersona", idPersona));
		Integer clasificando = (Integer)criteria.uniqueResult();
		return clasificando;
	} 

}
