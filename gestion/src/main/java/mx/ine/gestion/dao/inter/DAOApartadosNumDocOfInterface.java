/**
 * @(#)DAOApartadosNumDocOfInterface.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfID;

/**
 * Interface que contiene la firma de los métodos que harán 
 * consultas a la tabla de APARTADOS_NUM_DOC_OF del esquema GESTION 4
 * 
 * @author David Rodríguez Corral
 * @update José Miguel Ortiz
 * @since 26/03/2018
 */
public interface DAOApartadosNumDocOfInterface extends DAOGenericGestionInterface<DTOApartadosNumDocOfEntity, DTOApartadosNumDocOfID>{

	/**
	 * Método que consulta el primer folio con estatus L=Libre
	 * 
	 * @param idArea: id del área a consultar
	 * @param tipoArea: tipo del área a consultar
	 * 
	 * @return DTOApartadosNumDocOfEntity objeto que contiene el folio a verificar
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public DTOApartadosNumDocOfEntity consultarFolioDisponible(Integer idArea, Integer tipoArea);
	
	/**
	 * Método que obtiene el ultimo folio en caso de no haber libres
	 * 
	 * @param idArea: id del área a consultar
	 * @param tipoArea: tipo del área a consultar
	 * 
	 * @return Integer que contendrá el último folio
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public Integer obtenerUltimoFolio(Integer idArea, Integer tipoArea);
	
	/**
	 * Método que modifica el estatus del folio
	 * 
	 * @param idArea: id del área a consultar
	 * @param tipoArea: tipo del área a consultar
	 * @param folio: folio a modificar el estatus
	 * @param estatus: tipo de estatus A=Apartado, L=Libre, C=Concluido
	 * 
	 * @author David Rodríguez Corral
	 * @since 11/12/2017
	 */
	public void modificarEstatus(Integer idArea, Integer tipoArea, String folio, char estatus);

	/**
	 * Método que consulta un folio pendiente
	 * 
	 * @param idOficialia: id de la oficialia a consultar el folio
	 * @param estatus: estatus "A" que indica que el folio está apartado
	 * 
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOApartadosNumDocOfEntity consultarFolioPendiente(Integer idArea, Integer tipoArea, Integer idOficialia, char estatus, String tipoApartado);

	/**
	 * Método que consulta todos los folios existentes en BD
	 * @return List<DTOApartadosNumDocOfEntity>
	 * @author José Miguel Ortiz
	 * @since 26/03/2018
	 */
	public List<DTOApartadosNumDocOfEntity> consultarFoliosDocOf();
	
}
