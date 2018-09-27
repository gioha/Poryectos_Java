/**
 * @(#)MBBandejaEntrada.java 29/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaEAtencionInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaECCPInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaEInfoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaERecibidosInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaEntradaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.dto.db.DTOBandejaEAtencionEntity;
import mx.ine.gestion.dto.db.DTOBandejaECCPEntity;
import mx.ine.gestion.dto.db.DTOBandejaEInfoEntity;
import mx.ine.gestion.dto.db.DTOBandejaERecibidosEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOBandejaEAtencionHelper;
import mx.ine.gestion.dto.helper.DTOBandejaECCPHelper;
import mx.ine.gestion.dto.helper.DTOBandejaEInfoHelper;
import mx.ine.gestion.dto.helper.DTOBandejaERecibidosHelper;
import mx.ine.gestion.dto.helper.DTODetalleDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOResponderTurnadoHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHBandejaEntradaInterface;
import mx.ine.gestion.vh.inter.VHBandejaSeguimientoInterface;
import mx.ine.gestion.vh.inter.VHCapturaDocumentoInterface;
import mx.org.ine.servicios.utils.PaginarLazy;

/**
 * @author Homero Fidel Villanueva
 * @since 29/11/2017
 */
public class MBBandejaEntrada implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -4714185754810883823L;

	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;

	@Autowired
	@Qualifier("bsdBandejaEntrada")
	private transient BSDBandejaEntradaInterface bsdBandejaEntradaInterface;

	@Autowired
	@Qualifier("bsdBandejaERecibidos")
	private transient BSDBandejaERecibidosInterface bsdBandejaERecibidosInterface;

	@Autowired
	@Qualifier("bsdBandejaECCP")
	private transient BSDBandejaECCPInterface bsdBandejaECCPInterface;
	
	@Autowired
	@Qualifier("bsdBandejaEAtencion")
	private transient BSDBandejaEAtencionInterface bsdBandejaEAtencionInterface;

	@Autowired
	@Qualifier("bsdBandejaEInfo")
	private transient BSDBandejaEInfoInterface bsdBandejaEInfoInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdBorradorDocumentosInterface;
	
	@Autowired
	@Qualifier("vhBandejaEntrada")
	private transient VHBandejaEntradaInterface vhBandejaEntradaInterface;
	
	@Autowired
	@Qualifier("vhBandejaSeguimiento")
	private transient VHBandejaSeguimientoInterface vhBandejaSeguimientoInterface;
	
	@Autowired
	@Qualifier("vhCapturaDocumento")
	private transient VHCapturaDocumentoInterface vhCapturaDocumentoInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boBandejaBorradores")
	private transient BOBandejaBorradoresInterface boBorradorDocumentosInterface;
	
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private transient BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);

	/**
	 * Atributo que guarda al usuario que inició sesión
	 */
	DTOEstructuraAreasEntity usuario;

	/**
	 * Atributo que guarda la cuenta del usuario
	 */
	private String cuentaUsuario;

	/**
	 * Atributo que guarda la lista de los documentos recibidos
	 */
	private PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper> listaRecibidosLazy;

	/**
	 * Atributo que guarda la lista de los documentos recibidos
	 */
	private PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper> listaCCPLazy;
	
	/**
	 * Atributo que guarda la lista de los documentos recibidos
	 */
	private PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper> listaAtencionLazy;

	/**
	 * Atributo que guarda la lista de los documentos recibidos
	 */
	private PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper> listaInfoLazy;

	/**
	 * Atributo que guarda el documento recibido seleccionado
	 */
	private DTOBandejaERecibidosEntity recibidoSeleccionado;

	/**
	 * Atributo que guarda el documento atencion seleccionado
	 */
	private DTOBandejaEAtencionEntity atencionSeleccionado;

	/**
	 * Atributo que guarda el documento info seleccionado
	 */
	private DTOBandejaEInfoEntity infoSeleccionado;

	/**
	 * Atributo que guarda el documento CCP seleccionado
	 */
	private DTOBandejaECCPEntity ccpSeleccionado;
	
	private DTODocumentoEntity dtoDocumentoSeleccionado;

	/**
	 * Atributo que guarda los filtros necesarios para la consulta de lod
	 * documentos recibidos
	 */
	private DTOBandejaERecibidosEntity filtroRecibido;

	/**
	 * 
	 */
	private DTOBandejaERecibidosHelper filtrosRecibidosHelper;

	/**
	 * 
	 */
	private DTOBandejaECCPEntity filtroCCP;

	/**
	 * 
	 */
	private DTOBandejaECCPHelper filtrosCCPHelper;
	
	/**
	 * 
	 */
	private DTOBandejaEAtencionEntity filtroAtencion;

	/**
	 * 
	 */
	private DTOBandejaEAtencionHelper filtrosAtencionHelper;
	
	/**
	 * 
	 */
	private DTOBandejaEInfoEntity filtroInfo;

	/**
	 * 
	 */
	private DTOBandejaEInfoHelper filtrosInfoHelper;

	/**
	 * 
	 */
	private List<DTODocumentoAnexoEntity> listaAnexos;
	/**
	 * Atributo que guarda el nombre del documento seleccionado
	 */
	private String nombreDocumentoPDF;

	/**
	 * Atributo que guarda el que documento ha sido seleccionado. recibidos,
	 * atención, ccp, info
	 */
	private String indicaSeleccionado;

	/**
	 * Atributo que guarda la pestaña seleccionada
	 */
	private String pestanaSeleccionada;
	
	/**
	 * 
	 */
	private String tabSeleccionada;
	
	/**
	 * 
	 */
	private boolean ordenarPorNombre;
	
	/**
	 * 
	 */
	private boolean ordenarPorFecha;
	
	/**
	 * Atributo que guarda la cadena que serÃ¡ mostrada en el apartado del menÃº
	 * con su contador
	 */
	private Integer contBandeja;

	/**
	 * Atributo que guarda la cadena que serÃ¡ mostrada en el apartado del menÃº
	 * con su contador
	 */
	private Integer contEnviados;

	/**
	 * Atributo que guarda la cadena que será mostrada en el apartado del menú
	 * con su contador
	 */
	private Integer contBorradores;

	/**
	 * Atributo que guarda la cadena que será mostrada en el apartado del menú
	 * con su contador
	 */
	private Integer contHistorico;
	
	/**
	 * 
	 */
	private Integer atencionTurnado;
	
	/**
	 * 
	 */
	private Integer atencionTurnadoServer;
	
	/**
	 */
	private TreeNode listaHistoricoTurnado;
	
	/**
	 * 
	 */
	private String tipoRespuestaDocumento;
	
	/**
	 * 
	 */
	private String comentarioRespuesta;
	
	/**
	 * 
	 */
	private Integer idDocumentoSeleccionado;
	
	/**
	 * 
	 */
	private Integer idHistoricoRecepcion;
	
	/**
	 * 
	 */
	private String nombreOriginalAdjunto;
	
	/**
	 * 
	 */
	private DTOResponderTurnadoHelper helperResponder;
	
	/**
	 * Atributo utilizado en el dialog_enviar_fv. Sirve para indicar que vista
	 * de busqueda se mostrará. Si es true muestra el outputpanel para buscar
	 * personas. Si es false muestra el outputpanel para buscar titulares.
	 */
	private boolean muestraPersonas;
	
	/**
	 * Atributo que guarda una cadena para buscar coincidencias en la
	 * descripcion y siglas del área de los titulares.
	 */
	private String coincidenciaTitulares;
	
	/**
	 * Atributo que guarda una cadena para buscar coincidencias en el nombre o
	 * apellidos de las personas por área.
	 */
	private String coincidenciaPersonas;
	
	/**
	 * Lista de Estructuras personas
	 */
	private List<DTOEstructuraAreasEntity> listaEstPersonas;
	
	/**
	 * Lista de Estructuras personas que guarda registros Temporalmente
	 */
	private List<DTOEstructuraAreasEntity> listaEstPersonasTemp;

	/**
	 * Lista de Estructuras de personas seleccionadas por el usuario y que serán
	 * guardadas en el servidor.
	 */
	private List<DTOEstructuraAreasEntity> listaEstPersonasSeleccionadas;

	/**
	 * Lista de estructura de titulares
	 */
	private List<DTOEstructuraAreasEntity> listaEstTitulares;

	/**
	 * Lista de estructura de titulares que guardan registros temporalmente
	 */
	private List<DTOEstructuraAreasEntity> listaEstTitularesTemp;

	/**
	 * Lista de estructura de titulares seleccionados y que serán guardados en
	 * el servidor
	 */
	private List<DTOEstructuraAreasEntity> listaEstTitularesSeleccionados;
	
	private List<DTOInstruccionesEntity> listaInstrucciones;
	
	/**
	 * Lista donde se guardan los remitentes del documento seleccionado
	 */
	private List<DTOEstructuraAreasEntity> personasRemitentes;
	
	/**
	 * Lista donde se guardan los CCP del documento seleccionado
	 */
	private List<DTOEstructuraAreasEntity> personasCCP;
	
	/**
	 * Lista donde se guardan los destinatarios del documento seleccionado
	 */
	private List<DTODocumentoDestinatarioEntity> destinatarios;
	
	/**
	 * 
	 */
	private DTODetalleDocumentoHelper detalleDocumentoHelper;
	
	/**
	 * Lista que guarda todos los comentarios leidos de un determinado borrador.
	 */
	private List<DTOComentariosNoLeidos> listaComentariosLeidos;

	/**
	 * Lista que guarda todos los comentarios no leidos de un determinado
	 * borrador.
	 */
	private List<DTOComentariosNoLeidos> listaComentariosNoLeidos;

	// ------------------------ METÓDOS ------------------------ //

	/**
	 * Método para inicializar las variables necesarias para mostrar la pantalla
	 * cuando se entra al módulo.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/11/2017
	 */
	public void iniciar() {
		//Se obtiene la estrutura de la persona que inició sesión
		cuentaUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(cuentaUsuario);
		
		//Se actualizan los contadores del menú izquierdo
		consultarContadoresMenu();
		
		//Se inicializa el helper necesario para realizar la respuesta al turnado
		helperResponder = new DTOResponderTurnadoHelper();
		helperResponder.setPersonaComento(usuario);
		
		ordenarPorNombre = true;
		ordenarPorFecha = true;
		tipoRespuestaDocumento = "1";
		
		detalleDocumentoHelper = new DTODetalleDocumentoHelper();
		
		if(usuario.getVerVersionT() != null && usuario.getVerVersionT() == 1){
			seleccionarPestanaPrincipal();
			//tabSeleccionada = Utilidades.mensajeProperties("mensaje_titulo_recibidos");
			
		}else{
			seleccionarPestanaTurnados();
			//tabSeleccionada = Utilidades.mensajeProperties("mensaje_titulo_atencion");
		}
		
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesSeguimiento();
	}
	
	/**
	 * Método que abre el dialog para mostrar el detalle del documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 25/04/2018
	 */
	public void mostrarDialogDetalle(){
		RequestContext.getCurrentInstance().execute("PF('dialogDetalleDoc').show()");
	}
	
	/**
	 * Método que abre el dialog para ver los comentarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 25/04/2018
	 */
	public void mostrarDialogComentarios(){
		obtenerComentarios();
		RequestContext.getCurrentInstance().execute("PF('dialog_revisar_comentarios').show()");
	}
	
	/**
	 * Método que busca los comentarios leídos y no leídos de el borrador
	 * seleccionado.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerComentarios() {
		verInicio();
		
		if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && recibidoSeleccionado != null){
			try {
				this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(recibidoSeleccionado.getDocumento(),usuario);
				this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(recibidoSeleccionado.getDocumento(),usuario);
			} catch (Exception e) {
		
				log.error("<=================== Error al cargar la lista de comentarios no leidos del documento borrador con Id: " + recibidoSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		
		}else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("ccp_seleccionado")) && ccpSeleccionado != null){
			try {
				this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(ccpSeleccionado.getDocumento(),usuario);
				this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(ccpSeleccionado.getDocumento(),usuario);
			} catch (Exception e) {
		
				log.error("<=================== Error al cargar la lista de comentarios no leidos del documento borrador con Id: " + ccpSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		
		} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && atencionSeleccionado != null){
			try {
				this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(atencionSeleccionado.getDocumento(),usuario);
				this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(atencionSeleccionado.getDocumento(),usuario);
			} catch (Exception e) {
		
				log.error("<=================== Error al cargar la lista de comentarios no leidos del documento borrador con Id: " + atencionSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		
		}else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("info_seleccionado")) && infoSeleccionado != null){
			try {
				this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(infoSeleccionado.getDocumento(),usuario);
				this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(infoSeleccionado.getDocumento(),usuario);
			} catch (Exception e) {
		
				log.error("<=================== Error al cargar la lista de comentarios no leidos del documento borrador con Id: " + infoSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}
	
	/**
	 * Método que cambia el estatus de los comentarios ya leídos a 0.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void leerComentarios() {
			try {
				
				this.bsdBandejaSeguimientoInterface.leerComentarios(listaComentariosNoLeidos);

			} catch (Exception e) {

//				log.error("<=================== Error al actualizar los comentarios del Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: leerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
	}
	
	/**
	 * Método que obtiene el detalle del documento seleccionado, como son el remitente
	 * @param documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void obtenerDetalleDocumento(DTODocumentoEntity documento){
		//Consulta de Remitentes
		try {
			detalleDocumentoHelper.setListaRemitentes(bsdBandejaSeguimientoInterface.obtenerRemitentes(documento));
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerDetalleDocumento()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de personas remitentes de la BD para el documento seleccionado. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		//Consulta de CCP
		try {
			detalleDocumentoHelper.setListaCCP(bsdBandejaSeguimientoInterface.obtenerPersonasCCP(documento));
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerDetalleDocumento()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de personas CCP de la BD para el documento seleccionado. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		//Consulta de destinatarios
		try {
			detalleDocumentoHelper.setListaDestinatarios(bsdBandejaSeguimientoInterface.obtenerDestinatarios(documento));
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerDetalleDocumento()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de personas Destinatarias de la BD para el documento seleccionado. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}
	
	public void verDetalleHistorialCreacion(){
		detalleDocumentoHelper.setTituloDetalle("Detalle de verDetalleHistorialCreacion");
		detalleDocumentoHelper.setTipoDetalle("HistoricoCreacion");
	}
	
	public void verDetalleHistorialTurndo(){
		detalleDocumentoHelper.setTituloDetalle("Detalle de verDetalleHistorialTurndo");
		detalleDocumentoHelper.setTipoDetalle("HistoricoTurnado");
	}
	
	public void verDetalleComentarios(){
		detalleDocumentoHelper.setTituloDetalle("Detalle de verDetalleComentarios");
		detalleDocumentoHelper.setTipoDetalle("Comentarios");
		
	}
	
	public void verInicio(){
		detalleDocumentoHelper.setTituloDetalle("Detalle de documento");
		detalleDocumentoHelper.setTipoDetalle("Inicio");
		
	}
	
	/**
	 * Método que borra los datos de la pantalla del dialog responder
	 *
	 * @author Homero Fidel Villanueva
	 * @since 25/04/2018
	 */
	public void limpiarDialogResponder(){
		helperResponder = new DTOResponderTurnadoHelper();
		nombreOriginalAdjunto = null;
	}

	/**
	 * Método que guarda la respuesta del turnado que el usuario realizó
	 *
	 * @author Homero Fidel Villanueva
	 * @since 25/04/2018
	 */
	public void responderTurnado(){
		if(boBandejaSeguimientoInterface.tieneRespuesta(helperResponder) ){
			if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && recibidoSeleccionado != null){

				try {
	
					bsdBandejaERecibidosInterface.responderTurnado(usuario, recibidoSeleccionado, helperResponder);
					
					RequestContext.getCurrentInstance().execute("PF('dialogResponder').hide()");
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ÉXITO", "El documento se respondió con éxito"));
					
				} catch (Exception e) {
	
					Utilidades.enviaMensajeGeneral(null, 
							"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
							FacesMessage.SEVERITY_WARN);
					
	//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
	//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
	//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
	//				logger.error("", e);
				}
			
			} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && atencionSeleccionado != null){
				//listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(atencionSeleccionado.getDocumento());
				try {
	
					bsdBandejaEAtencionInterface.responderTurnado(usuario, atencionSeleccionado, helperResponder);
					
					RequestContext.getCurrentInstance().execute("PF('dialogResponder').hide()");
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ÉXITO", "El documento se respondió con éxito"));
					
				} catch (Exception e) {
	
					Utilidades.enviaMensajeGeneral(null, 
							"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
							FacesMessage.SEVERITY_WARN);
					
	//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
	//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
	//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
	//				logger.error("", e);
				}
				
			}
			comentarioRespuesta = "";
		}else{
			Utilidades.enviaMensajeGeneral(null, 
					"Debe realizar una respuesta","",
					FacesMessage.SEVERITY_WARN);
		}
	}
	
	/**
	 * Método que guarda el archivo adjunto para la respuesta del turnado
	 * 
	 * @param archivo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 25/04/2018
	 */
	public void adjuntarDocumento(FileUploadEvent archivo) {
		
		try {

//			nombreOriginalAdjunto = archivo.getFile().getFileName();
//			vhBandejaEntradaInterface.generarTemporalArchivoAdjunto(helperResponder, archivo);
			
			boolean archivoValido = vhCapturaDocumentoInterface.validaMetadatosArchivoAnexo(archivo, "anexo");
			if(archivoValido){
				nombreOriginalAdjunto = archivo.getFile().getFileName();
				vhBandejaEntradaInterface.generarTemporalArchivoAdjunto(helperResponder, archivo);
			}else{
		        Utilidades.enviaMensajeGeneral(null, 
		        		"Formato inválido, favor de verificar el archivo","",
						FacesMessage.SEVERITY_WARN);
			}
			
			
		} catch (Exception e) {

			Utilidades.enviaMensajeGeneral(null, 
					"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
					FacesMessage.SEVERITY_WARN);
			
//			logger.error("<===================== ERROR!! adjuntar el documento!!!");
//			logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//			logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//			logger.error("", e);
		}
	}
	
	public void reasignar(){
		if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && recibidoSeleccionado != null){
			
			try {

				bsdBandejaERecibidosInterface.reasignarDocumento(listaEstTitularesSeleccionados, recibidoSeleccionado, usuario);
				
			} catch (Exception e) {

				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//				logger.error("", e);
			}

		} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && atencionSeleccionado != null){
			try {

				bsdBandejaEAtencionInterface.reasignarDocumento(listaEstTitularesSeleccionados, atencionSeleccionado, usuario);
				
			} catch (Exception e) {

				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al capturar el documento, vuelva a intentar, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//				logger.error("", e);
			}
		}
		RequestContext.getCurrentInstance().execute("PF('dialogReasignar').hide()");
		guardarAtencion();
	}
	
	public void obtenerInstrucciones(){
		if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && recibidoSeleccionado != null){
			
			try {
				listaInstrucciones = bsdBandejaERecibidosInterface.obtenerInstrucciones(recibidoSeleccionado);
				
			} catch (Exception e) {

				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al obtener las instrucciones, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//				logger.error("", e);
			}

		} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && atencionSeleccionado != null){
			try {
				listaInstrucciones = bsdBandejaEAtencionInterface.obtenerInstrucciones(atencionSeleccionado);
				
			} catch (Exception e) {

				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al obtener las instrucciones, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//				logger.error("", e);
			}
		}else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("info_seleccionado")) && infoSeleccionado != null){
			try {
				listaInstrucciones = bsdBandejaEInfoInterface.obtenerInstrucciones(infoSeleccionado);
				
			} catch (Exception e) {

				Utilidades.enviaMensajeGeneral(null, 
						"Ocurrio un error al obtener las instrucciones, en caso de continuar el error favor de reportar al CAU.","",
						FacesMessage.SEVERITY_WARN);
				
//				logger.error("<===================== ERROR!! adjuntar el documento!!!");
//				logger.error("<=================== Clase: MBCapturaDocumento , Método: adjuntarDocumento");
//				logger.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//				logger.error("", e);
			}
		}
	}
	
	/**
	 * Método que elimina una área(titular). Este método se utiliza cuando el usuario
	 * selecciono un área para enviar a firmar o validar y decide ya no
	 * enviarlo
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarTitular(DTOEstructuraAreasEntity persona) {
		if (persona != null && listaEstTitularesSeleccionados != null && listaEstTitularesSeleccionados.size() > 0) {
			listaEstTitularesSeleccionados.remove(persona);
			listaEstTitularesTemp.remove(persona);
		}
	}
	
	/**
	 * Método que guarda los registros temporales de las estructuras personas en
	 * la lista de personas seleccionadas para posteriormente ser guardados en
	 * el servidor.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void cambiarListaTitularesTemp() {
		log.info(this.getClass().getName() + " Se inicia el método cambiarListaTitularesTemp()");

		if((((usuario.getVerVersionT() == null)|| (usuario.getVerVersionT() != 1) || 
				(!boBorradorDocumentosInterface.revisarPersonaEstaLista(listaEstTitularesTemp, usuario))))){
			
			if (listaEstTitularesSeleccionados != null && listaEstTitularesTemp != null && listaEstTitularesSeleccionados.size() > 0) {

				for (DTOEstructuraAreasEntity elemento : listaEstTitularesTemp) {
					if (!listaEstTitularesSeleccionados.contains(elemento)) {
						listaEstTitularesSeleccionados.add(elemento);
					}
				}

			} else if (listaEstTitularesTemp == null || listaEstTitularesTemp.size() == 0) {
	
				vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_advertencia"), 
														Utilidades.mensajeProperties("titulo_growl_advertencia"), 
														Utilidades.mensajeProperties("mensaje_no_seleccion_titulares"));
			} else {
				listaEstTitularesSeleccionados = listaEstTitularesTemp;
			}
		
		} else {
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_advertencia"), 
													Utilidades.mensajeProperties("titulo_growl_advertencia"), 
													Utilidades.mensajeProperties("mensaje_advertencua_titular_seleccionado"));			
		}
		log.info(this.getClass().getName()+ " Se termina el método cambiarListaTitularesTemp()");
	}

	
	/**
	 * Método que busca los titulares que coincidan coincidan en la descripción
	 * de área o siglas con el atributo "coincidenciaPersonas".
	 * 
	 * En caso de que el atributo "coincidenciaPersonas" sea null muestra todos
	 * los titulares de las áreas.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void buscarTitulares() {
		log.info(this.getClass().getName()+ " Se inicia el método buscarTitulares()");

		try {
			
			listaEstTitulares = this.bsdBorradorDocumentosInterface.consultarEstructurasTitulares(coincidenciaTitulares);

		} catch (Exception e) {

			log.error("<=================== Error al buscar los titulares ");
			log.error("<=================== Clase: MBAdministradorGestion , Método: cargaValoresRegistradoEnGestionParaUsuarioLogueado");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		

		if (listaEstTitulares.size() < 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "INFORMATIVO",
					"Su busqueda no encontró resultados"));
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método buscarTitulares()");
	}
	
	/**
	 * Método que elimina una persona. Este método se utiliza cuando el usuario
	 * selecciono una persona para enviar a firmar o validar y decide ya no
	 * enviarlo
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarPersona(DTOEstructuraAreasEntity persona) {
		if (persona != null && listaEstPersonasSeleccionadas != null && listaEstPersonasSeleccionadas.size() > 0) {
			listaEstPersonasSeleccionadas.remove(persona);
			listaEstPersonasTemp.remove(persona);
		}
	}
	
	/**
	 * Método que busca las personas que coincidan con el Id área y tipo área de
	 * la persona que inició sesión y que coincida en el nombre o apellido con
	 * el atributo "coincidenciaPersonas".
	 * 
	 * En caso de que el atributo "coincidenciaPersonas" sea null muestra todos
	 * los registros que coincidan en el Id área y tipo área de la persona que
	 * inició sesión
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void buscarPersonas() {
		log.info(this.getClass().getName()+ " Se inicia el método buscarPersonas()");
		try {
			
			this.listaEstPersonas = bsdBorradorDocumentosInterface.consultarPersonasXArea(usuario, coincidenciaPersonas);

		} catch (Exception e) {

			log.error("<=================== Error al buscar personas de la misma área del usuario logueado");
			log.error("<=================== Clase: MBBandejaBorradores , Método: buscarPersonas");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		if (listaEstPersonas.size() < 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "INFORMATIVO",
					"Su busqueda no encontró resultados"));
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método buscarPersonas()");
	}
	
	/**
	 * Método que muestra el outputpanel de los titulares de área.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void cambiarListaPersonasAAreas() {
		muestraPersonas = false;
	}

	/**
	 * Método que muestra el outputpanel de las personas de la misma área.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void cambiarListaAreasAPersonas() {
		muestraPersonas = true;
	}
	
	public void registrarAtencion(){
		if(atencionTurnado != null && atencionTurnado.equals(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_concluido")))){
			atencionTurnadoServer = new Integer(atencionTurnado.intValue());
			RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionConcluido').show()");
			
		}else if(atencionTurnado != null && atencionTurnado.equals(Integer.valueOf(Utilidades.mensajeProperties("id_estatus_reasignado")))){
		
			RequestContext.getCurrentInstance().execute("PF('dialogReasignar').show()");
			
		} else {
			guardarAtencion();
		}
	}
	
	public void confirmarConcluirDocumento(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionConcluido').hide()");
		atencionTurnado = atencionTurnadoServer;
		guardarAtencion();
	}
	
	public void anularConcluirDocumento(){
		RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionConcluido').hide()");
		RequestContext.getCurrentInstance().execute("PF('dialogRegistrarAtencion').show()");
	}
	
	public void guardarAtencion(){
		//bsdBandejaERecibidosInterface.registrarAtencion(recibidoSeleccionado, usuario, atencionTurnado,"");
		if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && recibidoSeleccionado != null){
			
			
			try {
				bsdBandejaERecibidosInterface.registrarAtencion(recibidoSeleccionado, usuario, atencionTurnado, "");
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + recibidoSeleccionado.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}

		} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && atencionSeleccionado != null){
			//listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(atencionSeleccionado.getDocumento());
			try {
				bsdBandejaEAtencionInterface.registrarAtencion(atencionSeleccionado, usuario, atencionTurnado, "");
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + atencionSeleccionado.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
		}
		
		atencionTurnado = null;
		
		RequestContext.getCurrentInstance().execute("PF('dialogRegistrarAtencion').hide()");
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ÉXITO", "Se guardó la atención correctamente"));
	}
	
	/**
	 * Método que abre el dialog para realizar la atención de un documento recibido o turnado.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/01/2018
	 */
	public void mostrarDialogRegistrarAtencion(){
		RequestContext.getCurrentInstance().execute("PF('dialogRegistrarAtencion').show()");
	}
	
	/**
	 * 
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/02/2018
	 */
	public void mostrarDialogHistoricoTurnado(){
		RequestContext.getCurrentInstance().execute("PF('dialogHistoricoTurnado').show()");
	}
	
	/**
	 * Método que muestra el dialog de Anexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogAnexos() {
		RequestContext.getCurrentInstance().execute("PF('dialogVerAnexosEntrada').show()");
	}
	
	
	/**
	 * Método que muestra el dialog para dar una respuesta al documento recibido.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogResponder() {
		RequestContext.getCurrentInstance().execute("PF('dialogResponder').show()");
	}
	
	/**
	 * Método que actualiza los contadores del menú de la Bandeja de
	 * Seguimiento.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarContadoresMenu() {

		contBandeja = bsdBandejaSeguimientoInterface.consultarNotificacionesBEntrada(usuario);

		contEnviados = bsdBandejaSeguimientoInterface.consultarNotificacionesBEnviados(usuario);

		contBorradores = bsdBandejaSeguimientoInterface.consultarNotificacionesBBorradores(usuario);

		//contHistorico = bsdBorradorDocumentosInterface.consultarNumeroHistoricos(usuario);
	}
	
	public void ordenarRecibidosPorNombre(){
		filtrosRecibidosHelper.ordenarPorNombre(ordenarPorNombre);
		listaRecibidosLazy = new PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper>("bsdBandejaERecibidos", filtrosRecibidosHelper);
		ordenarPorNombre = !ordenarPorNombre;
	}
	
	public void ordenarRecibidosPorFecha(){
		filtrosRecibidosHelper.ordenarPorFecha(ordenarPorFecha);
		listaRecibidosLazy = new PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper>("bsdBandejaERecibidos", filtrosRecibidosHelper);
		ordenarPorFecha = !ordenarPorFecha;
	}
	
	public void ordenarAtencionPorNombre(){
		filtrosAtencionHelper.ordenarPorNombre(ordenarPorNombre);
		listaAtencionLazy = new PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper>("bsdBandejaEAtencion", filtrosAtencionHelper);
		ordenarPorNombre = !ordenarPorNombre;
	}

	public void ordenarAtencionPorFecha(){
		filtrosAtencionHelper.ordenarPorFecha(ordenarPorFecha);
		listaAtencionLazy = new PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper>("bsdBandejaEAtencion", filtrosAtencionHelper);
		ordenarPorFecha = !ordenarPorFecha;
	}
	
	public void ordenarInfoPorNombre(){
		filtrosInfoHelper.ordenarPorNombre(ordenarPorNombre);
		listaInfoLazy= new PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper>("bsdBandejaEInfo", filtrosInfoHelper);
		ordenarPorNombre = !ordenarPorNombre;
	}

	public void ordenarInfoPorFecha(){
		filtrosInfoHelper.ordenarPorFecha(ordenarPorFecha);
		listaInfoLazy= new PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper>("bsdBandejaEInfo", filtrosInfoHelper);
		ordenarPorFecha = !ordenarPorFecha;
	}
	
	public void ordenarCCPPorNombre(){
		filtrosCCPHelper.ordenarPorNombre(ordenarPorNombre);
		listaCCPLazy = new PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper>("bsdBandejaECCP", filtrosCCPHelper);
		ordenarPorNombre = !ordenarPorNombre;
	}

	public void ordenarCCPPorFecha(){
		filtrosCCPHelper.ordenarPorFecha(ordenarPorFecha);
		listaCCPLazy = new PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper>("bsdBandejaECCP", filtrosCCPHelper);
		ordenarPorFecha = !ordenarPorFecha;
	}
	
	/**
	 * Método que actualiza los contadores de las notificaciones del menú
	 * principal.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/12/2017
	 */
	public void actualizarPantallaEntrada(){
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesSeguimiento();
		
//		MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//		menu.consultarMenu();
	}
	
	public void seleccionarTabAcordion(TabChangeEvent event){
		
		tabSeleccionada = event.getTab().getTitle();
	}

	
	
	/**
	 * Método que cambia la bandera "pestanaSeleccionada" para mostrar los
	 * documentos Recibidos y CCP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarPestanaPrincipal() {
		String tab = Utilidades.mensajeProperties("mensaje_titulo_ccp");
		indicaSeleccionado = null;
		recibidoSeleccionado = null;
		atencionSeleccionado = null;
		infoSeleccionado = null;
		
		if(pestanaSeleccionada == null || !pestanaSeleccionada.equals(Utilidades.mensajeProperties("mensaje_pestana_principal"))){
			pestanaSeleccionada = Utilidades.mensajeProperties("mensaje_pestana_principal");
		}
		
		consultarCCPLazy();
		if(usuario.getVerVersionT() != null && usuario.getVerVersionT() == 1){
			tab = Utilidades.mensajeProperties("mensaje_titulo_recibidos");
			consultarRecibidosLazy();
			
		}
		tabSeleccionada = tab;
		
	}

	/**
	 * Método que cambia la bandera "pestanaSeleccionada" para mostrar los
	 * documentos Atención e Info
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarPestanaTurnados() {
		
		indicaSeleccionado = null;
		recibidoSeleccionado = null;
		atencionSeleccionado = null;
		infoSeleccionado = null;
		ccpSeleccionado = null;
		
		if(pestanaSeleccionada == null || !pestanaSeleccionada.equals(Utilidades.mensajeProperties("mensaje_pestana_turnados"))){
			pestanaSeleccionada = Utilidades.mensajeProperties("mensaje_pestana_turnados");
			tabSeleccionada = Utilidades.mensajeProperties("mensaje_titulo_atencion");
		}
		consultarAtencionLazy();
		consultarInfoLazy();
	}

	/**
	 * Método que obtiene los documentos recibidos que copincidan con los
	 * filtros
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarRecibidosLazy() {
		filtroRecibido = new DTOBandejaERecibidosEntity();
		filtrosRecibidosHelper = new DTOBandejaERecibidosHelper();

		filtroRecibido.setIdPersona(usuario.getIdPersona());
		filtrosRecibidosHelper.getRecibidos().add(filtroRecibido);
		//ordenarRecibidosPorNombre();

		listaRecibidosLazy = new PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper>(
				"bsdBandejaERecibidos", filtrosRecibidosHelper);
	}

	/**
	 * Método que obtiene los documentos recibidos que copincidan con los
	 * filtros
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarCCPLazy() {
		filtroCCP = new DTOBandejaECCPEntity();
		filtrosCCPHelper = new DTOBandejaECCPHelper();

		filtroCCP.setIdPersona(usuario.getIdPersona());
		filtrosCCPHelper.getCcp().add(filtroCCP);

		listaCCPLazy = new PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper>(
				"bsdBandejaECCP", filtrosCCPHelper);
	}
	
	/**
	 * Método que obtiene los documentos recibidos que copincidan con los
	 * filtros
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarAtencionLazy() {
		filtroAtencion = new DTOBandejaEAtencionEntity();
		filtrosAtencionHelper = new DTOBandejaEAtencionHelper();

		filtroAtencion.setIdPersona(usuario.getIdPersona());
		filtrosAtencionHelper.getAtencion().add(filtroAtencion);
		//ordenarRecibidosPorNombre();

		listaAtencionLazy = new PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper>(
				"bsdBandejaEAtencion", filtrosAtencionHelper);
	}
	
	/**
	 * Método que obtiene los documentos recibidos que copincidan con los
	 * filtros
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void consultarInfoLazy() {
		filtroInfo = new DTOBandejaEInfoEntity();
		filtrosInfoHelper = new DTOBandejaEInfoHelper();

		filtroInfo.setIdPersona(usuario.getIdPersona());
		filtrosInfoHelper.getInfo().add(filtroInfo);
		//ordenarRecibidosPorNombre();

		listaInfoLazy = new PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper>(
				"bsdBandejaEInfo", filtrosInfoHelper);
	}
	
	public void obtenerAnexo(){
		
		if(indicaSeleccionado.equals(Utilidades.mensajeProperties("recibido_seleccionado")) && 
				recibidoSeleccionado != null && recibidoSeleccionado.getDocumento().getContieneAnexos()>0){
			
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(recibidoSeleccionado.getDocumento());
			
		}else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("ccp_seleccionado")) && 
				ccpSeleccionado != null && ccpSeleccionado.getDocumento().getContieneAnexos()>0){
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(ccpSeleccionado.getDocumento());
		} else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("atencion_seleccionado")) && 
				atencionSeleccionado != null && atencionSeleccionado.getDocumento().getContieneAnexos()>0){
			
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(atencionSeleccionado.getDocumento());
			
		}else if(indicaSeleccionado.equals(Utilidades.mensajeProperties("info_seleccionado")) && 
				infoSeleccionado != null && infoSeleccionado.getDocumento().getContieneAnexos()>0){
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(infoSeleccionado.getDocumento());
		}
	}

	/**
	 * Método el cual es llamado cuando se selecciona un documento recibido de
	 * la tabla
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarFilaRecibido(SelectEvent event) {
		DTOBandejaERecibidosEntity miRecibido = (DTOBandejaERecibidosEntity) event.getObject();
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miRecibido.getNoLeido())){
			try {
				bsdBandejaERecibidosInterface.notificacionBandejaRecibidos(usuario, miRecibido, false);
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miRecibido.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			//bsdBandejaEntradaInterface.actualizarDocumentoAEnterado(usuario, miRecibido.getDocumento());
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			consultarContadoresMenu();
			
//			MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//			menu.consultarMenu();
		}
		
		
		try {
			listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(miRecibido.getDocumento());
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miRecibido.getDocumento());
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		seleccionarRecibido(miRecibido);
	}

	/**
	 * Método el cual es llamado cuando se selecciona un documento recibido de
	 * la tabla
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarFilaCCP(SelectEvent event) {
		DTOBandejaECCPEntity miCCP = (DTOBandejaECCPEntity) event.getObject();
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miCCP.getNoLeido())){
			try {
				bsdBandejaECCPInterface.notificacionBandejaCCP(usuario, miCCP, false);
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miCCP.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			consultarContadoresMenu();
//			MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//			menu.consultarMenu();
		}
		seleccionarCCP(miCCP);
	}
	
	/**
	 * Método el cual es llamado cuando se selecciona un documento recibido de
	 * la tabla
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarFilaAtencion(SelectEvent event) {
		DTOBandejaEAtencionEntity miAtencion = (DTOBandejaEAtencionEntity) event.getObject();
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miAtencion.getNoLeido())){
//			bsdBandejaERecibidosInterface.notificacionBandejaRecibidos(usuario, miAtencion, false);
			try {
				bsdBandejaEAtencionInterface.notificacionBandejaAtencion(usuario, miAtencion, false);
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miAtencion.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			//bsdBandejaEntradaInterface.actualizarDocumentoAEnterado(usuario, miAtencion.getDocumento());
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			consultarContadoresMenu();
			
//			MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//			menu.consultarMenu();
		}
		
		try {
			listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(miAtencion.getDocumento());
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miAtencion.getDocumento());
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		seleccionarAtencion(miAtencion);
	}

	/**
	 * Método el cual es llamado cuando se selecciona un documento recibido de
	 * la tabla
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarFilaInfo(SelectEvent event) {
		DTOBandejaEInfoEntity miInfo = (DTOBandejaEInfoEntity) event.getObject();
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miInfo.getNoLeido())){
			//bsdBandejaECCPInterface.notificacionBandejaCCP(usuario, miInfo, false);
			
			try {
				bsdBandejaEInfoInterface.notificacionBandejaInfo(usuario, miInfo, false);
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miInfo.getDocumento());
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			consultarContadoresMenu();
//			MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//			menu.consultarMenu();
		}
		
		try {
			listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(miInfo.getDocumento());
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista del historico del documento: " + miInfo.getDocumento());
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		seleccionarInfo(miInfo);
	}


	/**
	 * Método que selecciona el documento recibido para mostrar sus atributos en
	 * la pantalla.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarRecibido(DTOBandejaERecibidosEntity recibido) {
		indicaSeleccionado = Utilidades.mensajeProperties("recibido_seleccionado");

		idDocumentoSeleccionado = recibido.getIdDocumento();
		idHistoricoRecepcion = recibido.getIdHistoricoRecep();
		recibidoSeleccionado = recibido;
		atencionSeleccionado = null;
		infoSeleccionado = null;
		ccpSeleccionado = null;
		dtoDocumentoSeleccionado = recibido.getDocumento();
		obtenerAnexo();
		obtenerComentarios();
		obtenerDetalleDocumento(recibido.getDocumento());

		setNombreDocumentoPDF(recibido.getIdDocumento() + "_" + recibido.getAnio() + ".pdf");
	}

	/**
	 * Método que selecciona el documento CCP para mostrar sus atributos en
	 * la pantalla.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarCCP(DTOBandejaECCPEntity ccp) {
		indicaSeleccionado = Utilidades.mensajeProperties("ccp_seleccionado");

		idDocumentoSeleccionado = ccp.getIdDocumento();
		recibidoSeleccionado = null;
		atencionSeleccionado = null;
		infoSeleccionado = null;
		ccpSeleccionado = ccp;
		dtoDocumentoSeleccionado = ccp.getDocumento();
		
		
		obtenerAnexo();
		obtenerComentarios();
		obtenerDetalleDocumento(ccp.getDocumento());
		

		setNombreDocumentoPDF(ccp.getIdDocumento() + "_" + ccp.getAnio() + ".pdf");
	}
	
	/**
	 * Método que selecciona el documento recibido para mostrar sus atributos en
	 * la pantalla.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarAtencion(DTOBandejaEAtencionEntity atencion) {
		indicaSeleccionado = Utilidades.mensajeProperties("atencion_seleccionado");

		idDocumentoSeleccionado = atencion.getIdDocumento();
		idHistoricoRecepcion = atencion.getIdHistoricoRecep();
		recibidoSeleccionado = null;
		atencionSeleccionado = atencion;
		infoSeleccionado = null;
		ccpSeleccionado = null;
		dtoDocumentoSeleccionado = atencion.getDocumento();
		
		obtenerAnexo();
		obtenerInstrucciones();
		obtenerComentarios();
		obtenerDetalleDocumento(atencion.getDocumento());

		setNombreDocumentoPDF(atencion.getIdDocumento() + "_" + atencion.getAnio() + ".pdf");
	}

	/**
	 * Método que selecciona el documento CCP para mostrar sus atributos en
	 * la pantalla.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void seleccionarInfo(DTOBandejaEInfoEntity info) {
		indicaSeleccionado = Utilidades.mensajeProperties("info_seleccionado");

		idDocumentoSeleccionado = info.getIdDocumento();
		recibidoSeleccionado = null;
		atencionSeleccionado = null;
		infoSeleccionado = info;
		ccpSeleccionado = null;
		dtoDocumentoSeleccionado = info.getDocumento();
		
		obtenerAnexo();
		obtenerInstrucciones();
		obtenerComentarios();
		obtenerDetalleDocumento(info.getDocumento());

		setNombreDocumentoPDF(info.getIdDocumento() + "_" + info.getAnio() + ".pdf");
	}

	// -------------------- GETTERS SETTERS -------------------- //

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public DTOEstructuraAreasEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo DTOBandejaERecibidosEntity que esta contenido en la
	 *         variable recibidoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public DTOBandejaERecibidosEntity getRecibidoSeleccionado() {
		return recibidoSeleccionado;
	}

	/**
	 * @param recibidoSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaERecibidosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public void setRecibidoSeleccionado(
			DTOBandejaERecibidosEntity recibidoSeleccionado) {
		this.recibidoSeleccionado = recibidoSeleccionado;
	}

	/**
	 * @return valor de tipo DTOBandejaEAtencionEntity que esta contenido en la
	 *         variable atencionSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public DTOBandejaEAtencionEntity getAtencionSeleccionado() {
		return atencionSeleccionado;
	}

	/**
	 * @param atencionSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaEAtencionEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public void setAtencionSeleccionado(
			DTOBandejaEAtencionEntity atencionSeleccionado) {
		this.atencionSeleccionado = atencionSeleccionado;
	}

	/**
	 * @return valor de tipo DTOBandejaEInfoEntity que esta contenido en la
	 *         variable infoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public DTOBandejaEInfoEntity getInfoSeleccionado() {
		return infoSeleccionado;
	}

	/**
	 * @param infoSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaEInfoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public void setInfoSeleccionado(DTOBandejaEInfoEntity infoSeleccionado) {
		this.infoSeleccionado = infoSeleccionado;
	}

	/**
	 * @return valor de tipo DTOBandejaECCPEntity que esta contenido en la
	 *         variable ccpSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public DTOBandejaECCPEntity getCcpSeleccionado() {
		return ccpSeleccionado;
	}

	/**
	 * @param ccpSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaECCPEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/12/2017
	 */
	public void setCcpSeleccionado(DTOBandejaECCPEntity ccpSeleccionado) {
		this.ccpSeleccionado = ccpSeleccionado;
	}

	/**
	 * @return valor de tipo
	 *         PaginarLazy<DTOBandejaERecibidosEntity,DTOBandejaERecibidosHelper
	 *         > que esta contenido en la variable listaRecibidosLazy
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper> getListaRecibidosLazy() {
		return listaRecibidosLazy;
	}

	/**
	 * @param listaRecibidosLazy
	 *            : valor que se ingresa a la variable de tipo
	 *            PaginarLazy<DTOBandejaERecibidosEntity
	 *            ,DTOBandejaERecibidosHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/12/2017
	 */
	public void setListaRecibidosLazy(
			PaginarLazy<DTOBandejaERecibidosEntity, DTOBandejaERecibidosHelper> listaRecibidosLazy) {
		this.listaRecibidosLazy = listaRecibidosLazy;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         cuentaUsuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public String getCuentaUsuario() {
		return cuentaUsuario;
	}

	/**
	 * @param cuentaUsuario
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public void setCuentaUsuario(String cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}

	/**
	 * @return valor de tipo DTOBandejaERecibidosEntity que esta contenido en la
	 *         variable filtroRecibido
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public DTOBandejaERecibidosEntity getFiltroRecibido() {
		return filtroRecibido;
	}

	/**
	 * @param filtroRecibido
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaERecibidosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public void setFiltroRecibido(DTOBandejaERecibidosEntity filtroRecibido) {
		this.filtroRecibido = filtroRecibido;
	}

	/**
	 * @return valor de tipo DTOBandejaERecibidosHelper que esta contenido en la
	 *         variable filtrosRecibidosHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public DTOBandejaERecibidosHelper getFiltrosRecibidosHelper() {
		return filtrosRecibidosHelper;
	}

	/**
	 * @param filtrosRecibidosHelper
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaERecibidosHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/12/2017
	 */
	public void setFiltrosRecibidosHelper(
			DTOBandejaERecibidosHelper filtrosRecibidosHelper) {
		this.filtrosRecibidosHelper = filtrosRecibidosHelper;
	}

	/**
	 * @return valor de tipo
	 *         PaginarLazy<DTOBandejaECCPEntity,DTOBandejaECCPHelper> que esta
	 *         contenido en la variable listaCCPLazy
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper> getListaCCPLazy() {
		return listaCCPLazy;
	}

	/**
	 * @param listaCCPLazy
	 *            : valor que se ingresa a la variable de tipo
	 *            PaginarLazy<DTOBandejaECCPEntity,DTOBandejaECCPHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public void setListaCCPLazy(
			PaginarLazy<DTOBandejaECCPEntity, DTOBandejaECCPHelper> listaCCPLazy) {
		this.listaCCPLazy = listaCCPLazy;
	}

	/**
	 * @return valor de tipo DTOBandejaECCPEntity que esta contenido en la
	 *         variable filtroCCP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public DTOBandejaECCPEntity getFiltroCCP() {
		return filtroCCP;
	}

	/**
	 * @param filtroCCP
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaECCPEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public void setFiltroCCP(DTOBandejaECCPEntity filtroCCP) {
		this.filtroCCP = filtroCCP;
	}

	/**
	 * @return valor de tipo DTOBandejaECCPHelper que esta contenido en la
	 *         variable filtrosCCPHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public DTOBandejaECCPHelper getFiltrosCCPHelper() {
		return filtrosCCPHelper;
	}

	/**
	 * @param filtrosCCPHelper
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBandejaECCPHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public void setFiltrosCCPHelper(DTOBandejaECCPHelper filtrosCCPHelper) {
		this.filtrosCCPHelper = filtrosCCPHelper;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         nombreDocumentoPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public String getNombreDocumentoPDF() {
		return nombreDocumentoPDF;
	}

	/**
	 * @param nombreDocumentoPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public void setNombreDocumentoPDF(String nombreDocumentoPDF) {
		this.nombreDocumentoPDF = nombreDocumentoPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         indicaSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public String getIndicaSeleccionado() {
		return indicaSeleccionado;
	}

	/**
	 * @param indicaSeleccionado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/12/2017
	 */
	public void setIndicaSeleccionado(String indicaSeleccionado) {
		this.indicaSeleccionado = indicaSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         pestanaSeleccionada
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public String getPestanaSeleccionada() {
		return pestanaSeleccionada;
	}

	/**
	 * @param pestanaSeleccionada
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/12/2017
	 */
	public void setPestanaSeleccionada(String pestanaSeleccionada) {
		this.pestanaSeleccionada = pestanaSeleccionada;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tabSeleccionada
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/12/2017
	 */
	public String getTabSeleccionada() {
		return tabSeleccionada;
	}

	/**
	 * @param tabSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/12/2017
	 */
	public void setTabSeleccionada(String tabSeleccionada) {
		this.tabSeleccionada = tabSeleccionada;
	}

	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en la variable listaAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/01/2018
	 */
	public List<DTODocumentoAnexoEntity> getListaAnexos() {
		return listaAnexos;
	}

	/**
	 * @param listaAnexos : valor que se ingresa a la variable de tipo List<DTODocumentoAnexoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/01/2018
	 */
	public void setListaAnexos(List<DTODocumentoAnexoEntity> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOBandejaEAtencionEntity,DTOBandejaEAtencionHelper> que esta contenido en la variable listaAtencionLazy
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper> getListaAtencionLazy() {
		return listaAtencionLazy;
	}

	/**
	 * @param listaAtencionLazy : valor que se ingresa a la variable de tipo PaginarLazy<DTOBandejaEAtencionEntity,DTOBandejaEAtencionHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setListaAtencionLazy(
			PaginarLazy<DTOBandejaEAtencionEntity, DTOBandejaEAtencionHelper> listaAtencionLazy) {
		this.listaAtencionLazy = listaAtencionLazy;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOBandejaEInfoEntity,DTOBandejaEInfoHelper> que esta contenido en la variable listaInfoLazy
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper> getListaInfoLazy() {
		return listaInfoLazy;
	}

	/**
	 * @param listaInfoLazy : valor que se ingresa a la variable de tipo PaginarLazy<DTOBandejaEInfoEntity,DTOBandejaEInfoHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setListaInfoLazy(
			PaginarLazy<DTOBandejaEInfoEntity, DTOBandejaEInfoHelper> listaInfoLazy) {
		this.listaInfoLazy = listaInfoLazy;
	}

	/**
	 * @return valor de tipo DTOBandejaEAtencionEntity que esta contenido en la variable filtroAtencion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public DTOBandejaEAtencionEntity getFiltroAtencion() {
		return filtroAtencion;
	}

	/**
	 * @param filtroAtencion : valor que se ingresa a la variable de tipo DTOBandejaEAtencionEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setFiltroAtencion(DTOBandejaEAtencionEntity filtroAtencion) {
		this.filtroAtencion = filtroAtencion;
	}

	/**
	 * @return valor de tipo DTOBandejaEAtencionHelper que esta contenido en la variable filtrosAtencionHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public DTOBandejaEAtencionHelper getFiltrosAtencionHelper() {
		return filtrosAtencionHelper;
	}

	/**
	 * @param filtrosAtencionHelper : valor que se ingresa a la variable de tipo DTOBandejaEAtencionHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setFiltrosAtencionHelper(
			DTOBandejaEAtencionHelper filtrosAtencionHelper) {
		this.filtrosAtencionHelper = filtrosAtencionHelper;
	}

	/**
	 * @return valor de tipo DTOBandejaEInfoEntity que esta contenido en la variable filtroInfo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public DTOBandejaEInfoEntity getFiltroInfo() {
		return filtroInfo;
	}

	/**
	 * @param filtroInfo : valor que se ingresa a la variable de tipo DTOBandejaEInfoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setFiltroInfo(DTOBandejaEInfoEntity filtroInfo) {
		this.filtroInfo = filtroInfo;
	}

	/**
	 * @return valor de tipo DTOBandejaEInfoHelper que esta contenido en la variable filtrosInfoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public DTOBandejaEInfoHelper getFiltrosInfoHelper() {
		return filtrosInfoHelper;
	}

	/**
	 * @param filtrosInfoHelper : valor que se ingresa a la variable de tipo DTOBandejaEInfoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setFiltrosInfoHelper(DTOBandejaEInfoHelper filtrosInfoHelper) {
		this.filtrosInfoHelper = filtrosInfoHelper;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable ordenarPorNombre
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public boolean isOrdenarPorNombre() {
		return ordenarPorNombre;
	}

	/**
	 * @param ordenarPorNombre : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setOrdenarPorNombre(boolean ordenarPorNombre) {
		this.ordenarPorNombre = ordenarPorNombre;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable ordenarPorFecha
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public boolean isOrdenarPorFecha() {
		return ordenarPorFecha;
	}

	/**
	 * @param ordenarPorFecha : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setOrdenarPorFecha(boolean ordenarPorFecha) {
		this.ordenarPorFecha = ordenarPorFecha;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable contBandeja
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public Integer getContBandeja() {
		return contBandeja;
	}

	/**
	 * @param contBandeja : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setContBandeja(Integer contBandeja) {
		this.contBandeja = contBandeja;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable contEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public Integer getContEnviados() {
		return contEnviados;
	}

	/**
	 * @param contEnviados : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setContEnviados(Integer contEnviados) {
		this.contEnviados = contEnviados;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable contBorradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public Integer getContBorradores() {
		return contBorradores;
	}

	/**
	 * @param contBorradores : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setContBorradores(Integer contBorradores) {
		this.contBorradores = contBorradores;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable contHistorico
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public Integer getContHistorico() {
		return contHistorico;
	}

	/**
	 * @param contHistorico : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setContHistorico(Integer contHistorico) {
		this.contHistorico = contHistorico;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable atencionTurnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/02/2018
	 */
	public Integer getAtencionTurnado() {
		return atencionTurnado;
	}

	/**
	 * @param atencionTurnado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/02/2018
	 */
	public void setAtencionTurnado(Integer atencionTurnado) {
		this.atencionTurnado = atencionTurnado;
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable listaHistoricoTurnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/02/2018
	 */
	public TreeNode getListaHistoricoTurnado() {
		return listaHistoricoTurnado;
	}

	/**
	 * @param listaHistoricoTurnado : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/02/2018
	 */
	public void setListaHistoricoTurnado(TreeNode listaHistoricoTurnado) {
		this.listaHistoricoTurnado = listaHistoricoTurnado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoRespuestaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/03/2018
	 */
	public String getTipoRespuestaDocumento() {
		return tipoRespuestaDocumento;
	}

	/**
	 * @param tipoRespuestaDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 13/03/2018
	 */
	public void setTipoRespuestaDocumento(String tipoRespuestaDocumento) {
		this.tipoRespuestaDocumento = tipoRespuestaDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocumentoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public Integer getIdDocumentoSeleccionado() {
		return idDocumentoSeleccionado;
	}

	/**
	 * @param idDocumentoSeleccionado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setIdDocumentoSeleccionado(Integer idDocumentoSeleccionado) {
		this.idDocumentoSeleccionado = idDocumentoSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable comentarioRespuesta
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public String getComentarioRespuesta() {
		return comentarioRespuesta;
	}

	/**
	 * @param comentarioRespuesta : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 14/03/2018
	 */
	public void setComentarioRespuesta(String comentarioRespuesta) {
		this.comentarioRespuesta = comentarioRespuesta;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable nombreOriginalAdjunto
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/03/2018
	 */
	public String getNombreOriginalAdjunto() {
		return nombreOriginalAdjunto;
	}

	/**
	 * @param nombreOriginalAdjunto : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/03/2018
	 */
	public void setNombreOriginalAdjunto(String nombreOriginalAdjunto) {
		this.nombreOriginalAdjunto = nombreOriginalAdjunto;
	}

	/**
	 * @return valor de tipo DTOResponderTurnadoHelper que esta contenido en la variable filtroResponder
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/03/2018
	 */
	public DTOResponderTurnadoHelper getFiltroResponder() {
		return helperResponder;
	}

	/**
	 * @param filtroResponder : valor que se ingresa a la variable de tipo DTOResponderTurnadoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 19/03/2018
	 */
	public void setFiltroResponder(DTOResponderTurnadoHelper filtroResponder) {
		this.helperResponder = filtroResponder;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idHistoricoRecepcion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public Integer getIdHistoricoRecepcion() {
		return idHistoricoRecepcion;
	}

	/**
	 * @param idHistoricoRecepcion : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setIdHistoricoRecepcion(Integer idHistoricoRecepcion) {
		this.idHistoricoRecepcion = idHistoricoRecepcion;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable muestraPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public boolean isMuestraPersonas() {
		return muestraPersonas;
	}

	/**
	 * @param muestraPersonas : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setMuestraPersonas(boolean muestraPersonas) {
		this.muestraPersonas = muestraPersonas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable coincidenciaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public String getCoincidenciaPersonas() {
		return coincidenciaPersonas;
	}

	/**
	 * @param coincidenciaPersonas : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setCoincidenciaPersonas(String coincidenciaPersonas) {
		this.coincidenciaPersonas = coincidenciaPersonas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonas() {
		return listaEstPersonas;
	}

	/**
	 * @param listaEstPersonas : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstPersonas(List<DTOEstructuraAreasEntity> listaEstPersonas) {
		this.listaEstPersonas = listaEstPersonas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstPersonasTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonasTemp() {
		return listaEstPersonasTemp;
	}

	/**
	 * @param listaEstPersonasTemp : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstPersonasTemp(
			List<DTOEstructuraAreasEntity> listaEstPersonasTemp) {
		this.listaEstPersonasTemp = listaEstPersonasTemp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstPersonasSeleccionadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonasSeleccionadas() {
		return listaEstPersonasSeleccionadas;
	}

	/**
	 * @param listaEstPersonasSeleccionadas : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstPersonasSeleccionadas(
			List<DTOEstructuraAreasEntity> listaEstPersonasSeleccionadas) {
		this.listaEstPersonasSeleccionadas = listaEstPersonasSeleccionadas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitulares() {
		return listaEstTitulares;
	}

	/**
	 * @param listaEstTitulares : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstTitulares(
			List<DTOEstructuraAreasEntity> listaEstTitulares) {
		this.listaEstTitulares = listaEstTitulares;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstTitularesTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitularesTemp() {
		return listaEstTitularesTemp;
	}

	/**
	 * @param listaEstTitularesTemp : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstTitularesTemp(
			List<DTOEstructuraAreasEntity> listaEstTitularesTemp) {
		this.listaEstTitularesTemp = listaEstTitularesTemp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable listaEstTitularesSeleccionados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitularesSeleccionados() {
		return listaEstTitularesSeleccionados;
	}

	/**
	 * @param listaEstTitularesSeleccionados : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setListaEstTitularesSeleccionados(
			List<DTOEstructuraAreasEntity> listaEstTitularesSeleccionados) {
		this.listaEstTitularesSeleccionados = listaEstTitularesSeleccionados;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable coincidenciaTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public String getCoincidenciaTitulares() {
		return coincidenciaTitulares;
	}

	/**
	 * @param coincidenciaTitulares : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/03/2018
	 */
	public void setCoincidenciaTitulares(String coincidenciaTitulares) {
		this.coincidenciaTitulares = coincidenciaTitulares;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable atencionTurnadoServer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public Integer getAtencionTurnadoServer() {
		return atencionTurnadoServer;
	}

	/**
	 * @param atencionTurnadoServer : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/03/2018
	 */
	public void setAtencionTurnadoServer(Integer atencionTurnadoServer) {
		this.atencionTurnadoServer = atencionTurnadoServer;
	}

	/**
	 * @return valor de tipo List<DTOInstruccionesEntity> que esta contenido en la variable listaInstrucciones
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public List<DTOInstruccionesEntity> getListaInstrucciones() {
		return listaInstrucciones;
	}

	/**
	 * @param listaInstrucciones : valor que se ingresa a la variable de tipo List<DTOInstruccionesEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/04/2018
	 */
	public void setListaInstrucciones(
			List<DTOInstruccionesEntity> listaInstrucciones) {
		this.listaInstrucciones = listaInstrucciones;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personasRemitentes
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonasRemitentes() {
		return personasRemitentes;
	}

	/**
	 * @param personasRemitentes : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setPersonasRemitentes(
			List<DTOEstructuraAreasEntity> personasRemitentes) {
		this.personasRemitentes = personasRemitentes;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido en la variable personasCCP
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTOEstructuraAreasEntity> getPersonasCCP() {
		return personasCCP;
	}

	/**
	 * @param personasCCP : valor que se ingresa a la variable de tipo List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setPersonasCCP(List<DTOEstructuraAreasEntity> personasCCP) {
		this.personasCCP = personasCCP;
	}

	/**
	 * @return valor de tipo List<DTODocumentoDestinatarioEntity> que esta contenido en la variable destinatarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public List<DTODocumentoDestinatarioEntity> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios : valor que se ingresa a la variable de tipo List<DTODocumentoDestinatarioEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void setDestinatarios(List<DTODocumentoDestinatarioEntity> destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return valor de tipo List<DTOComentariosNoLeidos> que esta contenido en la variable listaComentariosLeidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public List<DTOComentariosNoLeidos> getListaComentariosLeidos() {
		return listaComentariosLeidos;
	}

	/**
	 * @param listaComentariosLeidos : valor que se ingresa a la variable de tipo List<DTOComentariosNoLeidos>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public void setListaComentariosLeidos(
			List<DTOComentariosNoLeidos> listaComentariosLeidos) {
		this.listaComentariosLeidos = listaComentariosLeidos;
	}

	/**
	 * @return valor de tipo List<DTOComentariosNoLeidos> que esta contenido en la variable listaComentariosNoLeidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public List<DTOComentariosNoLeidos> getListaComentariosNoLeidos() {
		return listaComentariosNoLeidos;
	}

	/**
	 * @param listaComentariosNoLeidos : valor que se ingresa a la variable de tipo List<DTOComentariosNoLeidos>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 18/04/2018
	 */
	public void setListaComentariosNoLeidos(
			List<DTOComentariosNoLeidos> listaComentariosNoLeidos) {
		this.listaComentariosNoLeidos = listaComentariosNoLeidos;
	}

	/**
	 * @return valor de tipo DTOResponderTurnadoHelper que esta contenido en la variable helperResponder
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public DTOResponderTurnadoHelper getHelperResponder() {
		return helperResponder;
	}

	/**
	 * @param helperResponder : valor que se ingresa a la variable de tipo DTOResponderTurnadoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public void setHelperResponder(DTOResponderTurnadoHelper helperResponder) {
		this.helperResponder = helperResponder;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable dtoDocumentoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public DTODocumentoEntity getDtoDocumentoSeleccionado() {
		return dtoDocumentoSeleccionado;
	}

	/**
	 * @param dtoDocumentoSeleccionado : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public void setDtoDocumentoSeleccionado(
			DTODocumentoEntity dtoDocumentoSeleccionado) {
		this.dtoDocumentoSeleccionado = dtoDocumentoSeleccionado;
	}

	/**
	 * @return valor de tipo DTODetalleDocumentoHelper que esta contenido en la variable detalleDocumentoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public DTODetalleDocumentoHelper getDetalleDocumentoHelper() {
		return detalleDocumentoHelper;
	}

	/**
	 * @param detalleDocumentoHelper : valor que se ingresa a la variable de tipo DTODetalleDocumentoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public void setDetalleDocumentoHelper(
			DTODetalleDocumentoHelper detalleDocumentoHelper) {
		this.detalleDocumentoHelper = detalleDocumentoHelper;
	}
}
