package mx.ine.acuerdos.as;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOSeguimiento;

import org.primefaces.model.StreamedContent;


public interface ASReporteSeguimientoInterface {

	public List<DTOSeguimiento> buscarAcuerdosSeguimiento(String anio, int tipoDocumento, Integer idArea);
	public ArrayList<String> listaAnios();
	public ArrayList<String> listaAnios(Integer idArea);
	//public StreamedContent getReporteExcel(List<DTOSeguimiento> lista);
	
	/**
	 * implementacion del metodo para gemerar el reporte4
	 * @param boolean tipo del repote true pdf false xls
	 * @List<STOSeguimiento> lista para poblar la tabla
	 * */
	public StreamedContent getReporte(List<DTOSeguimiento>lista, String tipoReporte, boolean tipo ,boolean filtro, HashMap<String,Object> helperFiltros);
	public List<DTOCTipoDocumento> getTipoSeguimiento(String anio);
}
