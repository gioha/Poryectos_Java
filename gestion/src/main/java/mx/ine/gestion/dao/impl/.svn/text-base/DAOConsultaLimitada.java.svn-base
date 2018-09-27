/**
 * @(#)DAOConsultaLimitada.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dao.inter.DAOConsultaLimitadaInterface;
import mx.ine.gestion.util.AliasToEntityLinkedMapResultTransformer;
import mx.org.ine.servicios.dto.db.DTOEstado;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase de consultas
 *
 * @autor INE
 * @since 12/07/2016
 */
@Scope("prototype")
@Repository("daoConsultaLimitada")
public class DAOConsultaLimitada extends DAOGenericGestion<DTOEstado, Integer> implements DAOConsultaLimitadaInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.dao.DAOConsultaLimitadaInterface#haciendoMagia(java.lang.String)
	 */
	@Override
	public List<Map<String,Object>> haciendoMagia(String magiaQry) {

		Session session = getSession();
		Query query = session.createSQLQuery(magiaQry); 
		query.setResultTransformer(AliasToEntityLinkedMapResultTransformer.INSTANCE); 
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> resultadoConsulta = query.list();

		return resultadoConsulta;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.pautas.dao.DAOConsultaLimitadaInterface#usandoElPoder(java.lang.String)
	 */
	@Override
	public int usandoElPoder(String magiaQry) throws Exception{

		Session session = getSession();
		Query query=session.createSQLQuery(magiaQry); 
		int result = query.executeUpdate();
		
		return result;
	}
}
