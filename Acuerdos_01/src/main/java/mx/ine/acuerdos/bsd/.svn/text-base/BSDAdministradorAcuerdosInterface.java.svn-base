/**
 * @(#)BSDAdministradorAcuerdosInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.bsd;

import java.util.List;

import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.acuerdos.dto.admin.DTOMenu;
import mx.ine.acuerdos.dto.admin.DTOModulo;
import mx.ine.acuerdos.dto.helper.DTOMenuHelper;

/**
 * Interface  que contiene la firma de los métodos que acceden a el o los AS del administrador general del sistema de Gestión
 *
 * @author Roberto Shirásago Domínguez
 * @updatedBy Giovanni Hernández Alonso
 * @since 29/08/2017
 */
public interface BSDAdministradorAcuerdosInterface {

	/**
	 * Método que obtiene el menú a travez de las clases ya generadas del jar "funciones-comunes" y que mandan llamar
	 * al webservice del menú de administración.
	 * 
	 * @return List<DTOMenuHelper>: Lista que contiene las opciones del menú que se utilizan.
	 * @throws ClienteWebServiceException: excepción en caso de haber un error.
	 *	 * @author Roberto Shirásago Domínguez
     * @since 05/09/2017
	 */
	public List<DTOMenu> obtenMenuAcuerdos() throws Exception;	
	
	public List<DTOMenu> obtenMenuCustomizadoHeader( List<DTOMenu> menu ) throws Exception;

	public List<DTOModulo> obtenModulosReportes( List<DTOMenu> menu ) throws Exception; 
	
	public List<DTOModulo> obtenModulosCatalogos( List<DTOMenu> menu ) throws Exception;
	
	public String rutaVideo()throws Exception;
	
}
