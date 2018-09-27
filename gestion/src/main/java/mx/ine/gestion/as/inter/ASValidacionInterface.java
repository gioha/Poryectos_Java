/**
 * @(#)ASValidacionInterface.java 12/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;

/**
 * Interface encargada de administrar el o los DAO del módulo de 
 * Validación.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 14/09/2017
 */
public interface ASValidacionInterface {
	
	/**
	 * Método que consulta en la bd los documentos que tiene el usuario para ser
	 * validados
	 * 
	 * @param idPersona : id de la persona que esta en sesión.
	 * 
	 * @return List<DTOValidacionDocumentosEntity>: lista con los documentos del
	 * usuario en sesión para su validación.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 11/10/2017
	 */
	public List<DTOValidacionDocumentosEntity> consultarDocsParaValidacion(Integer idPersona) throws Exception;
	
	/**
	 * Método que inserta un comentario para un documento en la tabla de COMENTARIOS_DOCUMENTO 
	 * 
	 * @param dtoComentario: lista de comentarios a insertar en la bd de los documentos
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/09/2017
	 */
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona) throws Exception;
	
	/**
	 * Método que elimina los registros de la tabla de VALIDACION_DOCUMENTOS 
	 * 
	 * @param dtoDocumentosSeleccionados: lista de documentos a eliminar de la bd 
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 28/09/2017
	 */
	public void eliminarValidacion(List<DTOValidacionDocumentosEntity> dtoDocumentosSeleccionados) throws Exception ;
	
	/**
	 * Método que prende la bandera con_comentarios en la tabla BORRADOR_DOCUMENTOS para indicar que el documento
	 * tiene comentarios
	 * 
	 * @param DTOComentariosDocumentoEntity: lista de comentarios 
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public void activarComentario(List<DTOComentariosDocumentoEntity> dtoComentarios) throws Exception ;
	
	/**
	 * Método que obtiene un registro de la tabla BORRADOR_DOCUMENTOS
	 * 
	 * @return Objeto de tipo DTOBorradorDocumentosEntity que guarda un registro
	 * 
	 * @param idDocumento: id del documento a buscar
	 * @param idPersona: id de la persona a buscar  
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona) throws Exception;
	
	/**
	 * Método que que registra en el historial las acciones realizadas
	 * 
	 * @param dtoHistorial: lista historial de documentos 
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public void registrarEnHistorial(List<DTOHistDocCreacionEntity> dtoHistorial) throws Exception;
	
	/**
	 * Método que regresa los oficios al remitente
	 * 
	 * @param dtoLSeleccionados: lista de oficios que se van a regresar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 15/10/2017
	 */
	public void regresarARemitente(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception;

	/**
	 * Método para validar en la base de datos
	 * 
	 * @param dtoLSeleccionados: lista de oficios que se van a validar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/10/2017
	 */
	public void validar(List<DTOValidacionDocumentosEntity> dtoLSeleccionados) throws Exception;
	
	
	/**
	 * Método para indicar que se visualizó un documento en la bandeja de validación
	 * 
	 * @param idPersona: Integer con el id de la persona en el sistema en el momento.
	 * @param idPersonaRemitente: Integer con el id del remitente.
	 * @param idDocumento: Integer con el id del documento que se regresó a validar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/11/2017
	 */
	public void insertarVisualizacion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception ;
	
	/**
	 * Método para indicar que se editó un documento en la bandeja de validación
	 * 
	 * @param idPersona: Integer con el id de la persona en el sistema en el momento.
	 * @param idPersonaRemitente: Integer con el id del remitente.
	 * @param idDocumento: Integer con el id del documento que se regresó a validar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/11/2017
	 */
	public void insertarEdicion(Integer idPersona, Integer idPersonaRemitente, Integer idDocumento) throws Exception ;

	/**
	 * Método que inserta un comentario para un documento en la tabla de COMENTARIOS_DOCUMENTO 
	 * 
	 * @param dtoComentario: comentario
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 07/11/2017
	 */
	public void guardarComentario(DTOValidacionDocumentosEntity dtoValidacion, DTOEstructuraAreasEntity persona) throws Exception ;
	
	public void eliminarComentario(DTOValidacionDocumentosEntity dtoValidacion) throws Exception  ;
	
	/**
	 * Método para cambiar a leído una validación cuando se selecciona o se realiza otra acción
	 * 
	 * @param dtoValidacion: objeto dto con la validación que se va a actualizar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 19/11/2017
	 */	
	public void cambiarALeido(DTOValidacionDocumentosEntity dtoValidacion) throws Exception;
	
	/**
	 * Método para obtener las listas de Remitentes, Destinatarios y Ccp de cada documento
	 * 
	 * @param idDocumento: Integer con el id del documento
	 * 
	 * @return DTODocumentoOficialiaHelper: objeto con los remitentes, destinatarios y ccps separados por comas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/01/2018
	 */
	public DTODocumentoOficialiaHelper obtenerDetalleDocumento(Integer idDocumento) throws Exception;
	
	/**
	 * Método para obtener la los anexos de un documento.
	 * 
	 * @param documento
	 *            documento del cual se quieren los anexos.
	 * @return los anexos de dicho documento.
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/01/2018
	 */
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception;
	
	/**
	 * Método para consultar las acciones realizadas en cada documento
	 * 
	 * @param idDocumento: id del documento 
	 * @return lista List<DTOHistDocCreacionEntity> con las acciones registradas
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/01/2018
	 */
	public List<DTOHistDocCreacionEntity> consultarHistorialPorIdDocumento(int idDocumento) throws Exception;
	
	/**
	 * Método que regresa todos los comentarios
	 * 
	 * @param dtoDocumentoEntity
	 *            El documento del que se quieren los comentarios.
	 * @return Regresa una lista de Comentarios
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 04/01/2018
	 */
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception;
}
