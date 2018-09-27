/**
 * @(#)DTODocumentosRemitentesExtEntity.java 02/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
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
 * Clase encargada de proporcionar el mapeo de la tabla de
 * DOCUMENTOS_REMITENTES_EXT perteneciente al esquema de GESTION4
 *
 * @author José Miguel Ortiz
 * @since 02/04/2018
 */
@Entity
@Table(name = "DOCUMENTOS_REMITENTES_EXT", schema = "gestion4")
@IdClass(value = DTODocumentosRemitentesExtID.class)
public class DTODocumentosRemitentesExtEntity extends DTOBase {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 9026166451951483876L;

	/* ------------------------------ Atributos de la clase ------------------------------ */
	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@Id
	@Column(name = "ID_REMITENTE")
	private Integer idRemitente;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde REMITENTES_EXT_OF
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Forma parte de la llave primaria - Llave foránea desde DOCUMENTOS
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;

	/**
	 * Cuenta del usuario que realiza un movimiento
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Fecha y hora en que se genera el movimiento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	/* ------------------------------ Sobreescritura de métodos ------------------------------ */
	/** (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRemitente == null) ? 0 : idRemitente.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result + ((tipoArea == null) ? 0 : tipoArea.hashCode());
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		return result;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;

		DTODocumentosRemitentesExtEntity other = (DTODocumentosRemitentesExtEntity) obj;

		if(idRemitente == null) {
			if(other.idRemitente != null)
				return false;
		} else if(!idRemitente.equals(other.idRemitente))
			return false;

		if(idArea == null) {
			if(other.idArea != null)
				return false;
		} else if(!idArea.equals(other.idArea))
			return false;

		if(tipoArea == null) {
			if(other.tipoArea != null)
				return false;
		} else if(!tipoArea.equals(other.tipoArea))
			return false;

		if(idDocumento == null) {
			if(other.idDocumento != null)
				return false;
		} else if(!idDocumento.equals(other.idDocumento))
			return false;

		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentosRemitentesExtEntity [idRemitente=" + idRemitente
				+ ", idArea=" + idArea + ", tipoArea=" + tipoArea
				+ ", idDocumento=" + idDocumento + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + "]";
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public Integer getIdRemitente() {
		return idRemitente;
	}

	public void setIdRemitente(Integer idRemitente) {
		this.idRemitente = idRemitente;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
}