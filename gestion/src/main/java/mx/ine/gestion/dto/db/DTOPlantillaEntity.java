/**
 * @(#)DTOPlantillaEntity.java 05/09/2017
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

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.dto.DTOPaginarLazyInterface;


/**
 * Clase de mapeo para la tabla PLANTILLAS.
 * 
 * @author Sergio Ley Garcia
 * @since 05/09/2017
 */
@Entity
@Table(name = "PLANTILLAS", schema = "gestion4")
@IdClass(value = DTOPlantillaID.class)
public class DTOPlantillaEntity extends DTOBase implements DTOPaginarLazyInterface {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID	= 637063951383153591L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Atributo que contiene la llave primaria de la tabla
	 */
	@Id
	@Column(name = "ID_PLANTILLA")
	private Integer	idPlantilla;

	/**
	 * Area del usuario al que pertenece la plantilla
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer	idArea;

	/**
	 * Tipo de área del usuario al que pertenece la planilla
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer	tipoArea;

	/**
	 * Atributo que guarda el objeto con la información del área de la persona que crea la plantilla.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "ID_AREA", referencedColumnName = "ID_AREA"),
			@JoinColumn(updatable = false, insertable = false, name = "TIPO_AREA", referencedColumnName = "TIPO_AREA")})
	private DTOCAreaEntity area;

	/**
	 * Identificador del tipo de documento del usuario al que pertenece la plantilla
	 */
	@Id
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Integer idTipoDocumento;

	/**
	 * Atributo que guarda el tipo del documento al que corresponde la la
	 * plantilla.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, nullable = false, name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
	private DTOCTipoDocumentoEntity	tipoDocumento;

	/**
	 * Identificador del acronimo que pertenece al dueño de la plantillas
	 */
	@Id
	@Column(name = "ID_ACRONIMO")
	private Integer	idAcronimo;

	/**
	 * Atributo que guarda el acrónimo del documento al que corresponde la la
	 * plantilla.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA", insertable = false, updatable = false),
			@JoinColumn(name = "TIPO_AREA", referencedColumnName = "TIPO_AREA", insertable = false, updatable = false),
			@JoinColumn(name = "ID_ACRONIMO", referencedColumnName = "ID_ACRONIMO", insertable = false, updatable = false),
			@JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO", insertable = false, updatable = false)})
	private DTOAcronimoEntity acronimo;

	/**
	 * Identificador de la persona dueño de la plantilla
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer	idPersona;

	/**
	 * Atributo que guarda la persona que crea la plantilla.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, nullable = false, name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
	private DTOEstructuraAreasEntity persona;

	/**
	 * Atributo que guarda el nombre de la plantilla.
	 */
	@Column(name = "NOMBRE_PLANTILLA")
	private String	nombrePlantilla;

	/**
	 * Atributo que guarda el usuario que creo la plantilla.
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que guarda la fecha de creacion de la plantilla.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
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
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result
				+ ((idPlantilla == null) ? 0 : idPlantilla.hashCode());
		result = prime * result
				+ ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
		return result;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
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
		DTOPlantillaEntity other = (DTOPlantillaEntity) obj;
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
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		if (idPlantilla == null) {
			if (other.idPlantilla != null)
				return false;
		} else if (!idPlantilla.equals(other.idPlantilla))
			return false;
		if (idTipoDocumento == null) {
			if (other.idTipoDocumento != null)
				return false;
		} else if (!idTipoDocumento.equals(other.idTipoDocumento))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOPlantillaEntity [idPlantilla=" + idPlantilla + ", idArea="
				+ idArea + ", tipoArea=" + tipoArea + ", area=" + area
				+ ", idTipoDocumento=" + idTipoDocumento + ", tipoDocumento="
				+ tipoDocumento + ", idAcronimo=" + idAcronimo + ", acronimo="
				+ acronimo + ", idPersona=" + idPersona + ", persona="
				+ persona + ", nombrePlantilla=" + nombrePlantilla
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOPaginarLazyInterface#getRowKey()
	 */
	@Override
	public String getRowKey() {
		
		return String.valueOf(this.hashCode());
	}
	
	/* ------------------------------------- Getters y Setters ------------------------------------ */

	/**
	 * @return idPlantilla
	 */
	public Integer getIdPlantilla() {

		return idPlantilla;
	}

	/**
	 * @param idPlantilla
	 *            idPlantilla del DTOPlantillaEntity
	 */
	public void setIdPlantilla(Integer idPlantilla) {

		this.idPlantilla = idPlantilla;
	}

	/**
	 * @return area
	 */
	public DTOCAreaEntity getArea() {

		return area;
	}

	/**
	 * @param area
	 *            area del DTOPlantillaEntity
	 */
	public void setArea(DTOCAreaEntity area) {

		this.area = area;
	}

	/**
	 * @return tipoDocumento
	 */
	public DTOCTipoDocumentoEntity getTipoDocumento() {

		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            tipoDocumento del DTOPlantillaEntity
	 */
	public void setTipoDocumento(DTOCTipoDocumentoEntity tipoDocumento) {

		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return acronimo
	 */
	public DTOAcronimoEntity getAcronimo() {

		return acronimo;
	}

	/**
	 * @param acronimo
	 *            acronimo del DTOPlantillaEntity
	 */
	public void setAcronimo(DTOAcronimoEntity acronimo) {

		this.acronimo = acronimo;
	}

	/**
	 * @return persona
	 */
	public DTOEstructuraAreasEntity getPersona() {

		return persona;
	}

	/**
	 * @param persona
	 *            persona del DTOPlantillaEntity
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {

		this.persona = persona;
	}

	/**
	 * @return nombrePlantilla
	 */
	public String getNombrePlantilla() {

		return nombrePlantilla;
	}

	/**
	 * @param nombrePlantilla
	 *            nombrePlantilla del DTOPlantillaEntity
	 */
	public void setNombrePlantilla(String nombrePlantilla) {

		this.nombrePlantilla = nombrePlantilla;
	}

	/**
	 * @return usuario
	 */
	@Override
	public String getUsuario() {

		return usuario;
	}

	/**
	 * @param usuario
	 *            usuario del DTOPlantillaEntity
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
	 *            fechaHora del DTOPlantillaEntity
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}

	/**
	 * @return idArea
	 */
	public Integer getIdArea() {

		return idArea;
	}

	/**
	 * @param idArea
	 *            idArea del DTOPlantillaEntity
	 */
	public void setIdArea(Integer idArea) {

		this.idArea = idArea;
	}

	/**
	 * @return tipoArea
	 */
	public Integer getTipoArea() {

		return tipoArea;
	}

	/**
	 * @param tipoArea
	 *            tipoArea del DTOPlantillaEntity
	 */
	public void setTipoArea(Integer tipoArea) {

		this.tipoArea = tipoArea;
	}

	/**
	 * @return idTipoDocumento
	 */
	public Integer getIdTipoDocumento() {

		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento
	 *            idTipoDocumento del DTOPlantillaEntity
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {

		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return idAcronimo
	 */
	public Integer getIdAcronimo() {

		return idAcronimo;
	}

	/**
	 * @param idAcronimo
	 *            idAcronimo del DTOPlantillaEntity
	 */
	public void setIdAcronimo(Integer idAcronimo) {

		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return idPersona
	 */
	public Integer getIdPersona() {

		return idPersona;
	}

	/**
	 * @param idPersona
	 *            idPersona del DTOPlantillaEntity
	 */
	public void setIdPersona(Integer idPersona) {

		this.idPersona = idPersona;
	}

}
