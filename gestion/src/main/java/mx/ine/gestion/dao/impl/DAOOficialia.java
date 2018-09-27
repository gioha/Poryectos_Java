/**
 * @(#)DAOOficialia.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAOOficialiaInterface;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que consultaran
 * a la tabla OFICIALIAS del esquema GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
@Scope("prototype")
@Repository("daoOficialia")
public class DAOOficialia extends DAOGenericGestion<DTOOficialiaEntity, Integer> implements DAOOficialiaInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiaInterface#consultarOficialiaXCuenta(java.lang.String)
	 */
	@Override
	public DTOOficialiaEntity consultarOficialiaXCuenta(String cuentaINE) {

		Criteria criteria = this.getSession().createCriteria(DTOOficialiaEntity.class);
		criteria.add(Restrictions.eq("cuentaLDAP", cuentaINE));

		return (DTOOficialiaEntity) criteria.uniqueResult();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiaInterface#consultarOficialiaXCuentaActiva(java.lang.String)
	 */
	@Override
	public DTOOficialiaEntity consultarOficialiaXCuentaActiva(String cuentaINE) {

		Criteria criteria = this.getSession().createCriteria(DTOOficialiaEntity.class);
		criteria.add(Restrictions.eq("cuentaLDAP", cuentaINE));
		criteria.add(Restrictions.eq("estatus", "SI"));

		return (DTOOficialiaEntity) criteria.uniqueResult();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOOficialiaInterface#actualizarEstatusOficialia(java.lang.Integer)
	 */
	@Override
	public void actualizarEstatusOficialia(Integer idOficialia) {

		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_oficialia_actualizar_estatus"));
		query.setInteger("idOficialia", idOficialia);
		query.executeUpdate();
	}
}
