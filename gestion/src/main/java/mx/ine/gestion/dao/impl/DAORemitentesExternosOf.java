/**
 * @(#)DAORemitentesExternosOf.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfID;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a la tabla de REMITENTES_EXTERNOS_OF del esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @since 19/01/2018
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
@Repository("daoRemitentesExternosOf")
@Scope("prototype")
public class DAORemitentesExternosOf extends DAOGenericGestion<DTORemitentesExternosOfEntity, DTORemitentesExternosOfID> implements DAORemitentesExternosOfInterface {
	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarFolioDisponible(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTORemitentesExternosOfEntity> consultarRemitente(Integer idArea, Integer tipoArea) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);
		
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.addOrder(Order.asc("nombreRemitente"));
		criteria.addOrder(Order.asc("dependencia"));
		
		return (List<DTORemitentesExternosOfEntity>) criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarUltimoRemitente(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarUltimoRemitente(Integer idArea, Integer tipoArea) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);
		
		criteria.setProjection(Projections.max("idRemitente"));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));	
		
		return (Integer) criteria.uniqueResult();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarUltimoRemitente(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTORemitentesExternosOfEntity> consultarRemitenteAreaNombreDependencia(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);

		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		if(!nombreRemitente.isEmpty()) {
			criteria.add(Restrictions.ilike("nombreRemitente", "%" + nombreRemitente + "%"));
		}
		if(!dependencia.isEmpty()) {
			criteria.add(Restrictions.ilike("dependencia", "%" + dependencia + "%"));
		}

		return criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarRemitenteNombreDependencia(String nombreRemitente, String dependencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTORemitentesExternosOfEntity> consultarRemitenteNombreDependencia(String nombreRemitente, String dependencia) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);

		if(!nombreRemitente.isEmpty()) {
			criteria.add(Restrictions.ilike("nombreRemitente", "%" + nombreRemitente + "%"));
		}
		if(!dependencia.isEmpty()) {
			criteria.add(Restrictions.ilike("dependencia", "%" + dependencia + "%"));
		}

		return criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea)
	 */
	@Override
	public DTORemitentesExternosOfEntity consultarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);

		criteria.add(Restrictions.eq("idRemitente", idRemitente));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));

		return (DTORemitentesExternosOfEntity) criteria.uniqueResult();
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAORemitentesExternosOfInterface#consultarRemitenteAreaNombreDependenciaExacto(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia)
	 */
	@Override
	public DTORemitentesExternosOfEntity consultarRemitenteAreaNombreDependenciaExacto(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia) {
		Criteria criteria = getSession().createCriteria(DTORemitentesExternosOfEntity.class);

		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("nombreRemitente", nombreRemitente));
		criteria.add(Restrictions.eq("dependencia", dependencia));

		return (DTORemitentesExternosOfEntity) criteria.uniqueResult();
	}

}
