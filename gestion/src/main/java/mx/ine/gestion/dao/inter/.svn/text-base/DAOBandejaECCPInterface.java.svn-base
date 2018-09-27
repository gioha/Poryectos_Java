/**
 * @(#)DAOBandejaECCP.java 27/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPID;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOBandejaECCPInterface extends
		DAOGenericGestionInterface<DTOBandejaECCPEntity, DTOBandejaECCPID> {

	/**
	 * 
	 * @param listaPersonas
	 * @param documento
	 * @param usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public void insertarCCP(List<DTOEstructuraAreasEntity> listaPersonas, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario);
	
	/**
	 * 
	 * @param listaPersonas
	 * @param documento
	 * @param usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public void insertarCCP(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario);
	
	/**
	 * Método que regresa el número total de los registros de la tabla
	 * "BANDEJA_E_RECIBIDOS" que coinciden con el filtro recibido.
	 * 
	 * @param recibidos
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public int consultarNumeroCCP(DTOBandejaECCPHelper filtroCCPHelper);
	
	/**
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
	public List<DTOBandejaECCPEntity> obtenerListaCCPLazy(
			DTOBandejaECCPHelper filtroCCPHelper, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);
}
