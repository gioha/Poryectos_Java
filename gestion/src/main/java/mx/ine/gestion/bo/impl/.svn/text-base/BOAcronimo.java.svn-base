/**
 * @(#)BOAcronimo.java 11/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.util.Map;

import mx.ine.gestion.bo.inter.BOAcronimoInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * acronimos para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 11/01/2018
 */
@Component("boAcronimo")
@Scope("prototype")
public class BOAcronimo implements BOAcronimoInterface {

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOAcronimoInterface#procesaQueryConsultaAcronimos(java.lang.String, mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper, java.util.Map)
	 */
	@Override
	public String procesaQueryConsultaAcronimos(String query, Map<String, Object> filtrosColumna) {

		String validaciones = "";
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		//1.- Validamos si el usuario es de área para filtrar
		if (usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC")) {
			
			validaciones = " WHERE " + "acr.tipo_area = " + usuarioLogueado.getTipoArea() + " AND acr.id_area = " + usuarioLogueado.getIdArea();
		}

		//2.- Validamos que haya filtros para el rol de admin área
		if (usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC") && filtrosColumna.entrySet().size() > 0) {
			
			if (filtrosColumna.containsKey("idTipoDocumento")) {

				validaciones = validaciones + " AND acr.id_tipo_documento = " + filtrosColumna.get("idTipoDocumento");
			}
	
			if (filtrosColumna.containsKey("descripcion")) {

				validaciones = validaciones + " AND acr.descripcion like '" + filtrosColumna.get("descripcion") + " %'";
			}
		} 
		
		//3.- Validamos que haya filtros para los otros roles
		else if (!usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC") && filtrosColumna.entrySet().size() > 0) {
			
			validaciones = " WHERE ";
			int contadorValidaciones = 0;
			
			if (filtrosColumna.containsKey("tipoArea")) {
				
				validaciones = validaciones + " acr.tipo_area = " + filtrosColumna.get("tipoArea");
				contadorValidaciones++;
			}
			
			if (filtrosColumna.containsKey("area.descripcion")) {
				
				validaciones = validaciones + (contadorValidaciones > 0 ? " AND " : "") + " ca.descripcion like '" + filtrosColumna.get("area.descripcion")  + "%'";
				contadorValidaciones++;
			}
			
			if (filtrosColumna.containsKey("idTipoDocumento")) {

				validaciones = validaciones + (contadorValidaciones > 0 ? " AND " : "") + "  acr.id_tipo_documento = " + filtrosColumna.get("idTipoDocumento");
				contadorValidaciones++;
			}
	
			if (filtrosColumna.containsKey("descripcion")) {

				validaciones = validaciones + (contadorValidaciones > 0 ? " AND " : "") + "  acr.descripcion like '" + filtrosColumna.get("descripcion") + "%'";
				contadorValidaciones++;
			}
		}

		query = query.replaceAll("<!validaciones>", validaciones);
		
		return query;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOAcronimoInterface#procesaQueryConsultaTotalesAcronimos(java.lang.String)
	 */
	@Override
	public String procesaQueryConsultaTotalesAcronimos(String query) {
		
		String validaciones = "";
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		//1.- Validamos si el usuario es de área para filtrar
		if (usuarioLogueado.getRol().equals("GESTION4.ADMIN_AREA.OC")) {
			
			validaciones = "WHERE" + " acr.tipo_area = " + usuarioLogueado.getTipoArea() + " AND acr.id_area = " + usuarioLogueado.getIdArea();
		}
		
		query = query.replaceAll("<!validaciones>", validaciones);
		
		return query;
	}
}
