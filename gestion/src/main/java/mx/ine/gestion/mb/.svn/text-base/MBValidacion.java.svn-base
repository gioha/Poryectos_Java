/**
 * @(#)MBValidacion.java 10/10/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDValidacionInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOHistDocCreacionEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHValidacionInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase (ManageBean) que se encarga de recibir y delegar las peticiones que
 * lleguen de la pantalla de administracion para Validación de documentos.
 *
 * @author Pável Alexei Martínez Regalado
 * @since 10/10/2017
 */
public class MBValidacion implements Serializable {

	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID = 2068437045046571435L;

	/**
	 * Atributo log.
	 */
	private static final Logger log = Logger.getLogger(MBValidacion.class);

	/**
	 * Lista que guarda los objetos pertenecientes a validación.
	 */
	private List<DTOValidacionDocumentosEntity> dtoValidacion;

	/**
	 * Lista que guarda los objetos seleccionados para ser validados o
	 * regresados al remitente.
	 */
	private List<DTOValidacionDocumentosEntity> dtoDocumentosSeleccionados;

	/**
	 * Lista que guarda los objetos pertenecientes a un Comentario.
	 */
	private List<DTOComentariosDocumentoEntity> dtoComentarios;

	/**
	 * Lista que guarda los objetos pertenecientes a un Comentario.
	 */
	private List<DTOHistDocCreacionEntity> dtoHistorial;

	/**
	 * Objeto que guarda un comentario.
	 */
	private DTOComentariosDocumentoEntity dtoComentariosDocumento;

	/**
	 * Objeto que guarda un comentario.
	 */
	private DTOBorradorDocumentosEntity dtoBorradorDocumento;

	/**
	 * Indica si debe mostrarse o no la sección de firmas
	 */
	private Integer idDocTemp;

	/**
	 * DTOValidacion que está seleccionado para el comentario
	 */
	private DTOValidacionDocumentosEntity validacionParaComentario;
	
	/**
	 * DTOValidacion que está seleccionado para el comentario
	 */
	private DTOValidacionDocumentosEntity validacionParaEdicion;

	/**
	 * Indica el valor del comentario
	 */
	private String comentarioActual;

	/**
	 * Atributo que guarda el valor que se ingresa en el combo
	 */
	private Integer comboValidacion;

	/**
	 * Usuario logueado
	 */
	private DTOUsuarioLogin usuarioLogueado;
	
	/**
	 * Helper para el detalle del documento
	 */
	private DTODocumentoOficialiaHelper documentoDetalleHelper;
	
	/**
	 * DTODocumentoEntity con la información del documento en detalle
	 */
	private DTODocumentoEntity documentoDetalle;
	
	/**
	 * Boolean que indica si hay un mensaje de validación.
	 */
	private boolean confirmacion;

	/**
	 * Tiempo restante para la actualización
	 */
	private Integer tiempoRestanteActualizacion;
	
	/**
	 * DTOEstructuraAreasEntity que guarda la persona que inició sesión
	 */
	private DTOEstructuraAreasEntity dtoEstructuraUsuario;

	@Autowired
	@Qualifier("bsdValidacion")
	private transient BSDValidacionInterface bsdValidacionInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;

	@Autowired
	@Qualifier("vhValidacion")
	private transient VHValidacionInterface vhValidacionInterface;

	/* ------------------------------------------ Métodos --------------------------------------- */
	/**
	 * Método para inicializar los atributos que se necesiten en cuanto carga la
	 * pantalla de validación
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void iniciar() {
		actualizarMenu();
		tiempoRestanteActualizacion = Integer.valueOf(300);
		usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		this.dtoDocumentosSeleccionados = new ArrayList<DTOValidacionDocumentosEntity>();
		this.dtoComentarios = new ArrayList<DTOComentariosDocumentoEntity>();
		this.dtoHistorial = new ArrayList<DTOHistDocCreacionEntity>();
		this.dtoComentariosDocumento = new DTOComentariosDocumentoEntity();
		this.dtoBorradorDocumento = new DTOBorradorDocumentosEntity();
		this.comboValidacion = Integer.valueOf(0);
		this.validacionParaComentario = null;
		
		try {
			dtoEstructuraUsuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(SecurityContextHolder.getContext().getAuthentication().getName());
			dtoValidacion = bsdValidacionInterface.consultarDocsParaValidacion(usuarioLogueado.getIdPersona());
		} catch (Exception e) {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se cargaron correctamente los documentos a validar"));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: iniciar()");
			log.error("<=================== ocurrio un error al iniciar el MBValidacion. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método para actualizar la lista de documentos a Validar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/11/2017
	 */
	public void actualizar() {
		actualizarMenu();
		try {
			dtoValidacion = bsdValidacionInterface.consultarDocsParaValidacion(usuarioLogueado.getIdPersona());
		} catch (Exception e) {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No actualizaron correctamente los documentos a validar "));
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: actualizar()");
			log.error("<=================== ocurrio un error al tratar de actualizar los documentos a validar. ");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método que muestra el comentario de un documento
	 *
	 * @param dtoValidacion
	 *            : Objeto que tiene el comentario a mostrar
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void verComentario(DTOValidacionDocumentosEntity dtoValidacion) {
		if(dtoValidacion != null){
			comentarioActual = "";
			
			RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
			cambiarALeido(dtoValidacion);
			
			if (dtoValidacion.getDtoComentario() != null) {
				comentarioActual = dtoValidacion.getDtoComentario().getComentarios();
			}
			
			this.validacionParaComentario = dtoValidacion;
		}
	}

	/**
	 * Método que guarda un comentario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public void guardarComentario() {
		DTOComentariosDocumentoEntity nuevoComentario = null; 
		if (comentarioActual != null && !comentarioActual.trim().equals("") && validacionParaComentario != null ) {
			if (validacionParaComentario.getDtoComentario() == null) {
				
				nuevoComentario = new DTOComentariosDocumentoEntity();
				nuevoComentario.setIdDocumento(validacionParaComentario.getIdDocumento());
				nuevoComentario.setComentarios(comentarioActual);
				nuevoComentario.setUsuarioComento(usuarioLogueado.getUsername());
				validacionParaComentario.setDtoComentario(nuevoComentario);
//				try{
//					bsdValidacionInterface.guardarComentario(validacionParaComentario, dtoEstructuraUsuario);
//				} catch (Exception e) {
//					vhValidacionInterface.mostrarMensajeGrowl(
//							Utilidades.mensajeProperties("constante_growl_advertencia"),
//							Utilidades.mensajeProperties("titulo_growl_advertencia"),
//							Utilidades.mensajeProperties("Eror al tratar de guardar el comentario"));
//					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: iniciar()");
//					log.error("<=================== ocurrio un error al tratar de guardar el comentario. ");
//					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
//					log.error("", e);
//				}
			
			} else {
				validacionParaComentario.getDtoComentario().setComentarios(comentarioActual);
			}
			
			try{
				bsdValidacionInterface.guardarComentario(validacionParaComentario, dtoEstructuraUsuario);
			} catch (Exception e) {
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("Eror al tratar de guardar el comentario"));
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: guardarComentario()");
				log.error("<=================== ocurrio un error al tratar de guardar el comentario. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			actualizar();
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_exito"),
					Utilidades.mensajeProperties("titulo_growl_exito"),
					Utilidades.mensajeProperties("mensaje_exito_guardar_comentario"));

		} else {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_no_guardar_comentario"));
		}
	}

	/**
	 * Método que elimina un comentario
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public void eliminarComentario() {
		if (validacionParaComentario != null && validacionParaComentario.getDtoComentario() != null) {
			
			try{
				bsdValidacionInterface.eliminarComentario(validacionParaComentario);
			} catch (Exception e) {
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("Eror al tratar de eliminar el comentario"));
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: eliminarComentario()");
				log.error("<=================== ocurrio un error al tratar de eliminar el comentario. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
			
			actualizar();
		} else {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_no_eliminar_comentario"));
		}
	}

	/**
	 * Método que filtra que acción se debe de realizar
	 * 
	 * @param confirmacio
	 *            : boolean que indica si se debe de mostrar un dialog de
	 *            confirmación
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void accionesValidacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
		if (comboValidacion.equals(Integer.valueOf(1))) {
			validar();
		} else if (comboValidacion.equals(Integer.valueOf(2))) {
			regresarARemitente();
		} else {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_info"),
					Utilidades.mensajeProperties("titulo_growl_info"),
					Utilidades.mensajeProperties("mensaje_info_seleccionar_accion"));
		}
	}

	/**
	 * Método que regresa los oficios al remitente
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void regresarARemitente() {

		if (validarRegresarAlReminente()) {
			try {

				bsdValidacionInterface.regresarARemitente(this.dtoDocumentosSeleccionados);
				dtoValidacion = bsdValidacionInterface.consultarDocsParaValidacion(usuarioLogueado.getIdPersona());

				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						Utilidades.mensajeProperties("mensaje_exito_regresar_remitente"));

			} catch (Exception e) {
				
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_advertencia_no_regresar_remitente"));
				
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: regresarARemitente()");
				log.error("<=================== Ocurrió un error al regresar los oficios al remitente. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}
	
	/**
	 * Método para validar la acción de regresar a remitente.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public boolean validarRegresarAlReminente() {

		boolean validado = true;
		for (DTOValidacionDocumentosEntity documento : dtoDocumentosSeleccionados) {
			if (documento.getDtoComentario() == null) {
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("mensaje_agregar_comentario"));
				return false;
			}
		}
		
		if (this.dtoDocumentosSeleccionados.isEmpty()) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_seleccionar_regresar"));

			return false;
		}
		
		if (this.dtoDocumentosSeleccionados.size() > 10) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_hastaDiez"));

			return false;
		}

		if (confirmacion == false) {
			for (DTOValidacionDocumentosEntity documento : dtoDocumentosSeleccionados) {
				if (documento.getDtoComentario() == null && documento.getConModificaciones() == 0) {
					RequestContext.getCurrentInstance().execute("PF('confirmacion_regresar').show()");
					return false;
				}
			}
		}
		return validado;
	}

	/**
	 * Método para validar los objetos seleccionados
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void validar() {
		String mensajeAdvertencia = Utilidades.mensajeProperties("mensaje_advertencia_no_validar");
		if (validarProcesoValidacion()) {
			try {
				bsdValidacionInterface.validar(this.dtoDocumentosSeleccionados);
				dtoValidacion = bsdValidacionInterface.consultarDocsParaValidacion(usuarioLogueado.getIdPersona());
				
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_exito"),
						Utilidades.mensajeProperties("titulo_growl_exito"),
						Utilidades.mensajeProperties("mensaje_exito_validar"));
				
			} catch (Exception e) {
				if(e.getMessage().equals("Concurrencia")){
					mensajeAdvertencia = "El documento ha sido rechazado por alguien más";
				}

				vhValidacionInterface.enviarError(e, this.getClass().getName(), "validar()", "Ocurrió un error al tratar de guardar la validación en la base.");
				
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						mensajeAdvertencia);
				
				try{
					dtoValidacion = bsdValidacionInterface.consultarDocsParaValidacion(usuarioLogueado.getIdPersona());
				} catch (Exception ex) {
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: validar()");
					log.error("<=================== Error al tratar de validar los documentos seleccionados. ");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("", ex);
				}
				
			}
		}
	}

	/**
	 * Método para validar la acción de validar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public boolean validarProcesoValidacion() {
		boolean validado = true;
		if (this.dtoDocumentosSeleccionados.isEmpty()) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_seleccionar_validar"));
			
			return false;
		}
		
		if (this.dtoDocumentosSeleccionados.size() > 10) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_hastaDiez"));

			return false;
		}
		
		for (DTOValidacionDocumentosEntity documento : dtoDocumentosSeleccionados) {
			if (documento.getEstatusRegresado().intValue() == 1
					&& documento.getDtoPersonaRegreso() != null) {
				vhValidacionInterface
						.mostrarMensajeGrowl(
								Utilidades.mensajeProperties("constante_growl_advertencia"),
								Utilidades.mensajeProperties("titulo_growl_advertencia"),
								"El archivo "
										+ documento.getDtoDocumento()
												.getNoDocumento()
										+ " no fue validado por "
										+ documento.getDtoPersonaRegreso()
												.getNombreCompleto()
										+ " por lo que ahora no se puede validar. Regresar este archivo a remitente.");
				return false;
			}
		}
		if (confirmacion == false) {
			for (DTOValidacionDocumentosEntity documento : dtoDocumentosSeleccionados) {
				if (documento.getDtoComentario() == null
						&& documento.getConModificaciones() == 0) {
					RequestContext.getCurrentInstance().execute(
							"PF('confirmacion_validar').show()");
					return false;
				}
			}
		}

		return validado;
	}

	/**
	 * Método para visualizar el documento
	 * 
	 * @param dtoValidacion
	 *            : objeto que contiene el documento que se va a validar
	 * @param soloLectura
	 *            : boolean que indica si visualizará en modo de solo lectura, o
	 *            permitirá la edición
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void visualizarDocumento( DTOValidacionDocumentosEntity dtoValidacion, Boolean soloLectura) {
		// editar es false
		cambiarALeido(dtoValidacion);
		if (soloLectura == false) {
			try{
				bsdValidacionInterface.insertarEdicion(dtoValidacion.getIdPersona(), dtoValidacion.getIdPersonaRemitente(), dtoValidacion.getIdDocumento());
			} catch (Exception ex) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: validar()");
				log.error("<=================== Error al tratar de insertar la edición. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", ex);
			}
		} else {
			try{
				bsdValidacionInterface.insertarVisualizacion(dtoValidacion.getIdPersona(), dtoValidacion.getIdPersonaRemitente(), dtoValidacion.getIdDocumento());
			} catch (Exception ex) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: validar()");
				log.error("<=================== Error al tratar de insertar la visualización. ");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", ex);
			}
		}

		String ruta = "/Gestion4/documentos/";
		
		try {
			vhValidacionInterface.visualizarDocumento(dtoValidacion.getDtoDocumento().getNombreDocumento(), ruta, soloLectura);			
		} catch (Exception e) {
			vhValidacionInterface.enviarError(e, this.getClass().getName(), "visualizarDocumento()",
					"Ocurrió un error al visualizar documento.");
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("mensaje_advertencia_no_validar"));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: visualizarDocumento()");
			log.error("<=================== Ocurrió un error al visualizar documento.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	/**
	 * Método para validar la acción de regresar a remitente.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public void validarEditar(DTOValidacionDocumentosEntity dtoValidacion, Boolean soloLectura) {
		cambiarALeido(dtoValidacion);
		validacionParaEdicion = dtoValidacion;
		if (soloLectura == false) {
			if(dtoValidacion.getConModificaciones() == 0) {
				RequestContext.getCurrentInstance().execute("PF('confirmacion_editar').show()");
			} else {
				visualizarDocumento(dtoValidacion, soloLectura);
			}
		}
	}
	
	/**
	 * Método para abrir la ventana de información con los datos del documento
	 * 
	 * @param dtoValidacion: Objeto DTOValidacionEntity con el documento para validación seleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 20/12/2017
	 */
	public void abrirInformacionDocumento(DTOValidacionDocumentosEntity dtoValidacion) {
		cambiarALeido(dtoValidacion);
		try {
			documentoDetalle = dtoValidacion.getDtoDocumento();
			
			documentoDetalleHelper = bsdValidacionInterface.obtenerDetalleDocumento(Integer.valueOf(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("inicio");
			documentoDetalleHelper.setTituloInfoDetalle("Detalle de documento");

			documentoDetalleHelper.setHistorial(bsdValidacionInterface.consultarHistorialPorIdDocumento(documentoDetalle.getIdDocumento()));
			RequestContext.getCurrentInstance().execute("PF('inf-doc-dialog').show()");
		} catch (Exception e) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se pudo mostrar la información del documento."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: abrirInformacionDocumento()");
			log.error("<=================== Ocurrió un error al tratar de cargar la informacion.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
			
		}
	}
	
	public void regresarInicio() {
		try {
			documentoDetalleHelper = bsdValidacionInterface.obtenerDetalleDocumento(Integer.valueOf(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("inicio");
			documentoDetalleHelper.setTituloInfoDetalle("Detalle de documento");
		} catch (Exception e) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se puede regresar al inicio."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: regresarInicio()");
			log.error("<=================== Ocurrió un error al regresar al iniciar.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}
	
	public void verHistorial() {
		try {
			documentoDetalleHelper.setHistorial(bsdValidacionInterface.consultarHistorialPorIdDocumento(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("historial");
			documentoDetalleHelper.setTituloInfoDetalle("Historial de documento");
		} catch (Exception e) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se puede ver el historial."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: verHistorial()");
			log.error("<=================== Ocurrió un error al ver el historial.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	public void verAnexos() {
		try {
			documentoDetalleHelper.setListaDTOAnexos(bsdValidacionInterface.obtenerAnexoPorDocumento(documentoDetalle));
			documentoDetalleHelper.setTipoInfoDetalle("anexos");
			documentoDetalleHelper.setTituloInfoDetalle("Anexos de documento");
		} catch (Exception e) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se pueden mostrar los anexos."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: verAnexos()");
			log.error("<=================== Ocurrió un error al cargar los anexos.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	public void verComentarios() {
		try {
			documentoDetalleHelper.setComentarios(bsdValidacionInterface.consultarComentarios(documentoDetalle));
			documentoDetalleHelper.setTipoInfoDetalle("comentarios");
			documentoDetalleHelper.setTituloInfoDetalle("Comentarios de documento");
		} catch (Exception e) {
			
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se pueden mostrar los comentarios."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: verComentarios()");
			log.error("<=================== Ocurrió un error al cargar los comentario.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}

	public void verComentario(DTOComentariosDocumentoEntity comentario) {
		try {
			documentoDetalleHelper.setComentario(comentario);
			documentoDetalleHelper.setTipoInfoDetalle("comentario");
			documentoDetalleHelper.setTituloInfoDetalle("Comentario de documento");
		} catch (Exception e) {
			vhValidacionInterface.mostrarMensajeGrowl(
					Utilidades.mensajeProperties("constante_growl_advertencia"),
					Utilidades.mensajeProperties("titulo_growl_advertencia"),
					Utilidades.mensajeProperties("No se pudo guardar el comentario."));
			
			log.error("<=================== Clase: "+this.getClass().getName()+" , Método: verComentario()");
			log.error("<=================== Ocurrió un error al guardar el comentario.");
			log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
			log.error("", e);
		}
	}
	
	/**
	 * Método para cambiar si es necesario la entrada de validación a leído
	 * 
	 * @param dtoValidacion: Objeto DTOValidacionEntity con el documento para validación seleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 20/12/2017
	 */
	public void cambiarALeido(DTOValidacionDocumentosEntity dtoValidacion) {
		if(dtoValidacion != null && dtoValidacion.getNoLeido().intValue() > 0) {
			try {
				bsdValidacionInterface.cambiarALeido(dtoValidacion);
				actualizarMenu();
			} catch (Exception e){
				vhValidacionInterface.mostrarMensajeGrowl(
						Utilidades.mensajeProperties("constante_growl_advertencia"),
						Utilidades.mensajeProperties("titulo_growl_advertencia"),
						Utilidades.mensajeProperties("No se pudo actualizar el estatus del documento a validar."));
				
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: cambiarALeido()");
				log.error("<=================== Ocurrió un error al actualizar el estatus del documento a validar.");
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}
	
	/**
	 * Método para cambiar si es necesario la entrada de validación a leído
	 * 
	 * @param dtoValidacion: Objeto DTOValidacionEntity con el documento para validación seleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 20/12/2017
	 */
	public void cambiarALeidos() {
		log.error("marcar varios leídos");
		for (DTOValidacionDocumentosEntity dtoValidacion  : dtoDocumentosSeleccionados) {
			if(dtoValidacion.getNoLeido().intValue() > 0) {
				try {
					log.error("Cambios varios");
					bsdValidacionInterface.cambiarALeido(dtoValidacion);
					actualizarMenu();
				} catch (Exception e){
					vhValidacionInterface.mostrarMensajeGrowl(
							Utilidades.mensajeProperties("constante_growl_advertencia"),
							Utilidades.mensajeProperties("titulo_growl_advertencia"),
							Utilidades.mensajeProperties("No se pudo actualizar el estatus de los documentos seleccionados."));
					
					log.error("<=================== Clase: "+this.getClass().getName()+" , Método: cambiarALeidos()");
					log.error("<=================== Ocurrió un error al actualizar el estatus del documento a validar.");
					log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
					log.error("", e);
				}
			}			
		}
	}

	/**
	 * Método para cambiar si es necesario la entrada de validación a leído
	 * 
	 * @param dtoValidacion: Objeto DTOValidacionEntity con el documento para validación seleccionado
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 20/12/2017
	 */	
	public void cambiarALeido(SelectEvent event) {
		cambiarALeido((DTOValidacionDocumentosEntity) event.getObject());
	}
	
	public void actualizarMenu() {
		MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
		administrador.consultarNotificacionesSeguimiento(); 
	}
	
	// ------------------------ GETTERS & SETTERS ------------------------ //
	/**
	 * @return valor de tipo DTOValidacionDocumentosEntity que esta contenido en
	 *         la variable dtoValidacion
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/10/2017
	 */
	public List<DTOValidacionDocumentosEntity> getDtoValidacion() {
		return dtoValidacion;
	}

	/**
	 * @param dtoValidacion
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOValidacionDocumentosEntity
	 *
	 * @author Pável Alexei Martínez Regalado
	 * @since 14/09/2017
	 */
	public void setDtoValidacion(
			List<DTOValidacionDocumentosEntity> dtoValidacion) {
		this.dtoValidacion = dtoValidacion;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable
	 *         comboValidacion
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public Integer getcomboValidacion() {
		return comboValidacion;
	}

	/**
	 * @param comboValidacion
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setcomboValidacion(Integer comboValidacion) {
		this.comboValidacion = comboValidacion;
	}

	/**
	 * @return valor de tipo DTOComentariosDocumentoEntity que esta contenido en
	 *         la variable dtoComentarios
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public List<DTOComentariosDocumentoEntity> getDtoComentarios() {
		return dtoComentarios;
	}

	/**
	 * @param dtoComentarios
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOComentariosDocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setDtoComentarios(
			List<DTOComentariosDocumentoEntity> dtoComentarios) {
		this.dtoComentarios = dtoComentarios;
	}

	/**
	 * @return valor de tipo DTOComentariosDocumentoEntity que esta contenido en
	 *         la variable dtoComentarios
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public DTOComentariosDocumentoEntity getDtoComentariosDocumento() {
		return dtoComentariosDocumento;
	}

	/**
	 * @param dtoComentariosDocumento
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOComentariosDocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoComentariosDocumento(
			DTOComentariosDocumentoEntity dtoComentariosDocumento) {
		this.dtoComentariosDocumento = dtoComentariosDocumento;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable idDocTemp
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public Integer getIdDocTemp() {
		return idDocTemp;
	}

	/**
	 * @param idDocTemp
	 *            : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setIdDocTemp(Integer idDocTemp) {
		this.idDocTemp = idDocTemp;
	}

	/**
	 * @return valor de tipo DTOValidacionDocumentosEntity que esta contenido en
	 *         la variable dtoDocumentosSeleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public List<DTOValidacionDocumentosEntity> getDtoDocumentosSeleccionados() {
		return dtoDocumentosSeleccionados;
	}

	/**
	 * @param dtoDocumentosSeleccionados
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOValidacionDocumentosEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoDocumentosSeleccionados(
			List<DTOValidacionDocumentosEntity> dtoDocumentosSeleccionados) {
		this.dtoDocumentosSeleccionados = dtoDocumentosSeleccionados;
	}

	/**
	 * @return valor de tipo DTOBorradorDocumentosEntity que esta contenido en
	 *         la variable dtoBorradorDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public DTOBorradorDocumentosEntity getDtoBorradorDocumento() {
		return dtoBorradorDocumento;
	}

	/**
	 * @param dtoBorradorDocumento
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOBorradorDocumentosEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoBorradorDocumento(
			DTOBorradorDocumentosEntity dtoBorradorDocumento) {
		this.dtoBorradorDocumento = dtoBorradorDocumento;
	}

	/**
	 * @return valor de tipo DTOHistDocCreacionEntity que esta contenido en la
	 *         variable dtoHistorial
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public List<DTOHistDocCreacionEntity> getDtoHistorial() {
		return dtoHistorial;
	}

	/**
	 * @param dtoHistorial
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOHistDocCreacionEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoHistorial(List<DTOHistDocCreacionEntity> dtoHistorial) {
		this.dtoHistorial = dtoHistorial;
	}

	/**
	 * @return valor de tipo boolean que está contenido en la variable
	 *         confirmación
	 * @author Pável Alexei Martínez Regalado
	 * 
	 * @since 30/10/2017
	 */
	public boolean isConfirmacion() {
		return confirmacion;
	}

	/**
	 * @param confirmacion
	 *            : valor de tipo boolean
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 30/10/2017
	 */
	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	/**
	 * @return the tiempoRestanteActualizacion
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/11/2017
	 */
	public Integer getTiempoRestanteActualizacion() {
		return tiempoRestanteActualizacion;
	}

	/**
	 * @param tiempoRestanteActualizacion
	 *            the tiempoRestanteActualizacion to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/11/2017
	 */
	public void setTiempoRestanteActualizacion(
			Integer tiempoRestanteActualizacion) {
		this.tiempoRestanteActualizacion = tiempoRestanteActualizacion;
	}

	/**
	 * @return the validacionParaComentario
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public DTOValidacionDocumentosEntity getValidacionParaComentario() {
		return validacionParaComentario;
	}

	/**
	 * @param validacionParaComentario
	 *            the validacionParaComentario to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public void setValidacionParaComentario(
			DTOValidacionDocumentosEntity validacionParaComentario) {
		this.validacionParaComentario = validacionParaComentario;
	}

	/**
	 * @return the comentarioActual
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public String getComentarioActual() {
		return comentarioActual;
	}

	/**
	 * @param comentarioActual
	 *            the comentarioActual to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 08/11/2017
	 */
	public void setComentarioActual(String comentarioActual) {
		this.comentarioActual = comentarioActual;
	}

	/**
	 * @return the validacionParaEdicion
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public DTOValidacionDocumentosEntity getValidacionParaEdicion() {
		return validacionParaEdicion;
	}

	/**
	 * @param validacionParaEdicion the validacionParaEdicion to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/11/2017
	 */
	public void setValidacionParaEdicion(
			DTOValidacionDocumentosEntity validacionParaEdicion) {
		this.validacionParaEdicion = validacionParaEdicion;
	}

	/**
	 * @return variable de tipo DTODocumentoOficialiaHelper contenida en documentoDetalleHelper
	 * 
	 * @since 03/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTODocumentoOficialiaHelper getDocumentoDetalleHelper() {
		return documentoDetalleHelper;
	}

	/**
	 * @param documentoDetalleHelper: variable de tipo DTODocumentoOficialiaHelper contenida en documentoDetalleHelper
	 *
	 * @since 03/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDocumentoDetalleHelper(
			DTODocumentoOficialiaHelper documentoDetalleHelper) {
		this.documentoDetalleHelper = documentoDetalleHelper;
	}

	/**
	 * @return variable de tipo DTODocumentoEntity contenida en documentoDetalle
	 * 
	 * @since 03/01/2018
	 * @author Pável Alexei Martínez Regalado
	 */
	public DTODocumentoEntity getDocumentoDetalle() {
		return documentoDetalle;
	}

	/**
	 * @param documentoDetalle: variable de tipo DTODocumentoEntity contenida en documentoDetalle
	 *
	 * @since 03/01/2018
	 * @author Pável Alexei Martinez Regalado
	 */
	public void setDocumentoDetalle(DTODocumentoEntity documentoDetalle) {
		this.documentoDetalle = documentoDetalle;
	}
	
}
