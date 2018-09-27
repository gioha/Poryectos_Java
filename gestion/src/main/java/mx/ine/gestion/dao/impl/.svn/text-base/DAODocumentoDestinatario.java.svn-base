package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAODocumentoDestinatario.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de DTODocumentoDestinatarioEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
@Scope("prototype")
@Repository("daoDestinatario")
public class DAODocumentoDestinatario extends DAOGenericGestion<DTODocumentoDestinatarioEntity, DTODocumentoDestinatarioID> implements
		DAODocumentoDestinatarioInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#buscarPorDocumento(
	 * mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODocumentoDestinatarioEntity> buscarPorDocumento(DTODocumentoEntity documento) {

		Criteria criteria = getSession().createCriteria(this.getPersistentClass()).addOrder(Order.asc("idPersonaDestinataria"))
				.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#borrarDestinatarios
	 * (java.lang.Integer)
	 */
	@Override
	public void borrarDestinatarios(Integer idDocumento) {

		String hql = "delete from DTODocumentoDestinatarioEntity where idDocumento= :idDocumento";
		getSession().createQuery(hql).setInteger("idDocumento", idDocumento).executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#
	 * consutarPersonasDestinatarios(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consutarPersonasDestinatarios(int idDocumento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_consultar_documentos_destinatarios"))

				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idPersona", IntegerType.INSTANCE)
				//.addScalar("idPersonaDestinataria", IntegerType.INSTANCE)
				
				//Columnas de la tabla de "ESTRUCTURA_AREAS"
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidos", StringType.INSTANCE)
				.addScalar("puesto", StringType.INSTANCE)
				.addScalar("tratamiento", StringType.INSTANCE)
				.addScalar("extensionTel", StringType.INSTANCE)
				.addScalar("cuentaLDAP", StringType.INSTANCE)
				.addScalar("nombreNivelArea", StringType.INSTANCE)
				.addScalar("genero", StringType.INSTANCE)
				.addScalar("estatus", StringType.INSTANCE)
				;
		
		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));

		query.setInteger("idDocumento", idDocumento);

		return query.list();
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#consutarDocumentosDestinatarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODocumentoDestinatarioEntity> consutarDocumentosDestinatarios(DTODocumentoEntity documento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_consultar_documentos_destinatarios"))

				//Columnas de la tabla de "DOCUMENTOS_DESTINATARIOS"
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idPersonaDestinataria", IntegerType.INSTANCE)
				.addScalar("tipoDestinatario", IntegerType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				
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
				
				.addScalar("usuarioEstr", StringType.INSTANCE)
				.addScalar("fechaHoraEstr", DateType.INSTANCE)
				
				//Columnas de la tabla de "C_AREAS"
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTODocumentoDestinatarioEntity.class));

		query.setInteger("idDocumento", documento.getIdDocumento());

		return query.list();

	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#consultarOficialias(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity> consultarOficialias(int idDocumento) {
		Criteria criteria = getSession().createCriteria(DTODocumentoDestinatarioEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.createAlias("persona", "p",JoinType.INNER_JOIN);
		criteria.setProjection(
				Projections.projectionList().add(Projections.distinct(Projections.property("p.idArea").as("idArea")))
											.add(Projections.property("p.tipoArea").as("tipoArea")));
		criteria.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));
		
		return criteria.list(); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#consultarPersonasOficialias(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarPersonasOficialias(Integer idDocumento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_consultar_personas_oficialiasPorArea"))

				.addScalar("idOficialia", IntegerType.INSTANCE)
				.addScalar("idAreaRemitente", IntegerType.INSTANCE)
				.addScalar("tipoAreaRemitente", IntegerType.INSTANCE)
				.addScalar("idAreaDestinatario", IntegerType.INSTANCE)
				.addScalar("tipoAreaDestinatario", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEntradasOficialiaEntity.class));

		query.setInteger("idDocumento", idDocumento);

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#consutarDestinatarios(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODocumentoDestinatarioEntity> consutarDestinatarios(Integer idDocumento) {
		
		Criteria criteria = getSession().createCriteria(DTODocumentoDestinatarioEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.createAlias("persona", "persona",JoinType.INNER_JOIN);
		criteria.createAlias("persona.area", "area",JoinType.INNER_JOIN);	

		return (List<DTODocumentoDestinatarioEntity>) criteria.list(); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoDestinatarioInterface#consutarAreasDestinatarios(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTONotificacionesOFEntity> consultarAreasDestinatarios(Integer idDocumento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_firma_consultarAreasOficialias"))

				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers
				.aliasToBean(DTONotificacionesOFEntity.class));

		query.setInteger("idDocumento", idDocumento);

		return query.list();
	}

	
}
