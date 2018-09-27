/**
 * @(#)DAOHistDocRecepInterface.java 18/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocRecepEntity;

/**
 * @author Pável Alexei Martínez Regalado
 * @since 18/01/2018
 *
 */
public interface DAOHistDocRecepInterface extends DAOGenericGestionInterface<DTOHistDocRecepEntity, Integer> {
	
	public DTOHistDocRecepEntity obtenerHistoricoRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona);
	
	/**
	 * 
	 * @param documento
	 * @param persona
	 * @param estatus
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/02/2018
	 */
	public DTOHistDocRecepEntity obtenerHistoricoRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona, Integer estatus);
	
	/**
	 * Método que obtiene los reistros de la tabla "HIST_DOC_RECEP" que
	 * coinciden con el id del obejto documento recibido. 
	 * 
	 * @param documento: Objeto documento del cual se quiere obtener la lista de las personas relacionadas en el turnado
	 * @param personasTurnaron: Si es true regresará todos las personas que turnaron el documento, en caso
	 * contrario se regresara las personas a las que fueron turnadas
	 * @return: Lista de personas que estan involucradas en el turnado del documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/02/2018
	 */
	public List<DTOHistDocRecepEntity> obtenerListaHistoricoRecepcion(DTODocumentoEntity documento, boolean personasTurnaron);
	
	public List<DTOHistDocRecepEntity> obtenerListaPersonasUnicasTurnaron(DTODocumentoEntity documento);
	
	public Integer obtenerIdHistRecepcion(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno, DTOEstructuraAreasEntity personaTurnada);
	
}
