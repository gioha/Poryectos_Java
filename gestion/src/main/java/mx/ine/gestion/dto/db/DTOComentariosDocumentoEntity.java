/**
 * @(#)DTOComentariosDocumentoEntity.java 12/09/2017
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de COMENTARIOS_DOCUMENTO
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral
 * @since 12/09/2017
 */
@Entity
@Table(name="COMENTARIOS_DOCUMENTO", schema="gestion4")
@IdClass(value = DTOComentariosDocumentoID.class)
public class DTOComentariosDocumentoEntity extends DTOBase{

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = 7159574017258154465L;
	
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
	 * Parte de la llave primaria
	 */
	@Id
	@Column(name="ID_COMENTARIO")
	private Integer idComentario;
	
	/**
	 * Indica el año de comentario
	 */
	@Column(name="ANIO")
	private Integer anio;
	
	/**
	 * Indica el año de comentario
	 */
	@Column(name="COMENTARIOS")
	private String comentarios;
	
	/**
	 * Indica el estatus del comentario, 0 NO leido, 1 Leido
	 */
	@Column(name="ESTATUS")
	private Integer estatus;
	
	/**
	 * Indica el usuario que hizo comentario
	 */
	@Column(name="USUARIO_COMENTO")
	private String usuarioComento;
	
	/**
     * Cuenta del usuario que registra.
     */
	@Column(name="TIPO_COMENTARIO")
    private char tipoComentario;
	
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
	 * Nombre completo del usuario que realizó la edición
	 */
	@Transient
	private String nombreCompleto;
	
	/**
	 * 
	 */
	@Transient
	private DTOEstructuraAreasEntity personaComento;
	
    
/* ------------------------------------- Constructores ------------------------------------ */
    
    public DTOComentariosDocumentoEntity(Integer idDocumento, Integer anio, String comentarios, Integer estatus, String usuarioComento){
    	this.idDocumento = idDocumento;
    	this.anio = anio;
    	this.comentarios = comentarios;
    	this.estatus = estatus;
    	this.usuarioComento = usuarioComento;
    }
    
    public DTOComentariosDocumentoEntity(){
    }

    /* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idComentario == null) ? 0 : idComentario.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		return result;
	}
	
	/*
	 * (non-Javadoc)
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
		DTOComentariosDocumentoEntity other = (DTOComentariosDocumentoEntity) obj;
		if (idComentario == null) {
			if (other.idComentario != null)
				return false;
		} else if (!idComentario.equals(other.idComentario))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		return true;
	}

	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOComentariosDocumentoEntity [idDocumento=" + idDocumento
				+ ", dtoDocumento=" + dtoDocumento + ", idComentario="
				+ idComentario + ", anio=" + anio + ", comentarios="
				+ comentarios + ", estatus=" + estatus + ", usuarioComento="
				+ usuarioComento + ", tipoComentario=" + tipoComentario
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora
				+ ", nombreCompleto=" + nombreCompleto + "]";
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
	 * @since 12/09/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idComentario
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable comentarios
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable estatus
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable usuarioComento
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public String getUsuarioComento() {
		return usuarioComento;
	}

	/**
	 * @param usuarioComento : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setUsuarioComento(String usuarioComento) {
		this.usuarioComento = usuarioComento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreCompleto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable tipoComentario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/03/2018
	 */
	public char getTipoComentario() {
		return tipoComentario;
	}

	/**
	 * @param tipoComentario : valor que se ingresa a la variable de tipo char
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/03/2018
	 */
	public void setTipoComentario(char tipoComentario) {
		this.tipoComentario = tipoComentario;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaComento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public DTOEstructuraAreasEntity getPersonaComento() {
		return personaComento;
	}

	/**
	 * @param personaComento : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void setPersonaComento(DTOEstructuraAreasEntity personaComento) {
		this.personaComento = personaComento;
	}
}