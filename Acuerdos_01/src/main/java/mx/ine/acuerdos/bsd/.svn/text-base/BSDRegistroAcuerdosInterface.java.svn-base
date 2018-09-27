package mx.ine.acuerdos.bsd;

import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HLPFormRegistroAcuerdos;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;

/**
 * Funcionalidad de registro de acuerdos
 * @author José Hurtado
 * @since 09/10/2017
 *
 */
public interface BSDRegistroAcuerdosInterface extends Serializable {

	
//	/**
//      * Muestra u oculta panel de Engose
//      * @param form: elementos de la pantalla registro acuerdos
//      * @return regresa true: motrar o false: ocultar
//    */
//	public void mostrarOcultar(HLPFormRegistroAcuerdos form);



	/**
     * evaluar datos requeridos
     * @param form: elementos de la pantalla registro acuerdos
     * @return regresa true: motrar o false: ocultar
   */
	public void evaluaRequeridos();

	/**
     * Recuperar tipo de sesiones
     * @param void
     * @return List<DTOTipoSesiones>
   */
	public List<DTOTipoSesiones> recuperaTiposSesiones();

	/**
     * Obtener un Acuerdo
     * @param String IdAcuerdo
     * @return DTOAcuerdos
   */
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo);

	/**
     * Eliminar seguimiento
     * @param String IdAcuerdo
     * @return DTOAcuerdos
   */
	public void eliminarSeguimiento(String idAcuerdo);

	/**
     * Eliminar puntos
     * @param String IdAcuerdo
     * @return DTOAcuerdos
   */
	public void eliminarPuntosAc(String idAcuerdo);

	/**
     * Verifricar existencia de Acuerdo
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return 
   */
	public void existeAc(DTOAcuerdos dtoAcuerdos, HLPFormRegistroAcuerdos form);

	/**
     * Guardar acuerdo
     * @param DTOAcuerdos
     * @return void
   */	
	public String guardaAcuerdo(DTOAcuerdos dtoAcuerdos, HLPFormRegistroAcuerdos form);


	/**
     * Recupera nombre de imágenes de infografías
     * @param void
     * @return List<String>
   */
	public List<String> recuperaImgsInfografias();


	/**
     * Establece si hay engrose 
     * @param HLPFormRegistroAcuerdos
     * @return void
   */
	public void hayEngrose(HLPFormRegistroAcuerdos form);

	/**
     * Recupera tipo docuemntos 
     * @param void
     * @return DTOTipoSesiones
   */
	public List<DTOCTipoDocumento> recuperaTiposDocumentos();

}
