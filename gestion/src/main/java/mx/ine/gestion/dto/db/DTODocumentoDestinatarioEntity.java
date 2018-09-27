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
import mx.org.ine.servicios.dto.DTOBase;

/**
 * @(#)DTODocumentoDestinatarioEntity.java 12/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de MB para el módulo captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 12/09/2017
 */
@Entity
@Table(name = "DOCUMENTOS_DESTINATARIOS", schema = "gestion4")
@IdClass(DTODocumentoDestinatarioID.class)
public class DTODocumentoDestinatarioEntity extends DTOBase {

	private static final long			serialVersionUID	= 7643377012271255398L;
	/**
	 * Atributo que guarda el ID del documento, parte de la PK y FK para la
	 * tabla DOCUMENTO.
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer	idDocumento;
	/**
	 * Atributo que guarda el ID del destinatario, parte de la PK y FK para la
	 * tabla ESTRUCTURA_AREAS.
	 */
	@Id
	@Column(name = "ID_PERSONA_DESTINATARIA")
	private Integer	idPersonaDestinataria;

	/**
	 * Atributo que guarda el documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", insertable = false, updatable = false)
	private DTODocumentoEntity			documento;
	/**
	 * Atributo que guarda el destinatario.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "ID_PERSONA_DESTINATARIA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity	persona;
	/**
	 * Atributo que guarda el año del documento.
	 */
	@Column(name = "ANIO")
	private Integer	anio;
	
	/**
	 * Atributo que guarda el año del documento.
	 */
	@Column(name = "TIPO_DESTINATARIO")
	private Integer	tipoDestinatario;
	
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

	// -------------------- Métodos ------------------- //
	
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
				+ ((idPersonaDestinataria == null) ? 0 : idPersonaDestinataria.hashCode());
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
		DTODocumentoDestinatarioEntity other = (DTODocumentoDestinatarioEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPersonaDestinataria == null) {
			if (other.idPersonaDestinataria != null)
				return false;
		} else if (!idPersonaDestinataria.equals(other.idPersonaDestinataria))
			return false;
		return true;
	}
	
	
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTODocumentoDestinatarioEntity [idDocumento=" + idDocumento
				+ ", idPersonaDestinataria=" + idPersonaDestinataria
				+ ", documento=" + documento + ", persona=" + persona
				+ ", anio=" + anio + ", tipoDestinatario=" + tipoDestinatario
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	// -------------------- Métodos para Join ------------------- //
	/**
	 * Método utilizado para guardar el id de la persna en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setIdPersona(Integer idPersona){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setIdPersona(idPersona);
	}
	
	/**
	 * Método utilizado para guardar el id del área en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setIdArea(Integer idArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setIdArea(idArea);
	}
	
	/**
	 * Método utilizado para guardar el tipo del área en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setTipoArea(Integer tipoArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setIdArea(tipoArea);
	}
	
	/**
	 * Método utilizado para guardar el nombre en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param nombre
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setNombre(String nombre){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setNombre(nombre);
	}
	
	/**
	 * Método utilizado para guardar los apellidos en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param apellidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setApellidos(String apellidos){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setApellidos(apellidos);
	}
	
	/**
	 * Método utilizado para guardar el nombre completo en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param nombreCompleto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setNombreCompleto(String nombreCompleto){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setNombreCompleto(nombreCompleto);
	}
	
	/**
	 * Método utilizado para guardar el puesto en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param puesto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setPuesto(String puesto){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setPuesto(puesto);
	}
	
	/**
	 * Método utilizado para guardar el tratamiento en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param tratamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setTratamiento(String tratamiento){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setTratamiento(tratamiento);
	}
	
	/**
	 * Método utilizado para guardar la extensión de telefono en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param extensionTel
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setExtensionTel(String extensionTel){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setExtensionTel(extensionTel);
	}
	
	/**
	 * Método utilizado para guardar el genero en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param genero
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setGenero(String genero){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setGenero(genero);
	}
	
	/**
	 * Método utilizado para guardar la cuenta LDAP en el join con la tabla
	 * "ESTRUCTURA_AREAS"
	 * 
	 * @param cuentaLDAP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setCuentaLDAP(String cuentaLDAP){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setCuentaLDAP(cuentaLDAP);
	}
	
	/**
	 * Método utilizado para guardar el nombre de nivel de área  en el join
	 * con la tabla "ESTRUCTURA_AREAS"
	 * 
	 * @param nombreNivelArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setNombreNivelArea(String nombreNivelArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setNombreNivelArea(nombreNivelArea);
	}
	
	/**
	 * Método utilizado para guardar la versionT en el join
	 * con la tabla "ESTRUCTURA_AREAS"
	 * 
	 * @param verVersionT
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setVerVersionT(Integer verVersionT){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setVerVersionT(verVersionT);
	}
	
	/**
	 * Método utilizado para guardar el estatus en el join
	 * con la tabla "ESTRUCTURA_AREAS"
	 * 
	 * @param estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setEstatus(String estatus){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setEstatus(estatus);
	}
	
	/**
	 * Método utilizado para guardar el usuario en el join
	 * con la tabla "ESTRUCTURA_AREAS"
	 * @param usuarioEstr
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setUsuarioEstr(String usuarioEstr){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setUsuario(usuarioEstr);
	}
	
	/**
	 * Método utilizado para guardar la fecha y horaen el join
	 * con la tabla "ESTRUCTURA_AREAS"
	 * 
	 * @param fechaHoraEstr
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setFechaHoraEstr(Date fechaHoraEstr){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		persona.setFechaHora(fechaHoraEstr);
	}
	
	/**
	 * Método utilizado para la descripción en el join
	 * con la tabla "C_AREAS"
	 * 
	 * @param descripcionArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setDescripcionArea(String descripcionArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		if(persona.getArea() == null){
			persona.setArea(new DTOCAreaEntity());
		}
		persona.getArea().setDescripcion(descripcionArea);
	}
	
	/**
	 * Método utilizado para la descripción en el join
	 * con la tabla "C_AREAS"
	 * 
	 * @param siglasArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setSiglasArea(String siglasArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		if(persona.getArea() == null){
			persona.setArea(new DTOCAreaEntity());
		}
		persona.getArea().setSiglas(siglasArea);
	}
	
	/**
	 * Método utilizado para la descripción en el join
	 * con la tabla "C_AREAS"
	 * 
	 * @param idEntidadArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setIdEntidadArea(Integer idEntidadArea){
		if(persona == null){
			persona = new DTOEstructuraAreasEntity();
		}
		if(persona.getArea() == null){
			persona.setArea(new DTOCAreaEntity());
		}
		persona.getArea().setIdEntidad(idEntidadArea);
	}
	
	// ------------------------ Getters and Setters ------------------------ //
	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentoDestinatarioEntity
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
		if (documento == null) {
			documento = new DTODocumentoEntity();
		}
		documento.setIdDocumento(idDocumento);
	}

	/**
	 * @return persona
	 */
	public DTOEstructuraAreasEntity getPersona() {

		return persona;
	}

	/**
	 * @param persona
	 *            persona del DTODocumentoDestinatarioEntity
	 */
	public void setPersona(DTOEstructuraAreasEntity persona) {

		this.persona = persona;
	}

	/**
	 * @return anio
	 */
	public Integer getAnio() {

		return anio;
	}

	/**
	 * @param anio
	 *            anio del DTODocumentoDestinatarioEntity
	 */
	public void setAnio(Integer anio) {

		this.anio = anio;
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
	 *            usuario del DTODocumentoDestinatarioEntity
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
	 * @param fechaHora
	 *            fechaHora del DTODocumentoDestinatarioEntity
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}

	/**
	 * @return documento
	 */
	public DTODocumentoEntity getDocumento() {

		return documento;
	}

	/**
	 * @param documento
	 *            documento del DTODocumentoDestinatarioEntity
	 */
	public void setDocumento(DTODocumentoEntity documento) {

		this.documento = documento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoDestinatario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/03/2018
	 */
	public Integer getTipoDestinatario() {
		return tipoDestinatario;
	}

	/**
	 * @param tipoDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/03/2018
	 */
	public void setTipoDestinatario(Integer tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaDestinataria
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public Integer getIdPersonaDestinataria() {
		return idPersonaDestinataria;
	}

	/**
	 * @param idPersonaDestinataria : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setIdPersonaDestinataria(Integer idPersonaDestinataria) {
		this.idPersonaDestinataria = idPersonaDestinataria;
	}

}
