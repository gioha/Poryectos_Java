/**
 * @(#)DAOCSecciones.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCSeccionesInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que consultaran la tabla de C_SECCIONES del
 * esquema GESTION4
 * 
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
@Scope("prototype")
@Repository("daoCSecciones")
public class DAOCSecciones extends DAOGenericGestion<DTOCSeccionesEntity, Integer> implements DAOCSeccionesInterface {

	/**
	 * Sobreescritura de constructor para mandar la entidad relacionada al DAO
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public DAOCSecciones() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCSeccionesInterface#consultarSecciones()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCSeccionesEntity> consultarSecciones() {
		Criteria criteria = getSession().createCriteria(DTOCSeccionesEntity.class);
		
		criteria.addOrder( Order.asc("idSeccion") );
		
		return (List<DTOCSeccionesEntity>) criteria.list();
	}

	
}