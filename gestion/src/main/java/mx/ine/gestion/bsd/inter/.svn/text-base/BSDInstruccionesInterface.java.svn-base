/**
 * @(#)BSDInstruccionesInterface.java 17/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

/**
 * Interface encargada de administrar el o los AS del mÓdulo de 
 * Instrucciones.
 * 
 * @author David Rodríguez Corral
 * @since 28/08/2017
 */
public interface BSDInstruccionesInterface {

	/**
	 * Método que consulta en la bd las instrucciones del usuario en sesión.
	 * 
	 * @return List<DTOInstrucciones>: Lista que contiene las instrucciones del usuario en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/07/2017
	 */
	public List<DTOInstruccionesEntity> consultarInstruccciones(Integer idPersona, Integer tipoInstruccion);
	
	/**
	 * Método que envia un mensaje si se excede el límite de las instrucciones de 
	 * Atención a 5 e Informativas a 3
	 * 
	 * @param lista: Lista que contiene las instrucciones del usuario en sesión.
	 * @param límite: Entero que contiene el límite de instrucciones permitidas.
	 * 
	 * @return String que contiene un mensaje de error en caso de exceder el límite de instrucciones
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/08/2017
	 */
	public String limitarInstrucciones(List<DTOInstruccionesEntity> lista, int limite);
	
	/**
	 * Método que actualiza la lista de instrucciones en la bd del usuario en sesión.
	 * 
	 * @param lista: Lista que contiene las instrucciones a actualizar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void actualizarInstrucciones(List<DTOInstruccionesEntity> lista);
	
	/**
	 * Método que elimina logicamente las instrucciones del usuario en sesión
	 * cambiando el estatus = 'A'-- Activa a estatus = 'E' --Eliminada.
	 * 
	 * @param idPersona: Entero que contiene el id de la persona en sesión.
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/08/2017
	 */
	public void eliminarInstruccionesLogicas(Integer idPersona);
	
	/**
	 * Método que concatena la lista de atención e informativa para su inserción
	 * en la bd.
	 * 
	 * @param lista: Lista que contiene las instrucciones de instrucción.
	 * @param lista: Lista que contiene las instrucciones de atención.
	 * 
	 * @return List<DTOInstrucciones>: Lista de instrucciones
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/08/2017
	 */
	public List<DTOInstruccionesEntity> concatenarListas(List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativa);
	
	/**
	 * Método que ordena una lista de acuerdo a la prioridad establecida por el
	 * usuario.
	 * 
	 * @param lista: Lista que contiene las instrucciones a ordenar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/09/2017
	 */
	public void reOrdenar(List<DTOInstruccionesEntity> lista);
}
