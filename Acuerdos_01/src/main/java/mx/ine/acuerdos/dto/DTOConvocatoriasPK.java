package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que transporta y mapea los campos de la llave compuesta de la tabla CONVOCATORIAS del esquema de ACUERDOS.
 * @update Miguel Ortiz
 * @author Giovanni Hernandez Alonso
 * @since Octubre-2017
 * @ver 1
 **/

@Embeddable
public class DTOConvocatoriasPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5585747876986497249L;
	
	@Column(name="ANIO", nullable = false)
	private Integer anio;

	@Column(name="ID_COMISION", nullable = false)
	private Integer idComision;

	@Column(name = "INICIO_PERIODO", nullable = false)
	private Date inicioPeriodo;
	
	@Column(name="NUM_SESION", nullable = false)
	private Integer numSesion;
	
	/**
	 * Constructor default de la clase DTOConvocatoriasPK, que es la llave compuesta de la clase DTOConvocatoria
	 * @author Giovanni Hernández Alonso
	 * @update Miguel Ortiz
	 * @since 11/10/2017
	 * @param 
	 * @return DTOConvocatoriasPK
	 * **/
	public DTOConvocatoriasPK () {

		this.anio 			= new Integer(0);
		this.idComision 	= new Integer (0);
		this.inicioPeriodo	= new Date();
		this.numSesion 		= new Integer(0);
		
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

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOConvocatoriasPK) ) {
			DTOConvocatoriasPK dtoConvocatorias = (DTOConvocatoriasPK) obj;
			if( anio.equals(dtoConvocatorias.anio) &&
			    idComision.equals(dtoConvocatorias.idComision) &&
			    inicioPeriodo.equals(dtoConvocatorias.inicioPeriodo) &&
			    numSesion.equals(dtoConvocatorias.numSesion) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, idComision, inicioPeriodo, numSesion);
	}

}
