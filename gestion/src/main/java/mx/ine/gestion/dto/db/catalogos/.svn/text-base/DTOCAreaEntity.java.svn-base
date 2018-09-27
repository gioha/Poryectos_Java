/**
 * @(#)DTOCAreaEntity.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa la entidad C_AREAS que se encuentra en el esquema de gestion4
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */
@Entity
@Table(name = "C_AREAS", schema = "GESTION4")
@IdClass(value = DTOCAreaID.class)
public class DTOCAreaEntity implements Serializable {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long	serialVersionUID	= 4349362738150782895L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */
	
	/**
	 * Atributo que guarda el ID del Área.
	 */
	@Id
	@Column(name = "ID_AREA", nullable = false)
	private Integer	idArea;

	/**
	 * Atributo que guarda el Tipo de Área. 1 ORGANO CENTRAL, 2 ORGANO
	 * DESCONCENTRADO
	 */
	@Id
	@Column(name = "TIPO_AREA", nullable = false)
	private Integer	tipoArea;

	/**
	 * Objeto que contiene la información del tipo de área relacionada
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIPO_AREA", referencedColumnName = "ID_TIPO_AREA", insertable = false, updatable = false)
	private DTOCTipoAreaEntity dtoTipoArea;
	
	/**
	 * Atributo que guarda el nombre del Área.
	 */
	@Column(name = "DESCRIPCION", nullable = false)
	private String	descripcion;

	/**
	 * Atributo que guarda las siglas del Área
	 */
	@Column(name = "SIGLAS", nullable = true)
	private String	siglas;
	
	/**
	 * Atributo que guarda la entidad del Área
	 */
	@Column(name = "ID_ENTIDAD", nullable = true)
	private Integer idEntidad;

	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
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
		DTOCAreaEntity other = (DTOCAreaEntity) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}
	
	/* ---------------------------------------- Getters y Setters ----------------------------------------- */
	
	/**
	 * @return valor de tipo Integer que esta contenido en el atributo idArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param : valor que se ingresa al atributo idArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public void setIdArea(Integer idArea) {

		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en el atributo tipoArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public Integer getTipoArea() {

		return tipoArea;
	}

	/**
	 * @param : valor que se ingresa al atributo tipoArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public void setTipoArea(Integer tipoArea) {

		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en el atributo
	 *         descripcion
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public String getDescripcion() {

		return descripcion;
	}

	/**
	 * @param : valor que se ingresa al atributo descripcion de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	/**
	 * @return valor de tipo String que esta contenido en el atributo siglas
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public String getSiglas() {

		return siglas;
	}

	/**
	 * @param : valor que se ingresa al atributo siglas de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 29/08/2017
	 */
	public void setSiglas(String siglas) {

		this.siglas = siglas;
	}

	/**
	 * @return valor de tipo String que esta contenido en el atributo siglas
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public Integer getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param : valor que se ingresa al atributo siglas de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return valor de tipo DTOCTipoAreaEntity que esta contenido en la variable dtoTipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public DTOCTipoAreaEntity getDtoTipoArea() {
		return dtoTipoArea;
	}

	/**
	 * @param dtoTipoArea : valor que se ingresa a la variable de tipo DTOCTipoAreaEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setDtoTipoArea(DTOCTipoAreaEntity dtoTipoArea) {
		this.dtoTipoArea = dtoTipoArea;
	}
}