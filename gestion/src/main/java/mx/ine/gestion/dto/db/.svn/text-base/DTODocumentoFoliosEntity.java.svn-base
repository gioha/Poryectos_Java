/**
 * @(#)DTODocumentoFoliosEntity.java 10/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de DOCUMENTO_FOLIOS
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral 
 * @since 10/12/2017
 */
@Entity
@Table(name="DOCUMENTO_FOLIOS", schema="gestion4")
@IdClass(value = DTODocumentoFoliosID.class)
public class DTODocumentoFoliosEntity extends DTOBase {
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -4729367008873294163L;
	
/* ------------------------------------- Atributos/Campos ------------------------------------ */	

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name="ID_DOCUMENTO")
	private Integer idDocumento;
	
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
	
	/**
	 * Indica el id de la oficialia que apartó el folio
	 */
	@Id
	@Column(name="ID_FOLIO_OFICIALIA")
	private Integer idFolioOficialia;
	
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
				+ ((idFolioOficialia == null) ? 0 : idFolioOficialia.hashCode());
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
		DTODocumentoFoliosEntity other = (DTODocumentoFoliosEntity) obj;
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
		if (idFolioOficialia == null) {
			if (other.idFolioOficialia != null)
				return false;
		} else if (!idFolioOficialia.equals(other.idFolioOficialia))
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
		return "DTODocumentoFoliosEntity [idDocumento=" + idDocumento
				+ ", idAreaClasifica=" + idAreaClasifica
				+ ", tipoAreaClasifica=" + tipoAreaClasifica
				+ ", idFolioOficialia=" + idFolioOficialia + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + "]";
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
	 * @return valor de tipo Integer que esta contenido en la variable idFolioOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getIdFolioOficialia() {
		return idFolioOficialia;
	}

	/**
	 * @param idFolioOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setIdFolioOficialia(Integer idFolioOficialia) {
		this.idFolioOficialia = idFolioOficialia;
	}

}
