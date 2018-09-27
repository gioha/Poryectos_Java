package mx.ine.computosINE.bsd.impl;

import java.util.List;

import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.as.ASReportesInterface;
import mx.ine.computosINE.bsd.BSDReportesInterface;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.helper.HLPComputoPorCasilla;
import mx.ine.computosINE.dto.helper.HLPComputoPorDemarcacion;
import mx.ine.computosINE.dto.helper.HLPComputoPorDistrito;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorEstado;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorMunicipio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdReportes")
@Scope("prototype")
public class BSDReportesImpl implements BSDReportesInterface {

	@Autowired
	@Qualifier("asReportes")
	private ASReportesInterface asReportes;

	@Override
	public HLPComputoPorCasilla inicializaEncabezadoReportePorCasilla(
			DTOFiltroReportes dtoFiltros) throws Exception {
		return asReportes.inicializaEncabezadoReportePorCasilla(dtoFiltros);
	}

	@Override
	public List<DTODetalleEstadoProcesoWS> obtenEstadosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle)
			throws ClienteWebServiceException {
		return asReportes.obtenEstadosPorProcesoDetalle(idSistema, idProceso,
				idDetalle);
	}

	@Override
	public List<DTODetalleMunicipioProcesoWS> obtenMunicipiosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle,
			Integer idEstado) throws ClienteWebServiceException {
		return asReportes.obtenMunicipiosPorProcesoDetalle(idSistema,
				idProceso, idDetalle, idEstado);
	}

	@Override
	public HLPComputoPorDistrito inicializaEncabezadoReportePorDistrito(
			DTOFiltroReportes dtoFiltros) throws Exception {
		return asReportes.inicializaEncabezadoReportePorDistrito(dtoFiltros);
	}
	
	@Override
	public HLPConcentradoPorMunicipio inicializaEncabezadosReportesConcentradoPorMunicipio(
			DTOFiltroReportes dtoFiltros) throws Exception {
		return asReportes.inicializaEncabezadosReportesConcentradoPorMunicipio(dtoFiltros);
	}

	@Override
	public HLPComputoPorDemarcacion inicializaEncabezadoReportePorDemarcacion(
			DTOFiltroReportes dtoFiltros) throws Exception {		
		return asReportes.inicializaEncabezadosReportesPorDemarcacion(dtoFiltros);
	}
	
	@Override
	public HLPConcentradoPorEstado inicializaEncabezadosReportesConcentradoPorEstado(
			DTOFiltroReportes dtoFiltros) throws Exception {
		return asReportes.inicializaEncabezadosReportesConcentradoPorEstado(dtoFiltros);
	}

}
