package mx.ine.computosINE.as.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.common.helper.HLPCandidatosInterface;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.as.ASDistribucionVotosInterface;
import mx.ine.computosINE.dao.DAODistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOCDetalleSubcoaliciones;
import mx.ine.computosINE.dto.DTOCSubcoaliciones;
import mx.ine.computosINE.dto.DTOCandidato;
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
import mx.ine.computosINE.helper.HLPDistribuciones;


/**
 * <code>ASDistribucionVotosImpl.java</code>Descripcion de la clase
 *
 * @author Alejandra Gómez Ruiz
 * @version 1.0
 * @since 28/04/2017 12:13:00
 */
@Scope("prototype")
@Service("asDistribucionVotos")
//@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
public class ASDistribucionVotosImpl implements ASDistribucionVotosInterface{

	@Autowired
	@Qualifier("daoDistribucionVotos")
	private transient DAODistribucionVotosInterface dao;
	
	@Autowired
	@Qualifier("hlpCandidatos")
	private transient HLPCandidatosInterface hlpCandidatos;
	
	@Autowired
	@Qualifier("hplDistribuciones")
	private transient HLPDistribuciones hlpDistribuciones;
	
	
	/**
	 * Consulta la información de la tabla distribución totales
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTODistribucionTotales> consultarDistribucionTotales(DTODistribucionCandidatosPK pk) throws Exception{
		return dao.consultarDistribucionTotales(pk);
	}
	
	/**
	 * Consulta la información de la tabla distribución totales parcial
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTODistribucionTotParcial> consultarDistribucionTotalParcial(DTODistribucionCandidatosPK pk) throws Exception{
		return dao.consultarDistribucionTotalParcial(pk);
	}
	
	/**
	 * Consulta la información de la distribución final 
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel OPLE
	 * @param pk
	 * @return List<DTODistribucionCandidatos>
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTODistribucionCandidatos> consultarDistribucionFinal(DTODistribucionCandidatosPK pk) throws Exception{
		return dao.consultarDistribucionFinal(pk);
	}
	
	
	
	/**
	 * Consulta la información de la distribución parcial
	 * tomando en cuenta que es una candidatura de diputado o gobernador a nivel CM
	 * @param pk
	 * @return List<DTODistribucionCandParcial>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
     public List<DTODistribucionCandParcial> consultarDistribucionParcial(DTODistribucionCandidatosPK pk) throws Exception{
    	 return dao.consultarDistribucionParcial(pk);
     }
     
    
    /**
     * Consulta la información de la distribución de partidos y candidatos independientes,
     * para el acta final de diputados rp a nivel estatal
     */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTODistribucionPartidosCI> getDistribucionFinalDMRyDRP(Integer idProceso,
																	   Integer idDetalleProceso,
																	   Integer idEstado,
																	   List<Integer> idTipoCandidatura) throws Exception{
		
		return dao.getDistribucionFinalDMRyDRP(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}
	
     
     /**
      * Consulta la información de la distribución de partidos y candidatos independientes,
      * para los tipos de candidaturas de regidurias, ayuntamientos, gobernador y diputados
      * @param pk
	  * @return List<DTODistribucionPartidosCI>
      */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTODistribucionPartidosCI> consultarDistribucionPartidosCI(DTODistribucionCandidatosPK pk) throws Exception{
		return dao.consultarDistribucionPartidosCI(pk);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTODistribucionTotales getVotosNulosCNRDistribucionTotal(DTODistribucionCandidatosPK id) throws Exception{
		return dao.getVotosNulosCNRDistribucionTotal(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionTotalParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTODistribucionTotParcial getVotosNulosCNRDistribucionTotalParcial(DTODistribucionCandidatosPK id) throws Exception{
		return dao.getVotosNulosCNRDistribucionTotalParcial(id);
		
	}
	
	/**
	 * Consulta la información de total de votos nulos y de candidatos no registrados
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatosParcial
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTODistribucionCandParcial getVotosNulosCNRDistribucionCandParcial(DTODistribucionCandidatosPK id) throws Exception{
		return dao.getVotosNulosCNRDistribucionCandParcial(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionCandidatos
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTODistribucionCandidatos getVotosNulosCNRDistribucionCandidatos(DTODistribucionCandidatosPK id) throws Exception{
		return dao.getVotosNulosCNRDistribucionCandidatos(id);
	}
	
	
	/**
	 * Consulta la información de total de votos nulos y candidatos no registrados 
	 * dependiendo del idTipoCandidatura en la tabla de DistribucionPartidosCI
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTODistribucionPartidosCI getVotosNulosCNRDistribucionPartidosCI(DTODistribucionCandidatosPK id) throws Exception{
		return dao.getVotosNulosCNRDistribucionPartidosCI(id);
	}
	
	
	
	/**
	 * Consulta la información de los distritos en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getDistritosWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return dao.getDistritosWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
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
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getDistritosWithDistribucionRP(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return dao.getDistritosWithDistribucionRP(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
	}
	
	
	/**
	 * Consulta la información de las demarcaciones en los cuales ya se encuentra hecha la distribución
	 * @param idProcesoElectoral
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idMunicipio
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getDemarcacionesWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio,Integer idTipoCandidatura) throws Exception{
		return dao.getDemarcacionesWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
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
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getDemarcacionesWithDistribucionRP(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return dao.getDemarcacionesWithDistribucionRP(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
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
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getAyuntamientoWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura) throws Exception{
		return dao.getAyuntamientoWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio, idTipoCandidatura);
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
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getEstadoWithDistribucion(Integer idProcesoElectoral,
			 Integer idDetalleProceso, Integer idEstado, Integer idTipoCandidatura) throws Exception{
		return dao.getEstadoWithDistribucion(idProcesoElectoral, idDetalleProceso, idEstado, idTipoCandidatura);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantes(DTOActaCasillaVotosPK pk) throws Exception {
		return dao.consultarAsociacionesParticipantes(pk);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesDistrito(DTOActaCasillaVotosPK pk) throws Exception {
		return dao.consultarAsociacionesParticipantesDistrito(pk);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultarAsociacionesParticipantesEntidad(DTOActaCasillaVotosPK pk) throws Exception {
		return dao.consultarAsociacionesParticipantesEntidad(pk);
	}



	@Override
	public DTOCandidatoWS getInfoFromAsociacion(Integer idProcesoElectoral, Integer idPartidoCandidato,
			Integer idEstado) throws Exception {
		return hlpCandidatos.consultarAsociacion(idProcesoElectoral, idPartidoCandidato, idEstado);
	}



	@Override
	public List<DTOCandidatoWS> consumeAsociacionesCoaliciones(Integer idProcesoElectoral, Integer idEstado,
			Integer idMunicipio, Integer idCandidatura) throws Exception {
		return hlpCandidatos.consultarAsociacionesCoaliciones(idProcesoElectoral, idEstado, idCandidatura, null, idMunicipio, null);
	}



	@Override
	public ArrayList<Object> generarDistribuciones(List<DTOActaCasillaVotos> actaVotos, List<DTOCSubcoaliciones> coaliciones, List<DTOCDetalleSubcoaliciones> subcoaliciones, List<DTOCandidato> candidatos) throws Exception {
		return hlpDistribuciones.generarDistribuciones(actaVotos, coaliciones, subcoaliciones, candidatos);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOCSubcoaliciones> getVotosCoalicionesHijas(List<DTOActaCasillaVotos> hijas) throws Exception {
		return dao.getVotosCoalicionesHijas(hijas);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOCDetalleSubcoaliciones> getVotosHijas(List<DTOActaCasillaVotos> asociaciones, List<DTOCSubcoaliciones> hijas) throws Exception {
		return dao.getVotosHijas(asociaciones, hijas);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultarVotosAcumuladosAsociacionesDipAyun(DTOActaCasillaVotosPK pk) throws Exception{
		return dao.consultarVotosAcumuladosAsociacionesDipAyun(pk);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminarDistribuciones(DTODistribucionTotalesPK pk) throws Exception {
		dao.eliminaTotales(pk);
		dao.eliminaDistribucionPP(pk);
		dao.eliminaDistribucionCandidato(pk);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public void guardarDistribuciones(List<DTODistribucionTotales> totales, List<DTODistribucionPartidosCI> ppci,
			List<DTODistribucionCandidatos> candidatos) throws Exception {
		dao.saveTotales(totales);
		dao.saveDistribucionPP(ppci);
		dao.saveDistribucionCandidato(candidatos);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean buscarDistribucionParcialCreada(DTODistribucionCandidatosPK buscarDistribucion) throws Exception {
		return dao.buscarDistribucionParcialCreada(buscarDistribucion);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public boolean buscarDistribucionCreada(DTODistribucionTotalesPK buscarDistribucion) throws Exception {
		return dao.buscarDistribucionCreada(buscarDistribucion);
	}
	
	
	
	/**
	 * Método que guarda la distribución total parcial en la tabla de totales parcial
	 * @param totParcial
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardarDistribucionPartidosCI(List<DTODistribucionPartidosCI> ppCI) throws Exception{
		dao.guardarDistribucionPartidosCI(ppCI);
	}
	
	
	/**
	 * Método que guarda la distribución total parcial en la tabla de totales parcial
	 * @param totParcial
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardarDistribucionTotalParcial(List<DTODistribucionTotParcial> totParcial) throws Exception{
		dao.guardarDistribucionTotalParcial(totParcial);
	}
	
	
	/**
	 * Método que guarda la distribución parcial de candidatos en tabla parcial
	 * @param totParcial
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardarDistribucionCandParcial(List<DTODistribucionCandParcial> candParcial) throws Exception{
		dao.guardarDistribucionCandParcial(candParcial);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOCSubcoaliciones> getSubcoaliciones(List<Integer> coaliciones) throws Exception{
		return dao.getSubcoaliciones(coaliciones);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public DTOCSubcoaliciones getEmblemaCoalicion(Integer coalicion) throws Exception{
		return dao.getEmblemaCoalicion(coalicion);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getCoaliciones(Object [] arg0, Integer idDistrito)  throws Exception{
		return dao.getCoaliciones(arg0, idDistrito);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Object[]>  getPartidosCoalicion(List<Integer> coaliciones) throws Exception{
		return dao.getPartidosCoalicion(coaliciones);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer>  getPartidosSinCoalicion(List<Integer> partidosConCoalicion) throws Exception{
	   return dao.getPartidosSinCoalicion(partidosConCoalicion);	
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminaDistribucionCandParcial(DTODistribucionCandParcialPK pk)
			throws Exception {
		dao.eliminaDistribucionCandParcial(pk);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminaDistribucionTotParcial(DTODistribucionTotParcialPK pk)
			throws Exception {
		dao.eliminaDistribucionTotParcial(pk);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminaDistribucionPartidosCI(DTODistribucionPartidosCIPK pk) throws Exception{
		dao.eliminaDistribucionPartidosCI(pk);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPDistribucionVotos> getTotalVotosPPCIMRyRPFinal(Object[] arg0) throws Exception{
		return dao.getTotalVotosPPCIMRyRPFinal(arg0);
	}
	
	/**
	 * Método que consulta la distribución parcial de Diputados RP a nivel distrito
	 * esto para posteriromente insertar esta informacion en la tabla de distribucion
	 * final de partidosci
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<HLPDistribucionVotos> getDistribucionParcialByDistritoRP(Object[] arg0) throws Exception{
		return dao.getDistribucionParcialByDistritoRP(arg0);
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
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<Integer> getDistribucionFinalByDistritos(Integer idProceso,
			 											  Integer idDetalleProceso,
			 											  Integer idEstado,
			 											  Integer idTipoCandidatura) throws Exception{
		return dao.getDistribucionFinalByDistritos(idProceso, idDetalleProceso, idEstado, idTipoCandidatura);
	}
	
}
