/**
 * @(#)VHBandejaSeguimiento.java 16/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHBandejaSeguimientoInterface;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("vhBandejaSeguimiento")
@Scope("prototype")
public class VHBandejaSeguimiento implements VHBandejaSeguimientoInterface{

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHBandejaSeguimientoInterface#mostrarMensajeGrowl(java.lang.String, java.lang.String, java.lang.String)
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

}
