/**
 * DAOHistDocCreacion.java 18/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;

import mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * @author Homero Villanueva Dominguez 18/09/2017
 */
@Scope("prototype")
@Repository("daoHistDocCreacion")
public class DAOHistDocCreacion extends
		DAOGenericGestion<DTOHistDocCreacionEntity, DTOHistDocCreacionID> implements DAOHistDocCreacionInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#insertar(int,
	 * int, int, int)
	 */
	@Override
	public void guardarHistCrea(DTOHistDocCreacionEntity dtoHistDocCreacionEntity) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_hist_doc_creacion_insertar"));

		query.setInteger("idDocumento", dtoHistDocCreacionEntity.getIdDocumento());
		query.setInteger("anio", dtoHistDocCreacionEntity.getAnio());
		query.setInteger("idPersonaHist", dtoHistDocCreacionEntity.getIdPersonaHist());
		query.setInteger("idEstatus", dtoHistDocCreacionEntity.getIdEstatus());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		query.setDate("fechaHora", new Date());

		query.executeUpdate();
	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#insertar(mx.ine.
//	 * gestion.dto.db.DTOBorradorDocumentosEntity, int)
//	 */
//	@Override
//	public void insertar(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, int idEstatus) {
//		insertar(dtoBorradorDocumentosEntity.getIdDocumento(), dtoBorradorDocumentosEntity.getAnio(), dtoBorradorDocumentosEntity.getIdPersona(), idEstatus);
//
//	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#insertar(mx.ine.
//	 * gestion.dto.db.DTODocumentoEntity,
//	 * mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, int)
//	 */
//	@Override
//	public void insertar(DTODocumentoEntity dtoDocumento,
//			DTOEstructuraAreasEntity dtoEstructuraAreasEntity, int idEstatus) {
//
//		insertar(dtoDocumento.getIdDocumento(), dtoDocumento.getAnio(),
//				dtoEstructuraAreasEntity.getIdPersona(), idEstatus);
//
//	}

//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#insertar(mx.ine.
//	 * gestion.dto.db.DTODocumentoEntity, java.util.List, int)
//	 */
//	@Override
//	public void insertar(DTODocumentoEntity dtoDocumento,
//			List<DTOEstructuraAreasEntity> listaDTOEstructuraAreasEntity,
//			int idEstatus) {
//		for (DTOEstructuraAreasEntity dtoEstructuraAreasEntity : listaDTOEstructuraAreasEntity) {
//			insertar(dtoDocumento.getIdDocumento(), dtoDocumento.getAnio(),
//					dtoEstructuraAreasEntity.getIdPersona(), idEstatus);
//		}
//	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#registrarEnHistorial
	 * (mx.ine.gestion.dto.db.DTOHistDocCreacionEntity)
	 */
	@Override
	public void registrarEnHistorial(DTOHistDocCreacionEntity dtoHistorial) {
		
		Query query = getSession().createSQLQuery(
				this.getContainer()
						.getQuery("query_hist_doc_creacion_insertar"));

		query.setInteger("idDocumento", dtoHistorial.getIdDocumento());
		query.setInteger("anio", dtoHistorial.getAnio());
		query.setInteger("idPersonaHist", dtoHistorial.getIdPersonaHist());
		query.setInteger("idEstatus", dtoHistorial.getIdEstatus());
		query.setString("usuario", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		query.setDate("fechaHora", new Date());
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocCreacionInterface#consultarHistorialPorIdDocumento(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(Integer idDocumento) {
		
		Criteria criteria = getSession().createCriteria(DTOHistDocCreacionEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.addOrder(Order.desc("fechaHora"));
	
		return criteria.list();
	}
	
	@Override
	public Integer obtenerIdHistorial(Integer idDocumento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOHistDocCreacionEntity.class);
		criteria.setProjection(Projections.max("idHistorico"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		Integer ultimoId = (Integer)criteria.uniqueResult();
		return ultimoId;
	}

}
