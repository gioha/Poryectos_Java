package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAODocumentoInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAODocumento.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de DTODocumentoEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
@Scope("prototype")
@Repository("daoDocumento")
public class DAODocumento extends DAOGenericGestion<DTODocumentoEntity, Integer> implements DAODocumentoInterface {

//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAODocumentoInterface#eliminarDocumento(mx.ine
//	 * .gestion.dto.db.DTODocumentoEntity)
//	 */
//	@Override
//	public void eliminarDocumento(DTODocumentoEntity dtoDocumentoEntity) {
//		Query query = getSession().createSQLQuery(
//				this.getContainer().getQuery(
//						"query_documento_actualizar_estatus"));
//		query.setInteger("idDocumento", dtoDocumentoEntity.getIdDocumento());
//		query.setCharacter("estatusDocumento",
//				Utilidades.mensajeProperties("documento_eliminado").charAt(0));
//
//		query.executeUpdate();
//
//	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see
//	 * mx.ine.gestion.dao.inter.DAODocumentoInterface#eliminarDocumento(mx.ine
//	 * .gestion.dto.db.DTOBorradorDocumentosEntity)
//	 */
//	@Override
//	public void eliminarDocumento(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
//		eliminarDocumento(dtoBorradorDocumentosEntity.getDocumento());
//
//	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#consultarDocumento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTODocumentoEntity consultarDocumento(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(
				DTODocumentoEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));

		return (DTODocumentoEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#activarFirmaDocumento(java.lang.Integer)
	 */
	@Override
	public void activarFirmaDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_activarFirmaDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
	
	@Override
	public void activarValidacionDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_validacion_activarValidacionDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#eliminarValidacionEnDocumento(java.lang.Integer)
	 */
	@Override
	public void eliminarValidacionEnDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_eliminarValidacionDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#desactivarFirmaDocumento(java.lang.Integer)
	 */
	@Override
	public void desactivarFirmaDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_desactivarFirmaDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#activarEdicionDocumento(java.lang.Integer)
	 */
	@Override
	public void activarEdicionDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_activarEdicionDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#desActivarEdicionDocumento(java.lang.Integer)
	 */
	@Override
	public void desactivarEdicionDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_desactivarEdicionDocumento"));
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentoInterface#obtenerTipoDocumento(java.lang.Integer)
	 */
	@Override
	public String obtenerTipoDocumento(Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_oficialiaPartes_obtenerTipoDocumento"))
				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("descripcion", StringType.INSTANCE);

		query.setInteger("idDocumento", idDocumento);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOCTipoDocumentoEntity.class));
		DTOCTipoDocumentoEntity tipo = (DTOCTipoDocumentoEntity) query.uniqueResult();

		return tipo.getDescripcion(); 
	}

}
