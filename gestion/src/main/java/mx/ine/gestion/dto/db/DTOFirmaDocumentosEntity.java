/**
 * @(#)DTOFirmaDocumentosEntity.java 12/09/2017
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
 * Clase para mapeo de la tabla de FIRMA_DOCUMENTOS
 * perteneciente al esquema de GESTION4 FALTAN COMENTARIOS
 *
 * @author David Rodríguez Corral 
 * @since 12/09/2017
 */
@Entity
@Table(name="FIRMA_DOCUMENTOS", schema="gestion4")
@IdClass(value = DTOFirmaDocumentosID.class)
public class DTOFirmaDocumentosEntity extends DTOBase {
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 2134151036961815982L;

/* ------------------------------------- Atributos/Campos ------------------------------------ */	
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
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
	private DTOEstructuraAreasEntity dtoDocumentoRemitente;
	
	/**
	 * Indica el año de la firma.
	 *
	 */
	@Column(name="ANIO")
	private Integer anio;
	
	/**
	 * Campo para indicar si el archivo ya esta firmado y esté pendiente de envío.
	 */
	@Column(name="PENDIENTE_ENVIO")
	private Integer pendienteEnvio;
	
	/**
	 * Objeto para cargar la información de ESTRUCTURA_AREAS
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSONA_REGRESO_ID", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoPersonaRegreso;

	/**
	 * Objeto para cargar la información de ESTRUCTURA_AREAS
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_BLOQUEADO", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoPersonaBloqueado;
	
	/**
	 * Id de la primera persona que regresa el documento a remitente.
	 */
	@Column(name="ID_PERSONA_BLOQUEADO")
	private Integer idPersonaBloqueado;

	/**
	 * Campo para indicar si el documento estaá bloqueado (está siendo usado por otro usuario)
	 */
	@Column(name="BLOQUEADO")
	private Integer bloqueado; 
	
	/**
	 * Id de la primera persona que regresa el documento a remitente.
	 */
	@Column(name="PERSONA_REGRESO_ID")
	private Integer personaRegresoId;
	
	/*
	 * Estatus del documento
	 */
	@Column(name="ESTATUS_REGRESADO")
	private Integer estatusRegresado;
	
	/**
	 * Indica si el usuario ha modificado el documento, 0-Sin modificar, 1-Modificado
	 */
	@Column(name="CON_MODIFICACIONES")
	private Integer conModificaciones;
	
	/*
	 * Comentario, puede ser nulo
	 */
	@Column(name="ID_COMENTARIO")
	private Integer idComentario;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_COMENTARIO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	})

	private DTOComentariosDocumentoEntity dtoComentario;
	
	/**
	 * Indica si el documento ya fue leído o no
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;
	
	/**
     * Cuenta del usuario que registra.
     */
	@Column(name="USUARIO")
    private String usuario;
	
	/**
	 * Indica si el documento ha sido visto
	 */
	@Column(name="CON_VISUALIZACION")
	private Integer conVisualizacion;
	
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
    private String fechaFirma;
    
    /**
     * Indica el comentario del documento
     */
    @Transient
    private String comentario;
    
    /**
     * Indica si ha sido editado
     */
    @Transient
    private Boolean edito;
    
    /**
     * Objeto que contiene los datos para firma un documento
     */
    @Transient
    private DTODatosFirmaDocEntity dtoFirmaElectronica;
    
   
    /* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
    
    /*
     * 	(non-Javadoc)
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
		DTOFirmaDocumentosEntity other = (DTOFirmaDocumentosEntity) obj;
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
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOFirmaDocumentosEntity [idPersona=" + idPersona
				+ ", dtoEstructuraAreas=" + dtoEstructuraAreas
				+ ", idDocumento=" + idDocumento + ", dtoDocumento="
				+ dtoDocumento + ", idPersonaRemitente=" + idPersonaRemitente
				+ ", dtoDocumentoRemitente=" + dtoDocumentoRemitente
				+ ", anio=" + anio + ", pendienteEnvio=" + pendienteEnvio
				+ ", dtoPersonaRegreso=" + dtoPersonaRegreso
				+ ", dtoPersonaBloqueado=" + dtoPersonaBloqueado
				+ ", idPersonaBloqueado=" + idPersonaBloqueado + ", bloqueado="
				+ bloqueado + ", personaRegresoId=" + personaRegresoId
				+ ", estatusRegresado=" + estatusRegresado
				+ ", conModificaciones=" + conModificaciones
				+ ", idComentario=" + idComentario + ", dtoComentario="
				+ dtoComentario + ", noLeido=" + noLeido + ", usuario="
				+ usuario + ", conVisualizacion=" + conVisualizacion
				+ ", fechaHora=" + fechaHora + ", fechaFirma=" + fechaFirma
				+ ", comentario=" + comentario + ", edito=" + edito
				+ ", dtoFirmaElectronica=" + dtoFirmaElectronica + "]";
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
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructuraAreas
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	/**
	 * @param dtoEstructuraAreas : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 12/09/2017
	 */
	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}

	/**
	 * @return valor de tipo idDocumento que esta contenido en la variable Integer
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
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaRemitente
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public Integer getIdPersonaRemitente() {
		return idPersonaRemitente;
	}

	/**
	 * @param idPersonaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setIdPersonaRemitente(Integer idPersonaRemitente) {
		this.idPersonaRemitente = idPersonaRemitente;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoDocumentoRemitente
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public DTOEstructuraAreasEntity getDtoDocumentoRemitente() {
		return dtoDocumentoRemitente;
	}

	/**
	 * @param dtoDocumentoRemitente : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setDtoDocumentoRemitente(
			DTOEstructuraAreasEntity dtoDocumentoRemitente) {
		this.dtoDocumentoRemitente = dtoDocumentoRemitente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable fechaFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public String getFechaFirma() {
		return fechaFirma;
	}

	/**
	 * @param hashDocumentoRecibido : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setFechaFirma(String fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable comentario
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable edito
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public Boolean getEdito() {
		return edito;
	}

	/**
	 * @param edito : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setEdito(Boolean edito) {
		this.edito = edito;
	}

	/**
	 * @return valor de tipo DTODatosFirmaDocEntity que esta contenido en la variable dtoFirmaElectronica
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public DTODatosFirmaDocEntity getDtoFirmaElectronica() {
		return dtoFirmaElectronica;
	}

	/**
	 * @param dtoFirmaElectronica : valor que se ingresa a la variable de tipo DTODatosFirmaDocEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setDtoFirmaElectronica(
			DTODatosFirmaDocEntity dtoFirmaElectronica) {
		this.dtoFirmaElectronica = dtoFirmaElectronica;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable pendienteEnvio
	 *
	 * @author David Rodríguez Corral
	 * @since 10/10/2017
	 */
	public Integer getPendienteEnvio() {
		return pendienteEnvio;
	}

	/**
	 * @param pendienteEnvio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/10/2017
	 */
	public void setPendienteEnvio(Integer pendienteEnvio) {
		this.pendienteEnvio = pendienteEnvio;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoPersonaRegreso
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public DTOEstructuraAreasEntity getDtoPersonaRegreso() {
		return dtoPersonaRegreso;
	}

	/**
	 * @param dtoPersonaRegreso : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setDtoPersonaRegreso(DTOEstructuraAreasEntity dtoPersonaRegreso) {
		this.dtoPersonaRegreso = dtoPersonaRegreso;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable personaRegresoId
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public Integer getPersonaRegresoId() {
		return personaRegresoId;
	}

	/**
	 * @param personaRegresoId : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setPersonaRegresoId(Integer personaRegresoId) {
		this.personaRegresoId = personaRegresoId;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable estatusRegresado
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public Integer getEstatusRegresado() {
		return estatusRegresado;
	}

	/**
	 * @param estatusRegresado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 30/09/2017
	 */
	public void setEstatusRegresado(Integer estatusRegresado) {
		this.estatusRegresado = estatusRegresado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable conModificaciones
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public Integer getConModificaciones() {
		return conModificaciones;
	}

	/**
	 * @param conModificaciones : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void setConModificaciones(Integer conModificaciones) {
		this.conModificaciones = conModificaciones;
	}

	public Integer getConVisualizacion() {
		return conVisualizacion;
	}

	public void setConVisualizacion(Integer conVisualizacion) {
		this.conVisualizacion = conVisualizacion;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public DTOComentariosDocumentoEntity getDtoComentario() {
		return dtoComentario;
	}

	public void setDtoComentario(DTOComentariosDocumentoEntity dtoComentario) {
		this.dtoComentario = dtoComentario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable noLeido
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoPersonaBloqueado
	 * 
	 * @author David Rodríguez Corral
	 * @since 05/01/2018
	 */
	public DTOEstructuraAreasEntity getDtoPersonaBloqueado() {
		return dtoPersonaBloqueado;
	}

	/**
	 * @param dtoPersonaBloqueado : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 05/01/2018
	 */
	public void setDtoPersonaBloqueado(DTOEstructuraAreasEntity dtoPersonaBloqueado) {
		this.dtoPersonaBloqueado = dtoPersonaBloqueado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable bloqueado
	 * 
	 * @author David Rodríguez Corral
	 * @since 05/01/2018
	 */
	public Integer getBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 05/01/2018
	 */
	public void setBloqueado(Integer bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaBloqueado
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/01/2018
	 */
	public Integer getIdPersonaBloqueado() {
		return idPersonaBloqueado;
	}

	/**
	 * @param idPersonaBloqueado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 08/01/2018
	 */
	public void setIdPersonaBloqueado(Integer idPersonaBloqueado) {
		this.idPersonaBloqueado = idPersonaBloqueado;
	}
	
	
}
