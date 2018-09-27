/**
 * @(#)DAOHBandejaERecibidosInterface.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOHBandejaERecibidosID;
import mx.ine.gestion.dto.helper.DTOHBandejaERecibidosHelper;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAOHBandejaERecibidosInterface extends
DAOGenericGestionInterface<DTOHBandejaERecibidosEntity, DTOHBandejaERecibidosID> {
	/**
	 * Método que inserta documentos recibidos a las personas recibidas como
	 * parametros
	 * 
	 * @param listaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 28/11/2017
	 */
	public void insertarHRecibidos(List<DTOEstructuraAreasEntity> listaPersonas,
			DTODocumentoEntity documento, DTOEstructuraAreasEntity usuario);


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
	public int consultarNumeroHRecibidos(
			DTOHBandejaERecibidosHelper filtroRecibidoHelper);

	/**
	 * Método
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
	public List<DTOHBandejaERecibidosEntity> obtenerListaRecibidosLazy(
			DTOHBandejaERecibidosHelper filtroRecibidoHelper,
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden,
			SortOrder tipoOrdenamiento, Map<String, Object> filtrosPorColumna);

}
