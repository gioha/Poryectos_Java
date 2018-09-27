package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.admin.DTOMenu;


/**
 * <code>DAOMenuAdminInterface.java</code>* Interface de la capa de dao correspondiente la consulta del menu de un sistema con las bases de admin replicadas en la base local del sistema.
 * 
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 08/12/2017
 */

public interface DAOMenuAdminInterface {

	/**
	 * Recupera los registros de la tabla ACUERDOS. </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param Integer idSistema
	 * @param String rolUsuario
	 * @return List<DTOMenu>
	 * @throws Exception
	 */
	public List<DTOMenu> obtenMenuSistema(Integer idSistema, String rolUsuario);
	
}
