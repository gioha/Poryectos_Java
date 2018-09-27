/**
 * @(#)DAOPlantillas.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.ine.gestion.dao.inter.DAOPlantillasInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
 * cruces a diferentes tablas) para el módulo de configuración/plantillas.
 * 
 * @author Luis Urrutia
 * @since 05/12/2017
 */
@Repository("daoPlantillas")
@Scope("prototype")
public class DAOPlantillas extends DAOGenericGestion<Integer, Integer> implements DAOPlantillasInterface {
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillasInterface#obtenerTipoDocumentos()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCTipoDocumentoEntity> obtenerTipoDocumentos(){
		
		String queryFinal = "";
		
		queryFinal = this.getContainer().getQuery("query_consultar_tipo_documentos");
		
		Query query = getSession()
				.createSQLQuery(queryFinal)

				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("descripcion", StringType.INSTANCE);
				
		query.setResultTransformer(Transformers
				.aliasToBean(DTOCTipoDocumentoEntity.class));

		return query.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillasInterface#obtenerAcronimos()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcronimoEntity> obtenerAcronimos(DTOFiltrosPlantillaHelper filtros){
	
		String queryFinal = "";
		
		queryFinal = this.getContainer().getQuery("query_consultar_acronimos");
		
		Query query = getSession()
				.createSQLQuery(queryFinal)

				.addScalar("idAcronimo", IntegerType.INSTANCE)
				.addScalar("descripcion", StringType.INSTANCE)
				.addScalar("numeroRepeticiones", IntegerType.INSTANCE);
				
		query.setResultTransformer(Transformers
				.aliasToBean(DTOAcronimoEntity.class));
		
		query.setInteger("idArea", filtros.getIdArea());
		query.setInteger("tipoArea", filtros.getTipoArea());
		query.setInteger("idTipoDocumento", filtros.getIdTipoDocumentoSeleccionado());
		query.setInteger("idPersona", filtros.getIdPersona());

		return query.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillasInterface#buscarPlantillas()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOPlantillaEntity> consultarPlantillasLazy(DTOFiltrosPlantillaHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {

		Criteria criteria = this.getSession().createCriteria(DTOPlantillaEntity.class);
		criteria.createAlias("tipoDocumento", "tipoDocumento", JoinType.INNER_JOIN);
		criteria.createAlias("acronimo", "acronimo", JoinType.INNER_JOIN);
		
		for (Entry<String, Object> filtroColumna : filtrosColumna.entrySet()) {
	
			criteria.add(Restrictions.ilike(filtroColumna.getKey(), filtroColumna.getValue()+"%"));
		}
		
		criteria.setMaxResults(tamanioMaxPagina);
		criteria.setFirstResult(indicePrimerElemento);
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		criteria.add(Restrictions.eq("idPersona", usuarioLogueado.getIdPersona()));

		return (List<DTOPlantillaEntity>)criteria.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillasInterface#consultarTotalPlantillasLazy(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public Integer consultarTotalPlantillasLazy(DTOFiltrosPlantillaHelper filtros) {

		Criteria criteria = this.getSession().createCriteria(DTOPlantillaEntity.class);
		criteria.createAlias("tipoDocumento", "tipoDocumento", JoinType.INNER_JOIN);
		criteria.createAlias("acronimo", "acronimo", JoinType.INNER_JOIN);
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		criteria.add(Restrictions.eq("idPersona", usuarioLogueado.getIdPersona()));
		
		criteria.setProjection(Projections.rowCount());
		Long resultado = (Long) criteria.uniqueResult();
		
		return (resultado != null ? resultado.intValue() : 0);
	}

}
