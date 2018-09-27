 /**
 * @(#)CustomGrantedAuthority.java 01/03/2017
 * <p>
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.enums.EnumVigenciaProceso;
import mx.ine.common.helper.HLPAdministracionInterface;
import mx.ine.common.ws.administracion.dto.response.DTODetalleEstadoProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleMunicipioProcesoWS;
import mx.ine.common.ws.administracion.dto.response.DTODetalleProcesoWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.servicio.admin.dto.DTOResultado;
import mx.ine.servicio.admin.mb.MBServicioAdminLdap;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.util.Utilidades;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author INE
 *
 */
public class CustomUserDetailsService {
    /**
     * Elementopara generar log
     */
    private static final Log LOGGER = LogFactory.getLog(CustomUserDetailsService.class);
    
    @Autowired
    @Qualifier("hlpAdministracion")
    private HLPAdministracionInterface hlpAdmin;
    
    @Autowired
    @Qualifier("mbServicioAdminLdap")
    private MBServicioAdminLdap mbServicio;
    
    public UserDetails cargaUsuario(Integer idSistema, String userName, String password){
        DTOUsuarioLogin user;
        DTOResultado resultado;
        try{
            resultado = mbServicio.getDTO(idSistema, userName, password);
            Set<GrantedAuthority> authorities;
            //Valida si trae informacion
            if(resultado != null && resultado.getRoles() != null &&
                    CollectionUtils.isNotEmpty(resultado.getRoles())){
                //Obtener informacion del usuario
                authorities = obtenAuthorities(resultado.getRoles());
                user = new DTOUsuarioLogin(userName, "", true, true, true, true, authorities);
                user.setUsuario(userName);
                user.setIdSistema(idSistema);
                user.setUbicacion(Utilidades.mensajeProperties("constante_generica_ubicacion_usuario"));
                user.setIdEstado(resultado.getIdEdo() == null ? 
                        0 : resultado.getIdEdo().intValue());
                user.setIdMunicipio(resultado.getIdMpo() == null ?
                        0 : resultado.getIdMpo().intValue());
                //Roles
                obtenerRoles(user, authorities);
            }else{
                throw new BadCredentialsException("El usuario no tiene permisos para este sistema");
            }
        }catch(Exception e){
            LOGGER.error("Error CustomUserDetailsService - Al obtener la información de Usuario", e);
            throw new BadCredentialsException("El usuario no tiene permisos para este sistema");
        }
        //Valida si cuenta con rol del sistema
        if (user.getRolUsuario() != null){
            //Obtener versión usuario
            obtenerVersion(user, resultado.getIdEdo().toString(), resultado.getIdMpo() == null?"":resultado.getIdMpo().toString());
        }else{
            throw new BadCredentialsException("El usuario no tiene permisos para este sistema");
        }
        //Valida proceso para usuario
        if(!cargaProcesos(user)){
            throw new BadCredentialsException("El usuario no cuenta con los permisos correspondientes.");
        }
        LOGGER.info("Usuario logueado: " + user.getNombreUsuario());
        return user;
    }
    
    /**
     * Método encargado de obtener los roles LDAP
     * 
     * @author José Antonio López Torres
     * @param user
     * @param authorities
     * @since 8/11/2016
     */
    private void obtenerRoles(DTOUsuarioLogin user, Collection<? extends GrantedAuthority> authorities){
        List<String> roles = new ArrayList<>();
        if(authorities != null){
            roles = localizaRoles(user, authorities);
        }
        user.setRolesLdap(roles);
        LOGGER.info("lista de roles LDAP (Usuario): " + roles);
    }
    
    /**
     * Método encargado de localizar los roles LDAP
     * 
     * @param user : Usuario
     * @param authorities : Roles LDAP
     * @return List<String> : Roles LDAP
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private List<String> localizaRoles(DTOUsuarioLogin user, Collection<? extends GrantedAuthority> authorities){
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority auth : authorities){
            String rol = auth.getAuthority();
            if(rol.toUpperCase().contains(Utilidades.mensajeProperties("constante_roles_sistema"))){
                String rolApp=auth.getAuthority().split("ROLE_")[1];
                user.setRolUsuario(rolApp);
                roles.add(rol);
            }
        }
        return roles;
    }
    
    /**
     * Método encargado de convertir los roles de LDAP en GrantedAuthority
     * para el manejor en spring security
     * 
     * @param roles : Roles LDAP
     * 
     * @return Set<GrantedAuthority> : Roles LDAP
     *
     * @author José Antonio López Torres 
     * @since 20/02/2017
     */
    private Set<GrantedAuthority> obtenAuthorities(List<String> roles){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(String rol : roles){
            StringBuilder sb = new StringBuilder();
            sb.append("ROLE_").append(rol);
            GrantedAuthority authority = new CustomGrantedAuthority(sb.toString());
            authorities.add(authority);
        }
        return authorities;
    }
    
    /**
     * Método encargado de obtener la versión del usuario (OC, JL, JD, OPLE, CONFI, CONSEJERO y MAPAS) y 
     * los datos del estado y duistrito del usuario
     * 
     * @param user : Usuario
     *
     * @author Pablo Zuñiga Mata 
     * @since 23/02/2017
     */
    private void obtenerVersion(DTOUsuarioLogin user, String idEstado, String idMunicipio){
        String[] rolUsuario = user.getRolUsuario().split("\\.");
        String version = "";
        if(rolUsuario.length == 5){
            version = rolUsuario[4];
        }else if(rolUsuario.length == 4){
            version = rolUsuario[3];
        }else if(rolUsuario.length == 3){
            version = rolUsuario[2];
        }else{
            version = rolUsuario[1];
        }
        if(version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_confi"))
                || version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_consejero"))
                || version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_mapas"))){
            version = "OC";
        }
        if(version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_ople"))){
            version = "JL";
        }
        user.setVersion(version);
        if (version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_oc"))){
            user.setIdEstado(0);
            user.setIdMunicipio(0);
        }else if (version.equalsIgnoreCase(Utilidades.mensajeProperties("constante_version_rol_jl"))){
            user.setIdEstado(Integer.parseInt(idEstado));
            user.setIdMunicipio(0);
        }else{
            user.setIdEstado(Integer.parseInt(idEstado));
            user.setIdMunicipio(Integer.parseInt(idMunicipio));
        }
    }
    
    /**
     * Método encargado de vaidar si el sistema cuenta con detalles tomando en cuenta
     * el estado y distrito del usuario.<p>
     * La información recolectado de este método indicará el comportamiento de los
     * combos del menú, tomando en cuenta la versión del usuario.
     * 
     * @param user : Usuario
     * @return boolean : true-Todo bien, false:Acceso denegado
     *
     * @author Pablo Zuñiga Mata
     * @since 13/02/2017
     */
    private boolean cargaProcesos(DTOUsuarioLogin user){
        boolean respuesta = true;
        List<DTODetalleProcesoWS> lista = null;
        try{
            //JL o JM
        	if(user.getVersion().equalsIgnoreCase(
            		Utilidades.mensajeProperties("constante_version_rol_jl")) || user.getVersion().equalsIgnoreCase(
                    		Utilidades.mensajeProperties("constante_version_rol_jm"))){
                lista = hlpAdmin.obtenerDetallesProceso(user.getIdSistema(), EnumVigenciaProceso.S.getValor(),
                        null, new Integer(user.getIdEstado()), null);
            }else{
                lista = hlpAdmin.obtenerDetallesProceso(user.getIdSistema(), EnumVigenciaProceso.S.getValor(),
                        null, null, null);
            }
          //Valida si existen procesos
            if(lista == null){
                respuesta = false;
            //Si hay solo un proceso
            }else if(lista.size() == 1){
                //Obtener datos del detalle
                user.setListaDetalles(lista);
                user.setDetalleSeleccionado(lista.get(0));
                user.setIdProcesoElectoral(lista.get(0).getIdProcesoElectoral());
                user.setIdDetalleProceso(lista.get(0).getIdDetalleProceso());
                if("F".equalsIgnoreCase(lista.get(0).getAmbitoDetalle())){
                    user.setAmbitoDetalleProceso(EnumAmbitoDetalleProceso.F);
                }else{
                    user.setAmbitoDetalleProceso(EnumAmbitoDetalleProceso.L);
                }
                //Valida Estado
                validaEstado(user);
            //Si existen mas de un detalle
            }else{
                user.setListaDetalles(lista);
            }
        }catch(ClienteWebServiceException e){
            LOGGER.error("Error CustomUserDetailsService - Al validar proceso", e);
            respuesta = false;
        }
        return respuesta;
    }
    
    /**
     * Método encargado de validar el estado del usuario contra el proceso
     * 
     * @param user : Usuario
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private void validaEstado(DTOUsuarioLogin user) throws ClienteWebServiceException{
    	List<DTODetalleEstadoProcesoWS> lista = hlpAdmin.obtenerEstadosDestalle(
    			user.getIdSistema(), user.getIdProcesoElectoral(), user.getIdDetalleProceso());
        if(lista != null){
            user.setListaEstados(lista);
            DTODetalleEstadoProcesoWS estado = null;
            if(user.getIdEstado()!= null && user.getIdEstado() > 0){
            	estado = localizaEstado(lista, new Integer(user.getIdEstado()));
                if(estado != null){
                    user.setEstadoSeleccionado(estado);
                    user.setIdEstadoSeleccionado(estado.getIdEstado().intValue());
                    //valida municipio
                    validaMunicipios(user);
                }else{
                    LOGGER.error("No existe el estado del usuario");
                }
            }
        }else{
            LOGGER.error("Lista de estados vacía");
        }
    }
    
    /**
     * Método encargado de localizar un estado de una lista de estado por el
     * idEstado del usuario
     * 
     * @param estados : Lista de estados
     * @param idEstado : Id del estado del usuario
     * @return DTODetalleEstadoProcesoWS : estado
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private DTODetalleEstadoProcesoWS localizaEstado(List<DTODetalleEstadoProcesoWS> estados, Integer idEstado){
    	DTODetalleEstadoProcesoWS estado = null;
        for(DTODetalleEstadoProcesoWS a : estados){
            if(a.getIdEstado().intValue() == idEstado.intValue()){
                estado = a;
                break;
            }
        }
        return estado;
    }
    
    /**
     * Método encargado de validar el distrito del usuario contra el proceso
     * 
     * @param user : Distrito
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private void validaMunicipios(DTOUsuarioLogin user) throws ClienteWebServiceException{
    	List<DTODetalleMunicipioProcesoWS> lista = hlpAdmin.obtenerMunicipiosDetalle(
                user.getIdSistema(), user.getIdProcesoElectoral(), 
                user.getIdDetalleProceso(), user.getIdEstadoSeleccionado());
        if(lista != null){
            user.setListaMunicipios(lista);
            DTODetalleMunicipioProcesoWS municipio = null;
            if(user.getIdMunicipio()!= null && user.getIdMunicipio() > 0){
            	municipio = localizaMunicipio(lista, new Integer(user.getIdMunicipio()));
                if(municipio != null){
                    user.setMunicipioSeleccionado(municipio);
                    user.setIdMunicipioSeleccionado(municipio.getIdMunicipio().intValue());
                }else{
                    LOGGER.error("No existe el distrito del usuario");
                }
            }
        }else{
            LOGGER.error("Lista de distritos vacía");
        }
    }
    
    /**
     * Método encargado de localizar un distrito de una lista de distritos por el 
     * idDistrito del usuario
     * 
     * @param distritos : Lista de distritos
     * @param idDistrito : Id del distrito del usuario
     * @return DTODetalleDistritoProcesoWS : distrito
     *
     * @author José Antonio López Torres 
     * @since 13/02/2017
     */
    private DTODetalleMunicipioProcesoWS localizaMunicipio(List<DTODetalleMunicipioProcesoWS> municipios, Integer idMunicipio){
    	DTODetalleMunicipioProcesoWS municipio = null;
        for(DTODetalleMunicipioProcesoWS a : municipios){
            if(a.getIdMunicipio().intValue() == idMunicipio.intValue()){
            	municipio = a;
                break;
            }
        }
        return municipio;
    }

}
