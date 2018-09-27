package mx.ine.computosINE.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DTODistribucionPartidosCIPK implements Serializable {

	private static final long serialVersionUID = 7420946237202075833L;

	@Column(name="ID_PROCESO_ELECTORAL",  nullable = false)
	private Integer idProcesoElectoral;
	
	@Column(name="ID_DETALLE_PROCESO", nullable = false)
	private Integer idDetalleProceso;
	
	@Column(name="ID_ESTADO", nullable = false)
	private Integer idEstado;
	
	@Column(name="ID_DISTRITO", nullable = false)
	private Integer idDistrito;
	
	@Column(name="ID_MUNICIPIO", nullable = false)
	private Integer idMunicipio;
	
	@Column(name="ID_REGIDURIA", nullable = false)
	private Integer idRegiduria;
	
	@Column(name="SECCION", nullable = false)
	private Integer seccion;
	
	@Column(name="ID_COMUNIDAD", nullable = false)
	private Integer idComunidad;
	
	@Column(name="ID_TIPO_CANDIDATURA", nullable = false)
	private Integer idTipoCandidatura;
	
	@Column(name="ID_ASOCIACION", nullable = false)
	private Integer idAsociacion;
	
	
	
	
	public DTODistribucionPartidosCIPK(){
		
	}
	
	public DTODistribucionPartidosCIPK(Integer idProcesoElectoral, Integer idDetalleProceso, Integer idEstado,
			Integer idDistrito, Integer idMunicipio, Integer idRegiduria, Integer seccion, Integer idComunidad,
			Integer idTipoCandidatura, Integer idAsociacion) {
		super();
		this.idProcesoElectoral = idProcesoElectoral;
		this.idDetalleProceso = idDetalleProceso;
		this.idEstado = idEstado;
		this.idDistrito = idDistrito;
		this.idMunicipio = idMunicipio;
		this.idRegiduria = idRegiduria;
		this.seccion = seccion;
		this.idComunidad = idComunidad;
		this.idTipoCandidatura = idTipoCandidatura;
		this.idAsociacion = idAsociacion;
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

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdRegiduria() {
		return idRegiduria;
	}

	public void setIdRegiduria(Integer idRegiduria) {
		this.idRegiduria = idRegiduria;
	}

	public Integer getSeccion() {
		return seccion;
	}

	public void setSeccion(Integer seccion) {
		this.seccion = seccion;
	}

	public Integer getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public Integer getIdAsociacion() {
		return idAsociacion;
	}

	public void setIdAsociacion(Integer idAsociacion) {
		this.idAsociacion = idAsociacion;
	}
}
