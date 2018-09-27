/**
 * @(#)DAOBandejaEAtencion.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoBandejaEAtecion")
public class DAOBandejaEAtencion extends
		DAOGenericGestion<DTOBandejaEAtencionEntity, DTOBandejaEAtencionID> implements DAOBandejaEAtencionInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface#consultarNumeroAtencion(mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper)
	 */
	@Override
	public int consultarNumeroAtencion(DTOBandejaEAtencionHelper filtroAtencionHelper) {
		
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_num_bandeja_e_atencion");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",filtroAtencionHelper.obtenerFiltro(false));
		
		Query query = getSession().createSQLQuery(sentenciaSQL).addScalar("totalAtencion", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult()).getTotalAtencion();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface#obtenerListaAtencionLazy(mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOBandejaEAtencionEntity> obtenerListaAtencionLazy(
			DTOBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_bandeja_e_atencion");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>", filtroAtencionHelper.obtenerFiltro(true));
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",filtroAtencionHelper.obtenerOrdenamiento());
		
		Query query = getSession()
				.createSQLQuery(sentenciaSQL)
				.addScalar("idPersona", IntegerType.INSTANCE)
				.addScalar("idDocumento", IntegerType.INSTANCE)
				.addScalar("anio", IntegerType.INSTANCE)
				.addScalar("idArea", IntegerType.INSTANCE)
				.addScalar("tipoArea", IntegerType.INSTANCE)
				.addScalar("fechaRecepcion", DateType.INSTANCE)
				.addScalar("usuario", StringType.INSTANCE)
				.addScalar("fechaHora", DateType.INSTANCE)
				.addScalar("noLeido", IntegerType.INSTANCE)
				.addScalar("enAtencion", IntegerType.INSTANCE)
				.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
				.addScalar("idHistoricoPadre", IntegerType.INSTANCE)
				.addScalar("tieneRespuesta", IntegerType.INSTANCE)

				// Columnas de la tabla de "DOCUMENTOS"
				.addScalar("idTipoDocumento", IntegerType.INSTANCE)
				.addScalar("idAcronimoDocumento", IntegerType.INSTANCE)
//				.addScalar("secNumDocDocumento", IntegerType.INSTANCE)
				.addScalar("numDocumento", StringType.INSTANCE)
				.addScalar("nombreDocumento", StringType.INSTANCE)
				.addScalar("estatusDocumento", CharacterType.INSTANCE)
				.addScalar("tipoCaptura", IntegerType.INSTANCE)
				.addScalar("asunto", StringType.INSTANCE)
				.addScalar("fechaCreacion", DateType.INSTANCE)
				.addScalar("contieneAnexos", IntegerType.INSTANCE)

				// Columnas de la tabla "C_AREAS"
				.addScalar("idAreaDocumento", IntegerType.INSTANCE)
				.addScalar("tipoAreaDocumento", IntegerType.INSTANCE)
				.addScalar("idEntidadArea", IntegerType.INSTANCE)
				.addScalar("descripcionArea", StringType.INSTANCE)
				.addScalar("siglasArea", StringType.INSTANCE);

		query.setResultTransformer(Transformers
				.aliasToBean(DTOBandejaEAtencionEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOBandejaEAtencionEntity>) query.list();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOBandejaEAtencionInterface#obtenerDocumentoAtencion(mx.ine.gestion.dto.db.DTODocumentoEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public DTOBandejaEAtencionEntity obtenerDocumentoAtencion(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona) {
		Criteria criteria = getSession().createCriteria(DTOBandejaEAtencionEntity.class);
		
		criteria.add(Restrictions.eq("idDocumento", documento.getIdDocumento()));
		criteria.add(Restrictions.eq("idPersona", persona.getIdPersona()));

		return (DTOBandejaEAtencionEntity) criteria.uniqueResult();
	}


}
