/**
 * @(#)DAOCapturaDocumentos.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCapturaDocumentosInterface;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase DAO que genera los querys utilizados en la captura de documentos QUE NO VAN A UNA TABLA EN GENERAL
 * (Hacen varios cruces)
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
@Repository("daoCapturaDocumentos")
@Scope("prototype")
public class DAOCapturaDocumentos extends DAOGenericGestion<Integer, Integer> implements DAOCapturaDocumentosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOCapturaDocumentosInterface#consultarEntidadesCapturadasEnGestion()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstadosEntity> consultarEntidadesCapturadasEnGestion() {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_capturaDocumentos_consultaEstadosDisponibles"))
								.addScalar("idEstado", IntegerType.INSTANCE)
								.addScalar("nombreEstado", StringType.INSTANCE);
	
		query.setResultTransformer(Transformers.aliasToBean(DTOEstadosEntity.class));
		
		return (List<DTOEstadosEntity>)query.list();
	}
}
