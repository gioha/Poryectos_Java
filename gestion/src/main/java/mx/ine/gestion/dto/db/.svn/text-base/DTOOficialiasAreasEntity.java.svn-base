/**
 * @(#)DTOOficialiasAreasEntity.java 31/08/2017
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
 * Clase relacionada a la tabla de OFICIALIAS_AREAS en el esquema de GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
@Entity
@Table(name="OFICIALIAS_AREAS", schema="GESTION4")
@IdClass(value = DTOOficialiasAreasID.class)
public class DTOOficialiasAreasEntity extends DTOBase {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = 8514997190404615423L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Parte de la llave primaria de la tabla, es el identificador de la persona registrada como oficialia de partes.
	 */
	@Id
	@Column(name="ID_OFICIALIA")
	private Integer idOficialia;

	/**
	 * Parte de la llave primaria que guarda el área al que pertenece la persona de la oficialia
	 */
	@Id
	@Column(name="ID_AREA")
	private Integer idArea;
	
	/**
	 * Parte de la llave primaria que guarda el tipo de área al que pertenece la persona de la oficialia
	 */
	@Id
	@Column(name="TIPO_AREA")
	private Integer tipoArea;
	
	/**
	 * Objeto para cargar la información de la tabla de OFICIALIA
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFICIALIA", insertable = false, updatable = false)
	private DTOOficialiaEntity dtoOficialia;
	
	/**
     * Cuenta del usuario que registra en la BD.
     */
	@Column(name="USUARIO")
    private String usuario;
	
	/**
     * Fecha y hora en que se genera el registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_HORA")
    private Date fechaHora;
    
	/* ------------------------------------- Métodos ------------------------------------ */
    /* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idOficialia == null) ? 0 : idOficialia.hashCode());
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
		DTOOficialiasAreasEntity other = (DTOOficialiasAreasEntity) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idOficialia == null) {
			if (other.idOficialia != null)
				return false;
		} else if (!idOficialia.equals(other.idOficialia))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
    	return "ID_OFICIALIA= " + this.idOficialia + " ID_AREA=" + this.idArea + " TIPO_AREA= " + tipoArea;
    }
    
    /**
	 * @return valor de tipo String que esta contenido en la variable usuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */	
	public DTOOficialiaEntity getDtoOficialia() {
		return dtoOficialia;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 16/09/2017
	 */
	public void setDtoOficialia(DTOOficialiaEntity dtoOficialia) {
		this.dtoOficialia = dtoOficialia;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHora
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
}
