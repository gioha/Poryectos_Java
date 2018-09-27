/**
 * @(#)DAOApartadosNumDocOf.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfID;
import mx.ine.gestion.util.Utilidades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a la tabla de APARTADOS_NUM_DOC_OF
 * de el esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @update José Miguel Ortiz
 * @since 26/03/2018
 */
@Scope("prototype")
@Repository("daoApartadosNumDocOf")
public class DAOApartadosNumDocOf extends DAOGenericGestion<DTOApartadosNumDocOfEntity, DTOApartadosNumDocOfID> implements DAOApartadosNumDocOfInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface#consultarFolioDisponible(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOApartadosNumDocOfEntity consultarFolioDisponible(Integer idArea, Integer tipoArea) {
		
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_oficialiaPartes_consultarFolioDisponible"))

				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("idFolioOf", IntegerType.INSTANCE)
				.addScalar("idOficialiaAparto", IntegerType.INSTANCE)
				.addScalar("folioOficialia", StringType.INSTANCE)
				.addScalar("estatus", CharacterType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("tipoApartado", StringType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE);
		
		query.setResultTransformer(Transformers
				.aliasToBean(DTOApartadosNumDocOfEntity.class));

		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.setCharacter("estatus", Utilidades.mensajeProperties("constante_OficialiaPartes_liberar").charAt(0));

		return (DTOApartadosNumDocOfEntity) query.uniqueResult();
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface#obtenerUltimoFolio(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer obtenerUltimoFolio(Integer idArea, Integer tipoArea) {
		Criteria criteria = getSession().createCriteria(DTOApartadosNumDocOfEntity.class);
		
		criteria.setProjection(Projections.max("idFolioOf"));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));	
		
		return (Integer) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface#modificarEstatus(java.lang.Integer, java.lang.Integer, java.lang.String, char)
	 */
	@Override
	public void modificarEstatus(Integer idArea, Integer tipoArea, String folio, char estatus) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_oficialiaPartes_liberarFolio"));
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.setString("folioOficialia", folio);
		query.setCharacter("estatus", estatus);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface#consultarFolioPendiente(java.lang.Integer, char)
	 */
	@Override
	public DTOApartadosNumDocOfEntity consultarFolioPendiente(Integer idArea, Integer tipoArea, Integer idOficialia, char estatus, String tipoApartado) {
		Criteria criteria = getSession().createCriteria(DTOApartadosNumDocOfEntity.class);
		criteria.add(Restrictions.eq("idOficialiaAparto", idOficialia));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("estatus", estatus));
		criteria.add(Restrictions.eq("tipoApartado", tipoApartado));
		
		return (DTOApartadosNumDocOfEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOApartadosNumDocOfInterface#consultarFoliosDocOf()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOApartadosNumDocOfEntity> consultarFoliosDocOf() {
		Criteria criteria = getSession().createCriteria(DTOApartadosNumDocOfEntity.class);
		criteria.addOrder(Order.asc("folioOficialia"));
		return (ArrayList<DTOApartadosNumDocOfEntity>) criteria.list();
	}

}
