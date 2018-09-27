/**
 * MBBandeja.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.bo.inter.BOBandejaSeguimientoInterface;
import mx.ine.gestion.bo.inter.BOBandejaBorradoresInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosNoLeidos;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoDestinatarioEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.helper.DTOBorradorDocumentosHelper;
import mx.ine.gestion.dto.helper.DTODetalleDocumentoHelper;
import mx.ine.gestion.dto.helper.DTOPersonaBorradoresHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHBandejaSeguimientoInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Managebean encargado encargado del módulo de la Bandeja de Recepción
 * (Pantalla Borradores)
 * 
 * @author Homero Villanueva Dominguez
 * @since 12/07/2017
 * @update José Miguel Ortiz
 * @since 26/04/2018
 */
public class MBBandejaBorradores implements Serializable {
	
	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = 4063633888949820832L;
	
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
	@Qualifier("boArchivo")
	private transient BOArchivoInteface boArchivoInteface;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boBandejaSeguimiento")
	private transient BOBandejaSeguimientoInterface boBandejaSeguimientoInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boBandejaBorradores")
	private transient BOBandejaBorradoresInterface boBandejaBorradoresInterface;
	
	@Autowired
	@Qualifier("vhBandejaSeguimiento")
	private transient VHBandejaSeguimientoInterface vhBandejaSeguimientoInterface;

	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger
			.getLogger(MBBandejaBorradores.class);

	/**
	 * Lista de objetos de Borradores
	 */
	private List<DTOBorradorDocumentosEntity> listaDTOBorradores;

	/**
	 * Lista de objetos de Documentos
	 */
	private List<DTODocumentoEntity> listaDTODocumentos;

	/**
	 * Atributo que indica si el documento fue creado o fue editado
	 */
	private String numeroDocumento;
	
	/**
	 * 
	 */
	private String tipoCaptura;

	/**
	 * Borrador seleccionado por el usuario
	 */
	private DTOBorradorDocumentosEntity dtoBorradorSeleccionado;

//	/**
//	 * Borrador seleccionado por el usuario
//	 */
//	private DTOBorradorDocumentosEntity borradorSeleccionadoListaAcciones;

	private boolean seleccionoRegistroTabla;

	/**
	 * Lista de Estructuras personas
	 */
	private List<DTOPersonaBorradoresHelper> listaEstPersonas;

	/**
	 * Lista de Estructuras personas que guarda registros Temporalmente
	 */
	private List<DTOPersonaBorradoresHelper> listaEstPersonasTemp;

	/**
	 * Lista de Estructuras de personas seleccionadas por el usuario y que serán
	 * guardadas en el servidor.
	 */
	private List<DTOPersonaBorradoresHelper> listaEstPersonasSeleccionadas;

	/**
	 * Lista de estructura de titulares
	 */
	private List<DTOPersonaBorradoresHelper> listaEstTitulares;

	/**
	 * Lista de estructura de titulares que guardan registros temporalmente
	 */
	private List<DTOPersonaBorradoresHelper> listaEstTitularesTemp;

	/**
	 * Lista de estructura de titulares seleccionados y que serán guardados en
	 * el servidor
	 */
	private List<DTOPersonaBorradoresHelper> listaEstTitularesSeleccionados;

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
	 * 
	 */
	private List<DTOEdicionesDocumentoEntity> listaEdicionesNoLeidos;

	/**
	 * 
	 */
	private List<DTOEstructuraAreasEntity> listaDestinatarios;

	/**
	 * 
	 */
	private List<DTOEstructuraAreasEntity> listaRemitentes;

	/**
	 * 
	 */
	private DTOEstructuraAreasEntity remitenteSeleccionado;

	/**
	 * 
	 */
	private DTODetalleDocumentoHelper detalleDocumentoHelper;
	/**
	 * 
	 */
	private List<DTODocumentoAnexoEntity> listaAnexos;

	/**
	 * Objeto que guarda la estructura de la persona que inició sesión.
	 */
	private DTOEstructuraAreasEntity usuario;

	/**
	 * Atributo que guarda el nombre de la columna por el cual se ordenará la
	 * lista de Documentos Borradores.
	 */
	//private String columnaOrdenamiento;

	/**
	 * Atributo que guarda el tipo de ordenamiernto. Si está en true es
	 * ascendente, si está en false es descendente.
	 */
	//private boolean esOrdenamientoAscendente;

	/**
	 * Atributo utilizado en el dialog_enviar_fv. Sirve para indicar que vista
	 * de busqueda se mostrará. Si es true muestra el outputpanel para buscar
	 * personas. Si es false muestra el outputpanel para buscar titulares.
	 */
	private boolean muestraPersonas;

	/**
	 * 
	 */
	private String nombreDocumentoPDF;

	/**
	 * Atributo que guarda una cadena para buscar coincidencias en el nombre o
	 * apellidos de las personas por área.
	 */
	private String coincidenciaPersonas;

	/**
	 * Atributo que guarda una cadena para buscar coincidencias en la
	 * descripcion y siglas del área de los titulares.
	 */
	private String coincidenciaTitulares;

	/**
	 * Atributo utilizado en en el dialog_enviar_fv. Sirve para indicar que
	 * botón de enviar se mostrará. Si es true las personas seleccionadas se
	 * enviarán a Validar.En caso contrario se enviará a Firmar a las personas
	 * seleccionadas.
	 */
	private boolean esEnviarValidar;

	/**
	 * 
	 */
	private boolean firmarUnoMismo;
	
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
	private DTOEstructuraAreasEntity personaBorrar;
	
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
	private DTOBorradorDocumentosHelper filtroBorradores;
	
	/**
	 * Atributo que guarda la cuenta del usuario
	 */
	private String cuentaUsuario;
	
	// ------------------------ METÓDOS ------------------------ //
	/**
	 * Método para inicializar las variables necesarias para mostrar la pantalla
	 * cuando se entra al módulo.
	 *
	 * @since 07/09/2017
	 */
	public void iniciar() {
		//Se obtiene la estrutura de la persona que inició sesión
		cuentaUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(SecurityContextHolder.getContext().getAuthentication().getName());
		
		//Se actualizan los contadores del menú izquierdo
		consultarContadoresMenu();
		
		detalleDocumentoHelper = new DTODetalleDocumentoHelper();
		//Se revisa que mensaje se mostrará cuando se edita o crea un documento borrador
		String mensaje = "";
		filtroBorradores = new DTOBorradorDocumentosHelper();
		
		if (tipoCaptura.equals("C") ) {
			mensaje = Utilidades.mensajeProperties("mensaje_exito_creacion_documento1")+ numeroDocumento +" " +Utilidades.mensajeProperties("mensaje_exito_creacion_documento2");
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_exito"), 
					Utilidades.mensajeProperties("titulo_growl_exito"), 
					mensaje);
			tipoCaptura = "";
			
		} else if (tipoCaptura.equals("M")) {
			mensaje = Utilidades.mensajeProperties("mensaje_exito_modificacion_documento1")+ numeroDocumento +" " +Utilidades.mensajeProperties("mensaje_exito_modificacion_documento2");
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_exito"), 
					Utilidades.mensajeProperties("titulo_growl_exito"), 
					mensaje);
			tipoCaptura = "";
		}
		
		
		filtroBorradores.agregarPersonas(usuario);
		
		

		//Se consultan los documentos borradores de las personas
		filtroBorradores.setColumnaOrdenamiento("FECHA_CREACION");
		filtroBorradores.setTipoOrdenamiento(false);
		listaDTOBorradores = consultarBorradores();

//		if (listaDTOBorradores != null && listaDTOBorradores.size() > 0) {
//
//			seleccionarBorrador(listaDTOBorradores.get(0));
//
//
//			nombreDocumentoPDF = dtoBorradorSeleccionado.getDocumento()
//					.getIdDocumento()
//					+ "_"
//					+ dtoBorradorSeleccionado.getDocumento().getAnio() + ".pdf";
//			
//			obtenerAnexos();
//			obtenerComentarios();
//			obtenerEdiciones();
//
//			
//		}
		//Se pone primero la vista de consultar personas para enviar a firar o validar
		//muestraPersonas = true;

		//esEnviarValidar = false;
		log.info(this.getClass().getName() + " Finaliza con éxito el método iniciar() ");
	}
	
	public void mostrarDialogDetalle(){
		RequestContext.getCurrentInstance().execute("PF('dialogDetalleDoc').show()");
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
	 * Método que muestra el dialog de ediciones y actualiza la lista de
	 * ediciones
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogEdiciones() {
		log.info(this.getClass().getName()+ " Inicia el método muestraComentarios()");

		quitarNotificacionEditado();
		obtenerEdiciones();
		RequestContext.getCurrentInstance().execute("PF('dialog_revisar_ediciones').show()");

		log.info(this.getClass().getName()+ " Se finaliza el método muestraComentarios()");
	}
	
	/**
	 * Método que muestra el dialog para los comentarios y actualiza los
	 * comentarios.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogComentarios() {
		log.info(this.getClass().getName()+ " Se inicia el método muestraComentarios()");
		
		quitarNotificacionComentado();
		obtenerComentarios();
		RequestContext.getCurrentInstance().execute("PF('dialog_revisar_comentarios').show()");
		
		log.info(this.getClass().getName()+ " Se finaliza el método muestraComentarios()");
	}
	
	/**
	 * Método que muestra el dialog para enviar a Firmar.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogEnviarFirmar() {
		log.info(this.getClass().getName()+ " Se inicia el método mostrarEnviarFirmar()");

		setEsEnviarValidar(false);
		RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').show()");
		
		List<DTOPersonaBorradoresHelper> listaRemitentesFirmar = obtenerListaRemitentes();
		
		if (listaRemitentesFirmar != null && listaRemitentesFirmar.size() > 0) {
			listaEstPersonasSeleccionadas = listaRemitentesFirmar;
//			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(
//					Utilidades.mensajeProperties("constante_growl_info"), 
//					Utilidades.mensajeProperties("titulo_growl_info"), 
//					"El documento cuenta con más de 1 remitente por ello se agregaran a la lista de personas para firmar");
		}
		

		log.info(this.getClass().getName()+ " Se termina el método mostrarEnviarFirmar()");
	}
	
//	/**
//	 * Método que muestra el dialog para enviar a Firmar.
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/11/2017
//	 */
//	public void mostrarDialogEnviarFirmar(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
//		log.info(this.getClass().getName()+ " Se inicia el método mostrarEnviarFirmar()");
//		
//		setBorradorSeleccionadoListaAcciones(dtoBorradorDocumentosEntity);
//		setSeleccionoRegistroTabla(false);
//		setEsEnviarValidar(false);
//		RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').show()");
//
//		log.info(this.getClass().getName()+ " Se termina el método mostrarEnviarFirmar()");
//	}
	/**
	 * Método que muestra el dialog para enviar a Validar.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogEnviarValidar() {
		log.info(this.getClass().getName()+ " Se inicia el método mostrarEnviarValidar()");
		setEsEnviarValidar(true);
		RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').show()");
		log.info(this.getClass().getName()+ " Se finaliza el método mostrarEnviarValidar()");
	}
//	
//	/**
//	 * Método que muestra el dialog para enviar a Validar.
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/11/2017
//	 */
//	public void mostrarDialogEnviarValidar(DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
//		log.info(this.getClass().getName()+ " Se inicia el método mostrarEnviarValidar()");
//
//		setBorradorSeleccionadoListaAcciones(dtoBorradorDocumentosEntity);
//		setSeleccionoRegistroTabla(false);
//
//		setEsEnviarValidar(true);
//		RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').show()");
//		
//		log.info(this.getClass().getName()+ " Se finaliza el método mostrarEnviarValidar()");
//	}
	
	/**
	 * Método que muestra el dialog de confirmación para borrar un borrador.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarConfirmacionEliminarBorrador() {
		RequestContext.getCurrentInstance().execute("PF('confirmacionEliminarBorrador').show()");
	}
	
	/**
	 * Método que muestra el mensaje de éxito cuando se ha creado un documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarMensajeCaptura() {
		vhBandejaSeguimientoInterface.mostrarMensajeGrowl(
				Utilidades.mensajeProperties("constante_growl_exito"), 
				Utilidades.mensajeProperties("titulo_growl_exito"), 
				Utilidades.mensajeProperties("mensaje_exito_creacion_documento"));
	}
	
	/**
	 * Método que muestra el dialog de Anexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogAnexos() {
		RequestContext.getCurrentInstance().execute("PF('dialogVerAnexos').show()");
	}
	
	/**
	 * Método que regresa todos los borradores que pertenecesn al usuario que
	 * inició sesión.
	 * 
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public List<DTOBorradorDocumentosEntity> consultarBorradores() {
		log.info(this.getClass().getName()+ " Se finaliza el método consultarBorradores()");
		try {

			return bsdBorradorDocumentosInterface.consultarBorradores(filtroBorradores);

		} catch (Exception e) {
			log.info("<=================== Clase: MBBandeja , Método: consultarBorradores");
			log.info("<=================== ocurrio un error al tratar de obtener la lista de Borradores de la BD. ");
			log.info("<=================== USUARIO LOGUEADO: "
					+ SecurityContextHolder.getContext().getAuthentication()
							.getName());
			log.info("", e);
			return null;
		}

	}
	
	/**
	 * Método que envía a firmar el borrador seleccionado para personas de la
	 * misma área y titulares que hayan sido seleccionados.
	 * 
	 * Se inserta en la tabla del historico las transacciones realizadas.
	 * 
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 * @update José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public void enviarValidar() {
		log.info(this.getClass().getName() + " Se inicia el método enviarValidar()");

		DTOBorradorDocumentosEntity borradorValidar = dtoBorradorSeleccionado;

		if ((listaEstPersonasSeleccionadas != null && listaEstPersonasSeleccionadas.size() > 0) ||
				(listaEstTitularesSeleccionados != null && listaEstTitularesSeleccionados.size() > 0)) {
			
			List<DTOEstructuraAreasEntity> listaFinal = boBandejaSeguimientoInterface.unirListas(
					boBandejaBorradoresInterface.obtenerPersonas( listaEstTitularesSeleccionados), boBandejaBorradoresInterface.obtenerPersonas(listaEstPersonasSeleccionadas));
			
			try {
				this.bsdBorradorDocumentosInterface.enviarValidar(listaFinal, borradorValidar, usuario);
				this.bsdBorradorDocumentosInterface.enviarCorreoNotificacion(usuario, listaFinal, borradorValidar, "validar");
				
			} catch (Exception e) {

				log.error("<=================== Error al enviar  a validar el documento borrador con id:"+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: enviarValidar");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}

			RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').hide()");

			try {
				listaDTOBorradores = bsdBorradorDocumentosInterface.consultarBorradores(filtroBorradores);
			} catch (Exception e) {
				log.error("<=================== Error al intentar consultar la lista de borradores");
				log.error("<=================== Clase: MBBandejaBorradores , Método: enviarValidar");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}

			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_exito"), 
														Utilidades.mensajeProperties("titulo_growl_exito"),
														Utilidades.mensajeProperties("mensaje_exito_borrador_enviar_validar"));
			
		}else{
			String mensaje = (muestraPersonas)? "No has seleccionado ninguna persona.":"No has seleccionado ningún área.";
			
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_advertencia"), 
														Utilidades.mensajeProperties("titulo_growl_advertencia"),
														mensaje);
		}

		log.info(this.getClass().getName() + " Se finaliza el método enviarValidar()");
	}
	
	/**
	 * Método que actualiza la lista de personas a las que se les enviará a
	 * firmar un borrador documento. Si firmarUnoMismo==true el usuario logueado
	 * se agregará a dicha lista, en caso contrario si ya está dentro de la
	 * lista será removido.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 16/02/2018
	 */
	public void seleccionarUnoMismoFirma(){
		if(firmarUnoMismo){
			if(listaEstPersonasSeleccionadas == null){
				listaEstPersonasSeleccionadas = new ArrayList<DTOPersonaBorradoresHelper>();
			}
			listaEstPersonasSeleccionadas.add(new DTOPersonaBorradoresHelper(usuario));
		}else{
			if(listaEstPersonasSeleccionadas != null && listaEstPersonasSeleccionadas.size() > 0 && listaEstPersonasSeleccionadas.contains(usuario)){
				listaEstPersonasSeleccionadas.remove(usuario);
			}
		}
	}
	
	/**
	 * Método que envía a firmar el borrador seleccionado para personas de la
	 * misma área y titulares que hayan sido seleccionados.
	 * 
	 * Se inserta en la tabla del historico las transacciones realizadas.
	 * 
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 * @update José Miguel Ortiz
	 * @since 26/04/2018
	 */
	public void enviarFirmar() {
		log.info(this.getClass().getName()+ " Se inicia el método enviarFirmar()");

		DTOBorradorDocumentosEntity borradorFirmar = dtoBorradorSeleccionado;
		
		if ((listaEstTitularesSeleccionados != null && listaEstTitularesSeleccionados.size() > 0) ||
					(listaEstPersonasSeleccionadas != null && listaEstPersonasSeleccionadas.size() > 0)){
			
			List<DTOEstructuraAreasEntity> listaFinal = boBandejaSeguimientoInterface.unirListas(
					boBandejaBorradoresInterface.obtenerPersonas( listaEstTitularesSeleccionados ), boBandejaBorradoresInterface.obtenerPersonas(listaEstPersonasSeleccionadas));
			
			try {
				
				this.bsdBorradorDocumentosInterface.enviarAFirmar(listaFinal, borradorFirmar, usuario);
				this.bsdBorradorDocumentosInterface.enviarCorreoNotificacion(usuario, listaFinal, borradorFirmar, "firmar");

			} catch (Exception e) {

				log.error("<=================== Error al firmar el documento Borrador");
				log.error("<=================== Clase: MBBandejaBorradores , Método: enviarFirmar");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			if(firmarUnoMismo || listaFinal.contains(usuario)){
				//Se actualiza el menú
				MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
				administrador.consultarNotificacionesSeguimiento();
			}
			
			RequestContext.getCurrentInstance().execute("PF('dialog_enviar_fv').hide()");

			try {
				listaDTOBorradores = bsdBorradorDocumentosInterface.consultarBorradores(filtroBorradores);
			} catch (Exception e) {
				log.error("<=================== Error al tratar de consultar la lista de Documentos Borradores");
				log.error("<=================== Clase: MBBandejaBorradores , Método: enviarFirmar");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_exito"), 
								Utilidades.mensajeProperties("titulo_growl_exito"), 
								Utilidades.mensajeProperties("mensaje_exito_envio_firma_documento"));
		}else{
			String mensaje = (muestraPersonas)? Utilidades.mensajeProperties("mensaje_advertencia_sin_personas"):Utilidades.mensajeProperties("mensaje_advertencia_sin_area");
			
			vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_advertencia"), 
								Utilidades.mensajeProperties("titulo_growl_advertencia"), 
								mensaje);
		}
		
		log.info(this.getClass().getName()+ " Se finaliza el método enviarFirmar()");
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
	 * Método que quita la notificación de comentado actualizando la bandera de
	 * conComentario a 0 del borrador seleccionado
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void quitarNotificacionComentado() {
		if (dtoBorradorSeleccionado != null) {
			try {
				dtoBorradorSeleccionado.setConComentarios(Integer.parseInt( Utilidades.mensajeProperties("comentario_estatus_leido") ));
				bsdBorradorDocumentosInterface.actualizarBorrador(dtoBorradorSeleccionado);

			} catch (Exception e) {

				log.error("<=================== Error al tratar de actualizar el Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: quitarNotificacionEditado");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * Método que quita la notificación de editado actualizando la bandera de
	 * conModificaciones a 0 del borrador seleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void quitarNotificacionEditado() {
		if (dtoBorradorSeleccionado != null) {
			try {
				dtoBorradorSeleccionado.setConModificaciones(Integer.parseInt( Utilidades.mensajeProperties("edicion_estatus_leido") ));
				bsdBorradorDocumentosInterface.actualizarBorrador(dtoBorradorSeleccionado);

			} catch (Exception e) {

				log.error("<=================== Error al tratar de actualizar el Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: quitarNotificacionEditado");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * Método que quita la notificación de firmado actualizando la bandera de
	 * conFirma a 0 del borrador seleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void quitarNotificacionFirmado() {
		if (dtoBorradorSeleccionado != null) {
			try {
				dtoBorradorSeleccionado.setConFirma(Integer.parseInt( Utilidades.mensajeProperties("notificacion_firma_estatus_leido") ));
				bsdBorradorDocumentosInterface.actualizarBorrador(dtoBorradorSeleccionado);

			} catch (Exception e) {

				log.error("<=================== Error al tratar de actualizar el Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: quitarNotificacionEditado");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * Método que quita la notificación de validado actualizando la bandera de
	 * conValidacion a 0 del borrador seleccionado
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void quitarNotificacionValidado() {
		if (dtoBorradorSeleccionado != null) {
			try {
				dtoBorradorSeleccionado.setConValidacion(Integer.parseInt( Utilidades.mensajeProperties("notificacion_validacion_estatus_leido") ));
				bsdBorradorDocumentosInterface.actualizarBorrador(dtoBorradorSeleccionado);

			} catch (Exception e) {

				log.error("<=================== Error al tratar de actualizar el Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: quitarNotificacionEditado");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * Método llamado cuando el timer refresca la pantalla
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void actualizarPantallaBorradores() {
		listaDTOBorradores = consultarBorradores();
		//Si se tiene algún documento seleccionado se actualizan 
		if(dtoBorradorSeleccionado != null){
			dtoBorradorSeleccionado = boBandejaBorradoresInterface.obtenerBorrador(listaDTOBorradores, dtoBorradorSeleccionado);
			setDtoBorradorSeleccionado(dtoBorradorSeleccionado);
			seleccionarBorrador(dtoBorradorSeleccionado);
		}
		
		//Se actualizan los contadores del menú izquierdo 
		consultarContadoresMenu();
		
		//Se actualiza el menú principal
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesSeguimiento();
		
	}

	

	/**
	 * Método que consulta los Anexos del borrador seleccionado. Sólo cuando la
	 * bandera de contieneAnexos este prendia en 1.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerAnexos() {
		if (dtoBorradorSeleccionado != null && dtoBorradorSeleccionado.getDocumento().getContieneAnexos() > 0) {
			listaAnexos = bsdBandejaSeguimientoInterface.consultarAnexos(dtoBorradorSeleccionado.getDocumento());
		}
	}

	

//	/**
//	 * Método que manda llamar el log
//	 * 
//	 * @param mensaje
//	 *            : mensaje que se mostrará en el log
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 11/11/2017
//	 */
//	public void llamarLog(String mensaje) {
//		log.info(this.getClass().getName() + " " + mensaje);
//	}

	/**
	 * Método que actualiza el estatus de las ediciones que ya han sido vistas a
	 * 0.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerEdiciones() {
		if(dtoBorradorSeleccionado != null){
			try {
				
				this.listaEdicionesNoLeidos = bsdBorradorDocumentosInterface.obtenerListaEdicionesNoLeidas(dtoBorradorSeleccionado.getIdDocumento());
	
			} catch (Exception e) {
	
				log.error("<=================== Error al cargar la lista de las ediciones del documento borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerEdiciones");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * Método que busca los comentarios leídos y no leídos de el borrador
	 * seleccionado.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerComentarios() {
		if(dtoBorradorSeleccionado != null){
			try {
				
				this.listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(dtoBorradorSeleccionado.getDocumento(),usuario);
	
			} catch (Exception e) {
	
				log.error("<=================== Error al cargar la lista de comentarios leidos del documento borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: obtenerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			try {
				
				this.listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(dtoBorradorSeleccionado.getDocumento(),usuario);
	
			} catch (Exception e) {
	
				log.error("<=================== Error al cargar la lista de comentarios no leidos del documento borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
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
		if (dtoBorradorSeleccionado != null) {
			try {
				
				this.bsdBorradorDocumentosInterface.leerComentarios(dtoBorradorSeleccionado, listaComentariosNoLeidos);
				quitarNotificacionComentado();
				obtenerComentarios();

			} catch (Exception e) {

				log.error("<=================== Error al actualizar los comentarios del Documento Borrador con Id: "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: leerComentarios");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
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

		if(!esEnviarValidar || (((usuario.getVerVersionT() == null)|| (usuario.getVerVersionT() != 1) || 
				(!boBandejaBorradoresInterface.revisarPersonaEstaLista(boBandejaBorradoresInterface.obtenerPersonas( listaEstTitularesTemp), usuario))))){
			
			if (listaEstTitularesSeleccionados != null && listaEstTitularesTemp != null && listaEstTitularesSeleccionados.size() > 0) {

				for (DTOPersonaBorradoresHelper elemento : listaEstTitularesTemp) {
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
	 * Método que guarda los registros temporales de las estructuras titulares
	 * en la lista de titulares seleccionados para posteriormente ser guardados
	 * en el servidor.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void cambiarListaPersonasTemp() {
		log.info(this.getClass().getName()
				+ " Se inicia el método cambiarListaPersonasTemp()");

		if (listaEstPersonasSeleccionadas != null
				&& listaEstPersonasTemp != null
				&& listaEstPersonasSeleccionadas.size() > 0) {

			for (DTOPersonaBorradoresHelper elemento : listaEstPersonasTemp) {
				if (!listaEstPersonasSeleccionadas.contains(elemento)) {
					listaEstPersonasSeleccionadas.add(elemento);
				}
			}
		} else if (listaEstPersonasTemp == null
				|| listaEstPersonasTemp.size() == 0) {

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "INFORMATIVO",
					Utilidades.mensajeProperties("mensaje_no_seleccion_personas")));
		} else {
			listaEstPersonasSeleccionadas = listaEstPersonasTemp;
		}

		log.info(this.getClass().getName() + " Se finaliza el método cambiarListaPersonasTemp()");
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
	public void eliminarPersona(DTOPersonaBorradoresHelper persona) {
		if (persona != null && listaEstPersonasSeleccionadas != null && listaEstPersonasSeleccionadas.size() > 0) {
			listaEstPersonasSeleccionadas.remove(persona);
			listaEstPersonasTemp.remove(persona);
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
	public void eliminarTitular(DTOPersonaBorradoresHelper persona) {
		if (persona != null && listaEstTitularesSeleccionados != null && listaEstTitularesSeleccionados.size() > 0) {
			listaEstTitularesSeleccionados.remove(persona);
			listaEstTitularesTemp.remove(persona);
		}
	}

	/**
	 * Método que actualiza la lista de Borradores por nombre
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void ordenarDocumentosPorNombre() {
		log.info(this.getClass().getName() + " Se inicia el método ordenarDocumentosPorNombre()");
		
		filtroBorradores.setColumnaOrdenamiento("NUM_DOCUMENTO");
		filtroBorradores.setTipoOrdenamiento(!filtroBorradores.isTipoOrdenamiento());
		listaDTOBorradores = consultarBorradores();

		log.info(this.getClass().getName()+ " Se termina el método ordenarDocumentosPorNombre()");
	}

	/**
	 * Método que actualiza la lista de borradores por fecha.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void ordenarDocumentosPorFecha() {
		log.info(this.getClass().getName()+ " Se inicia el método ordenarDocumentosPorFecha()");

		filtroBorradores.setColumnaOrdenamiento("FECHA_CREACION");
		filtroBorradores.setTipoOrdenamiento(!filtroBorradores.isTipoOrdenamiento());
		listaDTOBorradores = consultarBorradores();

		log.info(this.getClass().getName()+ " Se inicia el método ordenarDocumentosPorFecha()");
	}

	/**
	 * Método que elimina un borrador de la lista de borradores y lo elimina
	 * lógicamente de la la BD
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarBorrador() {
		log.info(this.getClass().getName() + " Se inicia el método eliminarBorrador()");

		if (dtoBorradorSeleccionado != null) {
			try {
				
				this.bsdBorradorDocumentosInterface.eliminarDocumento(dtoBorradorSeleccionado, usuario);

			} catch (Exception e) {

				log.error("<=================== Error al eliminar el documento borrador con idDocumento= "+dtoBorradorSeleccionado.getIdDocumento());
				log.error("<=================== Clase: MBBandejaBorradores , Método: eliminarBorrador");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			listaDTOBorradores = consultarBorradores();

			dtoBorradorSeleccionado = null;

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"),Utilidades.mensajeProperties("mensaje_exito_eliminacion_borrador")));

		}

		log.info(this.getClass().getName() + " Se finaliza el método eliminarBorrador()");
	}

	/**
	 * Método que oculta el dialog de eliminar Borrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void ocultarConfirmacionEliminarBorrador() {
		RequestContext.getCurrentInstance().execute("PF('confirmacionEliminarBorrador').hide()");
	}

	/**
	 * Método que limpia las atributos de la busqueda de personas y busqueda de
	 * Titulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public void limpiar() {
		log.info(this.getClass().getName() + " Se inicia el método limpiar()");
		firmarUnoMismo = false;
		muestraPersonas = true;
		coincidenciaPersonas = null;
		coincidenciaTitulares = null;

		listaEstPersonas = null;
		listaEstPersonasSeleccionadas = null;
		listaEstPersonasTemp = null;

		listaEstTitulares = null;
		listaEstTitularesSeleccionados = null;
		listaEstTitularesTemp = null;

		log.info(this.getClass().getName() + " Se inicia el método limpiar()");
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
			
			this.listaEstPersonas = boBandejaBorradoresInterface.obtenerPersonasBorradorHelper( bsdBorradorDocumentosInterface.consultarPersonasXArea(usuario, coincidenciaPersonas));

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

		log.info(this.getClass().getName()+ " Se finaliza el método buscarPersonas()");
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
			
			listaEstTitulares = boBandejaBorradoresInterface.obtenerPersonasBorradorHelper( this.bsdBorradorDocumentosInterface.consultarEstructurasTitulares(coincidenciaTitulares));

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

//	public void mostrarVentanaDestinatario() {
//		// listaDestinatarios = bsdEjercicioInterface
//		// .consutarPersonasDestinatarios(dtoBorradorSeleccionado);
//		listaRemitentes = new ArrayList<DTOEstructuraAreasEntity>();
//		// listaRemitentes.add(bsdEjercicioInterface
//		// .consultarPersonaXCuenta("armando.calleja"));
//		listaRemitentes.add(usuario);
//
//		RequestContext.getCurrentInstance().execute(
//				"PF('dialog_enviar_destinatario').show()");
//	}

//	public void mostrarVentanaDestinatario(
//			DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
//		// listaDestinatarios = bsdEjercicioInterface
//		// .consutarPersonasDestinatarios(dtoBorradorSeleccionado);
//		setSeleccionoRegistroTabla(false);
//		setBorradorSeleccionadoListaAcciones(dtoBorradorDocumentosEntity);
//
//		listaRemitentes = new ArrayList<DTOEstructuraAreasEntity>();
//		listaRemitentes.add(bsdBandejaSeguimientoInterface
//				.consultarPersonaXCuenta("armando.calleja"));
//		listaRemitentes.add(usuario);
//
//		RequestContext.getCurrentInstance().execute(
//				"PF('dialog_enviar_destinatario').show()");
//	}

//	public void enviarADestinatarios() {
//		enviarADestinatarios(dtoBorradorSeleccionado);
//	}

	/**
	 * Método que envia un documento borrador a los titulares de la lista de
	 * distanatarios y a los ccp del documento
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 20/02/2018
	 */
	public void enviarADestinatarios() {
		try {
			
			this.bsdBorradorDocumentosInterface.enviarDestinatario(dtoBorradorSeleccionado, usuario);

		} catch (Exception e) {

			log.error("<=================== Error al enviar el documento Borrador");
			log.error("<=================== Clase: MBBandejaBorradores , Método: enviarADestinatarios");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
		
		
		listaDTOBorradores = consultarBorradores();

		dtoBorradorSeleccionado = null;

		vhBandejaSeguimientoInterface.mostrarMensajeGrowl(Utilidades.mensajeProperties("constante_growl_exito"), 
														Utilidades.mensajeProperties("titulo_growl_exito"), 
														Utilidades.mensajeProperties("mensaje_exito_borrador_enviado"));
	}

//	/**
//	 * Método que firma el borrador seleccionado por la persona que inició
//	 * sesión.
//	 * 
//	 * @author Homero Fidel Villanueva
//	 * @since 06/10/2017
//	 */
//	public void firmar() {
//		log.info(this.getClass().getName() + " Se inicia el método firmar()");
//
//		if (dtoBorradorSeleccionado != null && usuario != null) {
//
//			bsdBorradorDocumentosInterface.insertarFirma(usuario,
//					dtoBorradorSeleccionado, usuario);
//
//			try {
//				listaDTOBorradores = bsdBorradorDocumentosInterface.consultarBorradores(
//						columnaOrdenamiento, esOrdenamientoAscendente);
//			} catch (Exception e) {
//
//			}
//
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"),
//					"El documento fue firmado con éxito "));
//		}
//
//		log.info(this.getClass().getName() + " Se finaliza el método firmar()");
//	}

	


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

	/**
	 * Método que sirve para seleccionar un borrador y guardarlo en el atributo
	 * del "dtoBorradorSeleccionado".
	 * 
	 * @param event
	 *            es la fila que fue seleccionada por el usuario.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void seleccionarBorrador(SelectEvent event) {
		log.info(this.getClass().getName()
				+ " Se inicia el método seleccionarBorrador()");
		DTOBorradorDocumentosEntity miBorrador = (DTOBorradorDocumentosEntity) event.getObject();
		setSeleccionoRegistroTabla(true);
		if(Utilidades.mensajeProperties("constante_documento_noLeido").equals(""+miBorrador.getNoLeido())){
			try {
				
				this.bsdBorradorDocumentosInterface.notificacionBandejaBorradores(usuario, miBorrador, false);

			} catch (Exception e) {

				log.error("<=================== Error al cargar la información al entrar al sistema");
				log.error("<=================== Clase: MBAdministradorGestion , Método: cargaValoresRegistradoEnGestionParaUsuarioLogueado");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			consultarContadoresMenu();
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			
			
//			MBBandejaMenu menu = ((MBBandejaMenu) ApplicationContextUtils.getApplicationContext().getBean("mbBandejaMenu"));
//			menu.consultarMenu();
		}
		
		
		seleccionarBorrador(miBorrador);

		
	}

	public void seleccionarBorrador(DTOBorradorDocumentosEntity dtoBorrador) {

		if(dtoBorrador != null){
			dtoBorradorSeleccionado = dtoBorrador;
	
			nombreDocumentoPDF = dtoBorradorSeleccionado.getDocumento()
					.getIdDocumento()
					+ "_"
					+ dtoBorradorSeleccionado.getDocumento().getAnio() + ".pdf";
			obtenerDetalleDocumento(dtoBorrador.getDocumento());
			obtenerAnexos();
			obtenerComentarios();
			obtenerEdiciones();
	
			limpiar();
		}
	}
	
	/**
	 * Método que revisa la lista de remitentes y elimina al usuario que nició
	 * sesión
	 * 
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	private List<DTOPersonaBorradoresHelper> obtenerListaRemitentes(){
		List<DTOPersonaBorradoresHelper> nuevaLista = null;
		DTOPersonaBorradoresHelper personaHelper = null;
		if(personasRemitentes != null && personasRemitentes.size() > 1 && usuario != null){
			nuevaLista = new ArrayList<DTOPersonaBorradoresHelper>();
			for (DTOEstructuraAreasEntity remitente : personasRemitentes) {
				
				if(!remitente.equals(usuario)){
					personaHelper = new DTOPersonaBorradoresHelper(remitente);
					personaHelper.setEsRemitente(true);
					nuevaLista.add(personaHelper);
				}
			}
		}
		return nuevaLista;
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo List<DTOBorradorDocumentosEntity> que esta
	 *         contenido en la variable listaDTOBorradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 31/08/2017
	 */
	public List<DTOBorradorDocumentosEntity> getListaDTOBorradores() {
		return listaDTOBorradores;
	}

	/**
	 * @return the listaDTODocumentos
	 */
	public List<DTODocumentoEntity> getListaDTODocumentos() {
		return listaDTODocumentos;
	}

	/**
	 * @param listaDTODocumentos
	 *            the listaDTODocumentos to set
	 */
	public void setListaDTODocumentos(
			List<DTODocumentoEntity> listaDTODocumentos) {
		this.listaDTODocumentos = listaDTODocumentos;
	}

	/**
	 * @return valor de tipo DTOBorradorDocumentosEntity que esta contenido en
	 *         la variable dtoBorradorSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/09/2017
	 */
	public DTOBorradorDocumentosEntity getDtoBorradorSeleccionado() {
		return dtoBorradorSeleccionado;
	}

	/**
	 * @param dtoBorradorSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBorradorDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/09/2017
	 */
	public void setDtoBorradorSeleccionado(
			DTOBorradorDocumentosEntity dtoBorradorSeleccionado) {

		this.dtoBorradorSeleccionado = dtoBorradorSeleccionado;
	}

	/**
	 * @param listaDTOBorradores
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOBorradorDocumentosEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 31/08/2017
	 */
	public void setListaDTOBorradores(
			List<DTOBorradorDocumentosEntity> listaDTOBorradores) {
		this.listaDTOBorradores = listaDTOBorradores;
	}

	/**
	 * @return the muestraPersonas
	 */
	public boolean isMuestraPersonas() {
		return muestraPersonas;
	}

	/**
	 * @param muestraPersonas
	 *            the muestraPersonas to set
	 */
	public void setMuestraPersonas(boolean muestraPersonas) {
		this.muestraPersonas = muestraPersonas;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable remitente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/09/2017
	 */
	public DTOEstructuraAreasEntity getRemitente() {
		return usuario;
	}

	/**
	 * @param remitente
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 29/09/2017
	 */
	public void setRemitente(DTOEstructuraAreasEntity remitente) {
		this.usuario = remitente;
	}

	

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstPersonas() {
		return listaEstPersonas;
	}

	/**
	 * @param listaEstPersonas : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstPersonas(
			List<DTOPersonaBorradoresHelper> listaEstPersonas) {
		this.listaEstPersonas = listaEstPersonas;
	}

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstPersonasTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstPersonasTemp() {
		return listaEstPersonasTemp;
	}

	/**
	 * @param listaEstPersonasTemp : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstPersonasTemp(
			List<DTOPersonaBorradoresHelper> listaEstPersonasTemp) {
		this.listaEstPersonasTemp = listaEstPersonasTemp;
	}

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstPersonasSeleccionadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstPersonasSeleccionadas() {
		return listaEstPersonasSeleccionadas;
	}

	/**
	 * @param listaEstPersonasSeleccionadas : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstPersonasSeleccionadas(
			List<DTOPersonaBorradoresHelper> listaEstPersonasSeleccionadas) {
		this.listaEstPersonasSeleccionadas = listaEstPersonasSeleccionadas;
	}

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstTitulares() {
		return listaEstTitulares;
	}

	/**
	 * @param listaEstTitulares : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstTitulares(
			List<DTOPersonaBorradoresHelper> listaEstTitulares) {
		this.listaEstTitulares = listaEstTitulares;
	}

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstTitularesTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstTitularesTemp() {
		return listaEstTitularesTemp;
	}

	/**
	 * @param listaEstTitularesTemp : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstTitularesTemp(
			List<DTOPersonaBorradoresHelper> listaEstTitularesTemp) {
		this.listaEstTitularesTemp = listaEstTitularesTemp;
	}

	/**
	 * @return valor de tipo List<DTOPersonaBorradoresHelper> que esta contenido en la variable listaEstTitularesSeleccionados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public List<DTOPersonaBorradoresHelper> getListaEstTitularesSeleccionados() {
		return listaEstTitularesSeleccionados;
	}

	/**
	 * @param listaEstTitularesSeleccionados : valor que se ingresa a la variable de tipo List<DTOPersonaBorradoresHelper>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setListaEstTitularesSeleccionados(
			List<DTOPersonaBorradoresHelper> listaEstTitularesSeleccionados) {
		this.listaEstTitularesSeleccionados = listaEstTitularesSeleccionados;
	}

	/**
	 * @return valor de tipo DTOBorradorDocumentosHelper que esta contenido en la variable helperBorradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public DTOBorradorDocumentosHelper getHelperBorradores() {
		return filtroBorradores;
	}

	/**
	 * @param helperBorradores : valor que se ingresa a la variable de tipo DTOBorradorDocumentosHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 01/05/2018
	 */
	public void setHelperBorradores(DTOBorradorDocumentosHelper helperBorradores) {
		this.filtroBorradores = helperBorradores;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         coincidenciaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/10/2017
	 */
	public String getCoincidenciaPersonas() {
		return coincidenciaPersonas;
	}

	/**
	 * @param coincidenciaPersonas
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/10/2017
	 */
	public void setCoincidenciaPersonas(String coincidenciaPersonas) {
		this.coincidenciaPersonas = coincidenciaPersonas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         coincidenciaTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/10/2017
	 */
	public String getCoincidenciaTitulares() {
		return coincidenciaTitulares;
	}

	/**
	 * @param coincidenciaTitulares
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/10/2017
	 */
	public void setCoincidenciaTitulares(String coincidenciaTitulares) {
		this.coincidenciaTitulares = coincidenciaTitulares;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable
	 *         esEnviarValidar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/10/2017
	 */
	public boolean isEsEnviarValidar() {
		return esEnviarValidar;
	}

	/**
	 * @param esEnviarValidar
	 *            : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/10/2017
	 */
	public void setEsEnviarValidar(boolean esEnviarValidar) {
		this.esEnviarValidar = esEnviarValidar;
	}
	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable personaBorrar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public DTOEstructuraAreasEntity getPersonaBorrar() {
		return personaBorrar;
	}

	/**
	 * @param personaBorrar
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public void setPersonaBorrar(DTOEstructuraAreasEntity personaBorrar) {
		this.personaBorrar = personaBorrar;
	}

	/**
	 * @return valor de tipo List<DTOEdicionesDocumentoEntity> que esta
	 *         contenido en la variable listaEdicionesNoLeidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public List<DTOEdicionesDocumentoEntity> getListaEdicionesNoLeidos() {
		return listaEdicionesNoLeidos;
	}

	/**
	 * @param listaEdicionesNoLeidos
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEdicionesDocumentoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public void setListaEdicionesNoLeidos(
			List<DTOEdicionesDocumentoEntity> listaEdicionesNoLeidos) {
		this.listaEdicionesNoLeidos = listaEdicionesNoLeidos;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaDestinatarios
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaDestinatarios() {
		return listaDestinatarios;
	}

	/**
	 * @param listaDestinatarios
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/11/2017
	 */
	public void setListaDestinatarios(
			List<DTOEstructuraAreasEntity> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}


	/**
	 * @return valor de tipo List<DTODocumentoAnexoEntity> que esta contenido en
	 *         la variable listaAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/11/2017
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
	 * @since 09/11/2017
	 */
	public void setListaAnexos(List<DTODocumentoAnexoEntity> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

//	/**
//	 * @return valor de tipo DTOBorradorDocumentosEntity que esta contenido en
//	 *         la variable borradorSeleccionadoListaAcciones
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 15/11/2017
//	 */
//	public DTOBorradorDocumentosEntity getBorradorSeleccionadoListaAcciones() {
//		return borradorSeleccionadoListaAcciones;
//	}
//
//	/**
//	 * @param borradorSeleccionadoListaAcciones
//	 *            : valor que se ingresa a la variable de tipo
//	 *            DTOBorradorDocumentosEntity
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 15/11/2017
//	 */
//	public void setBorradorSeleccionadoListaAcciones(
//			DTOBorradorDocumentosEntity borradorSeleccionadoListaAcciones) {
//		this.borradorSeleccionadoListaAcciones = borradorSeleccionadoListaAcciones;
//	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable
	 *         seleccionoRegistroTabla
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public boolean isSeleccionoRegistroTabla() {
		return seleccionoRegistroTabla;
	}

	/**
	 * @param seleccionoRegistroTabla
	 *            : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 16/11/2017
	 */
	public void setSeleccionoRegistroTabla(boolean seleccionoRegistroTabla) {
		this.seleccionoRegistroTabla = seleccionoRegistroTabla;
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
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaRemitentes
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaRemitentes() {
		return listaRemitentes;
	}

	/**
	 * @param listaRemitentes
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public void setListaRemitentes(
			List<DTOEstructuraAreasEntity> listaRemitentes) {
		this.listaRemitentes = listaRemitentes;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable remitenteSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public DTOEstructuraAreasEntity getRemitenteSeleccionado() {
		return remitenteSeleccionado;
	}

	/**
	 * @param remitenteSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 17/11/2017
	 */
	public void setRemitenteSeleccionado(
			DTOEstructuraAreasEntity remitenteSeleccionado) {
		this.remitenteSeleccionado = remitenteSeleccionado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable numeroDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoCaptura
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public String getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * @param tipoCaptura : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setTipoCaptura(String tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public DTOEstructuraAreasEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario : valor que se ingresa a la variable de tipo DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable firmarUnoMismo
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public boolean isFirmarUnoMismo() {
		return firmarUnoMismo;
	}

	/**
	 * @param firmarUnoMismo : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 22/01/2018
	 */
	public void setFirmarUnoMismo(boolean firmarUnoMismo) {
		this.firmarUnoMismo = firmarUnoMismo;
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
	 * @since 03/05/2018
	 */
	public DTODetalleDocumentoHelper getDetalleDocumentoHelper() {
		return detalleDocumentoHelper;
	}

	/**
	 * @param detalleDocumentoHelper : valor que se ingresa a la variable de tipo DTODetalleDocumentoHelper
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/05/2018
	 */
	public void setDetalleDocumentoHelper(
			DTODetalleDocumentoHelper detalleDocumentoHelper) {
		this.detalleDocumentoHelper = detalleDocumentoHelper;
	}

}
