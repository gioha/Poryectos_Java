/**
 * @(#)BSDBandejaEntradaInterface.java 28/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import org.primefaces.model.TreeNode;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDBandejaEntradaInterface {

	public TreeNode obtenrHistoricoTurnado(DTODocumentoEntity documento)throws Exception;
	
	public void actualizarDocumentoAEnterado(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento) throws Exception;

}
