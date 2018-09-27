/**
 * DTOHistDocCreacion.java 18/09/2017
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

import mx.ine.gestion.dto.db.catalogos.DTOCHistDocCreaEntity;
import mx.org.ine.servicios.dto.DTOBase;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Homero Villanueva Dominguez
 * @since 18/09/2017
 */

@Entity
@Table(name = "HIST_DOC_CREACION", schema = "gestion4")
@IdClass(value = DTOHistDocCreacionID.class)
public class DTOHistDocCreacionEntity extends DTOBase {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = 2139702280459128706L;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "HIST_DOC_CREACION",
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "HIST_DOC_CREACION",
	 */
	@Id
	@Column(name = "ID_HISTORICO")
	private Integer idHistorico;

	/**
	 * Atributo que guarda el año del documento
	 */
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_HIST", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoPersona;
	
	/**
	 * Atributo que guarda el id de la persona que realizó la transacción
	 */
	@Column(name = "ID_PERSONA_HIST")
	private Integer idPersonaHist;

	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTATUS", insertable = false, updatable = false)
	private DTOCHistDocCreaEntity dtoEstatus;

	/**
	 * Atributo que guarda el tipo de acción que el usuario realizó. .
	 */
	@Column(name = "ID_ESTATUS")
	private Integer idEstatus;

	/**
	 * Atributo que guarda el comentario de cada entrada del historial
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_DOCUMENTO",   referencedColumnName = "ID_DOCUMENTO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_COMENTARIO", referencedColumnName = "ID_COMENTARIO", insertable = false, updatable = false)	
	})
	private DTOComentariosDocumentoEntity dtoComentario;
	
	/*
	 * Id del comentario, puede ser nulo
	 */
	@Column(name="ID_COMENTARIO")
	private Integer idComentario;
	
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumns({
		@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_EDICION", insertable = false, updatable = false)	
	})
	private DTOEdicionesDocumentoEntity dtoEdicion;
	
	/*
	 * Id de la edición, puede ser nulo.
	 */
	@Column(name="ID_EDICION")
	private Integer idEdicion;
	
	/**
	 * Atributo que permite guardar el nombre del usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fecha_Hora")
	private Date fechaHora;
	
    /**
     * Fecha en que se genera el registro con formato dd/mm/aaaa.
     */
    @Formula("to_char(fecha_hora,'DD/MM/YYYY')")
    private String fechaAccion;
	

	// ------------------------ HASHCODE & EQUALS------------------------ //

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
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idHistorico == null) ? 0 : idHistorico.hashCode());
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
		DTOHistDocCreacionEntity other = (DTOHistDocCreacionEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idHistorico == null) {
			if (other.idHistorico != null)
				return false;
		} else if (!idHistorico.equals(other.idHistorico))
			return false;
		return true;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOHistDocCreacionEntity [idDocumento=" + idDocumento
				+ ", idHistorico=" + idHistorico + ", anio=" + anio
				+ ", dtoPersona=" + dtoPersona + ", idPersonaHist="
				+ idPersonaHist + ", dtoEstatus=" + dtoEstatus + ", idEstatus="
				+ idEstatus + ", dtoComentario=" + dtoComentario
				+ ", idComentario=" + idComentario + ", dtoEdicion="
				+ dtoEdicion + ", idEdicion=" + idEdicion + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + ", fechaAccion="
				+ fechaAccion + "]";
	}
	
	// ------------------------ Constructor ------------------------ //
	public DTOHistDocCreacionEntity(Integer idDocumento, Integer idPersonaHist,
			Integer idEstatus) {
		this.idDocumento = idDocumento;
		this.idPersonaHist = idPersonaHist;
		this.idEstatus = idEstatus;
	}

	public DTOHistDocCreacionEntity() {
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idHistorico
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public int getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idPersonaHist
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public Integer getIdPersonaHist() {
		return idPersonaHist;
	}

	/**
	 * @param idPersonaHist
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdPersonaHist(Integer idPersonaHist) {
		this.idPersonaHist = idPersonaHist;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idEstatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public int getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/09/2017
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;

	}

	/**
	 * @return variable de tipo DTOCHistDocCrea contenida en dtoEstatus
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOCHistDocCreaEntity getDtoEstatus() {
		return dtoEstatus;
	}

	/**
	 * @param dtoEstatus: variable de tipo DTOCHistDocCrea contenida en dtoEstatus
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDtoEstatus(DTOCHistDocCreaEntity dtoEstatus) {
		this.dtoEstatus = dtoEstatus;
	}

	/**
	 * @return variable de tipo DTOComentariosDocumentoEntity contenida en dtoComentario
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOComentariosDocumentoEntity getDtoComentario() {
		return dtoComentario;
	}

	/**
	 * @param dtoComentario: variable de tipo DTOComentariosDocumentoEntity contenida en dtoComentario
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDtoComentario(DTOComentariosDocumentoEntity dtoComentario) {
		this.dtoComentario = dtoComentario;
	}

	/**
	 * @return variable de tipo Integer contenida en idComentario
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario: variable de tipo Integer contenida en idComentario
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	/**
	 * @return variable de tipo DTOEdicionesDocumentoEntity contenida en dtoEdicion
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOEdicionesDocumentoEntity getDtoEdicion() {
		return dtoEdicion;
	}

	/**
	 * @param dtoEdicion: variable de tipo DTOEdicionesDocumentoEntity contenida en dtoEdicion
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDtoEdicion(DTOEdicionesDocumentoEntity dtoEdicion) {
		this.dtoEdicion = dtoEdicion;
	}

	/**
	 * @return variable de tipo Integer contenida en idEdicion
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdEdicion() {
		return idEdicion;
	}

	/**
	 * @param idEdicion: variable de tipo Integer contenida en idEdicion
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdEdicion(Integer idEdicion) {
		this.idEdicion = idEdicion;
	}

	/**
	 * @return variable de tipo DTOEstructuraAreasEntity contenida en dtoPersona
	 * 
	 * @since 17/11/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTOEstructuraAreasEntity getDtoPersona() {
		return dtoPersona;
	}

	/**
	 * @param dtoPersona: variable de tipo DTOEstructuraAreasEntity contenida en dtoPersona
	 *
	 * @since 17/11/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDtoPersona(DTOEstructuraAreasEntity dtoPersona) {
		this.dtoPersona = dtoPersona;
	}

	/**
	 * @return the fechaAccion
	 */
	public String getFechaAccion() {
		return fechaAccion;
	}

	/**
	 * @param fechaAccion the fechaAccion to set
	 */
	public void setFechaAccion(String fechaAccion) {
		this.fechaAccion = fechaAccion;
	}
	
}
