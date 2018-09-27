/**
 * @(#)BSDRemitentesExternosInterface.java 03/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;

/**
 * Interfaz donde se declararan los métodos de la clase BSDRemitentesExternos, dicha clase
 * sirve para gestionar los métodos utilizados por la vista de Remitentes Externos.
 * 
 * @author José Miguel Ortiz
 * @since 03/04/2018
 */
public interface BSDRemitentesExternosInterface {
	/**
	 * Método que obtiene una lista de áreas de acuerdo al rol del usuario logueado.
	 * @return List<DTOCAreaEntity>: Lista de áreas recuperadas
	 * @author José Miguel Ortiz
	 * @param String rolUsuario, Integer idOficialia
	 * @since 03/04/2018
	 */
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia);

	/**
	 * Método que obtiene una lista de remitentes externos para su posterior visualización
	 * en la vista de Remitentes Externos.
	 * @return List<DTORemitentesExternosOfEntity>: Lista de remitentes externos recuperados
	 * @author José Miguel Ortiz
	 * @param DTOCAreaEntity dtoAreaOf 
	 * @since 03/04/2018
	 */
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExternos(Integer idArea, Integer tipoArea);

	/**
	 * Método que valida los datos del remitente externo a insertar en la vista de Remitentes Externos.
	 * @return String: Resultado de la validación
	 * @author José Miguel Ortiz
	 * @param DTORemitentesExternosOfEntity dtoRemitenteExt, List<DTORemitentesExternosOfEntity> listaRemitentesExt
	 * @since 03/04/2018
	 */
	public String validarCamposRemitente(DTORemitentesExternosOfEntity dtoRemitenteExt, List<DTORemitentesExternosOfEntity> listaRemitentesExt);

	/**
	 * Método encargado de insertar, actualizar y/o eliminar un remitente externo o una lista de ellos.
	 * Dicha lista proviene de la vista de Remitentes Externos.
	 * @return void
	 * @author José Miguel Ortiz
	 * @param dtoRemitenteExt 
	 * @param List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtAux
	 * @since 03/04/2018
	 */
	public void cargarRemitentesExt(List<DTORemitentesExternosOfEntity> listaRemitentesExt, List<DTORemitentesExternosOfEntity> listaRemitentesExtElim, DTORemitentesExternosOfEntity dtoRemitenteExt);

	/**
	 * Método encargado de recuperar una lista de remitentes externos ya asignados a un documento de oficialía.
	 * @return List<Integer>: Lista de remitentes externos asignados
	 * @param idArea
	 * @author José Miguel Ortiz
	 * @since 03/04/2018
	 */
	public List<Integer> recuperarRemitentesExtAsignados(Integer idArea);

	/**
	 * Método que recupera a un remitente externo desde la BD.
	 * @return DTORemitentesExternosOfEntity: Remitente externo recuperado
	 * @author José Miguel Ortiz
	 * @param Integer idRemitente, Integer idArea, Integer tipoArea
	 * @since 03/05/2018
	 */
	public DTORemitentesExternosOfEntity recuperarRemitentePorId(Integer idRemitente, Integer idArea, Integer tipoArea);

}
