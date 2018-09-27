package mx.ine.computosINE.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.computosINE.dao.DAODistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTODistribucionTotParcialPK;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;
import mx.ine.computosINE.dto.helper.HLPDistribucionVotos;
import mx.ine.computosINE.util.Constantes;

/**
 * <code>DAODistribucionVotosImpl.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 28/04/2017 11:27:00
 */
@Repository("daoDistribucionVotos")
@Scope("prototype")
public class DAODistribucionVotosImpl extends
		DAOGeneric<DTODistribucionCandidatos, DTODistribucionCandidatosPK>
		implements DAODistribucionVotosInterface {

	/**
	 * LOGGER
	 */
	private static final Log log = LogFactory
			.getLog(DAODistribucionVotosImpl.class);

	Query query;
	SQLQuery sqlQry;
	String qry;

	/**
	 * Consulta casillas aprobadas por consejo a nivel entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCasillaWS> casillasAprobadasPorEntidad(Integer idEstado)
			throws Exception {
		List<DTOCasillaWS> aprobadas = new ArrayList<>();
		Session sesion = getSession();
		qry = this.getContainer().getQuery(
				"query_verifica_distribucion_totales");
		sqlQry = sesion.createSQLQuery(qry);

		sqlQry.setParameter("idEstado", idEstado, IntegerType.INSTANCE);

		BigDecimal resultado = (BigDecimal) sqlQry.uniqueResult();

		if (resultado != null) {
			int res = resultado.intValueExact();
			for (int i = 0; i < res; i++) {
				DTOCasillaWS casillaAprobada = new DTOCasillaWS();
				aprobadas.add(casillaAprobada);
			}
		}
		return aprobadas;
	}

	/**
	 * Consulta la información de la distribución total de la votación
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idDistrito
	 * @param idMunicipio
	 * @param idRegiduria
	 * @param
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionTotales> consultarDistribucionTotales(
			DTODistribucionCandidatosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarDistribuciónTotales");
		Integer[] cnr_vn = { Constantes.TIPO_ASOCIACION_CAND_NO_REG,
				Constantes.TIPO_ASOCIACION_VOTOS_NULOS };
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionTotales.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", pk.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", pk.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", pk.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				pk.getIdTipoCandidatura()));
		criteria.add(Restrictions.not(Restrictions
				.in("id.idAsociacion", cnr_vn)));
		criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionTotales>) criteria.list();
	}

	/**
	 * Consulta la información de la distribución total parcial de la votación
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idDistrito
	 * @param idMunicipio
	 * @param idRegiduria
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionTotParcial> consultarDistribucionTotalParcial(
			DTODistribucionCandidatosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarDistribucionTotalParcial");
		Integer[] cnr_vn = { Constantes.TIPO_ASOCIACION_CAND_NO_REG,
				Constantes.TIPO_ASOCIACION_VOTOS_NULOS };
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionTotParcial.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", pk.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", pk.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", pk.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				pk.getIdTipoCandidatura()));
		criteria.add(Restrictions.not(Restrictions
				.in("id.idAsociacion", cnr_vn)));
		criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionTotParcial>) criteria.list();
	}

	/**
	 * Consulta la información de la distribución final de la votación
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idDistrito
	 * @param idMunicipio
	 * @param idRegiduria
	 * @param
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionCandidatos> consultarDistribucionFinal(
			DTODistribucionCandidatosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarDistribucionFinal");
		Integer[] cnr_vn = { Constantes.TIPO_ASOCIACION_CAND_NO_REG,
				Constantes.TIPO_ASOCIACION_VOTOS_NULOS };

		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", pk.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", pk.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", pk.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				pk.getIdTipoCandidatura()));
		criteria.add(Restrictions.not(Restrictions
				.in("id.idAsociacion", cnr_vn)));
		// criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionCandidatos>) criteria.list();
	}

	/**
	 * Consulta la información de la distribución de partidos y candidatos
	 * independientes, para los tipos de candidaturas de regidurias,
	 * ayuntamientos, gobernador y diputados
	 * 
	 * @param pk
	 * @return List<DTODistribucionPartidosCI>
	 */
	/**
	 * Consulta la información de la distribución de partidos y candidatos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionPartidosCI> getDistribucionFinalDMRyDRP(
			Integer idProceso, Integer idDetalleProceso, Integer idEstado,
			List<Integer> idTipoCandidatura) throws Exception {
		log.info("DAODistribucionVotosImpl.getDistribucionFinalDMRyDRP");

		String tipoCandidatura = null;
		// Convertimos la lista de Enteros a una cadena
		tipoCandidatura = String.valueOf(idTipoCandidatura);

		tipoCandidatura = tipoCandidatura.replace("[", "");
		tipoCandidatura = tipoCandidatura.replace("]", "");

		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionPartidosCI.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", idProceso));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", Constantes.SIN_DISTRITO));
		criteria.add(Restrictions.eq("id.idDistrito", idEstado));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", tipoCandidatura));
		criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionPartidosCI>) criteria.list();
	}

	/**
	 * Consulta la información de la distribución parcial
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionCandParcial> consultarDistribucionParcial(
			DTODistribucionCandidatosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarDistribucionParcial");
		Integer[] cnr_vn = { Constantes.TIPO_ASOCIACION_CAND_NO_REG,
				Constantes.TIPO_ASOCIACION_VOTOS_NULOS };

		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandParcial.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", pk.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", pk.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", pk.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				pk.getIdTipoCandidatura()));
		criteria.add(Restrictions.not(Restrictions
				.in("id.idAsociacion", cnr_vn)));
		// criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionCandParcial>) criteria.list();
	}

	/**
	 * Consulta la información de la distribución de partidos y candidatos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistribucionPartidosCI> consultarDistribucionPartidosCI(
			DTODistribucionCandidatosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarDistribucionPartidosCI");
		Integer[] cnr_vn = { Constantes.TIPO_ASOCIACION_CAND_NO_REG,
				Constantes.TIPO_ASOCIACION_VOTOS_NULOS };

		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionPartidosCI.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", pk.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", pk.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", pk.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				pk.getIdTipoCandidatura()));
		criteria.add(Restrictions.not(Restrictions
				.in("id.idAsociacion", cnr_vn)));
		criteria.addOrder(Order.asc("tipoAsociacion"));
		criteria.addOrder(Order.asc("orden"));
		return (List<DTODistribucionPartidosCI>) criteria.list();
	}

	/**
	 * Consulta la información de los votos nulos y candidatos no registrados de
	 * la tabla de distribucion total
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DTODistribucionTotales getVotosNulosCNRDistribucionTotal(
			DTODistribucionCandidatosPK id) throws Exception {
		log.info("DAODistribucionVotosImpl.getVotosNulosCNRDistribucionTotal");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionTotales.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));

		DTODistribucionTotales distribucionTotal = (DTODistribucionTotales) criteria
				.uniqueResult();

		return distribucionTotal;
	}

	/**
	 * Consulta la información de los votos nulos y candidatos no registrados de
	 * la tabla de distribucion total parcial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DTODistribucionTotParcial getVotosNulosCNRDistribucionTotalParcial(
			DTODistribucionCandidatosPK id) throws Exception {
		log.info("DAODistribucionVotosImpl.getVotosNulosCNRDistribucionTotalParcial");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionTotParcial.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));

		DTODistribucionTotParcial resultado = (DTODistribucionTotParcial) criteria
				.uniqueResult();

		return resultado;

	}

	/**
	 * Consulta la información de los votos nulos y candidatos no registrados de
	 * la tabla de distribucion candidatos parcial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DTODistribucionCandParcial getVotosNulosCNRDistribucionCandParcial(
			DTODistribucionCandidatosPK id) throws Exception {
		log.info("DAODistribucionVotosImpl.getVotosNulosCNRDistribucionCandParcial");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandParcial.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));

		DTODistribucionCandParcial resultado = (DTODistribucionCandParcial) criteria
				.uniqueResult();

		return resultado;

	}

	/**
	 * Consulta la información de los votos nulos y candidatos no registrados de
	 * la tabla de distribucion candidatos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DTODistribucionCandidatos getVotosNulosCNRDistribucionCandidatos(
			DTODistribucionCandidatosPK id) throws Exception {
		log.info("DAODistribucionVotosImpl.getVotosNulosCNRDistribucionCandidatos");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));

		DTODistribucionCandidatos resultado = (DTODistribucionCandidatos) criteria
				.uniqueResult();
		return resultado;

	}

	/**
	 * Consulta la información de los votos nulos y candidatos no registrados de
	 * la tabla de distribucion partidos ci
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DTODistribucionPartidosCI getVotosNulosCNRDistribucionPartidosCI(
			DTODistribucionCandidatosPK id) throws Exception {
		log.info("DAODistribucionVotosImpl.getVotosNulosCNRDistribucionPartidosCI");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionPartidosCI.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));

		DTODistribucionPartidosCI resultado = (DTODistribucionPartidosCI) criteria
				.uniqueResult();
		return resultado;

	}

	/**
	 * Consulta la información de los distritos que ya tienen capturada una
	 * distribución, al seleccionar el registro del idMunicipio
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDistritosWithDistribucion(
			Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {

		log.info("DAODistribucionVotosImpl.getDistritosWithDistribucion");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idDistrito"),
						"idDistrito")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
//		criteria.add(Restrictions.eq("id.idMunicipio", idMunicipio));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.ne("id.idDistrito", -5));

		return (List<Integer>) criteria.list();

	}

	/**
	 * Consulta la información de los distritos que ya tienen capturada una
	 * distribución, al seleccionar el registro del idMunicipio, este método
	 * solo funciona para el tipo de candidatura de Diputados RP
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDistritosWithDistribucionRP(
			Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {

		log.info("DAODistribucionVotosImpl.getDistritosWithDistribucionRP");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionPartidosCI.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idDistrito"),
						"idDistrito")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idMunicipio", idMunicipio));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.ne("id.idDistrito", -5));

		return (List<Integer>) criteria.list();

	}

	/**
	 * Consulta la información de las demarcaciones del municipio del usuario en
	 * los cuales ya se encuentra hecha la distribución final
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDemarcacionesWithDistribucion(
			Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {
		log.info("DAODistribucionVotosImpl.getDemarcacionesWithDistribucion");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idRegiduria"),
						"idRegiduria")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idMunicipio", idMunicipio));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.ne("id.idRegiduria", -7));
		return (List<Integer>) criteria.list();
	}

	/**
	 * Consulta la información de las demarcaciones del municipio del usuario en
	 * los cuales ya se encuentra hecha la distribución final, este método solo
	 * aplica para el tipo de candidatura Regidurias RP
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDemarcacionesWithDistribucionRP(
			Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {
		log.info("DAODistribucionVotosImpl.getDemarcacionesWithDistribucion");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionPartidosCI.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idRegiduria"),
						"idRegiduria")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idMunicipio", idMunicipio));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.ne("id.idRegiduria", -7));
		return (List<Integer>) criteria.list();
	}

	/**
	 * Consulta la información para saber si el ayuntamiento o municipio que se
	 * le pasa como parametro ya tiene distribución final
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAyuntamientoWithDistribucion(
			Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura)
			throws Exception {
		log.info("DAODistribucionVotosImpl.getAyuntamientoWithDistribucion");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idMunicipio"),
						"idMunicipio")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idMunicipio", idMunicipio));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.ne("id.idMunicipio", -6));
		return (List<Integer>) criteria.list();
	}

	/**
	 * Consulta la información para saber si el estado que se le pasa como
	 * parametro ya tiene la distribución final, esto para poder generar como
	 * usuario OPLE un acta final
	 * 
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getEstadoWithDistribucion(Integer idProcesoElectoral,
			Integer idDetalleProceso, Integer idEstado,
			Integer idTipoCandidatura) throws Exception {
		log.info("DAODistribucionVotosImpl.getEstadoWithDistribucion");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("id.idEstado"),
						"idEstado")));
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				idProcesoElectoral));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		return (List<Integer>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantes(
			DTOActaCasillaVotosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarAsociacionesParticipantes");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion"), "idAsociacion")
				.add(Projections.property("id.idCoalicion"), "idCoalicion")
				.add(Projections.property("id.tipoAsociacion"),
						"tipoAsociacion")
				.add(Projections.property("id.idTipoCandidatura"),
						"idTipoCandidatura")
				.add(Projections.property("orden"), "orden")
				.add(Projections.sum("votos")));
		criteria.add(Restrictions.sqlRestriction("this_.ID_PROCESO_ELECTORAL="
				+ pk.getIdProcesoElectoral() + " AND ID_DETALLE_PROCESO="
				+ pk.getIdDetalleProceso()
				+ " AND ID_ESTADO="
				+ pk.getIdEstado()
				+ " AND ID_DISTRITO="
				+ pk.getIdDistrito()
				+ " AND ID_MUNICIPIO="
				+ pk.getIdMunicipio()
				+ " AND ID_REGIDURIA="
				+ pk.getIdRegiduria()
				+ " AND ID_COMUNIDAD="
				+ pk.getIdComunidad()
				+ " AND ID_TIPO_CANDIDATURA="
				+ pk.getIdTipoCandidatura()
				+ " AND CAPTURADA <> 0  "
				+ // se agrega la condicion de Capturada <> 0 para no
					// contabilizar los votos de casillas mandadas a recuento
					// total que aun no pasan por la captura de recuento total
				" GROUP BY ID_ASOCIACION, ID_COALICION, TIPO_ASOCIACION, ID_TIPO_CANDIDATURA, ORDEN ORDER BY ORDEN ASC"));

		List<DTOActaCasillaVotos> asociaciones = new ArrayList<>();
		List<Object> result = (List<Object>) criteria.list();
		Iterator itr;
		if (result != null && result.size() > 0) {
			itr = result.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				DTOActaCasillaVotos dtoActa = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK actapk = new DTOActaCasillaVotosPK();

				int idAsoc = (int) obj[0];
				int idCoa = (int) obj[1];
				int tipoAs = (int) obj[2];
				int tipoCand = (int) obj[3];
				int orden = (int) obj[4];
				int votos = (int) (long) obj[5];

				actapk.setIdAsociacion(idAsoc);
				actapk.setIdCoalicion(idCoa);
				actapk.setTipoAsociacion(tipoAs);
				actapk.setIdTipoCandidatura(tipoCand);
				dtoActa.setId(actapk);
				dtoActa.setVotos(votos);
				dtoActa.setOrden(orden);
				asociaciones.add(dtoActa);
			}
		}

		return asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesDistrito(
			DTOActaCasillaVotosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarAsociacionesParticipantesDistrito");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion"), "idAsociacion")
				.add(Projections.property("id.idCoalicion"), "idCoalicion")
				.add(Projections.property("id.tipoAsociacion"),
						"tipoAsociacion")
				.add(Projections.property("id.idTipoCandidatura"),
						"idTipoCandidatura")
				.add(Projections.property("orden"), "orden")
				.add(Projections.sum("votos")));
		criteria.add(Restrictions.sqlRestriction("this_.ID_PROCESO_ELECTORAL="
				+ pk.getIdProcesoElectoral()
				+ " AND ID_DETALLE_PROCESO="
				+ pk.getIdDetalleProceso()
				+ " AND ID_ESTADO="
				+ pk.getIdEstado()
				+ " AND ID_DISTRITO="
				+ pk.getIdDistrito()
				+ " AND ID_REGIDURIA="
				+ pk.getIdRegiduria()
				+ " AND ID_COMUNIDAD="
				+ pk.getIdComunidad()
				+ " AND ID_TIPO_CANDIDATURA="
				+ pk.getIdTipoCandidatura()
				+ " AND CAPTURADA <> 0  "
				+ // se agrega la condicion de Capturada <> 0 para no
					// contabilizar los votos de casillas mandadas a recuento
					// total que aun no pasan por la captura de recuento total
				" GROUP BY ID_ASOCIACION, ID_COALICION, TIPO_ASOCIACION, ID_TIPO_CANDIDATURA, ORDEN ORDER BY ORDEN ASC"));

		List<DTOActaCasillaVotos> asociaciones = new ArrayList<>();
		List<Object> result = (List<Object>) criteria.list();
		Iterator itr;
		if (result != null && result.size() > 0) {
			itr = result.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				DTOActaCasillaVotos dtoActa = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK actapk = new DTOActaCasillaVotosPK();

				int idAsoc = (int) obj[0];
				int idCoa = (int) obj[1];
				int tipoAs = (int) obj[2];
				int tipoCand = (int) obj[3];
				int orden = (int) obj[4];
				int votos = (int) (long) obj[5];

				actapk.setIdAsociacion(idAsoc);
				actapk.setIdCoalicion(idCoa);
				actapk.setTipoAsociacion(tipoAs);
				actapk.setIdTipoCandidatura(tipoCand);
				dtoActa.setId(actapk);
				dtoActa.setVotos(votos);
				dtoActa.setOrden(orden);
				asociaciones.add(dtoActa);
			}
		}

		return asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesEntidad(
			DTOActaCasillaVotosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarAsociacionesParticipantesEntidad");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion"), "idAsociacion")
				.add(Projections.property("id.idCoalicion"), "idCoalicion")
				.add(Projections.property("id.tipoAsociacion"),
						"tipoAsociacion")
				.add(Projections.property("id.idTipoCandidatura"),
						"idTipoCandidatura")
				.add(Projections.property("orden"), "orden")
				.add(Projections.sum("votos")));
		criteria.add(Restrictions.sqlRestriction("this_.ID_PROCESO_ELECTORAL="
				+ pk.getIdProcesoElectoral()
				+ " AND ID_DETALLE_PROCESO="
				+ pk.getIdDetalleProceso()
				+ " AND ID_ESTADO="
				+ pk.getIdEstado()
				+ " AND ID_TIPO_CANDIDATURA="
				+ pk.getIdTipoCandidatura()
				+ " GROUP BY ID_ASOCIACION, ID_COALICION, TIPO_ASOCIACION, ID_TIPO_CANDIDATURA, ORDEN ORDER BY ID_COALICION, ORDEN, ID_ASOCIACION ASC"));

		List<DTOActaCasillaVotos> asociaciones = new ArrayList<>();
		List<Object> result = (List<Object>) criteria.list();
		Iterator itr;
		if (result != null && result.size() > 0) {
			itr = result.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				DTOActaCasillaVotos dtoActa = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK actapk = new DTOActaCasillaVotosPK();

				int idAsoc = (int) obj[0];
				int idCoa = (int) obj[1];
				int tipoAs = (int) obj[2];
				int tipoCand = (int) obj[3];
				int orden = (int) obj[4];
				int votos = (int) (long) obj[5];

				actapk.setIdAsociacion(idAsoc);
				actapk.setIdCoalicion(idCoa);
				actapk.setTipoAsociacion(tipoAs);
				actapk.setIdTipoCandidatura(tipoCand);
				dtoActa.setId(actapk);
				dtoActa.setVotos(votos);
				dtoActa.setOrden(orden);
				asociaciones.add(dtoActa);
			}
		}

		return asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOActaCasillaVotos> consultarVotosAcumuladosAsociacionesDipAyun(
			DTOActaCasillaVotosPK pk) throws Exception {
		log.info("DAODistribucionVotosImpl.consultarAsociacionesParticipantes");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion"), "idAsociacion")
				.add(Projections.property("id.idCoalicion"), "idCoalicion")
				.add(Projections.property("id.tipoAsociacion"),
						"tipoAsociacion")
				.add(Projections.property("id.idTipoCandidatura"),
						"idTipoCandidatura")
				.add(Projections.property("orden"), "orden")
				.add(Projections.sum("votos")));
		criteria.add(Restrictions.sqlRestriction("this_.ID_PROCESO_ELECTORAL="
				+ pk.getIdProcesoElectoral() + " AND ID_DETALLE_PROCESO="
				+ pk.getIdDetalleProceso()
				+ " AND ID_ESTADO="
				+ pk.getIdEstado()
				+ " AND ID_MUNICIPIO="
				+ pk.getIdMunicipio()
				+ " AND ID_COMUNIDAD="
				+ pk.getIdComunidad()
				+ " AND ID_TIPO_CANDIDATURA="
				+ pk.getIdTipoCandidatura()
				+ " AND CAPTURADA <> 0  "
				+ // se agrega la condicion de Capturada <> 0 para no
					// contabilizar los votos de casillas mandadas a recuento
					// total que aun no pasan por la captura de recuento total
				" GROUP BY ID_ASOCIACION, ID_COALICION, TIPO_ASOCIACION, ID_TIPO_CANDIDATURA, ORDEN ORDER BY ORDEN ASC"));

		List<DTOActaCasillaVotos> asociaciones = new ArrayList<>();
		List<Object> result = (List<Object>) criteria.list();
		Iterator itr;
		if (result != null && result.size() > 0) {
			itr = result.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				DTOActaCasillaVotos dtoActa = new DTOActaCasillaVotos();
				DTOActaCasillaVotosPK actapk = new DTOActaCasillaVotosPK();

				int idAsoc = (int) obj[0];
				int idCoa = (int) obj[1];
				int tipoAs = (int) obj[2];
				int tipoCand = (int) obj[3];
				int orden = (int) obj[4];
				int votos = (int) (long) obj[5];

				actapk.setIdAsociacion(idAsoc);
				actapk.setIdCoalicion(idCoa);
				actapk.setTipoAsociacion(tipoAs);
				actapk.setIdTipoCandidatura(tipoCand);
				dtoActa.setId(actapk);
				dtoActa.setVotos(votos);
				dtoActa.setOrden(orden);
				asociaciones.add(dtoActa);
			}
		}

		return asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCSubcoaliciones> getVotosCoalicionesHijas(
			List<DTOActaCasillaVotos> hijas) throws Exception {
		List<DTOCSubcoaliciones> coalicionesHijas = new ArrayList<>();
		if (hijas != null && hijas.size() > 0) {
			List<Integer> idsPadres = new ArrayList<>();
			for (DTOActaCasillaVotos p : hijas) {
				if (!p.getId().getIdCoalicion().equals(-10)
						&& !idsPadres.contains(p.getId().getIdCoalicion())) {
					idsPadres.add(p.getId().getIdCoalicion());
				}
			}
			if (idsPadres.size() > 0) {
				Session session = getSession();
				Criteria criteria = session
						.createCriteria(DTOCSubcoaliciones.class);
				criteria.add(Restrictions.in("idCoalicion", idsPadres));
				coalicionesHijas = (List<DTOCSubcoaliciones>) criteria.list();
			}
		}
		return coalicionesHijas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOSubcoalicion> recuperaSubcoaliciones(
			List<DTOAsociacion> coaliciones) throws Exception {

		List<DTOSubcoalicion> subcoalicones = new ArrayList<DTOSubcoalicion>();

		List<Integer> idsCoaliciones = new ArrayList<>();

		for (DTOAsociacion c : coaliciones) {
			idsCoaliciones.add(c.getIdAsociacion());
		}

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOCSubcoaliciones.class);
		criteria.add(Restrictions.in("idCoalicion", idsCoaliciones));
		criteria.add(Restrictions.not(Restrictions.in("idSubcoalicion",
				idsCoaliciones)));

		criteria.addOrder(Order.asc("idCoalicion"));
		criteria.addOrder(Order.asc("idSubcoalicion"));

		List<DTOCSubcoaliciones> subcoaliconesTemp = new ArrayList<DTOCSubcoaliciones>();
		if (criteria.list() != null && criteria.list().size() > 0) {
			subcoaliconesTemp.addAll(criteria.list());
		}

		for (DTOCSubcoaliciones temp : subcoaliconesTemp) {
			DTOSubcoalicion sub = new DTOSubcoalicion(temp);
			subcoalicones.add(sub);
		}

		return subcoalicones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCSubcoaliciones> getSubcoaliciones(List<Integer> coaliciones)
			throws Exception {

		String cadenaCoaliciones = null;
		// Convertimos la lista de Strings a una cadena
		cadenaCoaliciones = String.valueOf(coaliciones);
		
		log.info("CADENA DE COALICIONES: "+cadenaCoaliciones);
		log.info("LISTA QUE RECIBE: "+coaliciones.size());
		
		if(coaliciones == null || coaliciones.isEmpty()){
			return new ArrayList<DTOCSubcoaliciones>();
		}

		cadenaCoaliciones = cadenaCoaliciones.replace("[", "");
		cadenaCoaliciones = cadenaCoaliciones.replace("]", "");

		Session session = getSession();
		String query = "SELECT ID_COALICION idCoalicion,"
				+ "     ID_SUBCOALICION idSubcoalicion,"
				+ "     EMBLEMA emblema";
		query += "  FROM C_SUBCOALICIONES  ";
		query += "  WHERE ID_COALICION IN" + "( " + cadenaCoaliciones + ")";
		query += "  ORDER BY ID_COALICION, ID_SUBCOALICION";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idCoalicion", IntegerType.INSTANCE)
				.addScalar("idSubcoalicion", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(DTOCSubcoaliciones.class));

		return s.list();
	}

	public DTOCSubcoaliciones getEmblemaCoalicion(Integer coalicion)
			throws Exception {

		DTOCSubcoaliciones emblema = new DTOCSubcoaliciones();
		Session session = getSession();
		String query = "SELECT ID_COALICION idCoalicion,"
				+ "     ID_SUBCOALICION idSubcoalicion,"
				+ "     EMBLEMA emblema," + "     ORDEN orden ";
		query += "  FROM C_SUBCOALICIONES  ";
		query += "  WHERE ID_COALICION =" + coalicion;
		query += "  AND ID_SUBCOALICION =" + coalicion;

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idCoalicion", IntegerType.INSTANCE)
				.addScalar("idSubcoalicion", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(DTOCSubcoaliciones.class));

		emblema = (DTOCSubcoaliciones) s.uniqueResult();

		return emblema;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCDetalleSubcoaliciones> getVotosHijas(
			List<DTOActaCasillaVotos> asociaciones,
			List<DTOCSubcoaliciones> hijas) throws Exception {
		List<DTOCDetalleSubcoaliciones> detalle = new ArrayList<>();
		if (hijas != null && hijas.size() > 0) {
			List<Integer> coas = new ArrayList<>();
			List<Integer> subcoas = new ArrayList<>();
			for (DTOCSubcoaliciones sub : hijas) {
				int coa = sub.getIdCoalicion();
				int subcoa = sub.getIdSubcoalicion();
				coas.add(coa);
				subcoas.add(subcoa);
			}
			if (coas != null && coas.size() > 0 && subcoas != null
					&& subcoas.size() > 0) {
				Session session = getSession();
				Criteria criteria = session
						.createCriteria(DTOCDetalleSubcoaliciones.class);
				criteria.add(Restrictions.in("idCoalicion", coas));
				criteria.add(Restrictions.in("idSubcoalicion", subcoas));
				detalle = (List<DTOCDetalleSubcoaliciones>) criteria.list();
			}
		}
		return detalle;
	}

	@Override
	public boolean existeDistribucionTotales(DTODistribucionTotalesPK id)
			throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getSession();
		qry = this.getContainer().getQuery(
				"query_verifica_distribucion_totales_2");
		sqlQry = sesion.createSQLQuery(qry);

		// TODO Agrega los parametros de entrada del query.
		sqlQry.setParameter("idProcesoElectoral", id.getIdProcesoElectoral(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idDetalleProceso", id.getIdDetalleProceso(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idTipoCandidatura", id.getIdTipoCandidatura(),
				IntegerType.INSTANCE);

		sqlQry.setParameter("idEstado", id.getIdEstado(), IntegerType.INSTANCE);
		sqlQry.setParameter("idMunicipio", id.getIdMunicipio(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idRegiduria", id.getIdRegiduria(),
				IntegerType.INSTANCE);

		BigDecimal resultado = (BigDecimal) sqlQry.uniqueResult();

		if (resultado != null && resultado.intValue() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existeDistribucionPartidosCI(DTODistribucionPartidosCIPK id)
			throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getSession();
		qry = this.getContainer().getQuery("query_verifica_distribucion_ppci");
		sqlQry = sesion.createSQLQuery(qry);

		// TODO Agrega los parametros de entrada del query.
		sqlQry.setParameter("idProcesoElectoral", id.getIdProcesoElectoral(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idDetalleProceso", id.getIdDetalleProceso(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idTipoCandidatura", id.getIdTipoCandidatura(),
				IntegerType.INSTANCE);

		sqlQry.setParameter("idEstado", id.getIdEstado(), IntegerType.INSTANCE);
		sqlQry.setParameter("idMunicipio", id.getIdMunicipio(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idRegiduria", id.getIdRegiduria(),
				IntegerType.INSTANCE);

		BigDecimal resultado = (BigDecimal) sqlQry.uniqueResult();

		if (resultado != null && resultado.intValue() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existeDistribucionCandidatos(DTODistribucionCandidatosPK id)
			throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getSession();
		qry = this.getContainer().getQuery("query_verifica_distribucion_cand");
		sqlQry = sesion.createSQLQuery(qry);

		// TODO Agrega los parametros de entrada del query.
		sqlQry.setParameter("idProcesoElectoral", id.getIdProcesoElectoral(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idDetalleProceso", id.getIdDetalleProceso(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idTipoCandidatura", id.getIdTipoCandidatura(),
				IntegerType.INSTANCE);

		sqlQry.setParameter("idEstado", id.getIdEstado(), IntegerType.INSTANCE);
		sqlQry.setParameter("idMunicipio", id.getIdMunicipio(),
				IntegerType.INSTANCE);
		sqlQry.setParameter("idRegiduria", id.getIdRegiduria(),
				IntegerType.INSTANCE);

		BigDecimal resultado = (BigDecimal) sqlQry.uniqueResult();

		if (resultado != null && resultado.intValue() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void eliminaTotales(DTODistribucionTotalesPK pk) throws Exception {
		String query = "DELETE FROM DISTRIBUCION_TOTALES ";
		query += "WHERE ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_DISTRITO = " + pk.getIdDistrito() + " ";
		query += "AND ID_REGIDURIA = " + pk.getIdRegiduria() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();
	}

	@Override
	public void eliminaDistribucionPP(DTODistribucionTotalesPK pk)
			throws Exception {
		String query = "DELETE FROM DISTRIBUCION_PARTIDOS_CI ";
		query += "WHERE ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_DISTRITO = " + pk.getIdDistrito() + " ";
		query += "AND ID_REGIDURIA = " + pk.getIdRegiduria() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();
	}

	@Override
	public void eliminaDistribucionCandidato(DTODistribucionTotalesPK pk)
			throws Exception {
		String query = "DELETE FROM DISTRIBUCION_CANDIDATOS ";
		query += "WHERE ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_DISTRITO = " + pk.getIdDistrito() + " ";
		query += "AND ID_REGIDURIA = " + pk.getIdRegiduria() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();
	}

	@Override
	public void saveTotales(List<DTODistribucionTotales> totales)
			throws Exception {
		Session session = getSession();
		for (DTODistribucionTotales total : totales) {
			session.saveOrUpdate(total);
			session.flush();
			session.clear();
		}
	}

	@Override
	public void saveDistribucionPP(List<DTODistribucionPartidosCI> ppci)
			throws Exception {
		Session session = getSession();
		for (DTODistribucionPartidosCI pp : ppci) {
			session.saveOrUpdate(pp);
			session.flush();
			session.clear();
		}
	}

	@Override
	public void saveDistribucionCandidato(
			List<DTODistribucionCandidatos> candidatos) throws Exception {
		Session session = getSession();
		for (DTODistribucionCandidatos candidato : candidatos) {
			session.saveOrUpdate(candidato);
			session.flush();
			session.clear();
		}
	}

	@Override
	public boolean buscarDistribucionParcialCreada(
			DTODistribucionCandidatosPK buscarDistribucion) throws Exception {
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionCandidatos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				buscarDistribucion.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				buscarDistribucion.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado",
				buscarDistribucion.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito",
				buscarDistribucion.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio",
				buscarDistribucion.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria",
				buscarDistribucion.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				buscarDistribucion.getIdTipoCandidatura()));
		List<DTODistribucionCandidatos> distribucionInsertada = (List<DTODistribucionCandidatos>) criteria
				.list();
		if (distribucionInsertada != null && distribucionInsertada.size() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean buscarDistribucionCreada(
			DTODistribucionTotalesPK buscarDistribucion) throws Exception {
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTODistribucionTotales.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				buscarDistribucion.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				buscarDistribucion.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado",
				buscarDistribucion.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito",
				buscarDistribucion.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio",
				buscarDistribucion.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria",
				buscarDistribucion.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				buscarDistribucion.getIdTipoCandidatura()));
		List<DTODistribucionTotales> distribucionInsertada = (List<DTODistribucionTotales>) criteria
				.list();
		if (distribucionInsertada != null && distribucionInsertada.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * guarda la distribucion de final del partidos y candidatos
	 * 
	 * @param ppCI
	 * @throws Exception
	 */
	@Override
	public void guardarDistribucionPartidosCI(
			List<DTODistribucionPartidosCI> ppCI) throws Exception {
		Session session = getSession();
		for (DTODistribucionPartidosCI total : ppCI) {
			session.saveOrUpdate(total);
			session.flush();
			session.clear();
		}
	}

	/**
	 * Método que guarda la distribución total parcial en la tabla de totales
	 * parcial
	 * 
	 * @param totParcial
	 * @throws Exception
	 */
	@Override
	public void guardarDistribucionTotalParcial(
			List<DTODistribucionTotParcial> totParcial) throws Exception {
		Session session = getSession();
		for (DTODistribucionTotParcial total : totParcial) {
			session.saveOrUpdate(total);
			session.flush();
			session.clear();
		}
	}

	/**
	 * Método que guarda la distribución de Candidatos en la tabla de Candidatos
	 * parcial
	 * 
	 * @param candParcial
	 * @throws Exception
	 */
	@Override
	public void guardarDistribucionCandParcial(
			List<DTODistribucionCandParcial> candParcial) throws Exception {
		Session session = getSession();
		for (DTODistribucionCandParcial total : candParcial) {
			session.saveOrUpdate(total);
			session.flush();
			session.clear();
		}

	}

	/**
	 * Método que consulta las coaliciones para un tipo de candidatura
	 * 
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Integer> getCoaliciones(Object[] arg0, Integer idDistrito)
			throws Exception {
		Session session = getSession();
		String query = "SELECT DISTINCT ID_COALICION idCoalicion";
		query += "  FROM ACTA_CASILLA_VOTOS  ";
		query += "  WHERE ID_PROCESO_ELECTORAL =" + (Integer) arg0[0];
		query += " AND ID_DETALLE_PROCESO = " +(Integer) arg0[1];
		query += " AND ID_ESTADO = " +(Integer) arg0[2];
		if((Integer) arg0[4] == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
			query += " AND ID_DISTRITO = " +idDistrito;
		}
		query += " AND ID_MUNICIPIO = " +(Integer) arg0[3];
		query += " AND ID_TIPO_CANDIDATURA = " +(Integer) arg0[4];
		query += " AND ID_COALICION <> " + new Integer(-10);

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idCoalicion", IntegerType.INSTANCE);

		// Criteria criteria =
		// session.createCriteria(DTOActaCasillaVotos.class);
		// ProjectionList projectionDist= Projections.projectionList();
		// Integer[] sinCoalicion = {Constantes.SIN_COALICION};
		//
		// //Se definen las condiciones para recuperar la información de la
		// tabla
		// criteria.add(Restrictions.eq("id.idProcesoElectoral",(Integer)
		// arg0[0]));
		// criteria.add(Restrictions.eq("id.idDetalleProceso",(Integer)
		// arg0[1]));
		// criteria.add(Restrictions.eq("id.idEstado", (Integer) arg0[2]));
		// //Solo si el tipo de candidatura es diputado MR se agrega el distrito
		// if((Integer) arg0[4] == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
		// criteria.add(Restrictions.eq("id.idDistrito", idDistrito));
		// }
		//
		// criteria.add(Restrictions.eq("id.idMunicipio", (Integer) arg0[3]));
		// criteria.add(Restrictions.eq("id.idTipoCandidatura",(Integer)
		// arg0[4]));
		// criteria.add(Restrictions.ne("id.idCoalicion", new Integer(-10)));

		// Se hace un distinct
//		projectionDist.add(Projections.property("id.idCoalicion").as(
//				"idCoalicion"));
//		criteria.setProjection(Projections.distinct(projectionDist));

		return (List<Integer>) s.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPartidosCoalicion(List<Integer> coaliciones)
			throws Exception {
		
		if(coaliciones == null || coaliciones.isEmpty()){
			return new ArrayList<Object[]>();
		}
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTOCDetalleSubcoaliciones.class);
		ProjectionList projectionDist = Projections.projectionList();

		// Se definen las condiciones para recuperar la informacion de los
		// partidos de la subcoalicion
		criteria.add(Restrictions.in("idCoalicion", coaliciones));

		// Se hace un distinct
		projectionDist.add(Projections.property("idAsociacion").as(
				"idAsociacion"));
		projectionDist.add(Projections.property("idCoalicion")
				.as("idCoalicion"));
		criteria.setProjection(Projections.distinct(projectionDist));

		List<Object[]> result = (List<Object[]>) criteria.list();

		return result;
	}

	/**
	 * Método que consulta los partidos politicos que forman parte de una
	 * coalicion
	 * 
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getPartidosSinCoalicion(
			List<Integer> partidosConCoalicion) throws Exception {

		String cadenaCoaliciones = null;
		// Convertimos la lista de Strings a una cadena
		cadenaCoaliciones = String.valueOf(partidosConCoalicion);

		cadenaCoaliciones = cadenaCoaliciones.replace("[", "");
		cadenaCoaliciones = cadenaCoaliciones.replace("]", "");

		Session session = getSession();
		String query = "SELECT DISTINCT(a.ID_ASOCIACION) idAsociacion";
		query += "  FROM ACTA_CASILLA_VOTOS a, C_DETALLE_SUBCOALICIONES b ";
		if(partidosConCoalicion != null && !partidosConCoalicion.isEmpty()){
		query += "  WHERE a.ID_ASOCIACION NOT IN" + "( " + cadenaCoaliciones
				+ ")";
		query += "  AND a.TIPO_ASOCIACION = "
				+ Constantes.TIPO_ASOCIACION_PARTIDO;
		}else{
			query += "  WHERE a.TIPO_ASOCIACION = "
				+ Constantes.TIPO_ASOCIACION_PARTIDO;
		}
		

		SQLQuery s = getSession().createSQLQuery(query).addScalar(
				"idAsociacion", IntegerType.INSTANCE);

		List<Integer> result = (List<Integer>) s.list();

		return result;
	}

	
	public List<HLPDistribucionVotos> getTotalVotosPPCIMRyRPFinal(Object[] arg0)
			throws Exception {

		Integer[] candidaturas = { Constantes.ID_TIPO_CAND_DIPUTADO_MR,
				Constantes.ID_TIPO_CAND_DIPUTADO_RP };

		Session session = getSession();
		
		
		String query = " SELECT idProcesoElectoral, "
				+ "     idDetalleProceso, "
				+ "     idEstado,       " 
				+ "     idAsociacion,   "
				+ "     tipoAsociacion, "
				+ "     orden,     "
				+ "     emblema,   "
				+ "     VOTOS_RP,  "
				+ "     SUM(NVL(VOTOS_RP,0)) votos "
				+ "     FROM  "
				+ "     (SELECT DP.ID_PROCESO_ELECTORAL idProcesoElectoral, "
				+ "     DP.ID_DETALLE_PROCESO idDetalleProceso, "
				+ "     DP.ID_ESTADO idEstado, "
				+ "     DP.ID_ASOCIACION idAsociacion, "
				+ "     DP.TIPO_ASOCIACION tipoAsociacion, "
				+ "     DP.ORDEN orden, "
				+ "     DP.EMBLEMA emblema, "
				+ "     SUM(DP.VOTOS) AS VOTOS_RP "
	     		+ "     FROM DISTRIBUCION_PARTIDOS_CI DP   "
	     		+ "     WHERE DP.ID_PROCESO_ELECTORAL  =   "+ (Integer) arg0[0]
	     		+ "     AND DP.ID_DETALLE_PROCESO      =   "+ (Integer) arg0[1]
	     		+ "     AND DP.ID_ESTADO               =   "+ (Integer) arg0[2] + ""
	     		+ "     AND DP.ID_TIPO_CANDIDATURA     =   "+ candidaturas[1] + " "
	     		+ "     AND DP.TIPO_ASOCIACION 		 !=    "+ Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE + " "
	     		+ "     AND DP.ID_DISTRITO           !=     "+Constantes.SIN_DISTRITO + " "
	     		+ "     GROUP BY DP.ID_PROCESO_ELECTORAL, DP.ID_DETALLE_PROCESO, DP.ID_ESTADO, DP.ID_ASOCIACION, "
	     		+ "     DP.TIPO_ASOCIACION, DP.ORDEN, DP.EMBLEMA "
	     		+ "     )  "
	     		+ "     GROUP BY VOTOS_RP, idProcesoElectoral, idDetalleProceso, idEstado,  "
	     		+ "     idAsociacion, tipoAsociacion, orden, emblema  ";
	
		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPDistribucionVotos.class));
		
		return s.list();
	
	}
	
	
	public List<HLPDistribucionVotos> getDistribucionParcialByDistritoRP(Object[] arg0)
			throws Exception {

		Integer[] candidaturas = { Constantes.ID_TIPO_CAND_DIPUTADO_MR,
				Constantes.ID_TIPO_CAND_DIPUTADO_RP };
		
		Session session = getSession();
		
		String query = "SELECT idProcesoElectoral, "
				+ "     idDetalleProceso, "
				+ "     idEstado, "
				+ "     idDistrito, "
				+ "     idAsociacion,"
				+ "     tipoAsociacion,"
				+ "     orden,"
				+ "     emblema,"
				+ "     VOTOS_MR,"
				+ "     VOTOS_RP, "
				+ "     SUM(VOTOS_MR + NVL(VOTOS_RP,0)) votos "
				+ "     FROM  "
				+ "      (SELECT DP.ID_PROCESO_ELECTORAL idProcesoElectoral, "
				+ "     DP.ID_DETALLE_PROCESO idDetalleProceso, "
				+ "     DP.ID_ESTADO idEstado, "
				+ "     DP.ID_DISTRITO idDistrito,"
				+ "     DP.ID_ASOCIACION idAsociacion, "
				+ "     DP.TIPO_ASOCIACION tipoAsociacion,"
				+ "     DP.ORDEN orden, "
				+ "     DP.EMBLEMA emblema, "
				+ "     SUM(DP.VOTOS) AS VOTOS_MR, "
				+ "     (SELECT SUM(DC.VOTOS)"
				+ "     FROM ACTA_CASILLA_VOTOS DC "
				+ "     WHERE DC.ID_PROCESO_ELECTORAL = DP.ID_PROCESO_ELECTORAL "
				+ "     AND DC.ID_DETALLE_PROCESO     = DP.ID_DETALLE_PROCESO "
				+ "     AND DC.ID_ESTADO              = DP.ID_ESTADO "
				+ "     AND DC.ID_DISTRITO            = DP.ID_DISTRITO "
				+ "     AND DC.ID_TIPO_CANDIDATURA    = "+ candidaturas[1] + ""
				+ "     AND DC.ID_ASOCIACION          = DP.ID_ASOCIACION "
			//	+ "     AND DC.TIPO_ASOCIACION        = DP.TIPO_ASOCIACION "
				+ "     ) AS VOTOS_RP   "
				+ "      FROM DISTRIBUCION_PARTIDOS_CI DP   "
				+ "     WHERE DP.ID_PROCESO_ELECTORAL = " + (Integer) arg0[0] + ""
				+ "     AND DP.ID_DETALLE_PROCESO     =  "+ (Integer) arg0[1] + ""
				+ "     AND DP.ID_ESTADO              =  "+ (Integer) arg0[2] + ""
				+ "     AND DP.ID_DISTRITO            =  "+ (Integer) arg0[3] + ""
				+ "     AND DP.ID_TIPO_CANDIDATURA    =  "+ candidaturas[0]   + ""
				+ "     AND DP.TIPO_ASOCIACION <>        " + Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE + ""
				+ "     GROUP BY DP.ID_PROCESO_ELECTORAL, DP.ID_DETALLE_PROCESO, DP.ID_ESTADO, DP.ID_DISTRITO, DP.ID_ASOCIACION, "
				+ "     DP.TIPO_ASOCIACION, DP.ORDEN, DP.EMBLEMA "
				+ "     )  "
				+ "     GROUP BY VOTOS_MR, VOTOS_RP, idProcesoElectoral, idDetalleProceso, idEstado,  "
				+ "     idDistrito, idAsociacion, tipoAsociacion, orden, emblema  ";
		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idDistrito", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPDistribucionVotos.class));

		return s.list();

	}

	@Override
	public void eliminaDistribucionCandParcial(DTODistribucionCandParcialPK pk)
			throws Exception {

		String query = "DELETE FROM DISTRIBUCION_CAND_PARCIAL ";
		query += "WHERE ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_PROCESO_ELECTORAL = " + pk.getIdProcesoElectoral()
				+ " ";
		query += "AND ID_DETALLE_PROCESO = " + pk.getIdDetalleProceso() + " ";
		query += "AND ID_ESTADO = " + pk.getIdEstado() + " ";
		query += "AND ID_DISTRITO = " + pk.getIdDistrito() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();
	}

	@Override
	public void eliminaDistribucionTotParcial(DTODistribucionTotParcialPK pk)
			throws Exception {

		String query = "DELETE FROM DISTRIBUCION_TOT_PARCIAL ";
		query += "WHERE ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_PROCESO_ELECTORAL = " + pk.getIdProcesoElectoral()
				+ " ";
		query += "AND ID_DETALLE_PROCESO = " + pk.getIdDetalleProceso() + " ";
		query += "AND ID_ESTADO = " + pk.getIdEstado() + " ";
		query += "AND ID_DISTRITO = " + pk.getIdDistrito() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();

	}

	@Override
	public void eliminaDistribucionPartidosCI(DTODistribucionPartidosCIPK pk)
			throws Exception {

		String query = "DELETE FROM DISTRIBUCION_PARTIDOS_CI ";
		query += "WHERE ID_PROCESO_ELECTORAL = " + pk.getIdProcesoElectoral()
				+ " ";
		query += "AND ID_DETALLE_PROCESO = " + pk.getIdDetalleProceso() + " ";
		query += "AND ID_ESTADO = " + pk.getIdEstado() + " ";
		query += "AND ID_MUNICIPIO = " + pk.getIdMunicipio() + " ";
		query += "AND ID_REGIDURIA = " + pk.getIdRegiduria() + " ";
		query += "AND ID_TIPO_CANDIDATURA = " + pk.getIdTipoCandidatura() + " ";

		SQLQuery q = getSession().createSQLQuery(query);
		q.executeUpdate();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getDistribucionFinalByDistritos(Integer idProceso,
														 Integer idDetalleProceso,
														 Integer idEstado,
														 Integer idTipoCandidatura) throws Exception{
		
		
		String s = " SELECT DISTINCT(ID_DISTRITO) idDistrito FROM DISTRIBUCION_PARTIDOS_CI WHERE ID_TIPO_CANDIDATURA=" + idTipoCandidatura;
		
		SQLQuery query = getSession().createSQLQuery(s).addScalar("idDistrito", IntegerType.INSTANCE);
		
		List<Integer> lista = query.list();
		
		
		return lista;
	}
	
	
}
