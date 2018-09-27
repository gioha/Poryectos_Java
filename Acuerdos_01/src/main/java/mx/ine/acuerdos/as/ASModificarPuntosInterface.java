package mx.ine.acuerdos.as;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;


public interface ASModificarPuntosInterface {
	/**
	 * Obtiene una lista de los tipos de sesiones
	 * @param void
	 * @return List<DTOTipoSesiones>
	 * @throws Exception
	 */
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception;
	
	public void actualizarPunto(DTOPuntosAcuerdo punto)  throws Exception;
	
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public Date obtenerFechaSesion(String idAcuerdo);
	
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public List<DTOCAreas> obtenerTodasLasAreas() throws Exception;
	
	public DTOCAreas obtenerArea(Integer idArea) throws Exception;
	
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception;
	
	public void guardaroActualizarSeguimiento(DTOSeguimientosCG seguimiento ) throws Exception;
	
	public void eliminarSeguimiento(DTOSeguimientosCG seguimiento ) throws Exception;
	
	public List<DTOSeguimientosCG> obtenerSeguimiento(String idAcuerdo, Integer numeralia, Integer idArea)throws Exception;
	
	public List<DTOCClasificaciones> obtenerSiPPN() throws Exception;
	
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) throws Exception;
	
	public void modificaSeguimientoAreaGrupo(String idAcuerdo, Integer numeralia, Integer idArea, Integer grupo) throws Exception;
}
