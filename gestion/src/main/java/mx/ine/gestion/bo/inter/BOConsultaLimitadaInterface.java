/**
 * @(#)BOConsultaLimitadaInterface.java
 *
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.List;

import mx.ine.gestion.dto.helper.DTODocumentoGlusterHelper;

/**
 * Interfaz con la firma de los métodos del negocio
 *
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
public interface BOConsultaLimitadaInterface {

	/**
	 * Método para validar contraseña y permisos para una operación
	 * 
	 * @param contrasenia: cadena con la contraseña
	 * @param operacion: operacion que se lleva acabo
	 * @return String: mensaje de error en caso de haberlo, si no regresa vacio
	 *
	 * @autor INE
	 * @since 12/07/2016
	 */
	public String validarContraseniaYPermisosOperacion(String operacion, String contrasenia);

	/**
	 * Método para obtener los archivos que se encuentran en una carpeta de gluster
	 * 
	 * @param ruta : ruta dentro del gluster (debe tener permisos de lectura para verla)
	 * @return List<HLPDocumentoGluster> : archivos encontrados
	 *
	 * @autor Roberto Shirásago Domínguez
	 * @since 12/07/2016
	 */
	public List<DTODocumentoGlusterHelper> obtenerArchivosGluster(String ruta);

	/**
	 * Método para borrar archivos del gluster
	 * 
	 * @param listaArchivos: lista de archivos que se encontraron en la consulta
	 * @param archivo: archivo que sera eliminado
	 * @return boolean: bandera que indica si se borro o no el archivo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 10/08/2016
	 */
	public boolean eliminarArchivosGluster(List<DTODocumentoGlusterHelper> listaArchivos, DTODocumentoGlusterHelper archivo);

	/**
	 * Método para borrar el contenido de una carpeta
	 * 
	 * @param carpeta
	 * @return
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 11/08/2016
	 */
	public boolean eliminarContenidoCarpeta(DTODocumentoGlusterHelper carpeta);
}
