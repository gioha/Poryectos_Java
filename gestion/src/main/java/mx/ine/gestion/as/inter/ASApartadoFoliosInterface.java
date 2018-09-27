/**
 * @(#)ASApartadoFoliosInterface.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;

import org.primefaces.model.SortOrder;

/**
 * Interfaz que contiene la firma de los métodos que hacen las llamadas a los DAO´s que se utilizan en el módulo de Apartado de Folios
 * 
 * @author Roberto Shirásago Domínguez
 * @since 29/11/2017
 */
public interface ASApartadoFoliosInterface {

	/**
	 * Método el cual se implementa para obtener la lista de datos de los folios apartados
	 * que se presentara dentro del data table de manera lazy.
	 * 
	 * @param dtoFiltros : Objecto el cual contiene los filtros por los que se hara la búsqueda(en caso de que haya alguno)
	 * @param indicePrimerElemento : indica el índice del primer elemento que obtendra de la lista
	 * @param tamanioMaxPagina : tamanio máximo de elementos que traera para esta consulta
	 * @param campoOrden : Campo por el cual se hara el ordenamiento de los datos
	 * @param tipoOrdenamiento : Tipo de ordenamiento por el cual estaran los datos
	 * @param filtros : Mapa que contiene los filtros por los que se esta buscando en la/las columnas
	 * @return List<Object> : Lista de objetos obtenida
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 23/03/2014
	 */
	public List<DTOApartadoNumDocEntity> obtenerListaDeFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna);

	/**
	 * Método que obtiene el total de folios apartados para un datatable que consulta de manera lazy.
	 *
	 * @param filtros: objeto que contiene la información con la que se filtra la consilta
	 * @return Integer: total de registros encontrados bajo esos criterios.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public Integer obtenerTotalFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros);
	
	/**
	 * Método que obtiene la información de los tipos de área que se utilizan en los filtros.
	 * 
	 * @param filtros: Objeto al que se le ingresaran los datos para los filtros.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void obtenerInfoTipoAreas(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método que obtiene las áreas que se usan en los filtros para el apartado de folios
	 * IMPORTANTE: se trae las áreas que ya estan utilizadas en ACRONIMOS.
	 * 
	 * @param filtros: Objeto al que se le ingresaran los datos para los filtros.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public void obtenerInfoAreas(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método que obtiene la información de los tipos de documentos que se utilizan en los filtros.
	 * 
	 * @param filtros: Objeto al que se le ingresan los datos para los filtros.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public void obtenerInfoTipoDocumentos(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método para obtener las entidades de los tipos de documentos
	 * 
	 * @param filtros: Objeto al que se le ingresan los datos para los filtros y se utilizan en la consulta para acortar los resultados.
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 09/01/2018
	 */
	public void obtenerInfoEntidades(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método que obtiene la información de los acronimos que se utilizan en los filtros.
	 * 
	 * @param filtros: Objeto al que se le ingresan los datos para los filtros (como las listas que se despliegan en pantalla)
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 05/12/2017
	 */
	public void obtenerInfoActonimos(DTOFiltrosApartadoFolioHelper filtros);

	/**
	 * Método que captura el apartado de folios en base a los datos que el usuario ingreso en la pantalla.
	 *  
	 * @param filtrosSeleccionados: Objeto que contiene la información de los filtros que selecciono el usuario para la captura.
	 * @throws Exception: Manda una excepción en caso de haber algun error al insertar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public void capturarApartadoFolios(DTOFiltrosApartadoFolioHelper filtrosSeleccionados) throws Exception;

	/**
	 * Método que actualiza el apartado de folios deshabilitando el folio apartado para que no sea utilizado.
	 * 
	 * @param folioApartado: Objeto que contiene el registro que se actualizara
	 * @throws Exception: Manda una excepción en caso de haber algun error al actualizar
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/12/2017
	 */
	public void deshabilitarFoliosApartados(DTOApartadoNumDocEntity folioApartado) throws Exception;
}
