/**
 * @(#)DAOOficialiaInterface.java 31/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTOOficialiaEntity;

/**
 * Interfaz que contiene la firma de los métodos que consultaran
 * a la tabla OFICIALIAS del esquema GESTION4.
 * 
 * @author Roberto Shirásago Domínguez
 * @since 31/08/2017
 */
public interface DAOOficialiaInterface extends DAOGenericGestionInterface<DTOOficialiaEntity, Integer> {

	/**
	 * Método que busca a una persona registrada como de oficialia de partes por su cuenta institucional.
	 * 
	 * @param cuentaINE: cadena con la cuenta institucional
	 * @return DTOOficialiaEntity: Objeto con la información del registro encontrado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 31/08/2017
	 */
	public DTOOficialiaEntity consultarOficialiaXCuenta(String cuentaINE);
	
	/**
	 * Método que busca a una persona registrada como de oficialia de partes por su 
	 * cuenta institucional ACTIVA en el sistema de gestión (no ha sido borrada).
	 * 
	 * @param cuentaINE: cadena con la cuenta institucional
	 * @return DTOOficialiaEntity: Objeto con la información del registro encontrado
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 08/11/2017
	 */
	public DTOOficialiaEntity consultarOficialiaXCuentaActiva(String cuentaINE);
	
	/**
	 * Método para cambiar el estatus de las oficialías que ya no se encuentren en el organigrama.
	 * 
	 * @param Integer idOficialia: id de la oficialía
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 26/11/2017
	 */
	public void actualizarEstatusOficialia(Integer idOficialia);
	
}
