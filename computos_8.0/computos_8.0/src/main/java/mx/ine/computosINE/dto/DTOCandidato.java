/**
 * 
 */
package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;

/**
 * <code>DTOCandidato.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 24/04/2017 11:08:00
 */
public class DTOCandidato extends DTOCandidatoWS implements Serializable {

	private static final long serialVersionUID = 9068316932526267257L;

	// TODO Clave compuesta del candidato
	private String cveCandidato;

	// TODO Clave compuesta auxiliar del candidato, esta tiene la siguiente estructura: cveCandidatoAux = idAsociacion-tipoAsociacio 
	private String cveCandidatoAux;

	// TODO Recoje los votos por candidato
	private BigDecimal votos;

	// TODO Recoje los votos por candidato
	private String votosAux;

	// TODO Acumulativo de votos por candidato
	private BigDecimal votosAcumulados;

	// TODO Orden utilizado para ordenar a los candidatos con valores negativos
	private Integer tipoAsociacionAux;

	// TODO Orden utilizado para ordenar a los candidatos con valores negativos
	private Integer idAsociacionAux;
	
	// TODO Orden utilizado para ordenar a los candidatos con valores negativos
	private Integer ordenAux;

	/**
	 * @return the cveCandidato
	 */
	public String getCveCandidato() {
		return cveCandidato;
	}

	/**
	 * Clave compuesta auxiliar del candidato, esta tiene la siguiente estructura: cveCandidatoAux = idAsociacion-tipoAsociacio
	 * @return the cveCandidatoAux
	 */
	public String getCveCandidatoAux() {
		return cveCandidatoAux;
	}

	/**
	 * Clave compuesta auxiliar del candidato, esta tiene la siguiente estructura: cveCandidatoAux = idAsociacion-tipoAsociacio
	 * @param cveCandidatoAux the cveCandidatoAux to set
	 */
	public void setCveCandidatoAux(String cveCandidatoAux) {
		this.cveCandidatoAux = cveCandidatoAux;
	}

	/**
	 * @param cveCandidato the cveCandidato to set
	 */
	public void setCveCandidato(String cveCandidato) {
		this.cveCandidato = cveCandidato;
	}

	/**
	 * Recoje los votos por candidato
	 * @return the votos
	 */
	public BigDecimal getVotos() {
		return votos;
	}

	/**
	 * Recoje los votos por candidato
	 * @param votos the votos to set
	 */
	public void setVotos(BigDecimal votos) {
		this.votos = votos;
	}

	/**
	 * @return the votosAux
	 */
	public String getVotosAux() {
		return votosAux;
	}

	/**
	 * @param votosAux the votosAux to set
	 */
	public void setVotosAux(String votosAux) {
		this.votosAux = votosAux;
	}

	/**
	 * Acumulativo de votos por candidato
	 * @return the votosAcumulados
	 */
	public BigDecimal getVotosAcumulados() {
		return votosAcumulados;
	}

	/**
	 * @return the tipoAsociacionAux
	 */
	public Integer getTipoAsociacionAux() {
		return tipoAsociacionAux;
	}

	/**
	 * @param tipoAsociacionAux the tipoAsociacionAux to set
	 */
	public void setTipoAsociacionAux(Integer tipoAsociacionAux) {
		this.tipoAsociacionAux = tipoAsociacionAux;
	}

	/**
	 * @return the idAsociacionAux
	 */
	public Integer getIdAsociacionAux() {
		return idAsociacionAux;
	}

	/**
	 * @param idAsociacionAux the idAsociacionAux to set
	 */
	public void setIdAsociacionAux(Integer idAsociacionAux) {
		this.idAsociacionAux = idAsociacionAux;
	}

	/**
	 * Acumulativo de votos por candidato
	 * @param votosAcumulados the votosAcumulados to set
	 */
	public void setVotosAcumulados(BigDecimal votosAcumulados) {
		this.votosAcumulados = votosAcumulados;
	}

	/**
	 * @return the ordenAux
	 */
	public Integer getOrdenAux() {
		return ordenAux;
	}

	/**
	 * @param ordenAux the ordenAux to set
	 */
	public void setOrdenAux(Integer ordenAux) {
		this.ordenAux = ordenAux;
	}
}
