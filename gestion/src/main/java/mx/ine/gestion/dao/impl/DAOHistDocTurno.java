/**
 * @(#)DAOHistDocTurno.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 *
 */
@Scope("prototype")
@Repository("daoHistDocTurno")
public class DAOHistDocTurno extends
		DAOGenericGestion<DTOHistDocTurnoEntity, DTOHistDocTurnoID> implements DAOHistDocTurnoInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface#obtenerHistoricoTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public DTOHistDocTurnoEntity obtenerHistoricoTurnado(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona) {
		DTOHistDocTurnoEntity histTurno = null;

		if (documento != null && persona != null) {
			Criteria criteria = getSession().createCriteria(DTOHistDocTurnoEntity.class);

			criteria.add(Restrictions.eq("idPersonaHist",persona.getIdPersona()));
			criteria.add(Restrictions.eq("idDocumento",documento.getIdDocumento()));

			histTurno = (DTOHistDocTurnoEntity) criteria.uniqueResult();
		}
		return histTurno;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface#obtenerHistoricoTurnado(java.lang.Integer)
	 */
	@Override
	public DTOHistDocTurnoEntity obtenerHistoricoTurnado(Integer idHistoricoREcep) {
		DTOHistDocTurnoEntity histTurno = null;

		if (idHistoricoREcep != null ) {
			Criteria criteria = getSession().createCriteria(DTOHistDocTurnoEntity.class);

			criteria.add(Restrictions.eq("idHistoricoRecep", idHistoricoREcep));

			histTurno = (DTOHistDocTurnoEntity) criteria.uniqueResult();
		}
		return histTurno;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocTurnoInterface#obtenerPersonasTurnadas(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHistDocTurnoEntity> obtenerPersonasTurnadas(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno) {

		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_hist_doc_turno_consultar_personas_turnadas"))

				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
				.addScalar("idPersonaHist", IntegerType.INSTANCE)
				.addScalar("idEstatusTurno", IntegerType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				.addScalar("comentarioGrl", StringType.INSTANCE)
				;
		
		query.setResultTransformer(Transformers.aliasToBean(DTOHistDocTurnoEntity.class));

		query.setInteger("idDocumento", documento.getIdDocumento());
		query.setInteger("idPersona", personaTurno.getIdPersona());
		
		return query.list();
	}
}