/**
 * @(#)BOAdministradorGestionInterface.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOOficialiaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOMenuHelper;

/**
 * Interfaz que contendra la firma de los métodos que procesan la información y/o contienen
 * las reglas de negocio del administrador del sistema de gestión
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
public interface BOAdministradorGestionInterface {

	/**
	 * Método que obtiene la información que se ingresara al objeto que se encuentra en sesión para tenerlo disponible todo el tiempo.
	 * 
	 * @param persona: Objeto con la información registrada en tablas del esquema de gestión.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public void obtenerInformacionEstructuraParaUsuario(DTOEstructuraAreasEntity persona);

	/**
	 * Método que obtiene la información de una persona registrada como OFICILIA DE PARTES que se ingresara al objeto 
	 * que se encuentra en sesión para tenerlo disponible todo el tiempo.
	 * 
	 * @param personaDeOficilia: Objeto con la información registrada en tablas del esquema de gestión.
	 * @param areasOficialia: Todas las areas en las que esta registrad@ esa persona de oficilia.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 02/09/2017
	 */
	public void obtenerInformacionOficialiaParaUsuario(DTOOficialiaEntity personaDeOficilia, List<DTOCAreaEntity> areasOficialia);

	/**
	 * Método que obtiene el menú que se utilizara en gestión a partir de la información que regresa el query de administración.
	 * 
	 * @param tipoPerfil: Nos indica el tipo de perfil, por ejemplo el valor 'OF' indica que es un perfil de oficialia
	 * @param menuAdmin: Lista con la información que se obtiene del query de administración que contiene el menú cargado en dicho esquema.
	 * @return List<DTOMenuHelper>: lista con las opciones del menú que se utilizan en gestión.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public List<DTOMenuHelper> procesarMenuAdministracion(List<DTOMenuHelper> menuAdmin, String tipoPerfil);
}
