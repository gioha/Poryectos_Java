/**
 * @(#)ASConsultaLimitadaInterface.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.helper.DTOResultadoConsultaHelper;

/**
 * Interfaz de consulta
 *
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
public interface ASConsultaLimitadaInterface {

	/**
	 * Método para ejecutar consulta
	 * 
	 * @param consulta: consulta hecha
	 * @return List<HLPResultadoConsulta>: resultado
	 *
	 * @autor INE
	 * @copy MAVO
	 * @since 12/07/2016
	 */
	public List<DTOResultadoConsultaHelper> ejecutaConsulta(String consulta);

	/**
	 * Método para ejecutar una operación
	 * 
	 * @param operacion: operación hecha
	 * @return List<HLPResultadoConsulta>: resultado
	 *
	 * @autor INE
	 * @copy MAVO
	 * @since 12/07/2016
	 */
	public List<DTOResultadoConsultaHelper> ejecutaOperacion(String operacion);
}
