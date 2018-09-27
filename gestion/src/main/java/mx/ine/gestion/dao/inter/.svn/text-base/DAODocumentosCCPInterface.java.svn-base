package mx.ine.gestion.dao.inter;

import java.util.List;
import mx.ine.gestion.dto.db.DTODocumentoCCPID;
import mx.ine.gestion.dto.db.DTODocumentoCcpEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @(#)DAOCcpInterface.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * DTODocumentoCcpEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
public interface DAODocumentosCCPInterface extends DAOGenericGestionInterface<DTODocumentoCcpEntity, DTODocumentoCCPID> {

	/**
	 * Método para obtener la los ccp de un documento.
	 * 
	 * @param documento
	 *            documento del cual se quieren los ccp.
	 * @return los ccp de dicho documento.
	 *
	 *
	 * @author Sergio Ley Garcia
	 * @since 18/09/2017
	 */
	public List<DTODocumentoCcpEntity> buscarPorDocumento(DTODocumentoEntity documento);

	/**
	 * Método que borra los ccp de un documento de la base,
	 * 
	 * @param idDocumento
	 *            id del documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 30/10/2017
	 */
	public void borrarCcp(Integer idDocumento);
	
	/**
	 * Método que regresa la lista de personas CCP asociadas a un documento.
	 * 
	 * @param documento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 28/11/2017
	 */
	public List<DTOEstructuraAreasEntity> obtenerPersonasCCP(DTODocumentoEntity documento);
	
	/**
	 * Método que consulta la lista de ccp por documento.
	 * 
	 * @param idDocumento: Integer que contiene el id del documento
	 * @return List<DTODocumentoCcpEntity> contiene la lista de ccp
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public List<DTODocumentoCcpEntity> consultarCCP(Integer idDocumento);
}
