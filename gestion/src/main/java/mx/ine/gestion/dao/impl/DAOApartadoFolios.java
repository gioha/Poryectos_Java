/**
 * @(#)DAOApartadoFolios.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de apartado de folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
@Scope("prototype")
@Repository("daoApartadoFolios")
public class DAOApartadoFolios extends DAOGenericGestion<Integer, Integer> implements DAOApartadoFoliosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface#consultarTiposDocumentoParaApartado(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentoParaApartado(Integer idArea, Integer tipoArea) {
		
		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_apartadoFolios_obtenerTiposDocumentos"))
					 .addScalar("descripcion", StringType.INSTANCE)
					 .addScalar("idTipoDocumento", IntegerType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTOCTipoDocumentoEntity.class));
		query.setInteger("tipoArea", tipoArea);
		query.setInteger("idArea", idArea);
		
		return (List<DTOCTipoDocumentoEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface#consultarAreasParaApartado(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCAreaEntity> consultarAreasParaApartado(Integer tipoArea, Integer idEntidad) {
		
		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_apartadoFolios_obtenerAreas"))
					 .addScalar("descripcion", StringType.INSTANCE)
					 .addScalar("idArea", IntegerType.INSTANCE)
					 .addScalar("tipoArea", IntegerType.INSTANCE);
	
		query.setResultTransformer(Transformers.aliasToBean(DTOCAreaEntity.class));
		query.setInteger("tipoArea", tipoArea);
		query.setInteger("idEntidad", idEntidad);
		
		return (List<DTOCAreaEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface#consultarFoliosApartadosLazy(
	 * mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOApartadoNumDocEntity> consultarFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		Criteria criteria = this.getSession().createCriteria(DTOApartadoNumDocEntity.class);
		criteria.createAlias("area", "area", JoinType.INNER_JOIN);
		criteria.createAlias("personaCapturo", "personaCapturo", JoinType.INNER_JOIN);
		criteria.createAlias("personaElimino", "personaElimino", JoinType.LEFT_OUTER_JOIN);
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (!usuarioLogueado.getRol().equalsIgnoreCase("GESTION4.ADMIN.OC")) {
			
			criteria.add(Restrictions.eq("idArea", filtros.getIdArea()));
			criteria.add(Restrictions.eq("tipoArea", filtros.getTipoArea()));
		}
		
		criteria.add(Restrictions.eq("tipoApartado", "F"));
		criteria.addOrder(Order.desc("fechaCaptura"));
		criteria.addOrder(Order.desc("idNumeroDocumento"));
		
		criteria.setMaxResults(tamanioMaxPagina);
		criteria.setFirstResult(indicePrimerElemento);
		
		for (Entry<String, Object> filtroColumna : filtrosColumna.entrySet()) {
			
			if (filtroColumna.getKey().equalsIgnoreCase("fechaCaptura")) {
				
				criteria.add(Restrictions.sqlRestriction("to_char(fecha_captura, 'dd/MM/yyyy hh:mi') like '" + filtroColumna.getValue() + "%'"));

			} else  {
			
				criteria.add(Restrictions.ilike(filtroColumna.getKey(), filtroColumna.getValue()+"%"));
			}
		}
		
		return (List<DTOApartadoNumDocEntity>)criteria.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOApartadoFoliosInterface#consultarTotalFoliosApartadosLazy(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public Integer consultarTotalFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros) {

		Criteria criteria = this.getSession().createCriteria(DTOApartadoNumDocEntity.class);

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (!usuarioLogueado.getRol().equalsIgnoreCase("GESTION4.ADMIN.OC")) {
			
			criteria.add(Restrictions.eq("idArea", filtros.getIdArea()));
			criteria.add(Restrictions.eq("tipoArea", filtros.getTipoArea()));
		}
		criteria.add(Restrictions.eq("tipoApartado", "F"));
		
		criteria.setProjection(Projections.rowCount());
		Long resultado = (Long) criteria.uniqueResult();
		
		return (resultado != null ? resultado.intValue() : 0);
	}

}
