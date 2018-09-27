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
 * @(#)DTODocumentoAnexoEntity.java 12/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de mapeo para la tabla DOCUMENTOS_ANEXOS.
 * 
 * @author Sergio Ley Garcia
 * @since 12/09/2017
 */
@Entity
@Table(name = "DOCUMENTOS_ANEXOS", schema = "gestion4")
@IdClass(DTODocumentoAnexoID.class)
public class DTODocumentoAnexoEntity extends DTOBase {

	private static final long	serialVersionUID	= 1587855122868267965L;
	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer				idDocumento;
	/**
	 * Atributo que guarda el ID del documento anexo.
	 */
	@Id
	@Column(name = "ID_ANEXO")
	private Integer				idAnexo;
	/**
	 * Atributo que guarda el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity	documento;
	/**
	 * Atributo que guarda el año del documento anexo.
	 */
	@Column(name = "ANIO")
	private Integer				anio;
	/**
	 * Atributo que guarda el nombre del documento anexo.
	 */
	@Column(name = "NOMBRE_DOC_ANEXO")
	private String				nombreDocAnexo;
	/**
	 * Atributo que guarda el tamaño del documento anexo.
	 */
	@Column(name = "TAMANIO")
	private Double				tamanio;
	/**
	 * Atributo que guarda el nombre del documento anexo.
	 */
	@Column(name = "NOMBRE_GLUSTER_ANEXO")
	private String				nombreAnexo;
	/**
	 * Atributo que guarda el usuario que creo el documento anexo.
	 */
	@Column(name = "USUARIO")
	private String				usuario;
	/**
	 * Atributo que guarda la fecha de creacion del documento anexo.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date				fechaHora;

	/**
	 * Constructor por default de la clase
	 */
	public DTODocumentoAnexoEntity (){
	
		this.idDocumento 	= new Integer(0);
		this.idAnexo 		= new Integer(0);
		this.documento 		= null;
		this.anio			= new Integer(0); 		
		this.nombreDocAnexo = "";
		this.tamanio		= new Double(0);
		this.nombreAnexo	= "";
		this.usuario		= "";
		this.fechaHora		= null;
		
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
		result = prime * result + ((idAnexo == null) ? 0 : idAnexo.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
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
		DTODocumentoAnexoEntity other = (DTODocumentoAnexoEntity) obj;
		if (idAnexo == null) {
			if (other.idAnexo != null)
				return false;
		} else if (!idAnexo.equals(other.idAnexo))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		return true;
	}

	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentoAnexoEntity [idDocumento=" + idDocumento
				+ ", idAnexo=" + idAnexo + ", documento=" + documento
				+ ", anio=" + anio + ", nombreDocAnexo=" + nombreDocAnexo
				+ ", tamanio=" + tamanio + ", nombreAnexo=" + nombreAnexo
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	/**
	 * @return documento
	 */
	public DTODocumentoEntity getDocumento() {

		return documento;
	}

	/**
	 * @param documento
	 *            documento del DTODocumentoAnexoEntity
	 */
	public void setDocumento(DTODocumentoEntity documento) {

		this.documento = documento;
	}

	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoAnexoEntity
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return idAnexo
	 */
	public Integer getIdAnexo() {

		return idAnexo;
	}

	/**
	 * @param idAnexo
	 *            idAnexo del DTODocumentoAnexoEntity
	 */
	public void setIdAnexo(Integer idAnexo) {

		this.idAnexo = idAnexo;
	}

	/**
	 * @return anio
	 */
	public Integer getAnio() {

		return anio;
	}

	/**
	 * @param anio
	 *            anio del DTODocumentoAnexoEntity
	 */
	public void setAnio(Integer anio) {

		this.anio = anio;
	}

	/**
	 * @return nombreDocAnexo
	 */
	public String getNombreDocAnexo() {

		return nombreDocAnexo;
	}

	/**
	 * @param nombreDocAnexo
	 *            nombreDocAnexo del DTODocumentoAnexoEntity
	 */
	public void setNombreDocAnexo(String nombreDocAnexo) {

		this.nombreDocAnexo = nombreDocAnexo;
	}

	/**
	 * @return tamanio
	 */
	public Double getTamanio() {

		return tamanio;
	}

	/**
	 * @param tamanio
	 *            tamanio del DTODocumentoAnexoEntity
	 */
	public void setTamanio(Double tamanio) {

		this.tamanio = tamanio;
	}

	/**
	 * @return usuario
	 */
	@Override
	public String getUsuario() {

		return usuario;
	}

	/**
	 * @param usuario
	 *            usuario del DTODocumentoAnexoEntity
	 */
	@Override
	public void setUsuario(String usuario) {

		this.usuario = usuario;
	}

	/**
	 * @return fechaHora
	 */
	@Override
	public Date getFechaHora() {

		return fechaHora;
	}

	/**
	 * @return nombreAnexo
	 */
	public String getNombreAnexo() {

		return nombreAnexo;
	}

	/**
	 * @param nombreAnexo
	 *            nombreAnexo del DTODocumentoAnexoEntity
	 */
	public void setNombreAnexo(String nombreAnexo) {

		this.nombreAnexo = nombreAnexo;
	}

	/**
	 * @param fechaHora
	 *            fechaHora del DTODocumentoAnexoEntity
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}


}
