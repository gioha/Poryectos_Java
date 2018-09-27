/**
 * @(#)DAODatosFirmaDocInterface.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTODatosFirmaDocID;

/**
 * Interface que contiene la firma de los métodos que consultaran
 * a la tabla DATOS_FIRMA_DOC del esquema gestion4.
 * 
 * @author David Rodríguez Corral 
 * @since 11/10/2017
 */
public interface DAODatosFirmaDocInterface extends DAOGenericGestionInterface<DTODatosFirmaDocEntity, DTODatosFirmaDocID>{
	
	/**
	 * Método que inserta en la bd los datos para realizar una firma
	 * 
	 * @param dtoFirmaDoc: Objeto que contiene ls datos para firmar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/10/2017
	 */
	public void insertar(DTODatosFirmaDocEntity dtoFirmaDoc);
	
	/**
	 * Método que consulta los datos para firmar un documento
	 * 
	 * @param dtoFirmaDoc: Objeto que contiene ls datos para firmar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/10/2017
	 */
	public DTODatosFirmaDocEntity consultar(Integer idDocumento, Integer idPersonaFirma);
	
	/**
	 * Método que consulta los datos para firmar un documento
	 * 
	 * @param idDocumento: Id del documento a consultar
	 * 
	 * @return DTODatosFirmaDocEntity obueto que contiene los datos para la firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/01/2017
	 */
	public DTODatosFirmaDocEntity consultarDatosPorDocumento(Integer idDocumento);
	
	/**
	 * Método que consulta los datos para firmar un documento
	 * 
	 * @param dtoFirmaDoc: Objeto que contiene ls datos para firmar.
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/10/2017
	 */
	public String consultar(Integer idDocumento);
	
	/**
	 * Método que elimina todas las firmas de un documento por que al menos un usuario regreso a remitente
	 * 
	 * @param idDocumento: id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/11/2017
	 */
	public void eliminarPorIDFirma(Integer idDocumento);
	
}
