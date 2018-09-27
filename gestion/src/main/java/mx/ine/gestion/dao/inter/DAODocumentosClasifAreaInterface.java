/**
 * @(#)DAODocumentosClasifAreaInterface.java 10/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaID;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de DOCUMENTOS_CLASIF_AREA del esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
public interface DAODocumentosClasifAreaInterface extends DAOGenericGestionInterface<DTODocumentosClasifAreaEntity, DTODocumentosClasifAreaID>{

	/**
	 * Método que consulta si hay un folio pendiente
	 * 
	 * @param idOficialia: Contiene el id de oficialia
	 * @param estatus: Contiene el estatus 'A'
	 * 
	 * @return DTOApartadosNumDocOfEntity Objeto que contiene el folio pendiente
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOApartadosNumDocOfEntity consultarFoliosPendientes(Integer idOficialia, char estatus);
	
}
