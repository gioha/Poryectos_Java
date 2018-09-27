/**
 * @(#)BSDBandejaSeguimientoInterface.java 07/12/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;

/**
 * @author Homero Fidel Villanueva
 *
 */
public interface BSDBandejaSeguimientoInterface {

	/**
	 * Método que obtiene a una persona a través de su cuenta institucional la
	 * cual se registra en la tabla de ESTRUCTURA_AREA en el campo de
	 * CUENTA_LDAP
	 * 
	 * @param cuentaINE
	 *            : Cadena que contiene la cuenta institucional SIN EL @ine.mx
	 * @return DTOEstructuraAreasEntity: Objeto con el registro encontrado.
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 30/08/2017
	 */
	public DTOEstructuraAreasEntity consultarPersonaXCuenta(String cuentaINE);
	
	/**
	 * Método que regresa todos los comentarios que tiene un documento y no han
	 * sido leidos.
	 * 
	 * @param dtoDocumentoEntity
	 *            El documento del que se quieren los comentarios.
	 * @return Regresa una lista de Comentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public List<DTOComentariosNoLeidos> consultarComentariosNoLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona) throws Exception;
	
	/**
	 * Método que regresa todos los comentarios que tiene un documento y ya han
	 * sido leidos.
	 * 
	 * @param dtoDocumentoEntity
	 *            El documento del que se quieren los comentarios.
	 * @return Regresa una lista de Comentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public List<DTOComentariosNoLeidos> consultarComentariosLeidos(DTODocumentoEntity dtoDocumentoEntity, DTOEstructuraAreasEntity persona) throws Exception;
	
	/**
	 * Método que recibe una lista de comentarios no leidos y actualiza su
	 * estatus en la tabla "COMENTARIOS_DOCUMENTO"
	 * 
	 * @param listaComentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public void leerComentarios(List<DTOComentariosNoLeidos> listaComentarios);
	
	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * borrador y una persona.
	 * 
	 * @param persona: persona a la cual se le activará o desactivara la notificación
	 * @param enviado:
	 * @param activar: Sí es true activará la notificación, en caso contrario desactivará la notificación
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/12/2017
	 */
	public void notificacionBandejaBorradores(DTOEstructuraAreasEntity persona, DTOEnviadosDocumentosEntity enviado, boolean activar);
	
	/**
	 * Método que regresa el número de documentos pendientes para ser revisados
	 * en la bandeja de Entrada.
	 * 
	 * @param persona
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/12/2017
	 */
	public int consultarNotificacionesBEntrada(DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que regresa el número de documentos pendientes para ser revisados
	 * en la bandeja de Borradores.
	 * 
	 * @param persona
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/12/2017
	 */
	public int consultarNotificacionesBBorradores(DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que regresa el número de documentos pendientes para ser revisados
	 * en la bandeja de Enviados.
	 * 
	 * @param persona
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/12/2017
	 */
	public int consultarNotificacionesBEnviados(DTOEstructuraAreasEntity persona);
	
	/**
	 * Método que consulta todos los anexos de un documento.
	 * 
	 * @param dtoDocumentoEntity: Objeto DTODocumento del cual se quieren obtener sus anexos
	 * @return: Lista de anexos del documento recibido como parametro.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/01/2018
	 */
	public List<DTODocumentoAnexoEntity> consultarAnexos(DTODocumentoEntity dtoDocumentoEntity);
	
	/**
	 * Método que regresa la lista de remitentes transformados a un
	 * DTOEstructuraAreasEntity del documento recibido
	 * 
	 * @param documento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTOEstructuraAreasEntity> obtenerRemitentes(DTODocumentoEntity documento) throws Exception ;
	
	/**
	 * Método que regresa la lista de CCP transformados a un
	 * DTOEstructuraAreasEntity del documento recibido
	 * 
	 * @param documento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTOEstructuraAreasEntity> obtenerPersonasCCP(DTODocumentoEntity documento) throws Exception ;
	
	/**
	 * Método que regresa la lista de Destinatarios transformado en una lista
	 * DTODocumentoDestinatarioEntity y con join en la tabla "ESTRUCTURA_AREAS"
	 * y "C_AREAS"
	 * 
	 * @param documento
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTODocumentoDestinatarioEntity> obtenerDestinatarios(DTODocumentoEntity documento) throws Exception ;
	 
}
