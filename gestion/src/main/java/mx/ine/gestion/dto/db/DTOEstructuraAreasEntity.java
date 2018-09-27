/**
 * @(#)DTOEstructuraAreasEntity.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.text.DateFormat;
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
import javax.persistence.Transient;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.org.ine.servicios.dto.DTOBase;

import org.hibernate.annotations.Formula;

/**
 * DTO para la tabla de ESTRUCTURA_AREAS para el schema gestion4
 *
 * @author Pável Alexei Martínez Regalado
 * @since 30/08/2017
 */
@Entity
@Table(name = "ESTRUCTURA_AREAS", schema = "gestion4")
public class DTOEstructuraAreasEntity extends DTOBase {

	/**
	 * Atributo estático que contiene el número de serialización de la clase (se
	 * genera automaticamente con el ID).
	 */
	private static final long serialVersionUID = 6551134742248692816L;

	/**
	 * Atributo que guarda el ID de la persona
	 */
	@Id
	@Column(name = "ID_PERSONA")
	@SequenceGenerator(name = "S_ID_PERSONA", sequenceName = "S_ID_PERSONA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_ID_PERSONA")
	private Integer idPersona;

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
	private DTOCAreaEntity area;

	/**
	 * Atributo que guarda el nombre de la persona
	 */
	@Column(name = "NOMBRE")
	private String nombre;

	/**
	 * Atributo que guarda los apellidos de la persona
	 * 
	 */
	@Column(name = "APELLIDOS")
	private String apellidos;

	/**
	 * Atributo que guarda el puesto de la persona
	 */
	@Column(name = "PUESTO")
	private String puesto;

	/**
	 * Atributo que guarda el tratamiento de la persona
	 */
	@Column(name = "TRATAMIENTO")
	private String tratamiento;

	/**
	 * Atributo que guarda la extensión de teléfono de la persona
	 */
	@Column(name = "EXTENSION_TEL")
	private String extensionTel;

	/**
	 * Atributo que guarda el tipo de área al que pertenece la estructura
	 * 
	 */
	@Column(name = "CUENTA_LDAP")
	private String cuentaLDAP;

	/**
	 * Atributo que guarda el tipo de área al que pertenece la estructura
	 * 
	 */
	@Column(name = "NOMBRE_NIVEL_AREA")
	private String nombreNivelArea;

	/**
	 * Atributo que guarda el género de la persona en la estructura
	 * 
	 */
	@Column(name = "GENERO")
	private String genero;

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
	 * Estatus
	 */
	@Column(name = "ESTATUS")
	private String estatus;
	
	/**
	 * Bandera que permite visualizar en la bandeja la versión del titular
	 */
	@Column(name = "VER_VERSION_T")
	private Integer verVersionT;
	
	
	
	// ------------------------ ATRIBUTOS TRANSIENT ------------------------ //

	/*
	 * Atributo que guarda el nombre completo
	 */
	@Formula("tratamiento || nombre || ' ' || apellidos")
	private String nombreCompleto;

	/*
	 * Atributo que guarda el nombre del área
	 */
	@Transient
	private String nombreArea;
	
	/*
	 * Atributo que guarda el id_corresponsal de la tabla de Corresponsales
	 */
	@Transient
	private Integer idCorresponsal;
	
	/*
	 * Atributo que guarda la fecha_inicio de la tabla de Corresponsales
	 */
	@Transient
	private Date fechaInicio;
	
	/*
	 * Atributo que guarda la fecha_fin de la tabla de Corresponsales
	 */
	@Transient
	private Date fechaFin;
	/*
	 * Atributo auxiliar que indica si la persona debe mostrarse primero el area
	 * y luego el nombre o el nombre y luego el area.
	 */
	@Transient
	private Boolean	porArea;

	/**
	 * Indica si el perdiodo por el que esta el corresponsable ya vencio, 
	 * se usa en el módulo de captura de corresponsable
	 */
	@Transient
	private Boolean periodoVencido;

	// ------------------------ CONSTRUCTORES ------------------------ //
	/**
	 * Constructor Vacion 
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public DTOEstructuraAreasEntity() {

	}
	
	/**
	 * Constructor para crear un nuevo objeto a partir de un
	 * DTOEstructuraAreasEntity
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public DTOEstructuraAreasEntity(DTOEstructuraAreasEntity persona){
		if(persona != null){
			if(persona.idPersona != null){
				this.idPersona = persona.idPersona;
			}
			
			if(persona.idArea != null){
				this.idArea = persona.idArea;
			}
			
			if(persona.tipoArea != null){
				this.tipoArea = persona.tipoArea;
			}
			
			if(persona.area != null){
				this.area = persona.area;
			}
			
			if(persona.nombre != null){
				this.nombre = persona.nombre;
			}
			
			if(persona.apellidos != null){
				this.apellidos = persona.apellidos;
			}
			
			if(persona.puesto != null){
				this.puesto = persona.puesto;
			}
			
			if(persona.tratamiento != null){
				this.tratamiento = persona.tratamiento;
			}
			
			if(persona.extensionTel != null){
				this.extensionTel = persona.extensionTel;
			}
			
			if(persona.cuentaLDAP != null){
				this.cuentaLDAP = persona.cuentaLDAP;
			}
			
			if(persona.nombreNivelArea != null){
				this.nombreNivelArea = persona.nombreNivelArea;
			}
			
			if(persona.usuario != null){
				this.usuario = persona.usuario;
			}
			
			if(persona.fechaHora!= null){
				this.fechaHora = persona.fechaHora;
			}
			
			if(persona.estatus != null){
				this.estatus = persona.estatus;
			}
			
			if(persona.verVersionT != null){
				this.verVersionT = persona.verVersionT;
			}
			
			if(persona.nombreCompleto != null){
				this.nombreCompleto = persona.nombreCompleto;
			}
			
			if(persona.nombreArea != null){
				this.nombreArea = persona.nombreArea;
			}
			
			if(persona.idCorresponsal != null){
				this.idCorresponsal = persona.idCorresponsal;
			}
			
			if(persona.fechaInicio != null){
				this.fechaInicio = persona.fechaFin;
			}
			
			if(persona.porArea != null){
				this.porArea = persona.porArea;
			}
			
			if(persona.periodoVencido != null){
				this.periodoVencido = persona.periodoVencido;
			}
		}
	}
	
	public DTOEstructuraAreasEntity(Integer id, String nombre, String apellidos, String puesto, Integer idArea, String nombreNivelArea) {

		this.idPersona = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.puesto = puesto;
		this.idArea = idArea;
		this.nombreCompleto = nombre + " " + apellidos;
		this.nombreNivelArea = nombreNivelArea;
	}

	public DTOEstructuraAreasEntity(String nombre, String apellidos, String nombreCompleto, String puesto, String nombreArea, String cuentaLDAP, String nombreNivelArea) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nombreCompleto = nombre + " " + apellidos;
		this.puesto = puesto;
		this.nombreArea = nombreArea;
		this.cuentaLDAP = cuentaLDAP;
		this.nombreNivelArea = nombreNivelArea;
	}
	
	// ------------------------ MÉTODOS ------------------------ //
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DTOEstructuraAreasEntity other = (DTOEstructuraAreasEntity) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "ID_PERSONA=" + this.idPersona + " ID_AREA=" + this.getIdArea()
				+ " TIPO_AREA=" + this.getTipoArea() + " NOMBRE=" + this.nombre
				+ " APELLIDOS=" + this.apellidos + " PUESTO=" + this.puesto
				+ " NOM_AREA=" + this.idArea + " CUENTA_LDAP="
				+ this.cuentaLDAP + " NOMBRE_NIVEL=" + this.nombreNivelArea;
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * Método que define la descripción del Área de la persona. Este atributo se
	 * encuentra dentro del atributo "area"
	 * 
	 * @param descripcion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void setDescripcionArea(String descripcionArea) {

		if (area == null) {
			area = new DTOCAreaEntity();
		}

		area.setDescripcion(descripcionArea);
	}

	/**
	 * Método que define las siglas del Área de la persona. Este atributo se
	 * encuentra dentro del atributo "area"
	 * 
	 * @param siglasArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void setSiglasArea(String siglasArea) {

		if (area == null) {
			area = new DTOCAreaEntity();
		}

		area.setSiglas(siglasArea);
	}

	/**
	 * Método que define el idEntidadArea del Área de la persona. Este atributo
	 * se encuentra dentro del atributo "area"
	 * 
	 * @param idEntidadArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void setIdEntidadArea(int idEntidadArea) {

		if (area == null) {
			area = new DTOCAreaEntity();
		}

		area.setIdEntidad(idEntidadArea);
	}

	/**
	 * @return valor de tipo Integer que está contenido en el atributo idPersona
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public Integer getIdPersona() {

		return idPersona;
	}

	/**
	 * @param : valor que se ingresa al atributo idPersona de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setIdPersona(Integer idPersona) {

		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que está contenido en el atributo idArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public Integer getIdArea() {

		return idArea;
	}

	/**
	 * @param : valor que se ingresa al atributo idArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setIdArea(Integer idArea) {

		this.idArea = idArea;
	}

	/**
	 * @return the area
	 */
	public DTOCAreaEntity getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(DTOCAreaEntity area) {
		this.area = area;
	}

	/**
	 * @return valor de tipo Integer que está contenido en el atributo tipoArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public Integer getTipoArea() {

		return tipoArea;
	}

	/**
	 * @param : valor que se ingresa al atributo tipoArea de tipo Integer
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setTipoArea(Integer tipoArea) {

		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo nombre
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getNombre() {

		return nombre;
	}

	/**
	 * @param : valor que se ingresa al atributo nombre de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo apellidos
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getApellidos() {

		return apellidos;
	}

	/**
	 * @param : valor que se ingresa al atributo apellidos de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setApellidos(String apellidos) {

		this.apellidos = apellidos;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo puesto
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getPuesto() {

		return puesto;
	}

	/**
	 * @param : valor que se ingresa al atributo puesto de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setPuesto(String puesto) {

		this.puesto = puesto;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo cuentaLDAP
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getCuentaLDAP() {

		return cuentaLDAP;
	}

	/**
	 * @param : valor que se ingresa al atributo cuentaLDAP de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setCuentaLDAP(String cuentaLDAP) {

		this.cuentaLDAP = cuentaLDAP;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo
	 *         nombreNivelArea
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getNombreNivelArea() {

		return nombreNivelArea;
	}

	/**
	 * @param : valor que se ingresa al atributo nombreNivelArea de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setNombreNivelArea(String nombreNivelArea) {

		this.nombreNivelArea = nombreNivelArea;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo genero
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/09/2017
	 */
	public String getGenero() {

		return genero;
	}

	/**
	 * @param : valor que se ingresa al atributo genero de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/09/2017
	 */
	public void setGenero(String genero) {

		this.genero = genero;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo
	 *         nombreCompleto
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public String getNombreCompleto() {

		return nombreCompleto;
	}

	/**
	 * @param : valor que se ingresa al atributo nombreCompleto de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	public void setNombreCompleto(String nombreCompleto) {

		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo usuario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/09/2017
	 */
	public String getNombreArea() {

		return nombreArea;
	}

	/**
	 * @param : valor que se ingresa al atributo nombreCompleto de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/9/2017
	 */
	public void setNombreArea(String nombreArea) {

		this.nombreArea = nombreArea;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo usuario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	@Override
	public String getUsuario() {

		return usuario;
	}

	/**
	 * @param : valor que se ingresa al atributo usuario de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	@Override
	public void setUsuario(String usuario) {

		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Date que está contenido en el atributo fechaHora
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	@Override
	public Date getFechaHora() {

		return fechaHora;
	}

	/**
	 * @param : valor que se ingresa al atributo fechaHora de tipo Date
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 31/08/2017
	 */
	@Override
	public void setFechaHora(Date fechaHora) {

		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         tratamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public String getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         extensionTel
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public String getExtensionTel() {
		return extensionTel;
	}

	/**
	 * @param extensionTel
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */ 
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idCorresponsal
	 *
	 * @author Luis Urrutia
	 * @since 01/12/2017
	 */ 
	public Integer getIdCorresponsal() {
		return idCorresponsal;
	}

	/**
	 * @param idCorresponsal : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Luis Urrutia
	 * @since 01/12/2017
	 */ 
	public void setIdCorresponsal(Integer idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}
	
	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaInicio
	 *
	 * @author Luis Urrutia
	 * @since 04/12/2017
	 */ 
	public String getFechaInicio() {
		
		String fechaInicioS = "";
		if (fechaInicio != null) {
			fechaInicioS = DateFormat.getDateInstance().format(fechaInicio);
		}
		return fechaInicioS;
	}

	/**
	 * @param fechaInicio : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 04/12/2017
	 */ 
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaFin
	 *
	 * @author Luis Urrutia
	 * @since 04/12/2017
	 */ 
	public String getFechaFin() {

		String fechaFinS = "";
		if (fechaFin != null) {
			fechaFinS = DateFormat.getDateInstance().format(fechaFin);
		}
		return fechaFinS;
	}

	/**
	 * @param fechaFin : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Luis Urrutia
	 * @since 04/12/2017
	 */ 
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable porArea
	 *
	 * @author Sergio Ley Garcia
	 * @since 13/12/2017
	 */
	public Boolean getPorArea() {

		return porArea;
	}

	/**
	 * @param porArea
	 *            porArea del DTOEstructuraAreasEntity
	 *
	 * @author Sergio Ley Garcia
	 * @since 13/12/2017
	 */
	public void setPorArea(Boolean porArea) {

		this.porArea = porArea;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable periodoVencido
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/01/2018
	 */
	public Boolean getPeriodoVencido() {
		return periodoVencido;
	}

	/**
	 * @param periodoVencido : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/01/2018
	 */
	public void setPeriodoVencido(Boolean periodoVencido) {
		this.periodoVencido = periodoVencido;
	}

	/**
	 * @return variable de tipo Integer contenida en verVersionT
	 * 
	 * @since 16/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public Integer getVerVersionT() {
		return verVersionT;
	}

	/**
	 * @param verVersionT: variable de tipo Integer contenida en verVersionT
	 *
	 * @since 16/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setVerVersionT(Integer verVersionT) {
		this.verVersionT = verVersionT;
	}
	
	

}
