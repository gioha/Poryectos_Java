/**
 * @(#)DAOHBandejaEAtencion.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionId;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Scope("prototype")
@Repository("daoHBandejaEAtecion")
public class DAOHBandejaEAtencion extends
DAOGenericGestion<DTOHBandejaEAtencionEntity, DTOHBandejaEAtencionId> implements
DAOHBandejaEAtencionInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface#consultarNumeroAtencion(mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper)
	 */
	@Override
	public int consultarNumeroAtencion(
			DTOHBandejaEAtencionHelper filtroAtencionHelper) {
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_num_bandeja_e_hatencion");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",filtroAtencionHelper.obtenerFiltro(false));
		
		Query query = getSession().createSQLQuery(sentenciaSQL).addScalar("totalAtencion", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult()).getTotalAtencion();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaEAtencionInterface#obtenerListaAtencionLazy(mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHBandejaEAtencionEntity> obtenerListaAtencionLazy(
			DTOHBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_bandeja_e_hatencion");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroAtencionHelper.obtenerFiltro(true));
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
				.aliasToBean(DTOHBandejaEAtencionEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOHBandejaEAtencionEntity>) query.list();
	}

}
