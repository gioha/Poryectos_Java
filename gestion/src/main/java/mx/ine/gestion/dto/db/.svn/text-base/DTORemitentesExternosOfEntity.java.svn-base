/**
 * @(#)DTORemitentesExternosOfEntity.java 19/01/2018
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
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de REMITENTES_EXTERNOS_OF
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral 
 * @since 19/01/2018
 */
@Entity
@Table(name="REMITENTES_EXTERNOS_OF", schema="gestion4")
@IdClass(value = DTORemitentesExternosOfID.class)
public class DTORemitentesExternosOfEntity extends DTOBase {
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 71703498990682785L;

/* ------------------------------------- Atributos/Campos ------------------------------------ */	

	/**
	 * Parte de la llave primaria
	 */
	@Id
	@Column(name="ID_REMITENTE")
	private Integer idRemitente;
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="ID_AREA")
	private Integer idArea;
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="TIPO_AREA")
	private Integer tipoArea;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_AREA", insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_AREA", insertable = false, updatable = false)
	})
	private DTOCAreaEntity dtoCAreas;
	
	/**
	 * String que guarda el nombre del remitente externo
	 */
	@Id
	@Column(name="NOMBRE_REMITENTE")
	private String nombreRemitente;
	
	/**
	 * String que guarda la dependencia del remitente externo
	 */
	@Id
	@Column(name="DEPENDENCIA")
	private String dependencia;
	
	/**
     * Cuenta del usuario que registra.
     */
	@Column(name="USUARIO")
    private String usuario;
	
	/**
     * Fecha y hora en que se genera el registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_HORA")
    private Date fechaHora;
    
    /**
     * Boolean que habilita los campos hasta que se seleccione un área
     */
    @Transient
    private Boolean habilitarCampos;
    
    /* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idRemitente == null) ? 0 : idRemitente.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		DTORemitentesExternosOfEntity other = (DTORemitentesExternosOfEntity) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idRemitente == null) {
			if (other.idRemitente != null)
				return false;
		} else if (!idRemitente.equals(other.idRemitente))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTORemitentesExternosOfEntity [idRemitente=" + idRemitente
				+ ", idArea=" + idArea + ", tipoArea=" + tipoArea
				+ ", dtoCAreas=" + dtoCAreas + ", nombreRemitente="
				+ nombreRemitente + ", dependencia=" + dependencia
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora
				+ ", habilitarCampos=" + habilitarCampos + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}  

	/* ------------------------------------- Getters/Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idRemitente
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getIdRemitente() {
		return idRemitente;
	}

	/**
	 * @param idRemitente: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setIdRemitente(Integer idRemitente) {
		this.idRemitente = idRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea: valor que se ingresa a la variable de tipo Integer 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoCAreas
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public DTOCAreaEntity getDtoCAreas() {
		return dtoCAreas;
	}

	/**
	 * @param dtoCAreas: valor que se ingresa a la variable de tipo DTOCAreaEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setDtoCAreas(DTOCAreaEntity dtoCAreas) {
		this.dtoCAreas = dtoCAreas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreRemitente
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public String getNombreRemitente() {
		return nombreRemitente;
	}

	/**
	 * @param nombreRemitente: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable dependencia
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public String getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable habilitarCampos
	 *
	 * @author David Rodríguez Corral
	 * @since 21/01/2018
	 */
	public Boolean getHabilitarCampos() {
		return habilitarCampos;
	}

	/**
	 * @param habilitarCampos: valor que se ingresa a la variable de tipo Boolean 
	 *
	 * @author David Rodríguez Corral
	 * @since 21/01/2018
	 */
	public void setHabilitarCampos(Boolean habilitarCampos) {
		this.habilitarCampos = habilitarCampos;
	}

}
