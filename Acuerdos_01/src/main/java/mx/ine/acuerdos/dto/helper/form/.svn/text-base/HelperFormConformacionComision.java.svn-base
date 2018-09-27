package mx.ine.acuerdos.dto.helper.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.ine.acuerdos.dto.DTOComisiones;
/**
 * Clase que proveer las varibales necesarias para manipular los controles de la pantalla del registro de conformacion de comision
 *
 * @author sampier
 * @version 1.0
 * @since 8/12/2017 
 */
public class HelperFormConformacionComision implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8194845958158029265L;
	
	private DTOComisiones comision;
	private Date fechaMin;	
	private SimpleDateFormat formateador;	
	private String fechaFormateada;
	
	
	
	
	public HelperFormConformacionComision(){
		this.comision			  = new DTOComisiones();	
		this.fechaMin             = new Date();
    	this.formateador          = new SimpleDateFormat("dd/MM/yyyy");
    	this.fechaFormateada      = formateador.format(fechaMin);
	}
	
	
	
	
	
	
	
	
	
	

	public DTOComisiones getComision() {
		return comision;
	}

	public void setComision(DTOComisiones comision) {
		this.comision = comision;
	}

	public String getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	
	
	
	

}
