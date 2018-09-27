/**
 * @(#)DTOTurnadoHelper.java 10/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Clase de ayuda que contendrá los parámetros para generar los acrónimos
 *
 * @author Pável Alexei Martínez Regalado
 * @since 10/01/2018
 */
public class DTOTurnadoHelper implements Serializable{
	/*
	 * Serial
	 */
	private static final long serialVersionUID = 6874600061654253346L;	

	/**
	 * Persona a la que se puede turnar
	 */
	private DTOEstructuraAreasEntity persona;
	
	private boolean esPersonaCorrecta;

	/**
	 * Arreglo de las instrucciones de atención
	 */
	private boolean[] instruccionesAtencion;

	/**
	 * Arreglo de las instrucciones informativas
	 */
	private boolean[] instruccionesInformativas;
	
	/**
	 * Arreglo de las instrucciones de atención
	 */
	private boolean[] instruccionesAtencionRealizadas;

	/**
	 * Arreglo de las instrucciones informativas
	 */
	private boolean[] instruccionesInformativasRealizadas;
	
	private boolean tieneIntruccionesAtencionRealizadas;
	
	private boolean tieneIntruccionesInfoRealizadas;

	/**
	 * Lista con las instrucciones para insertar
	 */
	private List<Integer> idsInstruccionesInsertar;
	
	/**
	 * String con el tipo de instrucciones que se insertarán. Instrucciones de atención: aten, Intrucciones informativas: info
	 */
	private String tipoInstrucciones; 

	/**
	 * Boolean que se activa si es que se va a turnar a una persona
	 */
	private boolean enviarATurnar;

	public DTOTurnadoHelper(){
		esPersonaCorrecta = true;
	}

	/**
	 * @return variable de tipo List<DTOEstructuraAreasEntity> contenida en persona
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOEstructuraAreasEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona: variable de tipo List<DTOEstructuraAreasEntity> contenida en persona
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return variable de tipo boolean[] contenida en instruccionesAtencion
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public boolean[] getInstruccionesAtencion() {
		return instruccionesAtencion;
	}

	/**
	 * @param instruccionesAtencion: variable de tipo boolean[] contenida en instruccionesAtencion
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setInstruccionesAtencion(boolean[] instruccionesAtencion) {
		this.instruccionesAtencion = instruccionesAtencion;
	}

	/**
	 * @return variable de tipo boolean[] contenida en instruccionesInformativas
	 * 
	 * @since 10/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public boolean[] getInstruccionesInformativas() {
		return instruccionesInformativas;
	}

	/**
	 * @param instruccionesInformativas: variable de tipo boolean[] contenida en instruccionesInformativas
	 *
	 * @since 10/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setInstruccionesInformativas(boolean[] instruccionesInformativas) {
		this.instruccionesInformativas = instruccionesInformativas;
	}

	/**
	 * @return variable de tipo boolean contenida en enviarATurnar
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public boolean isEnviarATurnar() {
		return enviarATurnar;
	}

	/**
	 * @param enviarATurnar: variable de tipo boolean contenida en enviarATurnar
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setEnviarATurnar(boolean enviarATurnar) {
		this.enviarATurnar = enviarATurnar;
	}

	/**
	 * @return variable de tipo List<Integer> contenida en idsInstruccionesInsertar
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public List<Integer> getIdsInstruccionesInsertar() {
		return idsInstruccionesInsertar;
	}

	/**
	 * @param idsInstruccionesInsertar: variable de tipo List<Integer> contenida en idsInstruccionesInsertar
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdsInstruccionesInsertar(List<Integer> idsInstruccionesInsertar) {
		this.idsInstruccionesInsertar = idsInstruccionesInsertar;
	}

	/**
	 * @return variable de tipo String contenida en tipoInstrucciones
	 * 
	 * @since 19/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getTipoInstrucciones() {
		return tipoInstrucciones;
	}

	/**
	 * @param tipoInstrucciones: variable de tipo String contenida en tipoInstrucciones
	 *
	 * @since 19/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setTipoInstrucciones(String tipoInstrucciones) {
		this.tipoInstrucciones = tipoInstrucciones;
	}

	/**
	 * @return valor de tipo boolean[] que esta contenido en la variable instruccionesAtencionRealizadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public boolean[] getInstruccionesAtencionRealizadas() {
		return instruccionesAtencionRealizadas;
	}

	/**
	 * @param instruccionesAtencionRealizadas : valor que se ingresa a la variable de tipo boolean[]
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setInstruccionesAtencionRealizadas(
			boolean[] instruccionesAtencionRealizadas) {
		this.instruccionesAtencionRealizadas = instruccionesAtencionRealizadas;
	}

	/**
	 * @return valor de tipo boolean[] que esta contenido en la variable instruccionesInformativasRealizadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public boolean[] getInstruccionesInformativasRealizadas() {
		return instruccionesInformativasRealizadas;
	}

	/**
	 * @param instruccionesInformativasRealizadas : valor que se ingresa a la variable de tipo boolean[]
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setInstruccionesInformativasRealizadas(
			boolean[] instruccionesInformativasRealizadas) {
		this.instruccionesInformativasRealizadas = instruccionesInformativasRealizadas;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable tieneIntruccionesAtencionRealizadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public boolean isTieneIntruccionesAtencionRealizadas() {
		return tieneIntruccionesAtencionRealizadas;
	}

	/**
	 * @param tieneIntruccionesAtencionRealizadas : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public void setTieneIntruccionesAtencionRealizadas(
			boolean tieneIntruccionesAtencionRealizadas) {
		this.tieneIntruccionesAtencionRealizadas = tieneIntruccionesAtencionRealizadas;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable tieneIntruccionesInfoRealizadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public boolean isTieneIntruccionesInfoRealizadas() {
		return tieneIntruccionesInfoRealizadas;
	}

	/**
	 * @param tieneIntruccionesInfoRealizadas : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public void setTieneIntruccionesInfoRealizadas(
			boolean tieneIntruccionesInfoRealizadas) {
		this.tieneIntruccionesInfoRealizadas = tieneIntruccionesInfoRealizadas;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable esPersonaCorrecta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public boolean isEsPersonaCorrecta() {
		return esPersonaCorrecta;
	}

	/**
	 * @param esPersonaCorrecta : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setEsPersonaCorrecta(boolean esPersonaCorrecta) {
		this.esPersonaCorrecta = esPersonaCorrecta;
	}

}
