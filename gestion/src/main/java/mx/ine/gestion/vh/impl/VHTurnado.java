/**
 * @(#)VHTurnado.java 09/01/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.bo.inter.BOTurnadoInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.mb.MBTurnado;
import mx.ine.gestion.util.Utilidades;
import mx.ine.gestion.vh.inter.VHTurnadoInterface;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de Turnado.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 09/01/2018
 * @update José Miguel Ortiz
 * @since 20/04/2018
 */
@Component("vhTurnado")
@Scope("prototype")
public class VHTurnado implements VHTurnadoInterface {
	@Autowired
	@Qualifier("boTurnado")
	private transient BOTurnadoInterface boTurnadoInterface; 
	
	/**
     * Objeto para el servicio de bitácora de mensajes de la aplicación.
     */
	private static final Logger log = Logger.getLogger(MBTurnado.class);

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHTurnadoInterface#inicializar(java.util.List, int, int)
	 */
	@Override
	public List<DTOTurnadoHelper> inicializar(List<DTOEstructuraAreasEntity> listaPersonasTurnado, int tamanioAtencion, int tamanioInformativas ) {
		List<DTOTurnadoHelper> listaTurnado = new ArrayList<DTOTurnadoHelper>();
		for (DTOEstructuraAreasEntity persona : listaPersonasTurnado) {
			DTOTurnadoHelper nuevo = new DTOTurnadoHelper();
			nuevo.setPersona(persona);
			nuevo.setInstruccionesAtencion(new boolean[tamanioAtencion]);
			
			nuevo.setInstruccionesInformativas(new boolean[tamanioInformativas]);
			listaTurnado.add(nuevo);
		}
		return listaTurnado;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHTurnadoInterface#inicializarHistoricos(java.util.List, java.util.List, int, int)
	 */
	@Override
	public List<DTOTurnadoHelper> inicializarHistoricos(List<DTOEstructuraAreasEntity> listaPersonasTurnado, List<DTOTurnadoInstruHelper> instruccionesHelper, int tamanioAtencion, int tamanioInformativas) {
		List<DTOTurnadoHelper> listaTurnado = new ArrayList<DTOTurnadoHelper>();
		int indice = -1;
		for (DTOEstructuraAreasEntity persona : listaPersonasTurnado) {
			DTOTurnadoHelper nuevo = new DTOTurnadoHelper();
			nuevo.setPersona(persona);
			nuevo.setInstruccionesAtencion(new boolean[tamanioAtencion]);
			nuevo.setInstruccionesInformativas(new boolean[tamanioInformativas]);
			indice = boTurnadoInterface.contienePersona(instruccionesHelper, persona);
			
			if( indice >= 0 && instruccionesHelper.size() > 0 && instruccionesHelper.get(indice).getListaInstruccionesAtencion().size() > 0){
				for (DTOInstruccionesEntity instruccion: instruccionesHelper.get(indice).getListaInstruccionesAtencion()) {
					nuevo.getInstruccionesAtencion()[instruccion.getOrdenamiento() - 1] = true;
				}
				
				nuevo.setInstruccionesAtencionRealizadas(boTurnadoInterface.crearNuevoArreglo(nuevo.getInstruccionesAtencion()));
				nuevo.setTieneIntruccionesAtencionRealizadas(true);
				
			}else if(indice >= 0 && instruccionesHelper.get(indice).getListaInstruccionesInfo().size() > 0){
				for (DTOInstruccionesEntity instruccion: instruccionesHelper.get(indice).getListaInstruccionesInfo()) {
					nuevo.getInstruccionesInformativas()[instruccion.getOrdenamiento() - 1] = true;
				}
				
				nuevo.setInstruccionesInformativasRealizadas(boTurnadoInterface.crearNuevoArreglo(nuevo.getInstruccionesInformativas()));
				nuevo.setTieneIntruccionesInfoRealizadas(true);
			}

			listaTurnado.add(nuevo);
		}
		return listaTurnado;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHTurnadoInterface#mostrarMensajeGrowl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto) {
		if(tipo.equals(Utilidades.mensajeProperties("constante_growl_info"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_advertencia"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, texto));

		} else if(tipo.equals(Utilidades.mensajeProperties("constante_growl_exito"))) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, texto));			
		}
	}
	
	@Override
	public void enviarError(Exception e, String clase, String metodo, String mensaje) {
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		log.error("<=================== Clase: " + clase + " , Método: "
				+ metodo);
		if (!mensaje.equals("")) {
			log.error("<=================== " + mensaje);
		}
		if (usuarioLogueado != null) {
			log.error("<=================== USUARIO LOGUEADO: "
					+ usuarioLogueado.getNombreUsuario());
		}
		log.error("", e);
	}

	public Integer buscarInstruccionPorOrdenamiento (int ordenamiento, List<DTOInstruccionesEntity> lista){
		for (DTOInstruccionesEntity instruccion : lista) {
			if(instruccion.getOrdenamiento().intValue() == ordenamiento) {
				return instruccion.getIdInstruccion();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param arregloInstrucciones
	 * @param listaInstrucciones
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public List<Integer> obtenerListaInstrucciones(boolean[] arregloInstrucciones, List<DTOInstruccionesEntity> listaInstrucciones){
		Integer ayuda;
		List<Integer> lista = new ArrayList<Integer>();
			for(int i = 0; i < arregloInstrucciones.length; i++ ) {
				if (arregloInstrucciones[i] == true) {
					ayuda = buscarInstruccionPorOrdenamiento(i + 1, listaInstrucciones);
					if (ayuda != null ) {
						lista.add(ayuda);
					}
				}
			}
		return lista;
	}

	/**
	 * Método encargado de crear un listado de instrucciones seleccionadas.
	 * @param String[] instruccionesSeleccionadas, List<DTOInstruccionesEntity> instruccionesPosibles
	 * @return List<Integer>: Lista de instrucciones seleccionadas
	 * @author José Miguel Ortiz
	 * @since 20/04/2018
	 */
	public List<Integer> determinarListaInstrucciones(String[] instruccionesSeleccionadas, List<DTOInstruccionesEntity> instruccionesPosibles) {
		List<Integer> listaInstrucciones = new ArrayList<Integer>();

		for(int pos=0; pos<instruccionesSeleccionadas.length; pos++ ) {
			for(DTOInstruccionesEntity instruccion : instruccionesPosibles) {
				if(instruccionesSeleccionadas[pos].equals(instruccion.getDescripcion())) {
					listaInstrucciones.add(instruccion.getIdInstruccion());
				}
			}
		}

		return listaInstrucciones;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.ine.gestion.vh.inter.VHTurnadoInterface#procesarPersonasTurnado(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public boolean procesarPersonasTurnado(List<DTOTurnadoHelper> listaPersonasTurnado, List<DTOInstruccionesEntity> listaInstruccionesAtencion, List<DTOInstruccionesEntity> listaInstruccionesInformativas) {
		List<Integer> auxInfo = new ArrayList<Integer>();
		List<Integer> auxAten = new ArrayList<Integer>();
		for (DTOTurnadoHelper personaListado : listaPersonasTurnado) {
			auxAten = obtenerListaInstrucciones(personaListado.getInstruccionesAtencion(), listaInstruccionesAtencion);
			auxInfo = obtenerListaInstrucciones(personaListado.getInstruccionesInformativas(), listaInstruccionesInformativas);
//			auxAten = determinarListaInstrucciones(personaListado.getInstruccionesAtencionSel(), listaInstruccionesAtencion);
//			auxInfo = determinarListaInstrucciones(personaListado.getInstruccionesInformativasSel(), listaInstruccionesInformativas);
			
			if (auxAten.size() != 0 && auxInfo.size() != 0) {
				return false;
			} else if(auxAten.size() != 0) {
				personaListado.setTipoInstrucciones("aten");
				personaListado.setIdsInstruccionesInsertar(auxAten);
				personaListado.setEnviarATurnar(true);
			} else if(auxInfo.size() != 0) {
				personaListado.setTipoInstrucciones("info");
				personaListado.setIdsInstruccionesInsertar(auxInfo);
				personaListado.setEnviarATurnar(true);
			} else {
				personaListado.setEnviarATurnar(false);
			}
		}
		return true;
	}

	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.vh.inter.VHTurnadoInterface#procesarPersonasTurnadoHistorico(java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public boolean procesarPersonasTurnadoHistorico(List<DTOTurnadoHelper> listaPersonasTurnado, List<DTOInstruccionesEntity> listaInstruccionesAtencion, List<DTOInstruccionesEntity> listaInstruccionesInformativas) {
		
		List<Integer> auxInfo = new ArrayList<Integer>();
		List<Integer> auxAten = new ArrayList<Integer>();
		
		for (DTOTurnadoHelper personaListado : listaPersonasTurnado) {
			auxAten = obtenerListaInstrucciones(personaListado.getInstruccionesAtencion(), listaInstruccionesAtencion);
			auxInfo = obtenerListaInstrucciones(personaListado.getInstruccionesInformativas(), listaInstruccionesInformativas);
			
			if (auxAten.size() != 0 && auxInfo.size() != 0) {
				return false;
				
			} else if(auxAten.size() != 0 && !personaListado.isTieneIntruccionesAtencionRealizadas()) {
				personaListado.setTipoInstrucciones("aten");
				personaListado.setIdsInstruccionesInsertar(auxAten);
				personaListado.setEnviarATurnar(true);
				
			}  else if(auxAten.size() != 0 && personaListado.isTieneIntruccionesAtencionRealizadas() ) {
				
				personaListado = boTurnadoInterface.consultarHayNuevasInstrucciones(personaListado, "atencion");
				auxAten = obtenerListaInstrucciones(personaListado.getInstruccionesAtencion(), listaInstruccionesAtencion);
				
				if(auxAten.size() > 0){
					personaListado.setTipoInstrucciones("aten");
					personaListado.setIdsInstruccionesInsertar(auxAten);
					personaListado.setEnviarATurnar(true);					
				}
			}else if(auxInfo.size() != 0 && !personaListado.isTieneIntruccionesInfoRealizadas()) {
				personaListado.setTipoInstrucciones("info");
				personaListado.setIdsInstruccionesInsertar(auxInfo);
				personaListado.setEnviarATurnar(true);
				
			} else if(auxInfo.size() != 0 && personaListado.isTieneIntruccionesInfoRealizadas()) {
				personaListado = boTurnadoInterface.consultarHayNuevasInstrucciones(personaListado, "info");
				auxInfo = obtenerListaInstrucciones(personaListado.getInstruccionesInformativas(), listaInstruccionesInformativas);
				
				if(auxInfo.size() > 0){
					personaListado.setTipoInstrucciones("info");
					personaListado.setIdsInstruccionesInsertar(auxInfo);
					personaListado.setEnviarATurnar(true);					
				}
				
			} else {
				personaListado.setEnviarATurnar(false);
			}
		}
		return true;
	}	
}
