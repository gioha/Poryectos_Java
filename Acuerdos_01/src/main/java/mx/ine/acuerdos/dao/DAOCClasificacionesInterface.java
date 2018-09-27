package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;

/**
 * <code>DAOTipoSesionesInterface.java</code>Descripcion de la clase
 *
 * @author Jair López
 * @version 1.0
 * @since 12/10/2017
 */
public interface DAOCClasificacionesInterface extends DAOGenericInterface<DTOCClasificaciones, Integer> {

	/**
	 * Recupera los valores de la tabla C_CLASIFICACIONES. </BR>
	 * 
	 * @author Jair López
	 * @param Void
	 * @return List<DTOCClasificaciones>
	 * @throws Exception
	 */
	public List<DTOCClasificaciones> consultaClasificaiones() throws Exception;

	public DTOCClasificaciones obtenerClasificacion(Integer idClasificacion) throws Exception;
	
	public List<DTOCClasificaciones> obtenerSiPPN() throws Exception;

	public String actualizarClasif(DTOCClasificaciones dtoClasif);

	public String eliminarClasif(DTOCClasificaciones dtoClasif);
	
}
