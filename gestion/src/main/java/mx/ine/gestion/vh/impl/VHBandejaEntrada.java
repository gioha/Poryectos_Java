/**
 * @(#)VHBandejaEntrada.java 19/03/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.webapp.MultipartRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHBandejaEntradaInterface;

/**
 * @author Homero Fidel Villanuevav
 * @since 19/03/2018
 *
 */
@Scope("prototype")
@Component("vhBandejaEntrada")
public class VHBandejaEntrada implements VHBandejaEntradaInterface{

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHBandejaEntradaInterface#generarTemporalArchivoAdjunto(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper, org.primefaces.event.FileUploadEvent)
	 */
	@Override
	public void generarTemporalArchivoAdjunto(DTOResponderTurnadoHelper filtroResponder, FileUploadEvent archivo) throws Exception {
		//1.- Obtenemos el nombre temporal de prime
		MultipartRequest httpServletRequest = (MultipartRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String idClienteRegistroTabla = archivo.getComponent().getClientId();
		DiskFileItem fileItem = (DiskFileItem) httpServletRequest.getFileItem(idClienteRegistroTabla);
		File temporal = fileItem.getStoreLocation();
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] temporalArreglo = temporal.getAbsolutePath().split(pattern);
		String nombreTemporalPrime = temporalArreglo[temporalArreglo.length - 1];
			
		//2.-Lo movemos de lugar para evitar que desaparezca
		String  rutaTemporalDePrime = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") 
					+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmpPrimefaces") + File.separator + nombreTemporalPrime;
			
		String rutaTemporal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();
				
		File archivoTemporalPrime = new File(rutaTemporalDePrime);
		File archivoTemporal = new File(rutaTemporal);
				
		if (!archivoTemporal.exists()) {
			archivoTemporal.mkdirs();
		} else {
			 File[] files = archivoTemporal.listFiles();
			 if(files != null) {
				 for(File documentosBasura: files) {
					 documentosBasura.delete();
				 }
			 } 
		}
			
		FileUtils.moveToDirectory(archivoTemporalPrime, archivoTemporal, false);
		
		//3.- Lo renombramos
		Calendar calendario = Calendar.getInstance();
		String nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" + String.valueOf(calendario.get(Calendar.MILLISECOND)) + ".pdf";
	
		File archivoArenombrar = new File(rutaTemporal + File.separator + nombreTemporalPrime);
		File archivoRenombrado = new File(rutaTemporal + File.separator + nombreTemporal);
		archivoArenombrar.renameTo(archivoRenombrado);
		
		//3.- Guardamos la información que necesitaremos más adelante
		filtroResponder.setNombreAdjuntoTemporal(nombreTemporal);
		filtroResponder.setNombreAdjuntoOriginal(archivo.getFile().getFileName());
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHBandejaEntradaInterface#guardarAdjunto(mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper)
	 */
	@Override
	public void guardarAdjunto(DTOResponderTurnadoHelper filtroResponder) {
		//1.- Obtenemos el nombre temporal de prime
		MultipartRequest httpServletRequest = (MultipartRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//String idClienteRegistroTabla = archivo.getComponent().getClientId();
		//DiskFileItem fileItem = (DiskFileItem) httpServletRequest.getFileItem(idClienteRegistroTabla);
		File temporal;// = fileItem.getStoreLocation();
		//String pattern = Pattern.quote(System.getProperty("file.separator"));
		//String[] temporalArreglo = temporal.getAbsolutePath().split(pattern);
		//String nombreTemporalPrime = temporalArreglo[temporalArreglo.length - 1];
			
		//2.-Lo movemos de lugar para evitar que desaparezca
			
		String rutaTemporal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();
		temporal = new File(rutaTemporal+filtroResponder.getNombreAdjuntoTemporal());
		
	}
}