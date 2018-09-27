/**
 * @(#)DAOHBandejaEInfoInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOHBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEInfoID;
import mx.ine.gestion.dto.helper.DTOHBandejaEInfoHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOHBandejaEInfoInterface extends DAOGenericGestionInterface<DTOHBandejaEInfoEntity, DTOHBandejaEInfoID> {

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
	public int consultarNumeroInfo(DTOHBandejaEInfoHelper filtroInfoHelper);

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
	public List<DTOHBandejaEInfoEntity> obtenerListaInfoLazy(
			DTOHBandejaEInfoHelper filtroInfoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
}
