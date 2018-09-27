package mx.ine.computosINE.dao;

import java.util.List;

import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.computosINE.dto.DTOFiltroReportes;
import mx.ine.computosINE.dto.reportes.DTOEncabezadoAsociaciones;

/**
 * Interfaz para obtener las asociaciones a pintar en el encabezado del reporte.
 * 
 * Se consulta la tabla COMPUTOSINE.ACTA_CASILLA_VOTOS para obtener los
 * idAsociaciones, y se hace join con ORGANOS_DIRECTIVOS.ASOCIACIONES y
 * COMPUTOSINE.C_SUBCOALICIONES para obtener las siglas de las asociaciones. En
 * caso de ser candidato independiente, se obtiene su nombre por medio del WS
 * ComputosINEReportes/servicios/reportes/consultaCandidatosIndependientes
 * 
 * @author INE
 *
 */
public interface DAOReportesInterface {

	/**
	 * Método que permite construir el renglón de asociaciones para el
	 * encabezado del reporte a nivel casilla.
	 * 
	 * @param filtroReportes
	 * @return
	 */
	List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteXCasillas(
			DTOFiltroReportes filtroReportes) throws Exception;

	/**
	 * Método que permite construir el renglón de asociaciones para el
	 * encabezado del reporte a nivel distrito
	 * 
	 * @param filtroReportes
	 * @return
	 */
	List<DTOEncabezadoAsociaciones> obtenerEncabezadoReportePorDistrito(
			DTOFiltroReportes filtroReportes) throws Exception;

	/**
	 * Método construye los encabezados del reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 */
	List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteConcentradoPorMunicipio(
			DTOFiltroReportes dtoFiltros) throws Exception;

	/**
	 * Método que permite construir el renglón de asociaciones para el
	 * encabezado del reporte a nivel demarcacion. Se consulta en la tabla
	 * COMPUTOSINE.DISTRIBUCION_PARTIDOS_CI para generar el reporte
	 * 
	 * @param filtroReportes
	 * @return
	 */
	List<DTOEncabezadoAsociaciones> obtenerEncabezadoReportePorDemarcacion(
			DTOFiltroReportes filtroReportes) throws Exception;

	/**
	 * Método para validar si hay casillas especiales para la demarcación
	 * seleccionada
	 * 
	 * @param dtoFiltros
	 * @return
	 * @throws Exception
	 */
	boolean existenCasillasEspecialesPorDemarcacion(DTOFiltroReportes dtoFiltros)
			throws Exception;
	

	/**
	 * Método construye los encabezados del reporte
	 * 
	 * @param dtoFiltros
	 * @return
	 */
	List<DTOEncabezadoAsociaciones> obtenerEncabezadoReporteConcentradoPorEstado(
			DTOFiltroReportes dtoFiltros) throws Exception;
	

	

}
