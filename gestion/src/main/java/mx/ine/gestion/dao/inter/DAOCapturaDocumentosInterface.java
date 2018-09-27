/**
 * @(#)DAOCapturaDocumentos.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Interfaz DAO que contiene la firma de los métodos de los querys utilizados en la captura de documentos QUE NO VAN A UNA TABLA EN GENERAL
 * (Hacen varios cruces)
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
public interface DAOCapturaDocumentosInterface extends DAOGenericGestionInterface<Integer, Integer> {

	/**
	 * Método que obtiene las entidades que ya tienen un área registrada en gestión, ES IMPORTANTE MENCIONAR
	 * QUE NO TRAE LA ENTIDAD 0 (OFICINAS CENTRALES) SE ELIMINA DESDE EL QUERY.
	 * 
	 * @return List<DTOEstadosEntity>: registros encontrados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 01/09/2017
	 */
	public List<DTOEstadosEntity> consultarEntidadesCapturadasEnGestion();
}
