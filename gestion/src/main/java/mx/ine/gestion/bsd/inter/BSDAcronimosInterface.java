/**
 * @(#)BSDAcronimosInterface.java 12/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Interface encargada de administrar el o los BSD del módulo de 
 * Acrónimos.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 01/11/2017
 * @update José Miguel Ortiz
 * @since 12/04/2018
 */
public interface BSDAcronimosInterface {
	
	/**
	 * Método que consulta en el cátalogo todas las áreas por el tipo de área y la entidad.
	 * 
	 * @return List<DTOCAreas>: Lista que las relaciones de estructura con todas las áreas.
	 * @param int tipoArea: id del tipo de área
	 * @param int idEntidad id del estado. Si es cero no pertenece a algún estado.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public List<DTOCAreaEntity> consultarAreas(int tipoArea, int idEntidad);

	/**
	 * Método para consultar los tipos de documento
	 * 
	 * @return lista List<DTOCTiposDocumentos> con los tipos de área
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/11/2017
	 */
	public List<DTOCTipoDocumentoEntity> consultarTiposDocumentos() ;
	
	/**
	 * Método para guardar un acrónimo nuevo o modificar uno existente
	 * 
	 * @param acronimo: DTOAcronimoEntity con toda la información del acrónimo que se guardará
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 12/11/2017
	 */
	public void guardarAcronimo(DTOAcronimoEntity acronimo) throws Exception;
	
	/**
	 * Método para eliminar un acrónimo
	 * 
	 * @param acronimo: DTOAcronimoEntity con toda la información del acrónimo que se eliminará
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
	public List<DTOCTipoAreaEntity> consultarTiposAreas() ;
	
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
	 * Método para consultar los tipos de documento que sí tengan documentos;
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
