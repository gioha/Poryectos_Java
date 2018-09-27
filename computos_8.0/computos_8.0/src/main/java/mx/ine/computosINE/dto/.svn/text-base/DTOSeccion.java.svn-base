package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.common.ws.casillas.dto.response.DTOCasillaWS;

/**
 * <code>DTOSeccion.java</code>Clase que representa el modelado conceptual de una sección en el modelo del computo
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 27/04/2017
 */
public class DTOSeccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6749303527491197598L;

	// TODO Identificador de la seccion.
	private Integer seccion;
	
	// TODO Casillas pertenecientes a la sección.
	private List<DTOCasillaWS> casillas;
	
	public DTOSeccion(){
		seccion = new Integer(0);
		casillas = new ArrayList<DTOCasillaWS>();
	}

	public DTOSeccion(Integer seccion, List<DTOCasillaWS> casillas) {
		super();
		this.seccion = seccion;
		this.casillas = casillas;
	}

	public Integer getSeccion() {
		return seccion;
	}

	public void setSeccion(Integer seccion) {
		this.seccion = seccion;
	}

	public List<DTOCasillaWS> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<DTOCasillaWS> casillas) {
		this.casillas = casillas;
	}
	
	
}
