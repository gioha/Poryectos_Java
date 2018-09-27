/**
 * @(#)ASAdministradorGestion.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASAdministradorGestionInterface;
import mx.ine.gestion.bo.inter.BOAdministradorGestionInterface;
import mx.ine.gestion.dao.inter.DAOAdministradorGestionInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesInterface;
import mx.ine.gestion.dao.inter.DAONotificacionesOFInterface;
import mx.ine.gestion.dao.inter.DAOOficialiaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Clase encargada de administrar el o los DAO del administrador general del sistema de Gestión
 *
 * @author Roberto Shirásago Domínguez
 * @since 29/08/2017
 */
@Component("asAdministradorGestion")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASAdministradorGestion implements ASAdministradorGestionInterface {
	
	private static Logger logger= Logger.getLogger(ASAdministradorGestion.class);
	
	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;

	@Autowired
	@Qualifier("daoOficialia")
	private DAOOficialiaInterface daoOficialiaInterface;

	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daocAreasInterface;

	@Autowired
	@Qualifier("daoAdministradorGestion")
	private DAOAdministradorGestionInterface daoAdministradorGestionInterface;

	@Autowired
	@Qualifier("daoNotificaciones")
	private DAONotificacionesInterface daoNotificacionesInterface;

	@Autowired
	@Qualifier("daoNotificacionesOF")
	private DAONotificacionesOFInterface daoNotificacionesOFInterface;

	@Autowired
	@Qualifier("boAdministradorGestion")
	private BOAdministradorGestionInterface boAdministradorGestionInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAdministradorGestionInterface#obtenMenuGestion(java.lang.String)
	 */
	@Override
	public List<DTOMenuHelper> obtenMenuGestion(String tipoPerfil) {

		//1.- Obtenemos información necesaria
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String rol = tipoPerfil != null && tipoPerfil.equalsIgnoreCase("OF") ? "GESTION4.OFICIALIA.OC" : 
					 tipoPerfil != null && tipoPerfil.equalsIgnoreCase("SEG") ? "GESTION4.SEGUIMIENTO.OC" :
					 usuarioLogueado.getIdOficialia() != null ? "GESTION4.OFICIALIA.OC" :
					 usuarioLogueado.getIdPersona() != null && usuarioLogueado.getRol().equalsIgnoreCase("GESTION4.OFICIALIA.OC") ? "GESTION4.SEGUIMIENTO.OC" :
					 usuarioLogueado.getRol();
		
		//2.- Obtenemos el menú
		List<DTOMenuHelper> menuAdmin = this.daoAdministradorGestionInterface.consultarOpcionesMenuParaGestion(rol);

		//3.- Procesamos la información obtenida
		List<DTOMenuHelper> menuProcesado = boAdministradorGestionInterface.procesarMenuAdministracion(menuAdmin, tipoPerfil);

		//4.- Obtenemos las notificaciones
		if ((tipoPerfil != null && tipoPerfil.equalsIgnoreCase("OF")) || usuarioLogueado.getIdOficialia() != null) {
			this.obtenNotificacionesMenuOficilia(menuProcesado);
		} else {
			this.obtenNotificacionesMenuSeguimiento(menuProcesado);
		}
		
		return menuProcesado;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAdministradorGestionInterface#obtenNotificacionesMenuSeguimiento(java.util.List)
	 */
	@Override
	public void obtenNotificacionesMenuSeguimiento(List<DTOMenuHelper> menus) {
		
		Integer idPersonaLogueada = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdPersona();
		
		for (DTOMenuHelper menu : menus) {
		
			Integer notificaciones = this.daoNotificacionesInterface.consultarDocumentosPendientesXIdPersonaIdModulo(idPersonaLogueada, menu.getIdModulo());
			menu.setNumNotificaciones(notificaciones);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAdministradorGestionInterface#obtenNotificacionesMenuOficilia(java.util.List)
	 */
	@Override
	public void obtenNotificacionesMenuOficilia(List<DTOMenuHelper> menus) {

		List<DTOCAreaEntity> listaAreasOficialia = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAreasOficialia();
		String[] arreglosTiposAreasIdAreas = new String[listaAreasOficialia.size()];
		int contador = 0;
		
		for (DTOCAreaEntity area : listaAreasOficialia) {
			
			String tipoAreaIdArea = String.valueOf(area.getTipoArea()) + String.valueOf(area.getIdArea());
			arreglosTiposAreasIdAreas[contador] = tipoAreaIdArea;
			contador++;
		}
	
		for (DTOMenuHelper menu : menus) {
			
			Integer notificaciones = this.daoNotificacionesOFInterface.consultarDocumentosPendientesXAreaXIdModulo(arreglosTiposAreasIdAreas, menu.getIdModulo());
			menu.setNumNotificaciones(notificaciones);
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAdministradorGestionInterface#cargaInformacionGestionEnUsuario()
	 */
	@Override
	public void cargaInformacionGestionEnUsuario() throws Exception {
		
		//1.- Obtenemos el objeto que contiene la información del usuario en sesión
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		usuarioLogueado.setPuedeApartarFolios(false);
		
		//2.- Revisamos si hay algún registro del usuario en la tabla de ESTRUCTURA_AREA
		DTOEstructuraAreasEntity persona = this.daoEstructuraAreaInterface.consultarPersonaXCuentaActiva(usuarioLogueado.getUsername());
		
		//3.- Revisamos si hay algún registro del usuario en la de OFICIALIA
		DTOOficialiaEntity oficialia = this.daoOficialiaInterface.consultarOficialiaXCuentaActiva(usuarioLogueado.getUsername());
		
		//4.- Validamos la información
		//4.1.- Si esta registrado como persona
		if (persona != null) {
			
			this.boAdministradorGestionInterface.obtenerInformacionEstructuraParaUsuario(persona);
		} 
		
		//4.2.- Si esta registrado como oficilia
		if (oficialia != null) {

			List<DTOCAreaEntity> listaAreasDeLaPersonaOficilia = this.daoAdministradorGestionInterface.consultarAreasRegistradasParaUnaPersonaDeOficialia(oficialia.getIdOficialia());
			this.boAdministradorGestionInterface.obtenerInformacionOficialiaParaUsuario(oficialia, listaAreasDeLaPersonaOficilia);

		} 

		//5.- Validamos problemas o no registros
		//5.1.- Si no esta registrado y es administrador de area
		if (persona == null && usuarioLogueado.getRol().toUpperCase().contains("ADMIN_AREA")) {
				
			DTOCAreaEntity area = this.daocAreasInterface.buscarAreasPorNombre(usuarioLogueado.getNombreAreaLDAP().trim());
			
			//Validación para que no truene en caso de no encontrar nada Y se pueda resolver por BD
			if (area != null) {

				usuarioLogueado.setIdArea(area.getIdArea());
				usuarioLogueado.setTipoArea(area.getTipoArea());
				usuarioLogueado.setIdEstado(area.getIdEntidad());
				usuarioLogueado.setUsuarioNoRegistradoEnGestion(true);
				usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
				usuarioLogueado.setUsuarioAmbosRoles(false);

			} else {

				logger.error("<============ ERROR CONTROLADO!!!, el usuario: " + usuarioLogueado.getUsername() 
							+ " tiene rol de ADMIN_AREA pero no encuentra el area en nuestro cátalogo de C_AREAS, el área es: " 
							+ usuarioLogueado.getNombreAreaLDAP());

				usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(true);
				usuarioLogueado.setUsuarioNoRegistradoEnGestion(true);
				usuarioLogueado.setUsuarioAmbosRoles(false);
			}

		//5.2.- Si esta registrado en ambos
		} else if (persona != null && oficialia != null) {
		
			usuarioLogueado.setUsuarioAmbosRoles(true);
			usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
			usuarioLogueado.setUsuarioNoRegistradoEnGestion(false);
			
		//5.3.- Si no esta registrado en ningun lado
		} else if (persona == null && oficialia == null) {
			
			usuarioLogueado.setUsuarioAmbosRoles(false);
			usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
			usuarioLogueado.setUsuarioNoRegistradoEnGestion(true);

		//5.4.- Si esta registrado al menos en uno de los dos
		} else if (persona != null || oficialia != null) {
			
			usuarioLogueado.setUsuarioAmbosRoles(false);
			usuarioLogueado.setUsuarioProblemasEnRegistroCuenta(false);
			usuarioLogueado.setUsuarioNoRegistradoEnGestion(false);
		}
	}
}
