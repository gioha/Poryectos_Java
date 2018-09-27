/**
 * @(#)DTOFiltrosAcronimosHelper.java 10/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Clase de ayuda en el módulo de Acronimos,
 * contiene los filtros que se utiliza al principio de la pantalla
 * 
 * @author Roberto Shirásago Domínguez
 * @since 10/01/2018
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public class DTOFiltrosAcronimosHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -5145502077421864094L;

	/* ------------------------------------- Filtros ------------------------------------ */

	/**
	 * Tipo de area que selecciono el usuario con la cual dará de alta un tipo de área
	 */
	private Integer tipoAreaSeleccionada;

	/**
	 * Atributo con el id del área seleccionada
	 */
	private Integer idAreaSeleccionada;

	/**
	 * Atributo con el id de la entidad seleccionada
	 */
	private Integer idEntidadSeleccionada;

	/* -------------------------------- Listas de cátalogos ----------------------------- */

	/**
	 * Lista con los tipos de área que se utilizan en los filtros
	 */
	private List<DTOCTipoAreaEntity> listaTiposAreas;
	
	/**
	 * Atributo que contiene la lista de áreas que se utiliza en los filtros
	 */
	private List<DTOCAreaEntity> listaAreas;

	/**
	 * Atributo que contiene la lista de estados que se utilizan en los filtros
	 */
	private List<DTOEstadosEntity> listaEstados;

	/**
	 * Atributo que contiene una lista con los tipos de documentos
	 */
	private List<DTOCTipoDocumentoEntity> listaTiposDocumentos;

	/**
	 * Atributo con los posibles formatos para el número
	 */
	private HashMap<String, String> formatosAnio;

	/* ------------------------------ Atributos por actualización de vista ------------------------------ */
	/**
	 * Atributo con el id del área de acrónimos seleccionada
	 */
	private Integer idAreaAcronimos;
	/**
	 * Atributo que contiene la lista de áreas de acrónimos que se utiliza en los filtros
	 */
	private List<DTOCAreaEntity> listaAreasAcronimos;

	/**
	 * Atributo que contiene la lista de áreas de acrónimos que se utiliza en los filtros
	 */
	private List<DTOAcronimoEntity> listaAcronimosArea;

	/**
	 * Atributo que contiene la lista de áreas de acrónimos filtrados
	 */
	private List<DTOAcronimoEntity> listaAcronimosFiltrados;


	/* -------------------------------- Getters & Setters ----------------------------- */
	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaSeleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public Integer getTipoAreaSeleccionada() {
		return tipoAreaSeleccionada;
	}

	/**
	 * @param tipoAreaSeleccionada : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setTipoAreaSeleccionada(Integer tipoAreaSeleccionada) {
		this.tipoAreaSeleccionada = tipoAreaSeleccionada;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaSeleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public Integer getIdAreaSeleccionada() {
		return idAreaSeleccionada;
	}

	/**
	 * @param idAreaSeleccionada : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setIdAreaSeleccionada(Integer idAreaSeleccionada) {
		this.idAreaSeleccionada = idAreaSeleccionada;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidadSeleccionada
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public Integer getIdEntidadSeleccionada() {
		return idEntidadSeleccionada;
	}

	/**
	 * @param idEntidadSeleccionada : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setIdEntidadSeleccionada(Integer idEntidadSeleccionada) {
		this.idEntidadSeleccionada = idEntidadSeleccionada;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaAreas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public List<DTOCAreaEntity> getListaAreas() {
		return listaAreas;
	}

	/**
	 * @param listaAreas : valor que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setListaAreas(List<DTOCAreaEntity> listaAreas) {
		this.listaAreas = listaAreas;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaTiposAreas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public List<DTOCTipoAreaEntity> getListaTiposAreas() {
		return listaTiposAreas;
	}

	/**
	 * @param listaTiposAreas : valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setListaTiposAreas(List<DTOCTipoAreaEntity> listaTiposAreas) {
		this.listaTiposAreas = listaTiposAreas;
	}

	/**
	 * @return valor de tipo List<DTOEstadosEntity> que esta contenido en la variable listaEstados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public List<DTOEstadosEntity> getListaEstados() {
		return listaEstados;
	}

	/**
	 * @param listaEstados : valor que se ingresa a la variable de tipo List<DTOEstadosEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setListaEstados(List<DTOEstadosEntity> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return valor de tipo List<DTOCTipoDocumentoEntity> que esta contenido en la variable listaTiposDocumentos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public List<DTOCTipoDocumentoEntity> getListaTiposDocumentos() {
		return listaTiposDocumentos;
	}

	/**
	 * @param listaTiposDocumentos : valor que se ingresa a la variable de tipo List<DTOCTipoDocumentoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setListaTiposDocumentos(
			List<DTOCTipoDocumentoEntity> listaTiposDocumentos) {
		this.listaTiposDocumentos = listaTiposDocumentos;
	}

	/**
	 * @return valor de tipo HashMap<String,String> que esta contenido en la variable formatosAnio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public HashMap<String, String> getFormatosAnio() {
		return formatosAnio;
	}

	/**
	 * @param formatosAnio : valor que se ingresa a la variable de tipo HashMap<String,String>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setFormatosAnio(HashMap<String, String> formatosAnio) {
		this.formatosAnio = formatosAnio;
	}

	public Integer getIdAreaAcronimos() {
		return idAreaAcronimos;
	}

	public void setIdAreaAcronimos(Integer idAreaAcronimos) {
		this.idAreaAcronimos = idAreaAcronimos;
	}

	public List<DTOCAreaEntity> getListaAreasAcronimos() {
		return listaAreasAcronimos;
	}

	public void setListaAreasAcronimos(List<DTOCAreaEntity> listaAreasAcronimos) {
		this.listaAreasAcronimos = listaAreasAcronimos;
	}

	public List<DTOAcronimoEntity> getListaAcronimosArea() {
		return listaAcronimosArea;
	}

	public void setListaAcronimosArea(List<DTOAcronimoEntity> listaAcronimosArea) {
		this.listaAcronimosArea = listaAcronimosArea;
	}

	public List<DTOAcronimoEntity> getListaAcronimosFiltrados() {
		return listaAcronimosFiltrados;
	}

	public void setListaAcronimosFiltrados(List<DTOAcronimoEntity> listaAcronimosFiltrados) {
		this.listaAcronimosFiltrados = listaAcronimosFiltrados;
	}

}
