/**
 * @(#)DAOAdministradorGestionInterface.java 03/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

/**
 * Interfaz que contiene la firma de los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de administración.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
public interface DAOAdministradorGestionInterface extends DAOGenericGestionInterface<Integer, Integer> {

	/**
	 * Método que obtiene las areas que tiene registradas una persona de Oficilia de Partes.
	 * 
	 * @param idOficilia: Identificador de la persona de oficilia de partes.
	 * @return List<DTOCArea>: Lista con las áreas encontradas para la persona de oficilia de partes.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/09/2017
	 */
	public List<DTOCAreaEntity> consultarAreasRegistradasParaUnaPersonaDeOficialia(Integer idOficilia);

	/**
	 * Método que obtiene las opciones del menú que tendra el usuario al loguearse en el menú
	 * 
	 * @param rol: Cadena que contiene el rol del usuario al que se le obtienen las opciones del menú
	 * @return List<DTOMenuHelper>: Lista de opciones del menú
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public List<DTOMenuHelper> consultarOpcionesMenuParaGestion(String rol);
}
