/**
 * @(#)DTOCapturaDocumentoHelper.java 15/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.dto.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.dto.db.DTOAcronimoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOPlantillaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoAreaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCTipoDocumentoEntity;
import mx.ine.gestion.dto.db.geografico.DTOEstadosEntity;

/**
 * Clase de ayuda que contiene los atributos que guardar la información
 * seleccionada por el usuario en la pantalla de captura de documentos.
 * 
 * @author Sergio Ley Garcia
 * @since 15/09/2017
 */
public class DTOFiltrosCapturaDocumentoHelper implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 5371335348758752728L;
	
	/* ------------------------------------- Filtros ------------------------------------ */
	
	/**
	 * Atributo que contendrá el identificador del tipo de documento 
	 * que el usuario usa para crear el documento
	 */
	private Integer idTipoDocumento;

	/**
	 * Atributo que contendrá el identificador del acrónimo 
	 * que el usuario usa para crear el documento
	 */
	private Integer idAcronimo;

	/**
	 * Atributo que contendrá el asunto relacionado al documento
	 */
	private String asunto;

	/**
	 * Atributo que contendrá el identificador de la plantilla seleccionada
	 * (solo en caso de que el usuario haya seleccionado una plantilla)
	 */
	private DTOPlantillaEntity idPlantilla;
	
	/**
	 * Atributo que contiene a los remitentes que iran en el documento
	 */
	private List<DTOEstructuraAreasEntity> remitentes;
	
	/**
	 * Atributo que contiene a los destinatarios que iran en el documento
	 */
	private List<DTOEstructuraAreasEntity> destinatarios;
	
	/**
	 * Atributo que contiene a los destinatarios tipo área que iran en el documento
	 */
	private List<DTOCAreaEntity> destinatariosComoArea;
	
	
	/**
	 * Atributo que contiene a los ccp que iran en el documento
	 */
	private List<DTOEstructuraAreasEntity> ccp;

	/**
	 * Atributo que contiene el tipo de captura que se hizo sobre el documento
	 * 1.- Documento en blanco
	 * 2.- Plantilla
	 * 3.- Adjuntar documento
	 */
	private Integer tipoCapturaDocumento;

	/**
	 * Atributo que contiene la liga que abre el documento word para las opciones
	 * de "documento en blanco" o "plantilla"
	 */
	private String hyperLinkWord;

	/**
	 * Es un nombre que se genera para el documento que esta siendo editado.
	 */
	private String nombreTemporalDocumento;

	/**
	 * Atributo utilizado cuando se toma la opcion de "adjuntar documento"
	 * guarda el nombre original del documento;
	 */
	private String nombreOriginalPdf;
	
	/**
	 * Atributo utilizado cuando se toma la opcion de "adjuntar documento"
	 * guarda el nombre original del ultimo documento adjunto durante toda la ejecución de la pantalla;
	 */
	private String nombreOriginalPdfAux;

	/**
	 * Número de documento obtenido de la captura, se utiliza para
	 * enviarlo a la captura de documentos
	 */
	private String numeroDocumento;
	
	/**
	 * Lista de documentos anexos obtenido de la captura, se utiliza para
	 * enviarlos al gluster al guardar la captura del documento
	 */
	private List<DTODocumentoAnexoHelper> anexos;

	/* ------------------------------ Filtro - Remitente --------------------------------- */
	
	/**
	 * Atributo que contendrá el nombre del o los remitentes que vaya tecleando el usuario
	 */
	private String nombreRemitente;

	/**
	 * Atributo que contendra el apellido de o los remitentes que vaya tecleando el usuario
	 */
	private String apellidoRemitente;

	/**
	 * Atributo que contiene el tipo de área del o de los remitentes que sean seleccionados
	 */
	private Integer tipoAreaRemitente;

	/**
	 * Atributo que contiene el identificador de la entidad del o de los remitentes que sean seleccionados
	 */
	private Integer idEntidadRemitente;

	/**
	 * Atributo que contiene el identificador del área del o de los remitentes que sean seleccionados
	 */
	private Integer idAreaRemitente;
	
	/**
	 * Atributo que contiene todas las personas que son seleccionadas como
	 * remitentes para el documento
	 */
	private List<DTOEstructuraAreasEntity> remitentesSeleccionados;
	
	/* ------------------------------ Filtro - Destinatario --------------------------------- */
	
	/**
	 * Atributo que contendrá el nombre del o los destinatarios que vaya tecleando el usuario
	 */
	private String nombreDestinatario;

	/**
	 * Atributo que contendra el apellido de o los destinatarios que vaya tecleando el usuario
	 */
	private String apellidoDestinatario;

	/**
	 * Atributo que contiene el tipo de área del o de los destinatarios que sean seleccionados
	 */
	private Integer tipoAreaDestinatario;

	/**
	 * Atributo que contiene el identificador de la entidad del o de los destinatarios que sean seleccionados
	 */
	private Integer idEntidadDestinatario;

	/**
	 * Atributo que contiene el identificador del área del o de los destinatarios que sean seleccionados
	 */
	private Integer idAreaDestinatario;
	
	/**
	 * Atributo que nos indica si el destinatario esta siendo tratado como persona o como área
	 */
	private boolean busquedaPorArea;
	
	/**
	 * Atributo que nos indica el nombre del área destinatario cuando se trata al destinatario como área
	 */
	private String nombreAreaDestinatario;
	
	
	/**
	 * Atributo que contiene todas las personas que son seleccionadas como
	 * destinatarios para el documento
	 */
	private List<DTOEstructuraAreasEntity> destinatariosSeleccionados;
	
	/**
	 * Atributo que contiene todas las areas que son seleccionadas como
	 * destinatarios para el documento
	 */
	private List<DTOCAreaEntity> areasDestinatariosSeleccionados;
	
	/* ------------------------------ Filtro - Destinatario --------------------------------- */
	
	/**
	 * Atributo que contendrá el nombre del o las ccp que vaya tecleando el usuario
	 */
	private String nombreCCP;

	/**
	 * Atributo que contendra el apellido de o las ccp que vaya tecleando el usuario
	 */
	private String apellidoCCP;

	/**
	 * Atributo que contiene el tipo de área del o de las ccp que sean seleccionados
	 */
	private Integer tipoAreaCCP;

	/**
	 * Atributo que contiene el identificador de la entidad del o de las ccp que sean seleccionados
	 */
	private Integer idEntidadCCP;

	/**
	 * Atributo que contiene el identificador del área del o de las ccp que sean seleccionados
	 */
	private Integer idAreaCCP;
	
	/**
	 * Atributo que contiene todas las personas que son seleccionadas como
	 * ccp para el documento
	 */
	private List<DTOEstructuraAreasEntity> ccpSeleccionados;
	
	/* -------------------------------- Listas de cátalogos ----------------------------- */

	
	/**
	 * Atributo que contendrá la lista de tipos de documentos que el usuario
	 * puede seleccionar en pantalla
	 */
	private List<DTOCTipoDocumentoEntity> listaTiposDocumentos;

	/**
	 * Atributo que contendrá la lista de acrónimos que el usuario puede
	 * seleccionar en pantalla
	 */
	private List<DTOAcronimoEntity> listaAcronimos;

	/**
	 * Atributo que contendrá la lista de plantillas que el usuario
	 * configuro y que puede seleccionar
	 */
	private List<DTOPlantillaEntity> listaPlantillas;
	
	/**
	 * Atributo que contendrá los tipos de área que el usuario puede seleccionar
	 * en las busquedas de remitente
	 */
	private List<DTOCTipoAreaEntity> listaTiposAreaRemitentes;

	/**
	 * Atributo que contendrá las entidades que el usuario puede seleccionar
	 * en las busquedas de remitente
	 */
	private List<DTOEstadosEntity> listaEstadosRemitentes;

	/**
	 * Atributo que contendrá las áreas que el usuario puede seleccionar
	 * en las busquedas de remitente
	 */
	private List<DTOCAreaEntity> listaAreasRemitentes;
	
	/**
	 * Atributo que contendrá los tipos de área que el usuario puede seleccionar
	 * en las busquedas de destinatarios
	 */
	private List<DTOCTipoAreaEntity> listaTiposAreaDestinatarios;

	/**
	 * Atributo que contendrá las entidades que el usuario puede seleccionar
	 * en las busquedas de destinatarios
	 */
	private List<DTOEstadosEntity> listaEstadosDestinatarios;

	/**
	 * Atributo que contendrá las áreas que el usuario puede seleccionar
	 * en las busquedas de destinatarios
	 */
	private List<DTOCAreaEntity> listaAreasDestinatarios;
	
	/**
	 * Atributo que contendrá los tipos de área que el usuario puede seleccionar
	 * en las busquedas de ccp
	 */
	private List<DTOCTipoAreaEntity> listaTiposAreaCCP;

	/**
	 * Atributo que contendrá las entidades que el usuario puede seleccionar
	 * en las busquedas de ccp
	 */
	private List<DTOEstadosEntity> listaEstadosCCP;

	/**
	 * Atributo que contendrá las áreas que el usuario puede seleccionar
	 * en las busquedas de ccp
	 */
	private List<DTOCAreaEntity> listaAreasCCP;

	/**
	 * Contiene las personas que se encontraron de la búsqueda de destinatarios
	 * remitentes y ccp, se muestra en las ventanas emergentes!
	 */
	private List<DTOEstructuraAreasEntity> personasBusquedasEncontradas;
	
	/**
	 * Contiene las areas que se encontraron de la búsqueda de destinatarios
	 * se muestra en las ventanas emergentes!
	 */
	private List<DTOCAreaEntity> areasBusquedaEncontradas;
	
	/**
	 * Atributo donde se guarda el número del documento que al que se está respondiendo
	 */
	private Integer idDocumentoRespondido;

	/* -------------------------------- Getters & Setters ----------------------------- */
	
	/**
	 * @return valor de tipo Integer que esta contenido en la variable idTipoDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAcronimo
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdAcronimo() {
		return idAcronimo;
	}

	/**
	 * @param idAcronimo : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdAcronimo(Integer idAcronimo) {
		this.idAcronimo = idAcronimo;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable asunto
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return valor de tipo DTOPlantillaEntity que esta contenido en la variable idPlantilla
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public DTOPlantillaEntity getIdPlantilla() {
		return idPlantilla;
	}

	/**
	 * @param idPlantilla : valor que se ingresa a la variable de tipo DTOPlantillaEntity
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdPlantilla(DTOPlantillaEntity idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreRemitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getNombreRemitente() {
		return nombreRemitente;
	}

	/**
	 * @param nombreRemitente : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidoRemitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getApellidoRemitente() {
		return apellidoRemitente;
	}

	/**
	 * @param apellidoRemitente : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setApellidoRemitente(String apellidoRemitente) {
		this.apellidoRemitente = apellidoRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaRemitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getTipoAreaRemitente() {
		return tipoAreaRemitente;
	}

	/**
	 * @param tipoAreaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setTipoAreaRemitente(Integer tipoAreaRemitente) {
		this.tipoAreaRemitente = tipoAreaRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidadRemitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdEntidadRemitente() {
		return idEntidadRemitente;
	}

	/**
	 * @param idEntidadRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdEntidadRemitente(Integer idEntidadRemitente) {
		this.idEntidadRemitente = idEntidadRemitente;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaRemitente
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdAreaRemitente() {
		return idAreaRemitente;
	}

	/**
	 * @param idAreaRemitente : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdAreaRemitente(Integer idAreaRemitente) {
		this.idAreaRemitente = idAreaRemitente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreDestinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * @param nombreDestinatario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidoDestinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getApellidoDestinatario() {
		return apellidoDestinatario;
	}

	/**
	 * @param apellidoDestinatario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setApellidoDestinatario(String apellidoDestinatario) {
		this.apellidoDestinatario = apellidoDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaDestinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getTipoAreaDestinatario() {
		return tipoAreaDestinatario;
	}

	/**
	 * @param tipoAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setTipoAreaDestinatario(Integer tipoAreaDestinatario) {
		this.tipoAreaDestinatario = tipoAreaDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidadDestinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdEntidadDestinatario() {
		return idEntidadDestinatario;
	}

	/**
	 * @param idEntidadDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdEntidadDestinatario(Integer idEntidadDestinatario) {
		this.idEntidadDestinatario = idEntidadDestinatario;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaDestinatario
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdAreaDestinatario() {
		return idAreaDestinatario;
	}

	/**
	 * @param idAreaDestinatario : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdAreaDestinatario(Integer idAreaDestinatario) {
		this.idAreaDestinatario = idAreaDestinatario;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getNombreCCP() {
		return nombreCCP;
	}

	/**
	 * @param nombreCCP : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setNombreCCP(String nombreCCP) {
		this.nombreCCP = nombreCCP;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable apellidoCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public String getApellidoCCP() {
		return apellidoCCP;
	}

	/**
	 * @param apellidoCCP : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setApellidoCCP(String apellidoCCP) {
		this.apellidoCCP = apellidoCCP;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoAreaCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getTipoAreaCCP() {
		return tipoAreaCCP;
	}

	/**
	 * @param tipoAreaCCP : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setTipoAreaCCP(Integer tipoAreaCCP) {
		this.tipoAreaCCP = tipoAreaCCP;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idEntidadCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdEntidadCCP() {
		return idEntidadCCP;
	}

	/**
	 * @param idEntidadCCP : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdEntidadCCP(Integer idEntidadCCP) {
		this.idEntidadCCP = idEntidadCCP;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idAreaCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public Integer getIdAreaCCP() {
		return idAreaCCP;
	}

	/**
	 * @param idAreaCCP : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setIdAreaCCP(Integer idAreaCCP) {
		this.idAreaCCP = idAreaCCP;
	}

	/**
	 * @return valor de tipo List<DTOCTipoDocumentoEntity> que esta contenido en la variable listaTiposDocumentos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCTipoDocumentoEntity> getListaTiposDocumentos() {
		return listaTiposDocumentos;
	}

	/**
	 * @param listaTiposDocumentos : valor que se ingresa a la variable de tipo List<DTOCTipoDocumentoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaTiposDocumentos(
			List<DTOCTipoDocumentoEntity> listaTiposDocumentos) {
		this.listaTiposDocumentos = listaTiposDocumentos;
	}

	/**
	 * @return valor de tipo List<DTOAcronimoEntity> que esta contenido en la variable listaAcronimos
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOAcronimoEntity> getListaAcronimos() {
		return listaAcronimos;
	}

	/**
	 * @param listaAcronimos : valor que se ingresa a la variable de tipo List<DTOAcronimoEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaAcronimos(List<DTOAcronimoEntity> listaAcronimos) {
		this.listaAcronimos = listaAcronimos;
	}

	/**
	 * @return valor de tipo List<DTOPlantillaEntity> que esta contenido en la variable listaPlantillas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOPlantillaEntity> getListaPlantillas() {
		return listaPlantillas;
	}

	/**
	 * @param listaPlantillas : valor que se ingresa a la variable de tipo List<DTOPlantillaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaPlantillas(List<DTOPlantillaEntity> listaPlantillas) {
		this.listaPlantillas = listaPlantillas;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaTiposAreaRemitentes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCTipoAreaEntity> getListaTiposAreaRemitentes() {
		return listaTiposAreaRemitentes;
	}

	/**
	 * @param listaTiposAreaRemitentes : valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaTiposAreaRemitentes(
			List<DTOCTipoAreaEntity> listaTiposAreaRemitentes) {
		this.listaTiposAreaRemitentes = listaTiposAreaRemitentes;
	}

	/**
	 * @return valor de tipo List<DTOEstadosEntity> que esta contenido en la variable listaEstadosRemitentes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> getListaEstadosRemitentes() {
		return listaEstadosRemitentes;
	}

	/**
	 * @param listaEstadosRemitentes : valor que se ingresa a la variable de tipo List<DTOEstadosEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaEstadosRemitentes(
			List<DTOEstadosEntity> listaEstadosRemitentes) {
		this.listaEstadosRemitentes = listaEstadosRemitentes;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaAreasRemitentes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> getListaAreasRemitentes() {
		return listaAreasRemitentes;
	}

	/**
	 * @param listaAreasRemitentes : valor que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaAreasRemitentes(List<DTOCAreaEntity> listaAreasRemitentes) {
		this.listaAreasRemitentes = listaAreasRemitentes;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaTiposAreaDestinatarios
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCTipoAreaEntity> getListaTiposAreaDestinatarios() {
		return listaTiposAreaDestinatarios;
	}

	/**
	 * @param listaTiposAreaDestinatarios : valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaTiposAreaDestinatarios(
			List<DTOCTipoAreaEntity> listaTiposAreaDestinatarios) {
		this.listaTiposAreaDestinatarios = listaTiposAreaDestinatarios;
	}

	/**
	 * @return valor de tipo List<DTOEstadosEntity> que esta contenido en la variable listaEstadosDestinatarios
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> getListaEstadosDestinatarios() {
		return listaEstadosDestinatarios;
	}

	/**
	 * @param listaEstadosDestinatarios : valor que se ingresa a la variable de tipo List<DTOEstadosEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaEstadosDestinatarios(
			List<DTOEstadosEntity> listaEstadosDestinatarios) {
		this.listaEstadosDestinatarios = listaEstadosDestinatarios;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaAreasDestinatarios
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> getListaAreasDestinatarios() {
		return listaAreasDestinatarios;
	}

	/**
	 * @param listaAreasDestinatarios : valor que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaAreasDestinatarios(
			List<DTOCAreaEntity> listaAreasDestinatarios) {
		this.listaAreasDestinatarios = listaAreasDestinatarios;
	}

	/**
	 * @return valor de tipo List<DTOCTipoAreaEntity> que esta contenido en la variable listaTiposAreaCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCTipoAreaEntity> getListaTiposAreaCCP() {
		return listaTiposAreaCCP;
	}

	/**
	 * @param listaTiposAreaCCP : valor que se ingresa a la variable de tipo List<DTOCTipoAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaTiposAreaCCP(List<DTOCTipoAreaEntity> listaTiposAreaCCP) {
		this.listaTiposAreaCCP = listaTiposAreaCCP;
	}

	/**
	 * @return valor de tipo List<DTOEstadosEntity> que esta contenido en la variable listaEstadosCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstadosEntity> getListaEstadosCCP() {
		return listaEstadosCCP;
	}

	/**
	 * @param listaEstadosCCP : valor que se ingresa a la variable de tipo List<DTOEstadosEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaEstadosCCP(List<DTOEstadosEntity> listaEstadosCCP) {
		this.listaEstadosCCP = listaEstadosCCP;
	}

	/**
	 * @return valor de tipo List<DTOCAreaEntity> que esta contenido en la variable listaAreasCCP
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOCAreaEntity> getListaAreasCCP() {
		return listaAreasCCP;
	}

	/**
	 * @param listaAreasCCP : valor que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setListaAreasCCP(List<DTOCAreaEntity> listaAreasCCP) {
		this.listaAreasCCP = listaAreasCCP;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personasBusquedasEncontradas
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonasBusquedasEncontradas() {
		return personasBusquedasEncontradas;
	}

	/**
	 * @param personasBusquedasEncontradas : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setPersonasBusquedasEncontradas(
			List<DTOEstructuraAreasEntity> personasBusquedasEncontradas) {
		this.personasBusquedasEncontradas = personasBusquedasEncontradas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable remitentesSeleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getRemitentesSeleccionados() {
		return remitentesSeleccionados;
	}

	/**
	 * @param remitentesSeleccionados : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setRemitentesSeleccionados(
			List<DTOEstructuraAreasEntity> remitentesSeleccionados) {
		this.remitentesSeleccionados = remitentesSeleccionados;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable destinatariosSeleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getDestinatariosSeleccionados() {
		return destinatariosSeleccionados;
	}

	/**
	 * @param destinatariosSeleccionados : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setDestinatariosSeleccionados(
			List<DTOEstructuraAreasEntity> destinatariosSeleccionados) {
		this.destinatariosSeleccionados = destinatariosSeleccionados;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable ccpSeleccionados
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getCcpSeleccionados() {
		return ccpSeleccionados;
	}

	/**
	 * @param ccpSeleccionados : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setCcpSeleccionados(List<DTOEstructuraAreasEntity> ccpSeleccionados) {
		this.ccpSeleccionados = ccpSeleccionados;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable remitentes
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getRemitentes() {
		return remitentes;
	}

	/**
	 * @param remitentes : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setRemitentes(List<DTOEstructuraAreasEntity> remitentes) {
		this.remitentes = remitentes;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable destinatarios
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setDestinatarios(List<DTOEstructuraAreasEntity> destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable ccp
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public List<DTOEstructuraAreasEntity> getCcp() {
		return ccp;
	}

	/**
	 * @param ccp : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 18/01/2018
	 */
	public void setCcp(List<DTOEstructuraAreasEntity> ccp) {
		this.ccp = ccp;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable tipoCapturaDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public Integer getTipoCapturaDocumento() {
		return tipoCapturaDocumento;
	}

	/**
	 * @param tipoCapturaDocumento : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public void setTipoCapturaDocumento(Integer tipoCapturaDocumento) {
		this.tipoCapturaDocumento = tipoCapturaDocumento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable hyperLinkWord
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public String getHyperLinkWord() {
		return hyperLinkWord;
	}

	/**
	 * @param hyperLinkWord : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public void setHyperLinkWord(String hyperLinkWord) {
		this.hyperLinkWord = hyperLinkWord;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreTemporalDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public String getNombreTemporalDocumento() {
		return nombreTemporalDocumento;
	}

	/**
	 * @param nombreTemporalDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public void setNombreTemporalDocumento(String nombreTemporalDocumento) {
		this.nombreTemporalDocumento = nombreTemporalDocumento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreOriginalPdf
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public String getNombreOriginalPdf() {
		return nombreOriginalPdf;
	}

	/**
	 * @param nombreOriginalPdf : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 19/01/2018
	 */
	public void setNombreOriginalPdf(String nombreOriginalPdf) {
		this.nombreOriginalPdf = nombreOriginalPdf;

	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroDocumento
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 22/01/2018
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Roberto Shirásago Domínguez
	 * @since 22/01/2018
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable busquedaPorArea
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public boolean isBusquedaPorArea() {
		return busquedaPorArea;
	}

	/**
	 * @param busquedaPorArea : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public void setBusquedaPorArea(boolean busquedaPorArea) {
		this.busquedaPorArea = busquedaPorArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreAreaDestinatario
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public String getNombreAreaDestinatario() {
		return nombreAreaDestinatario;
	}

	/**
	 * @param nombreAreaDestinatario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 07/03/2018
	 */
	public void setNombreAreaDestinatario(String nombreAreaDestinatario) {
		this.nombreAreaDestinatario = nombreAreaDestinatario;
	}

	/**
	 * @return Lista de tipo DTOAreaEntity que esta contenido en la variable areasBusquedaEncontradas
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public List<DTOCAreaEntity> getAreasBusquedaEncontradas() {
		return areasBusquedaEncontradas;
	}

	/**
	 * @param areasBusquedaEncontradas : Lista que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public void setAreasBusquedaEncontradas(
			List<DTOCAreaEntity> areasBusquedaEncontradas) {
		this.areasBusquedaEncontradas = areasBusquedaEncontradas;
	}

	/**
	 * @return Lista de tipo DTOCAreaEntity que esta contenido en la variable areasDestinatariosSeleccionados
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public List<DTOCAreaEntity> getAreasDestinatariosSeleccionados() {
		return areasDestinatariosSeleccionados;
	}

	/**
	 * @param areasDestinatariosSeleccionados : Lista que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 09/03/2018
	 */
	public void setAreasDestinatariosSeleccionados(
			List<DTOCAreaEntity> areasDestinatariosSeleccionados) {
		this.areasDestinatariosSeleccionados = areasDestinatariosSeleccionados;
	}

	/**
	 * @return Lista de tipo DTOCAreaEntity que esta contenido en la variable destinatariosComoArea
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 13/03/2018
	 */
	public List<DTOCAreaEntity> getDestinatariosComoArea() {
		return destinatariosComoArea;
	}

	/**
	 * @param destinatariosComoArea : Lista que se ingresa a la variable de tipo List<DTOCAreaEntity>
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 13/03/2018
	 */
	public void setDestinatariosComoArea(List<DTOCAreaEntity> destinatariosComoArea) {
		this.destinatariosComoArea = destinatariosComoArea;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumentoRespondido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdDocumentoRespondido() {
		return idDocumentoRespondido;
	}

	/**
	 * @param idDocumentoRespondido : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdDocumentoRespondido(Integer idDocumentoRespondido) {
		this.idDocumentoRespondido = idDocumentoRespondido;
	}

	/**
	 * @return Lista tipo DTODocumentoAnexoHelper que esta contenida en la variable anexos
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 22/03/2018
	 */
	public List<DTODocumentoAnexoHelper> getAnexos() {
		return anexos;
	}

	/**
	 * @param anexos : Lista de tipo DTODocumentoAnexoHelper que se ingresa 
	 *
	 * @author Giovanni Hernández Alonso
	 * @since 22/03/2018
	 */
	public void setAnexos(List<DTODocumentoAnexoHelper> anexos) {
		this.anexos = anexos;
	}

	public String getNombreOriginalPdfAux() {
		return nombreOriginalPdfAux;
	}

	public void setNombreOriginalPdfAux(String nombreOriginalPdfAux) {
		this.nombreOriginalPdfAux = nombreOriginalPdfAux;
	}

}