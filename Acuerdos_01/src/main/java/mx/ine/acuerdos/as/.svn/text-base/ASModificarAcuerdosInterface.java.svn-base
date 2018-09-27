package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOTipoSesiones;

/**
 * <code>ASModificarAcuerdosInterface.java</code> Descripcion de la clase
 *
 * @author José Hurtado
 * @version 1.0
 * @since 30/10/2017 
 */
public interface  ASModificarAcuerdosInterface {
	
	/**
	 * Obtiene una lista de los tipos de sesiones
	 * @param void
	 * @return List<DTOTipoSesiones>
	 * @throws Exception
	 */
	public List<DTOTipoSesiones> recuperaTiposSesiones() throws Exception;
	
	
	/**
	 * Obtiene Acuerdo
	 * @param String
	 * @return DTOAcuerdos
	 * @throws 
	 */	
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) ;

	/**
	 * Modifica Acuerdo
	 * @param DTOAcuerdos
	 * @return String
	 * @throws 
	 */
	public String actualizarAcuerdo(DTOAcuerdos acuerdo);

	/**
	 * Recupera acuerdo o Resolucion
	 * @param String
	 * @return String
	 * @throws 
	 */
	public String recuperaTipoDocumentoS(Integer idDoc);
	
	/**
	 * Recupera los puntos del acuero
	 * @param String
	 * @return List<DTOPuntosAcuerdo>
	 * @throws 
	 */
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo) throws Exception;
	
	/**
	 * Recupera los responsables del area
	 * @param Integer
	 * @return List<DTOResponsables>
	 * @throws 
	 */
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception;
	
	/**
	 * Recupera los responsables del punto
	 * @param String, Integer
	 * @return List<DTOSeguimientosCG>
	 * @throws 
	 */
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception;

}
