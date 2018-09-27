package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DTODistrito.java</code>Clase que representa el modelado conceptual de un Distrito en el modelo del computo
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 27/04/2017
 */
public class DTODistrito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8569591766476696812L;

	// TODO Identificador del distrito.
	private Integer distrito;
	
	// TODO Nombre de la cabecera distrital del distrito.
	private String cabeceraDistrital;
	
	// TODO Secciones pertenecientes al distrito.
	private List<DTOSeccion> secciones;
	
	// TODO indica si el distrito esta en recuento total o no
	private boolean esRecuentoTotal;
	
	
	public DTODistrito (){
	
	    distrito 			= new Integer(0);
		cabeceraDistrital 	= "";
		secciones 			= new ArrayList<DTOSeccion>();
		esRecuentoTotal		= false;
		
	}

	public DTODistrito(Integer distrito, String cabeceraDistrital, List<DTOSeccion> secciones) {
		super();
		this.distrito = distrito;
		this.cabeceraDistrital = cabeceraDistrital;
		this.secciones = secciones;
	}

	public Integer getDistrito() {
		return distrito;
	}

	public void setDistrito(Integer distrito) {
		this.distrito = distrito;
	}

	public String getCabeceraDistrital() {
		return cabeceraDistrital;
	}

	public void setCabeceraDistrital(String cabeceraDistrital) {
		this.cabeceraDistrital = cabeceraDistrital;
	}

	public List<DTOSeccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<DTOSeccion> secciones) {
		this.secciones = secciones;
	}

	public boolean isEsRecuentoTotal() {
		return esRecuentoTotal;
	}

	public void setEsRecuentoTotal(boolean esRecuentoTotal) {
		this.esRecuentoTotal = esRecuentoTotal;
	}
	
}
