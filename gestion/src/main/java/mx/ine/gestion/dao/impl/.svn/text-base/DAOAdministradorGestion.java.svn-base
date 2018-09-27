/**
 * @(#)DAOAdministradorGestion.java 03/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOAdministradorGestionInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de administración.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
@Scope("prototype")
@Repository("daoAdministradorGestion")
public class DAOAdministradorGestion extends DAOGenericGestion<Integer, Integer> implements DAOAdministradorGestionInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAdministradorGestionInterface#consultarAreasRegistradasParaUnaPersonaDeOficialia(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreasRegistradasParaUnaPersonaDeOficialia(Integer idOficilia) {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_administrador_obtenAreasOficialia"))
										.addScalar("idArea", IntegerType.INSTANCE)
										.addScalar("tipoArea", IntegerType.INSTANCE)
										.addScalar("descripcion", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));
		query.setInteger("idOficilia", idOficilia);

		return (List<DTOCAreaEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAdministradorGestionInterface#consultarOpcionesMenuParaGestion(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOMenuHelper> consultarOpcionesMenuParaGestion(String rol) {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_menu_obtenerMenus"))
				.addScalar("idMenu", IntegerType.INSTANCE)
				.addScalar("idSubmenu", IntegerType.INSTANCE)
				.addScalar("idModulo", IntegerType.INSTANCE)
				.addScalar("idAccion", IntegerType.INSTANCE)
				.addScalar("nombreMenu", StringType.INSTANCE)
				.addScalar("nombreSubmenu", StringType.INSTANCE)
				.addScalar("tipoModulo", StringType.INSTANCE)
				.addScalar("nombreModulo", StringType.INSTANCE)
				.addScalar("urlMenu", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTOMenuHelper.class));
		query.setString("ROLUSER", rol);
	
		return (List<DTOMenuHelper>)query.list();
	}
}
