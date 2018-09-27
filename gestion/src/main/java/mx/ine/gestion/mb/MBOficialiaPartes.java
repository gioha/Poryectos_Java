/**
 * @(#)MBOficialiaPartes.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bsd.inter.BSDOficialiaPartesInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCCategoriasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCSeccionesEntity;
import mx.ine.gestion.dto.helper.DTODocumentoOficialiaHelper;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHOficialiaPartesInterface;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Clase de la capa de MB para el módulo Oficialia de partes.
 * 
 * @author Sergio Ley Garcia
 * @since 02/11/2017
 * 
 * @update David Rodríguez Corral
 * @since 05/12/2017
 */
public class MBOficialiaPartes implements Serializable {
	
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger	logger	= Logger.getLogger(MBOficialiaPartes.class);
	
	/**
	 * Atributo para la serialización de la clase.
	 */
	private static final long serialVersionUID	= -6492433805176611851L;

	/**
	 * Objeto que contiene el folio y las listas de remitentes, destinatarios, ccp y anexos de un documento
	 */
	private DTODocumentoOficialiaHelper	datosOficialia;
	
	/**
	 * Objeto que es recibido del módulo Bandeja de oficialía para la clasificación de un documento
	 */
	private DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada;
	
	/**
	 * Objeto que es recibido del módulo Bandeja de oficialía para la clasificación de un documento
	 */
	private DTOBandejaEntradasOficialiaEntity dtoBandejaEntradaElectronico;
	
	/**
	 * Objeto que es recibido del módulo Bandeja de oficialía para la clasificación de un documento
	 */
	private DTOBandejaEntradasOficialiaEntity dtoBandejaEntradaFisico;
	
	/**
	 * Atributo utilizado para guardar la ruta de los documentos pdf en el Gluster
	 */
	private String rutaGlusterDocumento;
	
	/**
	 * Atributo utilizado para guardar la ruta de los Anexos en el Gluster
	 */
	private String rutaGlusterAnexos;
	
	/**
	 * Lista de las secciones en donde se clasificará el documento
	 */
	private List<DTOCSeccionesEntity> listaDTOSecciones;
	
	/**
	 * Lista de las categorías en donde se clasificará el documento
	 */
	private List<DTOCCategoriasEntity> listaDTOCategorias;
	
	/**
	 * Seccion seleccionada
	 */
	private String seccionSeleccionada;
	
	/**
	 * Categoría seleccionada
	 */
	private String categoriaSeleccionada;
	
	/**
	 * Indica si el documentado fue clasificado = 1 o se canceló = 2 para mostrar mensaje de info o éxito al regresar a
	 * la bandeja de entrada de oficialia
	 */
	private Integer clasificado;
	
	private DTOBandejaEntradasOficialiaEntity folioPendiente;
	
	@Autowired
	@Qualifier("bsdOficialiaPartes")
	private transient BSDOficialiaPartesInterface bsdOficialiaPartesInterface;
	
	@Autowired
	@Qualifier("vhOficialiaPartes")
	private transient VHOficialiaPartesInterface vhOficialiaPartesInterface;
	
	/**
	 * Método de carga inicial de la pantalla
	 * 
	 *
	 * @author Sergio Ley Garcia
	 * @since 06/11/2017
	 * 
	 * @update David Rodríguez Corral
	 * @since 06/12/2017
	 */
	public void iniciar() {
		
		logger.info("<============== INICIANDO CLASIFICACIÓN DE DOCUMENTOS ELECTRÓNICOS ==================>");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
		
			if(this.dtoBandejaEntradaElectronico!=null){
				
				this.dtoBandejaEntrada = this.dtoBandejaEntradaElectronico;
				
			}else{
				
				this.folioPendiente = bsdOficialiaPartesInterface.consultarFoliosPendientes(usuarioLogueado.getIdOficialia(), 'A');
				this.dtoBandejaEntrada = this.folioPendiente;
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Por favor termina de asignar este folio antes de continuar capturando"));
			}
			
			
			String tipoApartado = Utilidades.mensajeProperties("constante_tipoApartado_electronico");
			this.datosOficialia = bsdOficialiaPartesInterface.consultarDatosClasificacion(this.dtoBandejaEntrada, tipoApartado);
			
			if(this.folioPendiente!=null){
				this.datosOficialia.setFolio(this.folioPendiente.getFolio());
			}
			
			
			this.listaDTOSecciones = bsdOficialiaPartesInterface.consultarSecciones();
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartes , Método: iniciar");
			logger.error("<=================== Ocurrió un error al cargar, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al cargar los datos, por favor intentelo de nuevo"));
		}
			
		this.listaDTOCategorias = new ArrayList<DTOCCategoriasEntity>();
		this.rutaGlusterDocumento = vhOficialiaPartesInterface.obtenerRutaGlusterPdf();
		this.rutaGlusterAnexos = vhOficialiaPartesInterface.obtenerRutaGlusterAnexo();
		this.seccionSeleccionada = "0";
		this.categoriaSeleccionada = "0";	
		
	}
	
	/**
	 * Método que obtiene la lista de categorias a partir de una sección
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/11/2017
	 */
	public void consultarCategoria(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
		
			this.categoriaSeleccionada = "0";
			this.listaDTOCategorias = bsdOficialiaPartesInterface.consultarCategorias(Integer.parseInt(this.seccionSeleccionada));
			
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartes , Método: consultarCategoria");
			logger.error("<=================== Ocurrió un error al cargar las categorías, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al cargar los datos, por favor intentelo de nuevo"));
		}
	}
	
	/**
	 * Método que clasifica el documento 
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/11/2017
	 */
	public void clasificarDocumento(){
		logger.info("Clasificando el documento");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
		
			/*
			if(Integer.parseInt(this.seccionSeleccionada)==0){
				
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Selecciona una sección"));
				
			}else{
				*/
				if(Integer.parseInt(this.seccionSeleccionada)!=0 && Integer.parseInt(this.categoriaSeleccionada)==0){
					
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Selecciona una categoría"));
					
				}else{
					
					bsdOficialiaPartesInterface.clasificarDocumento(this.dtoBandejaEntrada, this.seccionSeleccionada, 
							this.categoriaSeleccionada, this.datosOficialia.getFolio());
					
					this.clasificado = 1;
					
					RequestContext.getCurrentInstance().execute("regresar()");
					
				}
			
			//}
			
		
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartes , Método: clasificarDocumento");
			logger.error("<=================== Ocurrió un error al clasificar el documento, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un problema al clasificar el documento por favor intentelo de nuevo"));
		}
	}
	
	/**
	 * Método que cancela la clasificación del documento 
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/11/2017
	 */
	public void cancelarClasificarDocumento(){
		logger.info("Cancelando la clasificación");
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		try{
		
			bsdOficialiaPartesInterface.cancelarClasificarDocumento(this.dtoBandejaEntrada.getIdDocumento(), this.dtoBandejaEntrada.getIdAreaDestinatario(),
					this.dtoBandejaEntrada.getTipoAreaDestinatario(), this.datosOficialia.getFolio());
			
			this.clasificado = 2;
			
			RequestContext.getCurrentInstance().execute("regresar()");
		
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBOficialiaPartes , Método: cancelarClasificarDocumento");
			logger.error("<=================== Ocurrió un error al cancelar la clasificación del documento, por favor intetelo de nuevo");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("titulo_growl_advertencia"), "Ocurrió un error al cancelar la clasificación del documento, por favor intentelo de nuevo"));
		}
			
	}
	
	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable dtoBandejaEntrada
	 * 
	 * @author David Rodríguez Corral
	 * @since 06/12/2017
	 */
	public DTOBandejaEntradasOficialiaEntity getDtoBandejaEntrada() {
		return dtoBandejaEntrada;
	}

	/**
	 * @param dtoBandejaEntrada : valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 06/12/2017
	 */
	public void setDtoBandejaEntrada(
			DTOBandejaEntradasOficialiaEntity dtoBandejaEntrada) {
		this.dtoBandejaEntrada = dtoBandejaEntrada;
	}


	/**
	 * @return valor de tipo String que esta contenido en la variable rutaGlusterDocumento
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/12/2017
	 */
	public String getRutaGlusterDocumento() {
		return rutaGlusterDocumento;
	}


	/**
	 * @param rutaGlusterDocumento : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 07/12/2017
	 */
	public void setRutaGlusterDocumento(String rutaGlusterDocumento) {
		this.rutaGlusterDocumento = rutaGlusterDocumento;
	}

	/**
	 * @return valor de tipo List<DTOCSeccionesEntity> que esta contenido en la variable listaDTOSecciones
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCSeccionesEntity> getListaDTOSecciones() {
		return listaDTOSecciones;
	}


	/**
	 * @param listaDTOSecciones : valor que se ingresa a la variable de tipo List<DTOCSeccionesEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setListaDTOSecciones(List<DTOCSeccionesEntity> listaDTOSecciones) {
		this.listaDTOSecciones = listaDTOSecciones;
	}

	/**
	 * @return valor de tipo List<DTOCCategoriasEntity> que esta contenido en la variable listaDTOCategorias
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public List<DTOCCategoriasEntity> getListaDTOCategorias() {
		return listaDTOCategorias;
	}


	/**
	 * @param listaDTOCategorias : valor que se ingresa a la variable de tipo List<DTOCCategoriasEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setListaDTOCategorias(List<DTOCCategoriasEntity> listaDTOCategorias) {
		this.listaDTOCategorias = listaDTOCategorias;
	}

	
	/**
	 * @return valor de tipo String que esta contenido en la variable seccionSeleccionada
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public String getSeccionSeleccionada() {
		return seccionSeleccionada;
	}


	/**
	 * @param seccionSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setSeccionSeleccionada(String seccionSeleccionada) {
		this.seccionSeleccionada = seccionSeleccionada;
	}


	/**
	 * @return valor de tipo String que esta contenido en la variable categoriaSeleccionada
	 * 
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public String getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}


	/**
	 * @param categoriaSeleccionada : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 08/12/2017
	 */
	public void setCategoriaSeleccionada(String categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}


	/**
	 * @return valor de tipo DTODocumentoOficialiaHelper que esta contenido en la variable datosOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public DTODocumentoOficialiaHelper getDatosOficialia() {
		return datosOficialia;
	}


	/**
	 * @param datosOficialia : valor que se ingresa a la variable de tipo DTODocumentoOficialiaHelper
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setDatosOficialia(DTODocumentoOficialiaHelper datosOficialia) {
		this.datosOficialia = datosOficialia;
	}


	/**
	 * @return valor de tipo String que esta contenido en la variable rutaGlusterAnexos
	 * 
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public String getRutaGlusterAnexos() {
		return rutaGlusterAnexos;
	}


	/**
	 * @param rutaGlusterAnexos : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 09/12/2017
	 */
	public void setRutaGlusterAnexos(String rutaGlusterAnexos) {
		this.rutaGlusterAnexos = rutaGlusterAnexos;
	}


	/**
	 * @return valor de tipo Integer que esta contenido en la variable clasificado
	 * 
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public Integer getClasificado() {
		return clasificado;
	}


	/**
	 * @param clasificado : valor que se ingresa a la variable de tipo Integer
	 *
	 * @author David Rodríguez Corral
	 * @since 10/12/2017
	 */
	public void setClasificado(Integer clasificado) {
		this.clasificado = clasificado;
	}

	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable dtoBandejaEntradaElectronico
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity getDtoBandejaEntradaElectronico() {
		return dtoBandejaEntradaElectronico;
	}

	/**
	 * @param dtoBandejaEntradaElectronico: valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public void setDtoBandejaEntradaElectronico(
			DTOBandejaEntradasOficialiaEntity dtoBandejaEntradaElectronico) {
		this.dtoBandejaEntradaElectronico = dtoBandejaEntradaElectronico;
	}

	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable dtoBandejaEntradaFisico
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity getDtoBandejaEntradaFisico() {
		return dtoBandejaEntradaFisico;
	}

	/**
	 * @param dtoBandejaEntradaFisico: valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public void setDtoBandejaEntradaFisico(
			DTOBandejaEntradasOficialiaEntity dtoBandejaEntradaFisico) {
		this.dtoBandejaEntradaFisico = dtoBandejaEntradaFisico;
	}

	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable folioPendiente
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public DTOBandejaEntradasOficialiaEntity getFolioPendiente() {
		return folioPendiente;
	}

	/**
	 * @param folioPendiente: valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity 
	 *
	 * @author David Rodríguez Corral
	 * @since 22/01/2018
	 */
	public void setFolioPendiente(DTOBandejaEntradasOficialiaEntity folioPendiente) {
		this.folioPendiente = folioPendiente;
	}

}
