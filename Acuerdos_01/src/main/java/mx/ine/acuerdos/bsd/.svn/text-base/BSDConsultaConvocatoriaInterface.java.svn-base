package mx.ine.acuerdos.bsd;

import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOConvocatoriasPK;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.HelperDTOMesesAnio;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;

/**
 * Interfaz que sirve que comunica a las capas MB y BSD de la vista de Convocatoria,
 * contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface BSDConsultaConvocatoriaInterface {

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
     * Recupera el catálogo de los diferentes tipos de sesiones (List<DTOTipoSesiones>)
     * para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @return List<DTOTipoSesiones>
     * @since 05/12/2017
    */
	public List<DTOTipoSesiones> recuperarTiposDeSesiones();

	/**
     * Con base en el tipoSesión proveniente de la convocatoria seleccionada por el usuario actual,
     * recupera la descripción del tipo de sesión sobre dicha convocatoria con el fin de ser
     * desplegada en la vista de Consulta de Convocatoria
     * @author Miguel Ortiz
     * @param helpConvocatoria
     * @param tipoSesion
     * @return String
     * @since 05/12/2017
    */
	public String recuperarDescTipoDeSesion(HelperConvocatoria helpConvocatoria, Integer tipoSesion);

	/**
     * Con base en la convocatoria seleccionada por el usuario actual (dtoConvoctariaPK),
     * recupera el orden del día correspondiente a dicha convocatoria con el fin de ser
     * desplegado en la vista de Consulta de Convocatoria
     * @author Miguel Ortiz
     * @param dtoConvoctariaPK
     * @return List<DTOOrdenesDelDia>
     * @since 05/12/2017
    */
	public List<DTOOrdenesDelDia> recuperarOrdenDelDia(DTOConvocatoriasPK dtoConvoctariaPK);

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
     * Recupera una lista de todas las convocatorias existentes en la base de datos
     * (List<DTOConvocatorias>) para su posterior almacenamiento en HelperConvocatoria
     * @author Miguel Ortiz
     * @return List<DTOConvocatorias>
     * @since 05/12/2017
    */
	public List<DTOConvocatorias> recuperarConvocatorias();

	/**
     * Con base en la lista de todas las convocatorias existentes en la base de datos, determina
     * una lista de los diferentes años en que han sido registradas convocatorias (List<Integer>)
     * y la almacena en HelperConvocatoria. El contenido es desplegado por la vista de Consulta de
     * Convocatoria
     * @author Miguel Ortiz
     * @return List<Integer>
     * @param listaConvocatorias
     * @since 05/12/2017
    */
	public List<Integer> determinarListaAnios(List<DTOConvocatorias> listaConvocatorias);

	/**
     * Con base en el idAnio introducido por el usuario actual dentro de la vista de Consulta
     * de Convocatoria, determina una lista de los diferentes meses en que han sido registradas
     * convocatorias a lo largo del idAnio (List<HelperDTOMesesAnio>) y la almacena en
     * HelperConvocatoria. El contenido es desplegado por la vista de Consulta de Convocatoria
     * @author Miguel Ortiz
     * @return List<HelperDTOMesesAnio>
     * @param helpConvocatoria
     * @param idAnio
     * @since 05/12/2017
    */
	public List<HelperDTOMesesAnio> determinarMesesAnio(HelperConvocatoria helpConvocatoria, Integer idAnio);

	/**
     * Con base en el mes introducido por el usuario actual dentro de la vista de Consulta
     * de Convocatoria, determina una lista de los diferentes tipos de sesión registradas
     * a lo largo del idAnio y del mes (List<HelperDTOMesesAnio>) y la almacena en
     * HelperConvocatoria. El contenido es desplegado por la vista de Consulta de Convocatoria
     * @author Miguel Ortiz
     * @return List<DTOTipoSesiones>
     * @param helpConvocatoria
     * @param mes
     * @since 05/12/2017
    */
	public List<DTOTipoSesiones> determinarTiposSesion(HelperConvocatoria helpConvocatoria, Integer mes);

	/**
     * Con base en el tipo de sesión introducido por el usuario actual dentro de la vista de
     * Consulta de Convocatoria, determina una lista de convocatorias de acuerdo al idAnio, mes y
     * tipoSesion y la almacena en HelperConvocatoria. El contenido es desplegado por la vista de
     * Consulta de Convocatoria
     * @author Miguel Ortiz
     * @return List<DTOConvocatorias>
     * @param helpConvocatoria
     * @param tipoSesion
     * @since 05/12/2017
    */
	public List<DTOConvocatorias> recuperarConvocatoriasPorTipo(HelperConvocatoria helpConvocatoria, Integer tipoSesion);

}
