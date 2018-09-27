package mx.ine.acuerdos.bo;

import java.util.HashMap;
import java.util.List;

import mx.ine.acuerdos.dto.DTOSeguimiento;

/**
 *
 * @author Angel Omar Medel Hernandez
 * @version 1.0
 * @since 13/11/2017 
 */
public interface BOReportesInterface {
	
	
	/**
	 * Metodo dado los valores de entrada refresa el archivo en formato excel en arreglo de bytes[]
	 * 
	 *  @params
	 *  @return byte[]
	 * */	
	public byte[] generarJasperPDF(String jasperFile, HashMap<String, Object> params, List<DTOSeguimiento> lista);
	
	public byte[] obtenerReporteXls(String jasperFile, HashMap<String, Object> params, List<DTOSeguimiento> lista);
}
