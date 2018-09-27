/**
 * @(#)DAOEdicionesDocumento.java 26/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoID;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoEdicionesDocumento")
public class DAOEdicionesDocumento extends
		DAOGenericGestion<DTOEdicionesDocumentoEntity, DTOEdicionesDocumentoID>
		implements DAOEdicionesDocumentoInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface#obtenerListaEdiciones
	 * (int, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdiciones(
			int idDocumento, int estatus) {

		estatus = (estatus == 1) ? 1 : 0;

		Query query = getSession()
				.createSQLQuery(
						this.getContainer()
								.getQuery("query_consulta_ediciones"))

				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idEdicion", IntegerType.INSTANCE)
				.addScalar("estatus", IntegerType.INSTANCE)
				.addScalar("usuarioEdicion", StringType.INSTANCE)
				.addScalar("fechaHoraEdicion", DateType.INSTANCE)
				.addScalar("nombreCompleto", StringType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOEdicionesDocumentoEntity.class));

		query.setInteger("estatus", estatus);
		query.setInteger("idDocumento", idDocumento);

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOEdicionesDocumentoInterface#insertar(java.util.List)
	 */
	@Override
	public void insertar(DTOEdicionesDocumentoEntity dtoEdiciones) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_firma_insertarEdiciones"));
		
		query.setInteger("idDocumento", dtoEdiciones.getIdDocumento());
		query.setInteger("anio", dtoEdiciones.getAnio());
		query.setInteger("estatus", dtoEdiciones.getEstatus());
		query.setString("usuarioEdicion", SecurityContextHolder.getContext().getAuthentication().getName());
		query.setDate("fechaHoraEdicion", new Date());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		query.setDate("fechaHora", new Date());
		query.executeUpdate();
	}
	
	@Override
	public Integer obtenerIdEdicion(Integer idDocumento) {
		Criteria criteria = this.getSession().createCriteria(
				DTOEdicionesDocumentoEntity.class);
		criteria.setProjection(Projections.max("idEdicion"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		Integer ultimoId = (Integer)criteria.uniqueResult();
		return ultimoId;
	}
	
}
