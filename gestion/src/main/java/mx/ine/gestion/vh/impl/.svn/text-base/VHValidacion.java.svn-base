package mx.ine.gestion.vh.impl;

/**
 * @(#)VHPersonal.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.mb.MBValidacion;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHValidacionInterface;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAV;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAVImpl;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que tendran la lógica y procedimientos del módulo Personal.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */

@Component("vhValidacion")
@Scope("prototype")
public class VHValidacion implements VHValidacionInterface {

	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(MBValidacion.class);

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#mostrarMensajeGrowl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto) {
		if(tipo.equals(Utilidades.mensajeProperties("constante_growl_info"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_advertencia"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_exito"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, texto));			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#obtenerInicialesNombre(java.lang.String)
	 */
	@Override
	public String obtenerInicialesNombre(String nombre) {
		String iniciales = "";
		String[] nombreArr = nombre.split(" ");
		for (String nom : nombreArr) {
			if(nom.length() > 0) {
				iniciales += nom.charAt(0);
			} 
		}
		return iniciales;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#obtenerInicialesNombre(java.util.List)
	 */
	@Override
	public String obtenerInicialesNombre(
			List<DTOEstructuraAreasEntity> listaPersonas) {
		String iniciales = "";
		
		for (DTOEstructuraAreasEntity persona : listaPersonas) {
			iniciales += obtenerInicialesNombre(persona.getNombreCompleto()) + ",";
		}
		
		return iniciales.substring(0, iniciales.length()-2);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#enviarError(java.lang.Exception, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void enviarError(Exception e, String clase, String metodo, String mensaje) {
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		logger.error("<=================== Clase: " + clase + " , Método: " + metodo);
		if(!mensaje.equals("")) {
			logger.error(mensaje);			
		}
		if (usuarioLogueado != null) {
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());			
		}
		logger.error("",e);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHValidacionInterface#visualizarDocumento(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void visualizarDocumento(String nombreArchivo, String ruta,
			boolean soloLectura) throws Exception {
		
		
		ASConversorURLparaWEBDAV asConversorURL;
		
		String hiperlink = "";
		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();
		
		try {
			
			hiperlink = asConversorURL.crearHiperLinkWebDav(
					nombreArchivo, 
					ruta, 
					SecurityContextHolder.getContext().getAuthentication().getName(), 
					soloLectura, 
					"sidjwebdav", 
					(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
			
			logger.info("<================ HPLINK: " + hiperlink);
			
		} catch (GeneralSecurityException e) {
			logger.error("",e);
		} catch (IOException e) {
			logger.error("",e);
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(hiperlink);
		} catch (IOException e) {
			logger.error("",e);
			
		}
	}

	
	
}
