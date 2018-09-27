/**
 * @(#)BSDBandejaHistorico.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASBandejaEntradaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaHistoricoInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaHistorico")
@Scope("prototype")
public class BSDBandejaHistorico implements BSDBandejaHistoricoInterface{

	@Autowired
	@Qualifier("asBandejaEntrada")
	private ASBandejaEntradaInterface asBandejaEntradaInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaHistoricoInterface#registrarAtencion(mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity, mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void registrarAtencion(DTOHBandejaERecibidosEntity recibido,
			DTOEstructuraAreasEntity persona, Integer estatus, String comentario) {
		// TODO Auto-generated method stub
		
	}
}
