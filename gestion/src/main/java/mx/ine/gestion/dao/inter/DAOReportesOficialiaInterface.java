/**
 * @(#)DAOReportesOficialiaInterface.java 26/03/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;

/**
 * /**
 * Interfaz DAO que contiene la firma de los métodos utilizados en la vista de Reportes de Oficialía
 * que no pueden ser DTO's extraídos desde una tabla en BD (sino de varias tablas)
 * 
 * @author José Miguel Ortiz
 * @since 26/03/2018
 */
public interface DAOReportesOficialiaInterface extends DAOGenericGestionInterface<Integer, Integer> {

	/**
	 * Método que recupera cada uno de los documentos de acuedo a los filtros capturados y/o
	 * seleccionados por el usuario en la vista de Reportes de Oficialía.
	 * @param DTOReportesOficialiaHelper filtrosReportesOf
	 * @return List<DTOReportesOficialia>@author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<DTOReportesOficialia> consultarReporteOficialia(DTOReportesOficialiaHelper filtrosReportesOf);
}
