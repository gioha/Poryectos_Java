/**
 * @(#)DTOJerarquiaPersonasEntity.java 30/08/2017
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
import javax.persistence.Transient;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.org.ine.servicios.dto.DTOBase;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * DTO para la tabla de JERARQUIA_PERSONAS para el schema gestion4  
 *
 * @author Pável Alexei Martínez Regalado
 * @since 06/09/2017
 */
@Entity
@Table(name="JERARQUIA_PERSONAS", schema="gestion4")
@IdClass(value = DTOJerarquiaPersonasID.class)
public class DTOJerarquiaPersonasEntity extends DTOBase {
	
	
	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = -8426437957697205129L;
	
	/**
	 * Atributo que guarda el ID de la Persona.
	 */
	@Id
	@Column(name = "ID_PERSONA", nullable = false)
	private Integer	idPersona;

	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructuraAreas;

	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_PADRE", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructuraAreasPadre;
	
	/**
	 * Atributo que guarda el ID del Área.
	 */
	@Column(name = "ID_AREA", nullable = false)
	private Integer	idArea;
	
	/**
	 * Atributo que guarda el Tipo de área.
	 */
	@Column(name = "TIPO_AREA", nullable = false)
	private Integer	tipoArea;

	/**
	 * Atributo que guarda el area
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "ID_AREA", referencedColumnName = "ID_AREA"),
			@JoinColumn(updatable = false, insertable = false, name = "TIPO_AREA", referencedColumnName = "TIPO_AREA") })
	private DTOCAreaEntity area;
	
	/**
	 * Atributo que guarda el ID del Nivel.
	 */
	@Column(name = "ID_NIVEL", nullable = false)
	private Integer	idNivel;
	
	/**
	 * Atributo que guarda el ID de la Persona Padre.
	 */
	@Id
	@Column(name = "ID_PERSONA_PADRE", nullable = false)
	private Integer	idPersonaPadre;
	
	/**
	 * Atributo que guarda el ID del Área.
	 */
	@Column(name = "NIVEL_PADRE", nullable = false)
	private Integer	nivelPadre;
	
	/**
	 * Atributo que guarda el ID del Área.
	 */
	@Column(name = "USUARIO")
	private String usuario;
	
	/**
	 * Atributo que guarda el ID del Área.
	 */
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

    
    /*	********************** Atributos transient ********************** */
	
	/*
	 * Atributo que guarda la cuenta de la pesona
	 */
	@Transient
	private String cuentaPersona;
	
	/*
	 * Atributo que guarda la cuenta del padre
	 */
	@Transient
	private String cuentaPadre;
	

	
	/*	********************** Constructores ********************** */
	
	/**
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public DTOJerarquiaPersonasEntity() {}

	/**
	 * Constructor
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public DTOJerarquiaPersonasEntity(Integer idPersona, Integer idArea, Integer tipoArea, Integer idNivel, Integer idPersonaPadre, Integer nivelPadre) {
		this.idPersona = idPersona;
		this.idArea = idArea;
		this.tipoArea = tipoArea;
		this.idNivel = idNivel;
		this.idPersonaPadre = idPersonaPadre;
		this.nivelPadre = nivelPadre;
	}
	
	/**
	 * Constructor
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public DTOJerarquiaPersonasEntity(Integer idPersona, Integer idArea, Integer tipoArea, Integer idNivel, 
			Integer idPersonaPadre, Integer nivelPadre, String cuenta, String cuentaPadre) {
		this.idPersona = idPersona;
		this.idArea = idArea;
		this.tipoArea = tipoArea;
		this.idNivel = idNivel;
		this.idPersonaPadre = idPersonaPadre;
		this.nivelPadre = nivelPadre;
		this.cuentaPersona = cuenta;
		this.cuentaPadre = cuentaPadre;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ID_PESONA=" + this.idPersona + " ID_AREA=" + this.idArea + " ID_TIPO_AREA=" + this.tipoArea + " ID_NIVEL=" + this.idNivel +
				" ID_PADRE" + this.idPersonaPadre + " CUENTA_PERSONA=" + this.cuentaPersona + " CUENTA_PADRE=" + this.cuentaPadre + " NIVEL_PADRE" + this.nivelPadre;
	}
	
	
	
	/*
	 * GETTERS Y SETTERS
	 */

	

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idPersona
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	
	/**
	 * @return int: hashcode
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cuentaPadre == null) ? 0 : cuentaPadre.hashCode());
		result = prime * result
				+ ((cuentaPersona == null) ? 0 : cuentaPersona.hashCode());
		return result;
	}

	/**
	 * @param Object obj: objeto con el que se va a comparar
	 *
	 * @return boolean: true si hubo coincidencia, false si no
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOJerarquiaPersonasEntity other = (DTOJerarquiaPersonasEntity) obj;
		if (cuentaPadre == null) {
			if (other.cuentaPadre != null)
				return false;
		} else if (!cuentaPadre.equals(other.cuentaPadre))
			return false;
		if (cuentaPersona == null) {
			if (other.cuentaPersona != null)
				return false;
		} else if (!cuentaPersona.equals(other.cuentaPersona))
			return false;
		return true;
	}

	/**
	 * @param : valor que se ingresa al atributo idPersona de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param : valor que se ingresa al atributo idArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo tipoArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param : valor que se ingresa al atributo tipoArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return the area
	 */
	public DTOCAreaEntity getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(DTOCAreaEntity area) {
		this.area = area;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idNivel
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdNivel() {
		return idNivel;
	}

	/**
	 * @param : valor que se ingresa al atributo idNivel de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idPersonaPadre
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getIdPersonaPadre() {
		return idPersonaPadre;
	}

	/**
	 * @param : valor que se ingresa al atributo idPersonaPadre de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setIdPersonaPadre(Integer idPersonaPadre) {
		this.idPersonaPadre = idPersonaPadre;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo nivelPadre
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Integer getNivelPadre() {
		return nivelPadre;
	}

	/**
	 * @param : valor que se ingresa al atributo nivel de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setNivelPadre(Integer nivelPadre) {
		this.nivelPadre = nivelPadre;
	}
	
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}
	
	public DTOEstructuraAreasEntity getDtoEstructuraAreasPadre() {
		return dtoEstructuraAreasPadre;
	}

	public void setDtoEstructuraAreasPadre(
			DTOEstructuraAreasEntity dtoEstructuraAreasPadre) {
		this.dtoEstructuraAreasPadre = dtoEstructuraAreasPadre;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo usuario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param : valor que se ingresa al atributo usuario de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo fechaHora
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param : valor que se ingresa al atributo fechaHora de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 06/09/2017
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo String que esta contenido en el atributo cuentaPersona
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public String getCuentaPersona() {
		return cuentaPersona;
	}

	/**
	 * @param : valor que se ingresa al atributo cuentaPersona de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public void setCuentaPersona(String cuentaPersona) {
		this.cuentaPersona = cuentaPersona;
	}

	/**
	 * @return valor de tipo String que esta contenido en el atributo cuentaPersonaPadre
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public String getCuentaPadre() {
		return cuentaPadre;
	}

	/**
	 * @param : valor que se ingresa al atributo cuentaPadre de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public void setCuentaPadre(String cuentaPadre) {
		this.cuentaPadre = cuentaPadre;
	}
	
	
	
}