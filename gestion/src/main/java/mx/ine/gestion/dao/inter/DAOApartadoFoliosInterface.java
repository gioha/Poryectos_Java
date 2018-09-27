/**
 * @(#)DAOApartadoFoliosInterface.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOApartadoNumDocEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosApartadoFolioHelper;

/**
 * Interfaz que contiene la firma de los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de apartado de folios.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
public interface DAOApartadoFoliosInterface extends DAOGenericGestionInterface<Integer, Integer> {

	/**
	 * Método que obtiene los tipos de documentos disponibles para el apartado de folios.
	 * 
	 * @param idArea: identificador del área por el cual se filtra
	 * @param tipoArea: tipo de área por el cual se filtra
	 * @return List<DTOCTipoDocumentoEntity>: información obtenida.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/11/2017
	 */
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentoParaApartado(Integer idArea, Integer tipoArea);

	/**
	 * Método que obtiene las áreas que se usan para el apartado de folios
	 * IMPORTANTE: se trae las áreas que ya estan utilizadas en ACRONIMOS.
	 * 
	 * @param tipoArea: identificador del tipo de área que se utiliza para filtrar.
	 * @param idEntidad: identificador de la entidad que se utiliza para filtrar.
	 * @return List<DTOCAreaEntity>: áreas encontradas que arroja la consulta.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 06/12/2017
	 */
	public List<DTOCAreaEntity> consultarAreasParaApartado(Integer tipoArea, Integer idEntidad);
	
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
	public List<DTOApartadoNumDocEntity> consultarFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros, 
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
	public Integer consultarTotalFoliosApartadosLazy(DTOFiltrosApartadoFolioHelper filtros);
}
