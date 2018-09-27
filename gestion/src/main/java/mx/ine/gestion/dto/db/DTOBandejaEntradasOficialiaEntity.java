/**
 * @(#)DTOBandejaEntradasOficialiaEntity.java 12/11/2017
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




import javax.persistence.Transient;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.org.ine.servicios.dto.DTOBase;
import mx.org.ine.servicios.dto.DTOPaginarLazyInterface;











import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Clase para mapeo de la tabla de BANDEJA_ENTRADAS_OFICIALIA
 * perteneciente al esquema de GESTION4 
 *
 * @author David Rodríguez Corral 
 * @since 12/11/2017
 */
@Entity
@Table(name="BANDEJA_ENTRADAS_OFICIALIA", schema="gestion4")
@IdClass(value = DTOBandejaEntradasOficialiaID.class)
public class DTOBandejaEntradasOficialiaEntity extends DTOBase implements DTOPaginarLazyInterface{
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 7296513418454768423L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */	
	
	/**
	 * Parte de la llave primaria, es llave foranea a OFICIALIAS
	 */
	@Id
	@Column(name="ID_OFICIALIA")
	private Integer idOficialia;
	
	/**
	 * Objeto para cargar la información de la tabla de OFICIALIAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFICIALIA", insertable = false, updatable = false)
	private DTOOficialiaEntity dtoOficialia;
	
	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name="ID_DOCUMENTO")
	private Integer idDocumento;
	
	/**
	 * Objeto para cargar la información de la tabla de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity dtoDocumento;
	
	/**
	 * Indica el año de la firma.
	 *
	 */
	@Column(name="ANIO")
	private Integer anio;
	
	/**
	 * Indica el id del área remitente
	 */
	@Column(name="ID_AREA_REMITENTE")
	private Integer idAreaRemitente;
	
	/**
	 * Indica el tipo del área remitente
	 */
	@Column(name="TIPO_AREA_REMITENTE")
	private Integer tipoAreaRemitente;
	
	/**
	 * Parte de la llave primaria, es llave foranea a C_AREAS
	 */
	@Id
	@Column(name="ID_AREA_DESTINATARIO")
	private Integer idAreaDestinatario;
	
	/**
	 * Parte de la llave primaria, es llave foranea a C_AREAS
	 */
	@Id
	@Column(name="TIPO_AREA_DESTINATARIO")
	private Integer tipoAreaDestinatario;
	
	/**
	 * Objeto para cargar la información de la tabla de CAREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_AREA_REMITENTE", insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_AREA_REMITENTE", insertable = false, updatable = false)
	})
	private DTOCAreaEntity dtoAreasRemitente;
	
	/**
	 * Objeto para cargar la información de la tabla de CAREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_AREA_DESTINATARIO", insertable = false, updatable = false),
		@JoinColumn(name = "TIPO_AREA_DESTINATARIO", insertable = false, updatable = false)
	})
	private DTOCAreaEntity dtoAreasDestinatario;
	
	/**
	 * Indica si el documento se encuentra en clasificación
	 */
	@Column(name="EN_CLASIFICACION")
	private Integer enClasificacion;
	
	/**
	 * Indica sel id de la oficilia clasificando
	 */
	@Column(name="ID_OFICIALIA_CLASIFICANDO")
	private Integer idOficialiaClasificando;
	
	/**
	 * Objeto para cargar la información de ESTRUCTURA_AREAS
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFICIALIA_CLASIFICANDO", insertable = false, updatable = false)
	private DTOOficialiaEntity dtoOficialiaClasificando;
	
	/**
     * Fecha y hora en que se manda a clasificar el documento.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_CLASIFICACION")
    private Date fechaClasificacion;
	
	/**
     * Fecha y hora en que se genera el registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_RECEPCION")
    private Date fechaRecepcion;
    
    /**
	 * Indica si el documento ya fue leído o no
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;
		
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
    
    /**
     * Fecha en que se genera el registro con formato dd/mm/aaaa.
     */
    @Formula("TO_CHAR(fecha_clasificacion, 'DD/MM/YYYY HH:MI AM')")
    private String fechaOficialia;
     
    /**
     * Folio que se asigna a un documento
     */
    @Transient
    private String folio;
   
    /* ------------------------------------- Métodos/Sobrescritura ------------------------------------ */
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idAreaDestinatario == null) ? 0 : idAreaDestinatario
						.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idOficialia == null) ? 0 : idOficialia.hashCode());
		result = prime
				* result
				+ ((tipoAreaDestinatario == null) ? 0 : tipoAreaDestinatario
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		DTOBandejaEntradasOficialiaEntity other = (DTOBandejaEntradasOficialiaEntity) obj;
		if (idAreaDestinatario == null) {
			if (other.idAreaDestinatario != null)
				return false;
		} else if (!idAreaDestinatario.equals(other.idAreaDestinatario))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idOficialia == null) {
			if (other.idOficialia != null)
				return false;
		} else if (!idOficialia.equals(other.idOficialia))
			return false;
		if (tipoAreaDestinatario == null) {
			if (other.tipoAreaDestinatario != null)
				return false;
		} else if (!tipoAreaDestinatario.equals(other.tipoAreaDestinatario))
			return false;
		return true;
	}
	
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOBandejaEntradasOficialiaEntity [idOficialia=" + idOficialia
				+ ", dtoOficialia=" + dtoOficialia + ", idDocumento="
				+ idDocumento + ", dtoDocumento=" + dtoDocumento + ", anio="
				+ anio + ", idAreaRemitente=" + idAreaRemitente
				+ ", tipoAreaRemitente=" + tipoAreaRemitente
				+ ", idAreaDestinatario=" + idAreaDestinatario
				+ ", tipoAreaDestinatario=" + tipoAreaDestinatario
				+ ", dtoAreasRemitente=" + dtoAreasRemitente
				+ ", dtoAreasDestinatario=" + dtoAreasDestinatario
				+ ", enClasificacion=" + enClasificacion
				+ ", idOficialiaClasificando=" + idOficialiaClasificando
				+ ", dtoOficialiaClasificando=" + dtoOficialiaClasificando
				+ ", fechaClasificacion=" + fechaClasificacion
				+ ", fechaRecepcion=" + fechaRecepcion + ", noLeido=" + noLeido
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora
				+ ", fechaOficialia=" + fechaOficialia + ", folio=" + folio
				+ "]";
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
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dto.DTOPaginarLazyInterface#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return new StringBuilder()
		.append(idOficialia)
		.append(idDocumento)
		.append(idAreaDestinatario)
		.append(tipoAreaDestinatario).toString();
	}

	/* ------------------------------------- Getters/Setters ------------------------------------ */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo DTOOficialiaEntity que esta contenido en la variable dtoOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public DTOOficialiaEntity getDtoOficialia() {
		return dtoOficialia;
	}

	/**
	 * @param dtoOficialia : valor que se ingresa a la variable de tipo DTOOficialiaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setDtoOficialia(DTOOficialiaEntity dtoOficialia) {
		this.dtoOficialia = dtoOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaRemitente
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdAreaRemitente() {
		return idAreaRemitente;
	}

	/**
	 * @param idAreaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdAreaRemitente(Integer idAreaRemitente) {
		this.idAreaRemitente = idAreaRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaRemitente
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getTipoAreaRemitente() {
		return tipoAreaRemitente;
	}

	/**
	 * @param tipoAreaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setTipoAreaRemitente(Integer tipoAreaRemitente) {
		this.tipoAreaRemitente = tipoAreaRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable enClasificacion
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getEnClasificacion() {
		return enClasificacion;
	}

	/**
	 * @param enClasificacion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setEnClasificacion(Integer enClasificacion) {
		this.enClasificacion = enClasificacion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialiaClasificando
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public Integer getIdOficialiaClasificando() {
		return idOficialiaClasificando;
	}

	/**
	 * @param idOficialiaClasificando : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setIdOficialiaClasificando(Integer idOficialiaClasificando) {
		this.idOficialiaClasificando = idOficialiaClasificando;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable fechaOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public String getFechaOficialia() {
		return fechaOficialia;
	}

	/**
	 * @param fechaOficialia : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 12/11/2017
	 */
	public void setFechaOficialia(String fechaOficialia) {
		this.fechaOficialia = fechaOficialia;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaDestinatario
	 * 
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public Integer getIdAreaDestinatario() {
		return idAreaDestinatario;
	}

	/**
	 * @param idAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public void setIdAreaDestinatario(Integer idAreaDestinatario) {
		this.idAreaDestinatario = idAreaDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaDestinatario
	 * 
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public Integer getTipoAreaDestinatario() {
		return tipoAreaDestinatario;
	}

	/**
	 * @param tipoAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 29/11/2017
	 */
	public void setTipoAreaDestinatario(Integer tipoAreaDestinatario) {
		this.tipoAreaDestinatario = tipoAreaDestinatario;
	}

	/**
	 * @return valor de tipo DTOOficialiaEntity que esta contenido en la variable dtoOficialiaClasificando
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public DTOOficialiaEntity getDtoOficialiaClasificando() {
		return dtoOficialiaClasificando;
	}

	/**
	 * @param dtoOficialiaClasificando : valor que se ingresa a la variable de tipo DTOOficialiaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setDtoOficialiaClasificando(
			DTOOficialiaEntity dtoOficialiaClasificando) {
		this.dtoOficialiaClasificando = dtoOficialiaClasificando;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoAreasRemitente
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public DTOCAreaEntity getDtoAreasRemitente() {
		return dtoAreasRemitente;
	}

	/**
	 * @param dtoAreasRemitente : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setDtoAreasRemitente(DTOCAreaEntity dtoAreasRemitente) {
		this.dtoAreasRemitente = dtoAreasRemitente;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoAreasDestinatario
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public DTOCAreaEntity getDtoAreasDestinatario() {
		return dtoAreasDestinatario;
	}

	/**
	 * @param dtoAreasDestinatario : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setDtoAreasDestinatario(DTOCAreaEntity dtoAreasDestinatario) {
		this.dtoAreasDestinatario = dtoAreasDestinatario;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaClasificacion
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public Date getFechaClasificacion() {
		return fechaClasificacion;
	}

	/**
	 * @param fechaClasificacion : valor que se ingresa a la variable de tipo Date
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setFechaClasificacion(Date fechaClasificacion) {
		this.fechaClasificacion = fechaClasificacion;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaRecepcion
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion : valor que se ingresa a la variable de tipo Date
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable noLeido
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable folio
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio: valor que se ingresa a la variable de tipo String 
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
	
}
