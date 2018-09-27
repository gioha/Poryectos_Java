/**
 * @(#)VHTurnadoInterface.java 08/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.vh.inter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.db.DTOTurnInstruccionEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOCargaTurnadoInstruHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.ine.gestion.util.Utilidades;

/**
 * Interfaz que contiene la firma de los métodos que tienen la logica de negocio 
 * que únicamente llega a la primera capa la cual tiene que ver con el módulo de Turnado.
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 08/01/2018
 */
public interface VHTurnadoInterface {

	/**
	 * Método para inicializar la lista de personas para el turnado y arreglos para las instrucciones 
	 * 
	 * @param listaPersonasTurnado
	 * @param tamanioAtencion
	 * @param tamanioInformativas
	 * @return
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 10/01/2018
	 */
	public List<DTOTurnadoHelper> inicializar(List<DTOEstructuraAreasEntity> listaPersonasTurnado, int tamanioAtencion, int tamanioInformativas );
	
	
	public List<DTOTurnadoHelper> inicializarHistoricos(List<DTOEstructuraAreasEntity> listaPersonasTurnado, List<DTOTurnadoInstruHelper> instruccionesTurnadas, int tamanioAtencion, int tamanioInformativas );

	/**
	 * Método para mostrar mensajes
	 * 
	 * @param String tipo: tipo de mensaje que se va a mostrar
	 * @param String titulo: título que se quiera mostrar
	 * @param String texto: mensaje que se quiera mostrar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 13/11/2017
	 */
	public void mostrarMensajeGrowl(String tipo, String titulo, String texto);
	
	/**
	 * Método para mostrar los errores en consola con formato
	 * 
	 * @param e: Exception, excepción que originó el problema
	 * @param clase: String, clase en la que se lanzó la excepción
	 * @param metodo: String, método en el que se lanzó la excepción
	 * @param mensaje: String, mensaje adicional que se quiere mostrar
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 03/09/2017
	 */	
	public void enviarError(Exception e, String clase, String metodo, String mensaje);
	
	/**
	 * Método para ajustar las instrucciones en el dtoHelper
	 * 
	 * @param listaPersonasTurnado: Lista de las personas en el turnado
	 * @param listaInstruccionesAtencion: Lista con las instrucciones de atención
	 * @param listaInstruccionesInformativas: Lista con las instrucciones informativas
	 * 
	 * @return boolean: false en caso de que encuentre algún error
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 18/11/2017
	 */
	public boolean procesarPersonasTurnado(List<DTOTurnadoHelper> listaPersonasTurnado, 
			List<DTOInstruccionesEntity> listaInstruccionesAtencion, List<DTOInstruccionesEntity> listaInstruccionesInformativas);
	
	/**
	 * Método para la configuración de las instrucciones en el dtoHelper
	 * 
	 * @param listaPersonasTurnado
	 * @param instruccionesRealizadas
	 * @param listaInstruccionesAtencion
	 * @param listaInstruccionesInformativas
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 06/03/2018
	 */
	public boolean procesarPersonasTurnadoHistorico(List<DTOTurnadoHelper> listaPersonasTurnado, List<DTOInstruccionesEntity> listaInstruccionesAtencion, List<DTOInstruccionesEntity> listaInstruccionesInformativas);
}
