package mx.ine.acuerdos.bsd;

import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCEstatusPuntos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.HelperDTOTipoMov;
import mx.ine.acuerdos.dto.helper.form.HelperSeguimientoPunto;

/**
 * Interfaz que sirve que comunica a las capas MB y BSD de la vista de
 * Consulta y Seguimiento de Punto del lado del área reportante),
 * contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface BSDSeguimientoPuntosInterface {

	/**
     * Con base en el idNumAcuerdo seleccionado por el usuario en la vista de Bandeja de Seguimiento,
     * recupera su correspondiente DTOAcuerdos para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param idNumAcuerdo
     * @return DTOAcuerdos
     * @since 05/12/2017
    */
	public DTOAcuerdos recuperarAcuerdo(String idNumAcuerdo);

	/**
     * Con base en el idNumAcuerdo y la numeralia del punto seleccionado por el usuario en la
     * vista de Bandeja de Seguimiento, recupera su correspondiente DTOPuntosAcuerdo para su
     * posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param idNumAcuerdo
     * @param numeralia
     * @return DTOPuntosAcuerdo
     * @since 05/12/2017
    */
	public DTOPuntosAcuerdo recuperarPunto(String idNumAcuerdo, Integer numeralia);

	/**
     * Con base en el idClasificacion del punto seleccionado por el usuario en la vista de
     * Bandeja de Seguimiento, este método recupera el catálogo de los diferentes tipos de
     * clasificación (DTOCClasificaciones) que un punto puede tener con el fin de
     * almacenarlo en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param idClasificacion
     * @return DTOCClasificaciones
     * @since 05/12/2017
    */
	public DTOCClasificaciones recuperarClasificacion(Integer idClasificacion);

	/**
     * Con base en el idNumAcuerdo y la numeralia del punto seleccionado por el usuario en la
     * vista de Bandeja de Seguimiento, además de su correspondiente idArea, este método
     * recupera una lista  de todos los movimientos realizados sobre un punto
     * para un área en específico (List<DTOSeguimientosCG>). Posteriormente, dicho seguimiento
     * es almacenado en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param idNumAcuerdo
     * @param numeralia
     * @param idArea
     * @return List<DTOSeguimientosCG>
     * @since 05/12/2017
    */
	public List<DTOSeguimientosCG> recuperarSeguimientoArea(String idNumAcuerdo, Integer numeralia, Integer idArea);

	/**
     * Con base en el seguimiento del punto consultado (List<DTOSeguimientosCG>), determina un
     * listado de todas las áreas involucradas en dicho seguimiento. El resultado es almacenado
     * en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param accionesSegPunto
     * @return List<Integer>
     * @since 05/12/2017
    */
	public List<Integer> determinarAreasInvoluc(List<DTOSeguimientosCG> accionesSegPunto);

	/**
     * Con base en el nombre de usuario actual del sistema, recupera su
     * correspondiente DTOCAreas con el fin de conocer su idArea
     * @author Miguel Ortiz
     * @param nomUsuario
     * @return Integer
     * @since 05/12/2017
    */
	public Integer determinarIdAreaEnResponsables(String nomUsuario);

	/**
     * Con base en el listado de áreas involucradas en el seguimiento del punto (List<Integer>),
     * recupera una lista de DTOCAreas para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param areasInvolucradas
     * @return List<DTOCAreas>
     * @since 05/12/2017
    */
	public List<DTOCAreas> recuperarAreasInvolucPunto(List<Integer> areasInvolucradas);

	/**
     * Con base en el listado de áreas involucradas en el seguimiento del punto (List<Integer>),
     * recupera una lista de todos los responsables de área involucrados (List<DTOResponsables>)
     * para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param DTOResponsables
     * @return List<DTOCAreas>
     * @since 05/12/2017
    */
	public List<DTOResponsables> recuperarResponsInvolucPunto(List<Integer> areasInvolucradas);

	/**
     * Con base en la lista de áreas involucradas en el seguimiento del punto (List<DTOCAreas>),
     * construye un árbol de áreas asignando a cada uno de sus respectivos responsables para su
     * posterior almacenamiento en HelperSeguimientoPunto, dicho árlbol es desplegado por la vista
     * Consulta y Seguimiento de Punto
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @return TreeNode
     * @since 05/12/2017
    */
	public TreeNode construirArbolRespons(HelperSeguimientoPunto helpSegPunto);

	/**
     * Con base en el movimiento más actual del seguimiento del punto seleccionado por el
     * usuario (List<DTOSeguimientosCG>), genera un listado de movimientos válidos de acuerdo
     * al estatus actual del punto (List<HelperDTOTipoMov>) para su posterior almacenamiento
     * en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param accionesSegPunto
     * @return List<HelperDTOTipoMov>
     * @since 05/12/2017
    */
	public List<HelperDTOTipoMov> generarTiposMovimiento(List<DTOSeguimientosCG> accionesSegPunto);

	/**
     * Recupera el catálogo de los diferentes estatus de un punto (List<DTOCEstatusPuntos>)
     * para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @return List<DTOCEstatusPuntos>
     * @since 05/12/2017
    */
	public List<DTOCEstatusPuntos> recuperarEstatusPuntos();

	/**
     * Almacena en HelperSeguimientoPunto si el seguimiento del punto seleccionado permite
     * la inserción de un movimiento de finalización del punto en la vista de Consulta y
     * Seguimiento de Punto
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @return void
     * @since 05/12/2017
    */
	public void activarDesactivarCierre(HelperSeguimientoPunto helpSegPunto);

	/**
     * Con base en el idEstatusPunto proveniente del último movimiento en el seguimiento del
     * punto seleccionado, recupera la descripción del estatus sobre dicho punto con el fin
     * de ser desplegada en la vista de Consulta y Seguimiento de Punto
     * @author Miguel Ortiz
     * @param idEstatusPunto
     * @return String
     * @since 05/12/2017
    */
	public String recuperarDescEstatus(HelperSeguimientoPunto helpSegPunto, Integer idEstatusPunto);

	/**
     * Con base en el idEstatusPunto proveniente del último movimiento en el seguimiento del
     * punto seleccionado, recupera la descripción del estatus sobre dicho punto con el fin
     * de desplegar una imagen de semáforo en la vista de Consulta y Seguimiento de Punto
     * @author Miguel Ortiz
     * @param dtoPunto
     * @param ultimoMov
     * @return String
     * @since 05/12/2017
    */
	public String recuperarDescSemaforo(DTOPuntosAcuerdo dtoPunto, DTOSeguimientosCG ultimoMov);

	/**
     * Con base en el idArea del usuario actual, recupera la descripción del área
     * con el fin de ser desplegada en la vista de Consulta y Seguimiento de Punto
     * @author Miguel Ortiz
     * @param ultimoMov
     * @return String
     * @since 05/12/2017
    */
	public String recuperarDescArea(Integer idArea);

	/**
     * Con base en la información introducida por el usuario actual en la vista de Consulta
     * y Seguimiento de Punto, inserta un movimiento en el seguimiento del punto dentro de
     * la tabla SEGUIMIENTOS_CG
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @return void
     * @since 05/12/2017
    */
	public void insertarMovimiento(HelperSeguimientoPunto helpSegPunto);

	/**
     * Valida si el rol del usuario actual puede ingresar a la vista de
     * Consulta y Seguimiento de Punto, regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolUsuario(String rolUsuario);

	/**
     * Valida si el rol del usuario actual pertenece al secretariado,
     * regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolCG(String rolUsuario);
	
	/**
     * función que valida si un usuario tiene un área asignada
     * @author Giovanni Hernández Alonso
     * @param rolUsuario
     * @return boolean
     * @since 11/12/2017
    */
	public boolean bsdSegPuntos(String rolUsuario);

	/**
     * Valida si el rol del usuario actual cuenta con los permisos para capturar,
     * modificar o eliminar información, regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolCaptura(String rolUsuario);

	public List<String> recuperaImgsInfografias();

}
