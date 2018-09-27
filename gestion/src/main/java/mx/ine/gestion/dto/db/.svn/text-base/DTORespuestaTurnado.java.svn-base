/**
 * @(#)DTORespuestaTurnado.java 14/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * @author Homero Fidel Villanueva
 * @since 14/03/2018
 *
 */
@Entity
@Table(name = "RESPUESTAS_TURNADOS", schema = "gestion4")
public class DTORespuestaTurnado extends DTOBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5170860106898524018L;

	@Id
	@Column(name = "ID_RESPUESTA")
	@SequenceGenerator(name = "S_ID_RESPUESTA_TURNADO", sequenceName = "S_ID_RESPUESTA_TURNADO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_ID_RESPUESTA_TURNADO")
	private Integer idRespuesta;

	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;
	

	@Column(name = "ID_DOCUMENTO_RESPUESTA")
	private Integer idDocumentoRespuesta;
	

	@Column(name = "ID_RECEPCION")
	private Integer idHistoricoRecepcion;
	
	@Column(name = "ANIO")
	private Integer anio;


	@Column(name = "NOMBRE_ADJUNTO_ORIGINAL")
	private String nombreAdjuntoOriginal;

	@Column(name = "NOMBRE_ADJUNTO")
	private String nombreAdjunto;
	
	@Column(name = "ID_COMENTARIO")
	private Integer idComentario;
	
	/**
	 * Atributo que permite guardar el nombre del usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idRespuesta == null) ? 0 : idRespuesta.hashCode());
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
		DTORespuestaTurnado other = (DTORespuestaTurnado) obj;
		if (idRespuesta == null) {
			if (other.idRespuesta != null)
				return false;
		} else if (!idRespuesta.equals(other.idRespuesta))
			return false;
		return true;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTORespuestaTurnado [idRespuesta=" + idRespuesta
				+ ", idDocumento=" + idDocumento + ", idDocumentoRespuesta="
				+ idDocumentoRespuesta + ", idHistoricoRecepcion="
				+ idHistoricoRecepcion + ", anio=" + anio
				+ ", nombreAdjuntoOriginal=" + nombreAdjuntoOriginal
				+ ", nombreAdjunto=" + nombreAdjunto + ", idComentario="
				+ idComentario + ", usuario=" + usuario + ", fechaHora="
				+ fechaHora + "]";
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
	 * @return valor de tipo Integer que esta contenido en la variable idRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	/**
	 * @param idRespuesta : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumentoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdDocumentoRespuesta() {
		return idDocumentoRespuesta;
	}

	/**
	 * @param idDocumentoRespuesta : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdDocumentoRespuesta(Integer idDocumentoRespuesta) {
		this.idDocumentoRespuesta = idDocumentoRespuesta;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/03/2018
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/03/2018
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idHistoricoRecepcion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/03/2018
	 */
	public Integer getIdHistoricoRecepcion() {
		return idHistoricoRecepcion;
	}

	/**
	 * @param idHistoricoRecepcion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/03/2018
	 */
	public void setIdHistoricoRecepcion(Integer idHistoricoRecepcion) {
		this.idHistoricoRecepcion = idHistoricoRecepcion;
	}

	/**
	 * @return the nombreAdjuntoOriginal
	 */
	public String getNombreAdjuntoOriginal() {
		return nombreAdjuntoOriginal;
	}

	/**
	 * @param nombreAdjuntoOriginal the nombreAdjuntoOriginal to set
	 */
	public void setNombreAdjuntoOriginal(String nombreAdjuntoOriginal) {
		this.nombreAdjuntoOriginal = nombreAdjuntoOriginal;
	}

	/**
	 * @return the nombreAdjunto
	 */
	public String getNombreAdjunto() {
		return nombreAdjunto;
	}

	/**
	 * @param nombreAdjunto the nombreAdjunto to set
	 */
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}

	/**
	 * @return the idComentario
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario the idComentario to set
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

}
