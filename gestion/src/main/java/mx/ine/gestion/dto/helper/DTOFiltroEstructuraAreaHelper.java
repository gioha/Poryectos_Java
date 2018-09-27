/**
 * @(#)DTOFiltroEstructuraAreaHelper.java 14/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * Clase que contiene los filtors necesarios para realizar la consulta a la
 * tabla "ESTRUCTURA_AREAS"
 * 
 * @author Homero Fidel Villanueva
 * @since 14/02/2018
 */
public class DTOFiltroEstructuraAreaHelper implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = -5456537364783408219L;
	
	/**
	 * Atributo que guarda el "estatus" de los registros que se desea obtener.
	 * "SI" personas que no han sido borradas, "NO" personas que han sido
	 * borradas.
	 */
	private String estatus;
	
	/**
	 * Atributo que guarda el "ID_AREA" de los registros que se desean obtener.
	 */
	private Integer idArea;
	
	/**
	 * Atributo que guarda el "TIPO_AREA" de los registros que se desean obtener.
	 */
	private Integer tipoArea;
	
	/**
	 * Lista de cuentas LDAP que no deben aparecer en la busqueda.
	 */
	private List<String> cuentasLDAP;
	
	/**
	 * Lista de personas que no deben aparecer en la busqueda.
	 */
	private List<DTOEstructuraAreasEntity> listaPersonas;
	
	/**
	 * Atributo que guarda la cadena de coincidencia con la cual se buscará los
	 * registros que coincidan con ella.
	 * 
	 */
	private String coincidencia;
	
	public String obtenerFiltros(){
		String res = "";
		
		if(idArea != null){
			res += (res.equals(""))? "ID_AREA=:idArea": " AND ID_AREA=:idArea";
		}
		
		if(tipoArea != null){
			res += (res.equals(""))? "TIPO_AREA=:tipoArea": " AND TIPO_AREA=:tipoArea";
		}
		
		if(coincidencia != null && !coincidencia.trim().equals("")){
			res += (res.equals(""))? "(NOMBRE LIKE :coincidencia or APELLIDOS LIKE :coincidencia)": " AND (NOMBRE LIKE :coincidencia or APELLIDOS LIKE :coincidencia)";
		}
		
		if(cuentasLDAP != null){
			for (String cuenta : cuentasLDAP) {
				res += (res.equals(""))? "CUENTA_LDAP!='"+cuenta+"'" : " AND CUENTA_LDAP!='"+cuenta+"'";
			}
			
		}
		
		if(listaPersonas != null){
			for (DTOEstructuraAreasEntity persona : listaPersonas) {
				res += (res.equals(""))? "CUENTA_LDAP!='"+persona.getCuentaLDAP()+"'" : " AND CUENTA_LDAP!='"+persona.getCuentaLDAP()+"'";
			}
			
		}
		
		return (res.equals("")) ? "":"WHERE "+ res ;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public Integer getIdArea() {
		return idArea;
	}

	/**
	 * @param idArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public Integer getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo List<String> que esta contenido en la variable cuentasLDAP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public List<String> getCuentasLDAP() {
		return cuentasLDAP;
	}

	/**
	 * @param cuentasLDAP : valor que se ingresa a la variable de tipo List<String>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setCuentasLDAP(List<String> cuentasLDAP) {
		this.cuentasLDAP = cuentasLDAP;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable coincidencia
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public String getCoincidencia() {
		return coincidencia;
	}

	/**
	 * @param coincidencia : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setCoincidencia(String coincidencia) {
		this.coincidencia = coincidencia;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaPersonas() {
		return listaPersonas;
	}

	/**
	 * @param listaPersonas : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/02/2018
	 */
	public void setListaPersonas(List<DTOEstructuraAreasEntity> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

}
