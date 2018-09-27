/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.ine.acuerdos.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.util.Utilidades;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;



/**
 *
 * @author martha
 */
public class CustomUserDetailsMapper implements UserDetailsContextMapper{
	private static final Logger logger = Logger.getLogger(CustomUserDetailsMapper.class);
	String nombre;

	@Autowired
	@Qualifier("ldapTemplate")
	private transient LdapTemplate ldapTemplate;

	@Override
	public UserDetails mapUserFromContext(DirContextOperations arg0,
			String userName, Collection<? extends GrantedAuthority> arg2) {                                         

//        @Autowired
//    	@Qualifier("ldapUserSearchUID")
//    	private transient FilterBasedLdapUserSearch ldapUserSearch;
//
//    	@Autowired
//    	@Qualifier("ldapTemplate")
//    	private transient LdapTemplate ldapTemplate;
//
//    	@Autowired
//    	@Qualifier("bsdLogin")
//    	private transient BSDLoginInterface boLogin;
//
//    	
//    	private Integer idGrupo;
//    	private List<DTOSistemaPermisos> listaSistemas;
    	
		DTOUsuarioLogin user = new DTOUsuarioLogin(userName, "", true, true, true, true, arg2);
		try{
			user.setNombreUsuario(arg0.getStringAttribute("CN"));
			user.setUsuario(userName);       
			user.setUbicacion(arg0.getStringAttribute("OU"));

			String idEstado = arg0.getAttributes().get("idEstado").toString().split("\\s")[1];
			String idDistrito = arg0.getAttributes().get("idDistrito").toString().split("\\s")[1];

			List<String> listaRolesLDAP = new ArrayList<String>();
            if (user.getAuthorities()!= null){
	            for (GrantedAuthority auth : user.getAuthorities()) {
	            	listaRolesLDAP.add(auth.getAuthority());
	            	
	            	if (auth.getAuthority().toUpperCase().contains("ACUERDOS")){
	            		//ROLE_ELEC2015.ADMIN.OC
	            		String rolApp=auth.getAuthority().toString().split("ROLE_")[1];
	            		user.setRolUsuario(rolApp);
	            	}
	            }
            }
			
            user.setRolesLdap(listaRolesLDAP);
			logger.info("listaRolesLDAP:"+listaRolesLDAP);
			
			String version = user.getRolUsuario().toString().split("\\.")[2];
			logger.info("rol:"+user.getRolUsuario()+" version:"+version);
			if (version.equalsIgnoreCase("OC")){
				user.setIdEstado(0);
				user.setIdDistrito(0);
			}else if (version.equalsIgnoreCase("JL")){
				user.setIdEstado(Integer.parseInt(idEstado));
				user.setIdDistrito(0);
			}else{
				user.setIdEstado(Integer.parseInt(idEstado));
				user.setIdDistrito(Integer.parseInt(idDistrito));
			}
			

			/*Asignación de la propiedad idSistema*/
			ResourceBundle messages = ResourceBundle.getBundle("ApplicationConfig");/*DTO con las propiedades del usuario*/
			user.setIdSistema(Integer.parseInt(messages.getString("application.id")));


			
			logger.info("usuario logeado");
		}catch(Exception ex){
			logger.error("Error al generar el usuario en el logueo", ex);
		}
		return user;
	}

		@Override
		public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {	

		}

	}
