/**
 * @(#)DAOInstrucciones.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;

import mx.ine.gestion.dao.inter.DAOInstruccionesInterface;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que consultarán
 * la tabla de INSTRUCCIONES del esquema gestion4
 * 
 * @author David Rodríguez Corral
 * @since 28/08/2017
 */
@Scope("prototype")
@Repository("daoInstrucciones")
public class DAOInstrucciones extends DAOGenericGestion<DTOInstruccionesEntity, DTOInstruccionesID> implements DAOInstruccionesInterface{
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOInstruccionesInterface#consultarInstrucccionesAtencion()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOInstruccionesEntity> consultarInstruccciones(Integer idPersona, Integer tipoInstruccion) {
		Criteria criteria = this.getSession().createCriteria(DTOInstruccionesEntity.class);
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.add(Restrictions.eq("estatus", "A"));
		criteria.add(Restrictions.eq("tipoInstruccion", tipoInstruccion));
		criteria.addOrder(Order.asc("ordenamiento"));
		
		return (List<DTOInstruccionesEntity>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOInstruccionesInterface#eliminarInstruccionesLogicas(java.lang.Integer)
	 */
	@Override
	public void eliminarInstruccionesLogicas(Integer idPersona) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery("query_instrucciones_eliminarInstruccionesLogicas"));
		query.setInteger("idPersona", idPersona);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOInstruccionesInterface#actualizarInstrucciones(mx.ine.gestion.dto.db.DTOInstruccionesEntity)
	 */
	public void actualizarInstrucciones(DTOInstruccionesEntity dtoInstrucciones){
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_instrucciones_insertar"));
		
		query.setInteger("idPersona", dtoInstrucciones.getIdPersona());
		query.setInteger("tipoInstruccion", dtoInstrucciones.getTipoInstruccion());
		query.setString("descripcion", dtoInstrucciones.getDescripcion());
		query.setInteger("diasRespuesta", dtoInstrucciones.getDiasRespuesta());
		query.setInteger("conNotificacion", dtoInstrucciones.getConNotificacion());
		query.setString("estatus", dtoInstrucciones.getEstatus());
		query.setInteger("ordenamiento", dtoInstrucciones.getOrdenamiento());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		query.setDate("fechaHora", new Date());
		query.executeUpdate();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOInstruccionesInterface#obtenerInstrucciones(java.lang.Integer)
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOInstruccionesEntity> obtenerInstrucciones(Integer idHistoricoRecep) {
		List<DTOInstruccionesEntity> listaInstrucciones = null;
		if(idHistoricoRecep != null){
			Query query = getSession()
					.createSQLQuery(
							this.getContainer().getQuery(
									"query_consultar_instrucciones_turnadas"))
					.addScalar("idPersona", IntegerType.INSTANCE)
					.addScalar("idInstruccion", IntegerType.INSTANCE)
					.addScalar("tipoInstruccion", IntegerType.INSTANCE)
					.addScalar("diasRespuesta", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
					.addScalar("conNotificacion", IntegerType.INSTANCE)
					.addScalar("estatus", StringType.INSTANCE)
					.addScalar("ordenamiento", IntegerType.INSTANCE)
					.addScalar("usuario", StringType.INSTANCE)
					.addScalar("fechaHora", DateType.INSTANCE)
					
					;
			
			query.setInteger("idHistoricoRecep", idHistoricoRecep);

			query.setResultTransformer(Transformers.aliasToBean(DTOInstruccionesEntity.class));

			listaInstrucciones = query.list();
		}
		return listaInstrucciones;
	}

}


