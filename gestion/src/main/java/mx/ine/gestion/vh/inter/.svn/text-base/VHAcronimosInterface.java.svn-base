package mx.ine.gestion.vh.inter;

import java.util.HashMap;
import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOAcronimoHelper;

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
public interface VHAcronimosInterface {
	
	/**
	 * Método para traer las posibles opciones de formato de año
	 * 
	 * @return HashMap<String, String> mapa que contiene las opciones disponibles de formato de año
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public HashMap<String, String> obtenerFormatosAnio();

	/**
	 * Método para generar el acrónimo con los datos proporcionados
	 * 
	 * @param DTOAcronimoHelper acronimo: dto con la información del acrónimo para generar la estructura
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void generarAcronimo(DTOAcronimoHelper acronimo, int coor1, int coor2, String valor);
	
	/**
	 * Método para generar una vista previa con los datos proporcionados y la estructura
	 * 
	 * @param DTOAcronimoHelper acronimo: dto con la información del acrónimo para generar la vista previa
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void generarVistaPrevia(DTOAcronimoHelper acronimo);
	
	/**
	 * Método para inicializar un acrónimo nuevo
	 * 
	 * @param DTOAcronimoHelper acronimo: dto con el acrónimo que se va a inicializar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void inicializarAcronimo(DTOAcronimoHelper acronimo);
	
	/**
	 * Método para generar el acrónimo con los datos proporcionados
	 * 
	 * @param String tipo: tipo de mensaje que se va a mostrar
	 * @param String titulo: título que se quiera mostrar
	 * @param String texto: mensaje que se quiera mostrar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto);
	
	/**
	 * Método para generar el acrónimo que se guardará en la base con los datos proporcionados
	 * 
	 * @param DTOAcronimoHelper acronimo: dto con la información del acrónimo para generar el acrónimo
	 * 
	 * @return String con el acrónimo como se guardará en la base de datos
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public String generarAcronimoBase(DTOAcronimoHelper acronimo);

	/**
	 * Método para generar el acrónimo que se guardará en la base con los datos proporcionados
	 * 
	 * @param DTOAcronimoEntity acronimo: dto con la información del acrónimo para la edición
	 * @param DTOAcronimoHelper acronimoHelper: dto con la estructura que se generará con la información del otro dto
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void inicializarAcronimoGuardado(DTOAcronimoEntity acronimo, DTOAcronimoHelper acronimoHelper);
	
	public void enviarError(Exception e, String clase, String metodo, String mensaje);

	/**
	 * Método para obtener el nombre de un área dentro de una lista.
	 * 
	 * @param areas: lista de DTOCAreas con las áreas.
	 * @param idArea: Integer con el id del área del que se quiere el nombre.
	 * 
	 * @return String: nombre del área requerida.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/09/2017
	 */
	public String obtenerDescripcionDeListaAreas(List<DTOCAreaEntity> areas, Integer idArea);
	
	/**
	 * Método para obtener el nombre del estado por su id
	 * 
	 * @param estados: List<DTOEstados> todos los estados
	 * @param idEstado: Integer id del estado que buscamos el nombre
	 * 
	 * @return String con el nombre del estado
	 */
	public String obtenerDescripcionDeEntidades(List<DTOEstadosEntity> estados, Integer idEstado);
	

	public String obtenerDescripcionTipoArea(List<DTOCTipoAreaEntity> tiposArea, Integer idTipoArea);

}
