/**
 * @(#)DAORecuentoTotalImpl.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.dao.impl;

import java.io.Serializable;
import java.util.List;

import mx.ine.computosINE.dao.DAORecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.dto.reportes.DTOReporteRecuento;
import mx.ine.computosINE.util.Constantes;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase que provee la implementación de la interface de acceso a la base de
 * datos para el módulo de recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
@Repository("daoRecuentoTotal")
@Scope("prototype")
public class DAORecuentoTotalImpl extends
		DAOGeneric<Serializable, Serializable> implements
		DAORecuentoTotalInterface {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HLPEntornoGeografico> obtenGeografia(FormRecuentoTotal dto,
			List<String> remplaza) {
		Query query = getSession().createSQLQuery(
				this.qryContainer.getQuery("query_recuento_total_geografia")
						.replaceAll("<!REM_SELECT>", remplaza.get(0))
						.replaceAll("<!REM_GENERAL>", remplaza.get(1))
						.replaceAll("<!REM_WHERE>", remplaza.get(2))
						.replaceAll("<!REM_ORDER_GROUP>", remplaza.get(3))
						.replaceAll("<!REM_JOIN_AB>", remplaza.get(4))
						.replaceAll("<!REM_JOIN_AC>", remplaza.get(5)))
				.addScalar("id", IntegerType.INSTANCE);
		query.setResultTransformer(Transformers
				.aliasToBean(HLPEntornoGeografico.class));
		query.setParameter("PROCESO", dto.getUsuario().getIdProcesoElectoral());
		query.setParameter("DETALLE", dto.getUsuario().getIdDetalleProceso());
		query.setParameter("ESTADO", dto.getUsuario().getIdEstadoSeleccionado());
		query.setParameter("CANDIDATURA", dto.getIdCandidatura());
		return (List<HLPEntornoGeografico>) query.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HLPEntornoGeografico> obtenGeografiaConRecuento(
			FormRecuentoTotal dto, List<String> remplaza) {
		Query query = getSession().createSQLQuery(
				this.qryContainer.getQuery("query_recuento_total_con_recuento")
						.replaceAll("<!REM_SELECT>", remplaza.get(0))
						.replaceAll("<!REM_WHERE>", remplaza.get(2))
						.replaceAll("<!REM_ORDER_GROUP>", remplaza.get(3)))
				.addScalar("id", IntegerType.INSTANCE);
		query.setResultTransformer(Transformers
				.aliasToBean(HLPEntornoGeografico.class));
		query.setParameter("PROCESO", dto.getUsuario().getIdProcesoElectoral());
		query.setParameter("DETALLE", dto.getUsuario().getIdDetalleProceso());
		query.setParameter("ESTADO", dto.getUsuario().getIdEstadoSeleccionado());
		query.setParameter("CANDIDATURA", dto.getIdCandidatura());
		return (List<HLPEntornoGeografico>) query.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int ejecutaDelete(FormRecuentoTotal dto, String remplaza,
			String tabla) {
		Query query = getSession().createQuery(
				this.qryContainer.getQuery("query_recuento_total_delete")
						.replace("<!REM_TABLA>", tabla)
						.replace("<!REM_WHERE>", remplaza));
		query.setParameter("proceso", dto.getUsuario().getIdProcesoElectoral());
		query.setParameter("detalle", dto.getUsuario().getIdDetalleProceso());
		query.setParameter("estado", dto.getUsuario().getIdEstadoSeleccionado());
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", Constantes.ID_TIPO_CAND_DIPUTADO_RP);
		}
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", Constantes.ID_TIPO_CAND_REGIDURIA_RP);
		}
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_GOBERNADOR.intValue()
				|| dto.getIdCandidatura().intValue() == 
						Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", dto.getIdCandidatura());
		}
		return query.executeUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int ejecutaUpdate(FormRecuentoTotal dto, String remplaza) {
		Query query = getSession().createQuery(
				this.qryContainer.getQuery("query_recuento_total_update")
						.replace("<!REM_WHERE>", remplaza));
		query.setParameter("proceso", dto.getUsuario().getIdProcesoElectoral());
		query.setParameter("detalle", dto.getUsuario().getIdDetalleProceso());
		query.setParameter("estado", dto.getUsuario().getIdEstadoSeleccionado());
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_DIPUTADO_MR.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", Constantes.ID_TIPO_CAND_DIPUTADO_RP);
		}
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_REGIDURIA_MR.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", Constantes.ID_TIPO_CAND_REGIDURIA_RP);
		}
		if (dto.getIdCandidatura().intValue() == Constantes.ID_TIPO_CAND_GOBERNADOR.intValue()
				|| dto.getIdCandidatura().intValue() == 
						Constantes.ID_TIPO_CAND_AYUNTAMIENTO.intValue()) {
			query.setParameter("candidatura1", dto.getIdCandidatura());
			query.setParameter("candidatura2", dto.getIdCandidatura());
		}
		return query.executeUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOReporteRecuento> obtenReporte(FormReporteRecuento dto,
			List<String> remplaza) {
		Query query = getSession().createSQLQuery(this.qryContainer.getQuery("query_reporte_recuento_total")
                                .replaceAll("<!REM_SELECT>", remplaza.get(0))
                                .replaceAll("<!REM_GENERAL>", remplaza.get(1))
                                .replaceAll("<!REM_ORDER_GROUP>",remplaza.get(2))
                                .replaceAll("<!REM_WHERE>", remplaza.get(3))
                                .replaceAll("<!REM_JOIN_AB>", remplaza.get(4))
                                .replaceAll("<!REM_JOIN_AC>", remplaza.get(5))
                                .replaceAll("<!REM_JOIN_GEO_B>", remplaza.get(6))
                                .replaceAll("<!REM_JOIN_GEO_C>", remplaza.get(7))
                                .replaceAll("<!REM_WHERE_GEO>", remplaza.get(8))
                                .replaceAll("<!REM_ORDER_GEO>", remplaza.get(9))
                                .replaceAll("<!REM_TABLA_GEO>", remplaza.get(10)))
				.addScalar("id", IntegerType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("causalA", StringType.INSTANCE)
				.addScalar("causalB", StringType.INSTANCE);
		query.setResultTransformer(Transformers
				.aliasToBean(DTOReporteRecuento.class));
		query.setParameter("PROCESO", dto.getUsuario().getIdProcesoElectoral());
		query.setParameter("DETALLE", dto.getUsuario().getIdDetalleProceso());
		query.setParameter("ESTADO", dto.getUsuario().getIdEstadoSeleccionado());
		query.setParameter("CANDIDATURA", dto.getIdCandidatura());
		return (List<DTOReporteRecuento>) query.list();
	}

}
