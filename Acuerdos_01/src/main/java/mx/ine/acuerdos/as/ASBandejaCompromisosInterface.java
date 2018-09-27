package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

/**
 * <code>ASBandejaCompromisosInterface.java</code>Interface que contiene la firma de los métodos que acceden a el o los DAO Acuerdos
 *
 * @author Sampie Cuevas Flores
 * @version 1.0
 * @since 13/10/2017 
 */

public interface ASBandejaCompromisosInterface {

	public List<DTOAcuerdos> recuperaAcuerdos(Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo) throws Exception;
	
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo) throws Exception;
	
	public void eliminacionLogicaPuntos(List<DTOPuntosAcuerdo> puntos) throws Exception;
	
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto) throws Exception;
	
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo) throws Exception;
	
	public List<DTOSeguimientosCG> consultaPuntoEnSeguimiento(DTOPuntosAcuerdo punto) throws Exception;
	
	public List<DTOAcuerdos> consultaAcuerdosPorArea(Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public DTOResponsables obtenerResponsableSegunLdap(String usuario) throws Exception;
	
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, Integer idArea, Integer idTipoDocumento, Integer idNegocio) throws Exception;
	
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo, Integer idArea) throws Exception;
	
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception;
	
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception;
	
	public void eliminarSeguimientoArea(DTOSeguimientosCG seguimiento)throws Exception;
}

