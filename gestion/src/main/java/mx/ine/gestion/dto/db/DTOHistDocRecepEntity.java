/**
 * DTOHistDocRecep.java 17/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.ine.gestion.dto.db.catalogos.DTOCEstatusHistDocTurno;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 17/01/2018
 */

@Entity
@Table(name = "HIST_DOC_RECEP", schema = "gestion4")
public class DTOHistDocRecepEntity extends DTOBase {

	/**
	 * Atributo para la serialización de ésta clase.
	 */
	private static final long serialVersionUID = -2289314706674875821L;

	/**
	 * Atributo con el identificador de área
	 */
	 @Column(name = "ID_AREA")
	 private Integer idArea;
	 
	 /**
	  * Atributo con el tipo de área: Órganos centrales, Órganos desconcentrados
	  */
	 @Column(name = "TIPO_AREA")
	 private Integer tipoArea;
	 
	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "HIST_DOC_RECEP", identificador único del documento
	 */
	 @Column(name = "ID_DOCUMENTO")
	 private Integer idDocumento;

	 /**
	  * Atributo con el año de inserción de registro
	  */
	 @Column(name = "ANIO")
	 private Integer anio;
	 
	/**
	 * Atributo que es uno de los identificadores de la tabla
	 * "HIST_DOC_CREACION",
	 */
	 @Id
	 @Column(name = "ID_HISTORICO_RECEP")
	 @SequenceGenerator(name = "S_ID_HISTORICO_RECEP", sequenceName = "S_ID_HISTORICO_RECEP")
	 @GeneratedValue(strategy = GenerationType.AUTO, generator = "S_ID_HISTORICO_RECEP")
	 private Integer idHistoricoRecep;
	 
	/**
	 * Atributo para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_HISTORICO_RECEP", insertable = false, updatable = false)
	private DTOHistDocTurnoEntity dtoHistoricoTurno;

	 /**
	  * Atributo con el identificador único del padre quién realiza el turno
	  */
	 @Column(name = "ID_HISTORICO_PADRE")
	 private Integer idHistoricoPadre;
	 
	 /**
	  * Atributo con el identificador de la persona en histórico
	  */
	 @Column(name = "ID_PERSONA_HIST")
	 private Integer idPersonaHist;
	 
	/**
	 * Atributo para cargar la información de la tabla de ESTRUCTURA_AREAS
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_HIST", insertable = false, updatable = false)
	private DTOEstructuraAreasEntity dtoEstructura;

	 /**
	  * Atributo con el identificador del estatus en recepción
	  */
	 @Column(name = "ID_ESTATUS_RECEP")
	 private Integer idEstatusRecep;

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
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idHistoricoRecep == null) ? 0 : idHistoricoRecep.hashCode());
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
		DTOHistDocRecepEntity other = (DTOHistDocRecepEntity) obj;
		if (idHistoricoRecep == null) {
			if (other.idHistoricoRecep != null)
				return false;
		} else if (!idHistoricoRecep.equals(other.idHistoricoRecep))
			return false;
		return true;
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DTOHistDocRecepEntity [idArea=" + idArea + ", tipoArea="
				+ tipoArea + ", idDocumento=" + idDocumento + ", anio=" + anio
				+ ", idHistoricoRecep=" + idHistoricoRecep
				+ ", dtoHistoricoTurno=" + dtoHistoricoTurno
				+ ", idHistoricoPadre=" + idHistoricoPadre + ", idPersonaHist="
				+ idPersonaHist + ", dtoEstructura=" + dtoEstructura
				+ ", idEstatusRecep=" + idEstatusRecep + ", usuario=" + usuario
				+ ", fechaHora=" + fechaHora + "]";
	}

	// --------------------- JOIN GETTERS & SETTERS ------------------------ //
	public void setNombre(String nombre){
		if(dtoEstructura == null){
			dtoEstructura = new DTOEstructuraAreasEntity();
		}
		dtoEstructura.setNombre(nombre);
	}
	
	public void setApellidos(String apellidos){
		if(dtoEstructura == null){
			dtoEstructura = new DTOEstructuraAreasEntity();
		}
		dtoEstructura.setApellidos(apellidos);
	}
	
	public void setCuentaLDAP(String cuentaLDAP){
		if(dtoEstructura == null){
			dtoEstructura = new DTOEstructuraAreasEntity();
		}
		dtoEstructura.setCuentaLDAP(cuentaLDAP);
	}
	
	public void setIdEstatusTurno(Integer idEstatusTurno){
		if(dtoHistoricoTurno == null){
			dtoHistoricoTurno = new DTOHistDocTurnoEntity();
		}
		if(dtoHistoricoTurno.getDtoCEstatus() == null){
			dtoHistoricoTurno.setDtoCEstatus(new DTOCEstatusHistDocTurno());
		}
		dtoHistoricoTurno.setIdEstatusTurno(idEstatusTurno);
		dtoHistoricoTurno.getDtoCEstatus().setIdEstatusTurno(idEstatusTurno);
		
	}
	
	public void setDescripcion(String descripcion){
		if(dtoHistoricoTurno == null){
			dtoHistoricoTurno = new DTOHistDocTurnoEntity();
		}
		if(dtoHistoricoTurno.getDtoCEstatus() == null){
			dtoHistoricoTurno.setDtoCEstatus(new DTOCEstatusHistDocTurno());
		}
		
		dtoHistoricoTurno.getDtoCEstatus().setDescripcion(descripcion);
	}
	// ------------------------ GETTERS & SETTERS ------------------------ //
	
	/**
	 * @return variable de tipo Integer contenida en idArea
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea: variable de tipo Integer contenida en idArea
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return variable de tipo Integer contenida en tipoArea
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea: variable de tipo Integer contenida en tipoArea
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return variable de tipo Integer contenida en idDocumento
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento: variable de tipo Integer contenida en idDocumento
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return variable de tipo Integer contenida en anio
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio: variable de tipo Integer contenida en anio
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoRecep
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep: variable de tipo Integer contenida en idHistoricoRecep
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
	}

	/**
	 * @return variable de tipo Integer contenida en idHistoricoPadre
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdHistoricoPadre() {
		return idHistoricoPadre;
	}

	/**
	 * @param idHistoricoPadre: variable de tipo Integer contenida en idHistoricoPadre
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdHistoricoPadre(Integer idHistoricoPadre) {
		this.idHistoricoPadre = idHistoricoPadre;
	}

	/**
	 * @return variable de tipo Integer contenida en idPersonaHist
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdPersonaHist() {
		return idPersonaHist;
	}

	/**
	 * @param idPersonaHist: variable de tipo Integer contenida en idPersonaHist
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdPersonaHist(Integer idPersonaHist) {
		this.idPersonaHist = idPersonaHist;
	}

	/**
	 * @return variable de tipo Integer contenida en idEstatusRecep
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getIdEstatusRecep() {
		return idEstatusRecep;
	}

	/**
	 * @param idEstatusRecep: variable de tipo Integer contenida en idEstatusRecep
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setIdEstatusRecep(Integer idEstatusRecep) {
		this.idEstatusRecep = idEstatusRecep;
	}

	/**
	 * @return variable de tipo String contenida en usuario
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario: variable de tipo String contenida en usuario
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return variable de tipo Date contenida en fechaHora
	 * 
	 * @since 17/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora: variable de tipo Date contenida en fechaHora
	 *
	 * @since 17/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable dtoEstructura
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/02/2018
	 */
	public DTOEstructuraAreasEntity getDtoEstructura() {
		return dtoEstructura;
	}

	/**
	 * @param dtoEstructura : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/02/2018
	 */
	public void setDtoEstructura(DTOEstructuraAreasEntity dtoEstructura) {
		this.dtoEstructura = dtoEstructura;
	}

	/**
	 * @return valor de tipo DTOHistDocTurnoEntity que esta contenido en la variable dtoHistoricoTurno
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public DTOHistDocTurnoEntity getDtoHistoricoTurno() {
		return dtoHistoricoTurno;
	}

	/**
	 * @param dtoHistoricoTurno : valor que se ingresa a la variable de tipo DTOHistDocTurnoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/02/2018
	 */
	public void setDtoHistoricoTurno(DTOHistDocTurnoEntity dtoHistoricoTurno) {
		this.dtoHistoricoTurno = dtoHistoricoTurno;
	}
	
}
