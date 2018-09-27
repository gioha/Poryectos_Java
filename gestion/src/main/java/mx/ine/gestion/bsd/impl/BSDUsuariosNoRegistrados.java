/**
 * @(#)BSDUsuariosNoRegistrados.java 16/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASUsuariosNoRegistradosInterface;
import mx.ine.gestion.bsd.inter.BSDUsuariosNoRegistradosInterface;
import mx.ine.gestion.dto.helper.DTOInformacionUsNoRegHelper;

/**
 * Clase que contiene las llamadas a los AS´s que se utilizan en la vista de usuarios no registrados
 * 
 * @author Roberto Shirásago Domínguez
 * @since 16/01/2018
 */
@Scope("prototype")
@Component("bsdUsuariosNorRegistrados")
public class BSDUsuariosNoRegistrados implements BSDUsuariosNoRegistradosInterface {

	@Autowired
	@Qualifier("asUsuariosNoRegistrados")
	private ASUsuariosNoRegistradosInterface asUsuariosNoRegistradosInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDUsuariosNoRegistradosInterface#buscarInformacionUsuarioNoRegistrado(mx.ine.gestion.dto.helper.DTOInformacionUsNoRegHelper)
	 */
	@Override
	public void buscarInformacionUsuarioNoRegistrado(DTOInformacionUsNoRegHelper info) {

		asUsuariosNoRegistradosInterface.buscarInformacionUsuarioNoRegistrado(info);
	}
}
