package mx.ine.acuerdos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTOAnexosOrdenesDelDiaPK implements Serializable {
	private static final long serialVersionUID = 76021335481108082L;

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

	@Column(name="ANEXO_ADJUNTO", nullable = false)
	private String anexoAdjunto;

	public DTOAnexosOrdenesDelDiaPK () {
		this.anio			= new Integer(0);
		this.idComision		= new Integer (0);
		this.inicioPeriodo	= new Date();
		this.numSesion		= new Integer(0);
		this.numPunto		= new Integer(0);
		this.anexoAdjunto	= "";
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

	public String getAnexoAdjunto() {
		return anexoAdjunto;
	}

	public void setAnexoAdjunto(String anexoAdjunto) {
		this.anexoAdjunto = anexoAdjunto;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		if((obj != null) && (obj instanceof DTOAnexosOrdenesDelDiaPK) ) {
			DTOAnexosOrdenesDelDiaPK dtoAnexosOrdenDia = (DTOAnexosOrdenesDelDiaPK) obj;
			if( anio.equals(dtoAnexosOrdenDia.anio) &&
			    idComision.equals(dtoAnexosOrdenDia.idComision) &&
			    inicioPeriodo.equals(dtoAnexosOrdenDia.inicioPeriodo) &&
			    numSesion.equals(dtoAnexosOrdenDia.numSesion) &&
			    numPunto.equals(dtoAnexosOrdenDia.numPunto) &&
			    anexoAdjunto.equals(dtoAnexosOrdenDia.anexoAdjunto) ) {
				esIgual = true;
			}
		}
		return esIgual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, idComision, inicioPeriodo, numSesion, numPunto, anexoAdjunto);
	}

}
