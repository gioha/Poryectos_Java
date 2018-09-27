/**
 * @(#)HELPAccioneModulo.java 18/08/2016
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.dto.helper;

import java.io.Serializable;

/**
 * Clase que contiene el modelo para generar la respuesta del web service Menu.
 * 
 * @author Ricardo Rodríguez de los Santos.
 * @updatedBy Sampier
 * @since 18/08/2016
 *
 */
public class HELPAccioneModulo implements Serializable{	
	/**
	 * Objeto para la serialización de objetos
	 */
	private static final long serialVersionUID = 7202543813298171717L;
	private String urlModulo;
	private Integer idAccion;
	private String accionDescrip;
	private String tipoJunta;
	private String estatus;
	private String action;
	/**
	 * Obtiene el valor de urlModulo
	 * @return el valor de urlModulo
	 *
	 * @author Ricardo Rodríguez de los Santos.
	 * @since 22/08/2016
	 */
	public String getUrlModulo() {
		return urlModulo;
	}

	/**
	 * Asigna el valor a urlModulo
	 * @param urlModulo valor que se asigna a urlModulo
	 *
	 * @author Ricardo Rodríguez de los Santos
	 * @since 22/08/2016
	 */
	public void setUrlModulo(String urlModulo) {
		this.urlModulo = urlModulo;
	}

	/**
	 * Obtiene el valor de idAccion
	 * @return el valor de idAccion
	 *
	 * @author Ricardo Rodríguez de los Santos.
	 * @since 22/08/2016
	 */
	public Integer getIdAccion() {
		return idAccion;
	}

	/**
	 * Asigna el valor a idAccion
	 * @param idAccion valor que se asigna a idAccion
	 *
	 * @author Ricardo Rodríguez de los Santos
	 * @since 22/08/2016
	 */
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	/**
	 * Obtiene el valor de accionDescrip
	 * @return el valor de accionDescrip
	 *
	 * @author Ricardo Rodríguez de los Santos.
	 * @since 22/08/2016
	 */
	public String getAccionDescrip() {
		return accionDescrip;
	}

	/**
	 * Asigna el valor a accionDescrip
	 * @param accionDescrip valor que se asigna a accionDescrip
	 *
	 * @author Ricardo Rodríguez de los Santos
	 * @since 22/08/2016
	 */
	public void setAccionDescrip(String accionDescrip) {
		this.accionDescrip = accionDescrip;
	}

	/**
	 * Obtiene el valor de tipoJunta
	 * @return el valor de tipoJunta
	 *
	 * @author Ricardo Rodríguez de los Santos.
	 * @since 22/08/2016
	 */
	public String getTipoJunta() {
		return tipoJunta;
	}

	/**
	 * Asigna el valor a tipoJunta
	 * @param tipoJunta valor que se asigna a tipoJunta
	 *
	 * @author Ricardo Rodríguez de los Santos
	 * @since 22/08/2016
	 */
	public void setTipoJunta(String tipoJunta) {
		this.tipoJunta = tipoJunta;
	}

	/**
	 * Obtiene el valor de estatus
	 * @return el valor de estatus
	 *
	 * @author Ricardo Rodríguez de los Santos.
	 * @since 22/08/2016
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * Asigna el valor a estatus
	 * @param estatus valor que se asigna a estatus
	 *
	 * @author Ricardo Rodríguez de los Santos
	 * @since 22/08/2016
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * Método que obtiene el valor de action
	 * @return String : action
	 * @author RogelioMB
	 * @since 03/10/2016
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Método que establece el valor de action
	 * @param String : action 
	 * @author RogelioMB
	 * @since 03/10/2016
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
