/**
 * @(#)BOBandejaEntradaInterface.java 01/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;
import mx.ine.gestion.dto.db.DTOHistDocTurnoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BOBandejaEntradaInterface {
	
	public DTOHistDocTurnoEntity crearHistDocTurno(DTOHistDocRecepEntity histRecep, Integer estatus, String comentario);
	
	public TreeNode crearArbolHistoricoTurnado(List<DTOHistDocRecepEntity> listaPersonasTurnaron, List<DTOHistDocRecepEntity> listaPersonasTurnados);
	
	public 	List<DTOHistDocRecepEntity> revisarRepetidosLista(List<DTOHistDocRecepEntity> listaPersonas, List<DTOHistDocRecepEntity> listaPersonasUnicas);
	
	public void crearRecibido(DTOEstructuraAreasEntity persona, DTODocumentoEntity documento);
	
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOBandejaERecibidosEntity recibido) throws Exception ;
	
	public void guardarAdjuntoGlusterPrincipal(DTOResponderTurnadoHelper filtros, DTOBandejaEAtencionEntity atencion) throws Exception ;
	
	//public void crearComentarios
}
