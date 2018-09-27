/**
 * @(#)DAOConsultaLimitadaInterface.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;
import java.util.Map;

import mx.org.ine.servicios.dto.db.DTOEstado;

/**
 * Interfaz consultas 
 *
 * @autor INE
 * @since 12/07/2016
 */
public interface DAOConsultaLimitadaInterface extends DAOGenericGestionInterface<DTOEstado, Integer> {

	/**
	 * Método de consulta
	 * 
	 * @param magiaQry: operación
	 * @return List<Map<String,Object>> : resultado
	 *
	 * @autor INE
	 * @copy MAVO
	 * @since 12/07/2016
	 */
	public List<Map<String,Object>> haciendoMagia(String magiaQry);

	/**
	 * Método ejectura operación
	 * 
	 * @param magiaQry: operación
	 * @return int: bandera para decirnos si se ejecuto
	 * 
	 * @throws Exception: excepción en caso de que salga algo mal
	 *
	 * @autor INE
	 * @copy MAVO
	 * @since 12/07/2016
	 */
	public int usandoElPoder(String magiaQry) throws Exception;
}
