/**
 * @(#)BOAdministradorAcuerdos.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.bo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.bo.BOAdministradorAcuerdosInterface;
import mx.ine.acuerdos.dto.helper.DTOMenuHelper;
import mx.ine.acuerdos.dto.helper.HELPAccioneModulo;
import mx.ine.common.ws.administracion.dto.response.DTOMenuModulosResponse;
import mx.ine.common.ws.administracion.dto.response.DTOSubmenuModulosResponse;
import mx.ine.common.ws.administracion.dto.response.DTOModuloListResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author INE
 *@updatedBy Sampier
 */
@Component("boAdministradorAcuerdos")
@Scope("prototype")
public class BOAdministradorAcuerdos implements BOAdministradorAcuerdosInterface {


	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.acuerdos.bo.inter.BOAdministradorAcuerdosInterface#procesarMenuAdministracion(mx.ine.common.ws.administracion.dto.response.DTOMenuModulosResponse)
	 */
	@Override
	public List<DTOMenuHelper> procesarMenuAdministracion(DTOMenuModulosResponse menuAdmin) { 

		List<DTOMenuHelper> menuProcesadoParaAcuerdos = new ArrayList<DTOMenuHelper>();
		
		for (DTOSubmenuModulosResponse menu : menuAdmin.getListAccionesMenu()) {
			
			DTOMenuHelper opcionMenu = new DTOMenuHelper();
				String menuMinusculas = menu.getNombreSubMenu().toLowerCase();		
				String mayuscula=menuMinusculas.charAt(0)+""; 			 
				mayuscula=mayuscula.toUpperCase();
				menuMinusculas=menuMinusculas.replaceFirst(menuMinusculas.charAt(0)+"", mayuscula);			
			
			opcionMenu.setNombreMenu(menuMinusculas);
			opcionMenu.setIdMenu(menu.getModuloslist().get(0).getIdModulo());
			opcionMenu.setUrlMenu(menu.getModuloslist().get(0).getListUrlModulos().get(0).getUrlModulo());
			
				List<HELPAccioneModulo> subMenus =  new ArrayList<HELPAccioneModulo>();
					for (DTOModuloListResponse submenuLista : menu.getModuloslist()) {
						int contador = 0;
						HELPAccioneModulo sub = new HELPAccioneModulo();
						  sub.setAccionDescrip(submenuLista.getNombreModulo());	
						  sub.setUrlModulo(submenuLista.getListUrlModulos().get(0).getUrlModulo());						 
						  subMenus.add(sub);					  
						  opcionMenu.setSubMenu(subMenus);
					}		
			
			          
			menuProcesadoParaAcuerdos.add(opcionMenu);
		}		
	
		return menuProcesadoParaAcuerdos;
	}
	
}
