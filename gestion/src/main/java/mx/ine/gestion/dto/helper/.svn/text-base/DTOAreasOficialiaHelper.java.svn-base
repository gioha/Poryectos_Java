/**
 * @(#)DTOAreasOficialiaHelper.java 12/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Clase DTO Helper para el módulo de captura de documento de oficialía
 * 
 * @update David Rodríguez Corral
 * @since 18/01/2018
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public class DTOAreasOficialiaHelper implements Serializable {

	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = -771737841010585225L;
	
	/**
	 * String que guarda el tipo de busqueda I=Interno, E=Externo
	 */
	private String nombreAreaDestinatario;
	
	/**
	 * String que guarda el tipo de busqueda I=Interno, E=Externo
	 */
	private String tipo;
	
	/**
	 * Boolean para mostrar los campos de remitentes internos o externos
	 */
	private Boolean mostrarInternoExterno;
	
	/**
	 * String que guarda el nombre de una persona
	 */
	private String nombre;
	
	/**
	 * String que guarda los apellidos de una persona
	 */
	private String apellidos;

	/**
	 * String que guarda la dependencia de una persona
	 */
	private String dependencia;

	/**
	 * String que guarda el tipo de área seleccionada
	 */
	private String tipoAreaSeleccionada;
	
	/**
	 * Lista que guarda la lista de entidades
	 */
	private List<DTOEstadosEntity> listaEstados;
	
	/**
	 * String que guarda el estado seleccionada
	 */
	private String entidadSeleccionada;
	
	/**
	 * Boolean que habilita el combo de estados
	 */
	private Boolean habilitarEstados;
	
	/**
	 * Lista que guarda la lista areas
	 */
	private List<DTOCAreaEntity> listaAreas;
	
	/**
	 * SLista que guarda el area seleccionada
	 */
	private String areaSeleccionada;

	/********************************************** Remitentes ***********************************************/
	/**
	 * Lista que guarda las personas remitentes del documento fisico
	 */
	private List<DTOEstructuraAreasEntity> personasSeleccionadas;
	
	/**
	 * Lista que guarda las personas encontradas en la búsqueda
	 */
	private List<DTOEstructuraAreasEntity> personasBusquedasEncontradas;
	
	/**
	 * Atributo que contiene a los remitentes que iran en el documento
	 */
	private List<DTOEstructuraAreasEntity> personas;
	
	/**
	 * @return valor de tipo String que esta contenido en la variable tipoAreaSeleccionada
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public String getTipoAreaSeleccionada() {
		return tipoAreaSeleccionada;
	}

	/**
	 * @param tipoAreaSeleccionada: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setTipoAreaSeleccionada(String tipoAreaSeleccionada) {
		this.tipoAreaSeleccionada = tipoAreaSeleccionada;
	}

	/**
	 * @return valor de tipo List<DTOEstadosEntity> que esta contenido en la variable listaEstados
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> getListaEstados() {
		return listaEstados;
	}

	/**
	 * @param listaEstados: valor que se ingresa a la variable de tipo List<DTOEstadosEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaEstados(List<DTOEstadosEntity> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable entidadSeleccionada
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public String getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	/**
	 * @param entidadSeleccionada: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setEntidadSeleccionada(String entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaAreas
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> getListaAreas() {
		return listaAreas;
	}

	/**
	 * @param listaAreas: valor que se ingresa a la variable de tipo List<DTOCAreaEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaAreas(List<DTOCAreaEntity> listaAreas) {
		this.listaAreas = listaAreas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable areaSeleccionada
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public String getAreaSeleccionada() {
		return areaSeleccionada;
	}

	/**
	 * @param areaSeleccionada: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setAreaSeleccionada(String areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable habilitarEstados
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Boolean getHabilitarEstados() {
		return habilitarEstados;
	}

	/**
	 * @param habilitarEstados: valor que se ingresa a la variable de tipo Boolean 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setHabilitarEstados(Boolean habilitarEstados) {
		this.habilitarEstados = habilitarEstados;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombre
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidos
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipo
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable mostrarInternoExterno
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Boolean getMostrarInternoExterno() {
		return mostrarInternoExterno;
	}

	/**
	 * @param mostrarInternoExterno: valor que se ingresa a la variable de tipo Boolean 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setMostrarInternoExterno(Boolean mostrarInternoExterno) {
		this.mostrarInternoExterno = mostrarInternoExterno;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personasBusquedasEncontradas
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonasBusquedasEncontradas() {
		return personasBusquedasEncontradas;
	}

	/**
	 * @param personasBusquedasEncontradas: valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void setPersonasBusquedasEncontradas(
			List<DTOEstructuraAreasEntity> personasBusquedasEncontradas) {
		this.personasBusquedasEncontradas = personasBusquedasEncontradas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personasSeleccionadas
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonasSeleccionadas() {
		return personasSeleccionadas;
	}

	/**
	 * @param personasSeleccionadas: valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void setPersonasSeleccionadas(
			List<DTOEstructuraAreasEntity> personasSeleccionadas) {
		this.personasSeleccionadas = personasSeleccionadas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreAreaDestinatario
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public String getNombreAreaDestinatario() {
		return nombreAreaDestinatario;
	}

	/**
	 * @param nombreAreaDestinatario: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 23/01/2018
	 */
	public void setNombreAreaDestinatario(String nombreAreaDestinatario) {
		this.nombreAreaDestinatario = nombreAreaDestinatario;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personas
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonas() {
		return personas;
	}

	/**
	 * @param personas: valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public void setPersonas(List<DTOEstructuraAreasEntity> personas) {
		this.personas = personas;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

}
