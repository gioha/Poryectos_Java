/**
 * @(#)DTOInstruccionesEntity.java 20/08/2017
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

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de INSTRUCCIONES
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral
 * @since 18/08/2017
 */
@Entity
@Table(name="INSTRUCCIONES", schema="gestion4")
@IdClass(value = DTOInstruccionesID.class)
public class DTOInstruccionesEntity extends DTOBase{

	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = -3434492333829697440L;

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
	 * Llave primaria
	 */
	@Id
	@Column(name="ID_INSTRUCCION")
	private Integer idInstruccion;
	
	/**
	 * Indica el tipo de instrución para Atencion e Informativa.
	 * 1 = Atención
	 * 2 = Informativa
	 */
	@Column(name="TIPO_INSTRUCCION")
	private Integer tipoInstruccion;
	
	/**
	 * Indica la descripción de la instrucción.
	 */
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	/**
	 * Indica el tiempo de respuesta en días.
	 */
	@Column(name="DIAS_RESPUESTA")
	private Integer diasRespuesta;
	
	/**
	 * Indica si se desea enviar una notificación por correo.
	 * 0 = No
	 * 1 = Si
	 */
	@Column(name="CON_NOTIFICACION")
	private Integer conNotificacion;
	
	/**
	 * Indica el estatus de la instrucción, si está activa o eliminada.
	 * "A" Activa
	 * "E" Eliminada
	 */
	@Column(name="ESTATUS")
	private String estatus;
	
	/**
	 * Indica el orden en que se mostrarán las instrucciones
	 */
	@Column(name="ORDENAMIENTO")
	private Integer ordenamiento;
	
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
    
    /* ------------------------------------- Constructores ------------------------------------ */
		
	/*
	 * Constructor para una instrucción
	 * 
	 * @param idPersona: Valor que define el id del usuario en sesión.
	 * @param tipoInstruccion: Valor que define el tipo de instrucción.
	 * @param descripcion: Valor que define la descripción de la instrucción.
	 * @param diasRespuesta: Valor que define los días de respuesta.
	 * @param conNotificacion: Valor que define si se envía notificación de correo o no.
	 * @param estatus: Valor que define el estatus de la instrucción
	 * @param ordenamiento: Valor que define el número de la instrucción
	 */
	public DTOInstruccionesEntity(Integer idPersona, Integer tipoInstruccion, String descripcion, 
			Integer diasRespuesta, Integer conNotificacion, String estatus, Integer ordenamiento){
		this.idPersona = idPersona;
		this.tipoInstruccion = tipoInstruccion;
		this.descripcion = descripcion;
		this.diasRespuesta = diasRespuesta;
		this.conNotificacion = conNotificacion;
		this.estatus = estatus;
		this.ordenamiento = ordenamiento;
		
	}
	
	/*
	 * Constructor vacío
	 */
	public DTOInstruccionesEntity(){
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
				+ ((idInstruccion == null) ? 0 : idInstruccion.hashCode());
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
		DTOInstruccionesEntity other = (DTOInstruccionesEntity) obj;
		if (idInstruccion == null) {
			if (other.idInstruccion != null)
				return false;
		} else if (!idInstruccion.equals(other.idInstruccion))
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
		return "DTOInstruccionesEntity [idPersona=" + idPersona
				+ ", dtoEstructuraAreas=" + dtoEstructuraAreas
				+ ", idInstruccion=" + idInstruccion + ", tipoInstruccion="
				+ tipoInstruccion + ", descripcion=" + descripcion
				+ ", diasRespuesta=" + diasRespuesta + ", conNotificacion="
				+ conNotificacion + ", estatus=" + estatus + ", ordenamiento="
				+ ordenamiento + ", usuario=" + usuario + ", fechaHora="
				+ fechaHora + "]";
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
	 * @since 28/08/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idInstruccion
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getIdInstruccion() {
		return idInstruccion;
	}

	/**
	 * @param idInstruccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setIdInstruccion(Integer idInstruccion) {
		this.idInstruccion = idInstruccion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoInstruccion
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getTipoInstruccion() {
		return tipoInstruccion;
	}

	/**
	 * @param tipoInstruccion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setTipoInstruccion(Integer tipoInstruccion) {
		this.tipoInstruccion = tipoInstruccion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable descripcion
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable diasRespuesta
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getDiasRespuesta() {
		return diasRespuesta;
	}

	/**
	 * @param diasRespuesta : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setDiasRespuesta(Integer diasRespuesta) {
		this.diasRespuesta = diasRespuesta;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable conNotificacion
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getConNotificacion() {
		return conNotificacion;
	}

	/**
	 * @param conNotificacion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setConNotificacion(Integer conNotificacion) {
		this.conNotificacion = conNotificacion;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable estatus
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable ordenamiento
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getPrioridad() {
		return ordenamiento;
	}

	/**
	 * @param ordenamiento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setPrioridad(Integer ordenamiento) {
		this.ordenamiento = ordenamiento;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructuraAreas
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	/**
	 * @param dtoEstructuraAreas : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable ordenamiento
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public Integer getOrdenamiento() {
		return ordenamiento;
	}

	/**
	 * @param ordenamiento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 28/08/2017
	 */
	public void setOrdenamiento(Integer ordenamiento) {
		this.ordenamiento = ordenamiento;
	}	
	
}
