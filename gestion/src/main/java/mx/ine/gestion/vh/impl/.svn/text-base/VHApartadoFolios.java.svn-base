/**
 * @(#)VHApartadoFolios.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;
import mx.ine.gestion.vh.inter.VHApartadoFoliosInterface;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de Apartado de Folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
@Component("vhApartadoFolios")
@Scope("prototype")
public class VHApartadoFolios implements VHApartadoFoliosInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHApartadoFoliosInterface#cargarInformacionUsuarioEnSesion(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void cargarInformacionUsuarioEnSesion(DTOFiltrosApartadoFolioHelper filtros) {
	
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		filtros.setTipoArea(usuarioLogueado.getTipoArea());
		filtros.setIdArea(usuarioLogueado.getIdArea());
		filtros.setIdEstado(usuarioLogueado.getIdEstado());
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHApartadoFoliosInterface#reiniciarFiltros(java.lang.Integer, mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public void reiniciarFiltros(Integer escenarioReinicio, DTOFiltrosApartadoFolioHelper filtros) {

		switch(escenarioReinicio) {
		
			case 1:
				filtros.setIdEstado(null);
				filtros.setIdArea(null);
				filtros.setIdTipoDocumento(null);
				filtros.setIdAcronimo(null);
				filtros.setListaAreas(null);
				filtros.setListaEstados(null);
				filtros.setListaTiposDocumento(null);
				filtros.setListaAcronimos(null);
				break;
			
			case 2:
				filtros.setIdArea(null);
				filtros.setIdTipoDocumento(null);
				filtros.setIdAcronimo(null);
				filtros.setListaAreas(null);
				filtros.setListaEstados(null);
				filtros.setListaTiposDocumento(null);
				filtros.setListaAcronimos(null);
				break;
			
			case 3:
				filtros.setIdTipoDocumento(null);
				filtros.setIdAcronimo(null);
				filtros.setListaAreas(null);
				filtros.setListaTiposDocumento(null);
				filtros.setListaAcronimos(null);
				break;
			
			case 4:
				filtros.setIdAcronimo(null);
				filtros.setListaTiposDocumento(null);
				filtros.setListaAcronimos(null);
				break;

		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHApartadoFoliosInterface#validarCapturaApartadoFolios(mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper)
	 */
	@Override
	public String validarCapturaApartadoFolios(DTOFiltrosApartadoFolioHelper filtros) {

		String error = "";
		
		//1.- Validamos que el número de folios a apartar sea mayor a 0
		if (Integer.valueOf(filtros.getNumeroFoliosAApartar()) <= 0) {
			
			error = "El número de folios a apartar debe ser mayor a 0, verifique la información.";
		}
		
		return error;
		
	}
}
