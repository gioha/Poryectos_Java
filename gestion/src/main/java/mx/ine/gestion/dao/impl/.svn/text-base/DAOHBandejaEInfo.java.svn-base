/**
 * @(#)DAOHBandejaEInfo.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.impl;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dao.inter.DAOHBandejaEInfoInterface;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoID;
import mx.ine.gestion.dto.helper.DTOBandejaEntradaContadorHelper;
import mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper;

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
@Repository("daoHBandejaEInfo")
public class DAOHBandejaEInfo extends
		DAOGenericGestion<DTOHBandejaEInfoEntity, DTOHBandejaEInfoID> implements
		DAOHBandejaEInfoInterface {

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaEInfoInterface#consultarNumeroAtencion(mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper)
	 */
	@Override
	public int consultarNumeroInfo(DTOHBandejaEInfoHelper filtroInfoHelper) {
		String sentenciaSQL = this.getContainer().getQuery("query_consultar_num_bandeja_e_hinfo");
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",filtroInfoHelper.obtenerFiltro(false));
		
		Query query = getSession().createSQLQuery(sentenciaSQL).addScalar("totalInfo", IntegerType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DTOBandejaEntradaContadorHelper.class));

		return ((DTOBandejaEntradaContadorHelper) query.uniqueResult()).getTotalInfo();
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.dao.inter.DAOHBandejaEInfoInterface#obtenerListaAtencionLazy(mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper, int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOHBandejaEInfoEntity> obtenerListaInfoLazy(
			DTOHBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna) {
		//
		this.activaMatchModeWords();
		
		String sentenciaSQL = this.getContainer().getQuery(
				"query_consultar_bandeja_e_hinfo");
		//Se agregan los filtros
		sentenciaSQL = sentenciaSQL.replaceAll("<condiciones>",
				filtroInfoHelper.obtenerFiltro(true));
		//Se agrega el ordenamiento
		sentenciaSQL = sentenciaSQL.replaceAll("<orden>",filtroInfoHelper.obtenerOrdenamiento());
		
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
				.addScalar("idHistoricoRecep", IntegerType.INSTANCE)
				.addScalar("idHistoricoPadre", IntegerType.INSTANCE)

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
				.aliasToBean(DTOHBandejaEInfoEntity.class));

		query.setInteger("minRows", indicePrimerElemento);
		query.setInteger("maxRows", tamanioMaxPagina + indicePrimerElemento);

		return (List<DTOHBandejaEInfoEntity>) query.list();
	}

}
