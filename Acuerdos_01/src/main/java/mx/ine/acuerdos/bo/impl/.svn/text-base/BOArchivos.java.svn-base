package mx.ine.acuerdos.bo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.mb.MBBandejaCompromisos;
import mx.ine.acuerdos.mb.MBRegistroAcuerdos;
import mx.ine.acuerdos.util.Constantes;
import mx.ine.acuerdos.util.Constantes.moduloArchivo;
import mx.ine.acuerdos.util.Constantes.tipoArchivo;
import mx.ine.acuerdos.util.Utilidades;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;

@Scope("prototype")
@Component("boArchivosAcuerdos")
public class BOArchivos implements BOArchivosInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070884946218121617L;
	
	private static final Log log = LogFactory.getLog(BOArchivos.class);
	
	@Autowired
	@Qualifier("constante")
	private transient Constantes constantes;

	@Override
	public String getRutaBaseGluster() {
		
		String rutaGluster = "";
		
		char[] rutaBase = Utilidades.getRutaGlusterFS().toCharArray();
		
		if( rutaBase.length > 0 ){
			// si trae al final un slash en la ruta
			if(  rutaBase[rutaBase.length - 1] == '/' || rutaBase[rutaBase.length - 1] == '\\' ){
				rutaGluster = Utilidades.getRutaGlusterFS() +  constantes.path_name_project;
			}
			// no trae al final un slash en la ruta
			else{
				rutaGluster = Utilidades.getRutaGlusterFS() +  File.separator  + constantes.path_name_project;
			}
		}
	
		return rutaGluster;
	}

	@Override
	public String getSufijoRutaGluster(moduloArchivo modulo, String glusterBase) {

	
		if( modulo.getModulo() == constantes.modulo_acuerdo ){
			glusterBase = glusterBase + File.separator + constantes.path_negocio_cg + File.separator + constantes.path_modulo_acuerdo;
		}
		else if( modulo.getModulo() == constantes.modulo_punto){
			glusterBase = glusterBase + File.separator + constantes.path_negocio_cg + File.separator + constantes.path_modulo_punto;		
		}
		else if( modulo.getModulo() == constantes.modulo_documento){
			glusterBase = glusterBase + File.separator + constantes.path_negocio_sivople ;
		}
		else if( modulo.getModulo() == constantes.modulo_orden){
			glusterBase = glusterBase + File.separator + constantes.path_negocio_cg + File.separator + constantes.path_modulo_orden;
		}
		else if(modulo.getModulo() == constantes.modulo_anexos) {
			glusterBase = glusterBase + File.separator + constantes.path_negocio_cg + File.separator + constantes.path_anexos;
		}
		else{
			glusterBase = "";
		}
		
		return glusterBase;
	}
	
	@Override
	public String getSufijoNombreAcuerdoEngroseFile(tipoArchivo tipoArchivo, String numACuerdo, String nombreOriginal) {
	
		String fileName = "";
		String extencion = "";
		int posicionPunto = -1;
		
		char[] nameArray = nombreOriginal.toCharArray();
		
		if(nameArray.length > 0){
			
			for( int x = nameArray.length ; x >= 1 ; x -- ){
				char caracter = nameArray [ x - 1];
				if( caracter == '.' ){
					posicionPunto = x - 1;
					break;
				}
			}
			
			if( posicionPunto != -1 ){
				extencion = nombreOriginal.substring(posicionPunto + 1);
			}
		}
		
		
		if( tipoArchivo.getTipo() == constantes.tipo_acuerdo ){
			
			fileName = "DOCUMENTO";//fileName = "ACUERDO";
			
			for (String componente : numACuerdo.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
			
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_engrose ){
			
			fileName = "ENGROSE";
			
			for (String componente : numACuerdo.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
			
		}else if ( tipoArchivo.getTipo() == constantes.tipo_ordendeldia ){
			
			fileName = "ORDENDELDIA";
			
			for (String componente : numACuerdo.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
			
		}
		else if(tipoArchivo.getTipo() == constantes.tipo_doc_ordendeldia) {
			fileName = "DOC_ANEXA_ORDENDELDIA";

			for(String componente : numACuerdo.split("/")) {
				fileName = fileName + "_" + componente;
			}

			if(!extencion.equals("")) {
				fileName = fileName + "." + extencion;
			}
		}
		else {
			return fileName;
		}
		
		
		return fileName;
	}
	
	
	@Override
	public String getSufijoNombreOficioNotificacionFile(tipoArchivo tipoArchivo, String numACuerdo, Integer numeralia, String nombreOriginal) {
		
		String fileName = "";
		String extencion = "";
		int posicionPunto = -1;
		
		char[] nameArray = nombreOriginal.toCharArray();
		
		if(nameArray.length > 0){
			
			for( int x = nameArray.length ; x >= 1 ; x -- ){
				char caracter = nameArray [ x - 1];
				if( caracter == '.' ){
					posicionPunto = x - 1;
					break;
				}
			}
			
			if( posicionPunto != -1 ){
				extencion = nombreOriginal.substring(posicionPunto + 1);
			}
		}
		
		
		if( tipoArchivo.getTipo() == constantes.tipo_oficionotificacionpunto ){
			
			fileName = "OFICIO_NOTIFICACION";
			
			for (String componente : numACuerdo.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			
			fileName = fileName + "_" + numeralia.toString();
			
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
			
		}
		else {
			return fileName;
		}
		
		return fileName;
	}
	
	
	@Override
	public String getSufijoNombreTipoDocumentoFile(tipoArchivo tipoArchivo, String numDocumento, String nombreOriginal) {
		
		String fileName = "";
		String extencion = "";
		int posicionPunto = -1;
		
		char[] nameArray = nombreOriginal.toCharArray();
		
		if(nameArray.length > 0){
			
			for( int x = nameArray.length ; x >= 1 ; x -- ){
				char caracter = nameArray [ x - 1];
				if( caracter == '.' ){
					posicionPunto = x - 1;
					break;
				}
			}
			
			if( posicionPunto != -1 ){
				extencion = nombreOriginal.substring(posicionPunto + 1);
			}
		}
		
		if( tipoArchivo.getTipo() == constantes.tipo_acuerdo ){
			
			fileName = "ACUERDO";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
			
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_circular ){
			
			fileName = "CIRCULAR";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_oficio ){
			
			fileName = "OFICIO";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_lineamiento ){
			
			fileName = "LINEAMIENTO";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_ordendeldia ){
			
			fileName = "ORDENDELDIA";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
		}
		else if ( tipoArchivo.getTipo() == constantes.tipo_otro ){
			
			fileName = "OTRO";
			
			for (String componente : numDocumento.split("/") ) {
				fileName = fileName + "_" + componente;
			}
			if(!extencion.equals(""))
				fileName = fileName + "." + extencion;
		}
		else {
			return fileName;
		}
		
		return fileName;
	}
	

	public Boolean writeFile(String fileName, InputStream in, String destino){
		try {

			
			File rutaCompletaFile = new File(destino + File.separator);
			if(!rutaCompletaFile.getAbsoluteFile().exists()){
				rutaCompletaFile.mkdirs();
			}
			
			OutputStream out;
		
			out = new FileOutputStream(new File(destino + File.separator + fileName));
			
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			return true;
		} catch (IOException e) {
			log.error( " BOArchivos - writeFile() - Ocurrio un error: " + e.getMessage()  );
		}
		return false;
	}
	
	@Override
	public Boolean almacenarArchivoEnGluster(UploadedFile file, String destino, String rename){
		try {
						
			if (writeFile(rename, file.getInputstream(), destino)) {
				return true;
			} else {
				log.error( " BOArchivos - almacenarArchivoEnGluster() - Ocurrio un error ");
				return false;
			}
		}catch(Exception e) {
			log.error( " BOArchivos - almacenarArchivoEnGluster() - Ocurrio un error: " + e.getMessage()  );
			return false;
		}
	}

	@Override
	public Boolean eliminaArchivoEnGluster(String ruta) {
	
		Boolean deleteSuccess = false;
		
		try{
			File archivo = new File( ruta );
			if(archivo.exists()){
				
				if(archivo.delete()){
					deleteSuccess = true;
				} 
				else{
					log.error( " BOArchivos - eliminaArchivoEnGluster() - No se pudo eliminar archivo" );
				}
				
			}
			// si no hay algo que borrar igual retornamos true por que como tal no ocurrio un error al borrar
			else{
				deleteSuccess = true;
			}
		} catch(Exception e){
			log.error( " BOArchivos - eliminaArchivoEnGluster() - Ocurrio un error: " + e.getMessage()  );
		}
		
		
		return deleteSuccess;
	}	
	
	@Override
	public String getRutaPrevisualizacion(String rutaArchivo) {
		return "pdf" + File.separator + rutaArchivo.substring(rutaArchivo.indexOf(constantes.path_name_project));
	}
	
	@Override
	public String getRutaVideo(String rutaArchivo) {
		return "media" + File.separator + rutaArchivo.substring(rutaArchivo.indexOf(constantes.path_name_project));
	}
	
	public String getNombreArchivo(String rutaArchivo) {
		rutaArchivo = rutaArchivo.replace("\\", ";");
		rutaArchivo = rutaArchivo.replace("/", ";");
        String[] cadenas = rutaArchivo.split(";");
        
		return cadenas[cadenas.length-1];
	}

	@Override
	public void descargaArchivo(String rutaArchivo) {
		FacesContext fc;
		HttpServletResponse response;
		FileInputStream input = null;
		try {
			fc = FacesContext.getCurrentInstance();
			response = (HttpServletResponse) fc.getExternalContext().getResponse();
			
			File file = new File(rutaArchivo);
			if (!file.exists()) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
			}
			
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			response.setContentLength((int) file.length());
			
			input = new FileInputStream(file);
			int i = 0;
			byte[] buffer = new byte[1024];
			
            while ((i = input.read(buffer)) != -1) {
	            response.getOutputStream().write(buffer);
	            response.getOutputStream().flush();
            }
            fc.responseComplete();
            fc.renderResponse();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
