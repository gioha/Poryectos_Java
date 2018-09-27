package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import org.jboss.logging.Logger;
import org.primefaces.model.StreamedContent;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.as.ASReporteSeguimientoInterface;
import mx.ine.acuerdos.bsd.BSDReporteSeguimientoInterface;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimiento;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.dto.HelperFiltrosReporte;
import mx.ine.acuerdos.dto.helper.form.HLPFormReporteSeguimiento;
import mx.ine.acuerdos.util.Utilidades;



@Component("bsdReporteSeguimiento")
@Scope("prototype")
public class BSDReporteSeguimiento implements BSDReporteSeguimientoInterface, Serializable{

	private static final long serialVersionUID = 8990653922861270246L;
	
	@Autowired
	@Qualifier("asReporteSeguimiento")
	ASReporteSeguimientoInterface asReporteSeguimiento;
	@Autowired
	@Qualifier("asConvocatoria")
	ASConvocatoriaInterface asConvocatoria;
	
	private static Logger log = Logger.getLogger(BSDReporteSeguimiento.class);
	HLPFormReporteSeguimiento hlpReporteSeguimiento = new HLPFormReporteSeguimiento();
	HelperFiltrosReporte helperFiltros;
	//HashMap<String, Object> params;
	private List<DTOCTipoDocumento> seguimientoList = new ArrayList<DTOCTipoDocumento>();
	private List<String> tipoDocumentoList = new ArrayList<String>();
	private DTOUsuarioLogin usuarioLogueado;
	private DTOResponsables responsables;
	
	
	public BSDReporteSeguimiento() {		
		 try {
			  usuarioLogueado= (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  
		 }catch(Exception e) {
			 log.error("Error en BSDReporteSeguimiento.BSDReporteSeguimiento " + e.getMessage());
			 e.printStackTrace();}
	}
	

	
	 //log.info("Usuario logueado "+ usuarioLogueado.getNombreUsuario()+" " + usuarioLogueado.getUsername()+" " + usuarioLogueado.getTipo()+" perfil "+ usuarioLogueado.getUsuario());
	 
	
	 
	
	
	
	@Override
	public HLPFormReporteSeguimiento buscarAcuerdosSeguimiento(){
		
		try {
			 responsables= asConvocatoria.recuperarDtoResponsable(usuarioLogueado.getUsername());
			 
		 }catch(Exception e) {e.printStackTrace();}
		List<DTOSeguimiento> listDtoSeguimiento = new ArrayList<DTOSeguimiento>();
		try{
			if(usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC") ||
					usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC") ||
					usuarioLogueado.getRolUsuario().equals("ACUERDOS.CONSULTA_TITULAR_AREA.OC")|| 
					usuarioLogueado.getRolUsuario().equals("ACUERDOS.CAPTURA_TITULAR_AREA.OC"))
			{
				listDtoSeguimiento.addAll(asReporteSeguimiento.buscarAcuerdosSeguimiento(null, 0, responsables.getIdArea()));
	 	 }else {
	 		listDtoSeguimiento.addAll(asReporteSeguimiento.buscarAcuerdosSeguimiento(null, 0,null));
	 	 }
			
		}catch(Exception e) {e.printStackTrace();}
		
		
			
			 
			DTOSeguimiento aux;
			for(int i=0;i<listDtoSeguimiento.size();i++) {
				aux = listDtoSeguimiento.get(i);
				hlpReporteSeguimiento.setDtoSeguimiento(aux);
				
			}
			return hlpReporteSeguimiento; 
	}
	
	
	@Override
	public List<DTOSeguimiento> listaAcuerdosSeguimiento(String anio){
		
		try {
			
		}catch(Exception e) {e.printStackTrace();}
		List<DTOSeguimiento> listDtoSeguimiento = asReporteSeguimiento.buscarAcuerdosSeguimiento(anio, 0, responsables.getIdArea());
		return listDtoSeguimiento;
	}


	@Override
	public ArrayList<String> listaAnios() {
		try {
			 responsables= asConvocatoria.recuperarDtoResponsable(usuarioLogueado.getUsername());
			 
		 }catch(Exception e) {e.printStackTrace();}
		
		try {
			 if(usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC")||
				usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC")||
				usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_TITULAR_AREA.OC")||
				usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_TITULAR_AREA.OC")
				) 
			 	{				 
				 	return asReporteSeguimiento.listaAnios(responsables.getIdArea());
			 	}else {
			 		return asReporteSeguimiento.listaAnios();
			 	 }
	}catch(Exception e) {
		e.printStackTrace();
		return new ArrayList<String>();
	}
		
	}
	
	/**
	 * METODO PARA IR POR EL REPORTE DE SEGUIMIENTOS EN FORMATO PDF
	 * */
	@Override
	public StreamedContent getReportePDF(List<DTOSeguimiento>lista, String tipoReporte, boolean filtros, HashMap<String, Object> params) {
		try {
			if(lista.size()==0 || params.size()!=0) {params.put("mensaje", "No hay informaci\u00f3n para mostrar");}
			if(lista.size()==1) {				
				if(lista.get(0).getAcuerdo()==null) {
					params.put("mensaje", "No hay informaci\u00f3n para mostrar");
				}else {
					params.put("mensaje","");
				}				
			}
			if(lista.size()==0 && params.size()>0) {params.put("mensaje", "No hay informaci\u00f3n para mostrar");}
			return asReporteSeguimiento.getReporte(lista, tipoReporte,true, filtros, params);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * METODO PARA IR POR EL REPORTE DE SEGUIMIENTO EN FORMATO XLS
	 * **/
	@Override
	public StreamedContent getReporteXLs(List<DTOSeguimiento>lista, String tipoReporte, HashMap<String,Object> params) {
		try {
			if(lista.size()==0 || params.size()!=0) {params.put("mensaje", "No hay informaci\u00f3n para mostrar");}
			if(lista.size()==1) {				
				if(lista.get(0).getAcuerdo()==null) {
					params.put("mensaje", "No hay informaci\u00f3n para mostrar");
				}else {
					params.put("mensaje","");
				}				
			}
			if(lista.size()==0 && params.size()>0) {params.put("mensaje", "No hay informaci\u00f3n para mostrar");}
			return asReporteSeguimiento.getReporte(lista, tipoReporte, false,false, params);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * METODO PARA LOS FILTROS QUE SE DEBEN MOSTRAR EN EL REPORTE
	 * COMPARA  EL FILTRO CON CADA OBJETO  EN LA LISTA
	 * 
	 * **/
	@Override
	public boolean filtros(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }   
        
        return (value.toString().trim().toUpperCase().equals(filterText.trim().toUpperCase()));
		
	}
	
	
	/**
	 * METODO PARA LOS FILTROS QUE SE DEBEN MOSTRAR EN EL REPORTE
	 * COMPARA  EL FILTRO CON CADA OBJETO  EN LA LISTA
	 *  y si la cadena Value contiene la subcadena filter regresa  true
	 * **/
	@Override
	public boolean filtrosAutoComplete(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        String val = Utilidades.limpiarAcentos(value.toString());
        String filterSinAcentos = Utilidades.limpiarAcentos(filterText);
        if(val == null) {
            return false;
        }   
        
        int resultado =val.trim().toUpperCase().indexOf(filterSinAcentos.trim().toUpperCase());
        
        if(resultado == -1) {
        	return false;        	
        }else {
        	return true;
        }
		
	}
	
	
	
	

	/**
	 * MÉTODO PAR IR POR EL TIPO DE REPORTE QUE SE DESEA VISUALIZAR , ACUERDO O RESOLUCION
	 * @param String anio
	 * return List<String>
	 * **/
	 @Override
	 public List<DTOCTipoDocumento> getTipoSeguimiento(String anio){
		 return asReporteSeguimiento.getTipoSeguimiento(anio);
	 }


//	public HelperFiltrosReporte getHelperFiltros() {
//		return helperFiltros;
//	}
//
//
//
//
//
//
//	public void setHelperFiltros(HelperFiltrosReporte helperFiltros) {
//		this.helperFiltros = helperFiltros;
//	}
	 
	 
	 
	 /**
	  * MÉTODO PARA IR POR QUERY QUE RETORNA  LIST<DTOSeguimiento> PARA POBLAR LA TABLA
	  * LOS FINTROS SON AÑO Y TIPO DE DOCUMENTO: ACUERDO O RESOLUCIÓN
	  * @param List<DTOCTipoDocumento> seguimientoList
	  * @param String documento
	  * @return List<DTOSeguimiento> lista
	  * **/
	 @Override
	 public List<DTOSeguimiento> listaAcuerdosSeguimiento(List<DTOCTipoDocumento> seguimientoList, String documento, String anio){
		 int tipoDocumento=0;
		 List<DTOSeguimiento> listaSeguimiento = new ArrayList<DTOSeguimiento>();
		 try {
			 responsables= asConvocatoria.recuperarDtoResponsable(usuarioLogueado.getUsername());
			 
		 }catch(Exception e) {e.printStackTrace();}
		 
		 
		 for(DTOCTipoDocumento doc:seguimientoList) {
			 if(doc.getTipo().trim().equals(documento.trim())) {
				 tipoDocumento = doc.getIdTipoDocumento();
				 break;
			 }		 
		 }
		 //metodo par ir  al as y retornar la lista de datos
		try {
				 if(usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_AREA.OC")||
					usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_AREA.OC")||
					usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CONSULTA_TITULAR_AREA.OC")||
					usuarioLogueado.getRolUsuario().equalsIgnoreCase("ACUERDOS.CAPTURA_TITULAR_AREA.OC")
					) {
					 
					 	listaSeguimiento.addAll(asReporteSeguimiento.buscarAcuerdosSeguimiento(anio, tipoDocumento, responsables.getIdArea()));
				 	 }else {
				 		listaSeguimiento.addAll(asReporteSeguimiento.buscarAcuerdosSeguimiento(anio, tipoDocumento,null));
				 	 }
		}catch(Exception e) {
			e.printStackTrace();
		}
		 
		 
		 return listaSeguimiento;
		 
	 }
	 
	 
	 @Override
	 public String irEtiqueraSeguimiento() {
		 return Utilidades.mensajeProperties("lbl_resoluciono_o_sentencia").toUpperCase();
	 }
	 
	 
	 
	 
	 /**
		 * MÉTODO PARA IR POR EL TIPO DE REPORTE QUE SE VA A VISUALIZAR
		 * SI ESTE VA A SER UN ACUERDO O UNA RESOLUCION
		 * @params null
		 * @return void
		 * **/
	 	@Override
		public  List<String>  tipoSeguimiento(String anio){
			seguimientoList.addAll(getTipoSeguimiento(anio));
			for(DTOCTipoDocumento documento: seguimientoList) {
						tipoDocumentoList.add(documento.getTipo());
			}
			return  tipoDocumentoList;
		}
	
	 
}
