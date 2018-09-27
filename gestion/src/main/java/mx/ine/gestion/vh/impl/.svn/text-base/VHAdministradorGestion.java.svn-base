/**
 * @(#)VHAdministradorGestion.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.vh.inter.VHAdministradorGestionInterface;

/**
 * Clase que contiene la implementación de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 11/10/2017
 */
@Component("vhAdministradorGestion")
@Scope("prototype")
public class VHAdministradorGestion implements VHAdministradorGestionInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHAdministradorGestionInterface#obtenerPantallaInicioSegunRol()
	 */
	@Override
	public String obtenerPantallaInicioSegunRol() {

		String ruta = "";
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		
		//1.- Obtenemos el objeto que contiene la información del usuario en sesión
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		usuarioLogueado.setPuedeApartarFolios(false);
		
		//2.- Validamos por rol
		if (usuarioLogueado.getUsuarioProblemasEnRegistroCuenta()) {
		
			ruta = contextPath + "/app/error='problemasCuenta'";
			
		} else if (usuarioLogueado.getUsuarioNoRegistradoEnGestion() && usuarioLogueado.getRol().toUpperCase().contains("ADMIN")) {
			
			ruta = contextPath + "/app/estructura/captura";
			
		} else if (usuarioLogueado.getUsuarioAmbosRoles()) {
		
			ruta = contextPath + "/app/seleccion_rol";
			
		} else if (usuarioLogueado.getIdOficialia() != null) { 
			
			ruta = contextPath + "/app/bandeja_oficialia/administracion";

		} else if (usuarioLogueado.getIdPersona() != null) {
			
			ruta = contextPath + "/app/bandeja/administracion";
			usuarioLogueado.setPuedeApartarFolios(true);
			
		} else if (usuarioLogueado.getUsuarioNoRegistradoEnGestion()) {
			
			ruta = contextPath + "/app/vista_usuario_no_registrado";
		}
		
		return ruta;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHAdministradorGestionInterface#obtenerPantallaInicioMasDeUnRol(java.lang.String)
	 */
	@Override
	public String obtenerPantallaInicioMasDeUnRol(String tipoPerfil) {

		String ruta = "";
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		if (tipoPerfil != null && tipoPerfil.equalsIgnoreCase("OF")) {
			
			ruta = contextPath + "/app/bandeja_oficialia/administracion";
			usuarioLogueado.setPuedeApartarFolios(false);
			
		} else {

			ruta = contextPath + "/app/bandeja/administracion";
			usuarioLogueado.setPuedeApartarFolios(true);
		}

		return ruta;
	}
}
