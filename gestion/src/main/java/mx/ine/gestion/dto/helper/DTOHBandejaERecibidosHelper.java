/**
 * @(#)DTOHBandejaERecibidosHelper.java 10/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.dto.db.DTOHBandejaERecibidosEntity;
import mx.ine.gestion.util.Utilidades;

/**
 * @author Homero Fidel Villanueva
 *
 */
public class DTOHBandejaERecibidosHelper implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 7297026154701400496L;

	/**
	 * Atributo que sirve para guardar los filtros para realizar la consulta de
	 * la lista Lazy
	 */
	private List<DTOHBandejaERecibidosEntity> recibidos;

	/**
	 * Atributo que guarda en una lista los campos por los cuales será ordenada
	 * la lista Lazy
	 */
	private List<String> camposOrdenamiento;

	/**
	 * Atributo que guarda en una lista el tipo de ordenamiento de cada campo.
	 * En cada elemento se puede guardar un "asc" o "desc"
	 */
	private List<String> listaOrden;

	/**
	 * Atributo auxiliar que sirve para poder realizar diferentes operaciones
	 * sobre el atributo "camposOrdenamiento"
	 */
	private List<String> auxCamposOrdenamiento;

	/**
	 * Atributo auxiliar que sirve para poder realizar diferentes operaciones
	 * sobre el atributo "listaOrden"
	 */
	private List<String> auxListaOrden;

	public DTOHBandejaERecibidosHelper() {
		recibidos = new ArrayList<DTOHBandejaERecibidosEntity>();
		camposOrdenamiento = new ArrayList<String>();
		listaOrden =  new ArrayList<String>();
	}

	/**
	 * Método que regresa una cadena con todos los filtros que se realizará en
	 * la busqueda de la lista Lazy. En caso de que no se tenga ningun filtro
	 * regresará un ""
	 * 
	 * @param conAlias
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/01/2018
	 */
	public String obtenerFiltro(boolean conAlias) {
		String cad = "";
		boolean hayDatos = false;
		for (DTOHBandejaERecibidosEntity elemento : recibidos) {
			cad = (hayDatos) ? cad + " OR " : cad;
			cad += "(";
			cad += elemento.obtenerAtributos(conAlias);
			cad += ")";
			hayDatos = true;
		}

		return (hayDatos) ? " WHERE " + cad : cad;
	}

	/**
	 * Método que agrega un campo de ordenamiento a la lista
	 * "camposOrdenamiento" y un tipo de ordenamiento en "listaOrden".
	 * 
	 * @param campo
	 *            : El campo de ordenamiento
	 * @param esPrimero
	 *            : Si es true agrega el campo en primer lugar, en caso
	 *            contrario el campo se agrega en el ultimo lugar.
	 * @param esAscendente
	 *            : Si es true la cadena que se insertará en "listaOrden" será
	 *            "asc", en caso contrario será "desc"
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 14/12/2017
	 */
	public void agregarCampoOrdenamiento(String campo, boolean esPrimero, boolean esAscendente) {
		if (esPrimero && camposOrdenamiento.size() > 0) {
			auxCamposOrdenamiento = new ArrayList<String>();
			auxCamposOrdenamiento.add(campo);
			
			auxListaOrden = new ArrayList<String>();
			auxListaOrden.add((esAscendente)? "ASC":"DESC" );
			for (int i = 0; i < camposOrdenamiento.size(); i++) {
				auxCamposOrdenamiento.add(camposOrdenamiento.get(i));
				auxListaOrden.add(listaOrden.get(i));
			}
			camposOrdenamiento = auxCamposOrdenamiento;
			listaOrden = auxListaOrden;
		} else {
			camposOrdenamiento.add(campo);
			listaOrden.add((esAscendente)? "ASC":"DESC" );
		}
	}
	
	/**
	 * Método que regesa la cadena de ordenamiento de la lista Lazy. Previamente
	 * se deben haber ingresado campos de ordenamiento.
	 * 
	 * @return: Cadena que se utiliza en el ordenamiento de la lista Lazy
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/12/2017
	 */
	public String obtenerOrdenamiento() {
		String cad = "";
		if (camposOrdenamiento.size() > 0) {
			cad = " ORDER BY ";

			for (int i = 0; i < camposOrdenamiento.size(); i++) {
				cad += camposOrdenamiento.get(i)+" "+listaOrden.get(i) + " , ";
			}
			cad = cad.substring(0, cad.length() - 3);
		}
		return cad;
	}
	
	/**
	 * Método que agrega el campo de ordenamiento por folio de documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/12/2017
	 */
	public void ordenarPorNombre(boolean esAscendente) {
		camposOrdenamiento = new ArrayList<String>();
		listaOrden =  new ArrayList<String>();
		agregarCampoOrdenamiento("DOC.NUM_DOCUMENTO", true, esAscendente);
	}

	/**
	 * Método que agrega el campo de ordenamiento por fecha de recepción del documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/12/2017
	 */
	public void ordenarPorFecha(boolean esAscendente) {
		camposOrdenamiento = new ArrayList<String>();
		listaOrden =  new ArrayList<String>();
		agregarCampoOrdenamiento(Utilidades.mensajeProperties("alias_tabla_bandeja_e_recibidos") + "." +"FECHA_RECEPCION", true, esAscendente);
	}

	/**
	 * @return valor de tipo List<DTOBandejaERecibidosEntity> que esta contenido
	 *         en la variable recibidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public List<DTOHBandejaERecibidosEntity> getRecibidos() {
		return recibidos;
	}

	/**
	 * @param recibidos
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOBandejaERecibidosEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public void setRecibidos(List<DTOHBandejaERecibidosEntity> recibidos) {
		this.recibidos = recibidos;
	}
}
