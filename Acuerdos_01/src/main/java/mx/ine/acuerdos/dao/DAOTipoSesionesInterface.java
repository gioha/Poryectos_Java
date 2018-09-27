package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOTipoSesiones;



/**
 * <code>DAOTipoSesionesInterface.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernández
 * @version 1.0
 * @since 12/10/2017
 */
public interface DAOTipoSesionesInterface extends DAOGenericInterface<DTOTipoSesiones, Integer> {


	/**
	 * Recupera los valores de la tabla C_TIPO_SESIONES. </BR>
	 * 
	 * @author Giovanni Hernández	
	 * @param Void
	 * @return List<DTOTipoSesiones>
	 * @throws Exception
	 */
	public List<DTOTipoSesiones> consultaTipoSesiones() throws Exception;
	
	
}
