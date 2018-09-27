/**
 * MBBandeja.java 30/08/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoAnexoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEdicionesDocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.util.ZipFixer;





import mx.org.ine.servicios.utils.PaginarLazy;

import org.jboss.logging.Logger;

import java.io.InputStream;

import org.opengis.metadata.quality.Usability;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Managebean encargado encargado del módulo de la Bandeja de Recepción
 * (Pantalla Borradores)
 * 
 * @author Homero Villanueva Dominguez
 * @since 12/07/2017
 */
public class MBBandejaOficialia implements Serializable {

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
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger
			.getLogger(MBBandejaOficialia.class);

	/**
	 * Lista de objetos de Borradores
	 */
	private List<DTOBorradorDocumentosEntity> listaDTOBorradores;
	
	/**
	 * Lista de objetos pertenecientes a la bandeja de entrada de oficialia
	 */
	private List<DTOBandejaEntradasOficialiaEntity> listaDTOOficialia;
	
	/**
	 * Variable que contiene la lista de indicadores disponibles
	 * para configurar en un año
	 */
	private PaginarLazy<DTOBandejaEntradasOficialiaEntity, Integer> listaIndicadoresDisponibles;

	/**
	 * Lista de objetos de Documentos
	 */
	private List<DTODocumentoEntity> listaDTODocumentos;

	private int idDocumento;

	/**
	 * Borrador seleccionado por el usuario
	 */
	private DTOBorradorDocumentosEntity dtoBorradorSeleccionado;

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

	/**
	 * Lista que guarda todos los comentarios leidos de un determinado borrador.
	 */
	private List<DTOComentariosDocumentoEntity> listaComentariosLeidos;

	/**
	 * Lista que guarda todos los comentarios no leidos de un determinado
	 * borrador.
	 */
	private List<DTOComentariosDocumentoEntity> listaComentariosNoLeidos;

	/**
	 * 
	 */
	private List<DTOEdicionesDocumentoEntity> listaEdicionesNoLeidos;

	private List<DTOEstructuraAreasEntity> listaDestinatarios;

	private List<DTODocumentoAnexoEntity> listaAnexos;
	/**
	 * Objeto que guarda la estructura de la persona que inició sesión.
	 */
	private DTOEstructuraAreasEntity remitente;

	/**
	 * Atributo que guarda el nombre de la columna por el cual se ordenará la
	 * lista de Documentos Borradores.
	 */
	private String columnaOrdenamiento;

	/**
	 * Atributo que guarda el tipo de ordenamiernto. Si está en true es
	 * ascendente, si está en false es descendente.
	 */
	private boolean esOrdenamientoAscendente;

	/**
	 * Atributo utilizado en el dialog_enviar_fv. Sirve para indicar que vista
	 * de busqueda se mostrará. Si es true muestra el outputpanel para buscar
	 * personas. Si es false muestra el outputpanel para buscar titulares.
	 */
	private boolean muestraPersonas;

	/**
	 * Atributo que guarda la ruta en el luster del Documento Borrador.
	 */
	private String rutaDocumento;

	/**
	 * Atributo que guarda la ruta en el luster del Documento Borrador.
	 */
	private String rutaAnexos;

	private StreamedContent file;

	/**
	 * 
	 */
	private String rutaPrueba;

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
	 * Atributo que guarda cero si no se cuenta con firma o validacion del
	 * borrador seleccionado. en caso que guarde algun número mayor a 0
	 * indicaría que el borrador seleccionado cuenta con Firma o validación.
	 */
	private int puedeRealizarAccion;

	/**
	 * Atributo que guarda el mensaje que será mostrado cuando las tablas no
	 * tengan registros
	 */
	private String mensajeTablaVacia = Utilidades
			.mensajeProperties("mensaje_general_tablaVacia");

	/**
	 * Atributo que guarda el mensaje cuando no se obtuvo ningun documento
	 * borrador
	 */
	private String mensajeSinborradores;

	/**
	 * 
	 */
	private DTOEstructuraAreasEntity personaBorrar;

	// ------------------------ MENSAJES ------------------------ //
	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Aceptar
	 */
	private String mensajeAceptar;

	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Cancelar
	 */
	private String mensajeCancelar;

	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Buscar
	 */
	private String mensajeBuscar;

	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Agregar
	 */
	private String mensajeAgregar;

	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Enviar
	 */
	private String mensajeEnviar;

	/**
	 * Atributo que guarda el mensaje que se muestra cuando el usuario quiere
	 * eliminar un borrador.
	 */
	private String mensajeConfimacionEliminarBorrador;

	/**
	 * Atributo que guarda el mensaje que aparece en la notificación de
	 * comentado
	 */
	private String mensajeNotificacionComentado;

	/**
	 * Atributo que guarda el mensaje que aparece en la notificación de Edición
	 */
	private String mensajeNotificacionEditado;

	/**
	 * Atributo que guarda el mensaje que aparece en la notificación de Firmado
	 */
	private String mensajeNotificacionFirmado;

	/**
	 * Atributo que guarda el mensaje que aparece en la notificación de Validado
	 */
	private String mensajeNotificacionValidado;

	/**
	 * Atributo que guarda el mensaje que se mostrará cuando el navegador del
	 * usuarion no sea capaz de mostrar el PDF.
	 */
	private String mensajeErrorNavegadorPDF;

	/**
	 * Atributo que guarda el mensaje para indicarle al usuario de donde puede
	 * desacargar el pdf que no se pudo visualizar.
	 */
	private String mensajeSolucionNavegadorPDF;

	/**
	 * Atributo que guarda el mensaje que aparecerá en la cabecera del dialog
	 * para eliminar un documento borrador
	 */
	private String mensajeTituloEliminarBorrador;

	/**
	 * Atributo que guarda el mensaje que aparecerá en la cabecera del dialog
	 * cuando se quiere enviar a firmar
	 */
	private String mensajeTituloEnviarFirmar;

	/**
	 * Atributo que guarda el mensaje que aparecerá en la cabecera del dialog
	 * cuando se quiere enviar a Validar
	 */
	private String mensajeTituloEnviarValidar;

	/**
	 * Atributo que guarda el mensaje que aparecerá en la pestaña que selecciona
	 * la busqueda por personas del Área
	 */
	private String mensajePestanaBusquedaArea;

	/**
	 * Atributo que guarda el mensaje que aparecerá en la pestaña que selecciona
	 * la busqueda por Titulares
	 */
	private String mensajePestanaBusquedaTitulares;

	/**
	 * Atributo que guarda el mensaje para cuando no se ha buscado ninguna
	 * persona del área
	 */
	private String mensajeSinBusquedaPersonas;

	/**
	 * Atributo que guarda el mensaje para cuando no se ha agregado ninguna
	 * persona de la busqueda
	 */
	private String mensajeSinPersonasAgregadas;

	/**
	 * Atributo que guarda el mensaje para cuando no se ha buscado ninguna área
	 * (titular)
	 */
	private String mensajeSinBusquedaTitulares;

	/**
	 * Atributo que guarda el mensaje para cuando no se ha agregado ninguna área
	 * (titular) de la busqueda
	 */
	private String mensajeSinTitularesAgregados;

	// ------------------------ Atributos DI------------------------ //
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdEjercicioInterface;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boArchivo")
	private transient BOArchivoInteface boArchivoInteface;
	
	@Autowired
	@Qualifier("bsdBandejaOficialia")
	private transient BSDBandejaOficialiaInterface bsdBandejaOficialiaInterface;

	// ------------------------ METÓDOS ------------------------ //

	/**
	 * 
	 * Método para inicializar las variables necesarias para mostrar la pantalla
	 * cuando se entra al módulo.
	 *
	 * @since 07/09/2017
	 */
	public void iniciar() {

		llamarLog("Inició el método iniciar()");

		if (idDocumento > 0) {
			mostrarMensajeCaptura();
		}
		iniciarMensajes();
		
		//this.listaDTOOficialia = bsdBandejaOficialiaInterface.consultarBandeja(391);
		//listaIndicadoresDisponibles = 
			//	new PaginarLazy<DTOBandejaEntradasOficialiaEntity, Integer>("bsdBandejaOficialia",  391);
		
		/*
		remitente = bsdEjercicioInterface
				.consultarPersonaXCuenta(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		*/
		
		
		listaDTOBorradores = consultarBorradores("fecha", false);

		if (listaDTOBorradores != null && listaDTOBorradores.size() > 0) {

			llamarLog(" No se tienen Documentos Borradores en la BD");

			dtoBorradorSeleccionado = listaDTOBorradores.get(0);
//			listaDestinatarios = bsdEjercicioInterface.consutarPersonasDestinatarios(dtoBorradorSeleccionado);

			setRutaDocumento(dtoBorradorSeleccionado);
			rutaAnexos = boArchivoInteface.obtenerRutaGlusterAnexosNube()
					+ File.separator;
			// InputStream stream = FacesContext.getCurrentInstance()
			// .getExternalContext().getResourceAsStream(rutaDocumento);
			// file = new DefaultStreamedContent(stream, "application/pdf",
			// "downloaded_optimus.pdf","UTF-8");

			obtenerAnexos();
			actualizarComentarios();
			actualizarEdiciones();
			revisarMenuAcciones();

			muestraPersonas = true;

			esEnviarValidar = false;
		}
		log.info(this.getClass().getName()
				+ " Finaliza con éxito el método iniciar() ");
	}

	/**
	 * Método que inicializa todos los mensajes utilizados en la pantalla.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void iniciarMensajes() {
		mensajeSinborradores = Utilidades
				.mensajeProperties("mensaje_tabla_borradores_vacia");

		mensajeConfimacionEliminarBorrador = Utilidades
				.mensajeProperties("mensaje_confirmacion_eliminar_borrador");

		mensajeNotificacionComentado = Utilidades
				.mensajeProperties("mensaje_notificacion_comentado");

		mensajeNotificacionEditado = Utilidades
				.mensajeProperties("mensaje_notificacion_editado");

		mensajeNotificacionFirmado = Utilidades
				.mensajeProperties("mensaje_notificacion_firmado");

		mensajeNotificacionValidado = Utilidades
				.mensajeProperties("mensaje_notificacion_comentado");

		mensajeErrorNavegadorPDF = Utilidades
				.mensajeProperties("mensaje_error_navegador_pdf");

		mensajeSolucionNavegadorPDF = Utilidades
				.mensajeProperties("mensaje_solucion_navegador_pdf");

		mensajeTituloEliminarBorrador = Utilidades
				.mensajeProperties("mensaje_titulo_eliminar_borrador");

		mensajeAceptar = Utilidades.mensajeProperties("etiqueta_boton_aceptar");

		mensajeCancelar = Utilidades
				.mensajeProperties("etiqueta_boton_cancelar");

		mensajeBuscar = Utilidades.mensajeProperties("etiqueta_boton_buscar");

		mensajeAgregar = Utilidades.mensajeProperties("etiqueta_boton_agregar");

		mensajeEnviar = Utilidades.mensajeProperties("etiqueta_boton_enviar");

		mensajeTituloEnviarFirmar = Utilidades
				.mensajeProperties("mensaje_titulo_enviar_firma");

		mensajeTituloEnviarValidar = Utilidades
				.mensajeProperties("mensaje_titulo_enviar_validacion");

		mensajePestanaBusquedaArea = Utilidades
				.mensajeProperties("mensaje_pestana_busqueda_por_area");

		mensajePestanaBusquedaTitulares = Utilidades
				.mensajeProperties("mensaje_pestana_busqueda_por_titulares");

		mensajeSinBusquedaPersonas = Utilidades
				.mensajeProperties("mensaje_no_busqueda_personas");

		mensajeSinPersonasAgregadas = Utilidades
				.mensajeProperties("mensaje_no_agregado_personas");

		mensajeSinBusquedaTitulares = Utilidades
				.mensajeProperties("mensaje_no_busqueda_titulares");

		mensajeSinTitularesAgregados = Utilidades
				.mensajeProperties("mensaje_no_agregado_titulares");

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
			dtoBorradorSeleccionado.setConComentarios(0);
			
			try {
				bsdEjercicioInterface.actualizarBorrador(dtoBorradorSeleccionado);
			} catch (Exception e) {
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
			dtoBorradorSeleccionado.setConModificaciones(0);
			try {
				bsdEjercicioInterface.actualizarBorrador(dtoBorradorSeleccionado);
			} catch (Exception e) {
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
			dtoBorradorSeleccionado.setConFirma(0);
			
			try {
				bsdEjercicioInterface.actualizarBorrador(dtoBorradorSeleccionado);
			} catch (Exception e) {
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
			dtoBorradorSeleccionado.setConValidacion(0);
			try {
				bsdEjercicioInterface.actualizarBorrador(dtoBorradorSeleccionado);
			} catch (Exception e) {
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
		listaDTOBorradores = consultarBorradores(columnaOrdenamiento,
				esOrdenamientoAscendente);
	}

	/**
	 * Método que muestra el dialog de Anexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarDialogAnexos() {
		RequestContext.getCurrentInstance().execute(
				"PF('dialogVerAnexos').show()");
	}

	/**
	 * Método que consulta los Anexos del borrador seleccionado. Sólo cuando la
	 * bandera de contieneAnexos este prendia en 1.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void obtenerAnexos() {
		if (dtoBorradorSeleccionado != null&& dtoBorradorSeleccionado.getDocumento().getContieneAnexos() > 0) {
//			listaAnexos = bsdEjercicioInterface.consultarAnexos(dtoBorradorSeleccionado);
		}
	}

	/**
	 * Método que muestra el mensaje de éxito cuando se ha creado un documento.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarMensajeCaptura() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(
				null,
				new FacesMessage(
						FacesMessage.SEVERITY_FATAL,
						"MENSAJE ÉXITO",
						Utilidades
								.mensajeProperties("mensaje_exito_creacion_documento")));

		idDocumento = 0;
	}

	/**
	 * Método que manda llamar el log
	 * 
	 * @param mensaje
	 *            : mensaje que se mostrará en el log
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void llamarLog(String mensaje) {
		log.info(this.getClass().getName() + " " + mensaje);
	}

	/**
	 * Método que actualiza el estatus de las ediciones que ya han sido vistas a
	 * 0.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void actualizarEdiciones() {
		try {
			
//			listaEdicionesNoLeidos = bsdEjercicioInterface.obtenerListaEdiciones(dtoBorradorSeleccionado.getIdDocumento(), 0);
			quitarNotificacionEditado();
		} catch (Exception e) {
			// TODO: handle exception
		}
		;

	}

	/**
	 * Método que busca los comentarios leídos y no leídos de el borrador
	 * seleccionado.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void actualizarComentarios() {
//		try {
//			listaComentariosLeidos = bsdBandejaSeguimientoInterface.consultarComentariosLeidos(dtoBorradorSeleccionado.getDocumento());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//
//		try {
//			listaComentariosNoLeidos = bsdBandejaSeguimientoInterface.consultarComentariosNoLeidos(dtoBorradorSeleccionado.getDocumento());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
	}

	/**
	 * Método que cambia el estatus de los comentarios ya leídos a 0.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void leerComentarios() {
//		if (dtoBorradorSeleccionado != null) {
//			try {
//				bsdEjercicioInterface.leerComentarios(dtoBorradorSeleccionado,listaComentariosNoLeidos);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			quitarNotificacionComentado();
//			actualizarComentarios();
//		}

	}

	public void leerEdiciones() {
		System.out.print("Hola");
		// bsdEjercicioInterface.leerComentarios(listaComentariosNoLeidos);
		actualizarEdiciones();
		System.out.print("Adios");
	}

	/**
	 * Método que elimina una persona que ha sido buscada y agregada para enviar
	 * a firmar o validar.
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarPersona(DTOEstructuraAreasEntity persona) {
		if (persona != null && listaEstPersonasSeleccionadas != null
				&& listaEstPersonasSeleccionadas.size() > 0) {
			listaEstPersonasSeleccionadas.remove(persona);
			listaEstPersonasTemp.remove(persona);
		}

	}

	/**
	 * Método que elimina una área(titular) que ha sido buscada y agregada para
	 * enviar a firmar o validar.
	 * 
	 * @param persona
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarTitular(DTOEstructuraAreasEntity persona) {
		if (persona != null && listaEstTitularesSeleccionados != null
				&& listaEstTitularesSeleccionados.size() > 0) {
			// listaEstPersonasSeleccionadas.remove(persona);
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
		log.info(this.getClass().getName()
				+ " Se inicia el método ordenarDocumentosPorNombre()");

		listaDTOBorradores = consultarBorradores("nombre",
				!esOrdenamientoAscendente);

		log.info(this.getClass().getName()
				+ " Se termina el método ordenarDocumentosPorNombre()");
	}

	/**
	 * Método que actualiza la lista de borradores por fecha.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void ordenarDocumentosPorFecha() {
		log.info(this.getClass().getName()
				+ " Se inicia el método ordenarDocumentosPorFecha()");

		listaDTOBorradores = consultarBorradores("fecha",
				!esOrdenamientoAscendente);

		log.info(this.getClass().getName()
				+ " Se inicia el método ordenarDocumentosPorFecha()");
	}

	/**
	 * Método que muestra el dialog de ediciones y actualiza la lista de
	 * ediciones
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void muestraEdiciones() {

		RequestContext.getCurrentInstance().execute(
				"PF('dialog_revisar_ediciones').show()");

		actualizarEdiciones();
		log.info(this.getClass().getName()
				+ " Se finaliza el método muestraComentarios()");

	}

	/**
	 * Método que muestra el dialog para los comentarios y actualiza los
	 * comentarios.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void muestraComentarios() {
		log.info(this.getClass().getName()
				+ " Se inicia el método muestraComentarios()");

		RequestContext.getCurrentInstance().execute(
				"PF('dialog_revisar_comentarios').show()");

		actualizarComentarios();
		log.info(this.getClass().getName()
				+ " Se finaliza el método muestraComentarios()");
	}

	/**
	 * Método que muestra el dialog para enviar a Firmar.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarEnviarFirmar() {
		log.info(this.getClass().getName()
				+ " Se inicia el método mostrarEnviarFirmar()");

		setEsEnviarValidar(false);
		RequestContext.getCurrentInstance().execute(
				"PF('dialog_enviar_fv').show()");

		log.info(this.getClass().getName()
				+ " Se termina el método mostrarEnviarFirmar()");
	}

	/**
	 * Método que muestra el dialog para enviar a Validar.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void mostrarEnviarValidar() {
		log.info(this.getClass().getName()
				+ " Se inicia el método mostrarEnviarValidar()");

		setEsEnviarValidar(true);
		RequestContext.getCurrentInstance().execute(
				"PF('dialog_enviar_fv').show()");
		log.info(this.getClass().getName()
				+ " Se finaliza el método mostrarEnviarValidar()");
	}

	/**
	 * Método que muestra el dialog de confirmación para borrar un borrador.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void confirmarEliminarBorrador() {
		RequestContext.getCurrentInstance().execute(
				"PF('confirmacionEliminarBorrador').show()");
	}

	/**
	 * Método que elimina un borrador de la lista de borradores y lo elimina
	 * lógicamente de la la BD
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void eliminarBorrador() {
		log.info(this.getClass().getName()
				+ " Se inicia el método eliminarBorrador()");

		if (dtoBorradorSeleccionado != null) {
			
//			try {
//				bsdEjercicioInterface.eliminarDocumento(dtoBorradorSeleccionado,);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			
			listaDTOBorradores = consultarBorradores(columnaOrdenamiento,
					esOrdenamientoAscendente);

			dtoBorradorSeleccionado = null;

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_FATAL,
							"MENSAJE ÉXITO",
							Utilidades
									.mensajeProperties("mensaje_exito_eliminacion_borrador")));

		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método eliminarBorrador()");
	}

	/**
	 * Método que oculta el dialog de eliminar Borrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void cancelarEliminacionBorrador() {
		RequestContext.getCurrentInstance().execute(
				"PF('confirmacionEliminarBorrador').hide()");
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
	 * Método que guarda los registros temporales de las estructuras personas en
	 * la lista de personas seleccionadas para posteriormente ser guardados en
	 * el servidor.
	 *
	 * @author Homero Fidel Villanueva
	 * @since 05/10/2017
	 */
	public void cambiarListaTitularesTemp() {
		log.info(this.getClass().getName()
				+ " Se inicia el método cambiarListaTitularesTemp()");

		if (listaEstTitularesSeleccionados != null
				&& listaEstTitularesTemp != null
				&& listaEstTitularesSeleccionados.size() > 0) {

			for (DTOEstructuraAreasEntity elemento : listaEstTitularesTemp) {
				if (!listaEstTitularesSeleccionados.contains(elemento)) {
					listaEstTitularesSeleccionados.add(elemento);
				}
			}
		} else if (listaEstTitularesTemp == null
				|| listaEstTitularesTemp.size() == 0) {

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "MENSAJE INFORMATIVO",
					"Debe seleccionar algun titular para poder agregar!!!"));
		} else {
			listaEstTitularesSeleccionados = listaEstTitularesTemp;
		}

		log.info(this.getClass().getName()
				+ " Se termina el método cambiarListaTitularesTemp()");
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

			for (DTOEstructuraAreasEntity elemento : listaEstPersonasTemp) {
				if (!listaEstPersonasSeleccionadas.contains(elemento)) {
					listaEstPersonasSeleccionadas.add(elemento);
				}
			}
		} else if (listaEstPersonasTemp == null
				|| listaEstPersonasTemp.size() == 0) {

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "MENSAJE INFORMATIVO",
					"Debe seleccionar alguna persona para poder agregar!!!"));
		} else {
			listaEstPersonasSeleccionadas = listaEstPersonasTemp;
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método cambiarListaPersonasTemp()");
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
		log.info(this.getClass().getName()
				+ " Se inicia el método buscarPersonas()");
		try {
			//listaEstPersonas = bsdEjercicioInterface.consultarPersonasXArea( coincidenciaPersonas);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (listaEstPersonas.size() < 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "MENSAJE INFORMATIVO",
					"Su busqueda no encontró resultados"));
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método buscarPersonas()");
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
		log.info(this.getClass().getName()
				+ " Se inicia el método buscarTitulares()");
		try {
			listaEstTitulares = bsdEjercicioInterface.consultarEstructurasTitulares(coincidenciaTitulares);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		if (listaEstTitulares.size() < 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "MENSAJE INFORMATIVO",
					"Su busqueda no encontró resultados"));
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método buscarTitulares()");
	}

	public void abrirVentanaDestinatario() {
//		listaDestinatarios = bsdEjercicioInterface.consutarPersonasDestinatarios(dtoBorradorSeleccionado);
		RequestContext.getCurrentInstance().execute(
				"PF('dialog_enviar_destinatario').show()");
	}

	public void enviarADestinatarios() {
		try {
			bsdEjercicioInterface.enviarDestinatario(dtoBorradorSeleccionado,remitente);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		listaDTOBorradores = consultarBorradores(columnaOrdenamiento,
				esOrdenamientoAscendente);
		dtoBorradorSeleccionado = null;
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
				"MENSAJE ÉXITO", "Se envió el documento con éxito"));
	}

	/**
	 * Método que firma el borrador seleccionado por la persona que inició
	 * sesión.
	 * 
	 * @author Homero Fidel Villanueva
	 * @since 06/10/2017
	 */
	public void firmar() {
//		log.info(this.getClass().getName() + " Se inicia el método firmar()");
//
//		if (dtoBorradorSeleccionado != null && remitente != null) {
//			try {
//				//bsdEjercicioInterface.enviarAFirmar(listaDestinatarios, DTOBorradorDocumentosEntity, remitente);
//				//enviarAFir(remitente, dtoBorradorSeleccionado, remitente);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//
//			try {
//				listaDTOBorradores = bsdEjercicioInterface.consultarBorradores(
//						columnaOrdenamiento, esOrdenamientoAscendente);
//			} catch (Exception e) {
//
//			}
//
//			revisarMenuAcciones();
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_FATAL, "MENSAJE ÉXITO",
//					"El documento fue firmado con éxito "));
//		}
//
//		log.info(this.getClass().getName() + " Se finaliza el método firmar()");
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
	 */
	public void enviarFirmar() {
//		log.info(this.getClass().getName()
//				+ " Se inicia el método enviarFirmar()");
//
//		log.info("Inicio el método(enviarFirmar)");
//		log.info("\tEl documento con ID: "
//				+ dtoBorradorSeleccionado.getIdDocumento()
//				+ "se envio a firmar");
//		boolean seSeleccionoAlguno = false;
//
//		if (listaEstPersonasSeleccionadas != null
//				&& listaEstPersonasSeleccionadas.size() > 0) {
//			try {
//				bsdEjercicioInterface.enviarAFirmar(listaEstPersonasSeleccionadas, dtoBorradorSeleccionado, remitente);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			seSeleccionoAlguno = true;
//		}
//
//		if (listaEstTitularesSeleccionados != null
//				&& listaEstTitularesSeleccionados.size() > 0) {
//			try {
//				bsdEjercicioInterface.enviarAFirmar(listaEstTitularesSeleccionados, dtoBorradorSeleccionado, remitente);				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			seSeleccionoAlguno = true;
//		}
//
//		if (!seSeleccionoAlguno) {
//			String mensaje = "No has seleccionado ninguna persona.";
//			if (!muestraPersonas) {
//				mensaje = "No has seleccionado el área.";
//			}
//			FacesContext context = FacesContext.getCurrentInstance();
//
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_WARN, "MENSAJE ADVERTENCIA", mensaje));
//		} else {
//			RequestContext.getCurrentInstance().execute(
//					"PF('dialog_enviar_fv').hide()");
//			revisarMenuAcciones();
//
//			try {
//				listaDTOBorradores = bsdEjercicioInterface.consultarBorradores(columnaOrdenamiento, esOrdenamientoAscendente);
//			} catch (Exception e) {
//				log.error(this.getClass().getName()
//						+ " Sucedió algo al intentar consultar los Documentos Borradores de la BD\n "
//						+ e.getMessage());
//			}
//
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_FATAL, "MENSAJE ÉXITO",
//					"Se envió el documento a firmar con éxito "));
//		}
//		log.info(this.getClass().getName()
//				+ " Se finaliza el método enviarFirmar()");
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
	 */
	public void enviarValidar() {
		log.info(this.getClass().getName()
				+ " Se inicia el método enviarValidar()");

//		boolean seSeleccionoAlguno = false;
//
//		if (listaEstPersonasSeleccionadas != null
//				&& listaEstPersonasSeleccionadas.size() > 0) {
//			try {
//				bsdEjercicioInterface.enviarValidar(listaEstPersonasSeleccionadas, dtoBorradorSeleccionado, remitente);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			seSeleccionoAlguno = true;
//
//		}
//
//		if (listaEstTitularesSeleccionados != null
//				&& listaEstTitularesSeleccionados.size() > 0) {
//			try {
//				bsdEjercicioInterface.enviarValidar(listaEstTitularesSeleccionados, dtoBorradorSeleccionado, remitente);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//
//			seSeleccionoAlguno = true;
//		}
//
//		if (!seSeleccionoAlguno) {
//			String mensaje = "No has seleccionado ninguna persona.";
//			if (!muestraPersonas) {
//				mensaje = "No has seleccionado el área.";
//			}
//			FacesContext context = FacesContext.getCurrentInstance();
//
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_WARN, "MENSAJE ADVERTENCIA", mensaje));
//		} else {
//			revisarMenuAcciones();
//			try {
//				listaDTOBorradores = bsdEjercicioInterface.consultarBorradores(columnaOrdenamiento, esOrdenamientoAscendente);
//			} catch (Exception e) {
//
//				log.info(this.getClass().getName()
//						+ " Sucedió algo al intentar consultar los Documentos Borradores de la BD\n "
//						+ e.getMessage());
//
//			}
//			RequestContext.getCurrentInstance().execute(
//					"PF('dialog_enviar_fv').hide()");
//
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_FATAL, "MENSAJE ÉXITO",
//					"Se envió el documento a validar con éxito "));
//		}
//		log.info(this.getClass().getName()+ " Se finaliza el método enviarValidar()");
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
	public List<DTOBorradorDocumentosEntity> consultarBorradores(
			String columnaOrdenamiento, boolean esOrdenamientoAscendente) {
//		log.info(this.getClass().getName()
//				+ " Se finaliza el método consultarBorradores()");
//		this.columnaOrdenamiento = columnaOrdenamiento;
//		this.esOrdenamientoAscendente = esOrdenamientoAscendente;
//		try {
//
//			return bsdEjercicioInterface.consultarBorradores(this.columnaOrdenamiento, this.esOrdenamientoAscendente);
//
//		} catch (Exception e) {
//			log.info("<=================== Clase: MBBandeja , Método: consultarBorradores");
//			log.info("<=================== ocurrio un error al tratar de obtener la lista de Borradores de la BD. ");
//			log.info("<=================== USUARIO LOGUEADO: "
//					+ SecurityContextHolder.getContext().getAuthentication()
//							.getName());
//			log.info("", e);
//			return null;
//		}
		return null;

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

	public void seleccionarPersonaABorrar(SelectEvent event) {
		personaBorrar = (DTOEstructuraAreasEntity) event.getObject();

		System.out.print("");
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
		seleccionarBorrador((DTOBorradorDocumentosEntity) event.getObject());

	}

	public void seleccionarBorrador(DTOBorradorDocumentosEntity dtoBorrador) {

		dtoBorradorSeleccionado = dtoBorrador;
		setRutaDocumento(dtoBorradorSeleccionado);

		rutaPrueba = boArchivoInteface.obtenerRutaGlusterPdfNube()
				+ File.separator + "abc.jpg";

		// rutaDocumento = "https://w3.ual.es/~egallego/textos/que_cf.pdf";
		obtenerAnexos();
		revisarMenuAcciones();
		actualizarComentarios();
		actualizarEdiciones();

		limpiar();
		/*
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * 
		 * context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		 * "MENSAJE INFORMATIVO", "Se seleccionó el documento con ID..." +
		 * dtoBorradorSeleccionado.getIdDocumento().toString()));
		 * log.info(this.getClass().getName() +
		 * " Se finaliza el método seleccionarBorrador()");
		 */
	}

	/**
	 * Método que recibe un objeto Borrador y revisa si este borrdor fue enviado
	 * a Firmar o a Validar. Se actualiza la variable tieneFirmaOValidacion en
	 * la cual se guardan el número de personas a las que se les envió a Firmar
	 * o Validar.
	 * 
	 * @param dtoBorradorDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public void revisarMenuAcciones() {
		llamarLog("Se inicia el método revisarMenuAcciones");

		if (dtoBorradorSeleccionado != null) {
			if (dtoBorradorSeleccionado.getDocumento().getTipoCaptura() == 3) {
				puedeRealizarAccion = 0;
			} else {
				puedeRealizarAccion = (dtoBorradorSeleccionado
						.getNumEnviadoFirma() == null) ? 0
						: dtoBorradorSeleccionado.getNumEnviadoFirma();

				if (puedeRealizarAccion == 0) {
					puedeRealizarAccion = (dtoBorradorSeleccionado
							.getNumEnviadoValidacion() == null) ? 0
							: dtoBorradorSeleccionado.getNumEnviadoValidacion();
				}

			}
		}

		log.info(this.getClass().getName()
				+ " Se finaliza el método revisarFirmaOValidacion()");
	}

//	/**
//	 * Método que agrega un borrador la BD.
//	 * 
//	 * @param dtoBorrador
//	 *            : Es el borrador que será guardado en la BD.
//	 *
//	 * @author Homero Fidel Villanueva
//	 * @since 05/10/2017
//	 */
//	public void agregarBorrador(DTOBorradorDocumentosEntity dtoBorrador) {
//		log.info("HOLA MB agregarBorrador");
//		// this.dtoBorrador = dtoBorrador;
//		this.dtoBorradorSeleccionado.setIdPersona(1);
//		this.dtoBorradorSeleccionado.setIdDocumento(5);
//		this.dtoBorradorSeleccionado.setAnio(2017);
//		this.dtoBorradorSeleccionado.setConModificaciones(0);
//		this.dtoBorradorSeleccionado.setConComentarios(0);
//		this.dtoBorradorSeleccionado.setConFirma(0);
//		this.dtoBorradorSeleccionado.setConValidacion(0);
//		this.dtoBorradorSeleccionado.setUsuario("tany.cid");
//		this.dtoBorradorSeleccionado.setFechaHora(new Date());
//		try {
//			bsdEjercicioInterface.agregarBorrador(dtoBorrador);
//		} catch (Exception e) {
//			log.info("Error Agreee\n" + e.getMessage());
//		}
//
//	}

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
		return remitente;
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
		this.remitente = remitente;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonas() {
		return listaEstPersonas;
	}

	/**
	 * @param listaEstPersonas
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstPersonas(
			List<DTOEstructuraAreasEntity> listaEstPersonas) {
		this.listaEstPersonas = listaEstPersonas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstPersonasSeleccionadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonasSeleccionadas() {
		return listaEstPersonasSeleccionadas;
	}

	/**
	 * @param listaEstPersonasSeleccionadas
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstPersonasSeleccionadas(
			List<DTOEstructuraAreasEntity> listaEstPersonasSeleccionadas) {
		this.listaEstPersonasSeleccionadas = listaEstPersonasSeleccionadas;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstPersonasTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstPersonasTemp() {
		return listaEstPersonasTemp;
	}

	/**
	 * @param listaEstPersonasTemp
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstPersonasTemp(
			List<DTOEstructuraAreasEntity> listaEstPersonasTemp) {
		this.listaEstPersonasTemp = listaEstPersonasTemp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitulares() {
		return listaEstTitulares;
	}

	/**
	 * @param listaEstTitulares
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstTitulares(
			List<DTOEstructuraAreasEntity> listaEstTitulares) {
		this.listaEstTitulares = listaEstTitulares;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstTitularesTemp
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitularesTemp() {
		return listaEstTitularesTemp;
	}

	/**
	 * @param listaEstTitularesTemp
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstTitularesTemp(
			List<DTOEstructuraAreasEntity> listaEstTitularesTemp) {
		this.listaEstTitularesTemp = listaEstTitularesTemp;
	}

	/**
	 * @return valor de tipo List<DTOEstructuraAreasEntity> que esta contenido
	 *         en la variable listaEstTitularesSeleccionados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public List<DTOEstructuraAreasEntity> getListaEstTitularesSeleccionados() {
		return listaEstTitularesSeleccionados;
	}

	/**
	 * @param listaEstTitularesSeleccionados
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEstructuraAreasEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/10/2017
	 */
	public void setListaEstTitularesSeleccionados(
			List<DTOEstructuraAreasEntity> listaEstTitularesSeleccionados) {
		this.listaEstTitularesSeleccionados = listaEstTitularesSeleccionados;
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
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeTablaVacia
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public String getMensajeTablaVacia() {
		return mensajeTablaVacia;
	}

	/**
	 * @param mensajeTablaVacia
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/10/2017
	 */
	public void setMensajeTablaVacia(String mensajeTablaVacia) {
		this.mensajeTablaVacia = mensajeTablaVacia;
	}

	/**
	 * @return valor de tipo List<DTOComentariosDocumentoEntity> que esta
	 *         contenido en la variable listaComentariosLeidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public List<DTOComentariosDocumentoEntity> getListaComentariosLeidos() {
		return listaComentariosLeidos;
	}

	/**
	 * @param listaComentariosLeidos
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOComentariosDocumentoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public void setListaComentariosLeidos(
			List<DTOComentariosDocumentoEntity> listaComentariosLeidos) {
		this.listaComentariosLeidos = listaComentariosLeidos;
	}

	/**
	 * @return valor de tipo List<DTOComentariosDocumentoEntity> que esta
	 *         contenido en la variable listaComentariosNoLeidos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public List<DTOComentariosDocumentoEntity> getListaComentariosNoLeidos() {
		return listaComentariosNoLeidos;
	}

	/**
	 * @param listaComentariosNoLeidos
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOComentariosDocumentoEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/10/2017
	 */
	public void setListaComentariosNoLeidos(
			List<DTOComentariosDocumentoEntity> listaComentariosNoLeidos) {
		this.listaComentariosNoLeidos = listaComentariosNoLeidos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         rutaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/10/2017
	 */
	public String getRutaDocumento() {
		return rutaDocumento;
	}

	/**
	 * @param rutaDocumento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 12/10/2017
	 */
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}

	public void setRutaDocumento(
			DTOBorradorDocumentosEntity dtoBorradorDocumentosEntity) {
		if (dtoBorradorDocumentosEntity != null) {

			rutaDocumento = boArchivoInteface.obtenerRutaGlusterPdfNube()
					+ File.separator
					+ dtoBorradorDocumentosEntity.getDocumento()
							.getNoDocumento() + ".pdf";
		} else {
			rutaDocumento = "";
		}

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
	 * @return valor de tipo String que esta contenido en la variable
	 *         columnaOrdenamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public String getColumnaOrdenamiento() {
		return columnaOrdenamiento;
	}

	/**
	 * @param columnaOrdenamiento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public void setColumnaOrdenamiento(String columnaOrdenamiento) {
		this.columnaOrdenamiento = columnaOrdenamiento;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable
	 *         esOrdenamientoAscendente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public boolean isEsOrdenamientoAscendente() {
		return esOrdenamientoAscendente;
	}

	/**
	 * @param esOrdenamientoAscendente
	 *            : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 20/10/2017
	 */
	public void setEsOrdenamientoAscendente(boolean esOrdenamientoAscendente) {
		this.esOrdenamientoAscendente = esOrdenamientoAscendente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rutaPrueba
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/10/2017
	 */
	public String getRutaPrueba() {
		return rutaPrueba;
	}

	/**
	 * @param rutaPrueba
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 27/10/2017
	 */
	public void setRutaPrueba(String rutaPrueba) {
		this.rutaPrueba = rutaPrueba;
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
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinborradores
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public String getMensajeSinborradores() {
		return mensajeSinborradores;
	}

	/**
	 * @param mensajeSinborradores
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public void setMensajeSinborradores(String mensajeSinborradores) {
		this.mensajeSinborradores = mensajeSinborradores;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinPersonasAgregadas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public String getMensajeSinPersonasAgregadas() {
		return mensajeSinPersonasAgregadas;
	}

	/**
	 * @param mensajeSinPersonasAgregadas
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 30/10/2017
	 */
	public void setMensajeSinPersonasAgregadas(
			String mensajeSinPersonasAgregadas) {
		this.mensajeSinPersonasAgregadas = mensajeSinPersonasAgregadas;
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
	 * @return valor de tipo int que esta contenido en la variable
	 *         puedeRealizarAccion
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/11/2017
	 */
	public int getPuedeRealizarAccion() {
		return puedeRealizarAccion;
	}

	/**
	 * @param puedeRealizarAccion
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/11/2017
	 */
	public void setPuedeRealizarAccion(int puedeRealizarAccion) {
		this.puedeRealizarAccion = puedeRealizarAccion;
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
	 * @return valor de tipo int que esta contenido en la variable idDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/11/2017
	 */
	public int getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            : valor que se ingresa a la variable de tipo int
	 *
	 * @author Homero Fidel Villanueva
	 * @since 08/11/2017
	 */
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
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

	/**
	 * @return valor de tipo StreamedContent que esta contenido en la variable
	 *         file
	 *
	 * @author Homero Fidel Villanueva
	 * @since 09/11/2017
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable rutaAnexos
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/11/2017
	 */
	public String getRutaAnexos() {
		return rutaAnexos;
	}

	/**
	 * @param rutaAnexos
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/11/2017
	 */
	public void setRutaAnexos(String rutaAnexos) {
		this.rutaAnexos = rutaAnexos;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeConfimacionEliminarBorrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeConfimacionEliminarBorrador() {
		return mensajeConfimacionEliminarBorrador;
	}

	/**
	 * @param mensajeConfimacionEliminarBorrador
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeConfimacionEliminarBorrador(
			String mensajeConfimacionEliminarBorrador) {
		this.mensajeConfimacionEliminarBorrador = mensajeConfimacionEliminarBorrador;
	}

	/**
	 * @param file
	 *            : valor que se ingresa a la variable de tipo StreamedContent
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeNotificacionComentado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeNotificacionComentado() {
		return mensajeNotificacionComentado;
	}

	/**
	 * @param mensajeNotificacionComentado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeNotificacionComentado(
			String mensajeNotificacionComentado) {
		this.mensajeNotificacionComentado = mensajeNotificacionComentado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeNotificacionEditado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeNotificacionEditado() {
		return mensajeNotificacionEditado;
	}

	/**
	 * @param mensajeNotificacionEditado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeNotificacionEditado(String mensajeNotificacionEditado) {
		this.mensajeNotificacionEditado = mensajeNotificacionEditado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeNotificacionFirmado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeNotificacionFirmado() {
		return mensajeNotificacionFirmado;
	}

	/**
	 * @param mensajeNotificacionFirmado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeNotificacionFirmado(String mensajeNotificacionFirmado) {
		this.mensajeNotificacionFirmado = mensajeNotificacionFirmado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeNotificacionValidado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeNotificacionValidado() {
		return mensajeNotificacionValidado;
	}

	/**
	 * @param mensajeNotificacionValidado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeNotificacionValidado(
			String mensajeNotificacionValidado) {
		this.mensajeNotificacionValidado = mensajeNotificacionValidado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeErrorNavegadorPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeErrorNavegadorPDF() {
		return mensajeErrorNavegadorPDF;
	}

	/**
	 * @param mensajeErrorNavegadorPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeErrorNavegadorPDF(String mensajeErrorNavegadorPDF) {
		this.mensajeErrorNavegadorPDF = mensajeErrorNavegadorPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSolucionNavegadorPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSolucionNavegadorPDF() {
		return mensajeSolucionNavegadorPDF;
	}

	/**
	 * @param mensajeSolucionNavegadorPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSolucionNavegadorPDF(
			String mensajeSolucionNavegadorPDF) {
		this.mensajeSolucionNavegadorPDF = mensajeSolucionNavegadorPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeTituloEliminarBorrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeTituloEliminarBorrador() {
		return mensajeTituloEliminarBorrador;
	}

	/**
	 * @param mensajeTituloEliminarBorrador
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeTituloEliminarBorrador(
			String mensajeTituloEliminarBorrador) {
		this.mensajeTituloEliminarBorrador = mensajeTituloEliminarBorrador;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeAceptar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeAceptar() {
		return mensajeAceptar;
	}

	/**
	 * @param mensajeAceptar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeAceptar(String mensajeAceptar) {
		this.mensajeAceptar = mensajeAceptar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeCancelar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeCancelar() {
		return mensajeCancelar;
	}

	/**
	 * @param mensajeCancelar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeCancelar(String mensajeCancelar) {
		this.mensajeCancelar = mensajeCancelar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeTituloEnviarFirmar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeTituloEnviarFirmar() {
		return mensajeTituloEnviarFirmar;
	}

	/**
	 * @param mensajeTituloEnviarFirmar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeTituloEnviarFirmar(String mensajeTituloEnviarFirmar) {
		this.mensajeTituloEnviarFirmar = mensajeTituloEnviarFirmar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeTituloEnviarValidar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeTituloEnviarValidar() {
		return mensajeTituloEnviarValidar;
	}

	/**
	 * @param mensajeTituloEnviarValidar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeTituloEnviarValidar(String mensajeTituloEnviarValidar) {
		this.mensajeTituloEnviarValidar = mensajeTituloEnviarValidar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajePestanaBusquedaArea
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajePestanaBusquedaArea() {
		return mensajePestanaBusquedaArea;
	}

	/**
	 * @param mensajePestanaBusquedaArea
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajePestanaBusquedaArea(String mensajePestanaBusquedaArea) {
		this.mensajePestanaBusquedaArea = mensajePestanaBusquedaArea;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajePestanaBusquedaTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajePestanaBusquedaTitulares() {
		return mensajePestanaBusquedaTitulares;
	}

	/**
	 * @param mensajePestanaBusquedaTitulares
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajePestanaBusquedaTitulares(
			String mensajePestanaBusquedaTitulares) {
		this.mensajePestanaBusquedaTitulares = mensajePestanaBusquedaTitulares;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeBuscar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeBuscar() {
		return mensajeBuscar;
	}

	/**
	 * @param mensajeBuscar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeBuscar(String mensajeBuscar) {
		this.mensajeBuscar = mensajeBuscar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinBusquedaPersonas
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSinBusquedaPersonas() {
		return mensajeSinBusquedaPersonas;
	}

	/**
	 * @param mensajeSinBusquedaPersonas
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSinBusquedaPersonas(String mensajeSinBusquedaPersonas) {
		this.mensajeSinBusquedaPersonas = mensajeSinBusquedaPersonas;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeAgregar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeAgregar() {
		return mensajeAgregar;
	}

	/**
	 * @param mensajeAgregar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeAgregar(String mensajeAgregar) {
		this.mensajeAgregar = mensajeAgregar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeEnviar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeEnviar() {
		return mensajeEnviar;
	}

	/**
	 * @param mensajeEnviar
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeEnviar(String mensajeEnviar) {
		this.mensajeEnviar = mensajeEnviar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinBusquedaTitulares
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSinBusquedaTitulares() {
		return mensajeSinBusquedaTitulares;
	}

	/**
	 * @param mensajeSinBusquedaTitulares
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSinBusquedaTitulares(
			String mensajeSinBusquedaTitulares) {
		this.mensajeSinBusquedaTitulares = mensajeSinBusquedaTitulares;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinTitularesAgregados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSinTitularesAgregados() {
		return mensajeSinTitularesAgregados;
	}

	/**
	 * @param mensajeSinTitularesAgregados
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSinTitularesAgregados(
			String mensajeSinTitularesAgregados) {
		this.mensajeSinTitularesAgregados = mensajeSinTitularesAgregados;
	}

	/**
	 * @return valor de tipo List<DTOBandejaEntradasOficialiaEntity> que esta contenido en la variable listaDTOOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 17/11/2017
	 */
	public List<DTOBandejaEntradasOficialiaEntity> getListaDTOOficialia() {
		return listaDTOOficialia;
	}

	/**
	 * @param listaDTOOficialia : valor que se ingresa a la variable de tipo List<DTOBandejaEntradasOficialiaEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 17/11/2017
	 */
	public void setListaDTOOficialia(
			List<DTOBandejaEntradasOficialiaEntity> listaDTOOficialia) {
		this.listaDTOOficialia = listaDTOOficialia;
	}

	/**
	 * @return valor de tipo PaginarLazy<DTOBandejaEntradasOficialiaEntity,Integer> que esta contenido en la variable listaIndicadoresDisponibles
	 * 
	 * @author David Rodríguez Corral
	 * @since 19/11/2017
	 */
	public PaginarLazy<DTOBandejaEntradasOficialiaEntity, Integer> getListaIndicadoresDisponibles() {
		return listaIndicadoresDisponibles;
	}

	/**
	 * @param listaIndicadoresDisponibles : valor que se ingresa a la variable de tipo PaginarLazy<DTOBandejaEntradasOficialiaEntity,Integer>
	 *
	 * @author David Rodríguez Corral
	 * @since 19/11/2017
	 */
	public void setListaIndicadoresDisponibles(
			PaginarLazy<DTOBandejaEntradasOficialiaEntity, Integer> listaIndicadoresDisponibles) {
		this.listaIndicadoresDisponibles = listaIndicadoresDisponibles;
	}
	
	

}
