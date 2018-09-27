/**
 * @(#)DAOAcronimo.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.ine.gestion.bo.inter.BOAcronimoInterface;
import mx.ine.gestion.dao.inter.DAOAcronimoInterface;
import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase de la capa de dao para la entidad de Acronimo.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
@Scope("prototype")
@Repository("daoAcronimo")
public class DAOAcronimo extends DAOGenericGestion<DTOAcronimoEntity, DTOAcronimoID> implements DAOAcronimoInterface {

	@Autowired
	@Qualifier("boAcronimo")
	private BOAcronimoInterface boAcronimoInterface;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOAcronimoInterface#buscarAcronimos(mx.ine.
	 * gestion.dto.db.catalogos.DTOCAreas)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcronimoEntity> consultarAcronimosXAreaXTipoDocumento(Integer tipoArea, Integer idArea, Integer tipoDocumento) {

		//ESTE MÉTODO LO ESTAN USANDO EN VARIOS MÓDULOS SI LE CAMBIAS AVISA!.
		
		Criteria crit = getSession().createCriteria(DTOAcronimoEntity.class);
		crit.createAlias("area", "area", JoinType.INNER_JOIN);
		crit.add(Restrictions.eq("idArea", idArea));
		crit.add(Restrictions.eq("tipoArea", tipoArea));
		crit.add(Restrictions.eq("idTipoDocumento", tipoDocumento));
		crit.addOrder(Order.asc("descripcion"));

		return crit.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAcronimoInterface#obtenerListaDeAcronimosLazy(mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper, 
	 * int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOAcronimoEntity> obtenerListaDeAcronimosLazy(DTOFiltrosAcronimosHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {
		
		String queryS = boAcronimoInterface.procesaQueryConsultaAcronimos(this.getContainer().getQuery("query_acronimos_consultarAcronimosLazy"), filtrosColumna); 
		Query query = this.getSession().createSQLQuery(queryS)
										 .addScalar("descripcionArea", StringType.INSTANCE)
										 .addScalar("idArea", IntegerType.INSTANCE)
										 .addScalar("tipoArea", IntegerType.INSTANCE)
										 .addScalar("idTipoDocumento", IntegerType.INSTANCE)
										 .addScalar("idAcronimo", IntegerType.INSTANCE)
										 .addScalar("descripcion", StringType.INSTANCE)
										 .addScalar("acronimoAgrupacion", StringType.INSTANCE)
										 .addScalar("documentosUtilizados", IntegerType.INSTANCE)
										 .addScalar("descripcionTipoDoc", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DTOAcronimoEntity.class));
		
		
		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOAcronimoEntity>)query.list();
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAcronimoInterface#obtenerTotalDeAcronimosLazy(mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper)
	 */
	@Override
	public Integer obtenerTotalDeAcronimosLazy() {
		
		String queryS = boAcronimoInterface.procesaQueryConsultaTotalesAcronimos(this.getContainer().getQuery("query_acronimos_consultarAcronimosLazyTotal")); 
		Query query = this.getSession().createSQLQuery(queryS);
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOAcronimoInterface#obtenerIdAcronimo(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@Override
	public Integer obtenerIdAcronimo(DTOAcronimoEntity acronimo) {
		Criteria criteria = this.getSession().createCriteria(
				DTOAcronimoEntity.class);
		criteria.setProjection(Projections.max("idAcronimo"));
		criteria.add(Restrictions.eq("idArea", acronimo.getIdArea()));
		criteria.add(Restrictions.eq("tipoArea", acronimo.getTipoArea()));
		criteria.add(Restrictions.eq("idTipoDocumento", acronimo.getIdTipoDocumento()));
		Integer ultimoId = (Integer)criteria.uniqueResult();
		return ultimoId;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAcronimoInterface#consultarAcronimoEnUso(mx.ine.gestion.dto.db.DTOAcronimoEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcronimoEntity> consultarAcronimoEnUso(DTOAcronimoEntity acronimo) throws Exception{
		Criteria criteria = this.getSession().createCriteria(DTOAcronimoEntity.class);
		criteria.setMaxResults(1);
		criteria.add(Restrictions.eq("idAcronimo", acronimo.getIdAcronimo()));
		criteria.add(Restrictions.eq("idArea", acronimo.getIdArea()));
		criteria.add(Restrictions.eq("tipoArea", acronimo.getTipoArea()));
		criteria.add(Restrictions.eq("idTipoDocumento", acronimo.getIdTipoDocumento()));
		//criteria.createAlias("acronimo.documento", "documento", JoinType.INNER_JOIN);
		return (List<DTOAcronimoEntity>)criteria.list();
	}

	/**
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOAcronimoInterface#consultarAcronimosPorArea(Integer idArea)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOAcronimoEntity> consultarAcronimosPorArea(Integer idArea) {
		Query query = getSession().createSQLQuery(getContainer().getQuery("query_consultar_acronimos_por_area"))
					.addScalar("descripcionArea", StringType.INSTANCE)
					.addScalar("idArea", IntegerType.INSTANCE)
					.addScalar("tipoArea", IntegerType.INSTANCE)
					.addScalar("idTipoDocumento", IntegerType.INSTANCE)
					.addScalar("idAcronimo", IntegerType.INSTANCE)
					.addScalar("descripcion", StringType.INSTANCE)
					.addScalar("acronimoAgrupacion", StringType.INSTANCE)
					.addScalar("documentosUtilizados", IntegerType.INSTANCE)
					.addScalar("descripcionTipoDoc", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(DTOAcronimoEntity.class));
		query.setParameter("idArea", idArea);

		return (ArrayList<DTOAcronimoEntity>) query.list();
	}

}
