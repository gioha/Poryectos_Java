package mx.ine.gestion.bo.impl;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.helper.DTOArchivoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAV;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAVImpl;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.webapp.MultipartRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @(#)BOArchivo.java 04/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de BO para funciones de negocio relacionadas a la subida y
 * descarga de archivos al gluster.
 * 
 * @author Sergio Ley Garcia
 * @since 04/09/2017
 */
@Component("boArchivo")
@Scope("prototype")
public class BOArchivo implements BOArchivoInteface {

	/************************************************************* Constantes *****************************************************************************/
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger	log	= Logger.getLogger(BOArchivo.class);

	/************************************************************* Métodos *****************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerNombreTemporalPrime(
	 * org.primefaces.event.FileUploadEvent)
	 */
	@Override
	public String obtenerNombreTemporalPrime(FileUploadEvent archivo) {

		MultipartRequest httpServletRequest = (MultipartRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String idClienteRegistroTabla = archivo.getComponent().getClientId();
		DiskFileItem fileItem = (DiskFileItem) httpServletRequest.getFileItem(idClienteRegistroTabla);
		File temporal = fileItem.getStoreLocation();
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] temporalArreglo = temporal.getAbsolutePath().split(pattern);
		String nombreTemporalPrime = temporalArreglo[temporalArreglo.length - 1];
		return nombreTemporalPrime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#borrarArchivoGlusterTmpPrime
	 * (java.lang.String)
	 */
	@Override
	public void borrarArchivoGlusterTmpPrime(String nombreTemporalPrimefaces) {

		String rutaMaterialTmp = "";
		try{
			rutaMaterialTmp = this.obtenerRutaGlusterTemporalesPrimefaces() + File.separator + nombreTemporalPrimefaces;
			File rutaCompletaFile = new File(rutaMaterialTmp);
			if(rutaCompletaFile.exists()){
				rutaCompletaFile.delete();
			}
		} catch(Exception e){
			log.error("<=================== Clase: BOArchivoInteface , Método: borrarArchivoGlusterTmpPrime");
			log.error("<=================== Ocurrio un error al tratar de borrar el archivo: " + rutaMaterialTmp);
			log.error(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#borrarArchivoGlusterTmp(java
	 * .lang.String)
	 */
	@Override
	public void borrarArchivoGlusterTmp(String nombreTemporal) {

		String rutaMaterialTmp = "";
		try{
			rutaMaterialTmp = this.obtenerRutaGlusterTemporales() + File.separator + nombreTemporal;
			File rutaCompletaFile = new File(rutaMaterialTmp);
			if(rutaCompletaFile.exists()){
				rutaCompletaFile.delete();
			}
		} catch(Exception e){
			log.error("<=================== Clase: BOArchivoInteface , Método: borrarArchivoGlusterTmp");
			log.error("<=================== Ocurrio un error al tratar de borrar el archivo: " + rutaMaterialTmp);
			log.error(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#borrarArchivosGlusterTmp()
	 */
	@Override
	public void borrarArchivosGlusterTmp() {

		// lo mas sencillo borrar la carpeta con todo
		try{
			File ruta = new File(this.obtenerRutaGlusterTemporales());
			if(ruta.exists()){
				String[] entries = ruta.list();
				for (String s : entries){
					File currentFile = new File(ruta.getPath(), s);
					currentFile.delete();
				}
				boolean borro = ruta.delete();
				if(!borro){
					log.error("<=================== Clase: BOArchivoInteface , Método: borrarArchivosGlusterTmp");
					log.error("<=================== No pudo borrar los archivos temporales ");
				}
			}
		} catch(Exception e){
			log.error("<=================== Clase: BOArchivoInteface , Método: borrarArchivosGlusterTmp");
			log.error("<=================== Ocurrio un error al tratar de borrar temporales");
			log.error(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#
	 * obtenerRutaGlusterMaterialesTemporalesPrimefaces()
	 */
	@Override
	public String obtenerRutaGlusterTemporalesPrimefaces() {

		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmpPrimefaces");
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaGlusterTemporales()
	 */
	@Override
	public String obtenerRutaGlusterTemporales() {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + user.getUsername();
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaPlantillas()
	 */
	@Override
	public String obtenerRutaPlantillas() {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rutaGluster = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") + File.separator
				+ Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + user.getUsername();
		return rutaGluster;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaDocumentoBlanco()
	 */
	@Override
	public String obtenerRutaDocumentoBlanco() {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rutaGluster = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") + File.separator
				+ Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + user.getUsername();
		return rutaGluster;
				
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaGlusterPlantillas()
	 */
	@Override
	public String obtenerRutaGlusterPlantillas() {

		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_plantillas");
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaGlusterDocumentosNube
	 * ()
	 */
	@Override
	public String obtenerRutaGlusterDocumentosNube() {

		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaDocumentos");
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaGlusterPdfNube()
	 */
	@Override
	public String obtenerRutaGlusterPdfNube() {

		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaPdf");
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#obtenerRutaGlusterAnexosNube()
	 */
	@Override
	public String obtenerRutaGlusterAnexosNube() {

		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaAnexos");
		return rutaGluster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#moverArchivoTemporalPrimefaces
	 * (java.lang.String)
	 */
	@Override
	public void moverArchivoTemporalPrimefaces(String nombreTemporalPrime) throws Exception {

		// Movemos de directorio el archivo para utilizarlo despues
		String rutaPrimefaces = this.obtenerRutaGlusterTemporalesPrimefaces() + File.separator + nombreTemporalPrime;
		File fileTmpPrime = new File(rutaPrimefaces);
		String rutaTemporal = this.obtenerRutaGlusterTemporales() + File.separator;
		File fileTmp = new File(rutaTemporal);
		fileTmp.mkdirs();
		FileUtils.moveToDirectory(fileTmpPrime, fileTmp, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#validaranexos(java.util.List)
	 */
	@Override
	public Double validaranexos(List<DTOArchivoHelper> anexos) {

		Integer total = 0;
		for (Iterator<DTOArchivoHelper> iterator = anexos.iterator(); iterator.hasNext();){
			DTOArchivoHelper ane = iterator.next();
			total += ane.getTamanio();
		}
		// obtenermos el tamaño maximo de las constantes y comparamos para
		// return total/maximo
		return Double.valueOf(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#validaranexos(java.util.List,
	 * mx.ine.gestion.dto.helper.DTOArchivoHelper)
	 */
	@Override
	public Boolean validaranexos(List<DTOArchivoHelper> anexos, DTOArchivoHelper anexo) {

		Integer total = 0;
		if(anexos == null)
			anexos = new ArrayList<DTOArchivoHelper>();
		for (Iterator<DTOArchivoHelper> iterator = anexos.iterator(); iterator.hasNext();){
			DTOArchivoHelper ane = iterator.next();
			total += ane.getTamanio();
		}
		total += anexo.getTamanio();
		// obtenermos el tamaño maximo de las constantes y comparamos para el
		String maxStr = Utilidades.mensajeProperties("constante_tamanio_maximo");
		Integer maximo = Integer.parseInt(maxStr);
		String archivosPermitidos = Utilidades.mensajeProperties("constante_tipos_archivos_anexos");
		String[] tiposPermitidos = archivosPermitidos.split(",");
		return total <= maximo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#guardarDocumento(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void guardarDocumento(String nombreTemporal, DTODocumentoEntity documento) throws Exception {

		// renombramos
		String rutaTemporal = this.obtenerRutaGlusterTemporales() + File.separator + documento.getNombreDocumento();
		File fileGlus = new File(this.obtenerRutaGlusterTemporales() + File.separator + nombreTemporal);
		File newName = new File(rutaTemporal);
		fileGlus.renameTo(newName);
		// movemos
		File fileTmp = new File(rutaTemporal);
		String rutaGluster = obtenerRutaGlusterDocumentosNube();
		File file = new File(rutaGluster);
		FileUtils.moveToDirectory(fileTmp, file, false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#guardarAnexosDoc(java.util.
	 * List, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void guardarAnexosDoc(List<DTODocumentoAnexoEntity> anexos, DTODocumentoEntity documento) throws Exception {

		String rutaTemporal = this.obtenerRutaGlusterTemporales() + File.separator;
		String rutaGluster = obtenerRutaGlusterAnexosNube();
		File file = new File(rutaGluster);
		Integer acumulador = 1;
		for (Iterator<DTODocumentoAnexoEntity> iterator = anexos.iterator(); iterator.hasNext();){
			DTODocumentoAnexoEntity anexo = iterator.next();
			String nombreAnexo = documento.getNombreDocumento().split("\\.")[0] + "_" + acumulador;
			nombreAnexo = nombreAnexo + "." + anexo.getNombreDocAnexo().split("\\.")[1];
			File fileTmp = new File(rutaTemporal + anexo.getNombreDocAnexo());
			FileUtils.moveToDirectory(fileTmp, file, false);
			// renombramos
			renombrarArchivo(rutaGluster, anexo.getNombreDocAnexo(), nombreAnexo);
			anexo.setNombreDocAnexo(nombreAnexo);
			anexo.setIdAnexo(acumulador++);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#reponerExtension(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public String reponerExtension(String nombreTemporal, String extension) {

		String rutaTemporal = this.obtenerRutaGlusterTemporales() + File.separator;
		StringBuilder nombre = new StringBuilder();
		nombre.append(nombreTemporal.split("\\.")[0]);
		nombre.append(".").append(extension);
		File fileTmp = new File(rutaTemporal + nombreTemporal);
		File ext = new File(rutaTemporal + nombre.toString());
		fileTmp.renameTo(ext);
		return nombre.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#crearPdf(java.lang.String)
	 */
	@Override
	public void crearPdf(DTODocumentoEntity documento) throws Exception {

		String docPath = obtenerRutaGlusterDocumentosNube() + File.separator + documento.getNombreDocumento();
		String pdfPath = obtenerRutaGlusterPdfNube();
		if(documento.getTipoCaptura().intValue() == 3){
			File source = new File(docPath);
			log.error("se busca el pdf en : " + docPath);
			File dest = new File(pdfPath + File.separator + documento.getNombreDocumento());
			FileUtils.copyFile(source, dest);
			log.error("se copio el pdf en : " + pdfPath + File.separator + documento.getNombreDocumento());
		} else{

			transformOpenOffice(docPath, pdfPath, documento);
		}
	}

	private void transformOpenOffice(String docPath, String pdfPath, DTODocumentoEntity documento) throws OfficeException {

		// version 2
		// 1) Start LibreOffice in headless mode.
		OfficeManager officeManager = null;
		DefaultOfficeManagerBuilder ob = null;
		File source = new File(docPath);
		String nombre = documento.getNombreDocumento().split("\\.")[0];
		File dest = new File(pdfPath + File.separator + nombre + ".pdf");
		try{
			ob = new DefaultOfficeManagerBuilder();
			ob.setPortNumber(8100);
			officeManager = ob.setOfficeHome(new File(Utilidades.getRutaOfficeFS())).build();
			officeManager.start();
			
			// 2) Create JODConverter converter
			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
			// 3) Create PDF
			converter.convert(source, dest);
		} finally{
			// 4) Stop LibreOffice in headless mode.
			if(officeManager != null){
				officeManager.stop();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.ine.gestion.bo.inter.BOArchivoInteface#
	 * obtenerHiperLinkDAVSIDJParaEditarArchivo(java.lang.String,
	 * java.lang.String, java.lang.String,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String obtenerHiperLinkDAVSIDJParaEditarArchivo(String nombreYExtensionArchivo, String rutaDeArchivosEnGluster,
			String nombreDeUsuarioLDAP, HttpServletRequest request) throws GeneralSecurityException, IOException {

		// Variables
		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink;
		final boolean siMostrarCopiaSoloLectura = false;
		rutaDeArchivosEnGluster = rutaDeArchivosEnGluster.replaceAll("\\\\", "/") + "/";
		// Obtener instancia del conversor
		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();
		// Crear el hiperlink
		hiperlink = asConversorURL.crearHiperLinkWebDav(nombreYExtensionArchivo, rutaDeArchivosEnGluster, nombreDeUsuarioLDAP,
				siMostrarCopiaSoloLectura, "sidjwebdav", request);
		// Retornar resultado
		return hiperlink;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#crearDocTemp(mx.ine.gestion
	 * .dto.db.DTOPlantillaEntity)
	 */
	@Override
	public void crearDocTemp(DTOPlantillaEntity plantilla) throws IOException {

		String rutaPlantilla = obtenerRutaGlusterPlantillas();
		String rutaTemp = obtenerRutaGlusterTemporales();
		File pathDest = new File(rutaTemp);
		pathDest.mkdirs();
		if(plantilla != null){
			DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			rutaPlantilla = rutaPlantilla + File.separator + user.getUsername() + File.separator + plantilla.getIdTipoDocumento() + "_"
					+ plantilla.getIdAcronimo() + "_" + plantilla.getNombrePlantilla() + ".docx";
			rutaTemp = rutaTemp + File.separator + plantilla.getNombrePlantilla();
		} else{
			rutaPlantilla = rutaPlantilla + File.separator + Utilidades.mensajeProperties("constante_documento_blanco");
			rutaTemp = rutaTemp + File.separator + Utilidades.mensajeProperties("constante_documento_blanco");
		}
		File source = new File(rutaPlantilla);
		File dest = new File(rutaTemp);
		FileUtils.copyFile(source, dest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#crearDocTemp(mx.ine.gestion
	 * .dto.helper.DTOArchivoHelper)
	 */
	@Override
	public void crearDocTemp(DTOArchivoHelper archivoDocumento) throws IOException {

		String rutaDocumento = obtenerRutaGlusterDocumentosNube();
		String rutaTemp = obtenerRutaGlusterTemporales();
		File pathDest = new File(rutaTemp);
		pathDest.mkdirs();
		rutaDocumento = rutaDocumento + File.separator + archivoDocumento.getNombreArchivo();
		rutaTemp = rutaTemp + File.separator + archivoDocumento.getNombreArchivo();
		File source = new File(rutaDocumento);
		File dest = new File(rutaTemp);
		FileUtils.copyFile(source, dest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#regresarAnexos(java.util.List)
	 */
	@Override
	public void regresarAnexos(List<DTODocumentoAnexoEntity> anexos) {

		String rutaDocumento = obtenerRutaGlusterAnexosNube();
		String rutaTemp = obtenerRutaGlusterTemporales();
		File pathDest = new File(rutaTemp);
		pathDest.mkdirs();
		for (Iterator<DTODocumentoAnexoEntity> iterator = anexos.iterator(); iterator.hasNext();){
			DTODocumentoAnexoEntity anexo = iterator.next();
			File source = new File(rutaDocumento + File.separator + anexo.getNombreDocAnexo());
			try{
				FileUtils.moveToDirectory(source, pathDest, false);
			} catch(IOException e){
				// el anexo no esta aqui :v
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#regresarDocumento(mx.ine.gestion
	 * .dto.db.DTODocumentoEntity)
	 */
	@Override
	public void regresarDocumento(DTODocumentoEntity documento) {

		String rutaDocumento = obtenerRutaGlusterDocumentosNube();
		String rutaTemp = obtenerRutaGlusterTemporales();
		File pathDest = new File(rutaTemp);
		pathDest.mkdirs();
		rutaDocumento = rutaDocumento + File.separator + documento.getNombreDocumento();
			File source = new File(rutaDocumento);
			try{
				FileUtils.moveToDirectory(source, pathDest, false);
			} catch(IOException e){
			// el documento no esta aqui :v
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#eliminarPdf(mx.ine.gestion.
	 * dto.db.DTODocumentoEntity)
	 */
	@Override
	public void eliminarPdf(DTODocumentoEntity documento) {

		String rutaDocumento = obtenerRutaGlusterPdfNube();
		rutaDocumento = rutaDocumento + File.separator + documento.getNombreDocumento().split("\\.")[0] + ".pdf";
		File source = new File(rutaDocumento);
		if(source.exists()){
			source.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#renombrarArchivo(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public void renombrarArchivo(String ruta, String nombre, String nombreNuevo) {

		File fileGlus = new File(ruta + File.separator + nombre);
		File newName = new File(ruta + File.separator + nombreNuevo);
		fileGlus.renameTo(newName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#modificarAnexos(java.util.List,
	 * java.util.List, java.util.List)
	 */
	@Override
	public void modificarAnexos(List<DTODocumentoAnexoEntity> anexosAnteriores, List<DTODocumentoAnexoEntity> anexosNuevos,
			List<DTODocumentoAnexoEntity> anexosRepetidos, String noDocumento, Integer idDocumento) throws IOException {


		String rutaGluster = obtenerRutaGlusterAnexosNube();
		File file = new File(rutaGluster);
		List<Integer> secuenciaDisponible = new ArrayList<Integer>();
		Integer max = 0;
		for (DTODocumentoAnexoEntity anexo : anexosAnteriores){
			// renombramos y contamos los anexos anteriores -.-
			String[] aux = anexo.getNombreAnexo().split("_");// nobredooc
																// sec.extension
			aux = aux[aux.length - 1].split("\\.");
			Integer i = Integer.parseInt(aux[0]);
			secuenciaDisponible.add(i);
			renombrarArchivo(rutaGluster, anexo.getNombreAnexo(), "temp_" + anexo.getNombreAnexo());
			if(max < i)
				max = i;
		}
		for (DTODocumentoAnexoEntity anexo : anexosRepetidos){
			String[] aux = anexo.getNombreAnexo().split("_");
			aux = aux[aux.length - 1].split("\\.");
			Integer i = Integer.parseInt(aux[0]);
			if(max < i)
				max = i;
		}
		for (Iterator<DTODocumentoAnexoEntity> iterator = anexosNuevos.iterator(); iterator.hasNext();){
			DTODocumentoAnexoEntity anexo = iterator.next();
			Integer consecutivo;
			if(!secuenciaDisponible.isEmpty()){
				consecutivo = secuenciaDisponible.get(0);
				secuenciaDisponible.remove(0);
			} else{
				consecutivo = ++max;
			}
			String nombreAnexo = noDocumento + "_" + consecutivo + "." + anexo.getNombreAnexo().split("\\.")[1];
			File fileTmp = new File(obtenerRutaGlusterTemporales() + File.separator + anexo.getNombreAnexo());
			FileUtils.moveToDirectory(fileTmp, file, false);
			// renombramos
			renombrarArchivo(rutaGluster, anexo.getNombreAnexo(), nombreAnexo);
			anexo.setNombreAnexo(nombreAnexo);
			anexo.setIdAnexo(consecutivo);
		}
		// si sobran disponibles se quitaron mas anexos de los que se agregron,
		// recorremos
		if(!secuenciaDisponible.isEmpty()){
			for (DTODocumentoAnexoEntity anexo : anexosRepetidos){
				String[] aux = anexo.getNombreAnexo().split("_");
				aux = aux[aux.length - 1].split("\\.");
				Integer i = Integer.parseInt(aux[0]);
				Integer lugares = 0;
				for (Integer j : secuenciaDisponible){
					if(i > j)
						lugares++;
					else
						break;
				}
				if(lugares > 0){
					Integer posicionNueva = (i - lugares);
					String nombreAnexo = noDocumento + "_" + posicionNueva + "." + anexo.getNombreAnexo().split("\\.")[1];
					renombrarArchivo(rutaGluster, anexo.getNombreAnexo(), nombreAnexo);
					anexo.setNombreAnexo(nombreAnexo);
					anexo.setIdAnexo(posicionNueva);
				}
			}
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el
	 * método)
	 * 
	 * @see
	 * mx.ine.gestion.bo.inter.BOArchivoInteface#borrarArchivo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void borrarArchivo(String ruta, String nombre) {

		String rutaMaterialTmp = "";
		try{
			rutaMaterialTmp = ruta + File.separator + nombre;
			File rutaCompletaFile = new File(rutaMaterialTmp);
			rutaCompletaFile.delete();
		} catch(Exception e){}
	}
}
