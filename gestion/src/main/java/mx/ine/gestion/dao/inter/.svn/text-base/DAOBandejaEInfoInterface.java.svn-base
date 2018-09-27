/**
 * @(#)DAOBandejaEInfo.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOBandejaEInfoInterface extends
		DAOGenericGestionInterface<DTOBandejaEInfoEntity, DTOBandejaEInfoID> {
	
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
	public int consultarNumeroInfo(DTOBandejaEInfoHelper filtroInfoHelper);

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
	public List<DTOBandejaEInfoEntity> obtenerListaInfoLazy(DTOBandejaEInfoHelper filtroInfoHelper, int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
	
	public DTOBandejaEInfoEntity obtenerDocumnetoInfo(DTODocumentoEntity documento, DTOEstructuraAreasEntity persona);
}