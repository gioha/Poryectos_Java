/**
 * @(#)BSDFirmaInterface.java 14/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper; 

/**
 * Interface encargada de administrar el o los AS del módulo de 
 * Firma.
 * 
 * @author David Rodríguez Corral
 * @since 14/09/2017
 */
public interface BSDFirmaInterface {
	
	/**
	 * Método que consulta en la bd los documentos que tiene el usuario para ser firmados
	 * 
	 * @param idPersona: id de la persona que esta en sesión.
	 * @param pendienteEnvio: Entero que indica si no está firmado o está pendiente de envío (ya se firmó).
	 * 
	 * @return List<DTOInstrucciones>: Lista que contiene los documentos para ser firmados.
	 * 
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public List<DTOFirmaDocumentosEntity> consultarDocsFirmas(Integer idPersona, Integer pendienteEnvio);

	/**
	 * Método que inserta un comentario para un documento en la tabla de COMENTARIOS_DOCUMENTO 
	 * 
	 * @param dtoComentario: lista de comentarios a insertar en la bd de los documentos
	 * 
	 * @author David Rodríguez Corral
	 * @since 18/09/2017
	 */
	public void guardarComentario(List<DTOComentariosDocumentoEntity> dtoComentario, DTOEstructuraAreasEntity persona);
	
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
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento, Integer idPersona);
	
	/**
	 * Método que regresa los oficios al remitente
	 * 
	 * @param dtoLSeleccionados: lista de oficios a regresar
	 * 
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 15/10/2017
	 */
	public String regresarARemitente(List<DTOFirmaDocumentosEntity> dtoLSeleccionados) throws Exception;
	
	/**
	 * Método que verifica si ya se firmó y/o validó el documneto que se quiere editar
	 * 
	 * @param idDocumento: id documento del documento a validar
	 * 
	 * @return Integer con el mensaje a mostrar en pantalla cuando se requiera editar
	 * 
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public Integer verificarFirmaYValidacion(Integer idDocumento);
	
	/**
	 * Método que envía la información para la firma a SSign
	 * 
	 * @param certificado: Llave pública del usuario
	 * @param numeroArchivosAFirmar: Número de archivos a firmar
	 * @param dtoListaFirma: lista de documentos a firmar
	 * 
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 * @since 23/10/2017
	 */
	public List<DTOFirmaDocumentosEntity> obtenerDatosFirmas(List<DTOFirmaDocumentosEntity> dtoListaFirma, byte[] certificado, String tipoAlgoritmo) throws FileNotFoundException, IOException, Exception;

	/**
	 * Método que envía el pkcs7 generado del lado del cliente al web service para generar el ID de Secuencia
	 * 
	 * @param dtoListaDatosFirma: Lista donde se guarda la información a firmar
	 * 
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 * @since 28/10/2017
	 */
	public void enviarPKCS7(List<DTOFirmaDocumentosEntity> dtoListaDatosFirma) throws Exception, FileNotFoundException;

	/**
	 * Método que envía el documento firmado al remitente 
	 * 
	 * @param dtoDocumentosSeleccionadosFirmados: lista de documentos seleccionados firmados que serán regresados al remitente
	 * 
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @throws Exception 
	 * @since 30/10/2017
	 */
	public void regresarARemitenteDocFirmado(
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados) throws IOException, Exception;

	/**
	 * Método que envía el documento al destinatario insertando en la tabla ENVIADOS_DOCUMENTOS
	 * 
	 * @param dtoDocumentosSeleccionadosFirmados: lista de documentos seleccionados firmados que serán regresados al remitente
	 * 
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 30/10/2017
	 */
	public String enviarADestinatario( 
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados, Integer idArea, Integer tipoArea, Integer opcionEnvio) throws IOException, Exception;

	/**
	 * Método que prende la bandera con_modicaciones en la tabla de FIRMA_DOCUMENTOS
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void activarModificacionFirma(Integer idPersona, Integer idDocumento);

	/**
	 * Método que apaga la bandera validado en la tabla de DOCUMENTOS
	 * 
	 * @param dtoFirma: objeto de tipo DTOFirmaDocumentosEAntity
	 * 
	 * @author David Rodríguez Corral
	 * @since 01/11/2017
	 */
	public void eliminarValidacionEnDocumento(DTOFirmaDocumentosEntity dtoFirma);

	/**
	 * Método que activa la bandera conVisualizacion indicando que el documento ha sido visto
	 * 
	 * @param idPersona: Id de la persona
	 * @param idDocumento: Id del documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/11/2017
	 */
	public void activarVisualizacion(Integer idPersona, Integer idDocumento);
	
	/**
	 * Método que inserta un comentario para un documento en la tabla de COMENTARIOS_DOCUMENTO 
	 * 
	 * @param dtoComentario: comentario
	 * 

	 * @author David Rodríguez Corral
	 * @since 08/11/2017
	 */
	public void guardarComentario(DTOFirmaDocumentosEntity dtoFirma, DTOEstructuraAreasEntity persona);
	
	
	public void eliminarComentario(DTOFirmaDocumentosEntity dtoFirma);

	/**
	 * Método que marca como leído el documento en FIRMA_DOCUMENTOS
	 * 
	 * @param idPersona: id de la persona
	 * @param idDocumento: id del documento
	 * @param totalDocumentos: total de documentos a decrementar
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/12/2017
	 */
	public void marcarComoLeido(Integer idPersona, Integer idDocumento, Integer totalDocumentos);

	/**
	 * Método que marca como leído los documento en FIRMA_DOCUMENTOS
	 * 
	 * @param idPersona: id de la persona
	 * @param noLeidos: lista que contiene los documentos a marcar como leídos
	 * 
	 * @author David Rodríguez Corral
	 * @since 20/12/2017
	 */
	public void marcarComoLeido(Integer idPersona, List<DTOFirmaDocumentosEntity> noLeidos);

	/**
	 * Método que verifica si hay documentos firmados por otro usuario que se quiere regresar a remitente
	 * 
	 * @param dtoDocsFirma: lista de documentos que se verificarán
	 * 
	 * @author David Rodríguez Corral
	 * @since 03/01/2017
	 */
	public String verificarDocumentosFirmados(List<DTOFirmaDocumentosEntity> dtoDocsFirma);

	/**
	 * Método que verifica si el documento esta siendo editado (bloqueado) por otro usuario
	 * 
	 * @param dtoFirmaBloqueo: Objeto firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/01/2018
	 */
	public DTOFirmaDocumentosEntity verificarBloqueo(List<DTOFirmaDocumentosEntity> dtoFirmaBloqueo);
	
	/**
	 * Método que verifica si el documento esta siendo editado (bloqueado) por otro usuario
	 * 
	 * @param dtoFirmaBloqueo: Objeto firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/01/2018
	 */
	public DTOFirmaDocumentosEntity verificarBloqueo(DTOFirmaDocumentosEntity dtoFirmaBloqueo);
	
	/**
	 * Método que verifica si el documento ha sido regresao por otro usuario
	 * 
	 * @param dtoFirmaRegreso: Objeto firma
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/01/2018
	 */
	public DTOFirmaDocumentosEntity verificarRegresado(List<DTOFirmaDocumentosEntity> dtoFirmaRegreso);
	
	/**
	 * Método para obtener las listas de Remitentes, Destinatarios y Ccp de cada documento
	 * 
	 * @param idDocumento: Integer con el id del documento
	 * 
	 * @return DTODocumentoOficialiaHelper: objeto con los remitentes, destinatarios y ccps separados por comas
	 * 

	 * @author David Rodríguez Corral
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

	 * @author David Rodríguez Corral
	 * @since 03/01/2018
	 */
	public List<DTODocumentoAnexoEntity> obtenerAnexoPorDocumento(DTODocumentoEntity documento) throws Exception;
	
	/**
	 * Método para consultar las acciones realizadas en cada documento
	 * 
	 * @param idDocumento: id del documento 
	 * @return lista List<DTOHistDocCreacionEntity> con las acciones registradas
	 * 

	 * @author David Rodríguez Corral
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

	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public List<DTOComentariosDocumentoEntity> consultarComentarios(DTODocumentoEntity dtoDocumentoEntity) throws Exception;

	/**
	 * Método que rechaza los documentos por fe de erratas
	 * 
	 * @param dtoDocumentosSeleccionados: lista que contiene los documentos que se van a rechazar
	 * 
	 * @return String con un mensaje de error
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 10/01/2018
	 */
	public String rechazarDocumentos(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados) throws Exception;
}
