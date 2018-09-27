package mx.ine.gestion.dto.db;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.org.ine.servicios.dto.DTOBase;


/**
 * @(#)DTODocumentosDatosOficialia.java 08/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 *  Clase de mapeo para la tabla DOCUMENTOS_DATOS_OF.
 * 
 * @author Sergio Ley Garcia
 * @since 08/11/2017
 */
@Entity
@Table(name = "DOCUMENTOS_DATOS_OF", schema = "gestion4")
public class DTODocumentosDatosOficialiaEntity extends DTOBase {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long	serialVersionUID	= -3231943564868536071L;
	
	/**
	 * Atributo que guarda el ID del documento
	 */
	@Id
	@Column(name = "ID_DOCUMENTO")
	private Integer				idDocumento;
	/**
	 * Atributo que guarda el
	 */
	@Column(name = "PRIORIDAD")
	private Integer				prioridad;
	/**
	 * Atributo que guarda el
	 */
	@Column(name = "ID_OFICIALIA_RECIBE")
	private Integer				idOficialia;
	/**
	 * Atributo que guarda el
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_RECEPCION")
	private Date				fechaRecepcion;
	/**
	 * Atributo que guarda el
	 */
	@Column(name = "MODO_RECEPCION")
	private char				modoRecepcion;
	/**
	 * Atributo que guarda el
	 */
	@Column(name = "UBICACION_ARCHIVO_FISICO")
	private String				ubicacionArchivoFisico;
	/**
	 * Atributo que guarda el usuario que creo el documento
	 */
	@Column(name = "USUARIO")
	private String				usuario;
	/**
	 * Atributo que guarda la fecha de creacion del documento
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date				fechaHora;

	/**
	 * @return idDocumento
	 */
	public Integer getIdDocumento() {

		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            idDocumento del DTODocumentosDatosOficialia
	 */
	public void setIdDocumento(Integer idDocumento) {

		this.idDocumento = idDocumento;
	}

	/**
	 * @return prioridad
	 */
	public Integer getPrioridad() {

		return prioridad;
	}

	/**
	 * @param prioridad
	 *            prioridad del DTODocumentosDatosOficialia
	 */
	public void setPrioridad(Integer prioridad) {

		this.prioridad = prioridad;
	}

	/**
	 * @return idOficialia
	 */
	public Integer getIdOficialia() {

		return idOficialia;
	}

	/**
	 * @param idOficialia
	 *            idOficialia del DTODocumentosDatosOficialia
	 */
	public void setIdOficialia(Integer idOficialia) {

		this.idOficialia = idOficialia;
	}

	/**
	 * @return fechaRecepcion
	 */
	public Date getFechaRecepcion() {

		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion
	 *            fechaRecepcion del DTODocumentosDatosOficialia
	 */
	public void setFechaRecepcion(Date fechaRecepcion) {

		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * @return modoRecepcion
	 */
	public char getModoRecepcion() {

		return modoRecepcion;
	}

	/**
	 * @param modoRecepcion
	 *            modoRecepcion del DTODocumentosDatosOficialia
	 */
	public void setModoRecepcion(char modoRecepcion) {

		this.modoRecepcion = modoRecepcion;
	}

	/**
	 * @return ubicacionArchivoFisico
	 */
	public String getUbicacionArchivoFisico() {

		return ubicacionArchivoFisico;
	}

	/**
	 * @param ubicacionArchivoFisico
	 *            ubicacionArchivoFisico del DTODocumentosDatosOficialia
	 */
	public void setUbicacionArchivoFisico(String ubicacionArchivoFisico) {

		this.ubicacionArchivoFisico = ubicacionArchivoFisico;
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
	 *            usuario del DTODocumentosDatosOficialia
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
	 *            fechaHora del DTODocumentosDatosOficialia
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}
}
