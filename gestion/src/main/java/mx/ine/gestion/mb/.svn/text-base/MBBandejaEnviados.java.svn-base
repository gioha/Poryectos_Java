/**
 * @(#)MBBandejaEnviados.java 02/11/2017
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

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.bsd.inter.BSDBandejaEnviadoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTODetalleDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**-
 * @author Homero Fidel Villanueva
 *
 */
public class MBBandejaEnviados implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -5396668565294332622L;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdBorradorDocumentosInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaEnviados")
	private transient BSDBandejaEnviadoInterface bsdBandejaEnviadoInterface; 

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boArchivo")
	private transient BOArchivoInteface boArchivoInteface;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);

	/**
	 * Lista de documentos que han sido enviados
	 */
	private List<DTOEnviadosDocumentosEntity> listaEnviados;

	/**
	 * Lista de anexos del doccumento enviado seleccionado
	 */
	private List<DTODocumentoAnexoEntity> listaAnexos;

	/**
	 * Documento enviado que está siendo seleccionado
	 */
	private DTOEnviadosDocumentosEntity enviadoSeleccionado;

	/**
	 * Nombre de la columna por la cual serán ordenados los documentos enviados
	 */
	private String columnaOrdenamiento;

	/**
	 * Si es true el ordenamiento de los documentos enviados será ascendente, en caso contrario será descendente
	 */
	private boolean esOrdenamientoAscendente;

	/**
	 * El nombre del documento del documento enviado seleccionado
	 */
	private String nombreDocumentoPDF;

	/**
	 * Usuario que inició sesión
	 */
	private DTOEstructuraAreasEntity usuario;

	/**
	 * Lista que guarda todos los comentarios leidos de un determinado borrador.
	 */
	private List<DTOComentariosNoLeidos> listaComentariosLeidos;

	/**
	 * Lista que guarda todos los comentarios no leidos de un determinado
	 * borrador.
	 */
	private List<DTOComentariosNoLeidos> listaComentariosNoLeidos;

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
	 * Filtro utilizado para la consulta de Enviados 
	 */
	private DTOEnviadosDocumentosHelper filtroEnviados;
	
	/**
	 * Atributo que guarda la cuenta del usuario
	 */
	private String cuentaUsuario;
	
	/**
	 * 
	 */
	private DTODetalleDocumentoHelper detalleDocumentoHelper;
	
	// ------------------------ Métodos ------------------------ //
	public void iniciar() {
		log.info(this.getClass().getName() + " Inicia con éxito el método iniciar() ");
		
		//Se obtiene la estrutura de la persona que inició sesión
		cuentaUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(cuentaUsuario);
		
		//Se actualizan los contadores del menú izquierdo
		consultarContadoresMenu();
		
		//Filtro para la consulta de los documentos
		filtroEnviados = new DTOEnviadosDocumentosHelper();
		filtroEnviados.agregarPersonas(usuario);
		filtroEnviados.setColumnaOrdenamiento("FECHA_CREACION");
		filtroEnviados.setTipoOrdenamiento(false);
		
		detalleDocumentoHelper = new DTODetalleDocumentoHelper();
		
		//Consulta de los documentos
		try {
			listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(filtroEnviados);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: iniciar()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de Documentos de la BD. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		log.info(this.getClass().getName()+ " Termina con éxito el método iniciar() ");
	}
	
	/**
	 * Método que obtiene el detalle del documento seleccionado, como son el remitente
	 * @param documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/04/2018
	 */
	public void obtenerDetalleDocumento(DTODocumentoEntity documento){
		verInicio();
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

	/**
	 * Método que actualiza la lista de documentos enviados, el menú principal y
	 * el menú izquierdo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void actualizarPantallaEnviados() {
		consultarContadoresMenu();
		filtroEnviados.setColumnaOrdenamiento(filtroEnviados.getColumnaOrdenamiento());
		filtroEnviados.setTipoOrdenamiento(filtroEnviados.isTipoOrdenamiento());
		
		//Consulta de los documentos
		try {
			listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(filtroEnviados);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: actualizarPantallaEnviados()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de Documentos de la BD. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesSeguimiento();
	}

	/**
	 * Método que actualiza la lista de enviados por nombre
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void ordenarDocumentosPorNombre() {
		filtroEnviados.setColumnaOrdenamiento("NUM_DOCUMENTO");
		filtroEnviados.setTipoOrdenamiento(!filtroEnviados.isTipoOrdenamiento());
		
		//Consulta de los documentos
		try {
			listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(filtroEnviados);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: ordenarDocumentosPorNombre()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de Documentos de la BD. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}
	
	/**
	 * Método que actualiza la lista de enviados por fecha de creación
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void ordenarDocumentosPorFecha() {
		filtroEnviados.setColumnaOrdenamiento("FECHA_CREACION");
		filtroEnviados.setTipoOrdenamiento(!filtroEnviados.isTipoOrdenamiento());
		
		//Consulta de los documentos
		try {
			listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(filtroEnviados);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: ordenarDocumentosPorFecha()");
			log.error("<=================== ocurrio un error al tratar de obtener la lista de Documentos de la BD. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método que obtiene el nombre del documento previo, los comentarios y la
	 * lista de anexos del documento seleccionado
	 * 
	 * @param dtoEnviado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void seleccionarEnviado(DTOEnviadosDocumentosEntity dtoEnviado) {
		enviadoSeleccionado = dtoEnviado;
		nombreDocumentoPDF = enviadoSeleccionado.getDtoDocumento()
				.getIdDocumento()
				+ "_"
				+ enviadoSeleccionado.getDtoDocumento().getAnio() + ".pdf";
		obtenerDetalleDocumento(dtoEnviado.getDtoDocumento());
		obtenerComentarios();
		if (enviadoSeleccionado.getDtoDocumento().getContieneAnexos() > 0) {
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(enviadoSeleccionado.getDtoDocumento());
		}
	}

	/**
	 * Método utilizado cuando se selecciona un documento enviado. Si el
	 * documento no ha sido leido se actualizará en la bd.
	 * 
	 * @param event
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void seleccionarEnviado(SelectEvent event) {
		DTOEnviadosDocumentosEntity miEnviado = (DTOEnviadosDocumentosEntity) event.getObject();
		
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miEnviado.getNoLeido())){
			bsdBandejaSeguimientoInterface.notificacionBandejaBorradores(usuario, miEnviado, false);
			consultarContadoresMenu();
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
		}
		
		seleccionarEnviado(miEnviado);
	}
	
	/**
	 * Método que busca los comentarios leídos y no leídos de el borrador
	 * seleccionado.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerComentarios() {
		try {
			this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(enviadoSeleccionado.getDtoDocumento(),usuario);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerComentarios()");
			log.error("<=================== Error al cargar la lista de comentarios leidos del documento: "+enviadoSeleccionado);
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		try {
			this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(enviadoSeleccionado.getDtoDocumento(),usuario);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: obtenerComentarios()");
			log.error("<=================== Error al cargar la lista de comentarios no leidos del documento: "+enviadoSeleccionado);
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
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
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: leerComentarios()");
				log.error("<=================== ocurrio un error al tratar de cambiar el estatus de los comentarios. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
	}
	
	/**
	 * Método que elimina el documento seleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void eliminarEnviado(){
		try {
			bsdBandejaEnviadoInterface.eliminarEnviados(enviadoSeleccionado);
		} catch (Exception e) {
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: eliminarEnviado()");
			log.error("<=================== ocurrio un error al tratar de eliminar el documento "+enviadoSeleccionado+". ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		actualizarPantallaEnviados();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"),Utilidades.mensajeProperties("mensaje_exito_eliminacion_borrador")));
	}
	
	/**
	 * Método que muestra el dialog con el detalle del documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void mostrarDialogDetalle(){
		RequestContext.getCurrentInstance().execute("PF('dialogDetalleDoc').show()");
	}
	
	/**
	 * Método que muestra el dialog para comfirmar la eliminación del documento
	 * enviado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 21/02/2018
	 */
	public void mostrarConfirmacionEliminarEnviado() {
		if (enviadoSeleccionado != null) {
			RequestContext.getCurrentInstance().execute("PF('dialogConfirmacionEliminarEnviado').show()");
		}
	}
	
	/**
	 * Método que muestra el dialog para leer los comentarios
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void mostrarDialogComentarios(){
		obtenerComentarios();
		RequestContext.getCurrentInstance().execute("PF('dialog_revisar_comentarios').show()");
	}
	
	/**
	 * Método que muestra el dialog de Anexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogAnexos() {
		RequestContext.getCurrentInstance().execute("PF('dialogVerAnexosEnviados').show()");
	}
	
	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo List<DTOEnviadosDocumentosEntity> que esta
	 *         contenido en la variable listaEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public List<DTOEnviadosDocumentosEntity> getListaEnviados() {
		return listaEnviados;
	}

	/**
	 * @param listaEnviados
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEnviadosDocumentosEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setListaEnviados(List<DTOEnviadosDocumentosEntity> listaEnviados) {
		this.listaEnviados = listaEnviados;
	}

	/**
	 * @return valor de tipo DTOEnviadosDocumentosEntity que esta contenido en
	 *         la variable enviadoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public DTOEnviadosDocumentosEntity getEnviadoSeleccionado() {
		return enviadoSeleccionado;
	}

	/**
	 * @param enviadoSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEnviadosDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setEnviadoSeleccionado(
			DTOEnviadosDocumentosEntity enviadoSeleccionado) {
		this.enviadoSeleccionado = enviadoSeleccionado;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable
	 *         esOrdenamientoAscendente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public boolean isEsOrdenamientoAscendente() {
		return esOrdenamientoAscendente;
	}

	/**
	 * @param esOrdenamientoAscendente
	 *            : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setEsOrdenamientoAscendente(boolean esOrdenamientoAscendente) {
		this.esOrdenamientoAscendente = esOrdenamientoAscendente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         columnaOrdenamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public String getColumnaOrdenamiento() {
		return columnaOrdenamiento;
	}

	/**
	 * @param columnaOrdenamiento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setColumnaOrdenamiento(String columnaOrdenamiento) {
		this.columnaOrdenamiento = columnaOrdenamiento;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/11/2017
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
	 * @since 10/11/2017
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en
	 *         la variable listaAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/11/2017
	 */
	public List<DTODocumentoAnexoEntity> getListaAnexos() {
		return listaAnexos;
	}

	/**
	 * @param listaAnexos
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTODocumentoAnexoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 15/11/2017
	 */
	public void setListaAnexos(List<DTODocumentoAnexoEntity> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         nombreDocumentoPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public String getNombreDocumentoPDF() {
		return nombreDocumentoPDF;
	}

	/**
	 * @param nombreDocumentoPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setNombreDocumentoPDF(String nombreDocumentoPDF) {
		this.nombreDocumentoPDF = nombreDocumentoPDF;
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
	 * @return valor de tipo String que esta contenido en la variable cuentaUsuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public String getCuentaUsuario() {
		return cuentaUsuario;
	}

	/**
	 * @param cuentaUsuario : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/04/2018
	 */
	public void setCuentaUsuario(String cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
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
