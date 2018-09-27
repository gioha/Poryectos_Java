/**
	 * HelPForm  auxiliar  para mostrar los datos de la BD
	 * en el front
	 *  
	 * @author Angel Omar Medel Hernandex
	 * @since 23 Octubre-2017
**/
package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import mx.ine.acuerdos.dto.DTOSeguimiento;

import org.jboss.logging.Logger;

//@Scope("prototype")
//@Repository("hlpFormReporteSeguimiento")
public class HLPFormReporteSeguimiento  implements Serializable{
	
	private static final long serialVersionUID = 6116722418378601730L;
	private List<DTOSeguimiento> listSeguimiento;
	private List<DTOSeguimiento> listFiltroSeguimiento;
	private TreeSet<String> estatusList;
	private TreeSet<String> areaList;
	private TreeSet<String> tematicaList;
	private TreeSet<String> anioList;
	private static Logger log = Logger.getLogger(HLPFormReporteSeguimiento.class);
	
	public HLPFormReporteSeguimiento(int aux) {
		listSeguimiento = new ArrayList<DTOSeguimiento>();
		listFiltroSeguimiento = new ArrayList<DTOSeguimiento>();
	}
	public HLPFormReporteSeguimiento(){
		listFiltroSeguimiento = new ArrayList<DTOSeguimiento>();
		listSeguimiento = new ArrayList<DTOSeguimiento>();
		estatusList = new TreeSet<String>();
		areaList = new TreeSet<String>();
		tematicaList = new TreeSet<String>();
		anioList = new TreeSet<String>();
		}
	
	public HLPFormReporteSeguimiento(List<DTOSeguimiento> listSeguimiento) {
		log.info("Entro al contructor donde recibe el arreglo");
		this.listSeguimiento = listSeguimiento;
		listFiltroSeguimiento = new ArrayList<DTOSeguimiento>();
		estatusList = new TreeSet<String>();
		areaList = new TreeSet<String>();
		tematicaList = new TreeSet<String>();
		anioList = new TreeSet<String>();
		
		llenarCabeceras();
		
	}
	
	public List<DTOSeguimiento> getListSeguimiento() {
		return listSeguimiento;
	}
	public void setListSeguimiento(List<DTOSeguimiento> listSeguimiento) {
		this.listSeguimiento = listSeguimiento;
	}
	
	public void setDtoSeguimiento(DTOSeguimiento aux) {
		listSeguimiento.add(aux);
		llenarCabeceras();
	}
	
	/**
	 * Metodo para llenar las listas de las cabeceras de la tabla
	 * 
	 * */
	public void llenarCabeceras() {
				
		for(int i=0;i<listSeguimiento.size();i++) {
			//cabecera para el estatus
			DTOSeguimiento  aux = listSeguimiento.get(i);			
			estatusList.add(aux.getEstatus());
			
			if(aux.getArea()!=null) {
			areaList.add(aux.getArea());
			}			
			tematicaList.add(aux.getTematica());
			anioList.add(aux.getAnio());
			
		}
		
	}
			
		/**
		 * Metodos GET  y SET
		 * */
	
		public TreeSet<String> getEstatusList() {			
				return estatusList;
			}
			public void setEstatusList(TreeSet<String> estatusList) {
				this.estatusList = estatusList;
			}			
		public TreeSet<String> getAreaList() {	
			return areaList;
			}		
		public TreeSet<String> getTematicaList() {			
			return tematicaList;
		}
		
		public TreeSet<String> getAnioList(){
			return anioList;
		}
		public void setAreaList(TreeSet<String> areaList) {
			this.areaList = areaList;
		}
		public void setTematicaList(TreeSet<String> tematicaList) {
			this.tematicaList = tematicaList;
		}
		public void setAnioList(TreeSet<String> anioList) {
			this.anioList = anioList;
		}
		public List<DTOSeguimiento> getListFiltroSeguimiento() {
			return listFiltroSeguimiento;
		}
		public void setListFiltroSeguimiento(List<DTOSeguimiento> listFiltroSeguimiento) {
			this.listFiltroSeguimiento = listFiltroSeguimiento;
		}
		
		
		
}
