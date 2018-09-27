/**
 * @(#)DAOCAreas.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaID;

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
 * Clase que implementa los métodos que consultaran la tabla de C_AREAS del
 * esquema GESTION4
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
@Scope("prototype")
@Repository("daoCAreas")
public class DAOCAreas extends DAOGenericGestion<DTOCAreaEntity, DTOCAreaID> implements DAOCAreasInterface {

	/**
	 * Sobreescritura de constructor para mandar la entidad relacionada al DAO
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public DAOCAreas() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasSinOrganigrama(int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreasSinOrganigrama(int tipoArea, int idEntidad) {
			String queryString;
			if(idEntidad < 0) {
				queryString = "query_estructura_consultar_areas_sin_estructura_tipo";
			} else {
				queryString = "query_estructura_consultar_areas_sin_estructura";		
			}
			Query query = this.getSession().createSQLQuery(this.getContainer().getQuery(queryString))
					.addScalar("idArea", IntegerType.INSTANCE)
					.addScalar("tipoArea", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
					.addScalar("siglas", StringType.INSTANCE)
					.addScalar("idEntidad", IntegerType.INSTANCE);
			query.setInteger("tipoArea", tipoArea);
			if(idEntidad >= 0) {
				query.setInteger("idEntidad", idEntidad);				
			}
			query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));

			return (List<DTOCAreaEntity>)query.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasConOrganigrama(int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreasConOrganigrama(int tipoArea, int idEntidad) {

			//Método utilizado por más de un módulo
		
			String queryString;

			if(idEntidad < 0) {
				queryString = "query_estructura_consultar_areas_con_estructura_tipo";
			} else {
				queryString = "query_estructura_consultar_areas_con_estructura";		
			}

			Query query = this.getSession().createSQLQuery(this.getContainer().getQuery(queryString))
					.addScalar("idArea", IntegerType.INSTANCE)
					.addScalar("tipoArea", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
					.addScalar("siglas", StringType.INSTANCE)
					.addScalar("idEntidad", IntegerType.INSTANCE);
			
			query.setInteger("tipoArea", tipoArea);
			if(idEntidad >= 0) {
				query.setInteger("idEntidad", idEntidad);				
			}
			query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));

			return (List<DTOCAreaEntity>)query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasDestinatariasConOrganigrama(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreasDestinatariasConOrganigrama(String nombreArea){
		
		Criteria criteria = this.getSession().createCriteria(DTOCAreaEntity.class);
		//criteria.createAlias("area", "area");

		if (nombreArea != null && !nombreArea.isEmpty())
			criteria.add(
							Restrictions.or(
											Restrictions.ilike("descripcion", "%" + nombreArea + "%"), 
											Restrictions.ilike("siglas", "%" + nombreArea + "%")
											)
						);
		
		criteria.addOrder(Order.asc("idArea"));

		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasConOrganigrama(int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarEntidadesConOrganigrama() throws Exception {
	
		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_estructura_obtener_estados_con_estructura"))
					.addScalar("idEntidad", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));

		return (List<DTOCAreaEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarEntidadesSinOrganigrama()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarEntidadesSinOrganigrama() throws Exception {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_estructura_obtener_estados_sin_estructura"))
					.addScalar("idEntidad", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));

		return (List<DTOCAreaEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreas(int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad) {

		//ESTE MÉTODO ES USADO POR MÁS DE UN MÓDULO
		
		Criteria criteria = getSession().createCriteria(DTOCAreaEntity.class);
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		if(idEntidad >= 0) {
			criteria.add(Restrictions.eq("idEntidad", idEntidad));			
		}
		criteria.addOrder(Order.asc("descripcion"));
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#buscarAreasPorNombre(java.lang.String)
	 */
	@Override
	public DTOCAreaEntity buscarAreasPorNombre(String areastr) {

		this.activaMatchModeWords();
		
		Criteria criteria = getSession().createCriteria(DTOCAreaEntity.class);
		criteria.add(Restrictions.ilike("descripcion", areastr));
		criteria.addOrder(Order.asc("descripcion"));
		criteria.setMaxResults(1);
		
		return (DTOCAreaEntity)criteria.uniqueResult();
	}

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasAcronimos()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreaEntity> consultarAreasAcronimos() {
		Query query = getSession().createSQLQuery(getContainer().getQuery("query_consultar_areas_acronimos"))
					 .addScalar("idArea", IntegerType.INSTANCE)
					 .addScalar("tipoArea", IntegerType.INSTANCE)
					 .addScalar("idEntidad", IntegerType.INSTANCE)
					 .addScalar("descripcion", StringType.INSTANCE)
					 .addScalar("siglas", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));
		return (ArrayList<DTOCAreaEntity>) query.list();
	}

}