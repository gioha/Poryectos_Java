package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOAcronimoID;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;

import org.primefaces.model.SortOrder;

/**
 * @(#)DAOAcronimoInterface.java 06/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de dao correspondiente al dao para la entidad de
 * Acronimo.
 * 
 * @author Sergio Ley Garcia
 * @since 06/09/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public interface DAOAcronimoInterface extends DAOGenericGestionInterface<DTOAcronimoEntity, DTOAcronimoID> {

	/**
	 * Método que busca los acrónimos disponibles para el area del usuario.
	 * 
	 * @param tipoArea : contiene el tipo de área con la que se busca el acronimo
	 * @param idArea : contiene el identificador del área con la que se busca el acronimo
	 * @param tipoDocumento : contiene el tipo de documento con el que se hace la búsqueda
	 * @return lista de acrónimos disponibles para el area
	 *
	 * @author Sergio Ley Garcia
	 * @update Roberto Shirásago Domínguez
	 * @since 06/09/2017
	 */
	public List<DTOAcronimoEntity> consultarAcronimosXAreaXTipoDocumento(Integer tipoArea, Integer idArea, Integer tipoDocumento);
	
	/**
	 * Método el cual se implementa para obtener la lista de datos de los acronimos
	 * que se presentara dentro del data table de manera lazy.
	 * 
	 * @param filtros : Objecto el cual contiene los filtros por los que se hara la búsqueda(en caso de que haya alguno)
	 * @param indicePrimerElemento : indica el índice del primer elemento que obtendra de la lista
	 * @param tamanioMaxPagina : tamanio máximo de elementos que traera para esta consulta
	 * @param campoOrden : Campo por el cual se hara el ordenamiento de los datos
	 * @param tipoOrdenamiento : Tipo de ordenamiento por el cual estaran los datos
	 * @param filtrosColumna : Mapa que contiene los filtros por los que se esta buscando en la/las columnas
	 * @return List<Object> : Lista de objetos obtenida
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since 10/01/2018
	 */
	public List<DTOAcronimoEntity> obtenerListaDeAcronimosLazy(DTOFiltrosAcronimosHelper filtros, int indicePrimerElemento,
			int tamanioMaxPagina, String campoOrden, SortOrder tipoOrdenamiento, Map<String, Object> filtrosColumna);
	
	/**
	 * Método para consultar el total de registros de acronimos que se utiliza en la búsqueda anterior.
	 * 
	 * @return Integer: total de registros encontrados.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/01/2018
	 */
	public Integer obtenerTotalDeAcronimosLazy();
	
	/**
	 * Método que obtiene el último id del acrónimo necesario
	 * 
	 * @param acronimo
	 *            : DTOAcronimoEntity con la información del acrónimo
	 * 
	 * @return Integer: id del último acrónimo insertado necesario
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public Integer obtenerIdAcronimo(DTOAcronimoEntity acronimo);

	public List<DTOAcronimoEntity> consultarAcronimoEnUso(DTOAcronimoEntity acronimo) throws Exception;

	/**
	 * Método encargado de recuperar una lista de acrónimos con base en el área del
	 * usuario logueado o bien, con base en el área seleccionada por dicho usuario.
	 * @return List<DTOAcronimoEntity>: Lista de acrónimos recuperados
	 * @param Integer idArea
	 * 
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public List<DTOAcronimoEntity> consultarAcronimosPorArea(Integer idArea);

}
