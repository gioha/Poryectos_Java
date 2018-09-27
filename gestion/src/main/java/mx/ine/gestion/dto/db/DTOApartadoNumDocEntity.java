/**
 * @(#)DTOApartadoNumDoc.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
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

import org.hibernate.annotations.Formula;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.dto.DTOPaginarLazyInterface;


/**
 * Clase que mapea la tabla de APARTADO_NUM_DOC del esquema
 * de gestion4 la cual tiene la función de apartar folios de los documentos.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
@Entity
@Table(name = "APARTADO_NUM_DOC", schema = "GESTION4")
@IdClass(value = DTOApartadoNumDocID.class)
public class DTOApartadoNumDocEntity extends DTOBase implements DTOPaginarLazyInterface {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = 546662599913518791L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Atributo que indica el id_area del número de documento que se aparta
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que indica el tipo_area del número de documento que se aparta
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Atributo que guarda el area de la persona que crea el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "ID_AREA", referencedColumnName = "ID_AREA"),
			@JoinColumn(updatable = false, insertable = false, name = "TIPO_AREA", referencedColumnName = "TIPO_AREA")
	})
	private DTOCAreaEntity area;
	
	/**
	 * Identificador del tipo de documento del acronimo 
	 * que se utiliza en el número de documento que se aparta
	 */
	@Id
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Integer idTipoDocumento;

	/**
	 * Atributo que contiene la llave privada del acronimo que esta relacionado al documento
	 */
	@Id
	@Column(name = "ID_ACRONIMO")
	private Integer idAcronimo;

	/**
	 * Atributo que guarda la información del acrónimo del documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA", insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_AREA", referencedColumnName = "TIPO_AREA", insertable = false, updatable = false),
		@JoinColumn(name = "ID_ACRONIMO", referencedColumnName = "ID_ACRONIMO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO", insertable = false, updatable = false)
	})
	private DTOAcronimoEntity acronimo;

	/**
	 * Secuencia logica que contiene el número del documento que se esta apartando
	 */
	@Id
	@Column(name = "ID_NUMERO_DOCUMENTO")
	private Integer idNumeroDocumento;

	/**
	 * Atributo que guarda el año del registro
	 */
	@Column(name = "ANIO")
	private Integer	anio;

	/**
	 * Atributo que guarda F o E dependiendo si el apartado es para un documento Fisico o Electronico
	 */
	@Column(name = "TIPO_APARTADO")
	private String tipoApartado;

	/**
	 * Usuario que capturo el apartado del número de documento
	 */
	@Column(name = "ID_PERSONA_CAPTURO")
	private Integer idPersonaCapturo;
	
	/**
	 * Objeto con la información del usuario que capturo el apartado del número de documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "ID_PERSONA_CAPTURO", referencedColumnName = "ID_PERSONA")
	private DTOEstructuraAreasEntity personaCapturo;

	/**
	 * Fecha de captura del apartado del número de documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CAPTURA")
	private Date fechaCaptura;

	/**
	 * Usuario que elimino el apartado del número de documento
	 */
	@Column(name = "ID_PERSONA_ELIMINO")
	private Integer idPersonaElimino;
	
	/**
	 * Objeto con la información del usuario que elimino el apartado del folio
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "ID_PERSONA_ELIMINO", referencedColumnName = "ID_PERSONA")
	private DTOEstructuraAreasEntity personaElimino;

	/**
	 * Fecha de eliminación del apartado del número de documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ELIMINACION")
	private Date fechaEliminacion;
	
	/**
     * Fecha en que se elimina el registro con formato dd/mm/aaaa.
     */
    @Formula("to_char(FECHA_ELIMINACION,'DD/MM/YYYY')")
    private String fechaEliminacionConFormato;

	/**
	 * Contiene el nombre completo del número de documento (acronimo + año + número)
	 */
	@Column(name = "NUM_DOCUMENTO")
	private String numDocumento;
	
	/**
	 * Atributo que guarda el usuario que hizo o modifico el registro
	 */
	@Column(name = "USUARIO")
	private String usuario;
	
	/**
	 * Atributo que guarda la fecha en que se hizo o modifico el registro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	/* ------------------------------------- Sobreescritura de métodos ------------------------------------ */
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOApartadoNumDocEntity [idArea=" + idArea + ", tipoArea="
				+ tipoArea + ", area=" + area + ", idTipoDocumento="
				+ idTipoDocumento + ", idAcronimo=" + idAcronimo
				+ ", acronimo=" + acronimo + ", idNumeroDocumento="
				+ idNumeroDocumento + ", anio=" + anio + ", tipoApartado="
				+ tipoApartado + ", idPersonaCapturo=" + idPersonaCapturo
				+ ", personaCapturo=" + personaCapturo + ", fechaCaptura="
				+ fechaCaptura + ", idPersonaElimino=" + idPersonaElimino
				+ ", personaElimino=" + personaElimino + ", fechaEliminacion="
				+ fechaEliminacion + ", fechaEliminacionConFormato="
				+ fechaEliminacionConFormato + ", numDocumento=" + numDocumento
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAcronimo == null) ? 0 : idAcronimo.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime
				* result
				+ ((idNumeroDocumento == null) ? 0 : idNumeroDocumento
						.hashCode());
		result = prime * result
				+ ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
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
		DTOApartadoNumDocEntity other = (DTOApartadoNumDocEntity) obj;
		if (idAcronimo == null) {
			if (other.idAcronimo != null)
				return false;
		} else if (!idAcronimo.equals(other.idAcronimo))
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idNumeroDocumento == null) {
			if (other.idNumeroDocumento != null)
				return false;
		} else if (!idNumeroDocumento.equals(other.idNumeroDocumento))
			return false;
		if (idTipoDocumento == null) {
			if (other.idTipoDocumento != null)
				return false;
		} else if (!idTipoDocumento.equals(other.idTipoDocumento))
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
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return this.fechaHora;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOPaginarLazyInterface#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return String.valueOf(this.hashCode());
	}
	
	/* ------------------------------------- Getters y Setters ------------------------------------ */

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return valor de tipo DTOAcronimoEntity que esta contenido en la variable acronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public DTOAcronimoEntity getAcronimo() {
		return acronimo;
	}

	/**
	 * @param acronimo : valor que se ingresa a la variable de tipo DTOAcronimoEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setAcronimo(DTOAcronimoEntity acronimo) {
		this.acronimo = acronimo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idNumeroDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getIdNumeroDocumento() {
		return idNumeroDocumento;
	}

	/**
	 * @param idNumeroDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setIdNumeroDocumento(Integer idNumeroDocumento) {
		this.idNumeroDocumento = idNumeroDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoApartado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public String getTipoApartado() {
		return tipoApartado;
	}

	/**
	 * @param tipoApartado : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setTipoApartado(String tipoApartado) {
		this.tipoApartado = tipoApartado;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaCaptura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Date getFechaCaptura() {
		return fechaCaptura;
	}

	/**
	 * @param fechaCaptura : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaEliminacion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	/**
	 * @param fechaEliminacion : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/11/2017
	 */
	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable area
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public DTOCAreaEntity getArea() {
		return area;
	}

	/**
	 * @param area : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public void setArea(DTOCAreaEntity area) {
		this.area = area;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @param numDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaCapturo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOEstructuraAreasEntity getPersonaCapturo() {
		return personaCapturo;
	}

	/**
	 * @param personaCapturo : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setPersonaCapturo(DTOEstructuraAreasEntity personaCapturo) {
		this.personaCapturo = personaCapturo;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaElimino
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOEstructuraAreasEntity getPersonaElimino() {
		return personaElimino;
	}

	/**
	 * @param personaElimino : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setPersonaElimino(DTOEstructuraAreasEntity personaElimino) {
		this.personaElimino = personaElimino;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaCapturo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdPersonaCapturo() {
		return idPersonaCapturo;
	}

	/**
	 * @param idPersonaCapturo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdPersonaCapturo(Integer idPersonaCapturo) {
		this.idPersonaCapturo = idPersonaCapturo;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaElimino
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdPersonaElimino() {
		return idPersonaElimino;
	}

	/**
	 * @param idPersonaElimino : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdPersonaElimino(Integer idPersonaElimino) {
		this.idPersonaElimino = idPersonaElimino;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable fechaEliminacionConFormato
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public String getFechaEliminacionConFormato() {
		return fechaEliminacionConFormato;
	}

	/**
	 * @param fechaEliminacionConFormato : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public void setFechaEliminacionConFormato(String fechaEliminacionConFormato) {
		this.fechaEliminacionConFormato = fechaEliminacionConFormato;
	}

}
