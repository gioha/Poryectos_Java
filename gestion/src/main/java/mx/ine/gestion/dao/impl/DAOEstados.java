/**
 * @(#)DAOEstados.java 13/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOEstadosInterface;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * Clase que contiene los métodos que harán consultas a la tabla de ESTADOS de el esquema GEOGRAFICOINE
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 13/12/2017
 */
@Scope("prototype")
@Repository("daoEstados")
public class DAOEstados extends DAOGenericGestion<DTOEstadosEntity, Integer> implements DAOEstadosInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEstadosInterface#consultarEstados()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstadosEntity> consultarEstadosSinOC() {

		Criteria criteria = this.getSession().createCriteria(DTOEstadosEntity.class);
		criteria.addOrder(Order.asc("nombreEstado"));
		criteria.add(Restrictions.ne("idEstado", 0));
		
		return (List<DTOEstadosEntity>)criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstadosEntity> consultarEstadosSinOrganigrama() {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_estados_consultar_estados_sin_organigrama"))
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idCorte", IntegerType.INSTANCE)
				.addScalar("nombreEstado", StringType.INSTANCE)
				.addScalar("abreviatura", StringType.INSTANCE)
				.addScalar("idCircunscripcion", IntegerType.INSTANCE)
				.addScalar("circunscripcion", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOEstadosEntity.class));

		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstadosEntity> consultarEstadosConOrganigrama() {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_estados_consultar_estados_con_organigrama"))
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idCorte", IntegerType.INSTANCE)
				.addScalar("nombreEstado", StringType.INSTANCE)
				.addScalar("abreviatura", StringType.INSTANCE)
				.addScalar("idCircunscripcion", IntegerType.INSTANCE)
				.addScalar("circunscripcion", StringType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOEstadosEntity.class));
		return query.list();
	}
}
