/**
 * @(#)DTOFirmaDocumentosEntity.java 12/09/2017
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

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase para mapeo de la tabla de ENVIADOS_DOCUMENTOS perteneciente al esquema
 * de GESTION4
 *
 * @author David Rodríguez Corral
 * @since 12/09/2017
 */
@Entity
@Table(name = "ENVIADOS_DOCUMENTOS", schema = "gestion4")
@IdClass(value = DTOEnviadosDocumentosID.class)
public class DTOEnviadosDocumentosEntity extends DTOBase {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 5593506795846731359L;

	/*------------------Atributos/Campos------------------ */

	/**
	 * Parte de la llave primaria, es llave foranea a ESTRUCTURA_AREAS
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	/**
	 * Objeto para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructuraAreas;

	/**
	 * Parte de la llave primaria, es llave foranea a DOCUMENTOS
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
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
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Cuenta del usuario al que se le envía el documento
	 *
	 */
	@Column(name = "USUARIO_ENVIO")
	private String usuarioEnvio;
	
	/**
	 * Atributo que guarda el área al que pertenece la estructura
	 */
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que guarda el tipo de área al que pertenece la estructura
	 */
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Atributo que guarda el area de la persona que crea el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(updatable = false, insertable = false, name = "ID_AREA", referencedColumnName = "ID_AREA"),
			@JoinColumn(updatable = false, insertable = false, name = "TIPO_AREA", referencedColumnName = "TIPO_AREA") })
	private DTOCAreaEntity dtoAreas;

	/**
	 * Cuenta del usuario que registra.
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Fecha y hora en que se genera el registro.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	/**
	 * Indica si el documento fue firmado
	 */
	@Column(name = "NUM_ENVIADO_FIRMA")
	private Integer numEnviadoFirma;
	
	/**
	 * Indica si el documento fue validado
	 */
	@Column(name = "NUM_ENVIADO_VALIDACION")
	private Integer numEnviadoValidacion;
	
	/**
	 * Indica si el documento ya fue leído o no
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;
	
	/**
	 * Indica si el documento ya fue leído o no
	 */
	@Column(name = "ESTATUS")
	private Character estatus;
	
	/*----------------- Métodos/Sobrescritura----------------*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
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
		DTOEnviadosDocumentosEntity other = (DTOEnviadosDocumentosEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
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
		return "DTOEnviadosDocumentosEntity [idPersona=" + idPersona
				+ ", dtoEstructuraAreas=" + dtoEstructuraAreas
				+ ", idDocumento=" + idDocumento + ", dtoDocumento="
				+ dtoDocumento + ", anio=" + anio + ", usuarioEnvio="
				+ usuarioEnvio + ", idArea=" + idArea + ", tipoArea="
				+ tipoArea + ", dtoAreas=" + dtoAreas + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + ", numEnviadoFirma="
				+ numEnviadoFirma + ", numEnviadoValidacion="
				+ numEnviadoValidacion + ", noLeido=" + noLeido + ", estatus="
				+ estatus + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getUsuario()
	 */
	@Override
	public String getUsuario() {
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setUsuario(java.lang.String)
	 */
	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.org.ine.servicios.dto.DTOBase#setFechaHora(java.util.Date)
	 */
	@Override
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/*--------------------- Getters/Setters---------------------*/
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el id
	 * de la persona que creo el documento.
	 * 
	 * @param idPersonaDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdPersonaDoc(Integer idPersonaDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setIdPersona(idPersonaDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el id
	 * de la oficialia.
	 * 
	 * @param idOficialiaDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdOficialiaDoc(Integer idOficialiaDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setIdOficialia(idOficialiaDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el id
	 * del área.
	 * 
	 * @param idAreaDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdAreaDoc(Integer idAreaDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setIdArea(idAreaDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el tipo
	 * del área.
	 * 
	 * @param tipoAreaDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setTipoAreaDoc(Integer tipoAreaDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setTipoArea(tipoAreaDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS"
	 * "ESTRUCTURA_AREAS" y C_TIPO_DOCUMENTOS. Método que inicializa el Id del
	 * tipo de documento del objeto documento.
	 * 
	 * @param idTipoDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}

		if (dtoDocumento.getTipoDocumento() == null) {
			dtoDocumento.setTipoDocumento(new DTOCTipoDocumentoEntity());
		}
		dtoDocumento.setIdTipoDocumento(idTipoDocumento);
		dtoDocumento.getTipoDocumento().setIdTipoDocumento(idTipoDocumento);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y "ACRONIMOS".
	 * Método que inicializa el Id del acronimo del documento del objeto
	 * documento.
	 * 
	 * @param idAcronimoDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdAcronimoDoc(Integer idAcronimoDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}

		if (dtoDocumento.getAcronimo() == null) {
			dtoDocumento.setAcronimo(new DTOAcronimoEntity());
		}
		dtoDocumento.setIdAcronimo(idAcronimoDoc);
		dtoDocumento.getAcronimo().setIdAcronimo(idAcronimoDoc);

	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el número del documento del objeto documento.
	 * 
	 * @param numDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setNumDocumento(String numDocumento) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}

		dtoDocumento.setNoDocumento(numDocumento);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el nombre del documento del objeto documento.
	 * 
	 * @param nombreDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setNombreDocumento(String nombreDocumento) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}

		dtoDocumento.setNombreDocumento(nombreDocumento);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el
	 * estatus del documento del objeto documento.
	 * 
	 * @param estatusDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setEstatusDoc(Character estatusDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}
		dtoDocumento.setEstatus(estatusDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el
	 * estatus del documento del objeto documento.
	 * 
	 * @param firmadoDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setFirmadoDoc(Integer firmadoDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}
		dtoDocumento.setFirmado(firmadoDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa la
	 * bandera validado del documento del objeto documento.
	 * 
	 * @param validadoDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setValidadoDoc(Integer validadoDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}
		dtoDocumento.setValidado(validadoDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el
	 * estatus del documento del objeto documento.
	 * 
	 * @param tipoCapturaDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setTipoCapturaDoc(Integer tipoCapturaDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setTipoCaptura(tipoCapturaDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Método que
	 * inicializa el asunto del documento del objeto documento.
	 * 
	 * @param asuntoDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/11/2017
	 */
	public void setAsuntoDoc(String asuntoDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setAsunto(asuntoDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS".
	 * Inicializa el asunto del documento del objeto documento.
	 * 
	 * @param procedenciaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setProcedenciaDocumento(Character procedenciaDocumento) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setProcedenciaDocumento(procedenciaDocumento);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el
	 * atributo "editado" del documento del objeto documento.
	 * 
	 * @param asunto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/11/2017
	 */
	public void setEditadoDoc(Integer editadoDoc){
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setEditado(editadoDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". 
	 * Inicializa la fecha de creación del objeto documento.
	 * 
	 * @param fechaCreacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since Nov 22, 2017
	 */
	public void setFechaCreacionDoc(Date fechaCreacionDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}
		dtoDocumento.setFechaCreacion(fechaCreacionDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". 
	 * Inicializa la fecha de recepcion de la oficiala del objeto documento.
	 * 
	 * @param fechaCreacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since Nov 22, 2017
	 */
	public void setFechaRecepcionOfDoc(Date fechaRecepcionOfDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}
		dtoDocumento.setFechaRecepcionOf(fechaRecepcionOfDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". 
	 * Inicializa la bandera de contieneAnexos del documento del objeto
	 * documento.
	 * 
	 * @param contieneAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setContieneAnexosDoc(Integer contieneAnexosDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setContieneAnexos(contieneAnexosDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa la
	 * bandera que contiene el id del documento que se está respondiendo
	 * 
	 * @param idDocumentoRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdDocumentoRespuesta(Integer idDocumentoRespuesta) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setIdDocumentoRespuesta(idDocumentoRespuesta);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa el usuario
	 * 
	 * @param usuarioDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setUsuarioDoc(String usuarioDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setUsuario(usuarioDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS". Inicializa la fecha hora
	 * 
	 * @param fechaHoraDoc
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setFechaHoraDoc(Date fechaHoraDoc) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setFechaHora(fechaHoraDoc);
	}
	
	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS",
	 * "ESTRUCTURA_AREAS" y "C_AREAS" Método que inicializa el Id Entidad del
	 * Área del objeto documento.
	 * 
	 * @param idEntidadArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setIdEntidadArea(int idEntidadArea) {

		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}

		if (dtoDocumento.getArea() == null) {
			dtoDocumento.setArea(new DTOCAreaEntity());
		}
		dtoDocumento.getArea().setIdEntidad(idEntidadArea);

	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS". Método que inicializa la descripción del Área del
	 * objeto documento.
	 * 
	 * @param descripcionArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setDescripcionArea(String descripcionArea) {

		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();

		}

		if (dtoDocumento.getArea() == null) {
			dtoDocumento.setArea(new DTOCAreaEntity());
		}

		dtoDocumento.getArea().setDescripcion(descripcionArea);

	}

	/**
	 * Método utilizado para el Join con la tabla "DOCUMENTOS" y
	 * "ESTRUCTURA_AREAS" . Método que inicializa las siglas del Área del objeto
	 * documento.
	 * 
	 * @param siglasArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setSiglasArea(String siglasArea) {
		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}

		if (dtoDocumento.getArea() == null) {
			dtoDocumento.setArea(new DTOCAreaEntity());
		}

		dtoDocumento.getArea().setSiglas(siglasArea);
	}
	

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;

		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}
		dtoDocumento.setIdDocumento(idDocumento);
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable anio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;

		if (dtoDocumento == null) {
			dtoDocumento = new DTODocumentoEntity();
		}

		dtoDocumento.setAnio(anio);
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructuraAreas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public DTOEstructuraAreasEntity getDtoEstructuraAreas() {
		return dtoEstructuraAreas;
	}

	/**
	 * @param dtoEstructuraAreas : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setDtoEstructuraAreas(DTOEstructuraAreasEntity dtoEstructuraAreas) {
		this.dtoEstructuraAreas = dtoEstructuraAreas;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public DTODocumentoEntity getDtoDocumento() {
		return dtoDocumento;
	}

	/**
	 * @param dtoDocumento : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setDtoDocumento(DTODocumentoEntity dtoDocumento) {
		this.dtoDocumento = dtoDocumento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable usuarioEnvio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public String getUsuarioEnvio() {
		return usuarioEnvio;
	}

	/**
	 * @param usuarioEnvio : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setUsuarioEnvio(String usuarioEnvio) {
		this.usuarioEnvio = usuarioEnvio;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo DTOCAreaEntity que esta contenido en la variable dtoAreas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public DTOCAreaEntity getDtoAreas() {
		return dtoAreas;
	}

	/**
	 * @param dtoAreas : valor que se ingresa a la variable de tipo DTOCAreaEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setDtoAreas(DTOCAreaEntity dtoAreas) {
		this.dtoAreas = dtoAreas;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable numEnviadoFirma
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getNumEnviadoFirma() {
		return numEnviadoFirma;
	}

	/**
	 * @param numEnviadoFirma : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setNumEnviadoFirma(Integer numEnviadoFirma) {
		this.numEnviadoFirma = numEnviadoFirma;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable numEnviadoValidacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getNumEnviadoValidacion() {
		return numEnviadoValidacion;
	}

	/**
	 * @param numEnviadoValidacion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setNumEnviadoValidacion(Integer numEnviadoValidacion) {
		this.numEnviadoValidacion = numEnviadoValidacion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable noLeido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return valor de tipo Character que esta contenido en la variable estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public Character getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo Character
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}
	

	
	
}
