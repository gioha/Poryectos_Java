/**
 * @(#)DAOCCategoriasInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasID;

/**
 * Interface que contiene la firma de los métodos que consultaran a la tabla
 * C_CATEGORIAS del esquema GESTION4.
 * 
 * @author David Rodríguez Corral
 * @since 08/12/2017
 */
public interface DAOCCategoriasInterface extends DAOGenericGestionInterface<DTOCCategoriasEntity, DTOCCategoriasID> {

	/**
	 * Método que regresa la lista de categorias por sección
	 * 
	 * @param idSeccion: id sección de donde se consultrán las categorías
	 * 
	 * @return lista de categorías
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCCategoriasEntity> consultarCategorias(Integer idSeccion);

	
}
