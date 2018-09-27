/**
 * @(#)DTOComentariosNoLeidos.java 17/04/2018
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

import mx.org.ine.servicios.dto.DTOBase;

/**
 * @author Homero Fidel Villanueva
 * @since 17/04/2018
 *
 */
@Entity
@Table(name = "COMENTARIOS_NO_LEIDOS", schema = "gestion4")
@IdClass(value = DTOComentariosNoLeidosID.class)
public class DTOComentariosNoLeidos extends DTOBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1211373470503935547L;
	
	/**
	 * Atributo identificador que guarda el ID_COMENTARIO
	 */
	@Id
	@Column(name = "ID_COMENTARIO")
	private Integer idComentario;
	
	/**
	 * Atributo identificador que guarda el ID_DOCUMENTO
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_DOCUMENTO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_COMENTARIO", insertable = false, updatable = false)})
	private DTOComentariosDocumentoEntity dtoComentario;
	/**
	 * Atributo identificador que guarda el ID_PERSONA
	 */
	@Id
	@Column(name = "ID_PERSONA")
	private Integer idPersona;
	
	/**
	 * Atributo identificador que guarda el ID_AREA
	 */
	@Id
	@Column(name = "ID_AREA")
	private Integer idArea;
	
	/**
	 * Atributo identificador que guarda el TIPO_AREA
	 */
	@Id
	@Column(name = "TIPO_AREA")
	private Integer tipoArea;
	
	/**
	 * Atributo identificador que guarda el NO_LEIDO
	 */
	@Column(name = "NO_LEIDO")
	private Integer noLeido;
	
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
	
	/**
	 * Estructura donde se guarda el nombre de la persona que realizó el comentario
	 */
	@Transient
	private DTOEstructuraAreasEntity personaComentoEstructura;

	// ------------------------ Métodos ------------------------ //
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result
				+ ((idComentario == null) ? 0 : idComentario.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOComentariosNoLeidos other = (DTOComentariosNoLeidos) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idComentario == null) {
			if (other.idComentario != null)
				return false;
		} else if (!idComentario.equals(other.idComentario))
			return false;
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
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOComentariosNoLeidos [idComentario=" + idComentario
				+ ", idDocumento=" + idDocumento + ", dtoComentario="
				+ dtoComentario + ", idPersona=" + idPersona + ", idArea="
				+ idArea + ", tipoArea=" + tipoArea + ", noLeido=" + noLeido
				+ ", usuario=" + usuario + ", fechaHora=" + fechaHora + "]";
	}

	// -------------- GETTERS & SETTERS para Join -------------------- //
	/**
	 * Método ocupado para el Join
	 * 
	 * @param anioCom
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setAnioCom(Integer anioCom) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setAnio(anioCom);
	}

	/**
	 * Método ocupado para el Join
	 * 
	 * @param comentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setComentarios(String comentarios) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setComentarios(comentarios);
	}

	/**
	 * Método ocupado para el Join
	 * 
	 * @param estatusCom
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setEstatusCom(Integer estatusCom) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setEstatus(estatusCom);
	}

	/**
	 * Método ocupado para el Join
	 * 
	 * @param usuarioComento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setUsuarioComento(String usuarioComento) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setUsuarioComento(usuarioComento);
	}
	
	/**
	 * Método ocupado para el Join
	 * 
	 * @param tipoComentario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setTipoComentario(Character tipoComentario) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setTipoComentario(tipoComentario);
	}

	/**
	 * Método ocupado para el Join
	 *  
	 * @param usuarioCom
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setUsuarioCom(String usuarioCom) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setUsuario(usuarioCom);
	}

	/**
	 * Método ocupado para el Join
	 * 
	 * @param fechaHoraCom
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setFechaHoraCom(Date fechaHoraCom) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setFechaHora(fechaHoraCom);
	}
	
	/**
	 * Método ocupado para el Join
	 * 
	 * @param idPersonaEstr
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setIdPersonaEstr(Integer idPersonaEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setIdPersona(idPersonaEstr);
	}
	
	public void setTipoAreaEstr(Integer tipoAreaEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setTipoArea(tipoAreaEstr);
	}
	
	public void setNombreEstr(String nombreEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setNombre(nombreEstr);
	}
	
	public void setApellidosEstr(String apellidosEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setApellidos(apellidosEstr);
	}
	
	public void setNombreCompletoEstr(String nombreCompletoEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setNombreCompleto(nombreCompletoEstr);
	}
	
	
	
	public void setCuentraLDAPEstr(String cuentraLDAPEstr) {
		if (dtoComentario == null) {
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		if(dtoComentario.getPersonaComento() == null){
			dtoComentario.setPersonaComento(new DTOEstructuraAreasEntity());
		}
		dtoComentario.getPersonaComento().setCuentaLDAP(cuentraLDAPEstr);
	}
	// ------------------------ GETTERS & SETTERS ------------------------ //
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
	 * @return valor de tipo Integer que esta contenido en la variable idComentario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setIdComentario(Integer idComentario) {
		if(dtoComentario == null){
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setIdComentario(idComentario);
		this.idComentario = idComentario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setIdDocumento(Integer idDocumento) {
		if(dtoComentario == null){
			dtoComentario = new DTOComentariosDocumentoEntity();
		}
		dtoComentario.setIdDocumento(idDocumento);
		this.idDocumento = idDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable noLeido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public Integer getNoLeido() {
		return noLeido;
	}

	/**
	 * @param noLeido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/04/2018
	 */
	public void setNoLeido(Integer noLeido) {
		this.noLeido = noLeido;
	}

	/**
	 * @return valor de tipo DTOComentariosDocumentoEntity que esta contenido en la variable dtoComentario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public DTOComentariosDocumentoEntity getDtoComentario() {
		return dtoComentario;
	}

	/**
	 * @param dtoComentario : valor que se ingresa a la variable de tipo DTOComentariosDocumentoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public void setDtoComentario(DTOComentariosDocumentoEntity dtoComentario) {
		this.dtoComentario = dtoComentario;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaComentoEstructura
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public DTOEstructuraAreasEntity getPersonaComentoEstructura() {
		return personaComentoEstructura;
	}

	/**
	 * @param personaComentoEstructura : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 23/04/2018
	 */
	public void setPersonaComentoEstructura(
			DTOEstructuraAreasEntity personaComentoEstructura) {
		this.personaComentoEstructura = personaComentoEstructura;
	}
	
}
