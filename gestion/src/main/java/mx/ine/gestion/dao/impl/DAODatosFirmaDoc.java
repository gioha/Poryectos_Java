/**
 * @(#)DAODatosFirmaDoc.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;

import mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface;
import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTODatosFirmaDocID;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository("daoDatosFirmaDoc")
public class DAODatosFirmaDoc extends DAOGenericGestion<DTODatosFirmaDocEntity, DTODatosFirmaDocID> implements DAODatosFirmaDocInterface{

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface#insertar(mx.ine.gestion.dto.db.DTODatosFirmaDocEntity)
	 */
	@Override
	public void insertar(DTODatosFirmaDocEntity dtoFirmaDoc) {
		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_firma_datos_insertar"));

		query.setInteger("idPersonaFirma", dtoFirmaDoc.getIdPersonaFirma());
		query.setInteger("idDocumento", dtoFirmaDoc.getIdDocumento());
		query.setString("idProceso", dtoFirmaDoc.getIdProceso());
		query.setString("hashCadenaOriginal", dtoFirmaDoc.getHashCadenaOriginal());
		query.setString("pkcs7", dtoFirmaDoc.getPkcs7());
		query.setString("idSecuencia", dtoFirmaDoc.getIdSecuencia());

		query.setString("usuario", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		query.setDate("fechaHora", new Date());

		query.executeUpdate();
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface#consultar(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTODatosFirmaDocEntity consultar(Integer idDocumento, Integer idPersonaFirma) {
		Criteria criteria = getSession().createCriteria(DTODatosFirmaDocEntity.class);
		criteria.add(Restrictions.eq("idPersonaFirma", idPersonaFirma));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));

	return (DTODatosFirmaDocEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface#consultar(java.lang.Integer)
	 */
	@Override
	public String consultar(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(DTODatosFirmaDocEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.setProjection(
				Projections.projectionList().add(Projections.distinct(Projections.property("idProceso").as("idProceso"))));
		
	return (String) criteria.uniqueResult();
	
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface#eliminarPorIDFirma(java.lang.Integer)
	 */
	@Override
	public void eliminarPorIDFirma(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				getContainer().getQuery("query_firma_eliminarFirma"));

		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODatosFirmaDocInterface#consultarDatosPorDocumento(java.lang.Integer)
	 */
	@Override
	public DTODatosFirmaDocEntity consultarDatosPorDocumento(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(DTODatosFirmaDocEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		
		criteria.setProjection(
				Projections.projectionList().add(Projections.distinct(Projections.property("idProceso").as("idProceso")))
											.add(Projections.property("hashCadenaOriginal").as("hashCadenaOriginal"))
											.add(Projections.property("cadenaOriginal").as("cadenaOriginal")));
		
		criteria.setResultTransformer(Transformers.aliasToBean(DTODatosFirmaDocEntity.class));
		
	return (DTODatosFirmaDocEntity) criteria.uniqueResult();
	
	}

}
