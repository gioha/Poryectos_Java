package mx.ine.computosINE.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.dao.DAOReportesInterface;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.reportes.DTOCandidatosIndependientesWS;
import mx.ine.computosINE.dto.reportes.DTOEncabezadoAsociaciones;
import mx.ine.computosINE.dto.reportes.DTOParametrosEntradaWS;
import mx.ine.computosINE.helper.HLPReportes;
import mx.ine.computosINE.util.Constantes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("daoReportes")
@Scope("prototype")
public class DAOReportesImpl extends DAOGeneric<Serializable, Serializable>
		implements DAOReportesInterface {
	private static final Log logger = LogFactory.getLog(DAOReportesImpl.class);

	private static final Integer TODAS_LAS_ACTAS = 0;
	private static final Integer CAPTURADA = 1;
	private static final String CNR = "CANDIDATOS NO REGISTRADOS";	
	private static final int TIPO_ASOCIACION_PARTIDO = 1;
	private static final int TIPO_ASOCIACION_COALICION = 3;
	private static final int TIPO_ASOCIACION_CAND_IND = 4;
	private static final Integer ID_ESTADO = 18;
	private static final Integer ID_PROCESO_ELEC = 3;
	private boolean esReportePorDemarcacion = Boolean.FALSE;
	private Map<Integer, String> partidos = new LinkedHashMap<>();
	
	@Autowired
	@Qualifier("hlpReportes")
	private HLPReportes hlpReporte;

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteXCasillas(
			DTOFiltroReportes filtro) throws ClienteWebServiceException {

		List<Object[]> resultadoAsociaciones = new ArrayList<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();

		esReportePorDemarcacion = Boolean.FALSE;
		boolean esTipoCandidaturaDiputado = filtro.getIdTipoCandidatura()
				.equals(Constantes.ID_TIPO_CAND_DIPUTADO_RP)
				|| filtro.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_DIPUTADO_MR);
		boolean esTipoCandidaturaRegiduria = filtro.getIdTipoCandidatura()
				.equals(Constantes.ID_TIPO_CAND_REGIDURIA_MR)
				|| filtro.getIdTipoCandidatura().equals(
						Constantes.ID_TIPO_CAND_REGIDURIA_RP);
		boolean sonTodasLasActas = filtro.getIdFiltroEstatusCasilla() != null ? filtro
				.getIdFiltroEstatusCasilla().equals(TODAS_LAS_ACTAS)
				: Boolean.TRUE;

		// Se arma el query para obtener las asociaciones 
		String qryAsociaciones = getContainer()
				.getQuery("query_reportes_inicializaEncabezado_obtenAsociaciones_Casillas");

		// Se agregan filtros al query dependiendo del tipo candidatura y el
		// estatus de la casilla
		if (esTipoCandidaturaDiputado) {
			qryAsociaciones += " AND acv.ID_DISTRITO = :P_ID_DISTRITO";
		} else if (esTipoCandidaturaRegiduria) {
			qryAsociaciones += " AND acv.ID_REGIDURIA = :P_ID_DEMARCACION";
		}
		if (!sonTodasLasActas) {
			qryAsociaciones += " AND acv.ID_ESTATUS = :P_ID_ESTATUS";
		}
		qryAsociaciones += " ORDER BY acv.TIPO_ASOCIACION, cs.ID_COALICION, acv.ORDEN ASC";

		Query query = getSession().createSQLQuery(qryAsociaciones);
		query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
		query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
		query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
		query.setParameter("P_ID_MUNICIPIO",
				filtro.getIdMunicipioElectoralSeleccionado());
		query.setParameter("P_ID_TIPO_CANDIDATURA",
				filtro.getIdTipoCandidatura());
		query.setParameter("P_CAPTURADA", CAPTURADA);

		// Se agrega parámetro de distrito o demarcacion dependiendo del tipo de
		// candidatura
		if (esTipoCandidaturaDiputado) {
			query.setParameter("P_ID_DISTRITO", filtro.getIdDistrito());
		} else if (esTipoCandidaturaRegiduria) {
			query.setParameter("P_ID_DEMARCACION", filtro.getIdDemarcacion());
		}
		// Se agrega parámetro de estatus en caso de que se haya seleccionado
		if (!sonTodasLasActas) {
			query.setParameter("P_ID_ESTATUS",
					filtro.getIdFiltroEstatusCasilla());
		}

		resultadoAsociaciones = query.list();

		// Se obtienen asociaciones de partidos y candidaturas independientes
		if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
			encabezado_asociaciones = inicializaEncabezado(resultadoAsociaciones);
		}

		return encabezado_asociaciones;
	}

	private List<DTOEncabezadoAsociaciones> inicializaEncabezado(
			List<Object[]> resultadoAsociaciones)
			throws ClienteWebServiceException {
		this.partidos = new LinkedHashMap<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();
		Map<Integer, DTOEncabezadoAsociaciones> noRepetidos= new LinkedHashMap<Integer, DTOEncabezadoAsociaciones>(); 
	    Iterator<Integer> it;
		DTOEncabezadoAsociaciones asociacion = null;
		for (Object[] row : resultadoAsociaciones) {
			asociacion = new DTOEncabezadoAsociaciones();
			asociacion.setIdAsociacion(new Integer(row[0].toString()));
			asociacion.setTipoAsociacion(new Integer(row[1].toString()));

			switch (asociacion.getTipoAsociacion()) {
			case TIPO_ASOCIACION_PARTIDO:
				asociacion.setSiglas(row[2] != null ? row[2].toString() : "C_"
						+ asociacion.getIdAsociacion().toString());
				break;
			case TIPO_ASOCIACION_COALICION:
				asociacion.setSiglas(row[3] != null ? row[3].toString() : "C_"
						+ asociacion.getIdAsociacion().toString());
				break;
			case TIPO_ASOCIACION_CAND_IND:
				if(esReportePorDemarcacion){
					asociacion.setSiglas(row[3] != null ? row[3].toString() : "C_"
							+ asociacion.getIdAsociacion().toString());
				}
				else{
					String nombreCI = obtenNombreCandidato(asociacion
							.getIdAsociacion().toString());
					asociacion.setSiglas(nombreCI);
				}				
				break;
			default:
				break;
			}
//			encabezado_asociaciones.add(asociacion);
			partidos.put(asociacion.getIdAsociacion(), asociacion.getSiglas());
			
			noRepetidos.put(asociacion.getIdAsociacion(),asociacion);
			
		}
		
		logger.info("  NO REPETIDOS :" + noRepetidos);

        it= noRepetidos.keySet().iterator();
        
        while(it.hasNext()){
        	Integer key= (Integer) it.next();
        	encabezado_asociaciones.add(noRepetidos.get(key));
        	
        }

		logger.info("  ENCABEZADO ASOCIACION 1 :"+ encabezado_asociaciones.toString());

		// Se agregan al final las columnas de CNR y VotosNulos
		if (!encabezado_asociaciones.isEmpty()) {
			DTOEncabezadoAsociaciones cnr = new DTOEncabezadoAsociaciones();
			cnr.setIdAsociacion(Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			cnr.setTipoAsociacion(Constantes.TIPO_ASOCIACION_CAND_NO_REG);
			cnr.setSiglas(CNR);
			encabezado_asociaciones.add(cnr);
			partidos.put(cnr.getIdAsociacion(), cnr.getSiglas().trim());
			DTOEncabezadoAsociaciones nulos = new DTOEncabezadoAsociaciones();
			nulos.setIdAsociacion(Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			nulos.setTipoAsociacion(Constantes.TIPO_ASOCIACION_VOTOS_NULOS);
			nulos.setSiglas(Constantes.TITULO_VOTOS_NULOS);
			encabezado_asociaciones.add(nulos);
			partidos.put(nulos.getIdAsociacion(), nulos.getSiglas().trim());
		}
		return encabezado_asociaciones;

	}
	
	
	
	private List<DTOEncabezadoAsociaciones> inicializaEncabezadoRPEstatal( 
			List<Object[]> resultadoAsociaciones)
			throws ClienteWebServiceException {
		this.partidos = new LinkedHashMap<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();
		Map<Integer, DTOEncabezadoAsociaciones> noRepetidos= new LinkedHashMap<Integer, DTOEncabezadoAsociaciones>(); 
	    Iterator<Integer> it;
		DTOEncabezadoAsociaciones asociacion = null;
		for (Object[] row : resultadoAsociaciones) {
			asociacion = new DTOEncabezadoAsociaciones();
			asociacion.setIdAsociacion(new Integer(row[0].toString()));
			asociacion.setTipoAsociacion(new Integer(row[1].toString()));

			switch (asociacion.getTipoAsociacion()) {
			case TIPO_ASOCIACION_PARTIDO:
				asociacion.setSiglas(row[2] != null ? row[2].toString() : "C_"
						+ asociacion.getIdAsociacion().toString());
				break;
			case TIPO_ASOCIACION_COALICION:
				asociacion.setSiglas(row[3] != null ? row[3].toString() : "C_"
						+ asociacion.getIdAsociacion().toString());
				break;
			case TIPO_ASOCIACION_CAND_IND:
				if(esReportePorDemarcacion){
					asociacion.setSiglas(row[3] != null ? row[3].toString() : "C_"
							+ asociacion.getIdAsociacion().toString());
				}
				else{
					String nombreCI = obtenNombreCandidato(asociacion
							.getIdAsociacion().toString());
					asociacion.setSiglas(nombreCI);
				}				
				break;
			default:
				break;
			}
//			encabezado_asociaciones.add(asociacion);
			partidos.put(asociacion.getIdAsociacion(), asociacion.getSiglas());
			
			noRepetidos.put(asociacion.getIdAsociacion(),asociacion);
			
		}
		
		logger.info("  NO REPETIDOS :" + noRepetidos);

        it= noRepetidos.keySet().iterator();
        
        while(it.hasNext()){
        	Integer key= (Integer) it.next();
        	encabezado_asociaciones.add(noRepetidos.get(key));
        	
        }

		logger.info("  ENCABEZADO ASOCIACION 1 :"+ encabezado_asociaciones.toString());

		return encabezado_asociaciones;

	}

	/**
	 * Se obtiene el nombre del candidato independiente según su idAsociacion
	 * Se consume el WS de computosINEReportes, ComputosINEReportes/servicios/reportes/consultaCandidatosIndependientes
	 * @param idAsociacion
	 * @return
	 * @throws ClienteWebServiceException
	 */
	private String obtenNombreCandidato(String idAsociacion) throws ClienteWebServiceException {
		String nombreCandidato = null;
		DTOParametrosEntradaWS parametros = new DTOParametrosEntradaWS();
		parametros.setIdAsociacion(idAsociacion);
		parametros.setIdEstado(ID_ESTADO);
		parametros.setIdProcesoElectoral(ID_PROCESO_ELEC);
		DTOCandidatosIndependientesWS candidatosIndependientesWS;
		
		candidatosIndependientesWS = hlpReporte.consultaNombreCandidatos(parametros);
		if (candidatosIndependientesWS != null) {
			if(candidatosIndependientesWS.getNombreCandidato()!=null){
			nombreCandidato = candidatosIndependientesWS.getNombreCandidato();
			}else{
				nombreCandidato= "CI_" +candidatosIndependientesWS.getIdAsociacion();
			}
		}else{
			nombreCandidato = "CI_" + idAsociacion;
		}
		
		return nombreCandidato;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEncabezadoAsociaciones> obtenerEncabezadoReportePorDistrito(
			DTOFiltroReportes filtro) throws ClienteWebServiceException {

		List<Object[]> resultadoAsociaciones = new ArrayList<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();
		esReportePorDemarcacion = Boolean.FALSE;

		String qryAsociaciones = getContainer()
				.getQuery(
						"query_reportes_inicializaEncabezado_obtenAsociaciones_Distritos");

		Query query = getSession().createSQLQuery(qryAsociaciones);
		query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
		query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
		query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
		query.setParameter("P_ID_TIPO_CANDIDATURA",
				filtro.getIdTipoCandidatura());
		query.setParameter("P_CAPTURADA", CAPTURADA);


		resultadoAsociaciones = query.list();

		// Se obtienen asociaciones de partidos y candidaturas independientes
		if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
			encabezado_asociaciones = inicializaEncabezado(resultadoAsociaciones);
		}

		return encabezado_asociaciones;
	}
	
	//Se hace la consulta del nombre del CI, se omite su uso
	//debido a que se mueve la implementación al WS de Reportes
	private String obtenNombreCandidato(Integer idAsociacion, Integer idEstado) {

		String res;
		String qryNombreCandidato = getContainer().getQuery(
				"query_reportes_inicializaEncabezado_obtenNombreCI");

		Query query = getSession().createSQLQuery(qryNombreCandidato);
		query.setParameter("P_ID_ASOCIACION", idAsociacion);
		query.setParameter("P_ID_ESTADO", idEstado);

		String nombre = (String)query.uniqueResult();
		res = nombre == null ? "CI_" + idAsociacion : nombre;
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteConcentradoPorMunicipio(
			DTOFiltroReportes filtro) throws ClienteWebServiceException {
		this.partidos = new LinkedHashMap<>();
		List<Object[]> resultadoAsociaciones = new ArrayList<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();
		esReportePorDemarcacion = Boolean.FALSE;
		
		// Obtiene las asociaciones, subCoalisiones y CI a través de la tabla
		// COMPUTOSINE.ACTA_CASILLA_VOTOS
		String qryAsociaciones = getContainer().getQuery(
				"query_reportes_inicializaEncabezado_concentrado_por_municipio");
		
		Query query = getSession().createSQLQuery(qryAsociaciones);
		query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
		query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
		query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
		query.setParameter("P_ID_TIPO_CANDIDATURA", filtro.getIdTipoCandidatura());
		query.setParameter("P_CAPTURADA", CAPTURADA);
		
		resultadoAsociaciones = query.list();
		
		// Se obtienen asociaciones de partidos y candidaturas independientes
		if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
			encabezado_asociaciones = inicializaEncabezado(resultadoAsociaciones);
		}
       logger.info(" ENCABEZADO ASOCIACIONES  GENERAL"+ encabezado_asociaciones);
		return encabezado_asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEncabezadoAsociaciones> obtenerEncabezadoReportePorDemarcacion(
			DTOFiltroReportes filtro) throws Exception {
		
		List<Object[]> resultadoAsociaciones = new ArrayList<>();
		List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();

		String qryAsociaciones = getContainer()
				.getQuery(
						"query_reportes_inicializaEncabezado_obtenAsociaciones_Regidurias");

		Query query = getSession().createSQLQuery(qryAsociaciones);
		query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
		query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
		query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
		query.setParameter("P_ID_MUNICIPIO", filtro.getIdMunicipioElectoralSeleccionado());
		query.setParameter("P_ID_REGIDURIA", filtro.getIdDemarcacion());

		resultadoAsociaciones = query.list();

		// Se obtienen asociaciones de partidos y candidaturas independientes
		if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
			esReportePorDemarcacion = Boolean.TRUE;
			encabezado_asociaciones = inicializaEncabezado(resultadoAsociaciones);
		}

		return encabezado_asociaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean existenCasillasEspecialesPorDemarcacion(
			DTOFiltroReportes filtro) throws Exception {
		
		List<Object[]> resultadoAsociaciones = new ArrayList<>();
		
		String qry = getContainer().getQuery(
				"query_reportes_valida_existe_casillas_especiales_regidurias");

		Query query = getSession().createSQLQuery(qry);
		query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
		query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
		query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
		query.setParameter("P_ID_MUNICIPIO",
				filtro.getIdMunicipioElectoralSeleccionado());
		query.setParameter("P_ID_REGIDURIA", filtro.getIdDemarcacion());

		resultadoAsociaciones = query.list();

		if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}


	    @SuppressWarnings("unchecked")
@Override
public List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteConcentradoPorEstado(DTOFiltroReportes filtro) throws ClienteWebServiceException {
	this.partidos = new LinkedHashMap<>();
	List<Object[]> resultadoAsociaciones = new ArrayList<>();
	List<DTOEncabezadoAsociaciones> encabezado_asociaciones = new ArrayList<DTOEncabezadoAsociaciones>();
	esReportePorDemarcacion = Boolean.FALSE;
	logger.info("ENTRO AQUI NUEVO");
	// Obtiene las asociaciones, subCoalisiones y CI a través de la tabla
	// COMPUTOSINE.ACTA_CASILLA_VOTOS
	String qryAsociaciones = getContainer().getQuery("query_reportes_inicializaEncabezado_concentrado_por_estado");

	Query query = getSession().createSQLQuery(qryAsociaciones);
	query.setParameter("P_ID_PROCESO_ELECTORAL", filtro.getIdProceso());
	query.setParameter("P_ID_DETALLE_PROCESO", filtro.getIdDetalleProceso());
	query.setParameter("P_ID_ESTADO", filtro.getIdEstadoSeleccionado());
	//query.setParameter("P_ID_TIPO_CANDIDATURA", filtro.getIdTipoCandidatura());
	//query.setParameter("P_CAPTURADA", CAPTURADA);

	resultadoAsociaciones = query.list();

	// Se obtienen asociaciones de partidos y candidaturas independientes
	if (resultadoAsociaciones != null && !resultadoAsociaciones.isEmpty()) {
	    encabezado_asociaciones = inicializaEncabezadoRPEstatal(resultadoAsociaciones);
	}
	logger.info(" ENCABEZADO ASOCIACIONES  GENERAL" + encabezado_asociaciones);
	return encabezado_asociaciones;
}
	
	
	
}
