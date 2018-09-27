/**
 * @(#)BSDBandejaEntrada.java 28/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import mx.ine.gestion.as.inter.ASBandejaEntradaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaEntradaInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("bsdBandejaEntrada")
@Scope("prototype")
public class BSDBandejaEntrada implements BSDBandejaEntradaInterface {

	@Autowired
	@Qualifier("asBandejaEntrada")
	private ASBandejaEntradaInterface asBandejaEntradaInterface;

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEntradaInterface#obtenrHistoricoTurnado(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento) throws Exception{
		return asBandejaEntradaInterface.obtenrHistoricoTurnado(documento);
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bsd.inter.BSDBandejaEntradaInterface#actualizarDocumentoAEnterado(mx.ine.gestion.dto.db.DTOEstructuraAreasEntity, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) throws Exception {
		asBandejaEntradaInterface.actualizarDocumentoAEnterado(persona, documento);
	}

}
