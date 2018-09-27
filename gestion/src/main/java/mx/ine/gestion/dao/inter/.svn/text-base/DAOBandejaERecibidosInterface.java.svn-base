/**
 * @(#)DAOBandejaERecibido.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOBandejaERecibidosInterface extends DAOGenericGestionInterface<DTOBandejaERecibidosEntity, DTOBandejaERecibidosID> {

	/**
	 * Método que inserta documentos recibidos a las personas recibidas como
	 * parametros
	 * 
	 * @param listaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 28/11/2017
	 */
	public void insertarRecibidos(List<DTOEstructuraAreasEntity> listaPersonas, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario);
	
	/**
	 * Método que inserta documentos recibidos a las personas recibidas como
	 * parametros
	 * 
	 * @param listaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 28/11/2017
	 */
	public void insertarRecibidos(DTOEstructuraAreasEntity dtoEstructuraAreasEntity , DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario, Integer idHistoricoRecep);

	/**
	 * Método que regresa la suma de los registros que coincidan con el
	 * ID_PERSONA y que se encuentren en las tablas "BANDEJA_E_ATENCION",
	 * "BANDEJA_CCP", "BANDEJA_E_INFO", "BANDEJA_E_RECIBIDOS".
	 * 
	 * @param persona
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/01/2018
	 */
	public int consultarDocumentosBandejaE(DTOEstructuraAreasEntity persona);

	/**
	 * Método que regresa el número total de los registros de la tabla
	 * "BANDEJA_E_RECIBIDOS" que coinciden con el filtro recibido. Este método
	 * es necesario para la consulta de la tabla Lazy de REcibidos.
	 * 
	 * @param recibidos
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public int consultarNumeroRecibidos(DTOBandejaERecibidosHelper filtroRecibidoHelper);

	/**
	 * Método que obtiene la lista Lazy de recibidos 
	 * 
	 * @param filtros
	 * @param indicePrimerElemento
	 * @param tamanioMaxPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosPorColumna
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public List<DTOBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOBandejaERecibidosHelper filtroRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);

}
