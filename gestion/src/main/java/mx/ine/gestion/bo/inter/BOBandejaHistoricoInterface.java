/**
 * @(#)BOBandejaHistoricoInterface.java 12/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

/**
 * @author Homero Fidel Villanuevav
 * @since 12/03/2018
 *
 */
public interface BOBandejaHistoricoInterface {
	
	public DTOHBandejaERecibidosEntity crearHRecibido(DTOBandejaERecibidosEntity recibido);
	
	public DTOHBandejaEAtencionEntity crearHAtencion(DTOBandejaEAtencionEntity atencion);
	
	public DTOHBandejaECCPEntity crearHCCP(DTOBandejaECCPEntity ccp);
	
	public DTOHBandejaEInfoEntity crearHInfo(DTOBandejaEInfoEntity info);
	
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOHBandejaERecibidosEntity recibido) throws Exception ;
	
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOHBandejaEAtencionEntity atencion) throws Exception ;

}