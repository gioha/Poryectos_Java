/**
 * @(#)DAOBandejaEntradasOficialia.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaID;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOBandejaOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOMenuBandejaOficialiaHelper;
import mx.ine.gestion.util.Utilidades;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Clase que implementa los métodos que consultarán
 * la tabla de BANDEJA_ENTRADAS_OFICIALIA del esquema gestion4
 * 
 * @author David Rodríguez Corral
 * @since 12/11/2017
 */
@Scope("prototype")
@Repository("daoBandejaEntradasOficialia")
public class DAOBandejaEntradasOficialia extends DAOGenericGestion<DTOBandejaEntradasOficialiaEntity, DTOBandejaEntradasOficialiaID> implements DAOBandejaEntradasOficialiaInterface{
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOInstruccionesInterface#actualizarInstrucciones(mx.ine.gestion.dto.db.DTOInstruccionesEntity)
	 */
	public void insertar(DTOBandejaEntradasOficialiaEntity dtoBandejaOficialia){
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_firma_insertarBandejaOficialia"));
		
		query.setInteger("idOficialia", dtoBandejaOficialia.getIdOficialia());
		query.setInteger("idDocumento", dtoBandejaOficialia.getIdDocumento());
		query.setInteger("anio", dtoBandejaOficialia.getAnio());
		query.setInteger("idAreaRemitente", dtoBandejaOficialia.getIdAreaRemitente());
		query.setInteger("tipoAreaRemitente", dtoBandejaOficialia.getTipoAreaRemitente());
		query.setInteger("idAreaDestinatario", dtoBandejaOficialia.getIdAreaRemitente());
		query.setInteger("tipoAreaDestinatario", dtoBandejaOficialia.getTipoAreaRemitente());
		query.setInteger("enClasificacion", dtoBandejaOficialia.getEnClasificacion());
		query.setInteger("idOficialiaClasificando", dtoBandejaOficialia.getIdOficialiaClasificando());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		query.setDate("fechaHora", new Date());
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarAreasBandejaEntrada(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCAreaEntity> consultarAreasBandejaEntrada(Integer idOficialia) {
		
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_bandeja_oficialia_consultarAreas"))
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("idEntidad", IntegerType.INSTANCE)
				.addScalar("descripcion", StringType.INSTANCE)
				.addScalar("siglas", StringType.INSTANCE);

		query.setInteger("idOficialia", idOficialia);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOCAreaEntity.class));

		return (List<DTOCAreaEntity>) query.list(); 
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarNumeroEntrada(java.lang.Integer)
	 */
	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_consultar_numero_entrada_porusuario"))
				.addScalar("numEntrada", IntegerType.INSTANCE);

		query.setInteger("idOficialia", idOficialia);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOMenuBandejaOficialiaHelper.class));

		return ((DTOMenuBandejaOficialiaHelper) query.uniqueResult()).getNumEntrada(); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#buscarDocumentosDisponiblesLazy(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> buscarDocumentosDisponiblesLazy(
			DTOBandejaOficialiaHelper bandeja, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna) {
		
		Criteria criteria = getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		
		criteria.add(Restrictions.eq("idOficialia", bandeja.getIdOficialia()));
		criteria.add(Restrictions.eq("idAreaDestinatario", bandeja.getIdArea()));
		criteria.addOrder(Order.desc(bandeja.getCampoOrden()));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		criteria.createAlias("dtoOficialia", "dtoOficialia",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasRemitente", "dtoAreasRemitente",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasDestinatario", "dtoAreasDestinatario",JoinType.INNER_JOIN);
		
		criteria.setMaxResults(tamanioMaxPagina)
		.setFirstResult(indicePrimerElemento);
		
		return (List<DTOBandejaEntradasOficialiaEntity>) criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#getTotalDocumentosLazy(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity)
	 */
	@Override
	public Integer getTotalDocumentosLazy(DTOBandejaOficialiaHelper bandeja) {
		Integer total = consultarNumeroEntrada(bandeja.getIdOficialia(), bandeja.getIdArea());
		return total;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarBandeja(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandeja(
			Integer idOficialia, Integer idArea) {
		Criteria criteria = getSession().createCriteria(
				DTOBandejaEntradasOficialiaEntity.class);
		criteria.add(Restrictions.eq("idOficialia", idOficialia));
		criteria.add(Restrictions.eq("idAreaRemitente", idArea));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		criteria.createAlias("dtoDocumento.area", "area",JoinType.INNER_JOIN);	

		return (List<DTOBandejaEntradasOficialiaEntity>) criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarNumeroEntrada(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarNumeroEntrada(Integer idOficialia, Integer idArea) {
		/*
		Criteria criteria = getSession().createCriteria(DTODatosFirmaDocEntity.class);
		criteria.add(Restrictions.eq("idOficialia", idOficialia));
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.setProjection(Projections.rowCount());
		
	return (Integer) criteria.uniqueResult();
	*/ 
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_consultar_numero_entrada_porArea"))
				.addScalar("numEntrada", IntegerType.INSTANCE);

		query.setInteger("idOficialia", idOficialia);
		query.setInteger("idAreaDestinatario", idArea);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOMenuBandejaOficialiaHelper.class));

		return ((DTOMenuBandejaOficialiaHelper) query.uniqueResult()).getNumEntrada(); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarBandejaOficialia(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialia(Integer idOficialia, Integer idArea) {
		
		Criteria criteria = getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		
		criteria.add(Restrictions.eq("idOficialia", idOficialia));
		criteria.add(Restrictions.eq("idAreaDestinatario", idArea));
		criteria.addOrder(Order.desc("fechaHora"));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		criteria.createAlias("dtoOficialia", "dtoOficialia",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasRemitente", "dtoAreasRemitente",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasDestinatario", "dtoAreasDestinatario",JoinType.INNER_JOIN);

		return (List<DTOBandejaEntradasOficialiaEntity>) criteria.list(); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarBandejaOficialiaNombre(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOBandejaEntradasOficialiaEntity> consultarBandejaOficialiaNombre(Integer idOficialia, Integer idArea) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_bandeja_oficialia_consultarBandejaNombre"))
				.addScalar("idOficialia", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idAreaRemitente", IntegerType.INSTANCE)
				.addScalar("tipoAreaRemitente", IntegerType.INSTANCE)
				.addScalar("enClasificacion", IntegerType.INSTANCE)
				.addScalar("idOficialiaClasificando", IntegerType.INSTANCE)
				.addScalar("contieneAnexos", IntegerType.INSTANCE)
				.addScalar("numDocumento", StringType.INSTANCE)
				.addScalar("asunto", StringType.INSTANCE)
				.addScalar("nombreCompleto", StringType.INSTANCE)
				.addScalar("anioDocumento", IntegerType.INSTANCE)
				.addScalar("fechaOficialia", StringType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE);

		query.setInteger("idOficialia", idOficialia);
		query.setInteger("idAreaRemitente", idArea);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEntradasOficialiaEntity.class));

		return (List<DTOBandejaEntradasOficialiaEntity>) query.list(); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#clasificarDocumento(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void clasificarDocumento(Integer idDocumento, Integer idPersona, Integer idArea) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_bandeja_oficialia_clasificar"));
		query.setInteger("enClasificacion",Integer.parseInt(Utilidades.mensajeProperties("constante_bandejaOficialia_clasificar")));
		query.setInteger("idOficialiaClasificando", idPersona);
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idAreaDestinatario", idArea);
		query.setDate("fechaClasificacion", new Date());
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarDocumentoClasificando(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarDocumentoClasificando(Integer idDocumento, Integer idArea) {
		
		Criteria criteria = this.getSession().createCriteria(
				DTOBandejaEntradasOficialiaEntity.class);
		criteria.setProjection(Projections.max("enClasificacion"));
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("idAreaDestinatario", idArea));
		Integer clasificando = (Integer)criteria.uniqueResult();
		return clasificando;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#cancelarClasificarDocumento(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void cancelarClasificarDocumento(Integer idDocumento, Integer idArea, Integer tipoArea) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_bandejaOficialia_cancelarClasificacion"));
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);

		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#eliminarDocumentoPorArea(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity)
	 */
	@Override
	public void eliminarDocumentoPorArea(
			DTOBandejaEntradasOficialiaEntity dtoBandeja) {
		Query query = getSession().createSQLQuery(
				getContainer().getQuery("query_bandejaOficialia_eliminar"));

		query.setInteger("idDocumento", dtoBandeja.getIdDocumento());
		query.setInteger("idAreaDestinatario", dtoBandeja.getIdAreaDestinatario());
		query.setInteger("tipoAreaDestinatario", dtoBandeja.getTipoAreaDestinatario());
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#marcarComoLeido(java.lang.Integer)
	 */
	@Override
	public void marcarComoLeido(Integer idDocumento, Integer idArea, Integer tipoArea) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_bandejaOficialia_marcarComoLeido"));
		query.setInteger("idDocumento", idDocumento);
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.setInteger("noLeido", Integer.parseInt(Utilidades.mensajeProperties("constante_documento_leido")));
		

		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarDocumentoClasificando(mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity, java.lang.Integer)
	 */
	@Override
	public DTOBandejaEntradasOficialiaEntity consultarDocumentoPendiente(DTOBandejaEntradasOficialiaEntity dtoBandeja, Integer idOficialia) {
		Criteria criteria = this.getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		
		criteria.add(Restrictions.eq("idOficialia", dtoBandeja.getIdOficialia()));
		criteria.add(Restrictions.eq("idOficialiaClasificando", idOficialia));
		
		return (DTOBandejaEntradasOficialiaEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarLeido(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarLeido(Integer idDocumento, Integer idArea, Integer tipoArea) {
		Criteria criteria = getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		criteria.add(Restrictions.eq("idDocumento", idDocumento));
		criteria.add(Restrictions.eq("idAreaDestinatario", idArea));
		criteria.add(Restrictions.eq("tipoAreaDestinatario", tipoArea));
		criteria.setProjection(
				Projections.projectionList().add(Projections.distinct(Projections.property("noLeido").as("noLeido"))));
		
		return (Integer) criteria.uniqueResult();
	}
 
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEntradasOficialiaInterface#consultarFoliosPendientes(java.lang.Integer, char)
	 */
	@Override
	public DTOBandejaEntradasOficialiaEntity consultarFoliosPendientes(Integer idOficialia) {
		Criteria criteria = getSession().createCriteria(DTOBandejaEntradasOficialiaEntity.class);
		criteria.add(Restrictions.eq("idOficialia", idOficialia));
		criteria.add(Restrictions.eq("idOficialiaClasificando", idOficialia));
		criteria.createAlias("dtoDocumento", "dtoDocumento",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasRemitente", "dtoAreasRemitente",JoinType.INNER_JOIN);
		criteria.createAlias("dtoAreasDestinatario", "dtoAreasDestinatario",JoinType.INNER_JOIN);
		
		return (DTOBandejaEntradasOficialiaEntity) criteria.uniqueResult();
	} 
	
	

}

