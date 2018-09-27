package mx.ine.computosINE.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCEstatus;
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
import mx.ine.computosINE.dto.helper.HLPDistribucionVotos;

/**
 * <code>DAODistribucionVotosInterfaces.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 28/04/2017 11:58:00
 */
public interface DAODistribucionVotosInterface {
	

	/**
	 * Casillas aprobadas por entidad
	 */
	public List<DTOCasillaWS> casillasAprobadasPorEntidad(Integer idEstado) throws Exception;
	
	
	/**
	 * Consulta la información de la tabla distribución totales
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionTotales> consultarDistribucionTotales(DTODistribucionCandidatosPK pk) throws Exception;
	
	
	/**
	 * Consulta la información de la tabla distribución totales parcial
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public List<DTODistribucionTotParcial> consultarDistribucionTotalParcial(DTODistribucionCandidatosPK pk) throws Exception;
	
	
	/**
	 * Consulta la información de la distribución final 
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel OPLE
	 * @param pk
	 * @return List<DTODistribucionCandidatos>
	 * 
	 */
	public List<DTODistribucionCandidatos> consultarDistribucionFinal(DTODistribucionCandidatosPK pk) throws Exception;
	
	
	
	/**
	 * Consulta la información de la distribución parcial
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel CM
	 * @param pk
	 * @return List<DTODistribucionCandParcial>
	 */
     public List<DTODistribucionCandParcial> consultarDistribucionParcial(DTODistribucionCandidatosPK pk) throws Exception;
     
     
     
     /**
      * Consulta la información de la distribución de partidos y candidatos independientes,
      * para los tipos de candidaturas de regidurias, ayuntamientos, gobernador y diputados
      * @param pk
	  * @return List<DTODistribucionPartidosCI>
      */
	public List<DTODistribucionPartidosCI> getDistribucionFinalDMRyDRP(Integer idProceso,
																	   Integer idDetalleProceso,
																	   Integer idEstado,
																	   List<Integer> idTipoCandidatura) throws Exception;
	
	
	
	
    /**
     * Consulta la información de la distribucion de partidos politicos, aplica                                          
     * @param pk
	  * @return List<DTODistribucionPartidosCI>
     */
	public List<DTODistribucionPartidosCI> consultarDistribucionPartidosCI(DTODistribucionCandidatosPK pk) throws Exception;
	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionTotales getVotosNulosCNRDistribucionTotal(DTODistribucionCandidatosPK id) throws Exception;
	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionTotParcial getVotosNulosCNRDistribucionTotalParcial(DTODistribucionCandidatosPK id) throws Exception;
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatosParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionCandParcial getVotosNulosCNRDistribucionCandParcial(DTODistribucionCandidatosPK id) throws Exception;
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatos
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionCandidatos getVotosNulosCNRDistribucionCandidatos(DTODistribucionCandidatosPK id) throws Exception;
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionPartidosCI
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionPartidosCI getVotosNulosCNRDistribucionPartidosCI(DTODistribucionCandidatosPK id) throws Exception;
	
	/**
	 * Consulta la información de los distritos en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	public List<Integer> getDistritosWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta la información de los distritos que ya tienen capturada una distribución,
	 * al seleccionar el registro del idMunicipio, este método solo funciona para el tipo de candidatura
	 * de Diputados RP
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	public List<Integer> getDistritosWithDistribucionRP(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta la información de los distritos en los cuales ya se encuentra hecha la distribución
	 * final este metodo solo aplica para diputados RP, ya que solo se guarda información de distribución
	 * en la tabla de partidos ci
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	public List<Integer> getDemarcacionesWithDistribucionRP(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception;
	
	
	
	/**
	 * Consulta la información de las demarcaciones en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	public List<Integer> getDemarcacionesWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta la información para saber si el ayuntamiento o municipio que se le pasa
	 * como parametro ya tiene distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getAyuntamientoWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * Consulta la información para saber si el estado que se le pasa
	 * como parametro ya tiene distribución completa
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getEstadoWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idTipoCandidatura) throws Exception;
	
	
	/**
	 * 
	 *  Proporciona la lista de asociaciones que participan en una elección
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantes(DTOActaCasillaVotosPK pk) throws Exception;
	
	/**
	 * 
	 *  Proporciona la lista de asociaciones que participan en una elección por distrito
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesDistrito(DTOActaCasillaVotosPK pk) throws Exception;
	
	/**
	 * 
	 *  Proporciona la lista de asociaciones que participan en una elección por entidad
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesEntidad(DTOActaCasillaVotosPK pk) throws Exception;
	
	/**
	 * Devuelve las subcoaliciones de las coaliciones padre
	 * @param List<DTOActaCasillaVotos>
	 * @return List<DTOCSubcoaliciones>
	 */
	public List<DTOCSubcoaliciones> getVotosCoalicionesHijas(List<DTOActaCasillaVotos> hijas) throws Exception;
	
	
	/**
	 * Devuelve las subcoaliciones de una lista de coaliciones dada
	 */
	public List<DTOSubcoalicion> recuperaSubcoaliciones(List<DTOAsociacion> coaliciones) throws Exception;
	
	
	
	/**
	 * Método que obtiene las subcoaliciones de una lista de coaliciones
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<DTOCSubcoaliciones> getSubcoaliciones(List<Integer> coaliciones) throws Exception;
	
	
	/**
	 * Método que obtiene el objeto de la coalicion, para obtener los datos del emblema
	 * @param coalicion
	 * @return
	 * @throws Exception
	 */
	public DTOCSubcoaliciones getEmblemaCoalicion(Integer coalicion) throws Exception;

	
	/**
	 * Devuelve los votos asignados a los PP a partir de las coaliciones hijas
	 * @param List<DTOCSubcoaliciones>
	 * @return List<DTOCDetalleSubcoaliciones>
	 */
	public List<DTOCDetalleSubcoaliciones> getVotosHijas(List<DTOActaCasillaVotos> asociaciones, List<DTOCSubcoaliciones> hijas) throws Exception;
	
	
	/**
	 * Devuelve los votos acumulados de las asociaciones participantes en una demarcación geografica para la candidatura de ayuntamiento y diputados
	 * @param DTOActaCasillaVotosPK 
	 * @return List<DTOActaCasillaVotos>
	 */
	public List<DTOActaCasillaVotos> consultarVotosAcumuladosAsociacionesDipAyun(DTOActaCasillaVotosPK pk) throws Exception;
	
	/**
	 * Verifica si ya existe distrucion. </BR>
	 * true		: Si ya existe distribucion </BR>
	 * false	: No existe distrucion
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean existeDistribucionTotales(DTODistribucionTotalesPK id) throws Exception;

	/**
	 * Verifica si ya existe distrucion de Partidos CI. </BR>
	 * true		: Si ya existe distribucion </BR>
	 * false	: No existe distrucion
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean existeDistribucionPartidosCI(DTODistribucionPartidosCIPK id) throws Exception;

	/**
	 * Verifica si ya existe distrucion de Candidatos. </BR>
	 * true		: Si ya existe distribucion </BR>
	 * false	: No existe distrucion
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean existeDistribucionCandidatos(DTODistribucionCandidatosPK id) throws Exception;

	/**
	 * Elimina la distribución Total (PP, CI, Coaliciones)
	 */
	public void eliminaTotales(DTODistribucionTotalesPK pk) throws Exception;
	
	/**
	 * Elimina la distribución por PP
	 */
	public void eliminaDistribucionPP(DTODistribucionTotalesPK pk) throws Exception;
	
	/**
	 * Elimina la distribución por Candidato
	 */
	public void eliminaDistribucionCandidato(DTODistribucionTotalesPK pk) throws Exception;
	
	/**
	 * Guarda la distribución Total (PP, CI, Coaliciones)
	 */
	public void saveTotales(List<DTODistribucionTotales> totales) throws Exception;
	
	/**
	 * Guarda la distribución por PP
	 */
	public void saveDistribucionPP(List<DTODistribucionPartidosCI> ppci) throws Exception;
	
	/**
	 * Guarda la distribución por Candidato
	 */
	public void saveDistribucionCandidato(List<DTODistribucionCandidatos> candidatos) throws Exception;
	
	/**
	 * Verifica si ya ha sido creada una distribución parcial
	 */
	public boolean buscarDistribucionParcialCreada(DTODistribucionCandidatosPK buscarDistribucion) throws Exception;
	
	
	/**
	 * Verifica si ya ha sido creada una distribución
	 */
	public boolean buscarDistribucionCreada(DTODistribucionTotalesPK buscarDistribucion) throws Exception;
	
	
	/**
	 * Método que guarda la distribución final de partidos en la tabla de partidos ci
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionPartidosCI(List<DTODistribucionPartidosCI> ppCI) throws Exception;
	
	
	/**
	 * Método que guarda la distribución total parcial en la tabla de totales parcial
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionTotalParcial(List<DTODistribucionTotParcial> totParcial) throws Exception;
	
	/**
	 * Método que guarda la distribución parcial de candidatos en tabla parcial
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionCandParcial(List<DTODistribucionCandParcial> candParcial) throws Exception;
	
	/**
	 * Método que consulta las coaliciones para un tipo de candidatura
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getCoaliciones(Object [] arg0, Integer idDistrito)  throws Exception;
	
	
	/**
	 * Método que consulta los partidos politicos que forman parte de una coalicion
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<Object[]>  getPartidosCoalicion(List<Integer> coaliciones) throws Exception ;
	
	/**
	 * Método que elimina DISTRIBUCION_CAND_PARCIAL cuando se elimina un acta parcial
	 * @Param DTODistribucionCandParcialPK
	 * @return
	 */
	public void eliminaDistribucionCandParcial(DTODistribucionCandParcialPK pk) throws Exception ;
	
	
	/**
	 * Método que elimina DISTRIBUCION_TOT_PARCIAL cuando se elimina un acta parcial
	 * @Param DTODistribucionCandParcialPK
	 * @return
	 */
	public void eliminaDistribucionTotParcial(DTODistribucionTotParcialPK pk) throws Exception ;
	
	
	/**
	 * Método que consulta los partidos politicos que forman parte de una coalicion
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<Integer>  getPartidosSinCoalicion(List<Integer> partidosConCoalicion) throws Exception ;
	
	
	/**
	 * Método que consulta  la suma de las distribuciones por partido politico para
	 * el acta de diputados rp a nivel estatal
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPDistribucionVotos> getTotalVotosPPCIMRyRPFinal(Object[] arg0) throws Exception; 
	
	/**
	 * Método que consulta la distribución parcial de Diputados RP a nivel distrito
	 * esto para posteriromente insertar esta informacion en la tabla de distribucion
	 * final de partidosci
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPDistribucionVotos> getDistribucionParcialByDistritoRP(Object[] arg0) throws Exception;
	
	/**
	 * Método que permite eliminar la distribución de partidos y candidatos independientes
	 * @param pk
	 * @throws Exception
	 */
	public void eliminaDistribucionPartidosCI(DTODistribucionPartidosCIPK pk) throws Exception;
	
	
	/**
	 * Método que obtiene los distritos que ya cuentan con distribucion final a nivel de mr
	 * @param idProceso
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getDistribucionFinalByDistritos(Integer idProceso,
			 											  Integer idDetalleProceso,
			 											  Integer idEstado,
			 											  Integer idTipoCandidatura) throws Exception;
}
