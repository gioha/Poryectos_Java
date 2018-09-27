/**
 * @(#)DTOMenuBandejaOficialiaHelper.java 03/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;

/**
 * @author David Rodríguez Corral
 *
 */
public class DTOMenuBandejaOficialiaHelper implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1980298476514465057L;
	/**
	 * Variable que guarda el número de documentos de entrada
	 */
	
	private int numEntrada;
	/**
	 * Variable que guarda el número de Enviados
	 */
	private int numEnviados;
	
	// -------------- GETTERS & SETTERS -------------------- //	
	
	/**
	 * @return valor de tipo int que esta contenido en la variable numEntrada
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/11/2017
	 */
	public int getNumEntrada() {
		return numEntrada;
	}
	/**
	 * @param numEntrada : valor que se ingresa a la variable de tipo int
	 *
	 * @author David Rodríguez Corral
	 * @since 16/11/2017
	 */
	public void setNumEntrada(int numEntrada) {
		this.numEntrada = numEntrada;
	}
	/**
	 * @return valor de tipo int que esta contenido en la variable numEnviados
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/11/2017
	 */
	public int getNumEnviados() {
		return numEnviados;
	}
	/**
	 * @param numEnviados : valor que se ingresa a la variable de tipo int
	 *
	 * @author David Rodríguez Corral
	 * @since 16/11/2017
	 */
	public void setNumEnviados(int numEnviados) {
		this.numEnviados = numEnviados;
	}

	
}
