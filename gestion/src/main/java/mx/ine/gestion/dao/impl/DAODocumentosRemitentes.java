/**
 * @(#)DAODocumentosRemitentes.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;


import java.util.List;

import mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesID;
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
 * Clase encargada de hacer las consultas al schema de gestion4.
 *
 * @author David Rodríguez Corral
 * @since 21/11/2017
 */
@Scope("prototype")
@Repository("daoDocumentosRemitentes")
public class DAODocumentosRemitentes extends DAOGenericGestion<DTODocumentosRemitentesEntity, DTODocumentosRemitentesID> implements DAODocumentosRemitentesInterface {


	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface#buscarPorDocumento
	 * (mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODocumentosRemitentesEntity> buscarPorDocumento(DTODocumentoEntity documento) {
		Criteria criteria = getSession().createCriteria(this.getPersistentClass()).addOrder(Order.asc("idPersonaRemitente"))
				.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface#consultarRemitentes(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODocumentosRemitentesEntity> consultarRemitentes(Integer idDocumento) {
		Criteria criteria = getSession().createCriteria(DTODocumentosRemitentesEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.createAlias("dtoEstructuraAreas", "dtoEstructuraAreas",JoinType.INNER_JOIN);
		criteria.createAlias("dtoEstructuraAreas.area", "area",JoinType.INNER_JOIN);	

		return (List<DTODocumentosRemitentesEntity>) criteria.list(); 
	}

	@Override
	public void borrarRemitentes(Integer idDocumento) {

		String hql = "delete from DTODocumentosRemitentesEntity where idDocumento= :idDocumento";
		getSession().createQuery(hql).setInteger("idDocumento", idDocumento).executeUpdate();
	}

	/*¨
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface#consultarRemitentesCadena(java.lang.Integer)
	 */
	@Override
	public String consultarRemitentesCadena(Integer idDocumento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_bandejaOficialia_consultarRemitentesCadena"))

				.addScalar("remitentes", StringType.INSTANCE);
					
		//query.setResultTransformer(Transformers
			//	.aliasToBean(String.class));

		query.setInteger("idDocumento", idDocumento);

		return (String) query.uniqueResult();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAODocumentosRemitentesInterface#obtenerDocumentosRemitentes(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstructuraAreasEntity> obtenerPersonasRemitentes(DTODocumentoEntity documento) {
		Query query = getSession()
				.createSQLQuery(
						this.getContainer().getQuery(
								"query_consultar_personas_remitentes"))
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
				
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE);
		
		query.setInteger("idDocumento", documento.getIdDocumento());

		query.setResultTransformer(Transformers.aliasToBean(DTOEstructuraAreasEntity.class));

		return query.list();
	}
}
