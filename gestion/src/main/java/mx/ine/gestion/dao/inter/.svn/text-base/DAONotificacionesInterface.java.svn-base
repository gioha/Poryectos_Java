/**
 * @(#)DAONotificacionesInterface.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTONotificacionesID;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface DAONotificacionesInterface extends DAOGenericGestionInterface<DTONotificacionesEntity, DTONotificacionesID> {
	
	/**
	 * Método que consulta los documentosPendientes para una persona en un módulo en particular
	 * 
	 * @param idPersona: Identificador de la persona a la que se le buscan las notificaciones
	 * @param idModulo: Identificador del módulo de la persona que tiene las notificaciones
	 * @return Integer: contiene el total de documentos pendientes (suma todos los campos de documentos_pendientes.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 12/12/2017
	 */
	public Integer consultarDocumentosPendientesXIdPersonaIdModulo(Integer idPersona, Integer idModulo);

	/**
	 * 
	 * Método que actualiza o inserta un registro de la tabla "NOTIFICACIONES"
	 * dependiendo de la accion que reciba será como afectará el
	 * "numero de pendientes" de la notificación. Si el registro de la
	 * notificacion no existe el número de pendientes será 1 y no se tomará en
	 * cuenta las acciones
	 * 
	 * @param notificacion
	 *            : Objeto notificación, se requiere que se llenen previamente
	 *            el idPersona, idModulo y idApartado.
	 * @param accion
	 *            : Se pueden realizar las siguientes acciones: 1.Inicializar.
	 *            Si se inicializa el número de pendientes del registro será 0
	 *            2.Incrementar. Dependiendo el valor que se tenga en la BD el
	 *            numero de notificaciones se incrementará 3. Decrementar
	 *            Dependiendo el valor de la BD se disminuirá en 1 el valor del
	 *            número de pendientes.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/11/2017
	 */
	public void insertarNotificacion(DTONotificacionesEntity notificacion,
			String accion);

	/**
	 * Método que consulta la tabla de "NOTIFICACIONES" y regresa el registro
	 * que coincide con los parametros recibidos
	 * 
	 * @param idPerona
	 * @param idModulo
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public DTONotificacionesEntity consultarNotificacion(int idPersona, int idModulo);
}
