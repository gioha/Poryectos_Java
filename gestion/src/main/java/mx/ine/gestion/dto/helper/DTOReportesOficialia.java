/**
 * @(#)DTOReportesOficialia.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que simula el comportamiento de un DTOReportesOficialia, necesaria para soportar
 * la funcionalidad de la vista de Reportes de Oficialía - Sección Resultados de la búsqueda.
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
public class DTOReportesOficialia implements Serializable {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -5290682857575616325L;

	/* ------------------------------ Atributos de la clase ------------------------------ */
	/**
	 * Atributo que mapea la columna de folio de oficialía generada por Hibernate
	 */
	private String folioOficialia;
	/**
	 * Atributo que mapea la columna de número de documento generada por Hibernate
	 */
	private String numDocumento;
	/**
	 * Atributo que mapea la columna de asunto generada por Hibernate
	 */
	private String asunto;
	/**
	 * Atributo que mapea la columna de copias de conocimiento generada por Hibernate
	 */
	private String personaCC;
	/**
	 * Atributo que contiene la columna de fecha de creación del documento generada por Hibernate
	 */
	private Date fechaCreacion;
	/**
	 * Atributo que contiene la columna de tipo de documento generada por Hibernate
	 */
	private String tipoDocumento;
	/**
	 * Atributo que contiene la columna de sección generada por Hibernate
	 */
	private String descSeccion;
	/**
	 * Atributo que contiene la columna de categoría generada por Hibernate
	 */
	private String descCategoria;
	/**
	 * Atributo que contiene la columna de área generada por Hibernate
	 */
	private String descArea;
	/**
	 * Atributo que contiene la columna de tipo de área generada por Hibernate
	 */
	private String tipoArea;
	/**
	 * Atributo que contiene la columna de remitente generada por Hibernate
	 */
	private String personaRemitente;
	/**
	 * Atributo que contiene la columna de remitente externo generada por Hibernate
	 */
	private String personaRemitenteExt;
	/**
	 * Atributo que contiene la columna de folio de destinatario generada por Hibernate
	 */
	private String personaDestinataria;
	/**
	 * Atributo que contiene la lista procesada de remitentes
	 */
	private ArrayList<String> listaRemitentes;
	/**
	 * Atributo que contiene la lista procesada de remitentes externos
	 */
	private ArrayList<String> listaRemitentesExt;
	/**
	 * Atributo que contiene la lista procesada de destinatarios
	 */
	private ArrayList<String> listaDestinatarios;
	/**
	 * Atributo que contiene la lista procesada de copias de conocimiento
	 */
	private ArrayList<String> listaCopiasConocimiento;

	public DTOReportesOficialia() {
		folioOficialia			= "";
		numDocumento			= "";
		asunto					= "";
		personaCC				= "";
		fechaCreacion			= new Date();
		tipoDocumento			= "";
		descSeccion				= "";
		descCategoria			= "";
		descArea				= "";
		tipoArea				= "";
		personaRemitente		= "";
		personaRemitenteExt		= "";
		personaDestinataria		= "";
		listaRemitentes			= new ArrayList<String>();
		listaRemitentesExt		= new ArrayList<String>();
		listaDestinatarios		= new ArrayList<String>();
		listaCopiasConocimiento	= new ArrayList<String>();
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public String getFolioOficialia() {
		return folioOficialia;
	}

	public void setFolioOficialia(String folioOficialia) {
		this.folioOficialia = folioOficialia;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getPersonaCC() {
		return personaCC;
	}

	public void setPersonaCC(String personaCC) {
		this.personaCC = personaCC;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescSeccion() {
		return descSeccion;
	}

	public void setDescSeccion(String descSeccion) {
		this.descSeccion = descSeccion;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public String getDescArea() {
		return descArea;
	}

	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}

	public String getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	public String getPersonaRemitente() {
		return personaRemitente;
	}

	public void setPersonaRemitente(String personaRemitente) {
		this.personaRemitente = personaRemitente;
	}

	public String getPersonaRemitenteExt() {
		return personaRemitenteExt;
	}

	public void setPersonaRemitenteExt(String personaRemitenteExt) {
		this.personaRemitenteExt = personaRemitenteExt;
	}

	public String getPersonaDestinataria() {
		return personaDestinataria;
	}

	public void setPersonaDestinataria(String personaDestinataria) {
		this.personaDestinataria = personaDestinataria;
	}

	public ArrayList<String> getListaRemitentes() {
		return listaRemitentes;
	}

	public void setListaRemitentes(ArrayList<String> listaRemitentes) {
		this.listaRemitentes = listaRemitentes;
	}

	public ArrayList<String> getListaRemitentesExt() {
		return listaRemitentesExt;
	}

	public void setListaRemitentesExt(ArrayList<String> listaRemitentesExt) {
		this.listaRemitentesExt = listaRemitentesExt;
	}

	public ArrayList<String> getListaDestinatarios() {
		return listaDestinatarios;
	}

	public void setListaDestinatarios(ArrayList<String> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public ArrayList<String> getListaCopiasConocimiento() {
		return listaCopiasConocimiento;
	}

	public void setListaCopiasConocimiento(ArrayList<String> listaCopiasConocimiento) {
		this.listaCopiasConocimiento = listaCopiasConocimiento;
	}

}
