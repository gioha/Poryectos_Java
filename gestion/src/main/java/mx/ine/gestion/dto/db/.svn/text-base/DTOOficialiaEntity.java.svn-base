/**
 * @(#)DTOOficialiaEntity.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import mx.org.ine.servicios.dto.DTOBase;

/**
 * Clase relacionada a la tabla de OFICIALIAS en el esquema de GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
@Entity
@Table(name="OFICIALIAS", schema="GESTION4")
public class DTOOficialiaEntity extends DTOBase {

	/**
	 * Elemento necesario para la serialización de los objetos generados de esta clase.
	 */
	private static final long serialVersionUID = -8553038503809605726L;

	/* ------------------------------------- Atributos/Campos ------------------------------------ */

	/**
	 * Llave primaria de la tabla, es el identificador de la persona registrada como oficialia de partes.
	 */
	@Id
	@Column(name="ID_OFICIALIA")
	@SequenceGenerator(name = "S_ID_OFICIALIA", sequenceName = "S_ID_OFICIALIA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_ID_OFICIALIA")
	private Integer idOficialia;

	/**
	 * Nombre que se registra de la persona de oficialia de partes.
	 */
	@Column(name="NOMBRE")
	private String nombre;

	/**
	 * Apellidos que se registran de la persona de oficialia de partes.
	 * NOTA: no se registran por separado ya que se toma el dato de LDAP y no viene separado.
	 */
	@Column(name="APELLIDOS")
	private String apellidos;

	/**
	 * Cuenta de LDAP relacionada a la persona de oficialia de partes.
	 */
	@Column(name="CUENTA_LDAP")
	private String cuentaLDAP;

	/**
	 * Cuenta de LDAP relacionada a la persona de oficialia de partes.
	 */
	@Column(name="PUESTO")
	private String puesto;
	
	/**
	 * Atributo que guarda el género de la persona en la oficialía.
	 * 
	 */
	@Column(name = "GENERO")
	private String genero;
	
	/**
	 * Estatus
	 */
	@Column(name = "ESTATUS")
	private String estatus;
	
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
     * Cuenta del usuario que registra en la BD.
     */
	@Column(name="USUARIO")
    private String usuario;
	
	/**
     * Fecha y hora en que se genera el registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_HORA")
    private Date fechaHora;
    
    /**
	 * Atributo que guarda el nombre completo
	 */
	@Formula("tratamiento || nombre || ' ' || apellidos")
	private String nombreCompleto;
  
    /*	********************** Atributos transient ********************** */
	
	/*
	 * Atributo que guarda el nombre del área
	 */
	@Transient
	private String nombreArea;
    
    /* ------------------------------------- Sobreescritura ------------------------------------ */
    
    /* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOficialia == null) ? 0 : idOficialia.hashCode());
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
		DTOOficialiaEntity other = (DTOOficialiaEntity) obj;
		if (idOficialia == null) {
			if (other.idOficialia != null)
				return false;
		} else if (!idOficialia.equals(other.idOficialia))
			return false;
		return true;
	}
    
	@Override
	public String toString(){
		return "OFICIALIA-> " + " ID=" + this.puesto + " NOMBRE=" + this.nombre + " APELLIDOS="  +this.apellidos;
	}
	
    /* ------------------------------------- Métodos ------------------------------------ */
    
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombre
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable cuentaLDAP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public String getCuentaLDAP() {
		return cuentaLDAP;
	}

	/**
	 * @param cuentaLDAP : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setCuentaLDAP(String cuentaLDAP) {
		this.cuentaLDAP = cuentaLDAP;
	}
	

	/**
	 * @return valor de tipo String que esta contenido en la variable usuario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/09/2017
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/09/2017
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return valor de tipo String que está contenido en el atributo
	 *         genero
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
	 * @return valor de tipo String que esta contenido en la variable usuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHora
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return valor de tipo Date que esta contenido en la variable fechaHora
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/09/2017
	 */
	public String getNombreArea() {
		return nombreArea;
	}
	/**
	 * @param fechaHora : valor que se ingresa a la variable de tipo Date
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/09/2017
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
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
	 * @return valor de tipo String que esta contenido en la variable tratamiento
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 19/10/2017
	 */
	public String getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 19/10/2017
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tratamiento
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 19/10/2017
	 */
	public String getExtensionTel() {
		return extensionTel;
	}

	/**
	 * @param tratamiento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 19/10/2017
	 */
	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreCompleto
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/12/2017
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
}
