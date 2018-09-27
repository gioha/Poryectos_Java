/**
 * DTOBorradorDocumentosEntity.java 29/08/2017
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

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * 
 * Clase que sirve para hacer el mapeo con la tabla "BORRADOR_DOCUMENTOS" del
 * schema "gestion4".
 * 
 * @author Homero Villanueva Dominguez
 * @since 05/08/2017
 */
@Entity
@Table(name = "BORRADOR_DOCUMENTOS", schema = "gestion4")
@IdClass(value = DTOBorradorDocumentosID.class)
public class DTOBorradorDocumentosEntity extends DTOBase {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = -8251772557194245806L;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "BORRADOR_DOCUMENTOS",
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	/**
	 * Atributo para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity persona;

	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "BORRADOR_DOCUMENTOS",
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;

	/**
	 * Atributo para cargar la información de la tabla de DOCUMENTOS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity documento;

	/**
	 * Atributo que guarda el año del documento
	 */
	@Column(name = "ANIO")
	private Integer anio;

	/**
	 * Atributo que permite saber si al documento se le realizaron
	 * modificaciones
	 */
	@Column(name = "CON_MODIFICACIONES")
	private Integer conModificaciones;

	/**
	 * Atributo que permite saber si el documento tiene comentarios
	 */
	@Column(name = "CON_COMENTARIOS")
	private Integer conComentarios;

	/**
	 * Atributo que permite saber si el documento cuenta con firma
	 */
	@Column(name = "CON_FIRMA")
	private Integer conFirma;

	/**
	 * Atributo que permite saber si el documento cuenta con validacion
	 */
	@Column(name = "CON_VALIDACION")
	private Integer conValidacion;

	/**
	 * Atributo que permite saber si el documento cuenta con validacion
	 */
	@Column(name = "NUM_ENVIADO_FIRMA")
	private Integer numEnviadoFirma;
	/**
	 * Atributo que permite saber si el documento cuenta con validacion
	 */
	@Column(name = "NUM_ENVIADO_VALIDACION")
	private Integer numEnviadoValidacion;

	/**
	 * Atributo que guarda la validacion que se aplicará al documento.
	 */
	@Column(name = "SIGLAS_ENVIADOS")
	private String siglasEnviados;

	/**
	 * Atributo que indica el id Area
	 */
	@Column(name = "ID_AREA")
	private Integer idArea;

	/**
	 * Atributo que indica el tipo area
	 */
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;

	/**
	 * Atributo que indica la fecha en la cual el documento fue enviado a
	 * validar o firmar
	 */
	@Column(name = "FECHA_ENVIO")
	private Date fechaEnvio;

	/**
	 * Atributo que indica la fecha en la cual el documento regreso de ser
	 * validado o firmado
	 */
	@Column(name = "FECHA_REGRESO")
	private Date fechaRegreso;
	
	/**
	 * Atributo que indica que un documento tiene o no una notificación asociada
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;

	/**
	 * Atributo que indica que un documento ha sido rechazado para firmar
	 */
	@Column(name = "CON_RECHAZO")
	private Integer conRechazo;
	
	/**
	 * Indica si el documento ya fue leído o no
	 */
	@Column(name = "ESTATUS")
	private Character estatus;
	
	/**
	 * Atributo que permite guardar el nombre del usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	// ------------------------ Métodos ------------------------ //

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
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOBorradorDocumentosEntity other = (DTOBorradorDocumentosEntity) obj;
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
		return "DTOBorradorDocumentosEntity [idPersona=" + idPersona
				+ ", persona=" + persona + ", idDocumento=" + idDocumento
				+ ", documento=" + documento + ", anio=" + anio
				+ ", conModificaciones=" + conModificaciones
				+ ", conComentarios=" + conComentarios + ", conFirma="
				+ conFirma + ", conValidacion=" + conValidacion
				+ ", numEnviadoFirma=" + numEnviadoFirma
				+ ", numEnviadoValidacion=" + numEnviadoValidacion
				+ ", siglasEnviados=" + siglasEnviados + ", idArea=" + idArea
				+ ", tipoArea=" + tipoArea + ", fechaEnvio=" + fechaEnvio
				+ ", fechaRegreso=" + fechaRegreso + ", noLeido=" + noLeido
				+ ", conRechazo=" + conRechazo + ", estatus=" + estatus
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	// -------------- GETTERS & SETTERS para Join -------------------- //
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdPersona(idPersonaDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdOficialia(idOficialiaDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdArea(idAreaDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setTipoArea(tipoAreaDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		if (documento.getTipoDocumento() == null) {
			documento.setTipoDocumento(new DTOCTipoDocumentoEntity());
		}
		documento.setIdTipoDocumento(idTipoDocumento);
		documento.getTipoDocumento().setIdTipoDocumento(idTipoDocumento);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getAcronimo() == null) {
			documento.setAcronimo(new DTOAcronimoEntity());
		}
		documento.setIdAcronimo(idAcronimoDoc);
		documento.getAcronimo().setIdAcronimo(idAcronimoDoc);

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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		documento.setNoDocumento(numDocumento);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		documento.setNombreDocumento(nombreDocumento);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setEstatus(estatusDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setFirmado(firmadoDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setValidado(validadoDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setTipoCaptura(tipoCapturaDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setAsunto(asuntoDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setProcedenciaDocumento(procedenciaDocumento);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setEditado(editadoDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setFechaCreacion(fechaCreacionDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();

		}
		documento.setFechaRecepcionOf(fechaRecepcionOfDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setContieneAnexos(contieneAnexosDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdDocumentoRespuesta(idDocumentoRespuesta);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setUsuario(usuarioDoc);
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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setFechaHora(fechaHoraDoc);
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

		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}
		documento.getArea().setIdEntidad(idEntidadArea);

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

		if (documento == null) {
			documento = new DTODocumentoEntity();

		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}

		documento.getArea().setDescripcion(descripcionArea);

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
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		if (documento.getArea() == null) {
			documento.setArea(new DTOCAreaEntity());
		}

		documento.getArea().setSiglas(siglasArea);
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

		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdDocumento(idDocumento);
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

		if (documento == null) {
			documento = new DTODocumentoEntity();
		}

		documento.setAnio(anio);
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	
	/**
	 * @param idPersona
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         conModificaciones
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getConModificaciones() {
		return (conModificaciones == null) ? 0 : conModificaciones;
	}

	/**
	 * @param conModificaciones
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setConModificaciones(Integer conModificaciones) {
		this.conModificaciones = conModificaciones;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         conComentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getConComentarios() {
		return (conComentarios == null) ? 0 : conComentarios;
	}

	/**
	 * @param conComentarios
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setConComentarios(Integer conComentarios) {
		this.conComentarios = conComentarios;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable conFirma
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getConFirma() {
		return (conFirma == null) ? 0 : conFirma;
	}

	/**
	 * @param conFirma
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setConFirma(Integer conFirma) {
		this.conFirma = conFirma;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         conValidacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public Integer getConValidacion() {
		return (conValidacion == null) ? 0 : conValidacion;
	}

	/**
	 * @param conValidacion
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/08/2017
	 */
	public void setConValidacion(Integer conValidacion) {
		this.conValidacion = conValidacion;
	}

	/**
	 * @return the persona
	 */
	public DTOEstructuraAreasEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return the documento
	 */
	public DTODocumentoEntity getDocumento() {
		return documento;
	}

	/**
	 * @param documento
	 *            the documento to set
	 */
	public void setDocumento(DTODocumentoEntity documento) {
		this.documento = documento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         numEnviadoValidacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public Integer getNumEnviadoValidacion() {
		return numEnviadoValidacion;
	}

	/**
	 * @param numEnviadoValidacion
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public void setNumEnviadoValidacion(Integer numEnviadoValidacion) {
		this.numEnviadoValidacion = numEnviadoValidacion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         numEnviadoFirma
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public Integer getNumEnviadoFirma() {
		return numEnviadoFirma;
	}

	/**
	 * @param numEnviadoFirma
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void setNumEnviadoFirma(Integer numEnviadoFirma) {
		this.numEnviadoFirma = numEnviadoFirma;
	}

	
	public String getSiglasEnviados() {
		return (siglasEnviados == null) ? "" : this.siglasEnviados;
	}

	
	public void setSiglasEnviados(String siglasEnviados) {
		this.siglasEnviados = siglasEnviados;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.org.ine.servicios.dto.DTOBase#getFechaHora()
	 */
	@Override
	public Date getFechaHora() {
		return fechaHora;
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

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/11/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/11/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/11/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/11/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaEnvio
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio
	 *            : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaRegreso
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public Date getFechaRegreso() {
		return fechaRegreso;
	}

	/**
	 * @param fechaRegreso
	 *            : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setFechaRegreso(Date fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable noLeido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/12/2017
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/12/2017
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable conRechazo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/01/2018
	 */
	public Integer getConRechazo() {
		return conRechazo;
	}

	/**
	 * @param conRechazo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/01/2018
	 */
	public void setConRechazo(Integer conRechazo) {
		this.conRechazo = conRechazo;
	}

	/**
	 * @return valor de tipo Character que esta contenido en la variable estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public Character getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo Character
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}

}
