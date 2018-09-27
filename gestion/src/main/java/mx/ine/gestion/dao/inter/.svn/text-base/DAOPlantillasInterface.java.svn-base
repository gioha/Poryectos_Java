/**
 * @(#)DAOPlantillasInterface.java 05/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosPlantillaHelper;

import org.primefaces.model.SortOrder;

/**
 * Interfaz que contiene la firma de los métodos que harán consultas a varias tablas (varios 
 * cruces a diferentes tablas) para el módulo de configuración/plantillas.
 * 
 * @author Luis Urrutia
 * @since 05/12/2017
 */
public interface DAOPlantillasInterface extends DAOGenericGestionInterface<Integer, Integer> {

	/**
	 * Método para obtener los tipos de documentos
	 *
	 * @return List<DTOCTipoDocumentoEntity>: Lista que contiene los diferentes tipos de documentos.
	 *
	 * @author Luis Urrutia
	 * @since 05/12/2017
	 */
	public List<DTOCTipoDocumentoEntity> obtenerTipoDocumentos();

	/**
	 * Método para obtener los acrónimos
	 * 
	 * @param filtros: Objeto que contiene el idTipoDocumento, id y tipo del área para buscar los acrónimos
	 * 
	 * return List<DTOAcronimoEntity>: Lista que contiene los diferentes acrónimos
	 * 
	 * @author Luis Urrutia
	 * @since 06/12/2017
	 */
	public List<DTOAcronimoEntity> obtenerAcronimos(DTOFiltrosPlantillaHelper filtros);

	/**
	 * Método para obtener los regitros de las plantillas que pertenecen al usuario logueado
	 * 
	 * @param filtros : Objeto el cual contiene los filtros por los que se hara la búsqueda(en caso de que haya alguno)
	 * @param indicePrimerElemento : indica el índice del primer elemento que obtendra de la lista
	 * @param tamanioMaxPagina : tamanio máximo de elementos que traera para esta consulta
	 * @param campoOrden : Campo por el cual se hara el ordenamiento de los datos
	 * @param tipoOrdenamiento : Tipo de ordenamiento por el cual estaran los datos
	 * @param filtros : Mapa que contiene los filtros por los que se esta buscando en la/las columnas
	 * @return List<DTOPlantillaEntity>: Lista que contiene las plantillas
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 14/01/2018
	 */
	public List<DTOPlantillaEntity> consultarPlantillasLazy(DTOFiltrosPlantillaHelper filtros, 
			int indicePrimerElemento, int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna);

	/**
	 * Método para obtener el total de los registros que trae la consulta de arriba (SIN FILTROS, aquí no se aplican los filtros)
	 * 
	 * @param filtros : Objeto que contiene la información de los filtros que se pueden utilizar en la consulta.
	 * @return Integer : Total de registros encontrados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 15/01/2018
	 */
	public Integer consultarTotalPlantillasLazy(DTOFiltrosPlantillaHelper filtros);
}
