package mx.ine.gestion.dto.db;

import java.io.Serializable;
/**
 * @(#)DTOCapturaCorresponsalForm.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase que mapea la tabla CORRESPONSALES
 * 
 * @author Luis Urrutia
 * @since 30/11/2017
 */
@Entity
@Table(name ="CORRESPONSALES", schema ="GESTION4")
public class DTOCorresponsales extends DTOBase implements Serializable {
	
	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 7379083128492466527L;

	/**
	 * Llave primaria de la tabla, es el identificador del corresponsal
	 */
	@SequenceGenerator (sequenceName="S_ID_CORRESPONSAL",schema="GESTION4",name="sequencia")
	@GeneratedValue(generator="sequencia" ,strategy =GenerationType.AUTO)
	@Id
	@Column(name = "ID_CORRESPONSAL",nullable=false,length=18)
	private Integer idCorresponsal;
	
	/** 
	 * Atributo ID_PERSONA_CO 
	 */
	@Column(name = "ID_PERSONA_CO", nullable = false, length=18)
	private Integer idPersonaCo;
	
	/** 
	 * Atributo ID_PERSONA_TI 
	 */
	@Column(name = "ID_PERSONA_TI", nullable = false, length=18)
	private Integer idPersonaTi;
	
	/** 
	 * Atributo FECHA_CAPTURA 
	 */
	@Column(name = "FECHA_CAPTURA", nullable = false)
	private Date fechaCaptura;
	
	/** 
	 * Atributo FECHA_INICIO 
	 */
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	/** 
	 * Atributo FECHA_FIN 
	 */
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	/** 
	 * Atributo ESTATUS 
	 */
	@Column(name = "ESTATUS", nullable = false)
	private Character estatus;
	
	/** 
	 * Atributo USUARIO. 
	 */
	@Column(name = "USUARIO")
	private String usuario;
	
	/** 
	 * Atributo fecha hora.
	 */ 
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOCorresponsales [idCorresponsal=" + idCorresponsal + ", idPersonaCo=" + idPersonaCo + ", idPersonaTi=" + idPersonaTi
				+ ", fechaCaptura=" + fechaCaptura + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estatus=" + estatus + 
				", usuario=" + usuario + ", fechaHora="	+ fechaHora + "]";
	}

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
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fechaCaptura == null) ? 0 : fechaCaptura.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((idCorresponsal == null) ? 0 : idCorresponsal.hashCode());
		result = prime * result + ((idPersonaCo == null) ? 0 : idPersonaCo.hashCode());
		result = prime * result + ((idPersonaTi == null) ? 0 : idPersonaTi.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		DTOCorresponsales other = (DTOCorresponsales) obj;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (fechaCaptura == null) {
			if (other.fechaCaptura != null)
				return false;
		} else if (!fechaCaptura.equals(other.fechaCaptura))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (idCorresponsal == null) {
			if (other.idCorresponsal != null)
				return false;
		} else if (!idCorresponsal.equals(other.idCorresponsal))
			return false;
		if (idPersonaCo == null) {
			if (other.idPersonaCo != null)
				return false;
		} else if (!idPersonaCo.equals(other.idPersonaCo))
			return false;
		if (idPersonaTi == null) {
			if (other.idPersonaTi != null)
				return false;
		} else if (!idPersonaTi.equals(other.idPersonaTi))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

/* ------------------------------------- Getters y Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idCorresponsal
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Integer getIdCorresponsal() {
		return idCorresponsal;
	}

	/**
	 * @param idCorresponsal : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setIdCorresponsal(Integer idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaCo
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Integer getIdPersonaCo() {
		return idPersonaCo;
	}

	/**
	 * @param idPersonaCo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setIdPersonaCo(Integer idPersonaCo) {
		this.idPersonaCo = idPersonaCo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaTi
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Integer getIdPersonaTi() {
		return idPersonaTi;
	}

	/**
	 * @param idPersonaTi : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setIdPersonaTi(Integer idPersonaTi) {
		this.idPersonaTi = idPersonaTi;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaCaptura
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaCaptura() {
		return fechaCaptura;
	}

	/**
	 * @param fechaCaptura : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaInicio
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaFin
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return valor de tipo Character que esta contenido en la variable estatus
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Character getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo Character
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable usuario
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHora
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 16/11/2017
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
