/**
 * @(#)BOFirma.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import mx.ine.gestion.bo.inter.BOFirmaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODatosFirmaDocEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentosRemitentesEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jboss.logging.Logger;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lóogica y procedimientos del módulo de Firma.
 * 
 * @author David Rodríguez Corral
 * @since 15/10/2017
 */
@Component("boFirma")
@Scope("prototype")
public class BOFirma implements BOFirmaInterface{
	
	private static final Logger logger = Logger.getLogger(BOFirma.class);
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearComentario(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOComentariosDocumentoEntity crearComentario(
			DTOFirmaDocumentosEntity dtoFirma) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOComentariosDocumentoEntity dtoComentario = new DTOComentariosDocumentoEntity();
		dtoComentario.setIdDocumento(dtoFirma.getIdDocumento());
		dtoComentario.setAnio(anio);
		dtoComentario.setComentarios(dtoFirma.getComentario());
		dtoComentario.setEstatus(0);
		
		return dtoComentario;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearEdicion(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOEdicionesDocumentoEntity crearEdicion(
			DTOFirmaDocumentosEntity dtoFirma) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOEdicionesDocumentoEntity dtoEdicion = new DTOEdicionesDocumentoEntity();
		dtoEdicion.setIdDocumento(dtoFirma.getIdDocumento());
		dtoEdicion.setAnio(anio);
		dtoEdicion.setEstatus(Integer.parseInt(Utilidades.mensajeProperties("edicion_estatus_no_leido")));
		dtoEdicion.setUsuarioEdicion(SecurityContextHolder.getContext().getAuthentication().getName());
		dtoEdicion.setFechaHoraEdicion(date);
		
		return dtoEdicion;
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearHistorial(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOHistDocCreacionEntity crearHistorial(
			DTOFirmaDocumentosEntity dtoFirma) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOHistDocCreacionEntity dtoCreacionHist = new DTOHistDocCreacionEntity();
		dtoCreacionHist.setIdDocumento(dtoFirma.getIdDocumento());
		dtoCreacionHist.setAnio(anio);
		dtoCreacionHist.setIdPersonaHist(dtoFirma.getIdPersona());
		
		return dtoCreacionHist;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#verificarFirmaYValidacion(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public Integer verificarFirmaYValidacion(DTODocumentoEntity dtoDocumento) {
		Integer verifica = 0;
		if(dtoDocumento.getFirmado() == 1){
			
			verifica = 1;
			
		}else{
			
			if(dtoDocumento.getValidado() == 1){
				
				verifica = 2;
				
			}else{
				
				verifica = 3;
				
			}
		}
		return verifica;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearDatosDatosFirmas(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTODatosFirmaDocEntity crearDatosDatosFirmas(
			DTOFirmaDocumentosEntity dtoFirmaDocumentos) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearDatosDatosFirmas(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	public DTOEnviadosDocumentosEntity crearEnviados(DTOFirmaDocumentosEntity dtoEnviadosDocumentos, Integer idArea, Integer tipoArea, Integer idPersona) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOEnviadosDocumentosEntity dtoEnviados = new DTOEnviadosDocumentosEntity();

		dtoEnviados.setIdDocumento(dtoEnviadosDocumentos.getIdDocumento());
		dtoEnviados.setAnio(anio);
		dtoEnviados.setUsuarioEnvio(SecurityContextHolder.getContext().getAuthentication().getName());
		dtoEnviados.setNumEnviadoFirma(0);
		dtoEnviados.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
		dtoEnviados.setIdArea(idArea);
		dtoEnviados.setTipoArea(tipoArea);
		dtoEnviados.setIdPersona(idPersona);
		dtoEnviados.setEstatus('A');
		return dtoEnviados;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#configurarComentario(mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity)
	 */
	@Override
	public void configurarComentario(
			DTOComentariosDocumentoEntity dtoComentario) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
	
		dtoComentario.setAnio(anio);
		dtoComentario.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("comentario_estatus_no_leido")));
		dtoComentario.setTipoComentario('C');

	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearBandejaEntrada(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOBandejaEntradasOficialiaEntity crearBandejaEntrada(
			DTOBandejaEntradasOficialiaEntity dtoOficialiasAreas) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOBandejaEntradasOficialiaEntity dtoOficialia = new DTOBandejaEntradasOficialiaEntity();
		dtoOficialia.setIdOficialia(dtoOficialiasAreas.getIdOficialia());
		dtoOficialia.setIdAreaDestinatario(dtoOficialiasAreas.getIdAreaDestinatario());
		dtoOficialia.setTipoAreaDestinatario(dtoOficialiasAreas.getTipoAreaDestinatario());
		dtoOficialia.setIdAreaRemitente(dtoOficialiasAreas.getIdAreaRemitente());
		dtoOficialia.setTipoAreaRemitente(dtoOficialiasAreas.getTipoAreaRemitente());
		dtoOficialia.setFechaRecepcion(new Date());
		dtoOficialia.setAnio(anio);
		dtoOficialia.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_noLeido")));
		
		return dtoOficialia;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearPdf(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void crearPdf(DTODocumentoEntity documento, String carpeta) throws Exception {
		String docPath = Utilidades.getRutaGlusterFS() + "/Gestion4/"+carpeta+"/" + documento.getNombreDocumento();
		String pdfPath = Utilidades.getRutaGlusterFS() + "/Gestion4/pdf/";
		if(documento.getTipoCaptura().intValue() == 3){
			File source = new File(docPath);
			logger.error("se busca el pdf en : " + docPath);
			File dest = new File(pdfPath + documento.getNombreDocumento());
			FileUtils.copyFile(source, dest);
			logger.error("se copio el pdf en : " + pdfPath + documento.getNombreDocumento());
		} else{
//			DocxFixer.fix(docPath);
			transformarOpenOffice(docPath, pdfPath, documento);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#transformarOpenOffice(java.lang.String, java.lang.String, mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void transformarOpenOffice(String docPath, String pdfPath,
			DTODocumentoEntity documento) throws OfficeException {
		// version 2
				// 1) Start LibreOffice in headless mode.
				OfficeManager officeManager = null;
				DefaultOfficeManagerBuilder ob = null;
				File source = new File(docPath);
				File dest = new File(pdfPath + documento.getIdDocumento()+"_"+documento.getAnio() + ".pdf");
				try{
					ob = new DefaultOfficeManagerBuilder();
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
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearDocsRems(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTODocumentosRemitentesEntity crearDocsRems(DTOFirmaDocumentosEntity dtoFirmados) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTODocumentosRemitentesEntity dtoDocsRems = new DTODocumentosRemitentesEntity();
		//dtoEnviados.setIdPersona(dtoEnviadosDocumentos.getDtoDocumento().getIdPersona());
		dtoDocsRems.setIdDocumento(dtoFirmados.getIdDocumento());
		dtoDocsRems.setIdPersonaRemitente(dtoFirmados.getIdPersonaRemitente());
		dtoDocsRems.setAnio(anio);
		return dtoDocsRems;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearHashDocumento(java.lang.String, java.lang.String)
	 */
	public String crearHashDocumento(String nombreDocumento, String path) throws NoSuchAlgorithmException, IOException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		String file = path + File.separator + "Gestion4" + File.separator + "documentos" + File.separator + nombreDocumento;
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[1024];
		int read = 0;
		while ((read = fis.read(data)) != -1){
			sha256.update(data, 0, read);
		}
		;
		byte[] hashBytes = sha256.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hashBytes.length; i++){
			sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		String fileHash = sb.toString();
		fis.close();
		return fileHash;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#insertarFirmaEnDcumento()
	 */
	@SuppressWarnings("resource")
	public void insertarFirmaEnDcumento(DTODocumentoEntity dtoDocumento,
			DTOFirmaDocumentosEntity dtoFirma)throws  IOException, Exception, FileNotFoundException{
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		String rutaGluster = Utilidades.getRutaGlusterFS()+"Gestion4"+File.separator;//Utilidades.getRutaGlusterFS()+"\\Gestion4\\";
		String carpetaOrigen = "documentos" + File.separator; 
		String carpetaDestino = "documentos_firmados" + File.separator; 
		String ruta;
		String ruta2;  
		String nombreDocumento = dtoFirma.getIdDocumento()+"_"+dtoFirma.getDtoDocumento().getAnio();
		
		//for(int i=0;i<dtoDocumentosSeleccionados.size();i++){
			
			logger.error("RUTA: " + Utilidades.getRutaGlusterFS()+"Gestion4"+File.separator);			
			
			//this.dtoBorradorDocumento = bsdFirmaInterface.obtenerBorrador(this.dtoDocumentosSeleccionados.get(i).getIdDocumento(), usuarioLogueado.getIdPersona());
			
			ruta2 = rutaGluster+carpetaDestino+nombreDocumento+".docx";
			
			if(dtoDocumento.getFirmado().intValue() == 0){
				
				ruta = rutaGluster+carpetaOrigen+nombreDocumento+".docx";
								
			}else{
				
				ruta = rutaGluster+carpetaDestino+nombreDocumento+".docx";
				
			}
				
				InputStream documento = new FileInputStream(ruta);
				XWPFDocument document= new XWPFDocument(documento);
			    FileOutputStream out = new FileOutputStream(ruta2);
			    logger.error(ruta);
			    logger.error(ruta2);
			    XWPFParagraph titulo = document.createParagraph();
			    XWPFRun r=titulo.createRun();
			    r.setFontFamily("Arial");	
			    r.setFontSize(8);
			    
			      
			    if(dtoDocumento.getFirmado().intValue() == 0){
			    
			    	r.addBreak(BreakType.PAGE);
			    	r.setText("Cadena original documento:");
			    	r.addBreak();
				    r.setText(dtoFirma.getDtoFirmaElectronica().getCadenaOriginal());
				    r.addBreak();
				    r.addBreak();
				    r.setText("Firma electrónica del documento:");
				    r.addBreak();
				    r.addBreak();
				    
			    
			    }
			    
			    r.setText(usuarioLogueado.getNombreUsuario());
			    r.addBreak();
			    r.setText(dtoFirma.getDtoFirmaElectronica().getHashPkcs7());
			    
			    document.write(out);
			    out.close();
			     
			    logger.info("El archivo word ha sido firmado");
			
			
			
		}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearHashDocumento(java.lang.String)
	 */
	@Override
	public String crearHashCadena(String cadena) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(cadena.getBytes("UTF-8"));
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}

}
