package mx.ine.acuerdos.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.bo.BOReportesInterface;
import mx.ine.acuerdos.dto.DTOSeguimiento;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;



@Component("boReportes")
@Scope("prototype")
public class BOReportes implements BOReportesInterface, Serializable{
	private static final long serialVersionUID = 4947437014703424017L;
	Logger log = Logger.getLogger(BOReportes.class);
	
	@Override
	public byte[] generarJasperPDF(String jasperFile,HashMap<String,Object> params, List<DTOSeguimiento> datos) {
		try {
			
			
			JasperPrint jasperPrint = null;
			JRBeanCollectionDataSource jrbcds;
			try {
				if(datos.size()>1) {
					params.put("mensaje", "");
				}
			}catch(Exception e) {e.printStackTrace();}
			
			if(datos.size()==0 || datos==null) {
				
				//Se crea un objeto de tipo dto para que se pinte en la tabla con datos vacions
				DTOSeguimiento aux = new DTOSeguimiento();
				aux.setPunto("");
				aux.setFechaEmision("");
				aux.setEstatus("");
				aux.setArea("");
				aux.setTematica("");
				aux.setAnio("");
				aux.setNombreAcuerdo("");
				aux.setTextoPunto("");
				aux.setAreaSiglas("");
				aux.setTipoSesion("");
				aux.setDocumentoTipo("");
				//agregamos un mensaje para que  alertar que la busqueda filtrada no regresa nada
				datos.add(aux);
				jrbcds = new JRBeanCollectionDataSource(datos);
				
				
			}else {
				jrbcds = new JRBeanCollectionDataSource(datos);
			}
			
			
			
			JasperReport jasperReport = (JasperReport)  JRLoader.loadObjectFromFile(jasperFile);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrbcds);
			
			return  JasperExportManager.exportReportToPdf(jasperPrint);
		}catch(Exception e) {
			
			log.error("Error en BOReportes.generarJasperPDF ");
			e.printStackTrace();
			return null;
		}
			
	}

	
	/**
	 * METODO EXPORTAR EL JASPER A xlx
	 * @params String jasperfile
	 * @HasHMap params
	 * @List<DTOSeguimiento> datos
	 * @return byte[] salida
	 * **/
	@Override
	public byte[] obtenerReporteXls(String jasperFile,HashMap<String,Object> params, List<DTOSeguimiento> datos) {
		try {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRBeanCollectionDataSource jrbcds;
		try {
			if(datos.size()>1) {
				params.put("mensaje", "");
			}
		}catch(Exception e) {e.printStackTrace();}
		
		if(datos.size()==0 || datos==null) {
			
			//Se crea un objeto de tipo dto para que se pinte en la tabla con datos vacios
			DTOSeguimiento aux = new DTOSeguimiento();
			aux.setPunto("");
			aux.setFechaEmision("");
			aux.setEstatus("");
			aux.setArea("");
			aux.setTematica("");
			aux.setAnio("");
			aux.setNombreAcuerdo("");
			aux.setTextoPunto("");
			aux.setAreaSiglas("");
			aux.setTipoSesion("");
			aux.setDocumentoTipo("");
			//agregamos un mensaje para que  alertar que la busqueda filtrada no regresa nada
			datos.add(aux);
			jrbcds = new JRBeanCollectionDataSource(datos);
			
			
		}else {
			jrbcds = new JRBeanCollectionDataSource(datos);
		}
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrbcds);
		JRXlsxExporter exporterXLSXReporter = new JRXlsxExporter();
		exporterXLSXReporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporterXLSXReporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		
		
		
		SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(false);
        xlsReportConfiguration.setWhitePageBackground(false);
        exporterXLSXReporter.setConfiguration(xlsReportConfiguration);  
        exporterXLSXReporter.exportReport();
        
        
        
        
			return  out.toByteArray();	
		}catch(Exception e){
			log.error("Error en BOReportes.obtenerReporteXlx ");
			e.printStackTrace();
			return null;
		}
		
	}

}
