/**
 * @(#)VHAdministradorGestionInterface.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

/**
 * Interfaz que contiene la firma de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 11/10/2017
 */
public interface VHAdministradorGestionInterface {

	/**
	 * Método que indica la pantalla a la que se tiene que redirigir a la entrada del sistema
	 * según el rol que tenga el usuario.
	 * 
	 * @param variable para indicar si se muestra el menú o no
	 * @return String: cadena que contiene la ruta de la pantalla a la que se tiene que redirigir el sistema.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/10/2017
	 */
	public String obtenerPantallaInicioSegunRol();

	/**
	 * Para los perfiles con más de un Rol se selecciona con cual quieren entrar.
	 * 
	 * @param tipoPerfil: indica el tipo de perfil que selecciono el usuario con el que desea entrar.
	 * @return String: su ruta asignada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/11/2017
	 */
	public String obtenerPantallaInicioMasDeUnRol(String tipoPerfil);
}
