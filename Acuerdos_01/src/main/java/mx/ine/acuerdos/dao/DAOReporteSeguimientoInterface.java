/**
 * Clase DAO pare el reporte de seguimiento
 * @author Angel Omar Medel Hernández
 * @since 26/09/2016
 * @version 1.0
 * @copyright INE
 */
package mx.ine.acuerdos.dao;

import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOSeguimiento;

public interface DAOReporteSeguimientoInterface extends DAOGenericInterface<DTOSeguimiento,Integer> {
	public List<DTOSeguimiento> buscarAcuerdosSeguimiento(String anio, int documento, Integer idArea);

	public ArrayList<String> listaAnios();
	public ArrayList<String> listaAnios(Integer idArea);

	List<DTOCTipoDocumento> tipoSeguimientoList(String date);
}
