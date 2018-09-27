package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <code>DTOActa.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 11:20:00
 */
public class DTOActa implements Serializable {

	private static final long serialVersionUID = 545514922226161883L;

	// TODO Identificador del distrito.
	private Integer idDistrito;

	// TODO Identificador del municipio.
	private Integer idMunicipioLocal;

	// TODO Identificador de la Regiduria
	private Integer idRegiduria;
	
	// TODO Numero de la seccion.
	private Integer seccion;

	// TODO Nombre de la casilla (B, C1, C2, E1 C1)
	private String nombreCasilla;

	// TODO clave compuesta de la casilla
	private String cveCasilla;

	// TODO Lista Nominal
	private Integer listaNominal;

	// TODO Identificador del estatus de acta
	private Integer idEstatusActa;

	// TODO Descripcion del estatus del acta
	private String desEstatusActa;

	// TODO dentificador del tipo de candidatura
	private Integer idTipoCandidatura;

	// TODO Descripcion del tipo de candidatura
	private String descTipoCandidatura;

	// TODO Porcentaje de avance global de las actas capturadas
	private BigDecimal avanceGlobalActasCapt;

	// TODO Numero de actas que ya se capturaron.
	private Integer actasCapturadas;

	// TODO Numero de actas que aun estan pendientes por capturtar.
	private Integer actasPendPorCapturar;

	/**
	 * @return the idDistrito
	 */
	public Integer getIdDistrito() {
		return idDistrito;
	}

	/**
	 * @param idDistrito the idDistrito to set
	 */
	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	/**
	 * @return the idMunicipioLocal
	 */
	public Integer getIdMunicipioLocal() {
		return idMunicipioLocal;
	}

	/**
	 * @param idMunicipioLocal the idMunicipioLocal to set
	 */
	public void setIdMunicipioLocal(Integer idMunicipioLocal) {
		this.idMunicipioLocal = idMunicipioLocal;
	}

	/**
	 * @return the idRegiduria
	 */
	public Integer getIdRegiduria() {
		return idRegiduria;
	}

	/**
	 * @param idRegiduria the idRegiduria to set
	 */
	public void setIdRegiduria(Integer idRegiduria) {
		this.idRegiduria = idRegiduria;
	}

	/**
	 * @return the seccion
	 */
	public Integer getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(Integer seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the nombreCasilla
	 */
	public String getNombreCasilla() {
		return nombreCasilla;
	}

	/**
	 * @param nombreCasilla the nombreCasilla to set
	 */
	public void setNombreCasilla(String nombreCasilla) {
		this.nombreCasilla = nombreCasilla;
	}

	/**
	 * @return the cveCasilla
	 */
	public String getCveCasilla() {
		return cveCasilla;
	}

	/**
	 * @param cveCasilla the cveCasilla to set
	 */
	public void setCveCasilla(String cveCasilla) {
		this.cveCasilla = cveCasilla;
	}

	/**
	 * @return the listaNominal
	 */
	public Integer getListaNominal() {
		return listaNominal;
	}

	/**
	 * @param listaNominal the listaNominal to set
	 */
	public void setListaNominal(Integer listaNominal) {
		this.listaNominal = listaNominal;
	}

	/**
	 * @return the idEstatusActa
	 */
	public Integer getIdEstatusActa() {
		return idEstatusActa;
	}

	/**
	 * @param idEstatusActa the idEstatusActa to set
	 */
	public void setIdEstatusActa(Integer idEstatusActa) {
		this.idEstatusActa = idEstatusActa;
	}

	/**
	 * @return the desEstatusActa
	 */
	public String getDesEstatusActa() {
		return desEstatusActa;
	}

	/**
	 * @param desEstatusActa the desEstatusActa to set
	 */
	public void setDesEstatusActa(String desEstatusActa) {
		this.desEstatusActa = desEstatusActa;
	}

	/**
	 * @return the idTipoCandidatura
	 */
	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	/**
	 * @param idTipoCandidatura the idTipoCandidatura to set
	 */
	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	/**
	 * @return the descTipoCandidatura
	 */
	public String getDescTipoCandidatura() {
		return descTipoCandidatura;
	}

	/**
	 * @param descTipoCandidatura the descTipoCandidatura to set
	 */
	public void setDescTipoCandidatura(String descTipoCandidatura) {
		this.descTipoCandidatura = descTipoCandidatura;
	}

	/**
	 * @return the avanceGlobalActasCapt
	 */
	public BigDecimal getAvanceGlobalActasCapt() {
		return avanceGlobalActasCapt;
	}

	/**
	 * @param avanceGlobalActasCapt the avanceGlobalActasCapt to set
	 */
	public void setAvanceGlobalActasCapt(BigDecimal avanceGlobalActasCapt) {
		this.avanceGlobalActasCapt = avanceGlobalActasCapt;
	}

	/**
	 * @return the actasCapturadas
	 */
	public Integer getActasCapturadas() {
		return actasCapturadas;
	}

	/**
	 * @param actasCapturadas the actasCapturadas to set
	 */
	public void setActasCapturadas(Integer actasCapturadas) {
		this.actasCapturadas = actasCapturadas;
	}

	/**
	 * @return the actasPendPorCapturar
	 */
	public Integer getActasPendPorCapturar() {
		return actasPendPorCapturar;
	}

	/**
	 * @param actasPendPorCapturar the actasPendPorCapturar to set
	 */
	public void setActasPendPorCapturar(Integer actasPendPorCapturar) {
		this.actasPendPorCapturar = actasPendPorCapturar;
	}
}
