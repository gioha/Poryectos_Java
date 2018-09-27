package mx.ine.gestion.dao.impl;

import java.math.BigDecimal;

import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFID;
import mx.ine.gestion.util.Utilidades;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que contiene los métodos que interactuan u obtienen datos de la
 * tabla de NOTIFICAIONES_OF del esquema de GESTION4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 18712/2017
 */
@Scope("prototype")
@Repository("daoNotificacionesOF")
public class DAONotificacionesOF extends DAOGenericGestion<DTONotificacionesOFEntity, DTONotificacionesOFID> implements DAONotificacionesOFInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesOFInterface#consultarDocumentosPendientesXAreaXIdModulo(java.lang.String[], org.hibernate.type.Type[], java.lang.Integer)
	 */
	@Override
	public Integer consultarDocumentosPendientesXAreaXIdModulo(String[] tipoAreaIdAreas, Integer idModulo) {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_administrador_notificacionesOficialia"));
		
		query.setInteger("idModulo", idModulo);
		query.setParameterList("idAreaTipoArea", tipoAreaIdAreas);
		
		BigDecimal resultado = (BigDecimal)query.uniqueResult();
		return resultado.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesOFInterface#incrementar(mx.ine.gestion.dto.db.DTONotificacionesOFEntity)
	 */
	@Override
	public void incrementar(DTONotificacionesOFEntity notificacionesOF) {
		
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_firma_incrementarNotificacion"));
		query.setInteger("idArea", notificacionesOF.getIdArea());
		query.setInteger("tipoArea", notificacionesOF.getTipoArea());
		query.setInteger("idModulo", Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja_oficialia")));
		query.executeUpdate();
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesOFInterface#consultarNotificacionesPorArea(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer consultarNotificacionesPorArea(Integer idArea, Integer tipoArea, Integer idApartado) {
		Criteria criteria = getSession().createCriteria(DTONotificacionesOFEntity.class);
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("idApartado", idApartado));
		criteria.setProjection(Projections.property("documentosPendientes"));
		
		return (Integer) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.dao.inter.DAONotificacionesOFInterface#decrementar(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void decrementar(Integer idArea, Integer tipoArea) {
		Query query = getSession().createSQLQuery(
				this.getContainer().getQuery(
						"query_bandejaOficialia_decrementarNotificacion"));
		query.setInteger("idArea", idArea);
		query.setInteger("tipoArea", tipoArea);
		query.setInteger("idModulo", Integer.parseInt(Utilidades.mensajeProperties("id_modulo_bandeja_oficialia")));
		query.executeUpdate();
	}
}
