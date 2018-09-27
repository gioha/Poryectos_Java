package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAODocumentosCCPInterface;
import mx.ine.gestion.dto.db.DTODocumentoCCPID;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAOCcp.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de DTODocumentoCcpEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
@Scope("prototype")
@Repository("daoDocumentosCCP")
public class DAODocumentosCCP extends DAOGenericGestion<DTODocumentoCcpEntity, DTODocumentoCCPID> implements DAODocumentosCCPInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOCcpInterface#buscarPorDocumento(mx.ine.gestion
	 * .dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODocumentoCcpEntity> buscarPorDocumento(DTODocumentoEntity documento) {

		Criteria criteria = getSession().createCriteria(this.getPersistentClass()).addOrder(Order.asc("idPersona"))
				.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
		return criteria.list();
	}

	@Override
	public void borrarCcp(Integer idDocumento) {

		String hql = "delete from DTODocumentoCcpEntity where idDocumento= :idDocumento";
		getSession().createQuery(hql).setInteger("idDocumento", idDocumento).executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOCcpInterface#obtenerPersonasCCP(mx.ine.gestion
	 * .dto.db.DTODocumentoEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> obtenerPersonasCCP(DTODocumentoEntity documento) {
		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_consultar_personas_ccp"))
				//Columnas de la tabla de "ESTRUCTURA_AREAS"
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidos", StringType.INSTANCE)
				.addScalar("nombreCompleto", StringType.INSTANCE)
				.addScalar("puesto", StringType.INSTANCE)
				.addScalar("tratamiento", StringType.INSTANCE)
				.addScalar("extensionTel", StringType.INSTANCE)
				.addScalar("genero", StringType.INSTANCE)
				.addScalar("cuentaLDAP", StringType.INSTANCE)
				.addScalar("nombreNivelArea", StringType.INSTANCE)
				.addScalar("verVersionT", IntegerType.INSTANCE)
				.addScalar("estatus", StringType.INSTANCE)
				
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				
				//Columnas de la tabla de "C_AREAS"
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE);
		
		query.setInteger("idDocumento", documento.getIdDocumento());

		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCcpInterface#consultarCCP(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODocumentoCcpEntity> consultarCCP(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(DTODocumentoCcpEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.createAlias("persona", "persona",JoinType.INNER_JOIN);
		criteria.createAlias("persona.area", "area",JoinType.INNER_JOIN);	

		return (List<DTODocumentoCcpEntity>) criteria.list(); 
	}
} 
