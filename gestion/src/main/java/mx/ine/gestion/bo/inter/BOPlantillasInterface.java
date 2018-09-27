/**
 * @(#)BOPlantillasInterface.java 15/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

/**
 * Interfaz que contiene la firma de los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * plantillas para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 15/01/2018
 */
public interface BOPlantillasInterface {

	/**
	 * Método para guardar la plantilla temporal en la carpeta "oficial" y que deje de ser temporal.
	 * 
	 * @param filtros: Objeto que contiene la información que el usuario ingreso en pantalla
	 * @param idPlantilla: Identificador de la plantilla que se va a guardar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void guardarPlantilla(DTOFiltrosPlantillaHelper filtros, Integer idPlantilla) throws Exception;

	/**
	 * Método para eliminar una plantilla de gluster
	 * 
	 * @param plantilla: Objeto que contiene la información de la plantilla
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public void eliminarPlantilla(DTOPlantillaEntity plantilla);
}
