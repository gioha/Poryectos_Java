package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * --------------------------------------------------------------
 * DTONivelCapturaCandidaturaPK  -- PK de tabla:
 * 									NIVEL_CAPTURA_CANDIDATURA
 * 
 * --------------------------------------------------------------
 * @author JOSE GERARDO LOPEZ ROLDAN
 *
 */
@Embeddable
public class DTONivelCapturaCandidaturaPK implements Serializable {

	private static final long serialVersionUID = -7703674249525476770L;
	
	@Column(name = "ID_PROCESO_ELECTORAL", nullable = false)
	private Integer idProcesoElectoral;
	
	@Column(name = "ID_DETALLE_PROCESO", nullable = false)
	private Integer idDetalleProceso;
	
	@Column(name = "ID_TIPO_CANDIDATURA", nullable = false)
	private Integer idTipoCandidatura;
	
	
	public DTONivelCapturaCandidaturaPK(Integer idProcesoElectoral, Integer idDetalleProceso,
			Integer idTipoCandidatura) {
		super();
		this.idProcesoElectoral = idProcesoElectoral;
		this.idDetalleProceso = idDetalleProceso;
		this.idTipoCandidatura = idTipoCandidatura;
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

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}
	
}
