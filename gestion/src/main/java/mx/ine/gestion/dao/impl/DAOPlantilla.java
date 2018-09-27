package mx.ine.gestion.dao.impl;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOPlantillaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * @(#)DAOPlantilla.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de dao para la entidad de Plantilla.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 */
@Scope("prototype")
@Repository("daoPlantilla")
public class DAOPlantilla extends DAOGenericGestion<DTOPlantillaEntity, Integer> implements DAOPlantillaInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillaInterface#buscarPlantillas(mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOPlantillaEntity> consultarPlantillasXPersonaXAcronimo(DTOAcronimoID idAcronimo, Integer idPersona) {

		//Método ocupado por más de un módulo
		
		Criteria crit = getSession().createCriteria(DTOPlantillaEntity.class);
		crit.add(Restrictions.eq("idArea", idAcronimo.getIdArea()));
		crit.add(Restrictions.eq("tipoArea", idAcronimo.getTipoArea()));
		crit.add(Restrictions.eq("idTipoDocumento", idAcronimo.getIdTipoDocumento()));
		crit.add(Restrictions.eq("idAcronimo", idAcronimo.getIdAcronimo()));
		crit.add(Restrictions.eq("idPersona", idPersona));

		crit.addOrder(Order.asc("nombrePlantilla"));
	
		return (List<DTOPlantillaEntity>)crit.list();
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillasInterface#insertarPlantilla(mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper)
	 */
	@Override
	public boolean insertarPlantilla(DTOFiltrosPlantillaHelper filtros) {

		Query query = this.getSession().createSQLQuery(this.getContainer().getQuery("query_plantillas_insertarPlantilla"));
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		query.setInteger("tipoArea", filtros.getTipoArea());
		query.setInteger("idArea", filtros.getIdArea());
		query.setInteger("idTipoDocumento", filtros.getIdTipoDocumentoSeleccionado());
		query.setInteger("idAcronimo", filtros.getAcronimoSeleccionado().getIdAcronimo());
		query.setInteger("idPersona", usuarioLogueado.getIdPersona());
		query.setString("nombrePlantilla", filtros.getNombrePlantilla());
		query.setString("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		int resultado = query.executeUpdate();
		
		return resultado > 0 ? true : false;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOPlantillaInterface#consultarUltimaPlantillaXAcronimoXUsuario(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DTOPlantillaEntity consultarUltimaPlantillaXAcronimoXUsuario(Integer idArea, Integer tipoArea, Integer idTipoDocumento, Integer idAcronimo) {

		Criteria criteria = getSession().createCriteria(DTOPlantillaEntity.class);
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("tipoArea", tipoArea));
		criteria.add(Restrictions.eq("idTipoDocumento", idTipoDocumento));
		criteria.add(Restrictions.eq("idAcronimo", idAcronimo));
		criteria.add(Restrictions.eq("idPersona", usuarioLogueado.getIdPersona()));

		criteria.addOrder(Order.desc("fechaHora"));
		criteria.setMaxResults(1);

		return (DTOPlantillaEntity)criteria.uniqueResult();
	}
}
