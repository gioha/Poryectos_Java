package mx.ine.acuerdos.bsd;

import java.util.List;

import org.primefaces.event.ReorderEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;

/**
 * Interfaz que sirve que comunica a las capas MB y BSD de la vista de Convocatoria,
 * contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface BSDConvocatoriaInterface {

	/**
     * Valida si el rol del usuario actual puede ingresar a la vista de Convocatoria,
     * regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolUsuario(String rolUsuario);
	
	/**
     * Valida si el rol del usuario actual puede ingresar a la vista de Convocatoria de captura o de consulta,
     * regresa false si es consulta
     * @author Giovanni Hernández
     * @param rolUsuario
     * @return boolean
     * @since 10/12/2017
    */
	public boolean validarRolCapConsultaUsuario(String rolUsuario);
	
	/**
     * Valida si el rol del usuario actual pertenece a una comisión,
     * regresa false si se trata de un rol inválido
     * @author Miguel Ortiz
     * @param rolUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarRolComision(String rolUsuario);

	/**
     * Con base en el registro más actual de la tabla de CONVOCATORIAS,
     * determina el consecutivo del número de sesión de la última convocatoria,
     * regresa un número de sesión válido
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return Integer
     * @since 05/12/2017
    */
	public Integer determinarNumSesionSig(HelperConvocatoria helpConvocatoria);

	/**
     * Con base en el nombre de usuario actual del sistema, recupera su correspondiente
     * DTOResponsables para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @param nomUsuario
     * @return DTOResponsables
     * @since 05/12/2017
    */
	public DTOResponsables recuperarDtoResponsable(String nomUsuario);

	/**
     * Con base en el idResponsable del usuario actual del sistema, recupera su correspondiente
     * DTOIntegrantesComision para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @param idResponsable
     * @return DTOIntegrantesComision
     * @since 05/12/2017
    */
	public DTOIntegrantesComision recuperarDtoIntegComision(Integer idResponsable);

	/**
     * Con base en el idComision del usuario actual del sistema, recupera su correspondiente
     * DTOComisiones (información asociada a su comisión) para su posterior almacenamiento
     * en HelperConvocatoria
     * @author Miguel Ortiz
     * @param idComision
     * @return DTOComisiones
     * @since 05/12/2017
    */
	public DTOComisiones recuperarComision(Integer idComision);

	/**
     * Con base en el idComision del usuario actual del sistema, recupera una lista de todos los
     * integrantes de dicha comisión (List<DTOIntegrantesComision>) para su posterior almacenamiento
     * en HelperConvocatoria
     * @author Miguel Ortiz
     * @param idComision
     * @return List<DTOIntegrantesComision>
     * @since 05/12/2017
    */
	public List<DTOIntegrantesComision> recuperarIntegComision(Integer idComision);

	/**
     * Recupera el catálogo de los diferentes tipos de integrantes de una comisión
     * (List<DTOCTipoIntegComision>) para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @return List<DTOCTipoIntegComision>
     * @since 05/12/2017
    */
	public List<DTOCTipoIntegComision> recuperarTipoIntegComision();

	/**
     * Con base en la lista de todos los integrantes de la comisión, recupera una lista de los
     * DTOResponsables asociados a cada uno de dichos integrantes para su posterior
     * almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @param integComision
     * @return List<DTOResponsables>
     * @since 05/12/2017
    */
	public List<DTOResponsables> recuperarResponsComision(List<DTOIntegrantesComision> integComision);

	/**
     * Con base en la lista de comisiones involucradas en la convocatoria, construye un árbol de
     * comisiones asignando a cada uno de sus respectivos integrantes para su posterior almacenamiento
     * en HelperConvocatoria, dicho árlbol es desplegado por la vista de Convocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return TreeNode
     * @since 05/12/2017
    */
	public TreeNode construirArbolComisiones(HelperConvocatoria helpConvocatoria);

	/**
     * Recupera el catálogo de los diferentes tipos de sesiones (List<DTOTipoSesiones>)
     * para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @return List<DTOTipoSesiones>
     * @since 05/12/2017
    */
	public List<DTOTipoSesiones> recuperarTiposDeSesiones();

	/**
     * Con base en el rol del usuario actual, comprueba la asignación correcta del
     * número de sesión y el carácter según sea el caso (secretariado o integrante de
     * comisión) para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param rolUsuario
     * @return void
     * @since 05/12/2017
    */
	public void comprobarCamposSegunRol(HelperConvocatoria helpConvocatoria, String rolUsuario);

	/**
     * Con base en el número de sesión introducida por el usuario, valida si dicho número
     * es válido, debe ser mayor al último número de sesión registrado. El resultado es
     * almacenado en HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void validarNumSesion(HelperConvocatoria helpConvocatoria);

	/**
     * Almacena en HelperConvocatoria si la convocatoria será compuesta por una comisión
     * conjunta, dicha información es ingresada por el usuario en la vista de Convocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param comisionConjunta
     * @return void
     * @since 05/12/2017
    */
	public void actDesactComConjunta(HelperConvocatoria helpConvocatoria, boolean comisionConjunta);

	/**
     * Con base en el idComision del usuario actual, recupera una lista de todas las comisiones
     * disponibles (excepto la del usuario actual) luego de conocer que la convocatoria será
     * conjunta y la almacena en HelperConvocatoria
     * @author Miguel Ortiz
     * @param idComision
     * @return List<DTOComisiones>
     * @since 05/12/2017
    */
	public List<DTOComisiones> recuperarComisionesFiltradas(Integer idComision);

	/**
     * Una vez que el usuario selecciona alguna comisión adjunta en la vista de Convocatoria,
     * este método se encarga de almacenar dicha comisión y sus respectivos integrantes dentro
     * de HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param dtoComision
     * @return void
     * @since 05/12/2017
    */
	public void agregarComisionIntegrantes(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision);

	/**
     * Una vez que el usuario elimina una comisión adjunta en la vista de Convocatoria,
     * este método se encarga de elimnar dicha comisión y sus respectivos integrantes
     * de HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param dtoComision
     * @return void
     * @since 05/12/2017
    */
	public void eliminarComisionIntegrantes(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision);

	/**
     * Una vez que el usuario selecciona sólo una comisión adjunta en la vista de Convocatoria,
     * este método se encarga de almacenar dicha comisión y sus respectivos integrantes dentro
     * de HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param dtoComision
     * @return void
     * @since 05/12/2017
    */
	public void agregarFilaComision(HelperConvocatoria helpConvocatoria, DTOComisiones dtoComision);

	/**
     * Una vez que el usuario selecciona la opción de insertar punto en la vista de Convocatoria y que
     * ingresa la actividad a realizar, este método almacena y ordena dicho punto en HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void insertarPuntoConvoc(HelperConvocatoria helpConvocatoria);

	/**
     * Una vez que el usuario selecciona la opción de eliminar punto en la vista de Convocatoria,
     * este método elimina y reordena los puntos en HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void eliminarPuntoConvoc(HelperConvocatoria helpConvocatoria, DTOOrdenesDelDia ordenModificable);

	/**
     * Una vez que el usuario mueve un punto de una posición a otra en la vista de Convocatoria,
     * este método reordena los puntos en HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void moverPuntoConvoc(HelperConvocatoria helpConvocatoria, ReorderEvent event);

	/**
     * Con base en los datos introducidos en la vista de Convocatoria, inserta un registro
     * de convocatoria en la base de datos dentro de la tabla CONVOCATORIAS
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void insertarConvocatoria(HelperConvocatoria helpConvocatoria);

	/**
     * Con base en los datos introducidos en la vista de Convocatoria, inserta todos los
     * puntos de la orden del día (un registro por punto) en la base de datos dentro de la
     * tabla ORDENES_DEL_DIA
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void insertarOrdenDelDia(HelperConvocatoria helpConvocatoria);

	/**
     * Envía un correo de notificación a cada uno de los integrantes de la o las
     * comisiones involucradas en la convocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return void
     * @since 05/12/2017
    */
	public void enviarCorreoInformativo(HelperConvocatoria helpConvocatoria);

	/**
     * Con base en el idComisionPreside que preside a la convocatoria actual, recupera una
     * lista de todas las comisiones adjuntas involucradas en dicha convocatoria y la
     * almacena en HelperConvocatoria
     * @author Miguel Ortiz
     * @param idComisionPreside
     * @return List<DTOComisionesUnidas>
     * @since 05/12/2017
    */
	public List<DTOComisionesUnidas> recuperarComisionesUnidas(Integer idComisionPreside);

	/**
     * Determina la lista final de comisiones, la cual incluye la comisión que preside y las
     * comisiones adjuntas de la convocatoria actual. Dicha lista es almacenada en
     * HelperConvocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @return List<DTOComisiones>
     * @since 05/12/2017
    */
	public List<DTOComisiones> determinarComisionesFinal(HelperConvocatoria helpConvocatoria);

	/**
     * Con base en la lista final de comisiones involucradas en la convocatoria actual, determina
     * una lista de todos los integrantes de dichas comisiones. El resultado es almacenado en
     * HelperConvocatoria
     * @author Miguel Ortiz
     * @param comisionesFinal
     * @return List<DTOIntegrantesComision>
     * @since 05/12/2017
    */
	public List<DTOIntegrantesComision> determinarIntegComision(List<DTOComisiones> comisionesFinal);

	/**
     * Con base en el rol y el nombre del usuario actual, valida si dicho usuario pertenece a
     * alguna de la o las comisiones involucradas en la convocatoria. El resultado es almacenado en
     * HelperConvocatoria
     * @author Miguel Ortiz
     * @param rolUsuario
     * @param nomUsuario
     * @return boolean
     * @since 05/12/2017
    */
	public boolean validarMiembroComision(String rolUsuario, String nomUsuario);

	public void precargarOrdenDelDia(HelperConvocatoria helpConvocatoria, UploadedFile file);
	public void precargarDocumentacion(HelperConvocatoria helpConvocatoria, UploadedFile fileZIP);
	public void guardarOrdenDelDia(HelperConvocatoria helpConvocatoria);
	public void guardarDocumentacion(HelperConvocatoria helpConvocatoria);

	public List<String> recuperaImgsInfografias();

}
