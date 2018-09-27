package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class DTOPuntosAcuerdoPK implements Serializable {

	/**
	 * Clase que transporta y mapea los campos de la llave compuesta de la tabla PUNTOS_ACUERDO del esquema de ACUERDOS.
	 * 
	 * @author Jair López Jaras
	 * @since Octubre-2017
	 * @ver 1
	 **/
	private static final long serialVersionUID = 7154139369575112975L;

	@Column(name = "ID_NUM_ACUERDO", nullable = false)
	private String idNumAcuerdo;

	@Column(name = "NUMERALIA", nullable = false)
	private Integer numeralia;

	/**
	 * Constructor default de la clase DTOPuntosAcuerdo,	 que es la llave compuesta de la clase DTOPuntosAcuerdo
	 *  @author Jair López Jaras
	 * @since 11/10/2017
	 * @param 
	 * @return Void
	 * **/
	public DTOPuntosAcuerdoPK() {
		idNumAcuerdo =  "";
		numeralia = new Integer(0);
		
	}

	public String getIdNumAcuerdo() {
		return idNumAcuerdo;
	}

	public void setIdNumAcuerdo(String idNumAcuerdo) {
		this.idNumAcuerdo = idNumAcuerdo;
	}

	public Integer getNumeralia() {
		return numeralia;
	}

	public void setNumeralia(Integer numeralia) {
		this.numeralia = numeralia;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOPuntosAcuerdoPK) ) {
			DTOPuntosAcuerdoPK dtoPuntosAcuerdo = (DTOPuntosAcuerdoPK) obj;
			if( idNumAcuerdo.equals(dtoPuntosAcuerdo.idNumAcuerdo) &&
			    numeralia.equals(dtoPuntosAcuerdo.numeralia) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNumAcuerdo, numeralia);
	}

}
