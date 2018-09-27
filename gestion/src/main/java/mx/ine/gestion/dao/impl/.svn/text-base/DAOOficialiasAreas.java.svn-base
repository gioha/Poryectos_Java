/**
 * @(#)DAOOficialiasAreas.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface;
import mx.ine.gestion.dto.db.DTOOficialiasAreasEntity;
import mx.ine.gestion.dto.db.DTOOficialiasAreasID;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que consultaran
 * a la tabla OFICIALIAS_AREAS del esquema GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
@Repository("daoOficialiasAreas")
@Scope("prototype")
public class DAOOficialiasAreas extends DAOGenericGestion<DTOOficialiasAreasEntity, DTOOficialiasAreasID> implements DAOOficialiasAreasInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface#consultarOficialiasPorArea(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOOficialiasAreasEntity> consultarOficialiasPorArea(Integer tipoArea, Integer idArea) {
		Criteria criteria = getSession().createCriteria(DTOOficialiasAreasEntity.class);		
		criteria.add(Restrictions.eq("tipoArea", tipoArea.intValue()));
		criteria.add(Restrictions.eq("idArea", idArea.intValue()));
		criteria.createAlias("dtoOficialia", "Oficialia", JoinType.INNER_JOIN);
		return (List<DTOOficialiasAreasEntity>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface#consultarAreasOficialia(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreaEntity> consultarAreasOficialia(Integer idOficialia) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_bandeja_oficialia_consultarAreas"))
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("idEntidad", IntegerType.INSTANCE)
				.addScalar("descripcion", StringType.INSTANCE)
				.addScalar("siglas", StringType.INSTANCE);

		query.setInteger("idOficialia", idOficialia);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOCAreaEntity.class));

		return (List<DTOCAreaEntity>) query.list();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiasAreasInterface#consultarTodasLasAreasOficialia()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreaEntity> consultarTodasLasAreasOficialia() {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_bandeja_oficialia_consultarTodasLasAreas"))
					.addScalar("idArea", IntegerType.INSTANCE)
					.addScalar("tipoArea", IntegerType.INSTANCE)
					.addScalar("idEntidad", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
					.addScalar("siglas", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));

		return (List<DTOCAreaEntity>) query.list(); 
	}

}
