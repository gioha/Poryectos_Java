package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla ORDENES_DEL_DIA del esquema de ACUERDOS.
 * 
 * @author Giovanni Hernandez Alonso
 * @update Miguel Ortiz
 * @since Octubre-2017
 * @ver 1
 **/

@Embeddable
public class DTOOrdenesDelDiaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037848896169474175L;

	
	@Column(name="ANIO", nullable = false)
	private Integer anio;

	@Column(name="ID_COMISION", nullable = false)
	private Integer idComision;

	@Column(name = "INICIO_PERIODO", nullable = false)
	private Date inicioPeriodo;
	
	@Column(name="NUM_SESION", nullable = false)
	private Integer numSesion;

	@Column(name="NUM_PUNTO", nullable = false)
	private Integer numPunto;
	
	/**
	 * Constructor default de la clase DTOOrdenDelDiaPK, que es la llave compuesta de la clase DTOOrdenDelDia
	 * @author Giovanni Hernández Alonso
	 * @update Miguel Ortiz
	 * @since 03/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOOrdenesDelDiaPK () {

		this.anio			= new Integer(0);
		this.idComision		= new Integer (0);
		this.inicioPeriodo	= new Date();
		this.numSesion		= new Integer(0);
		this.numPunto		= new Integer(0);
		
	}


	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public Integer getIdComision() {
		return idComision;
	}


	public void setIdComision(Integer idComision) {
		this.idComision = idComision;
	}


	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}


	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}


	public Integer getNumSesion() {
		return numSesion;
	}


	public void setNumSesion(Integer numSesion) {
		this.numSesion = numSesion;
	}


	public Integer getNumPunto() {
		return numPunto;
	}


	public void setNumPunto(Integer numPunto) {
		this.numPunto = numPunto;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOOrdenesDelDiaPK) ) {
			DTOOrdenesDelDiaPK dtoOrdenDelDia = (DTOOrdenesDelDiaPK) obj;
			if( anio.equals(dtoOrdenDelDia.anio) &&
			    idComision.equals(dtoOrdenDelDia.idComision) &&
			    inicioPeriodo.equals(dtoOrdenDelDia.inicioPeriodo) &&
			    numSesion.equals(dtoOrdenDelDia.numSesion) &&
			    numPunto.equals(dtoOrdenDelDia.numPunto) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, idComision, inicioPeriodo, numSesion, numPunto);
	}

}
