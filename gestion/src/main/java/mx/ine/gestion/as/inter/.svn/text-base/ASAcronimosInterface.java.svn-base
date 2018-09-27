/**
 * @(#)ASAcronimosInterface.java 12/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOFiltrosAcronimosHelper;


/**
 * Interface encargada de administrar el o los DAO del módulo de Acronimos.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 01/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public interface ASAcronimosInterface {

	/**
	 * Método que consulta en el cátalogo todas las áreas por el tipo de área y
	 * la entidad.
	 * 
	 * @return List<DTOCArea>: Lista que las relaciones de estructura con todas
	 *         las áreas. S
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún
	 *        estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad);
	
	/**
	 * Método el cual se implementa para obtener la lista de datos de los acronimos
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
	 * @since 10/01/2018
	 */
	public List<DTOAcronimoEntity> obtenerListaDeDatos(DTOFiltrosAcronimosHelper filtros, int indicePrimerElemento,
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
	 * Método para consultar los tipos de documento
	 * 
	 * @return lista List<DTOCTiposDocumentos> con los tipos de área
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/11/2017
	 */
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos();

	/**
	 * Método para guardar un acrónimo nuevo o modificar uno existente
	 * 
	 * @param acronimo
	 *            : DTOAcronimoEntity con toda la información del acrónimo que
	 *            se guardará
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void guardarAcronimo(DTOAcronimoEntity acronimo) throws Exception;
	
	/**
	 * Método para eliminar un acrónimo
	 * 
	 * @param acronimo
	 *            : DTOAcronimoEntity con toda la información del acrónimo que
	 *            se eliminará
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void eliminarAcronimo(DTOAcronimoEntity acronimo) throws Exception;
		
	/**
	 * Método para consultor los tipos de área disponibles
	 * 
	 * @return List<DTOCTiposAreaEntity>: lista con los tipos de área 
	 * 
 	 * @author Pável Alexei Martínez Regalado
	 * @since 01/12/2017
	 */
	public List<DTOCTipoAreaEntity> consultarTiposAreas();
	
	/**
	 * TODO
	 * @param acronimo
	 * @return
	 * @throws Exception
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/01/2018
	 */
	public boolean consultarAcronimoEnUso(DTOAcronimoEntity acronimo);
	
	/**
	 * Método para consultar los tipos de área que sí tengan documentos;
	 * 
	 * @return List<DTOCTipoAreaEntity> lista con los tipos de área con documentos
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/12/2017
	 */
	public List<DTOCTipoAreaEntity> consultarTipoAreaConDocumento();
	
	/**
	 * Método para consultar los tipos de área que sí tengan documentos;
	 * 
	 * @return List<DTOCTipoDocumentoEntity> lista con los tipos de documento con documentos
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/12/2017
	 */
	public List<DTOCTipoDocumentoEntity> consultarTipoDocumentoConDocumento();
	
	/**
	 * Método para consultar los estados
	 *  
	 * @return List<DTOEstadosEntity> lista con los estados
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/12/2017
	 */
	public List<DTOEstadosEntity> consultarEstados();

	/**
	 * Método encargado de recuperar una lista de áreas asociadas a uno o varios acrónimos.
	 * @return List<DTOCAreaEntity>: Lista de áreas recuperadas
	 * 
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public List<DTOCAreaEntity> recuperarAreasAcronimos();

	/**
	 * Método encargado de recuperar una lista de acrónimos con base en el área del
	 * usuario logueado o bien, con base en el área seleccionada por dicho usuario.
	 * @return List<DTOAcronimoEntity>: Lista de acrónimos recuperados
	 * @param Integer idArea
	 * 
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public List<DTOAcronimoEntity> recuperarAcronimosPorArea(Integer idArea);

}
