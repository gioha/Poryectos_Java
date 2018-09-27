/**
 * @(#)DAOEnviadosDocumentosInterface.java 11/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosID;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;

/**
 * Interface que contiene la firma de los métodos que consultaran a la tabla
 * ENVIADOS_DOCUMENTOS del esquema gestion4.
 * 
 * @author David Rodríguez Corral
 * @since 30/10/2017
 */
public interface DAOEnviadosDocumentosInterface extends DAOGenericGestionInterface<DTOEnviadosDocumentosEntity, DTOEnviadosDocumentosID> {

	/**
	 * Método que regresa la lista de Documentos enviados de la tabla
	 * "ENVIADOS_DOCUMENTOS" se hace un Join con la tabla "DOCUMENTOS" de la
	 * persona que inició sesión.
	 * 
	 * @param columnaOrdenamiento
	 *            : Por default el campo de ordenamiento es el nombre, pero si
	 *            se recibe la cadena "fecha" los registros se ordenan por la
	 *            fecha de Enviados.
	 * 
	 * @param tipoOrdenamiento
	 *            : Si recibe true los registros se ordenan de forma ascendente,
	 *            en caso de ser false los registros se ordenan de forma
	 *            descendente.
	 * 
	 * @param idPersona
	 *            : Es el ID de la persona de la cual se desea obtener la lista
	 *            de enviados
	 * 
	 * @return: Lista con los documentos enviados por el usuario.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public List<DTOEnviadosDocumentosEntity> consultarEnviados(DTOEnviadosDocumentosHelper helperEnviados);

	/**
	 * Método que regresa el número total de Documentos enviados del usuario que
	 * inició sesión.
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public int consultarNumeroEnviados(DTOEstructuraAreasEntity dtoEstructuraAreasEntity);

	/**
	 * Método que inserta un registro en la tabla "ENVIADOS_DOCUMENTOS" a partir
	 * de un objeto borrador y una persona.
	 * 
	 * @param borrador
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 28/11/2017
	 */
	public void insertarEnviado(DTOBorradorDocumentosEntity borrador, DTOEstructuraAreasEntity persona);

}
