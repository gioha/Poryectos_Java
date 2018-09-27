package mx.ine.acuerdos.bsd;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.helper.form.HLPFormCClasificaciones;


/**
 * Funcionalidad de ABC Clasificacion Puntos
 * @author José Hurtado
 * @since 04/12/2017
 *
 */
public interface BSDCClasificacionInterface {

	/**
     * Recuperar tipo de clasificaciones punto
     * @param void
     * @return List<DTOCClasificaciones>
   */
	public List<DTOCClasificaciones> recuperaClasficacionesPunto();

	/**
     * Reordenar calsificaciones
     * @param HLPFormCClasificaciones
     * @return void
   */
	public void reordenarClasificaciones(HLPFormCClasificaciones form);

	/**
     * Agrega clasificación
     * @param HLPFormCClasificaciones
     * @return void
   */
	public void agregaRenglon(HLPFormCClasificaciones form);

	/**
     * revisa renglon seleccionado para evitar eliminar clasificaciones protegidas
     * @param HLPFormCClasificaciones
     * @return void
   */
//	public	void renglonSel(HLPFormCClasificaciones form);

	/**
     * Actualiza el catalogo de Clasificación Punto
     * @param HLPFormCClasificaciones, HLPFormCClasificaciones 
     * @return void
   */
	public void actualizarClasif(HLPFormCClasificaciones form, HLPFormCClasificaciones formOriginal);

	/**
     * Verifica IdClasificacion en Puntos Acuerdo
     * @param Integer 
     * @return void
   */
	public boolean verificaIdClasif(Integer idClasificacion);

	/**
     * Llena lista de control de clasificaciones
	 * @param HLPFormCClasificaciones 
     * @return void
   */
	public void llenalistaDeControl(HLPFormCClasificaciones form);

	/**
     * define imagenes de infografias
	 * @param List<String> 
     * @return void
   */
	public List<String> recuperaImgsInfografias();
	
	
}
