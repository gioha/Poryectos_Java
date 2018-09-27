/**
 * @(#)DAOComentariosDocumentoInterface.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Interface que contiene la firma de los métodos que consultarán a la tabla
 * FIRMA_DOCUMENTOS del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
public interface DAOComentariosDocumentoInterface
		extends
		DAOGenericGestionInterface<DTOComentariosDocumentoEntity, DTOComentariosDocumentoID> {

	/**
	 * Método que inserta un comentario para un documento en la tabla de
	 * COMENTARIOS_DOCUMENTO
	 * 
	 * @param dtoComentario
	 *            : lista de comentarios a insertar en la bd de los documentos
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/09/2017
	 */
	public void guardarComentario(DTOComentariosDocumentoEntity dtoComentario, DTOEstructuraAreasEntity persona);

	/**
	 * 
	 * @param dtoComentario
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/04/2018
	 */
	public Integer guardarComentarioTurnado(DTOComentariosDocumentoEntity dtoComentario, DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que regresa todos los comentarios que sean del documento recibido y coincidan con el estatus. 
	 * 
	 * @param documento
	 * @param estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity documento, int estatus) throws Exception;

//	/**
//	 * Método que regresa todos los comentarios que tiene un documento y ya han
//	 * sido leidos.
//	 * 
//	 * @param dtoDocumentoEntity
//	 *            El documento del que se quieren los comentarios.
//	 * @return Regresa una lista de Comentarios
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 09/10/2017
//	 */
//	public List<DTOComentariosDocumentoEntity> consultarComentariosLeidos(
//			DTODocumentoEntity dtoDocumentoEntity);
//
//	/**
//	 * Método que regresa todos los comentarios que tiene un documento y no han
//	 * sido leidos.
//	 * 
//	 * @param dtoDocumentoEntity
//	 *            El documento del que se quieren los comentarios.
//	 * @return Regresa una lista de Comentarios
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 09/10/2017
//	 */
//	public List<DTOComentariosDocumentoEntity> consultarComentariosNoLeidos(
//			DTODocumentoEntity dtoDocumentoEntity);
	

	/**
	 * Método que regresa todos los comentarios
	 * 
	 * @param dtoDocumentoEntity
	 *            El documento del que se quieren los comentarios.
	 * @return Regresa una lista de Comentarios
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/01/2018
	 */
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception;
	
	/**
	 * Método cambia el estatus a 1 para ser visto el comentario en la bandeja.
	 * 
	 * @param usuarioComento
	 *            Usuario que comentó
	 * @param idDocumento El id del documento
	 *
	 * @author David Rodríguez Corral
	 * @since 09/11/2017
	 */
	public void activarEstatus(String usuarioComento, Integer idDocumento);

}
