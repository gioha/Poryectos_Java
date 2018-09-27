/**
 * @(#)DTOMenuHelper.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * Objeto de transferencia que sirve de ayuda para pintar las opciones del menú que tiene disponible el sistema de Gestión.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 06/09/2017
 */
public class DTOMenuHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -9140840196533073919L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Identificador del menú
	 */
	private Integer idMenu;
	
	/**
	 * Nombre que recibe el menú que se pintara en la barra o que aparecera según sea el caso.
	 */
	private String nombreMenu;

	/**
	 * Identificador del submenu (en caso de tenerlo, en gestion el menu y el submenu son el mismo).
	 */
	private Integer idSubmenu;

	/**
	 * Nombre que recibe el submenú (en caso de tenerlo, en gestion el menu y el submenu son el mismo).
	 */
	private String nombreSubmenu;

	/**
	 * Identificador del módulo al que esta relacionado el menú (muy importante, se hacen muchas validaciones con este)
	 */
	private Integer idModulo;

	/**
	 * Se refiere a si el módulo es de tipo reporte 'R' o normal 'M'
	 */
	private String tipoModulo;

	/**
	 * Contiene el nombre del módulo al que esta relacionado el menú
	 */
	private String nombreModulo;

	/**
	 * Identificador de la acción (consulta, captura, modificación, etc.)
	 */
	private Integer idAccion;

	/**
	 * Url que este menú contendra con la pantalla hacia la cual envia
	 */
	private String urlMenu;

	/**
	 * Imagenes que se visualizan x menú, que representa el módulo al que abre.
	 */
	private String[] imagenesMenu;

	/**
	 * Contiene el número de notificaciones pendientes para ese menu para ese usuario (son las alertas rojas)
	 */
	private Integer numNotificaciones;
	
	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */
	

	/* ------------------------------------- Getters y Setters ------------------------------------ */

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreMenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public String getNombreMenu() {
		return nombreMenu;
	}

	/**
	 * @param nombreMenu : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable urlMenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public String getUrlMenu() {
		return urlMenu;
	}

	/**
	 * @param urlMenu : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	/**
	 * @return valor de tipo String[] que esta contenido en la variable imagenesMenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public String[] getImagenesMenu() {
		return imagenesMenu;
	}

	/**
	 * @param imagenesMenu : valor que se ingresa a la variable de tipo String[]
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setImagenesMenu(String[] imagenesMenu) {
		this.imagenesMenu = imagenesMenu;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idMenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public Integer getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable numNotificaciones
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/12/2017
	 */
	public Integer getNumNotificaciones() {
		return numNotificaciones;
	}

	/**
	 * @param numNotificaciones : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/12/2017
	 */
	public void setNumNotificaciones(Integer numNotificaciones) {
		this.numNotificaciones = numNotificaciones;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idSubmenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public Integer getIdSubmenu() {
		return idSubmenu;
	}

	/**
	 * @param idSubmenu : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setIdSubmenu(Integer idSubmenu) {
		this.idSubmenu = idSubmenu;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreSubmenu
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public String getNombreSubmenu() {
		return nombreSubmenu;
	}

	/**
	 * @param nombreSubmenu : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setNombreSubmenu(String nombreSubmenu) {
		this.nombreSubmenu = nombreSubmenu;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idModulo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoModulo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public String getTipoModulo() {
		return tipoModulo;
	}

	/**
	 * @param tipoModulo : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setTipoModulo(String tipoModulo) {
		this.tipoModulo = tipoModulo;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreModulo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public String getNombreModulo() {
		return nombreModulo;
	}

	/**
	 * @param nombreModulo : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAccion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public Integer getIdAccion() {
		return idAccion;
	}

	/**
	 * @param idAccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 13/12/2017
	 */
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

}
