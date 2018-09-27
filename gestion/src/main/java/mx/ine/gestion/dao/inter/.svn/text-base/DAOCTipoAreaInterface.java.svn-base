/**
 * @(#)DAOCTipoAreaInterface.java 30/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de C_TIPO_AREA del esquema GESTION 4
 * 
 * @author Roberto Shirásago Domínguez
 * @since 30/11/2017
 */
public interface DAOCTipoAreaInterface extends DAOGenericGestionInterface<DTOCTipoAreaEntity, Integer> {

	/**
	 * Método para consultar los tipos de área que sí tengan documentos;
	 * 
	 * @return List<DTOCTipoAreaEntity> lista con los tipos de área con documentos
	 * @throws Exception
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/12/2017
	 */
	public List<DTOCTipoAreaEntity> consultarTipoAreaConDocumento();
	
	/**
	 * Método para consultar un tipo de área por su ID;
	 * 
	 * @return DTOCTipoAreaEntity objeto con el tipo de área encontrado
	 * @throws Exception
	 * 
	 * @author Giovanni Hernández Alonso
	 * @since 14/03/2018
	 */
	public DTOCTipoAreaEntity consultarTipoArea( Integer idTipoArea );
}
