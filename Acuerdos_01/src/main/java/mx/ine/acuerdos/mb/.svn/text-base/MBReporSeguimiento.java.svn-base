package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.ine.acuerdos.bsd.BSDReporteSeguimientoInterface;
import mx.ine.acuerdos.dto.helper.HelperReporteSeguimiento;
import mx.ine.acuerdos.dto.helper.form.HLPFormReporteSeguimiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



@ManagedBean(name="mbReporSeguimiento")
@ViewScoped
public class MBReporSeguimiento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3790991703346671303L;
	//private static Logger log = Logger.getLogger(MBReporSeguimiento.class);	
	
	
		private HLPFormReporteSeguimiento hlpSeguimiento;
		private HLPFormReporteSeguimiento hlpFiltro;
		private List<String> nombresColumnas;
		private List<HelperReporteSeguimiento> columnas;
		private String columnTemplate= "A\\u00f1o";
		
		
		@Autowired
		@Qualifier("bsdReporteSeguimiento")
		private transient BSDReporteSeguimientoInterface bsdReporteSeguimiento;
	@PostConstruct
	public void init() {
		
		nombresColumnas = new ArrayList<String>();
		nombresColumnas.add("A\u00f1o");
		nombresColumnas.add("Estatus");
		nombresColumnas.add("\u00c1rea");
		nombresColumnas.add("Tem\u00e1tica");
		nombresColumnas.add("Acuerdo");
		//Inicializamos el Helper
		
		//hlpSeguimiento.llenarDatos();
		hlpFiltro = new HLPFormReporteSeguimiento(2);
		//log.info("El numero de columns es  " + nombresColumnas.size()+"Tamaño del filtro " + hlpSeguimiento.getListSeguimiento().size()+"\n"+"tamaño del filtro " +hlpFiltro.getListSeguimiento().size());
		
		hlpSeguimiento= bsdReporteSeguimiento.buscarAcuerdosSeguimiento();
		
		
		
	}	
	
	/**
	 * 
	 * metodos Get y Set*/

	public HLPFormReporteSeguimiento getHlpSeguimiento() {
		return hlpSeguimiento;
	}

	public void setHlpSeguimiento(HLPFormReporteSeguimiento hlpSeguimiento) {
		this.hlpSeguimiento = hlpSeguimiento;
	}

	public HLPFormReporteSeguimiento getHlpFiltro() {
		return hlpFiltro;
	}

	public void setHlpFiltro(HLPFormReporteSeguimiento hlpFiltro) {
		this.hlpFiltro = hlpFiltro;
	}

	public List<HelperReporteSeguimiento> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<HelperReporteSeguimiento> columnas) {
		this.columnas = columnas;
	}

	public List<String> getNombresColumnas() {
		return nombresColumnas;
	}

	public void setNombresColumnas(List<String> nombresColumnas) {
		this.nombresColumnas = nombresColumnas;
	}

	public String getColumnTemplate() {
		return columnTemplate;
	}

	public void setColumnTemplate(String columnTemplate) {
		this.columnTemplate = columnTemplate;
	}

	

}
