/**
 * @(#)DTOFiltrosReportesOficialiaHelper.java 09/04/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTORemitentesExternosOfEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Clase de ayuda que contiene los atributos que guardan la información
 * capturada y/o seleccionada por el usuario en la vista de
 * Reportes de la Oficialía.
 * 
 * @author José Miguel Ortiz
 * @since 09/04/2018
 */
public class DTOReportesOficialiaHelper implements Serializable {
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 3542082031508695966L;

	/* ------------------------------ Variables generales ------------------------------ */
	/**
	 * Atributo que contiene el tipo de búsqueda (seleccionado por el usuario)
	 */
	private String tipoBusqueda;
	/**
	 * Atributo que contiene la lista de personas encontradas por la búsqueda de remitentes y destinatarios
	 */
	private List<DTOEstructuraAreasEntity> personasEncontradas;
	/**
	 * Atributo que contiene la lista de todas las personas seleccionadas por el usuario
	 */
	private List<DTOEstructuraAreasEntity> personasSeleccionadas;
	/**
	 * Atributo que contiene el detalle de la persona seleccionada por el usuario
	 */
	private DTOEstructuraAreasEntity detallePersona;
	/**
	 * Atributo que contiene la lista de remitentes externos encontrados
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtEncontrados;
	/**
	 * Atributo que contiene la lista de todos los remitentes externos seleccionados por el usuario
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtSeleccionados;
	/**
	 * Atributo que contiene la lista de todos los remitentes externos agregados por el usuario
	 */
	private List<DTORemitentesExternosOfEntity> remitentesExtAgregados;
	/**
	 * Atributo que contiene el detalle del remitente externo seleccionado por el usuario
	 */
	private DTORemitentesExternosOfEntity remitenteExt;
	/**
	 * Atributo que contiene la lista de filtros predefinidos (seleccionados por default)
	 */
	private String[] filtrosPredefSel = {"Folio de oficialía", "Número de documento", "Tipo de documento", "Asunto", "Fecha de registro"};
	/**
	 * Atributo que contiene la lista de filtros seleccionados por el usuario
	 */
	private String[] filtrosSeleccionados;
	/**
	 * Atributo que describe la selección de todos los filtros para la sección de Creación de Reporte
	 */
	private boolean todosFiltros;

	/* ------------------------------ Listas de catálogos ------------------------------ */
	/**
	 * Atributo que contiene los acrónimos que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<String> catalogoAcronimosFolios;
	/**
	 * Atributo que contiene los años que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<Integer> catalogoAniosFolios;
	/**
	 * Atributo que contiene los parámetros de ordenación que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<String> catalogoOrdenacion;
	/**
	 * Atributo que contiene los tipos de documento que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<DTOCTipoDocumentoEntity> catalogoTiposDoc;
	/**
	 * Atributo que contiene las secciones que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<DTOCSeccionesEntity> catalogoSecciones;
	/**
	 * Atributo que contiene los meses del año que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<DTOMesesAnioHelper> catalogoMeses;
	/**
	 * Atributo que contiene las categorías que el usuario puede seleccionar en la
	 * sección de Filtros de Reporte
	 */
	private List<DTOCCategoriasEntity> catalogoCategorias;
	/**
	 * Atributo que contiene los tipos de área que el usuario puede seleccionar en la
	 * sección de Remitente (s) y de Destinatario (s)
	 */
	private List<DTOCTipoAreaEntity> catalogoTiposArea;
	/**
	 * Atributo que contiene las entidades que el usuario puede seleccionar en la
	 * sección de Remitente (s)
	 */
	private List<DTOEstadosEntity> entidadesRemitente;
	/**
	 * Atributo que contiene las áreas que el usuario puede seleccionar en la
	 * sección de Remitente (s)
	 */
	private List<DTOCAreaEntity> areasRemitente;
	/**
	 * Atributo que contiene las entidades que el usuario puede seleccionar en la
	 * sección de Destinatario (s)
	 */
	private List<DTOEstadosEntity> entidadesDestinatario;
	/**
	 * Atributo que contiene las áreas que el usuario puede seleccionar en la
	 * sección de Destinatario (s)
	 */
	private List<DTOCAreaEntity> areasDestinatario;
	/**
	 * Atributo que contiene la lista de filtros predefinidos en la sección de
	 * Creación de Reporte
	 */
	private List<String> filtrosPredefinidos;
	/**
	 * Atributo que contiene la lista de filtros que el usuario puede seleccionar en la
	 * sección de Creación de Reporte
	 */
	private List<String> catalogoFiltrosReporte;

	/* ------------------------------ Filtros - Sección Filtros de Reporte ------------------------------ */
	/**
	 * Atributo que contiene la selección o no del tipo de filtrado de folios
	 */
	private boolean conFiltroFolios;
	/**
	 * Atributo que contiene el folio de oficialía introducido por el usuario
	 */
	private String folioOficialia;
	/**
	 * Atributo que contiene el acrónimo del folio introducido por el usuario
	 */
	private String acronimoFolio;
	/**
	 * Atributo que contiene el folio inicial o de origen introducido por el usuario
	 */
	private Integer folioDe;
	/**
	 * Atributo que contiene el folio final o de destino introducido por el usuario
	 */
	private Integer folioA;
	/**
	 * Atributo que contiene el año del folio introducido por el usuario
	 */
	private Integer anioFolio;
	/**
	 * Atributo que contiene el número de documento introducido por el usuario
	 */
	private String numeroDocumento;
	/**
	 * Atributo que contiene el asunto introducido por el usuario
	 */
	private String asunto;
	/**
	 * Atributo que contiene la selección o no de copias de conocimiento
	 */
	private String copiasConocimiento;
	/**
	 * Atributo que contiene el día de la fecha inicial de registro introducido por el usuario
	 */
	private Integer diaInicial;
	/**
	 * Atributo que contiene el mes de la fecha inicial de registro introducido por el usuario
	 */
	private Integer mesInicial;
	/**
	 * Atributo que contiene el año de la fecha inicial de registro introducido por el usuario
	 */
	private Integer anioInicial;
	/**
	 * Atributo que contiene la fecha inicial de registro introducido por el usuario
	 */
	private Date fechaInicial;
	/**
	 * Atributo que contiene el día de la fecha final de registro introducido por el usuario
	 */
	private Integer diaFinal;
	/**
	 * Atributo que contiene el mes de la fecha final de registro introducido por el usuario
	 */
	private Integer mesFinal;
	/**
	 * Atributo que contiene el año de la fecha final de registro introducido por el usuario
	 */
	private Integer anioFinal;
	/**
	 * Atributo que contiene la fecha final de registro introducido por el usuario
	 */
	private Date fechaFinal;
	/**
	 * Atributo que contiene el tipo de ordenación seleccionado por el usuario
	 */
	private String parametroOrden;
	/**
	 * Atributo que contiene el tipo de documeno seleccionado por el usuario
	 */
	private Integer idTipoDocumento;
	/**
	 * Atributo que contiene la sección seleccionada por el usuario
	 */
	private Integer idSeccion;
	/**
	 * Atributo que contiene la categoría seleccionada por el usuario
	 */
	private Integer idCategoria;

	/* ------------------------------ Filtros - Sección Remitente (s) ------------------------------ */
	/**
	 * Atributo que contiene el tipo de remitente (selecionado por el usuario)
	 */
	private String tipoRemitente;
	/**
	 * Atributo que contiene el nombre o los nombres del remitente (capturado por el usuario)
	 */
	private String nombreRemitente;
	/**
	 * Atributo que contiene el apellido o los apellidos del remitente (capturado por el usuario)
	 */
	private String apellidoRemitente;
	/**
	 * Atributo que contiene el tipo de área del remitente (seleccionado por el usuario)
	 */
	private Integer idTipoAreaRemitente;
	/**
	 * Atributo que contiene la entidad del remitente (seleccionado por el usuario)
	 */
	private Integer idEntidadRemitente;
	/**
	 * Atributo que contiene el área del remitente (seleccionado por el usuario)
	 */
	private Integer idAreaRemitente;
	/**
	 * Atributo que contiene todos los remitentes almacenados por el usuario
	 */
	private List<DTOEstructuraAreasEntity> remitentes;
	/**
	 * Atributo que contiene el nombre completo del remitente externo (capturado por el usuario)
	 */
	private String nombreRemitenteExt;
	/**
	 * Atributo que contiene la dependencia del remitente externo (capturado por el usuario)
	 */
	private String dependenciaRemitenteExt;

	/* ------------------------------ Filtros - Sección Destinatario (s) ------------------------------ */
	/**
	 * Atributo que contiene el nombre o los nombres del destinatario (capturado por el usuario)
	 */
	private String nombreDestinatario;
	/**
	 * Atributo que contiene el apellido o los apellidos del destinatario (capturado por el usuario)
	 */
	private String apellidoDestinatario;
	/**
	 * Atributo que contiene el tipo de área del destinatario (seleccionado por el usuario)
	 */
	private Integer idTipoAreaDestinatario;
	/**
	 * Atributo que contiene la entidad del destinatario (seleccionado por el usuario)
	 */
	private Integer idEntidadDestinatario;
	/**
	 * Atributo que contiene el área del destinatario (seleccionado por el usuario)
	 */
	private Integer idAreaDestinatario;
	/**
	 * Atributo que contiene todos los destinatarios almacenados por el usuario
	 */
	private List<DTOEstructuraAreasEntity> destinatarios;

	/* ------------------------------ Vista de Reportes - Sección Resultados de la búsqueda ------------------------------ */
	/**
	 * Atributo que contiene la lista de los filtros / columnas seleccionados por el usuario para la creación del reporte
	 */
	private List<String> filtrosReporte;
	/**
	 * Atributo que contiene la lista de documentos recuperados una vez que la consulta es ejecutada en la vista de
	 * Reportes de la Oficialía
	 */
	private List<DTOReportesOficialia> reporteOficialia;
	/**
	 * Atributo que contiene el reporte generado por el usuario para su posterior exportación
	 */
	private StreamedContent reporte;

	/* ------------------------------ Constructor de la clase ------------------------------ */
	public DTOReportesOficialiaHelper() {
		tipoBusqueda			= "";
		personasEncontradas		= new ArrayList<DTOEstructuraAreasEntity>();
		personasSeleccionadas	= new ArrayList<DTOEstructuraAreasEntity>();
		detallePersona			= new DTOEstructuraAreasEntity();
		remitentesExtEncontrados	= new ArrayList<DTORemitentesExternosOfEntity>();
		remitentesExtSeleccionados	= new ArrayList<DTORemitentesExternosOfEntity>();
		remitentesExtAgregados	= new ArrayList<DTORemitentesExternosOfEntity>();
		remitenteExt			= new DTORemitentesExternosOfEntity();
		todosFiltros			= false;
		catalogoAcronimosFolios	= new ArrayList<String>();
		catalogoAniosFolios		= new ArrayList<Integer>();
		catalogoOrdenacion		= new ArrayList<String>();
		catalogoTiposDoc		= new ArrayList<DTOCTipoDocumentoEntity>();
		catalogoSecciones		= new ArrayList<DTOCSeccionesEntity>();
		catalogoMeses			= new ArrayList<DTOMesesAnioHelper>();
		catalogoCategorias		= new ArrayList<DTOCCategoriasEntity>();
		catalogoTiposArea		= new ArrayList<DTOCTipoAreaEntity>();
		conFiltroFolios			= false;
		folioOficialia			= "";
		acronimoFolio			= "";
//		folioDe					= new Integer(0);		// Variables no inicializadas para garantizar placeholder en la vista
//		folioA					= new Integer(0);
//		anioFolio				= new Integer(0);
		numeroDocumento			= "";
		asunto					= "";
		copiasConocimiento		= "";
//		diaInicial				= new Integer(0);		// Variables no inicializadas para garantizar placeholder en la vista
//		anioInicial				= new Integer(0);
//		diaFinal				= new Integer(0);
//		anioFinal				= new Integer(0);
//		mesInicial				= new Integer(0);
//		mesFinal				= new Integer(0);
		parametroOrden			= "";
		idTipoDocumento			= new Integer(0);
		idSeccion				= new Integer(0);
		idCategoria				= new Integer(0);
		tipoRemitente			= "";
		nombreRemitente			= "";
		apellidoRemitente		= "";
		entidadesRemitente		= new ArrayList<DTOEstadosEntity>();
		areasRemitente			= new ArrayList<DTOCAreaEntity>();
		idTipoAreaRemitente		= new Integer(0);
//		idEntidadRemitente		= new Integer(0);		// Variable que debe ser mantenida en null para correctas consultas
		idAreaRemitente			= new Integer(0);
		remitentes				= new ArrayList<DTOEstructuraAreasEntity>();
		nombreRemitenteExt		= "";
		dependenciaRemitenteExt	= "";
		nombreDestinatario		= "";
		apellidoDestinatario	= "";
		entidadesDestinatario	= new ArrayList<DTOEstadosEntity>();
		areasDestinatario		= new ArrayList<DTOCAreaEntity>();
		catalogoFiltrosReporte	= new ArrayList<String>();
		filtrosPredefinidos		= Arrays.asList("Folio de oficialía", "Número de documento", "Tipo de documento", "Asunto", "Fecha de registro");
		idTipoAreaDestinatario	= new Integer(0);
//		idEntidadDestinatario	= new Integer(0);		// Variable que debe ser mantenida en null para correctas consultas
		idAreaDestinatario		= new Integer(0);
		destinatarios			= new ArrayList<DTOEstructuraAreasEntity>();
		filtrosReporte			= new ArrayList<String>();
		reporteOficialia		= new ArrayList<DTOReportesOficialia>();
	}

	/* ------------------------------ Getters & Setters ------------------------------ */
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public List<DTOEstructuraAreasEntity> getPersonasEncontradas() {
		return personasEncontradas;
	}

	public void setPersonasEncontradas(List<DTOEstructuraAreasEntity> personasEncontradas) {
		this.personasEncontradas = personasEncontradas;
	}

	public List<DTOEstructuraAreasEntity> getPersonasSeleccionadas() {
		return personasSeleccionadas;
	}

	public void setPersonasSeleccionadas(List<DTOEstructuraAreasEntity> personasSeleccionadas) {
		this.personasSeleccionadas = personasSeleccionadas;
	}

	public DTOEstructuraAreasEntity getDetallePersona() {
		return detallePersona;
	}

	public void setDetallePersona(DTOEstructuraAreasEntity detallePersona) {
		this.detallePersona = detallePersona;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtEncontrados() {
		return remitentesExtEncontrados;
	}

	public void setRemitentesExtEncontrados(List<DTORemitentesExternosOfEntity> remitentesExtEncontrados) {
		this.remitentesExtEncontrados = remitentesExtEncontrados;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtSeleccionados() {
		return remitentesExtSeleccionados;
	}

	public void setRemitentesExtSeleccionados(List<DTORemitentesExternosOfEntity> remitentesExtSeleccionados) {
		this.remitentesExtSeleccionados = remitentesExtSeleccionados;
	}

	public List<DTORemitentesExternosOfEntity> getRemitentesExtAgregados() {
		return remitentesExtAgregados;
	}

	public void setRemitentesExtAgregados(List<DTORemitentesExternosOfEntity> remitentesExtAgregados) {
		this.remitentesExtAgregados = remitentesExtAgregados;
	}

	public DTORemitentesExternosOfEntity getRemitenteExt() {
		return remitenteExt;
	}

	public void setRemitenteExt(DTORemitentesExternosOfEntity remitenteExt) {
		this.remitenteExt = remitenteExt;
	}

	public String[] getFiltrosPredefSel() {
		return filtrosPredefSel;
	}

	public void setFiltrosPredefSel(String[] filtrosPredefSel) {
		this.filtrosPredefSel = filtrosPredefSel;
	}

	public String[] getFiltrosSeleccionados() {
		return filtrosSeleccionados;
	}

	public void setFiltrosSeleccionados(String[] filtrosSeleccionados) {
		this.filtrosSeleccionados = filtrosSeleccionados;
	}

	public boolean getTodosFiltros() {
		return todosFiltros;
	}

	public void setTodosFiltros(boolean todosFiltros) {
		this.todosFiltros = todosFiltros;
	}

	public List<String> getCatalogoAcronimosFolios() {
		return catalogoAcronimosFolios;
	}

	public void setCatalogoAcronimosFolios(List<String> catalogoAcronimosFolios) {
		this.catalogoAcronimosFolios = catalogoAcronimosFolios;
	}

	public List<Integer> getCatalogoAniosFolios() {
		return catalogoAniosFolios;
	}

	public void setCatalogoAniosFolios(List<Integer> catalogoAniosFolios) {
		this.catalogoAniosFolios = catalogoAniosFolios;
	}

	public List<String> getCatalogoOrdenacion() {
		return catalogoOrdenacion;
	}

	public void setCatalogoOrdenacion(List<String> catalogoOrdenacion) {
		this.catalogoOrdenacion = catalogoOrdenacion;
	}

	public List<DTOCTipoDocumentoEntity> getCatalogoTiposDoc() {
		return catalogoTiposDoc;
	}

	public void setCatalogoTiposDoc(List<DTOCTipoDocumentoEntity> catalogoTiposDoc) {
		this.catalogoTiposDoc = catalogoTiposDoc;
	}

	public List<DTOCSeccionesEntity> getCatalogoSecciones() {
		return catalogoSecciones;
	}

	public void setCatalogoSecciones(List<DTOCSeccionesEntity> catalogoSecciones) {
		this.catalogoSecciones = catalogoSecciones;
	}

	public List<DTOMesesAnioHelper> getCatalogoMeses() {
		return catalogoMeses;
	}

	public void setCatalogoMeses(List<DTOMesesAnioHelper> catalogoMeses) {
		this.catalogoMeses = catalogoMeses;
	}

	public List<DTOCCategoriasEntity> getCatalogoCategorias() {
		return catalogoCategorias;
	}

	public void setCatalogoCategorias(List<DTOCCategoriasEntity> catalogoCategorias) {
		this.catalogoCategorias = catalogoCategorias;
	}

	public List<DTOCTipoAreaEntity> getCatalogoTiposArea() {
		return catalogoTiposArea;
	}

	public void setCatalogoTiposArea(List<DTOCTipoAreaEntity> catalogoTiposArea) {
		this.catalogoTiposArea = catalogoTiposArea;
	}

	public List<DTOEstadosEntity> getEntidadesRemitente() {
		return entidadesRemitente;
	}

	public void setEntidadesRemitente(List<DTOEstadosEntity> entidadesRemitente) {
		this.entidadesRemitente = entidadesRemitente;
	}

	public List<DTOCAreaEntity> getAreasRemitente() {
		return areasRemitente;
	}

	public void setAreasRemitente(List<DTOCAreaEntity> areasRemitente) {
		this.areasRemitente = areasRemitente;
	}

	public List<DTOEstadosEntity> getEntidadesDestinatario() {
		return entidadesDestinatario;
	}

	public void setEntidadesDestinatario(List<DTOEstadosEntity> entidadesDestinatario) {
		this.entidadesDestinatario = entidadesDestinatario;
	}

	public List<DTOCAreaEntity> getAreasDestinatario() {
		return areasDestinatario;
	}

	public void setAreasDestinatario(List<DTOCAreaEntity> areasDestinatario) {
		this.areasDestinatario = areasDestinatario;
	}

	public List<String> getFiltrosPredefinidos() {
		return filtrosPredefinidos;
	}

	public void setFiltrosPredefinidos(List<String> filtrosPredefinidos) {
		this.filtrosPredefinidos = filtrosPredefinidos;
	}

	public List<String> getCatalogoFiltrosReporte() {
		return catalogoFiltrosReporte;
	}

	public void setCatalogoFiltrosReporte(List<String> catalogoFiltrosReporte) {
		this.catalogoFiltrosReporte = catalogoFiltrosReporte;
	}

	public boolean getConFiltroFolios() {
		return conFiltroFolios;
	}

	public void setConFiltroFolios(boolean conFiltroFolios) {
		this.conFiltroFolios = conFiltroFolios;
	}

	public String getFolioOficialia() {
		return folioOficialia;
	}

	public void setFolioOficialia(String folioOficialia) {
		this.folioOficialia = folioOficialia;
	}

	public String getAcronimoFolio() {
		return acronimoFolio;
	}

	public void setAcronimoFolio(String acronimoFolio) {
		this.acronimoFolio = acronimoFolio;
	}

	public Integer getFolioDe() {
		return folioDe;
	}

	public void setFolioDe(Integer folioDe) {
		this.folioDe = folioDe;
	}

	public Integer getFolioA() {
		return folioA;
	}

	public void setFolioA(Integer folioA) {
		this.folioA = folioA;
	}

	public Integer getAnioFolio() {
		return anioFolio;
	}

	public void setAnioFolio(Integer anioFolio) {
		this.anioFolio = anioFolio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCopiasConocimiento() {
		return copiasConocimiento;
	}

	public void setCopiasConocimiento(String copiasConocimiento) {
		this.copiasConocimiento = copiasConocimiento;
	}

	public Integer getDiaInicial() {
		return diaInicial;
	}

	public void setDiaInicial(Integer diaInicial) {
		this.diaInicial = diaInicial;
	}

	public Integer getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	public Integer getAnioInicial() {
		return anioInicial;
	}

	public void setAnioInicial(Integer anioInicial) {
		this.anioInicial = anioInicial;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Integer getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(Integer diaFinal) {
		this.diaFinal = diaFinal;
	}

	public Integer getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

	public Integer getAnioFinal() {
		return anioFinal;
	}

	public void setAnioFinal(Integer anioFinal) {
		this.anioFinal = anioFinal;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getParametroOrden() {
		return parametroOrden;
	}

	public void setParametroOrden(String parametroOrden) {
		this.parametroOrden = parametroOrden;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipoRemitente() {
		return tipoRemitente;
	}

	public void setTipoRemitente(String tipoRemitente) {
		this.tipoRemitente = tipoRemitente;
	}

	public String getNombreRemitente() {
		return nombreRemitente;
	}

	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	public String getApellidoRemitente() {
		return apellidoRemitente;
	}

	public void setApellidoRemitente(String apellidoRemitente) {
		this.apellidoRemitente = apellidoRemitente;
	}

	public Integer getIdTipoAreaRemitente() {
		return idTipoAreaRemitente;
	}

	public void setIdTipoAreaRemitente(Integer idTipoAreaRemitente) {
		this.idTipoAreaRemitente = idTipoAreaRemitente;
	}

	public Integer getIdEntidadRemitente() {
		return idEntidadRemitente;
	}

	public void setIdEntidadRemitente(Integer idEntidadRemitente) {
		this.idEntidadRemitente = idEntidadRemitente;
	}

	public Integer getIdAreaRemitente() {
		return idAreaRemitente;
	}

	public void setIdAreaRemitente(Integer idAreaRemitente) {
		this.idAreaRemitente = idAreaRemitente;
	}

	public List<DTOEstructuraAreasEntity> getRemitentes() {
		return remitentes;
	}

	public void setRemitentes(List<DTOEstructuraAreasEntity> remitentes) {
		this.remitentes = remitentes;
	}

	public String getNombreRemitenteExt() {
		return nombreRemitenteExt;
	}

	public void setNombreRemitenteExt(String nombreRemitenteExt) {
		this.nombreRemitenteExt = nombreRemitenteExt;
	}

	public String getDependenciaRemitenteExt() {
		return dependenciaRemitenteExt;
	}

	public void setDependenciaRemitenteExt(String dependenciaRemitenteExt) {
		this.dependenciaRemitenteExt = dependenciaRemitenteExt;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getApellidoDestinatario() {
		return apellidoDestinatario;
	}

	public void setApellidoDestinatario(String apellidoDestinatario) {
		this.apellidoDestinatario = apellidoDestinatario;
	}

	public Integer getIdTipoAreaDestinatario() {
		return idTipoAreaDestinatario;
	}

	public void setIdTipoAreaDestinatario(Integer idTipoAreaDestinatario) {
		this.idTipoAreaDestinatario = idTipoAreaDestinatario;
	}

	public Integer getIdEntidadDestinatario() {
		return idEntidadDestinatario;
	}

	public void setIdEntidadDestinatario(Integer idEntidadDestinatario) {
		this.idEntidadDestinatario = idEntidadDestinatario;
	}

	public Integer getIdAreaDestinatario() {
		return idAreaDestinatario;
	}

	public void setIdAreaDestinatario(Integer idAreaDestinatario) {
		this.idAreaDestinatario = idAreaDestinatario;
	}

	public List<DTOEstructuraAreasEntity> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<DTOEstructuraAreasEntity> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public List<String> getFiltrosReporte() {
		return filtrosReporte;
	}

	public void setFiltrosReporte(List<String> filtrosReporte) {
		this.filtrosReporte = filtrosReporte;
	}

	public List<DTOReportesOficialia> getReporteOficialia() {
		return reporteOficialia;
	}

	public void setReporteOficialia(List<DTOReportesOficialia> reporteOficialia) {
		this.reporteOficialia = reporteOficialia;
	}

	public StreamedContent getReporte() {
		return reporte;
	}

	public void setReporte(StreamedContent reporte) {
		this.reporte = reporte;
	}

}