/**
 * BOBorradorDocumentosInterface.java 06/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.helper.DTOFiltroEstructuraAreaHelper;
import mx.ine.gestion.dto.helper.DTOPersonaBorradoresHelper;

/**
 * @author Homero Villanueva Dominguez
 * @since 06/10/2017
 *
 */
public interface BOBandejaBorradoresInterface {

	public int revisarFimaEnBorrador(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, List<DTOFirmaDocumentosEntity> listaFirmaDocumentos);
	
	public int revisarValidacionEnBorrador();
	
	public boolean revisarPersonaEstaLista(List<DTOEstructuraAreasEntity> lista, DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que recibe un borrador documento y regresa el documento que tiene el mismo Id documento. En caso contrario regresa null
	 * 
	 * @param listaBorradores
	 * @param borrador
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 24/01/2018
	 */
	public DTOBorradorDocumentosEntity obtenerBorrador(List<DTOBorradorDocumentosEntity> listaBorradores, DTOBorradorDocumentosEntity borrador);
	
	/**
	 * Método que construye el filtro que se ocupa para realizar la busqueda de registros en la tabla "ESTRUCTURA_AREAS"
	 * 
	 * @param personaNoIncluidas: Lista de personas que no serán incluidas en la busqueda.
	 * @param persona: Objeto del cual obtiene el idPersona y el tipoArea para realizar la busqueda
	 * @param coincidencia: Cadena con la cual se buscarán coincidencias en las columnas "NOMBRE" Y "APELLIDOS" de la tabla "ESTRUCTURA_AREAS"
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/02/2018
	 */
	public DTOFiltroEstructuraAreaHelper obtenerFiltro(List<String> personaNoIncluidas, DTOEstructuraAreasEntity persona, String coincidencia );
	
	public DTOBandejaERecibidosEntity crearDocumentoRecibido(DTOEstructuraAreasEntity personaRecepecion, DTODocumentoEntity documento, Integer idHistoricoRecep);
	
	public List<DTOEstructuraAreasEntity> obtenerPersonas(List<DTOPersonaBorradoresHelper> personas);
	
	public List<DTOPersonaBorradoresHelper> obtenerPersonasBorradorHelper(List<DTOEstructuraAreasEntity> personas);
}
