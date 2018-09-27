package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DTOReglasEstatusPK implements Serializable {

	private static final long serialVersionUID = 060420171021L;

	@Column(name = "ID_PROCESO_ELECTORAL", nullable = false)
	private Integer idProcesoElectoral;

	@Column(name = "ID_DETALLE_PROCESO", nullable = false)
	private Integer idDetalleProceso;

	@ManyToOne
	@JoinColumn(name = "ID_ESTATUS", referencedColumnName = "ID_ESTATUS")
	private DTOCEstatus estatus;

	@ManyToOne
	@JoinColumn(name = "ID_REGLA", referencedColumnName = "ID_REGLA")
	private DTOCReglas regla;

	public DTOReglasEstatusPK(Integer idProcesoElectoral,
			Integer idDetalleProceso, DTOCEstatus estatus, DTOCReglas regla) {
		super();
		this.idProcesoElectoral = idProcesoElectoral;
		this.idDetalleProceso = idDetalleProceso;
		this.estatus = estatus;
		this.regla = regla;
	}

	public Integer getIdProcesoElectoral() {
		return idProcesoElectoral;
	}

	public void setIdProcesoElectoral(Integer idProcesoElectoral) {
		this.idProcesoElectoral = idProcesoElectoral;
	}

	public Integer getIdDetalleProceso() {
		return idDetalleProceso;
	}

	public void setIdDetalleProceso(Integer idDetalleProceso) {
		this.idDetalleProceso = idDetalleProceso;
	}

	public DTOCEstatus getEstatus() {
		return estatus;
	}

	public void setEstatus(DTOCEstatus estatus) {
		this.estatus = estatus;
	}

	public DTOCReglas getRegla() {
		return regla;
	}

	public void setRegla(DTOCReglas regla) {
		this.regla = regla;
	}

}
