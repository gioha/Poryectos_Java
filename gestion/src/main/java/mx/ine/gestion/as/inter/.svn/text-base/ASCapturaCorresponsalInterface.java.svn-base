package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Interface que contiene la firma de los métodos que acceden a el o los DAO de los corresponsales del sistema de Gestión.
 *
 * @author Luis Urrutia
 * @since 16/11/2017
 */
public interface ASCapturaCorresponsalInterface {
	
	/**
	 * Método que obtiene los corresponsales candidatos con base en los parámetros dados
	 * 
	 * @param nombre: Nos indica el nombre o parte del nombre que se está buscando.
	 * @param apellido: Nos indica el apellido o parte del apellido que se está buscando.
	 * @param idPersona: Nos indica el id del usuario para el que se están buscando los corresponsales candidatos.
	 * @param idArea: Nos indica el id del área del usuario para el que se están buscando los corresponsales candidatos.
	 * @param tipoArea: Nos indica el tipo del área del usuario para el que se están buscando los corresponsales candidatos.
	 * @return List<DTOEstructuraAreasEntity>: Lista que contiene los corresponsales candidatos.
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public List<DTOEstructuraAreasEntity> buscarCorresponsalesCandidatos(String nombre, String apellido, Integer idPersona, Integer idArea, Integer tipoArea);

	/**
	 * Método que obtiene los corresponsales ya asignados y con estatus 'A'
	 * 
	 * @param idPersona: Nos indica el id del usuario para el que se están buscando los corresponsales.
	 * @return List<DTOEstructuraAreasEntity>: Lista que contiene los corresponsales asignados.
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public List<DTOEstructuraAreasEntity> buscarCorresponsales(Integer idPersona);

	/**
	 * Método que agrega un corresponsal
	 * 
	 * @param corresponsal: Objeto que contien todas las propiedades del corresponsal a insertar en la BD.
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void agregarCorresponsal(DTOCorresponsales corresponsal) throws Exception;

	/**
	 * Método que elimina de manera lógica a un corresponasl, es decir cambie el estatus a 'I'
	 * 
	 * @param idCorresponsal: Nos indica el id del corresponsal a eliminar.
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void eliminarCorresponsal(Integer idCorresponsal) throws Exception;
}
