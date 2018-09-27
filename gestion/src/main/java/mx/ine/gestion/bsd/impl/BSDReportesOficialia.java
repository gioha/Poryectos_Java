/**
 * @(#)BSDReportesOficialia.java 13/03/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.as.inter.ASReportesOficialiaInterface;
import mx.ine.gestion.bsd.inter.BSDReportesOficialiaInterface;
import mx.ine.gestion.dto.db.DTOApartadosNumDocOfEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;
import mx.ine.gestion.dto.helper.DTOReportesOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOMesesAnioHelper;
import mx.ine.gestion.dto.helper.DTOReportesOficialia;
import mx.ine.gestion.util.Utilidades;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de implementar los métodos declarados en la clase BSDReportesOficialiaInterface.
 * @author José Miguel Ortiz
 * @since 17/03/2018
 */
@Component("bsdReportesOficialia")
@Scope("prototype")
public class BSDReportesOficialia implements BSDReportesOficialiaInterface {
	/**
	 * Objeto para el servicio de mensajes log de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(BSDReportesOficialia.class);

	@Autowired
	@Qualifier("asReportesOficialia")
	private transient ASReportesOficialiaInterface asReportesOf;

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#determinarCatalogosDeFolios(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public void determinarCatalogosDeFolios(DTOReportesOficialiaHelper filtrosReportesOf) {
		List<DTOApartadosNumDocOfEntity> foliosDocOf;
		List<String> catalogoAcronimosFolios = new ArrayList<String>();
		List<Integer> catalogoAniosFolios = new ArrayList<Integer>();

		// 1. Recupera todos una lista de folios disponibles en la BD
		foliosDocOf = asReportesOf.recuperarFoliosDocOf();

		// 2. Itera sobre el resultado, aquellos acrónimos que ya hayan sido recopilados ya no son agregados a la lista
		for(DTOApartadosNumDocOfEntity folioDocof : foliosDocOf) {
			if(!catalogoAcronimosFolios.contains(folioDocof.getFolioOficialia().substring(0, folioDocof.getFolioOficialia().length()-11))) {
				catalogoAcronimosFolios.add(folioDocof.getFolioOficialia().substring(0, folioDocof.getFolioOficialia().length()-11));
			}
			if(!catalogoAniosFolios.contains(folioDocof.getAnio())) {
				catalogoAniosFolios.add(folioDocof.getAnio());
			}
		}

		// 3. Guarda una lista con dichos folios
		filtrosReportesOf.setCatalogoAcronimosFolios(catalogoAcronimosFolios);
		filtrosReportesOf.setCatalogoAniosFolios(catalogoAniosFolios);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarTiposDocumento()
	 */
	@Override
	public List<DTOCTipoDocumentoEntity> recuperarTiposDocumento() {
		return asReportesOf.recuperarTiposDocumento();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarSecciones()
	 */
	@Override
	public List<DTOCSeccionesEntity> recuperarSecciones() {
		return asReportesOf.recuperarSecciones();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarCategorias(Integer idSeccion)
	 */
	@Override
	public List<DTOCCategoriasEntity> recuperarCategorias(Integer idSeccion) {
		return asReportesOf.recuperarCategorias(idSeccion);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#determinarMesesAnio()
	 */
	@Override
	public List<DTOMesesAnioHelper> determinarMesesAnio() {
		List<DTOMesesAnioHelper> catalogoMeses = new ArrayList<DTOMesesAnioHelper>();

		catalogoMeses.add(new DTOMesesAnioHelper(1, "Enero"));
		catalogoMeses.add(new DTOMesesAnioHelper(2, "Febrero"));
		catalogoMeses.add(new DTOMesesAnioHelper(3, "Marzo"));
		catalogoMeses.add(new DTOMesesAnioHelper(4, "Abril"));
		catalogoMeses.add(new DTOMesesAnioHelper(5, "Mayo"));
		catalogoMeses.add(new DTOMesesAnioHelper(6, "Junio"));
		catalogoMeses.add(new DTOMesesAnioHelper(7, "Julio"));
		catalogoMeses.add(new DTOMesesAnioHelper(8, "Agosto"));
		catalogoMeses.add(new DTOMesesAnioHelper(9, "Septiembre"));
		catalogoMeses.add(new DTOMesesAnioHelper(10, "Octubre"));
		catalogoMeses.add(new DTOMesesAnioHelper(11, "Noviembre"));
		catalogoMeses.add(new DTOMesesAnioHelper(12, "Diciembre"));

		return catalogoMeses;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#determinarParametrosOrden()
	 */
	@Override
	public List<String> determinarParametrosOrden() {
		List<String> parametrosOrden = Arrays.asList("Tipo de documento", "Folio de oficialía", "Tipo de área", "Número de documento",
				   									 "Asunto", "Copia de conocimiento", "Sección", "Fecha de registro", "Categoría");
		Collections.sort(parametrosOrden);
		return parametrosOrden;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarTiposDeArea()
	 */
	@Override
	public List<DTOCTipoAreaEntity> recuperarTiposDeArea() {
		return asReportesOf.recuperarTiposDeArea();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#determinarFiltrosReporte()
	 */
	@Override
	public List<String> determinarFiltrosReporte() {
		return Arrays.asList("Todos", "Tipo de área", /*"Folios", */"Área", "Remitente", "Destinatario", "Copia de conocimiento", "Sección",
							 "Categoría");
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarEntidades()
	 */
	@Override
	public List<DTOEstadosEntity> recuperarEntidades() {
		return asReportesOf.recuperarEntidades();
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarAreas(Integer idTipoArea, Integer idEstado)
	 */
	@Override
	public List<DTOCAreaEntity> recuperarAreas(Integer idTipoArea, Integer idEstado) {
		return asReportesOf.recuperarAreas(idTipoArea, idEstado);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarAreas(String rolUsuario, Integer idOficialia)
	 */
	@Override
	public List<DTOCAreaEntity> recuperarAreas(String rolUsuario, Integer idOficialia) {
		return asReportesOf.recuperarAreas(rolUsuario, idOficialia);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#validarFiltrosBusqueda(String tipoBusqueda, DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public String validarFiltrosBusqueda(String tipoBusqueda, DTOReportesOficialiaHelper filtrosReportesOf) {
		String mensaje = "";

		// 1. La búsqueda realizada viene desde la sección de Remitente (s)
		if(tipoBusqueda.equals("Remitente")) {
			if(filtrosReportesOf.getNombreRemitente() == "" &&
			   filtrosReportesOf.getApellidoRemitente() == "" &&
			   filtrosReportesOf.getIdAreaRemitente().equals(0)) {
				mensaje = Utilidades.mensajeProperties("mensaje_error_campo_personas");
			}
		}
		// 2. La búsqueda realizada viene desde la sección de Destinatario (s)
		if(tipoBusqueda.equals("Destinatario")) {
			if(filtrosReportesOf.getNombreDestinatario() == "" &&
			   filtrosReportesOf.getApellidoDestinatario() == "" &&
			   filtrosReportesOf.getIdAreaDestinatario().equals(0)) {
				mensaje = Utilidades.mensajeProperties("mensaje_error_campo_personas");
			}
		}

		return mensaje;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#validarFiltrosRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public String validarFiltrosRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf) {
		if(filtrosReportesOf.getNombreRemitenteExt().isEmpty() && filtrosReportesOf.getDependenciaRemitenteExt().isEmpty()) {
			return "Debes introducir al menos un filtro de búsqueda";
		}

		return "";
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarPersonas(String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente)
	 */
	@Override
	public List<DTOEstructuraAreasEntity> recuperarPersonas(String nombreRemitente, String apellidoRemitente, Integer idTipoAreaRemitente, Integer idAreaRemitente) {
		return asReportesOf.recuperarPersonas(nombreRemitente, apellidoRemitente, idTipoAreaRemitente, idAreaRemitente);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public List<DTORemitentesExternosOfEntity> recuperarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf) {
		return asReportesOf.recuperarRemitentesExt( filtrosReportesOf.getNombreRemitenteExt(),
													filtrosReportesOf.getDependenciaRemitenteExt() );
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#validarPersonasSeleccionadas(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public String validarPersonasSeleccionadas(DTOReportesOficialiaHelper filtrosReportesOf) {
		String mensaje = "", tipoPersona = "";
		DTOEstructuraAreasEntity personaComparable;
		List<DTOEstructuraAreasEntity> listaPorAgregar = new ArrayList<DTOEstructuraAreasEntity>();
		List<DTOEstructuraAreasEntity> listaPorComprobar = new ArrayList<DTOEstructuraAreasEntity>();

		// 1. El usuario agrega una persona desde la sección de Remitente (s)
		if(filtrosReportesOf.getTipoBusqueda().equals("Remitente")) {
			// La lista a la que se desea agregar la persona pertenece a la lista de remitentes
			listaPorAgregar = filtrosReportesOf.getRemitentes();
			// La lista opuesta con la que se debe validar que una persona remitente no sea una persona destinatario
			listaPorComprobar = filtrosReportesOf.getDestinatarios();
			tipoPersona = "destinatario";
		}
		// 2. El usuario agrega una persona desde la sección de Destinatario (s)
		if(filtrosReportesOf.getTipoBusqueda().equals("Destinatario")) {
			listaPorAgregar = filtrosReportesOf.getDestinatarios();
			listaPorComprobar = filtrosReportesOf.getRemitentes();
			tipoPersona = "remitente";
		}

		// 1. No se han seleccionado personas
		if(filtrosReportesOf.getPersonasSeleccionadas().size() == 0) {
			return mensaje = "Debes seleccionar cuando menos una persona";
		}

		// 2. Se ha seleccionado una persona con un área diferente a las personas ya agregadas con anterioridad
		if(listaPorAgregar.size() > 0) {
			personaComparable = listaPorAgregar.get(0);
			for(DTOEstructuraAreasEntity persona : filtrosReportesOf.getPersonasSeleccionadas()) {
				if(!persona.getArea().equals(personaComparable.getArea())) {
					return mensaje = "Los " + filtrosReportesOf.getTipoBusqueda().toLowerCase() + "s deben de ser de una sola área, verifica la información";
				}
			}
			
		} else {	// 3. Se ha seleccionado un listado de personas con áreas diferentes
			personaComparable = filtrosReportesOf.getPersonasSeleccionadas().get(0);
			for(DTOEstructuraAreasEntity persona : filtrosReportesOf.getPersonasSeleccionadas()) {
				if(!persona.getArea().equals(personaComparable.getArea())) {
					return mensaje = "Los " + filtrosReportesOf.getTipoBusqueda().toLowerCase() + "s deben de ser de una sola área, verifica la información";
				}
			}
		}

		// 4. Se han seleccionado una o varias personas que ya se encontraban en la lista de personas seleccionadas
		for(DTOEstructuraAreasEntity personaAgregada : listaPorAgregar) {
			for(DTOEstructuraAreasEntity personaPorAgregar : filtrosReportesOf.getPersonasSeleccionadas()) {
				if(personaAgregada.equals(personaPorAgregar)) {
					return mensaje = personaPorAgregar.getNombreCompleto() + " ya fue seleccionado/a como " + filtrosReportesOf.getTipoBusqueda().toLowerCase() + ", valida la información";
				}
			}
		}

		// 5. Se han seleccionado una o varias personas que ya se encontraban en la lista opuesta, es decir,
		// un remitente ya ha sido seleccionado como destinatario y viceversa
		for(DTOEstructuraAreasEntity persona : listaPorComprobar) {
			for(DTOEstructuraAreasEntity personaPorAgregar : filtrosReportesOf.getPersonasSeleccionadas()) {
				if(persona.equals(personaPorAgregar)) {
					return mensaje = personaPorAgregar.getNombreCompleto() + " ya fue seleccionado/a como " + tipoPersona + ", valida la información";
				}
			}
		}

		return mensaje;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#validarRemitenteExtSeleccionado(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public String validarRemitenteExtSeleccionado(DTOReportesOficialiaHelper filtrosReportesOf) {
		// 1. Se valida que se haya seleccionado uno o varios remitentes externos
		if(filtrosReportesOf.getRemitentesExtSeleccionados().isEmpty()) {
			return "Debes seleccionar al menos un remitente externo";
		} else {	// 2. Se valida que no se seleccione a un remitente externo previamente agregado
			for(DTORemitentesExternosOfEntity remitenteAgregado : filtrosReportesOf.getRemitentesExtAgregados()) {
				for(DTORemitentesExternosOfEntity remitenteSeleccionado : filtrosReportesOf.getRemitentesExtSeleccionados()) {
					if(remitenteAgregado.equals(remitenteSeleccionado)) {
						return remitenteSeleccionado.getNombreRemitente() + " ya fue seleccionado/a como un remitente externo, verifica la información";
					}
				}

			}
		}

		return "";
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#agregarPersonas(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public void agregarPersonas(DTOReportesOficialiaHelper filtrosReportesOf) {
		for(DTOEstructuraAreasEntity persona : filtrosReportesOf.getPersonasSeleccionadas()) {
			// Se agregan una o varias personas seleccionadas a la lista de remitentes o destinatarios según sea el caso
			if(filtrosReportesOf.getTipoBusqueda().equals("Remitente")) {
				filtrosReportesOf.getRemitentes().add(persona);
			}
			if(filtrosReportesOf.getTipoBusqueda().equals("Destinatario")) {
				filtrosReportesOf.getDestinatarios().add(persona);
			}
		}

		// Se limpian todos los campos de búsqueda de la sección de remitentes
		if(filtrosReportesOf.getTipoBusqueda().equals("Remitente")) {
			filtrosReportesOf.setNombreRemitente("");
			filtrosReportesOf.setApellidoRemitente("");
			filtrosReportesOf.setIdTipoAreaRemitente(new Integer(0));
			filtrosReportesOf.setIdEntidadRemitente(null);
			filtrosReportesOf.setIdAreaRemitente(new Integer(0));
			filtrosReportesOf.setAreasRemitente(new ArrayList<DTOCAreaEntity>());
			filtrosReportesOf.setEntidadesRemitente(new ArrayList<DTOEstadosEntity>());
		}

		// Se limpian todos los campos de búsqueda de la sección de destinatarios
		if(filtrosReportesOf.getTipoBusqueda().equals("Destinatario")) {
			filtrosReportesOf.setNombreDestinatario("");
			filtrosReportesOf.setApellidoDestinatario("");
			filtrosReportesOf.setIdTipoAreaDestinatario(new Integer(0));
			filtrosReportesOf.setIdEntidadDestinatario(null);
			filtrosReportesOf.setIdAreaDestinatario(new Integer(0));
//			filtrosReportesOf.setAreasDestinatario(new ArrayList<DTOCAreaEntity>());
			filtrosReportesOf.setEntidadesDestinatario(new ArrayList<DTOEstadosEntity>());
		}
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#agregarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public void agregarRemitentesExt(DTOReportesOficialiaHelper filtrosReportesOf) {
		// 1. Se agrega cada uno de los remitentes externos a la lista de remitentes agregados
		for(DTORemitentesExternosOfEntity remitenteSeleccionado : filtrosReportesOf.getRemitentesExtSeleccionados()) {
			filtrosReportesOf.getRemitentesExtAgregados().add(remitenteSeleccionado);
		}

		// 2. Se realiza la limpieza de los campos utilizados en la búsqueda
		filtrosReportesOf.setNombreRemitenteExt("");
		filtrosReportesOf.setDependenciaRemitenteExt("");
		filtrosReportesOf.setRemitentesExtEncontrados(new ArrayList<DTORemitentesExternosOfEntity>());
		filtrosReportesOf.setRemitentesExtSeleccionados(new ArrayList<DTORemitentesExternosOfEntity>());
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#generarFiltrosReporte(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public List<String> generarFiltrosReporte(DTOReportesOficialiaHelper filtrosReportesOf) {
		List<String> filtrosReporte = new ArrayList<String>();

		// 1. Se agregan los filtros o columnas por default
		for(int posFiltro = 0; posFiltro < filtrosReportesOf.getFiltrosPredefSel().length; posFiltro++) {
			switch(filtrosReportesOf.getFiltrosPredefSel()[posFiltro]) {
				case "Folio de oficialía":
					filtrosReporte.add("Folio de oficialía");
					break;
				case "Número de documento":
					filtrosReporte.add("Número de documento");
					break;
				case "Tipo de documento":
					filtrosReporte.add("Tipo de documento");
					break;
				case "Asunto":
					filtrosReporte.add("Asunto");
					break;
				case "Fecha de registro":
					filtrosReporte.add("Fecha de registro");
					break;
				default:
					break;
			}
		}

		// 2. Añade a la lista cada filtro seleccionado por parte del usuario 
		for(int posFiltro = 0; posFiltro < filtrosReportesOf.getFiltrosSeleccionados().length; posFiltro++) {
			switch(filtrosReportesOf.getFiltrosSeleccionados()[posFiltro]) {
				case "Copia de conocimiento":
					filtrosReporte.add("Copia de conocimiento");
					break;
				case "Sección":
					filtrosReporte.add("Sección");
					break;
				case "Categoría":
					filtrosReporte.add("Categoría");
					break;
				case "Área":
					filtrosReporte.add("Área");
					break;
				case "Tipo de área":
					filtrosReporte.add("Tipo de área");
					break;
				case "Remitente":
					filtrosReporte.add("Remitente");
					break;
				case "Destinatario":
					filtrosReporte.add("Destinatario");
					break;
				default:
					break;
			}
		}

		return filtrosReporte;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public List<DTOReportesOficialia> recuperarReporte(String rolUsuario, DTOReportesOficialiaHelper filtrosReportesOf) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setLenient(false);

		// 1. Valida el rango de folios de oficialía
		if(filtrosReportesOf.getConFiltroFolios()) {
			if(!filtrosReportesOf.getAcronimoFolio().isEmpty() || !filtrosReportesOf.getFolioA().equals(0) ||
			   !filtrosReportesOf.getFolioDe().equals(0) || !filtrosReportesOf.getAnioFolio().equals(0)) {
				if(filtrosReportesOf.getAcronimoFolio().isEmpty()) {	
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos incompletos.", "Rango de folios de oficialía"));
					return null;
				}
				if(filtrosReportesOf.getFolioA().equals(0)) {	
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos incompletos.", "Rango de folios de oficialía"));
					return null;
				}
				if(filtrosReportesOf.getFolioDe().equals(0)) {	
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos incompletos.", "Rango de folios de oficialía"));
					return null;
				}
				if(filtrosReportesOf.getAnioFolio().equals(0)) {	
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos incompletos.", "Rango de folios de oficialía"));
					return null;
				}
					
			}
		}

		if(!filtrosReportesOf.getAcronimoFolio().isEmpty() && !filtrosReportesOf.getFolioA().equals(0) &&
		   !filtrosReportesOf.getFolioDe().equals(0) && !filtrosReportesOf.getAnioFolio().equals(0)) {
			if(filtrosReportesOf.getFolioA() < filtrosReportesOf.getFolioDe()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes introducir un folio final posterior al folio inicial.", "Rango de folios de oficialía"));
				return null;	
			}
		}

//		if(filtrosReportesOf.getConFiltroFolios() && (!filtrosReportesOf.getAcronimoFolio().isEmpty() || !filtrosReportesOf.getFolioA().equals(0) ||
//													  !filtrosReportesOf.getFolioDe().equals(0) || !filtrosReportesOf.getAnioFolio().equals(0))) {
//			if(filtrosReportesOf.getAcronimoFolio().isEmpty()) {	
//				FacesContext context = FacesContext.getCurrentInstance();
//		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes seleccionar un Acrónimo.", "Rango de folios de oficialía"));
//		        return null;
//			}
//			if(filtrosReportesOf.getFolioA().equals(0) || filtrosReportesOf.getFolioDe().equals(0)) {	
//				FacesContext context = FacesContext.getCurrentInstance();
//		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes introducir un Rango de Folios.", "Rango de folios de oficialía"));
//		        return null;
//			}
//			if(filtrosReportesOf.getAnioFolio().equals(0)) {	
//				FacesContext context = FacesContext.getCurrentInstance();
//		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes seleccionar un Año.", "Rango de folios de oficialía"));
//		        return null;
//			}   
//		}

		// 2. Valida la fecha inicial de registro
		if(filtrosReportesOf.getFechaInicial() != null && filtrosReportesOf.getFechaFinal() != null) {
			if((new SimpleDateFormat("dd/MMMM/yyyy")).format(filtrosReportesOf.getFechaInicial()).compareTo((new SimpleDateFormat("dd/MMMM/yyyy")).format(filtrosReportesOf.getFechaFinal())) != 0) {
				if(!filtrosReportesOf.getFechaInicial().before(filtrosReportesOf.getFechaFinal())) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debes seleccionar una fecha final posterior a la fecha inicial.", "Fecha de registro"));
					return null;
				}
			}
		}

//		try {
//			if(!(filtrosReportesOf.getDiaInicial().equals(0)) ||
//			   !(filtrosReportesOf.getMesInicial().equals(0)) ||
//			   !(filtrosReportesOf.getAnioInicial().equals(0))) {
//		        calendar.set(Calendar.DAY_OF_MONTH, filtrosReportesOf.getDiaInicial());
//		        calendar.set(Calendar.MONTH, filtrosReportesOf.getMesInicial()-1);
//		        calendar.set(Calendar.YEAR, filtrosReportesOf.getAnioInicial());
//				if(filtrosReportesOf.getAnioInicial() < 2000) {
//		            throw new ArithmeticException();
//		        }
//		        filtrosReportesOf.setFechaInicial(calendar.getTime());
//			}
//		} catch(IllegalArgumentException e) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El día, mes y/o año no son válidos. Verifica la información.", "Fecha inicial de registro"));
//			return null;
//		} catch(ArithmeticException e) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Introduce un año mayor a 2000.", "Fecha inicial de registro"));
//			return null;
//		}
//
//		// 3. Valida la fecha final de registro
//		try {
//			if(!(filtrosReportesOf.getDiaFinal().equals(0)) ||
//			   !(filtrosReportesOf.getMesFinal().equals(0)) ||
//			   !(filtrosReportesOf.getAnioFinal().equals(0))) {
//		        calendar.set(Calendar.DAY_OF_MONTH, filtrosReportesOf.getDiaFinal());
//		        calendar.set(Calendar.MONTH, filtrosReportesOf.getMesFinal()-1);
//		        calendar.set(Calendar.YEAR, filtrosReportesOf.getAnioFinal());
//				if(filtrosReportesOf.getAnioFinal() < 2000) {
//		            throw new ArithmeticException();
//		        }
//		        filtrosReportesOf.setFechaFinal(calendar.getTime());
//			}
//		} catch(IllegalArgumentException e) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El día, mes y/o año no son válidos. Verifica la información.", "Fecha final de registro"));
//			return null;
//		} catch(ArithmeticException e) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Introduce un año mayor a 2000.", "Fecha final de registro"));
//			return null;
//		}

		// 3. Consulta y retorna el resultado de dicha consulta
		return asReportesOf.recuperarReporte(rolUsuario, filtrosReportesOf);
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#generarReporte(DTOReportesOficialiaHelper filtrosReportesOf)
	 */
	@Override
	public StreamedContent generarReporte(DTOReportesOficialiaHelper filtrosReportesOf) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	 	@SuppressWarnings("resource")
	 	// 1. Define el libro y la hoja de trabajo
	 	XSSFWorkbook libroExcel = new XSSFWorkbook();
        XSSFSheet hojaExcel = libroExcel.createSheet(SecurityContextHolder.getContext().getAuthentication().getName() + " - " + dateFormat.format(new Date()));

        // 2. Crea un tipo de fuente y su respectivo formato
        // 2.1 Para el título del reporte
        Font fuenteTitulo = libroExcel.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 15);
        fuenteTitulo.setColor(IndexedColors.BLACK.getIndex());

        // 2.2 Para los nombres de columnas del reporte
        Font fuenteCabecera = libroExcel.createFont();
        fuenteCabecera.setBold(true);
        fuenteCabecera.setFontHeightInPoints((short) 12);
        fuenteCabecera.setColor(IndexedColors.BLACK.getIndex());

        // 2.3 Para las celdas del reporte
        Font fuenteCelda = libroExcel.createFont();
        fuenteCelda.setFontHeightInPoints((short) 10);
        fuenteCelda.setColor(IndexedColors.BLACK.getIndex());

        // 3. Crea los estilos del reporte
        // 3.1 Para el título del reporte
        CellStyle estiloTitulo = libroExcel.createCellStyle();
        estiloTitulo.setFont(fuenteTitulo);
        estiloTitulo.setAlignment(CellStyle.ALIGN_CENTER);
        estiloTitulo.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        estiloTitulo.setFillBackgroundColor((short) 22);
        estiloTitulo.setBorderBottom(CellStyle.BORDER_THIN);
        estiloTitulo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estiloTitulo.setBorderLeft(CellStyle.BORDER_THIN);
        estiloTitulo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        estiloTitulo.setBorderRight(CellStyle.BORDER_THIN);
        estiloTitulo.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estiloTitulo.setBorderTop(CellStyle.BORDER_THIN);
        estiloTitulo.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // 3.2 Para los nombres de columna del reporte
        CellStyle estiloCabecera = libroExcel.createCellStyle();
        estiloCabecera.setFont(fuenteCabecera);
        estiloCabecera.setAlignment(CellStyle.ALIGN_CENTER);
        estiloCabecera.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        estiloCabecera.setFillBackgroundColor((short) 22);
        estiloCabecera.setBorderBottom(CellStyle.BORDER_THIN);
        estiloCabecera.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estiloCabecera.setBorderLeft(CellStyle.BORDER_THIN);
        estiloCabecera.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        estiloCabecera.setBorderRight(CellStyle.BORDER_THIN);
        estiloCabecera.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estiloCabecera.setBorderTop(CellStyle.BORDER_THIN);
        estiloCabecera.setTopBorderColor(IndexedColors.BLACK.getIndex());
      
        // 3.3 Para las celdas del reporte
        CellStyle estiloCelda = libroExcel.createCellStyle();
        estiloCelda.setWrapText(true);
        estiloCelda.setFont(fuenteCelda);
        estiloCelda.setAlignment(CellStyle.ALIGN_CENTER);
        estiloCelda.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        estiloCelda.setFillBackgroundColor((short) 22);
        estiloCelda.setBorderBottom(CellStyle.BORDER_THIN);
        estiloCelda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estiloCelda.setBorderLeft(CellStyle.BORDER_THIN);
        estiloCelda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        estiloCelda.setBorderRight(CellStyle.BORDER_THIN);
        estiloCelda.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estiloCelda.setBorderTop(CellStyle.BORDER_THIN);
        estiloCelda.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // 4. Inserta el título del Reporte
    	XSSFRow filaTitulo = hojaExcel.createRow(0);
		XSSFCell celdaTitulo = filaTitulo.createCell(0);
		celdaTitulo.setCellValue("REPORTE DE OFICIALÍA");
		celdaTitulo.setCellStyle(estiloTitulo);
		hojaExcel.addMergedRegion(new CellRangeAddress(0, 0, 0, filtrosReportesOf.getFiltrosReporte().size()-1));
		
        int numFila = 1;
        int numColumna = 0;

    	XSSFRow filaCabecera = hojaExcel.createRow(numFila++);

        // 5. Inserta los nombres de columnas del Reporte
    	for(String filtro : filtrosReportesOf.getFiltrosReporte()) {
            hojaExcel.setColumnWidth(numColumna, 7500);
    		XSSFCell celda = filaCabecera.createCell(numColumna++);
    		celda.setCellValue(filtro.toUpperCase());
    		celda.setCellStyle(estiloCabecera);

    		if(filtro.equals("Remitente")) {
    			hojaExcel.setColumnWidth(numColumna, 7500);
        		celda = filaCabecera.createCell(numColumna++);
        		celda.setCellValue("REMITENTE EXTERNO");
        		celda.setCellStyle(estiloCabecera);
    		}
    	}

    	// 6. Inserta la información de cada uno de los documentos del Reporte
        for(DTOReportesOficialia documento : filtrosReportesOf.getReporteOficialia()) {
        	numColumna = 0;
        	XSSFRow filaDocumento = hojaExcel.createRow(numFila++);
        	XSSFCell celda;
        	String listaPersonas;

        	if(filtrosReportesOf.getFiltrosReporte().contains("Folio de oficialía")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getFolioOficialia());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Número de documento")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getNumDocumento());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Tipo de documento")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getTipoDocumento());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Asunto")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getAsunto());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Fecha de registro")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(dateFormat.format(documento.getFechaCreacion()));
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Tipo de área")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getTipoArea());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Área")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getDescArea());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Remitente")) {
        		celda = filaDocumento.createCell(numColumna++);
        		listaPersonas = "";

        		for(String persona : documento.getListaRemitentes()) {
        			listaPersonas += persona + "\n";
        		}

        		celda.setCellValue(listaPersonas.substring(0, listaPersonas.length()-1));
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Remitente")) {
        		celda = filaDocumento.createCell(numColumna++);
        		listaPersonas = "";

        		for(String persona : documento.getListaRemitentesExt()) {
        			listaPersonas += persona + "\n";
        		}

        		celda.setCellValue(listaPersonas.substring(0, listaPersonas.length()-1));
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Destinatario")) {
        		celda = filaDocumento.createCell(numColumna++);
        		listaPersonas = "";

        		for(String persona : documento.getListaDestinatarios()) {
        			listaPersonas += persona + "\n";
        		}

        		celda.setCellValue(listaPersonas.substring(0, listaPersonas.length()-1));
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Copia de conocimiento")) {
        		celda = filaDocumento.createCell(numColumna++);
        		listaPersonas = "";

        		for(String persona : documento.getListaCopiasConocimiento()) {
        			listaPersonas += persona + "\n";
        		}

        		celda.setCellValue(listaPersonas.substring(0, listaPersonas.length()-1));
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Sección")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getDescSeccion());
        		celda.setCellStyle(estiloCelda);
        	}

        	if(filtrosReportesOf.getFiltrosReporte().contains("Categoría")) {
        		celda = filaDocumento.createCell(numColumna++);
        		celda.setCellValue(documento.getDescCategoria());
        		celda.setCellStyle(estiloCelda);
        	}
        }

        // 7. Crea un flujo de bytes a fin de escribirlos en un archivo Excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
			libroExcel.write(outputStream);
	        outputStream.close();
		} catch (IOException e) {
			log.error("BSDReportesOficialia.generarReporte: " + e.getMessage());
		}

        byte[] arregloBytes = outputStream.toByteArray();

		InputStream inputStream = new ByteArrayInputStream(arregloBytes);
		StreamedContent archivo = new DefaultStreamedContent(inputStream, "application/xlsx", "ReporteDeOficialia_" + SecurityContextHolder.getContext().getAuthentication().getName() + "_" + dateFormat.format(new Date()) + ".xlsx");
	
		return archivo;
	}

	/**
	 * (El comentario se encuentra en la interfaz donde fue declarado este método)
	 * @see mx.ine.gestion.as.inter.BSDReportesOficialiaInterface#generarListasPersonas(List<DTOReportesOficialia> reporteOficialia)
	 */
	@Override
	public void generarListasPersonas(List<DTOReportesOficialia> reporteOficialia) {
		for(DTOReportesOficialia documento : reporteOficialia) {

			// 1. Genera la lista de personas remitentes
			if(documento.getPersonaRemitente() != null) {
				String[] personasRemitentes = documento.getPersonaRemitente().split(",");
				for(int persona=0; persona<personasRemitentes.length; persona++) {
					if(!documento.getListaRemitentes().contains(personasRemitentes[persona])) {
						documento.getListaRemitentes().add(personasRemitentes[persona]);
					}
				}
			}

			// 2. Genera la lista de personas remitentes externas
			if(documento.getPersonaRemitenteExt() != null) {
				String[] personasRemitentesExt = documento.getPersonaRemitenteExt().split(",");
				for(int personaExt=0; personaExt<personasRemitentesExt.length; personaExt++) {
					if(!documento.getListaRemitentesExt().contains(personasRemitentesExt[personaExt])) {
						documento.getListaRemitentesExt().add(personasRemitentesExt[personaExt]);
					}
				}
			}

			// 3. Genera la lista de personas destinatarias
			if(documento.getPersonaDestinataria() != null) {
				String[] personasDestinatarias = documento.getPersonaDestinataria().split(",");
				for(int persona=0; persona<personasDestinatarias.length; persona++) {
					if(!documento.getListaDestinatarios().contains(personasDestinatarias[persona])) {
						documento.getListaDestinatarios().add(personasDestinatarias[persona]);
					}
				}
			}

			// 4. Genera la lista de personas copias de conocimiento
			if(documento.getPersonaCC() != null) {
				String[] personasCopias = documento.getPersonaCC().split(",");
				for(int persona=0; persona<personasCopias.length; persona++) {
					if(!documento.getListaCopiasConocimiento().contains(personasCopias[persona])) {
						documento.getListaCopiasConocimiento().add(personasCopias[persona]);
					}
				}
			}
		}
	}

}
