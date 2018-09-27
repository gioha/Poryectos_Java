/**
 * @(#)DAOJerarquiaPersonas.java 07/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase encargada de obtener la información de la base de datos para la tabla
 * JERARQUIA_PERSONAS del esquema de GESTION4 en base a consultas hechas por
 * criteria y/o sqlquery.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 07/09/2017
 */
@Repository("daoJerarquiaPersonas")
@Scope("prototype")
public class DAOJerarquiaPersonas extends DAOGenericGestion<DTOJerarquiaPersonasEntity, DTOJerarquiaPersonasID> implements DAOJerarquiaPersonasInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#consultarTitulares
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarTitulares() {
		
		Criteria criteria = getSession().createCriteria(DTOJerarquiaPersonasEntity.class);
		
		criteria.add(Restrictions.eq("idPersonaPadre", 0));
		criteria.add(Restrictions.eq("nivelPadre", 0));
		criteria.createAlias("dtoEstructuraAreas", "aliasPersona", JoinType.INNER_JOIN);
		criteria.createAlias("area", "area", JoinType.INNER_JOIN);

		return criteria.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#consultarTitular(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOJerarquiaPersonasEntity consultarTitular(Integer tipoArea, Integer idArea) {

		Criteria criteria = getSession().createCriteria(DTOJerarquiaPersonasEntity.class);
		criteria.createAlias("dtoEstructuraAreas", "dtoEstructuraAreas");
		
		criteria.add(Restrictions.eq("idPersonaPadre", 0));
		criteria.add(Restrictions.eq("nivelPadre", 0));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.setMaxResults(1);
		
		return (DTOJerarquiaPersonasEntity)criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#
	 * consultarEstructuraPorArea(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarEstructuraPorArea(Integer tipoArea, Integer idArea) {

		Criteria criteria = getSession().createCriteria(
				DTOJerarquiaPersonasEntity.class);
		criteria.add(Restrictions.eq("tipoArea", tipoArea.intValue()));
		criteria.add(Restrictions.eq("idArea", idArea.intValue()));
		criteria.createAlias("dtoEstructuraAreas", "Persona",JoinType.INNER_JOIN);
		criteria.createAlias("dtoEstructuraAreasPadre", "Padre", JoinType.LEFT_OUTER_JOIN);
		criteria.addOrder(Order.asc("idNivel"));
		return criteria.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#
	 * consultarEstructurasTitulares()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consultarEstructurasTitulares(String coincidencia) {

		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_consultar_titulares_estructura"))
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
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE)
				.addScalar("verVersionT", IntegerType.INSTANCE);
		
		query.setParameter("coincidencia", "%" + coincidencia.toUpperCase()
				+ "%");

		query.setResultTransformer(Transformers
				.aliasToBean(DTOEstructuraAreasEntity.class));

		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#consultarExisteEstructura(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOJerarquiaPersonasEntity> consultarExisteEstructura(Integer tipoArea, Integer idArea) {
		Criteria criteria = getSession().createCriteria(
				DTOJerarquiaPersonasEntity.class);
		criteria.add(Restrictions.eq("tipoArea", tipoArea.intValue()));
		criteria.add(Restrictions.eq("idArea", idArea.intValue()));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);

		return criteria.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#
	 * consultaTitularesDestinatarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consultarTitularesDestinatarios(
			DTODocumentoEntity dtoDocumentoEntity, int idNivel) {
		
		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_consultar_titulares_destinatarios"))
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
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE)
				.addScalar("verVersionT", IntegerType.INSTANCE);
		
		query.setInteger("idDocumento", dtoDocumentoEntity.getIdDocumento());
		query.setInteger("idNivel", idNivel);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOEstructuraAreasEntity.class));

		return query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface#buscarArea(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> buscarArea(String porArea) {

		Criteria criteria = this.getSession().createCriteria(DTOJerarquiaPersonasEntity.class);
		criteria.createAlias("area", "area");
		criteria.add(Restrictions.ilike("area.descripcion", "%" + porArea + "%"));
		criteria.add(Restrictions.eq("idNivel", 1));
		criteria.setProjection(Projections.property("idPersona"));
		return criteria.list();
	}
}
