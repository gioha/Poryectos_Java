/**
 * @(#)DTODetalleDocumentoHelper.java 03/05/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;

/**
 * @author Homero Fidel Villanueva
 * @since 03/05/2018
 *
 */
public class DTODetalleDocumentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1901202549072900888L;
	
	/**
	 * Lista donde se guardan los remitentes del documento seleccionado
	 */
	private List<DTOEstructuraAreasEntity> listaRemitentes;
	
	/**
	 * Lista donde se guardan los CCP del documento seleccionado
	 */
	private List<DTOEstructuraAreasEntity> listaCCP;
	
	/**
	 * Lista donde se guardan los destinatarios del documento seleccionado
	 */
	private List<DTODocumentoDestinatarioEntity> listaDestinatarios;

	/**
	 * Lista de comentarios 
	 */
	private List<DTOComentariosDocumentoEntity> listaComentarios;
	
	/**
	 * Lista del historial del documento
	 */
	private List<DTOHistDocCreacionEntity> listaHistorialCreacion;
	
	/**
	 * Lista del turnado del documento
	 */
	private TreeNode listaHistorialTurnado;
	
	/**
	 * Atributo que guarda el titulo que se mostrará en el dialog 
	 */
	private String tituloDetalle;
	
	/**
	 * Indica que Detalle se mostrará en el dialog
	 */
	private String tipoDetalle;

	/**
	 * @return valor de tipo String que esta contenido en la variable tituloDetalle
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public String getTituloDetalle() {
		return tituloDetalle;
	}

	/**
	 * @param tituloDetalle : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setTituloDetalle(String tituloDetalle) {
		this.tituloDetalle = tituloDetalle;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoDetalle
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public String getTipoDetalle() {
		return tipoDetalle;
	}

	/**
	 * @param tipoDetalle : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setTipoDetalle(String tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaRemitentes
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaRemitentes() {
		return listaRemitentes;
	}

	/**
	 * @param listaRemitentes : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaRemitentes(List<DTOEstructuraAreasEntity> listaRemitentes) {
		this.listaRemitentes = listaRemitentes;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaCCP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaCCP() {
		return listaCCP;
	}

	/**
	 * @param listaCCP : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaCCP(List<DTOEstructuraAreasEntity> listaCCP) {
		this.listaCCP = listaCCP;
	}

	/**
	 * @return valor de tipo List<DTODocumentoDestinatarioEntity> que esta contenido en la variable listaDestinatarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public List<DTODocumentoDestinatarioEntity> getListaDestinatarios() {
		return listaDestinatarios;
	}

	/**
	 * @param listaDestinatarios : valor que se ingresa a la variable de tipo List<DTODocumentoDestinatarioEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaDestinatarios(
			List<DTODocumentoDestinatarioEntity> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	/**
	 * @return valor de tipo List<DTOComentariosDocumentoEntity> que esta contenido en la variable listaComentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public List<DTOComentariosDocumentoEntity> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * @param listaComentarios : valor que se ingresa a la variable de tipo List<DTOComentariosDocumentoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaComentarios(
			List<DTOComentariosDocumentoEntity> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**
	 * @return valor de tipo List<DTOHistDocCreacionEntity> que esta contenido en la variable listaHistorialCreacion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public List<DTOHistDocCreacionEntity> getListaHistorialCreacion() {
		return listaHistorialCreacion;
	}

	/**
	 * @param listaHistorialCreacion : valor que se ingresa a la variable de tipo List<DTOHistDocCreacionEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaHistorialCreacion(
			List<DTOHistDocCreacionEntity> listaHistorialCreacion) {
		this.listaHistorialCreacion = listaHistorialCreacion;
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable listaHistorialTurnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public TreeNode getListaHistorialTurnado() {
		return listaHistorialTurnado;
	}

	/**
	 * @param listaHistorialTurnado : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setListaHistorialTurnado(TreeNode listaHistorialTurnado) {
		this.listaHistorialTurnado = listaHistorialTurnado;
	}

}
