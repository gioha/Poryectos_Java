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
 * Consulta y Seguimiento de Punto del lado del secretariado),
 * contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface BSDSeguimientoPuntosCGInterface {

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
     * Con base en el punto seleccionado por el usuario en la vista de Bandeja de
     * Seguimiento (dtoPunto), este método recupera una lista de todos los movimientos
     * realizados sobre dicho punto en todas las áreas involucradas (List<DTOSeguimientosCG>).
     * Posteriormente, el seguimiento es almacenado en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param dtoPunto
     * @return List<DTOSeguimientosCG>
     * @since 05/12/2017
    */
	public List<DTOSeguimientosCG> recuperarSeguimiento(DTOPuntosAcuerdo dtoPunto);

	/**
     * Con base en el idNumAcuerdo y la numeralia del punto seleccionado por el usuario en la
     * vista de Bandeja de Seguimiento, además de un valor idArea para un área en particular,
     * este método recupera una lista  de todos los movimientos realizados sobre un punto
     * para un área en específico (List<DTOSeguimientosCG>). Posteriormente, dicho
     * seguimiento es almacenado en HelperSeguimientoPunto
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
     * Con base en el listado de áreas involucradas en el seguimiento del punto (List<Integer>),
     * recupera una lista de DTOCAreas para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param areasInvolucradas
     * @return List<DTOCAreas>
     * @since 05/12/2017
    */
	public List<DTOCAreas> recuperarAreasInvolucPunto(List<Integer> areasInvolucradas);

	/**
     * Con base en el seguimiento del punto consultado (List<DTOSeguimientosCG>), determina un
     * listado de todas las responsabilidades conjuntas en dicho seguimiento. El resultado es
     * almacenado en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @param accionesSegPunto
     * @return List<Integer>
     * @since 05/12/2017
    */
	public List<Integer> determinarResponsConjInvoluc(List<DTOSeguimientosCG> accionesSegPunto);

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
     * Con base en el área seleccionada por el usuario en la vista de Consulta y Seguimiento de Punto,
     * determina el listado de movimientos para el punto dentro de dicha área hasta el momento
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @return List<DTOSeguimientosCG>
     * @since 05/12/2017
    */
	public List<DTOSeguimientosCG> determinarSegActualPorArea(HelperSeguimientoPunto helpSegPunto);

	/**
     * Recupera el catálogo de los diferentes estatus de un punto (List<DTOCEstatusPuntos>)
     * para su posterior almacenamiento en HelperSeguimientoPunto
     * @author Miguel Ortiz
     * @return List<DTOCEstatusPuntos>
     * @since 05/12/2017
    */
	public List<DTOCEstatusPuntos> recuperarEstatusPuntos();

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
     * y Seguimiento de Punto para un movimiento de reasignación de un punto del lado del
     * secretariado, inserta un movimiento en el seguimiento del punto dentro de la tabla
     * SEGUIMIENTOS_CG
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param movModificable
     * @return void
     * @since 05/12/2017
    */
	public void insertarMovReasignacion(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable);

	/**
     * Una vez que el usuario actual confirma el rechazo de un punto por parte de un área
     * reportante en la vista de Consulta y Seguimiento de Punto del lado del secretariado,
     * este método elimina el seguimiento de dicho punto de la tabla SEGUIMIENTOS_CG
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param movEliminable
     * @return void
     * @since 05/12/2017
    */
	public void eliminarSegPunto(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movEliminable);

	/**
     * Una vez que el usuario actual confirma el cierre de un punto por parte de un área
     * reportante en la vista de Consulta y Seguimiento de Punto del lado del secretariado,
     * inserta un movimiento en el seguimiento del punto dentro de la tabla SEGUIMIENTOS_CG
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param movModificable
     * @return void
     * @since 05/12/2017
    */
	public void insertarMovAceptacion(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable);

	/**
     * Con base en la información introducida por el usuario actual en la vista de Consulta
     * y Seguimiento de Punto para un movimiento de rechazo de cierre del punto (lado del
     * secretariado), inserta un movimiento en el seguimiento del punto dentro de la tabla
     * SEGUIMIENTOS_CG
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param movModificable
     * @return void
     * @since 05/12/2017
    */
	public void insertarMovRechazo(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG movModificable);

	/**
     * Actualiza el estatus global del punto de acuedo a la siguiente especificación:
     * si todas las áreas involucradas en dicho punto ya han concluido su participación,
     * el punto es inhabilitado y ya no es mostrado en la vista de Bandeja de Seguimiento
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param ultimoMov
     * @return void
     * @since 05/12/2017
    */
	public void actualizarEstatusPunto(HelperSeguimientoPunto helpSegPunto, DTOSeguimientosCG ultimoMov);

	/**
     * Actualiza el estatus global del acuerdo de acuedo a la siguiente especificación:
     * si todos los puntos involucrados en dicho acuerdo ya han sido cumplidos,
     * el acuerdo es inhabilitado y ya no es mostrado en la vista de Bandeja de Seguimiento
     * @author Miguel Ortiz
     * @param helpSegPunto
     * @param idNumAcuerdo
     * @return void
     * @since 05/12/2017
    */
	public void actualizarEstatusAcuerdo(HelperSeguimientoPunto helpSegPunto, String idNumAcuerdo);

	/**
     * Valida si el rol del usuario actual cuenta con los permisos para capturar,
     * modificar o eliminar información, regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolCaptura(String rolUsuario);

	public DTOResponsables recuperarDtoResponsable(String nomUsuario);
	public boolean recuperarVistaCompartida(HelperSeguimientoPunto helpSegPunto, String rolUsuario);
	public List<HelperDTOTipoMov> generarTiposMovimiento(List<DTOSeguimientosCG> accionesSegPorArea);
	public void insertarMovimiento(HelperSeguimientoPunto helpSegPunto);

	public List<String> recuperaImgsInfografias();

}
