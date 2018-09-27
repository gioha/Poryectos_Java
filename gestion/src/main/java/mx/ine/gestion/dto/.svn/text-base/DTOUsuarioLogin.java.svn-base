/**
 * @(#)DTOUsuarioLogin.java 29/08/2017
 * Copyright (C) 2017 INE.
 * Todos los derechos reservados.
 */

package mx.ine.gestion.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Dto que contiene los atributos que guardan la información del usuario
 * que se encuentra en sesión
 *
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */	
public class DTOUsuarioLogin extends User implements Serializable {

	/**
     * Elemento necesario para la serialización de los objetos generados de esta
     * clase.
     */
	private static final long serialVersionUID = -7230245713006496079L;
	
	/* ---------------------------------- ATRIBUTOS GENERALES -------------------------------------- */
    
    /**
	 * Llave primaria del sistema en el que esta logueado, en este caso "Gestión"
	 */
	private Integer idSistema;
	
	/**
	 * Rol del sistema de gestion que tiene el usuario
	 */
	private String rol;
	
	/**
	 * Atributo que contiene el nombre de la persona que se encuentra logueada
	 */
	private String nombreUsuario;

	/**
	 * Atributo que contiene el apellido de la persona que se encuentra logueada
	 */
	private String apellidoUsuario;

	/**
	 * Nos indica si el usuario ya esta dado de alta en el sistema de gestión
	 */
	private Boolean usuarioNoRegistradoEnGestion;

	/**
	 * Nos indica si el usuario tiene problemas en la cuenta que esta dada de alta en el sistema de gestión
	 */
	private Boolean usuarioProblemasEnRegistroCuenta;

	/**
	 * Nos indica si el usuario esta registrado para seguimiento y para oficilia.
	 */
	private Boolean usuarioAmbosRoles;

	/**
	 * Nos indica si el usuario puede apartar folios o no (depende de varios factores, no solo del rol)
	 */
	private Boolean puedeApartarFolios;
	
	/* ---------------------------------- ATRIBUTOS PARA PERSONA -------------------------------------- */
	
	/**
	 * Atributo que contiene el identificador del area con la cual esta registrado el usuario
	 */
	private Integer idArea;
	
	/**
	 * Atributo que contiene el tipo del área con la cual esta registrado el usuario
	 */
	private Integer tipoArea;

	/**
	 * Atributo que contendra el nombre del AREA como aparece/está registrada en LDAP
	 */
	private String nombreAreaLDAP;

	/**
	 * Atributo que contiene el identificador de la persona con la cual esta registrado el usuario
	 */
	private Integer idPersona;

	/**
	 * Atributo que contiene el identificador de la entidad del área en la cual esta registrado el usuario
	 */
	private Integer idEstado;
	
	/* ---------------------------------- ATRIBUTOS PARA OFICIALIA -------------------------------------- */

	/**
	 * Atributo que contiene el identificador de la oficilia con la cual esta registrado el usuario.
	 */
	private Integer idOficialia;

	/**
	 * Atributo que contiene las áreas que tiene el usuario disponibles para seleccionar o en las que esta ligado.
	 */
	private List<DTOCAreaEntity> areasOficialia;
	
	
	/* ---------------------------------- MÉTODOS -------------------------------------- */
	
	/**
	 * Sobreescritura del constructor por defecto lo pide al extender del objeto User
	 * 
	 * Nota* los parametros que pide como es sobreescritura no se que valores traiga
	 * 		 por lo cual no pongo una descriciÃ³n de ellos
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * 
	 * @author Roberto ShirÃ¡sago DomÃ­nguez
	 * @since 11/05/2016
	 */
	public DTOUsuarioLogin(String username, String password, boolean enabled, boolean accountNonExpired, 
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	/* ---------------------------------- GETTERS SETTERS -------------------------------------- */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idSistema
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Integer getIdSistema() {
		return idSistema;
	}

	/**
	 * @param idSistema : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rol
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreUsuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidoUsuario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	/**
	 * @param apellidoUsuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable usuarioNoRegistradoEnGestion
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Boolean getUsuarioNoRegistradoEnGestion() {
		return usuarioNoRegistradoEnGestion;
	}

	/**
	 * @param usuarioNoRegistradoEnGestion : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setUsuarioNoRegistradoEnGestion(Boolean usuarioNoRegistradoEnGestion) {
		this.usuarioNoRegistradoEnGestion = usuarioNoRegistradoEnGestion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersona
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public Integer getIdOficialia() {
		return idOficialia;
	}

	/**
	 * @param idOficialia : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 29/08/2017
	 */
	public void setIdOficialia(Integer idOficialia) {
		this.idOficialia = idOficialia;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreAreaLDAP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public String getNombreAreaLDAP() {
		return nombreAreaLDAP;
	}

	/**
	 * @param nombreAreaLDAP : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public void setNombreAreaLDAP(String nombreAreaLDAP) {
		this.nombreAreaLDAP = nombreAreaLDAP;
	}

	/**
	 * @return valor de tipo List<DTOCArea> que esta contenido en la variable areasOficialia
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public List<DTOCAreaEntity> getAreasOficialia() {
		return areasOficialia;
	}

	/**
	 * @param areasOficialia : valor que se ingresa a la variable de tipo List<DTOCAreas>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public void setAreasOficialia(List<DTOCAreaEntity> areasOficialia) {
		this.areasOficialia = areasOficialia;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable usuarioProblemasEnRegistroCuenta
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/10/2017
	 */
	public Boolean getUsuarioProblemasEnRegistroCuenta() {
		return usuarioProblemasEnRegistroCuenta;
	}

	/**
	 * @param usuarioProblemasEnRegistroCuenta : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/10/2017
	 */
	public void setUsuarioProblemasEnRegistroCuenta(
			Boolean usuarioProblemasEnRegistroCuenta) {
		this.usuarioProblemasEnRegistroCuenta = usuarioProblemasEnRegistroCuenta;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable usuarioAmbosRoles
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/11/2017
	 */
	public Boolean getUsuarioAmbosRoles() {
		return usuarioAmbosRoles;
	}

	/**
	 * @param usuarioAmbosRoles : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/11/2017
	 */
	public void setUsuarioAmbosRoles(Boolean usuarioAmbosRoles) {
		this.usuarioAmbosRoles = usuarioAmbosRoles;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEstado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable puedeApartarFolios
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public Boolean getPuedeApartarFolios() {
		return puedeApartarFolios;
	}

	/**
	 * @param puedeApartarFolios : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/12/2017
	 */
	public void setPuedeApartarFolios(Boolean puedeApartarFolios) {
		this.puedeApartarFolios = puedeApartarFolios;
	}

}
