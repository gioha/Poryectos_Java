package mx.ine.gestion.bo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.ine.gestion.bo.inter.BOValidacionInterface;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.util.Utilidades;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que tendrá la lógica y procedimientos del módulo de Validación.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 16/10/2017
 */
@Component("boValidacion")
@Scope("prototype")
public class BOValidacion implements BOValidacionInterface{

	//private static final Logger logger = Logger.getLogger(ASValidacion.class);
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOValidacionInterface#crearComentario(mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity)
	 */
	@Override
	public DTOComentariosDocumentoEntity crearComentario(
			DTOValidacionDocumentosEntity dtoFirma) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOComentariosDocumentoEntity dtoComentario = new DTOComentariosDocumentoEntity();
		dtoComentario.setIdDocumento(dtoFirma.getIdDocumento());
		dtoComentario.setAnio(anio);
		dtoComentario.setComentarios(dtoFirma.getComentario());
		dtoComentario.setEstatus(1);
		
		return dtoComentario;
	}
	
	@Override
	public void configurarComentario(DTOComentariosDocumentoEntity dtoComentario) {
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
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearEdicion(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOEdicionesDocumentoEntity crearEdicion(
			DTOValidacionDocumentosEntity dtoFirma) {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = cal.get(Calendar.YEAR);
		
		DTOEdicionesDocumentoEntity dtoEdicion = new DTOEdicionesDocumentoEntity();
		dtoEdicion.setIdDocumento(dtoFirma.getIdDocumento());
		dtoEdicion.setAnio(anio);
		dtoEdicion.setEstatus(Integer.valueOf(Utilidades.mensajeProperties("edicion_estatus_no_leido")));

		return dtoEdicion;
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearHistorial(mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity)
	 */
	@Override
	public DTOHistDocCreacionEntity crearHistorial(
			DTOValidacionDocumentosEntity dtoFirma) {
		
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
	
	public void validarDocumento(DTOValidacionDocumentosEntity dtoValidacionDocumento, String iniciales) {
		String rutaGluster = Utilidades.getRutaGlusterFS() + "Gestion4\\"; /* rutaHardcodeada */

		String carpetaOrigen = "documentos\\";
		String ruta;
				ruta = rutaGluster
						+ carpetaOrigen
						+ dtoValidacionDocumento.getIdDocumento() + "_" + dtoValidacionDocumento.getAnio() + ".docx";
						//+ dtoValidacionDocumento.getDtoDocumento()
						//		.getNoDocumento() + ".docx";
				try {
					agregarValidacionEnDocumento(ruta,ruta, iniciales);
				} catch (FileNotFoundException fnf) {
					//vhValidacionInterface.enviarError(fnf, "MBValidacion",
					//		"validar()",
					//		"Ocurrió un error, no se encontró el archivo.");
				} catch (Exception e) {
					//vhValidacionInterface.enviarError(e, "MBValidacion",
					//		"validar()",
					//		"Ocurrió un error al tratar de validar el archivo.");
				}
	}

	public void agregarValidacionEnDocumento(String ruta, String ruta2, String iniciales) throws  IOException, Exception{
		InputStream documento = new FileInputStream(ruta);
		XWPFDocument document= new XWPFDocument(documento);
	    FileOutputStream out = new FileOutputStream(ruta2);
	    
	    
		//LEER EL CONTENIDO DEL FOOTER
		XWPFHeaderFooterPolicy policyFooter = new XWPFHeaderFooterPolicy(document);
		XWPFFooter footer = policyFooter.getDefaultFooter();

		boolean conValidaciones = false;

		if(footer != null) {
			List <XWPFParagraph> lista = footer.getListParagraph();

			for (XWPFParagraph parrafo : lista) {
				List<XWPFRun> runs = parrafo.getRuns();
				Boolean token = false;
				if (runs != null) {
		            boolean seValido = false;
					for (XWPFRun r : runs) {
		                String text = r.getText(0);
		           
		                if(token == false && text != null) {
		                	if(text.contains("Revisó")) {
		                		token = true;
		                		if (text.contains("||")) {
			                		r.setText(text.substring(0, text.indexOf("Revisó")) + "Revisó y Aprobó: " + iniciales + " ||" + text.substring(text.indexOf("||") + 1, text.length()-1), 0);
			                		seValido = true;
			                		token = false;
		                		} else {
		                			r.setText(text.substring(text.indexOf("Revisó") + 1, text.length()-1), 0);
		                		}
		                	}
		                } else if (token == true && text != null) {
		                	if(text.contains("||")) {
		                		token = false;
		                		r.setText("Revisó y Aprobó: " + iniciales + " ||" + text.substring(text.indexOf("||") + 1, text.length()-1),0);
		                		seValido = true;
		                	} else {
		                		r.setText("",0);
		                	}
		                }
		                
	                	conValidaciones = true;
		            }
		        }
			}	
		} else {
				//ESCRIBIR EN EL CONTENIDO DEL FOOTER SIN BORRAR
			    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
				XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
				
				CTP ctpFooter = CTP.Factory.newInstance();
				CTR ctrFooter = ctpFooter.addNewR();
				CTText ctFooter = ctrFooter.addNewT();
				String footerText = "Revisó y Aprobó: " + iniciales + " ||";
				ctFooter.setStringValue(footerText);
				XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
		
		        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
		        parsFooter[0] = footerParagraph;
			        
				policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);	
		}

		//CERRAR ESCRITURA
	    document.write(out);
	    out.close();     
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.bo.inter.BOFirmaInterface#crearPdf(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public void crearPdf(DTODocumentoEntity documento) throws Exception {
		String docPath = Utilidades.getRutaGlusterFS() + "/Gestion4/documentos/" + documento.getNombreDocumento();
		String pdfPath = Utilidades.getRutaGlusterFS() + "/Gestion4/pdf/";
		if(documento.getTipoCaptura().intValue() == 3){
			File source = new File(docPath);
			//logger.error("se busca el pdf en : " + docPath);
			File dest = new File(pdfPath + documento.getNombreDocumento());
			FileUtils.copyFile(source, dest);
			//logger.error("se copio el pdf en : " + pdfPath + documento.getNombreDocumento());
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
				File dest = new File(pdfPath + documento.getNoDocumento() + ".pdf");
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
	
}
