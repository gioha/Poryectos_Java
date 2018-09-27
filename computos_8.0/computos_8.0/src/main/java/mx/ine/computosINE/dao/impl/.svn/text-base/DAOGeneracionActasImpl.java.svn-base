package mx.ine.computosINE.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.computosINE.dao.DAOGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTOCConsejo;
import mx.ine.computosINE.dto.DTOConsejero;
import mx.ine.computosINE.dto.DTORepresentante;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.util.Constantes;
import mx.org.ine.servicios.dto.DTOBase;

@Repository("daoGeneracionActas")
@Scope("prototype")
public class DAOGeneracionActasImpl extends
		DAOGeneric<DTOActaTipoCandidatura, DTOActaTipoCandidaturaPK> implements
		DAOGeneracionActasInterface {
	/*
	 * LOGGER
	 */
	private static final Log log = LogFactory
			.getLog(DAOGeneracionActasImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardaActualizaActa(DTOBase acta) throws Exception {
		try {
			guardarOactualizar(acta);
		} catch (HibernateException ex) {
			log.error(ex +" al guardar en el acta");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminaActa(DTOActaTipoCandidatura acta) {
		try {
			eliminar(acta);
		} catch (HibernateException ex) {
			log.error(ex);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminaConsejeros(List<DTOConsejero> consejeros) {
		try {
			for (DTOConsejero actaConsejero : consejeros) {
				eliminar(actaConsejero);
			}
		} catch (HibernateException ex) {
			log.error(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminaRepresentantes(List<DTORepresentante> representantes) {
		try {
			for (DTORepresentante actaRepresentante : representantes) {
				eliminar(actaRepresentante);
			}
		} catch (HibernateException ex) {
			log.error(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardaRepresentante(DTORepresentante representante)
			throws Exception {
		try {
			guardarOactualizar(representante);
		} catch (HibernateException ex) {
			log.error(ex +"al guardar representantes");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardaConsejero(DTOConsejero consejero) throws Exception {
		try {
			guardarOactualizar(consejero);
		} catch (HibernateException ex) {
			log.error(ex +"al guardar en consejeros");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOCConsejo> getCatalogoConsejeros() {
		Criteria criteria;
		criteria = getSession().createCriteria(DTOCConsejo.class);
		return (List<DTOCConsejo>) criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DTOBase getActa(Object pkActa) {

		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			Criteria criteria;
			criteria = getSession()
					.createCriteria(DTOActaTipoCandidatura.class);
			criteria.add(Restrictions
					.eq("pk.idProcesoElectoral",
							((DTOActaTipoCandidaturaPK) pkActa)
									.getIdProcesoElectoral()));
			criteria.add(Restrictions.eq("pk.idDetalleProceso",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDetalleProceso()));
			criteria.add(Restrictions.eq("pk.idEstado",
					((DTOActaTipoCandidaturaPK) pkActa).getIdEstado()));
			criteria.add(Restrictions.eq("pk.idDistrito",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDistrito()));
			criteria.add(Restrictions.eq("pk.idMunicipio",
					((DTOActaTipoCandidaturaPK) pkActa).getIdMunicipio()));
			criteria.add(Restrictions.eq("pk.idComunidad",
					((DTOActaTipoCandidaturaPK) pkActa).getIdComunidad()));
			criteria.add(Restrictions.eq("pk.idRegiduria",
					((DTOActaTipoCandidaturaPK) pkActa).getIdRegiduria()));
			criteria.add(Restrictions.eq("pk.idTipoCandidatura",
					((DTOActaTipoCandidaturaPK) pkActa).getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("pk.tipoActa",
					((DTOActaTipoCandidaturaPK) pkActa).getTipoActa()));
			return (DTOActaTipoCandidatura) criteria.uniqueResult();
		}

		return null;

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOConsejero> getConsejeros(Object pkActa) {

		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			Criteria criteria;
			criteria = getSession().createCriteria(DTOConsejero.class);
			criteria.add(Restrictions
					.eq("pk.idProcesoElectoral",
							((DTOActaTipoCandidaturaPK) pkActa)
									.getIdProcesoElectoral()));
			criteria.add(Restrictions.eq("pk.idDetalleProceso",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDetalleProceso()));
			criteria.add(Restrictions.eq("pk.idEstado",
					((DTOActaTipoCandidaturaPK) pkActa).getIdEstado()));
			criteria.add(Restrictions.eq("pk.idDistrito",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDistrito()));
			criteria.add(Restrictions.eq("pk.idMunicipio",
					((DTOActaTipoCandidaturaPK) pkActa).getIdMunicipio()));
			criteria.add(Restrictions.eq("pk.idComunidad",
					((DTOActaTipoCandidaturaPK) pkActa).getIdComunidad()));
			criteria.add(Restrictions.eq("pk.idRegiduria",
					((DTOActaTipoCandidaturaPK) pkActa).getIdRegiduria()));
			criteria.add(Restrictions.eq("pk.idTipoCandidatura",
					((DTOActaTipoCandidaturaPK) pkActa).getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("pk.tipoActa",
					((DTOActaTipoCandidaturaPK) pkActa).getTipoActa()));
			return (List<DTOConsejero>) criteria.list();
		}

		return null;

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTORepresentante> getRepresentantes(Object pkActa) {

		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			Criteria criteria;
			criteria = getSession().createCriteria(DTORepresentante.class);
			criteria.add(Restrictions
					.eq("pk.idProcesoElectoral",
							((DTOActaTipoCandidaturaPK) pkActa)
									.getIdProcesoElectoral()));
			criteria.add(Restrictions.eq("pk.idDetalleProceso",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDetalleProceso()));
			criteria.add(Restrictions.eq("pk.idEstado",
					((DTOActaTipoCandidaturaPK) pkActa).getIdEstado()));
			criteria.add(Restrictions.eq("pk.idDistrito",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDistrito()));
			criteria.add(Restrictions.eq("pk.idMunicipio",
					((DTOActaTipoCandidaturaPK) pkActa).getIdMunicipio()));
			criteria.add(Restrictions.eq("pk.idComunidad",
					((DTOActaTipoCandidaturaPK) pkActa).getIdComunidad()));
			criteria.add(Restrictions.eq("pk.idRegiduria",
					((DTOActaTipoCandidaturaPK) pkActa).getIdRegiduria()));
			criteria.add(Restrictions.eq("pk.idTipoCandidatura",
					((DTOActaTipoCandidaturaPK) pkActa).getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("pk.tipoActa",
					((DTOActaTipoCandidaturaPK) pkActa).getTipoActa()));
			return (List<DTORepresentante>) criteria.list();
		}

		return null;

	}

	@Override
	public void setEditable(DTOActaTipoCandidaturaPK pkActa) throws Exception {
		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			Criteria criteria;
			criteria = getSession()
					.createCriteria(DTOActaTipoCandidatura.class);
			criteria.add(Restrictions
					.eq("pk.idProcesoElectoral",
							((DTOActaTipoCandidaturaPK) pkActa)
									.getIdProcesoElectoral()));
			criteria.add(Restrictions.eq("pk.idDetalleProceso",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDetalleProceso()));
			criteria.add(Restrictions.eq("pk.idEstado",
					((DTOActaTipoCandidaturaPK) pkActa).getIdEstado()));
			criteria.add(Restrictions.eq("pk.idDistrito",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDistrito()));
			criteria.add(Restrictions.eq("pk.idMunicipio",
					((DTOActaTipoCandidaturaPK) pkActa).getIdMunicipio()));
			criteria.add(Restrictions.eq("pk.idComunidad",
					((DTOActaTipoCandidaturaPK) pkActa).getIdComunidad()));
			criteria.add(Restrictions.eq("pk.idRegiduria",
					((DTOActaTipoCandidaturaPK) pkActa).getIdRegiduria()));
			criteria.add(Restrictions.eq("pk.idTipoCandidatura",
					((DTOActaTipoCandidaturaPK) pkActa).getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("pk.tipoActa",
					((DTOActaTipoCandidaturaPK) pkActa).getTipoActa()));

			DTOBase acta = null;
			acta = (DTOActaTipoCandidatura) criteria.uniqueResult();

			if (((DTOActaTipoCandidatura) acta).getEditable().equals(0)) {
				((DTOActaTipoCandidatura) acta).setEditable(1);
			} else {
				((DTOActaTipoCandidatura) acta).setEditable(1);
			}
			guardaActualizaActa(acta);
		}

	}

	@Override
	public void eliminandoActa(DTOActaTipoCandidaturaPK pkActa) {
		if (pkActa instanceof DTOActaTipoCandidaturaPK) {
			Criteria criteria;
			criteria = getSession()
					.createCriteria(DTOActaTipoCandidatura.class);
			criteria.add(Restrictions
					.eq("pk.idProcesoElectoral",
							((DTOActaTipoCandidaturaPK) pkActa)
									.getIdProcesoElectoral()));
			criteria.add(Restrictions.eq("pk.idDetalleProceso",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDetalleProceso()));
			criteria.add(Restrictions.eq("pk.idEstado",
					((DTOActaTipoCandidaturaPK) pkActa).getIdEstado()));
			criteria.add(Restrictions.eq("pk.idDistrito",
					((DTOActaTipoCandidaturaPK) pkActa).getIdDistrito()));
			criteria.add(Restrictions.eq("pk.idMunicipio",
					((DTOActaTipoCandidaturaPK) pkActa).getIdMunicipio()));
			criteria.add(Restrictions.eq("pk.idComunidad",
					((DTOActaTipoCandidaturaPK) pkActa).getIdComunidad()));
			criteria.add(Restrictions.eq("pk.idRegiduria",
					((DTOActaTipoCandidaturaPK) pkActa).getIdRegiduria()));
			criteria.add(Restrictions.eq("pk.idTipoCandidatura",
					((DTOActaTipoCandidaturaPK) pkActa).getIdTipoCandidatura()));
			criteria.add(Restrictions.eq("pk.tipoActa",
					((DTOActaTipoCandidaturaPK) pkActa).getTipoActa()));

			DTOBase acta = null;
			acta = (DTOActaTipoCandidatura) criteria.uniqueResult();
			eliminaActa((DTOActaTipoCandidatura) acta);
		}

	}

	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso,
			Integer idDetalleProceso, Integer idEstado,
			Integer idTipoCandidatura) throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOGeneracionActasImpl.getTotalMunicipiosActasCapturadas");

		Criteria criteria;
		criteria = getSession().createCriteria(DTOActaTipoCandidatura.class);
		criteria.add(Restrictions.eq("pk.idProcesoElectoral", idProceso));
		criteria.add(Restrictions.eq("pk.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("pk.idEstado", idEstado));
		criteria.add(Restrictions.eq("pk.idTipoCandidatura", idTipoCandidatura));
		criteria.add(Restrictions.eq("pk.tipoActa",
				Constantes.TIPO_ACTA_PARCIAL));
		criteria.setProjection(Projections.distinct(Projections
				.countDistinct("pk.idMunicipio")));

		Integer total = ((Long) criteria.uniqueResult()).intValue();

		return total;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso,
			Integer idDetalleProceso, Integer idEstado,
			Integer idTipoCandidatura) throws Exception {
		// TODO Auto-generated method stub
		log.info("DAOGeneracionActasImpl.consultaActasCaptByMunDistrito");
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(DTOActaTipoCandidatura.class);
		ProjectionList projectionList = Projections.projectionList();
		criteria.add(Restrictions.eq("pk.idProcesoElectoral", idProceso));
		criteria.add(Restrictions.eq("pk.idDetalleProceso", idDetalleProceso));
		criteria.add(Restrictions.eq("pk.idEstado", idEstado));
		criteria.add(Restrictions.eq("pk.idTipoCandidatura", idTipoCandidatura));
		projectionList.add(Projections.property("pk.idMunicipio").as(
				"idMunicipio"));
		projectionList.add(Projections.property("pk.idDistrito").as(
				"idDistrito"));
		criteria.setProjection(Projections.distinct(projectionList));
		List<Object[]> resultado = (List<Object[]>) criteria.list();
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> consultaDistritosConSusMunicipios(
			Integer idTipoCandidatura) throws Exception {

		String query = " SELECT DISTINCT(ID_DISTRITO) idDistrito, ID_MUNICIPIO idMunicipio FROM ACTA_CASILLA_VOTOS ";
		query += "WHERE ID_TIPO_CANDIDATURA = " + idTipoCandidatura + " ";
		query += "ORDER BY idDistrito ";

		SQLQuery s = getSession().createSQLQuery(query)
				.addScalar("idDistrito", IntegerType.INSTANCE)
				.addScalar("idMunicipio", IntegerType.INSTANCE);

		return (List<Object[]>) s.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> consultaIdAsociacionesDipRPEstatal() throws Exception {

		String query = "SELECT DISTINCT (ID_ASOCIACION) idAsociacion FROM ACTA_CASILLA_VOTOS ";
		query += "WHERE ID_TIPO_CANDIDATURA = "
				+ Constantes.ID_TIPO_CAND_DIPUTADO_RP + " ";
		query += "AND TIPO_ASOCIACION = " + Constantes.TIPO_ASOCIACION_PARTIDO
				+ " ";
		query += "ORDER BY ID_ASOCIACION ";

		SQLQuery s = getSession().createSQLQuery(query).addScalar(
				"idAsociacion", IntegerType.INSTANCE);

		return (List<Integer>) s.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> consultaDistritosLocalesPorMunicipio(
			DTOUsuarioLogin usuario) throws Exception {

		String query = "SELECT DISTINCT (ID_DISTRITO) idDistrito FROM ACTA_CASILLA_VOTOS ";
		query += "WHERE ID_TIPO_CANDIDATURA = "
				+ Constantes.ID_TIPO_CAND_DIPUTADO_MR + " ";
		query += "AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()
				+ " ";
		query += "ORDER BY ID_DISTRITO ";

		SQLQuery s = getSession().createSQLQuery(query).addScalar("idDistrito",
				IntegerType.INSTANCE);

		return (List<Integer>) s.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> consultaDemarcacionesPorMR(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception {

		String query = "SELECT DISTINCT (ID_REGIDURIA) idRegiduria FROM ACTA_TIPO_CANDIDATURA ";
		query += "WHERE ID_TIPO_CANDIDATURA = " + idTipoCandidatura + " ";
		query += " AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()
				+ "";
		query += " AND ID_REGIDURIA = " + idSeleccion
				+ "";
		query += " ORDER BY ID_REGIDURIA";

		SQLQuery s = getSession().createSQLQuery(query).addScalar(
				"idRegiduria", IntegerType.INSTANCE);

		return (List<Integer>) s.list();
	}

	@Override
	public List<Integer> consultaDistritosPorMR(Integer idTipoCandidatura,
			DTOUsuarioLogin usuario, Integer idSeleccion) throws Exception {

		String query = "SELECT DISTINCT (ID_DISTRITO) idDistrito FROM ACTA_TIPO_CANDIDATURA ";
		query += "WHERE ID_TIPO_CANDIDATURA = " + idTipoCandidatura + "";
		query += "AND ID_MUNICIPIO = " + usuario.getIdMunicipioSeleccionado()
				+ "";
		query += "AND ID_DISTRITO = " + idSeleccion
				+ "";
		query += " ORDER BY ID_DISTRITO";

		SQLQuery s = getSession().createSQLQuery(query).addScalar("idDistrito",
				IntegerType.INSTANCE);

		return (List<Integer>) s.list();
	}

	@Override
	public boolean tieneEspeciales(Integer idTipoCandidatura,
			Integer idSeleccion) {
		
		boolean tieneEspecial = false;
		
		String query = "SELECT * FROM ACTA_CASILLA_VOTOS ";
		query += "WHERE ID_TIPO_CANDIDATURA = "+idTipoCandidatura +" ";
		if(idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_REGIDURIA_RP)){
			query += "AND ID_REGIDURIA = "+idSeleccion+" ";
		}
		if(idTipoCandidatura.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)){
			query += "AND ID_DISTRITO = "+idSeleccion+" ";
		}
		query += "AND TIPO_CASILLA = '"+Constantes.TIPO_CASILLA_ESPECIAL + "'";
		
		SQLQuery s = getSession().createSQLQuery(query);
		
		if(s.list() != null && !s.list().isEmpty()){
			tieneEspecial = true;
		}
		
		return tieneEspecial;
	}

	@Override
	public boolean isGeneradasDiputadosRPFinal() {
		boolean estanGeneradas = false;
		
		String query = "SELECT DISTINCT ID_DISTRITO ";
		query += "FROM ACTA_TIPO_CANDIDATURA ";
		query += "WHERE ID_TIPO_CANDIDATURA = "+ Constantes.ID_TIPO_CAND_DIPUTADO_RP + " ";
		query += "AND TIPO_ACTA = "+ Constantes.TIPO_ACTA_FINAL  + " ";
		
		SQLQuery s = getSession().createSQLQuery(query);
		
		List<Object> distritos = s.list();
		
		// Se agrega numero total de distritos por solvencia
		if(distritos.size() == 18){
			estanGeneradas = true;
		}
		
		
		return estanGeneradas;
	}

}
