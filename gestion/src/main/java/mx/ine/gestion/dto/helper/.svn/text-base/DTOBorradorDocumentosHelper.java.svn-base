/**
 * @(#)DTOBorradorDocumentosHelper.java 13/04/2018
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

/**
 * @author Homero Fidel Villanueva
 * @since 13/04/2018
 *
 */
public class DTOBorradorDocumentosHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1381593862955432617L;

	/**
	 * Atributo que guarda el nombre de la columna por la cual se ordenarán los documentos
	 */
	private String columnaOrdenamiento;
	
	/**
	 * Atributo que guarda el tipo de ordenamiento de los documentos. true =>
	 * ASC, false => DESC
	 */
	private boolean tipoOrdenamiento;
	
	/**
	 * Filtro para la busqueda de documentos. Sí estatusDocumento => 'A' se
	 * buscarán todos los documentos que estén activos. Sí estatusDocumento =>
	 * 'E' se buscarán todos los documentos que estén eliminados.
	 */
	private Character estatusDocumento;
	
	/**
	 * Filtro para la busqueda de documentos. Sí estatusBorrador => 'A' se
	 * buscarán todos los borradores que estén activos. Sí estatusBorrador =>
	 * 'E' se buscarán todos los borradores que estén eliminados.
	 */
	private Character estatusBorrador;
	
	/**
	 * Filtro donde se definen que personas se incluirán en las busquedas
	 */
	private List<DTOEstructuraAreasEntity> listaPersonas;
	
	public DTOBorradorDocumentosHelper(){
		listaPersonas = new ArrayList<DTOEstructuraAreasEntity>();
	}
	
	/**
	 * Método para agregar personas a la busqueda de borradores.
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public void agregarPersonas(DTOEstructuraAreasEntity persona){
		if(persona != null){
			listaPersonas.add(persona);
		}
	}
	
	/**
	 * Método que regresa los filtros con los que se realizará la busqueda. En
	 * caso de que no se haya agregado algun filtro se regresará una cadena
	 * vacia.
	 * 
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public String obtenerFiltros(){
		String res = "";
		String filtroPersonas = obtenerPersonas();
		boolean tieneFiltro = false;
		if(filtroPersonas != ""){
			res = (!tieneFiltro)? "WHERE " + filtroPersonas: filtroPersonas;
			tieneFiltro = true;
		}
		
		if(estatusDocumento != null){
			res = (!tieneFiltro)? "WHERE " + "DOC.ESTATUS=" + estatusDocumento : res + " AND   DOC.ESTATUS='"+estatusBorrador+"'";
			tieneFiltro = true;
		}
		if(estatusBorrador != null){
			res = (!tieneFiltro)? "WHERE " + "BOR.ESTATUS="+estatusBorrador: res + " AND BOR.ESTATUS='"+estatusBorrador+"'";
			tieneFiltro = true;
		}
		return res;
	}
	
	/**
	 * Método que regresa el ordenamiento con el que se realizará la busqueda.
	 * En caso de que no se haya agregado algun ordenamiento, se ordenará de
	 * forma DESC por la columna de fecha de creacion
	 * 
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	public String obtenerOrdenamiento(){
		String res = "ORDER BY ";
		if(columnaOrdenamiento != null){
			res = res + "DOC."+columnaOrdenamiento + " " + ((tipoOrdenamiento)? "ASC": "DESC");
		}else{
			res = res + "DOC.FECHA_CREACION" + " " + ((tipoOrdenamiento)? "ASC": "DESC");
		}
		return res;
	}
	
	/**
	 * Método donde se obtiene el fitro de las personas por el cual se obtendrán
	 * los documentos
	 * 
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/04/2018
	 */
	private String obtenerPersonas(){
		String res = "";
		
		for (DTOEstructuraAreasEntity persona : listaPersonas) {
			res = "BOR.ID_PERSONA=" + persona.getIdPersona()+" AND BOR.ID_AREA="+persona.getIdArea()+" AND BOR.TIPO_AREA="+persona.getTipoArea()+" OR";
		}
		return (!res.equals("")?"("+res.substring(0, res.length()-3)+")":"");
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable columnaOrdenamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public String getColumnaOrdenamiento() {
		return columnaOrdenamiento;
	}

	/**
	 * @param columnaOrdenamiento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setColumnaOrdenamiento(String columnaOrdenamiento) {
		this.columnaOrdenamiento = columnaOrdenamiento;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable tipoOrdenamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public boolean isTipoOrdenamiento() {
		return tipoOrdenamiento;
	}

	/**
	 * @param tipoOrdenamiento : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setTipoOrdenamiento(boolean tipoOrdenamiento) {
		this.tipoOrdenamiento = tipoOrdenamiento;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable estatusDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public char getEstatusDocumento() {
		return estatusDocumento;
	}

	/**
	 * @param estatusDocumento : valor que se ingresa a la variable de tipo char
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setEstatusDocumento(char estatusDocumento) {
		this.estatusDocumento = estatusDocumento;
	}

	/**
	 * @return valor de tipo char que esta contenido en la variable estatusBorrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public char getEstatusBorrador() {
		return estatusBorrador;
	}

	/**
	 * @param estatusBorrador : valor que se ingresa a la variable de tipo char
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setEstatusBorrador(char estatusBorrador) {
		this.estatusBorrador = estatusBorrador;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaPersonas() {
		return listaPersonas;
	}

	/**
	 * @param listaPersonas : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/04/2018
	 */
	public void setListaPersonas(List<DTOEstructuraAreasEntity> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
}
