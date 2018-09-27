/**
 * DTOHistDocTurnoEntity.java 18/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 */

@Entity
@Table(name = "TURN_INSTRUCCION", schema = "gestion4")
@IdClass(value = DTOTurnInstruccionID.class)
public class DTOTurnInstruccionEntity extends DTOBase {
	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = 3491576747394853944L;	

	/**
	 * Atributo con el identificador de área
	 */
	 @Id
	 @Column(name = "ID_DOCUMENTO")
	 private Integer idDocumento;

	 /**
	  * Atributo con el tipo de área: Órganos centrales, Órganos desconcentrados
	  */
	 @Id
	 @Column(name = "ID_HISTORICO_RECEP")
	 private Integer idHistoricoRecep;
	 
	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "HIST_DOC_RECEP", identificador único del documento
	 */
	 @Id
	 @Column(name = "ID_PERSONA")
	 private Integer idPersona;

	 /**
	  * Atributo con el id de la instrucción
	  */
	 @Id
	 @Column(name = "ID_INSTRUCCION")
	 private Integer idInstruccion;
	 
	 @Column(name = "ID_PERSONA_TURNADO")
	 private Integer idPersonaTurnado;
	 
	/**
	 * Usuario
	 */
	@Column(name = "USUARIO")
	private String usuario;

	/**
	 * Atributo que permite guardar la fecha
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;


	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime
				* result
				+ ((idHistoricoRecep == null) ? 0 : idHistoricoRecep.hashCode());
		result = prime * result
				+ ((idInstruccion == null) ? 0 : idInstruccion.hashCode());
		result = prime * result
				+ ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DTOTurnInstruccionEntity other = (DTOTurnInstruccionEntity) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idHistoricoRecep == null) {
			if (other.idHistoricoRecep != null)
				return false;
		} else if (!idHistoricoRecep.equals(other.idHistoricoRecep))
			return false;
		if (idInstruccion == null) {
			if (other.idInstruccion != null)
				return false;
		} else if (!idInstruccion.equals(other.idInstruccion))
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
		return "DTOTurnInstruccionEntity [idDocumento=" + idDocumento
				+ ", idHistoricoRecep=" + idHistoricoRecep + ", idPersona="
				+ idPersona + ", idInstruccion=" + idInstruccion
				+ ", idPersonaTurnado=" + idPersonaTurnado + ", usuario="
				+ usuario + ", fechaHora=" + fechaHora + "]";
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return variable de tipo Integer contenida en idDocumento
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento: variable de tipo Integer contenida en idDocumento
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoRecep
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep: variable de tipo Integer contenida en idHistoricoRecep
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
	}

	/**
	 * @return variable de tipo Integer contenida en idPersonaInst
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersonaInst: variable de tipo Integer contenida en idPersonaInst
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdPersona(Integer idPersonaInst) {
		this.idPersona = idPersonaInst;
	}

	/**
	 * @return variable de tipo Integer contenida en idInstruccion
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdInstruccion() {
		return idInstruccion;
	}

	/**
	 * @param idInstruccion: variable de tipo Integer contenida en idInstruccion
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdInstruccion(Integer idInstruccion) {
		this.idInstruccion = idInstruccion;
	}

	/**
	 * @return variable de tipo String contenida en usuario
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario: variable de tipo String contenida en usuario
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return variable de tipo Date contenida en fechaHora
	 * 
	 * @since 18/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora: variable de tipo Date contenida en fechaHora
	 *
	 * @since 18/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaTurnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public Integer getIdPersonaTurnado() {
		return idPersonaTurnado;
	}

	/**
	 * @param idPersonaTurnado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public void setIdPersonaTurnado(Integer idPersonaTurnado) {
		this.idPersonaTurnado = idPersonaTurnado;
	}
	
}
