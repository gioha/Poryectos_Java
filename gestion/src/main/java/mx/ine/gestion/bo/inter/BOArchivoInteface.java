package mx.ine.gestion.bo.inter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOArchivoHelper;

import org.primefaces.event.FileUploadEvent;

/**
 * @(#)BOArchivoInteface.java 04/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Interface de la capa de BO para funciones de negocio relacionadas a la subida
 * y descarga de archivos al gluster.
 * 
 * @author Sergio Ley Garcia
 * @since 04/09/2017
 */
public interface BOArchivoInteface {

	/**
	 * Constante para identificar un documento
	 */
	public final int	DOCUMENTO		= 1;
	/**
	 * Constante para identificar un anexo
	 */
	public final int	DOCUMENTOANEXO	= 2;

	/**
	 * Obtiene el nombre del temporal de primefaces que esta relacionado al
	 * archivo que estamos adjuntado
	 * 
	 * @param archivo
	 *            : objeto de primefaces que contiene la información del archivo
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public String obtenerNombreTemporalPrime(FileUploadEvent archivo);

	/**
	 * Método para borrar el archivo temporal de primefaces que se creo en el
	 * gluster
	 * 
	 * @param String
	 *            nombreTemporalPrimefaces : nombre del archivo temporal de
	 *            primefaces
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public void borrarArchivoGlusterTmpPrime(String nombreTemporalPrimefaces);

	/**
	 * Método para borrar el archivo temporal que se creo en el gluster
	 * 
	 * @param nombreTemporal
	 *            nombre del archivo temporal.
	 *
	 * @author Sergio Ley Garcia
	 * @since 28/09/2017
	 */
	public void borrarArchivoGlusterTmp(String nombreTemporal);

	/**
	 * Método que obtiene la ruta temporal para primefaces (FILEUPLOAD) de
	 * gluster donde se encuentran los documentos que fueron adjuntados
	 * 
	 * @return String: ruta de gluster donde se encuentran los documentos que
	 *         fueron adjuntados
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public String obtenerRutaGlusterTemporalesPrimefaces();

	/**
	 * Método que obtiene la ruta temporal de gluster donde se encuentran los
	 * documentos que fueron adjuntados
	 * 
	 * @return String: ruta de gluster donde se encuentran los documentos que
	 *         fueron adjuntados
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public String obtenerRutaGlusterTemporales();

	/**
	 * Método que obtiene la ruta en gluster de la carpeta donde se encuentran
	 * alojados los documentos que se utilizan en el servicio de la nube
	 * 
	 * @return String: ruta en gluster de los documentos
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public String obtenerRutaGlusterDocumentosNube();

	/**
	 * Método que obtiene la ruta en gluster de la carpeta donde se encuentran
	 * alojados los pdf que se utilizan en el servicio de la nube
	 * 
	 * @return String: ruta en gluster de los pdf
	 *
	 * @author Sergio Ley Garcia
	 * @since 02/10/2017
	 */
	public String obtenerRutaGlusterPdfNube();

	/**
	 * Método que obtiene la ruta en gluster de la carpeta donde se encuentran
	 * alojados los anexos que se utilizan en el servicio de la nube
	 * 
	 * @return String: ruta en gluster de los anexos
	 *
	 * @author Sergio Ley Garcia
	 * @since 19/09/2017
	 */
	public String obtenerRutaGlusterAnexosNube();

	/**
	 * Método que obtiene la ruta en gluster de la carpeta donde se encuentran
	 * alojadas las Plantillas que se utilizan en el servicio de la nube
	 * 
	 * @return String: ruta en gluster de los Plantillas
	 *
	 * @author Sergio Ley Garcia
	 * @since 19/09/2017
	 */
	public String obtenerRutaGlusterPlantillas();

	/**
	 * Método para mover el archivo temporal de primefaces a una carpeta
	 * "temporal", esto porque el archivo temporal de primefaces es borrado
	 * automaticamente y se sube a la nube
	 * 
	 * @param nombreTemporalPrime
	 *            : es el nombre que se le dio al archivo temporal (se lo da
	 *            primefaces)
	 * @throws Exception
	 *             : Lanza una excepción en caso de no poder mover el archivo de
	 *             carpeta
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public void moverArchivoTemporalPrimefaces(String nombreTemporalPrime) throws Exception;

	/**
	 * Método para obtener el porcentaje de espacio ocupado por los anexos
	 * 
	 * @param anexos
	 *            la lista de los anexos
	 * @return porcentaje 0-1 de espacio cubierto por los anexos.
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public Double validaranexos(List<DTOArchivoHelper> anexos);

	/**
	 * Método para validar si aun se pueden adjuntar mas anexos
	 * 
	 * @param anexos
	 *            la lista de los anexos
	 * @param anexo
	 *            anexos que se pretende ajuntar
	 * @return porcentaje 0-1 de espacio cubierto por los anexos.
	 *
	 * @author Sergio Ley Garcia
	 * @since 14/09/2017
	 */
	public Boolean validaranexos(List<DTOArchivoHelper> anexos, DTOArchivoHelper anexo);

	/**
	 * Método que guarda definitivamente el archivo documento en el gluster.
	 * 
	 * @param nombreTemporal
	 *            el nombre del archivo temporal del documento.
	 * @param documento
	 *            el documento.
	 *
	 * @author Sergio Ley Garcia
	 * @since 19/09/2017
	 */
	public void guardarDocumento(String nombreTemporal, DTODocumentoEntity documento) throws Exception;

	/**
	 * Método que guarda definitivamente los archivos anexos en el gluster.
	 * 
	 * @param anexos
	 *            los anexos del documento.
	 * @param documento
	 *            el coumento al que pertenecen estos anexos
	 *
	 * @author Sergio Ley Garcia
	 * @since 19/09/2017
	 */
	public void guardarAnexosDoc(List<DTODocumentoAnexoEntity> anexos, DTODocumentoEntity documento) throws Exception;

	/**
	 * Método que cambia la extencion de los archivos a la extension dada. Esta
	 * pensado para cambiar su extension tmp por la extension correcta en el
	 * servidor gluster.
	 * 
	 * @param nombreTemporal
	 *            nombre del archivo temporal
	 * @param extension
	 *            extension correcta del archivo
	 *
	 * @return String nuevo nombre temporal
	 * 
	 * @author Sergio Ley Garcia
	 * @since 29/09/2017
	 */
	public String reponerExtension(String nombreTemporal, String extension);

	/**
	 * Método que crea un el pdf de un documento.
	 * 
	 * @param documento
	 *            el documento
	 *
	 * @author Sergio Ley Garcia
	 * @since 03/10/2017
	 */
	public void crearPdf(DTODocumentoEntity documento) throws Exception;

	/**
	 * Método que obtiene el hiperlink para abrir y editar un documento.
	 * 
	 * @param nombreYExtensionArchivo
	 *            nombre y extension del documento.
	 * @param rutaDeArchivosEnGluster
	 *            ruta en el gluster.
	 * @param nombreDeUsuarioLDAP
	 *            nombre del usuario en el ldap.
	 * @param request
	 *            request actual.
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 *
	 * @author Sergio Ley Garcia
	 * @since 05/10/2017
	 */
	public String obtenerHiperLinkDAVSIDJParaEditarArchivo(String nombreYExtensionArchivo, String rutaDeArchivosEnGluster,
			String nombreDeUsuarioLDAP, HttpServletRequest request) throws GeneralSecurityException, IOException;

	/**
	 * Método que borra todos los archivos temporales del usuario
	 * 
	 *
	 * @author Sergio Ley Garcia
	 * @since 11/10/2017
	 */
	public void borrarArchivosGlusterTmp();

	/**
	 * Método que retorna la ruta local(sin incluir la ruta de archivos del
	 * guster) de las plantillas para el hiperlink.
	 * 
	 * @return ruta local de las plantillas
	 *
	 * @author Sergio Ley Garcia
	 * @since 27/10/2017
	 */
	public String obtenerRutaPlantillas();

	/**
	 * Método que retorna la ruta local(sin incluir la ruta de archivos del
	 * guster) de las plantillas para el hiperlink del archivo en blanco
	 * 
	 * @return ruta local de las plantillas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 17/01/2018
	 */
	public String obtenerRutaDocumentoBlanco();

	/**
	 * Método que crea una copia temporal de una plantilla en la carpeta de
	 * temporales del gluster.
	 * 
	 * @param plantilla
	 *            plantilla de la que se creara una copia temportal
	 * @throws IOException
	 *
	 * @author Sergio Ley Garcia
	 * @since 27/10/2017
	 */
	public void crearDocTemp(DTOPlantillaEntity plantilla) throws IOException;

	/**
	 * Método que crea una copia temporal de un documento previo en la carpeta
	 * de temporales del gluster.
	 * 
	 * @param archivoDocumento
	 *            DTOArchivoHelper que representa el documento previo del que se
	 *            hara una copia temporal
	 * @throws IOException
	 *
	 * @author Sergio Ley Garcia
	 * @since 27/10/2017
	 */
	public void crearDocTemp(DTOArchivoHelper archivoDocumento) throws IOException;

	/**
	 * Método que regresa los archivos anexos a la carpeta de temporales del
	 * gluster.
	 * 
	 * @param anexos
	 *            lista de anexos que se desea regresar (regresara los
	 *            documentos que esten en la carpeta de anexos, los que no esten
	 *            ahi los ingnorra de la lista)
	 *
	 * @author Sergio Ley Garcia
	 * @since 28/10/2017
	 */
	public void regresarAnexos(List<DTODocumentoAnexoEntity> anexos);

	/**
	 * Método que regresa el documento a la carpeta de temporales del gluster.
	 * 
	 * @param documento
	 *            el documento(si no esta en la carpeta solo lo ignorara)
	 *
	 * @author Sergio Ley Garcia
	 * @since 28/10/2017
	 */
	public void regresarDocumento(DTODocumentoEntity documento);

	/**
	 * Método que elimina del gluster.
	 * 
	 * @param documento
	 *            el documento(si no esta en la carpeta solo lo ignorara)
	 *
	 * @author Sergio Ley Garcia
	 * @since 28/10/2017
	 */
	void eliminarPdf(DTODocumentoEntity documento);

	/**
	 * Método que renombra un archivo, se usa mas de lo q crees -.-
	 * 
	 * @param ruta
	 *            ruta del archiv
	 * @param nombre
	 *            nombre del archivo
	 * @param nombreNuevo
	 *            nuevo nombre que se le dara al archivo
	 *
	 * @author Sergio Ley Garcia
	 * @since 29/10/2017
	 */
	void renombrarArchivo(String ruta, String nombre, String nombreNuevo);

	/**
	 * Método ue reordena renombra y guarda los documentos anexos
	 * 
	 * @param anexosAnteriores
	 *            lista de los documentos anexos que fueron eliminados en la
	 *            modificación.
	 * @param anexosNuevos
	 *            lista de los documentos anexos que fueron agregados en la
	 *            modificación.
	 * @param anexosRepetidos
	 *            lista de anexos del documento que no fueron modificados
	 *
	 * @author Sergio Ley Garcia
	 * @param integer
	 * @throws IOException
	 *             no pudo guardar algun archivo anexo.
	 * @since 30/10/2017
	 */
	public void modificarAnexos(List<DTODocumentoAnexoEntity> anexosAnteriores, List<DTODocumentoAnexoEntity> anexosNuevos,
			List<DTODocumentoAnexoEntity> anexosRepetidos, String noDocumento, Integer integer) throws IOException;

	/**
	 * Método que borra un archivo de una ruta especifica
	 * 
	 * @param ruta
	 * @param nombre
	 *
	 * @author Sergio Ley Garcia
	 * @since 31/10/2017
	 */
	public void borrarArchivo(String ruta, String nombre);

}
