/**
 * @(#)DAOCSecciones.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOCCategoriasInterface;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasID;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que consultaran la tabla de C_CATEGORIAS del
 * esquema GESTION4
 * 
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
@Scope("prototype")
@Repository("daoCCategorias")
public class DAOCCategorias extends DAOGenericGestion<DTOCCategoriasEntity, DTOCCategoriasID> implements DAOCCategoriasInterface {

	/**
	 * Sobreescritura de constructor para mandar la entidad relacionada al DAO
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public DAOCCategorias() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOCCategoriasInterface#consultarCategorias()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCCategoriasEntity> consultarCategorias(Integer idSeccion) {
		Criteria criteria = getSession().createCriteria(DTOCCategoriasEntity.class);
		criteria.add(Restrictions.eq("idSeccion", idSeccion));
		criteria.addOrder( Order.asc("idCategoria") );
		
		return (List<DTOCCategoriasEntity>) criteria.list();
	}

	
}