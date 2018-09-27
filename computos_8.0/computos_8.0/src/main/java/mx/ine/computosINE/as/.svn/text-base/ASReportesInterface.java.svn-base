package mx.ine.computosINE.as;

import java.util.List;

import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.helper.HLPComputoPorCasilla;
import mx.ine.computosINE.dto.helper.HLPComputoPorDemarcacion;
import mx.ine.computosINE.dto.helper.HLPComputoPorDistrito;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorEstado;
import mx.ine.computosINE.dto.helper.HLPConcentradoPorMunicipio;

public interface ASReportesInterface {

	/**
	 * Se inicializan los nombres de las asociaciones a presentarse en el
	 * reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	HLPComputoPorCasilla inicializaEncabezadoReportePorCasilla(
			DTOFiltroReportes dtoFiltros) throws Exception;

	/**
	 * Se inicializan los nombres de las asociaciones a presentarse en el
	 * reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	HLPComputoPorDistrito inicializaEncabezadoReportePorDistrito(
			DTOFiltroReportes dtoFiltros) throws Exception;

	/**
	 * Se inicializan los nombres de las asociaciones a presentarse en el
	 * reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	HLPConcentradoPorMunicipio inicializaEncabezadosReportesConcentradoPorMunicipio(
			DTOFiltroReportes dtoFiltros) throws Exception;

	/**
	 * Se obtiene lista de entidades federativas a traves del WS de
	 * parametrizacion, parametrizacion/ws/estadosEleccion
	 * 
	 * @param idSistema
	 * @param idProceso
	 * @param idDetalle
	 * @return
	 * @throws ClienteWebServiceException
	 */
	List<DTODetalleEstadoProcesoWS> obtenEstadosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle)
			throws ClienteWebServiceException;

	/**
	 * Se obtiene lista de municipios a traves del WS de parametrizacion,
	 * parametrizacion/ws/municipiosEleccion
	 * 
	 * @param idSistema
	 * @param idProceso
	 * @param idDetalle
	 * @param idEstado
	 * @return
	 * @throws ClienteWebServiceException
	 */
	List<DTODetalleMunicipioProcesoWS> obtenMunicipiosPorProcesoDetalle(
			Integer idSistema, Integer idProceso, Integer idDetalle,
			Integer idEstado) throws ClienteWebServiceException;

	/**
	 * Se inicializan los nombres de las asociaciones a presentarse en el
	 * reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	HLPComputoPorDemarcacion inicializaEncabezadosReportesPorDemarcacion(
			DTOFiltroReportes dtoFiltros) throws Exception;

	
	/**
	 * Se inicializan los nombres de las asociaciones a presentarse en el
	 * reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	HLPConcentradoPorEstado inicializaEncabezadosReportesConcentradoPorEstado(
			DTOFiltroReportes dtoFiltros) throws Exception;
}
