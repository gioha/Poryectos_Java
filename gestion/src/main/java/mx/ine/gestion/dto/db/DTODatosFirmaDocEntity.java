/**
 * @(#)DTODatosFirmaDocEntity.java 03/10/2017
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
import javax.persistence.Transient;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de DATOS_FIRMA_DOC
 * perteneciente al esquema de GESTION4
 *
 * @author David Rodríguez Corral
 * @since 03/10/2017
 */
@Entity
@Table(name="DATOS_FIRMA_DOC", schema="gestion4")
@IdClass(value = DTODatosFirmaDocID.class)
public class DTODatosFirmaDocEntity extends DTOBase{

	/**
	 * Atributo para la serialización de la clase
	 */
	private static final long serialVersionUID = -8886028391247690764L;
	
	/* ------------------------------------- Atributos/Campos ------------------------------------ */	
	
	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name="ID_DOCUMENTO")
	private Integer idDocumento;
	
	/**
	 * Objeto para cargar la información de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoDocumento;
	
	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name="ID_PERSONA_FIRMA")
	private Integer idPersonaFirma;
	
	/**
	 * Objeto para cargar la información de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_FIRMA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructura;
	
	/**
	 * Cadena que regresa el web service al iniciar un proceso de firma
	 */
	@Column(name="PROCESO_ID")
	private String idProceso;
	
	/**
	 * Cadena que se obtiene del hash de la cadena original
	 */
	@Column(name="HASH_CADENA_ORIGINAL")
	private String hashCadenaOriginal;
	
	/**
	 * Cadena que se obtiene al firmar el hash del certificado del firmante
	 */
	@Column(name="PKCS7")
	private String pkcs7;
	
	/**
	 * Cadena que se obtiene del hash del pkcs7 que será la firma en el documento
	 */
	@Column(name="HASH_PKCS7")
	private String hashPkcs7;
	
	/**
	 * Cadena original que se forma con "||id del documento|hash del documento|nombre del documento||"
	 */
	@Column(name="CADENA_ORIGINAL")
	private String cadenaOriginal;

	
	/**
	 * Cadena que regresa el web service para finalizar un proceso de firma
	 */
	@Column(name="SECUENCIA_ID")
	private String idSecuencia;
	
	/**
	 * Cadena que regresa el web service para finalizar un proceso de firma
	 */
	@Transient
	private String nombreDocumnto;
		
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
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersonaFirma == null) ? 0 : idPersonaFirma.hashCode());
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
		DTODatosFirmaDocEntity other = (DTODatosFirmaDocEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersonaFirma == null) {
			if (other.idPersonaFirma != null)
				return false;
		} else if (!idPersonaFirma.equals(other.idPersonaFirma))
			return false;
		return true;
	}

	
    /* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODatosFirmaDocEntity [idDocumento=" + idDocumento
				+ ", dtoDocumento=" + dtoDocumento + ", idPersonaFirma="
				+ idPersonaFirma + ", dtoEstructura=" + dtoEstructura
				+ ", idProceso=" + idProceso + ", hashCadenaOriginal="
				+ hashCadenaOriginal + ", pkcs7=" + pkcs7 + ", hashPkcs7="
				+ hashPkcs7 + ", cadenaOriginal=" + cadenaOriginal
				+ ", idSecuencia=" + idSecuencia + ", nombreDocumnto="
				+ nombreDocumnto + ", usuario=" + usuario + ", fechaHora="
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
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public DTOEstructuraAreasEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setDtoDocumento(DTOEstructuraAreasEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public Integer getIdPersonaFirma() {
		return idPersonaFirma;
	}

	/**
	 * @param idPersonaFirma : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setIdPersonaFirma(Integer idPersonaFirma) {
		this.idPersonaFirma = idPersonaFirma;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructura
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public DTOEstructuraAreasEntity getDtoEstructura() {
		return dtoEstructura;
	}

	/**
	 * @param dtoEstructura : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setDtoEstructura(DTOEstructuraAreasEntity dtoEstructura) {
		this.dtoEstructura = dtoEstructura;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable pkcs7
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public String getPkcs7() {
		return pkcs7;
	}

	/**
	 * @param pkcs7 : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/10/2017
	 */
	public void setPkcs7(String pkcs7) {
		this.pkcs7 = pkcs7;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable idProceso
	 *
	 * @author David Rodríguez Corral
	 * @since 25/10/2017
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * @param idProceso : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 25/10/2017
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable idSecuencia
	 *
	 * @author David Rodríguez Corral
	 * @since 25/10/2017
	 */
	public String getIdSecuencia() {
		return idSecuencia;
	}

	/**
	 * @param idSecuencia : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 25/10/2017
	 */
	public void setIdSecuencia(String idSecuencia) {
		this.idSecuencia = idSecuencia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreDocumnto
	 *
	 * @author David Rodríguez Corral
	 * @since 28/10/2017
	 */
	public String getNombreDocumnto() {
		return nombreDocumnto;
	}

	/**
	 * @param nombreDocumnto : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 28/10/2017
	 */
	public void setNombreDocumnto(String nombreDocumnto) {
		this.nombreDocumnto = nombreDocumnto;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable hashCadenaOriginal
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public String getHashCadenaOriginal() {
		return hashCadenaOriginal;
	}

	/**
	 * @param hashCadenaOriginal : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public void setHashCadenaOriginal(String hashCadenaOriginal) {
		this.hashCadenaOriginal = hashCadenaOriginal;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable hashPkcs7
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public String getHashPkcs7() {
		return hashPkcs7;
	}

	/**
	 * @param hashPkcs7 : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public void setHashPkcs7(String hashPkcs7) {
		this.hashPkcs7 = hashPkcs7;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable cadenaOriginal
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	/**
	 * @param cadenaOriginal : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	
}
