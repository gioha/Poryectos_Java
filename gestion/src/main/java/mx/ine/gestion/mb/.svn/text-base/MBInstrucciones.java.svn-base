/**
 * @(#)MBInstrucciones.java 14/08/2017
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

import mx.ine.gestion.bsd.inter.BSDInstruccionesInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase (ManageBean) que se encarga de recibir y delegar las peticiones que lleguen 
 * de la pantalla de administracion para captura, consulta y modificación de instrucciones.
 *
 * @author David Rodríguez Corral
 * @since 14/08/2017
 */
public class MBInstrucciones implements Serializable{
	
	/**
	 * Atributo para la serialización de la clase. 
	 */
	private static final long serialVersionUID = 3882052087943679546L;

	/**
	 * Atributo log. 
	 */
	private static final Logger logger = Logger.getLogger(MBInstrucciones.class);
	
	/**
	 * Objeto que contiene los datos para una Instrucción
	 */
	private DTOInstruccionesEntity dtoInstrucciones;

	/**
	 * Lista que guarda en una lista de objetos pertenecientes a instrucciones de tipo Atención.
	 */
	private List<DTOInstruccionesEntity> dtoListaAtencion;
	
	/**
	 * Lista que guarda en una lista de objetos pertenecientes a instrucciones de tipo Informativa.
	 */
	private List<DTOInstruccionesEntity> dtoListaInformativa;
	
	/**
	 * Atributo que guarda el tipo de área capturado en pantalla.
	 */
	private String tipoArea;
	
	/**
	 * Atributo que bloquea el campo de "Tiempo de respuesta".
	 */
	private Boolean bloqueaTiempo;
	
	/**
	 * Atributo que bloquea el boton Aceptar en caso en que los datatables esten en modo editable".
	 */
	private Boolean bAceptar;
	
	/**
	 * Atributo que bloquea el boton Aceptar en caso en que los datatables esten en modo editable".
	 */
	private Integer contadorEdicion;
	
	
		
	@Autowired
	@Qualifier("bsdInstrucciones")
	private transient BSDInstruccionesInterface bsdInstruccionesInterface;
	//@Autowired
	//private ResourceBundleMessageSource messageSource;
	
	
	/* ----------------------------------------------------------------------------------------- */
	/* --------------------------------- INICIO - EVENTOS -------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */
	
	/**
	 * Método para inicializar los atributos que se necesiten en cuanto carga 
	 * la pantalla de administración
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	
	public void iniciar(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
						
		this.dtoListaAtencion = new ArrayList<DTOInstruccionesEntity>();
		this.dtoListaInformativa = new ArrayList<DTOInstruccionesEntity>();
		this.dtoInstrucciones = new DTOInstruccionesEntity();
		this.bloqueaTiempo = true;
		this.bAceptar = false;
		this.contadorEdicion = 0;
		
		try{
		
			this.dtoListaAtencion = bsdInstruccionesInterface.consultarInstruccciones(usuarioLogueado.getIdPersona(),1);
			this.dtoListaInformativa = bsdInstruccionesInterface.consultarInstruccciones(usuarioLogueado.getIdPersona(),2);
			
			logger.info("Pantalla de instrucciones iniciada");
			logger.info("Usuario en sesión: "+ usuarioLogueado.getUsername()+" con ID "+usuarioLogueado.getIdPersona()+" nombre: "+usuarioLogueado.getNombreUsuario());
		
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBInstrucciones , Método: iniciar");
			logger.error("<=================== Ocurrió un error al cargar las instrucciones del usuario en sesión");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utilidades.mensajeProperties("mensaje_instrucciones_cargarError"), ""));
		}
		
	}
	
	/**
	 * Método que agrega una instrucción al datatable de acuerdo al tipo de instrucción.
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void agregar(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		if(this.dtoInstrucciones.getTipoInstruccion()==1){
			
			String mensajeErrorAtencion = bsdInstruccionesInterface.limitarInstrucciones(this.dtoListaAtencion, 5);
			
			if (!mensajeErrorAtencion.isEmpty()) {
				
		        FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_INFO,	mensajeErrorAtencion,	""));
		        limpiarCampos();
				return;
			}
			
			this.dtoListaAtencion.add(new DTOInstruccionesEntity(usuarioLogueado.getIdPersona(),this.dtoInstrucciones.getTipoInstruccion(),this.dtoInstrucciones.getDescripcion(),this.dtoInstrucciones.getDiasRespuesta(),this.dtoInstrucciones.getConNotificacion(),"A",(this.dtoListaAtencion.size())+1));
		
		}if(this.dtoInstrucciones.getTipoInstruccion()==2){
			
			String mensajeErrorInfo = bsdInstruccionesInterface.limitarInstrucciones(this.dtoListaInformativa, 3);
			
			if (!mensajeErrorInfo.isEmpty()) {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_INFO,	mensajeErrorInfo,	""));
				limpiarCampos();
				return;
			}
			
			this.dtoListaInformativa.add(new DTOInstruccionesEntity(usuarioLogueado.getIdPersona(),this.dtoInstrucciones.getTipoInstruccion(),this.dtoInstrucciones.getDescripcion(),0,0,"A",(this.dtoListaInformativa.size())+1));
			
		}
		limpiarCampos();
	}
	
	/**
	 * Método que guarda en la bd las listas de instrucciones que el usuario modificó
	 *
	 * @author David Rodríguez Corral
	 * @since 01/09/2017
	 */
	public void actualizarInstrucciones(){
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		try{
			
			//Se hace la eliminación lógica de las instrucciones del usuario en sesión
			bsdInstruccionesInterface.eliminarInstruccionesLogicas(usuarioLogueado.getIdPersona());		
			//Se concatenan las listas y se hace la actualización
			bsdInstruccionesInterface.actualizarInstrucciones(bsdInstruccionesInterface.concatenarListas(this.dtoListaAtencion, this.dtoListaInformativa));
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_FATAL,	Utilidades.mensajeProperties("mensaje_instrucciones_aceptarExito"),	""));
			
			//Se cargan instrucciones de la bd
			this.dtoListaAtencion = bsdInstruccionesInterface.consultarInstruccciones(usuarioLogueado.getIdPersona(),1);
			this.dtoListaInformativa = bsdInstruccionesInterface.consultarInstruccciones(usuarioLogueado.getIdPersona(),2);
			
		}catch(Exception e){
			
			logger.error("<=================== Clase: MBInstrucciones , Método: actualizarInstrucciones");
			logger.error("<=================== Ocurrió un error al guardar las instrucciones");
			logger.error("<=================== USUARIO LOGUEADO: " + usuarioLogueado.getNombreUsuario());
			logger.error("",e);
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(	FacesMessage.SEVERITY_WARN,	Utilidades.mensajeProperties("mensaje_instrucciones_aceptarError"),	""));
			
		}
	}
	
	/**
	 * Método que elimina una instrucción de tipo Atención
	 *
	 * @param dtoInstrucciones: Objeto que será eliminado de las lista de instrucciones.
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void eliminarInstruccionAtencion(DTOInstruccionesEntity dtoInstrucciones){
		this.dtoListaAtencion.remove(dtoInstrucciones);
		//Se actualiza el valor de ordenamiento de cada instrucción
		bsdInstruccionesInterface.reOrdenar(this.dtoListaAtencion);
	}
	
	/**
	 * Método que elimina una instrucción de tipo Informativa
	 *
	 * @param dtoInstrucciones: Objeto que será eliminado de las lista de instrucciones.
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void eliminarInstruccionInformativa(DTOInstruccionesEntity dtoInstrucciones){
		this.dtoListaInformativa.remove(dtoInstrucciones);
		//Se actualiza el valor de ordenamiento de cada instrucción
		bsdInstruccionesInterface.reOrdenar(this.dtoListaInformativa);
	}
	
	/**
	 * Método limpia los campos en pantalla
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void limpiarCampos(){
		this.dtoInstrucciones.setConNotificacion(null);
		this.dtoInstrucciones.setTipoInstruccion(null);
		this.dtoInstrucciones.setDescripcion(null);
		this.dtoInstrucciones.setDiasRespuesta(null);
		this.bloqueaTiempo = true;
		this.bAceptar = false;
	}
	
	/**
	 * Método que habilita e inhabilita los campos para una instrucción Atención.
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void bloquearTiempo(){
		if(this.dtoInstrucciones.getTipoInstruccion()==1){
			this.bloqueaTiempo = false;
		}else{
			this.bloqueaTiempo = true;
		}
	}
	
	/**
	 * Método que cancela los cambios que aún no se guardan en la bd
	 * 
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void cancelarCambios(){
		limpiarCampos();
		iniciar();
	}
	
	/**
	 * Método que ordena la lista de instrucción de atención según el orden que estableció el usuario
	 * 
	 * * @param event: Evento que se realiza al ordenar una instrucción.
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/08/2017
	 */
	public void reOrdenarListaAtencion(ReorderEvent event){
		bsdInstruccionesInterface.reOrdenar(this.dtoListaAtencion);
	}
	
	/**
	 * Método que ordena la lista de instrucción informativa según el orden que estableció el usuario
	 * 
	 * * @param event: Evento que se realiza al ordenar una instrucción.
	 * 
	 * @author David Rodríguez Corral
	 * @since 04/08/2017
	 */
	public void reOrdenarListaInformativa(ReorderEvent event){
		bsdInstruccionesInterface.reOrdenar(this.dtoListaInformativa);
	}
	
	/**
	 * Método que deshabilita el boton Aceptar cuando el datatable está en modo editable
	 * 
	 * * @param event: Evento que inicia el modo edición.
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public void iniciarEdicion(RowEditEvent event){
		this.bAceptar = true;
		logger.info("Estás editando una instrucción");
		logger.info("El valor es "+ this.bAceptar);
	}
	
	/**
	 * Método que habilita el boton Aceptar cuando el datatble no está en modo editable
	 * 
	 * * @param event: Evento que inicia el modo edición.
	 * 
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public void terminarEdicion(RowEditEvent event){
		this.bAceptar = false;
		logger.info("Estás editando una instrucción");
		logger.info("El valor es "+ this.bAceptar);
	}
	
	/* ----------------------------------------------------------------------------------------- */
	/* ----------------------------------  FIN - EVENTOS --------------------------------------- */
	/* ----------------------------------------------------------------------------------------- */	
	
	
	/* ---------------------------------- GETTERS SETTERS -------------------------------------- */

	/**
	 * @return valor de tipo DTOInstrucciones que esta contenido en la variable dtoInstrucciones
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public DTOInstruccionesEntity getDtoInstrucciones() {
		return dtoInstrucciones;
	}

	/**
	 * @param dtoInstrucciones : valor que se ingresa a la variable de tipo DTOInstrucciones
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void setDtoInstrucciones(DTOInstruccionesEntity dtoInstrucciones) {
		this.dtoInstrucciones = dtoInstrucciones;
	}

	/**
	 * @return valor de tipo DTOInstrucciones que esta contenido en la variable dtoInstrucciones
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public List<DTOInstruccionesEntity> getDtoListaAtencion() {
		return dtoListaAtencion;
	}

	/**
	 * @param dtoListaAtencion : valor que se ingresa a la variable de tipo List<DTOInstrucciones>
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void setDtoListaAtencion(List<DTOInstruccionesEntity> dtoListaAtencion) {
		this.dtoListaAtencion = dtoListaAtencion;
	}

	/**
	 * @return valor de tipo List<DTOInstrucciones> que esta contenido en la variable dtoListaInformativa
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public List<DTOInstruccionesEntity> getDtoListaInformativa() {
		return dtoListaInformativa;
	}

	/**
	 * @param dtoListaInformativa : valor que se ingresa a la variable de tipo List<DTOInstrucciones>
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void setDtoListaInformativa(List<DTOInstruccionesEntity> dtoListaInformativa) {
		this.dtoListaInformativa = dtoListaInformativa;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable tipoArea
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public String getTipoArea() {
		return tipoArea;
	}

	/**
	 * @param tipoArea : valor que se ingresa a la variable de tipo String
	 *
	 * @author David Rodríguez Corral
	 * @since 14/08/2017
	 */
	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable bloqueaTiempo
	 *
	 * @author David Rodríguez Corral
	 * @since 22/08/2017
	 */
	public Boolean getBloqueaTiempo() {
		return bloqueaTiempo;
	}

	/**
	 * @param bloqueaTiempo : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 22/08/2017
	 */
	public void setBloqueaTiempo(Boolean bloqueaTiempo) {
		this.bloqueaTiempo = bloqueaTiempo;
	}
	
	/**
	 * @return valor de tipo Boolean que esta contenido en la variable bAceptar
	 *
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public Boolean getbAceptar() {
		return bAceptar;
	}

	/**
	 * @param bAceptar : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public void setbAceptar(Boolean bAceptar) {
		this.bAceptar = bAceptar;
	}

	/**
	 * @return valor de tipo Boolean que esta contenido en la variable contadorEdicion
	 *
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public Integer getContadorEdicion() {
		return contadorEdicion;
	}

	/**
	 * @param contadorEdicion : valor que se ingresa a la variable de tipo Boolean
	 *
	 * @author David Rodríguez Corral
	 * @since 07/09/2017
	 */
	public void setContadorEdicion(Integer contadorEdicion) {
		this.contadorEdicion = contadorEdicion;
	}

		

}
