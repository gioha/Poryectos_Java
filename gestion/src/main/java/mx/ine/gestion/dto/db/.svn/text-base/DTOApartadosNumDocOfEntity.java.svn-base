/**
 * @(#)DTOApartadosNumDocOfEntity.java 12/09/2017
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
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de APARTADOS_NUM_DOC_OF
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral 
 * @since 08/12/2017
 */
@Entity
@Table(name="APARTADOS_NUM_DOC_OF", schema="gestion4")
@IdClass(value = DTOApartadosNumDocOfID.class)
public class DTOApartadosNumDocOfEntity extends DTOBase {
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -2587190534658580590L;

/* ------------------------------------- Atributos/Campos ------------------------------------ */	

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
	 * Parte de la llave primaria
	 */
	@Id
	@Column(name="ID_FOLIO_OF")
	private Integer idFolioOf;
	
	/**
	 * Indica el id de la oficialia que apartó el folio
	 */
	@Column(name="ID_OFICIALIA_APARTO")
	private Integer idOficialiaAparto;
	
	/**
	 * Guarda el folio apartar
	 */
	@Column(name="FOLIO_OFICIALIA")
	private String folioOficialia;
	
	/**
	 * Guarda el año en que se aparta el folio
	 */
	@Column(name="ANIO")
	private Integer anio;
	
	/**
	 * Indica el estatus del folio L=LIBRE, C=CONCLUIDO, A=APARTADO 
	 */
	@Column(name="ESTATUS")
	private char estatus;
	
	/**
	 * Atributo que guarda F o E dependiendo si el apartado es para un documento Fisico o Electronico
	 */
	@Column(name = "TIPO_APARTADO")
	private String tipoApartado;
	
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
				+ ((idFolioOf == null) ? 0 : idFolioOf.hashCode());
		result = prime
				* result
				+ ((idOficialiaAparto == null) ? 0 : idOficialiaAparto
						.hashCode());
		return result;
	}

	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOApartadosNumDocOfEntity [idArea=" + idArea + ", tipoArea="
				+ tipoArea + ", dtoCAreas=" + dtoCAreas + ", idFolioOf="
				+ idFolioOf + ", idOficialiaAparto=" + idOficialiaAparto
				+ ", folioOficialia=" + folioOficialia + ", anio=" + anio
				+ ", estatus=" + estatus + ", tipoApartado=" + tipoApartado
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
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
		DTOApartadosNumDocOfEntity other = (DTOApartadosNumDocOfEntity) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idFolioOf == null) {
			if (other.idFolioOf != null)
				return false;
		} else if (!idFolioOf.equals(other.idFolioOf))
			return false;
		if (idOficialiaAparto == null) {
			if (other.idOficialiaAparto != null)
				return false;
		} else if (!idOficialiaAparto.equals(other.idOficialiaAparto))
			return false;
		return true;
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

	/* ------------------------------------- Getters/Setters ------------------------------------ */
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoCAreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public DTOCAreaEntity getDtoCAreas() {
		return dtoCAreas;
	}

	/**
	 * @param dtoCAreas : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setDtoCAreas(DTOCAreaEntity dtoCAreas) {
		this.dtoCAreas = dtoCAreas;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idFolioOf
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getIdFolioOf() {
		return idFolioOf;
	}

	/**
	 * @param idFolioOf : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setIdFolioOf(Integer idFolioOf) {
		this.idFolioOf = idFolioOf;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialiaAparto
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public Integer getIdOficialiaAparto() {
		return idOficialiaAparto;
	}

	/**
	 * @param idOficialiaAparto : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setIdOficialiaAparto(Integer idOficialiaAparto) {
		this.idOficialiaAparto = idOficialiaAparto;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable folioOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public String getFolioOficialia() {
		return folioOficialia;
	}

	/**
	 * @param folioOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setFolioOficialia(String folioOficialia) {
		this.folioOficialia = folioOficialia;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable estatus
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public char getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo char
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/01/2018
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 11/01/2018
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	/**
	 * @return valor de tipo String que esta contenido en la variable tipoApartado
	 * 
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public String getTipoApartado() {
		return tipoApartado;
	}

	/**
	 * @param tipoApartado : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 16/01/2018
	 */
	public void setTipoApartado(String tipoApartado) {
		this.tipoApartado = tipoApartado;
	}
	
}
