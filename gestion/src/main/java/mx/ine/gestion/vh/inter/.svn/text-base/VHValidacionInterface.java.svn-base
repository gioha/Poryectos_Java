	package mx.ine.gestion.vh.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @(#)VHPersonalInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */

/**
 * Interfaz que contiene la firma de los métodos que tendran la lógica 
 * y procedimientos del módulo de Personal
 *
 * @author Pável Alexei Martínez Regalado
 * @since 29/08/2017
 */
public interface VHValidacionInterface {

	
	/**
	 * Método para mostrar mensajes de growl
	 * 
	 * @param tipo: String con el tipo de mensaje que se muestra.
	 * @param titulo: String con el titulo que se muestra.
	 * @param texto: String con el texto del mensaje. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 20/10/2017
	 */	
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto);
	
	/**
	 * Método para validar campos
	 * 
	 * @param idCampo: String con el id donde se mostrará el mensaje.
	 * @param mensaje: Mensaje que se mostrará
	 * @param nota: Mensaje adicional que se mostrará. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public String obtenerInicialesNombre(String nombre);

	/**
	 * Método que recibe una lista de personas (DTOEstructuraAreasEntity) para
	 * obtener sus siglas de todos y los separa por comas.
	 * 
	 * @param listaPersonas
	 *            : La lista de la cual se obtendran las siglas
	 *            
	 * @return: Regresa la cadena con todas las iniciales de las personas
	 *          separadas por coma
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/10/2017
	 */
	public String obtenerInicialesNombre(
			List<DTOEstructuraAreasEntity> listaPersonas);
	
	/**
	 * Método para validar campos
	 * 
	 * @param idCampo: String con el id donde se mostrará el mensaje.
	 * @param mensaje: Mensaje que se mostrará
	 * @param nota: Mensaje adicional que se mostrará. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void enviarError(Exception e, String clase, String metodo, String mensaje);
	
	/**
	 * Método para validar campos
	 * 
	 * @param idCampo: String con el id donde se mostrará el mensaje.
	 * @param mensaje: Mensaje que se mostrará
	 * @param nota: Mensaje adicional que se mostrará. 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void visualizarDocumento(String nombreArchivo, String ruta, boolean soloLectura) throws Exception; 
	
}