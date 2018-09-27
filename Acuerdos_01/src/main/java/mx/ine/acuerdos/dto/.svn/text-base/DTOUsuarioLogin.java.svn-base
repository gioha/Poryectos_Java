/**
 * @(#)DTOBase.java 21/02/2014
 *
 * Copyright (C) 2014 INE.
 * Todos los derechos reservados.
 */

package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


/**
 * Clase que contiene los datos del usuario que se autentica.
 * 
 * @author Martha Castañón
 * @since 20 de marzo de 2014
 */
public class DTOUsuarioLogin extends User implements Serializable {

    /**
     * Elemento necesario para la serialización de los objetos generados de esta
     * clase.
     */
    private static final long serialVersionUID = -6309588765960326593L;
    
    /**
     * Username del usuario que se autentica.
     */
    private String usuario;
    
    /**
     * Nombre del usuario que se autentica.
     */
    private String nombreUsuario;
    /**
     * Identificador de la Entidad dónde se ubica la Junta a la que pertenece el usuario
     */
    private Integer idEstado;
    /**
	 * Atributo utilizado para mostra la entidad a la que pertenece el usuario
	 */
	private String nombreEstado;
    /**
     * Identificador del Distito dónde se ubica la Junta a la que pertenece el usuario
     */
    private Integer idDistrito;
    /**
	 * Atributo nombre del distrito
	 */
	private String nombreDistrito;
   
    /**
	 * Atributo que contiene la ubicaciÃ³n/area a la que pertenece el usuario
	 * ou en LDAP
	 */
	private String ubicacion;
	
	/**
     * Rol que el usuario tiene dentro del sistema.
     */
    private String rolUsuario;
    
    /**
     * Atributo del identificador del sistema utilizado para el single sing on
     */
    private Integer idSistema;
    /**
     * Atributo que contiene la lista de roles no correspondientes 
     * al sistema con los que cuenta el usuario para el inicio único.
     */
    private List<String> rolesLdap;

    /**
     * Atributo que contiene la lista de de sistemas 
     * al los que el usuario tiene permiso para modificación de roles.
     */
    private List<Integer> conteosAreasPermitidas;
    
    
    private Integer idMunicipio;
    
    private Integer idSO;
    
    private Integer idEstadoSO;
    
    private Integer tipoSO;
    
    private Integer idLDAP;
    
    private Integer idProcesoElectoral;
    
    private Integer idDetalleProceso;
    
    private String tipo;
    
    private Boolean heredaPropiedades;
    
    private String nivelRol;
    
    private String ambitoUsuario;
    
    /**
     * Constructor
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */

        
	public DTOUsuarioLogin(String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        
    }



	/**
	 *Método que obtiene el valor del atributo usuario
	 * @return el String: usuario
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getUsuario() {
		return usuario;
	}



	/**
	 * Método que ingresa el valor al atributo the usuario 
	 * @param usuario el usuario a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	/**
	 *Método que obtiene el valor del atributo nombreUsuario
	 * @return el String: nombreUsuario
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}



	/**
	 * Método que ingresa el valor al atributo the nombreUsuario 
	 * @param nombreUsuario el nombreUsuario a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	/**
	 *Método que obtiene el valor del atributo idEstado
	 * @return el Integer: idEstado
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public Integer getIdEstado() {
		return idEstado;
	}



	/**
	 * Método que ingresa el valor al atributo the idEstado 
	 * @param idEstado el idEstado a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}



	/**
	 *Método que obtiene el valor del atributo nombreEstado
	 * @return el String: nombreEstado
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}



	/**
	 * Método que ingresa el valor al atributo the nombreEstado 
	 * @param nombreEstado el nombreEstado a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}



	/**
	 *Método que obtiene el valor del atributo idDistrito
	 * @return el Integer: idDistrito
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public Integer getIdDistrito() {
		return idDistrito;
	}



	/**
	 * Método que ingresa el valor al atributo the idDistrito 
	 * @param idDistrito el idDistrito a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}



	/**
	 *Método que obtiene el valor del atributo nombreDistrito
	 * @return el String: nombreDistrito
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getNombreDistrito() {
		return nombreDistrito;
	}



	/**
	 * Método que ingresa el valor al atributo the nombreDistrito 
	 * @param nombreDistrito el nombreDistrito a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}



	/**
	 *Método que obtiene el valor del atributo ubicacion
	 * @return el String: ubicacion
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getUbicacion() {
		return ubicacion;
	}



	/**
	 * Método que ingresa el valor al atributo the ubicacion 
	 * @param ubicacion el ubicacion a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}



	/**
	 *Método que obtiene el valor del atributo rolUsuario
	 * @return el String: rolUsuario
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public String getRolUsuario() {
		return rolUsuario;
	}



	/**
	 * Método que ingresa el valor al atributo the rolUsuario 
	 * @param rolUsuario el rolUsuario a ingresar
	 * @autor Mayra Victoria
	 * @since 12/09/2016
	 */
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}



	/**
	 *Método que obtiene el valor del atributo idSistema para el single sing on
	 * @return el Integer: idSistema
	 * @autor Mayra Victoria
	 * @since 14/09/2016
	 */
	public Integer getIdSistema() {
		return idSistema;
	}



	/**
	 * Método que ingresa el valor al atributo the idSistema para el single sing on
	 * @param idSistema el idSistema a ingresar
	 * @autor Mayra Victoria
	 * @since 14/09/2016
	 */
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}



	/**
	 *Método que obtiene el valor del atributo rolesLdap para el inicio único.
	 * @return el List<String>: rolesLdap
	 * @autor Mayra Victoria
	 * @since 14/09/2016
	 */
	public List<String> getRolesLdap() {
		return rolesLdap;
	}



	/**
	 * Método que ingresa el valor al atributo the rolesLdap para el inicio único.
	 * @param rolesLdap el rolesLdap a ingresar
	 * @autor Mayra Victoria
	 * @since 14/09/2016
	 */
	public void setRolesLdap(List<String> rolesLdap) {
		this.rolesLdap = rolesLdap;
	}

	


	/**
	 *Método que obtiene el valor del atributo conteosAreasPermitidas.
	 * @return el List<Integer> de conteosAreasPermitidas
	 * @autor Mayra Victoria
	 * @since 23/09/2016
	 */
	public List<Integer> getConteosAreasPermitidas() {
		return conteosAreasPermitidas;
	}



	/**
	 * Método que ingresa el valor al atributo conteosAreasPermitidas.
	 * @param conteosAreasPermitidas el atributo conteosAreasPermitidas a ingresar
	 * @autor Mayra Victoria
	 * @since 23/09/2016
	 */
	
	public void setConteosAreasPermitidas(List<Integer> conteosAreasPermitidas) {
		this.conteosAreasPermitidas = conteosAreasPermitidas;
	}



	public Integer getIdMunicipio() {
		return idMunicipio;
	}



	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}



	public Integer getIdSO() {
		return idSO;
	}



	public void setIdSO(Integer idSO) {
		this.idSO = idSO;
	}



	public Integer getIdEstadoSO() {
		return idEstadoSO;
	}



	public void setIdEstadoSO(Integer idEstadoSO) {
		this.idEstadoSO = idEstadoSO;
	}



	public Integer getTipoSO() {
		return tipoSO;
	}



	public void setTipoSO(Integer tipoSO) {
		this.tipoSO = tipoSO;
	}



	public Integer getIdLDAP() {
		return idLDAP;
	}



	public void setIdLDAP(Integer idLDAP) {
		this.idLDAP = idLDAP;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Boolean getHeredaPropiedades() {
		return heredaPropiedades;
	}



	public void setHeredaPropiedades(Boolean heredaPropiedades) {
		this.heredaPropiedades = heredaPropiedades;
	}



	public Integer getIdProcesoElectoral() {
		return idProcesoElectoral;
	}



	public void setIdProcesoElectoral(Integer idProcesoElectoral) {
		this.idProcesoElectoral = idProcesoElectoral;
	}



	public Integer getIdDetalleProceso() {
		return idDetalleProceso;
	}



	public void setIdDetalleProceso(Integer idDetalleProceso) {
		this.idDetalleProceso = idDetalleProceso;
	}



	/**
	 * @author Dulce Magali Martinez Torres
	 * @since 03/05/2017
	 * @return the nivelRol
	 */
	public String getNivelRol() {
		return nivelRol;
	}



	/** 
	 * @author Dulce Magali Martinez Torres
	 * @since 03/05/2017
	 * @param nivelRol the nivelRol to set
	 */
	public void setNivelRol(String nivelRol) {
		this.nivelRol = nivelRol;
	}



	/**
	 * @author Dulce Magali Martinez Torres
	 * @since 04/05/2017
	 * @return the ambitoUsuario
	 */
	public String getAmbitoUsuario() {
		return ambitoUsuario;
	}



	/** 
	 * @author Dulce Magali Martinez Torres
	 * @since 04/05/2017
	 * @param ambitoUsuario the ambitoUsuario to set
	 */
	public void setAmbitoUsuario(String ambitoUsuario) {
		this.ambitoUsuario = ambitoUsuario;
	}

   
}
