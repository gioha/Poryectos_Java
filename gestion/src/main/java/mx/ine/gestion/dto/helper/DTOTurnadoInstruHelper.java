/**
 * @(#)DTOTurnadoInstruHelper.java 06/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;

/**
 * @author Homero Fidel Villanuevav
 * @since 06/03/2018
 *
 */
public class DTOTurnadoInstruHelper  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1047068629560549302L;

	/**
	 * 
	 */
	private Integer idPersonaTurno;
	
	private DTOEstructuraAreasEntity personaTurnada;
	 
	private Integer idHistoricoRecep;
	
	private List<DTOInstruccionesEntity> listaInstruccionesAtencion;
	
	private List<DTOInstruccionesEntity> listaInstruccionesInfo;

	public DTOTurnadoInstruHelper(){
		personaTurnada = new DTOEstructuraAreasEntity();
		listaInstruccionesAtencion = new ArrayList<DTOInstruccionesEntity>();
		listaInstruccionesInfo = new ArrayList<DTOInstruccionesEntity>();
	}
	
	public void agregarInstruccion(Integer id, Integer tipo, Integer ordenamiento ){

		DTOInstruccionesEntity instruccion = new DTOInstruccionesEntity();
		
		instruccion.setIdInstruccion(id);
		instruccion.setTipoInstruccion(tipo);
		instruccion.setOrdenamiento(ordenamiento);
		//Atencion
		if(tipo.equals(1)){
			listaInstruccionesAtencion.add(instruccion);
		}else{
			listaInstruccionesInfo.add(instruccion);
		}
	}
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idPersonaTurno
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public Integer getIdPersonaTurno() {
		return idPersonaTurno;
	}

	/**
	 * @param idPersonaTurno : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setIdPersonaTurno(Integer idPersonaTurno) {
		this.idPersonaTurno = idPersonaTurno;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idHistoricoRecep
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public Integer getIdHistoricoRecep() {
		return idHistoricoRecep;
	}

	/**
	 * @param idHistoricoRecep : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setIdHistoricoRecep(Integer idHistoricoRecep) {
		this.idHistoricoRecep = idHistoricoRecep;
	}

	/**
	 * @return valor de tipo List<DTOInstruccionesEntity> que esta contenido en la variable listaInstruccionesAtencion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public List<DTOInstruccionesEntity> getListaInstruccionesAtencion() {
		return listaInstruccionesAtencion;
	}

	/**
	 * @param listaInstruccionesAtencion : valor que se ingresa a la variable de tipo List<DTOInstruccionesEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setListaInstruccionesAtencion(
			List<DTOInstruccionesEntity> listaInstruccionesAtencion) {
		this.listaInstruccionesAtencion = listaInstruccionesAtencion;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable personaTurnada
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public DTOEstructuraAreasEntity getPersonaTurnada() {
		return personaTurnada;
	}

	/**
	 * @param personaTurnada : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setPersonaTurnada(DTOEstructuraAreasEntity personaTurnada) {
		this.personaTurnada = personaTurnada;
	}

	/**
	 * @return valor de tipo List<DTOInstruccionesEntity> que esta contenido en la variable listaInstruccionesInfo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public List<DTOInstruccionesEntity> getListaInstruccionesInfo() {
		return listaInstruccionesInfo;
	}

	/**
	 * @param listaInstruccionesInfo : valor que se ingresa a la variable de tipo List<DTOInstruccionesEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public void setListaInstruccionesInfo(
			List<DTOInstruccionesEntity> listaInstruccionesInfo) {
		this.listaInstruccionesInfo = listaInstruccionesInfo;
	}

}