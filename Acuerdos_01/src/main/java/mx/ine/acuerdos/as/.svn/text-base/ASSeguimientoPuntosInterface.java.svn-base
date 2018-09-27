package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;

/**
 * Interfaz que sirve que comunica a las capas BSD y AS de la vista de
 * Consulta y Seguimiento de Punto para ambos lados (secretariado y área reportante),
 * contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface ASSeguimientoPuntosInterface {

	public DTOAcuerdos recuperarAcuerdo(String idNumAcuerdo) throws Exception;
	public DTOPuntosAcuerdo recuperarPunto(String idNumAcuerdo, Integer numeralia) throws Exception;
	public DTOCClasificaciones recuperarClasificacion(Integer idClasificacion) throws Exception;
	public List<DTOSeguimientosCG> recuperarSeguimiento(DTOPuntosAcuerdo dtoPunto) throws Exception;
	public List<DTOSeguimientosCG> recuperarSeguimientoArea(String idNumAcuerdo, Integer numeralia, Integer idArea) throws Exception;
	public DTOResponsables recuperarAreaUsuarioResponsable(String nomUsuario) throws Exception;
	public DTOCAreas recuperarAreaInvolucrada(Integer idArea) throws Exception;
	public List<DTOResponsables> recuperarResponsInvolucrado(Integer idArea) throws Exception;
	public DTOCAreas recuperarArea(Integer idArea) throws Exception;
	public List<DTOSeguimientosCG> recuperarRespConj( String idNumAcuerdo, Integer numeralia, Integer respConj ) throws Exception;
	public void insertarMovimiento(DTOSeguimientosCG dtoSegCG);
	public DTOPuntosAcuerdo recuperarPuntoAcuerdo(String idNumAcuerdos, Integer numeralia) throws Exception;
	public List<DTOPuntosAcuerdo> recuperarPuntosAcuerdo(String idNumAcuerdo) throws Exception;
	public void actualizarPuntoAcuerdo(DTOPuntosAcuerdo puntoPorActualizar) throws Exception;
	public void actualizarAcuerdo(DTOAcuerdos acuerdo) throws Exception;
	public void eliminarSegPunto(DTOSeguimientosCG movSeguimiento) throws Exception;
	public List<DTOCEstatusPuntos> recuperarEstatusPuntos() throws Exception;

	public DTOResponsables recuperarDtoResponsable(String nomUsuario) throws Exception;

}
