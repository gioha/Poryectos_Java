/**
 * @(#)DTOMenuBandeja.java 03/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * @author Homero Fidel Villanueva
 *
 */
public class DTOMenuBandejaHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 885462692028207590L;

	/**
	 * Atributo que guarda el número total de documentos para el apartado de
	 * Bandeja de Entrada
	 */
	private int numRecibidos;

	/**
	 * Atributo que guarda el número total de documentos para el apartado de
	 * Bandeja de Historico
	 */
	private int numHistoricos;

	/**
	 * Atributo que guarda el número de Borradores
	 */

	private int numBorradores;
	/**
	 * Atributo que guarda el número de Enviados
	 */
	private int numEnviados;

	// -------------- GETTERS & SETTERS -------------------- //
	/**
	 * @return valor de tipo int que esta contenido en la variable numBorradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public int getNumBorradores() {
		return numBorradores;
	}

	/**
	 * @param numBorradores
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public void setNumBorradores(int numBorradores) {
		this.numBorradores = numBorradores;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable numEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public int getNumEnviados() {
		return numEnviados;
	}

	/**
	 * @param numEnviados
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public void setNumEnviados(int numEnviados) {
		this.numEnviados = numEnviados;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable numRecibidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public int getNumRecibidos() {
		return numRecibidos;
	}

	/**
	 * @param numRecibidos
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setNumRecibidos(int numRecibidos) {
		this.numRecibidos = numRecibidos;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable numHistoricos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public int getNumHistoricos() {
		return numHistoricos;
	}

	/**
	 * @param numHistoricos
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setNumHistoricos(int numHistoricos) {
		this.numHistoricos = numHistoricos;
	}

}
