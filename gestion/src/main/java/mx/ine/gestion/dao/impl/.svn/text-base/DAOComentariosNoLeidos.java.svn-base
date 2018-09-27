/**
 * @(#)DAOComentariosNoLeidos.java 17/04/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.gestion.dao.inter.DAOComentariosNoLeidosInterface;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidosID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 * @since 17/04/2018
 *
 */
@Scope("prototype")
@Repository("daoComentariosNoLeidos")
public class DAOComentariosNoLeidos extends DAOGenericGestion<DTOComentariosNoLeidos, DTOComentariosNoLeidosID>
					implements DAOComentariosNoLeidosInterface{

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOComentariosNoLeidosInterface#obtenerComentariosNoLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOComentariosNoLeidos> obtenerEstatusComentarios(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona) {
		Criteria criteria = getSession().createCriteria(this.getPersistentClass())
				.add(Restrictions.eq("idDocumento", documento.getIdDocumento()))
				.add(Restrictions.eq("idPersona", persona.getIdPersona()))
				.add(Restrictions.eq("idArea", persona.getIdArea()))
				.add(Restrictions.eq("tipoArea", persona.getTipoArea()))
				;
		return (List<DTOComentariosNoLeidos>) criteria.list();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOComentariosNoLeidosInterface#obtenerComentariosLeidos(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOComentariosNoLeidos> obtenerComentarios(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona, Integer noLeido) {
		Query query = getSession().createSQLQuery(this.getContainer().getQuery("query_consulta_comentarios_estatus"))
				
				.addScalar("idComentario", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("noLeido", IntegerType.INSTANCE)
				
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				
				//COMENTARIOS_DOCUMENTO
				.addScalar("anioCom", IntegerType.INSTANCE)
				.addScalar("comentarios", StringType.INSTANCE)
				.addScalar("estatusCom", IntegerType.INSTANCE)
				.addScalar("usuarioComento", StringType.INSTANCE)
				.addScalar("tipoComentario", CharacterType.INSTANCE)
				
				.addScalar("usuarioCom", StringType.INSTANCE)
				.addScalar("fechaHoraCom", DateType.INSTANCE)
				
				//ESTRUCTURA PERSONA COMENTO
				.addScalar("idPersonaEstr", IntegerType.INSTANCE)
				.addScalar("tipoAreaEstr", IntegerType.INSTANCE)
				.addScalar("nombreEstr", StringType.INSTANCE)
				.addScalar("apellidosEstr", StringType.INSTANCE)
				.addScalar("nombreCompletoEstr", StringType.INSTANCE)
				.addScalar("cuentraLDAPEstr", StringType.INSTANCE)
				;
		
		query.setParameter("idDocumento", documento.getIdDocumento());
		query.setParameter("idPersona", persona.getIdPersona());
		query.setParameter("idArea", persona.getIdArea());
		query.setParameter("tipoArea", persona.getTipoArea());
		query.setParameter("noLeido", noLeido);
		
		
		query.setResultTransformer(Transformers.aliasToBean(DTOComentariosNoLeidos.class));
		
		return query.list();
	}

}
