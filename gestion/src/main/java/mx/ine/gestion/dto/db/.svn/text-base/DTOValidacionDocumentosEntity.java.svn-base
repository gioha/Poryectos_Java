/**
 * @(#)DTOValidacionDocumentosEntity.java 10/10/2017
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

import mx.org.ine.servicios.dto.DTOBase;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Clase para mapeo de la tabla de VALIDACIÓN_DOCUMENTOS
 * perteneciente al esquema de GESTION4
 *
 * @author Pável Alexei Martínez Regalado
 * @since 10/10/2017
 */
@Entity
@Table(name="VALIDACION_DOCUMENTOS", schema="gestion4")
@IdClass(value = DTOValidacionDocumentosID.class)
public class DTOValidacionDocumentosEntity extends DTOBase {


/* ------------------------------------- Atributos/Campos ------------------------------------ */	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4493376115033168165L;	
	
	/**
	 * Parte de la llave primaria, es llave foránea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="ID_PERSONA")
	private Integer idPersona;
	
	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructuraAreas;

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
	 * Indica a la persona remitente, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Column(name="ID_PERSONA_REMITENTE")
	private Integer idPersonaRemitente;
	
	/**
	 * Objeto para cargar la información de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_REMITENTE", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoPersonaRemitente;
	
	/**
	 * Indica el año de la firma.
	 *
	 */
	@Column(name="ANIO")
	private Integer anio;
	
	/**
	 * 
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSONA_REGRESO_ID", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoPersonaRegreso;
	
	/**
	 * Id de la primera persona que regresa el documento a remitente.
	 */
	@Column(name="PERSONA_REGRESO_ID")
	private Integer personaRegresoId;
	
	/**
	 * Cuando ya fue regresado y no se puede validar
	 */
	@Column(name="ESTATUS_REGRESADO")
	private Integer estatusRegresado;
	
	/**
	 * Cuando ya tiene modifciaciones
	 */
	@Column(name="CON_MODIFICACIONES")
	private Integer conModificaciones;
	
	/**
	 * Cuando ya tiene visualizaciones
	 */
	@Column(name="CON_VISUALIZACION")
	private Integer conVisualizacion;
	
	/**
	 * 
	 */
	@Column(name="ID_COMENTARIO")
	private Integer idComentario;

	/**
	 * 
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_COMENTARIO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	})
	private DTOComentariosDocumentoEntity dtoComentario;
	
	/**
	 * Indica si la validación ha sido leída
	 */
	@Column(name="NO_LEIDO")
	private Integer noLeido; 
	
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
     * Fecha en que se genera el registro con formato dd/mm/aaaa.
     */
    @Formula("to_char(fecha_hora,'DD/MM/YYYY')")
    private String fechaValidacion;

    @Transient
    private String comentario;
    
    public DTOValidacionDocumentosEntity(){
    	//comentario = "";
    }
    /* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOValidacionDocumentosEntity other = (DTOValidacionDocumentosEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}
	
//	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "DTOValidacionDocumentosEntity [idPersona=" + idPersona
//				+ ", dtoEstructuraAreas=" + dtoEstructuraAreas
//				+ ", idDocumento=" + idDocumento + ", dtoDocumento="
//				+ dtoDocumento + ", idPersonaRemitente=" + idPersonaRemitente
//				//+ ", dtoDocumentoRemitente=" + dtoDocumentoRemitente
//				+ ", anio=" + anio + ", dtoPersonaRegreso=" + dtoPersonaRegreso
//				+ ", personaRegresoId=" + personaRegresoId
//				+ ", estatusRegresado=" + estatusRegresado
//				+ ", conModificaciones=" + conModificaciones
//				+ ", conVisualizacion=" + conVisualizacion + ", idComentario="
//				+ idComentario + ", dtoComentario=" + dtoComentario
//				+ ", noLeido=" + noLeido + ", usuario=" + usuario
//				+ ", fechaHora=" + fechaHora + ", fechaValidacion="
//				+ fechaValidacion + ", comentario=" + comentario + "]";
//	}

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
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructuraAreas
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	/**
	 * @param dtoEstructuraAreas : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}

	/**
	 * @return valor de tipo idDocumento que esta contenido en la variable Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaRemitente
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getIdPersonaRemitente() {
		return idPersonaRemitente;
	}

	/**
	 * @param idPersonaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setIdPersonaRemitente(Integer idPersonaRemitente) {
		this.idPersonaRemitente = idPersonaRemitente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable fechaFirma
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public String getFechaValidacion() {
		return fechaValidacion;
	}

	/**
	 * @param fechaFirma : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setFechaValidacion(String fechaFirma) {
		this.fechaValidacion = fechaFirma;
	}

	/**
	 * @return valor de tipo Integer que está contenido en la variable personaRegresoId
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getPersonaRegresoId() {
		return personaRegresoId;
	}

	/**
	 * @param personaRegresoId : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setPersonaRegresoId(Integer idPersonaRegreso) {
		this.personaRegresoId = idPersonaRegreso;
	}

	/**
	 * @return valor de tipo Integer que está contenido en la variable estatusRegresado
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public Integer getEstatusRegresado() {
		return estatusRegresado;
	}

	/**
	 * @param estatusRegresado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void setEstatusRegresado(Integer estatusRegresado) {
		this.estatusRegresado = estatusRegresado;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoPersonaRegreso
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/10/2017
	 */
	public DTOEstructuraAreasEntity getDtoPersonaRegreso() {
		return dtoPersonaRegreso;
	}

	/**
	 * @param dtoPersonaRegreso : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/10/2017
	 */
	public void setDtoPersonaRegreso(DTOEstructuraAreasEntity dtoPersonaRegreso) {
		this.dtoPersonaRegreso = dtoPersonaRegreso;
	}

	/**
	 * @return the conModificaciones
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public Integer getConModificaciones() {
		return conModificaciones;
	}

	/**
	 * @param conModificaciones the conModificaciones to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public void setConModificaciones(Integer conModificaciones) {
		this.conModificaciones = conModificaciones;
	}

	/**
	 * @return the idComentario
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario the idComentario to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	/**
	 * @return the dtoComentario
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public DTOComentariosDocumentoEntity getDtoComentario() {
		return dtoComentario;
	}

	/**
	 * @param dtoComentario the dtoComentario to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/11/2017
	 */
	public void setDtoComentario(DTOComentariosDocumentoEntity dtoComentario) {
		this.dtoComentario = dtoComentario;
		if(dtoComentario != null && dtoComentario.getComentarios() != null){
			comentario = dtoComentario.getComentarios();			
		}
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return variable de tipo Integer contenida en noLeido
	 * 
	 * @since 18/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido: variable de tipo Integer contenida en noLeido
	 *
	 * @since 18/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return variable de tipo Integer contenida en conVisualizacion
	 * 
	 * @since 19/12/2017
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getConVisualizacion() {
		return conVisualizacion;
	}

	/**
	 * @param conVisualizacion: variable de tipo Integer contenida en conVisualizacion
	 *
	 * @since 19/12/2017
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setConVisualizacion(Integer conVisualizacion) {
		this.conVisualizacion = conVisualizacion;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoPersonaRemitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/04/2018
	 */
	public DTOEstructuraAreasEntity getDtoPersonaRemitente() {
		return dtoPersonaRemitente;
	}

	/**
	 * @param dtoPersonaRemitente : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/04/2018
	 */
	public void setDtoPersonaRemitente(DTOEstructuraAreasEntity dtoPersonaRemitente) {
		this.dtoPersonaRemitente = dtoPersonaRemitente;
	}
	
}
