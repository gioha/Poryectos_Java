/**
 * @(#)VHFirmaInterface.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.vh.inter.VHFirmaInterface;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAV;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAVImpl;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene la implementación de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author David Rodríguez Corral
 * @since 15/10/2017
 */
@Component("vhFirma")
@Scope("prototype")
public class VHFirma implements VHFirmaInterface{

	
	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(VHFirma.class);
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#eliminarOficioSincambios(java.util.List)
	 */
	@Override
	public DTOFirmaDocumentosEntity verificarOficioSincambios(
			List<DTOFirmaDocumentosEntity> dtoLFirma) {

		for (DTOFirmaDocumentosEntity documento : dtoLFirma) {
			if(documento.getDtoComentario() == null){ //&& documento.getConModificaciones() == 0) {
				return documento;
			}
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#eliminarValidacionEnDocumento(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void eliminarValidacionEnDocumento(
			String ruta, String ruta2, String iniciales) throws Exception {
		InputStream documento = new FileInputStream(ruta);
		XWPFDocument document= new XWPFDocument(documento);
	    FileOutputStream out = new FileOutputStream(ruta2);
	    logger.info(ruta);
	    logger.info(ruta2);		    
	    
		//LEER EL CONTENIDO DEL FOOTER
		XWPFHeaderFooterPolicy policyFooter = new XWPFHeaderFooterPolicy(document);
		XWPFFooter footer = policyFooter.getDefaultFooter();

		boolean conValidaciones = false;
		
		if(footer != null) {
			List <XWPFParagraph> lista = footer.getListParagraph();
			Boolean token = false;
			for (XWPFParagraph parrafo : lista) {
				List<XWPFRun> runs = parrafo.getRuns();
		        if (runs != null) {
		            for (XWPFRun r : runs) {
		                logger.error("ESTE SI? " + r.getText(0));
		                String text = r.getText(0);
		                
		                if (text != null && text.contains("<<") ) {
		                	token=true;
		                }
		                if(text != null && token==true){
		                	logger.error("ENCONTRADO");
		                	conValidaciones = true;
		                	logger.error("el texto ->" + text);
		                    r.setText("", 0);
		                }
		                if(text != null && text.contains(">>")){
		                	r.setText("", 0);
		                	token =false;
		                }
		            }
		        }
			}
		}
		if(!conValidaciones) {
			
			logger.error("Por alguna razón no tiene validaciones el documento ");
		
		}
		//CERRAR ESCRITURA
	    document.write(out);
	    out.close();
	     
	    logger.info("Se han eliminado las validaciones del documento exitosamente");
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#visualizarDocumento(java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void visualizarDocumento(String nombreArchivo, String ruta,
			Boolean soloLectura) throws Exception {
		
		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink = "";
		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();
		File archivo = new File( File.separator + "Gestion4" + File.separator + "plantilla.docx");
		String rutaDoc = archivo.getParent() +  File.separator;
		rutaDoc =  rutaDoc.replaceAll("\\\\", "/");
		logger.error("UNO: "+rutaDoc);
		logger.error("DOS: "+File.separator+"Gestion4"+File.separator);
		
		try {
			
			hiperlink = asConversorURL.crearHiperLinkWebDav(
					nombreArchivo, 
					ruta, 
					SecurityContextHolder.getContext().getAuthentication().getName(), 
					soloLectura, 
					"sidjwebdav", 
					(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
			
			
			logger.error("<================ HPLINK: " + hiperlink);
			
		} catch (GeneralSecurityException e) {
			logger.error("",e);
		} catch (IOException e) {
			logger.error("",e);
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(hiperlink);
		} catch (IOException e) {
			logger.error("",e);
			
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#verificarOficioConNotificacion(java.util.List)
	 */
	@Override
	public String verificarOficioConNotificacion(List<DTOFirmaDocumentosEntity> dtoLFirma) {
		
		for (DTOFirmaDocumentosEntity documento : dtoLFirma) {
			if (documento.getEstatusRegresado().intValue() == 1 && documento.getDtoPersonaRegreso() != null) {
				
				return documento.getDtoDocumento().getNoDocumento();
			}
		}

		return null;
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#obtenerAlgoritmoCertificado(java.io.InputStream)
	 */
	@Override
	public String obtenerAlgoritmoCertificado(InputStream inp) throws CertificateException {
		
	    CertificateFactory cf = CertificateFactory.getInstance("X509");
	    X509Certificate certificado = (X509Certificate)cf.generateCertificate(inp);
	    
	    String AlgoritmoHash = certificado.getSigAlgName();
	    
	    logger.info("El algoritmo del Certificado es "+ AlgoritmoHash);
	    
	    if(AlgoritmoHash.contains("SHA1")){
	    	logger.info("SHA1");
	    	return "SHA1";
	    }
	    if(AlgoritmoHash.contains("SHA256")){
	    	logger.info("SHA-256");
	    	return "SHA-256";
	    }
	    if(AlgoritmoHash.contains("SHA384")){
	    	logger.info("SHA-384");
	    	return "SHA-384";
	    }
	    if(AlgoritmoHash.contains("SHA512")){
	    	logger.info("SHA-512");
	    	return "SHA-512";
	    }
	    return "";
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHFirmaInterface#eliminarDocumentosLeidos(java.util.List)
	 */
	@Override
	public List<DTOFirmaDocumentosEntity> eliminarDocumentosLeidos(List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados) {
		
		List<DTOFirmaDocumentosEntity> dtoDoc = new ArrayList<DTOFirmaDocumentosEntity>(dtoDocumentosSeleccionados);
		//dtoDoc = dtoDocumentosSeleccionados;
		Iterator<DTOFirmaDocumentosEntity> i = dtoDoc.iterator();
		while (i.hasNext()) {
			if(i.next().getNoLeido().intValue() == 0){
				i.remove();
			}
		}
		return dtoDoc;
	}

}
