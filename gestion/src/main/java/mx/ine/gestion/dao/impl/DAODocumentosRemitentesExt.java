/**
 * @(#)DAODocumentosRemitentes.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import mx.ine.gestion.dao.inter.DAODocumentosRemitentesExtInterface;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesExtEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesExtID;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que interactúan con la tabla DOCUMENTOS_REMITENTES_EXT del esquema GESTION4.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
@Repository("daoDocumentosRemitentesExt")
@Scope("prototype")
public class DAODocumentosRemitentesExt extends DAOGenericGestion<DTODocumentosRemitentesExtEntity, DTODocumentosRemitentesExtID> implements DAODocumentosRemitentesExtInterface {

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAODocumentosRemitentesExtInterface#consultarRemitentesExtAsignados(Integer idArea)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> consultarRemitentesExtAsignados(Integer idArea) {
		Query query = getSession().createSQLQuery(getContainer().getQuery("query_remitentes_externos"))
					 .addScalar("idRemitente", IntegerType.INSTANCE);

		query.setParameter("idArea", idArea);

		return (ArrayList<Integer>) query.list();
	}

}
