/**
 * @(#)DAODocumentosRemitentesInterface.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentosRemitentesExtEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesExtID;

/**
 * Interfaz que declara los métodos que interactúan con la tabla DOCUMENTOS_REMITENTES_EXT del esquema GESTION4.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
public interface DAODocumentosRemitentesExtInterface extends DAOGenericGestionInterface<DTODocumentosRemitentesExtEntity, DTODocumentosRemitentesExtID> {

	/**
	 * Método encargado de consultar a los remitentes externos asignados a un documento de oficialía.
	 * @return List<Integer>: Lista de remitentes externos asignados
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public List<Integer> consultarRemitentesExtAsignados(Integer idArea);

}
