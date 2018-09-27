/**
 * @(#)MBFirma.java 12/09/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDBandejaSeguimientoInterface;
import mx.ine.gestion.bsd.inter.BSDFirmaInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBorradorDocumentosEntity;
import mx.ine.gestion.dto.db.DTOComentariosDocumentoEntity;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOFirmaDocumentosEntity;
import mx.ine.gestion.dto.db.DTONotificacionesEntity;
import mx.ine.gestion.dto.db.DTOValidacionDocumentosEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.dto.helper.DTOFirmaElectronicaHelper;
import mx.ine.gestion.util.ApplicationContextUtils;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHFirmaInterface;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import com.googlecode.mp4parser.util.Path;

/**
 * Clase (ManageBean) que se encarga de recibir y delegar las peticiones que lleguen 
 * de la pantalla de administracion para Firma de documentos (FALTAN COMENTARIOS)
 *
 * @author David Rodríguez Corral
 * @since 13/09/2017
 */
public class MBFirma implements Serializable{
	
	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = 2068437045046571435L;
	
	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(MBFirma.class);
	
	/**
	 * Lista que guarda los objetos pertenecientes a firma
	 */
	private List<DTOFirmaDocumentosEntity> dtoFirma;
	
	/**
	 * Objeto de apoyo que guarda una firma
	 */
	private DTOFirmaDocumentosEntity dtoFirmaTemp;
	
	/**
	 * Lista que guarda los objetos pertenecientes a firma que ya se encuentran firmados y estan pendientes de envío.
	 */
	private List<DTOFirmaDocumentosEntity> dtoFirmados;
	
	
	
	/**
	 * Lista que guarda los objetos seleccionados para ser firmados o regresados al remitente.
	 */
	private List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados;
	
	/**
	 * Lista que guarda los objetos seleccionados para ser enviados a destinatarios o regresados al remitente.
	 */
	private List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados;
	
	/**
	 * Objeto de tipo .
	 */
	private DTOBorradorDocumentosEntity dtoBorradorDocumento;
	
	/**
	 * Atributo de ayuda para comentarios
	 */
	private Integer idDocTemp;
	
	/**
	 * Atributo de ayuda para comentarios
	 */
	private String comentarioTemp;
	
	/**
	 * Indica si debe mostrarse o no la sección de firmas
	 */
	private Boolean muestraFirma;
	
	/**
	 * Indica si debe mostrarse el boton Aceptar
	 */
	private Boolean muestraBotonAceptar;
	
	/**
	 * Atributo que guarda el valor que se ingresa en el combo del panel de documentos a firmar
	 */
	private Integer comboFirma;
	
	/**
	 * Atributo que guarda el valor que se ingresa en el combo del panel de documentos firmados
	 */
	private Integer comboFirmados;
	
	/**
	 * DTO de ayuda para la firma electrónica
	 */
	private DTOFirmaElectronicaHelper firmaH;
	
	/**
	 * Atributo para indicar el numero de archivos que se van a firmar
	 */
	private Integer numeroArchivosAFirmar;
	
	/**
	 * Atributo que guarda el hash de un documento
	 */
	private String hashDocumento;
	
	/**
	 * Atributo para guardar el certificado del usuario (Es llave pública)
	 */
	private UploadedFile certificado;
	
	/**
	 * Atributo para guardar nombre del certificado del usuario (Es llave pública)
	 */
	private String nombreCertificado;
	
	/**
	 * Atributo para guardar el tipo de algoritmo de un certificado (SHA1, SHA256) 
	 */
	private String tipoAlgoritmo;
	
	/**
	 * Lista que guarda los objetos pertenecientes a un Comentario.
	 */
	private List<DTOComentariosDocumentoEntity> dtoComentarios;
	
	/**
	 * Objeto que guarda un comentario.
	 */
	private DTOComentariosDocumentoEntity dtoComentariosDocumento;
	
	/**
	 * DTOValidacion que está seleccionado para el comentario
	 */
	private DTOFirmaDocumentosEntity validacionParaComentario;
	
	/**
	 * Indica el valor del comentario
	 */
	private String comentarioActual;
	
	/**
	 * Tiempo restante para la actualización
	 */
	private Integer tiempoRestanteActualizacion;
	
	/**
	 * Atributo que guarda la ruta de un pdf en el gluster
	 */
	private String rutaPDF;
	
	/**
	 * String que guarda los números de documento de los documentos que fueron firmados por otra persona y yo los quiero
	 * regresar sin firmar
	 */
	private String docsFirmadosRegresadosMensaje;
	
	/**
	 * Helper para el detalle del documento
	 */
	private DTODocumentoOficialiaHelper documentoDetalleHelper;
	
	/**
	 * DTODocumentoEntity con la información del documento en detalle
	 */
	private DTODocumentoEntity documentoDetalle;
	
	/**
	 * DTOEstructuraAreasEntity que guarda la persona que inició sesión
	 */
	private DTOEstructuraAreasEntity dtoEstructuraUsuario;
	
	@Autowired
	@Qualifier("bsdFirma")
	private transient BSDFirmaInterface bsdFirmaInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaSeguimiento")
	private transient BSDBandejaSeguimientoInterface bsdBandejaSeguimientoInterface;
	
	@Autowired
	@Qualifier("vhFirma")
	private transient VHFirmaInterface vhFirmaInterface;
	
	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */
	
	/**
	 * Método para inicializar los atributos que se necesiten en cuanto carga 
	 * la pantalla de firmas
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void iniciar(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
			dtoEstructuraUsuario = bsdBandejaSeguimientoInterface.consultarPersonaXCuenta(SecurityContextHolder.getContext().getAuthentication().getName());
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			//Consulta los documentos que se van a firmar
			dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),0);
			dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),1);
			this.dtoDocumentosSeleccionados = new ArrayList<DTOFirmaDocumentosEntity>();
			this.dtoBorradorDocumento = new DTOBorradorDocumentosEntity();
			//this.muestraFirma = false;
			//this.comboFirma = null;
			//this.comboFirmados = 0;
			this.nombreCertificado = null;
			this.certificado = null;
			this.firmaH = new DTOFirmaElectronicaHelper();
			this.numeroArchivosAFirmar = this.dtoDocumentosSeleccionados.size();
			tiempoRestanteActualizacion = Integer.valueOf(300);
			
			logger.info("Pantalla de Firma iniciada");
			logger.info("Usuario en sesión: "+ usuarioLogueado.getUsername()+" con ID "+usuarioLogueado.getIdPersona()+" nombre: "+usuarioLogueado.getNombreUsuario());
		
			
		
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBFirma , Método: iniciar");
			logger.error("<=================== Ocurrió un error al cargar los documentos para firmar");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"),	Utilidades.mensajeProperties("mensaje_firma_errorCargaIniciar")));
		}
	}
	
	public void actualizar() {
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			
			dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),0);
			dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),1);
			
			this.dtoBorradorDocumento = new DTOBorradorDocumentosEntity();
			this.nombreCertificado = null;
			this.certificado = null;
			this.firmaH = new DTOFirmaElectronicaHelper();
			this.numeroArchivosAFirmar = this.dtoDocumentosSeleccionados.size();
			
			tiempoRestanteActualizacion = Integer.valueOf(300);
			logger.info("Pantalla de Firma iniciada");
			logger.info("Usuario en sesión: "+ usuarioLogueado.getUsername()+" con ID "+usuarioLogueado.getIdPersona()+" nombre: "+usuarioLogueado.getNombreUsuario());
			
			
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBFirma , Método: actualizar");
			logger.error("<=================== Ocurrió un error al cargar los documentos para firmar");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"),	Utilidades.mensajeProperties("mensaje_firma_errorCargaIniciar")));
		}
	}
	
	public void actualizarListas(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),0);
		dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),1);
	}
	
	/**
	 * Método para que apartir del combo se muestre o no las secciones de firmas y el boton Aceptar
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void mostrarFirma(){
		if(this.comboFirma==0){
			this.muestraFirma = false;
			this.muestraBotonAceptar = false;
		}
		if(this.comboFirma==1){
			this.muestraFirma = true;
			this.muestraBotonAceptar = false;
		}
		if(this.comboFirma==2 || this.comboFirma==3){
			this.muestraFirma = false;
			this.muestraBotonAceptar = true;
		}
	}
	
	/**
	 * Método que realiza la acción de regresar a remitente o rechazar documento para el botón aceptar dependiendo
	 * de la opción seleccionada.
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void accionarAceptar() throws Exception{
		
		if(this.comboFirma==2){
			validarRegresarARemitente();
		}
		if(this.comboFirma==3){
			validarRechazarDocumentos();
		}
		
	}
	
	/**
	 * Método que muestra el comentario de un documento
	 *
	 * @param dtoFirma: Objeto que tiene el comentario a mostrar
	 *
	 * @author David Rodríguez Corral
	 * @since 03/09/2017
	 */
	public void verComentario(DTOFirmaDocumentosEntity dtoFirma) {
		
		DTOFirmaDocumentosEntity dtoBloqueado = bsdFirmaInterface.verificarBloqueo(dtoFirma);
		
		if(dtoBloqueado==null){
			
			RequestContext context = RequestContext.getCurrentInstance();
        	context.execute("PF('dlg1').show();");
		
			logger.info(dtoFirma.getDtoComentario());
			if (dtoFirma.getDtoComentario() != null) {
				logger.info("El comentario tiene uno");
				comentarioActual = dtoFirma.getDtoComentario()
						.getComentarios();
			} else {
				comentarioActual = "";
				logger.info("El comentario no tiene");
			}
			this.validacionParaComentario = dtoFirma;
		
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento esta siendo editado por "+dtoBloqueado.getDtoPersonaBloqueado().getNombreCompleto()+", espera a que el documento sea liberado para continuar"));
			
		}
	}
	
	/**
	 * Método que guarda un comentario 
	 *
	 * @author David Rodríguez Corral
	 * @since 03/09/2017
	 */
	public void guardarComentario() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		
		if (!comentarioActual.trim().equals("")) {
			if (validacionParaComentario.getDtoComentario() == null) {
				DTOComentariosDocumentoEntity nuevoComentario = new DTOComentariosDocumentoEntity();
				nuevoComentario.setIdDocumento(validacionParaComentario
						.getIdDocumento());
				nuevoComentario.setComentarios(comentarioActual);
				nuevoComentario
						.setUsuarioComento(usuarioLogueado.getUsername());
				validacionParaComentario.setDtoComentario(nuevoComentario);
				bsdFirmaInterface
						.guardarComentario(validacionParaComentario, dtoEstructuraUsuario);
			} else {
				validacionParaComentario.getDtoComentario().setComentarios(
						comentarioActual);
				bsdFirmaInterface
						.guardarComentario(validacionParaComentario, dtoEstructuraUsuario);
			}
			actualizar();
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_WARN,	Utilidades.mensajeProperties("titulo_growl_advertencia"),	Utilidades.mensajeProperties("mensaje_firma_comentarioVacio")));
		}

	}
	
	public void eliminarComentario() {
		if (validacionParaComentario.getDtoComentario() != null) {
			bsdFirmaInterface.eliminarComentario(validacionParaComentario);
			actualizar();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_WARN,	Utilidades.mensajeProperties("titulo_growl_advertencia"),	Utilidades.mensajeProperties("mensaje_firma_comentarioEliminarVacio")));
		}

	}

	
	/**
	 * Método que verifica si el documento ya se firmó y/o validó
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 18/09/2017
	 */
	public void verificarEdicion(DTOFirmaDocumentosEntity dtoFirma) throws Exception{
		
		DTOFirmaDocumentosEntity dtoBloqueado = bsdFirmaInterface.verificarBloqueo(dtoFirma);
		
		if(dtoBloqueado==null){
		
			Integer mensaje = bsdFirmaInterface.verificarFirmaYValidacion(dtoFirma.getIdDocumento());
			
			if (mensaje==1) {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_WARN,	Utilidades.mensajeProperties("titulo_growl_advertencia"),	Utilidades.mensajeProperties("mensaje_firma_yaFirmo")));
				logger.info("Ya se firmó");
				return;
		        
			}else{
				
				if(dtoFirma.getConModificaciones()==0){
					
			        if(mensaje == 2){
			        	this.dtoFirmaTemp = dtoFirma;
			        	RequestContext context = RequestContext.getCurrentInstance();
			        	context.execute("PF('dlg3').show();");
						logger.info("Ya se validó, se muestra mensaje");		
						return;
			        
			        }else{
			        	
			        	this.dtoFirmaTemp = dtoFirma;
			        	RequestContext context = RequestContext.getCurrentInstance();
			        	context.execute("PF('dlg4').show();");
						logger.info("Preguntando si desea editar");		
						return;
			      
					}
			        
				}else{
					this.dtoFirmaTemp = dtoFirma;
					editarDocumento();
					
				}
				
			}
			
		}else{
				
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento esta siendo editado por "+dtoBloqueado.getDtoPersonaBloqueado().getNombreCompleto()+", espera a que el documento sea liberado para continuar"));
			
		}
	}
	
	/**
	 * Método que abre un documento para su edición registrando en FIRMA_DOCUMENTOS que el documento fue editado
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 06/09/2017
	 */
	public void editarDocumento() throws Exception{
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			bsdFirmaInterface.activarModificacionFirma(this.dtoFirmaTemp.getIdPersona(),this.dtoFirmaTemp.getIdDocumento());
			visualizarDocumento(this.dtoFirmaTemp, false);
			
			//this.dtoFirma = bsdFirmaInterface.consultarDocsFirmas(this.dtoFirmaTemp.getIdPersona(),0);
			
	        FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_INFO,	Utilidades.mensajeProperties("titulo_growl_info"), Utilidades.mensajeProperties("mensaje_firma_editando")));
			logger.info("Editando word...");
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBFirma , Método: iniciar");
			logger.error("<=================== Ocurrió un error al abrir el documento, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorEditar")));
		}	
	}
	
	/**
	 * Método que valida el rechaza de un documento
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void validarRechazarDocumentos(){
		
		if(!this.dtoDocumentosSeleccionados.isEmpty()){
			
			if(this.dtoDocumentosSeleccionados.size() <= 10){
				
				DTOFirmaDocumentosEntity documentoFirma = vhFirmaInterface.verificarOficioSincambios(this.dtoDocumentosSeleccionados);
				
				if(documentoFirma != null){
					
					//RequestContext.getCurrentInstance().execute("PF('confirmacion_regresar').show()");
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "No es posible regresar el documento "+documentoFirma.getDtoDocumento().getNoDocumento()+" sin comentarios. Escribe uno"));
					
				}else{
					
					rechazarDocumentos();
					
				}
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_hastaDiez")));
				
			}
			
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionaUno")));	
		}
	}
	
	/**
	 * Método que valida el rechaza de un documento, para indicar que se le envió por error
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void rechazarDocumentos(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
		
			String mensaje = bsdFirmaInterface.rechazarDocumentos(this.dtoDocumentosSeleccionados);
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
		
			if(mensaje.equals("")){	
				
				dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(), 0);
					
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), Utilidades.mensajeProperties("mensaje_firma_exitoRegresar")));
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), mensaje));
				
			}
				
		}catch(Exception e){
					
			logger.error("<=================== Clase: MBFirma , Método: rechazarDocumentos()");
			logger.error("<=================== Ocurrió un error al rechazar los documentos");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
					
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorRegresar")));

		}
		
	}
	
	/**
	 * Método que envía un mensaje de confirmación de sin cambios en un documento
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 15/10/2017
	 */
	public void validarRegresarARemitente() throws Exception{
		
		if(!this.dtoDocumentosSeleccionados.isEmpty()){
			
			if(this.dtoDocumentosSeleccionados.size() <= 10){
				
				DTOFirmaDocumentosEntity dtoBloqueado = bsdFirmaInterface.verificarBloqueo(this.dtoDocumentosSeleccionados);
				
				if(dtoBloqueado==null){ 

					DTOFirmaDocumentosEntity documentoFirma = vhFirmaInterface.verificarOficioSincambios(this.dtoDocumentosSeleccionados);
					
					if(documentoFirma != null){
						
						//RequestContext.getCurrentInstance().execute("PF('confirmacion_regresar').show()");
						FacesContext context = FacesContext.getCurrentInstance();
				        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "No es posible regresar el documento "+documentoFirma.getDtoDocumento().getNoDocumento()+" sin comentarios. Escribe uno"));
						
					}else{
						
						String mensajeDocsFirmados = bsdFirmaInterface.verificarDocumentosFirmados(this.dtoDocumentosSeleccionados);
						
						if(mensajeDocsFirmados.equals("")){
							
							regresarARemitente();
							
						}else{
							
							this.docsFirmadosRegresadosMensaje = mensajeDocsFirmados;
							RequestContext.getCurrentInstance().execute("PF('confirmacion_regresar_sin_firmar').show()");
							
						}
						
					}
					
				}else{
					 
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento esta siendo editado por "+dtoBloqueado.getDtoPersonaBloqueado().getNombreCompleto()+", espera a que el documento sea liberado para continuar"));
					  
				}
					
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_hastaDiez")));
				
			}
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionaUno")));	
		}
	}
		
	/**
	 * Método que regresa los oficios al remitente
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 15/10/2017
	 */
	public void regresarARemitente() throws Exception{
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
			
			String mensaje = bsdFirmaInterface.regresarARemitente(this.dtoDocumentosSeleccionados);
			
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
		


		

			if(mensaje.equals("")){	
				
				dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(), 0);
					
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), Utilidades.mensajeProperties("mensaje_firma_exitoRegresar")));
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), mensaje));
				
			}
				
		}catch(Exception e){
					
			logger.error("<=================== Clase: MBFirma , Método: regresarARemitente()");
			logger.error("<=================== Ocurrió un error al regresar los oficios al remitente");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
					
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorRegresar")));

		}
	}
		
	/**
	 * Método que marca los documentos seleccionados como leídos
	 *
	 * @author David Rodríguez Corral
	 * @since 20/12/2017
	 */
	public void marcarLeido(SelectEvent event){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		DTOFirmaDocumentosEntity dtoFirma = (DTOFirmaDocumentosEntity) event.getObject();
		
		this.numeroArchivosAFirmar = this.dtoDocumentosSeleccionados.size();
		this.certificado = null;
		this.nombreCertificado = null;
		
		if(dtoFirma.getNoLeido().intValue() == 1){
		
			bsdFirmaInterface.marcarComoLeido(usuarioLogueado.getIdPersona(), dtoFirma.getIdDocumento(), 1);
			dtoFirma.setNoLeido(Integer.parseInt(Utilidades.mensajeProperties("constante_documento_leido")));
			
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
			
		}
	}
	
	/**
	 * Método que obtiene el numero de elementos seleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void obtenerTamanioDeSeleccionados(){
		
		this.numeroArchivosAFirmar = this.dtoDocumentosSeleccionados.size();
		this.certificado = null;
		this.nombreCertificado = null;
		logger.info("Número de documentos seleccionados "+this.numeroArchivosAFirmar);
		 
	}
	
	/**
	 * Método que marca todos los documentos como leídos
	 *
	 * @author David Rodríguez Corral
	 * @since 20/12/2017
	 */
	public void marcarTodosLeidos(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		this.numeroArchivosAFirmar = this.dtoDocumentosSeleccionados.size();
		this.certificado = null;
		this.nombreCertificado = null;
		logger.info("Número de documentos seleccionados "+this.numeroArchivosAFirmar);
		
		List<DTOFirmaDocumentosEntity> noLeidos = vhFirmaInterface.eliminarDocumentosLeidos(this.dtoDocumentosSeleccionados);
		
		if(noLeidos!=null){
			bsdFirmaInterface.marcarComoLeido(usuarioLogueado.getIdPersona(), noLeidos);
			MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
			administrador.consultarNotificacionesSeguimiento();
		}
	}
	
	/**
	 * Método abre el documento en word
	 *
	 * @author David Rodríguez Corral
	 * @throws Exception 
	 * @since 15/10/2017
	 */
	public void visualizarDocumento(DTOFirmaDocumentosEntity dtofirma, Boolean soloLectura) throws  Exception{
		logger.info("Abriendo arhivo en modo lectura "+soloLectura);
		String ruta = "/Gestion4/documentos/";
		//Boolean soloLectura = true;
		if(dtofirma.getConVisualizacion()==0){
			bsdFirmaInterface.activarVisualizacion(dtofirma.getIdPersona(),dtofirma.getIdDocumento());
			dtofirma.setConVisualizacion(1);
		}
		//this.dtoFirma = bsdFirmaInterface.consultarDocsFirmas(dtofirma.getIdPersona(), 0);
		vhFirmaInterface.visualizarDocumento(dtofirma.getDtoDocumento().getNombreDocumento(), ruta, soloLectura);
		
		
	}
	
	/**
	 * Método abre el documento en PDF
	 *
	 * @author David Rodríguez Corral
	 * 
	 * @since 15/10/2017
	 */
	public void mostrarPDF(DTOFirmaDocumentosEntity dtoFirma){
		this.rutaPDF = Utilidades.getRutaGlusterFS() + "/Gestion4/pdf/" + dtoFirma.getDtoDocumento().getNoDocumento()+".pdf";
		if(dtoFirma.getConVisualizacion()==0){
			bsdFirmaInterface.activarVisualizacion(dtoFirma.getIdPersona(),dtoFirma.getIdDocumento());
			dtoFirma.setConVisualizacion(1);
		}
	}

	/**
	 * Método elimina las validaciones (Iniciales de la persona quien validó el documento) de un archivo
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void eliminarValidacion()throws  IOException, Exception{
		
		logger.info("Entrando a la eliminación de la validación");
		String rutaHardcodeada = Utilidades.getRutaGlusterFS();
		String archivo = this.dtoFirmaTemp.getDtoDocumento().getNombreDocumento();
		String rutaGluster = rutaHardcodeada;
		String carpetaDestino = "\\Gestion4\\documentos\\"; 
		String ruta;
		String iniciales = "";
		
		try {
				logger.info("RUTA: " + rutaHardcodeada);			
				ruta = rutaGluster+carpetaDestino+archivo;
				//Envío la ruta del archivo donde se eliminarán las validaciones
				bsdFirmaInterface.eliminarValidacionEnDocumento(this.dtoFirmaTemp);
				vhFirmaInterface.eliminarValidacionEnDocumento(ruta, ruta, iniciales);
				this.dtoFirma = bsdFirmaInterface.consultarDocsFirmas(this.dtoFirmaTemp.getIdPersona(),0);
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.mensajeProperties("titulo_growl_info"), Utilidades.mensajeProperties("mensaje_firma_exitoEliminarValidacion")));
		        
		        visualizarDocumento(this.dtoFirmaTemp,false);
		        //Aquí debo de crear el hash del documento
			
		} catch(FileNotFoundException fnf) {
			logger.error(fnf.getCause() + " --- - - " + fnf.getMessage());
		} catch(Exception e) {
			logger.error("", e);
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorEliminarValidacion")));

		}
		
	}
	
	/**
	 * Método que valida que los documentos que se vayan a firmar no se haya regresado a remitente por otro usuario
	 *
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws CertificateException 
	 * @since 15/10/2017
	 */
	public void validarFirmar() throws FileNotFoundException, IOException, CertificateException{
		
		if(!this.dtoDocumentosSeleccionados.isEmpty()){
			
			if(this.dtoDocumentosSeleccionados.size() <= 10){
				
				// verificamos si existe la ruta destino de los doc firmados, si no la creamos
				String rutaGluster = Utilidades.getRutaGlusterFS()+"\\Gestion4\\";
				String carpetaDestino = "documentos_firmados\\"; 
				File rutaDocFirmados = new File( rutaGluster + carpetaDestino);
				if (!rutaDocFirmados.exists()) {
					rutaDocFirmados.mkdirs();
				}
				
				
				DTOFirmaDocumentosEntity dtoBloqueado = bsdFirmaInterface.verificarBloqueo(this.dtoDocumentosSeleccionados);
				
				if(dtoBloqueado==null){
				
					DTOFirmaDocumentosEntity documentoRegresado = bsdFirmaInterface.verificarRegresado(this.dtoDocumentosSeleccionados);
					
					if(documentoRegresado!=null){
						
						FacesContext context = FacesContext.getCurrentInstance();
				        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento " + documentoRegresado.getDtoDocumento().getNoDocumento() +" "+Utilidades.mensajeProperties("mensaje_firma_validarFirmar")));		
						
					}else{
						
						iniciarFirma();
						
					}
					
				}else{
					 
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento esta siendo editado por "+dtoBloqueado.getDtoPersonaBloqueado().getNombreCompleto()+", espera a que el documento sea liberado para continuar"));
					  
				}
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_hastaDiez")));
				
			}
			
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionaUno")));	
		}
	}	
	
	/**
	 * Método que inicializa el proceso de firma (Paso 1 y 2)
	 *
	 * @author David Rodríguez Corral
	 * @throws CertificateException 
	 * @since 30/10/2017
	 */
	public void iniciarFirma() throws FileNotFoundException, IOException{
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
			if(this.certificado !=null){
				
				if((this.nombreCertificado.substring(this.nombreCertificado.lastIndexOf("."))).equals(".cer") && this.tipoAlgoritmo!=""){
					
					logger.info("Archivo correcto .cer");
					
					logger.info(this.certificado.getFileName());
					
					try{
					
						this.dtoDocumentosSeleccionados = bsdFirmaInterface.obtenerDatosFirmas(this.dtoDocumentosSeleccionados, this.certificado.getContents(), this.tipoAlgoritmo);
						RequestContext context = RequestContext.getCurrentInstance();
				    	context.execute("firmarArchivos('firmaAdministracion',0,'"+this.tipoAlgoritmo+"');");
				    	this.nombreCertificado = null;
						this.certificado = null;
				 
					}
					
					catch(Exception e){
					
						logger.error("<=================== Clase: MBFirma , Método: iniciarFirma");
						logger.error("<=================== Ocurrió un error al firmar los documentos");
						logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
						logger.error("",e);
						
						FacesContext context = FacesContext.getCurrentInstance();
				        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorFirmar")));
				        this.nombreCertificado = null;
						this.certificado = null;
					}
					
				}else{
					
					logger.info("No se ha seleccionado el archivo correcto .cer");
					
					RequestContext context = RequestContext.getCurrentInstance();
			    	context.execute("firmarArchivos('firmaAdministracion',1,'"+this.tipoAlgoritmo+"');");
			    	this.nombreCertificado = null;
					this.certificado = null;
				}
			}else{
				
				logger.info("No se ha seleccionado ningún archivo para el certificado");
				
				RequestContext context = RequestContext.getCurrentInstance();
		    	context.execute("firmarArchivos('firmaAdministracion',0,'SHA1');");
		    	this.nombreCertificado = null;
				this.certificado = null;
			}
	}
	
	/**
	 * Método que actualiza el proceso de firma (Paso 4)
	 *
	 * @author David Rodríguez Corral
	 * @since 30/10/2017
	 */
	public void cerrarFirma() throws IOException, FileNotFoundException{
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		for (DTOFirmaDocumentosEntity dtoFirma : this.dtoDocumentosSeleccionados) {
			logger.info("id proceso "+dtoFirma.getDtoFirmaElectronica().getIdProceso());
			logger.info("hash "+dtoFirma.getDtoFirmaElectronica().getHashCadenaOriginal());
			logger.info("pkcs7 "+dtoFirma.getDtoFirmaElectronica().getPkcs7());
		}
		
		try{
			
			bsdFirmaInterface.enviarPKCS7(this.dtoDocumentosSeleccionados);
			
			this.dtoFirma = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),0);
			
			this.dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(),1);
			
			iniciar();
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), Utilidades.mensajeProperties("mensaje_firma_exitoFirmar")));
			
		}catch(FileNotFoundException f){
			
			logger.error("Excepcion: " + f + " , Mensaje: " + f.getMessage() );
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento está siendo editado por otra persona, no es posible firmar en este momento"));
	        
		}catch(Exception e){
			logger.error("<=================== Clase: MBFirma , Método: cerrarFirma");
			logger.error("<=================== Ocurrió un error al firmar los documentos");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorFirmar")));
	        
		}
		
	}
	
	/**
	 * Método que realiza la acción de envío o regreso a remitente de los documentos ya firmados
	 *
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @since 30/10/2017
	 */
	public void ValidarCerrarDocumento(){
		
		if(!this.dtoDocumentosSeleccionadosFirmados.isEmpty()){
					
			
		}else{
					
			FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionaUno")));	
		}
	}
	
	/**
	 * Método que realiza la acción de envío o regreso a remitente de los documentos ya firmados
	 *
	 * @author David Rodríguez Corral
	 * @throws IOException 
	 * @since 30/10/2017
	 */
	public void cerrarDocumento() throws IOException{
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		if(!this.dtoDocumentosSeleccionadosFirmados.isEmpty()){
			
			if(this.dtoDocumentosSeleccionadosFirmados.size() <= 10){
			
				DTOFirmaDocumentosEntity documentoRegresado = bsdFirmaInterface.verificarRegresado(this.dtoDocumentosSeleccionados);
				
				if(documentoRegresado==null){
				
					switch(this.comboFirmados) {
					
					case 0:
						logger.info("Selecciona una opción válida");
						
						FacesContext contexto = FacesContext.getCurrentInstance();
				        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionOpcion")));
					
			
						break;
					
					case 1:
					case 2:
					case 3:
						
						try{
							
							String documento = vhFirmaInterface.verificarOficioConNotificacion(this.dtoDocumentosSeleccionadosFirmados);
							
							if(documento!=null){
								
								FacesContext context = FacesContext.getCurrentInstance();
						        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento " + documento + Utilidades.mensajeProperties("mensaje_firma_validarRegresarFirmado")));	
								
							}else{
								logger.info("Entrando a enviar");
							
								logger.info("Enviar a destinatario (con envío a oficialía)");
								String mensaje = bsdFirmaInterface.enviarADestinatario(this.dtoDocumentosSeleccionadosFirmados, usuarioLogueado.getIdArea(), usuarioLogueado.getTipoArea(), this.comboFirmados);
								this.dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(), 1);
								logger.info(mensaje);
								
								if(mensaje.equals("")){
									
									MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
									administrador.consultarNotificacionesSeguimiento();
									
									FacesContext context = FacesContext.getCurrentInstance();
							        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), Utilidades.mensajeProperties("mensaje_firma_exitoEnviarADestinatario")));
							
								}else{
									
									FacesContext context = FacesContext.getCurrentInstance();
							        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), mensaje));
									
								}
							}
							
						}catch (Exception e) {
							
							logger.error("<=================== Clase: MBFirma , Método: cerrarDocumento, opción 1");
							logger.error("<=================== Ocurrió un error al regresar los documentos firmados al remitente");
							logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
							logger.error("",e);
							
							FacesContext context = FacesContext.getCurrentInstance();
					        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorEnviarADestinatario")));
						}
			
						break;
						
					case 4:
						try{
							
							logger.info("Regresar a remitente");
							bsdFirmaInterface.regresarARemitenteDocFirmado(this.dtoDocumentosSeleccionadosFirmados);
							bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(), 1);
							this.dtoFirmados = bsdFirmaInterface.consultarDocsFirmas(usuarioLogueado.getIdPersona(), 1);
							
							MBAdministradorGestion administrador = ((MBAdministradorGestion) ApplicationContextUtils.getApplicationContext().getBean("mbAdministrador"));
							administrador.consultarNotificacionesSeguimiento();
							
							FacesContext context = FacesContext.getCurrentInstance();
					        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.mensajeProperties("titulo_growl_exito"), Utilidades.mensajeProperties("mensaje_firma_exitoRegresarFirmado")));
							
						}catch (Exception e) {
							
							logger.error("<=================== Clase: MBFirma , Método: cerrarDocumento, opción 4");
							logger.error("<=================== Ocurrió un error al regresar los documentos firmados al remitente");
							logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
							logger.error("",e);
							
							FacesContext context = FacesContext.getCurrentInstance();
					        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_errorRegresarFirmado")));
						}
						break;
			
					}
					
				}else{
					
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "El documento " + documentoRegresado.getDtoDocumento().getNoDocumento() + "No es posible enviar a destinatario ya que el documento "+documentoRegresado.getDtoDocumento().getNoDocumento()+" no quiso firmar"));
					
				}
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
			    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_hastaDiez")));
				
			}
			
		}else{
				
			FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), Utilidades.mensajeProperties("mensaje_firma_seleccionaUno")));	
		}
	
	}

	/**
	 * Método que reicbe el certificado del componente fileUpload
	 *
	 * @param event
	 *
	 * @author David Rodríguez Corral 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @since 30/10/2017
	 */
	public void handleFileUpload(FileUploadEvent event) throws CertificateException, IOException {
		this.certificado = event.getFile();
		this.nombreCertificado = this.certificado.getFileName();
		try{
		this.tipoAlgoritmo = vhFirmaInterface.obtenerAlgoritmoCertificado(this.certificado.getInputstream());
		logger.info("El archivo .cer es "+ this.certificado.getFileName());
		}catch(CertificateException c){
			this.tipoAlgoritmo = "";
			logger.error("El certificado es incorrecto");
			
		}
    }
	
	/**
	 * Método para abrir la ventana de información con los datos del documento
	 * 
	 * @param dtoValidacion: Objeto DTOValidacionEntity con el documento para validación seleccionado
	 * 
	 * @author David Roodríguez Corral
	 * @since 10/01/2018
	 */
	public void abrirInformacionDocumento(DTOFirmaDocumentosEntity dtoFirma) {
		
		try {
			documentoDetalle = dtoFirma.getDtoDocumento();
			
			documentoDetalleHelper = bsdFirmaInterface.obtenerDetalleDocumento(Integer.valueOf(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("inicio");
			documentoDetalleHelper.setTituloInfoDetalle("Detalle de documento");

			documentoDetalleHelper.setHistorial(bsdFirmaInterface.consultarHistorialPorIdDocumento(documentoDetalle.getIdDocumento()));
			RequestContext.getCurrentInstance().execute("PF('inf-doc-dialog').show()");
		} catch (Exception e) {}
	}
	
	public void regresarInicio() {
		try {
			documentoDetalleHelper = bsdFirmaInterface.obtenerDetalleDocumento(Integer.valueOf(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("inicio");
			documentoDetalleHelper.setTituloInfoDetalle("Detalle de documento");
		} catch (Exception e) {}
	}
	
	public void verHistorial() {
		try {
			documentoDetalleHelper.setHistorial(bsdFirmaInterface.consultarHistorialPorIdDocumento(documentoDetalle.getIdDocumento()));
			documentoDetalleHelper.setTipoInfoDetalle("historial");
			documentoDetalleHelper.setTituloInfoDetalle("Historial de documento");
		} catch (Exception e) {}
	}

	public void verAnexos() {
		try {
			documentoDetalleHelper.setListaDTOAnexos(bsdFirmaInterface.obtenerAnexoPorDocumento(documentoDetalle));
			documentoDetalleHelper.setTipoInfoDetalle("anexos");
			documentoDetalleHelper.setTituloInfoDetalle("Anexos de documento");
		} catch (Exception e) {}
	}

	public void verComentarios() {
		try {
			documentoDetalleHelper.setComentarios(bsdFirmaInterface.consultarComentarios(documentoDetalle));
			documentoDetalleHelper.setTipoInfoDetalle("comentarios");
			documentoDetalleHelper.setTituloInfoDetalle("Comentarios de documento");
		} catch (Exception e) {}
	}

	public void verComentario(DTOComentariosDocumentoEntity comentario) {
		try {
			documentoDetalleHelper.setComentario(comentario);
			documentoDetalleHelper.setTipoInfoDetalle("comentario");
			documentoDetalleHelper.setTituloInfoDetalle("Comentario de documento");
		} catch (Exception e) {}
	}

	/**
	 * @return valor de tipo DTOFirmaDocumentosEntity que esta contenido en la variable dtoFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public List<DTOFirmaDocumentosEntity> getDtoFirma() {
		return dtoFirma;
	}

	/**
	 * @param dtoFirma : valor que se ingresa a la variable de tipo DTOFirmaDocumentosEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setDtoFirma(List<DTOFirmaDocumentosEntity> dtoFirma) {
		this.dtoFirma = dtoFirma;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable muestraFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public Boolean getMuestraFirma() {
		return muestraFirma;
	}

	/**
	 * @param muestraFirma : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setMuestraFirma(Boolean muestraFirma) {
		this.muestraFirma = muestraFirma;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable comboFirma
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public Integer getComboFirma() {
		return comboFirma;
	}

	/**
	 * @param comboFirma : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 14/09/2017
	 */
	public void setComboFirma(Integer comboFirma) {
		this.comboFirma = comboFirma;
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
	 * @param idDocTemp : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setIdDocTemp(Integer idDocTemp) {
		this.idDocTemp = idDocTemp;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable comentarioTemp
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public String getComentarioTemp() {
		return comentarioTemp;
	}

	/**
	 * @param comentarioTemp : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setComentarioTemp(String comentarioTemp) {
		this.comentarioTemp = comentarioTemp;
	}

	/**
	 * @return valor de tipo DTOFirmaDocumentosEntity que esta contenido en la variable dtoDocumentosSeleccionados
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public List<DTOFirmaDocumentosEntity> getDtoDocumentosSeleccionados() {
		return dtoDocumentosSeleccionados;
	}

	/**
	 * @param dtoDocumentosSeleccionados : valor que se ingresa a la variable de tipo DTOFirmaDocumentosEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoDocumentosSeleccionados(
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionados) {
		this.dtoDocumentosSeleccionados = dtoDocumentosSeleccionados;
	}

	/**
	 * @return valor de tipo DTOBorradorDocumentosEntity que esta contenido en la variable dtoBorradorDocumento
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public DTOBorradorDocumentosEntity getDtoBorradorDocumento() {
		return dtoBorradorDocumento;
	}

	/**
	 * @param dtoBorradorDocumento : valor que se ingresa a la variable de tipo DTOBorradorDocumentosEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 03/10/2017
	 */
	public void setDtoBorradorDocumento(
			DTOBorradorDocumentosEntity dtoBorradorDocumento) {
		this.dtoBorradorDocumento = dtoBorradorDocumento;
	}

	/**
	 * @return valor de tipo DTOFirmaElectronicaHelper que esta contenido en la variable firmaH
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public DTOFirmaElectronicaHelper getFirmaH() {
		return firmaH;
	}

	/**
	 * @param firmaH : valor que se ingresa a la variable de tipo DTOFirmaElectronicaHelper
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void setFirmaH(DTOFirmaElectronicaHelper firmaH) {
		this.firmaH = firmaH;
	}

	/**
	 * @return valor de tipo Integer que esta contenido en la variable numeroArchivosAFirmar
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public Integer getNumeroArchivosAFirmar() {
		return numeroArchivosAFirmar;
	}

	/**
	 * @param numeroArchivosAFirmar : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void setNumeroArchivosAFirmar(Integer numeroArchivosAFirmar) {
		this.numeroArchivosAFirmar = numeroArchivosAFirmar;
	}

	/**
	 * @return valor de tipo List<DTOFirmaDocumentosEntity> que esta contenido en la variable dtoFirmados
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public List<DTOFirmaDocumentosEntity> getDtoFirmados() {
		return dtoFirmados;
	}

	/**
	 * @param dtoFirmados : valor que se ingresa a la variable de tipo List<DTOFirmaDocumentosEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void setDtoFirmados(List<DTOFirmaDocumentosEntity> dtoFirmados) {
		this.dtoFirmados = dtoFirmados;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable muestraBotonAceptar
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public Boolean getMuestraBotonAceptar() {
		return muestraBotonAceptar;
	}

	/**
	 * @param muestraBotonAceptar : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 15/10/2017
	 */
	public void setMuestraBotonAceptar(Boolean muestraBotonAceptar) {
		this.muestraBotonAceptar = muestraBotonAceptar;
	}

	public DTOFirmaDocumentosEntity getDtoFirmaTemp() {
		return dtoFirmaTemp;
	}

	public void setDtoFirmaTemp(DTOFirmaDocumentosEntity dtoFirmaTemp) {
		this.dtoFirmaTemp = dtoFirmaTemp;
	}

	public UploadedFile getCertificado() {
		return certificado;
	}

	public void setCertificado(UploadedFile certificado) {
		this.certificado = certificado;
	}
	
	public Integer getComboFirmados() {
		return comboFirmados;
	}

	public void setComboFirmados(Integer comboFirmados) {
		this.comboFirmados = comboFirmados;
	}

	public List<DTOFirmaDocumentosEntity> getDtoDocumentosSeleccionadosFirmados() {
		return dtoDocumentosSeleccionadosFirmados;
	}

	public void setDtoDocumentosSeleccionadosFirmados(
			List<DTOFirmaDocumentosEntity> dtoDocumentosSeleccionadosFirmados) {
		this.dtoDocumentosSeleccionadosFirmados = dtoDocumentosSeleccionadosFirmados;
	}

	public String getHashDocumento() {
		return hashDocumento;
	}

	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
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
	 * @param tiempoRestanteActualizacion the tiempoRestanteActualizacion to set
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 02/11/2017
	 */
	public void setTiempoRestanteActualizacion(Integer tiempoRestanteActualizacion) {
		this.tiempoRestanteActualizacion = tiempoRestanteActualizacion;
	}

	public String getNombreCertificado() {
		return nombreCertificado;
	}

	public void setNombreCertificado(String nombreCertificado) {
		this.nombreCertificado = nombreCertificado;
	}

	public String getTipoAlgoritmo() {
		return tipoAlgoritmo;
	}

	public void setTipoAlgoritmo(String tipoAlgoritmo) {
		this.tipoAlgoritmo = tipoAlgoritmo;
	}

	public List<DTOComentariosDocumentoEntity> getDtoComentarios() {
		return dtoComentarios;
	}

	public void setDtoComentarios(List<DTOComentariosDocumentoEntity> dtoComentarios) {
		this.dtoComentarios = dtoComentarios;
	}

	public DTOComentariosDocumentoEntity getDtoComentariosDocumento() {
		return dtoComentariosDocumento;
	}

	public void setDtoComentariosDocumento(
			DTOComentariosDocumentoEntity dtoComentariosDocumento) {
		this.dtoComentariosDocumento = dtoComentariosDocumento;
	}

	public DTOFirmaDocumentosEntity getValidacionParaComentario() {
		return validacionParaComentario;
	}

	public void setValidacionParaComentario(
			DTOFirmaDocumentosEntity validacionParaComentario) {
		this.validacionParaComentario = validacionParaComentario;
	}

	public String getComentarioActual() {
		return comentarioActual;
	}

	public void setComentarioActual(String comentarioActual) {
		this.comentarioActual = comentarioActual;
	}

	public String getRutaPDF() {
		return rutaPDF;
	}

	public void setRutaPDF(String rutaPDF) {
		this.rutaPDF = rutaPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable docsFirmadosRegresadosMensaje
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public String getDocsFirmadosRegresadosMensaje() {
		return docsFirmadosRegresadosMensaje;
	}

	/**
	 * @param docsFirmadosRegresadosMensaje : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 04/01/2018
	 */
	public void setDocsFirmadosRegresadosMensaje(
			String docsFirmadosRegresadosMensaje) {
		this.docsFirmadosRegresadosMensaje = docsFirmadosRegresadosMensaje;
	}

	/**
	 * @return valor de tipo DTODocumentoOficialiaHelper que esta contenido en la variable documentoDetalleHelper
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/01/2018
	 */
	public DTODocumentoOficialiaHelper getDocumentoDetalleHelper() {
		return documentoDetalleHelper;
	}

	/**
	 * @param documentoDetalleHelper : valor que se ingresa a la variable de tipo DTODocumentoOficialiaHelper
	 *
	 * @author David Rodríguez Corral
	 * @since 10/01/2018
	 */
	public void setDocumentoDetalleHelper(
			DTODocumentoOficialiaHelper documentoDetalleHelper) {
		this.documentoDetalleHelper = documentoDetalleHelper;
	}

	/**
	 * @return valor de tipo DTODocumentoEntity que esta contenido en la variable documentoDetalle
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/01/2018
	 */
	public DTODocumentoEntity getDocumentoDetalle() {
		return documentoDetalle;
	}

	/**
	 * @param documentoDetalle : valor que se ingresa a la variable de tipo DTODocumentoEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 10/01/2018
	 */
	public void setDocumentoDetalle(DTODocumentoEntity documentoDetalle) {
		this.documentoDetalle = documentoDetalle;
	}

}
