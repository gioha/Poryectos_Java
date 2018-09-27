package mx.ine.gestion.vh.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.gestion.as.inter.ASEstructuraInterface;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAV;
import mx.ine.sidj.webdav.cipher.ASConversorURLparaWEBDAVImpl;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.webapp.MultipartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @(#)VHCapturaDocumento.java 06/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
/**
 * Clase de la capa de VH para el módulo captura de documento.
 * 
 * @author Sergio Ley Garcia
 * @since 06/10/2017
 */
@Scope("prototype")
@Component("vhCapturaDocumento")
public class VHCapturaDocumento implements VHCapturaDocumentoInterface {
	
	@Autowired
	@Qualifier("asEstructura")
	private ASEstructuraInterface asEstructuraInterface;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#reiniciaFiltros(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void reiniciaFiltros(Integer tipoReinicio, DTOFiltrosCapturaDocumentoHelper filtros) {

		switch(tipoReinicio) {
		
			case 1:
				
				filtros.setIdAcronimo(null);
				filtros.setIdPlantilla(null);
				filtros.setListaPlantillas(null);
				filtros.setTipoCapturaDocumento(null);
				filtros.setNombreTemporalDocumento(null);
				filtros.setNombreOriginalPdf(null);
				break;
			
			case 2:

				filtros.setIdPlantilla(null);
				filtros.setTipoCapturaDocumento(null);
				filtros.setNombreTemporalDocumento(null);
				filtros.setNombreOriginalPdf(null);
				break;
				
			case 3:
				
				filtros.setIdEntidadRemitente(null);
				filtros.setIdAreaRemitente(null);
				filtros.setListaAreasRemitentes(null);
				break;
				
			case 4:
				
				filtros.setIdEntidadDestinatario(null);
				filtros.setIdAreaDestinatario(null);
				filtros.setListaAreasDestinatarios(null);
				break;
			
			case 5:

				filtros.setIdEntidadCCP(null);
				filtros.setIdAreaCCP(null);
				filtros.setListaAreasCCP(null);
				break;

			case 6:

				filtros.setIdPlantilla(null);
				filtros.setHyperLinkWord(null);
				//filtros.setNombreTemporalDocumento(null);
				//filtros.setNombreOriginalPdf(null);
				break;
		}
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarConsultaRemitentes(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarConsultaRemitentes(DTOFiltrosCapturaDocumentoHelper filtros) {

		String error = "";
		
		if ((filtros.getNombreRemitente() == null || filtros.getNombreRemitente().isEmpty()) && (filtros.getApellidoRemitente() == null || filtros.getApellidoRemitente().isEmpty()) 
		&& (filtros.getIdAreaRemitente() == null || filtros.getIdAreaRemitente() == 0)) {
	
			error = Utilidades.mensajeProperties("mensaje_error_campo_personas");
		}
		
		// el nombre no cumple
		if( !filtros.getNombreRemitente().matches( Utilidades.mensajeProperties("regex_solo_letras_acentos_espacios") ) ){
			error = Utilidades.mensajeProperties("mensaje_regex_solo_letras_acentos_espacios");
		}
	
		return error;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarConsultaDestinatarios(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarConsultaDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros) {
		
		String error = "";
		
		if ((filtros.getNombreDestinatario() == null || filtros.getNombreDestinatario().isEmpty()) && (filtros.getApellidoDestinatario() == null || filtros.getApellidoDestinatario().isEmpty()) 
		&& (filtros.getIdAreaDestinatario() == null || filtros.getIdAreaDestinatario() == 0)) {
	
			error = Utilidades.mensajeProperties("mensaje_error_campo_personas");
		}
	
		return error;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarConsultaDestinatariosPorArea(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarConsultaDestinatariosPorArea(DTOFiltrosCapturaDocumentoHelper filtros){
		
		String error = "" ;
		
		if ( filtros.getNombreAreaDestinatario() == null || filtros.getNombreAreaDestinatario().isEmpty() ){
			error = Utilidades.mensajeProperties("mensaje_error_destinatario_por_area");
		}
		
		return error;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarConsultaCCP(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarConsultaCCP(DTOFiltrosCapturaDocumentoHelper filtros) {
		
		String error = "";
		
		if ((filtros.getNombreCCP() == null || filtros.getNombreCCP().isEmpty()) && (filtros.getApellidoCCP() == null || filtros.getApellidoCCP().isEmpty()) 
		&& (filtros.getIdAreaCCP() == null || filtros.getIdAreaCCP() == 0)) {
	
			error = Utilidades.mensajeProperties("mensaje_error_campo_personas");
		}
	
		return error;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarAgregarRemitente(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarAgregarRemitente(DTOFiltrosCapturaDocumentoHelper filtros) {

		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (filtros.getRemitentesSeleccionados().isEmpty()) {

			return error = "Selecciona al menos una opción, intenta nuevamente";
		}
	
		//1.- Validamos si ya se habían agregado personas
		if (!filtros.getRemitentes().isEmpty()) {
			
			//1.1.- Obtenemos una persona base para obtener su área y comparar con los que se buscaron
			DTOEstructuraAreasEntity personaParaComparar = filtros.getRemitentes().get(0);
			
			//1.2.- Validamos con su área que no sea diferente
			for (DTOEstructuraAreasEntity persona : filtros.getRemitentesSeleccionados()) {
				
				if (!persona.getArea().equals(personaParaComparar.getArea())) {
					
					return error = "Los remitentes deben de ser de una sola área, verifica la información";
				}
			}
			
		} 

		//2.- Validamos para cuando aun no se habían seleccionado personas que todas sean de la misma área
		else {
			
			//2.1. -Obtenemos una persona base para obtener su área y comparar con los que se buscaron
			DTOEstructuraAreasEntity personaParaComparar = filtros.getRemitentesSeleccionados().get(0);
			
			//2.2.- Validamos con su área que no sea diferente a las demás
			for (DTOEstructuraAreasEntity persona : filtros.getRemitentesSeleccionados()) {
			
				if (!persona.getArea().equals(personaParaComparar.getArea())) {
					
					return error = "Los remitentes deben de ser de una sola área, verifica la información";
				}
			}
		}

		//3.- Validamos que no se seleccionen pesonas que ya esten de remitentes
		for (DTOEstructuraAreasEntity remitente : filtros.getRemitentes()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getRemitentesSeleccionados()) {

				if (remitente.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}
		}

		//4.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity destinatario : filtros.getDestinatarios()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getRemitentesSeleccionados()) {

				if (destinatario.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}
		}
		
		//5.- Validamos que no se seleccionen pesonas que ya esten de Ccp
		for (DTOEstructuraAreasEntity ccp : filtros.getCcp()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getRemitentesSeleccionados()) {

				if (ccp.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a para ccp, valida la información";
				}
			}
		}
		
		return error;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarAgregarDestinatario(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarAgregarDestinatario(DTOFiltrosCapturaDocumentoHelper filtros) {

		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (filtros.getDestinatariosSeleccionados().isEmpty()) {

			return error = "Selecciona al menos una opción, intenta nuevamente";
		}
	
		//2.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity destinatario : filtros.getDestinatarios()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getDestinatariosSeleccionados()) {

				if (destinatario.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}
		}
		
		//3.- Validamos que no se seleccionen pesonas que ya esten de remitentes
		for (DTOEstructuraAreasEntity remitente : filtros.getRemitentes()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getDestinatariosSeleccionados()) {

				if (remitente.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}
		}
		
		//4.- Validamos que no se seleccionen pesonas que ya esten de Ccp
		for (DTOEstructuraAreasEntity ccp : filtros.getCcp()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getDestinatariosSeleccionados()) {

				if (ccp.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a para ccp, valida la información";
				}
			}
		}
		
		//5.- Validamos que no se seleccionen pesonas pertenecientes a áreas ya agregadas como áreas destinatarias
		for (DTOCAreaEntity areaDestinataria : filtros.getDestinatariosComoArea()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getDestinatariosSeleccionados()) {

				if ( areaDestinataria.getIdArea().equals( personaSeleccionada.getIdArea() ) ){
					
					return error = personaSeleccionada.getArea().getDescripcion() + " ya fue registrada como Destinatario, verifica la información";
				}
			}
		}
		

		return error;
	}
	
	@Override
	public String validarAgregarAreaDestinatario( DTOFiltrosCapturaDocumentoHelper filtros) {
	
		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (filtros.getAreasDestinatariosSeleccionados().isEmpty()) {

			return error = "Selecciona al menos una opción, intenta nuevamente";
		}
		
		//2.- Validamos que el área destinataria seleccionada tenga un titular de área registrado
		List<DTOJerarquiaPersonasEntity> titularesBD = new ArrayList<DTOJerarquiaPersonasEntity>(); 
		titularesBD = asEstructuraInterface.consultarTitulares();
		
		for (DTOCAreaEntity areaSeleccionada : filtros.getAreasDestinatariosSeleccionados()){
			boolean tieneTitular = false;
			
			for (DTOJerarquiaPersonasEntity titular : titularesBD) {
				if( areaSeleccionada.getIdArea().equals( titular.getIdArea() ) ){
					tieneTitular = true;
					break;
				}
			}
			
			if(!tieneTitular)
				return error =  areaSeleccionada.getDescripcion() + " no cuenta con un Organigrama definido, valida la información";
		}
	
		//3.- Validamos que no se seleccionen áreas que ya esten de destionatarias
		for (DTOCAreaEntity areasDestinatario : filtros.getDestinatariosComoArea()) {
			
			for (DTOCAreaEntity areaSeleccionada : filtros.getAreasDestinatariosSeleccionados()) {

				if (areasDestinatario.equals(areaSeleccionada)) {
					
					return error = areaSeleccionada.getDescripcion() + " ya fue seleccionada como área destinataria, valida la información";
				}
			}
		}
		
		//4.- Validamos que no se seleccionen áreas que ya tengan personal agregado como destinatarios
		for (DTOEstructuraAreasEntity destinatario : filtros.getDestinatarios()) {
			
			for (DTOCAreaEntity areaSeleccionada : filtros.getAreasDestinatariosSeleccionados()) {
				
				if( destinatario.getIdArea().equals( areaSeleccionada.getIdArea() ) ){
					
					return error = areaSeleccionada.getDescripcion() + " ya fue registrada con un Destinatario, verifica la información";
				}
			}
		}

		return error;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validarAgregarCCP(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validarAgregarCCP(DTOFiltrosCapturaDocumentoHelper filtros) {

		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (filtros.getCcpSeleccionados().isEmpty()) {

			return error = "Selecciona al menos una opción, intenta nuevamente";
		}
	
		//2.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity ccp : filtros.getCcp()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getCcpSeleccionados()) {

				if (ccp.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a para ccp, valida la información";
				}
			}
		}
		
		//3.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity destinatario : filtros.getDestinatarios()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getCcpSeleccionados()) {

				if (destinatario.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}
		}
		
		//4.- Validamos que no se seleccionen pesonas que ya esten de remitentes
		for (DTOEstructuraAreasEntity remitente : filtros.getRemitentes()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getCcpSeleccionados()) {

				if (remitente.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}
		}

		return error;
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#agregarRemitentes(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void agregarRemitentes(DTOFiltrosCapturaDocumentoHelper filtros) {

		//1.- Agregamos las personas seleccionadas como remitentes
		for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getRemitentesSeleccionados()) {
			
			filtros.getRemitentes().add(personaSeleccionada);
		}

		//2.- Limpiamos los filtros de búsqueda
		filtros.setRemitentesSeleccionados(null);
		filtros.setPersonasBusquedasEncontradas(null);
		filtros.setNombreRemitente(null);
		filtros.setApellidoRemitente(null);
		filtros.setIdAreaRemitente(null);
		filtros.setIdEntidadRemitente(null);
		filtros.setTipoAreaRemitente(null);
	}

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#agregarDestinatarios(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void agregarDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros) {

		//1.- Agregamos las personas seleccionadas como destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getDestinatariosSeleccionados()) {
			
			filtros.getDestinatarios().add(personaSeleccionada);
		}

		//2.- Limpiamos los filtros de búsqueda
		filtros.setDestinatariosSeleccionados(null);
		filtros.setPersonasBusquedasEncontradas(null);
		filtros.setNombreDestinatario(null);
		filtros.setApellidoDestinatario(null);
		filtros.setIdAreaDestinatario(null);
		filtros.setIdEntidadDestinatario(null);
		filtros.setTipoAreaDestinatario(null);
	}

	@Override
	public void agregarAreasDestinatarios(DTOFiltrosCapturaDocumentoHelper filtros) {
		
		//1.- Agregamos las áreas seleccionadas como destinatarios
		for (DTOCAreaEntity areaSeleccionada : filtros.getAreasDestinatariosSeleccionados()) {
			
			filtros.getDestinatariosComoArea().add(areaSeleccionada);
		}

		//2.- Limpiamos los filtros de búsqueda
		filtros.setDestinatariosSeleccionados(null);
		filtros.setPersonasBusquedasEncontradas(null);
		filtros.setNombreDestinatario(null);
		filtros.setApellidoDestinatario(null);
		filtros.setIdAreaDestinatario(null);
		filtros.setIdEntidadDestinatario(null);
		filtros.setTipoAreaDestinatario(null);		
		
		//3.- Limpiamos variables de destiantarios tipo área
		filtros.setAreasDestinatariosSeleccionados(null);
		filtros.setAreasBusquedaEncontradas(null);
		filtros.setNombreAreaDestinatario(null);
		
	};
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#agregarCCP(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void agregarCCP(DTOFiltrosCapturaDocumentoHelper filtros) {

		//1.- Agregamos las personas seleccionadas como destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : filtros.getCcpSeleccionados()) {
			
			filtros.getCcp().add(personaSeleccionada);
		}

		//2.- Limpiamos los filtros de búsqueda
		filtros.setCcpSeleccionados(null);
		filtros.setPersonasBusquedasEncontradas(null);
		filtros.setNombreCCP(null);
		filtros.setApellidoCCP(null);
		filtros.setIdAreaCCP(null);
		filtros.setIdEntidadCCP(null);
		filtros.setTipoAreaCCP(null);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#generarHyperLinkDocumentoBlanco(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void generarHyperLinkDocumentoBlanco(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception {

		//1.- Copiamos el documento base a la carpeta temporal del usuario
		String rutaGlusterDocumentoBlanco = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
			  + File.separator + Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + Utilidades.mensajeProperties("constante_documento_blanco");

		String rutaDocumentoBlancoTemporal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();

		Calendar calendario = Calendar.getInstance();
		String nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" + String.valueOf(calendario.get(Calendar.MILLISECOND)) + ".docx";

		File documentoBlanco = new File(rutaGlusterDocumentoBlanco);
		File rutaDocumentoBlancoTemporalFile = new File(rutaDocumentoBlancoTemporal);
		File documentoBlancoTemporal = new File(rutaDocumentoBlancoTemporal + File.separator  + nombreTemporal);

		if (!rutaDocumentoBlancoTemporalFile.exists()) {
			rutaDocumentoBlancoTemporalFile.mkdirs();
		} else {
			 File[] files = rutaDocumentoBlancoTemporalFile.listFiles();
			 if(files != null) {
				 for(File documentosBasura: files) {
					 documentosBasura.delete();
				 }
			 }
		}

		FileUtils.copyFile(documentoBlanco, documentoBlancoTemporal);

		//2.- Generamos el hyperLink del documento en blanco
		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink = "";
		final boolean mostrarSoloLectura = false;
		
		String rutaDelTemporalParaWord = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") 
				+ File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + nombreTemporal;

		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();

		File archivo = new File(rutaDelTemporalParaWord);
		String rutaDoc = archivo.getParent() +  File.separator;
		rutaDoc =  rutaDoc.replaceAll("\\\\", "/");

		hiperlink = asConversorURL.crearHiperLinkWebDav(
					archivo.getName(), 
					rutaDoc, 
					SecurityContextHolder.getContext().getAuthentication().getName(), 
					mostrarSoloLectura, 
					"sidjwebdav", 
					(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());

		//3.- Guardamos el hyperlink y el nombre temporal
		filtros.setHyperLinkWord(hiperlink);
		filtros.setNombreTemporalDocumento(nombreTemporal);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#generarHyperLinkPlantilla(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public void generarHyperLinkPlantilla(DTOFiltrosCapturaDocumentoHelper filtros) throws Exception {

		//1.- Copiamos el documento base a la carpeta temporal del usuario
		DTOPlantillaEntity plantillaSeleccionada = filtros.getIdPlantilla();
		String rutaGlusterPlantillaUsuario = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
			  + File.separator + Utilidades.mensajeProperties("constante_generica_plantillas") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() 
			  + File.separator + plantillaSeleccionada.getIdTipoDocumento() + "_" + plantillaSeleccionada.getIdAcronimo() + "_" + plantillaSeleccionada.getIdPlantilla() + ".docx";

		String rutaDocumentoBlancoTemporal = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				  + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName();

		Calendar calendario = Calendar.getInstance();
		String nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" + String.valueOf(calendario.get(Calendar.MILLISECOND)) + ".docx";

		File plantillaUusario = new File(rutaGlusterPlantillaUsuario);
		File rutaDocumentoBlancoTemporalFile = new File(rutaDocumentoBlancoTemporal);
		File documentoBlancoTemporal = new File(rutaDocumentoBlancoTemporal + File.separator  + nombreTemporal);

		if (!rutaDocumentoBlancoTemporalFile.exists()) {
			rutaDocumentoBlancoTemporalFile.mkdirs();
		} else {
			 File[] files = rutaDocumentoBlancoTemporalFile.listFiles();
			 if(files != null) {
				 for(File documentosBasura: files) {
					 documentosBasura.delete();
				 }
			 } 
		}

		FileUtils.copyFile(plantillaUusario, documentoBlancoTemporal);

		//2.- Generamos el hyperLink del documento en blanco
		ASConversorURLparaWEBDAV asConversorURL;
		String hiperlink = "";
		final boolean mostrarSoloLectura = false;
		
		String rutaDelTemporalParaWord = File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") + File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp")
				+ File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator + nombreTemporal;

		asConversorURL = ASConversorURLparaWEBDAVImpl.getInstance();

		File archivo = new File(rutaDelTemporalParaWord);
		String rutaDoc = archivo.getParent() +  File.separator;
		rutaDoc =  rutaDoc.replaceAll("\\\\", "/");

		hiperlink = asConversorURL.crearHiperLinkWebDav(
					archivo.getName(), 
					rutaDoc, 
					SecurityContextHolder.getContext().getAuthentication().getName(), 
					mostrarSoloLectura, 
					"sidjwebdav", 
					(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());

		//3.- Guardamos el hyperlink y el nombre temporal
		filtros.setHyperLinkWord(hiperlink);
		filtros.setNombreTemporalDocumento(nombreTemporal);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#generarTemporalArchivoAdjunto(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper, org.primefaces.event.FileUploadEvent)
	 */
	@Override
	public void generarTemporalArchivoAdjunto(DTOFiltrosCapturaDocumentoHelper filtros, FileUploadEvent archivo, boolean esAnexo, boolean borrarTemporales) throws Exception {
		
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
			 if(files != null && borrarTemporales) {
				 for(File documentosBasura: files) {
					 documentosBasura.delete();
				 }
			 } 
		}

		long size = archivo.getFile().getSize();
		
		FileUtils.moveToDirectory(archivoTemporalPrime, archivoTemporal, false);
		
		//3.- Lo renombramos
		Calendar calendario = Calendar.getInstance();
		String nombreTemporal = "";
		
		// si es anexo, recuperamos la extención del archivo
		String extencion = "";
		if(esAnexo){

			String temp = ""; 
			for (char c :  new StringBuffer(archivo.getFile().getFileName()).reverse().toString().toCharArray()  ) {
				if( c == '.')
					break;
				else
					temp = temp + c;
			} 
			
			extencion = new StringBuffer(temp).reverse().toString();
			
			nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" + String.valueOf(calendario.get(Calendar.MILLISECOND)) + "." + extencion;
		}
		else{
			nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" + String.valueOf(calendario.get(Calendar.MILLISECOND)) + ".pdf";
		}

		File archivoArenombrar = new File(rutaTemporal + File.separator + nombreTemporalPrime);
		File archivoRenombrado = new File(rutaTemporal + File.separator + nombreTemporal);
		archivoArenombrar.renameTo(archivoRenombrado);
	
		//4.- Guardamos la información que necesitaremos más adelante
		if(esAnexo){
		
			Double s = new Double( size );
			
			DTODocumentoAnexoHelper anexo = new DTODocumentoAnexoHelper();
			anexo.setNombreOriginal( archivo.getFile().getFileName() );
			anexo.setNombreTemporal(nombreTemporal);
			anexo.setTipoExtencion(extencion);
			anexo.setSize( s / (1024 * 1024) );
			
			filtros.getAnexos().add(anexo);
		}
		else{
			
			filtros.setNombreTemporalDocumento(nombreTemporal);
			filtros.setNombreOriginalPdf(archivo.getFile().getFileName());
			
			filtros.setNombreOriginalPdfAux( archivo.getFile().getFileName() );
		}
	}
	
	@Override
	public boolean evaluarAdjuntoRepetidos( List<DTODocumentoAnexoHelper> anexos, String nombreAdjunto )	{
		
		boolean estaRepetido = false;
		
		if( anexos != null && anexos.size() > 0 ){
			
			for (DTODocumentoAnexoHelper anexo : anexos) {
				
				if( anexo.getNombreOriginal().equalsIgnoreCase( nombreAdjunto ) )
					estaRepetido = true;
			}
		}
		
		return estaRepetido;
	}

	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validaMetadatosArchivo(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override	
	public boolean validaMetadatosArchivoAnexo(FileUploadEvent archivo, String tipoDoc) throws Exception{
	
		Boolean esValido = false;
		
		try {	
			
			UploadedFile archivoP = archivo.getFile();
			
			//validacion nueva para el tipo de archivo
			TikaConfig config = TikaConfig.getDefaultConfig();
			Detector detector = config.getDetector();

			TikaInputStream stream = TikaInputStream.get(archivoP.getInputstream());
			Metadata metadata = new Metadata();
			metadata.add(Metadata.RESOURCE_NAME_KEY, archivoP.getFileName());
			MediaType mime = detector.detect(stream, metadata);
			
			if( tipoDoc.equalsIgnoreCase("anexo") ){

				//TODO validar los Mime type para : .pdf, *.xls, *.doc, *.ppt, *.xlsx, *.docx, *.pptx, *.txt y *.zip
				esValido = (
								(
									mime.toString().toLowerCase().contains("application/pdf")
									|| mime.toString().toLowerCase().contains("application/excel")
									|| mime.toString().toLowerCase().contains("application/vnd.ms-excel")
									|| mime.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
									|| mime.toString().toLowerCase().contains("application/msword")
									|| mime.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
									|| mime.toString().toLowerCase().contains("application/vnd.ms-powerpoint")
									|| mime.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.presentationml.presentation")
									|| mime.toString().toLowerCase().contains("text/plain")
									|| mime.toString().toLowerCase().contains("application/zip")
								) 
								&& 
								(	
									archivoP.getFileName().toLowerCase().contains(".pdf") 
									|| archivoP.getFileName().toLowerCase().contains(".xls")
									|| archivoP.getFileName().toLowerCase().contains(".doc") 
									|| archivoP.getFileName().toLowerCase().contains(".ppt")
									|| archivoP.getFileName().toLowerCase().contains(".xlsx")
									|| archivoP.getFileName().toLowerCase().contains(".docx")
									|| archivoP.getFileName().toLowerCase().contains(".pptx")
									|| archivoP.getFileName().toLowerCase().contains(".txt")
									|| archivoP.getFileName().toLowerCase().contains(".zip")
								)
						   ) ? true : false;	
			
			}
			else if( tipoDoc.equalsIgnoreCase("documento") ){

				//TODO validar los Mime type para : .pdf
				esValido = (
								(
									mime.toString().toLowerCase().contains("application/pdf")
								) 
								&& 
								(	
									archivoP.getFileName().toLowerCase().contains(".pdf") 
								)
						   ) ? true : false;
				
			}
			
			
			// cerramos el hilo del TikaInputStream, para liberar el archivo y poder hacer operaciones sobre el mas adelante
			stream.close();
			archivoP = null;
			metadata = null;
			
			return esValido;
			
		}catch(Exception e ) {
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface#validaCapturaDocumento(mx.ine.gestion.dto.helper.DTOFiltrosCapturaDocumentoHelper)
	 */
	@Override
	public String validaCapturaDocumento(DTOFiltrosCapturaDocumentoHelper filtros) {

		String error = "";

		//1.- Validamos que haya escogido remitentes
		if (filtros.getRemitentes() == null || filtros.getRemitentes().isEmpty()) {
			return error = "Debes seleccionar cuando menos un Remitente";
		}

		//2.- Validamos que haya escogido destinatarios
		if ( ( filtros.getDestinatarios() == null || filtros.getDestinatarios().isEmpty() ) && ( filtros.getDestinatariosComoArea() == null || filtros.getDestinatariosComoArea().isEmpty() ) ) {
			return error = "Debes seleccionar cuando menos un Destinatario";
		}

		//3.- Validamos que haya generado un documento
		if (filtros.getTipoCapturaDocumento() == null || filtros.getTipoCapturaDocumento() <= 0) {
			return error = "Debes generar el documento, para esto ve a la sección de 'Generar documento' y elige una de las opciones disponibles";
		}

		//4.- Validamos que se haya escogido una plantilla
		if(filtros.getTipoCapturaDocumento().equals(2) && filtros.getIdPlantilla() == null) {
			return error = "Debes seleccionar una plantilla. Ve a la sección de 'Generar documento' y elige una de las plantillas disponibles";
		}
		return error;
	}

}
