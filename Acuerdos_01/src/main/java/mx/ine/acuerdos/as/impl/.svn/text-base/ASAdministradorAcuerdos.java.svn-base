/**
 * @(#)ASAdministradorAcuerdos.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.acuerdos.as.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASAdministradorAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOMenuAdminInterface;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.dto.admin.DTOMenu;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Clase encargada de administrar el o los DAO del administrador general del sistema de Acuerdos
 *
 * @author Roberto Shirásago Domínguez
 * @updatedBy Sampier
 * @updatedBy Giovanni Hernández Alonso
 * @since 08/12/2017
 */
@Component("asAdministradorAcuerdos")
@Scope("prototype")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASAdministradorAcuerdos implements ASAdministradorAcuerdosInterface {

	public static final Logger log = Logger.getLogger(ASAdministradorAcuerdos.class);
	 
//	@Autowired
//	@Qualifier("boAdministradorAcuerdos")
//	private BOAdministradorAcuerdosInterface boAdministradorAcuerdos;
//
//	@Autowired
//	@Qualifier("hlpAdministracion")
//    private HLPAdministracionInterface hlpAdministracion;
	
	@Autowired
	@Qualifier("daoMenuAdmin")
    private DAOMenuAdminInterface daoMenuAdmin;


	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASAdministradorGestionInterface#obtenMenuGestion()
	 */
	@Override
	public List<DTOMenu> obtenMenuAcuerdos()throws ClienteWebServiceException{
		
		List<DTOMenu> menu = new ArrayList<DTOMenu>();
		
		// recuperamos el usuario que ha iniciado sesión
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		// recuperamos la estructura del ménu del sistema
		menu = daoMenuAdmin.obtenMenuSistema( 155, usuarioLogueado.getRolUsuario().toUpperCase() );
		
		
//		DTOMenuModulosResponse menuAdmin = hlpAdministracion.generaMenuAccionesConsejoEnObjetos(0, 0, 155, 0, 0, usuarioLogueado.getRolUsuario());
//		List<DTOMenuHelper> menuProcesado = boAdministradorAcuerdos.procesarMenuAdministracion(menuAdmin);
		
		return menu;		
	
	}

}
