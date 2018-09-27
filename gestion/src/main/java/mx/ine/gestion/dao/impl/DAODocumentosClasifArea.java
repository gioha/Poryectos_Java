/**
 * @(#)DAODocumentosClasifArea.java 10/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAODocumentosClasifAreaInterface;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaID;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a la tabla de DOCUMENTOS_CLASIF_AREA
 * de el esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
@Scope("prototype")
@Repository("daoDocumentosClasifArea")
public class DAODocumentosClasifArea extends DAOGenericGestion<DTODocumentosClasifAreaEntity, DTODocumentosClasifAreaID> implements DAODocumentosClasifAreaInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAODocumentosClasifAreaInterface#consultarFoliosPendientes(java.lang.Integer, char)
	 */
	@Override
	public DTOApartadosNumDocOfEntity consultarFoliosPendientes(Integer idOficialia, char estatus) {
		Criteria criteria = getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		criteria.add(Restrictions.eq("idOficialia", idOficialia));
		criteria.add(Restrictions.eq("estatus", estatus));
		
		return (DTOApartadosNumDocOfEntity) criteria.uniqueResult();
	}

	
}
