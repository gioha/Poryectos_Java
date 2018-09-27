/**
 * @(#)DAOEstructuraArea.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase encargada de obtener la información de la base de datos para la tabla
 * ESTRUCTURA_AREA del esquema de GESTION4 en base a consultas hechas por
 * criteria y/o sqlquery.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/08/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
@Repository("daoEstructuraArea")
@Scope("prototype")
public class DAOEstructuraArea extends DAOGenericGestion<DTOEstructuraAreasEntity, Integer> implements DAOEstructuraAreaInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#consultarPersonaXCuenta
	 * (java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaXCuenta(String cuentaINE) {

		Criteria criteria = this.getSession().createCriteria(DTOEstructuraAreasEntity.class);
		criteria.add(Restrictions.eq("cuentaLDAP", cuentaINE));
		return (DTOEstructuraAreasEntity) criteria.uniqueResult();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#
	 * consultarPersonaXCuentaActiva(java.lang.String)
	 */
	@Override
	public DTOEstructuraAreasEntity consultarPersonaXCuentaActiva(String cuentaINE) {

		Criteria criteria = this.getSession().createCriteria(DTOEstructuraAreasEntity.class);
		criteria.createAlias("area", "area", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("cuentaLDAP", cuentaINE));
		criteria.add(Restrictions.eq("estatus", "SI"));
		return (DTOEstructuraAreasEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersona(java
	 * .lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonasXNombreXApellidosXArea(String nombre, String apellido, Integer tipoArea, Integer idArea) {
		
		Criteria criteria = this.getSession().createCriteria(DTOEstructuraAreasEntity.class);
		criteria.createAlias("area", "area");

		if (nombre != null && !nombre.isEmpty())
			criteria.add(Restrictions.ilike("nombre", "%" + nombre + "%"));
		if (apellido != null && !apellido.isEmpty())
			criteria.add(Restrictions.ilike("apellidos", "%" + apellido + "%"));
		if (idArea != null && idArea > 0) {
			criteria.add(Restrictions.eq("idArea", idArea));
			criteria.add(Restrictions.eq("tipoArea", tipoArea));
		}

		criteria.addOrder(Order.asc("cuentaLDAP"));

		return criteria.list();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersonasPorArea(mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(DTOFiltroEstructuraAreaHelper filtro) {
		this.activaMatchModeWords();
		String sentenciaSQL = this.getContainer().getQuery("query_estructura_consultar_area");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>", filtro.obtenerFiltros());
		
		Query query = getSession()
				.createSQLQuery(sentenciaSQL)
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidos", StringType.INSTANCE)
				.addScalar("nombreCompleto", StringType.INSTANCE)
				.addScalar("puesto", StringType.INSTANCE)
				.addScalar("tratamiento", StringType.INSTANCE)
				.addScalar("extensionTel", StringType.INSTANCE)
				.addScalar("cuentaLDAP", StringType.INSTANCE)
				.addScalar("nombreNivelArea", StringType.INSTANCE)
				.addScalar("genero", StringType.INSTANCE)
				.addScalar("verVersionT", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));
		
		query.setInteger("idArea", filtro.getIdArea());
		query.setInteger("tipoArea", filtro.getTipoArea());
		
//		if(filtro.getCuentasLDAP() != null){
//			for (String cuenta: filtro.getCuentasLDAP()) {
//				query.setString("cuentaLDAP", cuenta);
//			}
//		}
//		
//		if(filtro.getListaPersonas() != null){
//			for (DTOEstructuraAreasEntity persona: filtro.getListaPersonas()) {
//				query.setString("cuentaLDAP", persona.getCuentaLDAP());
//			}
//		}
		
		if (filtro.getCoincidencia() != null && !filtro.getCoincidencia().trim().equals("")) {
			query.setParameter("coincidencia", "%" + filtro.getCoincidencia().toUpperCase() + "%");
		}
		return query.list();
	}
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersonasPorArea
//	 * (int, int, java.lang.String)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(int idArea, int tipoArea, String coincidencia, String cuentaLDAP) {
//
//		this.activaMatchModeWords();
//		boolean agregarRestriccion = true;
//		String sentencia = "query_estructura_consultar_personas_misma_area_coincidencia";
//
//		if (coincidencia == null || coincidencia.trim().equals("")) {
//			sentencia = "query_estructura_consultar_personas_misma_area";
//			agregarRestriccion = false;
//		}
//		Query query = getSession()
//				.createSQLQuery(this.getContainer().getQuery(sentencia))
//				.addScalar("idPersona", IntegerType.INSTANCE)
//				.addScalar("idArea", IntegerType.INSTANCE)
//				.addScalar("tipoArea", IntegerType.INSTANCE)
//				.addScalar("nombre", StringType.INSTANCE)
//				.addScalar("apellidos", StringType.INSTANCE)
//				.addScalar("nombreCompleto", StringType.INSTANCE)
//				.addScalar("puesto", StringType.INSTANCE)
//				.addScalar("tratamiento", StringType.INSTANCE)
//				.addScalar("extensionTel", StringType.INSTANCE)
//				.addScalar("cuentaLDAP", StringType.INSTANCE)
//				.addScalar("nombreNivelArea", StringType.INSTANCE)
//				.addScalar("genero", StringType.INSTANCE)
//				.addScalar("verVersionT", IntegerType.INSTANCE);
//		
//		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));
//		
//		query.setInteger("idArea", idArea);
//		query.setInteger("tipoArea", tipoArea);
//		query.setString("cuentaLDAP", cuentaLDAP);
//		
//		if (agregarRestriccion) {
//			query.setParameter("coincidencia", "%" + coincidencia.toUpperCase() + "%");
//		}
//		return query.list();
//	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersonasPorArea
//	 * (int, int)
//	 */
//	@Override
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(int idArea,
//			int tipoArea) {
//
//		return buscarPersonasPorArea(idArea, tipoArea, null, null);
//	}

//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersonasPorArea
//	 * (mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
//	 */
//	@Override
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(
//			DTOEstructuraAreasEntity dtoEstructuraEntity) {
//
//		return buscarPersonasPorArea(dtoEstructuraEntity.getIdArea(),
//				dtoEstructuraEntity.getTipoArea(), null,
//				dtoEstructuraEntity.getCuentaLDAP());
//	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#buscarPersonasPorArea
//	 * (mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.String)
//	 */
//	@Override
//	public List<DTOEstructuraAreasEntity> buscarPersonasPorArea(DTOEstructuraAreasEntity dtoEstructuraEntity, String coincidencia) {
//
//		return buscarPersonasPorArea(dtoEstructuraEntity.getIdArea(),dtoEstructuraEntity.getTipoArea(), coincidencia,dtoEstructuraEntity.getCuentaLDAP());
//	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#actualizarEstatusEstructura(java.lang.Integer)
	 */
	@Override
	public void actualizarEstatusEstructura(Integer idPersona) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_estructura_actualizar_estatus"));
		query.setInteger("idPersona", idPersona);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#reiniciarNombreNivelArea(java.lang.Integer)
	 */
	@Override
	public void reiniciarNombreNivelArea(Integer idPersona) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_estructura_actualizar_nombre_nivel"));
		query.setInteger("idPersona", idPersona);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> buscarIds(List<Integer> ids) {

		Criteria criteria = this.getSession().createCriteria(DTOEstructuraAreasEntity.class);
		criteria.add(Restrictions.in("idPersona", ids));
		criteria.createAlias("area", "area");
		criteria.addOrder(Order.asc("cuentaLDAP"));
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#reiniciarCorresponsalesArea(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void reiniciarCorresponsalesArea(Integer idArea, Integer tipoArea){
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_estructura_reiniciar_corresponsal"));
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#actualizarCorresponsales(java.lang.Integer)
	 */
	@Override
	public void actualizarCorresponsalesArea(Integer idArea, Integer tipoArea){
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_estructura_actualizar_corresponsal"));
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface#consultarPersonasXCuentasXArea(java.util.List, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consultarPersonasXCuentasXArea(List<String> cuentas, Integer tipoArea, Integer idArea) {

		Criteria criteria = this.getSession().createCriteria(DTOEstructuraAreasEntity.class);
		
		criteria.add(Restrictions.in("cuentaLDAP", cuentas));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("idArea", idArea));

		return (List<DTOEstructuraAreasEntity>)criteria.list();
	}

	
}
