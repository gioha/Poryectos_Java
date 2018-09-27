/**
 * @(#)DAOCAreasInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaID;

/**
 * Interface que contiene la firma de los métodos que consultaran a la tabla
 * C_AREAS del esquema GESTION4.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public interface DAOCAreasInterface extends DAOGenericGestionInterface<DTOCAreaEntity, DTOCAreaID> {

	/**
	 * Método para buscar una área por nombre (se valida que no sea más de 1)
	 * 
	 * @param area:  Nombre del area a buscar
	 * @return lista de areas que cumplen los criterios de busqueda
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/09/2017
	 */
	public DTOCAreaEntity buscarAreasPorNombre(String area);

	/**
	 * Método que consulta en el cátalogo de áreas las áreas que no tienen organigrama todavía
	 * 
	 * @return List<DTOCAreas>: Lista que las relaciones de estructura con las áreas que no tienen organigrama.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public List<DTOCAreaEntity> consultarAreasSinOrganigrama(int tipoArea, int idEntidad) ;

	/**
	 * Método que consulta en el cátalogo de áreas las áreas que ya tienen organigrama
	 * 
	 * @return List<DTOCAreas>: Lista que las relaciones de estructura con las áreas que ya tienen organigrama.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/09/2017
	 */
	public List<DTOCAreaEntity> consultarAreasConOrganigrama(int tipoArea, int idEntidad);

	/**
	 * Método que obtiene las áreas capturadas en gestión
	 * (NO TODAS, si no se ha hecho el organigrama del área no la trae) 
	 * 
	 * @param nombreArea: nombre del área por la que se requiere filtrar
	 * @return List<DTOCAreaEntity> : lista de áreas encontradas
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public List<DTOCAreaEntity> consultarAreasDestinatariasConOrganigrama(String nombreArea);

	
	/**
	 * Método que consulta en el cátalogo de todas las áreas.
	 * 
	 * @return List<DTOCAreas>: Lista que las relaciones de estructura con todas las áreas.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/10/2017
	 */
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad);
	
	/**
	 * Método que consulta en la bd las entidades que ya tienen estructuras creadas.
	 * 
	 * @return List<DTOCAreas>: Lista que tiene los ids de entidades que ya tienen estructura como un objeto DTOAreas.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/10/2017
	 */
	public List<DTOCAreaEntity> consultarEntidadesConOrganigrama() throws Exception;
	
	/**
	 * Método que consulta en la bd las entidades que no tienen estructuras creadas.
	 * 
	 * @return List<DTOCAreas>: Lista que tiene los ids de entidades que no tienen estructura como un objeto DTOAreas.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 25/10/2017
	 */
	public List<DTOCAreaEntity> consultarEntidadesSinOrganigrama() throws Exception;

	/**
	 * Método encargado de recuperar una lista de áreas asociadas a uno o varios acrónimos.
	 * @return List<DTOCAreaEntity>: Lista de áreas recuperadas
	 * 
	 * @update José Miguel Ortiz
	 * @since 12/04/2018
	 */
	public List<DTOCAreaEntity> consultarAreasAcronimos();
	
}
