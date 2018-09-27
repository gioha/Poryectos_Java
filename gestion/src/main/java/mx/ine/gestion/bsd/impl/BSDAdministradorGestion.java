/**
 * @(#)BSDAdministradorGestion.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASAdministradorGestionInterface;
import mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del administrador general del sistema de Gestión
 *
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */
@Component("bsdAdministradorGestion")
@Scope("prototype")
public class BSDAdministradorGestion implements BSDAdministradorGestionInterface {

	@Autowired
	@Qualifier("asAdministradorGestion")
	private ASAdministradorGestionInterface administradorGestionInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface#obtenMenuGestion(java.lang.String)
	 */
	@Override
	public List<DTOMenuHelper> obtenMenuGestion(String tipoPerfil) {

		return this.administradorGestionInterface.obtenMenuGestion(tipoPerfil);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface#obtenNotificacionesMenuSeguimiento(java.util.List)
	 */
	@Override
	public void obtenNotificacionesMenuSeguimiento(List<DTOMenuHelper> menus) {
	
		this.administradorGestionInterface.obtenNotificacionesMenuSeguimiento(menus);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface#obtenNotificacionesMenuOficilia(java.util.List)
	 */
	@Override
	public void obtenNotificacionesMenuOficilia(List<DTOMenuHelper> menus) {

		this.administradorGestionInterface.obtenNotificacionesMenuOficilia(menus);
	}
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDAdministradorGestionInterface#cargaInformacionGestionEnUsuario()
	 */
	@Override
	public void cargaInformacionGestionEnUsuario() throws Exception {

		this.administradorGestionInterface.cargaInformacionGestionEnUsuario();
	}
}
