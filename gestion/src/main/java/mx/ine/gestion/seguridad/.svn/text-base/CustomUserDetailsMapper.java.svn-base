/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ine.gestion.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 * Clase encarga de obtener los valores/datos del usuario a través de sus diferentes métodos
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */
public class CustomUserDetailsMapper implements UserDetailsContextMapper{

	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(CustomUserDetailsMapper.class);

	/*
	 * (El comentario se encuentra en la interfase donde esta declarado el método)
	 * @see org.springframework.security.ldap.userdetails.UserDetailsContextMapper#mapUserFromContext(
	 * org.springframework.ldap.core.DirContextOperations, java.lang.String, java.util.Collection)
	 */
    @Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, 
			String userName, Collection<? extends GrantedAuthority> authorities) {                                         

    	DTOUsuarioLogin usuarioEnSesion = new DTOUsuarioLogin(userName, "", true, true, true, true, authorities);
    	
    	//Se obtiene el rol del usuario
    	this.obtenDatoRolUsuario(authorities, userName, usuarioEnSesion);

    	usuarioEnSesion.setIdSistema(Integer.valueOf(Utilidades.mensajeProperties("id_sistema")));
    	usuarioEnSesion.setNombreUsuario(ctx.getAttributes().get("gecos") != null ? ctx.getAttributes().get("gecos").toString().split(":")[1] : "");
    	usuarioEnSesion.setNombreAreaLDAP(ctx.getAttributes().get("ou") != null ? ctx.getAttributes().get("ou").toString().split(":")[1] : "");
        
        return usuarioEnSesion;
    }

    /*
     * (El comentario se encuentra en la interfase donde esta declarado el método)
     * @see org.springframework.security.ldap.userdetails.UserDetailsContextMapper#mapUserToContext(
     * org.springframework.security.core.userdetails.UserDetails, org.springframework.ldap.core.DirContextAdapter)
     */
    @Override
	public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {	
	}
   
    /**
     * MÃ©todo que obtiene el dato del rol de gestion que tiene el usuario
     * 
     * @param authorities : lista de roles que tiene el usuario
     * @param userName : nombre de usuario
     * @param usuarioEnSesion : objeto que contiene la información del usuario que se pondra en sesión
     *
     * @author Roberto Shirásago Domínguez
     * @since 11/05/2016
     */
    private void obtenDatoRolUsuario(Collection<? extends GrantedAuthority> authorities, String userName, DTOUsuarioLogin usuarioEnSesion) {
    	
		/*Obtiene los roles de gestion, 
		 * NOTA: se estan quitando los usuarios con rol de portal
		 */
		List<String> listaRolesGestionUsuario = new ArrayList<String>();
		
		if (authorities != null) {
		  	
			for (GrantedAuthority auth : authorities) {
		  		
		  		String rol = auth.getAuthority().toLowerCase();
		  		
		  		if (rol.contains("gestion4")) {
		
		  			listaRolesGestionUsuario.add(auth.getAuthority().substring(auth.getAuthority().indexOf("_") + 1, auth.getAuthority().length()));
		  		
		  		}
		  	}
		}

		//Validamos que tenga el rol de gestion
		if (listaRolesGestionUsuario.isEmpty()) {
			
			throw new BadCredentialsException("Acceso denegado, no cuenta con los permisos para ingresar al sistema");
			
		}
		
		//Validamos que únicamente tenga un rol de gestion
		if (listaRolesGestionUsuario.size() > 1) {
		  	
			log.error("<=================== ERROR en la clase CustomUserDetailsMapper en el método obtenDatoRolUsuario ");
		  	log.error("<=================== Al ingresar con el usuario: " + userName);
		  	log.error("<=================== Este usuario tiene más de un rol para el sistema de GESTIÓN VERSIÓN 4: ");
		
		  	int contadorRoles = 1;
		
		  	for (String rol : listaRolesGestionUsuario) {
			
		  			log.error("<=================== ROL Número " + contadorRoles + ": " + rol);
		      		contadorRoles++;
		 	}

		}
		
		usuarioEnSesion.setRol(listaRolesGestionUsuario.get(0));
    }
}
