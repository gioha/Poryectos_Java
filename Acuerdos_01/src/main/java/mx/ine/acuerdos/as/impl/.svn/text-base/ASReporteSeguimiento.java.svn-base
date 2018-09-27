package mx.ine.acuerdos.as.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mx.ine.acuerdos.as.ASReporteSeguimientoInterface;
import mx.ine.acuerdos.dao.DAOReporteSeguimientoInterface;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOSeguimiento;
import mx.ine.acuerdos.pao.PAOReportesInterface;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Repository("asReporteSeguimiento")
public class ASReporteSeguimiento implements ASReporteSeguimientoInterface, Serializable{
	
	private static final long serialVersionUID = 2385159506327320718L;
	Logger log = Logger.getLogger(ASReporteSeguimiento.class);
	@Autowired
	@Qualifier("daoReporteSeguimiento")
	private transient DAOReporteSeguimientoInterface daoReporteSeguimiento;
	
	@Autowired
	@Qualifier("paoReportes")
	private transient PAOReportesInterface paoReportes;
	
	private StreamedContent file;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public List<DTOSeguimiento> buscarAcuerdosSeguimiento(String anio, int tipoDocumento, Integer idArea) {		
		return daoReporteSeguimiento.buscarAcuerdosSeguimiento(anio, tipoDocumento, idArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public ArrayList<String> listaAnios() {
		
		return daoReporteSeguimiento.listaAnios();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public ArrayList<String> listaAnios(Integer idArea) {
		
		return daoReporteSeguimiento.listaAnios(idArea);
	}
	
	/*Override
	public StreamedContent getReporteExcel(List<DTOSeguimiento> lista) {
		byte[] salida= null;
		 StreamedContent file;
				try {
					InputStream stream = new ByteArrayInputStream(salida);
				//	file = new DefaultStreamedContent(stream, "application/xls",nombreArchivo(numeroResolucion, tipoSancion));
				}catch(Exception e) {
					e.printStackTrace();
				}
				return null;
	}*/
	
	
	/***
	 * METODO PARA LA GENERACIÓN DEL REPORTE DE SEGUIMIENTO EN PDF
	 * @param boolean tipo true pdf false xlsx
	 * @return StreamedContent
	 * */
	@Override
	public StreamedContent  getReporte(List<DTOSeguimiento>lista, String tipoReporte, boolean tipo, boolean filtro, HashMap<String,Object> params) {
		//vamos por el usuario que esta loggeado.

		  
				
		byte[] salida = paoReportes.getReporteSeguimientoCG(lista, tipo, filtro, params);
		try {
			InputStream stream = new ByteArrayInputStream(salida);
			
			if(tipo) {
				file = new DefaultStreamedContent(stream, "application/pdf", nombreArchivo(tipoReporte,tipo));
			}
				file = new DefaultStreamedContent(stream, "application/xlsx", nombreArchivo(tipoReporte,tipo));
			return file;
			
		}catch(Exception e) {
			log.error("Error en ASReporteSeguimiento.getReporteSeguimiento ");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/***
	 * METODO PARA  NOMBRAR EL ARCHIVO
	 * QUE SE GENERA CON JASPER}
	 * @params String tipoReporte
	 * @params boolean extencion true= pdf, false = xlsx
	 * @return String  nombre
	 * **/
	
	private String nombreArchivo(String tipoReporte, boolean extension) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
		if(extension) {
			return tipoReporte+"_"+d.format(cal.getTime())+".pdf";
		}
			return tipoReporte+"_"+d.format(cal.getTime())+".xlsx";
			
		
	}
	
	/**
	 * MÉTODO PARA IR POR EL TIPO DE SEGUIMIENTO, ACUERDO O RESOLUCION
	 * params String anio
	 * @return List<String>
	 * **/
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public List<DTOCTipoDocumento> getTipoSeguimiento(String anio) {		
		return daoReporteSeguimiento.tipoSeguimientoList(anio);
	}
	
	

}
