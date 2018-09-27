/**
 * @(#)BSDAdministradorAcuerdos.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.bsd.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASAdministradorAcuerdosInterface;
import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDAdministradorAcuerdosInterface;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.dto.admin.DTOMenu;
import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.dto.admin.DTOSubMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de administrar el o los AS del administrador general del sistema de Gestión
 *
 * @author Roberto Shirásago Domínguez
 * @updatedBy Sampier
 * @updatedBy Giovanni Hernández Alonso
 * @since 09/12/2017
 */
@Component("bsdAdministradorAcuerdos")
@Scope("prototype")
public class BSDAdministradorAcuerdos implements BSDAdministradorAcuerdosInterface {
//
	@Autowired
	@Qualifier("asAdministradorAcuerdos")
	private ASAdministradorAcuerdosInterface asAdministradorAcuerdos;

	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;
	
	// metodo general para cualquier cualquier sistema que invoque su menu desde tablas de admin creadas localmente
	// NOTA: en el AS hay que cambiar el id de sistema
	@Override
	public List<DTOMenu> obtenMenuAcuerdos() throws Exception{

		List<DTOMenu> menu = new ArrayList<DTOMenu>();
		
		// recuperamos el ménu de sistema (hasta aqui esta llamada del menu  funcina para cualquier sistema con sus tablas de Admin locales)
		menu = asAdministradorAcuerdos.obtenMenuAcuerdos();
		
		return menu; 
	}

	
	// metodo customisado para Acuerdos
	@Override
	public List<DTOModulo> obtenModulosReportes( List<DTOMenu> menu ) throws Exception {
		
		List<DTOModulo> modulosReportes = new ArrayList< DTOModulo >();
		
		for (DTOMenu m : menu) {
			
			for (DTOSubMenu sM : m.getSubmenus()) {
				
				for (DTOModulo mod : sM.getModulos()) {
				
					if(mod.getTipo().equalsIgnoreCase("R")){
						modulosReportes.add(mod);
					}
				}
			}
		}
		
		if( modulosReportes.size() > 0 ){
			
			DTOModulo remove = new DTOModulo();
			
			for (DTOModulo m : modulosReportes) {
				
				if( m.getNombre().equalsIgnoreCase("REPORTES") ){
					remove = m;
					break;
				}
			}
			
			modulosReportes.remove(remove);
		}
		
		return modulosReportes;
	}

	
	// metodo customisado para Acuerdos
	@Override
	public List<DTOModulo> obtenModulosCatalogos(List<DTOMenu> menu) throws Exception {
	
		List<DTOModulo> modulosCatalogos = new ArrayList< DTOModulo >();
		
		for (DTOMenu m : menu) {
			
			if ( m.getDescripcion().equalsIgnoreCase("ADMINISTRACIÓN") || m.getDescripcion().equalsIgnoreCase("ADMINISTRACION")  ){

				for (DTOSubMenu sM : m.getSubmenus()) {
					for (DTOModulo mod : sM.getModulos()) {
						modulosCatalogos.add(mod);
					}
				}				
				break;
			}	
		}

		
		if( modulosCatalogos.size() > 0 ){
			
			DTOModulo remove = new DTOModulo();
			
			for (DTOModulo m : modulosCatalogos) {
				
				if( m.getNombre().equalsIgnoreCase("CATALOGOS") || m.getNombre().equalsIgnoreCase("CÁTALOGOS") ){
					remove = m;
					break;
				}
			}
			
			modulosCatalogos.remove(remove);
		}
		
		
		return modulosCatalogos;
	}


	@Override
	public List<DTOMenu> obtenMenuCustomizadoHeader( List<DTOMenu> menu ) throws Exception {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		List<DTOMenu> menuHeader = new ArrayList<DTOMenu>();
		
		for (DTOMenu m : menu) {
			
			boolean agrega = false;
			
			if( !m.getDescripcion().equalsIgnoreCase( "BANDEJA DE DOCUMENTOS" ) ){
				agrega = true;
				
				
				if( m.getDescripcion().equalsIgnoreCase( "SESION" ) || m.getDescripcion().equalsIgnoreCase( "SESIÓN" ) ){
					
					if(
							!(
								usuarioLogueado.getRolUsuario().toUpperCase().equalsIgnoreCase("ACUERDOS.CAPTURA_ADMIN.OC") || 
								usuarioLogueado.getRolUsuario().toUpperCase().equalsIgnoreCase("ACUERDOS.CONSULTA_ADMIN.OC") ||
								usuarioLogueado.getRolUsuario().toUpperCase().equalsIgnoreCase("ACUERDOS.CAPTURA_SE.OC") ||
								usuarioLogueado.getRolUsuario().toUpperCase().equalsIgnoreCase("ACUERDOS.CONSULTA_SE.OC") 
							)
					  )
					{
						agrega = false;
					}
					
					
				}
			}
						
			if(agrega)
				menuHeader.add(m);
		}
		
		return menuHeader;
	}
	
	@Override
	public String rutaVideo()throws Exception{		
		return boArchivos.getRutaVideo("//Ambiente//GlusterFS\\acuerdos\\Video_Tutorial_Acuerdos.mp4");
	}

}
