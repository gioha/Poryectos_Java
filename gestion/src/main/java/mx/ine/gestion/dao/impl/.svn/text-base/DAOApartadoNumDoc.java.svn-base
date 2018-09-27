/**
 * @(#)DAOApartadoNumDoc.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Calendar;

import mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a la tabla de APARTADO_NUM_DOC
 * de el esquema GESTION 4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
@Scope("prototype")
@Repository("daoApartadoNumDoc")
public class DAOApartadoNumDoc extends DAOGenericGestion<DTOApartadoNumDocEntity, DTOApartadoNumDocID> implements DAOApartadoNumDocInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface#consultarUltimoRegistroUsuario(java.lang.String)
	 */
	public DTOApartadoNumDocEntity consultarUltimoRegistroUsuario(String usuario) {
	
		Criteria criteria = this.getSession().createCriteria(DTOApartadoNumDocEntity.class);
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		criteria.add(Restrictions.eq("idPersonaCapturo", usuarioLogueado.getIdPersona()));
		criteria.add(Restrictions.isNull("numDocumento"));
		criteria.addOrder(Order.desc("fechaCaptura"));
		criteria.setMaxResults(1);

		return (DTOApartadoNumDocEntity)criteria.uniqueResult();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface#capturarApartadoFolios(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean capturarApartadoFolios(Integer tipoArea, Integer idArea, Integer idTipoDocumento, Integer idAcronimo, String tipoApartado) {

		//Este método lo usa más de un módulo, si hay cambios hay que revisar antes.
		
		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_apartadoFolios_insertarApartado"));
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		query.setInteger("tipoArea", tipoArea);
		query.setInteger("idArea", idArea);
		query.setInteger("idTipoDocumento", idTipoDocumento);
		query.setInteger("idAcronimo", idAcronimo);
		query.setInteger("anio", Calendar.getInstance().get(Calendar.YEAR));
		query.setString("tipoApartado", tipoApartado);
		query.setParameter("idPersonaCapturo", usuarioLogueado.getIdPersona());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		int resultado = query.executeUpdate();
		
		return resultado > 0 ? true : false;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoNumDocInterface#consultarAcronimoEnUso(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public DTOApartadoNumDocEntity consultarAcronimoEnUso(DTOAcronimoEntity acronimo) {
		Criteria criteria = getSession().createCriteria(DTOApartadoNumDocEntity.class);
		criteria.setMaxResults(1);
		criteria.add(Restrictions.eq("idAcronimo", acronimo.getIdAcronimo()));
		criteria.add(Restrictions.eq("idArea", acronimo.getIdArea()));
		criteria.add(Restrictions.eq("tipoArea", acronimo.getTipoArea()));
		criteria.add(Restrictions.eq("idTipoDocumento", acronimo.getIdTipoDocumento()));
		return (DTOApartadoNumDocEntity) criteria.uniqueResult();
	}

}
