/**
 * @(#)DAOJerarquiaPersonasInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasID;

/**
 * Interfaz encargada de contener la firma de los métodos que obtendrán la
 * información de la base de datos para la tabla JERARQUIA_PERSONAS del esquema
 * de GESTION4 en base a consultas hechas por criteria y/o sqlquery.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 07/09/2017
 */
public interface DAOJerarquiaPersonasInterface
		extends
		DAOGenericGestionInterface<DTOJerarquiaPersonasEntity, DTOJerarquiaPersonasID> {

	/**
	 * Método que consulta en la bd los titulares.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene la lista de
	 *         los titulares.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultarTitulares();

	/**
	 * Método que hace la busqueda del titular de una área
	 * 
	 * @param tipoArea: identificador del tipo de área al que se le busca el titular
	 * @param idArea: identificador del área a la que se le busca el titular
	 * @return DTOJerarquiaPersonasEntity: Registro encontrado con la información del titular
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public DTOJerarquiaPersonasEntity consultarTitular(Integer tipoArea, Integer idArea);

	/**
	 * Método que consulta en la bd las relaciones para crear la estructura.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene las
	 *         relaciones de estructura filtradas por tipo de área y área.
	 * @param Integer
	 *            tipo: id del tipo de area para la búsqueda
	 * @param Integer
	 *            idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/09/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultarEstructuraPorArea(
			Integer tipoArea, Integer idArea);

	/**
	 * Método que revisa en la tabla "JERARQUIA_PERSONAS" todos los titulares de
	 * cada área. Estos registros los convierte en objetos
	 * DTOEstructuraAreasEntity.
	 * 
	 * @return List<DTOEstructuraAreasEntity>: Lista que contiene todos los
	 *         titulares de cada área.
	 * 
	 * @author Homero Villanueva Dominguez
	 * @since 05/09/17
	 */
	public List<DTOEstructuraAreasEntity> consultarEstructurasTitulares(String coincidencia);

	/**
	 * Método que consulta en la bd si ya existe la estructura para un área.
	 * 
	 * @return List<DTOJerarquiaPersonasEntity>: Lista que contiene máximo un
	 *         registro que indica que sí hay una estructura creada.
	 * @param Integer
	 *            tipo: id del tipo de area para la búsqueda
	 * @param Integer
	 *            idArea: id del área para la búsqueda
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/11/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultarExisteEstructura(Integer tipoArea, Integer idArea);
	
	/**
	 * Método que regresa una lista con los titulares de los destinatarios de un
	 * documento
	 * 
	 * @param dtoDocumentoEntity
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/11/2017
	 */
	public List<DTOEstructuraAreasEntity> consultarTitularesDestinatarios(DTODocumentoEntity dtoDocumentoEntity , int idNivel);

	/**
	 * Método que busca los titurales de las areas que contengan la cadena
	 * porArea en su nombre
	 * 
	 * @param porArea
	 *            cadena de caracteres con la que se hara la busqueda
	 * @return lista de los id de los titulares de las areas que coinciden con
	 *         la cadena en porArea
	 *
	 * @author Sergio Ley Garcia
	 * @since 11/12/2017
	 */
	public List<Integer> buscarArea(String porArea);
	
}
