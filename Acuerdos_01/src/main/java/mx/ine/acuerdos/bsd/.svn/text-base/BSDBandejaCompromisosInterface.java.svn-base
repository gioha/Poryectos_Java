package mx.ine.acuerdos.bsd;

import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;

/**
 * Interface  que contiene la firma de los métodos que acceden a el o los AS para la gestion de la Bandeja de Compromisos
 *
 * @author Sampier Cuevas Flores
 * @since 13/10/2017
 */

public interface BSDBandejaCompromisosInterface extends Serializable{
	/**
     * Recupera todos los Acuerdos, Resoluciones y Dictámenes (idTipoDocumento) para el usuario logeado
     * @author Sampier Cuevas
     * @update Miguel Ortiz
     * @param DTOUsuarioLogin
     * @param idTipoDocumento
	 * @param idNegocio
     * @return List<DTOAcuerdos>
     * @since 22/02/2018
     **/
	public List<DTOAcuerdos> recuperaAcuerdos(DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio);

	/**
     * recupera todos los puntos (tipo) para el usuario logeado 
     * @author Sampier Cuevas
     * @param DTOUsuarioLogin, boolean
     * @return List<DTOPuntosAcuerdo>
     * @since 13/10/2017*/
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo,DTOUsuarioLogin usuario);
	/**
     * hace una eliminacion logica del acuerdo proporcionado
     * @author Sampier Cuevas
     * @param DTOAcuerdos 
     * @return void
     * @since 13/10/2017*/
	public void eliminacionLogicaAcuerdo(DTOAcuerdos acuerdo);
	/**
     * hace una eliminacion logica de los puntos proporcionados
     * @author Sampier Cuevas
     * @param DTOAcuerdos, List<DTOPuntosAcuerdo> 
     * @return void
     * @since 13/10/2017*/
	public void eliminacionLogicaPuntos(DTOAcuerdos acuerdo, List<DTOPuntosAcuerdo> puntos);
	/**
     * hace una eliminacion logica del punto proporcionado
     * @author Sampier Cuevas
     * @param DTOPuntosAcuerdo, DTOAcuerdos 
     * @return void
     * @since 13/10/2017*/
	public void eliminacionLogicaPunto(DTOPuntosAcuerdo punto, DTOAcuerdos acuerdo);
	/**
     * consulta acuerdo seleccionado para mostrar el detalle
     * @author Sampier Cuevas
     * @param String idAcuerdo 
     * @return List<DTOAcuerdos>
     * @since 13/10/2017*/
	public List<DTOAcuerdos> consultaAcuerdo(String idAcuerdo);

	/**
     * Método para la busqueda autocomplete de documento
     * @author Sampier Cuevas
     * @update Miguel Ortiz
	 * @param idAcuerdo, usuario, idTipoDocumento, idNegocio
     * @return List<DTOAcuerdos>
     * @since 23/02/2018
     **/
	public List<DTOAcuerdos> busquedaAcuerdo(String idAcuerdo, DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio);

	/**
     * Método para la busqueda por fecha
     * @author Sampier Cuevas
     * @update Miguel Ortiz
     * @param fechaSesion, usuario, idTipoDocumento, idNegocio
     * @return List<DTOAcuerdos>
     * @since 23/02/2018
     **/
	public List<DTOAcuerdos> busquedaAcuerdoPorFecha(String fechaSesion, DTOUsuarioLogin usuario, Integer idTipoDocumento, Integer idNegocio);

	/**
     * metodo para saber si un punto esta en seguimiento y alertar por si se va a eliminar
     * @author Sampier Cuevas
     * @param fechaSesion, usuario, tipoDocumento
     * @return List<DTOSeguimientosCG>
     * @since 13/10/2017*/
	public List<DTOSeguimientosCG> consultaPuntoEnSeguimiento(DTOPuntosAcuerdo punto);
	
}
