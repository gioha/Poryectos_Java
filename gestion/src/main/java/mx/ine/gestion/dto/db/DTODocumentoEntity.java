/**
 * @(#)DTODocumentoEntity.java 31/08/2017
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase de mapeo para la tabla DOCUMENTOS.
 * 
 * @author Sergio Ley Garcia
 * @update José Miguel Ortiz
 * @since 02/04/2018
 */
@Entity
@Table(name = "DOCUMENTOS", schema = "gestion4")
public class DTODocumentoEntity extends DTOBase {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID	= 2616340194017248855L;

	/**
	 * Atributo que guarda el ID del documento
	 */
	@Id
	@SequenceGenerator(sequenceName = "S_ID_DOCUMENTO", name = "S_ID_DOCUMENTO", schema = "gestion4")
	@GeneratedValue(generator = "S_ID_DOCUMENTO", strategy = GenerationType.AUTO)
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;

	/**
	 * Atributo que guarda el año del documento
	 */
	@Column(name = "ANIO")
	private Integer	anio;

	/**
	 * Atributo que contiene el identificador de la persona que realizo la
	 * creación del documento
	 */
	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	/**
	 * Atributo que guarda la persona que crea el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity persona;

	//TODO
	@Column(name = "ID_AREA")
	private Integer						idArea;

	//TODO
	@Column(name = "TIPO_AREA")
	private Integer						tipoArea;

	/**
	 * Atributo que guarda el area de la persona que crea el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA", insertable = false, updatable = false),
			@JoinColumn(name = "TIPO_AREA", referencedColumnName = "TIPO_AREA", insertable = false, updatable = false)
	})
	private DTOCAreaEntity area;

	/**
	 * Identificador del tipo de documento que se utilizo para crear el documento
	 */
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Integer idTipoDocumento;

	/**
	 * Atributo que guarda el tipo del documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO", insertable = false, updatable = false)
	private DTOCTipoDocumentoEntity	tipoDocumento;

	/**
	 * Atributo que contiene la llave privada del acronimo que esta relacionado al documento
	 */
	@Column(name = "ID_ACRONIMO")
	private Integer idAcronimo;
	
	/**
	 * Atributo que guarda el acrónimo del documento
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
	 * Atributo que guarda el Acronimo-número del documento
	 */
	@Column(name = "NUM_DOCUMENTO")
	private String noDocumento;
	
	/**
	 * Atributo que guarda el estatus del documento
	 */
	@Column(name = "ESTATUS")
	private char estatus;

	/**
	 * Atributo que guarda el estatus de firmado del documento 0 no firmado, 1
	 * firmado
	 */
	@Column(name = "FIRMADO")
	private Integer	firmado;
	
	/**
	 * Atributo que guarda el estatus de validado del documento 0 no validado, 1
	 * validado
	 */
	@Column(name = "VALIDADO")
	private Integer	validado;
	
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
		DTODocumentoEntity other = (DTODocumentoEntity) obj;
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
		return "DTODocumentoEntity [idDocumento=" + idDocumento + ", anio="
				+ anio + ", idPersona=" + idPersona + ", persona=" + persona
				+ ", idArea=" + idArea + ", tipoArea=" + tipoArea + ", area="
				+ area + ", idTipoDocumento=" + idTipoDocumento
				+ ", tipoDocumento=" + tipoDocumento + ", idAcronimo="
				+ idAcronimo + ", acronimo=" + acronimo + ", noDocumento="
				+ noDocumento + ", estatus=" + estatus + ", firmado=" + firmado
				+ ", validado=" + validado + ", fechaCreacion=" + fechaCreacion
				+ ", fechaRecepcionOf=" + fechaRecepcionOf
				+ ", nombreDocumento=" + nombreDocumento + ", tipoCaptura="
				+ tipoCaptura + ", asunto=" + asunto + ", idOficialia="
				+ idOficialia + ", editado=" + editado
				+ ", procedenciaDocumento=" + procedenciaDocumento
				+ ", contieneAnexos=" + contieneAnexos
				+ ", idDocumentoRespuesta=" + idDocumentoRespuesta
				+ ", documentoRespuesta=" + documentoRespuesta + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + "]";
	}

	/**
	 * Atributo que guarda la fecha de creacion del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	/**
	 * Atributo que guarda la fecha de recepción del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_RECEPCION_OF")
	private Date fechaRecepcionOf;

	/**
	 * Atributo que guarda el nombre del archivo
	 */
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;
	
	/**
	 * Atributo que guarda el tipo de captura utilizado: 1 nuevo docuento en
	 * blanco. 2 plantilla. 3 adjunto.
	 */
	@Column(name = "TIPO_CAPTURA")
	private Integer tipoCaptura;

	/**
	 * 
	 */
	@Column(name = "ASUNTO")
	private String	asunto;
	
	/**
	 * 
	 */
	@Column(name = "ID_OFICIALIA")
	private Integer	idOficialia;

	/**
	 * Atributo que marca si ha sido editado este documento
	 */
	@Column(name = "EDITADO")
	private Integer editado;

	/**
	 * Atributo que guarda la procedencia del utilizado
	 */
	@Column(name = "PROCEDENCIA_DOCUMENTO")
	private char procedenciaDocumento;

	/**
	 * Atributo que indica si el documento tiene anexos o no (es una bandera, 0 es NO y 1 es SI)
	 */
	@Column(name = "CONTIENE_ANEXOS")
	private Integer contieneAnexos;
	
	/**
	 * Atributo que indica que documento se está respondiendo
	 */
	@Column(name = "ID_DOCUMENTO_RESPUESTA")
	private Integer idDocumentoRespuesta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO_RESPUESTA", insertable = false, updatable = false)
	private DTODocumentoEntity documentoRespuesta;
	
	/**
	 * Atributo que guarda el usuario que creo el documento
	 */
	@Column(name = "USUARIO")
	private String usuario;
	
	/**
	 * Atributo que guarda la fecha de creacion del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;


	

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable persona
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOEstructuraAreasEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable area
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOCAreaEntity getArea() {
		return area;
	}

	/**
	 * @param area : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setArea(DTOCAreaEntity area) {
		this.area = area;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo DTOCTipoDocumentoEntity que esta contenido en la variable tipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOCTipoDocumentoEntity getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento : valor que se ingresa a la variable de tipo DTOCTipoDocumentoEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setTipoDocumento(DTOCTipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return valor de tipo DTOAcronimoEntity que esta contenido en la variable acronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public DTOAcronimoEntity getAcronimo() {
		return acronimo;
	}

	/**
	 * @param acronimo : valor que se ingresa a la variable de tipo DTOAcronimoEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setAcronimo(DTOAcronimoEntity acronimo) {
		this.acronimo = acronimo;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable noDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public String getNoDocumento() {
		return noDocumento;
	}

	/**
	 * @param noDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setNoDocumento(String noDocumento) {
		this.noDocumento = noDocumento;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable estatus
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public char getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo char
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable firmado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getFirmado() {
		return firmado;
	}

	/**
	 * @param firmado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setFirmado(Integer firmado) {
		this.firmado = firmado;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable validado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getValidado() {
		return validado;
	}

	/**
	 * @param validado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setValidado(Integer validado) {
		this.validado = validado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable usuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHora
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaCreacion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return fechaRecepcionOf : valor de tipo Date que está contenido en la variable fechaRecepcionOf
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public Date getFechaRecepcionOf() {
		return fechaRecepcionOf;
	}

	/**
	 * @param fechaRecepcionOf : valor que se asigna a la fecha de recepción del documento
	 * @return void
	 * @author José Miguel Ortiz
	 * @since 02/04/2018
	 */
	public void setFechaRecepcionOf(Date fechaRecepcionOf) {
		this.fechaRecepcionOf = fechaRecepcionOf;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * @param nombreDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoCaptura
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * @param tipoCaptura : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setTipoCaptura(Integer tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable asunto
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable editado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getEditado() {
		return editado;
	}

	/**
	 * @param editado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setEditado(Integer editado) {
		this.editado = editado;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable procedenciaDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public char getProcedenciaDocumento() {
		return procedenciaDocumento;
	}

	/**
	 * @param procedenciaDocumento : valor que se ingresa a la variable de tipo char
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setProcedenciaDocumento(char procedenciaDocumento) {
		this.procedenciaDocumento = procedenciaDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable contieneAnexos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer getContieneAnexos() {
		return contieneAnexos;
	}

	/**
	 * @param contieneAnexos : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public void setContieneAnexos(Integer contieneAnexos) {
		this.contieneAnexos = contieneAnexos;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumentoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/03/2018
	 */
	public Integer getIdDocumentoRespuesta() {
		return idDocumentoRespuesta;
	}

	/**
	 * @param idDocumentoRespuesta : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/03/2018
	 */
	public void setIdDocumentoRespuesta(Integer idDocumentoRespuesta) {
		this.idDocumentoRespuesta = idDocumentoRespuesta;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable documentoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/03/2018
	 */
	public DTODocumentoEntity getDocumentoRespuesta() {
		return documentoRespuesta;
	}

	/**
	 * @param documentoRespuesta : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/03/2018
	 */
	public void setDocumentoRespuesta(DTODocumentoEntity documentoRespuesta) {
		this.documentoRespuesta = documentoRespuesta;
	}

}
