/**
 * @(#)DTOCapturaOficialiaHelper.java 12/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;

/**
 * Clase DTO Helper para el módulo de captura de documento de oficialía
 * @author David Rodríguez Corral
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public class DTOCapturaOficialiaHelper implements Serializable {
	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = -9134855213996507544L;
	
	/**
	 * Lista que contiene los tipos de documentos
	 */
	private List<DTOCTipoDocumentoEntity> listaDTOTipoDocumento;
	
	/**
	 * Lista que contiene las areas de una oficialía
	 */
	private List<DTOCAreaEntity> listaDTOAreas;
	
	/**
	 * Lista que contiene los tipos de área
	 */
	private List<DTOCTipoAreaEntity> listaDTOTipoArea;

	/**
	 * Lista que contiene las secciones para clasificación de un documento
	 */
	private List<DTOCSeccionesEntity> listaDTOSecciones;
	
	/**
	 * Atributo que contiene la sección de clasificación seleccionada por el usuario
	 */
	private Integer seccionSeleccionada;
	
	/**
	 * Lista que contiene las categorías para la asignación de un folio
	 */
	private List<DTOCCategoriasEntity> listaDTOCategorias;
	
	/**
	 * Atributo que contiene la categoría de clasificación seleccionada por el usuario
	 */
	private Integer categoriaSeleccionada;
	
	/**
	 * Lista que contiene las secciones para clasificación de un documento
	 */
	private String nombreDocumento;
	
	/**
	 * Lista que contiene las secciones para clasificación de un documento
	 */
	private double tamanioDocumento;

	/* -------------------------------- Atributos utilizados por actualización de vistas ----------------------------- */
	/**
	 * Lista de documentos anexos insertados por parte del usuario, dicha lista
	 * es utilizada para enviar y guardar cada uno de estos documentos.
	 */
	private List<DTODocumentoAnexoHelper> docsAnexos;
	
	/**
	 * Atributo que contiene el nombre temporal del documento adjunto
	 */
	private String nombreTemporalDoc;

	/**
	 * Boolean para habilitar un componente
	 */
	private Boolean habilitarComponente;

	/**
	 * String para nombre del remitente externo
	 */
	private String nombreRemitenteExt;

	/**
	 * String para dependencia del remitente externo
	 */
	private String dependenciaRemitenteExt;

	/**
	 * Lista que contiene a los remitentes externos encontrados
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtEncontrados;

	/**
	 * Lista que contiene a los remitentes externos seleccionados
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtSeleccionados;

	/**
	 * Lista que contiene a los remitentes externos agregados
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtAgregados;

	/**
	 * DTO que contiene el detalle del remitente externo seleccionado
	 */
	private DTORemitentesExternosOfEntity remitenteExt;

	/**
	 * Atributo que contiene la Fecha de recepción del documento físico por parte de la oficialía
	 */
	private Date fechaRecepcion;

	/**
	 * Atributo que contiene la hora de recepción del documento físico por parte de la oficialía
	 */
	private Integer hrRecepcion;

	/**
	 * Atributo que contiene los minutos de recepción del documento físico por parte de la oficialía
	 */
	private Integer minRecepcion;
	
	/**
	 * @return valor de tipo List<DTOCTipoDocumentoEntity> que esta contenido en la variable listaDTOTipoDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCTipoDocumentoEntity> getListaDTOTipoDocumento() {
		return listaDTOTipoDocumento;
	}

	/**
	 * @param listaDTOTipoDocumento: valor que se ingresa a la variable de tipo List<DTOCTipoDocumentoEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaDTOTipoDocumento(
			List<DTOCTipoDocumentoEntity> listaDTOTipoDocumento) {
		this.listaDTOTipoDocumento = listaDTOTipoDocumento;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaDTOAreas
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> getListaDTOAreas() {
		return listaDTOAreas;
	}

	/**
	 * @param listaDTOAreas: valor que se ingresa a la variable de tipo List<DTOCAreaEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaDTOAreas(List<DTOCAreaEntity> listaDTOAreas) {
		this.listaDTOAreas = listaDTOAreas;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaDTOTipoArea
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCTipoAreaEntity> getListaDTOTipoArea() {
		return listaDTOTipoArea;
	}

	/**
	 * @param listaDTOTipoArea: valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaDTOTipoArea(List<DTOCTipoAreaEntity> listaDTOTipoArea) {
		this.listaDTOTipoArea = listaDTOTipoArea;
	}

	/**
	 * @return valor de tipo List<DTOCSeccionesEntity> que esta contenido en la variable listaDTOSecciones
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public List<DTOCSeccionesEntity> getListaDTOSecciones() {
		return listaDTOSecciones;
	}

	/**
	 * @param listaDTOSecciones: valor que se ingresa a la variable de tipo List<DTOCSeccionesEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 18/01/2018
	 */
	public void setListaDTOSecciones(List<DTOCSeccionesEntity> listaDTOSecciones) {
		this.listaDTOSecciones = listaDTOSecciones;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable seccionSeleccionada
	 * @author David Rodríguez Corral
	 * @update José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public Integer getSeccionSeleccionada() {
		return seccionSeleccionada;
	}

	/**
	 * @param seccionSeleccionada: valor que se ingresa a la variable de tipo String 
	 * @author David Rodríguez Corral
	 * @update José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void setSeccionSeleccionada(Integer seccionSeleccionada) {
		this.seccionSeleccionada = seccionSeleccionada;
	}

	/**
	 * @return valor de tipo List<DTOCCategoriasEntity> que esta contenido en la variable listaDTOCategorias
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public List<DTOCCategoriasEntity> getListaDTOCategorias() {
		return listaDTOCategorias;
	}

	/**
	 * @param listaDTOCategorias: valor que se ingresa a la variable de tipo List<DTOCSeccionesEntity> 
	 *
	 * @author David Rodríguez Corral
	 * @since 24/01/2018
	 */
	public void setListaDTOCategorias(List<DTOCCategoriasEntity> listaDTOCategorias) {
		this.listaDTOCategorias = listaDTOCategorias;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable categoriaSeleccionada
	 * @author David Rodríguez Corral
	 * @update José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public Integer getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	/**
	 * @param categoriaSeleccionada: valor que se ingresa a la variable de tipo String
	 * @author David Rodríguez Corral
	 * @update José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void setCategoriaSeleccionada(Integer categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 26/01/2018
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * @param nombreDocumento: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 26/01/2018
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tamanioDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 26/01/2018
	 */
	public double getTamanioDocumento() {
		return tamanioDocumento;
	}

	/**
	 * @param tamanioDocumento: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 26/01/2018
	 */
	public void setTamanioDocumento(double tamanioArchivo) {
		this.tamanioDocumento = tamanioArchivo;
	}

	public List<DTODocumentoAnexoHelper> getDocsAnexos() {
		return docsAnexos;
	}

	public void setDocsAnexos(List<DTODocumentoAnexoHelper> docsAnexos) {
		this.docsAnexos = docsAnexos;
	}

	public String getNombreTemporalDoc() {
		return nombreTemporalDoc;
	}

	public void setNombreTemporalDoc(String nombreTemporalDoc) {
		this.nombreTemporalDoc = nombreTemporalDoc;
	}

	public Boolean getHabilitarComponente() {
		return habilitarComponente;
	}

	public void setHabilitarComponente(Boolean habilitarComponente) {
		this.habilitarComponente = habilitarComponente;
	}

	public String getNombreRemitenteExt() {
		return nombreRemitenteExt;
	}

	public void setNombreRemitenteExt(String nombreRemitenteExt) {
		this.nombreRemitenteExt = nombreRemitenteExt;
	}

	public String getDependenciaRemitenteExt() {
		return dependenciaRemitenteExt;
	}

	public void setDependenciaRemitenteExt(String dependenciaRemitenteExt) {
		this.dependenciaRemitenteExt = dependenciaRemitenteExt;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtEncontrados() {
		return remitentesExtEncontrados;
	}

	public void setRemitentesExtEncontrados(List<DTORemitentesExternosOfEntity> remitentesExtEncontrados) {
		this.remitentesExtEncontrados = remitentesExtEncontrados;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtSeleccionados() {
		return remitentesExtSeleccionados;
	}

	public void setRemitentesExtSeleccionados(List<DTORemitentesExternosOfEntity> remitentesExtSeleccionados) {
		this.remitentesExtSeleccionados = remitentesExtSeleccionados;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtAgregados() {
		return remitentesExtAgregados;
	}

	public void setRemitentesExtAgregados(List<DTORemitentesExternosOfEntity> remitentesExtAgregados) {
		this.remitentesExtAgregados = remitentesExtAgregados;
	}

	public DTORemitentesExternosOfEntity getRemitenteExt() {
		return remitenteExt;
	}

	public void setRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		this.remitenteExt = remitenteExt;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Integer getHrRecepcion() {
		return hrRecepcion;
	}

	public void setHrRecepcion(Integer hrRecepcion) {
		this.hrRecepcion = hrRecepcion;
	}

	public Integer getMinRecepcion() {
		return minRecepcion;
	}

	public void setMinRecepcion(Integer minRecepcion) {
		this.minRecepcion = minRecepcion;
	}

}
