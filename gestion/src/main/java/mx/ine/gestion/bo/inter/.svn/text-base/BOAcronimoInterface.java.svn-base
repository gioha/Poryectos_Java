/**
 * @(#)BOAcronimoInterface.java 11/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.Map;

/**
 * Interfaz que contiene la firma de los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * acronimos para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 11/01/2018
 */
public interface BOAcronimoInterface {

	/**
	 * Método que procesa el query que se utiliza en acronimos para buscar registros (busqueda principal)
	 * 
	 * @param query: Cadena que contiene el query que se va a procesar.
	 * @param filtrosColumna: Mapa que contiene los filtros que se seleccionan en la tabla que contiene los registros obtenidos de la busqueda.
	 * @return String: Cadena con el query procesado.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/01/2018
	 */
	public String procesaQueryConsultaAcronimos(String query, Map<String, Object> filtrosColumna);

	/**
	 * Método que procesa el query que se utiliza en acronimos para buscar el TOTAL de registros (búsqueda principal)
	 * 
	 * @param query: Cadena que contiene el query que se va a procesar.
	 * @return String: Cadena con el query procesado.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/01/2018
	 */
	public String procesaQueryConsultaTotalesAcronimos(String query);
}
