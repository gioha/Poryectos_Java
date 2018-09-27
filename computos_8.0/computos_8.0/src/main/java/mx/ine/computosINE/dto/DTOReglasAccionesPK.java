package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DTOReglasAccionesPK implements Serializable {

	private static final long serialVersionUID = 060420171013L;

	@ManyToOne
	@JoinColumn(name = "ID_REGLA", referencedColumnName = "ID_REGLA")
	private DTOCReglas regla;

	@ManyToOne
	@JoinColumn(name = "ID_ACCION", referencedColumnName = "ID_ACCION")
	private DTOCAcciones accion;

	public DTOReglasAccionesPK(DTOCReglas regla, DTOCAcciones accion) {
		super();
		this.regla = regla;
		this.accion = accion;
	}

	public DTOCReglas getRegla() {
		return regla;
	}

	public void setRegla(DTOCReglas regla) {
		this.regla = regla;
	}

	public DTOCAcciones getAccion() {
		return accion;
	}

	public void setAccion(DTOCAcciones accion) {
		this.accion = accion;
	}

}
