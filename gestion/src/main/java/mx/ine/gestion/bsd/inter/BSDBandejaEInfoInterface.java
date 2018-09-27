/**
 * @(#)BSDBandejaEInfoInterface.java 06/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDBandejaEInfoInterface {
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * recibido y una persona.
	 * 
	 * @param persona
	 * @param recibido
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaInfo(DTOEstructuraAreasEntity persona, DTOBandejaEInfoEntity info, boolean activar) throws Exception;

	/**
	 * @param infoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public List<DTOInstruccionesEntity> obtenerInstrucciones(DTOBandejaEInfoEntity infoSeleccionado)throws Exception;
}
