/**
 * DAOBorradorDocumentosInterface.java 29/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dao.inter;

import java.util.List;

import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosID;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.dao.inter.DAOGenericGestionInterface;

/**
 * Interface que contiene la firma de los métodos que consultaran la tabla de
 * "BORRADOR_DOCUMENTOS" del esquema "gestion4"
 * 
 * @author Homero Villanueva Dominguez
 * @since 05/09/2017
 * 
 */
public interface DAOBorradorDocumentosInterface extends DAOGenericGestionInterface<DTOBorradorDocumentosEntity, DTOBorradorDocumentosID> {

	/**
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
	 * @param estatusDocumento
	 *            : Parametro de tipo char define el tipo de busqueda que se
	 *            hará en la columna estatus de la tabla "DOCUMENTOS". El valor
	 *            del parametro puede ser 'A' de Activo o 'E' de eliminado. Por
	 *            default se seleccionarán los registros Activos
	 * 
	 * @return: Lita de Borradores Documentos que cumplan con los parametros
	 *          recibidos.
	 * 
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public List<DTOBorradorDocumentosEntity> consutarBorradoresPorPersona(DTOBorradorDocumentosHelper helperBorradores);

	/**
	 * Método que prende la bandera con_comentarios en la tabla
	 * BORRADOR_DOCUMENTOS para indicar que el documento tiene comentarios
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public void activarComentario(Integer idDocumento);

	/**
	 * Método que prende la bandera con_modificacion en la tabla
	 * BORRADOR_DOCUMENTOS para indicar que el documento tiene modificacion
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 01/11/2017
	 */
	public void activarModificacion(Integer idDocumento);
	
	
	/**
	 * Método que prende la bandera con_validacion en la tabla
	 * BORRADOR_DOCUMENTOS para indicar que el documento tiene validacion
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/10/2017
	 */
	public void activarValidacion(Integer idDocumento);
	
	/**
	 * Método que prende la bandera con_firma en la tabla
	 * BORRADOR_DOCUMENTOS para indicar que el documento tiene firma
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public void activarFirma(Integer idDocumento);
	
	/**
	 * Método que prende la bandera con_firma en la tabla
	 * BORRADOR_DOCUMENTOS para indicar que el documento tiene firma
	 * 
	 * @param Integer
	 *            : id del Documento
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public void desactivarFirma(Integer idDocumento);
	
	
	
	/**
	 * 
	 * @param idDocumento
	 * @since 27/10/2017
	 */
	public void desactivarValidacion(Integer idDocumento);
	
	/**
	 * Método que decrementa el valor en el campo NUM_ENVIADO_FIRMA
	 * 
	 * @param idDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public void restarFirma(Integer idDocumento);
	
	/**
	 * 
	 * @param idDocumento
	 * @since 23/10/2017
	 */
	public void restarValidacion(Integer idDocumento);

	/**
	 * Método que pone en null el campo NUM_ENVIADO_FIRMA
	 * 
	 * @param idDocumento: Integer con el id del documento
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 23/11/2017
	 */
	public void ponerContadorEnNull(Integer idDocumento);
	
	/**
	 * Método que obtiene un registro de la tabla BORRADOR_DOCUMENTOS
	 * 
	 * @return Objeto de tipo DTOBorradorDocumentosEntity que guarda un registro
	 * 
	 * @param idDocumento
	 *            : id del documento a buscar
	 * @param idPersona
	 *            : id de la persona a buscar
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public DTOBorradorDocumentosEntity obtenerBorrador(Integer idDocumento,
			Integer idPersona);
	
	/**
	 * Método que obtiene el numero de firmas de un documento
	 * 
	 * @param idDocumento
	 *            : id del documento a buscar
	 *            
	 * @return DTOBorradorDocumentosEntity
	 * 
	 * @author David Rodríguez Corral
	 * @since 02/10/2017
	 */
	public Integer obtenerNumeroFirmasBorrador(Integer idDocumento);
	
	/**
	 * Método que obtiene el campo contiene_anexos
	 * 
	 * @param idDocumento
	 *            : id del documento a buscar
	 *            
	 * @return DTOBorradorDocumentosEntity
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/11/2017
	 */
	public Integer obtenerContieneAnexos(Integer idDocumento);
	
	/**
	 * Método que elimina un registro por id de documento
	 * 
	 * @param idDocumento
	 *            : id del documento a eliminar
	 * 
	 * @author David Rodríguez Corral
	 * @since 31/10/2017
	 */
	public void eliminarPorIdDocumento(Integer idDocumento);
	
	/**
	 * 
	 * Método que regresa el número total de Documentos borradores del usuario que
	 * inició sesión.
	 * 
	 * @param dtoEstructuraAreasEntity
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public int consultarNumeroBorradores(
			DTOEstructuraAreasEntity dtoEstructuraAreasEntity);
	
	/**
	 * 
	 * Método que registra la fecha de regreso a remitente cuando el numero de envío es cero.
	 * 
	 * @param idDocumento: id del documento 
	 * @param numEnviado: Si el documento se regresó sin firmar es null, si se regresó firmado es 0
	 *
	 * @author David Rodríguez Corral
	 * @since 17/11/2017
	 */
	public void registrarFechaRegreso(Integer idDocumento, Integer numEnviado);
}
