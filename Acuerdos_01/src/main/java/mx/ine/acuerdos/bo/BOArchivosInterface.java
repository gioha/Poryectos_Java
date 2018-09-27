package mx.ine.acuerdos.bo;




import java.io.InputStream;

import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



/**
 * <code>MBCapturaVotoDiputadoRP.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernandez
 * @version 1.0
 * @since 03/11/2017 
 */

public interface BOArchivosInterface {
	
	/**
	 * Recupera la ruta base del Gluster </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param Void
	 * @return String
	 * @throws Exception
	 */
	public String getRutaBaseGluster();
	
	/**
	 * crea la ruta final del Gluster </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param Void
	 * @return String
	 * @throws Exception
	 */
	public String getSufijoRutaGluster(moduloArchivo modulo, String glusterBase );

	/**
	 * genera el nombre de un archivo adjunto para acuerdos o engroses </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param tipoArchivo tipoArchivo
	 * @param String numACuerdo
	 * @param String nombre Original del archivo
	 * @return String
	 * @throws Exception
	 */
	public String getSufijoNombreAcuerdoEngroseFile(tipoArchivo tipoArchivo, String numACuerdo, String nombreOriginal );
	
	/**
	 * genera el nombre de un archivo adjunto para oficio de notificación </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param tipoArchivo tipoArchivo
	 * @param String numACuerdo
	 * @param Integer numeralia punto
	 * @param String nombre Original del archivo
	 * @return String
	 * @throws Exception
	 */
	public String getSufijoNombreOficioNotificacionFile(tipoArchivo tipoArchivo, String numACuerdo, Integer numeralia, String nombreOriginal );
	
	/**
	 * genera el nombre de un documento adjunto </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param tipoArchivo tipoArchivo
	 * @param String numDocumento
	 * @param String nombre Original del archivo
	 * @return String vacio si no reconoce el tipo de archivo, nombre formado y si todo salio bien
	 * @throws Exception
	 */
	public String getSufijoNombreTipoDocumentoFile(tipoArchivo tipoArchivo, String numDocumento, String nombreOriginal);
	
	/**
	 * guarda un archivo en una ruta del gluster </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param String fileName - nombre del archivo a copiar
	 * @param InputStream in
	 * @param String destino - ruta donde se guardar el archivo 
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean almacenarArchivoEnGluster( UploadedFile file, String destino, String rename);

	
	/**
	 * elimina un archivo en una ruta del gluster </BR>
	 * 
	 * @author Giovanni Hernández Alonso	
	 * @param String ruta del archivo a eliminar 
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean eliminaArchivoEnGluster( String ruta);
	
	public String getRutaPrevisualizacion(String rutaArchivo);
	
	public void descargaArchivo(String rutaArchivo);
	
	public String getNombreArchivo(String rutaArchivo);

	public String getRutaVideo(String rutaArchivo);
	
}
