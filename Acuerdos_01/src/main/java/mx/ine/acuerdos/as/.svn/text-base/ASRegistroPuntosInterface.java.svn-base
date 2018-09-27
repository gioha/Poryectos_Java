package mx.ine.acuerdos.as;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;


public interface ASRegistroPuntosInterface {
	/**
	 * Obtiene una lista de los tipos de sesiones
	 * @param void
	 * @return List<DTOTipoSesiones>
	 * @throws Exception
	 */
	public List<DTOCClasificaciones> recuperaClasificaciones() throws Exception;
	
	public void guardarPunto(DTOPuntosAcuerdo punto) throws Exception;
	
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public Date obtenerFechaSesion(String idAcuerdo);
	
	public List<DTOCAreas> obtenerTodasLasAreas() throws Exception;
	
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception;
	
	public void guardaroActualizarSeguimiento(DTOSeguimientosCG seguimiento ) throws Exception;	
	
	public List<DTOCClasificaciones> obtenerSiPPN() throws Exception;
	
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) throws Exception;
	
}
