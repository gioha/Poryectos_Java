/**
 * @(#)BOCapturaDocumento.java 01/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import mx.ine.gestion.as.impl.ASCapturarDocumento;
import mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.jboss.logging.Logger;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase de la capa de BO para funciones de negocio relacionadas al documento.
 * 
 * @author Sergio Ley Garcia
 * @since 01/09/2017
 */
@Component("boCapturaDocumento")
@Scope("prototype")
public class BOCapturaDocumento implements BOCapturaDocumentoInterface {

	private static Logger logger= Logger.getLogger(BOCapturaDocumento.class);
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface#generarEntidadDocumento(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public DTODocumentoEntity generarEntidadDocumento(DTOFiltrosCapturaDocumentoHelper filtros) {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		DTODocumentoEntity documento = new DTODocumentoEntity();
		documento.setTipoArea(user.getTipoArea());
		documento.setIdArea(user.getIdArea());
		documento.setIdTipoDocumento(filtros.getIdTipoDocumento());
		documento.setIdAcronimo(filtros.getIdAcronimo());
		documento.setIdPersona(user.getIdPersona());
		documento.setEstatus('A');
		documento.setEditado(0);
		documento.setFirmado(0);
		documento.setValidado(0);
		documento.setContieneAnexos(0);
		documento.setFechaCreacion(new Date());
		documento.setTipoCaptura(filtros.getTipoCapturaDocumento());
		documento.setAsunto(filtros.getAsunto());
		documento.setProcedenciaDocumento('S');
		documento.setNombreDocumento("NOMBRE_TEMPORAL");
		documento.setIdDocumentoRespuesta(filtros.getIdDocumentoRespondido());
		
		Calendar calendario = Calendar.getInstance();
		documento.setAnio(calendario.get(Calendar.YEAR));
		
		return documento;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface#generaEntidadBorrador(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper, java.lang.Integer)
	 */
	@Override
	public DTOBorradorDocumentosEntity generaEntidadBorrador(Integer idDocumento) {

		DTOUsuarioLogin user = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		DTOBorradorDocumentosEntity borrador = new DTOBorradorDocumentosEntity();
		borrador.setIdDocumento(idDocumento);
		borrador.setIdPersona(user.getIdPersona());
		borrador.setConComentarios(0);
		borrador.setConFirma(0);
		borrador.setConModificaciones(0);
		borrador.setConValidacion(0);
		borrador.setConRechazo(0);
		borrador.setNoLeido(0);
		borrador.setEstatus(Utilidades.mensajeProperties("documento_activo").charAt(0));
		borrador.setIdArea(user.getIdArea());
		borrador.setTipoArea(user.getTipoArea());
		
		Calendar calendario = Calendar.getInstance();
		borrador.setAnio(calendario.get(Calendar.YEAR));
		
		return borrador;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface#guardarDocumentoGlusterPrincipal(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper, java.lang.Integer)
	 */
	@Override
	public void guardarDocumentoGlusterPrincipal(DTOFiltrosCapturaDocumentoHelper filtros, Integer idDocumento) throws Exception {

		Calendar calendario = Calendar.getInstance();
		
		//1.- Para documento que fueron de plantillas o en blanco
		if (filtros.getTipoCapturaDocumento() != 3) {

			String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
					  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + filtros.getNombreTemporalDocumento();

			String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaDocumentos");
	
			File archivoTemporal = new File(rutaTemporalDocumento);
			File rutaPrincipal = new File(rutaDocumentoPrincipal);
			File archivoTmpPrincipal = new File(rutaDocumentoPrincipal + File.separator + filtros.getNombreTemporalDocumento());
			File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".docx");

			if (!rutaPrincipal.exists()) {
				rutaPrincipal.mkdirs();
			}

			FileUtils.moveToDirectory(archivoTemporal, rutaPrincipal, false);
			archivoTmpPrincipal.renameTo(archivoPrincipal);
		}
	
		//2.- Para documento que fueron adjuntos
		else {

			String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
					  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + filtros.getNombreTemporalDocumento();

			String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaPdf");
	
			File archivoTemporal = new File(rutaTemporalDocumento);
			File rutaPrincipal = new File(rutaDocumentoPrincipal);
			File archivoTmpPrincipal = new File(rutaDocumentoPrincipal + File.separator + filtros.getNombreTemporalDocumento());
			File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".pdf");

			if (!rutaPrincipal.exists()) {
				rutaPrincipal.mkdirs();
			}

			FileUtils.moveToDirectory(archivoTemporal, rutaPrincipal, false);
			archivoTmpPrincipal.renameTo(archivoPrincipal);
		}
		
		//3.- guardamos documentos Anexos
		if( filtros.getAnexos() != null && filtros.getAnexos().size() > 0 ){
			
			String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
					  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator ;

			String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaAnexos");

			int count = 1;
			for ( DTODocumentoAnexoHelper anexo : filtros.getAnexos() ) {
			
				String temporal = "";
				temporal = rutaTemporalDocumento;
				File archivoTemporal = new File(temporal  + anexo.getNombreTemporal() );
				File rutaPrincipal = new File(rutaDocumentoPrincipal);
				File archivoTmpPrincipal = new File(rutaDocumentoPrincipal + File.separator + anexo.getNombreTemporal());
				File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + "_" + count + "." + anexo.getTipoExtencion() );

				if (!rutaPrincipal.exists()) {
					rutaPrincipal.mkdirs();
				}

				FileUtils.moveToDirectory(archivoTemporal, rutaPrincipal, false);
				archivoTmpPrincipal.renameTo(archivoPrincipal);
				
				count ++;
			}	
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface#reversaAGuardarDocumentoGlusterPrincipal(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper, java.lang.Integer)
	 */
	@Override
	public void reversaAGuardarDocumentoGlusterPrincipal(DTOFiltrosCapturaDocumentoHelper filtros, Integer idDocumento) throws Exception {

		Calendar calendario = Calendar.getInstance();
		
		//1.- Para documento que fueron de plantillas o en blanco
		if (filtros.getTipoCapturaDocumento() != 3) {

			String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
					  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();

			String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaDocumentos");
	
			File rutaTmp = new File(rutaTemporalDocumento);
			File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".docx");
			File archivoTemporalNombreErroneo = new File(rutaTemporalDocumento + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".docx");
			File archivoTemporalNombreCorrecto = new File(rutaTemporalDocumento + File.separator + filtros.getNombreTemporalDocumento());
			

			FileUtils.moveToDirectory(archivoPrincipal, rutaTmp, false);
			archivoTemporalNombreErroneo.renameTo(archivoTemporalNombreCorrecto);
			
		}
		//2.- Para documento que fueron adjuntos
		else {

			String rutaTemporalDocumento = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
					  + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();

			String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
					  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaPdf");
	
			File rutaTmp = new File(rutaTemporalDocumento);
			File archivoPrincipal = new File(rutaDocumentoPrincipal + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".pdf");
			File archivoTemporalNombreErroneo = new File(rutaTemporalDocumento + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".pdf");
			File archivoTemporalNombreCorrecto = new File(rutaTemporalDocumento + File.separator + filtros.getNombreTemporalDocumento());
			

			FileUtils.moveToDirectory(archivoPrincipal, rutaTmp, false);
			archivoTemporalNombreErroneo.renameTo(archivoTemporalNombreCorrecto);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOCapturaDocumentoInterface#transformarAPdf(java.lang.Integer)
	 */
	@Override
	public void transformarAPdf(Integer idDocumento) throws OfficeException {

		Calendar calendario = Calendar.getInstance();
		String rutaDocumentoPrincipal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaDocumentos") 
				  + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".docx";
		
		String rutaDestinoPdf = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaPdf");
		
		String rutaFinalPdf = rutaDestinoPdf + File.separator + idDocumento + "_" + calendario.get(Calendar.YEAR) + ".pdf"; 
		
		File rutaPdf = new File(rutaDestinoPdf);
		if (!rutaPdf.exists()) {
			rutaPdf.mkdirs();
		}
		
		// version 2
		// 1) Start LibreOffice in headless mode.
		OfficeManager officeManager = null;
		DefaultOfficeManagerBuilder ob = null;
		
		File source = new File(rutaDocumentoPrincipal);
		File dest = new File(rutaFinalPdf);
		
		logger.error( "ruta libre office: " + Utilidades.getRutaOfficeFS() );
		
		try{
			
			ob = new DefaultOfficeManagerBuilder();
			
			officeManager = ob.setOfficeHome(new File(Utilidades.getRutaOfficeFS())).build();
			officeManager.start();
			
			// 2) Create JODConverter converter
			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

			// 3) Create PDF
			converter.convert(source, dest);
			
		} 
		
		catch(OfficeException oe) {
			
			logger.error("OfficeExeption: " + oe.getMessage() + " causa: " + oe.getCause() + " mensaje localizado: " + oe.getLocalizedMessage() + " traza: " + oe.getStackTrace() );
		}
		
		catch (Exception e) {
			
			logger.error("OfficeExeption: " + e.getMessage() + " causa: " + e.getCause() + " mensaje localizado: " + e.getLocalizedMessage() + " traza: " + e.getStackTrace() );
		}
		
		finally{
			// 4) Stop LibreOffice in headless mode.
			if(officeManager != null){
				
				officeManager.stop();
				
			}
		}
		
		// version 4
//		ExternalOfficeManagerConfiguration officeConfiguration = new 
//		ExternalOfficeManagerConfiguration();
//		officeConfiguration.setConnectionProtocol(OfficeConnectionProtocol.SOCKET);
//		officeConfiguration.setPortNumber(2002);
//		OfficeManager officeManager = officeConfiguration.buildOfficeManager();
//		officeManager.start();
//
//		OfficeManager officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
//		officeManager.start();
		
		
		// version 3	
//		try {
//			String comando = "";
//			if(SystemUtils.IS_OS_WINDOWS)
//			{
//				comando = "C:/Program Files/LibreOffice 5/program/soffice.exe --headless --convert-to pdf "+ rutaDocumentoPrincipal +" --outdir "+ rutaDestinoPdf ;
//			} // Program Files (x86)
//			else
//			{
//				comando = "/opt/libreoffice5.4/program/soffice --headless --convert-to pdf "+ rutaDocumentoPrincipal +" --outdir "+ rutaDestinoPdf + File.separator  ;
//			}
//			
//			logger.error("-*-*-*-*-*-*-*-*-*-*-*-comando: "+comando);
//			Process p = Runtime.getRuntime().exec(comando);
//			logger.error("-*-*-*-*-*-*-*-*--Se ejecuto comando");
//			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//			String s = "";
//			// reading output stream of the command
//			while ((s = inputStream.readLine()) != null) {
//				logger.error(s);
//			}
			
//			esto no
			
//			File f = new File(noti.getRutaArchivo()+noti.getNombreArchivo().split("\\.")[0]+".pdf");
//			   if(f.exists() && f.canRead())
//			   {
//				   f.renameTo(new File(noti.getRutaArchivo()+noti.getNombreArchivo().split("\\.")[0]+"1.pdf"));
//				   insertaFirmaPdf(noti, cadenaOriginal, nombreUsuario, firmaElectronicaOficio);
//			   }
//			   else
//			   {
//				   logger.error("-*-*-*-*-**-*-*-*No existe archivo en pdf");
//			   }

//		} catch (Exception e) {
//			logger.error("-*-*-*-*-*-*-*-*-Ocurrió un error al ejecutar el comando para convertir a pdf: ", e);			
//		} 
		
	}
}
