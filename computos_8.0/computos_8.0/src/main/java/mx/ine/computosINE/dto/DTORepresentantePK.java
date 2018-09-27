package mx.ine.computosINE.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

public class DTORepresentantePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7087038468260030757L;

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
	
	@Column(name="ID_COMUNIDAD", nullable = false)
	private Integer idComunidad;
	
	@Column(name="ID_TIPO_CANDIDATURA", nullable = false)
	private Integer idTipoCandidatura;
	
	@Column(name = "ID_REPRESENTANTE", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MY_SEQ_GEN_REP")
	@SequenceGenerator(name = "MY_SEQ_GEN_REP", sequenceName = "S_REPRESENTANTES")
	private Integer idRepresentante;

	@Column(name = "TIPO_ACTA", nullable = false)
	private Integer tipoActa;
	
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

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Integer getTipoActa() {
		return tipoActa;
	}

	public void setTipoActa(Integer tipoActa) {
		this.tipoActa = tipoActa;
	}
	

}
