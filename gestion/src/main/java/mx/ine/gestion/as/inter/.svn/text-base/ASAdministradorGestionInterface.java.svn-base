/**
 * @(#)ASAdministradorGestionInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.helper.DTOMenuHelper;

/**
 * Interface que contiene la firma de los métodos que acceden a el o los DAO del administrador general del sistema de Gestión.
 *
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */
public interface ASAdministradorGestionInterface {

	/**
	 * Método que obtiene el menú a travez de las clases ya generadas del jar "funciones-comunes" y que mandan llamar
	 * al webservice del menú de administración.
	 * 
	 * @param tipoPerfil: Nos indica el tipo de perfil, por ejemplo el valor 'OF' indica que es un perfil de oficialia
	 * @return List<DTOMenuHelper>: Lista que contiene las opciones del menú que se utilizan.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/09/2017
	 */
	public List<DTOMenuHelper> obtenMenuGestion(String tipoPerfil);

	/**
	 * Obtiene las notificaciones que tiene cada uno de los menús para el usuario (son los númeritos en rojo) que NO es de oficilia (para oficilia hay otro método)
	 * 
	 * @param menus: Lista de objetos que contiene la información del menú a la que se le obtendran las notificaciones.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/12/2017
	 */
	public void obtenNotificacionesMenuSeguimiento(List<DTOMenuHelper> menus);
	
	/**
	 * Obtiene las notificaciones que tiene cada uno de los menús para el usuario (son los númeritos en rojo) que SON de oficilia (para seguimiento hay otro método)
	 * 
	 * @param menus: Lista de objetos que contiene la información del menú a la que se le obtendran las notificaciones.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/12/2017
	 */
	public void obtenNotificacionesMenuOficilia(List<DTOMenuHelper> menus);
	
	/**
	 * Método para cargar la información que se encuentra registrada en las tablas de Gestión 
	 * y que esta relacionada al usuario que se esta logueando.
	 * 
	 * @throws Exception: en caso de error manda una excepción
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void cargaInformacionGestionEnUsuario() throws Exception;

}
