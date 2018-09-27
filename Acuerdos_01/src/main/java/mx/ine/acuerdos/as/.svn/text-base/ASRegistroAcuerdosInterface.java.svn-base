package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOTipoSesiones;


/**
 * <code>ASRegistroAcuerdosInterface.java</code>Descripcion de la clase
 *
 * @author Giovanni HernÃ¡ndez Alonso
 * @version 1.0
 * @since 12/10/2017 
 */

public interface ASRegistroAcuerdosInterface {

	
	/**
	 * Obtiene una lista de los tipos de sesiones
	 * @param void
	 * @return List<DTOTipoSesiones>
	 * @throws Exception
	 */
	public List<DTOTipoSesiones> recuperaTiposSesiones() throws Exception;

	
	/**
	 * Guarda el acuerdo
	 * @param void
	 * @return String
	 */	
	public String guardaAcuerdo(DTOAcuerdos dtoAcuerdos) ;

	
	/**
	 * Obtiene una Acuerdo
	 * @param String
	 * @return DTOAcuerdos
	 * @throws 
	 */	
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo);

	/**
	 * Eliminar seguimiento
	 * @param String
	 * @return void
	 * @throws
	 */	
	public void eliminarSeguimiento(String idAcuerdo);

	/**
	 * Eliminar puntos
	 * @param String
	 * @return void
	 * @throws
	 */	
	public void eliminarPuntosAc(String idAcuerdo);

	/**
	 * Obtiene una lista de los tipos de docuemntos
	 * @param void
	 * @return List<DTOCTipoDocumento>
	 * @throws Exception
	 */
	public List<DTOCTipoDocumento> recuperaTiposDocumentos();
	
	
}
