package mx.ine.gestion.dao.inter;

import java.util.List;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;

/**
 * @(#)DAODocumentoAnexoInterface.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * DTODocumentoAnexoEntity.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
public interface DAODocumentoAnexoInterface extends DAOGenericGestionInterface<DTODocumentoAnexoEntity, DTODocumentoAnexoID> {

	/**
	 * Método para obtener la los anexos de un documento.
	 * 
	 * @param documento
	 *            documento del cual se quieren los anexos.
	 * @return los anexos de dicho documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 18/09/2017
	 */
	public List<DTODocumentoAnexoEntity> buscarPorDocumento(DTODocumentoEntity documento);

	/**
	 * Método que borra los anexos adjuntos de un documento de la base,
	 * 
	 * @param idDocumento
	 *            id del documento al que estan adjuntos.
	 *
	 * @author Sergio Ley Garcia
	 * @since 30/10/2017
	 */
	public void borrarAnexos(Integer idDocumento);
	
	/**
	 * Método que consulta todos los anexos de un documento.
	 * 
	 * @param idDocumento
	 *            ID del documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/11/2017
	 */
	public List<DTODocumentoAnexoEntity> consultarAnexos(int idDocumento);
	
	
}
