/**
 * @(#)BOOficialiaPartesInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTODocumentoFoliosEntity;
import mx.ine.gestion.dto.db.DTODocumentosClasifAreaEntity;

/**
 * Interfaz donde se declararan los métodos de la clase BO del módulo de
 * Oficialia de partes.
 *
 * @author David Rodríguez Corral
 * @since 10/12/2017
 */
public interface BOOficialiaPartesInterface {

	/**
	 * Método que crea un objeto de tipo DTOApartadosNumDocOfEntity
	 * 
	 * @param folioApartado: folio apartado
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * 
	 * @return DTOApartadosNumDocOfEntity a crear para insertar
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public DTOApartadosNumDocOfEntity crearApartadoFolioSiguienteOf(Integer folioApartado, Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, String tipoApartado);
	
	/**
	 * Método que crea un objeto de tipo DTOApartadosNumDocOfEntity que será el primer folio para el área
	 * 
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * 
	 * @return DTOApartadosNumDocOfEntity a crear
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public DTOApartadosNumDocOfEntity crearPrimerFolioOf(Integer idOficialia, Integer idArea, Integer tipoArea, String siglas, String tipoApartado);
	
	/**
	 * Método que crea un objeto de tipo DTODocumentosClasifAreaEntity
	 * 
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * @param seccionSeleccionada: Sección donde se clasificará el documento
	 * @param categoriaSeleccionada: Categoría donde se clasificará el documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public DTODocumentosClasifAreaEntity creaDocumentoClasifArea(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, 
			String seccionSeleccionada, String categoriaSeleccionada);
	
	/**
	 * Método que crea un objeto de tipo DTODocumentoFoliosEntity
	 * 
	 * @param dtoBandejaEntrada: DTOBandejaEntradasOficialiaEntity donde se guardan los datos del documento a clasificar
	 * @param folio: String que contiene el folio
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public DTODocumentoFoliosEntity creaDocumentoFolios(DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada, String folio);
	
}
