package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTONotificacionesOFEntity;

/**
 * @(#)DAODocumentoDestinatarioInterface.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * DTODocumentoDestinatarioEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
public interface DAODocumentoDestinatarioInterface extends DAOGenericGestionInterface<DTODocumentoDestinatarioEntity, DTODocumentoDestinatarioID> {

	/**
	 * Método para obtener los destinatarios de un documento.
	 * 
	 * @param documento
	 *            documento del cual se quieren los destinatarios.
	 * @return los destinatarios de dicho documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 18/09/2017
	 */
	public List<DTODocumentoDestinatarioEntity> buscarPorDocumento(DTODocumentoEntity documento);

	/**
	 * Método que borra los destinatarios de un documento de la base,
	 * 
	 * @param idDocumento
	 *            id del documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 30/10/2017
	 */
	public void borrarDestinatarios(Integer idDocumento);
	
	/**
	 * Método que a partir del id de un documento regresa la lista de personas
	 * que tiene como destinatarios.
	 * 
	 * @param idDocumento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/11/2017
	 */
	public List<DTOEstructuraAreasEntity> consutarPersonasDestinatarios(int idDocumento);
	
	/**
	 * Método que a partir de un objeto documento se obtienen los registros de
	 * la tabla "DOCUMENTOS_DESTINATARIOS" haciendo Join con las tablas
	 * "ESTRUCTURA_AREAS" y "C_AREAS"
	 * 
	 * @param idDocumento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/11/2017
	 */
	public List<DTODocumentoDestinatarioEntity> consutarDocumentosDestinatarios(DTODocumentoEntity documento);
	
	/**
	 * Método que a partir del id de un documento regresa la lista de personas
	 * que tiene como destinatarios.
	 * 
	 * @param idDocumento
	 * @return
	 *
	 * @author David Rodríguez Corral
	 * @since 07/11/2017
	 */
	public List<DTOEstructuraAreasEntity> consultarOficialias(int idDocumento);
	
	/**
	 * Método que a partir del id de un documento regresa la lista de las
	 * personas de oficialias registradas en el área del destinatario(Si la
	 * persona de oficialia está registrada en más de un área, regresa los
	 * registros de la persona por cada área).
	 * 
	 * @param idDocumento
	 * @return List<DTOBandejaEntradasOficialiaEntity> lista ed personas de
	 *         oficialia registradas en las área del remitente
	 *
	 * @author David Rodríguez Corral
	 * @since 07/11/2017
	 */
	public List<DTOBandejaEntradasOficialiaEntity> consultarPersonasOficialias(Integer idDocumento);
	
	/**
	 * Método que a partir del id de un documento regresa la lista de personas
	 * que tiene como destinatarios.
	 * 
	 * @param idDocumento
	 * @return List<DTODocumentoDestinatarioEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 07/12/2017
	 */
	public List<DTODocumentoDestinatarioEntity> consutarDestinatarios(Integer idDocumento);
	
	/**
	 * Método que a partir del id de un documento regresa la lista de areas que tiene como destinatarias.
	 * 
	 * @param idDocumento
	 * @return List<DTODocumentoDestinatarioEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 18/12/2017
	 */
	public List<DTONotificacionesOFEntity> consultarAreasDestinatarios(Integer idDocumento);
}
