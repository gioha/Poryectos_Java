package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DTORegiduria.java</code>Clase que representa el modelado conceptual de una Regiduria en el modelo del computo
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 27/04/2017
 */
public class DTORegiduria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7848700740423311467L;
	
	// TODO Identificador de la regiduria.
	private Integer idRegiduria;
	
	// TODO Nombre de la cabecera distrital del distrito.
	private String nombreRegiduria;
	
	// TODO Secciones pertenecientes a la regiduria.
	private List<DTOSeccion> secciones;
	
	// TODO indica si la regiduria esta en recuento total o no
	private boolean esRecuentoTotal;
	

	public DTORegiduria (){
		
		idRegiduria 		= new Integer(0);
		nombreRegiduria 	= "";
		secciones 			= new ArrayList<DTOSeccion>();
		esRecuentoTotal		= false;
	}

	public DTORegiduria(Integer idRegiduria, String nombreRegiduria, List<DTOSeccion> secciones) {
		super();
		this.idRegiduria = idRegiduria;
		this.nombreRegiduria = nombreRegiduria;
		this.secciones = secciones;
	}

	public Integer getIdRegiduria() {
		return idRegiduria;
	}

	public void setIdRegiduria(Integer idRegiduria) {
		this.idRegiduria = idRegiduria;
	}

	public String getNombreRegiduria() {
		return nombreRegiduria;
	}

	public void setNombreRegiduria(String nombreRegiduria) {
		this.nombreRegiduria = nombreRegiduria;
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
