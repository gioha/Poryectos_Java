package mx.ine.acuerdos.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import mx.ine.acuerdos.bsd.BSDGraphicsIndexInterface;
import mx.ine.acuerdos.dto.helper.form.HelperFormGraphicsIndex;

import org.jboss.logging.Logger;
//import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.jfree.data.general.PieDataset;
import org.primefaces.model.chart.PieChartModel;
//@Component("MBGraphicsIndex")
//@ApplicationScoped
@ManagedBean(name = "mbGraphicsIndex")
public class MBGraphicsIndex {
	private HelperFormGraphicsIndex form;
	//**Atributos para el diagrama PIE
	private PieChartModel graficaAcuerdo;
	private PieChartModel graficaResolucion;
	private static Logger log = Logger.getLogger(MBGraphicsIndex.class);
	
	@Autowired
	@Qualifier("bsdGraphicsIndex")
	public transient BSDGraphicsIndexInterface bsdGraphicsIndex;
	
	
	@PostConstruct
	public void  init() {
		form = new  HelperFormGraphicsIndex();
		try {
			form.setDatosGraficaAcuerdos(bsdGraphicsIndex.acuerdosPuntosEstatus(1)); //vamos por los acuerdos
		}catch(Exception e) {
			log.error("BSDGraphicsIndexInterface.init() " + e.getMessage());
			e.printStackTrace();}
		try {
			form.setDatosGraficaResoluciones(bsdGraphicsIndex.acuerdosPuntosEstatus(6));//vamos por los acuerdos de tipo resoluciones
		}catch(Exception e) {
			log.error("BSDGraphicsIndexInterface.init() " + e.getMessage());
			e.printStackTrace();}
		
		//Atributos para el  diagrama PIE
		crearGraficaAcuerdos();
		crearGraficaResoluciones();
	}
	
	public PieChartModel getGraficaAcuerdo() {
        return graficaAcuerdo;
    }
	
	public PieChartModel getGraficaResolucion() {
		return graficaResolucion;
	}
	
	//Metodo para pintar el diagrama PIE de Acuerdos
	private void crearGraficaAcuerdos() {
		graficaAcuerdo = new PieChartModel();   
		
		if(
			form.getDatosGraficaAcuerdos().isEmpty()	// cuando no hay conexion a la BD o un caso parecido
		  )
		{
			graficaAcuerdo.set("Sin Información", 0);
			         
			graficaAcuerdo.setTitle("Puntos de Acuerdos");		
			graficaAcuerdo.setLegendPosition("e");        
			graficaAcuerdo.setShowDataLabels(true);
			graficaAcuerdo.setDiameter(260);
			graficaAcuerdo.setSeriesColors("B7C1B1"); 
			graficaAcuerdo.setShadow(false);
			graficaAcuerdo.setShowDataLabels(true);
		}
		else{ // cuando si se recupero informacion de la base de datos
			
			if
				(
						form.getDatosGraficaAcuerdos().get("cumplido") 		<= 0 &&
						form.getDatosGraficaAcuerdos().get("parcialmente") 	<= 0 &&
						form.getDatosGraficaAcuerdos().get("pendiente")		<= 0
				)
			{
				graficaAcuerdo.set("Sin Información", 0);
		         
				graficaAcuerdo.setTitle("Puntos de Acuerdos");		
				graficaAcuerdo.setLegendPosition("e");        
				graficaAcuerdo.setShowDataLabels(true);
				graficaAcuerdo.setDiameter(260);
				graficaAcuerdo.setSeriesColors("B7C1B1"); 
				graficaAcuerdo.setShadow(false);
				graficaAcuerdo.setShowDataLabels(true);
			}
			
			else{
				graficaAcuerdo.set("Cumplidos", form.getDatosGraficaAcuerdos().get("cumplido"));
				graficaAcuerdo.set("Cumplidos Parcialmente", form.getDatosGraficaAcuerdos().get("parcialmente"));
				graficaAcuerdo.set("Pendientes", form.getDatosGraficaAcuerdos().get("pendiente"));
				
				         
				graficaAcuerdo.setTitle("Puntos de Acuerdos");		
				graficaAcuerdo.setLegendPosition("e");        
				graficaAcuerdo.setShowDataLabels(true);
				graficaAcuerdo.setDiameter(260);
				graficaAcuerdo.setSeriesColors("62CA93,60b8EC,9D679D"); 
				graficaAcuerdo.setShadow(false);
				graficaAcuerdo.setShowDataLabels(true);
			}
			
		}
    }
	
	//Metodo para pintar el diagrama PIE de Resoluciones
	private void crearGraficaResoluciones() {
		graficaResolucion = new PieChartModel();
		
		if(
				form.getDatosGraficaResoluciones().isEmpty()	// cuando no hay conexion a la BD o un caso parecido
		  )
		{
			graficaResolucion.set("Sin Información", 0);
			
			graficaResolucion.setTitle("Puntos Resolutivos");
			graficaResolucion.setLegendPosition("e");        
			graficaResolucion.setShowDataLabels(true);
			graficaResolucion.setDiameter(260);
			graficaResolucion.setSeriesColors("B7C1B1");
			graficaResolucion.setShadow(false);
			graficaResolucion.setShowDataLabels(true);
		}
		
		else{	// cuando si se recupero informacion de la base de datos
			
			if(
					form.getDatosGraficaResoluciones().get("cumplido") 		<= 0 &&
					form.getDatosGraficaResoluciones().get("parcialmente") 	<= 0 &&
					form.getDatosGraficaResoluciones().get("pendiente")		<= 0	
			  )
			{
				graficaResolucion.set("Sin Información", 0);
				
				graficaResolucion.setTitle("Puntos Resolutivos");
				graficaResolucion.setLegendPosition("e");        
				graficaResolucion.setShowDataLabels(true);
				graficaResolucion.setDiameter(260);
				graficaResolucion.setSeriesColors("B7C1B1");
				graficaResolucion.setShadow(false);
				graficaResolucion.setShowDataLabels(true);
			}
			else{
				graficaResolucion.set("Cumplidos", form.getDatosGraficaResoluciones().get("cumplido"));
				graficaResolucion.set("Cumplidos Parcialmente", form.getDatosGraficaResoluciones().get("parcialmente"));
				graficaResolucion.set("Pendientes", form.getDatosGraficaResoluciones().get("pendiente"));
					
				graficaResolucion.setTitle("Puntos Resolutivos");
				graficaResolucion.setLegendPosition("e");        
				graficaResolucion.setShowDataLabels(true);
				graficaResolucion.setDiameter(260);
				graficaResolucion.setSeriesColors("62CA93,60b8EC,9D679D");
				graficaResolucion.setShadow(false);
				graficaResolucion.setShowDataLabels(true);
			}
		}
	}
	
	
	/**
	 * Metodos get y set del form
	 * */
	public HelperFormGraphicsIndex getForm() {
		return form;
	}
	public void setForm(HelperFormGraphicsIndex form) {
		this.form = form;
	}	

		
}
