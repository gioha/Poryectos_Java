/**
 * @(#)DTODocumentosClasifAreaEntity.java 10/12/2017
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
 * Clase para mapeo de la tabla de DOCUMENTOS_CLASIF_AREA
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral 
 * @since 10/12/2017
 */
@Entity
@Table(name="DOCUMENTOS_CLASIF_AREA", schema="gestion4")
@IdClass(value = DTODocumentosClasifAreaID.class)
public class DTODocumentosClasifAreaEntity extends DTOBase {
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 2965587944656719344L;

/* ------------------------------------- Atributos/Campos ------------------------------------ */	

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name="ID_DOCUMENTO")
	private Integer idDocumento;
	
	/**
	 * Objeto para cargar la información de la tabla de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity dtoDocumento;
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="ID_AREA_CLASIFICA")
	private Integer idAreaClasifica;
	

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="TIPO_AREA_CLASIFICA")
	private Integer tipoAreaClasifica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_AREA_CLASIFICA", insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_AREA_CLASIFICA", insertable = false, updatable = false)
	})
	private DTOCAreaEntity dtoCAreas;
	
	/**
	 * Indica el id de la oficialia que apartó el folio
	 */
	@Column(name="ID_OFICIALIA_CLASIFICA")
	private Integer idOficialiaClasifica;
	
	/**
	 * Indica el id de la sección a clasificar
	 */
	@Column(name="ID_SECCION")
	private Integer idSeccion;
	
	/**
	 * Indica el id de la categoría a clasificar
	 */
	@Column(name="ID_CATEGORIA")
	private Integer idCategoria;
	
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
		result = prime * result
				+ ((idAreaClasifica == null) ? 0 : idAreaClasifica.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime
				* result
				+ ((tipoAreaClasifica == null) ? 0 : tipoAreaClasifica
						.hashCode());
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
		DTODocumentosClasifAreaEntity other = (DTODocumentosClasifAreaEntity) obj;
		if (idAreaClasifica == null) {
			if (other.idAreaClasifica != null)
				return false;
		} else if (!idAreaClasifica.equals(other.idAreaClasifica))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (tipoAreaClasifica == null) {
			if (other.tipoAreaClasifica != null)
				return false;
		} else if (!tipoAreaClasifica.equals(other.tipoAreaClasifica))
			return false;
		return true;
	}

	
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentosClasifAreaEntity [idDocumento=" + idDocumento
				+ ", dtoDocumento=" + dtoDocumento + ", idAreaClasifica="
				+ idAreaClasifica + ", tipoAreaClasifica=" + tipoAreaClasifica
				+ ", dtoCAreas=" + dtoCAreas + ", idOficialiaClasifica="
				+ idOficialiaClasifica + ", idSeccion=" + idSeccion
				+ ", idCategoria=" + idCategoria + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + "]";
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
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaClasifica
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdAreaClasifica() {
		return idAreaClasifica;
	}

	/**
	 * @param idAreaClasifica : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdAreaClasifica(Integer idAreaClasifica) {
		this.idAreaClasifica = idAreaClasifica;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaClasifica
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getTipoAreaClasifica() {
		return tipoAreaClasifica;
	}

	/**
	 * @param tipoAreaClasifica : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setTipoAreaClasifica(Integer tipoAreaClasifica) {
		this.tipoAreaClasifica = tipoAreaClasifica;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoCAreas
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public DTOCAreaEntity getDtoCAreas() {
		return dtoCAreas;
	}

	/**
	 * @param dtoCAreas : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setDtoCAreas(DTOCAreaEntity dtoCAreas) {
		this.dtoCAreas = dtoCAreas;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialiaClasifica
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdOficialiaClasifica() {
		return idOficialiaClasifica;
	}

	/**
	 * @param idOficialiaClasifica : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdOficialiaClasifica(Integer idOficialiaClasifica) {
		this.idOficialiaClasifica = idOficialiaClasifica;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idSeccion
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdSeccion() {
		return idSeccion;
	}

	/**
	 * @param idSeccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idCategoria
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

}
