/**
 * @(#)DTOMenuHelper.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.dto.helper;

import java.io.Serializable;
import java.util.List;

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
	 * Nombre que recibe el menú que se pintara en la barra o que aparecera según sea el caso.
	 */
	private String nombreMenu;

	/**
	 * Url que este menú contendra con la pantalla hacia la cual envia
	 */
	private String urlMenu;

	/**
	 * Imagenes que se visualizan x menú, que representa el módulo al que abre.
	 */
	private String[] imagenesMenu;

	/**
	 * Identificador del menú QUE VIENE DE ADMIN ES EL ID_MODULO
	 */
	private Integer idMenu;
	
	private List<HELPAccioneModulo> subMenu;
	
	

	/* ------------------------------------- Getters y Setters ------------------------------------ */


	public List<HELPAccioneModulo> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<HELPAccioneModulo> subMenu) {
		this.subMenu = subMenu;
	}

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

}
