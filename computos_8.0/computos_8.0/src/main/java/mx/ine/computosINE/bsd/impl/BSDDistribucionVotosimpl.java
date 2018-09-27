package mx.ine.computosINE.bsd.impl;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.as.ASDistribucionVotosInterface;
import mx.ine.computosINE.as.ASInformacionGeneralActasInterface;
import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.dto.DTODistribucionTotParcial;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTOCandidato;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotParcialPK;
import mx.ine.computosINE.dto.DTODistribucionTotales;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;
import mx.ine.computosINE.dto.helper.HLPDistribucionVotos;


/**
 * <code>BSDDistribucionVotosimpl.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 28/04/2017 12:31:00
 */
@Component("bsdDistribucionVotos")
@Scope("prototype")
public class BSDDistribucionVotosimpl implements BSDDistribucionVotosInterface, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7716504077769846678L;

	private static final Log log = LogFactory.getLog(BSDDistribucionVotosimpl.class);

    @Autowired
    @Qualifier("asDistribucionVotos")
    private ASDistribucionVotosInterface asDistribucionVotos;
    
    
    @Autowired
    @Qualifier("asInfoGralActas")
    private ASInformacionGeneralActasInterface asInfoGralActas;
    

	/**
	 * Consulta la información de la tabla distribución totales
	 * @param pk
	 * @return
	 * @throws Exception
	 */
    public List<DTODistribucionTotales> consultarDistribucionTotales(DTODistribucionCandidatosPK pk) throws Exception{
    	return asDistribucionVotos.consultarDistribucionTotales(pk);
    }
    
    
	/**
	 * Consulta la información de la tabla distribución totales parcial
	 * @param pk
	 * @return
	 * @throws Exception
	 */
    public List<DTODistribucionTotParcial> consultarDistribucionTotalParcial(DTODistribucionCandidatosPK pk) throws Exception{
    	return asDistribucionVotos.consultarDistribucionTotalParcial(pk);
    }

	/**
	 * Consulta la información de la distribución final 
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel OPLE
	 * @param pk
	 * @return List<DTODistribucionCandidatos>
	 * 
	 */
	public List<DTODistribucionCandidatos> consultarDistribucionFinal(DTODistribucionCandidatosPK pk) throws Exception{
		return asDistribucionVotos.consultarDistribucionFinal(pk);
	}
	
	
	/**
	 * Consulta la información de la distribución parcial
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel CM
	 * @param pk
	 * @return List<DTODistribucionCandParcial>
	 */
     public List<DTODistribucionCandParcial> consultarDistribucionParcial(DTODistribucionCandidatosPK pk) throws Exception{
    	 return asDistribucionVotos.consultarDistribucionParcial(pk);
     }
     
     
     
     /**
      * Consulta la información de la distribución de partidos y candidatos independientes,
      * para el acta final de diputados rp a nivel estatal
      */
	public List<DTODistribucionPartidosCI> getDistribucionFinalDMRyDRP(Integer idProceso,
																	   Integer idDetalleProceso,
																	   Integer idEstado,
																	   List<Integer> idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getDistribucionFinalDMRyDRP(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}
     
     
     
     /**
      * Consulta la información de la distribución de partidos y candidatos independientes,
      * para los tipos de candidaturas de regidurias, ayuntamientos, gobernador y diputados
      * @param pk
	  * @return List<DTODistribucionPartidosCI>
      */
	public List<DTODistribucionPartidosCI> consultarDistribucionPartidosCI(DTODistribucionCandidatosPK pk) throws Exception{
		return asDistribucionVotos.consultarDistribucionPartidosCI(pk);
	}

	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionTotales getVotosNulosCNRDistribucionTotal(DTODistribucionCandidatosPK id) throws Exception{
		return asDistribucionVotos.getVotosNulosCNRDistribucionTotal(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionTotParcial getVotosNulosCNRDistribucionTotalParcial(DTODistribucionCandidatosPK id) throws Exception{
		return asDistribucionVotos.getVotosNulosCNRDistribucionTotalParcial(id);
	}
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatosParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionCandParcial getVotosNulosCNRDistribucionCandParcial(DTODistribucionCandidatosPK id) throws Exception{
		return asDistribucionVotos.getVotosNulosCNRDistribucionCandParcial(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatos
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionCandidatos getVotosNulosCNRDistribucionCandidatos(DTODistribucionCandidatosPK id) throws Exception{
		return asDistribucionVotos.getVotosNulosCNRDistribucionCandidatos(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionPartidosCI
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DTODistribucionPartidosCI getVotosNulosCNRDistribucionPartidosCI(DTODistribucionCandidatosPK id) throws Exception{
		return asDistribucionVotos.getVotosNulosCNRDistribucionPartidosCI(id);
	}


	/**
	 * Proporciona la lista de asociaciones que participan en una elección
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantes(DTOActaCasillaVotosPK pk) throws Exception {
		return asDistribucionVotos.consultarAsociacionesParticipantes(pk);
	}
	
	
	/**
	 * Proporciona la lista de asociaciones que participan en una elección por distrito
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesDistrito(DTOActaCasillaVotosPK pk)
			throws Exception {
		return asDistribucionVotos.consultarAsociacionesParticipantesDistrito(pk);
	}
	
	/**
	 * Proporciona la lista de asociaciones que participan en una elección por entidad
	 * @param pk
	 * @return List<DTOActaCasillaVotos>
	 */
	@Override
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesEntidad(DTOActaCasillaVotosPK pk) throws Exception {
		return asDistribucionVotos.consultarAsociacionesParticipantesEntidad(pk);
	}



	/**
	 * Consume información de la asociación
	 */
	@Override
	public DTOCandidatoWS getInfoFromAsociacion(Integer idProcesoElectoral, Integer idPartidoCandidato,
			Integer idEstado) throws Exception {
		log.info("BSDDistribucionVotosImpl.getInfoFromAsociacion");
		return asDistribucionVotos.getInfoFromAsociacion(idProcesoElectoral, idPartidoCandidato, idEstado);
	}
	

	/**
	 * Consulta la información de los distritos en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	public List<Integer> getDistritosWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getDistritosWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}
	
	
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
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getDistritosWithDistribucionRP(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}
	
	
	/**
	 * Consulta la información de las demarcaciones en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	public List<Integer> getDemarcacionesWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getDemarcacionesWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}
	
	
	/**
	 * Consulta la información de las demarcaciones del municipio del usuario
	 * en los cuales ya se encuentra hecha la distribución final, este método solo aplica
	 * para el tipo de candidatura Regidurias RP
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 * @param idTipoCandidatura
	 */
	public List<Integer> getDemarcacionesWithDistribucionRP(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getDemarcacionesWithDistribucionRP(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}


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
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getAyuntamientoWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}
	
	
	/**
	 * Consulta la información para saber si el estado que se le pasa
	 * como parametro ya tiene la distribución completa
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getEstadoWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idTipoCandidatura) throws Exception{
		return asDistribucionVotos.getEstadoWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idTipoCandidatura);
	}
	

	@Override
	public List<DTOCandidatoWS> consumeAsociacionesCoaliciones(Integer idProcesoElectoral, Integer idEstado,
			Integer idMunicipio, Integer idCandidatura) throws Exception {
		return asDistribucionVotos.consumeAsociacionesCoaliciones(idProcesoElectoral, idEstado, idMunicipio, idCandidatura);
	}


	/**
	 * Devuelve las distribuciones de votos a partir de la lista de DTOActaCasillaVotos y coaliciones que conforman un acta
	 * @param List<Object>
	 * @return ArrayList<Object>
	 */
	@Override
	public ArrayList<Object> generarDistribuciones(List<DTOActaCasillaVotos> actaVotos, List<DTOCSubcoaliciones> coaliciones, List<DTOCDetalleSubcoaliciones> subcoaliciones, List<DTOCandidato> candidatos) throws Exception {
		return asDistribucionVotos.generarDistribuciones(actaVotos, coaliciones, subcoaliciones, candidatos);
	}



	@Override
	public List<DTOCSubcoaliciones> getVotosCoalicionesHijas(List<DTOActaCasillaVotos> hijas) throws Exception {
		return asDistribucionVotos.getVotosCoalicionesHijas(hijas);
	}



	@Override
	public List<DTOCDetalleSubcoaliciones> getVotosHijas(List<DTOActaCasillaVotos> asociaciones, List<DTOCSubcoaliciones> hijas) throws Exception {
		return asDistribucionVotos.getVotosHijas(asociaciones, hijas);
	}
	
	/**
	 * Devuelve los votos acumulados de las asociaciones participantes en una demarcación geografica para la candidatura de ayuntamiento y diputados
	 * @param DTOActaCasillaVotosPK 
	 * @return List<DTOActaCasillaVotos>
	 */
	public List<DTOActaCasillaVotos> consultarVotosAcumuladosAsociacionesDipAyun(DTOActaCasillaVotosPK pk) throws Exception{
		return asDistribucionVotos.consultarVotosAcumuladosAsociacionesDipAyun(pk);
	}
	
	@Override
	public void eliminarDistribuciones(DTODistribucionTotalesPK pk) throws Exception {
		asDistribucionVotos.eliminarDistribuciones(pk);
	}
	
	@Override
	public void guardarDistribuciones(List<DTODistribucionTotales> totales, List<DTODistribucionPartidosCI> ppci,
			List<DTODistribucionCandidatos> candidatos) throws Exception {
		asDistribucionVotos.guardarDistribuciones(totales, ppci, candidatos);
	}


	@Override
	public boolean buscarDistribucionParcialCreada(DTODistribucionCandidatosPK buscarDistribucion) throws Exception {
		return asDistribucionVotos.buscarDistribucionParcialCreada(buscarDistribucion);
	}
	
	@Override
	public boolean buscarDistribucionCreada(DTODistribucionTotalesPK buscarDistribucion) throws Exception {
		return asDistribucionVotos.buscarDistribucionCreada(buscarDistribucion);
	}
	
	
	/**
	 * Método que guarda la distribución final de partidos en la tabla de partidos ci
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionPartidosCI(List<DTODistribucionPartidosCI> ppCI) throws Exception{
		asDistribucionVotos.guardarDistribucionPartidosCI(ppCI);
	}
	
	
	/**
	 * Método que guarda la distribución total parcial en la tabla de totales parcial
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionTotalParcial(List<DTODistribucionTotParcial> totParcial) throws Exception{
		asDistribucionVotos.guardarDistribucionTotalParcial(totParcial);
	}
	
	
	/**
	 * Método que guarda la distribución parcial de candidatos en tabla parcial
	 * @param totParcial
	 * @throws Exception
	 */
	public void guardarDistribucionCandParcial(List<DTODistribucionCandParcial> candParcial) throws Exception{
		asDistribucionVotos.guardarDistribucionCandParcial(candParcial);
	}
	
	/**
	 * Método que obtiene las subcoaliciones de una lista de coaliciones
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<DTOCSubcoaliciones> getSubcoaliciones(List<Integer> coaliciones) throws Exception{
		return asDistribucionVotos.getSubcoaliciones(coaliciones);
	}
	
	
	/**
	 * Método que obtiene el objeto de la coalicion, para obtener los datos del emblema
	 * @param coalicion
	 * @return
	 * @throws Exception
	 */
	public DTOCSubcoaliciones getEmblemaCoalicion(Integer coalicion) throws Exception{
		return asDistribucionVotos.getEmblemaCoalicion(coalicion);
	}
	
	/**
	 * Método que consulta las coaliciones para un tipo de candidatura
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getCoaliciones(Object [] arg0, Integer idDistrito)  throws Exception{
		return asDistribucionVotos.getCoaliciones(arg0, idDistrito);
	}
	
	/**
	 * Método que consulta los partidos politicos que forman parte de una coalicion
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<Object[]>  getPartidosCoalicion(List<Integer> coaliciones) throws Exception{
		return asDistribucionVotos.getPartidosCoalicion(coaliciones);
	}
	
	/**
	 * Método que consulta los partidos politicos que forman parte de una coalicion
	 * @param coaliciones
	 * @return
	 * @throws Exception
	 */
	public List<Integer>  getPartidosSinCoalicion(List<Integer> partidosConCoalicion) throws Exception{
		return asDistribucionVotos.getPartidosSinCoalicion(partidosConCoalicion);
	}

	
	/**
	 * Método que elimina DISTRIBUCION_CAND_PARCIAL para cuando se elimina un acta parcial
	 */
	@Override
	public void eliminaDistribucionCandParcial(DTODistribucionCandParcialPK pk)
			throws Exception {
		asDistribucionVotos.eliminaDistribucionCandParcial(pk);
	}

	/**
	 * Método que elimina DISTRIBUCION_TOT_PARCIAL para cuando se elimina un acta parcial
	 */
	@Override
	public void eliminaDistribucionTotParcial(DTODistribucionTotParcialPK pk)
			throws Exception {
		asDistribucionVotos.eliminaDistribucionTotParcial(pk);		
	}
	
	
	/**
	 * Método que permite eliminar la distribución de partidos y candidatos independientes
	 * @param pk
	 * @throws Exception
	 */
	public void eliminaDistribucionPartidosCI(DTODistribucionPartidosCIPK pk) throws Exception{
		asDistribucionVotos.eliminaDistribucionPartidosCI(pk);
	}
	
	/**
	 * Método que permite eliminar la distribución de partidos y candidatos independientes
	 * @param pk
	 * @throws Exception
	 */
	public List<HLPDistribucionVotos> getTotalVotosPPCIMRyRPFinal(Object[] arg0) throws Exception{
		return asDistribucionVotos.getTotalVotosPPCIMRyRPFinal(arg0);
	}
	
	/**
	 * Método que consulta la distribución parcial de Diputados RP a nivel distrito
	 * esto para posteriromente insertar esta informacion en la tabla de distribucion
	 * final de partidosci
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPDistribucionVotos> getDistribucionParcialByDistritoRP(Object[] arg0) throws Exception{
		return asDistribucionVotos.getDistribucionParcialByDistritoRP(arg0);
	}
	
	
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
			 											  Integer idTipoCandidatura) throws Exception{
		
		return asDistribucionVotos.getDistribucionFinalByDistritos(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}

}
