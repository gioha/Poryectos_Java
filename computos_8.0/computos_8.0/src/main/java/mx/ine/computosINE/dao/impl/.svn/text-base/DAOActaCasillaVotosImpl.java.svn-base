package mx.ine.computosINE.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.computosINE.dao.DAOActaCasillaVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

/**
 * <code>DAOActaCasillaVotosInterface.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 27/04/2017 11:27:00
 */
@Scope("prototype")
@Repository("daoActaCasillaVotos")
public class DAOActaCasillaVotosImpl extends DAOGeneric<DTOActaCasillaVotos, Integer> implements DAOActaCasillaVotosInterface {

	private static final Log log = LogFactory.getLog(DAOActaCasillaVotosImpl.class);

	Query query;
	SQLQuery sqlQry;
	String qry;

	@Override
	@SuppressWarnings("unchecked")
	public BigDecimal getCountVotosCapturados(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getCountVotosCaptEspeciales");

		Criteria criteria;
		ProjectionList projectionList0 = Projections.projectionList();
		ProjectionList projectionList = Projections.projectionList();
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		if ( id.getIdTipoCandidatura() == Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_DRP")) ) {
			// TODO CANDIDATURA DIPUTADOS RP
			criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		} else {
			// TODO CANDIDATURA REGIDURIAS RP
			criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		}
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));

		projectionList0.add(Projections.property("id.idProcesoElectoral").as("idProcesoElectoral"));
		projectionList0.add(Projections.property("id.idDetalleProceso").as("idDetalleProceso"));
		projectionList0.add(Projections.property("id.idEstado").as("idEstado"));
		projectionList0.add(Projections.property("id.idMunicipio").as("idMunicipio"));
		projectionList0.add(Projections.property("id.seccion").as("seccion"));
		projectionList0.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionList0.add(Projections.property("id.tipoCasilla").as("tipoCasilla"));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));

		criteria.setProjection(projectionList0);
		criteria.setProjection(projectionList);

		List<Object[]> resultado1 = (List<Object[]>) criteria.list();

		BigDecimal resultado = new BigDecimal(resultado1.size());
		return resultado;
	}

	@Override
	public BigDecimal getVotosAcumuladosDiputados(DTOActaCasillaVotosPK id)	throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getVotosAcumuladosDiputados");
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));
		if ( id.getIdTipoCandidatura() == Integer.valueOf(Utilidades.mensajeProperties("constante_idTipoCandidatura_DRP")) ) {
			// TODO CANDIDATURA DIPUTADOS RP
			criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		} else {
			// TODO CANDIDATURA REGIDURIAS RP
			criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		}
		criteria.add(Restrictions.eq("id.tipoAsociacion", id.getTipoAsociacion()));
		criteria.add(Restrictions.eq("id.idAsociacion", id.getIdAsociacion()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.setProjection(Projections.sum("votos"));
		if (criteria.uniqueResult() == null) {
			return new BigDecimal(0);
		}
		BigDecimal resultado = BigDecimal.valueOf((long) criteria.uniqueResult());
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getListActaCasillaVotos");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.seccion", id.getSeccion()));
		criteria.add(Restrictions.eq("id.idCasilla", id.getIdCasilla()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.add(Restrictions.eq("id.extContigua", id.getExtContigua()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));

		List<DTOActaCasillaVotos> resultado = (List<DTOActaCasillaVotos>) criteria.list();
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasEntidad(DTOActaCasillaVotosPK pk) throws Exception {
		log.info("DAOActaCasillaVotosImpl.getActasEntidad");
		List<DTOActaCasillaVotos> actasEntidad = new ArrayList<>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion"), "idAsociacion")
				.add(Projections.property("id.idCoalicion"), "idCoalicion")
				.add(Projections.property("id.tipoAsociacion"),	"tipoAsociacion")
				.add(Projections.property("id.idTipoCandidatura"), "idTipoCandidatura")
				.add(Projections.property("orden"), "orden")
				.add(Projections.sum("votos")));

		criteria.add(Restrictions.sqlRestriction("this_.ID_PROCESO_ELECTORAL="
				+ pk.getIdProcesoElectoral() + " AND ID_DETALLE_PROCESO="
				+ pk.getIdDetalleProceso()
				+ " AND ID_ESTADO="
				+ pk.getIdEstado()
				+ " AND ID_TIPO_CANDIDATURA="
				+ pk.getIdTipoCandidatura()
				+ " AND CAPTURADA <> 0  "
				+ // se agrega la condicion de Capturada <> 0 para no
					// contabilizar los votos de casillas mandadas a recuento
					// total que aun no pasan por la captura de recuento total
				" GROUP BY ID_ASOCIACION, ID_COALICION, TIPO_ASOCIACION, ID_TIPO_CANDIDATURA, ORDEN ORDER BY ORDEN ASC"));

		List<Object> result = (List<Object>) criteria.list();
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				actasEntidad.add(new DTOActaCasillaVotos());
			}
		}
		return actasEntidad;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> consultaListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception {
		// TODO Auto-generated method stub

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));

		List<DTOActaCasillaVotos> resultado = (List<DTOActaCasillaVotos>) criteria.list();
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> consultaActasCapturadasXestado(DTOActaCasillaVotosPK id, String modulo) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		ProjectionList projectionListA = Projections.projectionList();
		ProjectionList projectionListB = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);

		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));

		projectionListA.add(Projections.property("id.idProcesoElectoral").as("idProcesoElectoral"));
		projectionListA.add(Projections.property("id.idDetalleProceso").as("idDetalleProceso"));
		projectionListA.add(Projections.property("id.idEstado").as("idEstado"));
		if (modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP"))) {
			// TODO CANDIDATURA DIPUTADOS RP
			projectionListA.add(Projections.property("id.idDistrito").as("idDistrito"));
		} else {
			// TODO CANDIDATURA REGIDURIAS RP
			projectionListA.add(Projections.property("id.idRegiduria").as("idRegiduria"));
		}
		projectionListA.add(Projections.property("id.idMunicipio").as("idMunicipio"));
		projectionListA.add(Projections.property("id.seccion").as("seccion"));
		projectionListA.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionListA.add(Projections.property("id.tipoCasilla").as("tipoCasilla"));
		projectionListA.add(Projections.property("id.extContigua").as("extContigua"));
		projectionListA.add(Projections.property("listaNominal").as("listaNominal"));
		projectionListA.add(Projections.property("idEstatus").as("idEstatus"));

		projectionListB.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionListB.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionListB.add(Projections.groupProperty("id.idEstado"));
		if (modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP"))) {
			// TODO CANDIDATURA DIPUTADOS RP
			projectionListB.add(Projections.groupProperty("id.idDistrito"));
		} else {
			// TODO CANDIDATURA REGIDURIAS RP
			projectionListB.add(Projections.groupProperty("id.idRegiduria"));
		}
		projectionListB.add(Projections.groupProperty("id.idMunicipio"));
		projectionListB.add(Projections.groupProperty("id.seccion"));
		projectionListB.add(Projections.groupProperty("id.idCasilla"));
		projectionListB.add(Projections.groupProperty("id.tipoCasilla"));
		projectionListB.add(Projections.groupProperty("id.extContigua"));
		projectionListB.add(Projections.groupProperty("listaNominal"));
		projectionListB.add(Projections.groupProperty("idEstatus"));

		criteria.setProjection(projectionListA);
		criteria.setProjection(projectionListB);

		List<Object[]> resultado = (List<Object[]>) criteria.list();
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> consultaCandidatosXacta(DTOActaCasillaVotosPK id, Integer idEstatus, String modulo) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);

		criteria.add(Restrictions.eq("id.idProcesoElectoral", id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",	id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		if (modulo.equalsIgnoreCase(Utilidades.mensajeProperties("constante_modulo_diputadosRP"))) {
			criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		} else {
			criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		}
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.seccion", id.getSeccion()));
		criteria.add(Restrictions.eq("id.idCasilla", id.getIdCasilla()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.add(Restrictions.eq("id.extContigua", id.getExtContigua()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));
		if ( idEstatus != null ) {
			criteria.add(Restrictions.eq("idEstatus", idEstatus));
		}

		List<DTOActaCasillaVotos> resultado = (List<DTOActaCasillaVotos>) criteria.list();
		return resultado;
	}

	@Override
	public boolean existeGeneracionActa(DTOActaTipoCandidaturaPK id) throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getSession();
		qry = this.getContainer().getQuery("query_verifica_generacion_acta");
		sqlQry = sesion.createSQLQuery(qry);

		// TODO Agrega los parametros de entrada del query.
		sqlQry.setParameter("idProcesoElectoral", id.getIdProcesoElectoral(), IntegerType.INSTANCE);
		sqlQry.setParameter("idDetalleProceso", id.getIdDetalleProceso(),     IntegerType.INSTANCE);
		sqlQry.setParameter("idTipoCandidatura", id.getIdTipoCandidatura(),   IntegerType.INSTANCE);

		sqlQry.setParameter("idEstado", id.getIdEstado(), IntegerType.INSTANCE);
		sqlQry.setParameter("idDistrito", id.getIdDistrito(), IntegerType.INSTANCE);
		sqlQry.setParameter("idMunicipio", id.getIdMunicipio(),	IntegerType.INSTANCE);

		BigDecimal resultado = (BigDecimal) sqlQry.uniqueResult();

		if ( resultado != null && resultado.intValue() > 0 ) {
			return true;
		}
		return false;
	}

	@Override
	public void saveOrUpdate(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		for (DTOActaCasillaVotos it : votos) {
			session.saveOrUpdate(it);
			session.flush();
			session.clear();
		}
	}

	@Override
	public void eliminar(List<DTOActaCasillaVotos> votos) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		for (DTOActaCasillaVotos it : votos) {
			session.delete(it);
			session.flush();
			session.clear();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public BigDecimal getCountActasCapturadasByCandidatura(Object[] arg0)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getCountVotosCaptTipoCandidatura-----se cuenta el total de votos");

		// Se define el tipo de casilla especial
		String tipoCasillaEspecial = Constantes.TIPO_CASILLA_ESPECIAL;
		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		ProjectionList projectionGroup = Projections.projectionList();
		ProjectionList projectionDist = Projections.projectionList();
		criteria.add(Restrictions
				.eq("id.idProcesoElectoral", (Integer) arg0[0]));
		criteria.add(Restrictions.eq("id.idDetalleProceso", (Integer) arg0[1]));
		criteria.add(Restrictions.eq("id.idEstado", (Integer) arg0[2]));
		// criteria.add(Restrictions.eq("id.idDistrito", (Integer) arg0[3]));
		criteria.add(Restrictions.eq("id.idMunicipio", (Integer) arg0[4]));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", (Integer) arg0[5]));
		criteria.add(Restrictions.eq("capturada", new Integer(1)));

		if ((Integer) arg0[5] == Constantes.ID_TIPO_CAND_DIPUTADO_RP) {
			criteria.add(Restrictions.eq("id.tipoCasilla", tipoCasillaEspecial));
		}

		// Se hace un distinct
		projectionDist.add(Projections.property("id.idProcesoElectoral").as(
				"idProcesoElectoral"));
		projectionDist.add(Projections.property("id.idDetalleProceso").as(
				"idDetalleProceso"));
		projectionDist.add(Projections.property("id.idEstado").as("idEstado"));
		// projectionDist.add(Projections.property("id.idDistrito").as(
		// "idDistrito"));
		projectionDist.add(Projections.property("id.idMunicipio").as(
				"idMunicipio"));
		projectionDist.add(Projections.property("id.seccion").as("seccion"));
		projectionDist
				.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionDist.add(Projections.property("id.tipoCasilla").as(
				"tipoCasilla"));
		projectionDist.add(Projections.property("id.extContigua").as(
				"extContigua"));
		criteria.setProjection(Projections.distinct(projectionDist));

		// se hace un proup by por los datos del distinct
		projectionGroup.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionGroup.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionGroup.add(Projections.groupProperty("id.idEstado"));
		// projectionGroup.add(Projections.groupProperty("id.idDistrito"));
		projectionGroup.add(Projections.groupProperty("id.idMunicipio"));
		projectionGroup.add(Projections.groupProperty("id.seccion"));
		projectionGroup.add(Projections.groupProperty("id.idCasilla"));
		projectionGroup.add(Projections.groupProperty("id.tipoCasilla"));
		projectionGroup.add(Projections.groupProperty("id.extContigua"));
		criteria.setProjection(projectionGroup);

		List<Object[]> resultado1 = (List<Object[]>) criteria.list();

		BigDecimal resultado = new BigDecimal(resultado1.size());
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasCapturadasxEntidadCandidatura(
			DTOActaCasillaVotosPK pk) throws Exception {

		List<DTOActaCasillaVotos> actasEntidad = new ArrayList<>();

		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		ProjectionList projectionGroup = Projections.projectionList();
		ProjectionList projectionDist = Projections.projectionList();
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				(Integer) pk.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				(Integer) pk.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", (Integer) pk.getIdEstado()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				(Integer) pk.getIdTipoCandidatura()));

		// Se hace un distinct
		projectionDist.add(Projections.property("id.idProcesoElectoral").as(
				"idProcesoElectoral"));
		projectionDist.add(Projections.property("id.idDetalleProceso").as(
				"idDetalleProceso"));
		projectionDist.add(Projections.property("id.idEstado").as("idEstado"));
		projectionDist.add(Projections.property("id.idMunicipio").as(
				"idMunicipio"));
		projectionDist.add(Projections.property("id.seccion").as("seccion"));
		projectionDist
				.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionDist.add(Projections.property("id.tipoCasilla").as(
				"tipoCasilla"));
		projectionDist.add(Projections.property("id.extContigua").as(
				"extContigua"));
		criteria.setProjection(Projections.distinct(projectionDist));

		// se hace un proup by por los datos del distinct
		projectionGroup.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionGroup.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionGroup.add(Projections.groupProperty("id.idEstado"));
		projectionGroup.add(Projections.groupProperty("id.idMunicipio"));
		projectionGroup.add(Projections.groupProperty("id.seccion"));
		projectionGroup.add(Projections.groupProperty("id.idCasilla"));
		projectionGroup.add(Projections.groupProperty("id.tipoCasilla"));
		projectionGroup.add(Projections.groupProperty("id.extContigua"));
		criteria.setProjection(projectionGroup);

		List<Object[]> resultado1 = (List<Object[]>) criteria.list();

		if (resultado1 != null && resultado1.size() > 0) {
			for (int i = 0; i < resultado1.size(); i++) {
				actasEntidad.add(new DTOActaCasillaVotos());
			}
		}

		return actasEntidad;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BigDecimal getActasCapturadasxMunicipioCandidatura(Object[] arg0)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getCountVotosCaptTipoCandidatura-----se cuenta el total de votos");

		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		ProjectionList projectionGroup = Projections.projectionList();
		ProjectionList projectionDist = Projections.projectionList();
		criteria.add(Restrictions
				.eq("id.idProcesoElectoral", (Integer) arg0[0]));
		criteria.add(Restrictions.eq("id.idDetalleProceso", (Integer) arg0[1]));
		criteria.add(Restrictions.eq("id.idEstado", (Integer) arg0[2]));
		criteria.add(Restrictions.eq("id.idMunicipio", (Integer) arg0[3]));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", (Integer) arg0[4]));
		criteria.add(Restrictions.eq("capturada", new Integer(1)));

		// Se hace un distinct
		projectionDist.add(Projections.property("id.idProcesoElectoral").as(
				"idProcesoElectoral"));
		projectionDist.add(Projections.property("id.idDetalleProceso").as(
				"idDetalleProceso"));
		projectionDist.add(Projections.property("id.idEstado").as("idEstado"));
		projectionDist.add(Projections.property("id.idMunicipio").as(
				"idMunicipio"));
		projectionDist.add(Projections.property("id.seccion").as("seccion"));
		projectionDist
				.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionDist.add(Projections.property("id.tipoCasilla").as(
				"tipoCasilla"));
		projectionDist.add(Projections.property("id.extContigua").as(
				"extContigua"));
		criteria.setProjection(Projections.distinct(projectionDist));

		// se hace un proup by por los datos del distinct
		projectionGroup.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionGroup.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionGroup.add(Projections.groupProperty("id.idEstado"));
		projectionGroup.add(Projections.groupProperty("id.idMunicipio"));
		projectionGroup.add(Projections.groupProperty("id.seccion"));
		projectionGroup.add(Projections.groupProperty("id.idCasilla"));
		projectionGroup.add(Projections.groupProperty("id.tipoCasilla"));
		projectionGroup.add(Projections.groupProperty("id.extContigua"));
		criteria.setProjection(projectionGroup);

		List<Object[]> resultado1 = (List<Object[]>) criteria.list();

		BigDecimal resultado = new BigDecimal(resultado1.size());
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BigDecimal getCountActasCapturadasByDemarcacion(Object[] arg0)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getCountActasCapturadasByDemarcacion-----se cuenta el total de votos");

		// Se define el tipo de casilla especial
		String tipoCasillaEspecial = Constantes.TIPO_CASILLA_ESPECIAL;
		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		ProjectionList projectionGroup = Projections.projectionList();
		ProjectionList projectionDist = Projections.projectionList();
		criteria.add(Restrictions
				.eq("id.idProcesoElectoral", (Integer) arg0[0]));
		criteria.add(Restrictions.eq("id.idDetalleProceso", (Integer) arg0[1]));
		criteria.add(Restrictions.eq("id.idEstado", (Integer) arg0[2]));
		criteria.add(Restrictions.eq("id.idMunicipio", (Integer) arg0[3]));
		// criteria.add(Restrictions.eq("id.idRegiduria", (Integer) arg0[4]));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", (Integer) arg0[5]));
		criteria.add(Restrictions.eq("id.tipoCasilla", tipoCasillaEspecial));
		criteria.add(Restrictions.eq("capturada", new Integer(1)));

		// Se hace un distinct
		projectionDist.add(Projections.property("id.idProcesoElectoral").as(
				"idProcesoElectoral"));
		projectionDist.add(Projections.property("id.idDetalleProceso").as(
				"idDetalleProceso"));
		projectionDist.add(Projections.property("id.idEstado").as("idEstado"));
		projectionDist.add(Projections.property("id.idMunicipio").as(
				"idMunicipio"));
		// projectionDist.add(Projections.property("id.idRegiduria").as("idRegiduria"));
		projectionDist.add(Projections.property("id.seccion").as("seccion"));
		projectionDist
				.add(Projections.property("id.idCasilla").as("idCasilla"));
		projectionDist.add(Projections.property("id.tipoCasilla").as(
				"tipoCasilla"));
		projectionDist.add(Projections.property("id.extContigua").as(
				"extContigua"));
		criteria.setProjection(Projections.distinct(projectionDist));

		// se hace un proup by por los datos del distinct
		projectionGroup.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionGroup.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionGroup.add(Projections.groupProperty("id.idEstado"));
		projectionGroup.add(Projections.groupProperty("id.idMunicipio"));
		// projectionGroup.add(Projections.groupProperty("id.idRegiduria"));
		projectionGroup.add(Projections.groupProperty("id.seccion"));
		projectionGroup.add(Projections.groupProperty("id.idCasilla"));
		projectionGroup.add(Projections.groupProperty("id.tipoCasilla"));
		projectionGroup.add(Projections.groupProperty("id.extContigua"));
		criteria.setProjection(projectionGroup);

		List<Object[]> resultado1 = (List<Object[]>) criteria.list();

		BigDecimal resultado = new BigDecimal(resultado1.size());
		return resultado;
	}

	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso,
			Integer idDetalleProceso, Integer idEstado,
			Integer idTipoCandidatura) throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getTotalMunicipiosActasCapturadas");

		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral", idProceso));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		criteria.setProjection(Projections.distinct(Projections
				.countDistinct("id.idMunicipio")));

		Integer total = ((Long) criteria.uniqueResult()).intValue();

		return total;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso,
			Integer idDetalleProceso, Integer idEstado,
			Integer idTipoCandidatura) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		ProjectionList projectionList = Projections.projectionList();
		criteria.add(Restrictions.eq("id.idProcesoElectoral", idProceso));
		criteria.add(Restrictions.eq("id.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("id.idEstado", idEstado));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", idTipoCandidatura));
		projectionList.add(Projections.property("id.idMunicipio").as(
				"idMunicipio"));
		projectionList.add(Projections.property("id.idDistrito").as(
				"idDistrito"));
		criteria.setProjection(Projections.distinct(projectionList));
		List<Object[]> resultado = (List<Object[]>) criteria.list();
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnSecciones(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnSeccionesParaDistribucion(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(1)));
		//clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnDistritosSecciones(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idDistrito"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Integer getActasParcialesCapturadasEnDistrito(
			DTODistribucionCandParcialPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idDistrito"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();
		Integer cantidad = 0;
		if (resultado != null && resultado.size() > 0) {
			cantidad = resultado.size();
		}

		return cantidad;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasCapturadasEnDistrito(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idDistrito"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegidurias(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idRegiduria"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasParaDistribucion(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(1)));
		//clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idRegiduria"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasSecciones(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		ProjectionList projectionList = Projections.projectionList();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));

		// sentencia: capturada = 0 and id_estatus = 5
		Conjunction clausulaAND = Restrictions.conjunction();
		clausulaAND.add(Restrictions.eq("capturada", new Integer(0)));
		clausulaAND.add(Restrictions.eq("idEstatus", new Integer(5)));

		// sentencia: capturada = 1 or ( capturada = 0 and id_estatus = 5 )
		Disjunction clausulaOR = Restrictions.disjunction();
		clausulaOR.add(Restrictions.eq("capturada", new Integer(1)));
		clausulaOR.add(clausulaAND);

		// sentencia: and ( capturada = 1 or ( capturada = 0 and id_estatus = 5
		// ) )
		criteria.add(clausulaOR);

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idRegiduria").as("idRegiduria"))
				.add(Projections.property("id.seccion").as("seccion"))
				.add(Projections.property("id.idComunidad").as("idComunidad"))
				.add(Projections.property("id.idCasilla").as("idCasilla"))
				.add(Projections.property("id.tipoCasilla").as("tipoCasilla"))
				.add(Projections.property("id.extContigua").as("extContigua"))
				.add(Projections.property("id.idTipoCandidatura").as(
						"idTipoCandidatura"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("capturada")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idRegiduria"));
		projectionList.add(Projections.groupProperty("id.seccion"));
		projectionList.add(Projections.groupProperty("id.idComunidad"));
		projectionList.add(Projections.groupProperty("id.idCasilla"));
		projectionList.add(Projections.groupProperty("id.tipoCasilla"));
		projectionList.add(Projections.groupProperty("id.extContigua"));
		projectionList.add(Projections.groupProperty("id.idTipoCandidatura"));
		projectionList.add(Projections.groupProperty("idEstatus"));
		projectionList.add(Projections.groupProperty("capturada"));
		criteria.setProjection(projectionList);

		criteria.addOrder(Order.asc("id.idRegiduria"));
		criteria.addOrder(Order.asc("id.seccion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> actas = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdProcesoElectoral((Integer) acta[0]);
			key.setIdDetalleProceso((Integer) acta[1]);
			key.setIdEstado((Integer) acta[2]);
			key.setIdDistrito((Integer) acta[3]);
			key.setIdMunicipio((Integer) acta[4]);
			key.setIdRegiduria((Integer) acta[5]);
			key.setSeccion((Integer) acta[6]);
			key.setIdComunidad((Integer) acta[7]);
			key.setIdCasilla((Integer) acta[8]);
			key.setTipoCasilla((String) acta[9]);
			key.setExtContigua((Integer) acta[10]);
			key.setIdTipoCandidatura((Integer) acta[11]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[12]);
			a.setCapturada((Integer) acta[13]);

			actas.add(a);
		}

		return actas;
	}

	@Override
	public BigDecimal getTotalVotosNulosCNR(DTOActaCasillaVotosPK id)
			throws Exception {

		log.info("DAOActaCasillaVotosImpl.getTotalVotosNulosCNR");
		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		switch (id.getIdTipoCandidatura()) {
		// Si es Gobernador
		case 6:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			break;
		// Si es un Diputado MR
		case 7:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			break;
		// Si es un Diputado RP, es sobre casillas especiales
		case 8:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
			break;
		// Si es Ayuntamiento
		case 9:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			break;
		// si es Regiduria MR
		case 15:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			break;
		// Si es Regiduria RP
		case 16:
			criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
			criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
			criteria.add(Restrictions.eq("id.idTipoCandidatura",
					id.getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("id.tipoAsociacion",
					id.getTipoAsociacion()));
			criteria.add(Restrictions.eq("id.idAsociacion",
					id.getIdAsociacion()));
			criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
			break;
		}

		criteria.setProjection(Projections.sum("votos"));
		if (criteria.uniqueResult() == null) {
			return new BigDecimal(0);
		}
		BigDecimal resultado = BigDecimal.valueOf((long) criteria
				.uniqueResult());
		return resultado;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> getvotosAsociacionesActa(
			DTOActaCasillaVotosPK id) throws Exception {

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura",
				id.getIdTipoCandidatura()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.idComunidad", id.getIdComunidad()));
		criteria.add(Restrictions.eq("id.seccion", id.getSeccion()));
		criteria.add(Restrictions.eq("id.idCasilla", id.getIdCasilla()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.add(Restrictions.eq("id.extContigua", id.getExtContigua()));

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idAsociacion").as("idAsociacion"))
				.add(Projections.property("id.tipoAsociacion").as(
						"tipoAsociacion"))
				.add(Projections.property("id.idCoalicion").as("idCoalicion"))
				.add(Projections.property("idEstatus"))
				.add(Projections.property("votos")));

		criteria.addOrder(Order.asc("id.tipoAsociacion"));
		criteria.addOrder(Order.asc("id.idAsociacion"));

		List<Object[]> resultado = (List<Object[]>) criteria.list();

		List<DTOActaCasillaVotos> votosAsociacionesActa = new ArrayList<DTOActaCasillaVotos>();
		for (Object[] acta : resultado) {

			DTOActaCasillaVotosPK key = new DTOActaCasillaVotosPK();
			key.setIdAsociacion((Integer) acta[0]);
			key.setTipoAsociacion((Integer) acta[1]);
			key.setIdCoalicion((Integer) acta[2]);

			DTOActaCasillaVotos a = new DTOActaCasillaVotos();
			a.setId(key);
			a.setIdEstatus((Integer) acta[3]);
			a.setVotos((Integer) acta[4]);

			votosAsociacionesActa.add(a);
		}

		return votosAsociacionesActa;
	}

	public List<DTOActaCasillaVotos> getVotosActaDiputadosMR(Object[] arg0)
			throws Exception {
		log.info("DAOActaCasillaVotosImpl.getListaVotosDiputadosMR");

		Criteria criteria;
		ProjectionList projectionList = Projections.projectionList();
		criteria = getSession().createCriteria(DTOActaCasillaVotos.class);
		criteria.add(Restrictions
				.eq("id.idProcesoElectoral", (Integer) arg0[0]));
		criteria.add(Restrictions.eq("id.idDetalleProceso", (Integer) arg0[1]));
		criteria.add(Restrictions.eq("id.idEstado", (Integer) arg0[2]));
		criteria.add(Restrictions.eq("id.idMunicipio", (Integer) arg0[3]));
		criteria.add(Restrictions.eq("id.idDistrito", (Integer) arg0[4]));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", (Integer) arg0[5]));

		criteria.setProjection(Projections
				.projectionList()
				.add(Projections.property("id.idProcesoElectoral").as(
						"idProcesoElectoral"))
				.add(Projections.property("id.idDetalleProceso").as(
						"idDetalleProceso"))
				.add(Projections.property("id.idEstado").as("idEstado"))
				.add(Projections.property("id.idMunicipio").as("idMunicipio"))
				.add(Projections.property("id.idDistrito").as("idDistrito"))
				.add(Projections.property("id.idAsociacion").as("idAsociacion"))
				.add(Projections.property("id.tipoAsociacion").as(
						"tipoAsociacion")).add(Projections.property("votos")));

		projectionList.add(Projections.groupProperty("id.idProcesoElectoral"));
		projectionList.add(Projections.groupProperty("id.idDetalleProceso"));
		projectionList.add(Projections.groupProperty("id.idEstado"));
		projectionList.add(Projections.groupProperty("id.idMunicipio"));
		projectionList.add(Projections.groupProperty("id.idDistrito"));
		projectionList.add(Projections.groupProperty("id.idAsociacion"));
		projectionList.add(Projections.groupProperty("id.tipoAsociacion"));

		criteria.setProjection(projectionList);

		List<DTOActaCasillaVotos> resultado = (List<DTOActaCasillaVotos>) criteria
				.list();
		return resultado;
	}

	public List<HLPActaCasillaVotos> getTotalVotosActaDiputados(Object[] arg0)
			throws Exception {

		Session session = getSession();
		String query = "SELECT ID_PROCESO_ELECTORAL idProcesoElectoral,"
				+ "     ID_DETALLE_PROCESO idDetalleProceso,"
				+ "     ID_ESTADO idEstado," + "     ID_DISTRITO idDistrito,"
				+ "     ID_MUNICIPIO idMunicipio,"
				+ "     ID_TIPO_CANDIDATURA idTipoCandidatura,"
				+ "     ID_ASOCIACION idAsociacion,"
				+ "     TIPO_ASOCIACION tipoAsociacion,"
				+ "     ID_COALICION idCoalicion," + "     ORDEN orden,"
				+ "     SUM(VOTOS) votos ";
		query += " FROM ACTA_CASILLA_VOTOS WHERE ";
		query += " ID_PROCESO_ELECTORAL=" + (Integer) arg0[0]
				+ " AND ID_DETALLE_PROCESO=" + (Integer) arg0[1]
				+ " AND ID_ESTADO=" + (Integer) arg0[2] + ""
				+ " AND ID_DISTRITO=" + (Integer) arg0[3] + ""
				+ " AND ID_MUNICIPIO=" + (Integer) arg0[4] + ""
				+ " AND ID_TIPO_CANDIDATURA=" + (Integer) arg0[5]
				+ " AND CAPTURADA = " + 1;

		query += " GROUP BY ID_PROCESO_ELECTORAL, ID_DETALLE_PROCESO, ID_ESTADO, ID_DISTRITO, ID_MUNICIPIO, ID_TIPO_CANDIDATURA, ID_ASOCIACION, TIPO_ASOCIACION, ID_COALICION, ORDEN";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idDistrito", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE)
				.addScalar("idTipoCandidatura", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("idCoalicion", IntegerType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();

	}

	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRPParcial(
			Object[] arg0) throws Exception {
		Session session = getSession();
		String query = "SELECT ID_PROCESO_ELECTORAL idProcesoElectoral,"
				+ "     ID_DETALLE_PROCESO idDetalleProceso,"
				+ "     ID_ESTADO idEstado," + "     ID_MUNICIPIO idMunicipio,"
				+ "     ID_REGIDURIA idRegiduria, "
				+ "     ID_TIPO_CANDIDATURA idTipoCandidatura,"
				+ "     ID_ASOCIACION idAsociacion,"
				+ "     TIPO_ASOCIACION tipoAsociacion,"
				+ "     ID_COALICION idCoalicion," + "     ORDEN orden,"
				+ "     SUM(VOTOS) votos ";
		query += " FROM ACTA_CASILLA_VOTOS WHERE ";
		query += " ID_PROCESO_ELECTORAL=" + (Integer) arg0[0]
				+ " AND ID_DETALLE_PROCESO=" + (Integer) arg0[1]
				+ " AND ID_ESTADO=" + (Integer) arg0[2] + ""
				+ " AND ID_MUNICIPIO=" + (Integer) arg0[3] + ""
				+ " AND ID_REGIDURIA=" + (Integer) arg0[4] + ""
				+ " AND ID_TIPO_CANDIDATURA=" + (Integer) arg0[5];

		query += " GROUP BY ID_PROCESO_ELECTORAL, ID_DETALLE_PROCESO, ID_ESTADO,  ID_MUNICIPIO, ID_REGIDURIA, ID_TIPO_CANDIDATURA, ID_ASOCIACION, TIPO_ASOCIACION, ID_COALICION, ORDEN";
		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE)
				.addScalar("idRegiduria", IntegerType.INSTANCE)
				.addScalar("idTipoCandidatura", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("idCoalicion", IntegerType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();

	}

	public List<HLPActaCasillaVotos> getTotalVotosDiputadosMR(Object[] arg0)
			throws Exception {

		Integer candidaturaMR = Constantes.ID_TIPO_CAND_DIPUTADO_MR;

		Session session = getSession();
		String query = "SELECT ID_PROCESO_ELECTORAL idProcesoElectoral,"
				+ "     ID_DETALLE_PROCESO idDetalleProceso,"
				+ "     ID_ESTADO idEstado," + "     ID_DISTRITO idDistrito,"
				+ "     ID_ASOCIACION idAsociacion,"
				+ "     TIPO_ASOCIACION tipoAsociacion,"
				+ "     EMBLEMA emblema," + "     ORDEN orden,"
				+ "     VOTOS votos ";
		query += " FROM DISTRIBUCION_PARTIDOS_CI WHERE ";
		query += "  ID_PROCESO_ELECTORAL=" + (Integer) arg0[0]
				+ " AND ID_DETALLE_PROCESO=" + (Integer) arg0[1]
				+ " AND ID_ESTADO=" + (Integer) arg0[2] + ""
				+ " AND ID_DISTRITO=" + (Integer) arg0[4] + ""
				+ " AND ID_TIPO_CANDIDATURA=" + candidaturaMR + ""
				+ " AND TIPO_ASOCIACION <> "
				+ Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE + " ";

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
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();

	}

	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaMR(Object[] arg0)
			throws Exception {

		Integer candidaturaMR = Constantes.ID_TIPO_CAND_REGIDURIA_MR;

		Session session = getSession();
		String query = "SELECT ID_PROCESO_ELECTORAL idProcesoElectoral,"
				+ "     ID_DETALLE_PROCESO idDetalleProceso,"
				+ "     ID_ESTADO idEstado," + "     ID_MUNICIPIO idMunicipio,"
				+ "     ID_REGIDURIA idRegiduria, "
				+ "     ID_ASOCIACION idAsociacion,"
				+ "     TIPO_ASOCIACION tipoAsociacion,"
				+ "     EMBLEMA emblema," + "     ORDEN orden,"
				+ "     VOTOS votos ";
		query += " FROM DISTRIBUCION_PARTIDOS_CI WHERE ";
		query += " ID_PROCESO_ELECTORAL=" + (Integer) arg0[0]
				+ " AND ID_DETALLE_PROCESO=" + (Integer) arg0[1]
				+ " AND ID_ESTADO=" + (Integer) arg0[2] + ""
				+ " AND ID_MUNICIPIO=" + (Integer) arg0[3] + ""
				+ " AND ID_REGIDURIA=" + (Integer) arg0[4] + ""
				+ " AND ID_TIPO_CANDIDATURA=" + candidaturaMR + ""
				+ " AND TIPO_ASOCIACION <> "
				+ Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE + " ";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE)
				.addScalar("idRegiduria", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();

	}

	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRP(Object[] arg0)
			throws Exception {

		Integer[] candidaturas = { Constantes.ID_TIPO_CAND_REGIDURIA_MR,
				Constantes.ID_TIPO_CAND_REGIDURIA_RP };

		Session session = getSession();

		String query = "SELECT idProcesoElectoral, "
				+ "     idDetalleProceso, "
				+ "     idEstado, "
				+ "     idMunicipio, "
				+ "     idRegiduria, "
				+ "     idAsociacion, "
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
				+ "     DP.ID_MUNICIPIO idMunicipio, "
				+ "     DP.ID_REGIDURIA idRegiduria, "
				+ "     DP.ID_ASOCIACION idAsociacion, "
				+ "     DP.TIPO_ASOCIACION tipoAsociacion,"
				+ "     DP.ORDEN orden, "
				+ "     DP.EMBLEMA emblema, "
				+ "     SUM(DP.VOTOS) AS VOTOS_MR, "
				+ "     (SELECT SUM(A.VOTOS) "
				+ "     FROM ACTA_CASILLA_VOTOS A"
				+ "     WHERE A.ID_PROCESO_ELECTORAL = DP.ID_PROCESO_ELECTORAL "
				+ "     AND A.ID_DETALLE_PROCESO     = DP.ID_DETALLE_PROCESO "
				+ "     AND A.ID_ESTADO              = DP.ID_ESTADO "
				+ "     AND A.ID_MUNICIPIO           = DP.ID_MUNICIPIO "
				+ "     AND A.ID_REGIDURIA           = DP.ID_REGIDURIA "
				+ "     AND A.ID_TIPO_CANDIDATURA    = "
				+ candidaturas[1]
				+ ""
				+ "     AND A.ID_ASOCIACION          = DP.ID_ASOCIACION "
				// + "     AND A.TIPO_ASOCIACION        = DP.TIPO_ASOCIACION "
				+ "     ) AS VOTOS_RP   "
				+ "      FROM DISTRIBUCION_PARTIDOS_CI DP   "
				+ "     WHERE DP.ID_PROCESO_ELECTORAL  = "
				+ (Integer) arg0[0]
				+ ""
				+ "     AND DP.ID_DETALLE_PROCESO      =  "
				+ (Integer) arg0[1]
				+ ""
				+ "     AND DP.ID_ESTADO               =  "
				+ (Integer) arg0[2]
				+ ""
				+ "     AND DP.ID_MUNICIPIO            =  "
				+ (Integer) arg0[3]
				+ ""
				+ "     AND DP.ID_REGIDURIA            =  "
				+ (Integer) arg0[4]
				+ ""
				+ "     AND DP.ID_TIPO_CANDIDATURA     =  "
				+ candidaturas[0]
				+ ""
				+ "     AND DP.TIPO_ASOCIACION 		 <>  "
				+ Constantes.TIPO_ASOCIACION_CAND_INDEPENDIENTE
				+ ""
				+ "     GROUP BY DP.ID_PROCESO_ELECTORAL, DP.ID_DETALLE_PROCESO, DP.ID_ESTADO, DP.ID_MUNICIPIO, DP.ID_REGIDURIA, DP.ID_ASOCIACION,"
				+ "     DP.TIPO_ASOCIACION, DP.ORDEN, DP.EMBLEMA "
				+ "     )  "
				+ "     GROUP BY VOTOS_MR, VOTOS_RP, idProcesoElectoral, idDetalleProceso, idEstado,  "
				+ "     idMunicipio, idRegiduria, idAsociacion, tipoAsociacion, orden, emblema  ";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE)
				.addScalar("idRegiduria", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("emblema", StringType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();

	}

	@SuppressWarnings("unchecked")
	public List<HLPActaCasillaVotos> getTotalVotosActaGobernador(Object[] arg0)
			throws Exception {

		Session session = getSession();
		String query = "SELECT ID_PROCESO_ELECTORAL idProcesoElectoral,"
				+ "     ID_DETALLE_PROCESO idDetalleProceso,"
				+ "     ID_ESTADO idEstado," + "     ID_MUNICIPIO idMunicipio,"
				+ "     ID_TIPO_CANDIDATURA idTipoCandidatura,"
				+ "     ID_ASOCIACION idAsociacion,"
				+ "     TIPO_ASOCIACION tipoAsociacion,"
				+ "     ID_COALICION idCoalicion," + "     ORDEN orden,"
				+ "     SUM(VOTOS) votos ";
		query += " FROM ACTA_CASILLA_VOTOS WHERE ";
		query += " ID_PROCESO_ELECTORAL=" + (Integer) arg0[0]
				+ " AND ID_DETALLE_PROCESO=" + (Integer) arg0[1]
				+ " AND ID_ESTADO=" + (Integer) arg0[2] + ""
				+ " AND ID_MUNICIPIO=" + (Integer) arg0[3] + ""
				+ " AND ID_TIPO_CANDIDATURA=" + (Integer) arg0[4] + " "
				+ " AND CAPTURADA = " + 1;

		query += " GROUP BY ID_PROCESO_ELECTORAL, ID_DETALLE_PROCESO, ID_ESTADO, ID_MUNICIPIO, ID_TIPO_CANDIDATURA, ID_ASOCIACION, TIPO_ASOCIACION, ID_COALICION, ORDEN";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idProcesoElectoral", IntegerType.INSTANCE)
				.addScalar("idDetalleProceso", IntegerType.INSTANCE)
				.addScalar("idEstado", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE)
				.addScalar("idTipoCandidatura", IntegerType.INSTANCE)
				.addScalar("idAsociacion", IntegerType.INSTANCE)
				.addScalar("tipoAsociacion", IntegerType.INSTANCE)
				.addScalar("idCoalicion", IntegerType.INSTANCE)
				.addScalar("orden", IntegerType.INSTANCE)
				.addScalar("votos", IntegerType.INSTANCE);

		s.setResultTransformer(Transformers
				.aliasToBean(HLPActaCasillaVotos.class));

		return s.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOActaCasillaVotos> consultaActa(DTOActaCasillaVotosPK id)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOActaCasillaVotosImpl.getListActaCasillaVotos");

		Session session = getSession();
		Criteria criteria = session.createCriteria(DTOActaCasillaVotos.class);

		criteria.add(Restrictions.eq("id.idProcesoElectoral",
				id.getIdProcesoElectoral()));
		criteria.add(Restrictions.eq("id.idDetalleProceso",
				id.getIdDetalleProceso()));
		criteria.add(Restrictions.eq("id.idEstado", id.getIdEstado()));
		criteria.add(Restrictions.eq("id.idDistrito", id.getIdDistrito()));
		criteria.add(Restrictions.eq("id.idMunicipio", id.getIdMunicipio()));
		criteria.add(Restrictions.eq("id.idRegiduria", id.getIdRegiduria()));
		criteria.add(Restrictions.eq("id.seccion", id.getSeccion()));
		criteria.add(Restrictions.eq("id.idComunidad", id.getIdComunidad()));
		criteria.add(Restrictions.eq("id.idCasilla", id.getIdCasilla()));
		criteria.add(Restrictions.eq("id.tipoCasilla", id.getTipoCasilla()));
		criteria.add(Restrictions.eq("id.extContigua", id.getExtContigua()));
		criteria.add(Restrictions.eq("id.idTipoCandidatura", id.getIdTipoCandidatura()));

		List<DTOActaCasillaVotos> resultado = (List<DTOActaCasillaVotos>) criteria.list();
		return resultado;
	}

	@Override
	public boolean esRecuentoParcial(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion)
			throws Exception {
		String q = null;
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
			if(usuario.getIdMunicipioSeleccionado() != null
					&& usuario.getIdMunicipioSeleccionado() > 0){
				q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
					}
			q += " AND ID_ESTATUS = 2";
		}
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
			q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()
					+ " ";
			q += " AND ID_ESTATUS = 2";
		}
		
		// Acta parcial para Diputados
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
				|| idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
			if(usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado() > 0){
			q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
				}
			q += " AND ID_DISTRITO = " + distrito + " ";
			q += " AND ID_ESTATUS = 2";
		}
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
				|| idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
				q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
			q += " AND ID_REGIDURIA = " + demarcacion + " ";
			q += " AND ID_ESTATUS = 2";
		}

		SQLQuery query = getSession().createSQLQuery(q);
		query.addScalar("total", IntegerType.INSTANCE);
		Integer cuenta = (Integer) query.uniqueResult();

		return cuenta > 0;
	}

	@Override
	public boolean esRecuentoTotal(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion)
			throws Exception {
		String q = null;
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_GOBERNADOR)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
			if(usuario.getIdMunicipioSeleccionado() != null
					&& usuario.getIdMunicipioSeleccionado() > 0){
				q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
					}
			q += " AND ID_ESTATUS = 1";
		}
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_AYUNTAMIENTO)) {
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA = "
					+ idTipoCandidatura;
			q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()
					+ " ";
			q += " AND ID_ESTATUS = 1";
		}
		
		// Acta parcial para Diputados MR
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR)
				|| idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)) {
			String ids = "";
			if( idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_MR ) )
				ids = ""+Constantes.ID_TIPO_CAND_DIPUTADO_MR;
			else
				ids = Constantes.ID_TIPO_CAND_DIPUTADO_MR + ", " + Constantes.ID_TIPO_CAND_DIPUTADO_RP;
			
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA IN (" + ids + ")";
			if(usuario.getIdMunicipioSeleccionado() != null
				&& usuario.getIdMunicipioSeleccionado() > 0){
				q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
			}
			q += " AND ID_DISTRITO = " + distrito + " ";
			q += " AND ID_ESTATUS = 1";
		}
		
		if (idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
				|| idTipoCandidatura
						.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)) {
			String ids = "";
			if( idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR ) )
				ids = ""+Constantes.ID_TIPO_CAND_REGIDURIA_MR;
			else
				ids = Constantes.ID_TIPO_CAND_REGIDURIA_MR + ", " + Constantes.ID_TIPO_CAND_REGIDURIA_RP;
			
			q = "SELECT COUNT(*) AS total FROM ACTA_CASILLA_VOTOS WHERE ID_TIPO_CANDIDATURA IN (" + ids + ")";
			q += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()+"";
			q += " AND ID_REGIDURIA = " + demarcacion + " ";
			q += " AND ID_ESTATUS = 1";
		}

		SQLQuery query = getSession().createSQLQuery(q);
		query.addScalar("total", IntegerType.INSTANCE);
		Integer cuenta = (Integer) query.uniqueResult();
		boolean esRecuento = !(cuenta > 0); 
		return esRecuento;
	}
}
