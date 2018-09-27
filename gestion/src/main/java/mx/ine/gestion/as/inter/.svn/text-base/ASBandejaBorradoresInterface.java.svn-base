/**
 * ASBorradorDocumentosInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;

/**
 * Interface que contiene la firma de los métodos que acceden a el o los DAO que
 * serán ocupados en el módulo de Bandeja de Borradores del sistema de Gestión.
 * 
 * @author Homero Villanueva Dominguez
 * @since 05/08/2017
 *
 */
public interface ASBandejaBorradoresInterface {

	/**
	 * 
	 * Método que regersa los registros de la tabla "BORRADOR_DOCUMENTOS" que
	 * pertenecen al usuario que ha iniciado sesión.
	 * 
	 * @param columnaOrdenamiento
	 *            : Parametro de tipo String que indica la columna por la cual
	 *            se ordenarán los registros. Puede ser "fecha" o "nombre". Por
	 *            default se ordenará por "nombre".
	 * 
	 * @param tipoOrdenamiento
	 *            : Parametro de tipo boolean que indica si el ordenamiento será
	 *            ascendente o descendente. Por default el ordenmaiento de la
	 *            consulta será ascendente.
	 * 
	 * 
	 * @return: Lita de Borradores Documentos que cumplan con los parametros
	 *          recibidos y que tengan un estatus "Activo"
	 * @throws Exception
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public List<DTOBorradorDocumentosEntity> consultarBorradores(DTOBorradorDocumentosHelper helperBorradores) throws Exception;

	/**
	 * Método que regresa todos los registros de la tabla "ESTRUCTURA_AREAS" que
	 * coincidan con el idArea y tipoArea de la persona que haya iniciado sesión
	 * y cuyos registros coincidan en el nombre o los apellidos con la cadena
	 * coincidencia .
	 * 
	 * Excluye el registro que tiene la misma cuentaLDAP que la persona que haya
	 * iniciado sesión.
	 * 
	 * @param coincidencia
	 *            : Si la cadena es null la consulta regresará todos los
	 *            registros de la tabla "ESTRUCTURA_AREAS" que coincidan en el
	 *            Id Área y tipo área de la persona que inició sesión. En caso
	 *            que la cadena no sea null el método regresara todos los
	 *            registros la tabla "ESTRUCTURA_AREAS" que coincidan en el Id
	 *            Área y tipo área de la persona que inició sesión y que
	 *            coincidan en el nombre o apellido con la cadena "coincidencia"
	 * 
	 * @return Lista de todos los registros de la tabla "ESTRUCTURA_AREAS" que
	 *         tienen el mismo Id del Área y tipo área de la persona que inició
	 *         sesión y que contengan la cadena "coincidencia" en el nombre o
	 *         apellido.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/10/2017
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasXArea(DTOEstructuraAreasEntity persona, String coincidencia) throws Exception ;

	/**
	 * Método que consulta todos los titulares de las diferentes áreas y regresa
	 * una lista de objetos DTOJerarquiaPersonasEntity.
	 * 
	 * @return Lista de objetos DTOJerarquiaPersonasEntity que contiene todos
	 *         los titulares
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/09/2017
	 */
	public List<DTOJerarquiaPersonasEntity> consultaTitulares() throws Exception;

	/**
	 * Método que revisa en la tabla "JERARQUIA_PERSONAS" todos los titulares de
	 * cada área. Estos registros los convierte en objetos
	 * DTOEstructuraAreasEntity.
	 * 
	 * @return List<DTOEstructuraAreasEntity>: Lista que contiene todos los
	 *         titulares de cada área.
	 * 
	 * @author Homero Villanueva Dominguez
	 * @since 05/09/17
	 */
	public List<DTOEstructuraAreasEntity> consultarEstructurasTitulares(String coincidencia) throws Exception;

	/**
	 * 
	 * Método que inserta un registro en la tabla "FIRMA_DOCUMENTOS".
	 * 
	 * @param listaDestinatarios
	 * @param DTOBorradorDocumentosEntity
	 * @param remitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/10/2017
	 */
	public void enviarFirmar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity DTOBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception;

	/**
	 * Método que elimina lógicamente el documento asociado al borrador recibido
	 * y elimina el registro del borrador de la tabla "BORRADOR_DOCUMENTOS".
	 * 
	 * @param dtoBorradorDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/10/2017
	 */
	public void eliminarDocumentoBorrador(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity dtoEstructuraAreasEntity) throws Exception ;
	
	/**
	 * Método que inserta un registro en la tabla "VALIDACION_DOCUMENTOS".
	 * 
	 * @param listaDestinatarios
	 * @param dtoBorradorDocumentosEntity
	 * @param remitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/10/2017
	 */
	public void enviarValidar(List<DTOEstructuraAreasEntity> listaDestinatarios, DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity, DTOEstructuraAreasEntity remitente) throws Exception ;

	/**
	 * Método que recibe una lista de comentarios no leidos y actualiza su
	 * estatus en la tabla "COMENTARIOS_DOCUMENTO". Cambia el atributo conComentarios del borrador recibido  
	 * 
	 * @param listaComentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/11/2017
	 */
	public void leerComentarios(DTOBorradorDocumentosEntity borrador, List<DTOComentariosNoLeidos> listaComentarios) throws Exception;

	/**
	 * Método que regresa la lista de ediciones del documento que tiene el ID
	 * recibido.
	 * 
	 * @param idDocumento
	 *            : ID del documento del cual se quieren visualizar la lista de
	 *            ediciones.
	 * 
	 * @param estatus
	 *            : Parametro que determina el tipo de registros que se
	 *            regresaran. Sí se recibe 0 muestra los registros que no han
	 *            sido leidos. Sí se recibe 1 muestra los registros que han sido
	 *            leidos, en cualquier otro caso se mostrarán los mensajes no
	 *            leidos.
	 * 
	 * @return: Regresa una lista con los comentarios de un determinado
	 *          documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/10/2017
	 */
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesLeidas(int idDocumento) throws Exception;
	
	/**
	 * Método que regresa la lista de ediciones del documento que tiene el ID
	 * recibido.
	 * 
	 * @param idDocumento
	 *            : ID del documento del cual se quieren visualizar la lista de
	 *            ediciones.
	 * 
	 * @param estatus
	 *            : Parametro que determina el tipo de registros que se
	 *            regresaran. Sí se recibe 0 muestra los registros que no han
	 *            sido leidos. Sí se recibe 1 muestra los registros que han sido
	 *            leidos, en cualquier otro caso se mostrarán los mensajes no
	 *            leidos.
	 * 
	 * @return: Regresa una lista con los comentarios de un determinado
	 *          documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 26/10/2017
	 */
	public List<DTOEdicionesDocumentoEntity> obtenerListaEdicionesNoLeidas(int idDocumento) throws Exception;

	/**
	 * Método que: 1.Elimina el documento de la tabla "BORRADOR_DOCUMENTOS"
	 * 2.Inserta el documento en la tabla de "ENVIADOS_DOCUMENTOS" 3.Inserta los
	 * destinatarios a la bandeja de recibidos 4.Inserta los CPP del documento a
	 * la bandeja cpp
	 * 
	 * @param documento
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/11/2017
	 */
	public void enviarDestinatario(DTOBorradorDocumentosEntity borrador, DTOEstructuraAreasEntity persona) throws Exception ;

	/**
	 * Método que actualiza un Borrador
	 * 
	 * @param DTOBorradorDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void actualizarBorrador(DTOBorradorDocumentosEntity DTOBorradorDocumentosEntity) throws Exception;

	/**
	 * Método que activa o desactiva las notificaciones para un documento
	 * borrador y una persona.
	 * 
	 * @param persona: persona a la cual se le activará o desactivara la notificación
	 * @param activar: Sí es true activará la notificación, en caso contrario desactivará la notificación
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/12/2017
	 */
	public void notificacionBandejaBorradores(DTOEstructuraAreasEntity persona, DTOBorradorDocumentosEntity borrador , boolean activar) throws Exception;
	
}