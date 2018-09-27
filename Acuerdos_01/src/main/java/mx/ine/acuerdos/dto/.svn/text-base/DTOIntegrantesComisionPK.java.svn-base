package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOIntegrantesComisionPK implements Serializable {
	private static final long serialVersionUID = 1218643501852784575L;

	@Column(name = "ID_COMISION", nullable = false)
	private Integer idComision;

	@Column(name = "INICIO_PERIODO", nullable = false)
	private Date inicioPeriodo;

	@Column(name = "ID_RESPONSABLE", nullable = false)
	private Integer idResponsable;
	
	public DTOIntegrantesComisionPK() {
		this.idComision		= new Integer(0);
		this.inicioPeriodo	= new Date();
		this.idResponsable	= new Integer(0);
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

	public Integer getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOIntegrantesComisionPK) ) {
			DTOIntegrantesComisionPK dtoIntegsComision = (DTOIntegrantesComisionPK) obj;
			if( idComision.equals(dtoIntegsComision.idComision) &&
			    inicioPeriodo.equals(dtoIntegsComision.inicioPeriodo) &&
			    idResponsable.equals(dtoIntegsComision.idResponsable) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComision, inicioPeriodo, idResponsable);
	}
 
}
