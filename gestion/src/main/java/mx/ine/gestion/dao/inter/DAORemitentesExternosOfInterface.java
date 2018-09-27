/**
 * @(#)DAORemitentesExternosOfInterface.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfID;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de REMITENTES_EXTERNOS_OF del esquema GESTION4
 * 
 * @author David Rodríguez Corral
 * @since 19/01/2018
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
public interface DAORemitentesExternosOfInterface extends DAOGenericGestionInterface<DTORemitentesExternosOfEntity, DTORemitentesExternosOfID> {
	/**
	 * Método que consulta los remitentes externos de un area
	 * @param idArea: id del área a consultar los remitentes
	 * @param tipoArea: tipo del área a consultar los remitentes
	 * @return List<DTORemitentesExternosOfEntity> Lista que contiene los remitentes externos
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public List<DTORemitentesExternosOfEntity> consultarRemitente(Integer idArea, Integer tipoArea);

	/**
	 * Método que consulta el último id del remitente insertado
	 * @param idArea: id del área a consultar los remitentes
	 * @param tipoArea: tipo del área a consultar los remitentes
	 * @return List<DTORemitentesExternosOfEntity> Lista que contiene los remitentes externos
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public Integer consultarUltimoRemitente(Integer idArea, Integer tipoArea);

	/**
	 * Método que consulta los remitentes externos de un area filtrado por
	 * nombre del remitente, dependencia, idArea y tipoArea
	 * @param idArea: id del área a consultar los remitentes
	 * @param tipoArea: tipo del área a consultar los remitentes
	 * @return List<DTORemitentesExternosOfEntity> Lista que contiene los remitentes externos
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/01/2018
	 */
	public List<DTORemitentesExternosOfEntity> consultarRemitenteAreaNombreDependencia(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia);

	/**
	 * Método que recupera una lista de remitentes externos con base
	 * en el nombre y dependencia del remitente
	 * @param String nombreRemitente, String dependencia
	 * @return List<DTORemitentesExternosOfEntity>: Lista que contiene a los remitentes externos recuperados
	 * 
	 * @update José Miguel Ortiz
	 * @since 09/04/2018
	 */
	public List<DTORemitentesExternosOfEntity> consultarRemitenteNombreDependencia(String nombreRemitente, String dependencia);

	/**
	 * Método que consulta a un remitente externo con base en su llave primaria.
	 * @return DTORemitentesExternosOfEntity: Remitente externo recuperado
	 * @author José Miguel Ortiz
	 * @param Integer idRemitente, Integer idArea, Integer tipoArea
	 * @since 03/05/2018
	 */
	public DTORemitentesExternosOfEntity consultarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea);

	/**
	 * Método que recupera a un remitente externo con base en una coincidencia exacta.
	 * @return DTORemitentesExternosOfEntity: Remitente externo recuperado
	 * @author José Miguel Ortiz
	 * @param Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia
	 * @since 03/05/2018
	 */
	public DTORemitentesExternosOfEntity consultarRemitenteAreaNombreDependenciaExacto(Integer idArea, Integer tipoArea, String nombreRemitente, String dependencia);


}
