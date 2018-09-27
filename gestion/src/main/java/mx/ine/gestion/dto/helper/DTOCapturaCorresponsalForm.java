/**
 * @(#)DTOCapturaCorresponsalForm.java 16/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.Date;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Clase de ayuda en el módulo de captura corresponsales,
 * contiene los campos del formulario de la pantalla de corresponsales.
 * 
 * @author Luis Urrutia
 * @since 16/11/2017
 */
public class DTOCapturaCorresponsalForm  implements Serializable{
	
	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -7067151013467127323L;

	/**
	 * Variable que contiene el nombre del corresponsal candidato
	 */
	private String nombreCorresponsalCandidato;
	
	/**
	 * Variable que contiene los apellidos del corresponsal candidato
	 */
	private String apellidosCorresponsalCandidato;
	
	/**
	 * Objeto que contiene las propiedades del corresponsal candidato seleccionado
	 */
	private DTOEstructuraAreasEntity corresponsalCandidatoSeleccionado;
	
	/**
	 * Objeto que contiene los detalles del corresponsal
	 */
	private DTOEstructuraAreasEntity corresponsalDetalle;
	
	/**
	 * Variable que contiene la fecha inicial del corresponsal
	 */
	private Date fechaInicio;
	
	/**
	 * Variable que contiene la fecha final del corresponsal
	 */
	private Date fechaFin;

	/**
	 * Variable que contendra el día de inicio del periodo en el que el corresponsable estará vigente.
	 */
	private String diaInicio;

	/**
	 * Variable que contendra el día de fin del periodo en el que el corresponsable estará vigente.
	 */
	private String diaFin;

	/**
	 * Variable que contendra el mes de inicio del periodo en el que el corresponsable estará vigente.
	 */
	private String mesInicio;

	/**
	 * Variable que contendra el mes de fin del periodo en el que el corresponsable estará vigente.
	 */
	private String mesFin;

	/**
	 * Variable que contendra el año de inicio del periodo en el que el corresponsable estará vigente.
	 */
	private String anioInicio;

	/**
	 * Variable que contendra el año de fin del periodo en el que el corresponsable estará vigente.
	 */
	private String anioFin;

	/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @param nombreCorresponsalCandidato : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setNombreCorresponsalCandidato(String nombreCorresponsalCandidato){
		this.nombreCorresponsalCandidato = nombreCorresponsalCandidato;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable nombreCorresponsalCandidato
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public String getNombreCorresponsalCandidato(){
		return nombreCorresponsalCandidato;
	}
	
	/**
	 * @param apellidosCorresponsalCandidato : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setApellidosCorresponsalCandidato(String apellidosCorresponsalCandidato){
		this.apellidosCorresponsalCandidato = apellidosCorresponsalCandidato;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable apellidosCorresponsalCandidato
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public String getApellidosCorresponsalCandidato(){
		return apellidosCorresponsalCandidato;
	}

	/**
	 * @param corresponsalCandidatoSeleccionado : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setCorresponsalCandidatoSeleccionado(DTOEstructuraAreasEntity corresponsalCandidatoSeleccionado){
		this.corresponsalCandidatoSeleccionado = corresponsalCandidatoSeleccionado;
	}
	
	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable corresponsalCandidatoSeleccionado
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public DTOEstructuraAreasEntity getCorresponsalCandidatoSeleccionado(){
		return corresponsalCandidatoSeleccionado;
	}
	
	/**
	 * @param corresponsalDetalle : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setCorresponsalDetalle(DTOEstructuraAreasEntity corresponsalDetalle){
		this.corresponsalDetalle = corresponsalDetalle;
	}
	
	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable corresponsalDetalle
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public DTOEstructuraAreasEntity getCorresponsalDetalle(){
		return corresponsalDetalle;
	}
	
	/**
	 * @param fechaInicio : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaInicio
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaInicio(){
		return fechaInicio;
	}
	
	/**
	 * @param fechaFin : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
	
	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaFin
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaFin(){
		return fechaFin;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable diaInicio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getDiaInicio() {
		return diaInicio;
	}

	/**
	 * @param diaInicio : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable diaFin
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getDiaFin() {
		return diaFin;
	}

	/**
	 * @param diaFin : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable mesInicio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getMesInicio() {
		return mesInicio;
	}

	/**
	 * @param mesInicio : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setMesInicio(String mesInicio) {
		this.mesInicio = mesInicio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable mesFin
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getMesFin() {
		return mesFin;
	}

	/**
	 * @param mesFin : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setMesFin(String mesFin) {
		this.mesFin = mesFin;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable anioInicio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getAnioInicio() {
		return anioInicio;
	}

	/**
	 * @param anioInicio : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setAnioInicio(String anioInicio) {
		this.anioInicio = anioInicio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable anioFin
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public String getAnioFin() {
		return anioFin;
	}

	/**
	 * @param anioFin : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 03/01/2018
	 */
	public void setAnioFin(String anioFin) {
		this.anioFin = anioFin;
	}

}
