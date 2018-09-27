/**
 * @(#)BSDBandejaECCPInterface.java 06/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDBandejaECCPInterface {
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * CCP y una persona.
	 * 
	 * @param persona
	 * @param ccp
	 * @param activar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaCCP(DTOEstructuraAreasEntity persona, DTOBandejaECCPEntity ccp, boolean activar)throws Exception;
}
