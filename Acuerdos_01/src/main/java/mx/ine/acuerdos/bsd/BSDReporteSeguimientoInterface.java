package mx.ine.acuerdos.bsd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.primefaces.model.StreamedContent;

import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOSeguimiento;
import mx.ine.acuerdos.dto.helper.form.HLPFormReporteSeguimiento;



public interface BSDReporteSeguimientoInterface extends Serializable {	
	public HLPFormReporteSeguimiento buscarAcuerdosSeguimiento();
	public List<DTOSeguimiento> listaAcuerdosSeguimiento(String anio);
	public ArrayList<String> listaAnios();
	public StreamedContent getReportePDF(List<DTOSeguimiento>lista, String tipoReporte, boolean filtros, HashMap<String, Object> params);
	public StreamedContent getReporteXLs(List<DTOSeguimiento> lista, String tipoReporte, HashMap<String,Object> params);
	public boolean filtros(Object value, Object filter, Locale locale);
	public List<DTOCTipoDocumento> getTipoSeguimiento(String anio);
	public List<DTOSeguimiento> listaAcuerdosSeguimiento(List<DTOCTipoDocumento> seguimientoList,String documento, String anio);
	public boolean filtrosAutoComplete(Object value, Object filter, Locale locale);
	public String irEtiqueraSeguimiento();
	public List<String> tipoSeguimiento(String anio);
	
}
