package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;

public class DTOAsociacion extends DTOCandidatoWS implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -214902615098966213L;

	// es el id_asociacion con el tipo_asociacion
	private String cveAsociacion;
	
	private BigDecimal votos;

	private BigDecimal votosAcumulados;
	
	private Integer idCoalicion;

	private List<DTOSubcoalicion> subcoaliciones;
	
	
	public DTOAsociacion(){
		this.cveAsociacion 		= "";
		//this.votos 				= new BigDecimal(0);
		this.votosAcumulados 	= new BigDecimal(0);
		this.subcoaliciones 	= new ArrayList<DTOSubcoalicion>();
		this.idCoalicion		= new Integer(0);
	}
	
	public DTOAsociacion(DTOCandidatoWS a){
		
		this.cveAsociacion 		= a.getIdAsociacion()+ "_" + a.getTipoAsociacion();
		//this.votos 				= new BigDecimal(0);
		this.votosAcumulados 	= new BigDecimal(0);
		this.subcoaliciones 	= new ArrayList<DTOSubcoalicion>();
		this.idCoalicion		= new Integer(0);
	
		// seteamos los aributos de la clase padre
		setIdEstado(a.getIdEstado());
		setIdAsociacion(a.getIdAsociacion());
		setTipoAsociacion(a.getTipoAsociacion());
		setNombreAsociacion(a.getNombreAsociacion());
		setNombreCandidato(a.getNombreCandidato());
		setEmblema(a.getEmblema());
		setSiglas(a.getSiglas());
		setOrden(a.getOrden());
		setIdTipoCandidatura(a.getIdTipoCandidatura());
		setNombreCortoTipoCandidatura(a.getNombreCortoTipoCandidatura());
		setNombreLargoTipoCandidatura(a.getNombreLargoTipoCandidatura());
	}

	
	public String getCveAsociacion() {
		return cveAsociacion;
	}

	public void setCveAsociacion(String cveAsociacion) {
		this.cveAsociacion = cveAsociacion;
	}

	public BigDecimal getVotos() {
		return votos;
	}

	public void setVotos(BigDecimal votos) {
		this.votos = votos;
	}

	public BigDecimal getVotosAcumulados() {
		return votosAcumulados;
	}

	public void setVotosAcumulados(BigDecimal votosAcumulados) {
		this.votosAcumulados = votosAcumulados;
	}

	public List<DTOSubcoalicion> getSubcoaliciones() {
		return subcoaliciones;
	}

	public void setSubcoaliciones(List<DTOSubcoalicion> subcoaliciones) {
		this.subcoaliciones = subcoaliciones;
	}
	
	public Integer getIdCoalicion() {
		return idCoalicion;
	}

	public void setIdCoalicion(Integer idCoalicion) {
		this.idCoalicion = idCoalicion;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
