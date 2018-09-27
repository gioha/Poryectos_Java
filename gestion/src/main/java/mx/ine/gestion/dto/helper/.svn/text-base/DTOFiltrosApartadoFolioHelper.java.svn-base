/**
 * @(#)DTOApartadoFolioHelper.java 10/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Clase de ayuda en el módulo de Apartado de folios,
 * contiene los filtros que se utiliza al principio de la pantalla
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
public class DTOFiltrosApartadoFolioHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -3728414857783409487L;

	/* ------------------------------------- Filtros ------------------------------------ */

	/**
	 * Filtro que contiene el identificador del tipo de área al que se le apartaran los folios.
	 */
	private Integer tipoArea;

	/**
	 * Filtro que contiene el identificador de la entidad a la que se le apartaran los folios.
	 */
	private Integer idEstado;

	/**
	 * Filtro que contiene el identificador del área a la que se le apartaran los folios.
	 */
	private Integer idArea;

	/**
	 * Filtro que contiene el identificador del tipo de documento al que se le apartan los folios.
	 */
	private Integer idTipoDocumento;

	/**
	 * Filtro que contiene el identificador del acronimo al que se le apartan los folios.
	 */
	private Integer idAcronimo;

	/**
	 * Filtro que contiene el número de folios que se van a apartar.
	 */
	private String numeroFoliosAApartar;

	/**
	 * Es el folio generado para el documento (ya con la estructura del acronimo que se selecciono)
	 */
	private String folioGenerado;

	/* -------------------------------- Listas de cátalogos ----------------------------- */

	/**
	 * Lista que contiene los tipos de área con las que se filtra en la pantalla
	 * todas estas áreas provienen del catálogo de C_TIPOS_AREA
	 */
	private List<DTOCTipoAreaEntity> listaTiposArea;
	
    /**
     * Lista que contiene las áreas con las que se filtra en la pantalla
     * todas estas áreas provienen del catálogo de C_AREAS.
     */
    private List<DTOCAreaEntity> listaAreas;

    /**
     * Lista que contiene las entidades con las que se filtra en la pantalla
     * todas estas entidades provienen del webservice que va al esquema de geográfico.
     */
	private List<DTOEstadosEntity> listaEstados;
	
	/**
	 * Lista que contiene los tipos de documento disponibles para el área.
	 * todos estos tipos de documento provienen del cátalogo de C_TIPOS_DOCUMENTO
	 */
	private List<DTOCTipoDocumentoEntity> listaTiposDocumento;

	/**
	 * Lista que contiene los acronimos disponibles para los tipos de documento
	 * seleccionados anteriormente. Todos estos acronimos provienen de la tabla ACRONIMOS
	 */
	private List<DTOAcronimoEntity> listaAcronimos;

	/**
	 * Contiene los folios apartados recientemente por el usuario
	 */
	private List<DTOApartadoNumDocEntity> listaApartadosRecientes;

	/* -------------------------------- getters & setters ----------------------------- */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEstado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroFoliosAApartar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public String getNumeroFoliosAApartar() {
		return numeroFoliosAApartar;
	}

	/**
	 * @param numeroFoliosAApartar : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setNumeroFoliosAApartar(String numeroFoliosAApartar) {
		this.numeroFoliosAApartar = numeroFoliosAApartar;
	}

	/**
	 * @return valor de tipo List<DTOCAreas> que esta contenido en la variable listaAreas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOCAreaEntity> getListaAreas() {
		return listaAreas;
	}

	/**
	 * @param listaAreas : valor que se ingresa a la variable de tipo List<DTOCAreas>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setListaAreas(List<DTOCAreaEntity> listaAreas) {
		this.listaAreas = listaAreas;
	}

	/**
	 * @return valor de tipo List<DTOEstados> que esta contenido en la variable listaEstados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOEstadosEntity> getListaEstados() {
		return listaEstados;
	}

	/**
	 * @param listaEstados : valor que se ingresa a la variable de tipo List<DTOEstadosEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setListaEstados(List<DTOEstadosEntity> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return valor de tipo List<DTOCTiposDocumentos> que esta contenido en la variable listaTiposDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOCTipoDocumentoEntity> getListaTiposDocumento() {
		return listaTiposDocumento;
	}

	/**
	 * @param listaTiposDocumento : valor que se ingresa a la variable de tipo List<DTOCTiposDocumentos>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setListaTiposDocumento(List<DTOCTipoDocumentoEntity> listaTiposDocumento) {
		this.listaTiposDocumento = listaTiposDocumento;
	}

	/**
	 * @return valor de tipo List<DTOAcronimoEntity> que esta contenido en la variable listaAcronimos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOAcronimoEntity> getListaAcronimos() {
		return listaAcronimos;
	}

	/**
	 * @param listaAcronimos : valor que se ingresa a la variable de tipo List<DTOAcronimoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setListaAcronimos(List<DTOAcronimoEntity> listaAcronimos) {
		this.listaAcronimos = listaAcronimos;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaTiposArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOCTipoAreaEntity> getListaTiposArea() {
		return listaTiposArea;
	}

	/**
	 * @param listaTiposArea : valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setListaTiposArea(List<DTOCTipoAreaEntity> listaTiposArea) {
		this.listaTiposArea = listaTiposArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable folioGenerado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public String getFolioGenerado() {
		return folioGenerado;
	}

	/**
	 * @param folioGenerado : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public void setFolioGenerado(String folioGenerado) {
		this.folioGenerado = folioGenerado;
	}

	/**
	 * @return valor de tipo List<DTOApartadoNumDocEntity> que esta contenido en la variable listaApartadosRecientes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/12/2017
	 */
	public List<DTOApartadoNumDocEntity> getListaApartadosRecientes() {
		return listaApartadosRecientes;
	}

	/**
	 * @param listaApartadosRecientes : valor que se ingresa a la variable de tipo List<DTOApartadoNumDocEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/12/2017
	 */
	public void setListaApartadosRecientes(
			List<DTOApartadoNumDocEntity> listaApartadosRecientes) {
		this.listaApartadosRecientes = listaApartadosRecientes;
	}

}
