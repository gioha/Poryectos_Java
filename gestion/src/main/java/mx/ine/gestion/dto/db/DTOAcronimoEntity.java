/**
 * @(#)DTOAcronimoEntity.java 05/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.dto.DTOPaginarLazyInterface;


/**
 * Clase de la capa de MB para el módulo captura de documento. TODO
 * 
 * @author Sergio Ley Garcia
 * @since 05/09/2017
 */
@Entity
@Table(name = "ACRONIMOS", schema = "gestion4")
@IdClass(value = DTOAcronimoID.class)
public class DTOAcronimoEntity extends DTOBase implements DTOPaginarLazyInterface {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Atributo que guarda el ID del acrónimo
	 */
	@Id
	@Column(name = "ID_ACRONIMO")
	private Integer	idAcronimo;

	/**
	 * Atributo que contiene el identificador del área a la que pertenece el
	 * acronimo
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que contiene el identificador del tipo área a la que pertenece
	 * el acronimo (puede ser organo centra y organo desconcentrado)
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Atributo que contiene el identificador del tipo de documento (atenta nota, circular, etc)
	 */
	@Id
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Integer idTipoDocumento;
	
	/**
	 * Atributo que guarda el area de la persona que crea el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "ID_AREA", referencedColumnName = "ID_AREA"),
			@JoinColumn(updatable = false, insertable = false, name = "TIPO_AREA", referencedColumnName = "TIPO_AREA")
	})
	private DTOCAreaEntity area;
	
	/**
	 * Atributo que guarda el tipo del documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_DOCUMENTO", columnDefinition = "ID_TIPO_DOCUMENTO"  ,insertable = false, updatable = false)
	private DTOCTipoDocumentoEntity	dtoTipoDocumento;
	
	/**
	 * Atributo que guarda descripción del acrónimo
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion;

	/*
	 * Atributo que guarda la agrupación del acrónimo
	 */
	
	@Column(name = "ACRONIMO_AGRUPACION")
	private String acronimoAgrupacion;

	/**
	 * Atributo que guarda el usuario que creo el documento
	 */
	@Column(name = "USUARIO")
	private String	usuario;

	/**
	 * Atributo que guarda la fecha de creación del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	
	/* ********************** Atributos transient ********************** */

	/*
	 * Atributo que guarda el número de veces de un acronimo en una plantilla por persona
	 */
	@Transient
	private Integer numeroRepeticiones;

	/*
	 * Atributo que se utiliza para saber cuantos documentos estan siendo ocupados por este acronimo.
	 */
	@Transient
	private Integer documentosUtilizados;
	
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOAcronimoEntity [idAcronimo=" + idAcronimo + ", idArea="
				+ idArea + ", tipoArea=" + tipoArea + ", idTipoDocumento="
				+ idTipoDocumento + ", area=" + area + ", dtoTipoDocumento="
				+ dtoTipoDocumento + ", descripcion=" + descripcion
				+ ", acronimoAgrupacion=" + acronimoAgrupacion + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora
				+ ", numeroRepeticiones=" + numeroRepeticiones
				+ ", documentosUtilizados=" + documentosUtilizados + "]";
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAcronimo == null) ? 0 : idAcronimo.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
		return result;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOAcronimoEntity other = (DTOAcronimoEntity) obj;
		if (idAcronimo == null) {
			if (other.idAcronimo != null)
				return false;
		} else if (!idAcronimo.equals(other.idAcronimo))
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idTipoDocumento == null) {
			if (other.idTipoDocumento != null)
				return false;
		} else if (!idTipoDocumento.equals(other.idTipoDocumento))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOPaginarLazyInterface#getRowKey()
	 */
	@Override
	public String getRowKey() {
		
		return String.valueOf(this.hashCode());
	}

	/**
	 * @return idAcronimo
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public Integer getIdAcronimo() {

		return idAcronimo;
	}

	/**
	 * @param idAcronimo
	 *            el ID del acrónimo
	 * 
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public void setIdAcronimo(Integer idAcronimo) {

		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return area
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public DTOCAreaEntity getArea() {

		return area;
	}

	/**
	 * @param area
	 *            el area de la persona que crea el documento
	 * 
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public void setArea(DTOCAreaEntity area) {

		this.area = area;
	}

	/**
	 * @return descripcion
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public String getDescripcion() {

		return descripcion;
	}

	/**
	 * @param descripcion
	 *            descripción del acrónimo
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	/**
	 * @return usuario
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	@Override
	public String getUsuario() {

		return usuario;
	}

	/**
	 * @param usuario
	 *            usuario que creo el documento
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	@Override
	public void setUsuario(String usuario) {

		this.usuario = usuario;
	}

	/**
	 * @return fechaHora
	 */
	@Override
	public Date getFechaHora() {

		return fechaHora;
	}

	/**
	 * @param fechaHora
	 *            la fecha de creación del documento
	 * @author Sergio Ley Garcia
	 * @since 05/09/2017
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo DTOCTiposDocumentos que esta contenido en la
	 *         variable dtoTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public DTOCTipoDocumentoEntity getDtoTipoDocumento() {
		return dtoTipoDocumento;
	}

	/**
	 * @param dtoTipoDocumento
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOCTiposDocumentos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/10/2017
	 */
	public void setDtoTipoDocumento(DTOCTipoDocumentoEntity dtoTipoDocumento) {
		this.dtoTipoDocumento = dtoTipoDocumento;
	}

	/**
	 * @return the acronimoAgrupacion
	 */
	public String getAcronimoAgrupacion() {
		return acronimoAgrupacion;
	}

	/**
	 * @param acronimoAgrupacion the acronimoAgrupacion to set
	 */
	public void setAcronimoAgrupacion(String acronimoAgrupacion) {
		this.acronimoAgrupacion = acronimoAgrupacion;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable numeroRepeticiones
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public Integer getNumeroRepeticiones() {
		return numeroRepeticiones;
	}

	/**
	 * @param numeroRepeticiones : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 13/12/2017
	 */
	public void setNumeroRepeticiones(Integer numeroRepeticiones) {
		this.numeroRepeticiones = numeroRepeticiones;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosUtilizados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public Integer getDocumentosUtilizados() {
		return documentosUtilizados;
	}

	/**
	 * @param documentosUtilizados : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setDocumentosUtilizados(Integer documentosUtilizados) {
		this.documentosUtilizados = documentosUtilizados;
	}

	/**
	 * Método para poder ingresar la descripción del area al objeto c_area
	 * 
	 * @param descripcionArea: valor que entra al objeto
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setDescripcionArea(String descripcionArea) {
		
		if (this.area == null) {
			this.area = new DTOCAreaEntity();
		}
		
		this.area.setDescripcion(descripcionArea);
	}

	/**
	 * Método para poder ingresar la descripción del tipo documento al objeto c_tipo_documentos
	 * 
	 * @param descripcionTipoDoc: valor que entra al objeto
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public void setDescripcionTipoDoc(String descripcionTipoDoc) {

		if (this.dtoTipoDocumento == null) {
			this.dtoTipoDocumento = new DTOCTipoDocumentoEntity();
		}

		this.dtoTipoDocumento.setDescripcion(descripcionTipoDoc);
	}
}
