/**
 * @(#)ASBandejaEnviados.java 14/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface ASBandejaEnviadosInterface {

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
	 * @param dtoEstructuraAreasEntity
	 *            :Objeto del cual se obtiene el ID de la persona de la cual se
	 *            quiere obtener su lista de enviados
	 * 
	 * @return: Lista con los documentos enviados por el usuario.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public List<DTOEnviadosDocumentosEntity> consultarEnviados(DTOEnviadosDocumentosHelper helperEnviados) throws Exception;
	
	/**
	 * 
	 * @param enviado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void eliminarEnviados(DTOEnviadosDocumentosEntity enviado) throws Exception;
}
