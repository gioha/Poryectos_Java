/**
 * @(#)BOApartadoFoliosInterface.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;


/**
 * Interfaz que contiene la firma de los métodos que tienen la lógica, procesos y reglas de negocio que se utiliza en el módulo de
 * apartado de folios para las capas de BSD, AS Y DAO
 * 
 * @author Roberto Shirásago Domínguez
 * @since 07/12/2017
 */
public interface BOApartadoFoliosInterface {

	/**
	 * Método que hace el procesamiento del campo ACRONIMO_AGRUPACION para generar el número de documento que se le da a los documentos.
	 * 
	 * @param acronimoAgrupacion: Objeto que contiene la información del campo ACRONIMO_AGRUPACION con el que se va a generar el número de documento
	 * @param numeroConsecutivoFolio: número consecutivo que le toca al folio
	 * @return String: número de documento obtenido
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 07/12/2017
	 */
	public String procesarInfoParaCrearNumeroDocumento(String acronimoAgrupacion, Integer numeroConsecutivoFolio);
}
