package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dao.inter.DAOGenericGestionInterface;
import mx.ine.gestion.dto.db.DTOCorresponsales;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Interfaz que contiene la firma de los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de configuración/corresponsales.
 * 
 * @author Luis Urrutia
 * @since 17/11/2017
 */
public interface DAOCorresponsalInterface extends
		DAOGenericGestionInterface<DTOCorresponsales, Integer>  {

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
	 * @since 17/11/2017
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

	//public void agregarCorresponsal(Integer idPersona, Integer idCorresponsal);

	/**
	 * Método que elimina de manera lógica a un corresponasl, es decir cambie el estatus a 'I'
	 * 
	 * @param idCorresponsal: Nos indica el id del corresponsal a eliminar.
	 *
	 * @author Luis Urrutia
	 * @since 21/11/2017
	 */
	public void eliminarCorresponsal(Integer idCorresponsal);
	
	/**
	 * Método para cambiar el estatus de los corresponsales que ya no se encuentren en
	 * el organigrama.
	 * 
	 * @param Integer
	 *            idPersona: id de la persona
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/01/2018
	 */
	public void actualizarEstatusCorresponsales(Integer idPersona);

}
