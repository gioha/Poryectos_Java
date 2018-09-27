package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * <code>DTOActaCap.java</code>Clase que describe una acta de casilla, con su escenerio participantes y votos
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 06/05/2017 
 */

public class DTOActaCap extends DTOActaCasillaVotos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4376370667008509971L;
	

	private String cveCasilla;
	
	private String nombreCasilla;

	private Integer listaNominal;

	private List<DTOCandidato> partidos;
	
	private List<DTOCandidato> independientes;
	
	private List<DTOCandidato> coaliciones;
	
	public DTOActaCap(){
		
		id 					= new DTOActaCasillaVotosPK();
		idEstatus 			= new Integer(0);
		orden 				= new Integer(0);
		votos 				= new Integer(0);
		capturada 			= new Integer(0);
		listaNominal 		= new Integer(0);
		usuario 			= "";
		fechaHora 			= null;
		cabeceraDistrital 	= "";
		
		cveCasilla 			= "";
		nombreCasilla		= "";
		listaNominal 		= new Integer(0);
		partidos 			= new ArrayList<DTOCandidato>();
		independientes 		= new ArrayList<DTOCandidato>();
		coaliciones 		= new ArrayList<DTOCandidato>();
		
	}

	
	public String getCveCasilla() {
		return cveCasilla;
	}

	public void setCveCasilla(String cveCasilla) {
		this.cveCasilla = cveCasilla;
	}

	public Integer getListaNominal() {
		return listaNominal;
	}

	public void setListaNominal(Integer listaNominal) {
		this.listaNominal = listaNominal;
	}

	public List<DTOCandidato> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<DTOCandidato> partidos) {
		this.partidos = partidos;
	}

	public List<DTOCandidato> getIndependientes() {
		return independientes;
	}

	public void setIndependientes(List<DTOCandidato> independientes) {
		this.independientes = independientes;
	}

	public List<DTOCandidato> getCoaliciones() {
		return coaliciones;
	}

	public void setCoaliciones(List<DTOCandidato> coaliciones) {
		this.coaliciones = coaliciones;
	}


	public String getNombreCasilla() {
		return nombreCasilla;
	}


	public void setNombreCasilla(String nombreCasilla) {
		this.nombreCasilla = nombreCasilla;
	}
	
}
