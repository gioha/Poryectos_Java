/**
 * @(#)DAOEdicionesDocumento.java 26/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoID;


/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOEdicionesDocumentoInterface extends DAOGenericGestionInterface<DTOEdicionesDocumentoEntity, DTOEdicionesDocumentoID>{

	/**
	 * Método que regresa la lista de ediciones del documento que tiene el ID
	 * recibido.
	 * 
	 * @param idDocumento
	 *            : ID del documento del cual se quieren visualizar la lista de
	 *            ediciones.
	 * 
	 * @param estatus
	 *            : Parametro que determina el tipo de registros que se
	 *            regresaran. Sí se recibe 0 muestra los registros que no han
	 *            sido leidos. Sí se recibe 1 muestra los registros que han sido
	 *            leidos, en cualquier otro caso se mostrarán los mensajes no
	 *            leidos.
	 * 
	 * @return: Regresa una lista con los comentarios de un determinado
	 *          documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/10/2017
	 */
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdiciones(
			int idDocumento, int estatus);

	/**
	 * Método que inserta un registro en la tabla de ediciones_documento indicando en que ocasiones fueron modificados
	 * los documentos
	 * 
	 * @param dtoEdiciones: Objeto a insertar en la tabla ediciones_documento.
	 *
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void insertar(DTOEdicionesDocumentoEntity dtoEdiciones);

	/*
	 * 
	 */
	public Integer obtenerIdEdicion(Integer idDocumento);
	
}
