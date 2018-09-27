/**
 * @(#)BSDConsultaLimitadaInterface.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.helper.DTOResultadoConsultaHelper;

/**
 * Interfaz con la firma de los métodos
 *
 * @autor INE
 * @since 12/07/2016
 */
public interface BSDConsultaLimitadaInterface {

	/**
	 * Método para ejecutar acciones
	 * 
	 * @param query: contiene el query que se ejecuta
	 * @return List<HLPResultadoConsulta>: resultado
	 *
	 * @autor INE
	 * @copy MAVO
	 * @since 12/07/2016
	 */
	public List<DTOResultadoConsultaHelper> ejecutaAccion(String query);
}
