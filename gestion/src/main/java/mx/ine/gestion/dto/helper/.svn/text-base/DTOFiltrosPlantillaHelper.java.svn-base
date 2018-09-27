/**
 * @(#)DTOPlantillaForm.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;


/**
 * Clase de ayuda en el módulo de Configuración de plantillas,
 * contiene los campos del formulario de la pantalla de Plantillas.
 * 
 * @author Luis Urrutia
 * @since 05/12/2017
 */
public class DTOFiltrosPlantillaHelper implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -4341039001708948958L;

	/* ------------------------------------- Filtros ------------------------------------ */
	
	/**
	 * Variable que contiene el id del tipo de documento seleccionado en el comboBox
	 */
	private Integer idTipoDocumentoSeleccionado;
	
	/**
	 * Objeto que contiene el id del acrónimo seleccionado en el comboBox y el número
	 * de veces que se repite en la plantilla por persona
	 */
	private DTOAcronimoEntity acronimoSeleccionado;

	/**
	 * Variable que contiene el id del tipo de área
	 */
	private Integer tipoArea;
	
	/**
	 * Variable que contiene el id del área
	 */
	private Integer idArea;
	
	/**
	 * Variable que contiene el id de la entidad
	 */
	private Integer idEntidad;
	
	/**
	 * Variable que contiene el nombre de la plantilla
	 */
	private String nombrePlantilla;
	
	/**
	 * Variable que contiene el id de la persona logueada
	 */
	private Integer idPersona;

	/**
	 * Variable que contiene el hipervinculo del documento temporal con la que se crean las plantillas
	 */
	private String hyperLinkDocumentoTmp;
	
	/**
	 * Bandera que indica si se pueden ver los elementos que se utilizan despues de dar clic en el botón "Captura"
	 * y antes de dar clic en el botón "Agregar"
	 */
	private boolean visualizarElementosPosCaptura;
	
	/* -------------------------------- Listas de cátalogos ----------------------------- */

	/**
	 * Lista que contiene los tipos de documentos para el comboBox.
	 */
	private List<DTOCTipoDocumentoEntity> tipoDocumentos;

	/**
	 * Lista que contiene los acrónimos para el comboBox.
	 */
	private List<DTOAcronimoEntity> acronimos;
	
	/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumentoSeleccionado
	 *
	 * @author Luis Urrutia
	 * @since 05/12/2017
	 */
	public Integer getIdTipoDocumentoSeleccionado() {
		return idTipoDocumentoSeleccionado;
	}

	/**
	 * @param idTipoDocumentoSeleccionado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 05/12/2017
	 */
	public void setIdTipoDocumentoSeleccionado(Integer idTipoDocumentoSeleccionado) {
		this.idTipoDocumentoSeleccionado = idTipoDocumentoSeleccionado;
	}
	
	/**
	 * @return valor de tipo DTOAcronimoEntity que esta contenido en la variable acronimoSeleccionado
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public DTOAcronimoEntity getAcronimoSeleccionado() {
		return acronimoSeleccionado;
	}

	/**
	 * @param acronimoSeleccionado : valor que se ingresa a la variable de tipo DTOAcronimoEntity
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public void setAcronimoSeleccionado(DTOAcronimoEntity acronimoSeleccionado) {
		this.acronimoSeleccionado = acronimoSeleccionado;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidad
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public Integer getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 07/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable nombrePlantilla
	 *
	 * @author Luis Urrutia
	 * @since 08/12/2017
	 */
	public String getNombrePlantilla() {
		return nombrePlantilla;
	}

	/**
	 * @param nombrePlantilla : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 08/12/2017
	 */
	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo List<DTOCTipoDocumentoEntity> que esta contenido en la variable tipoDocumentos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public List<DTOCTipoDocumentoEntity> getTipoDocumentos() {
		return tipoDocumentos;
	}

	/**
	 * @param tipoDocumentos : valor que se ingresa a la variable de tipo List<DTOCTipoDocumentoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setTipoDocumentos(List<DTOCTipoDocumentoEntity> tipoDocumentos) {
		this.tipoDocumentos = tipoDocumentos;
	}

	/**
	 * @return valor de tipo List<DTOAcronimoEntity> que esta contenido en la variable acronimos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public List<DTOAcronimoEntity> getAcronimos() {
		return acronimos;
	}

	/**
	 * @param acronimos : valor que se ingresa a la variable de tipo List<DTOAcronimoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setAcronimos(List<DTOAcronimoEntity> acronimos) {
		this.acronimos = acronimos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable hyperLinkDocumentoTmp
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public String getHyperLinkDocumentoTmp() {
		return hyperLinkDocumentoTmp;
	}

	/**
	 * @param hyperLinkDocumentoTmp : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setHyperLinkDocumentoTmp(String hyperLinkDocumentoTmp) {
		this.hyperLinkDocumentoTmp = hyperLinkDocumentoTmp;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable visualizarElementosPosCaptura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public boolean isVisualizarElementosPosCaptura() {
		return visualizarElementosPosCaptura;
	}

	/**
	 * @param visualizarElementosPosCaptura : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/01/2018
	 */
	public void setVisualizarElementosPosCaptura(
			boolean visualizarElementosPosCaptura) {
		this.visualizarElementosPosCaptura = visualizarElementosPosCaptura;
	}

}
