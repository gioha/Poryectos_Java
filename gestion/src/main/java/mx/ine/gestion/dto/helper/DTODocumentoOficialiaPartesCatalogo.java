package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.db.DTOEstado;


/**
 * @(#)DTODocumentoOficialiaPartesCatalogo.java 13/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de MB para el módulo captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 13/11/2017
 */
public class DTODocumentoOficialiaPartesCatalogo implements Serializable{

	/**
	 * 
	 */
	private static final long			serialVersionUID	= -2339175388822719391L;
	/**
	 * 
	 */
	private List<DTOCAreaEntity>				areasIne			= new ArrayList<>();
	/**
	 * 
	 */
	private List<DTOCAreaEntity>				areasccp			= new ArrayList<>();
	/**
	 * 
	 */
	private List<DTOCAreaEntity>				areasDestinatario	= new ArrayList<>();
	/**
	 * 
	 */
	private List<DTOCAreaEntity>				areasRemitente		= new ArrayList<>();
	/**
	 * Atributo Catálogo que corresponde a la lista de entidades disponibles
	 */
	private List<DTOEstado>			entidades;
	/**
	 * Atributo Catálogo que corresponde a la lista de tipos de documento
	 * disponibles
	 */
	private List<DTOCTipoDocumentoEntity>	tipoDoc				= new ArrayList<>();
	/**
	 * 
	 */
	private List						categoria			= new ArrayList<>();
	/**
	 * 
	 */
	private List						prioridad			= new ArrayList<>();

	/**
	 * @return areasIne
	 */
	public List<DTOCAreaEntity> getAreasIne() {

		return areasIne;
	}

	/**
	 * @param areasIne
	 *            areasIne del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setAreasIne(List<DTOCAreaEntity> areasIne) {

		this.areasIne = areasIne;
	}

	/**
	 * @return areasccp
	 */
	public List<DTOCAreaEntity> getAreasccp() {

		return areasccp;
	}

	/**
	 * @param areasccp
	 *            areasccp del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setAreasccp(List<DTOCAreaEntity> areasccp) {

		this.areasccp = areasccp;
	}

	/**
	 * @return areasDestinatario
	 */
	public List<DTOCAreaEntity> getAreasDestinatario() {

		return areasDestinatario;
	}

	/**
	 * @param areasDestinatario
	 *            areasDestinatario del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setAreasDestinatario(List<DTOCAreaEntity> areasDestinatario) {

		this.areasDestinatario = areasDestinatario;
	}

	/**
	 * @return areasRemitente
	 */
	public List<DTOCAreaEntity> getAreasRemitente() {

		return areasRemitente;
	}

	/**
	 * @param areasRemitente
	 *            areasRemitente del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setAreasRemitente(List<DTOCAreaEntity> areasRemitente) {

		this.areasRemitente = areasRemitente;
	}

	/**
	 * @return entidades
	 */
	public List<DTOEstado> getEntidades() {

		return entidades;
	}

	/**
	 * @param entidades
	 *            entidades del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setEntidades(List<DTOEstado> entidades) {

		this.entidades = entidades;
	}

	/**
	 * @return tipoDoc
	 */
	public List<DTOCTipoDocumentoEntity> getTipoDoc() {

		return tipoDoc;
	}

	/**
	 * @param tipoDoc
	 *            tipoDoc del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setTipoDoc(List<DTOCTipoDocumentoEntity> tipoDoc) {

		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return categoria
	 */
	public List getCategoria() {

		return categoria;
	}

	/**
	 * @param categoria
	 *            categoria del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setCategoria(List categoria) {

		this.categoria = categoria;
	}

	/**
	 * @return prioridad
	 */
	public List getPrioridad() {

		return prioridad;
	}

	/**
	 * @param prioridad
	 *            prioridad del DTODocumentoOficialiaPartesCatalogo
	 */
	public void setPrioridad(List prioridad) {

		this.prioridad = prioridad;
	}
}
