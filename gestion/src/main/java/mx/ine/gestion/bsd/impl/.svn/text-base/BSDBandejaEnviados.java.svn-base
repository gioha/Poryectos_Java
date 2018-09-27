/**
 * @(#)BSDBandejaEnviados.java 14/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.gestion.as.inter.ASBandejaEnviadosInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaEnviadoInterface;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaEnviados")
@Scope("prototype")
public class BSDBandejaEnviados implements BSDBandejaEnviadoInterface{
	
	@Autowired
	@Qualifier("asBandejaEnviados")
	private ASBandejaEnviadosInterface asBandejaEnviadosInterface;
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.bsd.inter.BSDBorradorDocumentosInterface#consultarEnviados
	 * (java.lang.String, boolean)
	 */
	@Override
	public List<DTOEnviadosDocumentosEntity> consultarEnviados(DTOEnviadosDocumentosHelper helperEnviados) throws Exception {

		return asBandejaEnviadosInterface.consultarEnviados(helperEnviados);
	}
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEnviadoInterface#eliminarEnviados(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public void eliminarEnviados(DTOEnviadosDocumentosEntity enviado) throws Exception {
		asBandejaEnviadosInterface.eliminarEnviados(enviado);
	}
}
