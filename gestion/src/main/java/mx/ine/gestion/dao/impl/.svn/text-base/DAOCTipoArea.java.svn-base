/**
 * @(#)DAOCTipoArea.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCTipoAreaInterface;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * Clase que contiene los métodos que harán consultas a la tabla de C_TIPO_AREA de el esquema GESTION 4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
@Scope("prototype")
@Repository("daoCTipoArea")
public class DAOCTipoArea extends DAOGenericGestion<DTOCTipoAreaEntity, Integer> implements DAOCTipoAreaInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCAreasInterface#consultarAreasConOrganigrama(int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCTipoAreaEntity> consultarTipoAreaConDocumento(){

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_consultar_tipo_area_con_documentos"))

					.addScalar("idTipoArea", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE);

			query.setResultTransformer(Transformers.aliasToBean(DTOCTipoAreaEntity.class));
			query.executeUpdate();
			return (List<DTOCTipoAreaEntity>)query.list();
	}
	
	@Override
	public DTOCTipoAreaEntity consultarTipoArea(Integer idTipoArea) {
		
		Criteria criteria = getSession().createCriteria(DTOCTipoAreaEntity.class);
		
		criteria.add(Restrictions.eq("idTipoArea", idTipoArea));

		return (DTOCTipoAreaEntity) criteria.uniqueResult(); 
	}
}
