/**
 * 
 */
package mx.ine.computosINE.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPActaCasillaVotos;

/**
 * <code>DAOActaCasillaVotosInterface.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 27/04/2017 11:27:00
 */
public interface DAOActaCasillaVotosInterface extends DAOGenericInterface<DTOActaCasillaVotos, Integer> {

	/**
	 * Obtiene el conteo del numero de actas capturadas. </BR>
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountVotosCapturados(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene el acumulado de votos para diputados. </BR>
	 * arg0[idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio,idTipoCandidatura, tipoAsociacion, idAsociacion, idTipoCasilla]</BR>
	 * Nota 1: Diputados RP y Regidurias RP </BR>
	 * Nota 2: La acumulacion de votos para Diputados RP se realiza a nivel de Distrito y la de Regidurias RP a nivel de Regiduria. </BR>
	 * 
	 * @author Clemencia Cuellar
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getVotosAcumuladosDiputados(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene una lista de casillas. </BR>
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Consulta las actas o casillas capturadas
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Consulta las Actas capturadas por estado, agrupadas por:</BR>
	 * id_proceso_electoral, id_detalle_proceso, id_estado, id_distrito, seccion, id_casilla, tipo_casilla.</BR>
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]>  consultaActasCapturadasXestado(DTOActaCasillaVotosPK id, String modulo) throws Exception;

	/**
	 * Consulta la informacion de los candidatos por acta. </BR>
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @param idEstatus
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaCandidatosXacta(DTOActaCasillaVotosPK id, Integer idEstatus, String modulo) throws Exception;

	/**
	 * Verifica si existe generacion de acta. </BR>
	 * true		: Si ya existe generacion de acta </BR>
	 * false	: No existe generacion de acta 
	 * 
	 * @author Clemencia Cuellar
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean existeGeneracionActa(DTOActaTipoCandidaturaPK id) throws Exception;

	/**
	 * Registra el acta.
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param votos
	 */
	public void saveOrUpdate(List<DTOActaCasillaVotos> votos) throws Exception;

	/**
	 * Elimina el acta,  siempre u cuando no tenga una distribucion generada.
	 * Nota: Diputados RP y Regidurias RP
	 * 
	 * @author Clemencia Cuellar
	 * @param votos
	 */
	public void eliminar(List<DTOActaCasillaVotos> votos) throws Exception;

	/**
	 * Consulta las actas capturadas por entidad
	 */
	public List<DTOActaCasillaVotos> getActasEntidad(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene el numero de actas capturadas por el idTipoCandidatura
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountActasCapturadasByCandidatura(Object [] arg0) throws Exception;
	
	
	
	/**
	 * Obtiene el numero de actas capturadas por demarcacion
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountActasCapturadasByDemarcacion(Object[] arg0) throws Exception;
	
	/**
	 * Obtiene el numero de actas capturadas por el tipo de Candidatura y por municipio
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getActasCapturadasxMunicipioCandidatura(Object [] arg0) throws Exception;

	/**
	 * Obtiene el numero de municipios que ya tienen actas capturadas
	 * por el idTipoCandidatura
	 * @param idProceso
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return Integer
	 * 
	 */
	public Integer getTotalMunicipiosActasCapturadas(Integer idProceso, Integer idDetalleProceso, Integer idEstado, Integer idTipoCandidatura) throws Exception;

	/**
	 * Consulta la informacion de los municipios y distritos que ya tuvieron actas capturadas
	 * @param idProceso
	 * @param idDetalleProceso
	 * @param idEstado
	 * @param idTipoCandidatura
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> consultaActasCaptByMunDistrito(Integer idProceso, Integer idDetalleProceso, Integer idEstado, Integer idTipoCandidatura) throws Exception;

   /**
    * Obtiene el acumulado de votos nulos y candidatos no registrados por tipo de candidatura. </BR>
	* arg0[idProcesoElectoral, idDetalleProceso, idEstado, idDistrito,
	* idMunicipio, idRegiduria, idTipoCandidatura, tipoAsociacion, idAsociacion]
	* donde idAsociacion = 1 y idTipoAsociacion = 1 pertenece a Candidatos no registrados
	* donde idAsociacion = 2 y idTipoAsociacion = 2 pertenece a Votos Nulos
    * @param id
    * @return
    * @throws Exception
    */
	public BigDecimal getTotalVotosNulosCNR(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene una lista de casillas ordenadas por secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	public List<DTOActaCasillaVotos> getActasMunicipioEnSeccionesParaDistribucion(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene una lista de casillas ordenadas por Distritos y Secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnDistritosSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene las actas capturas con condistribucion parcial por distrito
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer getActasParcialesCapturadasEnDistrito(DTODistribucionCandParcialPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas capturas en entidad
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasCapturadasxEntidadCandidatura(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas capturas en distrito
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasCapturadasEnDistrito(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene una lista de casillas capturadas en una Regiduría
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegidurias(DTOActaCasillaVotosPK id) throws Exception;
	
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasParaDistribucion(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene una lista de casillas ordenadas por Regidurias y Secciones
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasSecciones(DTOActaCasillaVotosPK id) throws Exception;
	
	/**
	 * Obtiene los votos de las asociaciones de un acta
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getvotosAsociacionesActa(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene la suma de votos por cada partido 
	 * este metodo se tiene contemplado solo para el tipo de candidatura
	 * Diputados RP y Diputados MR, porque es anivel distrito
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPActaCasillaVotos> getTotalVotosActaDiputados(Object [] arg0) throws Exception ;
	
	
	/**
	 * Obtiene la suma de votos por regiduria rp, es decir solo se contemplan casillas especiales,
	 * esto aplica solo para cuando es un acta parcial
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRPParcial(Object[] arg0)throws Exception;
	
	
	/**
	 * Obtiene la suma de votos por regiduria mr, esto aplica cuando es un acta final de regiduria rp
	 * aunque no se tengan casillas especiales
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaMR(Object[] arg0) throws Exception;
	
	
	/**
	 * Obtiene la suma de votos por regiduria rp, es decir solo se contemplan casillas especiales,
	 * esto aplica cuando es un acta final de regiduria rp a nivel ople
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosRegiduriaRP(Object[] arg0) throws Exception;
	
	
	/**
	 * Obtiene la suma de votos por cada partido 
	 * este metodo se tiene contemplado solo para el tipo de candidatura
	 * Gobernador , porque es a nivel municipio
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<HLPActaCasillaVotos> getTotalVotosActaGobernador(Object [] arg0) throws Exception ;

	
	/**
	 * Obtiene la suma de votos por regiduria mr, esto aplica cuando es un acta final de diputados rp
	 * aunque no se tengan casillas especiales
	 * @param arg0
	 * @return
	 */
	public List<HLPActaCasillaVotos> getTotalVotosDiputadosMR(Object[] arg0) throws Exception;
	
	
	/**
	 * Obtiene la suma de votos por los diferentes tipos de MR
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getVotosActaDiputadosMR(Object [] arg0) throws Exception;
	
	/**
	 * Recupera todos los registros que representan un acta capturada.
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaActa (DTOActaCasillaVotosPK id)	throws Exception;

	 /**
	  * Nos dice si las casillas tienen el estatus de recuento parcial
	  * @param idTipoCandidatura
	  * @return
	  * @throws Exception
	  */
	 public boolean esRecuentoParcial(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception;

	 /**
	  * Nos dice si las casillas tienen el estatus de recuento total
	  * @param idTipoCandidatura
	  * @return
	  * @throws Exception
	  */
	 public boolean esRecuentoTotal(Integer idTipoCandidatura, DTOUsuarioLogin usuario, Integer distrito, Integer demarcacion) throws Exception;

}
