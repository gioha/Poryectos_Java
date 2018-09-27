package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.bsd.BSDReporteSeguimientoInterface;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimiento;
import mx.ine.acuerdos.dto.helper.form.HLPFormReporteSeguimiento;
import mx.ine.acuerdos.util.Constantes;

import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@ManagedBean(name="mbSeguimientoCG")
@ViewScoped
public class MBSeguimientoCG extends MBGeneric implements Serializable{
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;
	
	@Autowired
	@Qualifier("asConvocatoria")
	private transient ASConvocatoriaInterface asConvocatoria;
	
	
	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;
	
	private static final long serialVersionUID = -1351084041502919479L;
	private List<DTOSeguimiento> lista;	
	private List<DTOSeguimiento> filterLista;
	private List<DTOCTipoDocumento> seguimientoList;
	private List<String> tipoDocumentoList;
	private HLPFormReporteSeguimiento helper;
	private int aux;
	private static Logger log = Logger.getLogger(MBSeguimientoCG.class);
	private int tabla=0;
	private String anio;
	private List<String> listAnio;
	private List<String> reporteList;
	private int contenedor=0;
	private String menu="";
	private String seguimiento="";
	private String acuerdo="";
	//private HelperFiltrosReporte helperFiltros;
	private String tipoDocumento;
	@Autowired
	@Qualifier("bsdReporteSeguimiento")
	private transient BSDReporteSeguimientoInterface bsdReporteSeguimiento;	
	HashMap<String, Object> params;
	
	@PostConstruct
	public void init() {
		//lista = bsdReporteSeguimiento.listaAcuerdosSeguimiento();
		 lista= new ArrayList<DTOSeguimiento>();
		 //helper = new HLPFormReporteSeguimiento(lista);
		 listAnio= bsdReporteSeguimiento.listaAnios();		
		 tabla =0;
		 
		 //Codigo temporal  para mostrar el menu de seleccion de reportes
		 reporteList = new ArrayList<String>();
			reporteList.add("Seguimiento de Acuerdos y Puntos");
//			reporteList.add("Sesiones Convocadas");
//			reporteList.add("Seguimiento de actividades SIVOPL");
			//filterLista = new ArrayList<DTOSeguimiento>();
			params = new HashMap<String,Object>();
			seguimientoList = new ArrayList<DTOCTipoDocumento>();
			tipoDocumentoList = new ArrayList<String>();
			
//			try {
//				bsdReporteSeguimiento.irEtiqueraSeguimiento();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			
			mbAdmin.setImgsInfografias(null);
	}
	
	 
	 
	 public List<DTOSeguimiento> getLista(){
		 return lista;
	 }
	 
	 public List<DTOSeguimiento> getFilterLista(){
		 return filterLista;
	 }
	 
	 public void setFilterLista(List<DTOSeguimiento> filterLista) {
		 this.filterLista = filterLista;
	 }



	public HLPFormReporteSeguimiento getHelper() {
		return helper;
	}


	public void rowSelected(SelectEvent event) {
		log.error(" " + event.getObject().toString());
	}
	public void setHelper(HLPFormReporteSeguimiento helper) {
		this.helper = helper;
	}
	
	
	public int getTabla() {
		return tabla;
	}



	public void setTabla(int tabla) {
		this.tabla = tabla;
	}


	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getAnio() {
		return anio;
	}
	

	public List<String> getListAnio() {
		return listAnio;
	}
	
	public void setListAnio(List<String> listAnio) {
		this.listAnio = listAnio;
	}
	
	public List<String> getReporteList() {
		return reporteList;
	}
	
	public void setReporteList(List<String> reporteList) {
		this.reporteList = reporteList;
	}
	
	public String getMenu() {
		return menu;
	}



	public void setMenu(String menu) {
		this.menu = menu;
	}



	public int getContenedor() {
		return contenedor;
	}



	public void setContenedor(int contenedor) {
		this.contenedor = contenedor;
	}



	public String getAcuerdo() {
		return acuerdo;
	}



	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}



	public String getSeguimiento() {
		return seguimiento;
	}



	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}


	public List<DTOCTipoDocumento> getSeguimientoList() {
		return seguimientoList;
	}



	public void setSeguimientoList(List<DTOCTipoDocumento> seguimientoList) {
		this.seguimientoList = seguimientoList;
	}



	public String getTipoDocumento() {
		return tipoDocumento;
	}



	public void setDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}



	public List<String> getTipoDocumentoList() {
		return tipoDocumentoList;
	}



	public void setTipoDocumentoList(List<String> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}



	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}



	/**
	 * Metodo para la generción del Reporte excel o PDF
	 * 
	 * */
	public  StreamedContent getReporte() {
		
		
		return null;
	}
	
	/**
	 * Método para aplicar los filros de la Base de datos
	 * */
	
	public boolean filtroBusqueda(Object value, Object filter, Locale locale) {
			
		String filterText = (filter==null)? null:filter.toString().trim();
		
		if(filterText == null || filterText.isEmpty()) {
			return false;
		}
		
		if(value==null) {
			return false;
		}
		filterLista.addAll(lista);
		
		return true;
		
	}
	
	/**
	 * Metodo que esta a la escucha de un evento*/
	public void escuchando() {
				
		if(menu.equals("Seguimiento de Acuerdos y Puntos")) {
			
			tabla = 1;
		}else if(menu.equals("")) {
			
			tabla = 0;
		}	
		
	}

/**
 * Metodo que escucha el año 
 *  que  se selecciona  e muestra los datos en  la tabla
 *  */

	public  void getRango() {
		
		if(anio.equals("")) {
			
			aux=0;
			tabla=0;
			tipoDocumentoList.clear();
			lista.clear();
			try {
				if(filterLista!=null) {filterLista.clear();}
				
			}catch(Exception e) {}
			try {
				helper = null;
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		}else {
			//validamos 
			
			tipoDocumentoList.clear();
			if(tabla==1) {tabla=0;}//es para el output panel tablas activa o desactiva  el selectOneMenu para  el tipo de reportes
			
			lista.clear();
			try {
				if(filterLista!=null) {filterLista.clear();}
				//helper=null;
			}catch(Exception e) {}
			//lista.clear();
			
			//tabla =1;			
			helper = new HLPFormReporteSeguimiento(lista);
			
			tipoSeguimiento();
			aux=1;
			
			try {
				if(!tipoDocumento.equals("")) {
					getDocumento();
					if(lista.size()==0) {
						tabla=0;
					}
				}
			}catch(Exception e) {}
			
		}
		
	}
	public int getAux() {
		return aux;
	}
	
	
	
	/***
	 * MÉTODO QUE ESCUCHA EL TIPO DE REPORTE QUE SELECCIONA EL USUARIO
	 * **/
	public void getDocumento() {	
		if(!tipoDocumento.equals("")) {
			if(tabla==0) {tabla=1;}
			lista.clear();
					
			try {
				if(filterLista!=null) {filterLista.clear();}
			}catch(Exception e) {}
			
			//ESTE METODO IRA POR EL QUERY EN EL QUE SE SELECCIONARA EL TIPO DE
			//SEGUIMIENTO QUE SE DESEA VISUALIZAR
			// SI ES ACUERDO O RESOLUCION
			//******CODIGO****
			lista.addAll(bsdReporteSeguimiento.listaAcuerdosSeguimiento(seguimientoList, tipoDocumento, anio));
			if(filterLista!=null) {
				filterLista.clear();
				filterLista.addAll(lista);
			}
			params.clear();
			params.put("tipoDocumento",tipoDocumento);
			helper = new HLPFormReporteSeguimiento(lista);
			bsdReporteSeguimiento.tipoSeguimiento(anio);
		}else {
			
			bsdReporteSeguimiento.tipoSeguimiento(anio);
			tabla=0;	
		}
		
	}
	
	

	/**
	 * MÉTODO PARA IR POR EL TIPO DE REPORTE QUE SE VA A VISUALIZAR
	 * SI ESTE VA A SER UN ACUERDO O UNA RESOLUCION
	 * @params null
	 * @return void
	 * **/
	public void tipoSeguimiento(){
		seguimientoList.clear();
		tipoDocumentoList.clear();
		if(anio!=null) {
			seguimientoList.addAll(bsdReporteSeguimiento.getTipoSeguimiento(anio));
			for(DTOCTipoDocumento documento: seguimientoList) {
				tipoDocumentoList.add(documento.getTipo());
			}
		}
		
	}


	public void setAux(int aux) {
		this.aux = aux;
	}
	
	/**
	 * Metodo  para generar el reporte pdf
	 * @params null
	 * @return StreamContent
	 * */
	public StreamedContent getReportePDF() {
				
			try {
		
				if(filterLista.size() ==0 && params.size()==0) {
					params.put("anio", anio);
					return bsdReporteSeguimiento.getReportePDF(lista,"reporte_seguimiento_acuerdos_puntos",false, params);
				}
				params.put("anio", anio);
				params.put("mensaje","");
				return bsdReporteSeguimiento.getReportePDF(filterLista,"reporte_seguimiento_acuerdos_puntos",true,params);
			}catch(Exception e) {
				params.put("anio", anio);
				//reporte_seguimiento_acuerdos_puntos
				//Utilidades.mensajeProperties("lbl_reporte_seguimiento_acuerdo_puntos").replace(" ","_")
				return bsdReporteSeguimiento.getReportePDF(lista,"reporte_seguimiento_acuerdos_puntos", false, params);
			}
		
		
	}
	
	/**
	 * Metodo  para generar el reporte xls
	 * @params null
	 * @return StreamContent
	 * */
	
	public StreamedContent getReporteXls() {
		params.put("anio", anio);
		try {
			if(filterLista.size() ==0 && params.size()==0) {
				params.put("anio", anio);
				return bsdReporteSeguimiento.getReporteXLs(lista,"reporte_seguimiento_acuerdos_puntos", params);
			}
			params.put("anio",anio);
			return bsdReporteSeguimiento.getReporteXLs(filterLista,"reporte_seguimiento_acuerdos_puntos", params);
		}catch(Exception e) {
			params.put("anio", anio);
			//reporte_seguimiento_acuerdos_puntos
			//Utilidades.mensajeProperties("lbl_reporte_seguimiento_acuerdo_puntos").replace(" ","_")
			return bsdReporteSeguimiento.getReporteXLs(lista,"reporte_seguimiento_acuerdos_puntos", params);
		}
	
	}
	
	
	/**
	 * METODO PARA IR POR LOS FILTROS DE CADA COLUMNA Y ALMACENAR  LO QUE TECLEA
	 *  EN EL LOG
	 * */
	public boolean filtros(Object value, Object filter, Locale locale) {
		return bsdReporteSeguimiento.filtros(value,filter,locale);
	}	
	
	public boolean filtroAcuerdo(Object value, Object filter, Locale locale) {
		
		boolean bandera = bsdReporteSeguimiento.filtrosAutoComplete(value, filter, locale); 
		
	if(bandera) {
			try {
				params.put("filtroAcuerdo",value.toString().trim().toUpperCase());

			}catch(Exception e) {
				
			}
				
				
			}
			
		
		return bandera;
	}
	public boolean filtroEstatus(Object value, Object filter, Locale locale) {
		
		boolean bandera = filtros(value, filter, locale); 
		
		if(bandera) {
			
//			if(!helperFiltros.getFiltroEstatus().equals(filter.toString().trim().toUpperCase())) {
//				helperFiltros.setFiltroEstatus(filter.toString().trim().toUpperCase());
			try {
				params.put("filtroEstatus",filter.toString().trim());
			}catch(Exception e) {
				params.put("filtroEstatus", "");
			}
				
			//}
			
		}
		return bandera;
	}
	
	public boolean filtroArea(Object value, Object filter, Locale locale) {
		boolean bandera = filtros(value, filter, locale); 
		
		
		
		if(bandera) {
			
//			if(helperFiltros.getFiltroArea().equals("")) {
//				log.info("entro primero en esta condicion con helperFiltro " + helperFiltros.getFiltroArea() + " filter "  + filter.toString().toUpperCase());
//				helperFiltros.setFiltroArea(filter.toString().trim().toUpperCase());
//			}
//			if(!helperFiltros.getFiltroArea().equals(filter.toString().trim().toUpperCase())) {
//				log.info("Helper Area "+ helperFiltros.getFiltroArea() +"");
//				helperFiltros.setFiltroArea(filter.toString().trim().toUpperCase());
//			}
			try {
				if(filter.toString().equals("")) {
					params.put("filtroArea","");
				}
//				log.info("puto helper + " +helperFiltros.getFiltroArea());
				
					params.put("filtroArea",filter.toString().trim());
				
			}catch(Exception e) {
				params.put("filtroArea", "");
			}
		}
		return bandera;
	}
	
	public boolean filtroClasificacion(Object value, Object filter, Locale locale) {
		
		boolean bandera = filtros(value, filter, locale); 
		
		if(bandera) {
//			if(!helperFiltros.getFiltroClasificacion().equals(filter.toString().trim().toUpperCase())) {
//				helperFiltros.setFiltroClasificacion(filter.toString().trim().toUpperCase());
			try {
				params.put("filtroClasificacion",filter.toString().trim());
				
			}catch(Exception e) {
				params.put("filtroClasificacion", "");
				
			}
				
			//}
			
		}
		return bandera;
	}
	public boolean filtroPunto(Object value, Object filter, Locale locale) {
		
		boolean bandera = bsdReporteSeguimiento.filtrosAutoComplete(value, filter, locale); 
		
		if(bandera) {
			
				try {
					params.put("filtroPunto",value.toString().trim().toUpperCase());
				
				}catch(Exception e) {
					e.printStackTrace();
					log.error("error en MBSeguimientoCG.filtroPunto " + e.getMessage());
				}
				
			}
			
		
		return bandera;
	}
	
	// metodo para validar los roles permitidos
	public boolean validaRoles(){	
		
		boolean rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_ADMIN_OC ) 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_SE_OC )	 		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_SE_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )				
    	   ){
			
			rolValido = true;			
    	}
    
		return rolValido;
	}
	
	/**
	 * Metodo que recupera el Rol del usuario en sesion y ve si es de tipo titular ara o area
	 * @author Giovanni Hernández
	 * @since 10/12/2017
	 * @param 
	 * @return boolean es de tipo titular area o area
	 * **/
	public boolean esTitularArea(){
		
		boolean rolValido = false;
		
		if (  
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_TITULAR_AREA_OC )		||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CAPTURA_AREA_OC )			||
    			this.obtenUsuario().getRolUsuario().equalsIgnoreCase( constantes.CONSULTA_AREA_OC )			
    			
    	   ){
			
			rolValido = true;			
    	}
    
		return rolValido;
		
	}
	
	public boolean tieneArea(){
		
		boolean tieneArea = false;
		
		try {
		
			DTOResponsables responsArea = asConvocatoria.recuperarDtoResponsable(this.obtenUsuario().getUsername());
		
			if(!(responsArea == null)) {
				tieneArea = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tieneArea;
	}
	 
}
