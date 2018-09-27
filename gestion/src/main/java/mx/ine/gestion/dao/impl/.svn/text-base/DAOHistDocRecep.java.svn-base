/**
 * @(#)DAOHistDocRecep.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOHistDocRecepInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;

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
@Repository("daoHistDocRecep")
public class DAOHistDocRecep extends DAOGenericGestion<DTOHistDocRecepEntity, Integer> implements DAOHistDocRecepInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocRecepInterface#obtenerHistoricoRecepcion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public DTOHistDocRecepEntity obtenerHistoricoRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona) {
		DTOHistDocRecepEntity histRecep = null;
		
		if(documento != null && persona!= null){
			Criteria criteria = getSession().createCriteria(DTOHistDocRecepEntity.class);
			
			criteria.add(Restrictions.eq("idPersonaHist", persona.getIdPersona()));
			criteria.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));

			histRecep = (DTOHistDocRecepEntity) criteria.uniqueResult();
		}
		return histRecep;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocRecepInterface#obtenerHistoricoRecepcion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer)
	 */
	@Override
	public DTOHistDocRecepEntity obtenerHistoricoRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona, Integer estatus) {
		DTOHistDocRecepEntity histRecep = null;
		
		if(documento != null && persona!= null){
			Criteria criteria = getSession().createCriteria(DTOHistDocRecepEntity.class);
			
			criteria.add(Restrictions.eq("idPersonaHist", persona.getIdPersona()));
			criteria.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
			if(estatus != null){
				criteria.add(Restrictions.eq("idEstatusRecep", estatus));
			}

			histRecep = (DTOHistDocRecepEntity) criteria.uniqueResult();
		}
		return histRecep;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocRecepInterface#obtenerListaHistoricoRecepcion(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHistDocRecepEntity> obtenerListaHistoricoRecepcion(DTODocumentoEntity documento, boolean personasTurnaron) {
		String sentenciaSQL = (personasTurnaron) ? "query_hist_doc_recep_consultar_personas_turnaron":"query_hist_doc_recep_consultar_personas_turnados" ;
		Query query;
		//Integer idEstatusRecep = (personasTurnaron) ? Integer.parseInt(Utilidades.mensajeProperties("estatus_turnado_por")) : Integer.parseInt(Utilidades.mensajeProperties("estatus_turnado_a")) ;
		
		if(personasTurnaron){
			query = getSession()
					.createSQLQuery(this.getContainer().getQuery(sentenciaSQL))
					.addScalar("idDocumento", IntegerType.INSTANCE)
					.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
					.addScalar("idHistoricoPadre", IntegerType.INSTANCE)
					.addScalar("idPersonaHist", IntegerType.INSTANCE)
					.addScalar("idEstatusRecep", IntegerType.INSTANCE)
					.addScalar("fechaHora", DateType.INSTANCE)
					
					//Join tabla ESTRUCTURA_AREAS
					.addScalar("nombre", StringType.INSTANCE)
					.addScalar("apellidos", StringType.INSTANCE)
					.addScalar("cuentaLDAP", StringType.INSTANCE)
			;
		}else{
			query = getSession()
					.createSQLQuery(this.getContainer().getQuery(sentenciaSQL))
					.addScalar("idDocumento", IntegerType.INSTANCE)
					.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
					.addScalar("idHistoricoPadre", IntegerType.INSTANCE)
					.addScalar("idPersonaHist", IntegerType.INSTANCE)
					.addScalar("idEstatusRecep", IntegerType.INSTANCE)
					.addScalar("fechaHora", DateType.INSTANCE)
	
					//Join tabla ESTRUCTURA_AREAS
					.addScalar("nombre", StringType.INSTANCE)
					.addScalar("apellidos", StringType.INSTANCE)
					.addScalar("cuentaLDAP", StringType.INSTANCE)
	
					//Join tabla HIST_DOC_TURNO
					.addScalar("idEstatusTurno", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
			;
		}
		query.setParameter("idDocumento",documento.getIdDocumento());
		
		//query.setParameter("idEstatusRecep",idEstatusRecep);
		
		query.setResultTransformer(Transformers.aliasToBean(DTOHistDocRecepEntity.class));

		return query.list();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocRecepInterface#obtenerListaPersonasTurnaron(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHistDocRecepEntity> obtenerListaPersonasUnicasTurnaron(DTODocumentoEntity documento) {
		Query query = getSession()
				.createSQLQuery(this.getContainer().getQuery("query_hist_doc_recep_consultar_personas_unica_turnaron"))
				.addScalar("idPersonaHist", IntegerType.INSTANCE);
		
		query.setParameter("idDocumento",documento.getIdDocumento());
		
		query.setParameter("idDocumento",documento.getIdDocumento());

		query.setResultTransformer(Transformers.aliasToBean(DTOHistDocRecepEntity.class));

		return query.list();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHistDocRecepInterface#obtenerHistRecepcion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public Integer obtenerIdHistRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno, DTOEstructuraAreasEntity personaTurnada) {
		Integer res = null;
		
		if(documento != null && personaTurno!= null && personaTurnada != null){
			Criteria criteria = getSession().createCriteria(DTOHistDocRecepEntity.class);
			
			criteria.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
			criteria.add(Restrictions.eq("idHistoricoPadre", personaTurno.getIdPersona()));
			criteria.add(Restrictions.eq("idPersonaHist", personaTurnada.getIdPersona()));
			
			res = ((DTOHistDocRecepEntity) criteria.uniqueResult()).getIdHistoricoRecep();
		}
		return res;
	}
}