/**
 * 
 */
package mx.ine.computosINE.as;

import java.math.BigDecimal;
import java.util.List;

import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTODistribucionTotalesPK;

/**
 * <code>ASCapturaVotoRPInterface.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 27/04/2017 11:27:00
 */
public interface ASCapturaVotoRPInterface {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCountVotosCapturados(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene el acumulado de votos para diputados. </BR>
	 * arg0[idProcesoElectoral, idDetalleProceso, idEstado, idMunicipio,idTipoCandidatura, tipoAsociacion, idAsociacion, idTipoCasilla]
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getVotosAcumuladosDiputados(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Obtiene una lista de casillas
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> getListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Consulta las actas o casillas capturadas
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaListActaCasillaVotos(DTOActaCasillaVotosPK id) throws Exception;

	/**
	 * Consulta las Actas capturadas por estado, agrupadas por:</BR>
	 * id_proceso_electoral, id_detalle_proceso, id_estado, id_distrito o id_regiduria, seccion, id_casilla, tipo_casilla.
	 * @param id
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]>  consultaActasCapturadasXestado(DTOActaCasillaVotosPK id, String modulo) throws Exception;

	/**
	 * Consulta la informacion de los candidatos por acta. </BR>
	 * Nota: Diputados RP y Regidurias RP
	 * @param id
	 * @param idEstatus
	 * @param modulo
	 * @return
	 * @throws Exception
	 */
	public List<DTOActaCasillaVotos> consultaCandidatosXacta(DTOActaCasillaVotosPK id, Integer idEstatus, String modulo) throws Exception;

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
	 * Registra los votos
	 * @param votos
	 * @throws Exception
	 */
	public void guardar(List<DTOActaCasillaVotos> votos)throws Exception;

	/**
	 * Elimina el acta,  siempre u cuando no tenga una distribucion generada.
	 * Nota: Por el momento esta para el negocio de Diputados RP
	 * 
	 * @param votos
	 */
	public void eliminar(List<DTOActaCasillaVotos> votos) throws Exception;
	
}
