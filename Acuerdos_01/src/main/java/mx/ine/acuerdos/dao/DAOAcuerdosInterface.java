package mx.ine.acuerdos.dao;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;

	/**
	 * <code>DAOAcuerdosInterface.java</code>* Interface de la capa de dao correspondiente a la entidad de Acuerdos.
	 * 
	 * @author Sampier Cuevas
	 * @version 1.0
	 * @since 13/10/2017
	 */
	public interface DAOAcuerdosInterface extends DAOGenericInterface<DTOAcuerdos, Integer>{
	
	/**
	 * Recupera los registros de la tabla ACUERDOS.
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz	
	 * @param idNegocio 
	 * @param idTipoDocumento
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 * @since 22/02/2018
	 */
	public List<DTOAcuerdos> consultaAcuerdos(Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Registra Acuerdo.
	 * 
	 * @author José Hurtado
	 * @param void
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 */
	public String registrarAcuerdo( DTOAcuerdos acuerdo);
	
	/**
	 * Elimina logicamente un Acuerdo. </BR>
	 * 
	 * @author Sampier Cuevas	
	 * @param Void
	 * @return 
	 * @throws Exception
	 */
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo) throws Exception;

	/**
	 * Busca los acuerdos donde el id contenga algun fragmento de la búsqueda
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz
	 * @param idAcuerdo, idTipoDocumento, idNegocio
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 * @since 23/02/2018
	 **/
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Retorna una lista de acuerdos por área que coincidan con la cadena idAcuerdo
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz
	 * @param idAcuerdo, idArea, idTipoDocumento, idNegocio
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 * @since 23/02/2018
	 **/
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Retorna el acuerdo sugún la fecha de la sesión
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz
	 * @param fechaSesion, idTipoDocumento, idNegocio
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 * @since 23/02/2018
	 */
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Retorna una lista de acuerdos por área que coincidan con la fecha de sesión y el área
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz
	 * @param fechaSesion, idArea, idTipoDocumento, idNegocio
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 * @since 23/02/2018
	 */
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Elimina seguimiento de acuerdos
	 * 
	 * @author José Hurtado	
	 * @param String
	 * @return void
	 *  */
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo) throws Exception;

	/**
	 * obetencion de Acuerdo </BR>
	 * 
	 * @author José Hurtado	
	 * @param String IdAuerdo
	 * @return DTOAcuerdos
	 * @throws Exception
	 */
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo)  throws Exception;

	/**
	 * Modificar Acuerdo </BR>
	 * 
	 * @author José Hurtado	
	 * @param Auerdo
	 * @return void
	 * @throws Exception
	 */	
	public String guardaroActualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception;

	public Date obtenerFechaSesion(String idAcuerdo);
	
	/**
	 * Busca los acuerdos por fecha</BR>
	 * 
	 * @author Sampier Cuevas	
	 * @param Void
	 * @return List<DTOAcuerdos>
	 * @throws Exception
	 *  */
	public void eliminarSeguimiento(String idAcuerdo);

	/**
	 * Elimina puntos de acuerdos
	 * 
	 * @author José Hurtado	
	 * @param String
	 * @return void
	 *  */
	public void eliminarPuntosAc(String idAcuerdo);
	
	/**
	 * Retorna una lista de acuerdos por area que se encuentren en seguimiento
	 * @author Sampier Cuevas
	 * @update Miguel Ortiz
	 * @param idArea
	 * @param idTipoDocumento
	 * @param idNegocio
	 * @return List<DTOAcuerdos>
	 * @since 22/02/2018
	 */
	public List<DTOAcuerdos> consultaAcuerdosPorArea(Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;

	/**
	 * Retorna una lista de resoluciones activas 
	 * 
	 * @author Sampier Cuevas
	 * @version 1.0
	 * @since 122/11/2017
	 */
	public List<DTOAcuerdos> consultaResoluciones() throws Exception;

	public void actualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception;

}
