package mx.ine.computosINE.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DTOAgrupacionCasillas.java</code>Clase que agrupa las casillas de un escenario dado, segun su tipo de candidatura y el nivel geografico inicial con las que son consultadas, puede agrupar las casillas por secciones, distritos o regidurias tambien conocidas como demarcaciones
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 27/04/2017
 */

public class DTOAgrupacionCasillas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7824135591761020458L;

	// TODO Identificador del estado del escenario.
	private Integer idEstado;
	
	// TODO Identificador del estado del escenario.
	private Integer idMunicipio;
	
	// TODO Identificador del tipo de candidatura del escenario.
	private Integer idTipoCandidatura;
	
	// TODO nombre del tipo de candidatura del escenario.
	private String nombreTipoCandidatura;
	
	// TODO Listado de las secciones que agrupan las casillas.
	private List<DTOSeccion> secciones;
	
	// TODO Listado de los distritos que agrupan las casillas.
	private List<DTODistrito> distritos;
	
	// TODO Listado de las regidurias que agrupan las casillas.
	private List<DTORegiduria> regidurias;
	
	public DTOAgrupacionCasillas (){
		
		this.idEstado = new Integer(0);
		this.idMunicipio = new Integer(0);
		this.idTipoCandidatura = new Integer(0);
		this.nombreTipoCandidatura = "";
		this.secciones = new ArrayList<DTOSeccion>();
		this.distritos = new ArrayList<DTODistrito>();
		this.regidurias = new ArrayList<DTORegiduria>();
		
	}

	public DTOAgrupacionCasillas(Integer idEstado, Integer idMunicipio, Integer idTipoCandidatura,
			String nombreTipoCandidatura, List<DTOSeccion> secciones, List<DTODistrito> distritos,
			List<DTORegiduria> regidurias) {
		super();
		this.idEstado = idEstado;
		this.idMunicipio = idMunicipio;
		this.idTipoCandidatura = idTipoCandidatura;
		this.nombreTipoCandidatura = nombreTipoCandidatura;
		this.secciones = secciones;
		this.distritos = distritos;
		this.regidurias = regidurias;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public String getNombreTipoCandidatura() {
		return nombreTipoCandidatura;
	}

	public void setNombreTipoCandidatura(String nombreTipoCandidatura) {
		this.nombreTipoCandidatura = nombreTipoCandidatura;
	}

	public List<DTOSeccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<DTOSeccion> secciones) {
		this.secciones = secciones;
	}

	public List<DTODistrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<DTODistrito> distritos) {
		this.distritos = distritos;
	}

	public List<DTORegiduria> getRegidurias() {
		return regidurias;
	}

	public void setRegidurias(List<DTORegiduria> regidurias) {
		this.regidurias = regidurias;
	}
	
}
