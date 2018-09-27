/**
 * @(#)DAOTurnado.java 09/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOTurnadoInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de apartado de folios.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 30/11/2018
 */
@Scope("prototype")
@Repository("daoTurnado")
public class DAOTurnado extends DAOGenericGestion<Integer, Integer> implements DAOTurnadoInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEstructuraAreasEntity>  consultarPersonasTurnado(DTOEstructuraAreasEntity personaActual) throws Exception {
		Query query = getSession()
			.createSQLQuery(this.getContainer().getQuery("query_turnado_obtener_personas_turnar"))
			.addScalar("idPersona", IntegerType.INSTANCE)
			.addScalar("idArea", IntegerType.INSTANCE)
			.addScalar("tipoArea", IntegerType.INSTANCE)
			.addScalar("nombre", StringType.INSTANCE)
			.addScalar("apellidos", StringType.INSTANCE)
			.addScalar("nombreCompleto", StringType.INSTANCE)
			.addScalar("puesto", StringType.INSTANCE)
			.addScalar("tratamiento", StringType.INSTANCE)
			.addScalar("extensionTel", StringType.INSTANCE)
			.addScalar("cuentaLDAP", StringType.INSTANCE)
			.addScalar("nombreNivelArea", StringType.INSTANCE)
			.addScalar("genero", StringType.INSTANCE)
			.addScalar("estatus", StringType.INSTANCE);
		query.setResultTransformer(Transformers
				.aliasToBean(DTOEstructuraAreasEntity.class));
		query.setInteger("personaActual", personaActual.getIdPersona());
		query.setInteger("idArea", personaActual.getIdArea());
		query.setInteger("tipoArea", personaActual.getTipoArea());

		return query.list();
	}
}
