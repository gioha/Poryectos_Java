/**
 * @(#)DAOComentariosDocumento.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.Date;
import java.util.List;

import mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Clase encargada de hacer las consultas al schema de gestion4.
 *
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
@Repository("daoComentariosDocumento")
@Scope("prototype")
public class DAOComentariosDocumento extends DAOGenericGestion<DTOComentariosDocumentoEntity, DTOComentariosDocumentoID> implements DAOComentariosDocumentoInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface#guardarComentario
	 * (mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity)
	 */
	@Override
	public void guardarComentario(DTOComentariosDocumentoEntity dtoComentario, DTOEstructuraAreasEntity persona) {

		Query queryInsercionComentario, queryInsercionEstatus, queryConsultarId;
		Integer idComentario;
		//Se obtiene el id del comentario que se insertará
		queryConsultarId = getSession().createSQLQuery(this.getContainer().getQuery("query_consulta_siguiente_id_comentario"));
		queryConsultarId.setInteger("idDocumento", dtoComentario.getIdDocumento());
		//queryConsultarId.setResultTransformer(Transformers.aliasToBean(Integer.class));
		Object o = queryConsultarId.uniqueResult();
		idComentario = Integer.valueOf(o.toString());
		
		//Se inserta el comentario en la BD
		queryInsercionComentario = getSession().createSQLQuery(this.getContainer().getQuery("query_firma_guardarComentario"));

		queryInsercionComentario.setInteger("idDocumento", dtoComentario.getIdDocumento());
		queryInsercionComentario.setInteger("idComentario", idComentario);
		queryInsercionComentario.setInteger("anio", dtoComentario.getAnio());
		queryInsercionComentario.setString("comentarios", dtoComentario.getComentarios());
		queryInsercionComentario.setInteger("estatus", dtoComentario.getEstatus());
		queryInsercionComentario.setString("usuarioComento", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionComentario.setCharacter("tipoComentario", dtoComentario.getTipoComentario());
		
		queryInsercionComentario.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionComentario.setDate("fechaHora", new Date());
		queryInsercionComentario.executeUpdate();

		//Se inserta el COMENTARIOS_NO_LEIDOS
		queryInsercionEstatus = getSession().createSQLQuery(this.getContainer().getQuery("query_insertar_comentario_estatus"));
		queryInsercionEstatus.setInteger("idComentario", idComentario);
		queryInsercionEstatus.setInteger("idDocumento", dtoComentario.getIdDocumento());
		queryInsercionEstatus.setInteger("idPersona", persona.getIdPersona());
		queryInsercionEstatus.setInteger("idArea", persona.getIdArea());
		queryInsercionEstatus.setInteger("tipoArea", persona.getTipoArea());
		queryInsercionEstatus.setInteger("noLeido", 0);
		
		queryInsercionEstatus.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionEstatus.setDate("fechaHora", new Date());
		queryInsercionEstatus.executeUpdate();
		
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface#guardarComentarioTurnado(mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity)
	 */
	@Override
	public Integer guardarComentarioTurnado(DTOComentariosDocumentoEntity dtoComentario, DTOEstructuraAreasEntity persona) {
		
		Query queryInsercionComentario, queryInsercionEstatus, queryConsultarId;
		Integer idComentario;
		
		//Se obtiene el id del comentario que se insertará
		queryConsultarId = getSession().createSQLQuery(this.getContainer().getQuery("query_consulta_siguiente_id_comentario"));
		queryConsultarId.setInteger("idDocumento", dtoComentario.getIdDocumento());
		//queryConsultarId.executeUpdate();
		//queryConsultarId.setResultTransformer(Transformers.aliasToBean(Integer.class));
		idComentario = (Integer)queryConsultarId.uniqueResult();
		
		queryInsercionComentario = getSession().createSQLQuery(this.getContainer().getQuery("query_firma_guardarComentario"));

		queryInsercionComentario.setInteger("idDocumento", dtoComentario.getIdDocumento());
		queryInsercionComentario.setInteger("idComentario", idComentario);
		queryInsercionComentario.setInteger("anio", dtoComentario.getAnio());
		queryInsercionComentario.setString("comentarios", dtoComentario.getComentarios());
		queryInsercionComentario.setInteger("estatus", dtoComentario.getEstatus());
		queryInsercionComentario.setString("usuarioComento", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionComentario.setCharacter("tipoComentario", dtoComentario.getTipoComentario());
		
		queryInsercionComentario.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionComentario.setDate("fechaHora", new Date());
		queryInsercionComentario.executeUpdate();
		
		
		//Se inserta el COMENTARIOS_NO_LEIDOS
		queryInsercionEstatus = getSession().createSQLQuery(this.getContainer().getQuery("query_insertar_comentario_estatus"));
		queryInsercionEstatus.setInteger("idComentario", idComentario);
		queryInsercionEstatus.setInteger("idDocumento", dtoComentario.getIdDocumento());
		queryInsercionEstatus.setInteger("idPersona", persona.getIdPersona());
		queryInsercionEstatus.setInteger("idArea", persona.getIdArea());
		queryInsercionEstatus.setInteger("tipoArea", persona.getTipoArea());
		queryInsercionEstatus.setInteger("noLeido", 0);
		
		queryInsercionEstatus.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		queryInsercionEstatus.setDate("fechaHora", new Date());
		
		queryInsercionEstatus.executeUpdate();
//		//Se obtiene el id con el que se guardo el registro
//		queryInsercionComentario = getSession().createSQLQuery(this.getContainer().getQuery("query_consulta_id_comentario"))
//				.addScalar("idComentario", IntegerType.INSTANCE);
//		queryInsercionComentario.setResultTransformer(Transformers.aliasToBean(DTOComentariosDocumentoEntity.class));
//		
//		queryInsercionComentario.setInteger("idDocumento", dtoComentario.getIdDocumento());
//		queryInsercionComentario.setString("usuarioComento", SecurityContextHolder.getContext().getAuthentication().getName());
//		queryInsercionComentario.setCharacter("tipoComentario", 'T');
		
//		return ((DTOComentariosDocumentoEntity)queryInsercionComentario.uniqueResult()).getIdComentario();
		return idComentario;
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface#consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity documento, int estatus) throws Exception {
		
		Query query = getSession()
				.createSQLQuery(
						this.getContainer()
								.getQuery("query_consulta_comentarios_documento"))

				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idComentario", IntegerType.INSTANCE)
				.addScalar("comentarios", StringType.INSTANCE)
				.addScalar("estatus", IntegerType.INSTANCE)
				.addScalar("usuarioComento", StringType.INSTANCE)
				
				.addScalar("nombreCompleto", StringType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOComentariosDocumentoEntity.class));

		query.setInteger("estatus", estatus);
		query.setInteger("idDocumento", documento.getIdDocumento());

		return query.list();
		
	}


//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface#
//	 * consultarComentarios(mx.ine.gestion.dto.db.DTODocumentoEntity)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<DTOComentariosDocumentoEntity> consultarComentariosLeidos(
//			DTODocumentoEntity dtoDocumentoEntity) {
//
////		Criteria criteria = getSession()
////				.createCriteria(this.getPersistentClass())
////				.add(Restrictions.eq("idDocumento",
////						dtoDocumentoEntity.getIdDocumento()))
////				.add(Restrictions.eq("estatus", Integer.parseInt(Utilidades
////						.mensajeProperties("comentario_estatus_leido"))));
////
////		return (List<DTOComentariosDocumentoEntity>) criteria.list();
//
//	}
//
//	/*
//	 * (El comentario se encuentra en la interfaz dónde esta declarado el
//	 * método)
//	 * 
//	 * @see mx.ine.gestion.dao.inter.DAOComentariosDocumentoInterface#
//	 * consultarComentariosLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<DTOComentariosDocumentoEntity> consultarComentariosNoLeidos(
//			DTODocumentoEntity dtoDocumentoEntity) {
//		Criteria criteria = getSession()
//				.createCriteria(this.getPersistentClass())
//				.add(Restrictions.eq("idDocumento",
//						dtoDocumentoEntity.getIdDocumento()))
//				.add(Restrictions.eq("estatus", Integer.parseInt(Utilidades
//						.mensajeProperties("comentario_estatus_no_leido"))));
//
//		return (List<DTOComentariosDocumentoEntity>) criteria.list();
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception {
		Criteria criteria = getSession().createCriteria(this.getPersistentClass())
				.add(Restrictions.eq("idDocumento", dtoDocumentoEntity.getIdDocumento()));
		return (List<DTOComentariosDocumentoEntity>) criteria.list();
	}

	@Override
	public void activarEstatus(String usuarioComento, Integer idDocumento) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery( "query_firma_activarEstatusComentado" ) );
		query.setString("usuarioComento", usuarioComento);
		query.setInteger("idDocumento", idDocumento);
		query.executeUpdate();
	}
}
