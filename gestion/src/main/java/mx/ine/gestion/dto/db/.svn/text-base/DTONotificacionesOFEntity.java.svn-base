/**
 * @(#)DTONotificacionesOFEntity.java 18/12/2017
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
 * Clase que mapea la tabla de NOTIFICACIONES_OF del esquema de GESTION4,
 * la cual tiene como función generar las nótificaciones para el menú de la bandeja de
 * oficialia
 * 
 * @author Roberto Shirásago Domínguez
 * @since 18/12/2017
 */
@Entity
@Table(name = "NOTIFICACIONES_OF", schema = "gestion4")
@IdClass(value = DTONotificacionesOFID.class)
public class DTONotificacionesOFEntity extends DTOBase {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -4270032864508642954L;

	/**
	 * Atributo que contiene el identificador del área a la que pertenece el
	 * acronimo
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que contiene el identificador del tipo área a la que pertenece
	 * el acronimo (puede ser organo centra y organo desconcentrado)
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Parte de la llave primaria el cual identifica al módulo
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
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idModulo == null) ? 0 : idModulo.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
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
		DTONotificacionesOFEntity other = (DTONotificacionesOFEntity) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idModulo == null) {
			if (other.idModulo != null)
				return false;
		} else if (!idModulo.equals(other.idModulo))
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
		return "DTONotificacionesOFEntity [idArea=" + idArea + ", tipoArea="
				+ tipoArea + ", idModulo=" + idModulo
				+ ", documentosPendientes=" + documentosPendientes
				+ ", idApartado=" + idApartado + ", documentosPendientes2="
				+ documentosPendientes2 + ", idApartado2=" + idApartado2
				+ ", documentosPendientes3=" + documentosPendientes3
				+ ", idApartado3=" + idApartado3 + ", documentosPendientes4="
				+ documentosPendientes4 + ", idApartado4=" + idApartado4
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

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

	// -------------- GETTERS & SETTERS -------------------- //

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idModulo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getDocumentosPendientes() {
		return documentosPendientes;
	}

	/**
	 * @param documentosPendientes : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setDocumentosPendientes(Integer documentosPendientes) {
		this.documentosPendientes = documentosPendientes;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idApartado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdApartado() {
		return idApartado;
	}

	/**
	 * @param idApartado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdApartado(Integer idApartado) {
		this.idApartado = idApartado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes2
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getDocumentosPendientes2() {
		return documentosPendientes2;
	}

	/**
	 * @param documentosPendientes2 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setDocumentosPendientes2(Integer documentosPendientes2) {
		this.documentosPendientes2 = documentosPendientes2;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idApartado2
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdApartado2() {
		return idApartado2;
	}

	/**
	 * @param idApartado2 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdApartado2(Integer idApartado2) {
		this.idApartado2 = idApartado2;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes3
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getDocumentosPendientes3() {
		return documentosPendientes3;
	}

	/**
	 * @param documentosPendientes3 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setDocumentosPendientes3(Integer documentosPendientes3) {
		this.documentosPendientes3 = documentosPendientes3;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idApartado3
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdApartado3() {
		return idApartado3;
	}

	/**
	 * @param idApartado3 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdApartado3(Integer idApartado3) {
		this.idApartado3 = idApartado3;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable documentosPendientes4
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getDocumentosPendientes4() {
		return documentosPendientes4;
	}

	/**
	 * @param documentosPendientes4 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setDocumentosPendientes4(Integer documentosPendientes4) {
		this.documentosPendientes4 = documentosPendientes4;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idApartado4
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public Integer getIdApartado4() {
		return idApartado4;
	}

	/**
	 * @param idApartado4 : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void setIdApartado4(Integer idApartado4) {
		this.idApartado4 = idApartado4;
	}
}
