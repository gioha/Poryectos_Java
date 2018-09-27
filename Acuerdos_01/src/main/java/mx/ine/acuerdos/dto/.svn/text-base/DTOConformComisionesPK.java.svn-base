package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOConformComisionesPK implements Serializable {
	private static final long serialVersionUID = 419170669586835899L;

	@Column(name = "ID_COMISION", nullable = false)
	private Integer idComision;

	@Column(name = "INICIO_PERIODO", nullable = false)
	private Date inicioPeriodo;
	
	public DTOConformComisionesPK() {
		this.idComision		= new Integer(0);
		this.inicioPeriodo	= new Date();
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

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOConformComisionesPK) ) {
			DTOConformComisionesPK dtoConformComisiones = (DTOConformComisionesPK) obj;
			if( idComision.equals(dtoConformComisiones.idComision) &&
			    inicioPeriodo.equals(dtoConformComisiones.inicioPeriodo) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComision, inicioPeriodo);
	}

}
