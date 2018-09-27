package mx.ine.acuerdos.dao;

import java.util.List;

import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

/**
 * <code>DAOSeguimientosCGInterface.java</code>* Interface de la capa de dao correspondiente a la entidad de SeguimientosCG
 * 
 * @author Sampier Cuevas
 * @version 1.0
 * @since 06/11/2017
 */
public interface DAOSeguimientosCGInterface extends DAOGenericInterface<DTOSeguimientosCG, Integer>{
	
	/**
	 * Consulta el seguimiento de un punto en la tabla SEGUIMIENTOS_CG.
	 * 
	 * @author Sampier Cuevas
	 * @version 1.0
	 * @since 07/11/2017
	 */
	public List<DTOSeguimientosCG> consultaSeguimiento(DTOPuntosAcuerdo punto) throws Exception;
	public List<DTOSeguimientosCG> obtenerSeguimientoPorArea(String idNumAcuerdo, Integer numeralia, Integer idArea) throws Exception;
	public DTOSeguimientosCG obtenerSeguimientoPorUsuario(String idNumAcuerdo, Integer numeralia, String nomUsuario) throws Exception;
	public void insertarMovimiento(DTOSeguimientosCG seguimiento);
	
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public List<DTOSeguimientosCG> obtenerSeguimiento(String idAcuerdo, Integer numeralia, Integer idArea) throws Exception ;

	public void eliminarSeguimientoArea(DTOSeguimientosCG movSeguimiento) throws Exception;
	
	public void modificaSeguimientoAreaGrupo(String idAcuerdo, Integer numeralia, Integer idArea, Integer grupo)throws Exception;

	public List<DTOSeguimientosCG> obtenerRespConjunta(String idNumAcuerdo, Integer numeralia, Integer respConj) throws Exception;

}
