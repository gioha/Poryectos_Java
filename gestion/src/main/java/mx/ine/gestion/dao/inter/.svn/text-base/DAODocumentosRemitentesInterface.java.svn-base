/**
 * @(#)DAODocumentosRemitentesInterface.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesID;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Interface que contiene los métodos que consultarán a la tabla
 * DOCUMENTOS_REMITENTES del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 21/11/2017
 */
public interface DAODocumentosRemitentesInterface extends DAOGenericGestionInterface<DTODocumentosRemitentesEntity, DTODocumentosRemitentesID> {


	/**
	 * Método para obtener los remitentes de un documento.
	 * 
	 * @param documento
	 *            documento del cual se quieren los remitentes.
	 * @return los remitentes de dicho documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 05/12/2017
	 */
	public List<DTODocumentosRemitentesEntity> buscarPorDocumento(DTODocumentoEntity documento);

	/**
	 * Método que a partir del id de un documento regresa la lista de personas
	 * que tiene como remitentes.
	 * 
	 * @param idDocumento
	 * @return List<DTODocumentosRemitentesEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 07/12/2017
	 */
	public List<DTODocumentosRemitentesEntity> consultarRemitentes(Integer idDocumento);

	/**
	 * Método que borra los remitentes de un documento de la base,
	 * 
	 * @param idDocumento
	 *            id del documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 06/12/2017
	 */
	public void borrarRemitentes(Integer idDocumento);
	
	/**
	 * Método que a partir del id de un documento regresa una cadena de personas
	 * que tiene como remitentes.
	 * 
	 * @param idDocumento
	 * @return String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/01/2018
	 */
	public String consultarRemitentesCadena(Integer idDocumento);
	
	public List<DTOEstructuraAreasEntity> obtenerPersonasRemitentes(DTODocumentoEntity documento);
}

