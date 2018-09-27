package mx.ine.computosINE.as.impl;

import java.util.List;

import mx.ine.common.helper.HLPAdministracionInterface;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.as.ASReportesInterface;
import mx.ine.computosINE.dao.DAOReportesInterface;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.helper.HLPComputoPorCasilla;
import mx.ine.computosINE.dto.helper.HLPComputoPorDemarcacion;
import mx.ine.computosINE.dto.helper.HLPComputoPorDistrito;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorEstado;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorMunicipio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("asReportes")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASReportesImpl implements ASReportesInterface {

	@Autowired
	private HLPAdministracionInterface helper;

	@Autowired
	@Qualifier("daoReportes")
	private DAOReportesInterface daoReportes;

	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public HLPComputoPorCasilla inicializaEncabezadoReportePorCasilla(
			DTOFiltroReportes filtros) throws Exception {

		HLPComputoPorCasilla computoPorCasilla = new HLPComputoPorCasilla();

		computoPorCasilla.setAsociaciones(daoReportes
				.obtenerEncabezadoReporteXCasillas(filtros));

		return computoPorCasilla;
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public HLPComputoPorDistrito inicializaEncabezadoReportePorDistrito(
			DTOFiltroReportes filtros) throws Exception {

		HLPComputoPorDistrito computoPorDistrito = new HLPComputoPorDistrito();
		computoPorDistrito.setAsociaciones(daoReportes
				.obtenerEncabezadoReportePorDistrito(filtros));
		return computoPorDistrito;
	}

	@Override
	public List<DTODetalleEstadoProcesoWS> obtenEstadosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle)
			throws ClienteWebServiceException {
		return helper.obtenerEstadosDestalle(idSistema, idProceso, idDetalle);
	}

	@Override
	public List<DTODetalleMunicipioProcesoWS> obtenMunicipiosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle,
			Integer idEstado) throws ClienteWebServiceException {
		return helper.obtenerMunicipiosDetalle(idSistema, idProceso, idDetalle,
				idEstado);
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public HLPConcentradoPorMunicipio inicializaEncabezadosReportesConcentradoPorMunicipio(
			DTOFiltroReportes dtoFiltros) throws Exception {
		HLPConcentradoPorMunicipio concentradoPorMunicipio = new HLPConcentradoPorMunicipio();

		concentradoPorMunicipio.setEncabezados(daoReportes
				.obtenerEncabezadoReporteConcentradoPorMunicipio(dtoFiltros));
		return concentradoPorMunicipio;
	}

	@Override
	public HLPComputoPorDemarcacion inicializaEncabezadosReportesPorDemarcacion(
			DTOFiltroReportes dtoFiltros) throws Exception {
		HLPComputoPorDemarcacion computoPorDemarcacion = new HLPComputoPorDemarcacion();
		computoPorDemarcacion.setAsociaciones(daoReportes
				.obtenerEncabezadoReportePorDemarcacion(dtoFiltros));
		if (computoPorDemarcacion.getAsociaciones() != null
				&& !computoPorDemarcacion.getAsociaciones().isEmpty()) {
			computoPorDemarcacion.setHayCasillasEspeciales(daoReportes
					.existenCasillasEspecialesPorDemarcacion(dtoFiltros));
		}
		return computoPorDemarcacion;
	}
	
	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public HLPConcentradoPorEstado inicializaEncabezadosReportesConcentradoPorEstado(
		DTOFiltroReportes dtoFiltros) throws Exception {
	    	HLPConcentradoPorEstado concentradoPorEstado = new HLPConcentradoPorEstado();
	    	concentradoPorEstado.setEncabezados(daoReportes
	    		.obtenerEncabezadoReporteConcentradoPorEstado(dtoFiltros));
	    return concentradoPorEstado;
	}
}
