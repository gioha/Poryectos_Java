package mx.ine.acuerdos.pao.impl;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bo.BOReportesInterface;
import mx.ine.acuerdos.pao.PAOReportesInterface;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.dto.DTOSeguimiento;

@Scope("prototype")
@Repository("paoReportes")
public class PAOReportes implements PAOReportesInterface, Serializable{
	private static final long serialVersionUID = -8339644189021089399L;
	/**
	 * Metodo  para la generación jasper del reporte de seguimeiento CG
	 * Este metodo llamara a los metodos genericos  para la construccion del pdf
	 * */
	Logger log = Logger.getLogger(PAOReportes.class);
	
	@Autowired
	@Qualifier("boReportes")
	BOReportesInterface boReportes;
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;

	@Autowired
    @Qualifier("boArchivosAcuerdos")
    private BOArchivosInterface boArchivos;
	
	@Override
	public byte[] getReporteSeguimientoCG(List<DTOSeguimiento> lista, boolean tipo, boolean filtros, HashMap<String,Object> filtrosList) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.putAll(filtrosList);
		
		
		String jasperFile="";
		try {
			//vamor por los reportes en el gluster
			 
			
			String rutaJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/reportes");
		    String rutaImagen = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img");
			//agregamos la ruta de la imagen para el reporte
			params.put("logoIne",rutaImagen+"/ine.jpg".replace("/", File.separator));
//			try {
//				params.put("anio", lista.get(0).getAnio());
//			}catch(Exception e) {
//				params.put("anio", "");
//			}
			
			
			//VALIDAMOS QUE SISTEMA OPERATIVO SE ENCUENTRA  LA APLICACION PARA PODER IR A LA RUTA CORRECTA
			String so = mx.ine.acuerdos.util.Utilidades.getSistemaOperativo();
			int contiene = so.indexOf("Windows");
			
			
			if(contiene == -1) {//aqui vemos si contiene una cadena valida				
							
				if(tipo) {									
					jasperFile = rutaJasper+"/seguimiento_cg_ori.jasper".replace("/", File.separator);
				}else {
					jasperFile =rutaJasper+"/seguimiento_cg_xls.jasper".replace("/",File.separator);		
					
				}				
			}else{
			
			
				//Cargamos el archivo jasper
				//donde true  es PDF false xls
				if(tipo) {
					jasperFile = rutaJasper+"/seguimiento_cg_ori.jasper".replace("/", File.separator);
				}else {
					
					jasperFile =rutaJasper+"/seguimiento_cg_xls.jasper".replace("/",File.separator);	
				}
			}		
			if(tipo) {
				return boReportes.generarJasperPDF(jasperFile, params, lista);
			}
			return  boReportes.obtenerReporteXls(jasperFile, params, lista);
		}catch(Exception e) {
		  log.error("Error en PAOReportes.getReporteSeguimientoCG " + e.getMessage());
		 
		  e.printStackTrace();
		  return null;
		}
	}

}
