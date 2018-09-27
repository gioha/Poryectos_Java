/**
 * @(#)VHOficialiaPartes.java 15/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.helper.DTOAreasOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOCapturaOficialiaHelper;
import mx.ine.gestion.dto.helper.DTODocumentoAnexoHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHOficialiaPartesInterface;

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
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene la implementación de los métodos que hacen 
 * el procesamiento de los datos a nivel MB para el administrador del sistema.
 * 
 * @author David Rodríguez Corral
 * @since 07/10/2017
 * @update José Miguel Ortiz
 * @since 09/04/2018
 */
@Component("vhOficialiaPartes")
@Scope("prototype")
public class VHOficialiaPartes implements VHOficialiaPartesInterface {
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#obtenerRutaGlusterPdfNube(mx.ine.gestion.dto.db.DTODocumentoEntity)
	 */
	@Override
	public String obtenerRutaGlusterPdf() {
		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaPdf")+ File.separator;
		return rutaGluster;
	}

	@Override
	public String obtenerRutaGlusterAnexo() {
		String rutaGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema")
				+ File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaAnexos")+ File.separator ;
		return rutaGluster;
	}

	@Override
	public String validarConsultaRemitentes(DTOAreasOficialiaHelper dtoCombosRemitentes) {
		String error = "";
		
		if ((dtoCombosRemitentes.getNombre() == null || dtoCombosRemitentes.getNombre().isEmpty()) && (dtoCombosRemitentes.getApellidos() == null || dtoCombosRemitentes.getApellidos().isEmpty()) 
		&& (dtoCombosRemitentes.getAreaSeleccionada().equals("0") )) {
	
			error = Utilidades.mensajeProperties("mensaje_error_campo_personas");
		}
	
		return error;
	}

	@Override
	public String validarAgregarRemitente(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp) {
		String error = "";
		
		
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (dtoCombosRemitentes.getPersonasSeleccionadas().isEmpty()) {

			return error = "Debes de seleccionar cuando menos un persona";
		}
	
		//1.- Validamos si ya se habían agregado personas
		if (!dtoCombosRemitentes.getPersonas().isEmpty()) {
			
			//1.1.- Obtenemos una persona base para obtener su área y comparar con los que se buscaron
			DTOEstructuraAreasEntity personaParaComparar = dtoCombosRemitentes.getPersonas().get(0);
			
			//1.2.- Validamos con su área que no sea diferente
			for (DTOEstructuraAreasEntity persona : dtoCombosRemitentes.getPersonasSeleccionadas()) {
				
				if (!persona.getArea().equals(personaParaComparar.getArea())) {
					
					return error = "Los remitentes deben de ser de una sola área, verifica la información";
				}
			}
			
		} 

		//2.- Validamos para cuando aun no se habían seleccionado personas que todas sean de la misma área
		else {
			
			//2.1. -Obtenemos una persona base para obtener su área y comparar con los que se buscaron
			DTOEstructuraAreasEntity personaParaComparar = dtoCombosRemitentes.getPersonasSeleccionadas().get(0);
			
			//2.2.- Validamos con su área que no sea diferente a las demás
			for (DTOEstructuraAreasEntity persona : dtoCombosRemitentes.getPersonasSeleccionadas()) {
			
				if (!persona.getArea().equals(personaParaComparar.getArea())) {
					
					return error = "Los remitentes deben de ser de una sola área, verifica la información";
				}
			}
		}

		//3.- Validamos que no se seleccionen pesonas que ya esten de remitentes
		for (DTOEstructuraAreasEntity remitente : dtoCombosRemitentes.getPersonas()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosRemitentes.getPersonasSeleccionadas()) {

				if (remitente.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}

		}
		
		//4.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosRemitentes.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity destinatario : dtoCombosDestinatarios.getPersonas()) {

				if (personaSeleccionada.equals(destinatario)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}

		}
		
		//5.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosRemitentes.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity ccp : dtoCombosCcp.getPersonas()) {

				if (personaSeleccionada.equals(ccp)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como ccp, valida la información";
				}
			}

		}


		return error;
	}

	@Override
	public void agregarRemitentes(DTOAreasOficialiaHelper dtoCombosRemitentes) {
		//1.- Agregamos las personas seleccionadas como remitentes
				for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosRemitentes.getPersonasSeleccionadas()) {
					
					dtoCombosRemitentes.getPersonas().add(personaSeleccionada);
				}

				//2.- Limpiamos los filtros de búsqueda
				dtoCombosRemitentes.setPersonasSeleccionadas(null);
				dtoCombosRemitentes.setPersonasBusquedasEncontradas(null);
				dtoCombosRemitentes.setNombre(null);
				dtoCombosRemitentes.setApellidos(null);
				dtoCombosRemitentes.setAreaSeleccionada(null);
				dtoCombosRemitentes.setEntidadSeleccionada(null);
				dtoCombosRemitentes.setTipoAreaSeleccionada(null);
				dtoCombosRemitentes.setListaAreas(null);
				dtoCombosRemitentes.setListaEstados(null);
	}

	@Override
	public String validarAgregarDestinatario(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp) {
		
		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (dtoCombosDestinatarios.getPersonasSeleccionadas().isEmpty()) {

			return error = "Debes de seleccionar cuando menos un persona";
		}
	
		//2.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity destinatario : dtoCombosDestinatarios.getPersonas()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosDestinatarios.getPersonasSeleccionadas()) {

				if (destinatario.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}
		}
		
		//3.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosDestinatarios.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity remitente : dtoCombosRemitentes.getPersonas()) {

				if (personaSeleccionada.equals(remitente)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}

		}
		
		//4.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosDestinatarios.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity ccp : dtoCombosCcp.getPersonas()) {

				if (personaSeleccionada.equals(ccp)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como ccp, valida la información";
				}
			}

		}

		return error;
	}

	@Override
	public void agregarDestinatarios(DTOAreasOficialiaHelper dtoCombosDestinatarios) {
		//1.- Agregamos las personas seleccionadas como remitentes
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosDestinatarios.getPersonasSeleccionadas()) {
			
			dtoCombosDestinatarios.getPersonas().add(personaSeleccionada);
		}

		//2.- Limpiamos los filtros de búsqueda
		dtoCombosDestinatarios.setPersonasSeleccionadas(null);
		dtoCombosDestinatarios.setPersonasBusquedasEncontradas(null);
		dtoCombosDestinatarios.setNombre(null);
		dtoCombosDestinatarios.setApellidos(null);
		dtoCombosDestinatarios.setAreaSeleccionada("0");
		dtoCombosDestinatarios.setEntidadSeleccionada("0");
		dtoCombosDestinatarios.setTipoAreaSeleccionada("0");
		dtoCombosDestinatarios.setListaAreas(null);
		dtoCombosDestinatarios.setListaEstados(null);
	}

	@Override
	public String validarAgregarCCP(DTOAreasOficialiaHelper dtoCombosRemitentes, DTOAreasOficialiaHelper dtoCombosDestinatarios, DTOAreasOficialiaHelper dtoCombosCcp) {
		
		String error = "";
		
		//1.- Validamos que la lista de seleccionados no venga vacía
		if (dtoCombosCcp.getPersonasSeleccionadas().isEmpty()) {

			return error = "Debes de seleccionar cuando menos un persona";
		}
	
		//2.- Validamos que no se seleccionen pesonas que ya esten de destionatarios
		for (DTOEstructuraAreasEntity ccp : dtoCombosCcp.getPersonas()) {
			
			for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosCcp.getPersonasSeleccionadas()) {

				if (ccp.equals(personaSeleccionada)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a para ccp, valida la información";
				}
			}
		}
		
		//3.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosCcp.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity remitente : dtoCombosRemitentes.getPersonas()) {
	
				if (personaSeleccionada.equals(remitente)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como remitente, valida la información";
				}
			}
	
		}
		
		//4.- Validamos que no se seleccionen pesonas que ya esten de destinatarios
		for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosCcp.getPersonasSeleccionadas()) {
		
			for (DTOEstructuraAreasEntity destinatario : dtoCombosDestinatarios.getPersonas()) {
	
				if (personaSeleccionada.equals(destinatario)) {
					
					return error = personaSeleccionada.getNombreCompleto() + " ya fue seleccionado/a como destinatario, valida la información";
				}
			}
	
		}

		return error;
	}

	@Override
	public void agregarCCP(DTOAreasOficialiaHelper dtoCombosCcp) {
		//1.- Agregamos las personas seleccionadas como remitentes
				for (DTOEstructuraAreasEntity personaSeleccionada : dtoCombosCcp.getPersonasSeleccionadas()) {
					
					dtoCombosCcp.getPersonas().add(personaSeleccionada);
				}

				//2.- Limpiamos los filtros de búsqueda
				dtoCombosCcp.setPersonasSeleccionadas(null);
				dtoCombosCcp.setPersonasBusquedasEncontradas(null);
				dtoCombosCcp.setNombre(null);
				dtoCombosCcp.setApellidos(null);
				dtoCombosCcp.setAreaSeleccionada("0");
				dtoCombosCcp.setEntidadSeleccionada("0");
				dtoCombosCcp.setTipoAreaSeleccionada("0");
				dtoCombosCcp.setListaAreas(null);
				dtoCombosCcp.setListaEstados(null);
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#validarMetadatosArchivo(FileUploadEvent archivo, String tipoAdjunto)
	 */
	@Override	
	public boolean validarMetadatosArchivo(FileUploadEvent archivo, String tipoAdjunto) throws Exception {
		boolean esArchivoValido = false;

		try {
			UploadedFile archivoAux = archivo.getFile();
			TikaConfig tikaConfig = TikaConfig.getDefaultConfig();
			Detector detector = tikaConfig.getDetector();
			TikaInputStream tikaInputStream = TikaInputStream.get(archivoAux.getInputstream());
			Metadata metadata = new Metadata();
			metadata.add(Metadata.RESOURCE_NAME_KEY, archivoAux.getFileName());
			MediaType mediaType = detector.detect(tikaInputStream, metadata);

			if(tipoAdjunto.equalsIgnoreCase("Anexo")) {
				// 1. Archivos válidos: *.pdf, *.xls, *.doc, *.ppt, *.xlsx, *.docx, *.pptx, *.txt y *.zip
				esArchivoValido = (( mediaType.toString().toLowerCase().contains("application/pdf")
								  || mediaType.toString().toLowerCase().contains("application/excel")
								  || mediaType.toString().toLowerCase().contains("application/vnd.ms-excel")
								  || mediaType.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
								  || mediaType.toString().toLowerCase().contains("application/msword")
								  || mediaType.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
								  || mediaType.toString().toLowerCase().contains("application/vnd.ms-powerpoint")
								  || mediaType.toString().toLowerCase().contains("application/vnd.openxmlformats-officedocument.presentationml.presentation")
								  || mediaType.toString().toLowerCase().contains("text/plain")
								  || mediaType.toString().toLowerCase().contains("application/zip"))
								  &&
								  (  archivoAux.getFileName().toLowerCase().contains(".pdf")
								  || archivoAux.getFileName().toLowerCase().contains(".xls")
								  || archivoAux.getFileName().toLowerCase().contains(".doc")
								  || archivoAux.getFileName().toLowerCase().contains(".ppt")
								  || archivoAux.getFileName().toLowerCase().contains(".xlsx")
								  || archivoAux.getFileName().toLowerCase().contains(".docx")
								  || archivoAux.getFileName().toLowerCase().contains(".pptx")
								  || archivoAux.getFileName().toLowerCase().contains(".txt")
								  || archivoAux.getFileName().toLowerCase().contains(".zip")
								  )) ? true : false;
			}
			else if(tipoAdjunto.equalsIgnoreCase("Documento")) {
				// 2. Archivo válido: *.pdf
				esArchivoValido = ((mediaType.toString().toLowerCase().contains("application/pdf")) &&
								   (archivoAux.getFileName().toLowerCase().contains(".pdf"))) ? true : false;
			}

			tikaInputStream.close();
			archivoAux = null;
			metadata = null;

			return esArchivoValido;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#generarTemporalArchivoAdjunto(DTOCapturaOficialiaHelper dtoCaptura, FileUploadEvent archivo, boolean esAnexo)
	 */
	@Override
	public void generarTemporalArchivoAdjunto(DTOCapturaOficialiaHelper dtoCapturaOf, FileUploadEvent archivo, boolean esAnexo, boolean eliminarTemp) throws Exception {
		//1. Se obtiene el nombre temporal de PrimeFaces
		MultipartRequest httpServletRequest = (MultipartRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String idClienteRegistroTabla = archivo.getComponent().getClientId();
		DiskFileItem fileItem = (DiskFileItem) httpServletRequest.getFileItem(idClienteRegistroTabla);
		File temporal = fileItem.getStoreLocation();
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] temporalArreglo = temporal.getAbsolutePath().split(pattern);
		String nombreTemporalPrime = temporalArreglo[temporalArreglo.length - 1];
		double tamanioArchivo = archivo.getFile().getSize();

		//2. Debe moverse de carpeta para evitar que el archivo desaparezca
		String rutaTemporalPrimeFaces = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") +
										File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmpPrimefaces") +
										File.separator + nombreTemporalPrime;
	
		String rutaTemporalGluster = Utilidades.getRutaGlusterFS() + Utilidades.mensajeProperties("constante_generica_nombreCarpetaSistema") +
									 File.separator + Utilidades.mensajeProperties("constante_generica_nombreCarpetaTmp") + File.separator +
									 SecurityContextHolder.getContext().getAuthentication().getName();
		
		File archivoTemporalPrimeFaces = new File(rutaTemporalPrimeFaces);
		File archivoTemporalGluster = new File(rutaTemporalGluster);

		if (!archivoTemporalGluster.exists()) {
			archivoTemporalGluster.mkdirs();
		} else {
			 File[] archivosTemporales = archivoTemporalGluster.listFiles();
			 if(archivosTemporales != null && eliminarTemp) {
				 for(File archivosBasura: archivosTemporales) {
					 archivosBasura.delete();
				 }
			 } 
		}

		FileUtils.moveToDirectory(archivoTemporalPrimeFaces, archivoTemporalGluster, false);	// Podría ser true para forzar la creación del directorio

		//3. El archivo original es renombrado
		Calendar calendario = Calendar.getInstance();
		String nombreTemporal = "";
		String extension = "";

		if(esAnexo) {
			String temp = "";

			for(char c : new StringBuffer(archivo.getFile().getFileName()).reverse().toString().toCharArray()) {
				if(c == '.') {
					break;
				} else {
					temp = temp + c;
				}
			}
			
			extension = new StringBuffer(temp).reverse().toString();
			nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" +
							 String.valueOf(calendario.get(Calendar.MILLISECOND)) + "." + extension;
		} else {
			nombreTemporal = String.valueOf(calendario.get(Calendar.MINUTE)) + "_" + String.valueOf(calendario.get(Calendar.SECOND)) + "_" +
							 String.valueOf(calendario.get(Calendar.MILLISECOND)) + ".pdf";
		}

		File archivoArenombrar = new File(rutaTemporalGluster + File.separator + nombreTemporalPrime);
		File archivoRenombrado = new File(rutaTemporalGluster + File.separator + nombreTemporal);
		archivoArenombrar.renameTo(archivoRenombrado);

		// 4. Se construye el DTODocumentoAnexoHelper necesario para guardar en BD
		if(esAnexo) {
			DTODocumentoAnexoHelper anexo = new DTODocumentoAnexoHelper();

			anexo.setNombreOriginal(archivo.getFile().getFileName());
			anexo.setNombreTemporal(nombreTemporal);
			anexo.setTipoExtencion(extension);
			anexo.setSize(tamanioArchivo);

			dtoCapturaOf.getDocsAnexos().add(anexo);
		} else {
			dtoCapturaOf.setNombreTemporalDoc(nombreTemporal);
			dtoCapturaOf.setNombreDocumento(archivo.getFile().getFileName());
			dtoCapturaOf.setTamanioDocumento(tamanioArchivo);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#validarFiltrosCaptura(DTOCapturaOficialiaHelper dtoCapturaOf, DTODocumentoEntity dtoDocumentoOf, String areaDoc,
																					 Boolean conNumeroDoc, List<DTOEstructuraAreasEntity> listaRemitentesDoc,
																					 List<DTOEstructuraAreasEntity> listaDestinatariosDoc, List<DTOEstructuraAreasEntity> listaCcpDoc)
	 */
	@Override
	public String validarFiltrosCaptura(DTOCapturaOficialiaHelper dtoCapturaOf, DTODocumentoEntity dtoDocumentoOf, String areaDoc,
										Boolean conNumeroDoc, List<DTOEstructuraAreasEntity> listaRemitentesDoc,
										List<DTOEstructuraAreasEntity> listaDestinatariosDoc, List<DTOEstructuraAreasEntity> listaCcpDoc) {
		if(areaDoc.equals("SinArea")) {
			return "Debes seleccionar un Área para generar un folio";
		}

		if(dtoCapturaOf.getNombreDocumento() == null) {
			return "Debes adjuntar un Documento de Oficialía";
		}

		if(dtoDocumentoOf.getAsunto().isEmpty()) {
			return "Debes introducir información en el campo Asunto";
		}

		if(dtoDocumentoOf.getIdTipoDocumento().equals(0)) {
			return "Debes seleccionar un Tipo de documento";
		}

		if(!conNumeroDoc && dtoDocumentoOf.getNoDocumento().isEmpty()) {
			return "Debes introducir un Número de documento";
		}

		if(dtoDocumentoOf.getFechaCreacion() == null) {
			return "Debes introducir una Fecha de creación del documento";
		}

		if(listaRemitentesDoc.size() == 0 && dtoCapturaOf.getRemitentesExtAgregados().size() == 0) {
			return "Debes seleccionar al menos un Remitente";
		}

		if(listaDestinatariosDoc.size() == 0) {
			return "Debes seleccionar al menos un Destinatario";
		}

		if(dtoCapturaOf.getFechaRecepcion() == null) {
			return "Debes introducir una Fecha de recepción del documento";
		}

		if(dtoCapturaOf.getSeccionSeleccionada().equals(0)) {
			return "Debes seleccionar una Sección para clasificar el documento";
		}

		if(dtoCapturaOf.getCategoriaSeleccionada().equals(0)) {
			return "Debes seleccionar una Categoría para clasificar el documento";
		}

		if(dtoCapturaOf.getFechaRecepcion().before(dtoDocumentoOf.getFechaCreacion())) {
			return "Debes seleccionar una fecha de recepción posterior a la fecha del documento";
		}

		return "";
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#validarConsultaRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf)
	 */
	@Override
	public String validarConsultaRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf) {
		if(dtoCapturaOf.getNombreRemitenteExt().isEmpty() && dtoCapturaOf.getDependenciaRemitenteExt().isEmpty()) {
			return "Debes introducir al menos un filtro de búsqueda";
		}

		return "";
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#validarAgregarRemitenteExt(DTOCapturaOficialiaHelper dtoCapturaOf)
	 */
	@Override
	public String validarAgregarRemitenteExt(DTOCapturaOficialiaHelper dtoCaptura) {
		// 1. Se valida que se haya seleccionado un o varios remitentes externos
		if(dtoCaptura.getRemitentesExtSeleccionados().isEmpty()) {
			return "Debes seleccionar al menos un remitente externo";
		} else {	// 2. Se valida que no se seleccione a un remitente externo previamente agregado
			for(DTORemitentesExternosOfEntity remitenteAgregado : dtoCaptura.getRemitentesExtAgregados()) {
				for(DTORemitentesExternosOfEntity remitenteSeleccionado : dtoCaptura.getRemitentesExtSeleccionados()) {
					if(remitenteAgregado.equals(remitenteSeleccionado)) {
						return remitenteSeleccionado.getNombreRemitente() + " ya fue seleccionado/a como un remitente externo, verifica la información";
					}
				}

			}
		}

		return "";
	}

	/**
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHOficialiaPartesInterface#agregarRemitentesExt(DTOCapturaOficialiaHelper dtoCapturaOf)
	 */
	@Override
	public void agregarRemitentesExt(DTOCapturaOficialiaHelper dtoCaptura) {
		// 1. Se agrega cada uno de los remitentes externos a la lista de remitentes agregados
		for(DTORemitentesExternosOfEntity remitenteSeleccionado : dtoCaptura.getRemitentesExtSeleccionados()) {
			dtoCaptura.getRemitentesExtAgregados().add(remitenteSeleccionado);
		}

		// 2. Se realiza la limpieza de los campos utilizados en la búsqueda
		dtoCaptura.setNombreRemitenteExt("");
		dtoCaptura.setDependenciaRemitenteExt("");
		dtoCaptura.setRemitentesExtEncontrados(new ArrayList<DTORemitentesExternosOfEntity>());
		dtoCaptura.setRemitentesExtSeleccionados(new ArrayList<DTORemitentesExternosOfEntity>());
	}

}
