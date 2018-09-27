/**
 * @(#)BOAdministradorAcuerdosInterface.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.bo;

import java.util.List;

import mx.ine.common.ws.administracion.dto.response.DTOMenuModulosResponse;
import mx.ine.acuerdos.dto.helper.DTOMenuHelper;

/**
 * Interfaz que contendra la firma de los métodos que procesan la información y/o contienen
 * las reglas de negocio del administrador del sistema de gestión
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
public interface BOAdministradorAcuerdosInterface {

	/**
	 * Método que obtiene el menú que se utilizara en gestión a partir de la información que regresa el servicio de administración.
	 * 
	 * @param menuAdmin: Objeto con la información que se obtiene del servicio de administración que contiene el menú cargado en dicho esquema.
	 * @return List<DTOMenuHelper>: lista con las opciones del menú que se utilizan en gestión.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public List<DTOMenuHelper> procesarMenuAdministracion(DTOMenuModulosResponse menuAdmin) ;
}
