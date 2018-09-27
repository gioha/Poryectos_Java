/**
 * @(#)DAOHBandejaEAtencionInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOHBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOHBandejaEAtencionId;
import mx.ine.gestion.dto.helper.DTOHBandejaEAtencionHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOHBandejaEAtencionInterface extends DAOGenericGestionInterface<DTOHBandejaEAtencionEntity, DTOHBandejaEAtencionId> {

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
	public int consultarNumeroAtencion(DTOHBandejaEAtencionHelper filtroAtencionHelper);

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
	public List<DTOHBandejaEAtencionEntity> obtenerListaAtencionLazy(
			DTOHBandejaEAtencionHelper filtroAtencionHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
}
