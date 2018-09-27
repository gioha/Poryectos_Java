/**
 * @(#)DTONotificacionesEntity.java 29/11/2017
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
 * @author Homero Fidel Villanueva
 *
 */
@Entity
@Table(name = "NOTIFICACIONES", schema = "gestion4")
@IdClass(value = DTONotificacionesID.class)
public class DTONotificacionesEntity extends DTOBase {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 6727472346957822957L;

	/**
	 * Atributo que es uno de los identificadores de la tabla "NOTIFICACIONES",
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	/**
	 * Parte de la llave primaria
	 */
	@Id
	@Column(name = "ID_MODULO")
	private Integer idModulo;
	
	/**
	 * Atributo que guarda el número de documentos en los cual el usuario tiene
	 * que realizar alguna acción
	 */
	@Column(name = "DOCUMENTOS_PENDIENTES")
	private Integer documentosPendientes;

	/**
	 * Atributo utilizado para identificar los apartados de la bandeja
	 */
	@Column(name = "ID_APARTADO")
	private Integer idApartado;
	
	/**
	 * Atributo que guarda el número de documentos en los cual el usuario tiene
	 * que realizar alguna acción
	 */
	@Column(name = "DOCUMENTOS_PENDIENTES_2")
	private Integer documentosPendientes2;

	/**
	 * Atributo utilizado para identificar los apartados de la bandeja
	 */
	@Column(name = "ID_APARTADO_2")
	private Integer idApartado2;
	
	/**
	 * Atributo que guarda el número de documentos en los cual el usuario tiene
	 * que realizar alguna acción
	 */
	@Column(name = "DOCUMENTOS_PENDIENTES_3")
	private Integer documentosPendientes3;

	/**
	 * Atributo utilizado para identificar los apartados de la bandeja
	 */
	@Column(name = "ID_APARTADO_3")
	private Integer idApartado3;
	
	/**
	 * Atributo que guarda el número de documentos en los cual el usuario tiene
	 * que realizar alguna acción
	 */
	@Column(name = "DOCUMENTOS_PENDIENTES_4")
	private Integer documentosPendientes4;

	/**
	 * Atributo utilizado para identificar los apartados de la bandeja
	 */
	@Column(name = "ID_APARTADO_4")
	private Integer idApartado4;

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

	// ------------------------ Métodos ------------------------ //
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idModulo == null) ? 0 : idModulo.hashCode());
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
		DTONotificacionesEntity other = (DTONotificacionesEntity) obj;
		if (idModulo == null) {
			if (other.idModulo != null)
				return false;
		} else if (!idModulo.equals(other.idModulo))
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
		return "DTONotificacionesEntity [idPersona=" + idPersona
				+ ", idModulo=" + idModulo + ", documentosPendientes="
				+ documentosPendientes + ", idApartado=" + idApartado
				+ ", documentosPendientes2=" + documentosPendientes2
				+ ", idApartado2=" + idApartado2 + ", documentosPendientes3="
				+ documentosPendientes3 + ", idApartado3=" + idApartado3
				+ ", documentosPendientes4=" + documentosPendientes4
				+ ", idApartado4=" + idApartado4 + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + "]";
	}

	// -------------- GETTERS & SETTERS -------------------- //
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;

	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idModulo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         documentosPendientes
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public Integer getDocumentosPendientes() {
		return documentosPendientes;
	}

	/**
	 * @param documentosPendientes
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void setDocumentosPendientes(Integer documentosPendientes) {
		this.documentosPendientes = documentosPendientes;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idApartado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/11/2017
	 */
	public int getIdApartado() {
		return idApartado;
	}

	/**
	 * @param idApartado : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/11/2017
	 */
	public void setIdApartado(int idApartado) {
		this.idApartado = idApartado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes2
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getDocumentosPendientes2() {
		return documentosPendientes2;
	}

	/**
	 * @param documentosPendientes2 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setDocumentosPendientes2(Integer documentosPendientes2) {
		this.documentosPendientes2 = documentosPendientes2;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idApartado2
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public int getIdApartado2() {
		return idApartado2;
	}

	/**
	 * @param idApartado2 : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setIdApartado2(int idApartado2) {
		this.idApartado2 = idApartado2;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes3
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getDocumentosPendientes3() {
		return documentosPendientes3;
	}

	/**
	 * @param documentosPendientes3 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setDocumentosPendientes3(Integer documentosPendientes3) {
		this.documentosPendientes3 = documentosPendientes3;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idApartado3
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public int getIdApartado3() {
		return idApartado3;
	}

	/**
	 * @param idApartado3 : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setIdApartado3(int idApartado3) {
		this.idApartado3 = idApartado3;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes4
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public Integer getDocumentosPendientes4() {
		return documentosPendientes4;
	}

	/**
	 * @param documentosPendientes4 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setDocumentosPendientes4(Integer documentosPendientes4) {
		this.documentosPendientes4 = documentosPendientes4;
	}

	/**
	 * @return valor de tipo int que esta contenido en la variable idApartado4
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public int getIdApartado4() {
		return idApartado4;
	}

	/**
	 * @param idApartado4 : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setIdApartado4(int idApartado4) {
		this.idApartado4 = idApartado4;
	}

}
