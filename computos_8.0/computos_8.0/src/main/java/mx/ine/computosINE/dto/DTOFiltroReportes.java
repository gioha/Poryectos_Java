package mx.ine.computosINE.dto;

import java.io.Serializable;

public class DTOFiltroReportes implements Serializable {

	private static final long serialVersionUID = 110420171139L;

	private DTOUsuarioLogin usuario;
	private Integer idTipoCandidatura;
	private String tipoCandidatura;
	private Integer idFiltroEstatusCasilla;
	private String nombreFiltroEstatusCasilla;
	private Integer idMunicipioElectoral;
	private Integer idMunicipioElectoralSeleccionado;
	private String nombreMunicipioEletoral;
	private Integer idEstadoSeleccionado;	
	private Integer idProceso;
	private Integer idDetalleProceso;
	private String nombreDistrito;
	private String nombreDemarcacion;
	
	private Integer idDistrito;
	private Integer idDemarcacion;

	public Integer getIdEstadoSeleccionado() {
		return idEstadoSeleccionado;
	}

	public void setIdEstadoSeleccionado(Integer idEstadoSeleccionado) {
		this.idEstadoSeleccionado = idEstadoSeleccionado;
	}

	public Integer getIdMunicipioElectoral() {
		return idMunicipioElectoral;
	}

	public void setIdMunicipioElectoral(Integer idMunicipioElectoral) {
		this.idMunicipioElectoral = idMunicipioElectoral;
	}

	public Integer getIdMunicipioElectoralSeleccionado() {
		return idMunicipioElectoralSeleccionado;
	}

	public void setIdMunicipioElectoralSeleccionado(
			Integer idMunicipioElectoralSeleccionado) {
		this.idMunicipioElectoralSeleccionado = idMunicipioElectoralSeleccionado;
	}

	public String getNombreMunicipioEletoral() {
		return nombreMunicipioEletoral;
	}

	public void setNombreMunicipioEletoral(String nombreMunicipioEletoral) {
		this.nombreMunicipioEletoral = nombreMunicipioEletoral;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public String getTipoCandidatura() {
		return tipoCandidatura;
	}

	public void setTipoCandidatura(String tipoCandidatura) {
		this.tipoCandidatura = tipoCandidatura;
	}

	public Integer getIdFiltroEstatusCasilla() {
		return idFiltroEstatusCasilla;
	}

	public void setIdFiltroEstatusCasilla(Integer idFiltroEstatusCasilla) {
		this.idFiltroEstatusCasilla = idFiltroEstatusCasilla;
	}

	public String getNombreFiltroEstatusCasilla() {
		return nombreFiltroEstatusCasilla;
	}

	public void setNombreFiltroEstatusCasilla(String nombreFiltroEstatusCasilla) {
		this.nombreFiltroEstatusCasilla = nombreFiltroEstatusCasilla;
	}

	public DTOUsuarioLogin getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuarioLogin usuario) {
		this.usuario = usuario;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdDetalleProceso() {
		return idDetalleProceso;
	}

	public void setIdDetalleProceso(Integer idDetalleProceso) {
		this.idDetalleProceso = idDetalleProceso;
	}

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public Integer getIdDemarcacion() {
		return idDemarcacion;
	}

	public void setIdDemarcacion(Integer idDemarcacion) {
		this.idDemarcacion = idDemarcacion;
	}

	public String getNombreDistrito() {
		return nombreDistrito;
	}

	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}

	public String getNombreDemarcacion() {
		return nombreDemarcacion;
	}

	public void setNombreDemarcacion(String nombreDemarcacion) {
		this.nombreDemarcacion = nombreDemarcacion;
	}
	
}
