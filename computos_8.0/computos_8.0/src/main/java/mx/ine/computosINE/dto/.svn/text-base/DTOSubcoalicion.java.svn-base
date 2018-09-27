package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DTOSubcoalicion extends DTOCSubcoaliciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4553731878257681183L;

	// es el id_coalicion-id_subcoalicion
	private String cveSubcoalicion;

	private BigDecimal votos;

	private BigDecimal votosAcumulados;
	
	
	public DTOSubcoalicion(){
		this.cveSubcoalicion 	= "";
		//this.votos 				= new BigDecimal(0);
		this.votosAcumulados 	= new BigDecimal(0);
	}


	public DTOSubcoalicion( DTOCSubcoaliciones s){
		
		this.cveSubcoalicion 	= s.getIdCoalicion() +"_"+ s.getIdSubcoalicion();
		//this.votos 				= new BigDecimal(0);
		this.votosAcumulados 	= new BigDecimal(0);
		
		setIdCoalicion(s.getIdCoalicion());
		setIdSubcoalicion(s.getIdSubcoalicion());
		setEmblema(s.getEmblema());
		setOrden(s.getOrden());
		setSiglas(s.getSiglas());
		setUsuario(s.getUsuario());
		setFechaHora(s.getFechaHora());
	}
	
	
	public String getCveSubcoalicion() {
		return cveSubcoalicion;
	}

	public void setCveSubcoalicion(String cveSubcoalicion) {
		this.cveSubcoalicion = cveSubcoalicion;
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
	
}
