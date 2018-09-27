/**
 * @(#)DAONotificaciones.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesID;
import mx.ine.gestion.util.Utilidades;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * @author Homero Fidel Villanueva
 */
@Scope("prototype")
@Repository("daoNotificaciones")
public class DAONotificaciones extends DAOGenericGestion<DTONotificacionesEntity, DTONotificacionesID> implements DAONotificacionesInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesInterface#consultarDocumentosPendientesXIdPersonaIdModulo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarDocumentosPendientesXIdPersonaIdModulo(Integer idPersona, Integer idModulo) {

		Criteria criteria = this.getSession().createCriteria(DTONotificacionesEntity.class);
		criteria.setProjection(Projections.sqlProjection("documentos_pendientes + documentos_pendientes_2 + documentos_pendientes_3 + documentos_pendientes_4 AS documentosPendientes", 
								new String[]{"documentosPendientes"}, 
								new Type[]{IntegerType.INSTANCE}));
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.add(Restrictions.eq("idModulo", idModulo));

		return (Integer)criteria.uniqueResult();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAONotificacionesInterface#insertarNotificacion
	 * (mx.ine.gestion.dto.db.DTOEstructuraAreasEntity,
	 * mx.ine.gestion.dto.db.DTONotificacionesEntity, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void insertarNotificacion(DTONotificacionesEntity notificacion, String accion) {

		// Revisar que la notificación exista
		List<DTONotificacionesEntity> listaNotificacion;
		DTONotificacionesEntity notificacionAux = new DTONotificacionesEntity();
		int valida;

		Criteria criteria = getSession().createCriteria(DTONotificacionesEntity.class);
		criteria.add(Restrictions.eq("idPersona", notificacion.getIdPersona()));
		criteria.add(Restrictions.eq("idModulo", notificacion.getIdModulo()));
		criteria.add(Restrictions.eq("idApartado", notificacion.getIdApartado()));

		listaNotificacion = criteria.list();
		if (listaNotificacion.size() == 1 && accion.equals(Utilidades.mensajeProperties("constante_notificacion_incrementar"))) {
			
			notificacionAux = listaNotificacion.get(0);
			notificacionAux.setDocumentosPendientes(notificacionAux.getDocumentosPendientes() + 1);
			
			
		} else if (listaNotificacion.size() == 1 && accion.equals(Utilidades.mensajeProperties("constante_notificacion_decrementar"))) {
			
			notificacionAux = listaNotificacion.get(0);
			valida = (notificacionAux.getDocumentosPendientes() < 1 )? 0 : notificacionAux.getDocumentosPendientes()- 1 ;
			notificacionAux.setDocumentosPendientes(valida);
			
		} else if (listaNotificacion.size() == 1 && accion.equals(Utilidades.mensajeProperties("constante_notificacion_inicializar"))) {
			
			notificacionAux = listaNotificacion.get(0);
			notificacionAux.setDocumentosPendientes(0);
		
		} else if(listaNotificacion.size() == 0){
			
			notificacionAux = notificacion;
			notificacionAux.setDocumentosPendientes(1);
		}
		
		modificar(notificacionAux);

	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesInterface#consultarNotificacion(int, int)
	 */
	@Override
	public DTONotificacionesEntity consultarNotificacion(int idPersona, int idModulo) {
		Criteria criteria = getSession().createCriteria(
				DTONotificacionesEntity.class);
		criteria.add(Restrictions.eq("idPersona", idPersona));
		criteria.add(Restrictions.eq("idModulo", idModulo));
		return (DTONotificacionesEntity) criteria.uniqueResult();
	}
	
}