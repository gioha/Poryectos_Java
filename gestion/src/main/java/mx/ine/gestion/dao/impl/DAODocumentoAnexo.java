package mx.ine.gestion.dao.impl;

import java.util.List;
import mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAODocumentoAnexo.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de DTODocumentoAnexoEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
@Scope("prototype")
@Repository("daoAnexo")
public class DAODocumentoAnexo extends
 DAOGenericGestion<DTODocumentoAnexoEntity, DTODocumentoAnexoID> implements
		DAODocumentoAnexoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface#buscarPorDocumento(mx.ine.
	 * gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODocumentoAnexoEntity> buscarPorDocumento(
			DTODocumentoEntity documento) {

		Criteria criteria = getSession()
				.createCriteria(this.getPersistentClass())
				.addOrder(Order.asc("nombreDocAnexo"))
				.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface#borrarAnexos(java.lang.Integer
	 * )
	 */
	@Override
	public void borrarAnexos(Integer idDocumento) {

		String hql = "delete from DTODocumentoAnexoEntity where idDocumento= :idDocumento";
		getSession().createQuery(hql).setInteger("idDocumento", idDocumento)
				.executeUpdate();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentoAnexoInterface#consultarAnexos(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODocumentoAnexoEntity> consultarAnexos(int idDocumento) {

		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_consultar_anexos_pordocumento"))

				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("idAnexo", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("nombreDocAnexo", StringType.INSTANCE)
				.addScalar("nombreAnexo", StringType.INSTANCE)
				.addScalar("tamanio", DoubleType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTODocumentoAnexoEntity.class));

		query.setInteger("idDocumento", idDocumento);

		return query.list();
	}
	
	
}
