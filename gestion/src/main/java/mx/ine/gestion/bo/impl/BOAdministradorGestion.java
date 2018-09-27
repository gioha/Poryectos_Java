/**
 * @(#)BOAdministradorGestion.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.List;

import mx.ine.gestion.bo.inter.BOAdministradorGestionInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que guarda la logica que se utiliza para procesar la información general del sistema.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 08/07/2017
 */
@Component("boAdministradorGestion")
@Scope("prototype")
public class BOAdministradorGestion implements BOAdministradorGestionInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOAdministradorGestionInterface#obtenerInformacionEstructuraParaUsuario(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity)
	 */
	@Override
	public void obtenerInformacionEstructuraParaUsuario(DTOEstructuraAreasEntity persona) {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		usuarioLogueado.setIdArea(persona.getIdArea());
		usuarioLogueado.setTipoArea(persona.getTipoArea());
		usuarioLogueado.setIdPersona(persona.getIdPersona());
		usuarioLogueado.setIdEstado(persona.getArea().getIdEntidad());
		usuarioLogueado.setUsuarioNoRegistradoEnGestion(false);
		usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOAdministradorGestionInterface#obtenerInformacionOficialiaParaUsuario(mx.ine.gestion.dto.db.DTOOficialiaEntity, java.util.List)
	 */
	@Override
	public void obtenerInformacionOficialiaParaUsuario(DTOOficialiaEntity personaDeOficilia, List<DTOCAreaEntity> areasOficialia) {

		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		usuarioLogueado.setIdOficialia(personaDeOficilia.getIdOficialia());
		usuarioLogueado.setAreasOficialia(areasOficialia);
		usuarioLogueado.setUsuarioNoRegistradoEnGestion(false);
		usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOAdministradorGestionInterface#procesarMenuAdministracion(java.util.List, java.lang.String)
	 */
	@Override
	public List<DTOMenuHelper> procesarMenuAdministracion(List<DTOMenuHelper> menuAdmin, String tipoPerfil) {

		for (DTOMenuHelper opcionMenu : menuAdmin) {
			
			opcionMenu.setImagenesMenu(this.obtenImagenMenuXId(opcionMenu.getIdModulo()));
		}
		
		return menuAdmin;
	}

	/**
	 * Obtiene los nombres de la imagenes que lleva el menú a través de su ID.
	 * 
	 * @param idMenu: identificador del menú que se desea encontrar su imágen.
	 * @return Cadena con el nombre de la imagen encontrada.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	private String[] obtenImagenMenuXId(Integer idMenu) {

		String nombresImagenes[] = new String[3];
		
		switch(idMenu) {
			
			case 2:
			case 3:
				nombresImagenes[0] = "btgris_1.png";
				nombresImagenes[1] = "btmag_1.png";
				nombresImagenes[2] = "btvin_1.png";
				
				break;
			
			case 1:
				nombresImagenes[0] = "btgris_2.png";
				nombresImagenes[1] = "btmag_2.png";
				nombresImagenes[2] = "btvin_2.png";

				break;
				
			case 19:
			case 8:
				nombresImagenes[0] = "btgris_7.png";
				nombresImagenes[1] = "btmag_7.png";
				nombresImagenes[2] = "btvin_7.png";
				
				break;
			
			case 7:
				nombresImagenes[0] = "btgris_5.png";
				nombresImagenes[1] = "btmag_5.png";
				nombresImagenes[2] = "btvin_5.png";
				
				break;
				
			case 4:
			case 5:
				nombresImagenes[0] = "btgris_3.png";
				nombresImagenes[1] = "btmag_3.png";
				nombresImagenes[2] = "btvin_3.png";
				
				break;
			
			case 6:
				nombresImagenes[0] = "btgris_4.png";
				nombresImagenes[1] = "btmag_4.png";
				nombresImagenes[2] = "btvin_4.png";
				
				break;
			
			case 9:
				nombresImagenes[0] = "btgris_6.png";
				nombresImagenes[1] = "btmag_6.png";
				nombresImagenes[2] = "btvin_6.png";
				
				break;
		}
		
		return nombresImagenes;
	}
}
