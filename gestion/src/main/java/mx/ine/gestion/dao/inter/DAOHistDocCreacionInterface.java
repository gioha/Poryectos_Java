/**
 * DAOHistDocCreacion.java 18/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionID;

/**
 * @author Homero Villanueva Dominguez 18/09/2017
 */
public interface DAOHistDocCreacionInterface
		extends
		DAOGenericGestionInterface<DTOHistDocCreacionEntity, DTOHistDocCreacionID> {

	/**
	 * 
	 * Método que inserta en la tabla "HIST_DOC_CREACION" el historico de un
	 * determinado documento
	 * 
	 * @param idDocumento
	 *            : Id del Documento
	 * @param anio
	 *            : Año de creación del Documento
	 * @param idPersonaHist
	 *            : Id de la persona que es asignado el estatus
	 * @param idEstatus
	 *            : Id del Estatus
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/09/2017
	 */
	public void guardarHistCrea(DTOHistDocCreacionEntity dtoHistDocCreacionEntity);
//
//	/**
//	 * Método que inserta en la tabla "HIST_DOC_CREACION" el historico de un
//	 * determinado borrador.
//	 * 
//	 * @param dtoBorradorDocumentosEntity
//	 * @param idEstatus
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/10/2017
//	 */
//	public void insertar(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity,int idEstatus);
//
//	/**
//	 * Método que inserta en la tabla "HIST_DOC_CREACION" el historico de un
//	 * determinado documento.
//	 * 
//	 * @param dtoDocumento
//	 *            : Objeto del cual se utilizarán sus atributos: idDocumento y
//	 *            anio
//	 * @param dtoEstructuraAreasEntity
//	 *            : Objeto del cual se obtendrá el Id de la persona a la que se
//	 *            le fue asignada el estatus
//	 * @param idEstatus
//	 *            : El estatus del documento
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 19/09/2017
//	 */
//	public void insertar(DTODocumentoEntity dtoDocumento,DTOEstructuraAreasEntity dtoEstructuraAreasEntity, int idEstatus);

//	/**
//	 * Método que inserta en la tabla "HIST_DOC_CREACION" una lista de personas
//	 * (DTOEstructuraAreas) que tienen en común un Documento y un mismo estatus.
//	 * 
//	 * @param dtoDocumento
//	 *            : El documento compartido por la lista de personas.
//	 * @param listaDTOEstructuraAreasEntity
//	 *            : La lista de personas(DTOEstructuraAreas)
//	 * @param idEstatus
//	 *            : Estatus compartido por la lista de personas.
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 05/10/2017
//	 */
//	public void insertar(DTODocumentoEntity dtoDocumento, List<DTOEstructuraAreasEntity> listaDTOEstructuraAreasEntity, int idEstatus);

	/**
	 * Método que que registra en el historial de creación
	 * "regresar a remitente"
	 * 
	 * @param dtoHistorial
	 *            : lista historial de documentos
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public void registrarEnHistorial(DTOHistDocCreacionEntity dtoHistorial);
	
	/**
	 * Método que trae las acciones registradas para un documento que se guardan en el historial
	 * 
	 * @param idDocumento: Integer con el ID del documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 17/11/2017
	 */
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(Integer idDocumento);

	/*
	 * 
	 */
	public Integer obtenerIdHistorial(Integer idDocumento);
	
}
